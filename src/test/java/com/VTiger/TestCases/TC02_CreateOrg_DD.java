package com.VTiger.TestCases;

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

public class TC02_CreateOrg_DD {
	public WebDriver driver;
	@Test
	public void createorg_dd () throws Throwable {

		// Launch Browser and login

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

		driver.findElement(By.xpath("//a[.='Organizations']")).click();

		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		TestData testData= new TestData();
		JavaUtil javaUtil = new JavaUtil();
		String orgname=testData.getOrgname()+javaUtil.createRandomnumber();

		driver.findElement(By.name("accountname")).sendKeys(orgname);

		WebElement ratingdd=driver.findElement(By.name("rating"));
		webDriverUtil.selectValuefromdd("Active", ratingdd);
		WebElement industrydd=driver.findElement(By.name("industry"));
		webDriverUtil.selectValuefromdd(industrydd, 8);
		WebElement typedd=driver.findElement(By.name("accounttype"));
		webDriverUtil.selectValuefromdd(typedd, "Customer");

		driver.findElement(By.name("button")).click();

		Thread.sleep(4000);
		driver.navigate().refresh();

		driver.findElement(By.xpath("//a[.='Organizations']")).click();

		driver.findElement(By.name("search_text")).sendKeys(orgname);

		WebElement searchorgdd=driver.findElement(By.name("search_field"));

		webDriverUtil.selectValuefromdd("Organization Name", searchorgdd);

		driver.findElement(By.name("submit")).click();

		Thread.sleep(3000);

		String actualorgname=driver.findElement(By.xpath("//a[@title='Organizations']")).getText();


		if (orgname.equals(actualorgname)) 
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
