package com.selenium.scenarios.testing;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.selenium.screenshot.Screenshot;

public class Scenario1 {
	
	public static void addFavorites(WebDriver driver, Properties prop, ExtentTest test1) throws InterruptedException {
		
		test1.info("S1_Start Scenario1 Testing");
		Thread.sleep(500);
		Screenshot.takeScreenShot(driver, "S1_BeforeScenario1");
		String myTranscript = "My Transcript";
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='app-search-list']/div/input")).sendKeys(myTranscript);  //Input String
		Thread.sleep(1000);
		Screenshot.takeScreenShot(driver, "S1_AfterTypeIn");
		test1.pass("Entered Text into SearchBox");
		driver.findElement(By.xpath("//i[@data-gtm-event-action='" + myTranscript + "']")).click();   //Add to favorite list
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='app-search-list']/div/a")).click();                     //Close button
		Thread.sleep(1000);
		Screenshot.takeScreenShot(driver, "AfterScenario1");
		test1.info("S1_Scenario1 Test Completed");
		Thread.sleep(5000);
		
	}

}
