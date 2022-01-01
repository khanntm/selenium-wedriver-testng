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


public class Topic_07_WebElement_Command {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01() {
		WebElement element = driver.findElement(By.id("email"));
		
		element.sendKeys("1234556@118.3039");
		SleepInSecond(3);
		// Xoa du lieu o field edit*le 
		element.clear();
		element.sendKeys("khan@gmail.com");
		SleepInSecond(3);
		
		//element.getAttribute("placeholder");
		// So sánh mong muon 2 du lieu ang nhau 
		Assert.assertEquals(element.getAttribute("placeholder"),"Search entire store here...");
		
		//GUI: font/size/color lay ma hexa
		element.getCssValue("background");
		
		// get fontsize
		element.getCssValue("font-size");
		
		// get location lay toa do en ngoai 
		element.getLocation();
		
		// get size of 1 element
		element.getSize();
		
		// Neu ko iet chac tagname la gi? Trong TH element dynamic thi tagname cua element nay la dau vao cua element kia
		element.getTagName(); // ra 1 tagname nao đó 
		//driver.findElement(By.xpath("/" + element.getTagName() + "[@id="]"));
		
		// Chi lay ra text hien thi visile text of elemnt
		element.getText();
		
		// kiem tra element co hien thi hay ko
		element.isDisplayed();
		// false ko hien thi = ko nhin thay & ko thao tac dc
		//true dang hien thi & thao tac dc
		// dùng dc cho tat ca cac element
		
		// Ktra element co enale hay ko? 
		// Test phân quyền của user 
		element.isEnabled();
		
		// ktra co dc chon hay ko?
		element.isSelected();
		// apply cho checkbox or radio
		
		element.sendKeys("tets");
		
		//Chi lam dc voi form e.g: login. Nó giống lệnh Enter trên bàn phím. Nhap user/pass & submit. Nằm trong FORM mới dc nha
		element.submit();
		
		
		
		
		
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