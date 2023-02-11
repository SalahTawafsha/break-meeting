package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private EditText id;
    private EditText password;

    static String logInID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);

    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.putExtra("type", "add");
        startActivity(intent);

    }

    public void logIn(View view) {
        fireStore.collection("students").whereEqualTo("id_student", id.getText().toString())
                .get().addOnCompleteListener(task -> {
                    List<Student> list = task.getResult().toObjects(Student.class);

                    if (list.size() > 0) {
                        Student s = list.get(0);
                        if (s.getPassword().equals(password.getText().toString())) {
                            Intent intent = new Intent(this, HomeActivity.class);
                            logInID = id.getText().toString();
                            startActivity(intent);
                        } else
                            Toast.makeText(this, "Uncorrected Password!", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(this, "ID NOT exist!", Toast.LENGTH_SHORT).show();

                });
    }
}