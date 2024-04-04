package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashBoardPage {
  //POM class name as same as page name ends with Page
	
	
	@FindBy(xpath = "//h6[text()='Dashboard']")
	private WebElement dashboard;

	public DashBoardPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Always try to return the boolean values to the calling methods for the ease of programming
	//When you develop any method always use the boolean value. 
	
	
	public boolean veyfyDashboardPageisDisplayed(WebDriverWait wait) 
	{
	       // Wait until Dashboard is displayed
		try {
			wait.until(ExpectedConditions.visibilityOf(dashboard));
			System.out.println("Dashboard is displayed");
			return true; // Dashboard is displayed for using it in the assert in the TestNG
			}
	
	
			catch (Exception e) 
			{
			System.out.println("Dashboard is not displayed");
			return false; // Dashboard is not Displayed // To use it in the TestNG
			}
		
	}
}
