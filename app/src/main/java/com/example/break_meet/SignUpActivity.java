package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    private TextView date;
    private EditText name;
    private EditText id;
    private RadioGroup gender;
    private RadioButton male;
    private RadioButton female;
    private EditText firstPass;
    private EditText secondPass;
    private Student s;
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private String ID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        date = findViewById(R.id.editTextDate);
        name = findViewById(R.id.studentName);
        id = findViewById(R.id.studentId);
        gender = findViewById(R.id.gender);
        firstPass = findViewById(R.id.firstPassword);
        secondPass = findViewById(R.id.secondPassword);
        Button add = findViewById(R.id.button3);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.login)
                , Context.MODE_PRIVATE);

        if (getIntent().getStringExtra("type").equals("update")) {
            add.setText(R.string.update);
            id.setText(sharedPref.getString("logInID", ""));
            fireStore.collection("students").whereEqualTo("id_student", sharedPref.getString("logInID", ""))
                    .get().addOnCompleteListener(task -> {
                        s = task.getResult().toObjects(Student.class).get(0);
                        ID = task.getResult().getDocuments().get(0).getId();
                        id.setEnabled(false);
                        fillData();
                    });
        }

    }

    private void fillData() {

        name.setText(s.getName());
        if ("M".equals(s.getGender()))
            male.setChecked(true);
        else
            female.setChecked(true);
        date.setText(s.getIsoDate());

        firstPass.setHint("Old Password");
        secondPass.setHint("New Password");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                date.setText(data.getStringExtra("date"));
            }
        }
    }

    public void openCal(View view) {
        Intent intent = new Intent(this, Calender.class);
        startActivityForResult(intent, 1);
    }

    public void add(View view) {
        if (getIntent().getStringExtra("type").equals("update"))
           update();
        else
            add();
    }

    @SuppressLint("SimpleDateFormat")
    private void update() {
        if (!name.getText().toString().trim().isEmpty()) {
            if (firstPass.getText().toString().equals(s.getPassword())) {
                try {
                     Date date = Objects.requireNonNull(new SimpleDateFormat("dd/MM/yyyy").parse(this.date.getText().toString()));

                    s = new Student(id.getText().toString(),
                            (!secondPass.getText().toString().isEmpty()) ? secondPass.getText().toString()
                                    : s.getPassword(), name.getText().toString(),
                            (gender.getCheckedRadioButtonId() == R.id.male) ? "M" : "F", new Timestamp(date));

                    fireStore.collection("students").document(ID).set(s);

                    Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show();
                } catch (ParseException ignored) {
                }
            } else if (firstPass.getText().toString().isEmpty())
                Toast.makeText(this, "Enter old password to update", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Uncorrected Password", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Name can't be empty!", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SimpleDateFormat")
    private void add() {

        fireStore.collection("students").whereEqualTo("id_student", id.getText().toString())
                .get().addOnCompleteListener(task -> {
                    if (task.getResult().getDocuments().size() > 0) {
                        id.setText("");
                        Toast.makeText(SignUpActivity.this, "ID already exist !", Toast.LENGTH_SHORT).show();
                    } else {
                        if (validateAll()) {
                            try {
                                Date date = Objects.requireNonNull(new SimpleDateFormat("dd-MM-yyyy").parse(this.date.getText().toString()));
                                if (firstPass.getText().toString().equals(secondPass.getText().toString())) {
                                    fireStore.collection("students").add(new Student(id.getText().toString(), firstPass.getText().toString(), name.getText().toString(),
                                                    (gender.getCheckedRadioButtonId() == R.id.male) ? "M" : "F", new Timestamp(date)))
                                            .addOnSuccessListener(e -> Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show())
                                            .addOnFailureListener(e -> Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show());
                                    finish();
                                } else
                                    Toast.makeText(this, "Passwords doesn't match!", Toast.LENGTH_SHORT).show();
                            } catch (ParseException e) {
                                Toast.makeText(this, "You must fill all field!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "You must fill all field!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private boolean validateAll() {
        return !id.getText().toString().trim().isEmpty() && !name.getText().toString().trim().isEmpty() &&
                !date.getText().toString().trim().isEmpty() && !firstPass.getText().toString().trim().isEmpty()
                && !secondPass.getText().toString().trim().isEmpty() && (male.isChecked() || female.isChecked());
    }

}