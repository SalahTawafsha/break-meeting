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
    private EditText placeID;
    private static int numOfObjects;

    private FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private HashMap<String, Place> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        placeName = findViewById(R.id.placeName);
        type = findViewById(R.id.typeOfPlace);
        placeID = findViewById(R.id.placeId);
    }

    public void add(View view) {

        map.put(placeID.getText().toString() + "", new Place(placeName.getText().toString(), type.getSelectedItem().toString()));

        fireStore.collection("places").document(placeID.getText().toString()).set(map)
                .addOnSuccessListener(e -> {
                    Toast.makeText(this, "Successful!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Place ID is already exist!", Toast.LENGTH_SHORT).show();
                });
    }
}