package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_05_Run_Many_Browsers {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");


	@Test
	public void TC_01_Run_FF() {
		if(osName.startsWith("Windows"))
		{
			//Window
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else
		{
			//MAC
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			
		}
		driver = new FirefoxDriver();

		driver.get("https://www.facebook.com/");
		SleepInSecond(5);
		
		driver.quit();
			       
	}

	@Test
	public void TC_02_Run_Chrome() {
		if(osName.startsWith("Windows"))
		{
			//Window
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		else
		{
			//MAC
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			
		}
		driver = new ChromeDriver();

		driver.get("https://www.facebook.com/");
		SleepInSecond(5);
		
		driver.quit();
		
	}

	@Test
	public void TC_03_Run_Edge_Chromium() {
		if(osName.startsWith("Windows"))
		{
			//Window
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		}
		else
		{
			//MAC
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
			
		}
		driver = new EdgeDriver();

		driver.get("https://www.facebook.com/");
		SleepInSecond(5);
		
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