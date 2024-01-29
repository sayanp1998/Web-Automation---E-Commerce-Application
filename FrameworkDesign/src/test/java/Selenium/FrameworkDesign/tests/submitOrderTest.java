package Selenium.FrameworkDesign.tests;

import Selenium.FrameworkDesign.pageObjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import testComponents.BaseTest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class submitOrderTest extends BaseTest{

	//String productName = "IPHONE 13 PRO";
	String countryName = "India";

/*
	@Test(dataProvider = "getData", groups = {"purchaaseOrder"})
	public void submitOrder(String email, String pwd, String productName) throws IOException, InterruptedException {
		productCatelogue productCatelogue = LandingPage.loginApplication(email, pwd);
		List<WebElement> products = productCatelogue.getProductList();
		productCatelogue.addToCartProduct(productName); //product added to cart
		cartPage cart = productCatelogue.cartButton();
		boolean match = cart.validateProductDisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkoutpg = cart.goTocheckOut();
        checkoutpg.selectCountry(countryName);
        confirmationPage confirmPg = checkoutpg.submitbutton();
        String confirmMessage =  confirmPg.getConfimation();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
*/

@Test(dataProvider = "getData", groups = {"purchaseOrder"})
public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

	ProductCatelogue productCatelogue = LandingPage.loginApplication(input.get("email"), input.get("password"));

	List<WebElement> products = productCatelogue.getProductList();
	productCatelogue.addToCartProduct(input.get("productName"));
	
	cartPage cart = productCatelogue.cartButton();
	boolean match = cart.validateProductDisplay(input.get("productName"));
	Assert.assertTrue(match);
	CheckOutPage checkoutpg = cart.goTocheckOut();
	checkoutpg.selectCountry(countryName);
	confirmationPage confirmPg = checkoutpg.submitbutton();
	String confirmMessage =  confirmPg.getConfimation();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

}

	@Test(dependsOnMethods = {"submitOrder"})
	public void 	orderhistoryTest(HashMap<String, String> input){
		//Iphone 13 pro
		ProductCatelogue productCatelogue = LandingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage = productCatelogue.goToOrderPage();
		Assert.assertTrue(orderPage.validateOrderDisplay(input.get("productName")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "//src//test//java//Selenium//FrameworkDesign//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

	//Extent reports

	/*
	@DataProvider
	public Object[][] getData(){
		return new Object[][] {{"psayan10@yahoo.com", "Sayan@123", "IPHONE 13 PRO"}, {"psayan12@yahoo.com", "Sayan@123", "ADIDAS ORIGINAL"}}
	}
	 */

	/*
	//	@DataProvider
	//	public Object[][] getData(){
	//		HashMap<String, String> map = new HashMap<String, String>();
	//		map.put("email", "psayan10@yahoo.com");
	//		map.put("password", "Sayan@123");
	//		map.put("productName", "IPHONE 13 PRO");
	//
	//		HashMap<String, String> map1 = new HashMap<String, String>();
	//		map.put("email", "psayan12@yahoo.com");
	//		map.put("password", "Sayan@123");
	//		map.put("productName", "ADIDAS ORIGINAL");
	//	}

	 */

}
