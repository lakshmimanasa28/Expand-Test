package com.expandtest.utils;



import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	public static Object[][] getTestData(String pathname, String sheetname){
		Object[][] data=null;
		try {
			FileInputStream fis=new FileInputStream(pathname);
			Workbook wb=new XSSFWorkbook(fis);
			Sheet sh=wb.getSheet(sheetname);
			
			int rowCount=sh.getPhysicalNumberOfRows();
			int colCount=sh.getRow(0).getPhysicalNumberOfCells();
			
			data=new Object[rowCount-1][colCount];
			for(int i=1;i<rowCount;i++) {
				Row row=sh.getRow(i);
				for(int j=0;j<colCount;j++) {
					Cell cell=row.getCell(j);
					if(cell==null) {
						data[i-1][j]="";
					}else{
						data[i-1][j]=cell.toString();
					}
				}
			}
			wb.close();
			fis.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
