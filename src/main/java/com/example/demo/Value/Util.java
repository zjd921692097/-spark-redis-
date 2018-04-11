package com.example.demo.Value;

import com.example.demo.model.MyPlayer;
import com.example.demo.redis.RedisUtil;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import redis.clients.jedis.Jedis;

import java.util.Random;

public class Util {
    private static Random random=new Random();
    private static Jedis jedis= RedisUtil.getConnectionRedis();


    public static MyPlayer GeneratePlayer(){
        MyPlayer myPlayer=new MyPlayer();
        myPlayer.setGrade(0);
        myPlayer.setName(Const.FIRST_NAMES[random.nextInt(Const.FIRST_NAMES.length-1)]+" "
                +Const.LAST_NAMES[random.nextInt(Const.LAST_NAMES.length-1)]);
        return  myPlayer;
    }


}
