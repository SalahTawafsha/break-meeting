package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class SelectMeetingActivity extends AppCompatActivity {
    private ListView list;
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private List<Meeting> all;
    private String meetingID;
    private Meeting meet;
    private Button select;
    private List<String> keys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_meeting);


        list = findViewById(R.id.myMeetingList);
        select = findViewById(R.id.select);

        fireStore.collection("meetings").whereNotEqualTo("studentId", MainActivity.logInID)
                .whereEqualTo("secondStudentId", "")
                .get().addOnCompleteListener(task -> {
                    all = task.getResult().toObjects(Meeting.class);

                    for (DocumentSnapshot document : task.getResult().getDocuments()) {
                        keys.add(document.getId());
                    }

                    Log.e("TEESSSTT", keys.toString());


                    ArrayAdapter<Meeting> adapter = new ArrayAdapter<>(SelectMeetingActivity.this, android.R.layout.simple_spinner_item, all);

                    list.setAdapter(adapter);

                });

        list.setOnItemClickListener((adapterView, view, i, l) -> {
            meetingID = keys.get(i);
            meet = all.get(i);
            Log.e("TEESSSTT", meetingID);
            select.setEnabled(true);
        });
    }

    public void selectMeeting(View view) {
        fireStore.collection("request").add(new Request(MainActivity.logInID, meet.getStudentId(), meetingID))
                .addOnSuccessListener(runnable -> Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(runnable -> Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show());
    }
}