package com.example.break_meet;

public class Place {
    private String name;
    private String type;

    public Place(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Place() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
