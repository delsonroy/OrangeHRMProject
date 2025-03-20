package generic;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	public void onTestFailure(ITestResult result) {

		String name = result.getName();
		System.out.println(name);
		String timeStamp = Util.timeStamp();

		ScreenShots.takeDesktopScreenshot("./images/" + name + "-" + timeStamp + ".png");

	}

}
