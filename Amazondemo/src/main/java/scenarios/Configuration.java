package scenarios;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Configuration {

	WebDriver driver;

	ExtentReports report;
	ExtentTest logger;

	@BeforeTest
	public void setReport() {

	
		report = new ExtentReports("C:\\Users\\HP\\Desktop\\Amazondemo\\Amazondemo\\Screenshot\\report.html");
	}

	
	@AfterTest
	public void closeReporter() throws InterruptedException {
		report.endTest(logger);

		report.flush();
		
	}

	@BeforeMethod
	public void setupBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
				
		// created a report
		// logger = report.startTest("VerifyBlogTitle");

	}

	@AfterMethod
	public void closetheBrowser() throws InterruptedException {
     System.out.println("completed");
     Thread.sleep(3000);
		driver.quit();

	}
}
