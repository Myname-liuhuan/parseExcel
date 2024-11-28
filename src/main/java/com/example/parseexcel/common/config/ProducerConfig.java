package com.example.parseexcel.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Supplier;

/**
 * @author liuhuan
 * @date 2024/10/13
 */
@Configuration
public class ProducerConfig {

    private int id = 1;

    /**
     * 发送 静态消息，
     * @bean注解的方法名需要和配置文件里面的一样
     * 没有手动指定的情况下，每秒发送一条消息
     * @return
     */
    @Bean
    public Supplier<Message<String>> musicInfoProducer() {
        return () -> MessageBuilder.withPayload(String.format("第%d条静态消息", id++))
                .build();
    }
}
