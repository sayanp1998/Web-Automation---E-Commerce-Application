package Selenium.abstructComponents;

import java.time.Duration;

import Selenium.FrameworkDesign.pageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium.FrameworkDesign.pageObjects.cartPage;

public class abstructComponents {
	WebDriver driver;
	
	public abstructComponents(WebDriver driver) {
		this.driver=driver; //coming from super(driver) of child classes
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartbtn;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	
	//Utilities mentioned here
	public void waitElemntToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy)); //findBy is By locator
	}

	public void waitWebElemntToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy)); //findBy is By locator
	}

	public void waitWebElemntToDisAppear(WebElement disAppearElement) throws InterruptedException {
		
		//Wait time create issues as it application based, use thred.sleep to get fast execution as of now
		Thread.sleep(2000);
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		// driver.findElement(By.cssSelector(".ng-animating"))
//		wait.until(ExpectedConditions.invisibilityOf(disAppearElement));
	}
	
	public cartPage cartButton() {
		cartbtn.click();
		cartPage cart = new cartPage(driver); //Create object of the class in this method as this method is the initiator for the cart page 
		return cart;
	}

	public OrderPage goToOrderPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver); //Create object of the class in this method as this method is the initiator for the cart page
		return orderPage;
	}

}
