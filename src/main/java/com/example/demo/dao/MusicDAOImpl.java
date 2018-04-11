package com.example.demo.dao;

import com.example.demo.Value.Const;
import com.example.demo.Value.SerializeUtil;

import com.example.demo.model.Music;
import com.example.demo.redis.RedisUtil;
import org.apache.commons.net.nntp.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Repository
public class MusicDAOImpl implements MusicDAO{

    final static Jedis jedis= RedisUtil.getConnectionRedis();
    @Override
    public List<Music> getAllMusic() {
     /*   Music music1=new Music("we are not yet","we are not yet","dazhuang",0);
        Music music2=new Music("周杰伦 - 等你下课 (with 杨瑞代)","follow sun","周杰伦",0);
        Music music3=new Music("Birthday Suit","follow sun","Dan Talevski",0);
        Music music4=new Music(" You're Mine","follow sun","Dripice",0);
        Music music5=new Music("Wrong","follow sun","Far Out",0);
        Music music6=new Music("Move Like Me","follow sun","Freddy",0);
        Music music7=new Music("Alarm","follow sun","Krewella",0);
        Music music8=new Music("HITS OF","follow sun","T10 MO",0);
        jedis.set("we are not yet","0");
        jedis.set("周杰伦 - 等你下课 (with 杨瑞代)","0");
        jedis.set("Birthday Suit","0");
        jedis.set(" You're Mine","0");
        jedis.set("Wrong","0");
        jedis.set("Move Like Me","0");
        jedis.set("Alarm","0");
        jedis.set("HITS OF","0");
*/

        /*List<Music> musicList2=new ArrayList<>();*/
       /* musicList2.add(music1);
        musicList2.add(music2);
        musicList2.add(music3);
        musicList2.add(music4);
        musicList2.add(music5);
        musicList2.add(music6);
        musicList2.add(music7);
        musicList2.add(music8);*/

       /* jedis.set("Music".getBytes(),SerializeUtil.serialize(musicList2));*/
        List<Music> musicList=new ArrayList<>();
        byte[] in=jedis.get("Music".getBytes());
        List<Music> list = SerializeUtil.unserializeForList(in);
        for(Music obj : list){
            int a=Integer.valueOf(jedis.get(obj.getName()));
            System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&"+jedis.get(obj.getName())+"&&&&&&&&&&&&&&&&&&"+obj.getName());
            Music music=new Music(obj.getName(),obj.getPlayerName(),obj.getAddress(),a);
            musicList.add(music);

        }
        return musicList;
    }

}
