package com.example.break_meet;

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

    @Override
    public String toString() {
        return "Meeting{" +
                "type='" + type + '\'' +
                ", placeName='" + placeName + '\'' +
                ", fromTime='" + fromTime + '\'' +
                ", date='" + date + '\'' +
                ", des='" + des + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
