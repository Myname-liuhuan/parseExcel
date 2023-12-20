package com.example.parseexcel.service;

import com.example.parseexcel.common.constant.KettleConstant;
import com.example.parseexcel.common.utils.FileContentUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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

    public void outPutScript(String sortPath, String targetDatabaseName, String middleDatabaseName,
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
                + KettleConstant.WINDOW_OUTPUT_PATH_SUB
                + scriptName + "/" + targetTableName + "/";
        //创建脚本文件夹,创建目标库文件夹
        File directory = new File(dirPath);
        if (!directory.exists()){
            directory.mkdirs();
        }

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

    public static void main(String[] args) {
        Map<String, String> scriptNameMap = new HashMap<>();
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_WORK, "SSC公示车辆VIN（技术支持组）.kjb");
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_TRANS01, "SSC公示车辆VIN（技术支持组）01.ktr");
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_TRANS02, "SSC公示车辆VIN（技术支持组）02.ktr");
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_TRANS03, "SSC公示车辆VIN（技术支持组）03.ktr");
        scriptNameMap.put(KettleConstant.SCRIPT_NAME_TRANS04, "SSC公示车辆VIN（技术支持组）04.ktr");
        Map<String, String>  replaceMap = new HashMap<>();
        replaceMap.put("ScriptName", "SSC公示车辆VIN（技术支持组）");
        replaceMap.put("TargetTableName", "t_wty_ssc_public_vin");
        replaceMap.put("MiddleTableName", "srv_ssc_m_middle2");
        new KettleScriptOutPutService(null).outPutScriptNoSpring("",
                "SSC公示车辆VIN（技术支持组）",
                "t_wty_ssc_public_vin",
                replaceMap,
                scriptNameMap);
    }

}
