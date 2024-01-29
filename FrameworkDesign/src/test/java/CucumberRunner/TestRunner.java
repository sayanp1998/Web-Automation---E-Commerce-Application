package CucumberRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/features", glue = {"stepDefinations"},
monochrome = true,  plugin = {"html:target/cucumber.html"}, tags = "@test1")
public class TestRunner extends AbstractTestNGCucumberTests {

}

//@CucumberOptions(features = "src/test/java/features", glue = {"stepDefinations"},
//monochrome = true, plugin = {"html:target/cucumber2.html"})
//public class test2 extends AbstractTestNGCucumberTests{
//	
//}



