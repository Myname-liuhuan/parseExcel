package com.example.parseexcel.controller;

import com.example.parseexcel.service.KettleScriptOutPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author liuhuan
 * @date 2024/04/03
 */
@Controller
@RequestMapping("simpleParse")
public class KettleOutPutController {

    @Autowired
    KettleScriptOutPutService kettleScriptOutPutService;

    public ResponseEntity<byte[]> outPutScript(Integer templateId, String middleName){

        return null;
    }
 

}
