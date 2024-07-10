package com.example.parseexcel.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson2.JSON;
import com.example.parseexcel.common.constant.ExcelConstant;
import com.example.parseexcel.common.utils.StringUtils;
import com.example.parseexcel.module.parseexcel.dao.model.CSYLColData;
import com.example.parseexcel.module.parseexcel.dao.model.CSYLData;

/**
 * 测试用例生成逻辑
 * 读取excel提取数据，然后按照模板生成测试用例的excel
 */
@Service
public class GenerateTestCasesService {

    /**
     * excel单一模式生成
     */
    public void readSourceExcel(){

        //先读取文件
        //String sourcePath = "D:\\svnsyc\\01_工程\\02_详细设计\\01_数据要件\\售后（正式版）\\03_售后保修";
        String sourcePath = "D:\\run\\template\\csyl\\source";
        String templatePath = "D:\\run\\template\\csyl\\template02.xlsx";
        String templatePath2 = "D:\\run\\template\\csyl\\template03.xlsx";
        String outputPath = "D:\\run\\template\\csyl\\target\\";

        // String sourcePath = "/Users/huanliu/Documents/ISPR/testfile/source/";
        // String templatePath = "/Users/huanliu/Documents/ISPR/testfile/template02.xlsx";
        // String outputPath = "/Users/huanliu/Documents/ISPR/testfile/target/";

        File directoryFile = new File(sourcePath);
        File[] files = directoryFile.listFiles();
        for (File file : files) {
            if (file.isHidden()){
                continue;
            }
            CSYLData data = new CSYLData();
            data.setSourceFileName(file.getName());
            DateTimeFormatter dtfer = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            data.setCreateDate(sourcePath);
            data.setCreateDate(dtfer.format(LocalDate.now()));
            String title = "ST-06_业务数据测试用例";
            // /String title = "ST-05_基础数据测试用例";
            data.setShortProjectName(title);
            data.setProjectName(title + StringUtils.cutString(file.getName()));
            data.setWriterName("刘欢");

            CSYLData data2 = (CSYLData) data.clone();
            //是否是二合一
            boolean isTwo2One = false;
            
            //读取文件内容
            List<CSYLColData> colDataList = new ArrayList<>();
            List<CSYLColData> colDataList2 = new ArrayList<>();
            try( InputStream in = new FileInputStream(file);
                XSSFWorkbook xwb = new XSSFWorkbook(in)){
                int tt = xwb.getNumberOfSheets();
                if ( tt == 4) {
                    isTwo2One = true;
                }
                //两次循环，匹配二合一要件
                for(int n = ExcelConstant.SHEETINDEX; n < ExcelConstant.SHEETINDEX + 2; n++){
                    if(!isTwo2One && n != ExcelConstant.SHEETINDEX){
                        break;
                    }
                    Sheet sheet = xwb.getSheetAt(n);
                    //先读取头部信息
                    Row headRow = sheet.getRow(ExcelConstant.HEAD_INFO_ROW_INDEX);
                    Cell enCell = headRow.getCell(9);
                    String tableNameEn =  enCell==null? "":enCell.toString();
                    Cell zhCell = headRow.getCell(13);
                    String tableNameZh =  zhCell==null? "":zhCell.toString();
                    Cell sourceTableNameAllCell = headRow.getCell(17);
                    String sourceTableNameAll =  sourceTableNameAllCell==null? "":sourceTableNameAllCell.toString();
                    if (n == ExcelConstant.SHEETINDEX) {
                        data.setTableNameEn(tableNameEn);
                        data.setTableNameZh(tableNameZh);
                        data.setSourceTableNameAll(sourceTableNameAll);
                    }else{
                        data2.setTableNameEn(tableNameEn);
                        data2.setTableNameZh(tableNameZh);
                        data2.setSourceTableNameAll(sourceTableNameAll);
                    }
    
                    int rowEndIndex = sheet.getLastRowNum();
                    for (int i = ExcelConstant.ROWSTARTINDEX + 1; i <= rowEndIndex; i++){
                        Row row = sheet.getRow(i);
    
                        int colEndIndex = row.getLastCellNum();
                        Map<String, Object> beanMap = new HashMap<>();
                        for (int m = ExcelConstant.COLSTARTINDEX; m < colEndIndex; m++){
                            Cell cell = row.getCell(m);
                            String str =  cell==null? "":cell.toString();
                            beanMap.put(ExcelConstant.COLMAP.get(m), str);
                        }
                        CSYLColData colData = JSON.parseObject(JSON.toJSONString(beanMap), CSYLColData.class);
                        //如果没有字段名称，则数据无意义
                        if (colData.getColId() == null || "".equals(colData.getColId())){
                            continue;
                        }
    
                        String colId = org.springframework.util.StringUtils.trimAllWhitespace(colData.getColId()).toUpperCase();
                        colData.setColId(colId);

                        if (ExcelConstant.whiteSet.contains(colId)){
                            colData.setCheckFlag(ExcelConstant.NO);
                            colData.setTestDone(ExcelConstant.NA);
                            colData.setCheckType(ExcelConstant.NA);
                        }else{
                            colData.setCheckFlag(ExcelConstant.YES);
                            colData.setTestDone(ExcelConstant.OK);
                            colData.setCheckType(ExcelConstant.CHECK_NUMBER);
                        }
                        //删除标记需要被验证
                        if (colId.equalsIgnoreCase("DEL_FLAG")){
                            colData.setCheckType(ExcelConstant.CHECK_VALUE_LIST);
                        }
    
                        String p = "[0-9]+(\\.)?[0-9]*";
                        //序号格式化
                        if(colData.getColNo().matches(p)){
                            colData.setColNo(String.valueOf(Double.valueOf(colData.getColNo()).intValue()));
                        }
                        if (colData.getColByte().matches(p)) {
                            colData.setColByte(String.valueOf(Double.valueOf(colData.getColByte()).intValue()));
                        }
                        if (n == ExcelConstant.SHEETINDEX) {
                            colDataList.add(colData);
                        }else{
                            colDataList2.add(colData);
                        }
                    }
                }
                
            }catch (Exception e) {
               e.printStackTrace();
            }
            //最后加一行空
            colDataList.add(new CSYLColData());
            if (!isTwo2One) {
                compositeInsertion(data, colDataList, templatePath, outputPath);
            }else{
                compositeInsertion(data, data2, colDataList,colDataList2, templatePath2, outputPath);
            }
           
        }
    }

