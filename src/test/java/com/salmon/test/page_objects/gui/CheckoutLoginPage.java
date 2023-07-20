package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;


public class CheckoutLoginPage extends PageObject
{
	private Logger log = LogManager.getLogger(CheckoutLoginPage.class.getName());
	private static final By GUEST_CHECKOUT_EMAIL_FIELD = By.id("email");
	private static final By CHECKOUT_BUTTON = By.xpath("//*[@id='guestForm']//button[@type='submit']");

	public void enterEmailAddressForGuestCheckoutField()
	{
		pause(4000);
		waitForAndGetElement(GUEST_CHECKOUT_EMAIL_FIELD, DEFAULT_TIMEOUT);
		try
		{
			if (!webDriver.findElements(GUEST_CHECKOUT_EMAIL_FIELD).isEmpty())
			{
				webDriver.findElement(GUEST_CHECKOUT_EMAIL_FIELD).click();
				webDriver.findElement(GUEST_CHECKOUT_EMAIL_FIELD).sendKeys(RandomGenerator.randomEmailAddress(5));
			}
		}
		catch (Exception e)
		{
			webDriver.findElement(GUEST_CHECKOUT_EMAIL_FIELD).sendKeys(RandomGenerator.randomEmailAddress(5));
		}
	}

	public void clickGuestCheckoutButton()
	{
		waitForAndGetElement(CHECKOUT_BUTTON, DEFAULT_TIMEOUT);
		try
		{
			if (!webDriver.findElements(CHECKOUT_BUTTON).isEmpty())
			{
				webDriver.findElement(CHECKOUT_BUTTON).click();
			}
		}
		catch (Exception e)
		{
			webDriver.findElement(GUEST_CHECKOUT_EMAIL_FIELD).sendKeys(RandomGenerator.randomEmailAddress(5));
		}
		waitForPageLoad();
	}
}
