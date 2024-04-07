package com.example.parseexcel.service;

import com.example.parseexcel.common.constant.KettleConstant;
import com.example.parseexcel.common.utils.FileContentUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成的kettle脚本文件输出逻辑
 * @author liuhuan
 * @date 2023/12/17
 */
@Service
public class KettleScriptOutPutService {

    private final ResourceLoader resourceLoader;

    @Value("${kettle.output.path}")
    private String outputPath;

    public KettleScriptOutPutService(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }
    
    public ResponseEntity<byte[]> outPutScript(String sortPath, String targetDatabaseName, String middleDatabaseName,
                             Map<String,String> scriptNameMap) throws IOException {
        //创建脚本文件夹,创建目标库文件夹
        File directory = new File(outputPath + sortPath);
        if (!directory.exists()){
            directory.mkdirs();
        }

        //输出逻辑
        for (Map.Entry<String, String> entry : scriptNameMap.entrySet()) {
            Resource resource = resourceLoader.getResource(entry.getKey());
            String text = FileContentUtil.readResource(resource);
            FileContentUtil.outputFile(text, outputPath, entry.getValue());
        }

        return null;
    }

    /**
     * 不依赖spring进行输出脚本
     * @param subPath 次级路径     售后/售后保修/
     * @param replaceMap 要替换以及替换为的map,key:现存文本中的待替换字段；value:即将替换为的字段
     * @param scriptNameMap 需要输出的模板文件及其输出名称 key: 模板名称; value: 输出文件名称
     */
    public void outPutScriptNoSpring(String subPath, String scriptName, String targetTableName,
                                     Map<String, String> replaceMap, Map<String,String> scriptNameMap){
        String dirPath = KettleConstant.WINDOW_OUTPUT_PATH
                + KettleConstant.WINDOW_OUTPUT_PATH_SUB_ORACLE
                + scriptName + "/" + targetTableName + "/";
        //创建脚本文件夹,创建目标库文件夹
        File directory = new File(dirPath);
        if (!directory.exists()){
            directory.mkdirs();
        }

        //文本内动态路径
        replaceMap.put(KettleConstant.SUB_PATH, KettleConstant.WINDOW_OUTPUT_PATH_SUB_ORACLE);

        //输出逻辑
        for (Map.Entry<String, String> entry : scriptNameMap.entrySet()) {
            String templatePath = KettleConstant.TEMPLATE_PATH_NO_SPRING + entry.getKey();
            String text = FileContentUtil.readByClazz(this.getClass(), templatePath);
            //修改模板中数据
            for (Map.Entry<String, String> replaceEntry : replaceMap.entrySet()) {
                text = text.replaceAll(replaceEntry.getKey(), replaceEntry.getValue());
            }
            FileContentUtil.outputFile(text, dirPath, entry.getValue());
        }
    }

