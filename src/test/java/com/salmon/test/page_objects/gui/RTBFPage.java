package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

/**
 * @author gokhan.ates
 */
public class RTBFPage extends PageObject {

    private Logger log = LogManager.getLogger(RTBFPage.class.getName());
    private static final By COUNTRY = By.cssSelector(".input.input-0.rtbf-country-input");
    private static final By TITLE = By.id("titleCode");
    private static final By FIRST_NAME = By.id("firstName");
    private static final By LAST_NAME = By.id("lastName");
    private static final By ADDRESS_LINE_1 = By.id("line1");
    private static final By ADDRESS_LINE_2 = By.id("line2");
    private static final By TOWN = By.id("town");
    private static final By COUNTY = By.id("county");
    private static final By POSTCODE = By.id("postalCode");
    private static final By EMAIL_ADDRESS = By.id("email");
    private static final By PHONE_NUMBER = By.id("phoneNumber");
    private static final By IM_NOT_A_ROBOT = By.cssSelector(".rc-anchor.rc-anchor-compact.rc-anchor-light");
    private static final By SUBMIT_REQUEST = By.cssSelector(".button.button--secondary-black.button--full-width.forget-me__button");
    private static final By CHECK_YOUR_INBOX_TITLE = By.cssSelector(".forget-me-request__title.forget-me-request__title--email-icon");

    public void completeDetails(){
    	  pause(3000);
    	  scrollElementIntoView(FIRST_NAME);
        waitForAndGetElement(FIRST_NAME,DEFAULT_TIMEOUT).sendKeys("JOE");
        selectFromDropDown(webDriver.findElement(TITLE), 1);
        webDriver.findElement(LAST_NAME).sendKeys("Bloggs");
        webDriver.findElement(ADDRESS_LINE_1).sendKeys("Address 1");
        webDriver.findElement(ADDRESS_LINE_2).sendKeys("Address 2");
        webDriver.findElement(TOWN).sendKeys("London");
        webDriver.findElement(COUNTY).sendKeys("London");
        webDriver.findElement(POSTCODE).sendKeys("EN66AA");
        webDriver.findElement(PHONE_NUMBER).sendKeys("0123456789");
        webDriver.findElement(EMAIL_ADDRESS).sendKeys("joe.bloggs@salmon.co.uk");
        webDriver.switchTo().frame(1);
		  if(webDriver.findElements(IM_NOT_A_ROBOT).size()!=0){
			  webDriver.findElement(IM_NOT_A_ROBOT).click();
		  }
	     webDriver.switchTo().defaultContent();
        webDriver.findElement(SUBMIT_REQUEST).click();
    }

    public String getCheckYourInboxTitle(){
        pause(1500);
        waitForAndGetElement(CHECK_YOUR_INBOX_TITLE,10);
        return waitForAndGetElement(CHECK_YOUR_INBOX_TITLE,10).getText();
    }

}
