package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//mở trang đăng kí lên
		driver.get("https://vi-vn.facebook.com/r.php");
		
		//biến cục bộ 
		//String name = "Firefox";
	}

	@Test
	public void TC_01_ID() {
		/* <input class="yReWDs" type="text" placeholder="Email/Số điện thoại/Tên đăng nhập" autocomplete="on" name="loginKey" maxlength="128" value="khanntm@gmail.com"> */	
		//find by id
		driver.findElement(By.id("password_step_input")).sendKeys("123456");
		SleepInSecond(3);
	}

	@Test
	public void TC_02_Class() {
		driver.findElement(By.className("registration_redesign"));
		SleepInSecond(3);	
		
	}

	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("reg_passwd__")).sendKeys("16276");
		SleepInSecond(3);
	}
	@Test
	public void TC_04_LinkText() {
		//click xong chuyen qua man hinh khac
		driver.findElement(By.linkText("Bạn đã có tài khoản?")).click();
		SleepInSecond(3);
	}
	@Test
	public void TC_05_PartialLinkText() {
		driver.findElement(By.partialLinkText("tài khoản?")).click();
		SleepInSecond(3);
	}
	@Test
	public void TC_06_TagName() {
		int InputSize = driver.findElements(By.tagName("input")).size();
		System.out.println("Input Size = " + InputSize);
		SleepInSecond(3);
	}
	@Test
	public void TC_07_Css() {
		//CSS cú pháp: tagname[attribute='value']
		driver.findElement(By.cssSelector("input[id='identify_email']")).sendKeys("automation@gmail.com");
		SleepInSecond(3); 
	}
	
	@Test
	public void TC_08_Xpath	() {
		//Xpath cú pháp: //tagname[@attribute='value']
		driver.findElement(By.xpath("//button[@name='did_submit']")).click();
		SleepInSecond(3); 
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
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