package com.salmon.test.page_objects.gui;


import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.WebDriverHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.*;
import static com.salmon.test.page_objects.gui.MyAccountPage.MY_ACCOUNT_LINKS;




public class SignInPage extends PageObject
{
	private Logger log = LogManager.getLogger(SignInPage.class.getName());
	private final By registerForm = By.id("registerForm");
	private final By logInSection = By.className("register__tabs");
	private final By loginForm = By.id("loginForm");
	private By RETURNING_CUSTOMERS_HEADER = By.cssSelector(".checkout__form-title.checkout__form-title--login");
	private By EMAIL_ADDRESS_LABEL = By.cssSelector(".label.form-group__label.checkout__form-label");
	private By PASSWORD_LABEL = By.cssSelector(".checkout__form-label[for='j_password']");
	private By SIGNIN_BUTTON = By.cssSelector(".checkout__form-button.button.button--primary");
	private By checkoutButton = By.cssSelector(".checkout__login-box--signin button");
	private By FORGOTTEN_PASSWORD_LINK = By.cssSelector(".js-password-forgotten");
	private By PASSWORD_SHOW_BUTTON = By.cssSelector(".input__inline-control.checkout__form-span");
	private By USER_NAME_INPUT_FIELD = By.id("j_username");
	private By PASSWORD_INPUT_FIELD = By.id("j_password");
	private By EMAIL_PASSWORD_WRONG_ALERT = By.cssSelector(".alert__item.alert__item--rendered");
	private By ENTER_AN_EMAIL_MESSAGE = By.cssSelector(".form-group__field--checkout-login > .form__error-msg.ng-scope");
	private By ENTER_YOUR_PASSWORD_MESSAGE = By.cssSelector(".input__inline-wrapper > .form__error-msg.ng-scope");
	private By NEWSLETTER_OPTION_CHECKBOX = By.cssSelector("input[id = 'account-preferences']");

	public List<String> getSignInboxLabels()
	{
		if(WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
		   WebDriverHelper.DEVICE_NAME.contains("iPad") ||
				WebDriverHelper.DEVICE_NAME.contains("Samsung") || BROWSER.contains("emulator")){
			waitForAndGetElement(RETURNING_CUSTOMERS_HEADER,DEFAULT_TIMEOUT);
		}
		ArrayList<String> signInBoxLabels = new ArrayList<>();
		signInBoxLabels.add(webDriver.findElement(RETURNING_CUSTOMERS_HEADER).getText());
		signInBoxLabels.add(webDriver.findElement(EMAIL_ADDRESS_LABEL).getText());
		signInBoxLabels.add(webDriver.findElement(PASSWORD_LABEL).getText());
		signInBoxLabels.add(webDriver.findElement(SIGNIN_BUTTON).getText());
		signInBoxLabels.add(webDriver.findElement(FORGOTTEN_PASSWORD_LINK).getText());
		signInBoxLabels.add(webDriver.findElement(PASSWORD_SHOW_BUTTON).getText());
		return signInBoxLabels;
	}

	public void signInUsingUserNameAndPassword(final String userName, final String password)
	{
		pause(3000);
		waitForAndGetElement(USER_NAME_INPUT_FIELD,DEFAULT_TIMEOUT);
		webDriver.findElement(USER_NAME_INPUT_FIELD).click();
		webDriver.findElement(USER_NAME_INPUT_FIELD).sendKeys(userName);
		webDriver.findElement(PASSWORD_INPUT_FIELD).sendKeys(password);
		//webDriver.findElement(SIGNIN_BUTTON).click();
		clickByJavaScriptExecutor(getWebDriver().findElement(checkoutButton));
		//getButtonWithText("CHECKOUT").click();
		if(WebDriverHelper.BROWSER.equals("firefox") ||
		   WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
		   WebDriverHelper.DEVICE_NAME.contains("iPad") ||
				WebDriverHelper.DEVICE_NAME.contains("Samsung") || BROWSER.contains("emulator")){
			waitForAndGetElement(MY_ACCOUNT_LINKS,DEFAULT_TIMEOUT);
		}
		if(WebDriverHelper.DEVICE_NAME.contains("iPhone") || BROWSER.contains("emulator")){
			pause(2000);
		}
	}

	public void signInWithDefaultUser(){
		signInUsingUserNameAndPassword(Props.getProp("emailAddress"),Props.getProp("password"));
	}

	public String getEmailOrPasswordWasIncorrectMessage()
	{
		waitForAndGetElement(EMAIL_PASSWORD_WRONG_ALERT, DEFAULT_TIMEOUT);
		return webDriver.findElement(EMAIL_PASSWORD_WRONG_ALERT).getText();
	}

	public String getPleaseEnterAnEmailMessage()
	{
		waitForAndGetElement(ENTER_AN_EMAIL_MESSAGE, DEFAULT_TIMEOUT);
		return webDriver.findElement(ENTER_AN_EMAIL_MESSAGE).getText();
	}

	public String getPleaseEnterPasswordMessage()
	{
		waitForAndGetElement(ENTER_YOUR_PASSWORD_MESSAGE, DEFAULT_TIMEOUT);
		return webDriver.findElement(ENTER_YOUR_PASSWORD_MESSAGE).getText();
	}

	public WebElement logInSection()
	{
		if(WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
		   WebDriverHelper.DEVICE_NAME.contains("iPad") || BROWSER.contains("emulator")){
			pause(1000);
		}
		return waitForAndGetElement(logInSection,DEFAULT_TIMEOUT);
	}

	public WebElement registerForm()
	{
		return waitForExpectedElement(registerForm);
	}

	public WebElement loginForm()
	{
		return waitForExpectedElement(loginForm);
	}

	public WebElement getNewsLetteroOtincCheckbox() {
		return waitForExpectedElement(NEWSLETTER_OPTION_CHECKBOX);
	}

	public boolean newsLetteroOtincCheckboxExists() {

		boolean isexists;

		if (webDriver.findElements(NEWSLETTER_OPTION_CHECKBOX).size() > 0) {
			log.info("component does exist");
			isexists = true;
		} else {

			log.info("component doesn't exist");
			isexists = false;
		}

		return isexists;

	}

}
