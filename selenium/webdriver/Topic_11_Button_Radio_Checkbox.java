package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_11_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor; 	
	String projectPath = System.getProperty("user.dir");

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		By loginButton = By.cssSelector("button.fhs-btn-login");
		By loginTab = By.cssSelector("li.pop-login-tab-login");
		
		driver.findElement(loginTab).click();
		
		//Verify button Login disable
		
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		System.out.println("login Button is disabled");
		driver.findElement(By.id("login_username")).sendKeys("khan@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("abcd1234");
		sleepInSeconds(2);
		
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		// Refresh the page
		driver.navigate().refresh();
		driver.findElement(loginTab).click();
		// Remove disale attr cua Login 	 
		
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButton));
		sleepInSeconds(3);
		
		//LoginButton is enabled -> Verify it
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		System.out.println("Login Button is enabled after remove attribute" + driver.findElement(loginButton).getText());
		
		// Verify login button as RED color (background color)
		String loginRgbColor_Button = driver.findElement(loginButton).getCssValue("Background-color");
		System.out.println("Màu của Login button " + loginRgbColor_Button);
		
		// Verify mã màu mã RGb
		Assert.assertEquals(loginRgbColor_Button, "rgb(201,33,39)");
		
		//Verify HECxA
		String loginHexaColor_Button = Color.fromString(loginRgbColor_Button).asHex().toUpperCase();
		Assert.assertEquals(loginHexaColor_Button, "#C92129");
		
		
		//Click on Login Button
		driver.findElement(loginButton).click();
				
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
	}

	@Test
	public void TC_02_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		sleepInSeconds(3);
		
		By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		By twoDiselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");
		
		//Scroll the screen
		jsExecutor.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//div[@id='example']")));
				
		
		driver.findElement(twoPetrolRadio).click();
		Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());

		driver.findElement(twoDiselRadio).click();
		sleepInSeconds(2); 
		Assert.assertFalse(driver.findElement(twoPetrolRadio).isSelected());
	}

	//@Test
	public void TC_03_Checkbox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		By duralZoneCheckox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		By luggageCoverCheckox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		
		//Scroll the screen
		jsExecutor.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//div[@id='demo-runner']")));
		
		driver.findElement(luggageCoverCheckox).click();
		sleepInSeconds(3);
		Assert.assertTrue(driver.findElement(luggageCoverCheckox).isSelected());
		
		driver.findElement(luggageCoverCheckox).click();
		sleepInSeconds(2);
		Assert.assertFalse(driver.findElement(luggageCoverCheckox).isSelected());
		
		driver.findElement(duralZoneCheckox).click();
		sleepInSeconds(2);
		Assert.assertTrue(driver.findElement(duralZoneCheckox).isSelected());
	}

	//@Test
	public void TC_04_CheckboxAll() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> listCheckboxs = driver.findElements(By.xpath("//input[@type='checkbox']")); 
		//Select all checkboxes
		for (WebElement eachCheckbox : listCheckboxs) {
			if (!eachCheckbox.isSelected()) {
				eachCheckbox.click();
				//sleepInSeconds(2);
			}	
		}
		//Verify all checkboxes là selected
		for (WebElement eachCheckbox : listCheckboxs) {
		Assert.assertTrue(eachCheckbox.isSelected());
		}
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
}