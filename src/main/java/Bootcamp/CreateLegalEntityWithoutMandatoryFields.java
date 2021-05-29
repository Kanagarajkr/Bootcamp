package Bootcamp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLegalEntityWithoutMandatoryFields {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("cypress@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Selbootcamp@123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
	//2. Click on the toggle menu button from the left corner	
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
	//3.Click View All and click Legal Entities from App Launcher
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement ViewAllbutton = driver.findElement(By.xpath("(//button[text()='View All'])[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(ViewAllbutton));
		ViewAllbutton.click();
		Actions a = new Actions(driver);
		WebElement LegalEntity = driver.findElement(By.xpath("//p[text()='Legal Entities']"));
		a.moveToElement(LegalEntity).click().perform();
		
	//4.Click on the Dropdown icon in the legal Entities tab
		Thread.sleep(6000);
		WebElement LegalEntitydropdown = driver.findElement(By.xpath("(//span[text()='Legal Entities']//following::div//a//lightning-primitive-icon)[1]/*"));
		LegalEntitydropdown.click();
		
	//5.Click on New Legal Entity
		WebElement newLegalEntity = driver.findElementByXPath("//span[text()='New Legal Entity']");
		driver.executeScript("arguments[0].click();", newLegalEntity);
		
	//6. Enter the Company name as 'Testleaf'.
	//7. Enter Description as 'SalesForce'.
	//8. Select Status as 'Active'
	//9. Click on Save
		
		driver.findElementByXPath("//span[text()='Company Name']/following::input").sendKeys("Testleaf");
		driver.findElementByXPath("(//textarea[@role='textbox'])[2]").sendKeys("SalesForce");
		driver.findElementByXPath("//a[@class='select']").click();
		driver.findElementByXPath("//a[text()='Active']").click();
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		
	//10. Verify the Alert message (Complete this field) displayed for Name
		WebElement alert = driver.findElementByXPath("//li[@class='form-element__help']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='form-element__help']")));
		System.out.println("Alert message received: " +alert.getText());
		
				

	}

}
