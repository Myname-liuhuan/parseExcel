package com.example.parseexcel.module.parseexcel.controller;

import com.example.parseexcel.service.SimpleParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author liuhuan
 * @date 2023/11/14
 */
@RestController
@RequestMapping("simpleParse")
public class SimpleParseController {

    @Autowired
    SimpleParseService simpleParseService;

    @RequestMapping("/excelToText")
    public Map<String, Object> excelToText(MultipartFile file){
        return simpleParseService.excelToText(file);
    }


}
