package com.example.break_meet;

import androidx.annotation.NonNull;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Meeting {
    private String type;
    private String placeName;
    private Timestamp fromTime;
    private String des;
    private String studentId;
    private String secondStudentId;

    public Meeting() {
    }

    public Meeting(String type, String placeName, Timestamp fromTime,
                   String des, String studentId) {
        super();
        this.type = type;
        this.placeName = placeName;
        this.fromTime = fromTime;
        this.des = des;
        this.studentId = studentId;
        secondStudentId = "";
    }

    public String getSecondStudentId() {
        return secondStudentId;
    }

    public void setSecondStudentId(String secondStudentId) {
        this.secondStudentId = secondStudentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getFromTime() {
        Date date = new Date(fromTime.getSeconds() * 1000 + fromTime.getNanoseconds());

        return new SimpleDateFormat("dd-MM-yyyy").format(date) +" at "+ new SimpleDateFormat("HH:MM").format(date);
    }

    public void setFromTime(Timestamp fromTime) {
        this.fromTime = fromTime;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @NonNull
    @Override
    public String toString() {
        Date date = new Date(fromTime.getSeconds() * 1000);
        return placeName +
                ", " + date +
                ", " + des +
                ", " + type +
                ", " + studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return type.equals(meeting.type) && placeName.equals(meeting.placeName) && fromTime.equals(meeting.fromTime) && des.equals(meeting.des) && studentId.equals(meeting.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, placeName, fromTime, des, studentId);
    }
}
