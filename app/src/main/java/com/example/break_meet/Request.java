package com.example.break_meet;

import androidx.annotation.NonNull;

public class Request {
    private String fromStudent;
    private String toStudent;


    public Request() {
    }

    public Request(String fromStudent, String toStudent) {
        this.fromStudent = fromStudent;
        this.toStudent = toStudent;
    }

    public String getFromStudent() {
        return fromStudent;
    }

    public void setFromStudent(String fromStudent) {
        this.fromStudent = fromStudent;
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
                ", " + toStudent;
    }
}
