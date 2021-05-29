package Bootcamp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditIndividuals {

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
		WebElement IndividualTab = driver.findElementByXPath("//a[@title='Individuals']/span[text()='Individuals']");
		driver.executeScript("arguments[0].click;", IndividualTab);
		Thread.sleep(3000);
		driver.findElementByXPath("//input[@id='input-116']").sendKeys("Kumar");
		Thread.sleep(3000);
		driver.findElementByXPath("//button[@name='refreshButton']").click();
		Thread.sleep(3000);
		WebElement modifiedDate = driver.findElementByXPath("//span[@title='Last Modified Date']");
		driver.executeScript("arguments[0].click();", modifiedDate);
		Thread.sleep(3000);
		driver.findElementByXPath("//a[contains(@class,'rowActionsPlaceHolder')]/span/span").click();
		driver.findElementByXPath("//a[@title='Edit']").click();
		driver.findElementByXPath("(//a[@class='select'])[1]").click();
		List<WebElement> salutationList = driver.findElementsByXPath("//a[@role='menuitemradio']");
		for (WebElement ele : salutationList) {
			String text = ele.getText();
			if(text.contains("Mr.")) {
				ele.click();
			}
		}
		driver.findElementByXPath("//input[contains(@class,'firstName')]").sendKeys("Ganesh");
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		Thread.sleep(5000);
		driver.findElementByXPath("//a[@title='Ganesh kumar']").click();
		String text = driver.findElementByXPath("//span[@class='uiOutputText']").getText();
		String[] split = text.split(" ");
		System.out.println("First name: " +split[1]);
		
		

	}

}
