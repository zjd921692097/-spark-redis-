package com.example.demo.Game;

import com.example.demo.Value.Const;
import com.example.demo.Value.Util;
import com.example.demo.kafka.MsgProducer;
import com.example.demo.model.MyPlayer;
import com.example.demo.model.TokafkaMsg;
import com.example.demo.service.MyPlayerManager;
import com.example.demo.service.MyPlayerManagerImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

public class SimulationPlayer {


    @Autowired
    MsgProducer msgProducer;

    @Autowired
    MyPlayerManager myPlayerManager;



//    public void startSimulationRegister(){
//        //Simulation Register
//
//                while (true){
//                    //regoster
//
//                    Gson gson=new Gson();
//                    try {
//                        MyPlayer myPlayer= Util.GeneratePlayer();
//
//                        TokafkaMsg tokafkaMsg=new TokafkaMsg(Const.TOKAFKAMSG_REGISTER,myPlayer.getName(),0);
//                        System.out.println("prduce");
//                        msgProducer.sendMessage(Const.kafkaTopic, gson.toJson(tokafkaMsg));
//
//                        Thread.sleep((long) (Math.random()*1000+100));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//    }
//    public void startSimulationPlay(){
//        //Simulation Play
//
//
//                Random random=new Random();
//                Gson gson=new Gson();
//                while (true){
//                    try {
//                        System.out.println("play");
//                        List<MyPlayer> myPlayerList=myPlayerManager.getAllMyPlayer();
//                        if (myPlayerList.size()>2){
//                            TokafkaMsg tokafkaMsg1=new TokafkaMsg(Const.TOKAFKAMSG_Play,myPlayerList.get(random.nextInt(myPlayerList.size())).getName(), (int) (Math.random()*10-5));
//                            TokafkaMsg tokafkaMsg2=new TokafkaMsg(Const.TOKAFKAMSG_Play,myPlayerList.get(random.nextInt(myPlayerList.size())).getName(), (int) (Math.random()*10-5));
//                            msgProducer.sendMessage(Const.kafkaTopic,gson.toJson(tokafkaMsg1));
//                            msgProducer.sendMessage(Const.kafkaTopic,gson.toJson(tokafkaMsg2));
//                        }
//                        Thread.sleep((long) (Math.random()*500+100));
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                }
//
//    }

}
