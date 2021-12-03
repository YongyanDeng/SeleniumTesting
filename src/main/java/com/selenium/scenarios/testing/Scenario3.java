package com.selenium.scenarios.testing;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.selenium.screenshot.Screenshot;

public class Scenario3 {

	public static void runScenario(WebDriver driver, Properties prop, ExtentTest test1) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		String parent = driver.getWindowHandle();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(10000);
		test1.info("S3_Start Scenario3 Testing");
		Screenshot.takeScreenShot(driver, "Before_Scenario3");
		driver.findElement(By.xpath("//*[@id='app-search-list']/div/input")).sendKeys(prop.getProperty("CourseRegistration"));
		test1.pass("S3_Entered text in SearchBox");
		Screenshot.takeScreenShot(driver, "S3_AfterTypeIn");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id=\"app-search-list\"]/div/div/div/div[2]/div/div[2]/p")).click();
		test1.pass("S3_Clicked 'Course Registration' Option");
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(10000);
		
		//Registration Page
		Screenshot.takeScreenShot(driver, "S3_CourseRegistration_Page");
		test1.pass("S3_Course Registration Page");
		WebElement browseClasses = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("classSearchLink"))));
		long endRegistrationTimer = System.currentTimeMillis();
		browseClasses.click();
		test1.pass("S3_Clicked 'Browse Classes' Button");
		
		//Select Term Page
		Screenshot.takeScreenShot(driver, "S3_Befor_SelectTerm");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("s2id_txt_term")))).click();
		WebElement search = driver.findElement(By.id("s2id_autogen1_search"));
		search.sendKeys(prop.getProperty("SELECTTERM"));
		test1.pass("S3_Selected Semester");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[contains(text(),'Spring 2022 Semester')]"))))
		.click();
		Screenshot.takeScreenShot(driver, "S3_After_SelectTerm");
		driver.findElement(By.id("term-go")).click();
		test1.pass("S3_Clicked Term-Go Button");
		
		//Course Catalog Page
		test1.pass("S3_Entered Browse-Classes Page");
		Screenshot.takeScreenShot(driver, "S3_Before_Search");
//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("s2id_txt_subject")))).click();
//		WebElement subject = driver.findElement(By.xpath("//*[@id=\"s2id_autogen1\"]"));
//		subject.sendKeys("Information Systems");
//		driver.findElement(By.id("INFO")).click();
		driver.findElement(By.id("search-go")).click();
		test1.pass("S3_Clicked Search Button");
		long startTimeCourses = System.currentTimeMillis();
		Screenshot.takeScreenShot(driver, "S3_Full_Course");
		test1.pass("S3_Entered Course List Page");
		Select dropDown = new Select(wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("page-size-select")))));
		dropDown.selectByValue("50");
		long endTimeCourses = System.currentTimeMillis();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("page-size-select"))));
		test1.pass("S3_Modified Size of List");
		Screenshot.takeScreenShot(driver, "S3_After_Modified_Size");
		Thread.sleep(10000);
		test1.info("S3_Scenario3 Test Completed");
		Screenshot.takeScreenShot(driver, "After_Scenario3");
		driver.close();
		driver.switchTo().window(parent);
	}
}
