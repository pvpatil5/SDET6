package com.Vtiger.genric;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.Vtiger.ObjectRepo.HomePage;
import com.Vtiger.ObjectRepo.LoginPage;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	public static WebDriver sdriver;
	public ProppertyFiles proppertyFiles= new ProppertyFiles();

	@BeforeSuite
	public void connecttoDB() {
		System.out.println("====DB Connection====");
	}

	@AfterSuite
	public void disConnectDB() {
		System.out.println("=====Disconnected DB=====");
	}

	//@Parameters("BROWSER")
	@BeforeClass
	public void launchBrowser() throws IOException 
	{
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
		sdriver=driver;
		driver.get(proppertyFiles.readDatafrompropertyfile("url"));
		WebDriverUtil webDriverUtil = new WebDriverUtil(driver);
		webDriverUtil.maxwindow();
		webDriverUtil.pageloadtimeout();

	}

	@BeforeMethod
	public void login() throws IOException 
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.logintoApp();

	}
	@AfterMethod
	public void logout() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.logoutfromApp();
	}
	@AfterTest
	public void closeBrowser() throws InterruptedException {

		Thread.sleep(10000);
		driver.close();

	}

	public static void takeScreenshot(String methodname) {
		TakesScreenshot screenshot = (TakesScreenshot)sdriver;

		String dest = "../SDET6/screenshot/"+methodname+".png";

		File src = screenshot.getScreenshotAs(OutputType.FILE);

		File destpath = new File(dest);

		try {
			Files.copy(src, destpath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
