package webdriver;
import java.util.Date;
//import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_23_17_Wait_PartVI_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
	}

	//@Test
	public void TC_01_Found() {
		//Happy case: ko cần phải chờ hết timeout
		driver.get("https://vi-vn.facebook.com");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		explicitWait = new WebDriverWait(driver, 5);
		
		System.out.println("1.1 - Start implicit wait: " + getDateTimeNow());
		driver.findElement(By.id("email"));
		System.out.println("1.2 - End implicit wait: " + getDateTimeNow());
		
		System.out.println("2.1 - Start explicit wait: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		System.out.println("2.2 - End explicit wait: " + getDateTimeNow());
		
	}

	//@Test
	public void TC_02_Not_Found_Only_Implicit() {
		//Unhappy path case: Chờ hết timout của implicit rồi mới end e.g: 10s - through exeception no_such_element.html
		driver.get("https://vi-vn.facebook.com");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		System.out.println("1.1 - Start implicit wait: " + getDateTimeNow());
		
		try {
			driver.findElement(By.id("selenium"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("1.2 - End implicit wait: " + getDateTimeNow());
		
	}

	//@Test
	public void TC_03_Not_Found_Implicit_Explicit() {
		
		//unHappy case - Implicit 5s - 7s for explicit
				driver.get("https://vi-vn.facebook.com");
				
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				
				explicitWait = new WebDriverWait(driver, 7);
				
				System.out.println("1.1 - Start implicit wait: " + getDateTimeNow());
				try {
					driver.findElement(By.id("selenium"));
				} catch (Exception e) {
					System.out.println("--------Exception of implicit:-------------- ");
					e.printStackTrace();
					System.out.println("--------Exception of implicit:-------------- ");
				}
				System.out.println("1.2 - End implicit wait: " + getDateTimeNow());
				
				System.out.println("2.1 - Start explicit wait: " + getDateTimeNow());
				try {
					explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selenium")));
				} catch (Exception e) {
					System.out.println("--------Exception of explicit:-------------- ");
					e.printStackTrace();
					System.out.println("--------Exception of explicit:-------------- ");
				}
				System.out.println("2.2 - End explicit wait: " + getDateTimeNow());
	}

	@Test
	public void TC_04_Not_Found_Only_Explicit_By() {
		driver.get("https://vi-vn.facebook.com");	
		//implicit ko set thi coi nhu = 0
		explicitWait = new WebDriverWait(driver, 5);
		
		System.out.println("2.1 - Start explicit wait: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selenium")));
		} catch (Exception e) {
			System.out.println("--------Exception of explicit:-------------- ");
			e.printStackTrace();
			System.out.println("--------Exception of explicit:-------------- ");
		}
		System.out.println("2.2 - End explicit wait: " + getDateTimeNow());
	}
	

	//@Test
	public void TC_05_Not_Found_Only_Explicit_Element() {
		// KO NEN DUNG visibilityOf
		driver.get("https://vi-vn.facebook.com");	
		explicitWait = new WebDriverWait(driver, 2);
		
		System.out.println("2.1 - Start explicit wait: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("selenium"))));
		} catch (Exception e) {
			System.out.println("--------Exception of explicit:----------");
			e.printStackTrace();
			System.out.println("--------Exception of explicit:----------");
		}
		System.out.println("2.2 - End explicit wait: " + getDateTimeNow());
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getDateTimeNow(){
		Date date = new Date();
		return date.toString();
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