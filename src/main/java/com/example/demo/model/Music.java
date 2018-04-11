package com.example.demo.model;

import java.io.Serializable;

public class Music implements Serializable{
    String name;
    String address;
    String playerName;
    int count=0;

    public Music(String name, String address, String playerName,int count) {
        this.name = name;
        this.address = address;
        this.playerName = playerName;
        this.count=count;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }



    @Override
    public String toString() {
        return "MyPlayer{" +
                "name='" + name + '\'' +
                ", playName=" +playerName +
                '}';
    }
}
