package com.Practice;

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportt 
{

	WebDriver driver ;
	ExtentHtmlReporter reporter;

	ExtentReports reports;

	ExtentTest test;
	@Test

	public void test() {
		WebDriverManager.chromedriver().setup();

		reporter= new ExtentHtmlReporter("..//SDET6/Report.html");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName("VTiger");
		reporter.config().setDocumentTitle("NEW");

		reports= new ExtentReports();
		reports.attachReporter(reporter);

		test = reports.createTest("new");


		driver = new ChromeDriver();


		driver.get("https://amazon.in");

		Assert.assertFalse(false);

		reports.flush();
	}









}

