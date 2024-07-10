package com.example.parseexcel.module.parseexcel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.parseexcel.module.parseexcel.service.KettleTestScriptService;

import java.util.Map;


/**
 * @author liuhuan
 * @date 2024/04/07
 */
@Controller
@RequestMapping("/kettleTestScript")
public class KettleTestScriptController {

    @Autowired
    KettleTestScriptService kettleTestScriptService;

    @RequestMapping("/downloadScript")
    @ResponseBody
    //public ResponseEntity<byte[]> downloadScript(){
    public ResponseEntity<byte[]> downloadScript(String filename, String databaseName, String middleTableName,
                                                 String targetTableName, Map<String, String> countMap, Map<String, String> valueMap){
//        String filename = "TWC保修申请";
//        String databaseName = "infra-dms-wty";
//        String middleTableName = "";
//        String targetTableName = "";
//        Map<String, String> countMap = new HashMap<>();
//        countMap.put("dms_id","ID");
//        Map<String, String> valueMap = new HashMap<>();
//        valueMap.put("DELETEFLAG","DEL_FLAG");
        return kettleTestScriptService.downloadScript(filename, databaseName, middleTableName, targetTableName,
                countMap, valueMap);
    }


}
