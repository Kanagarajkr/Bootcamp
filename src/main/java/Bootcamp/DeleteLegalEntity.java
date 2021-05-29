package Bootcamp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteLegalEntity {

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
		WebDriverWait wait = new WebDriverWait(driver, 25);
		WebElement ViewAllbutton = driver.findElement(By.xpath("(//button[text()='View All'])[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(ViewAllbutton));
		ViewAllbutton.click();
		Actions a = new Actions(driver);
		WebElement LegalEntity = driver.findElement(By.xpath("//p[text()='Legal Entities']"));
		//a.moveToElement(LegalEntity).click().perform();
		driver.executeScript("arguments[0].scrollIntoView();", LegalEntity);
		LegalEntity.click();
	//4. Click on the legal Entities tab 
		Thread.sleep(5000);
		WebElement LegalEntity2 = driver.findElementByXPath("(//span[text()='Legal Entities'])[1]");
		driver.executeScript("arguments[0].click();", LegalEntity2);
	//5. Search the Legal Entity 'Salesforce Automation by Your Name'
		driver.findElementByXPath("//input[@name='LegalEntity-search-input']").sendKeys("Salesforce Automation by KR");
		Thread.sleep(3000);
		driver.findElementByXPath("//*[@data-key='refresh']").click();
		Thread.sleep(5000);
	//6. Click on the Dropdown icon and Select Delete
		WebElement dropdown = driver.findElementByXPath("(//span[@class='slds-icon_container slds-icon-utility-down']/span)[1]");
		driver.executeScript("arguments[0].scrollIntoView();",dropdown );
		Thread.sleep(2000);
		dropdown.click();
		Thread.sleep(2000);
		WebElement delete = driver.findElementByXPath("//a[@title='Delete']"); 
		a.moveToElement(delete).click().build().perform();
	//7.Click on the Delete option in the displayed popup window.
		driver.findElementByXPath("//span[text()='Delete']").click();
	//8.Verify Whether Legal Entity is Deleted using Legal Entity Name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'forceActionsText')]")));
		String result = driver.findElementByXPath("//span[contains(@class,'forceActionsText')]").getText();
		String[] deletemessage = result.split("\\.");
		System.out.println(deletemessage[0]);
		
		

	}

}
