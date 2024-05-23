package com.example.parseexcel.common.utils;

import com.example.parseexcel.service.KettleScriptOutPutService;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.*;
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

    /**
     * 读取resource中的内容为文本
     * @param resource
     * @return
     */
    public static String readResource(Resource resource){
        String result = null;
        try (InputStream inputStream = resource.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            result = content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 通过类加载器读取resource下面的文件
     * @param clazz
     * @param sourcePath 要读取的文件的路径
     * @return
     */
    public static String readByClazz(Class clazz, String sourcePath){
        String result = null;
        try(InputStream inputStream = clazz.getClassLoader().getResourceAsStream(sourcePath);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            result = content.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 通过文件路径读取文件内容
     * @param filePath
     * @return 成功返回字符串，否则返回null
     */
    public static String readByPath(String filePath){
        String result = null;
        File file = new File(filePath);
        if (file.isFile()){
            try(InputStream inputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
                result = stringBuilder.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 输出文件内容
     * @param source
     * @param outputPath
     * @param fileName
     * @return
     */
    public static void outputFile(String source, String outputPath, String fileName){
        try(FileOutputStream fileOutputStream = new FileOutputStream(outputPath + fileName, false);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)){
            bufferedWriter.write(source);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出文件夹下的所有文件路径（遍历子文件夹）
     * @param fileName
     */
    public void exportPath2File(String fileName){
        String sourceFloder  = "D:\\kettle_file\\售后（Oracle）\\售后保修";
        String exportFile = "D:\\file\\temp\\售后保修filepath.txt";
        File file = new File(sourceFloder);

        try(FileOutputStream fileOutputStream = new FileOutputStream(exportFile, false);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)){
            bufferedWriter.write(getAbsolutePath(file));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getAbsolutePath(File file){
        if (file.isDirectory()) {
            StringBuffer sb = new StringBuffer();
            File[] files = file.listFiles();
            for (File file2 : files) {
                sb.append(getAbsolutePath(file2) + "\n");
            }
            return sb.toString();
        }else{
            return file.getAbsolutePath() + "\n";
        }
       
    }


    /**
     * 遍历文件夹及其子文件夹替换其中文件内容
     * @param filePath 文件夹路径
     * @param sourceStr
     * @param targetStr
     */
    public void replaceContent(File file, String sourceStr, String targetStr){
        if (file.isFile()) {
            String result = null;
            try(InputStream inputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);){
                StringBuilder stringBuilder = new StringBuilder();
                int i ;
                //不适用readLine是因为readLine会去重换行符
                while((i = bufferedReader.read()) != -1){
                    stringBuilder.append((char)i);
                }
                result = stringBuilder.toString().replaceAll(sourceStr, targetStr);
            }catch (Exception e){
                e.printStackTrace();
            }

            try(FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)){
                bufferedWriter.write(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            File[] files = file.listFiles();
            for (File file2 : files) {
                replaceContent(file2, sourceStr, targetStr);
            }
        }
    }

    public static void main(String[] args) {
        //new FileContentUtil().exportPath2File(null);
        new FileContentUtil()
        .replaceContent(new File("D:\\file\\temp\\汇率"),
         "DELETEFLAG", "DEL_FLAG");
    }

}
