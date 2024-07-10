package com.example.parseexcel.module.parseexcel.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface KettleTestScriptService {
    /**
     * 
     * @return
     */
    ResponseEntity<byte[]> downloadScript(String filename, String databaseName, String middleTableName,String targetTableName, 
    Map<String, String> countMap, Map<String, String> valueMap);
}
