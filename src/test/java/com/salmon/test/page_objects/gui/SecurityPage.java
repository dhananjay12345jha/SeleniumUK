package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.models.gui.TrackMyOrderModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author gokhan.ates
 */
public class SecurityPage extends PageObject {

    private Logger log = LogManager.getLogger(SecurityPage.class.getName());
    private static final By PRIVACY_TEXT = By.cssSelector("#item_00006028 .accordion-item__title-bar");
    private static final By RIGHT_TO_BE_FORGOTTEN_LINK = By.cssSelector(".cms-paragraph>ul>li>u>a[href*='/uk/rtbf-form']");

    public void clickPrivacyText(){
        waitForAndGetElement(PRIVACY_TEXT,DEFAULT_TIMEOUT).click();
        waitForAndGetElement(RIGHT_TO_BE_FORGOTTEN_LINK,DEFAULT_TIMEOUT).click();
    }

}
