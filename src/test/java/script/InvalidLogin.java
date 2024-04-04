package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import page.LoginPage;

public class InvalidLogin extends BaseTest{
	
	
	
	@Test(priority = 2)
	public void testInvalidLogin()
	{
		//Takda data from Excel
		String un=Excel.getData("./data/Book1.xlsx", "InvalidLogin", 1, 0);
		String pw=Excel.getData("./data/Book1.xlsx", "InvalidLogin", 1, 1);
		System.out.println(un);
		System.out.println(pw);
		//Every loginPage takes driver as an argumen
		LoginPage page = new LoginPage(driver);
		//Enter Invalid un
		page.setUsername(un);
		//Enter invalid pw
		page.setPassword(pw);
		//Click lgnbtn
		page.clickLoginBtn();
		//ErrorMessageshould be displayed
		boolean result = page.veyfyErrorMessageisDisplayed(wait);
		
		Assert.assertTrue(result);
		
	}

}
