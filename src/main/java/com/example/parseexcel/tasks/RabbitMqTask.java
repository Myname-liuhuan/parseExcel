package com.example.parseexcel.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * StreamBridge 动态发送消息示例
 * @author liuhuan
 * @date 2024/10/13
 */
@Slf4j
@Component
public class RabbitMqTask {

    private StreamBridge streamBridge;

    @Autowired
    public void setStreamBridge(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    /**
     * 每2秒发送一条消息
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void sendToMq(){

        streamBridge.send("musicInfoProducer-out-0", "动态消息");
    }
}