    /**
     * 默认输出的方式 即一个作业四个转换
     * @param scriptName
     * @param targetTableName
     * @param middleTableName
     */
    private void defaultOutPut(String scriptName, String targetTableName, String middleTableName){
        Map<String, String> scriptNameMap = new HashMap<>();
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_WORK, scriptName + ".kjb");
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_TRANS01, scriptName + "01.ktr");
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_TRANS02, scriptName + "02.ktr");
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_TRANS03, scriptName + "03.ktr");
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_TRANS04, scriptName + "04.ktr");
        Map<String, String>  replaceMap = new HashMap<>();
        replaceMap.put(KettleConstant.SCRIPT_NAME, scriptName);
        replaceMap.put(KettleConstant.TARGET_TABLE_NAME, targetTableName);
        replaceMap.put(KettleConstant.MIDDLE_TABLE_NAME, middleTableName);
        outPutScriptNoSpring("", scriptName, targetTableName, replaceMap, scriptNameMap);
    }

    /**
     * oracle脚本输出
     * @param scriptName
     * @param targetTableName
     * @param middleTableName
     */
    private void oracleOutPut(String scriptName, String targetTableName, String middleTableName){
        Map<String, String> scriptNameMap = new HashMap<>();
        scriptNameMap.put("ORACLE_" + KettleConstant.SCRIPT_NAME_WORK, scriptName + ".kjb");
        scriptNameMap.put("ORACLE_" + KettleConstant.SCRIPT_NAME_TRANS01, scriptName + "01.ktr");
        scriptNameMap.put("ORACLE_" + KettleConstant.SCRIPT_NAME_TRANS02, scriptName + "02.ktr");
        scriptNameMap.put("ORACLE_" + KettleConstant.SCRIPT_NAME_TRANS03, scriptName + "03.ktr");
        scriptNameMap.put("ORACLE_" + KettleConstant.SCRIPT_NAME_TRANS04, scriptName + "04.ktr");
        Map<String, String>  replaceMap = new HashMap<>();
        replaceMap.put(KettleConstant.SCRIPT_NAME, scriptName);
        replaceMap.put(KettleConstant.TARGET_TABLE_NAME, targetTableName);
        replaceMap.put(KettleConstant.MIDDLE_TABLE_NAME, middleTableName);
        outPutScriptNoSpring("", scriptName, targetTableName, replaceMap, scriptNameMap);
    }

    /**
     * 一个作业五个转换（历史表）
     * @param scriptName
     * @param targetTableName
     * @param middleTableName
     */
    private void outPut1(String scriptName, String targetTableName, String middleTableName){
        Map<String, String> scriptNameMap = new HashMap<>();
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_WORK_HISTORY, scriptName + ".kjb");
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_TRANS01, scriptName + "01.ktr");
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_TRANS02, scriptName + "02.ktr");
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_TRANS03, scriptName + "03.ktr");
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_TRANS04, scriptName + "04.ktr");
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_TRANS05, scriptName + "05.ktr");
        Map<String, String>  replaceMap = new HashMap<>();
        replaceMap.put(KettleConstant.SCRIPT_NAME, scriptName);
        replaceMap.put(KettleConstant.TARGET_TABLE_NAME, targetTableName);
        replaceMap.put(KettleConstant.MIDDLE_TABLE_NAME, middleTableName);
        outPutScriptNoSpring("", scriptName, targetTableName, replaceMap, scriptNameMap);
    }


    /**
     * oracle 一个作业五个转换（历史表）
     * @param scriptName
     * @param targetTableName
     * @param middleTableName
     */
    private void oracleOutPut1(String scriptName, String targetTableName, String middleTableName){
        Map<String, String> scriptNameMap = new HashMap<>();
        scriptNameMap.put("ORACLE_" + KettleConstant.SCRIPT_NAME_WORK_HISTORY, scriptName + ".kjb");
        scriptNameMap.put("ORACLE_" + KettleConstant.SCRIPT_NAME_TRANS01, scriptName + "01.ktr");
        scriptNameMap.put("ORACLE_" + KettleConstant.SCRIPT_NAME_TRANS02, scriptName + "02.ktr");
        scriptNameMap.put("ORACLE_" + KettleConstant.SCRIPT_NAME_TRANS03, scriptName + "03.ktr");
        scriptNameMap.put("ORACLE_" + KettleConstant.SCRIPT_NAME_TRANS04, scriptName + "04.ktr");
        scriptNameMap.put("ORACLE_" + KettleConstant.SCRIPT_NAME_TRANS05, scriptName + "05.ktr");
        Map<String, String>  replaceMap = new HashMap<>();
        replaceMap.put(KettleConstant.SCRIPT_NAME, scriptName);
        replaceMap.put(KettleConstant.TARGET_TABLE_NAME, targetTableName);
        replaceMap.put(KettleConstant.MIDDLE_TABLE_NAME, middleTableName);
        outPutScriptNoSpring("", scriptName, targetTableName, replaceMap, scriptNameMap);
    }


    public static void main(String[] args) {
      
    }

}
