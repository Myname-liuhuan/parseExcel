package com.example.parseexcel.service;

import com.example.parseexcel.common.constant.KettleScriptConstant;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 生成的kettle脚本文件输出逻辑
 * @author liuhuan
 * @date 2023/12/17
 */
@Service
public class KettleScriptOutPutService {

    private final ResourceLoader resourceLoader;

    public KettleScriptOutPutService(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    public void outPutScript() throws IOException {
        Resource resource = resourceLoader.getResource(KettleScriptConstant.SCRIPT_NAME_WORK);
        try (InputStream inputStream = resource.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            //输出逻辑

        }
    }

}
