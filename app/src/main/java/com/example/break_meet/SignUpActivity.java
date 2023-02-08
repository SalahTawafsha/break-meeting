package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private TextView date;
    private EditText name;
    private EditText id;
    private RadioGroup gender;
    RadioButton male;
    RadioButton female;
    private EditText firstPass;
    private EditText secondPass;
    Student s;
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private final HashMap<String, Student> map = new HashMap<>();

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
            fireStore.collection("break_meet").document("student")
                    .get().addOnSuccessListener(e -> {
                        Log.e("MYDebug", e.get(id.getText().toString()).toString());
                        fillData(e.get(id.getText().toString()));
                    });
        }

    }

    private void fillData(Object o) {
        String[] tokens = o.toString().split("[=,]");

        name.setText(tokens[9]);
        if (tokens[7].equals("M"))
            male.setChecked(true);
        else
            female.setChecked(true);
        date.setText(tokens[3]);

        firstPass.setHint("Old Password");
        secondPass.setHint("New Password");

        s = new Student(Integer.parseInt(id.getText().toString()), tokens[5]);

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
                s = new Student(Integer.parseInt(id.getText().toString()),
                        (!secondPass.getText().toString().isEmpty()) ? secondPass.getText().toString() : s.getPassword(), name.getText().toString(),
                        (gender.getCheckedRadioButtonId() == R.id.male) ? "M" : "F", date.getText().toString());
                fireStore.collection("break_meet").document("student")
                        .update(id.getText().toString(), s);
                Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show();
            }
        } else {


            map.clear();

            if (firstPass.getText().toString().equals(secondPass.getText().toString())) {
                map.put(id.getText().toString(), new Student(Integer.parseInt(id.getText().toString()), firstPass.getText().toString(), name.getText().toString(),
                        (gender.getCheckedRadioButtonId() == R.id.male) ? "M" : "F", date.getText().toString()));
                fireStore.collection("break_meet").document("student").set(map)
                        .addOnSuccessListener(e -> Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show());
            }
        }
    }
}