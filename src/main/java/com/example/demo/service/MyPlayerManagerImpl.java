package com.example.demo.service;

import com.example.demo.Value.Const;
import com.example.demo.dao.MyPlayerDAO;
import com.example.demo.dao.MyPlayerDAOImpl;
import com.example.demo.kafka.MsgProducer;
import com.example.demo.model.MyPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPlayerManagerImpl implements MyPlayerManager{


    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    MyPlayerDAO myPlayerDAO=new MyPlayerDAOImpl();

    @Override
    public List<MyPlayer> getAllMyPlayer() {
        System.out.println("getall");
        msgProducer.sendMessage(Const.kafkaTopic,"GetAllMyPlayer");

        return myPlayerDAO.getAllMyPlayer();
    }

    @Override
    public List<MyPlayer> getMyPlayer(int num) {
        System.out.println("get"+num);
        msgProducer.sendMessage(Const.kafkaTopic,"GetMyPlayer "+num);

        return myPlayerDAO.getMyPlayer(num);
    }
}
