package com.selenium.scenarios.testing;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.selenium.screenshot.Screenshot;

public class Scenario4 {
	
	public static void addItems(WebDriver driver, Properties prop, ExtentTest test1) throws InterruptedException {
		
		test1.info("S4_Start Scenario4 Testing");
		Screenshot.takeScreenShot(driver, "Before_Scenario4");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(prop.getProperty("BOOKSTORELINK"));	
		driver.manage().window().maximize();
		Actions action = new Actions(driver); Thread.sleep(3000);
		driver.navigate().refresh();
		
		//Login Process
		WebElement bookStoreLogin=driver.findElement(By.xpath("//li[@class='bned-fanatics-link-sign-in js-initially-hidden-content']//button[@id='bnedLoginButton']"));
		bookStoreLogin.click();
		test1.pass("S4_Clicked Login Button");
		Screenshot.takeScreenShot(driver, "S4_Before_Login");
		driver.switchTo().frame("loginHeaderIframe1");
		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		action.sendKeys(email, prop.getProperty("BOOKSTOREACCOUNT")).perform();
		Thread.sleep(2000);
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		action.sendKeys(password, prop.getProperty("BOOKSTOREPASSWORD")).perform();
		Screenshot.takeScreenShot(driver, "S4_Typein_Account");
		Thread.sleep(2000);
		driver.switchTo().defaultContent(); WebElement signinButton=driver.findElement(By.xpath("(//a[@id='submitLoginHeaderForm'])[1]"));
		action.click(signinButton).perform();
		test1.pass("S4_Login");
		Thread.sleep(3000);
		
		//After Login
		Screenshot.takeScreenShot(driver, "S4_After_Login");
		test1.pass("S4_Login Succeeded");
		WebElement searchBarElement=driver.findElement(By.xpath("//*[@id='bned_site_search']"));
		searchBarElement.sendKeys(prop.getProperty("PRODUCTNAME"));
		test1.pass("S4_Entered Text into SearchBox");
		Screenshot.takeScreenShot(driver, "S4_Entered_ProductName");
		searchBarElement.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		//Search Result Page, select product you want;
		Screenshot.takeScreenShot(driver, "S4_Search_Result");
		test1.pass("S4_Entered Search_Result_Page");
		WebElement firstItem=driver.findElement(By.xpath("/html/body/main/div[3]/div[4]/div/div[2]/div[1]/div/div/ul/div[2]/a/img"));
		String itemName = firstItem.getText();
		System.out.println(itemName); try{
			WebElement firstItem1=driver.findElement(By.xpath("/html/body/main/div[3]/div[4]/div/div[2]/div[1]/div/div/ul/div[2]/a/img"));
			System.out.println(firstItem1.getText());
			action.click(firstItem1).perform();
		}catch(Exception e) {
			System.out.println("Issue here");
		}
		test1.pass("S4_Selected Target Product");
		Thread.sleep(1000);
		
		//Add product to cart
		Screenshot.takeScreenShot(driver, "S4_Befor_AddtoCart");
		test1.pass("S4_Entered Cart Page");
		try{
			WebElement addTocart=driver.findElement(By.xpath("//*[@id=\"addToCartForm\"]"));
			addTocart.click();
		}catch(Exception e) {
			WebElement addTocart=driver.findElement(By.xpath("//*[@id=\"addToCartForm\"]"));
			addTocart.click();
		}
		Screenshot.takeScreenShot(driver, "S4_After_AddtoCart");
		test1.pass("S4_Product Add to Cart");
		Thread.sleep(5000);
		
		//View Cart
		WebElement viewCart = driver.findElement(By.cssSelector("path.svgIconFill"));
		viewCart.click();
		Screenshot.takeScreenShot(driver, "S4_View_Cart");
		test1.pass("S4_View Cart");
		WebElement cartItemno1 = driver.findElement(By.xpath("(//div[@class='bned-product-name']/a)[last()]"));
		
		Screenshot.takeScreenShot(driver, "After_Scenario4");
		test1.info("S4_Scenario4 Test Completed");
		driver.close();
		driver.quit();
	}

}
