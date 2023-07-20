package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.framework.helpers.utils.Credentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.*;
import static com.salmon.test.page_objects.gui.Features.CONSENT_PRINCIPAL_FEATURE;
import static com.salmon.test.page_objects.gui.MyAccountPage.MY_ACCOUNT_HOME;



//public class CreateNewAccountPage extends AbstractPage
public class CreateNewAccountPage extends PageObject {

    private Logger log = LogManager.getLogger(CreateNewAccountPage.class.getName());
    private Credentials credentials = new Credentials();

    By CREATE_NEW_ACCOUNT_LABEL = By.className("checkout__form-title");

    By REGISTER_MESSAGE_LABELS = By.className("register__messages");

    By EMAIL_ADDRESS_LABEL = By.xpath("//*[@for='email']");

    By PASSWORD_LABEL = By.xpath("//*[@for='pwd']");

    By PASSWORD_SHOW_LABEL = By.className("input__inline-control--account");

    By PASSWORD_ADDITIONAL_INFO = By.cssSelector(".account__additional-info-input--full:nth-of-type(1)");

    By TITLE_LABEL = By.xpath("//*[@for='titleCode']");

    By FIRST_NAME_LABEL = By.xpath("//*[@for='firstName']");

    By LAST_NAME_LABEL = By.xpath("//*[@for='lastName']");

    By GENDER_LABEL = By.id("gender-describer");

    By RADIO_BUTTON_FEMALE_LABEL = By.cssSelector(".label.radio__label[for='gender0']");

    By RADIO_BUTTON_MALE_LABEL = By.cssSelector(".label.radio__label[for='gender1']");

    By DATE_OF_BIRTH_LABEL = By.cssSelector("div:nth-of-type(8) > div >.checkout__form-label");

    By MOBILE_NUMBER_LABEL = By.cssSelector(".checkout__form-label[for='mobileNumber']");

    By NO_MARKETING_INFO = By.cssSelector(".account__additional-info-input--full:nth-of-type(2)");

    By ACCOUNT_PREFERENCES_TEXT = By.cssSelector(".label.checkbox__label[for='account-preferences']");

    By CREATE_MY_ACCOUNT_BUTTON = By.cssSelector(".button.button--primary.button--full-width");
    By PASSWORD_INPUT_FIELD = By.id("pwd");
    By SELECT_TITLE_FIELD = By.id("titleCode");
    By FIRST_NAME_INPUT_FIELD = By.id("firstName");
    By LAST_NAME_INPUT_FIELD = By.id("lastName");
    By SELECT_DATE_OF_BIRTH = By.cssSelector(".select--dob");
    By MOBILE_NUMBER_INPUT_FIELD = By.cssSelector("#mobileNumber");
    private By EMAIL_INPUT_FIELD = By.id("email");
    private static final By OVER_AGE_18_CHECKBOX = By.cssSelector(".label.checkbox__label[for='ageConfirmation']");

    public List<String> getCreateNewAccountFormLabels() {
        ArrayList<String> createAccountFormLabels = new ArrayList<>();
        createAccountFormLabels.add(getWebDriver().findElement(CREATE_NEW_ACCOUNT_LABEL).getText());
        createAccountFormLabels.add(getWebDriver().findElement(REGISTER_MESSAGE_LABELS).getText());
        createAccountFormLabels.add(getWebDriver().findElement(EMAIL_ADDRESS_LABEL).getText());
        createAccountFormLabels.add(getWebDriver().findElement(PASSWORD_LABEL).getText());
        createAccountFormLabels.add(getWebDriver().findElement(PASSWORD_SHOW_LABEL).getText());
        createAccountFormLabels.add(getWebDriver().findElement(PASSWORD_ADDITIONAL_INFO).getText());
        createAccountFormLabels.add(getWebDriver().findElement(TITLE_LABEL).getText());
        createAccountFormLabels.add(getWebDriver().findElement(FIRST_NAME_LABEL).getText());
        createAccountFormLabels.add(getWebDriver().findElement(LAST_NAME_LABEL).getText());
        createAccountFormLabels.add(getWebDriver().findElement(CREATE_MY_ACCOUNT_BUTTON).getText());
        return createAccountFormLabels;
    }

