package com.VTiger.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;

import com.Vtiger.genric.ExcelUtility;

public class Excel 
{

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		Map<String, Integer> hp = new HashMap<String, Integer>();



		ExcelUtility excelUtility = new ExcelUtility();
		for (int i = 0; i <= excelUtility.getLastRow("Sheet1"); i++) 
		{
			int value=excelUtility.readNumericDatafromExcel("Sheet1", i, 1);
			String	names=excelUtility.readDatafromExcel("Sheet1", i, 0);

			hp.put(names, value);


		}
		//Set<Entry<String, Integer>> ie = hp.entrySet();
		
		System.out.println(hp);


	}

}
