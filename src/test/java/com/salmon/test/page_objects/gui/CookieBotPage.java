package com.salmon.test.page_objects.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.salmon.test.framework.PageObject;

public class CookieBotPage extends PageObject
{
	private Logger log = LogManager.getLogger(CookieBotPage.class.getName());
	private final By COOKIEBOT_BANNER = By.xpath("//*[@class='cookiebanner-container']");
	private final By COOKIEBOT_BANNER_ACCEPT = By.xpath("//*[@class='cookiebanner-btns--btn cookiebanner-btns--btn__accept']");
	private final By COOKIEBOT_BANNER_SETTINGS_BUTTON = By.xpath("//*[@class='cookiebanner-btns--btn cookiebanner-btns--btn__settings']");
	private final By COOKIEBOT_SETTINGS_BANNER = By.xpath("//*[@class='cookiebanner-container cookiebanner-container__details']");
	private final By COOKIEBOT_SETTINGS_BANNER_CLOSE = By.xpath("//*[@class='cookiebanner-btns--close']");
	private final By COOKIEBOT_SETTINGS_FUNCTIONAL_TOGGLE = By.id("cookiebanner-checkbox-preference");
	private final By COOKIEBOT_SETTINGS_STATS_TOGGLE = By.xpath("//*[@id='cookiebanner-checkbox-statistics']");
	private final By COOKIEBOT_SETTINGS_MARKETING_TOGGLE = By.id("cookiebanner-checkbox-marketing");

	public WebElement cookieBotBanner()
	{
		return waitForAndGetElement(COOKIEBOT_BANNER, DEFAULT_TIMEOUT);
	}

	public WebElement cookieFunctionalToggle()
	{
		return waitForAndGetElement(COOKIEBOT_SETTINGS_FUNCTIONAL_TOGGLE, DEFAULT_TIMEOUT);
	}

	public WebElement cookieStatsToggle()
	{
		return waitForAndGetElement(COOKIEBOT_SETTINGS_STATS_TOGGLE, DEFAULT_TIMEOUT);
	}

	public WebElement cookieMarketingToggle()
	{
		return waitForAndGetElement(COOKIEBOT_SETTINGS_MARKETING_TOGGLE, DEFAULT_TIMEOUT);
	}

	public boolean cookieBotBannerAccept()
	{
		return isElementPresent(COOKIEBOT_BANNER_ACCEPT, DEFAULT_TIMEOUT);
	}

	public void clickCookieBotBannerAccept()
	{
		scrollElementIntoView(COOKIEBOT_BANNER_ACCEPT);
		waitForAndGetElement(COOKIEBOT_BANNER_ACCEPT, DEFAULT_TIMEOUT).click();
	}

	public WebElement cookieBotBannerSettingsButton()
	{
		return waitForAndGetElement(COOKIEBOT_BANNER_SETTINGS_BUTTON, DEFAULT_TIMEOUT);
	}

	public WebElement cookieBotSettingsBanner()
	{
		return waitForAndGetElement(COOKIEBOT_SETTINGS_BANNER, DEFAULT_TIMEOUT);
	}

	public WebElement cookieSettingsBannerCloseButton()
	{
		return waitForAndGetElement(COOKIEBOT_SETTINGS_BANNER_CLOSE, DEFAULT_TIMEOUT);
	}

}

