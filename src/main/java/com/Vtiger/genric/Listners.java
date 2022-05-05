package com.Vtiger.genric;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listners implements ITestListener  {

	public void onTestStart(ITestResult result) {
	
		
	}

	public void onTestSuccess(ITestResult result) {
	
		
	}

	public void onTestFailure(ITestResult result) {
		
		BaseClass.takeScreenshot(result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult result) {
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	public void onStart(ITestContext context) {
		
		
	}

	public void onFinish(ITestContext context) {
	
		
	}

}
