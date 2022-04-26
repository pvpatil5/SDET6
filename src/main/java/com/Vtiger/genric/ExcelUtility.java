package com.Vtiger.genric;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{

	public String readDatafromExcel(String sheetname,int rownum,int cellnum) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis = new FileInputStream("../SDET6/SDET6.xlsx");
		return	WorkbookFactory.create(fis).getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();	

	}

	public int readNumericDatafromExcel(String sheetname,int rownum,int cellnum) throws EncryptedDocumentException, IOException  
	{
		FileInputStream fis = new FileInputStream("../SDET6/SDET6.xlsx");
		int value=	(int) WorkbookFactory.create(fis).getSheet(sheetname).getRow(rownum).getCell(cellnum).getNumericCellValue();

		return value;

	}

	public int getLastRow(String sheetname) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis = new FileInputStream("../SDET6/SDET6.xlsx");
		return WorkbookFactory.create(fis).getSheet(sheetname).getLastRowNum();

	}

}
