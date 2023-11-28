package com.example.parseexcel.service;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class SimpleParseService {

    //表头开的行
    private int rowStartIndex = 6;
    private int sheetIndex = 2;

    /**
     * 简单解析
     * @param file
     * @return
     */
    public Map<String, Object> excelToText(@RequestParam MultipartFile file){

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String createTemplate = "`%s` %s %s COMMENT '%s',\n";
        String selectTemplate = " t.%s AS %s,\n";
        //需要的字段
        Map<String, String> singleRowMap = new HashMap<String, String>(){{
            put("字段名", null);
            put("字段ID", null);
            put("NotNull", null);
            put("字段属性", null);
            put("原字段名", null);
        }};

        //判断是否excel
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)){
            return null;
        }
        try {
            XSSFWorkbook xwb = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = xwb.getSheetAt(sheetIndex);
            Map<Integer, String> mapIndex = new HashMap<>();
            //表 头行
            Row thRow = sheet.getRow(rowStartIndex);
            for (Cell cell : thRow) {
                if (cell != null && !StringUtils.isEmpty(cell.toString()) && singleRowMap.containsKey(cell.toString())){
                    mapIndex.put(cell.getColumnIndex(), cell.toString());
                }
            }

            int rowEndIndex = sheet.getLastRowNum();
            for (int i = rowStartIndex + 2; i <= rowEndIndex; i++){
                Row row = sheet.getRow(i);

                int colStartIndex = row.getFirstCellNum();
                int colEndIndex = row.getLastCellNum();
                for (int m = colStartIndex; m < colEndIndex; m++){
                    if (mapIndex.containsKey(m)){
                        Cell cell = row.getCell(m);
                        singleRowMap.put(mapIndex.get(m), cell==null?"":cell.toString());
                    }
                }
                sb.append(String.format(createTemplate,
                        StringUtils.trimAllWhitespace(singleRowMap.get("字段ID")),
                        singleRowMap.get("字段属性"),
                        "Y".equalsIgnoreCase(singleRowMap.get("NotNull"))? "NOT NULL":"",
                        singleRowMap.get("字段名")));
                sb2.append(String.format(selectTemplate, singleRowMap.get("原字段名"),
                        singleRowMap.get("字段ID")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("create", sb.toString());
        resultMap.put("select", sb2.toString());
        return resultMap;
    }

    public static void main(String[] args) {
        String p = "^[a-z]";
        System.out.println("1".matches(p));
    }

}
