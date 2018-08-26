package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=10;
	
	public static String TESTDATA_SHEET_PATH="C:\\Users\\Muruli\\Desktop\\freeCRM_Contacts.xlsx";
	static Workbook book;
	static Sheet sheet;
	
	@FindBy(xpath="//div[@class='intercom-borderless-dismiss-button']")
	WebElement intercomClose;
	
	@FindBy(xpath="//div[@class='intercom-borderless-body']")
	WebElement intercomBody;
	
	@FindBy(xpath="//iframe[@class='intercom-borderless-frame']")
	WebElement intercomFrame;
	
	//initializing the page objects
		public TestUtil() {
			PageFactory.initElements(driver, this);
		}
	
	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}
	
	public void checkAndCloseIntercome() {
		if (intercomFrame.isEnabled()) {
			
			
			Actions action = new Actions(driver);			
			action.moveToElement(intercomFrame).perform();
			driver.switchTo().frame(intercomFrame);
			//if(intercomBody.isDisplayed()) {
				intercomClose.click();
			//}
		}		
	}
	
	public static Object[][] getTestData(String sheetName) {
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0; i< sheet.getLastRowNum(); i++) {
			for (int k=0; k<sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i+1).getCell(k).toString();
			}
		}
		return data;
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		
		FileUtils.copyFile(scrFile, new File(currentDir + "\\screenshots\\" + System.currentTimeMillis() + ".png"));
		
		}
}
