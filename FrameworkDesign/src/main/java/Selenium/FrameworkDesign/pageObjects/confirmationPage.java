package Selenium.FrameworkDesign.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.abstructComponents.abstructComponents;

public class confirmationPage extends abstructComponents {

	WebDriver driver;
	public confirmationPage(WebDriver driver) {
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
	WebElement confimationMessage;
	
	public String getConfimation() {
		 return confimationMessage.getText();
	}


}
