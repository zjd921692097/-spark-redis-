package com.example.demo.dao;

import com.example.demo.kafka.MsgProducer;
import com.example.demo.model.MyPlayer;
import com.example.demo.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class MyPlayerDAOImpl implements MyPlayerDAO{

    private static final Logger log = LoggerFactory.getLogger(MsgProducer.class);
    final static Jedis jedis=RedisUtil.getConnectionRedis();
    static String MYPLAYERKEY="test";

    @Override
    public List<MyPlayer> getAllMyPlayer() {
        List<MyPlayer> myPlayerList=new ArrayList<>();
        Set<Tuple> tuple= jedis.zrangeWithScores(MYPLAYERKEY,0,-1);
        for(Tuple value : tuple){
            MyPlayer myPlayer=new MyPlayer(value.getElement(),value.getScore());

            myPlayerList.add(myPlayer);
        }
        return myPlayerList;
    }

    @Override
    public List<MyPlayer> getMyPlayer(int num) {
        List<MyPlayer> myPlayerList=new ArrayList<>();
        Set<Tuple> tuple= jedis.zrevrangeWithScores(MYPLAYERKEY,0,-1);
        for(Tuple value : tuple){
            MyPlayer myPlayer=new MyPlayer(value.getElement(),value.getScore());
            myPlayerList.add(myPlayer);
           // System.out.println("size:"+myPlayerList.size());
            if (myPlayerList.size()>=num) {break;}
        }

        return myPlayerList;
    }

    @Override
    public void AddMyPlayer(MyPlayer myPlayer) {
        jedis.zadd(MYPLAYERKEY, myPlayer.getGrade(), myPlayer.getName());
    }

    @Override
    public void RemoveMyPlayer(MyPlayer myPlayer) {
        jedis.zrem(MYPLAYERKEY,myPlayer.getName());
    }
}
