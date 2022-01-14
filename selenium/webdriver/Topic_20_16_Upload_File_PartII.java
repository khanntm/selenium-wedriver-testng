package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_20_16_Upload_File_PartII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	String image01 = "Excel_Format_01.jpg";
	String image02 = "Excel_Format_02.jpg";
	String image03 = "Excel_Format_03.jpg";
	
	String jsonFile = "startNode.json";
	String image01Path = projectPath + "\\Upload_File\\" + image01;
	String image02Path = projectPath + "\\Upload_File\\" + image02;
	String image03Path = projectPath + "\\Upload_File\\" + image03;
	String jsonPath = projectPath + "\\uploadFiles\\" + jsonFile;
	String singleFirefox = projectPath + "\\autoIT\\" + "Single_Firefox.exe";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_AutoIT_Single_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		sleepInSeconds(2);
		
		//Upload via AutoIT
		//Runtime.getRuntime().exec(new String[] {singleFirefox,image01Path});
		sleepInSeconds(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image01 + "']")).isDisplayed());
			       
	}

	@Test
	public void TC_02_AutoIT_Multiples_Files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		sleepInSeconds(2);
		
		//Upload via AutoIT
		//Runtime.getRuntime().exec(new String[] {singleFirefox,image01Path});
		sleepInSeconds(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image01 + "']")).isDisplayed());
		
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