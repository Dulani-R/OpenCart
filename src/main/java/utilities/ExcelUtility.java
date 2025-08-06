package utilities;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtility {

    public FileInputStream fileInput;
    public FileOutputStream fileOutput;
    public XSSFWorkbook workBook;
    public XSSFSheet workSheet;
    public XSSFRow workRow;
    public XSSFCell workCell;
    public CellStyle cellStyle;
    String path;

    public ExcelUtility(String path){
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fileInput = new FileInputStream(path);
        workBook = new XSSFWorkbook(fileInput);
        workSheet = workBook.getSheet(sheetName);
        int rowCount = workSheet.getLastRowNum();
        workBook.close();
        fileInput.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rowNumber) throws IOException {
        fileInput = new FileInputStream(path);
        workBook = new XSSFWorkbook(fileInput);
        workSheet = workBook.getSheet(sheetName);
        workRow = workSheet.getRow(rowNumber);
        int cellCount = workRow.getLastCellNum();
        workBook.close();
        fileInput.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int column) throws IOException {
        fileInput = new FileInputStream(path);
        workBook = new XSSFWorkbook(fileInput);
        workSheet = workBook.getSheet(sheetName);
        workRow = workSheet.getRow(rowNum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(workCell);
        }
        catch (Exception e){
            data ="";
        }

        workBook.close();
        fileInput.close();
        return  data;
    }

    public void setCellData(String sheetName, int rowNum, int column, String data) throws IOException {
        File xlFile = new File(path);

        if(!xlFile.exists()){
            workBook = new XSSFWorkbook();
            fileOutput = new FileOutputStream(path);
            workBook.write(fileOutput);
        }

        fileInput = new FileInputStream(path);
        workBook = new XSSFWorkbook(fileInput);

        if(workBook.getSheetIndex(sheetName)==-1)
            workBook.createSheet(sheetName);
        workSheet = workBook.getSheet(sheetName);

        if(workSheet.getRow(rowNum)== null)
            workSheet.createRow(rowNum);
        workRow = workSheet.getRow(rowNum);

        workCell = workRow.createCell(column);
        workCell.setCellValue(data);
        fileOutput = new FileOutputStream(path);
        workBook.write(fileOutput);
        workBook.close();
        fileInput.close();
        fileOutput.close();
    }

    public void fillGreenColor(String sheetName, int column, int rowNum) throws IOException {
        fileInput = new FileInputStream(path);
        workBook = new XSSFWorkbook(fileInput);
        workSheet = workBook.getSheet(sheetName);

        workRow = workSheet.getRow(rowNum);
        workCell = workRow.getCell(column);

        cellStyle = workBook.createCellStyle();

        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        workCell.setCellStyle(cellStyle);
        workBook.write(fileOutput);
        workBook.close();
        fileInput.close();
        fileOutput.close();
    }

    public void fillRedColor(String sheetName, int rowNum, int column) throws IOException {
        fileInput = new FileInputStream(path);
        workBook = new XSSFWorkbook(fileInput);
        workSheet = workBook.getSheet(sheetName);
        workRow = workSheet.getRow(rowNum);
        workCell = workRow.getCell(column);

        cellStyle = workBook.createCellStyle();

        cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        workCell.setCellStyle(cellStyle);
        workBook.write(fileOutput);
        workBook.close();
        fileInput.close();
        fileOutput.close();
    }


}
