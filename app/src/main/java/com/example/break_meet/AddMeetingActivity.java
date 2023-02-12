package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity {
    private Spinner place;
    private EditText time;
    private TextView date;
    private EditText description;

    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        place = findViewById(R.id.place);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        description = findViewById(R.id.description);

        fireStore.collection("places")
                .get().addOnCompleteListener(task -> {
                    List<Place> all = task.getResult().toObjects(Place.class);

                    ArrayAdapter<Place> adapter = new ArrayAdapter<>(AddMeetingActivity.this, android.R.layout.simple_spinner_item, all);

                    place.setAdapter(adapter);
                });

    }

    @SuppressLint("SimpleDateFormat")
    public void add(View view) {
        if (validate()) {
            String[] tokens = time.getText().toString().split(":");
            if (tokens.length != 2) {
                Toast.makeText(this, "time must be as HH:MM", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                Date date = Objects.requireNonNull(new SimpleDateFormat("dd-MM-yyyy").parse(this.date.getText().toString()));
                date.setHours(Integer.parseInt(tokens[0]));
                date.setMinutes(Integer.parseInt(tokens[1]));

                fireStore.collection("meetings")
                        .add(new Meeting(
                                ((Place) place.getSelectedItem()).getType()
                                , ((Place) place.getSelectedItem()).getName()
                                , new Timestamp(date), description.getText().toString()
                                , MainActivity.logInID + ""))
                        .addOnSuccessListener(documentReference -> Toast.makeText(this, "Added!", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(this, "Can't add", Toast.LENGTH_SHORT).show());
            } catch (ParseException e) {
                Toast.makeText(this, "You Must fill All data !", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "time must be integers as HH:MM!", Toast.LENGTH_SHORT).show();
            }
        } else
            Toast.makeText(this, "You MUST fill All data !", Toast.LENGTH_SHORT).show();


    }

    private boolean validate() {
        return !time.getText().toString().trim().isEmpty() && !date.getText().toString().trim().isEmpty()
                && !description.getText().toString().trim().isEmpty();
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