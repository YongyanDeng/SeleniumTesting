package com.selenium.scenarios.testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.screenshot.Screenshot;

public class Scenario5 {
	
	static Logger logger = Logger.getLogger(Scenario5.class.getName());

	public static void createPlan(WebDriver driver, Properties prop, ExtentTest test1) throws InterruptedException {

		String parent = driver.getWindowHandle();
		
		Thread.sleep(500);
		test1.info("S5_Start Scenario5 Testing");
		Screenshot.takeScreenShot(driver, "Before_Scenario5");
		
		driver.findElement(By.xpath("//*[@id='app-search-list']/div/input")).sendKeys(prop.getProperty("CourseRegistration"));
		test1.pass("S5_Entered text in SearchBox");
		Screenshot.takeScreenShot(driver, "S5_AfterTypeIn");
		Thread.sleep(5000); 
		driver.findElement(By.xpath("//*[@id=\"app-search-list\"]/div/div/div/div[2]/div/div[2]/a")).click(); //Enter Course Registration Page
		test1.pass("S5_Clicked 'Course Registration' Option");
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(10000);
		
		//Registration Page
		Screenshot.takeScreenShot(driver, "S5_CourseRegistration_Page");
		logger.log(Level.INFO, "Course Registration Page");
		test1.pass("S5_Entered Course Registration Page");
		Thread.sleep(2000);
		driver.findElement(By.id("planningLink")).click();
		test1.pass("S5_Clicked Planning Button");
		logger.log(Level.INFO, "Clicked Planning Button");
		Thread.sleep(5000);
		
		//Select Term Page
		Screenshot.takeScreenShot(driver, "S5_Befor_SelectTerm");
		driver.findElement(By.id("s2id_txt_term")).click();
		driver.findElement(By.xpath("//*[@id=\'s2id_autogen1_search\']")).sendKeys(prop.getProperty("SELECTTERM"));
		Thread.sleep(2000);
		test1.pass("S5_Selected Semester");
		logger.log(Level.INFO, "Selected Semester");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[contains(text(),'Spring 2022 Semester')]")).click();
		Screenshot.takeScreenShot(driver, "S5_After_SelectTerm");
		driver.findElement(By.id("term-go")).click();
		test1.pass("S5_Clicked Term-Go Button");
		
		//Planning Page
		Screenshot.takeScreenShot(driver, "S5_Plan_Page");
		test1.pass("S5_Entered Plan Page");
		Thread.sleep(1000);
		driver.findElement(By.id("createPlan")).click();
		test1.pass("S5_Clicked Create-New-Plan Button");
		logger.log(Level.INFO, "Clicked Create-New-Plan Button");
		Thread.sleep(1000);
		
		//Plan Ahead Page
		test1.pass("S5_Entered Plan-Ahead Page");
		Screenshot.takeScreenShot(driver, "S5_Before_Search");
		driver.findElement(By.xpath("//input[@id='txt_keywordlike']")).sendKeys(prop.getProperty("COURSECODE"));
		test1.pass("S5_Entered Course Number");
		logger.log(Level.INFO, "Entered Course Number");
		Thread.sleep(1000);
		driver.findElement(By.id("search-go")).click();
		test1.pass("S5_Clicked Search Button");
		logger.log(Level.INFO, "Clicked Search Button");
		Screenshot.takeScreenShot(driver, "S5_After_Search");
		
		//Search Result Page
		Screenshot.takeScreenShot(driver, "S5_Before_Add_Course");
		test1.pass("S5_Search Course Result Page");
		logger.log(Level.INFO, "Search Course Result Page");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr/td[6]/div/button[2]")).click();
		test1.pass("S5_Clicked 'Add Course' Button");
		logger.log(Level.INFO, "Clicked 'Add Course' Button");
		Thread.sleep(1000);
		Screenshot.takeScreenShot(driver, "S5_After_Add_Course");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"saveButton\"]")).click();
		test1.pass("S5_Clicked 'Save Plan' Button");
		logger.log(Level.INFO, "Clicked 'Save Plan' Button");
		Thread.sleep(1000);
		
		//Enter Plan Name
		Screenshot.takeScreenShot(driver, "S5_Before_Enter_PlanName");
		test1.pass("S5_'Enter Plan Name' Window Open");
		driver.findElement(By.id("txt_planDesc")).sendKeys(prop.getProperty("PLANNAME"));
		test1.pass("S5_Entered Plan Name");
		logger.log(Level.INFO, "Entered Plan Name");
		Screenshot.takeScreenShot(driver, "S5_After_Enter_PlanName");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[24]/div[3]/div/button[2]")).click();
		test1.pass("S5_Clicked Plan Name 'Save' Button");
		logger.log(Level.INFO, "Clicked Plan Name 'Save' Button");
		test1.pass("S5_Plan Saved");
		logger.log(Level.INFO, "Plan Saved");
		Thread.sleep(2000);
		Screenshot.takeScreenShot(driver, "S5_After_SavePlan");
		Thread.sleep(5000);
		Screenshot.takeScreenShot(driver, "After_Scenario5");
		test1.info("S5_Scenario5 Test Completed");
		driver.close();
		driver.switchTo().window(parent);
		
	}
	
}
