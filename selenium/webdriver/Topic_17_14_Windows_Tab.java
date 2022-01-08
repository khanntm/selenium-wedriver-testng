package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_17_14_Windows_Tab {
	WebDriver driver;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Windows_ID() {
		//Parent window
		//Only apply for 2 window or tab
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSeconds(3);
		
		//Get current URL 
		String parentPageID = driver.getWindowHandle();
		System.out.println("CurrentURL: " + parentPageID + " " + driver.getCurrentUrl());
		
		//Switch qua Google page
		swithToWindowByID(parentPageID);
		// In Google tab: check title của current page
		Assert.assertEquals(driver.getTitle(),"Google");
		
		//Get ID of google page
		String googlePageID = driver.getWindowHandle();
		
		//Witch qua Parent page
		swithToWindowByID(googlePageID);
		//Return to parent page
		System.out.println("CurrentURL: " + parentPageID + " " + driver.getCurrentUrl());
		Assert.assertEquals(driver.getTitle(),"SELENIUM WEBDRIVER FORM DEMO");		
		sleepInSeconds(4);
	}

	//@Test
	public void TC_02_Window_Title() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSeconds(2);
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSeconds(4);
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSeconds(3);
		
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSeconds(1);
		
		swithToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSeconds(10);
		
		//Nhay ve window parent
		swithToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSeconds(5);
		
	}

	//@Test
	public void TC_03_Kyna() {
		driver.get("https://kyna.vn/");
		String parentPageID = driver.getWindowHandle();
		
		By salePopupBy = By.cssSelector("div.fancybox-inner img");
		List<WebElement> salePopupElement = driver.findElements(salePopupBy);
		
		if(salePopupElement.size() > 0) {
			System.out.println("---------- Pop up hien thi & close đi --------");
			driver.findElement(By.cssSelector("a.fancybox-close")).click();
			sleepInSeconds(2);
		} else {
			System.out.println("-------------Popup ko hien thi & qua step sau--------------");
		}
		//Click vao facebook link
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
		
		//Switch to facebook page
		swithToWindowByTitle("Kyna.vn - Home | Facebook");
		//Assert.assertEquals(driver.findElement(By.cssSelector("h1#seo_h1_tag span")).getText(), "Kyna.vn");
		
		swithToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		
		//Click vao link youtube
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")).click();
		swithToWindowByTitle("Kyna.vn - YouTube");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#contentContainer div#channel-header-container div#text-container #text")).getText(), "Kyna.vn");
		
		closeAllWindowWithoutParent("parentPageID");
		
		}
	
	@Test
	public void TC_04_Techpanda() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		String parentPageID = driver.getWindowHandle();
		
		//Click on Add Compare on Iphone
		driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']/ul/child::li/a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product IPhone has been added to comparison list.");
		sleepInSeconds(2);
			
		//Click on Samsung Galaxy
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/ul/child::li/a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		sleepInSeconds(3);
		
		//Click on Compare button
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSeconds(3);
		
		//Switch to page to compare 
		swithToWindowByTitle("Products Comparison List - Magento Commerce");
		sleepInSeconds(5);
		
		//Close current tab
		closeAllWindowWithoutParent(parentPageID);
		
		//Switch to parent window
		swithToWindowByTitle("Mobile");
		
		//Print current parent link
		System.out.println("Current URL: " + driver.getCurrentUrl() + "Title" + driver.getTitle());
		
		//Click on Clear All
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		
		//Wait truoc roi moi click 
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "Are you sure you would like to remove all products from your comparison?");
		sleepInSeconds(10);
		alert.accept();
		sleepInSeconds(10);
		//Verify the messag success
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The comparison list was cleared.");	
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
	
	public void swithToWindowByID(String windowPageID) {
		// Lay ra tat ca cac ID dang có
		Set<String> windowPageIDList = driver.getWindowHandles();
		
		// Dung vong lap duyet qua tung ID
		for (String eachWindow : windowPageIDList) {
			if (!eachWindow.equals(windowPageID)) {
				driver.switchTo().window(eachWindow);
				System.out.println("Current URL as: " + eachWindow + " " + driver.getCurrentUrl() );		
			}
		}
		
	}
	
	public void swithToWindowByTitle(String expectedTitlePage) {
		// Lay ra tat ca cac ID dang có
		Set<String> windowPageIDList = driver.getWindowHandles();
		
		// Dung vong lap duyet qua tung ID
		for (String eachWindow : windowPageIDList) {
			//Switch qua tung tab ID
			driver.switchTo().window(eachWindow);
			sleepInSeconds(2);
				
			//Kiem tra sau
			String actualPageTitle = driver.getTitle().trim();
			System.out.println(actualPageTitle);
			if(actualPageTitle.equals(expectedTitlePage)) {
				break;
			 }
		   }
		}
	public void closeAllWindowWithoutParent(String parentPageID) {
		//Lay tat ca cac ID dang có
		Set<String> windowPageIDList = driver.getWindowHandles();
		
		// Dung vong lap duyet qua tung ID
		for (String eachWindow : windowPageIDList) {
			//Switch qua tung tab ID
			if(!eachWindow.equals(parentPageID)) {
				driver.switchTo().window(eachWindow);
				sleepInSeconds(2);
				
				// No chi close window dang active
				driver.close();
			 }
		   }
	}
		
	}