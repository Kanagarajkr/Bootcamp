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

public class DeleteIndividual {

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
		Thread.sleep(2000);
		WebElement IndividualTab = driver.findElementByXPath("(//span[contains(text(),'Individuals')])[2]");
		driver.executeScript("arguments[0].click;", IndividualTab);
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@placeholder='Search this list...']").sendKeys("kumar");
		Thread.sleep(2000);
		driver.findElementByXPath("//button[@name='refreshButton']").click();
		Thread.sleep(2000);
		WebElement last = driver.findElementByXPath("//span[text()='Last Modified Date']");
		driver.executeScript("arguments[0].click();", last);
		Thread.sleep(2000);
		driver.findElementByXPath("//a[contains(@class,'rowActionsPlaceHolder')]/span/span").click();
		driver.findElementByXPath("//a[@title='Delete']").click();
		driver.findElementByXPath("//span[text()='Delete']").click();
		WebElement message = driver.findElementByXPath("//span[contains(@class,'toastMessage')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'toastMessage')]")));
		String output = message.getText();
		String[] split = output.split(" ");
		System.out.println("The individual "+split[1]+ " is deleted successfully");

	}

}
