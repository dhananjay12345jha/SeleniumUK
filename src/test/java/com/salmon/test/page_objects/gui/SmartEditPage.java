package com.salmon.test.page_objects.gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.salmon.test.framework.PageObject;

import lombok.Data;

@Data
public class SmartEditPage extends PageObject
{
	private static final By SEARCH_DROPDOWN_BOX = By.xpath(" //div[@class='se-item-printer ng-scope']");
	private static final By LOGIN_BACKOFFICE_BUTTON = By.cssSelector(".se-login--btn");
	private static final By ADMIN_BACKOFFICE_ROLE = By.xpath("//div[@class='z-listcell-content' and contains(text(),'Backoffice Administrator Role')]");
	private static final By USER_NAME_INPUT_FIELD = By.xpath("//input[@name='username']");
	private static final By PASSWORD_INPUT_FIELD = By.xpath("//input[@name='password']");
	public static final By PROCEED_BUTTON = By.cssSelector(".yw-selector-btn");


	public void signInUsingUserNameAndPasswordOnSmartEdit(final String userName, final String password)
	{
		waitForElementVisible(USER_NAME_INPUT_FIELD,DEFAULT_TIMEOUT);
		webDriver.findElement(USER_NAME_INPUT_FIELD).sendKeys(userName);
		webDriver.findElement(PASSWORD_INPUT_FIELD).sendKeys(password);
		clickByJavaScriptExecutor(getWebDriver().findElement(LOGIN_BACKOFFICE_BUTTON));

		final WebElement adminRole = waitForAndGetElement(ADMIN_BACKOFFICE_ROLE, 5);
		if(adminRole != null)
		{
			clickByJavaScriptExecutor(adminRole);
		}
		final WebElement proceedButton = waitForAndGetElement(PROCEED_BUTTON, 5);
		if(proceedButton != null)
		{
			clickByJavaScriptExecutor(proceedButton);
		}
	}

	public boolean searchDropdownBoxDisplayed() {
		waitForElementVisible(SEARCH_DROPDOWN_BOX,5);
		return waitForAndGetElement(SEARCH_DROPDOWN_BOX,5).isDisplayed();
	}
}
