package com.VTiger.TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.Vtiger.ObjectRepo.ConatctInfoPage;
import com.Vtiger.ObjectRepo.ContactOrg_popup;
import com.Vtiger.ObjectRepo.CreateNewContactPage;
import com.Vtiger.ObjectRepo.HomePage;
import com.Vtiger.ObjectRepo.LoginPage;
import com.Vtiger.genric.ProppertyFiles;
import com.Vtiger.genric.TestData;
import com.Vtiger.genric.WebDriverUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC03_CreateContact_org {
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

		LoginPage loginPage = new LoginPage(driver);
		loginPage.logintoApp();

		WebDriverUtil webDriverUtil = new WebDriverUtil(driver);

		webDriverUtil.maxwindow();
		webDriverUtil.pageloadtimeout();
		HomePage homePage = new HomePage(driver);
		homePage.getContactslink().click();

		ConatctInfoPage conatctInfoPage = new  ConatctInfoPage(driver);
		conatctInfoPage.getCreatecontactsimg().click();
		TestData testData= new TestData();

		String contactname=testData.getLastName();
		
		System.out.println(contactname);

		CreateNewContactPage createNewContactPage = new CreateNewContactPage(driver);
		createNewContactPage.getLastNameEdt().sendKeys(contactname);
		
		createNewContactPage.getOrganizationLookUpImage().click();

		Thread.sleep(2000);
		webDriverUtil.switchWindow("Accounts");
		
		Thread.sleep(4000);
		
		ContactOrg_popup contactOrg_popup = new ContactOrg_popup(driver);
		
		contactOrg_popup.searchandSelectforOrg("Mango");

		Thread.sleep(2000);
		webDriverUtil.switchWindow("Contacts");

		createNewContactPage.getSaveBtn().click();

		Thread.sleep(8000);

		String contactid=createNewContactPage.getcontactid().getText();

		System.out.println(contactid);
		driver.navigate().refresh();
		
		homePage.getContactslink().click();
		
		conatctInfoPage.checkcontactcreated(contactid);
		
		Thread.sleep(4000);
		
		String actualcontactlastname=conatctInfoPage.getactualcontact().getText();

		System.out.println(actualcontactlastname);
		
		if (actualcontactlastname.equals(contactname)) 
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
