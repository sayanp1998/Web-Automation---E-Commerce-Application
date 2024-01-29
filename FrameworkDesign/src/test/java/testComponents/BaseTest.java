package testComponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import Selenium.FrameworkDesign.pageObjects.landingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	public WebDriver driver;
	public landingPage LandingPage; // landingPage object created public so that it can be accessed throughout the code
	
	public void robotClass() throws AWTException {
		Robot rb = new Robot();
		rb.mouseMove(0, 0);
		rb.mouseRelease(0);
		rb.mousePress(0);
		rb.keyPress(0);
		rb.keyRelease(0);
	}
	
	public WebDriver initializeDriver() throws IOException {
		//Properties Class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream( System.getProperty("user.dir")
				+ "//src//main//java//Selenium//resources//GlobalData.properties");
		// D:\\Workspace\\Eclipse Workspace\\Data\\FrameworkDesign\\ == "user.dir"
		prop.load(fis);
		//Ternary Operator
		String browserName = System.getProperty("browser")!= null ?
				System.getProperty("browser") : prop.getProperty("browser");

		
		if(browserName.equalsIgnoreCase("chrome")) {
			//Chrome
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("Edge")) {
			//Edge
			System.setProperty("webdriver.edge.driver", "D:\\Workspace\\Eclipse Workspace\\Web Drivers\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Workspace\\Eclipse Workspace\\Web Drivers\\geckodriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public landingPage launchApplication() throws IOException {
		Actions a = new Actions(driver);
		a.moveToElement(null);
		
		
		driver = initializeDriver();
		LandingPage = new landingPage(driver);
		LandingPage.goTo();
		return LandingPage;
		
		

	}
	@AfterMethod(alwaysRun = true)
	public void tearDown(){
		driver.quit();
		
	}

	//Taking this method from dataReader class
	String filePath = System.getProperty("user.dir"+"\\src\\test\\java\\Selenium\\FrameworkDesign\\Data\\PurchaseOrder.json");
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//Read Json and convert it to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		//Now convert Sting value to hashmap
		//To convert to Hashmap from string we need "Jackson Datbiln"

		//now create a mapper object from a ObjectMapper class
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {});
		return data;
		//Return value type - {{map}, {map1}}
		
		}

	//Take Screenshot utility
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) this.driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	// Jenkins
	//http://localhost:8080/
}
