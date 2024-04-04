package script;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import generic.TestListener;
import page.DashBoardPage;
import page.LoginPage;


public class ValidLogin extends BaseTest {

	@Test(priority = 1)
	public void testValidLogin() {
		// Creat the POM class Object
		String un=Excel.getData("./data/Book1.xlsx", "ValidLogin", 1, 0);
		String pw=Excel.getData("./data/Book1.xlsx", "ValidLogin", 1, 1);
		
		System.out.println(un);
		System.out.println(pw);
		LoginPage page = new LoginPage(driver);
		// Test cases

		// Enter valid username
		page.setUsername(un);
		// Enter valid password
		page.setPassword(pw);
		// Enter click
		page.clickLoginBtn();
		// verify DashboardPage is Displayed
		DashBoardPage dashboardpage = new DashBoardPage(driver);
		boolean result = dashboardpage.veyfyDashboardPageisDisplayed(wait);
		// We are Expecting True sp
      Assert.assertTrue(result);
		
	}

}
