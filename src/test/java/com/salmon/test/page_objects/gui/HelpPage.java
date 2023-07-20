package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

/**
 * @author gokhan.ates
 */
public class HelpPage extends PageObject {

    private Logger log = LogManager.getLogger(HelpPage.class.getName());
    private static final By SECURITY_PRIVACY_COOKIES_LINK = By.linkText("Security, Privacy & Cookies");
    private final By HELP_CENTRE_LOGO_SELECTOR = By.cssSelector(".logo p");


    public void clickSecurityPrivacyCookiesLink(){
        webDriver.findElement(SECURITY_PRIVACY_COOKIES_LINK).click();

    }

	public boolean isLogoVisible()
	{
		return isElementVisible(HELP_CENTRE_LOGO_SELECTOR,20);
	}
}
