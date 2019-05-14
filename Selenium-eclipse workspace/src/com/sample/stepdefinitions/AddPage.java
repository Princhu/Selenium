/* Author	: @Preetz
 * Team No.	: 8
 * Members	: Preethi 
 * 			  Archana Devi
 * Date		: 24-04-2019
 * Topic	: Add a new page in opensourcecms - wordpress and verify the same
 */
package com.sample.stepdefinitions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class AddPage {
	public static WebDriver driver;
	public String pageTitle;
	public String pageLink;
	public void AddPages() throws InterruptedException 
	{
		String expectedTitle = "Add New Page ‹ opensourcecms — WordPress";
		String expectedText="Welcome to WordPress!";
		
		//Chrome driver is used to launch chrome browser
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("http://5cc2d13e5eacc.s1.demo.opensourcecms.com/wordpress/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='http://5cc2d13e5eacc.s1.demo.opensourcecms.com/wordpress/wp-login.php']")).click();
		
		//login functionality
		driver.findElement(By.id("user_login")).sendKeys("opensourcecms");
		Thread.sleep(2000);
		driver.findElement(By.id("user_pass")).sendKeys("opensourcecms");
		Thread.sleep(2000);
		driver.findElement(By.id("wp-submit")).click();
		Thread.sleep(2000);
		
		/* Text Assertion to check if the app is logged in correctly using the welcome text displayed
		 * Throws exception if there is a change in the text displayed
		 */
		Assert.assertTrue("Text does not match", expectedText.equals(driver.findElement(By.xpath("//*[@id='welcome-panel']/div/h2")).getText()));
		
		driver.findElement(By.xpath("//*[@id='menu-pages']//*[@class='wp-menu-image dashicons-before dashicons-admin-page']")).click();
		driver.findElement(By.xpath("//a[@href='post-new.php?post_type=page']")).click();
		
		/* Title Assertion to check if the displayed page is Add New Page 
		 * Throws exception if there is a change in the title displayed
		 */
		Assert.assertTrue("Title does not match", expectedTitle.equals(driver.getTitle()));
		
		pageTitle="NATURE’S LAW";
		
		//Page title
		driver.findElement(By.id("title")).sendKeys(pageTitle);
		driver.findElement(By.id("content-html")).click();
		
		//Page contents
		driver.findElement(By.id("content")).sendKeys("Will to win..... Live and let live....be black or white..don't be a zebra...!!...you have to follow nature's law");
		driver.findElement(By.id("save-post")).click();
		driver.findElement(By.id("publish")).click();
		Thread.sleep(2000);
		
		//once a page has been added, a link to that page will be generated
		pageLink=driver.findElement(By.xpath("//span[@id='sample-permalink']//a")).getText();
		driver.findElement(By.xpath("//span[@id='sample-permalink']//a")).click();
		
		//retrieving the title of the added page
		String val1= driver.findElement(By.className("entry-title")).getText();
		
		//Function call to TakeScreenshot() to capture the page that was added in the website
		TakeScreenShot();
		System.out.println("Added page Name :"+val1);
		System.out.println("Page to be added :"+pageTitle);
		System.out.println("Link of the page :"+pageLink);
		
		//checks if the page added in wordpress is the one that was inserted in the title text field
		if(val1.equals(pageTitle))
		{
			System.out.println("The page added is verified");
		}
		else
		{
			System.out.println("Invalid page");
		}
	}
	public void TakeScreenShot()
	{
		//Function to take screenshot using TakesScreenshot
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try
		{   
			//stores the taken screenshot into the specified location
			FileHandler.copy(src,new File("H:\\WordPress_Selenium\\Added_Page.png"));
			driver.close();
			driver.quit();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void SaveLinkToExcel() throws IOException 
	{
		 //workbook creation
		 XSSFWorkbook workbook = new XSSFWorkbook();
		 
		 //excel sheet creation in the name specified
         XSSFSheet sheet = workbook.createSheet("Added Page Links");
         
         //data stored in two-dimensional array
         Object[][] bookData = {
                 				{"Page Name","Page Link",""},
                 				{pageTitle,pageLink,""},
         					   };
  
         int rowCount = 0;
     
         for (Object[] aBook : bookData) 
         {
        	 //the loop runs till there is data in object[][] array and indicates each row
             Row row = sheet.createRow(++rowCount);
              
             int columnCount = 0;
              
             for (Object field : aBook) 
             {
            	 //the loop runs till there is data in next row and indicates each cell
                 Cell cell = row.createCell(++columnCount);
                 //checks if the value being inserted into excel is an instance of string and typecasts into string
                 if (field instanceof String) 
                 {
                     cell.setCellValue((String) field);
                 }
                 //checks if the value being inserted into excel is an instance of integer and typecasts into integer
                 else if (field instanceof Integer) 
                 {
                     cell.setCellValue((Integer) field);
                 }
             }
              
         }
          
         /*
          * The original excel file will be retained the same if copying contents to excel fails
          */
         try (FileOutputStream outputStream = new FileOutputStream("H:\\WordPress_Selenium\\Booking1.xlsx")) 
         {
             workbook.write(outputStream);
         }
     
	}
	
	public static void main(String[] args) throws InterruptedException, IOException
	{
		//creation of object for the AddPage class
		AddPage page=new AddPage(); 
		
		//Function calls to AddPages() and SaveLinkToExcel() using object invocation
		page.AddPages();
		page.SaveLinkToExcel();
		
	}
}