package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_12_Custom_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor; 
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		By winterRadio = By.xpath("//input[@value='Winter']");
		
		//Ko dùng ham click cua Buil-in selenium dc.
		//Dung jsExecutor de xử lý
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(winterRadio));
		sleepInSeconds(2);
		
		//The input có the verify duoc by isSelect()
		Assert.assertTrue(driver.findElement(winterRadio).isSelected());	       
	}

	//@Test
	public void TC_02_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		By firstCheckbox = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::span/input");
		By IndeterminateCheckbox = By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::span/input");
		
		// Check both 2 checkbox
		//jsExecutor.executeScript("arguments[0].click();", driver.findElement(firstCheckbox));
		checkToCheckbox(firstCheckbox);
		sleepInSeconds(2);
		Assert.assertTrue(driver.findElement(firstCheckbox).isSelected());
		
		//jsExecutor.executeScript("arguments[0].click();", driver.findElement(IndeterminateCheckbox));
		checkToCheckbox(IndeterminateCheckbox);
		sleepInSeconds(2);
		Assert.assertTrue(driver.findElement(IndeterminateCheckbox).isSelected());
		
		//Uncheck both 2 checkbox
		uncheckToCheckbox(firstCheckbox);
		sleepInSeconds(2);
		Assert.assertFalse(driver.findElement(firstCheckbox).isSelected());
		
		//jsExecutor.executeScript("arguments[0].click();", driver.findElement(IndeterminateCheckbox));
		uncheckToCheckbox(IndeterminateCheckbox);
		sleepInSeconds(2);
		Assert.assertFalse(driver.findElement(IndeterminateCheckbox).isSelected());
		
	}

	@Test
	public void TC_03_Custom_Checkbox_Radio() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		// Truoc khi click
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hồ Chí Minh' and @aria-checked='false']")).isDisplayed());
		driver.findElement(By.xpath("//div[@aria-label='Hồ Chí Minh']")).click();
		sleepInMiliseconds(500);
		
		// Check sau khi click
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hồ Chí Minh' and @aria-checked='true']")).isDisplayed());
		
		// Click on checkbox with 'Quang' 
		List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@role='checkbox' and @aria-checked='false']"));
		for (WebElement checkbox : checkboxes) {
			// Check not display before click
			Assert.assertTrue(checkbox.isDisplayed());
			System.out.println("Checkbox chua dc chọn " + checkbox.getAttribute("aria-label"));
			//Đi qua từng checkbox & click it
			checkbox.click();
			sleepInSeconds(1);
			//Verify bang cach getAttribute
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
		}
		
		//Verify bang cach check display
	    //List<WebElement> checkboxesAfter =	driver.findElements(By.xpath("//div[@role='checkbox' and @aria-checked='true']"));
	   // for (WebElement checkbox : checkboxesAfter) {
	   // 		Assert.assertTrue(checkbox.isDisplayed());
			
	//	}
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	//Viet ham checkToCheckbox
	
	public void checkToCheckbox(By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", element);
			sleepInSeconds(2);	
		}
	}
	
	public void uncheckToCheckbox(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", element);
			sleepInSeconds(2);	
		}
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