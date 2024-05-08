package script;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import generic.Util;
import page.LoginPage;

public class InvalidLogin2 extends BaseTest
{
	@DataProvider
	public Iterator<String[]> getData()
	{
		return Util.getDataFromCSVtoIterator("./data/invalid_login.csv");
	}
	@Test(priority = 3,dataProvider = "getData")
	public void testInvalidLogin(String un,String pw)
	{
		
	//		1. enter invalid un
			LoginPage loginPage=new LoginPage(driver);
			loginPage.setUsername(un);
	//		2. enter invalid pw
			loginPage.setPassword(pw);
	//		3. click login button
			loginPage.clickLoginBtn();
	//		4. err msg should be displayed
			boolean result = loginPage.veyfyErrorMessageisDisplayed(wait);
			Assert.assertTrue(result);
		
	}
}
