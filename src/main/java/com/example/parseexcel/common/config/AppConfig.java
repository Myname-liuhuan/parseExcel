package com.example.parseexcel.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 存放配置文件信息
 */
@Component
public class AppConfig {

    // 读取单个参数
    @Value("${image.path}")
    public String imagePath;

    @Value("${image.prefix-url}")
    public String prefixUrl;

}
