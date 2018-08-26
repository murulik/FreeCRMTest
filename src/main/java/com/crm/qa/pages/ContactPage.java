package com.crm.qa.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class ContactPage extends TestBase {
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactsPage;
	

	@FindBy(xpath="//ul[@class='mlddm']//li//ul//li/a[@title='New Contact']")
	WebElement newContactPage;
	
	@FindBy(xpath="//*[@id='first_name']")
	WebElement firstName;
	
	
	@FindBy(xpath="//input[@name='surname']")
	WebElement lastName;
	
	@FindBy(xpath="//*[@id=\"contactForm\"]/table/tbody/tr[1]/td/input[2]")
	WebElement createContact;
	
	@FindBy(xpath="//*[@id=\"vSummary\"]/table/tbody/tr[1]/td/table/tbody/tr/td[1]")
	WebElement newContactName;
	
	public ContactPage() {
		PageFactory.initElements(driver, this);		
	}
	TestUtil testUtil = new TestUtil();
	public ContactPage gotoContactPage() {
		testUtil.switchToFrame();
		contactsPage.click();
		return new ContactPage();
	}
	
	public boolean verifyContactsLandingPage() {
		return contactsPage.isDisplayed();				
	}
	
	public String createNewContact(String fname, String lname) throws InterruptedException {
		Actions action = new Actions(driver);	
		action.moveToElement(contactsPage).perform();		
		newContactPage.click();
		//Thread.sleep(2000);
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		createContact.click();		
		return newContactName.getText();
	}
	
	public void selectContactByName(String name) {
		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
	}
	
}
