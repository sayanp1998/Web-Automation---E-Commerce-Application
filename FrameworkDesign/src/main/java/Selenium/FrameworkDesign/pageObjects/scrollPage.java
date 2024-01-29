package Selenium.FrameworkDesign.pageObjects;

import Selenium.abstructComponents.abstructComponents;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class scrollPage extends abstructComponents {

    WebDriver driver;

    @FindBy(css = ".action__submit")
    WebElement placeOrderbtn;

    public scrollPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    public void checkOutpageScroll(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");
        //js.executeScript("document.querySelector(placeOrderbtn).scrollTop=5000");
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
    }
}
