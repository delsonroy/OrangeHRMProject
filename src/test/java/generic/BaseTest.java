package generic;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


@Listeners(TestListener.class)
public class BaseTest {
	
	//.............Global variables...................
	
	public ExtentTest extentTest;
	public  WebDriver driver;
	public WebDriverWait wait;
	public WebDriver orginal_driver;
	public static ExtentReports reports; //Making it static sice we need only once
	
	//.............B4 and After Suite for ExtentReport....................
	
	@BeforeSuite //Execute only once
	public void intReport()
	{
		 reports = new ExtentReports();
		
		ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
		reports.attachReporter(spark);
	}
	
	@AfterSuite // Execute only once of the lifeTime of Framework
	public void generateReport()
	{
		reports.flush();
		
	}
	
	@Parameters({"grid","gridURL","browser", "appURL", "ITO", "ETO"})
	@BeforeMethod //BeforeMethod
	
	//.............Before method....................
	public void preCondition(
			 				 @Optional ("no")String grid,
			 				 @Optional ("https://192.168.111.1")String gridURL,
							 @Optional ("chrome")String browser,
							 @Optional ("https://opensource-demo.orangehrmlive.com/")String appURL,
							 @Optional ("10")String ITO, 
							 @Optional ("9")String ETO,Method method ) throws Exception   //Method Ends here
					
	{
		Reporter.log("This is Before method...", true);
		String testName=  method.getName();
		
		extentTest = reports.createTest(testName); // Since we need to create this before EveryTest we are keeping B4 Test
		//Repeating step, this happens b4 wvery test. 
		
		// createTest("ValidLogin") This is the name in the extenReport
		
		
		
		//.............Execution in Remote System....................
		if(grid.equalsIgnoreCase("yes"))
		{
				extentTest.info("Execution in Remote system");
			    URL url = new URL(gridURL);
				if(browser.equalsIgnoreCase("chrome"))
				{
				
					ChromeOptions options = new ChromeOptions();
					orginal_driver = new RemoteWebDriver(url, options);
				}
				else
				{
					FirefoxOptions options = new FirefoxOptions();
					orginal_driver = new RemoteWebDriver(url, options);
				}
			
		}
		//.............Execution in Locl Browser....................
		else {
			
			
			    extentTest.info("Execution in local Browser");
				if(browser.equalsIgnoreCase("chrome"))
				{
					extentTest.info("Browser is Chrome");
					orginal_driver = new ChromeDriver();
				}
				else
				{
					extentTest.info("Browser is fireFox");
					orginal_driver= new FirefoxDriver();
				}
			}
		extentTest.info("Enter the url "+appURL);
		orginal_driver.get(appURL);
		extentTest.info("Maximize the browser");
		orginal_driver.manage().window().maximize();
		extentTest.info("Setting the ITO 10 sec");
		orginal_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(ITO)));
		extentTest.info("Setting the ETO 5 sec");
		wait=new WebDriverWait(orginal_driver, Duration.ofSeconds(Integer.valueOf(ETO)));		
		//.............Decorator....................
	
		EventFiringDecorator<WebDriver> decorator= new 	EventFiringDecorator<WebDriver>(new SeleniumListener(extentTest));
		driver = decorator.decorate(orginal_driver); // Our driver has become Decorator
	}
	
	@AfterMethod  ////.............After Method....................
	public void postCondition(ITestResult result) throws Exception
	{
		String name=result.getName();
		System.out.println(name);
		int status=result.getStatus();
		if(status==2)
		{
			
			//.............Screenshot....................
      	String timeStamp=Util.timeStamp();		
      	String imgPath = "./images/"+name+"=="+"-"+timeStamp+".png";
	    ScreenShots.takeCompletePageScreenShot(driver,imgPath, 1);
	    String message=result.getThrowable().getMessage(); // This messge comes from the TestNG
	    imgPath = "../images/"+name+"=="+"-"+timeStamp+".png"; // This path is for the ExtenReport
	    extentTest.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
		
		}
			
		extentTest.info("Closing the session");
		driver.quit();		
	}
}
