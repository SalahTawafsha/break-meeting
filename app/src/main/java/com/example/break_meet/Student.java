package com.example.break_meet;


import androidx.annotation.NonNull;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Student {
    private String id_student;
    private String name;
    private String password;
    private String gender;
    private Timestamp date;

    public Student() {
        super();
    }

    public Student(String id_student, String password, String name, String gender, Timestamp date) {
        super();
        this.id_student = id_student;
        this.name = name;
        this.gender = gender;
        this.date = date;
        this.password = password;
    }

    public Student(String id_student, String password) {
        this.id_student = id_student;
        this.password = password;
    }

    public String getId_student() {
        return id_student;
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getDate() {
        Date date = new Date(this.date.getSeconds() * 1000 + this.date.getNanoseconds());

        return new SimpleDateFormat("dd-MM-yyyy").format(date) +" at "+ new SimpleDateFormat("HH:MM").format(date);
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        return "Student [Id_student=" + id_student + ", name=" + name + ", password=" + password + ", gender=" + gender + ", date=" + date
                + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student)
            return Objects.equals(((Student) obj).getId_student(), this.getId_student());
        return super.equals(obj);
    }


}

