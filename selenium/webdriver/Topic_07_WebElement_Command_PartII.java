package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_07_WebElement_Command_PartII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_IsDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Kiem tra txtbox Email có hien thi hay ko
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		boolean Status = emailTextbox.isDisplayed();
		
		if (Status) {
			System.out.println("Eamil textbox is displayed");
			emailTextbox.sendKeys("Please input email");
			
		} else {
			System.out.println("Email textbox is not displayed");

		}
		
		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		if (ageUnder18Radio.isDisplayed()) {
			System.out.println("Age under 18 radio is displayed");
			ageUnder18Radio.click();
			
		} else {
			System.out.println("Age under 18 is not displayed");
		}
		
		WebElement eduTextbox = driver.findElement(By.id("edu"));
		if (eduTextbox.isDisplayed()) {
			System.out.println("Edu textbox is displayed");
			eduTextbox.sendKeys("My edu textbox");
			
		} else {
			System.out.println("Edu textbox is not displayed");

		}
		
		WebElement nameh5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (nameh5.isDisplayed()) {
			System.out.println("name h5 is displayed");
			
		} else {
			System.out.println("name h5 is NOT displayed");

		}
		
	}

	@Test
	public void TC_02_isEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement emailTextboxTC02 = driver.findElement(By.xpath("//input[@id='mail']"));
		boolean emailTextboxTC02_isEnable = emailTextboxTC02.isEnabled();
		if (emailTextboxTC02_isEnable) {
			System.out.println("Email textbox is enabled");
			emailTextboxTC02.sendKeys("Email is enabled");
			
		} else {
			System.out.println("Email textbox is disabled");

		}
		
		WebElement ageUnder18TC02 = driver.findElement(By.id("under_18"));
		boolean ageUnder18TC02_isEnable = ageUnder18TC02.isEnabled();
		if (ageUnder18TC02_isEnable) {
			System.out.println("Radio under 18 is enabled");
			ageUnder18TC02.sendKeys("Age under 18 is enabled");
			
		} else {
			System.out.println("Age under is disabled");

		}

		WebElement eduTextAreaTC02 = driver.findElement(By.id("edu"));
		boolean eduTextAreaTC02_isEnable = eduTextAreaTC02.isEnabled();
		if (eduTextAreaTC02_isEnable) {
			System.out.println("Text Area edu is enabled");
			eduTextAreaTC02.sendKeys("Text Area edu is enabled");
			
		} else {
			System.out.println("Text Aea edu is disabled");

		}

		WebElement jobRole1TC02 = driver.findElement(By.id("job1"));
		boolean jobRole1TC02_isEnable = jobRole1TC02.isEnabled();
		if (jobRole1TC02_isEnable) {
			System.out.println("Job role 1 is enabled");
			jobRole1TC02.click();
			
		} else {
			System.out.println("Job role 1 is disabled");

		}

		WebElement interestCheckboxTC02 = driver.findElement(By.xpath("//label[@for='development']"));
		boolean interestCheckboxTC02_isEnable = interestCheckboxTC02.isEnabled();
		if (interestCheckboxTC02_isEnable) {
			System.out.println("Interest checkox is enabled");
			interestCheckboxTC02.click();
			
		} else {
			System.out.println("interestCheckbox is disabled");

		}
		
		WebElement slider01TC02 = driver.findElement(By.id("slider-1"));
		boolean slider01TC02_isEnable = slider01TC02.isEnabled();
		if (slider01TC02_isEnable) {
			System.out.println("Slider 01 is enabled");
			
		} else {
			System.out.println("Slider 01 is disabled");

		}
		
		WebElement PasswordTC02 = driver.findElement(By.id("password"));
		boolean PasswordTC02_isDisable = PasswordTC02.isEnabled();
		if (PasswordTC02_isDisable) {
			System.out.println("Password is enaled");
			
		} else {
			System.out.println("Password is disaled");

		}
	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement ageUnder18TC03 = driver.findElement(By.id("under_18"));
		ageUnder18TC03.click();
		boolean ageUnder18TC03_isSelected = ageUnder18TC03.isSelected();
		if (ageUnder18TC03_isSelected) {
			System.out.println("Radio under 18 is selected");
			
		} else {
			System.out.println("Age under is de-selected");

		}

		WebElement javaCheckbox = driver.findElement(By.xpath("//label[@for='java']"));
		//javaCheckbox.click();
		if (javaCheckbox.isSelected()) {
			System.out.println("Java checkbox is selected");
			//javaCheckbox.click();
		} else {
			System.out.println("Java check is de-selected");
		}
	
	}
	
	@Test
		public void TC_04_MailChimp() {
			driver.get("https://login.mailchimp.com/signup/");
			
			driver.findElement(By.id("email")).sendKeys("khan@gmail.com");
			
			
			WebElement username = driver.findElement(By.id("new_username"));
			username.sendKeys("khanntm");
			
			WebElement password = driver.findElement(By.id("new_password"));
			WebElement signUpBtn = driver.findElement(By.id("create-account"));
			
			// 1 - Lower case:
			password.sendKeys("auto");
			SleepInSecond(3);
			
			// Expected display (assertTrue)
			Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
			
			//Button Sign Up is disabled
			Assert.assertFalse(signUpBtn.isEnabled());
			
			
			// 2 - Upper case: 
			password.clear();
			password.sendKeys("AUTO");
			SleepInSecond(3);
			Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
			Assert.assertFalse(signUpBtn.isEnabled());
			
			// 3 - One Number
			password.clear();
			password.sendKeys("123");
			SleepInSecond(3);
			Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
			Assert.assertFalse(signUpBtn.isEnabled());
			
			// 4 - One special characters
			password.clear();
			password.sendKeys("$%%$");
			SleepInSecond(3);
			Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
			Assert.assertFalse(signUpBtn.isEnabled());
			
			// 5 - Minimum 8 character 
			password.clear();
			password.sendKeys("12345678");
			SleepInSecond(3);
			Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
			Assert.assertFalse(signUpBtn.isEnabled());
			
			// 6 - Valid all fields & check the checkbox
			password.clear();
			password.sendKeys("1111aAB1@Test");
			SleepInSecond(3);
			Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")).isDisplayed());
			Assert.assertTrue(signUpBtn.isEnabled());			
				
		}

	

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	public void SleepInSecond(long second)
	{
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}