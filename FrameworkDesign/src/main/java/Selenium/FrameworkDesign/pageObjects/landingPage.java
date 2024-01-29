package Selenium.FrameworkDesign.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.abstructComponents.abstructComponents;

public class landingPage extends abstructComponents {
	WebDriver driver;

	//create constructor for accessing the chrome webdrivers
	public landingPage(WebDriver driver) { 
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPwd;
	
	@FindBy(id="login")
	WebElement clkLogin;

	@FindBy(css = ".ngx-toastr") //.ngx-toastr //"[class*='.ngx-toastr']"
	WebElement errorMsg;
	
	//Create a Action Method
	public ProductCatelogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPwd.sendKeys(password);
		clkLogin.click();
		
		//Object created for product catelogue class
		ProductCatelogue productCatelogue = new ProductCatelogue(driver);
		return productCatelogue;
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

	public String getErrorMessage(){
		waitWebElemntToAppear(errorMsg);
		return errorMsg.getText();

	}
	
	

}
