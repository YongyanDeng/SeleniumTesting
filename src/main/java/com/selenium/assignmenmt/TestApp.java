package com.selenium.assignmenmt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.selenium.scenarios.testing.Scenario1;
import com.selenium.scenarios.testing.Scenario3;
import com.selenium.scenarios.testing.Scenario4;
import com.selenium.scenarios.testing.Scenario5;

public class TestApp {
	
	public static void main(String[] args) throws Exception {
		
		try(InputStream input = new FileInputStream("./config.properties")) {
			
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReports.html");
			ExtentReports extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			
			ExtentTest test1 = extent.createTest("Group2 - Selenium Test Report", "This is a test to MyNeu.com");
			test1.log(Status.INFO, "Starting Test Case 1, 3, 4, 5");
			
			Properties prop = new Properties();
			prop.load(input);
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\dengy\\eclipse-workspace\\WebdriverTest\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			performLogin(driver, prop);
			test1.pass("Login MyNeu");
			
			Scenario1.addFavorites(driver, prop, test1);
			Scenario3.runScenario(driver, prop, test1);
			Scenario4.addItems(driver, prop, test1);
			
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			performLogin(driver, prop);
			Scenario5.createPlan(driver, prop, test1);
			
			driver.close();
			driver.quit();
			test1.pass("Closed the Broswer");
			test1.info("Test Completed");
			extent.flush();     //Create ExtentReports
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void performLogin(WebDriver driver, Properties prop) throws InterruptedException {

		driver.get(prop.getProperty("LINK"));
		driver.findElement(By.className("btn-primary")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("username")).sendKeys(prop.getProperty("USERNAME"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("PASSWORD"));
		driver.findElement(By.className("form-button")).click();
		driver.switchTo().frame("duo_iframe");
		Thread.sleep(8000);    //Wait for Duo procedure
		
	}

}
