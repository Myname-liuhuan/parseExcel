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

import java.io.File;
import java.util.UUID;

@Service
public class SimpleParseService {

    private int rowStartIndex = 8;
    private int sheetIndex = 2;

    /**
     * 简单解析
     * @param file
     * @return
     */
    public String excelToText(@RequestParam MultipartFile file){
        //判断是否excel
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)){
            return "文件错误";
        }
        try {
            //POIFSFileSystem pfs = new POIFSFileSystem(file.getInputStream());
            XSSFWorkbook xwb = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = xwb.getSheetAt(sheetIndex);

            int rowEndIndex = sheet.getLastRowNum();
            for (int i = rowStartIndex; i <= rowEndIndex; i++){
                Row row = sheet.getRow(i);
                int colStartIndex = row.getFirstCellNum();
                int colEndIndex = row.getLastCellNum();
                for (int m = colStartIndex; m < colEndIndex; m++){
                    Cell cell = row.getCell(m);
                    String s = cell==null?"":cell.toString();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }




        return "";
    }

//    private
}
