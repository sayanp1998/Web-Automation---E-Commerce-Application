package Selenium.FrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.abstructComponents.abstructComponents;
import net.bytebuddy.asm.Advice.This;

public class ProductCatelogue extends abstructComponents {
	
	WebDriver driver;

	//create constructor for accessing the chrome webdrivers
	public ProductCatelogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); //Create pagefactory to access
	}
	
	// List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
	
	//PageFactory
	@FindBy(css =".col-lg-4")
	List<WebElement> products;
	
	By productFindBy = By.cssSelector(".col-lg-4"); 
	By addToCartButton = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	// driver.findElement(By.cssSelector(".ng-animating"))
	@FindBy(css = ".ng-animating")
	WebElement spinn;


	//Create a Action Method
	public List<WebElement> getProductList() {
		waitElemntToAppear(productFindBy);
		return products;	
	}
	
	public WebElement getProductbyNames(String productName) { //iteratiing the list of web element
		WebElement prod = products.stream().filter(item -> item.findElement(By.cssSelector("b")).
				getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addToCartProduct(String productName) throws InterruptedException {
		WebElement prod = getProductbyNames(productName);
		prod.findElement(addToCartButton).click(); // Add to cart
		waitElemntToAppear(toastMessage);
		waitWebElemntToDisAppear(spinn);
	}
	
	
		

}
