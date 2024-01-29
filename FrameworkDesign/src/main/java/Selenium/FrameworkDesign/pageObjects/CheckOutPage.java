package Selenium.FrameworkDesign.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import Selenium.abstructComponents.abstructComponents;

public class CheckOutPage extends abstructComponents {
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By waitLoc = By.cssSelector(".field .input.txt.text-validated");
	
	@FindBy(xpath = "(//select[@class='input ddl'])[1]")
	Select month;
    //month.selectByVisibleText("10");
	@FindBy(xpath = "(//select[@class='input ddl'])[2]")
	Select year;
    //year.selectByVisibleText("26");
	
	@FindBy(xpath = "(//input[@type='text'])[2]")
	WebElement cvv;
	
	@FindBy(xpath = "(//input[@type='text'])[3]")
	WebElement nameonCard;
	
	//***************************************//
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement CountryName;
	
	By countryNameWait = By.cssSelector(".ta-results");
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css = ".action__submit")
	WebElement submitBtn;
	
	public void selectCountry(String countryName ) {
		//waitWebElemntToAppear(waitLoc);
//		month.selectByVisibleText(monthVal);
//		year.selectByVisibleText(yearVal);
//		cvv.sendKeys("394");
//		nameonCard.sendKeys("Raju Hatela");
//		
		Actions a = new Actions(driver);
		a.sendKeys(CountryName, countryName).build().perform();
		
		waitElemntToAppear(countryNameWait);
		selectCountry.click();
		
	}
	public confirmationPage submitbutton() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submitBtn);
//		scrollPage sp  = new scrollPage(driver);
//		sp.checkOutpageScroll();
//		Actions b = new Actions(driver);
//        b.moveToElement(submitBtn).click().build().perform();

//		WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", submit);
		return new confirmationPage(driver);
		//return confirmPg;
		
	}
    
}
