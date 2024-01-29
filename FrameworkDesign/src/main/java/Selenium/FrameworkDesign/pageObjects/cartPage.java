package Selenium.FrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.abstructComponents.abstructComponents;

public class cartPage extends abstructComponents{
	WebDriver driver;
	
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}

	//now go to the cart and check the proper added item is present or not
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProduct;

    @FindBy(css = ".totalRow button")
    WebElement checkOutbtn;
    
    	
	public Boolean validateProductDisplay(String productName) {
		//now go to the cart and check the proper added item is present or not
		// Use stream() and Assertions to validate
		boolean match = cartProduct.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goTocheckOut() {
		checkOutbtn.click();
		CheckOutPage checkoutpg = new CheckOutPage(driver);
		return checkoutpg;
	}    

}
