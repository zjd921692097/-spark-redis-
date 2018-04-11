package com.example.demo.dao;

import com.example.demo.model.MyPlayer;

import java.util.List;

public interface MyPlayerDAO {
    public List<MyPlayer> getAllMyPlayer();
    public List<MyPlayer> getMyPlayer(int num);
    public void AddMyPlayer(MyPlayer myPlayer);
    public void RemoveMyPlayer(MyPlayer myPlayer);
}
