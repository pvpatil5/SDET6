package com.VTiger.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import com.Vtiger.ObjectRepo.CreateNewOrgPage;
import com.Vtiger.ObjectRepo.HomePage;
import com.Vtiger.ObjectRepo.LoginPage;
import com.Vtiger.ObjectRepo.OrgInfoPage;
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

		LoginPage loginPage = new LoginPage(driver);
		loginPage.logintoApp();

		WebDriverUtil webDriverUtil = new WebDriverUtil(driver);

		webDriverUtil.maxwindow();
		webDriverUtil.pageloadtimeout();

		HomePage homePage= new HomePage(driver);
		homePage.getOrglink().click();


		OrgInfoPage orgInfoPage = new OrgInfoPage(driver);
		orgInfoPage.getCreateorgbtn().click();


		TestData testData= new TestData();
		JavaUtil javaUtil = new JavaUtil();
		String orgname=testData.getOrgname()+javaUtil.createRandomnumber();
		CreateNewOrgPage createNewOrgPage= new CreateNewOrgPage(driver);

		createNewOrgPage.getOrgname().sendKeys(orgname);

		webDriverUtil.selectValuefromdd("Active", createNewOrgPage.getRating());

		webDriverUtil.selectValuefromdd(createNewOrgPage.getIndustry(), "Education");

		webDriverUtil.selectValuefromdd(createNewOrgPage.getType(), 3);

		createNewOrgPage.getSaveorgbtn().click();

		Thread.sleep(4000);
		driver.navigate().refresh();

		homePage.getOrglink().click();

		orgInfoPage.searchforOrg(orgname, "accountname");

		Thread.sleep(3000);

		String actualorgname=orgInfoPage.getfirstOrg().getText();


		if (orgname.equals(actualorgname)) 
		{
			System.out.println("Tc Passes");
		}
		else {
			System.out.println("TC Fail");
		}

		homePage.logoutfromApp();

		Thread.sleep(10000);

		driver.close();
	}
}
