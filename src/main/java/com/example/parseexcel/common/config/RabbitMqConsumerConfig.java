package com.example.parseexcel.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/**
 * @author liuhuan
 * @date 2024/10/13
 */
@Configuration
public class RabbitMqConsumerConfig {

    /**
     * 消费端，@bean注解的方法名需要和配置文件里面的一样
     * @return
     */
    @Bean
    public Consumer<String> musicInfoConsumer(){
        return msgData -> System.out.println(msgData);
    }

}
