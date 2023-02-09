package com.example.break_meet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity {
    private Spinner place;
    private EditText time;
    private TextView date;
    private EditText description;
    private HashMap<String, Meeting> map = new HashMap<>();

    private FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        place = findViewById(R.id.place);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        description = findViewById(R.id.description);

        fireStore.collection("places")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Place> all = task.getResult().toObjects(Place.class);

                        ArrayAdapter<Place> adapter = new ArrayAdapter<>(AddMeetingActivity.this, android.R.layout.simple_spinner_item, all);

                        place.setAdapter(adapter);
                    }

                });

    }

    public void add(View view) {
        map.clear();

        fireStore.collection("meetings")
                .add(new Meeting(
                        ((Place) place.getSelectedItem()).getType()
                        , ((Place) place.getSelectedItem()).getName()
                        , time.getText().toString(), date.getText().toString()
                        , description.getText().toString(), MainActivity.logInID + ""))
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Added!", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> {
                    Toast.makeText(this, "Can't add", Toast.LENGTH_SHORT).show();
                });

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
}