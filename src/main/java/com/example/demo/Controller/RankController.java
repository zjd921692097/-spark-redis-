package com.example.demo.Controller;

import com.example.demo.Game.SimulationPlayer;
import com.example.demo.Value.Const;
import com.example.demo.Value.Util;
import com.example.demo.kafka.MsgProducer;
import com.example.demo.model.MyPlayer;
import com.example.demo.model.TokafkaMsg;
import com.example.demo.service.MyPlayerManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class RankController {
    @Autowired
    private MsgProducer msgProducer;
    @Autowired
    MyPlayerManager myPlayerManager;

    @RequestMapping(value = "/rank")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/rank");
//        List<MyPlayer> playerList=new ArrayList<>();
//        for (int i=0;i<5;i++){
//            MyPlayer player=new MyPlayer();
//            player.setName("hhaa"+i);
//            player.setGrade(i*10);
//            playerList.add(player);
//        }
//        mv.addObject("playerlist",playerList);
//        mv.addObject("title","欢迎使用Thymeleaf!");
        //msgProducer.sendMessage("RunLog","请求了rank");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/getRankList", method = RequestMethod.GET)
    public String ajax(@RequestParam("username") String username) {
        List<MyPlayer> playerList=new ArrayList<>();
        playerList=myPlayerManager.getMyPlayer(10);
        Gson gson=new Gson();
        //System.out.println(gson.toJson(playerList));
        //msgProducer.sendMessage("RunLog","请求了getRankList");

        return gson.toJson(playerList);
    }
    @RequestMapping(value = "/test")
    public ModelAndView testasd(ModelAndView mv) {
        mv.setViewName("/confirm");


        new Thread(){
            @Override
            public void run() {
               new Thread(){
                   @Override
                   public void run() {
                       while (true){
                           //regoster

                           Gson gson=new Gson();
                           try {
                               MyPlayer myPlayer= Util.GeneratePlayer();

                               TokafkaMsg tokafkaMsg=new TokafkaMsg(Const.TOKAFKAMSG_REGISTER,myPlayer.getName(),0);
                               System.out.println("prduce");
                               msgProducer.sendMessage(Const.kafkaTopic, gson.toJson(tokafkaMsg));

                               Thread.sleep((long) (Math.random()*2000+100));
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }

                   }
               }.start();


                new Thread(){
                    @Override
                    public void run() {

                        Random random=new Random();
                        Gson gson=new Gson();
                        while (true){
                            try {
                                System.out.println("play");
                                List<MyPlayer> myPlayerList=myPlayerManager.getAllMyPlayer();
                                if (myPlayerList.size()>2){
                                    TokafkaMsg tokafkaMsg1=new TokafkaMsg(Const.TOKAFKAMSG_Play,myPlayerList.get(random.nextInt(myPlayerList.size())).getName(), (int) (Math.random()*10-5));
                                    TokafkaMsg tokafkaMsg2=new TokafkaMsg(Const.TOKAFKAMSG_Play,myPlayerList.get(random.nextInt(myPlayerList.size())).getName(), (int) (Math.random()*10-5));
                                    msgProducer.sendMessage(Const.kafkaTopic,gson.toJson(tokafkaMsg1));
                                    msgProducer.sendMessage(Const.kafkaTopic,gson.toJson(tokafkaMsg2));
                                }
                                Thread.sleep((long) (Math.random()*2000+100));
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();

            }
        }
        .start();


        //simulationPlayer.startSimulationPlay();
        return mv;
    }
}
