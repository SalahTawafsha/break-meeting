package com.example.break_meet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MyMeetingsActivity extends AppCompatActivity {
    private ListView list;
    private FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private List<Meeting> all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_meetings);


        list = findViewById(R.id.myMeetingList);

        fireStore.collection("meetings")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        all = task.getResult().toObjects(Meeting.class);


                        ArrayAdapter<Meeting> adapter = new ArrayAdapter<>(MyMeetingsActivity.this, android.R.layout.simple_spinner_item, all);

                        list.setAdapter(adapter);

                    }

                });

        list.setOnItemClickListener((adapterView, view, i, l) -> {
            Log.e("MMyError", all.get(i).toString());

        });
    }

    public void delete(View view) {
    }
}