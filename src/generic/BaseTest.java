package generic;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

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
	
//	//.............Global variables...................
	public static final String DEFAULT_URL="https://opensource-demo.orangehrmlive.com";
	public static final String DEFAULT_GRID="no";
	public static final String DEFAULT_PPT_FILE="qa.properties";
	public static String XL_PATH;
	public static final String DEFAULT_HTMLPATH="report/Spark.html";
	

	
	public static ExtentReports reports; //Making it static sice we need only once
	public ExtentTest extentTest;
	
	public WebDriver orginal_driver;
	public  WebDriver driver;
	public WebDriverWait wait;
	

	
	//.............B4 and After Suite for ExtentReport....................
	
	@Parameters({"htmlpath"})
	@BeforeSuite //Execute only once
	public void intReport(@Optional(DEFAULT_HTMLPATH) String htmlPath)
	{
		 reports = new ExtentReports();
		
		ExtentSparkReporter spark = new ExtentSparkReporter(htmlPath);
		reports.attachReporter(spark);
	}
	
	@AfterSuite // Execute only once of the lifeTime of Framework
	public void generateReport()
	{
		reports.flush();
		
	}
	
	@Parameters({"grid","gridURL","appURL","pptfile"})
	@BeforeMethod //BeforeMethod
	
	//.............Before method....................
	public void preCondition(
			 				 @Optional (DEFAULT_GRID)String grid,
			 				 @Optional ("https://192.168.111.1")String gridURL,
							 @Optional (DEFAULT_URL)String appURL,
							 @Optional (DEFAULT_PPT_FILE)String pptfile,
							 Method method ) throws Exception   //Method Ends here
					
	{
		
        String testName=  method.getName();
		extentTest = reports.createTest(testName);
		
		String browser=Util.getProperty(pptfile, "browser");
		extentTest.info("Browser is "+browser);
		String sITO=Util.getProperty(pptfile, "ITO");
		String sETO=Util.getProperty(pptfile, "ETO");
		Long lITO=Long.parseLong(sITO);
		extentTest.info("ITO is "+lITO);
		Long lETO=Long.parseLong(sETO);
		extentTest.info("ETO is "+lETO);
		XL_PATH=Util.getProperty(pptfile, "XLPATH");
		extentTest.info("Excel path is "+XL_PATH);
		
		
		
		
		// Since we need to create this before EveryTest we are keeping B4 Test
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
		extentTest.info("Setting the ITO: "+lITO);
		orginal_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(lITO));
		extentTest.info("Setting the ETO: "+lETO);
		wait=new WebDriverWait(orginal_driver, Duration.ofSeconds(lETO));
		//.............Decorator....................
	
		EventFiringDecorator<WebDriver> decorator= new 	EventFiringDecorator<WebDriver>(new SeleniumListener(extentTest));
		driver = decorator.decorate(orginal_driver); // Our driver has become Decorator
	}
	
	@AfterMethod  ////.............After Method....................
	public void postCondition(ITestResult result) throws Exception
	{
		List<String> output = Reporter.getOutput(result);
		for (String msg : output) {
			
			extentTest.info(msg);
		}
		
		
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
