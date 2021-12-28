package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_Custom_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	
	}

	@Test
	public void TC_01_JQuery() {
		
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// Goi ham de dung
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "7");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "7");
		
	}

	@Test
	public void TC_02_ReactJSC() {
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[contains(@class,'item')]/span[@class='text']", "Matt");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Matt");
		
		
		//1. Click vào 1 locator bat ky de xổ ra các item trong dropdown
		driver.findElement(By.xpath("//i[@class='dropdown icon']")).click();
		sleepInSecond(20);
		
		//2. Cho cac item duoc hien thi len
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'item')]/span[@class='text']")));
		
		//3. Lấy hết tat ca cac item ra
		List<WebElement> childItems_TC02 = driver.findElements(By.xpath("//div[contains(@class,'item')]/span[@class='text']"));
		// In ra danh sach cac item
		System.out.println("Danh sach cac item = " + childItems_TC02.size());	
		
		//Duyet qua từng item 
		for (WebElement tempElementTC02 : childItems_TC02) {
			System.out.println("Item Text TC02 = "+ tempElementTC02.getText());
			// Check thu neu dung cai da chọn thì dừng 
			if (tempElementTC02.getText().equals("Jenny Hess")) {
				//Click chọn 
				tempElementTC02.click();
				sleepInSecond(3);
				break;
			}
			
		} 
		
	}

	@Test
	public void TC_03_vuedropdown() {
		
		// Parent //span[@class='caret']
		// Children //a[@data-v-3ec2ada6='']
		// Element text  //li[@class='dropdown-toggle']
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//span[@class='caret']", "//a[@data-v-3ec2ada6='']", "First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");
		
		selectItemInCustomDropdown("//span[@class='caret']", "//a[@data-v-3ec2ada6='']", "Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
		
		selectItemInCustomDropdown("//span[@class='caret']", "//a[@data-v-3ec2ada6='']", "Third Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
		
	}
	
	@Test
	public void TC_04_Angular() {
		
		//parent: //ejs-dropdownlist[@id='games']//span[@class='e-input-group-icon e-ddl-icon e-search-icon']
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[@class='e-input-group-icon e-ddl-icon e-search-icon']", "//ul[@id='games_options']/li", "Football");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector('#games input').value;"), "Football");
		
		//jsExecutor.executeScript("return document.querySelector("#games input").value;", "Football");
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[@class='e-input-group-icon e-ddl-icon e-search-icon']", "//ul[@id='games_options']/li", "Tennis");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector('#games input').value;"), "Tennis");
		
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[@class='e-input-group-icon e-ddl-icon e-search-icon']", "//ul[@id='games_options']/li", "Golf");
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector('#games input').value;"), "Golf");
		
	}
	
	@Test
	public void TC_05_AngularTiemChung() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		// Parent: //ng-select[@formcontrolname='provinceCode']//div[@class='ng-select-container']
		// Children lay the chứa text //span[contains(@class,'ng-option-label')]
		selectItemInCustomDropdown("//ng-select[@formcontrolname='provinceCode']//div[@class='ng-select-container']", "//span[contains(@class,'ng-option-label')]", "Thành phố Hồ Chí Minh");
		sleepInSecond(3);
		//Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode']\").innerText.replace(\"×\",\"\").trim()"), "Thành phố Hồ Chí Minh");
		
		//String expectedProvice = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode']\").innerText");
		//Assert.assertEquals(expectedProvice.replace("×", "").trim(),"Thành phố Hồ Chí Minh");
		
		Assert.assertEquals(driver.findElement(By.xpath("//ng-select[@bindvalue='provinceCode']//span[contains(@class,'ng-value-label')]")).getText(), "Thành phố Hồ Chí Minh");
		
		selectItemInCustomDropdown("//ng-select[@bindvalue='districtCode']//span[@class='ng-arrow-wrapper']", "//span[@class='ng-option-label ng-star-inserted']", "Thành phố Thủ Đức");
		sleepInSecond(3);
		//Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='districtCode']\").innerText.replace(\"×\",\"\").trim()"), "Thành phố Thủ Đức");
		
		//String expectedDistrict = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='districtCode']\").innerText");
		//Assert.assertEquals(expectedDistrict.replace("×", "").trim(),"Thành phố Thủ Đức");
		
		Assert.assertEquals(driver.findElement(By.xpath("//ng-select[@bindvalue='districtCode']//span[contains(@class,'ng-value-label')]")).getText(), "Thành phố Thủ Đức");
		
		
		selectItemInCustomDropdown("//ng-select[@bindvalue='wardCode']", "//span[contains(@class,'ng-option-label')]", "Phường Tăng Nhơn Phú B");
		sleepInSecond(3);
		//Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='wardCode']\").innerText.replace(\"×\",\"\").trim()"), "Phường Tăng Nhơn Phú B");
		
		//String expectedWard = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='wardCode']\").innerText");
		//Assert.assertEquals(expectedWard.replace("×", "").trim(),"Phường Tăng Nhơn Phú B");
		
		Assert.assertEquals(driver.findElement(By.xpath("//ng-select[@bindvalue='wardCode']//span[contains(@class,'ng-value-label')]")).getText(), "Phường Tăng Nhơn Phú B");
		
	}
	
	@Test
	public void TC_06_Editable() {
		
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		selectItemInEditableDropdown("//ng-select[@bindvalue='provinceCode']//input", "//span[contains(@class,'ng-option-label')]", "Thành phố Hồ Chí Minh");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//ng-select[@bindvalue='provinceCode']//span[contains(@class,'ng-value-label')]")).getText(), "Thành phố Hồ Chí Minh");
		
		selectItemInEditableDropdown("//ng-select[@bindvalue='districtCode']//input", "//span[contains(@class,'ng-option-label')]", "Thành phố Thủ Đức");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//ng-select[@bindvalue='districtCode']//span[contains(@class,'ng-value-label')]")).getText(), "Thành phố Thủ Đức");
		
		selectItemInEditableDropdown("//ng-select[@bindvalue='wardCode']//input", "//span[contains(@class,'ng-option-label')]", "Phường Tăng Nhơn Phú B");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//ng-select[@bindvalue='wardCode']//span[contains(@class,'ng-value-label')]")).getText(), "Phường Tăng Nhơn Phú B");
		
	}
	

	@Test
	public void TC_07_Editable_Semantic() {
		
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
		selectItemInEditableDropdown("//div[@id='default-place']/input", "//div[@id='default-place']/ul/li", "Audi");
		sleepInSecond(2);
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"#default-place>input\").value;"), "Audi");
		
		selectItemInEditableDropdown("//div[@id='default-place']/input", "//div[@id='default-place']/ul/li", "Smart");
		sleepInSecond(2);
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"#default-place>input\").value;"), "Smart");
	
		selectItemInEditableDropdown("//div[@id='default-place']/input", "//div[@id='default-place']/ul/li", "Jeep");
		sleepInSecond(2);
		Assert.assertEquals(jsExecutor.executeScript("return document.querySelector(\"#default-place>input\").value;"), "Jeep");
	
	}
	
	@Test
	public void TC_08_jQuery_Scroll() {
		
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		// Parent: //span[@id='number-button']
		// Child: //ul[@id='number-menu']/li/div
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "19");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "19");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
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
	
	public void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectedItemText) {
		// CHON ITEM TRONG DROPDOWN
				//1. Click vào 1 thẻ dai dien cho the cha xo ra het cac item
				driver.findElement(By.xpath(parentXpath)).click();
				sleepInSecond(2);
				
				//2. Chờ cho các item duoc load len/ hien thi thanh cong & dc present trong vong 30s
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
				
				//3. Lấy hết tat ca cac item ra 
				List<WebElement> childItems = driver.findElements(By.xpath(childXpath));
				//chua 19 items
				System.out.println("Tong so luong item trong dropdown = " + childItems.size());
				//4. duyet tung item 
				for (WebElement tempElement : childItems) {
					System.out.println("Item text = " + tempElement.getText());
					//5. xem co dung vs cai da chon ko
					if (tempElement.getText().trim().equals(expectedItemText)) {
						//6. Neu đúng cái cần chọn thì click vào
						if (tempElement.isDisplayed()) {
							System.out.println("Click by Selenium " + tempElement.getText());
							tempElement.click();
							sleepInSecond(1);
						} else {
							// Scroll to element
							System.out.println("Click by Js" + tempElement.getText());
							jsExecutor.executeScript("arguments[0].scrollIntoView(true)", tempElement);
							sleepInSecond(1);
							//Click by Javascript Executor
							jsExecutor.executeScript("arguments[0].click();", tempElement);
							sleepInSecond(1);
						}
						//7. thoat ra khoi vong lap
						//8. dropdow đóng nen duyet tiep se bi sai
						//9. nhung item con lai ko duyet nua
						break;
					}
				}

	}
	
	public void selectItemInEditableDropdown(String parentXpath, String childXpath, String expectedItemText) {
		// CHON ITEM TRONG DROPDOWN
		
				// 0. Clear text trong dropdown truoc, neu chọn lại thi ko bi duplicate dữ liệu
				driver.findElement(By.xpath(parentXpath)).clear();
				sleepInSecond(1);

				//1. Click vào 1 thẻ dai dien cho the cha xo ra het cac item
				driver.findElement(By.xpath(parentXpath)).sendKeys(expectedItemText);
				sleepInSecond(2);
				
				//2. Chờ cho các item duoc load len/ hien thi thanh cong & dc present trong vong 30s
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
				
				//3. Lấy hết tat ca cac item ra 
				List<WebElement> childItems = driver.findElements(By.xpath(childXpath));
				//chua 19 items
				System.out.println("Tong so luong item trong dropdown = " + childItems.size());
				//4. duyet tung item 
				for (WebElement tempElement : childItems) {
					System.out.println("Item text = " + tempElement.getText());
					//5. xem co dung vs cai da chon ko
					if (tempElement.getText().trim().equals(expectedItemText)) {
						//6. Neu đúng cái cần chọn thì click vào 
						tempElement.click();
						sleepInSecond(3);
						//7. thoat ra khoi vong lap
						//8. dropdow đóng nen duyet tiep se bi sai
						//9. nhung item con lai ko duyet nua
						break;
					}
				}

	}
		
}