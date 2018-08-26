package com.crm.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//td[contains(text(),'user: m k')]")
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[text()='Upgrade your account' and @style='color:#E74C3C;']")
	WebElement upgradeAcct;
	
	@FindBy(how=How.XPATH, using="//select[@name='search_target']//option")
	List<WebElement> searchTargetList;
	
	TestUtil testUtil = new TestUtil();
	public HomePage() {
		PageFactory.initElements(driver, this);
		
	}
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public List<String> getSearchTargetList() {
		
		
		List<String> searchDDValues = new ArrayList<String>();
		System.out.println(searchTargetList.size());
		for (WebElement dd: searchTargetList) {
			System.out.println(dd);
			searchDDValues.add(dd.getText());
		}
				
		return searchDDValues;		
	}
	
	public String verifyUpgradeLinkText() {
		return upgradeAcct.getText();
	}
	
}
