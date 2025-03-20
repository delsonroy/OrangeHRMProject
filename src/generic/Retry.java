package generic;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
		
	public int count=0;
		
	@Override
	public boolean retry(ITestResult result) {
		// Upcasting to BaseTest to have access to the extentTest
		BaseTest baseTest = (BaseTest)result.getInstance();
	
		baseTest.extentTest.info("We are inside retry methd ");	
		baseTest.extentTest.info("The failed method is  "+result.getName());
		
		if(count<1)  //How many times to run
		{
			baseTest.extentTest.skip("Skipping the test and re-running it");
			
			baseTest.reports.removeTest(result.getName()); //we can remove the skipped test and keep only the latest one
			count++;
		    return true; // true-> Re-run
		}
		else
		{
			baseTest.extentTest.warning("Already re-executed it, hence stopping it");
			return false; //false -> don't re run
		}
		
	}
}
