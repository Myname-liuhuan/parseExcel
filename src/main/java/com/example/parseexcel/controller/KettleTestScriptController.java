package com.example.parseexcel.controller;

import com.example.parseexcel.service.SimpleParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author liuhuan
 * @date 2024/04/07
 */
@Controller
@RequestMapping("/kettleTestScript")
public class KettleTestScriptController {

    @Autowired
    SimpleParseService simpleParseService;

    @RequestMapping("/downloadScript")
    @ResponseBody
    public ResponseEntity<byte[]> downloadScript(){
        return null;
    }


}
