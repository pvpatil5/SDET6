package com.Vtiger.genric;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listners implements ITestListener  {

	ExtentHtmlReporter reporter;

	ExtentReports reports;

	ExtentTest test;

	public void onTestStart(ITestResult result) {
		test	= reports.createTest(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {


	}

	public void onTestFailure(ITestResult result) 
	{
		test.log(Status.FAIL, result.getName()+" is Failed");
		test.log(Status.FAIL, result.getThrowable());


		try {
			String path = BaseClass.takeScreenshot(result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void onTestSkipped(ITestResult result) {

		test.log(Status.SKIP, result.getMethod().getMethodName()+"Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {


	}

	public void onStart(ITestContext context) {
		JavaUtil jv = new JavaUtil();
		String date=	jv.getDate();

		reporter= new ExtentHtmlReporter("..//SDET6/Report.html");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName("VTiger");
		reporter.config().setDocumentTitle("NEW");

		reports= new ExtentReports();
		reports.attachReporter(reporter);

		reports.setSystemInfo("Tester", "RMG");


	}

	public void onFinish(ITestContext context) {
		reports.flush();

	}

}
