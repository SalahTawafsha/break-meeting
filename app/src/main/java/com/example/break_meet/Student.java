package com.example.break_meet;


import androidx.annotation.NonNull;

public class Student {
	private int Id_student;
	private String name;
	private String password;

	private String gender;
	private String date;
	private int visStudentId;

	public Student() {
		super();
	}

	public Student(int id_student,String password, String name, String gender, String date,int visStudentId) {
		super();
		Id_student = id_student;
		this.name = name;
		this.gender = gender;
		this.date = date;
		this.visStudentId=visStudentId;
		this.password=password;
	}
	public Student(int id_student,String password, String name, String gender, String date) {
		super();
		Id_student = id_student;
		this.name = name;
		this.gender = gender;
		this.date = date;
		this.password=password;
	}

	public Student(int id_student,String password) {
		Id_student = id_student;
		this.password=password;
	}

	public int getId_student() {
		return Id_student;
	}

	public void setId_student(int id_student) {
		Id_student = id_student;
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
	public int getVisStudentId() {
		return visStudentId;
	}

	public void setVisStudentId(int visStudentId) {
		this.visStudentId = visStudentId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@NonNull
	@Override
	public String toString() {
		return "Student [Id_student=" + Id_student + ", name=" + name + ", password="+password+", gender=" + gender + ", date=" + date
				+ ", visStudentId=" + visStudentId + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Student)
			return ((Student) obj).getId_student()== this.getId_student();
		return super.equals(obj);
	}




}

