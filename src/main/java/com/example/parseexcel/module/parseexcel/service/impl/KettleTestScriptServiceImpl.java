package com.example.parseexcel.module.parseexcel.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.parseexcel.common.constant.KettleConstant;
import com.example.parseexcel.common.utils.FileContentUtil;
import com.example.parseexcel.module.parseexcel.service.KettleTestScriptService;
import com.example.parseexcel.service.KettleTestScriptOutPutService;

import org.springframework.stereotype.Service;

@Service
public class KettleTestScriptServiceImpl implements KettleTestScriptService{

    @Override
    public ResponseEntity<byte[]> downloadScript(String filename, String databaseName, String middleTableName,
            String targetTableName, Map<String, String> countMap, Map<String, String> valueMap) {
        //读取模板文件
        String text = FileContentUtil.readByClazz(this.getClass(), KettleConstant.SCRIPT_CHECK_PATH_ORACLE);
        //修改模板中数据
        KettleTestScriptOutPutService scriptOutPutObj = new KettleTestScriptOutPutService();
        text = text.replaceAll("SCRIPT_NAME", filename)
                   .replaceAll("MIDDLE_SQL", scriptOutPutObj.autoMiddleSql(databaseName, middleTableName, targetTableName, countMap, valueMap))
                   .replaceAll("TARGET_SQL", scriptOutPutObj.autoTargetSql(databaseName, targetTableName, countMap, valueMap))
                   .replaceAll("DB_NAME", databaseName)
                   .replaceAll("TB_NAME",targetTableName);
        //封装返回
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(filename + ".ktr", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        ResponseEntity<byte[]> result = new ResponseEntity<byte[]>(text.getBytes(), headers,
                HttpStatus.OK);
           
        return result;
    }
    
}
