package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AddPlaceActivity extends AppCompatActivity {
    private EditText placeName;
    private Spinner type;

    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        placeName = findViewById(R.id.placeName);
        type = findViewById(R.id.typeOfPlace);
    }

    public void add(View view) {

        fireStore.collection("places").add(new Place(placeName.getText().toString(), type.getSelectedItem().toString()))
                .addOnSuccessListener(e -> Toast.makeText(this, "Successful!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Place ID is already exist!", Toast.LENGTH_SHORT).show());
    }
}