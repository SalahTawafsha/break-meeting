package com.example.break_meet;

import androidx.annotation.NonNull;

public class Request {
    private String fromStudent;
    private String toStudent;
    private String meetingID;


    public Request() {
    }

    public Request(String fromStudent, String toStudent, String meetingID) {
        this.fromStudent = fromStudent;
        this.toStudent = toStudent;
        this.meetingID = meetingID;
    }

    public String getFromStudent() {
        return fromStudent;
    }

    public void setFromStudent(String fromStudent) {
        this.fromStudent = fromStudent;
    }

    public String getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(String meetingID) {
        this.meetingID = meetingID;
    }

    public String getToStudent() {
        return toStudent;
    }

    public void setToStudent(String toStudent) {
        this.toStudent = toStudent;
    }

    @NonNull
    @Override
    public String toString() {
        return fromStudent +
                ", " + meetingID;
    }
}
