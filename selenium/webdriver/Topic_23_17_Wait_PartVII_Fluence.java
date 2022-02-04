package webdriver;

import java.time.Duration;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;


public class Topic_23_17_Wait_PartVII_Fluence {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentWaitDriver;
	FluentWait<WebElement> fluenceWaitElement; 

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countDownTime = driver.findElement(By.id("javascript_countdown_time"));
		
		fluenceWaitElement = new FluentWait<WebElement>(countDownTime);
		
		//Wait với tổng thời gian là 15s
		fluenceWaitElement.withTimeout(Duration.ofSeconds(15))
		
		// Cơ chế tìm lại nếu chưa thỏa dk là 1/2s tìm lại
		.pollingEvery(Duration.ofMillis(500))
		
		//Neu như trong thoi gian tim lai ko thay element
		.ignoring(NoSuchElementException.class)
		
		// xu lý dk 
		.until(new Function<WebElement, Boolean>() {
			@Override // Điều kiện
			public Boolean apply(WebElement element) {
			String text = element.getText();
			System.out.println("Time = " + text);
			return text.endsWith("00");
			}
		});
		}
		 

	//@Test
	public void TC_02() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click 'Start' button
		driver.findElement(By.id("start")).click();
		
		fluentWaitDriver = new FluentWait<WebDriver>(driver);
		
		fluentWaitDriver.withTimeout(Duration.ofSeconds(6))
		.pollingEvery(Duration.ofSeconds(1))
		
		//Neu ko run dong nay thi TC failed lập tức, vì ko tìm thấy element finish, nếu xet implicit thì có thể pass
		// No Such Element fai import đúng package nếu ko sẽ hien thị lỗi NoSuchElement, khó phân biệt
		// Import org.openqa.selenium.NoSuchElementException [KO import java.util.NoSuchElementException;]
		.ignoring(NoSuchElementException.class); 

		WebElement helloWordText = fluentWaitDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#finish>h4"));
				
		}
		});
		
		Assert.assertEquals(helloWordText.getText(), "Hello Word!");
		
	}

	//@Test
	public void TC_03() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click 'Start' button
		driver.findElement(By.id("start")).click();
		
		fluentWaitDriver = new FluentWait<WebDriver>(driver);
		
		fluentWaitDriver.withTimeout(Duration.ofSeconds(6))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#finish>h4")).getText().equals("Hello Word!");
		}
		});	
	}
	
	@Test
	public void TC_04() {
		//Custom from TC03 dùng hàm viết sẵn
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click 'Start' button
		waitForElementAndClick(By.cssSelector("div#start>button"));
	
		//Assert.assertEquals(getWebElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		Assert.assertTrue(waitForElementAndDisplay(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
	}
	
	@Test
	public void TC_05() {
		
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
		
		/*Thay thế bằng hàm có Wait 
		 * driver.findElement(By.xpath("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click(); */
		
		getWebElement(By.id("txtUsername")).sendKeys("Admin");
		getWebElement(By.id("txtPassword")).sendKeys("admin123");
		getWebElement(By.id("btnLogin")).click();
		
		Assert.assertTrue(isJQueryLoadedSuccess(driver));
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
	
	//findElement (Custom)
	public WebElement getWebElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	
	public void waitForElementAndClick(By locator)
	{
		WebElement element = getWebElement(locator);
		element.click();
	}
	
	public boolean waitForElementAndDisplay(By locator) {
		WebElement element = getWebElement(locator);
		return element.isDisplayed();
	}
	
	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.
						executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}
	
	public boolean isJQueryAndAjaxIconLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 20);
		jsExecutor = (JavascriptExecutor) driver;
		
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		
		ExpectedCondition<Boolean> ajaxIconLoading = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply (WebDriver driver) {
				return jsExecutor.executeScript("return $('.raDiv').is(':visible')").toString().equals("false");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(ajaxIconLoading);
	}
	
	public boolean isJQueryAndPageLoadSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("completed");
			}
		};
		
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
		
		}
 	
}