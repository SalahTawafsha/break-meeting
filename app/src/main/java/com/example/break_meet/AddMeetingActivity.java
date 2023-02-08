package com.example.break_meet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.HashMap;

public class AddMeetingActivity extends AppCompatActivity {
    private Spinner type;
    private Spinner place;
    private EditText time;
    private TextView date;
    private EditText description;
    private HashMap<String, Meeting> map = new HashMap<>();

    private FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        type = findViewById(R.id.type);
        place = findViewById(R.id.place);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        description = findViewById(R.id.description);

        fireStore.collection("places")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<Place> all = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                all.add(new Place(document.getData().get("name").toString(), document.getData().get("type").toString()));
                            }
                            for (int i = 0; i < all.size(); i++) {
                                Log.d("hi shella", all.get(i).toString());
                            }
                        }
                    }

                });

    }

    public void add(View view) {
        map.clear();

//        map.put(id.getText().toString(), new Student(Integer.parseInt(id.getText().toString()), firstPass.getText().toString(), name.getText().toString(),
//                (gender.getCheckedRadioButtonId() == R.id.male) ? "M" : "F", date.getText().toString()));
//        fireStore.collection("break_meet").document("student").set(map)
//                .addOnSuccessListener(e -> Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show())
//                .addOnFailureListener(e -> Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show());

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