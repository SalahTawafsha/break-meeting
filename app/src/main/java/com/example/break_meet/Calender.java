package com.example.break_meet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

public class Calender extends AppCompatActivity {
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callender);

        calendarView = findViewById(R.id.calendarView2);

        calendarView.setOnDateChangeListener((calendarView, year, month, day) -> {
            String date = day + "/" + month + "/" + year;
            Intent intent = new Intent();
            intent.putExtra("date", date);
            setResult(Activity.RESULT_OK,intent);
            finish();
        });
    }
}