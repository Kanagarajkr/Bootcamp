package Bootcamp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLegalEntity {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options  = new ChromeOptions();
	    options.addArguments("--disable-notifications");
	    
		
		ChromeDriver driver = new ChromeDriver(options);
		
	//1. log in to https://login.salesforce.com
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("cypress@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Selbootcamp@123",Keys.ENTER);
		//driver.findElement(By.xpath("//input[@type='submit']")).click();
		
	//2. Click on the toggle menu button from the left corner	
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
	//3.Click View All and click Legal Entities from App Launcher
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement ViewAllbutton = driver.findElement(By.xpath("(//button[text()='View All'])[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(ViewAllbutton));
		ViewAllbutton.click();
		Actions a = new Actions(driver);
		WebElement LegalEntity = driver.findElement(By.xpath("//p[text()='Legal Entities']"));
		a.moveToElement(LegalEntity).click().perform();
		
		
	//4. Click on the Dropdown icon in the legal Entities tab
		Thread.sleep(6000);
		//WebDriverWait wait2 = new WebDriverWait(driver, 30);
		WebElement LegalEntitydropdown = driver.findElement(By.xpath("(//span[text()='Legal Entities']//following::div//a//lightning-primitive-icon)[1]/*"));
		//wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Legal Entities']//following::div//a//lightning-primitive-icon)[1]/*" )));
		 
		LegalEntitydropdown.click();
		
		
	//5.Click on New Legal Entity
		WebElement newLegalEntity = driver.findElementByXPath("//span[text()='New Legal Entity']");
		driver.executeScript("arguments[0].click();", newLegalEntity); 
		
		
		
	//6.Enter Name as 'Salesforce Automation by Your Name'
		String input="Salesforce Automation by KR";
		driver.findElementByXPath("(//input[@class=' input'])[1]").sendKeys(input);
		
	//7.Click save and verify Legal Entity Name
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		
		WebDriverWait wait3 = new WebDriverWait(driver, 15);
		wait3.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(@class,'toastMessage slds-text-heading--small forceActionsText')]")));
		String result = driver.findElementByXPath("//span[contains(@class,'toastMessage slds-text-heading--small forceActionsText')]").getText();
		if(result.equalsIgnoreCase(result)) {
			System.out.println("Test case is pass");
		}
		else {
			System.out.println("Test case failed");
		}
		
		
			
	}

}

// No need to create object for JavascriptExecutor js, there is a method in driver itself.
//There is a method for each locator, no need to use By class.
//not sure which explicit wait type to use in line 40.