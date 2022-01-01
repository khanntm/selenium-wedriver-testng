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


public class Topic_06_Web_Browser_Command {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01() {
		//De thao tac vs trinh duyet thi thong qua bien driver
	
		//Ham nao ma de tuong tac la 100% void ko can tra ve 
		//driver.get("https://vi-vn.facebook.com");
		// Action: refresh/ ack/ forwark/maximize
		// Ham nao lay du lieu ra de verify (dung/sai/= voi mong muon hay ko)  100% phai tra ve du lieu
		//Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		//Get:URL/ Title/ Page Source/ Size/ Location
		// De thao tac voi cac element thi thong qua bien element
		//WebElement emailTextbox = driver.findElement(By.id("email"));
	
		//get currentURL tra ve url page hien tai
		//ko quit o giua chung moi TC 
		//driver.quit();
		//Tra ve windowID cua ta* hien tai
		//driver.getWindowHandle();
		
		//Tra ve window ID cua tat ca cac ta* hien tai
		//driver.getWindowHandles();
		//Interface
		//driver.manage().addCookie(cookie); // de lay cookie de dang nhap cho lan sau nhanh hơn
		
		// Chờ cho element duoc tìm thấy trong vong xxx giay 15s
		// We*driverWait 
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//Full rowsers khop voi man hinh khop voi menu taskar
		//driver.manage().window().;
		//Set kich thuot cua rowser la ao nhieu
		//driver.manage().window().setSize(new Dimension(1366,768));
		
		
		// De thao tac vs cac element thi thong qua bien element
			       
	}

	@Test
	public void TC_02() {
		
	}

	@Test
	public void TC_03() {

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