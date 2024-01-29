package stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.google.common.annotations.VisibleForTesting;

import Selenium.FrameworkDesign.pageObjects.CheckOutPage;
import Selenium.FrameworkDesign.pageObjects.ProductCatelogue;
import Selenium.FrameworkDesign.pageObjects.cartPage;
import Selenium.FrameworkDesign.pageObjects.confirmationPage;
import Selenium.FrameworkDesign.pageObjects.landingPage;
import Selenium.FrameworkDesign.tests.errorValidation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testComponents.BaseTest;

public class stepDefination extends BaseTest {
	
	//Globally
	public landingPage landingPage;
	public ProductCatelogue productCatelogue;
	public cartPage cart;
	public CheckOutPage checkoutpg;
	public confirmationPage confirmPg;
	
	@Given("I landed on the E-commerce Page")
	public void i_landed_on_the_e_commerce_page() throws IOException {
		
		landingPage = launchApplication();
	}
	
	@Given("Logged in with {string} and {string}")
	public void logged_in_with_and(String username, String password) {
	
		productCatelogue = LandingPage.loginApplication(username, password);
	}
	
	@When("Add the {string} into cart")
	public void add_the_into_cart(String productName) throws InterruptedException {
		
		List<WebElement> products = productCatelogue.getProductList();
		productCatelogue.addToCartProduct(productName);
	}
	
	@When ("Checkout {string} and submit the order")
	public void checkout_submit_order(String productName) {
		cart = productCatelogue.cartButton();
		boolean match = cart.validateProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutpg = cart.goTocheckOut();
		checkoutpg.selectCountry("India");
		confirmPg = checkoutpg.submitbutton();
	}
	
	@Then ("{string} message is displayed")
	public void message_display_after_checkout(String string) {
		confirmPg = new confirmationPage(driver);
		String confirmMessage =  confirmPg.getConfimation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
//	//For error messgae validation
//	@Then ("{string} message is displayed")
//	public void errorMessage_validation(String errorMsg) {
//		Assert.assertEquals(errorMsg, LandingPage.getErrorMessage());
//		driver.close();
//	}
	
	//TIDY GHERKIN - Only for ChromeOs

}
