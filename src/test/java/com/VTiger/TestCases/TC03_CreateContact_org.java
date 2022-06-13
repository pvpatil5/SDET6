package com.VTiger.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Vtiger.ObjectRepo.ConatctInfoPage;
import com.Vtiger.ObjectRepo.ContactOrg_popup;
import com.Vtiger.ObjectRepo.CreateNewContactPage;
import com.Vtiger.ObjectRepo.HomePage;
import com.Vtiger.genric.BaseClass;
import com.Vtiger.genric.TestData;
import com.Vtiger.genric.WebDriverUtil;

public class TC03_CreateContact_org extends  BaseClass {
	@Test
	public void createcontact_org() throws IOException, InterruptedException 
	{
		WebDriverUtil webDriverUtil = new WebDriverUtil(driver);

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

		Assert.assertEquals(actualcontactlastname, contactname);
		
		Assert.assertEquals(false, true);
	}
}
