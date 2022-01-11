package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_18_16_Upload_File_PartI {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String image01 = "Excel_Format_01.jpg";
	String image02 = "Excel_Format_02.jpg";
	String image03 = "Excel_Format_03.jpg";
	
	String image01Path = projectPath + "\\Upload_File\\" + image01;
	String image02Path = projectPath + "\\Upload_File\\" + image02;
	String image03Path = projectPath + "\\Upload_File\\" + image03;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 20);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_SendKey() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Load file success
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image01Path);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image02Path);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image03Path);
		sleepInSeconds(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image01 + "']")).isDisplayed());
		sleepInSeconds(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image02 + "']")).isDisplayed());
		sleepInSeconds(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image03 + "']")).isDisplayed());
		sleepInSeconds(3);
		
		//Click Start Upload each images
		List<WebElement> uploadButtons = driver.findElements(By.xpath("//tr[@class='template-upload fade image in']//button[@class='btn btn-primary start']"));
		for (WebElement eachImage : uploadButtons) {
			eachImage.click();
			sleepInSeconds(3);
		}
		
		//Verify upload success p.name>a[title='Excel_Format_01.jpg']
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + image01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + image02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + image03 + "']")).isDisplayed());
		
		
	}

	//@Test
	public void TC_02_Multiple_Files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Load file success
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image01Path + "\n" + image02Path  + "\n" + image03Path);
		
        sleepInSeconds(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image01 + "']")).isDisplayed());
		sleepInSeconds(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image02 + "']")).isDisplayed());
		sleepInSeconds(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image03 + "']")).isDisplayed());
		sleepInSeconds(3);
		
		

		//Click Start Upload each images
		List<WebElement> uploadButtons = driver.findElements(By.xpath("//tr[@class='template-upload fade image in']//button[@class='btn btn-primary start']"));
		for (WebElement eachImage : uploadButtons) {
			eachImage.click();
			sleepInSeconds(3);
		}
		
		//Verify upload success p.name>a[title='Excel_Format_01.jpg']
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + image01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + image02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + image03 + "']")).isDisplayed());
		
	}

	@Test
	public void TC_03_GoFFile() {
		driver.get("https://gofile.io/?t=uploadFiles");
		//Load file success
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image01Path + "\n" + image02Path  + "\n" + image03Path);
		//sleepInSeconds(10);	
		
		//Wait cho cac icon dang loading bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		
		//Spiner biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainContent i.fa-spinner")));
		
		//Wait cho message visible & display là 1
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Your files have been successfully uploaded')]")));
		
		//Check display message upload success i.text-success
		Assert.assertTrue(driver.findElement(By.cssSelector("i.text-success")).isDisplayed());
		
	    String uploadUrl = driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).getAttribute("href");
	    driver.get(uploadUrl);
	    
	    Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + image01 + "']")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + image02 + "']")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + image03 + "']")).isDisplayed());
		
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