package com.VTiger.TestCases;

import org.testng.annotations.Test;

import com.Vtiger.ObjectRepo.CreateNewOrgPage;
import com.Vtiger.ObjectRepo.HomePage;
import com.Vtiger.ObjectRepo.OrgInfoPage;
import com.Vtiger.genric.BaseClass;
import com.Vtiger.genric.JavaUtil;
import com.Vtiger.genric.TestData;

import junit.framework.Assert;

public class TC01_CreateOrg  extends BaseClass{
	//@Parameters("Orgname")
	@Test(enabled= true)
	public void createorg () throws Throwable
	{
		HomePage homePage = new HomePage(driver);

		homePage.getOrglink().click();

		OrgInfoPage orgInfoPage= new OrgInfoPage(driver);
		orgInfoPage.getCreateorgbtn().click();

		TestData testData= new TestData();
		JavaUtil javaUtil = new JavaUtil();
		String Orgname=testData.getOrgname()+javaUtil.createRandomnumber();

		CreateNewOrgPage createNewOrgPage = new CreateNewOrgPage(driver);

		createNewOrgPage.getOrgname().sendKeys(Orgname);
		createNewOrgPage.getSaveorgbtn().click();

		Thread.sleep(4000);
		driver.navigate().refresh();

		homePage.getOrglink().click();

		orgInfoPage.searchforOrg(Orgname, "accountname");

		Thread.sleep(3000);

		String actualorgname=orgInfoPage.getfirstOrg().getText();

		Assert.assertEquals(Orgname, actualorgname);
		
	}

}
