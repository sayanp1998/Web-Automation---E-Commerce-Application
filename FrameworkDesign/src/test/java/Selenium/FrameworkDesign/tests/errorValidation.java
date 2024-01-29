package Selenium.FrameworkDesign.tests;

import Selenium.FrameworkDesign.pageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import testComponents.BaseTest;
import testComponents.IretryListener;

import java.io.IOException;
import java.util.List;

public class errorValidation extends BaseTest {

    @Test(groups = {"errorHandling"}, retryAnalyzer = IretryListener.class) //retry analyzer once
    public void LoginErrorValidation() throws IOException, InterruptedException {
        ProductCatelogue productCatelogue = LandingPage.loginApplication("psayan22@yahoo.com", "Sayan@22");
        //Error Message
        // cssSelector --> .ngx-toastr (Go to landing page for webelement placement)
        Assert.assertEquals("Incorrect email or password.", LandingPage.getErrorMessage());

    }

    @Test(groups = {"errorHandling"})
    public void ProductErrorValidation() throws IOException, InterruptedException {

        String productName = "IPHONE 13 PRO";
        String countryName = "India";
        ProductCatelogue productCatelogue = LandingPage.loginApplication("psayan10@yahoo.com", "Sayan@123");
        //Loading time for the products to load
        List<WebElement> products = productCatelogue.getProductList();
        productCatelogue.addToCartProduct(productName); //product added to cart
        cartPage cart = productCatelogue.cartButton();
        boolean match = cart.validateProductDisplay("ZARA COAT 3");
        //anyMatch is used instead of Filter because we need to validate item present or not(Condition matches or not)
        Assert.assertFalse(match);
    }

}
