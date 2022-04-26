package com.VTiger.TestCases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.Vtiger.genric.ExcelUtility;
import com.Vtiger.genric.ProppertyFiles;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC001_TestCaseNameTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		ProppertyFiles prop = new ProppertyFiles();
		WebDriver driver;
		String BROWSER=	prop.readDatafrompropertyfile("browser");

		if (BROWSER.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();

		}
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else
		{
			System.out.println("No Browser Provided Launching Default browser");
			driver= new ChromeDriver();
		}

		driver.get(prop.readDatafrompropertyfile("url"));

		driver.findElement(By.name("user_name")).sendKeys(prop.readDatafrompropertyfile("username"));
		driver.findElement(By.name("user_password")).sendKeys(prop.readDatafrompropertyfile("password"));


	}

}
