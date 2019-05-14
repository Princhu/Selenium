package com.sample.stepdefinitions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class ScrollByIE {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.ie.driver", "C:\\Selenium\\IEDriverServer_x64_3.14.0\\IEDriverServer.exe");
		WebDriver driver=new InternetExplorerDriver();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		driver.get("http://www.espncricinfo.com");
		driver.manage().window().maximize();
		js.executeScript("window.scrollBy(0,2000)");
		Thread.sleep(2000);
		driver.quit();
		driver.close();
	}
}
