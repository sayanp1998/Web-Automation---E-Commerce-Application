package Selenium.FrameworkDesign;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;

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
import org.openqa.selenium.JavascriptExecutor;

import Selenium.FrameworkDesign.pageObjects.landingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.jar.asm.Handle;

public class standAloneTest {

	public static void main(String[] args) {
		
		String productName = "IPHONE 13 PRO";
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.id("userEmail")).sendKeys("psayan10@yahoo.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sayan@123");
		driver.findElement(By.id("login")).click();
		
		//Lading time for the products to load
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		
		WebElement prod = products.stream().filter(item -> item.findElement(By.cssSelector("b")).
				getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//Wait for message and loading to complete
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); //This is taking so much time
		
		//now go to the cart and check the proper added item is present or not
		// Use stream() and Assertions to validate
        List<WebElement> cartProduct = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProduct.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
        //anyMatch is used instead of Filter because we need to validate item present or not(Condition matches or not)
        Assert.assertTrue(match);
		
        driver.findElement(By.cssSelector(".totalRow button")).click();
        
        //Go to payment page
        //driver.findElement(By.cssSelector(".field .input.txt.text-validated")).sendKeys("2258 2334 1193 4757");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".field .input.txt.text-validated")));
        
        WebElement dropdown =  driver.findElement(By.xpath("(//select[@class='input ddl'])[1]"));
        Select month = new Select(dropdown);
        month.selectByVisibleText("10");
        
        WebElement yearDropdown = driver.findElement(By.xpath("(//select[@class='input ddl'])[2]"));
        Select year = new Select(yearDropdown);
        year.selectByVisibleText("26");
        
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("394");
        driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Raju Hatela");
        
        //Handle dynamic dropdown -
//        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
        //section[@class='ta-results list-group ng-star-inserted'] //button
        
//        List<WebElement> options = driver.findElements
//    		  (By.xpath("//section[@class='ta-results list-group ng-star-inserted'] //button"));
//        for(WebElement option:options) {
//        	if(option.getText().equalsIgnoreCase("India")) {
//        		option.click();
//        		break;
//        	}
//        }
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ta-results")));

        //UsernameAndPassword actionAndPassword Class to Handle dynamicHandle dropdown
        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
               
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        
        //Problem occure
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", submit);
        
        String confirmMessage =  driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

}
