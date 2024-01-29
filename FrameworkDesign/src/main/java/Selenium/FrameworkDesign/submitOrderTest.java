package Selenium.FrameworkDesign;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;

import Selenium.FrameworkDesign.pageObjects.*;
import Selenium.FrameworkDesign.pageObjects.ProductCatelogue;
import org.openqa.selenium.By;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v118.domstorage.model.Item;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.jar.asm.Handle;

public class submitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "IPHONE 13 PRO";
		String countryName = "India";
//		String monthVal="10";
//		String yearVal="26";
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		landingPage landingPage = new landingPage(driver); //create object od landing page class
		
		landingPage.goTo(); //page URL
		ProductCatelogue productCatelogue = landingPage.loginApplication("psayan10@yahoo.com", "Sayan@123");

		//Lading time for the products to load
		List<WebElement> products = productCatelogue.getProductList();
		productCatelogue.addToCartProduct(productName); //product added to cart
		
		cartPage cart = productCatelogue.cartButton();

		boolean match = cart.validateProductDisplay(productName);
		//anyMatch is used instead of Filter because we need to validate item present or not(Condition matches or not)
        Assert.assertTrue(match);
        
        CheckOutPage checkoutpg = cart.goTocheckOut();
        
        //Go to payment page 
        checkoutpg.selectCountry(countryName);
        
        confirmationPage confirmPg = checkoutpg.submitbutton();
        
        String confirmMessage =  confirmPg.getConfimation();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();
        		
	}

}
