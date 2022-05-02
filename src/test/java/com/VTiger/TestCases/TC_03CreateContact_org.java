package com.VTiger.TestCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.Vtiger.genric.JavaUtil;
import com.Vtiger.genric.ProppertyFiles;
import com.Vtiger.genric.TestData;
import com.Vtiger.genric.WebDriverUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_03CreateContact_org {
	public WebDriver driver;
	@Test
	public void createcontact_org() throws IOException, InterruptedException {
		ProppertyFiles proppertyFiles= new ProppertyFiles();

		String BROWSER=proppertyFiles.readDatafrompropertyfile("browser");

		if (BROWSER.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else {
			System.out.println("Invalid input");
		}

		driver.get(proppertyFiles.readDatafrompropertyfile("url"));

		driver.findElement(By.name("user_name")).sendKeys(proppertyFiles.readDatafrompropertyfile("username"));
		driver.findElement(By.name("user_password")).sendKeys(proppertyFiles.readDatafrompropertyfile("password"));
		driver.findElement(By.id("submitButton")).click();

		WebDriverUtil webDriverUtil = new WebDriverUtil(driver);

		webDriverUtil.maxwindow();
		webDriverUtil.pageloadtimeout();

		driver.findElement(By.xpath("//a[.='Contacts']")).click();

		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		TestData testData= new TestData();

		String contactname=testData.getLastName();

		driver.findElement(By.name("lastname")).sendKeys(contactname);

		String createcontactpagetitle=driver.getTitle();

		System.out.println("Parent title="+createcontactpagetitle);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		Thread.sleep(2000);
		webDriverUtil.switchWindow("Accounts");
		
		Thread.sleep(4000);

		driver.findElement(By.id("search_txt")).sendKeys("Mango");

		driver.findElement(By.name("search")).click();

		driver.findElement(By.id("1")).click();

		Thread.sleep(2000);
		webDriverUtil.switchWindow("Contacts");

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();


		Thread.sleep(6000);

		String contactid=driver.findElement(By.xpath("//td[@id='mouseArea_First Name']/../td[4]")).getText();


		driver.findElement(By.xpath("//a[.='Contacts']")).click();

		driver.findElement(By.xpath("//input[@class='txtBox']")).sendKeys(contactid);

		driver.findElement(By.name("submit")).click();
		
		Thread.sleep(2000);
		
		String actualcontactlastname=driver.findElement(By.xpath("(//a[@title='Contacts'])[2]")).getText();

		
		if (actualcontactlastname.equals(contactname)) 
		{
			System.out.println("Tc Passes");
		}
		else {
			System.out.println("TC Fail");
		}

		WebElement signoutimg=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		webDriverUtil.moveToelement(signoutimg);

		driver.findElement(By.xpath("//a[.='Sign Out']")).click();

		Thread.sleep(10000);

		driver.close();
		
		







	}
}
