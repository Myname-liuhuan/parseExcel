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

    public String autoMiddleSql(String databaseName, String middleTableName,String targetTableName, Map<String, String> data){
        String dataStr = "";
        String dataStr2 = "";
        for(Entry<String, String> entry : data.entrySet()){
            dataStr += String.format(templateStr, entry.getKey(), entry.getValue(),entry.getValue());
            dataStr2 += String.format(templateStr2, entry.getValue(),entry.getValue()
                        , databaseName, targetTableName);
        }
        String str1 = "WITH temp_table AS (\n" + 
            "SELECT\n" + 
            dataStr.substring(dataStr.lastIndexOf(',', dataStr.length())) +
            "\nFROM " + middleTableName + " t" + 
            "\n)";

        return str1 + dataStr2;
    }

    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<>();
        map.put("dms_id", "ID");
        map.put("ssc_public_id", "SSC_PUBLIC_ID");
        map.put("SSCCODE", "SSC_CODE");

        System.out.println(new KettleTestScriptOutPutService().autoMiddleSql("infra-dms-wty", "srv_ssc_m_middle", "t_wty_ssc", map)); 
    }
    
}
