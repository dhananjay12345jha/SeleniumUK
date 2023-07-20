package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;


public class MySavedCollectionPage extends PageObject
{

	private Logger log = LogManager.getLogger(MySavedCollectionPage.class.getName());
	private static final By COLLECTION_LOCATIONS = By.className("account-card");

	public boolean getCollectionLocation()
	{
		waitForAndGetElement(webDriver, COLLECTION_LOCATIONS, DEFAULT_TIMEOUT);
		if(!getWebDriver().findElements(COLLECTION_LOCATIONS).isEmpty()){
			return true;
		}
		return false;
	}
}
