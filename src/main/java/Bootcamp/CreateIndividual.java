package Bootcamp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateIndividual {

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
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement ViewAllbutton = driver.findElement(By.xpath("(//button[text()='View All'])[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(ViewAllbutton));
		ViewAllbutton.click();
		WebElement Individuals = driver.findElementByXPath("//p[text()='Individuals']");
		driver.executeScript("arguments[0].scrollIntoView();", Individuals);
		Individuals.click();
		Thread.sleep(3000);
		driver.findElementByXPath("(//lightning-icon[@class='slds-icon-utility-chevrondown slds-icon_container']/lightning-primitive-icon/*)[15]").click();
		Thread.sleep(3000);
		WebElement NewIndividual = driver.findElementByXPath("//span[text()='New Individual']");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", NewIndividual);
		driver.findElementByXPath("//input[contains(@class,\"lastName\")]").sendKeys("Kumar");
		driver.findElementByXPath("//button[@title='Save']/span").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'toastMessage')]")));
		String message = driver.findElementByXPath("//span[contains(@class,'toastMessage')]").getText();
		System.out.println(message);
		String IndividualName = driver.findElementByXPath("//span[text()='Kumar']").getText();
		System.out.println("Individual name is: "+IndividualName);
	}

}
