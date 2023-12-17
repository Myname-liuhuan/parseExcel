package com.example.parseexcel.common.utils;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件内容工具类
 * @author liuhuan
 * @date 2023/12/17
 */
public class FileContentUtil {

    /**
     * 替换文件中内容
     * @param filePath 文件地址
     * @param sourceStr 源字符串 （文件中现有的）
     * @param targetStr 目标字符串 （待替换为的）
     * @return
     */
    public static boolean replaceContent(String filePath, String sourceStr, String targetStr){
        Path path = Paths.get(filePath);
        if (!Files.isRegularFile(path)){
            return false;
        }
        if (StringUtils.isEmpty(sourceStr) && StringUtils.isEmpty(targetStr)){
            return true;
        }

        try {
            String content = new String(Files.readAllBytes(path));
            content = content.replace(sourceStr, targetStr);
            Files.write(path, content.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



}
