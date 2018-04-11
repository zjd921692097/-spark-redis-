package com.example.demo.redis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
@Component
public class RedisUtil {


    public static Jedis getConnectionRedis(){
        Jedis jedis=new Jedis("localhost");
        return jedis;
    }

}
