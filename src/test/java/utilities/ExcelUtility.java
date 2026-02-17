package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;



public class ExcelUtility {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;//applying styles on the cell like geen colour for pass or red color for fail
	String path;
	
	public ExcelUtility(String path) {
		this.path=path;
	}
	
	
	public int getRowCount(String xlSheet) throws IOException {
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(xlSheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}
	
	public int getCellCount(String xlSheet,int rownum) throws IOException {
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(xlSheet);
		row= ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	public String getCellData(String xlSheet,int rownum,int cellnum) throws IOException {
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(xlSheet);
		row= ws.getRow(rownum);
		cell = row.getCell(cellnum);
		
		String Data;
		
		try {
			Data= cell.toString();
		}
		catch(Exception e) {
			Data="";
		}
		
		
		wb.close();
		fi.close();
		return Data;
	}
	
	public void setCellData(String xlSheet,int rownum,int colnum,String data) throws IOException {
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(xlSheet);
		row= ws.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public void fillGreenColor(String xlSheet,int rownum,int colnum) throws IOException {
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(xlSheet);
		row= ws.getRow(rownum);
		cell=row.getCell(colnum);
		
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);		
		cell.setCellStyle(style);
	
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public void fillRedColor(String xlSheet,int rownum,int colnum) throws IOException {
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(xlSheet);
		row= ws.getRow(rownum);
		cell=row.getCell(colnum);
		
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);		
		cell.setCellStyle(style);
	
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

}
