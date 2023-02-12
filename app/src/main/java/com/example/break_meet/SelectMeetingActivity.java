package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class SelectMeetingActivity extends AppCompatActivity {
    private RecyclerView list;
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private List<Meeting> all;
    private static Button select;
    private final List<String> keys = new ArrayList<>();
    private static int index;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_meeting);


        list = findViewById(R.id.myMeetingList);
        select = findViewById(R.id.select);

        sharedPref = getSharedPreferences(
                getString(R.string.login)
                , Context.MODE_PRIVATE);

        fireStore.collection("meetings").whereNotEqualTo("studentId", sharedPref.getString("logInID", ""))
                .whereEqualTo("secondStudentId", "")
                .get().addOnCompleteListener(task -> {
                    all = task.getResult().toObjects(Meeting.class);

                    for (DocumentSnapshot document : task.getResult().getDocuments())
                        keys.add(document.getId());


                    list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                    MeetingAdapter adapter = new MeetingAdapter(all, MeetingAdapter.SELECT_MEETING);

                    list.setAdapter(adapter);

                });

    }

    public void selectMeeting(View view) {
        String meetingID = keys.get(index);
        Meeting meet = all.get(index);


        fireStore.collection("request").add(new Request(sharedPref.getString("logInID", ""), meet.getStudentId(), meetingID))
                .addOnSuccessListener(runnable -> {
                    Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show();
                    select.setEnabled(false);
                })
                .addOnFailureListener(runnable -> Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show());
    }

    public static Button getSelect() {
        return select;
    }

    public static void setIndex(int index) {
        SelectMeetingActivity.index = index;
    }
}