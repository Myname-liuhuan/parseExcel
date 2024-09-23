package com.example.parseexcel.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.image.service.LocalImageService;

import lombok.extern.slf4j.Slf4j;

/**
 * image模块定时任务
 */
@Component
@EnableScheduling
@Slf4j
public class ImageTask {

    @Autowired
    private LocalImageService localImageService;


    //每天0点调用refreshPath方法
    @Scheduled(cron = "0 0 0 * * ?")
    public void refreshPath(){
        CommonResult<Integer> result = localImageService.refreshPath();
        log.info(result.toString());
    }

}
