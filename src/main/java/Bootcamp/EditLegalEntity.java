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

public class EditLegalEntity {

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
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement ViewAllbutton = driver.findElement(By.xpath("(//button[text()='View All'])[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(ViewAllbutton));
		ViewAllbutton.click();
		Actions a = new Actions(driver);
		WebElement LegalEntity = driver.findElement(By.xpath("//p[text()='Legal Entities']"));
		a.moveToElement(LegalEntity).click().perform();
		
	//4. Click on the legal Entities tab 
		Thread.sleep(5000);
		WebElement LegalEntity2 = driver.findElementByXPath("(//span[text()='Legal Entities'])[1]");
		driver.executeScript("arguments[0].click();", LegalEntity2);
		
	//5. Search the Legal Entity 'Salesforce Automation by Your Name
		Actions obj = new Actions(driver);
		WebElement input = driver.findElementByXPath("//input[@name='LegalEntity-search-input']");
		input.sendKeys("Salesforce Automation by Kanagaraj");
		Thread.sleep(3000);
		driver.findElementByXPath("//*[@data-key='refresh']").click();
		Thread.sleep(2000);
		
		WebElement dropdown = driver.findElementByXPath("(//span[@class='slds-icon_container slds-icon-utility-down']/span)[1]");
		driver.executeScript("arguments[0].scrollIntoView();",dropdown );
		//wait.until(ExpectedConditions.elementToBeClickable(dropdown));
		Thread.sleep(2000);
		dropdown.click();
		WebElement edit = driver.findElementByXPath("//a[@title='Edit']"); 
		obj.moveToElement(edit).click().build().perform();
		 
		driver.findElementByXPath("(//input[@class=' input'])[1]").clear();		
		driver.findElementByXPath("(//input[@class=' input'])[1]").sendKeys("Testleaf");
		driver.findElementByXPath("(//textarea[@role='textbox'])[2]").sendKeys("SalesForce");
		driver.findElementByXPath("//a[@class='select']").click();
		driver.findElementByXPath("//a[text()='Active']").click();
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		WebElement result = driver.findElementByXPath("//span[@class='toastMessage slds-text-heading--small forceActionsText']");
		wait.until(ExpectedConditions.elementToBeClickable(result));
		System.out.println(result.getText());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")));
		driver.findElementByXPath("//a[@title='Testleaf']").click();
		
		String finalstatus = driver.findElementByXPath("(//span[@title='Status']/following-sibling::div/div/span)[2]").getText();
		System.out.println(finalstatus);
		

	}

}


