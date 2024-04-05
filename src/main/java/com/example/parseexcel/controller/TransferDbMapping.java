package com.example.parseexcel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuhuan
 * @date 2024/4/5
 */
@Controller
@RequestMapping("/transferDbMapping")
public class TransferDbMapping {

    /**
     * 列表查询
     */
    @RequestMapping("/list")
    @ResponseBody
    public void list(){

    }
}
