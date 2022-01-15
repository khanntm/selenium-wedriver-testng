package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_22_17_Wait_PartII_Find_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//driver.get("https://vi-vn.facebook.com");
		driver.get("http://live.techpanda.org/");
		
	}

	@Test
	public void TC_01_Find_Element() {
		
		WebElement element = null;
		// Ko có element nào được tìm thấy
		// Nếu ko tìm thấy thì failed test case
		//driver.findElement(By.id("selenium"));
		
		//Có 1 element được tìm thấy, passed
		//driver.findElement(By.id("email"));
		
		//Có nhiều hơn 1 element được tìm thấy
		element = driver.findElement(By.xpath("//a[text()='My Account']"));
		//element.click();
		System.out.println("Get text of element: " + element.getText());
	}

	@Test
	public void TC_02_Find_Elements() {
		List<WebElement> listSelenium = null;
		// Ko có element nào được tìm thấy, trả về 1 danh sách các element
		//listSelenium = driver.findElements(By.id("selenium"));
		
		//Check danh sách các phần tử trong danh sách dùng hàm Size()
		//System.out.println("Danh sách các element tìm thấy: " + listSelenium.size());
		//for (WebElement link : listSelenium) {
		//	System.out.println("Get text of element" + link.getText());
		//	System.out.println("Get attriute of element" + link.getAttribute("href"));
		//}
		
		//Có 1 element được tìm thấy
		//listSelenium = driver.findElements(By.id("email"));
		//System.out.println("Danh sách các element tìm thấy: " + listSelenium.size());
		//listSelenium.get(0).sendKeys("kahnns@gmail.com");
		
		//Show exception IndexOutOfBoundsException
		//listSelenium.get(1).sendKeys("kahnns@gmail.com");
				
		//Có nhiều hơn 1 element được tìm thấy, thì nó chỉ tương tác với element đầu tiên
		listSelenium = driver.findElements(By.xpath("//a[text()='My Account']"));
		for (WebElement link : listSelenium) {
			System.out.println("Get text of element: " + link.getText()); // Hàm getText() vẫn passed nếu như ko click dc cho findElements
			System.out.println("Get attriute of element: " + link.getAttribute("href"));
			}
	}

	@Test
	public void TC_03() {

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