package com.example.demo.model;

import java.io.Serializable;

public class MyPlayer implements Serializable{

    private String name;
    private double grade;

    public MyPlayer() {
    }

    public MyPlayer(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "MyPlayer{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
