package com.example.demo;

import com.example.demo.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.HashMap;

import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)

public class Redis {


    @Test
    public void testRedis(){

       Jedis jedis=RedisUtil.getConnectionRedis();
       Map<String, Double> scores = new HashMap<>();
       scores.put("PlayerOne", 3000.0);
            scores.put("PlayerTwo", 1500.0);
            scores.put("PlayerThree", 8200.0);

            scores.keySet().forEach(player -> {
                jedis.zadd("ranktest", scores.get(player), player);
            });

            Set<Tuple> tuple= jedis.zrangeByScoreWithScores("ranktest",0,10000);

            System.out.println("size:  "+tuple.size());
        for(Tuple value : tuple){
            System.out.println("element:"+value.getElement()+"score:"+value.getScore());
        }
        //String player = jedis.zrevrange("ranktest", 0, 1).iterator().next();
        //long rank = jedis.zrevrank("ranktest", "PlayerOne");
    }

}
