package com.example.break_meet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class Calender extends AppCompatActivity {
    DatePicker calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callender);

        calendarView = findViewById(R.id.calendarView2);

    }

    public void selectDate(View view) {
        String date = calendarView.getDayOfMonth() + "/" + (calendarView.getMonth()+1) + "/" + calendarView.getYear();
        Intent intent = new Intent();
        intent.putExtra("date", date);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}