package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class SelectMeetingActivity extends AppCompatActivity {
    private ListView list;
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private List<Meeting> all;
    private Meeting meeting;
    private Button select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_meeting);


        list = findViewById(R.id.myMeetingList);
        select = findViewById(R.id.select);

        fireStore.collection("meetings")
                .get().addOnCompleteListener(task -> {
                    all = task.getResult().toObjects(Meeting.class);

                    for (int i = 0; i < all.size(); i++) {
                        if (all.get(i).getStudentId().equals(MainActivity.logInID))
                            all.remove(i--);
                    }

                    ArrayAdapter<Meeting> adapter = new ArrayAdapter<>(SelectMeetingActivity.this, android.R.layout.simple_spinner_item, all);

                    list.setAdapter(adapter);

                });

        list.setOnItemClickListener((adapterView, view, i, l) -> {
            meeting = all.get(i);
            select.setEnabled(true);
        });
    }

    public void selectMeeting(View view) {
        fireStore.collection("request").add(new Request(MainActivity.logInID, meeting.getStudentId()))
                .addOnSuccessListener(runnable -> Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(runnable -> Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show());
    }
}