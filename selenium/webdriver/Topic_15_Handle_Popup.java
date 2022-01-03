package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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


public class Topic_15_Handle_Popup {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Trang thai element
		explicitWait = new WebDriverWait(driver,30);
		
		// De tim element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Fix_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		// Nên define thành biến By, chỉ là lưu locator thôi. Ko find element
		By modalLoginPopupBy = By.xpath("//div[@id='modal-login-v1']");
		
		// Kiem tra ko hien thi
		Assert.assertFalse(driver.findElement(modalLoginPopupBy).isDisplayed());
		
		// Click dang nhập button
		//driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		driver.findElement(By.cssSelector("button.login_")).click();
		
		sleepInSeconds(3);
		
		//Kiem tra popup hien thi
		Assert.assertTrue(driver.findElement(modalLoginPopupBy).isDisplayed());
		
		// Input user name & password
		driver.findElement(By.id("account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		
		// Click Đăng Nhập button
		driver.findElement(By.cssSelector("button.btn-v1")).click();
		sleepInSeconds(5);
		
		// Kiem tra tai khoan ko tồn tại
		Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		//Click close button to close pop-up
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInSeconds(3);
		
		// Check pop-up ko display
		Assert.assertFalse(driver.findElement(modalLoginPopupBy).isDisplayed());
		
	}

	//@Test
	public void TC_02() {
		driver.get("https://bizbooks.vn/");
		
		By loginPopup = By.cssSelector("div#md-signin");
		By registerPopup = By.cssSelector("div#md-signup");
		
		// Check not display
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
		
		driver.findElement(By.xpath("//span[text()='ĐĂNG NHẬP']")).click();
		sleepInSeconds(2);
		
		
		driver.findElement(By.xpath("//div[@class='header__elements']//a[text()='Đăng nhập']")).click();
		sleepInSeconds(3);
		
		// Check not display
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
		
		// Input username & password
		driver.findElement(By.xpath("//div[@id='md-signin']//input[@name='email']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='md-signin']//input[@name='password']")).sendKeys("automationfc");
		
		//Click on Dang nhap button
		driver.findElement(By.cssSelector("button.js-btn-member-login")).click();
		
		//Check error message display 
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='text-danger']")).getText(), "Vui lòng nhập chính xác địa chỉ email");
		
		//Close popup by click ESC button
		driver.findElement(By.xpath("//div[@id='md-signin']//input[@name='email']")).sendKeys(Keys.ESCAPE);
		sleepInSeconds(3);
		
		// Check popup not display
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
		
	}

	@Test
	public void TC_03_Fix_Popup() {
		driver.get("https://jtexpress.vn/");
		By homePagePopup = By.cssSelector("div#homepage-popup");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(homePagePopup));
		Assert.assertTrue(driver.findElement(homePagePopup).isDisplayed());
		
		driver.findElement(By.cssSelector("div#homepage-popup button.close")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(homePagePopup));
		Assert.assertFalse(driver.findElement(homePagePopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#billcodes")).sendKeys("283248998329");
		driver.findElement(By.xpath("//form[@id='formTrack']//button[text()='Tra cứu vận đơn']")).click()	;
		sleepInSeconds(5);
		
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