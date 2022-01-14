package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_21_17_Wait_PartI {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		explicitWait = new WebDriverWait(driver, 20);
		
	}

	@Test
	public void TC_01_Visible() {
		driver.get("https://vi-vn.facebook.com/");
		
		//sleepInSeconds(5);
		// Wait regiter display & click
		//driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).click();
		
		// Wait cho email field display roi moi sendkey
		//driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("khann@gmail.com");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("khann@gmail.com");
		
		
		// Wait cho confirm email element được visible hoac display
		WebElement confirmEmailTextbox = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	
		Dimension confirmEmailSize = confirmEmailTextbox.getSize();
		System.out.println("Confirm email height = " + confirmEmailSize.getHeight());
		System.out.println("Confirm email width = " + confirmEmailSize.getWidth());
	}

	@Test
	public void TC_02_Invisible_In_DOM() {
		//Element ko hiển thị trên UI & có trong DOM
		driver.get("https://vi-vn.facebook.com/");
		
		//Click vao button Dang ki tai khoản
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		sleepInSeconds(3);
		
		//Verify confirm email ko có tren UI but có trong DOM. Check wait ko hien thị
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
	}
	
	@Test
	public void TC_02_Invisible_NOT_In_DOM() {
		
		driver.get("https://vi-vn.facebook.com/");
		//Wait có element trong DOM hay ko
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		sleepInSeconds(3);
		
		//Close popup
	}

	@Test
	public void TC_03_Presence_In_UI_Pass() {
		
		driver.get("https://vi-vn.facebook.com/");
		//Pass precence thì phải có trong DOM & In UI
		//Wait until element  user
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		
		//Click on email textbox
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).click();
		
		//Check confirm email presence & có tren UI 
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
	}
	
	@Test
	public void TC_03_Presence_NOT_In_UI_Pass() {
		driver.get("https://vi-vn.facebook.com/");
		//Pass precence thì phải có trong DOM & In UI
		//Wait until element  user
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		sleepInSeconds(3);
		//Click on email textbox
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).click();
		
		//Check confirm email presence & ko có tren UI 
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
	}
	
	
	@Test
	public void TC_04_Staleness() {
		//Tìm element tại thời điểm nó đang xuất hiện - sau thời điểm này thao tác 1 vài step nào đó làm cho element ko còn xuất hiện trong DOM nữa
		// Wait cho 1 element staleness -> Đúng
		
		driver.get("https://vi-vn.facebook.com/");	
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("khann@gmail.com");
	
		WebElement confirmEmailTextbox = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	
		Dimension confirmEmailSize = confirmEmailTextbox.getSize();
		System.out.println("Confirm email height = " + confirmEmailSize.getHeight());
		System.out.println("Confirm email width = " + confirmEmailSize.getWidth());
		
		//Check Staleness of confirm Email after close pop-up
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/parent::div/img")).click();
		
		//Check Staleness confirm Email
		explicitWait.until(ExpectedConditions.stalenessOf(confirmEmailTextbox));
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
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