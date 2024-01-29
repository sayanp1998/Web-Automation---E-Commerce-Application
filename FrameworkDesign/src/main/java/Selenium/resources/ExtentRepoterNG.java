package Selenium.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentRepoterNG {

    public static ExtentReports configExtentReports() {
        //ExtentReport //ExtentSparkReporter
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path); //html file created (package 1 created)
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports(); //(package 2 created)
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Sayan Pal");
        return extent;
    }
}
