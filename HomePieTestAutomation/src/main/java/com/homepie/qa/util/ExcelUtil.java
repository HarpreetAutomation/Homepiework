package com.homepie.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil
{
    private static Workbook workbook;
    private static Sheet sheet;
    private static Cell cell;
    String os = System.getProperty("os.name");

    public void SetExcelFile(String fileName,String sheetName ) throws IOException
    {   
    
        String filePath = Util.testDataLocation();
        //Create an object of File class to open excel file
        File file =    new File(filePath);
        
        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file+fileName);
        
        //creating workbook instance that refers to .xls file
        workbook=new XSSFWorkbook(inputStream);
        
        //creating a Sheet object
        sheet=workbook.getSheet(sheetName);
    	
    	System.out.println(ClassLoader.getSystemResourceAsStream(filePath));
    }

    public String getCellDataString(int rowNumber, int cellNumber)
    {
        //getting the cell value from rowNumber and cell Number
        return sheet.getRow(rowNumber).getCell(cellNumber).getStringCellValue();
    }

    public Double getCellDataNumeric(int rowNumber, int cellNumber){
        return sheet.getRow(rowNumber).getCell(cellNumber).getNumericCellValue();
    }

    public boolean getCellDataBoolean(int rowNumber, int cellNumber){
        return sheet.getRow(rowNumber).getCell(cellNumber).getBooleanCellValue();
    }
}
