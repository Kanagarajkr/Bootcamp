package Bootcamp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateIndividualsWithoutMandatory {

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
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		WebElement ViewAllbutton = driver.findElement(By.xpath("(//button[text()='View All'])[2]"));
		ViewAllbutton.click();
		WebElement Individuals = driver.findElementByXPath("//p[text()='Individuals']");
		driver.executeScript("arguments[0].scrollIntoView();", Individuals);
		Individuals.click();
		Thread.sleep(3000);
		WebElement drop = driver.findElementByXPath("(//lightning-icon[@class='slds-icon-utility-chevrondown slds-icon_container']/lightning-primitive-icon/*)[15]");
		//driver.executeScript("arguments[0].click();", drop);
		drop.click();
		Thread.sleep(3000);
		WebElement newIndividual = driver.findElementByXPath("//span[text()='New Individual']");
		driver.executeScript("arguments[0].click();", newIndividual);
		driver.findElementByXPath("//a[@class='select']").click();
		List<WebElement> salutationList = driver.findElementsByXPath("//li[@role='presentation']");
		int size = salutationList.size();
		for (int i = 0; i < size; i++) {
			String text = salutationList.get(i).getText();
			if(text.contains("Mr.")) {
				salutationList.get(i).click();
				
			}
		}
		driver.findElementByXPath("//input[contains(@class,'firstName')]").sendKeys("Ganesh");
		driver.findElementByXPath("//*[@title='Save']").click();
		String text = driver.findElementByXPath("//input[contains(@class,'lastName')]/following::ul/li").getText();
		System.out.println("Error message received: "+text);
		
	}

}
