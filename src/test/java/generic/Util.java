package generic;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;

public class Util {
	
	public static String timeStamp()
	{
		SimpleDateFormat date= new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String timeStamp=date.format(new Date());
		return timeStamp;
	}
	
	public static String getLocatorDetais(By by)
	{
		String msg="";
		try
		{
	        String FQCN=by.getClass().getCanonicalName();
			String locatorName=FQCN.split("By")[2];
			
			//The above lines provide me the locator	
			Field[] allF = by.getClass().getDeclaredFields();
			allF[0].setAccessible(true);
			String locatorvalue=allF[0].get(by).toString();
			msg=locatorName+" as "+ locatorvalue;
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		return msg;		
	}

	public static String getProperty(String path, String key)
	{
		
		String value=""; 
		try {
		Properties p = new Properties();
		p.load(new FileInputStream(path));
		value=p.getProperty(key);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return value;
	}
}
