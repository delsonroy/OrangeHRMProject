package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	        @FindBy (xpath="//input[@name='username']")  
	        private WebElement unTB;
	        
	        @FindBy (name="password")  
	        private WebElement pwTB;
	        
	        @FindBy (xpath="//button[@type='submit']")  
	        private WebElement loginBtn;
	        
	        @FindBy (xpath="//p[text()='Invalid credentials']")
	        private WebElement errMsg;
	        
	        
	        public LoginPage(WebDriver driver)
	        {
	        	PageFactory.initElements(driver, this);
	        }
	        
	        public void setUsername(String un)
	        {
	        
	        	unTB.sendKeys(un);
	        }
	        public void setPassword(String pw)
	        {
	        
	        	pwTB.sendKeys(pw);
	        }
	        public void clickLoginBtn()
	        {
	        
	        	loginBtn.click();
	        }
	        
	        public boolean veyfyErrorMessageisDisplayed(WebDriverWait wait) 
	    	{
	    	       // Wait until Dashboard is displayed
	    		try {
	    			wait.until(ExpectedConditions.visibilityOf(errMsg));
	    			System.out.println("Error message is displayed");
	    			return true; // Dashboard is displayed for using it in the assert in the TestNG
	    			}
	    	
	    	
	    			catch (Exception e) 
	    			{
	    			System.out.println("Error message is not displayed");
	    			return false; // Dashboard is not Displayed // To use it in the TestNG
	    			}
	    		
	    	}
	
	     
}
