package com.example.demo.model;

import com.example.demo.Value.Const;
import com.example.demo.kafka.MsgProducer;
import com.example.demo.redis.RedisUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;

public class MyLog {
    private static final Logger log = LoggerFactory.getLogger(MsgProducer.class);
    private TokafkaMsg tokafkaMsg;
    private static Jedis jedis= RedisUtil.getConnectionRedis();
    private  static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
    private static Gson gson=new Gson();
    public static void MyLog(String string) {
        System.out.println("MyLog:"+string);

        TokafkaMsg tokafkaMsg=gson.fromJson(string,TokafkaMsg.class);
        java.util.Date date=new java.util.Date();
        String strDate=sdf.format(date);
        Double doubleDate=Double.valueOf(strDate);
        if (tokafkaMsg.getType()== Const.TOKAFKAMSG_REGISTER){
            //regiser
            jedis.zadd(Const.registerkey,doubleDate,tokafkaMsg.getName());
            log.info("zadd"+Const.registerkey+" "+doubleDate+" "+tokafkaMsg.getName());
        }
        else if (tokafkaMsg.getType()==Const.TOKAFKAMSG_Play){
            //play time
            jedis.zadd(Const.playkey,doubleDate,tokafkaMsg.getName());
            log.info("zadd"+Const.playkey+" "+doubleDate+" "+tokafkaMsg.getName());
            //rank
            jedis.zincrby(Const.rankKey,tokafkaMsg.getChangeScore(),tokafkaMsg.getName());
            log.info("zadd"+Const.registerkey+" "+Double.valueOf(strDate.toString())+" "+tokafkaMsg.getName());
        }
    }
    public MyLog(TokafkaMsg tokafkaMsg) {

        this.tokafkaMsg = tokafkaMsg;
        java.util.Date date=new java.util.Date();
        String strDate=sdf.format(date);
        Double doubleDate=Double.valueOf(strDate);
        if (tokafkaMsg.getType()== Const.TOKAFKAMSG_REGISTER){
            //regiser
            jedis.zadd(Const.registerkey,doubleDate,tokafkaMsg.getName());
            log.info("zadd"+Const.registerkey+" "+doubleDate+" "+tokafkaMsg.getName());
        }
        else if (tokafkaMsg.getType()==Const.TOKAFKAMSG_Play){
            //play time
            jedis.zadd(Const.playkey,doubleDate,tokafkaMsg.getName());
            log.info("zadd"+Const.playkey+" "+doubleDate+" "+tokafkaMsg.getName());
            //rank
            jedis.zincrby(Const.rankKey,tokafkaMsg.getChangeScore(),tokafkaMsg.getName());
            log.info("zadd"+Const.registerkey+" "+Double.valueOf(strDate.toString())+" "+tokafkaMsg.getName());
        }
    }

    public TokafkaMsg getTokafkaMsg() {
        return tokafkaMsg;
    }

    public void setTokafkaMsg(TokafkaMsg tokafkaMsg) {
        this.tokafkaMsg = tokafkaMsg;
    }
}
