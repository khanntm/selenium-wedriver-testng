package webdriver;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_14_Action_PartI {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String dragDropHelperPath = projectPath + "\\DragDropHTML5\\drag_and_drop_helper.js";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSeconds(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");	
		
	}

	//@Test
	public void TC_02_Hover_Fahasa() {
		driver.get("https://www.fahasa.com/");
		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage')]//span[@class='menu-title' and text()='Sách Trong Nước']"))).perform();
		sleepInSeconds(2);
		
		action.click(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage')]//a[text()='Kỹ Năng Sống']"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Kỹ năng sống']")).isDisplayed());
	   
		
	}

	//@Test
	public void TC_03_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
	    List<WebElement> listElements =	driver.findElements(By.xpath("//li[contains(@class,'ui-selectee')]")); 
	    System.out.println("Tong so elements: " + listElements.size());
	    
	    action.clickAndHold(listElements.get(0)).moveToElement(listElements.get(7)).release().perform();
	    sleepInSeconds(5);
	    
	    List<WebElement> listSelectedElements =	driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]")); 
	    System.out.println("Tong so elements: " + listSelectedElements.size());
	    
	}
	
	//@Test
		public void TC_04_Click_And_Select() {
			driver.get("https://automationfc.github.io/jquery-selectable/");
		    List<WebElement> listElements =	driver.findElements(By.xpath("//li[contains(@class,'ui-selectee')]")); 
		    System.out.println("Tong so elements: " + listElements.size());
		    
		    // Dang nhan phim Ctrl	
		    action.keyDown(Keys.CONTROL).perform();
		    action.click(listElements.get(1)).click(listElements.get(11)).perform();
		    
		    //Nhả phím ra
		    action.keyUp(Keys.CONTROL).perform();
		    
		    List<WebElement> listSelectedElements =	driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]")); 
		    System.out.println("Tong so elements: " + listSelectedElements.size());
		    
		    Assert.assertEquals(listSelectedElements.size(), 2);
		}
		
		//@Test
		public void TC_05_Double_Click() {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			//By btnDouble = By.xpath("//button[text()='Double click me']");
			// Scroll màn hình
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
			sleepInSeconds(3);
			
			action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
			sleepInSeconds(3);
			Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
		}
		
		//@Test
		public void TC_06_Right_Click() {
			driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
			action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
			sleepInSeconds(3);
			
			action.moveToElement(driver.findElement(By.xpath("//span[text()='Paste']"))).perform();	
			sleepInSeconds(3);
			
			Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
			
			action.click(driver.findElement(By.xpath("//span[text()='Paste']"))).perform();
			sleepInSeconds(3);
			
			Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: paste");
			driver.switchTo().alert().accept();
			
			Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
		}
		
		//@Test
		public void TC_07_Drag_And_Drop_HTML4() {
			driver.get("https://automationfc.github.io/kendo-drag-drop/");
			
			WebElement smallCirle = driver.findElement(By.id("draggable"));
			WebElement bigCirle = driver.findElement(By.id("droptarget"));
			
			action.dragAndDrop(smallCirle, bigCirle).perform();
			sleepInSeconds(3);
			
			Assert.assertEquals(bigCirle.getText(), "You did great!");
			
			//Verify background color
			String grbColor = bigCirle.getCssValue("background-color");
		    String hexaColor = Color.fromString(grbColor).asHex().toLowerCase();
		    Assert.assertEquals(hexaColor, "#03a9f4");
		}
		
		@Test
		public void TC_08_Drag_And_Drop_HTML5_Css() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");	
		String columnACss = "#column-a";
		String columnBCss = "#column-b";
		
		String dragDropHelperContent = getContentFile(dragDropHelperPath);
		
		dragDropHelperContent = dragDropHelperContent + "$(\"" + columnACss + "\").simulateDragDrop({ dropTarget: \"" + columnBCss + "\"});"; 
		
		jsExecutor.executeScript(dragDropHelperContent);
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-a>header")).getText(),"B");
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-b>header")).getText(),"A");
		
		
		//Drag & Drop A to B
		jsExecutor.executeScript(dragDropHelperContent);
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-a>header")).getText(),"A");
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-b>header")).getText(),"B");
		
		}
		
		
	@AfterClass
	public void afterClass() {
		//driver.quit();
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
	
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	/* public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	} */
}