package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;
import static com.salmon.test.framework.helpers.WebDriverHelper.DEVICE_NAME;


/**
 * @author gokhan.ates
 */


public class MyContactPreferencesPage extends PageObject {

    private Logger log = LogManager.getLogger(MyContactPreferencesPage.class.getName());
    private static final By SAVE_PREFERENCES_BUTTON = By.cssSelector(".consent__button.button.button--secondary.button--secondary-black.button--full-width-tablet");
    private static final By SAVE_PREFERENCES_ALERT = By.cssSelector(".alert__item.alert__item--rendered");
    private By EMAIL_MARKETING_RADIO_YES = By.id("consent__EmailMarketing-yes");
    private By EMAIL_MARKETING_RADIO_NO = By.id("consent__EmailMarketing-no");
    private By PRODUCT_REVIEWS_RADIO_YES = By.id("consent__ProductReviews-yes");
    private By PRODUCT_REVIEWS_RADIO_NO = By.id("consent__ProductReviews-no");
    private By SOCIAL_MARKETING_RADIO_YES = By.id("consent__SocialMarketing-yes");
    private By SOCIAL_MARKETING_RADIO_NO = By.id("consent__SocialMarketing-no");

    public void clickSavePreferencesButton(){
        webDriver.findElement(SAVE_PREFERENCES_BUTTON).click();
    }

    public void alertItemIsDisplayed(){
    	  pause(2000);
        waitForAndGetElement(SAVE_PREFERENCES_ALERT,10);
        Assert.assertEquals(webDriver.findElement(SAVE_PREFERENCES_ALERT).getText(), "Your contact preferences have been updated");
    }

    public void setRadioButtonsToYes(){
        waitForAndGetElement(EMAIL_MARKETING_RADIO_YES,DEFAULT_TIMEOUT);
        clickByJavaScriptExecutor(webDriver.findElement(EMAIL_MARKETING_RADIO_YES));
        clickByJavaScriptExecutor(webDriver.findElement(PRODUCT_REVIEWS_RADIO_YES));
        clickByJavaScriptExecutor(webDriver.findElement(SOCIAL_MARKETING_RADIO_YES));
    }

    public void setRadioButtonsToNo(){
        if(DEVICE_NAME.contains("iPhone") || BROWSER.contains("emulator")){
            waitForAndGetElement(EMAIL_MARKETING_RADIO_NO,DEFAULT_TIMEOUT);
        }
        clickByJavaScriptExecutor(webDriver.findElement(EMAIL_MARKETING_RADIO_NO));
        clickByJavaScriptExecutor(webDriver.findElement(PRODUCT_REVIEWS_RADIO_NO));
        clickByJavaScriptExecutor(webDriver.findElement(SOCIAL_MARKETING_RADIO_NO));
    }

    public void assertRadioButtonsSetToYes(){
        if(DEVICE_NAME.contains("iPhone") || BROWSER.contains("emulator")){
            waitForAndGetElement(EMAIL_MARKETING_RADIO_YES,DEFAULT_TIMEOUT);
        }
        Assert.assertTrue(webDriver.findElement(EMAIL_MARKETING_RADIO_YES).isSelected());
        Assert.assertTrue(webDriver.findElement(PRODUCT_REVIEWS_RADIO_YES).isSelected());
        Assert.assertTrue(webDriver.findElement(SOCIAL_MARKETING_RADIO_YES).isSelected());
    }

    public void assertRadioButtonsSetToNo(){
        if(DEVICE_NAME.contains("iPhone") || BROWSER.contains("emulator")){
            waitForAndGetElement(EMAIL_MARKETING_RADIO_NO,DEFAULT_TIMEOUT);
        }
        Assert.assertTrue(webDriver.findElement(EMAIL_MARKETING_RADIO_NO).isSelected());
        Assert.assertTrue(webDriver.findElement(PRODUCT_REVIEWS_RADIO_NO).isSelected());
        Assert.assertTrue(webDriver.findElement(SOCIAL_MARKETING_RADIO_NO).isSelected());
    }

}
