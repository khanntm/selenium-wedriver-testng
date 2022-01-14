package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_08_Textbox_TextArea {
	WebDriver driver;
	JavascriptExecutor jsExecutor; 
	String projectPath = System.getProperty("user.dir");
	String loginPageUrl;
	String userID;
	String userPassword;
	String customerName, gender, dateOfBirth, addressInput, addressOutput, city, state, pinNumber, phoneNumber, email, password; 
	String addressInputEdit, addressOutputEdit, cityEdit, stateEdit, pinNumberEdit, phoneNumberEdit, emailEdit;
	String customerID;

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Ep kieu 
		jsExecutor = (JavascriptExecutor) driver;
	    
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("http://demo.guru99.com/v4");
		
		loginPageUrl = "http://demo.guru99.com/v4"; 
		customerName= "Brian Tracy"; 
		gender = "male";
		dateOfBirth= "1950-01-31";
		addressInput= "123 Los Angles \nUnites State"; 
		addressOutput= "123 Los Angles Unites State";
		city= "New York"; 
		state= "California"; 
		pinNumber= "632565"; 
		phoneNumber= "0992673872"; 
		email= "Briance" + getRandomNumber() + "@email.us"; 
		password = "123456";
		
		addressInputEdit= "490 Le Van Sy \n P14 Q3"; 
		addressOutputEdit= "490 Le Van Sy P14 Q3";
		cityEdit= "Ho Chi Minh"; 
		stateEdit= "Nam Lo"; 
		pinNumberEdit= "094838"; 
		phoneNumberEdit= "0938473772"; 
		emailEdit= "Khan" + getRandomNumber() + "@email.us"; 

		driver.get(loginPageUrl);
	}

	@Test
	public void TC_01_Register() {
		
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		//Truyen tham so la 1 email random
		driver.findElement(By.name("emailid")).sendKeys("khan" + getRandomNumber() + "@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		driver.get(loginPageUrl);
		

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(userPassword);
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		// Neu de nhu vay thi ko ao loi nhung ko ra kq String & String
		// kieu du lieu phai ang
		// cung gia tri
		//Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")), "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(), "Manger Id : " + userID);
		
	}

	@Test
	public void TC_03_New_Customer() {
		//driver.get("http://demo.guru99.com/v4/manager/addcustomerpage.php");
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys(customerName);
		
		WebElement dobTextbox = driver.findElement(By.name("dob"));
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", dobTextbox);
		SleepInSecond(3);
		dobTextbox.sendKeys(dateOfBirth);
		
		driver.findElement(By.name("addr")).sendKeys(addressInput); // Text Area
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pinNumber);
		driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		SleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");
		
		//Check display Customer ID 
		/*WebElement customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td"));
		boolean Status_customerID = customerID.isDisplayed();
		
		if (Status_customerID) {
			System.out.println("Customer ID is existed" + " " + customerID.getText());
		} else {
			System.out.println("Customer ID is not existed");

		} */
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(); 
		
	}
	
	@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		// Verify customer display correctly
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.name("gender")).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(By.name("dob")).getAttribute("value"), dateOfBirth );
		Assert.assertEquals(driver.findElement(By.name("addr")).getAttribute("value"), addressInput);
		Assert.assertEquals(driver.findElement(By.name("city")).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(By.name("state")).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(By.name("pinno")).getAttribute("value"), pinNumber);
		Assert.assertEquals(driver.findElement(By.name("telephoneno")).getAttribute("value"), phoneNumber);
		Assert.assertEquals(driver.findElement(By.name("emailid")).getAttribute("value"), email);
		
		// Edit customer
		driver.findElement(By.name("addr")).clear();
		driver.findElement(By.name("addr")).sendKeys(addressInputEdit);
		driver.findElement(By.name("city")).clear();
		driver.findElement(By.name("city")).sendKeys(cityEdit);
		driver.findElement(By.name("state")).clear();
		driver.findElement(By.name("state")).sendKeys(stateEdit);
		driver.findElement(By.name("pinno")).clear();
		driver.findElement(By.name("pinno")).sendKeys(pinNumberEdit);
		driver.findElement(By.name("telephoneno")).clear();
		driver.findElement(By.name("telephoneno")).sendKeys(phoneNumberEdit);
		driver.findElement(By.name("emailid")).clear();
		driver.findElement(By.name("emailid")).sendKeys(emailEdit);
		
		driver.findElement(By.name("sub")).click();
		SleepInSecond(6);
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer details updated Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressOutputEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), cityEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), stateEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinNumberEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phoneNumberEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailEdit);
	
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
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