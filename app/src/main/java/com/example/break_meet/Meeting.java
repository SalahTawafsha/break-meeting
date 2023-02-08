package com.example.break_meet;

public class Meeting {
	private String studentName;
	private String type;
	private String placeName;
	private String fromTime;
	private String toTime;
	private String date;
	private String des;
	private String studentId;
	private String id;

	public Meeting() {
	}

	public Meeting(String studentName, String type, String placeName, String fromTime, String toTime, String date,
			String des, String studentId) {
		super();
		this.studentName = studentName;
		this.type = type;
		this.placeName = placeName;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.date = date;
		this.des = des;
		this.studentId = studentId;
		id = "";
	}

	public Meeting(String studentName, String type, String placeName, String fromTime, String toTime, String date,
			String des, String studentId, String id) {
		super();
		this.studentName = studentName;
		this.type = type;
		this.placeName = placeName;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.date = date;
		this.des = des;
		this.studentId = studentId;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
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
}
