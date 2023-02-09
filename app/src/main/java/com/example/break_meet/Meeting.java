package com.example.break_meet;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Meeting {
    private String type;
    private String placeName;
    private String fromTime;
    private String date;
    private String des;
    private String studentId;

    public Meeting() {
    }

    public Meeting(String type, String placeName, String fromTime, String date,
                   String des, String studentId) {
        super();
        this.type = type;
        this.placeName = placeName;
        this.fromTime = fromTime;
        this.date = date;
        this.des = des;
        this.studentId = studentId;
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
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return placeName +
                ", " + date +
                ", " + fromTime +
                ", " + des +
                ", " + type +
                ", " + studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return type.equals(meeting.type) && placeName.equals(meeting.placeName) && fromTime.equals(meeting.fromTime) && date.equals(meeting.date) && des.equals(meeting.des) && studentId.equals(meeting.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, placeName, fromTime, date, des, studentId);
    }
}
