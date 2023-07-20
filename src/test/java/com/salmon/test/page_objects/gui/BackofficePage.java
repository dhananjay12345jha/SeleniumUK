package com.salmon.test.page_objects.gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.salmon.test.framework.PageObject;

import lombok.Data;

@Data
public class BackofficePage extends PageObject
{
	private static final By SEARCH_IN_TYPE_TREE_BACKOFFICE_FIELD_LOCATOR = By.cssSelector(".yw-explorerTree-filterTextbox");
	private static final By LOGIN_BACKOFFICE_BUTTON = By.cssSelector(".login_btn");
	private static final By ADMIN_BACKOFFICE_ROLE = By.xpath("//div[@class='z-listcell-content' and contains(text(),'Backoffice Administrator Role')]");
	private static final By BACKOFFICE_USER_NAME_INPUT_FIELD = By.xpath("//input[@name='j_username']");
	private static final By BACKOFFICE_PASSWORD_INPUT_FIELD = By.xpath("//input[@name='j_password']");
	public static final By PROCEED_BUTTON = By.cssSelector(".yw-selector-btn");


	public void signInUsingUserNameAndPasswordOnBackoffice(final String userName, final String password)
	{
		waitForAndGetElement(BACKOFFICE_USER_NAME_INPUT_FIELD,DEFAULT_TIMEOUT);
		webDriver.findElement(BACKOFFICE_USER_NAME_INPUT_FIELD).click();
		webDriver.findElement(BACKOFFICE_USER_NAME_INPUT_FIELD).sendKeys(userName);
		webDriver.findElement(BACKOFFICE_PASSWORD_INPUT_FIELD).sendKeys(password);
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

	public boolean searchInTypeTreeIsDisplayed() {
		waitForElementVisible(SEARCH_IN_TYPE_TREE_BACKOFFICE_FIELD_LOCATOR,5);
		return waitForAndGetElement(SEARCH_IN_TYPE_TREE_BACKOFFICE_FIELD_LOCATOR,5).isDisplayed();
	}
}