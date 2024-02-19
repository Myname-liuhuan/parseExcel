package com.example.parseexcel.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
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
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.fastjson2.JSON;
import com.example.parseexcel.common.constant.ExcelConstant;
import com.example.parseexcel.common.utils.StringUtils;
import com.example.parseexcel.entity.CSYLColDataEntity;
import com.example.parseexcel.entity.CSYLDataEntity;

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
        //String sourcePath = "D:\\run\\template\\csyl\\source";
        //String templatePath = "D:\\run\\template\\csyl\\template02.xlsx";
        //String outputPath = "D:\\run\\template\\csyl\\target\\";

        String sourcePath = "/Users/huanliu/Documents/ISPR/testfile/source/";
        String templatePath = "/Users/huanliu/Documents/ISPR/testfile/template02.xlsx";
        String outputPath = "/Users/huanliu/Documents/ISPR/testfile/target/";

        File directoryFile = new File(sourcePath);
        File[] files = directoryFile.listFiles();
        for (File file : files) {
            if (file.isHidden()){
                continue;
            }
            CSYLDataEntity data = new CSYLDataEntity();
            data.setSourceFileName(file.getName());
            DateTimeFormatter dtfer = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            data.setCreateDate(sourcePath);
            data.setCreateDate(dtfer.format(LocalDate.now()));
            data.setShortProjectName("ST-06_业务数据测试用例");
            data.setProjectName("ST-06_业务数据测试用例" + StringUtils.cutString(file.getName()));
            data.setWriterName("刘欢");
            data.setLastProjectName(StringUtils.cutLastString(file.getName()));
            
            //读取文件内容
            List<CSYLColDataEntity> colDataList = new ArrayList<>();
            try( InputStream in = new FileInputStream(file);
                XSSFWorkbook xwb = new XSSFWorkbook(in);){
                Sheet sheet = xwb.getSheetAt(ExcelConstant.SHEETINDEX);

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
                    CSYLColDataEntity colData = JSON.parseObject(JSON.toJSONString(beanMap), CSYLColDataEntity.class);
                    if (colData.getColId() == null || "".equals(colData.getColId())){
                        continue;
                    }
                    String colId = org.springframework.util.StringUtils.trimAllWhitespace(colData.getColId()).toUpperCase();
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
                    //序号格式化
                    colData.setColNo(String.valueOf(Double.valueOf(colData.getColNo())));

                    colDataList.add(colData);
                }
            }catch (Exception e) {
               e.printStackTrace();
            }
            //最后加一行空
            colDataList.add(new CSYLColDataEntity());
            compositeInsertion(data, colDataList, templatePath, outputPath);
        }
    }

    /**
     * excel复合生成
     */
    public void compositeInsertion(CSYLDataEntity data, List<CSYLColDataEntity> colDataList,
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

   

    public static void main(String[] args) {
        new GenerateTestCasesService().readSourceExcel();
    }
    
}
