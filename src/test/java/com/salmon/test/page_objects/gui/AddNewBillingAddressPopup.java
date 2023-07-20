package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gates on 20/03/18.
 */
public class AddNewBillingAddressPopup extends PageObject
{
	private static final Logger LOG = LoggerFactory.getLogger(AddNewBillingAddressPopup.class);
	private static final By ENTER_ADDRESS_MANUALLY_LINK = By.linkText("Enter address manually");
	private static final By billingAddressTileSelector = By.xpath("//select[@name='titleCode']");
	private static final By FIRST_NAME = By.id("firstName");
	private static final By LAST_NAME = By.id("lastName");
	private static final By ADDRESS_LINE_1 = By.name("line1");
	private static final By ADDRESS_LINE_2 = By.name("line2");
	private static final By TOWN = By.name("town");
	private static final By COUNTY = By.name("county");
	private static final By POSTAL_CODE = By.name("postalCode");
	private static final By BILLING_USE_THIS_ADDRESS = By.xpath("//*[@name='useAddress']");
	private static final By ADDRESS_FINDER_INPUT = By.id("addressLookup");
	private static final By ADDRESS_LOOKUP_RESULTS = By.cssSelector("address-lookup__results-list-item-link");


	public void updateBillingAddress(String postCode)
	{
		new Select(returnAvailableElement(billingAddressTileSelector)).selectByVisibleText("Mr.");
		returnAvailableElement(FIRST_NAME).clear();
		returnAvailableElement(FIRST_NAME).sendKeys("firstName");
		clickBillingAddressTitle();
		returnAvailableElement(LAST_NAME).clear();
		returnAvailableElement(LAST_NAME).sendKeys("lastName");
		returnAvailableElement(ENTER_ADDRESS_MANUALLY_LINK).click();
		returnAvailableElement(ADDRESS_LINE_1).sendKeys("41 Basinghall Street");
		clickBillingAddressTitle();
		returnAvailableElement(ADDRESS_LINE_2).sendKeys("City Tower");
		clickBillingAddressTitle();
		returnAvailableElement(TOWN).sendKeys("London");
		clickBillingAddressTitle();
		returnAvailableElement(COUNTY).sendKeys("London");
		clickBillingAddressTitle();
		returnAvailableElement(POSTAL_CODE).sendKeys(postCode);
		clickBillingAddressTitle();
		clickByJavaScriptExecutor(returnAvailableElement(BILLING_USE_THIS_ADDRESS));
		waitForPageLoad();
	}

	public void clickBillingAddressTitle()
	{
		try
		{
			getWebDriver().findElements(By.xpath("//*[contains(@class,'modal__content--card-mobile')]/h3")).get(1).click();
		}
		catch (Exception e)
		{
			LOG.info("Not able to click on billing title");
		}
	}

	public void updateBillingAddress(String addressLine1, String addressLine2, String town, String county, String postalCode)
	{
		waitForElementVisible(LAST_NAME, 10);
		webDriver.findElement(LAST_NAME).clear();
		webDriver.findElement(LAST_NAME).sendKeys("lastName");
		webDriver.findElement(ENTER_ADDRESS_MANUALLY_LINK).click();
		webDriver.findElement(ADDRESS_LINE_1).sendKeys(addressLine1);
		webDriver.findElement(ADDRESS_LINE_2).sendKeys(addressLine2);
		webDriver.findElement(TOWN).sendKeys(town);
		webDriver.findElement(COUNTY).sendKeys(county);
		webDriver.findElement(POSTAL_CODE).sendKeys(postalCode);
		clickByJavaScriptExecutor(webDriver.findElement(BILLING_USE_THIS_ADDRESS));
	}
}
