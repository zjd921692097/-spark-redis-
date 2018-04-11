package com.example.demo.model;

public class TokafkaMsg {

    private String name;
    private int count;

    @Override
    public String toString() {
        return "TokafkaMsg{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    public TokafkaMsg(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
