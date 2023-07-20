package com.salmon.test.step_definitions.gui.when;

import com.salmon.test.page_objects.gui.ContactUsFromPage;
import com.salmon.test.page_objects.gui.HomePage;

import io.cucumber.java.en.When;

import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;

public class ContactUsPageWhenStepDefs
{
	@Autowired
	private ContactUsFromPage contactUsFromPage;
	@Autowired
	private HomePage homePage;

//	public ContactUsPageWhenStepDefs(final ContactUsFromPage contactUsFromPage, HomePage homePage)
//	{
//		this.contactUsFromPage = contactUsFromPage;
//		this.homePage = homePage;
//	}

	@When("^I select contact us form link from the footer$")
	public void the_customer_clicks_on_contact_us_form_link_from_the_footer() throws Throwable
	{
		if(BROWSER.contains("firefox"))
		{
			contactUsFromPage.pause(2000);
		}
		contactUsFromPage.contactUsFormLink().click();
	}

	@When("^I select contact us form link from the footer on mobile$")
	public void the_customer_clicks_on_contact_us_form_link_from_the_footer_on_mobile() throws InterruptedException
	{
		contactUsFromPage.scrollToBottom();
		homePage.clickFooterOnMobile("Help");
		Thread.sleep(4000);
		contactUsFromPage.contactUsFormLink().click();
	}
}
