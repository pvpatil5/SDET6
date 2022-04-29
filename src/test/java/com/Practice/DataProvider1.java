package com.Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataProvider1 
{

	@Test(dataProvider = "getData")
	public void travel(String from, String to) throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.makemytrip.com/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[@class='langCardClose']")).click();

		driver.findElement(By.xpath("//li[@data-cy=\"account\"]")).click();

		driver.findElement(By.id("fromCity")).sendKeys(from);

		driver.findElement(By.xpath("//div[.='"+from+"']")).click();

		driver.findElement(By.id("toCity")).sendKeys(to);

		driver.findElement(By.xpath("//div[.='"+to+"']")).click();

	//	driver.findElement(By.xpath("//span[.='DEPARTURE']")).click();
		
		Thread.sleep(5000);
		
		driver.close();

	}

	@DataProvider
	public Object[][] getData() 
	{
		Object arr[][]= new Object[4][2];

		arr[0][0]="BOM";
		arr[0][1]="HYD"	;
		
		arr[1][0]="PNQ";
		arr[1][1]="STV"	;
		
		arr[2][0]="IXU";
		arr[2][1]="DIU"	;
		
		arr[3][0]="MAA";
		arr[3][1]="HYD"	;
		
		return arr;

	}


}
