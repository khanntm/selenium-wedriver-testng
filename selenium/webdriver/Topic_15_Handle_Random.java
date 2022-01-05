package webdriver;

import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_15_Handle_Random {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Trang thai element
		explicitWait = new WebDriverWait(driver,30);
		jsExecutor = (JavascriptExecutor) driver;
		// De tim element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Random_Popup_In_DOM() {
		driver.get("https://www.kmplayer.com/home");
		
		sleepInSeconds(10);
		
		WebElement supportHomePopup = driver.findElement(By.cssSelector("div.pop-conts"));
		// Neu hien thi popup thi close, hoac la action len popup
		if (supportHomePopup.isDisplayed()) {
			System.out.println("Hien thi thì vào close đi");
			//Close it 
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
			sleepInSeconds(5);
		} else
		{
			System.out.println("Case 2 - Ko hien thi ko lam gi het");
		}
		
		//Expected home popup ko hien thi
		Assert.assertFalse(supportHomePopup.isDisplayed());
		
		// Click vao link PC 64 thi nó display popup Player img
		driver.findElement(By.xpath("//div[@id='container']//a[text()='PC 64X']")).click();
		sleepInSeconds(2);	
		
		//Check display popup
		Assert.assertTrue(driver.findElement(By.xpath("//img[@class='layer-popup']")).isDisplayed());
		
	}

	@Test
	public void TC_02_Random_Popup_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSeconds(10);
		
		//WebElement couponHomePopup = driver.findElement(By.cssSelector("div.popup-content"));
		// Neu hien thi popup thi close, hoac la action len popup
		List<WebElement> couponHomePopup = driver.findElements(By.cssSelector("div.popup-content"));
		System.out.println("Popup size " + couponHomePopup.size());
		
		//Nếu như Popup hien thi thì size element > 0
		if (couponHomePopup.size() > 0 ) {
			System.out.println("Case 1 - Hien thi thì vào close đi");
			//Close it 
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button.close")));
			sleepInSeconds(5);
		} else
		{
			System.out.println("Case 2 - Ko hien thi ko lam gi het");
		}
		
		driver.findElement(By.xpath("//h4[text()='Khóa học thiết kế hệ thống M&E - Tòa nhà']")).click();
		
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