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

public class RequestToMeActivity extends AppCompatActivity {
    private RecyclerView list;
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private static String meetingID;
    private static Button approval;
    private static Button reject;
    private static int selectedIndex;
    private final ArrayList<Meeting> values = new ArrayList<>();
    private static final ArrayList<String> keys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_to_me);


        list = findViewById(R.id.requests);
        approval = findViewById(R.id.approval);
        reject = findViewById(R.id.reject);


        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        fill();

    }

    private void fill() {

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.login)
                , Context.MODE_PRIVATE);
        fireStore.collection("request").whereEqualTo("toStudent", sharedPref.getString("logInID", ""))
                .get().addOnCompleteListener(task -> {

                    for (DocumentSnapshot document : task.getResult().getDocuments()) {
                        String s = document.getId();
                        fireStore.collection("meetings").document(document.get("meetingID").toString()).get()
                                .addOnSuccessListener(e -> {
                                    Meeting m = e.toObject(Meeting.class);
                                    keys.add(s + "$" + document.get("fromStudent").toString() + "$" + e.getId());

                                    m.setStudentId(document.get("fromStudent").toString());
                                    values.add(m);
                                    RequestAdapter adapter = new RequestAdapter(values);
                                    list.setAdapter(adapter);
                                });
                    }


                });
    }

    public void reject(View view) {
        String[] tokens = meetingID.split("[$]");
        fireStore.collection("request").document(tokens[0]).delete();
        Toast.makeText(this, "Rejected", Toast.LENGTH_SHORT).show();
        values.remove(selectedIndex);
        RequestAdapter adapter = new RequestAdapter(values);
        list.setAdapter(adapter);
        reject.setEnabled(false);
    }

    public void approval(View view) {
        String[] tokens = meetingID.split("[$]");
        fireStore.collection("meetings").document(tokens[2])
                .update("secondStudentId", tokens[1]);

        fireStore.collection("request").whereEqualTo("meetingID", tokens[2])
                .get().addOnCompleteListener(task -> {

                    for (DocumentSnapshot document : task.getResult().getDocuments()) {
                        fireStore.collection("request").document(document.getId()).delete();
                    }

                    values.clear();
                    keys.clear();
                    RequestAdapter adapter = new RequestAdapter(values);
                    list.setAdapter(adapter);
                    fill();
                    approval.setEnabled(false);
                    Toast.makeText(this, "Approved", Toast.LENGTH_SHORT).show();
                });
    }

    public static void setMeetingID(String meetingID) {
        RequestToMeActivity.meetingID = meetingID;
    }

    public static Button getReject() {
        return reject;
    }

    public static Button getApproval() {
        return approval;
    }

    public static void setSelectedIndex(int selectedIndex) {
        RequestToMeActivity.selectedIndex = selectedIndex;
    }

    public static ArrayList<String> getKeys() {
        return keys;
    }
}