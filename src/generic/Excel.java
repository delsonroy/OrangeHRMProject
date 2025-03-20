package generic;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {
	
	
	//WorkbookFactory.create(new FileInputStream(path)). 
	//This line uses Apache POI library to create a Workbook object from the Excel file specified by the path.

	public static String getData(String path, String sheetname, int r, int c) {
		String rowcount = "";
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			rowcount = wb.getSheet(sheetname).getRow(r).getCell(c).toString();
		

		}

		catch (Exception e) {

			e.printStackTrace();

		}
		return rowcount;

	}

	public static int getRowCount(String path, String sheetname) {
		int rc = 0;

		try {

			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			rc = wb.getSheet(sheetname).getLastRowNum();

		} catch (Exception e)

		{
			e.printStackTrace();

		}
		return rc;

	}

	public static int getCellCount(String path, String sheetname, int r) {
		int cc = 0;
		try {

			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			cc = wb.getSheet(sheetname).getRow(r).getLastCellNum();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return cc;

	}

}
