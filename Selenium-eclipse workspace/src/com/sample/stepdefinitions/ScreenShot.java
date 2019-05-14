package com.sample.stepdefinitions;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try
		{
			FileHandler.copy(src,new File("C:\\Users\\HP\\Pictures\\Screenshots\\Samplepic.png"));
			driver.close();
			driver.quit();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}
