package com.example.parseexcel.common.constant;

public class KettleTestScriptConstant {
    public final static String TEMPLATE_STR = "count(t.%s) AS %s, -- %s\n" ;
	public final static String TEMPLATE_STR2 = "\n-- id\n" + 
                "SELECT\n" + 
                "'%s' AS column_name,\n" + 
                " %s AS count,\n" + 
                "'%s' AS database_name,\n" + 
                "'%s' AS table_name,\n" + 
                "'数量校验' AS check_type,\n" + 
                "'原数据库' AS data_type\n" + 
                "FROM\n" + 
                "  temp_table\n"  + 
                "UNION ALL";

    /**
     * oracle语法
     */
    public final static String TEMPLATE_STR3 = "\nUNION ALL\n" + 
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
     * mysql语法
     */
    public final static String TEMPLATE_STR4 = "\nUNION ALL\n" + 
                "SELECT\n" + 
                "('%s' || '_' || NVL(TO_CHAR(%s),'null')) AS column_name,\r\n" + 
                "count AS count,\r\n" + 
                "'%s' AS database_name,\n" + 
                "'%s' AS table_name,\n" + 
                "'值列表校验' AS check_type,\n" + 
                "'原数据库' AS data_type\n" + 
                "FROM (\n" + 
                "SELECT\n" + 
                "%s AS %s,\n" + 
                "count(*) AS count\n" + 
                "FROM %s GROUP BY %s\n" + 
                ") ";

}
