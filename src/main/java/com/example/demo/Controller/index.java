package com.example.demo.Controller;

import com.example.demo.Value.Const;
import com.example.demo.Value.SerializeUtil;
import com.example.demo.kafka.MsgProducer;
import com.example.demo.model.EncodingTool;
import com.example.demo.model.Music;
import com.example.demo.model.TokafkaMsg;
import com.example.demo.redis.RedisUtil;
import com.example.demo.service.MusicManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Controller
public class index {
    final static Jedis jedis= RedisUtil.getConnectionRedis();

    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    MusicManager musicManager;

    @RequestMapping(value = "/index3")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("index");
        List<Music> musicList=new ArrayList<>();
        musicList=musicManager.getAllMusic();
        mv.addObject("musiclist",musicList);
        return mv;
    }





    @ResponseBody
    @RequestMapping(value = "/getMusicList", method = RequestMethod.GET)
    public String ajax(@RequestParam("username") String username) {
        List<Music> musicList=new ArrayList<>();
        musicList=musicManager.getAllMusic();
        Gson gson=new Gson();
        return gson.toJson(musicList);
    }
 /*   public int aa(String names){
        int count=0;
        List<Music> musicList=new ArrayList<>();
        byte[] in=jedis.get("Music".getBytes());

        List<Music> list = SerializeUtil.unserializeForList(in);
        for(Music obj : list){
            Music music=new Music(obj.getName(),obj.getPlayerName(),obj.getAddress(),obj.getCount());
            if(music.getName().equals(names)){
                count=music.getCount();
            }
            musicList.add(music);

        }
        return count;
    }*/

    @ResponseBody
    @RequestMapping(value = "/getMusic", method = RequestMethod.GET)
    public String getMusic(@RequestParam("musicname") String musicname) {
        int count=0;

        String[] names = musicname.split("\\+");
        System.out.print(EncodingTool.encodeStr(names[1])+"qqqqqqqqqqqqqqqqqqqqqqqqqq");


        new Thread(){
            @Override
            public void run() {
                Gson gson=new Gson();
                TokafkaMsg tokafkaMsg=new TokafkaMsg(names[1],1+Integer.valueOf(jedis.get(names[1])));
                msgProducer.sendMessage("music2", gson.toJson(tokafkaMsg));
            }}.start();
        return "./"+names[1]+".mp3";
    }

    @RequestMapping(value="/music+{variable1}")
    public String test3(@PathVariable String variable1,Model model) {
        model.addAttribute("music2",variable1);
        return "index2";
    }
}