    /**
     * excel复合生成
     */
    public void compositeInsertion(CSYLData data, List<CSYLColData> colDataList,
                                   String templatePath, String outputPath){
        //工作薄对象
        ExcelWriter workBook = EasyExcel.write(outputPath + data.getProjectName() + ".xlsx")
        .withTemplate(templatePath).build();

        //工作区对象
        for (int i = 0; i < 3; i++) {
            WriteSheet sheet = EasyExcel.writerSheet(i).build();
            workBook.fill(data, sheet);
            if (i > 1) {
                workBook.fill(colDataList, sheet);
            }
        }
        workBook.finish();
    }

    /**
     * excel复合生成(源数据二合一)
     */
    public void compositeInsertion(CSYLData data, CSYLData data2, List<CSYLColData> colDataList
    , List<CSYLColData> colDataList2, String templatePath, String outputPath){
        //工作薄对象
        ExcelWriter workBook = EasyExcel.write(outputPath + data.getProjectName() + ".xlsx")
        .withTemplate(templatePath).build();

        //工作区对象
        for (int i = 0; i < 4; i++) {
            WriteSheet sheet = EasyExcel.writerSheet(i).build();
            if (i < 2) {
                workBook.fill(data, sheet);
            }else if (i == 2) {
                workBook.fill(data, sheet);
                workBook.fill(colDataList, sheet);
            }else{
                workBook.fill(data2, sheet);
                workBook.fill(colDataList2, sheet);
            }
        }
        workBook.finish();
    }

   

    public static void main(String[] args) {
        new GenerateTestCasesService().readSourceExcel();
        //System.out.println("29080".matches("[0-9]+(\\.)?[0-9]*"));
    }
    
}