    public Credentials createNewAccountWithCorrectDetails() {
        waitForExpectedElement(EMAIL_INPUT_FIELD, DEFAULT_TIMEOUT);
        waitForExpectedElement(EMAIL_INPUT_FIELD).sendKeys(credentials.getEmail());
        waitForExpectedElement(PASSWORD_INPUT_FIELD).sendKeys(credentials.getPassword());
        scrollByCoordinates(120);
        if(BROWSER.contains("microsoftEdge") || PLATFORM.contains("macOS 10.14") || BROWSER.contains("emulator")){
        	scrollByCoordinates(120);
            waitForPageLoad();
        }
        selectFromDropDown(waitForExpectedElement(SELECT_TITLE_FIELD), 1);
        waitForPageLoad();
        waitForExpectedElement(FIRST_NAME_INPUT_FIELD).sendKeys(RandomGenerator.randomAlphabetic(5));
        if(BROWSER.contains("microsoftEdge") || PLATFORM.contains("macOS 10.14") || BROWSER.contains("emulator"))
        {
	        scrollByCoordinates(60);
	        waitForPageLoad();
        }
        waitForExpectedElement(LAST_NAME_INPUT_FIELD).sendKeys(RandomGenerator.randomAlphabetic(5));
        waitForPageLoad();
        clickByJavaScriptExecutor(waitForExpectedElement(OVER_AGE_18_CHECKBOX));
        waitForPageLoad();
        clickByJavaScriptExecutor(waitForExpectedElement(CREATE_MY_ACCOUNT_BUTTON));
        waitForPageLoad();
        if(WebDriverHelper.BROWSER.contains("firefox"))
        {
            pause(2000);
        }
        if (CONSENT_PRINCIPAL_FEATURE) {
            try{
                waitForAndGetElement(MY_ACCOUNT_HOME, DEFAULT_TIMEOUT).click();
            }
            catch (Exception e){
            }
        }
        waitForPageLoad();
        return credentials;
    }

    public Credentials createNewAccountWithCorrectDetailsAndStayOnConsentPage() {
        waitForAndGetElement(EMAIL_INPUT_FIELD, DEFAULT_TIMEOUT);
        waitForExpectedElement(EMAIL_INPUT_FIELD).sendKeys(credentials.getEmail());
        waitForPageLoad();
        waitForExpectedElement(PASSWORD_INPUT_FIELD).sendKeys(credentials.getPassword());
        waitForPageLoad();
	    if(WebDriverHelper.BROWSER.contains("microsoftEdge") || WebDriverHelper.PLATFORM.contains("macOS 10.14") || BROWSER.contains("emulator")){
		    scrollElementIntoView(SELECT_TITLE_FIELD);
		    waitForPageLoad();
	    }
        selectFromDropDown(waitForExpectedElement(SELECT_TITLE_FIELD), 1);
	    waitForPageLoad();
        waitForExpectedElement(FIRST_NAME_INPUT_FIELD).sendKeys(RandomGenerator.randomAlphabetic(5));
        waitForPageLoad();
        waitForExpectedElement(LAST_NAME_INPUT_FIELD).sendKeys(RandomGenerator.randomAlphabetic(5));
        waitForPageLoad();
        waitForExpectedElement(OVER_AGE_18_CHECKBOX).click();
        waitForPageLoad();
        clickByJavaScriptExecutor(waitForExpectedElement(CREATE_MY_ACCOUNT_BUTTON));
        waitForPageLoad();
        return credentials;
    }


    public String getUserName() {
        return credentials.getEmail();
    }

    public String getNewPassword() {
        return credentials.getPassword();
    }

    public boolean ageConfirmationCheckBoxExists() {
        return getWebDriver().findElement(OVER_AGE_18_CHECKBOX).isDisplayed();
    }

}
