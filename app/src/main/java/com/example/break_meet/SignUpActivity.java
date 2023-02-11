package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

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

        if (getIntent().getStringExtra("type").equals("update")) {
            add.setText(R.string.update);
            id.setText(MainActivity.logInID);
            fireStore.collection("students").whereEqualTo("id_student",MainActivity.logInID)
                    .get().addOnCompleteListener(task->{
                       s = task.getResult().toObjects(Student.class).get(0);
                       ID = task.getResult().getDocuments().get(0).getId();
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
        date.setText(s.getDate());

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
        if (getIntent().getStringExtra("type").equals("update")) {
            if (firstPass.getText().toString().equals(s.getPassword())) {
                s = new Student(id.getText().toString(),
                        (!secondPass.getText().toString().isEmpty()) ? secondPass.getText().toString()
                                : s.getPassword(), name.getText().toString(),
                        (gender.getCheckedRadioButtonId() == R.id.male) ? "M" : "F", date.getText().toString());

                fireStore.collection("students").document(ID)
                        .set(s);
                
                Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show();
            } else if (firstPass.getText().toString().isEmpty())
                Toast.makeText(this, "Enter Old password to save", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Uncorrected Password", Toast.LENGTH_SHORT).show();

        } else {

            fireStore.collection("students").add(new Student(id.getText().toString(), firstPass.getText().toString(), name.getText().toString(),
                            (gender.getCheckedRadioButtonId() == R.id.male) ? "M" : "F", date.getText().toString()))
                    .addOnSuccessListener(e -> Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show());
        }
    }

}