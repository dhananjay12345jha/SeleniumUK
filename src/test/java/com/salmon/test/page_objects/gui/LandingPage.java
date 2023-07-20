package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.xml.bind.annotation.XmlElementRef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;


public class LandingPage extends PageObject {

	private Logger log = LogManager.getLogger(LandingPage.class.getName());
   private static final By POPULAR_COUNTRIES_SELECTOR = By.cssSelector(".landing-page__popular a .landing-page__country-name");
	private static final By DELIVERY_COUNTRY_UK_SELECTOR = By.xpath("//*[@class='landing-page__countries']//div[@class='landing-page__country']//*[text() = 'United Kingdom']");
	private static final By ALL_OTHER_COUNTRIES_SELECTOR = By.cssSelector(".landing-page__all .landing-page__country-name");
	private static final By LANDING_PAGE_HEADING_SELECTOR = By.cssSelector(".landing-page h1");
	private static final By DELIVERY_COUNTRY_NAME_SELECTOR = By.className("locale-select__flag");
	private final By BREXIT_BANNER_MESSAGE_SELECTOR = By.cssSelector(".brexit-banner p");
	private final By LEARN_MORE_CTA_SELECTOR = By.cssSelector(".brexit-banner a");



    public String getCurrentUrl() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getWebDriver().getCurrentUrl();
    }


	public boolean isPopularCountriesListDisplayed()
	{
		return isElementVisible(POPULAR_COUNTRIES_SELECTOR,10);
	}

	public boolean isAllCountriesListDisplayed()
	{
		return isElementVisible(ALL_OTHER_COUNTRIES_SELECTOR,10);
	}

	public String getLandingPageHeadingText()
	{
		waitForExpectedElement(LANDING_PAGE_HEADING_SELECTOR, 10);
		return getElementText(LANDING_PAGE_HEADING_SELECTOR);
	}

	public void selectUKAsDeliveryCountry()
	{
		scrollForFocusAndClick(DELIVERY_COUNTRY_UK_SELECTOR,10);
	}

	public String getNLCookieValue(String cookieName){
    	return getCookieValue(cookieName);
	}

	public String getDeliveryCountryValue(){
    	return getElementText(DELIVERY_COUNTRY_NAME_SELECTOR);
	}

	public String getBrexitBannerMessage()
	{
		return waitForExpectedElement1(BREXIT_BANNER_MESSAGE_SELECTOR,20).getText();
	}

	public void clickOnLearnMoreCTA(){
    	if(IS_MOBILE){
    		clickByJavaScriptExecutor(waitForExpectedElement(LEARN_MORE_CTA_SELECTOR));
      } else
    	waitForExpectedElement(LEARN_MORE_CTA_SELECTOR).click();
	}
}
