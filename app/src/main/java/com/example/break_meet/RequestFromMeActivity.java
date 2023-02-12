package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class RequestFromMeActivity extends AppCompatActivity {
    private RecyclerView list;
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private List<Meeting> all = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_from_me);


        list = findViewById(R.id.myRequests);


        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        fill();

    }

    private void fill() {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.login)
                , Context.MODE_PRIVATE);

        fireStore.collection("meetings").whereEqualTo("secondStudentId", sharedPref.getString("logInID", ""))
                .get().addOnCompleteListener(task -> {
                    all = task.getResult().toObjects(Meeting.class);

                    list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                    MeetingAdapter adapter = new MeetingAdapter(all, MeetingAdapter.APPROVAL_REQUEST);

                    list.setAdapter(adapter);

                });
    }

}