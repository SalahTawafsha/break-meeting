package com.example.break_meet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MyMeetingsActivity extends AppCompatActivity {
    private ListView list;
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private List<Meeting> all;
    private Meeting meeting;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_meetings);


        list = findViewById(R.id.myMeetingList);
        delete = findViewById(R.id.delete);

        fireStore.collection("meetings")
                .get().addOnCompleteListener(task -> {
                    all = task.getResult().toObjects(Meeting.class);

                    for (int i = 0; i < all.size(); i++) {
                        if (!all.get(i).getStudentId().equals(MainActivity.logInID))
                            all.remove(i--);
                    }

                    ArrayAdapter<Meeting> adapter = new ArrayAdapter<>(MyMeetingsActivity.this, android.R.layout.simple_spinner_item, all);

                    list.setAdapter(adapter);

                });

        list.setOnItemClickListener((adapterView, view, i, l) -> {
            meeting = all.get(i);
            delete.setEnabled(true);
        });
    }

    public void delete(View view) {
        fireStore.collection("meetings").get()
                .addOnCompleteListener(task -> {

                    for (QueryDocumentSnapshot o : task.getResult()) {
                        if (o.toObject(Meeting.class).equals(meeting)) {
                            fireStore.collection("meetings").document(o.getId()).delete();
                            return;
                        }
                    }
                });

        Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
    }


}