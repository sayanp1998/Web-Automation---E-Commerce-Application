package Selenium.FrameworkDesign.pageObjects;

import Selenium.abstructComponents.abstructComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends abstructComponents {

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //now go to the Order Page and check the all the added items and validate orders are visible or not
    @FindBy(xpath = "//tr/td[2]")
    List<WebElement> productNames;

    @FindBy(css = ".totalRow button")
    WebElement checkOutbtn;

    public Boolean validateOrderDisplay(String productName) {
        //now go to the cart and check the proper added item is present or not
        // Use stream() and Assertions to validate
        boolean match = productNames.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
        return match;
    }

}
