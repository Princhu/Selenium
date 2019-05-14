package com.sample.stepdefinitions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScrollBy {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		driver.get("http://www.espncricinfo.com");
		driver.manage().window().maximize();
		js.executeScript("window.scrollBy(0,2000)");
		Thread.sleep(2000);
		driver.quit();
		driver.close();
	}
}
