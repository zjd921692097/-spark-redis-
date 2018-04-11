package com.example.demo;

import com.example.demo.sparkstreamingkakfa.JavaDirectKafkaWordCount;
import com.example.demo.kafka.MsgProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class BaseTest {

    @Autowired
    private MsgProducer msgProducer;


    @Test
    public void test() throws Exception {
        //spark.sample();
       // JavaDirectKafkaWordCount.main();


                msgProducer.sendMessage("test", "mytopic");
                //msgProducer.sendMessage("topic-2", "topic--------2");

                    Thread.sleep(1000);


                msgProducer.sendMessage("test", "4444");
                Thread.sleep(1000);
                for (int i=0;i<1000;i++){
                    msgProducer.sendMessage("test", "haha"+i);
                    Thread.sleep(1000);
                }

    }
    @Test
    public void testRedis() throws InterruptedException {
      //  RedisUtil.GetConnectionRedis();
        JavaDirectKafkaWordCount.main();
    }

}

