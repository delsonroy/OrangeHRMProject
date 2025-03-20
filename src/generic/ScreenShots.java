package generic;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

public class ScreenShots {

	public static void takeElementScreenshot(WebElement element, String path) {
		try {
			File srcfile = element.getScreenshotAs(OutputType.FILE);
			File destFile = new File(path);

			FileUtils.copyFile(srcfile, destFile);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public static void takePageScreenShot(WebDriver driver, String path) {
		try {
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File src = screenshot.getScreenshotAs(OutputType.FILE);
			File dest = new File(path);

			FileUtils.copyFile(src, dest);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void takeCompletePageScreenShot(WebDriver driver, String path, int delayinSeconds) {
		try {

			AShot shot = new AShot();
			ShootingStrategy s = ShootingStrategies.viewportPasting(delayinSeconds * 1000);
			Screenshot screenshot = shot.shootingStrategy(s).takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(), "png", new File(path));

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}

	}

	public static void takeDesktopScreenshot(String path) {
		try {
			Robot robot = new Robot();
			Dimension desktopsize = Toolkit.getDefaultToolkit().getScreenSize();
			//System.out.println(desktopsize);
			BufferedImage img = robot.createScreenCapture(new Rectangle(desktopsize));
			ImageIO.write(img, "png", new File(path));

		}

		catch (Exception e) {

			System.err.println(e.getMessage());
		}

	}

}
