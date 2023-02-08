package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;

public class Calender extends AppCompatActivity {
    DatePicker calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callender);

        calendarView = findViewById(R.id.calendarView2);

        calendarView.setOnDateChangedListener((calendarView, year, month, day) -> {
            String date = day + "/" + month + "/" + year;
            Intent intent = new Intent();
            intent.putExtra("date", date);
            setResult(Activity.RESULT_OK,intent);
            finish();
        });
    }
}