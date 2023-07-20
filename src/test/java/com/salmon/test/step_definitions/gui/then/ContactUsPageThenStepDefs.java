package com.salmon.test.step_definitions.gui.then;

import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.page_objects.gui.ContactUsFromPage;

import io.cucumber.java.en.Then;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;

public class ContactUsPageThenStepDefs
{
	@Autowired
	private ContactUsFromPage contactUsFromPage;

//	public ContactUsPageThenStepDefs(final ContactUsFromPage contactUsFromPage)
//	{
//		this.contactUsFromPage = contactUsFromPage;
//	}

	@Then("^I am taken to the contact us redirect page$")
	public void iAmTakenToTheContactUsRedirectPage()
	{
		if (WebDriverHelper.PLATFORM.contains("OS") ||
				WebDriverHelper.BROWSER.contains("internet") ||
				WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("Samsung") ||
				BROWSER.contains("emulator"))
		{
			contactUsFromPage.pause(3000);
		}
		Assert.assertTrue(contactUsFromPage.getCurrentUrl().contains("https://help-uk.newlook.com/hc/en-gb/requests/new"));
	}
}
