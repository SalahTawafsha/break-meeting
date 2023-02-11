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

public class RequestToMeActivity extends AppCompatActivity {
    private ListView list;
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private String meetingID;
    private Button approval;
    private Button reject;
    private List<String> values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_to_me);


        list = findViewById(R.id.myMeetingList);
        approval = findViewById(R.id.approval);
        reject = findViewById(R.id.reject);

        fireStore.collection("request").whereEqualTo("toStudent", MainActivity.logInID)
                .get().addOnCompleteListener(task -> {

                    for (DocumentSnapshot document : task.getResult().getDocuments()) {
                        Log.e("Salah", document.get("meetingID").toString());
                        fireStore.collection("meetings").document(document.get("meetingID").toString()).get()
                                .addOnCompleteListener(e -> {
                                    Log.e("Meeting", e.getResult().toString());
                                    Meeting m = e.getResult().toObject(Meeting.class);
                                    Log.e("Meeting", m.toString());
                                    values.add(document.get("fromStudent").toString() + " in " + m.getPlaceName() + " at "
                                            + m.getDate() + ", " + m.getFromTime());
                                });
                    }

                    Log.e("TEXTT", values.toString());

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(RequestToMeActivity.this, android.R.layout.simple_spinner_item, values);
                    list.setAdapter(adapter);

                });

        list.setOnItemClickListener((adapterView, view, i, l) -> {
            meetingID = values.get(i);
            Log.e("TEESSSTT", meetingID);
            approval.setEnabled(true);
            reject.setEnabled(true);
        });
    }

    public void reject(View view) {
//        fireStore.collection("request").add(new Request(MainActivity.logInID, meetingID))
//                .addOnSuccessListener(runnable -> Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show())
//                .addOnFailureListener(runnable -> Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show());
    }

    public void approval(View view) {
//        fireStore.collection("request").add(new Request(MainActivity.logInID, meetingID))
//                .addOnSuccessListener(runnable -> Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show())
//                .addOnFailureListener(runnable -> Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show());
    }
}