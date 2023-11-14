package com.example.parseexcel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuhuan
 * @date 2023/11/14
 */
@Controller
@RequestMapping("simpleParse")
public class SimpleParseController {

    @RequestMapping("/excelToText")
    @ResponseBody
    public String excelToText(){
        return "";
    }


}
