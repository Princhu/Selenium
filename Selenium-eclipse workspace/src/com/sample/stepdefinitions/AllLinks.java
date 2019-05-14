package com.sample.stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AllLinks {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		java.util.List<WebElement> links=driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		for(int i=1;i<=links.size()-1;i=i+1)
		{
			System.out.println(links.get(i).getText());
		}
		driver.close();
		driver.quit();
	}
}
