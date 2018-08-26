package com.crm.qa.testcases;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	HomePage homePage;
	LoginPage loginPage;
	TestUtil testUtil;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void homePageTitleTest() {
		String title = homePage.verifyHomePageTitle();
		Assert.assertEquals(title, "CRMPRO","Title text did not match");
	}
	
	@Test
	public void upgradeAccountTextTest() {
		testUtil.switchToFrame();
		String upgradeText = homePage.verifyUpgradeLinkText();
		Assert.assertEquals(upgradeText, "Upgrade your account");
	}
	
	@Test
	public void searchTargetListTest() {
		testUtil.switchToFrame();
		List<String> expSearchTargetList = new ArrayList<String>();
		expSearchTargetList.add("CRM");
		expSearchTargetList.add("Web");
		List<String> srcTrgtList = homePage.getSearchTargetList();
		Assert.assertEquals(srcTrgtList, expSearchTargetList );		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
