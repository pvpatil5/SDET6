package com.Practice;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FutureDate {

	public static void main(String[] args) throws InterruptedException {
		Date date = new  Date();

		//		String currentdate=date.toString();
		//		
		//		//Thu Apr 28 18:09:03 IST 2022
		//		// 0   1   2    3      4    5 
		//		// Thu Apr 28 2022
		//		
		//		String[] arr = currentdate.split(" ");
		//		
		//		String day= arr[0];
		//		String month= arr[1];
		//		String dt= arr[2];
		//		String year = arr[5];
		//		
		//		String xpath= day+" "+month+" "+dt+" "+year;
		//		
		//		System.out.println(xpath);

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.makemytrip.com/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Thread.sleep(4000);

		driver.findElement(By.xpath("//span[@class='langCardClose']")).click();

		driver.findElement(By.xpath("//li[@data-cy=\"account\"]")).click();

		driver.findElement(By.id("fromCity")).sendKeys("BOM");
		
		driver.findElement(By.xpath("//div[.='BOM']")).click();
		

		driver.findElement(By.id("toCity")).sendKeys("HYD");
		
		driver.findElement(By.xpath("//div[.='HYD']")).click();

		driver.findElement(By.xpath("//span[.='DEPARTURE']")).click();

		Thread.sleep(1000);

		WebElement nextmonth = driver.findElement(By.xpath("//span[@aria-label='Next Month']"));

		for (int i = 0; i < 36; i++)
		{
			try {
				driver.findElement(By.xpath("//div[@aria-label=\"Sun Sep 04 2025\"]")).click();
				break;
			} catch (Exception e) {
				nextmonth.click();
			}
		}
	}

}
