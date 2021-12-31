package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_13_Alert {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait; 
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");	 
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		//Wait truoc roi moi switch qua alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		sleepInSeconds(10);
		alert.accept();
		sleepInSeconds(10);
		//Verify the messag success
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	}

	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");	 
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		//Wait truoc roi moi switch qua alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		sleepInSeconds(10);
		
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	}

	//@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");	 
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		//Wait truoc roi moi switch qua alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		sleepInSeconds(10);
		
		String text = "Automation Alert";
		
		alert.sendKeys(text);
		sleepInSeconds(2);
		
		alert.accept();
		sleepInSeconds(2);
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + text);
	}
	
	//@Test
	public void TC_04_Authentication_Alert() {
		// Truyen tham so vao URL voi cú pháp username:pass@url
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}
	
	@Test
		public void TC_05_Authentication_Alert_Change_Page() {
			
			String username = "admin";
			String password = "admin";
			driver.get("http://the-internet.herokuapp.com/");
			
			WebElement basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']"));
			driver.get(getAuthenticationUrl(basicAuthenLink.getAttribute("href"), username, password));
			
			Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
			
		}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	public void sleepInSeconds(long second)
	{
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getAuthenticationUrl(String url, String username, String password) {
		String[] urlValues = url.split("//");
		url = urlValues[0] + "//" + username + ":" + password + "@" + urlValues[1];
		return url;
	}
	
	
	public void sleepInMiliseconds(long milisecond)
	{
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}