package com.VTiger.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Vtiger.ObjectRepo.CreateNewOrgPage;
import com.Vtiger.ObjectRepo.HomePage;
import com.Vtiger.ObjectRepo.OrgInfoPage;
import com.Vtiger.genric.BaseClass;
import com.Vtiger.genric.JavaUtil;
import com.Vtiger.genric.TestData;
import com.Vtiger.genric.WebDriverUtil;

public class TC02_CreateOrg_DD  extends BaseClass{

	@Test
	public void createorg_dd () throws Throwable {
		HomePage homePage= new HomePage(driver);
		homePage.getOrglink().click();

		OrgInfoPage orgInfoPage = new OrgInfoPage(driver);
		orgInfoPage.getCreateorgbtn().click();

		TestData testData= new TestData();
		JavaUtil javaUtil = new JavaUtil();
		String orgname=testData.getOrgname()+javaUtil.createRandomnumber();
		CreateNewOrgPage createNewOrgPage= new CreateNewOrgPage(driver);

		createNewOrgPage.getOrgname().sendKeys(orgname);
		WebDriverUtil webDriverUtil= new WebDriverUtil(driver);

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

		Assert.assertEquals(actualorgname, orgname);
	}
}
