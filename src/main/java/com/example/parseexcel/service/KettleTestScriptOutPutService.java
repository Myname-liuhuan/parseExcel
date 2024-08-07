package com.example.parseexcel.service;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.example.parseexcel.common.constant.KettleConstant;
import com.example.parseexcel.common.constant.KettleTestScriptConstant;
import com.example.parseexcel.common.utils.FileContentUtil;

/**
 * 纯Java语法不带spring
 */
public class KettleTestScriptOutPutService {

    

    /**
     * 数量校验
     * @param databaseName
     * @param middleTableName
     * @param targetTableName
     * @param countMap
     * @return
     */
    private String countCheck(String databaseName, String middleTableName,String targetTableName, Map<String, String> countMap){
        String dataStr = "";
        String dataStr2 = "";
        for(Entry<String, String> entry : countMap.entrySet()){
            dataStr += String.format(KettleTestScriptConstant.TEMPLATE_STR, entry.getKey(), entry.getValue(),entry.getValue());
            dataStr2 += String.format(KettleTestScriptConstant.TEMPLATE_STR2, entry.getValue(),entry.getValue()
                        , databaseName, targetTableName);
        }
        //处理最后的 UNION ALL
        dataStr2 = dataStr2.substring(0, dataStr2.lastIndexOf("UNION ALL"));
        
        String str1 = "WITH temp_table AS (\n" + 
            "SELECT\n" + 
            dataStr.substring(0, dataStr.lastIndexOf(',')) +
            "\nFROM " + middleTableName + " t" + 
            "\n)";
        return str1 + dataStr2;
    }

    /**
     * 值列表校验
     * @param databaseName
     * @param middleTableName
     * @param targetTableName
     * @param valueMap
     * @return
     */
    private String valueListCheck(String databaseName, String middleTableName,String targetTableName, Map<String, String> valueMap){
        String dataStr3 = "-- 值列表校验\n";
        for (Entry<String, String> entry : valueMap.entrySet()) {
            dataStr3 += String.format(KettleTestScriptConstant.TEMPLATE_STR3, entry.getValue(), entry.getValue(), databaseName, targetTableName, entry.getKey(), entry.getValue(), middleTableName, entry.getKey());
        }
        return dataStr3;
    }

     /**
     * 值列表校验
     * @param databaseName
     * @param middleTableName
     * @param targetTableName
     * @param valueMap
     * @return
     */
    private String valueListCheckOracle(String databaseName, String middleTableName,String targetTableName, Map<String, String> valueMap){
        String dataStr4 = "-- 值列表校验\n";
        for (Entry<String, String> entry : valueMap.entrySet()) {
            dataStr4 += String.format(KettleTestScriptConstant.TEMPLATE_STR4, entry.getValue(), entry.getValue(), databaseName, targetTableName, entry.getKey(), entry.getValue(), middleTableName, entry.getKey());
        }
        return dataStr4;
    }

    /**
     * 中间表sql
     */
    public String autoMiddleSql(String databaseName, String middleTableName,String targetTableName, Map<String, String> countMap, Map<String, String> valueMap){
        return countCheck(databaseName, middleTableName, targetTableName,countMap)  + valueListCheckOracle(databaseName, middleTableName, targetTableName,valueMap);
    }

    /**
     * 目标表sql
     */
    public String autoTargetSql(String databaseName, String targetTableName, Map<String, String> countMap, Map<String, String> valueMap){
        Map<String, String> targetCountMap = new TreeMap<>();
        Map<String, String> targetValueMap = new TreeMap<>();
        for(String value : countMap.values()){
            targetCountMap.put(value, value);
        }
        for(String value : valueMap.values()){
            targetValueMap.put(value, value);
        }

        String resultStr = countCheck(databaseName, targetTableName, targetTableName,targetCountMap)  
        + valueListCheck(databaseName, targetTableName, targetTableName,targetValueMap);

        return resultStr.replaceAll("原数据库", "目标数据库");
    }

    /**
     * 输出验证脚本文件
     * @param filename
     * @param databaseName
     * @param middleTableName
     * @param targetTableName
     * @param countMap
     * @param valueMap
     */
    public void outPutCheckScript(String filename, String databaseName, String middleTableName,String targetTableName, 
                                Map<String, String> countMap, Map<String, String> valueMap){
        String outputDir = KettleConstant.WINDOW_OUTPUT_TEST_PATH_ORACLE + filename + "/";
        File file = new File(outputDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        //读取模板文件
        String text = FileContentUtil.readByClazz(this.getClass(), KettleConstant.SCRIPT_CHECK_PATH_ORACLE);
        //修改模板中数据
        text = text.replaceAll("SCRIPT_NAME", filename)
                   .replaceAll("MIDDLE_SQL", autoMiddleSql(databaseName, middleTableName, targetTableName, countMap, valueMap) )
                   .replaceAll("TARGET_SQL", autoTargetSql(databaseName, targetTableName, countMap, valueMap))
                   .replaceAll("DB_NAME", databaseName)
                   .replaceAll("TB_NAME",targetTableName);

        FileContentUtil.outputFile(text, outputDir, filename + ".ktr");
    }


    public static void main(String[] args) {

  
        
    }
    
}
