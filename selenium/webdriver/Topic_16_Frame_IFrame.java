package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_Frame_IFrame {
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
	public void TC_01IFrame() {
		driver.get("https://kyna.vn/");
		
		//Switch vào iframe chứa element này trước
		WebElement iframe = driver.findElement(By.xpath("//div[@class='face-content']//iframe"));
		driver.switchTo().frame(iframe);
		
		sleepInSeconds(2);
		
		String KynaFacebookLikes = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println("So luot like page: " + KynaFacebookLikes);
		Assert.assertEquals(KynaFacebookLikes, "167K likes");
			
		// Switch về trang parent
		driver.switchTo().defaultContent();
		sleepInSeconds(2);
		
		// Swith to chat iframe
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleepInSeconds(2);
		
		// Input info into chat form
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Khan Nguyen"); 
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("092342823"); 
		
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Test lalala");
		
		//Click submit 
		//driver.findElement(By.cssSelector("input.submit")).click();
		
		// Swith to parrent page
		driver.switchTo().defaultContent();
		sleepInSeconds(3);
		
		// Search a text
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSeconds(2);
		
		//Verify kq tìm kiem get all course name
		String keyWord = "Excel";
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content h4"));
		for (WebElement eachCourse : courseName) {
			System.out.println("Khoa hoc excel: "+ eachCourse.getText());
			Assert.assertTrue(eachCourse.getText().toLowerCase().contains(keyWord.toLowerCase()));
			
		}
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		//Switch to frame
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys("Automation");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSeconds(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
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