package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;


public class Topic_09_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	String firstName, lastName, email, passwordUser, day, month, year; 
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Ham implicit nay dung de cho cho ham findElement(s) di tim element, doi het 30s nó mới đánh failed timeout 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstName = "Khan";
		lastName = "Nguyen";
		email = "Khan" + getRandomNumber() + "@gmail.com";
		passwordUser = "C@tus123";
		day = "10";
		month = "June";
		year = "1954";
		
	}

	//@Test
	public void TC_01_Rodedotcom() {
		driver.get("https://www.rode.com/wheretobuy");	     
		//Chi khoi tao khi element dc xuất hiện tại thời điểm này
		select = new Select(driver.findElement(By.id("where_country")));
		
		// Chon 1 item nào đó
		select.selectByVisibleText("Vietnam");
		
		// Verify gia tri chon duoc đúng hay ko?
		Assert.assertEquals("Vietnam", select.getFirstSelectedOption().getText());
		
		//Kiem tra dropdown có phai la multiple hay ko? 
		Assert.assertFalse(select.isMultiple());
		
		// Kiem tra xem 1 dropdown co bao nhieu item
		Assert.assertEquals(233, select.getOptions().size());
		
		driver.findElement(By.id("search_loc_submit")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(), "31");
		
		List<WebElement> distributorName = driver.findElements(By.cssSelector("div.result_item div.store_name"));
		for (WebElement distributor : distributorName ) {
			System.out.println(distributor.getText());
		}
	}

	@Test
	public void TC_02_nopcommercedotcom() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
		dayDropdown.selectByVisibleText(day);
		
		Select monthDropdown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		monthDropdown.selectByVisibleText(month);
		
		Select yearDropdown = new Select(driver.findElement(By.name("DateOfBirthYear")));
		yearDropdown.selectByVisibleText(year);
		
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(passwordUser);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(passwordUser);
		driver.findElement(By.id("register-button")).click();		
		
		//Verify text after completyeare 
	   
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result' and text()='Your registration completed']")).isDisplayed());
		driver.findElement(By.className("ico-account")).click();
		
		// Dki thanh cong 
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		
		// Gan lai du lieu moi
		dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(dayDropdown.getFirstSelectedOption().getText(), day);
		Assert.assertEquals(dayDropdown.getOptions().size(), 32);
		
		monthDropdown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(monthDropdown.getFirstSelectedOption().getText(), month);
		Assert.assertEquals(monthDropdown.getOptions().size(), 13);
		
		yearDropdown = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(yearDropdown.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(yearDropdown.getOptions().size(), 112);
		
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		
		
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
	
	public int getRandomNumber()
	{
		// 1 ham tra ve 1 kieu du lieu cu the thì se return de lay du lieu ra
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}
}