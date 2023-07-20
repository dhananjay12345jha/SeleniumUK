package com.salmon.test.step_definitions.gui;


import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.page_objects.gui.CookieBotPage;

import com.salmon.test.page_objects.gui.Features;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.net.MalformedURLException;
import java.net.URL;

public class NavigationSteps {

	@Given("^i navigate to \"(.*?)\" home page$")
	public void i_navigate_to_the_Salmon_page(String pageName) throws Throwable {
		if (pageName.equalsIgnoreCase("Newlook")) {
			UrlBuilder.startAtHomePage();
			if(Features.COOKIE_BOT_NEW)
			{
				CookieBotPage cookieBotPage = new CookieBotPage();
				Thread.sleep(5000);
				if (cookieBotPage.cookieBotBannerAccept())
				{
					cookieBotPage.clickCookieBotBannerAccept();
				}
			}
		}
	}

	@Given("^i navigate to \"(.*?)\" home page and not interact with cookies$")
	public void i_navigate_to_the_Salmon_page_and_not_interact_cookies(String pageName) throws Throwable {
		if (pageName.equalsIgnoreCase("Newlook")) {
			UrlBuilder.startAtHomePage();
		}
	}

	@Given("^i navigate to \"(.*?)\" home page with locale (.*)$")
    public void i_navigate_to_the_Salmon_pageLoc(String pageName, String loc) throws Throwable {
        if (pageName.equalsIgnoreCase("Newlook")) {
            UrlBuilder.startAtHomePage(loc);
        }
    }

    @Given("^i navigate to the Salmon mobile \"(.*?)\" page$")
    public void i_navigate_to_the_Salmon_mobile_page(String pageName) throws Throwable {
        if (pageName.equalsIgnoreCase("HOME")) {
            UrlBuilder.startAtMobileHomePage();
        }
    }

    @Given("^go to newlook french site$")
    public void goNewlookFrenchSite() throws Throwable {
        WebDriverHelper.getWebDriver().get(UrlBuilder.getSiteUrl()+"fr");
    }

	@Given("^i navigate to \"(.*?)\" app page$")
	public void i_navigate_to_the_backoffice_page(String pageLoc) throws Throwable {
    	UrlBuilder.startAtAppPage(pageLoc);
	}

	@Given("^I navigate to \"(.*?)\" homepage for first time with no valid cookies$")
	public void iNavigateToHomepageForFirstTimeWithNoValidCookies(String pageName) throws MalformedURLException
	{
		if (pageName.equalsIgnoreCase("Newlook")) {
			UrlBuilder.startAtPage(new URL(UrlBuilder.getSiteUrl()));
		}
		CookieBotPage cookieBotPage = new CookieBotPage();
		if (cookieBotPage.cookieBotBannerAccept())
		{
			cookieBotPage.clickCookieBotBannerAccept();
		}
	}
	@And("^i open hac page$")
	public void openHacPage() throws Throwable {
		UrlBuilder.launchHac();
	}
	@And("^i open newLook page$")
	public void newLookPage() throws Throwable {
		UrlBuilder.launchNewLook();
	}

}
