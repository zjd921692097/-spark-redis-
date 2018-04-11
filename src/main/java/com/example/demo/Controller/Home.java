package com.example.demo.Controller;

import com.example.demo.kafka.MsgProducer;
import com.example.demo.model.MyPlayer;
import com.example.demo.service.MyPlayerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Home {

    @Autowired
    private MsgProducer msgProducer;
    @Autowired
    MyPlayerManager myPlayerManager;

    @RequestMapping(value = "/home")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/home");
        List<MyPlayer> playerList=new ArrayList<>();
        playerList=myPlayerManager.getMyPlayer(10);
//        for (int i=0;i<5;i++){
//            MyPlayer player=new MyPlayer();
//            player.setName("hhaa"+i);
//            player.setGrade(i*10);
//            playerList.add(player);
//        }
        mv.addObject("playerlist",playerList);
        //mv.addObject("title","欢迎使用Thymeleaf!");
        msgProducer.sendMessage("RunLog","请求了home");
        return mv;
    }
}
