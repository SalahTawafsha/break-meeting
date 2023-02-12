package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class RequestFromMeActivity extends AppCompatActivity {
    private RecyclerView list;
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private static String meetingID;
    private static int selectedIndex;
    private List<Meeting> all = new ArrayList<>();
    private static final ArrayList<String> keys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_from_me);


        list = findViewById(R.id.myRequests);


        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        fill();

    }

    private void fill() {

        fireStore.collection("meetings").whereEqualTo("secondStudentId", MainActivity.logInID)
                .get().addOnCompleteListener(task -> {
                    all = task.getResult().toObjects(Meeting.class);

                    for (DocumentSnapshot document : task.getResult().getDocuments())
                        keys.add(document.getId());


                    list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                    MeetingAdapter adapter = new MeetingAdapter(all,true);

                    list.setAdapter(adapter);

                });
    }

    public static void setMeetingID(String meetingID) {
        RequestFromMeActivity.meetingID = meetingID;
    }

    public static void setSelectedIndex(int selectedIndex) {
        RequestFromMeActivity.selectedIndex = selectedIndex;
    }

    public static ArrayList<String> getKeys() {
        return keys;
    }

}