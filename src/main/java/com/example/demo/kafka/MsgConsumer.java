package com.example.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class MsgConsumer {
    private static final Logger log = LoggerFactory.getLogger(MsgProducer.class);
    @KafkaListener(topics = {"music2"})
    public void processMessage(String content) throws InterruptedException {
        log.info("消息消费"+content);
    }
}

