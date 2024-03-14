package com.example.parseexcel.service;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

@Service
public class KettleTestScriptOutPutService {

    String templateStr = "count(t.%s) AS '%s', -- %s\n" ;
	String templateStr2 = "\n-- id\n" + 
                "SELECT\n" + 
                "'%s' AS 'column_name',\n" + 
                " %s AS 'count',\n" + 
                "'%s' AS 'database_name',\n" + 
                "'%s' AS 'table_name',\n" + 
                "'数量校验' AS 'check_type',\n" + 
                "'原数据库' AS 'data_type'\n" + 
                "FROM\n" + 
                "  temp_table\n"  + 
                "UNION ALL";

    String templateStr3 = "\nUNION ALL\n" + 
                "SELECT\n" + 
                "CONCAT('%s','_', IFNULL(%s,'null')) AS 'column_name',\r\n" + 
                "count AS 'count',\r\n" + 
                "'%s' AS 'database_name',\n" + 
                "'%s' AS 'table_name',\n" + 
                "'值列表校验' AS 'check_type',\n" + 
                "'原数据库' AS 'data_type'\n" + 
                "FROM (\n" + 
                "SELECT\n" + 
                "%s AS %s,\n" + 
                "count(*) AS 'count'\n" + 
                "FROM %s GROUP BY %s\n" + 
                ") AS m";
    /**
     * 
     * @param databaseName 目标数据库名称
     * @param middleTableName 中间表名称
     * @param targetTableName 目标表名称
     * @param countMap 数量校验
     * @param valueMap 值列表校验
     * @return
     */
    public String autoMiddleSql(String databaseName, String middleTableName,String targetTableName, Map<String, String> countMap, Map<String, String> valueMap){
        String dataStr = "";
        String dataStr2 = "";
        for(Entry<String, String> entry : countMap.entrySet()){
            dataStr += String.format(templateStr, entry.getKey(), entry.getValue(),entry.getValue());
            dataStr2 += String.format(templateStr2, entry.getValue(),entry.getValue()
                        , databaseName, targetTableName);
        }
        //处理最后的 UNION ALL
        dataStr2 = dataStr2.substring(0, dataStr2.lastIndexOf("UNION ALL"));
        
        String str1 = "WITH temp_table AS (\n" + 
            "SELECT\n" + 
            dataStr.substring(0, dataStr.lastIndexOf(',')) +
            "\nFROM " + middleTableName + " t" + 
            "\n)";
        
        //值列表校验
        String dataStr3 = "";
        for (Entry<String, String> entry : valueMap.entrySet()) {
            dataStr3 += String.format(templateStr3, entry.getValue(), entry.getValue(), databaseName, targetTableName, entry.getKey(), entry.getValue(), middleTableName, entry.getKey());
        }

        return str1 + dataStr2 + dataStr3;
    }

    public String autoTargetSql(String databaseName, String targetTableName, Map<String, String> countMap, Map<String, String> valueMap){
        return autoMiddleSql(databaseName, targetTableName, targetTableName, countMap, valueMap);
    }

    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<>();
        map.put("dms_id", "ID");
        map.put("ssc_public_id", "SSC_PUBLIC_ID");
        map.put("SSCCODE", "SSC_CODE");

        Map<String, String> valueMap = new TreeMap<>();
        valueMap.put("DELETEFLAG", "del_flag");

        System.out.println(new KettleTestScriptOutPutService().autoMiddleSql("infra-dms-wty", "srv_ssc_m_middle", "t_wty_ssc", map, valueMap)); 
    }
    
}
