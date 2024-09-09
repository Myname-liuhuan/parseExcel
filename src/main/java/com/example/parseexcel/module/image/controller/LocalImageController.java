package com.example.parseexcel.module.image.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.image.service.LocalImageService;

@RestController
@RequestMapping("/media/image/")
public class LocalImageController {


    @Autowired
    private LocalImageService localImageService;

    /**
     * 手动刷新图片到数据库
     * @return
     */
    @GetMapping("/refreshPath")
    public CommonResult<Integer> refreshPath() {
        return localImageService.refreshPath();
    }
    
}
