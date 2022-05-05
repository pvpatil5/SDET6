package com.TESTNG.pac;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Screenshot1 {

	@Test
	public void scrnshot() throws IOException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888");

		TakesScreenshot screenshot = (TakesScreenshot)driver;

		String dest = "../SDET6/screenshot/Vtiger.png";

		File src = screenshot.getScreenshotAs(OutputType.FILE);

		File destpath = new File(dest);

		Files.copy(src, destpath);

	}
}
