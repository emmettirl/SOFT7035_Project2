package com.example.soft7035project2.models;

public class Appointment {

    private int id;
    private String user;
    private String time;
    private String duration;


    public Appointment() {
        this.id = id;
        this.user = user;
        this.time = time;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", time='" + time + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
