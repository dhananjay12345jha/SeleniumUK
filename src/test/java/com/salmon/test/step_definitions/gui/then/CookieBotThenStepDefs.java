package com.salmon.test.step_definitions.gui.then;

import com.mysql.jdbc.AssertionFailedException;
import com.salmon.test.framework.PageObject;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.salmon.test.page_objects.gui.CookieBotPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

public class CookieBotThenStepDefs
{
	protected WebDriver webDriver;
	@Autowired
	CookieBotPage cookieBotPage ;
//	public CookieBotThenStepDefs(final CookieBotPage cookieBotPage)
//	{
//		this.cookieBotPage = cookieBotPage;
//	}

	@Then("^I can see cookie bot banner$")
	public void iCanSeeCookieBotBanner()
	{
		cookieBotPage.pause(3000);
		Assert.assertTrue(cookieBotPage.cookieBotBanner().isDisplayed());
	}

	@Given("^I accept cookies$")
	public void iAcceptCookies()
	{
		cookieBotPage.pause(3000);
		cookieBotPage.clickCookieBotBannerAccept();
		cookieBotPage.pause(5000);
	}


	@Given("^I opt in for (all|partial) cookies and accept$")
	public void iOptinAllCookiesAndAccept(String type) throws InterruptedException
	{
		cookieBotPage.pause(3000);
		iClickCookiesSettingsButton();
		if (type.equals("all"))
		{
			cookieBotPage.clickByJavaScriptExecutor(cookieBotPage.cookieFunctionalToggle());
			cookieBotPage.clickByJavaScriptExecutor(cookieBotPage.cookieStatsToggle());
			cookieBotPage.clickByJavaScriptExecutor(cookieBotPage.cookieMarketingToggle());
		}
		cookieBotPage.cookieSettingsBannerCloseButton().click();
		cookieBotPage.clickCookieBotBannerAccept();
		cookieBotPage.pause(1000);
	}

	@Then("^cookie bot banner is not displayed$")
	public void cookieBotBannerIsNotDisplayed()
	{
		cookieBotPage.pause(3000);
		try{
			Assert.assertFalse(cookieBotPage.cookieBotBanner().isDisplayed());
		}
		/*catch(AssertionFailedException e){
			Assert.assertNull(cookieBotPage.cookieBotBanner());
		}*/
		catch (Exception e){
			//Assert.fail("Not able to validate cookie bot banner");
			Assert.assertNull(cookieBotPage.cookieBotBanner());
		}
	}

	@Given("^I click cookies settings$")
	public void iClickCookiesSettingsButton()
	{
		cookieBotPage.pause(3000);
		cookieBotPage.cookieBotBannerSettingsButton().click();
		cookieBotPage.pause(1000);
	}

	@Then("^cookie bot settings banner is (not displayed|displayed)$")
	public void cookieBotSettingsBannerIsDisplayed(String displayed)
	{
		cookieBotPage.pause(3000);
		switch (displayed)
		{
			case "displayed":
				Assert.assertTrue(cookieBotPage.cookieBotSettingsBanner().isDisplayed());
				break;
			case "not displayed":
				Assert.assertNull(cookieBotPage.cookieBotSettingsBanner());
		}
	}

	@Given("^I close cookie settings banner$")
	public void iCloseCookieSettingsBanner()
	{
		cookieBotPage.pause(3000);
		cookieBotPage.cookieSettingsBannerCloseButton().click();
	}
}


