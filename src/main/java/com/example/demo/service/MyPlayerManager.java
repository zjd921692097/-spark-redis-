package com.example.demo.service;

import com.example.demo.model.MyPlayer;

import java.util.List;

public interface MyPlayerManager {
    public List<MyPlayer> getAllMyPlayer();
    public List<MyPlayer> getMyPlayer(int num);
}
