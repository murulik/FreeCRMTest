package com.crm.qa.testcases;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactPageTest extends TestBase {
	
	HomePage homePage;
	LoginPage loginPage;
	ContactPage contactPage;
	TestUtil testUtil;
	String sheetName = "contacts";
	public ContactPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactPage = new ContactPage();
		contactPage = contactPage.gotoContactPage();		
	}
	
	@Test
	public void verifyContactPageTitle() {
		Assert.assertTrue(contactPage.verifyContactsLandingPage());
	}
	
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(dataProvider="getContactsTestData")
	public void createNewContactTest(String fname, String lname) throws InterruptedException {
		String createdName=contactPage.createNewContact(fname, lname);
		Assert.assertEquals(createdName, fname +" "+ lname);		
	}
	
	@Test
	public void selectContactTest() {
		contactPage.selectContactByName("Testfirst Testlast");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
