package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if ("1200339".equals(MainActivity.logInID)) {
            Button addPlace = findViewById(R.id.addPlace);
            addPlace.setVisibility(View.VISIBLE);
        }
    }

    public void add(View view) {
        Intent intent = new Intent(this, AddMeetingActivity.class);
        startActivity(intent);
    }

    public void select(View view) {
        Intent intent = new Intent(this, SelectMeetingActivity.class);
        startActivity(intent);
    }

    public void myMeetings(View view) {
        Intent intent = new Intent(this, MyMeetingsActivity.class);
        startActivity(intent);
    }

    public void updateProfile(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.putExtra("type", "update");
        startActivity(intent);
    }

    public void addPlace(View view) {
        Intent intent = new Intent(this, AddPlaceActivity.class);
        startActivity(intent);
    }

    public void showRequests(View view) {
        Intent intent = new Intent(this, RequestToMeActivity.class);
        startActivity(intent);
    }
}