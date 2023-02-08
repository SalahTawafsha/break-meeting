package com.example.break_meet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    EditText id;
    EditText password;

    static String logInID;

    Map<String, String> map = new HashMap<>();

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
        fireStore.collection("break_meet").document(id.getText().toString())
                .get().addOnSuccessListener(e -> {
                    if (e.get(id.getText().toString()) != null) {
                        String studentPass = getStudent(e.get(id.getText().toString()).toString());
                        if (studentPass.equals(password.getText().toString())) {
                            Intent intent = new Intent(this, HomeActivity.class);
                            logInID = id.getText().toString();
                            startActivity(intent);
                        } else
                            Toast.makeText(this, "Uncorrected Password!", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(this, "ID NOT exist!", Toast.LENGTH_SHORT).show();

                });
    }

    private String getStudent(String str) {
        Log.e("MyDebug", str);
        String[] tokens = str.split("[=,]");
        return tokens[5];
    }
}