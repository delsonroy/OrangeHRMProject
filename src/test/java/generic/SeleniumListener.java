package generic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import com.aventstack.extentreports.ExtentTest;

/**
 * This method will be called after {@link WebDriver#findElement(By)} is called.
 *
 * @param driver  - decorated WebDriver instance
 * @param locator - locator used to find the element
 * @param result  - found WebElement
 */
public class SeleniumListener implements WebDriverListener {

	// default methods are only allowed inside interFace

	// In the baseTest code, whenever the element is encountered it should print it
	// is finding the element

	// before finding any element "it will print Browser is chrome" using b4
	// findelement

	ExtentTest extentTest;

	public SeleniumListener(ExtentTest extentTest) {

		this.extentTest = extentTest;
	}

	public void beforeFindElement(WebDriver driver, By locator) 
	{
		String msg = Util.getLocatorDetais(locator);
		extentTest.info("Finding the element by using " + msg);
	}

	public void beforeSendKeys(WebElement element, CharSequence... keysToSend)
	{

		extentTest.info("Entering the iput" + keysToSend[0]);

	}

	public void beforeClick(WebElement element) 
	{

		extentTest.info("Clicking on the element");
	}
}
