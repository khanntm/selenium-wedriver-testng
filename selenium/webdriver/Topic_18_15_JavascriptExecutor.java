package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_18_15_JavascriptExecutor {
	WebDriver driver;
	Select select;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	
	String loginPageUrl;
	String userID;
	String userPassword;
	String customerName, gender, dateOfBirth, addressInput, addressOutput, city, state, pinNumber, phoneNumber, email, password; 
	String addressInputEdit, addressOutputEdit, cityEdit, stateEdit, pinNumberEdit, phoneNumberEdit, emailEdit;
	String customerID;
	
	String FirstName, LastName, EmailAddress, Pass;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("http://demo.guru99.com/v4");	
		
		FirstName = "Khan";
		LastName = "Nguyen"; 
		EmailAddress = "Briance" + getRandomNumber() + "@email.us"; 
		Pass = "12345678";
		
	}

	//@Test
	public void TC_01_JavaScript_Executor() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);
		
		//Get domain cua page, ham nay dang la kieu O*ject nen co the ep kieu qua String
		String domainLivePanda = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domainLivePanda, "live.techpanda.org");	       
		
		//Get URL cua page
		String urlLivePanda = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(urlLivePanda, "http://live.techpanda.org/");	       
		sleepInSecond(5);
		
		//Highlight element sap dc click 
		hightlightElement("//a[text()='Mobile']");
		//Open Mo*ile //a[text()='Mobile']
		clickToElementByJS("//a[text()='Mobile']");
		
		//Hightlight & click element //a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']/button
		hightlightElement("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(5);
		
		//Verify text is display
		String innerTextValue = getInnerText();
		sleepInSecond(5);
		
		Assert.assertTrue(innerTextValue.contains("IPhone was added to your shopping cart."));
		//Verify cach 2
		Assert.assertTrue(areExpectedTextInInnerText("IPhone was added to your shopping cart."));
		sleepInSecond(5);
		
		//Open customer service page
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(5);
		//Verify page Title 
		//Verify page Title 
		String pageTitelCustomer = (String) executeForBrowser("return document.title");
		Assert.assertEquals(pageTitelCustomer, "Customer Service");
		
		//Scroll toi Newsletter nam o cuoi page using cSS
		scrollToElementOnDown("//input[@id='newsletter']");
		sleepInSecond(5);
		
		//Sendkey
		sendkeyToElementByJS("//input[@id='newsletter']", "acder@gmail.com");
		
		//highligh & click
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(4);
	
		//Verify test
		Assert.assertFalse(innerTextValue.contains("Thank you for your"));
		sleepInSecond(3);
		
		//Navigate to other page
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(3);
		
		//Get domain cua page, ham nay dang la kieu O*ject nen co the ep kieu qua String
		String domainDemoGuru = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domainDemoGuru, "demo.guru99.com");	       
		
	}

	//@Test
	public void TC_02_HTML5() {
		driver.get("https://automationfc.github.io/html5/index.html");
		
		//Click Su*mit & verify message tại Field Name text*ox
		driver.findElement(By.xpath("//input[@class='btn']")).click();
		
		//Verify error message at FName 
		sleepInSecond(4);
		String errorMessageFN = getElementValidationMessage("//input[@id='fname']");
		sleepInSecond(4);
		Assert.assertEquals(errorMessageFN, "Please fill out this field.");
		
		//Check FN
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("Khan");
		
		driver.findElement(By.xpath("//input[@class='btn']")).click();
		
		String errorMessagePass = getElementValidationMessage("//input[@id='pass']");
		sleepInSecond(4);
		Assert.assertEquals(errorMessagePass, "Please fill out this field.");
	
		//Check LN
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
				
		driver.findElement(By.xpath("//input[@class='btn']")).click();
		
		//Check email
		driver.findElement(By.xpath("//input[@class='btn']")).click();
		
		String errorMessageEmail = getElementValidationMessage("//input[@id='em']");
		sleepInSecond(4);
		Assert.assertEquals(errorMessageEmail, "Please fill out this field.");
		
		driver.findElement(By.xpath("//input[@id='em']")).sendKeys("khann@gmail.com");
		
		driver.findElement(By.xpath("//input[@class='btn']")).click();
		
		String errorMessageAddress = getElementValidationMessage("//select");
		sleepInSecond(4);
		Assert.assertEquals(errorMessageAddress, "Please select an item in the list.");
		
		//Click to select an option
		select = new Select(driver.findElement(By.xpath("//select")));
		select.selectByVisibleText("HO CHI MINH");
		sleepInSecond(4);
		
		// Verify gia tri chon duoc đúng hay ko?
		Assert.assertEquals("HO CHI MINH", select.getFirstSelectedOption().getText());
		
	}

	//@Test
	public void TC_03_Remove_Attribute() {
		//Copy from topic text*ox
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
		password = "YmYjuzU";
		
		addressInputEdit= "490 Le Van Sy \n P14 Q3"; 
		addressOutputEdit= "490 Le Van Sy P14 Q3";
		cityEdit= "Ho Chi Minh"; 
		stateEdit= "Nam Lo"; 
		pinNumberEdit= "094838"; 
		phoneNumberEdit= "0938473772"; 
		emailEdit= "Khan" + getRandomNumber() + "@email.us"; 

		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys("mngr379258");
		driver.findElement(By.name("password")).sendKeys("YmYjuzU");
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		// Neu de nhu vay thi ko ao loi nhung ko ra kq String & String
		// kieu du lieu phai ang
		// cung gia tri
		//Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")), "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(), "Manger Id : " + userID);
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys(customerName);
		
		//Remote attriute in DOM
		removeAttributeInDOM("//input[@name='dob]", "type");
		
		
		driver.findElement(By.name("addr")).sendKeys(addressInput); // Text Area
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pinNumber);
		driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		sleepInSecond(3);
		
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
		
	}
	

	@Test
	public void TC_04_Create_An_Account() {
		driver.get("http://live.techpanda.org/");
		
		//Click vao My Account by Js Executor 
		sleepInSecond(5);
		//driver.findElement(By.xpath("//header[@id='header']//a[@title='My Account']")).click();
		clickToElementByJS("//header[@id='header']//a[@title='My Account']");
		sleepInSecond(3);
		
		//Verify
		//Get URL cua page
		String urlMyAccount = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(urlMyAccount, "http://live.techpanda.org/index.php/customer/account/login/");	       
		sleepInSecond(5);
		
		//Click on Create An Account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(FirstName);
		driver.findElement(By.id("lastname")).sendKeys(LastName);
		driver.findElement(By.id("email_address")).sendKeys(EmailAddress);
		driver.findElement(By.id("password")).sendKeys(Pass);
		driver.findElement(By.id("confirmation")).sendKeys(Pass);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		sleepInSecond(10);
		
		//Verify message khi dk thanh cong
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for registering with Main Website Store."));
		sleepInSecond(5);
		
		//Click Logout //a[@title='Log Out']
		clickToElementByJS("//a[@title='Log Out']");
		sleepInSecond(3);
		
		//Navigate to homeepage 
		navigateToUrlByJS("http://live.techpanda.org/index.php/");
		sleepInSecond(3);
		
		String urlHomepage = (String) executeForBrowser("return document.URL");
		System.out.println("The url of home page: " + urlHomepage);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		//Ham này tra ve O*ject nên ep kieu trả ve String thì phải return moi tra ve
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		//Kiem tra Text nam trong InnerText hay ko
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		// The same voi lenh duoi nhung viet ham rieng de goi getElement cho ngan gon 
		// WebElement element = getElement(locator);
		WebElement element = getElement(locator);
		
		// Get trang thai A
		String originalStyle = element.getAttribute("style");
		
		// Trang thai C: Co viền *ao quanh
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public int getRandomNumber()
	{
		// 1 ham tra ve 1 kieu du lieu cu the thì se return de lay du lieu ra
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public void sleepInSecond(long second)
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