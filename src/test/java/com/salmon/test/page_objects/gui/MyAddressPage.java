package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.RandomGenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.PLATFORM;
import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;


public class MyAddressPage extends PageObject
{

	private Logger log = LogManager.getLogger(MyAddressPage.class.getName());
	private static final By ADDRESS_DELETE_BUTTON = By.cssSelector(".address-book__address-data-link.button-link[data-ng-click*='deleteAddress']");
	private static final By DELETE_ADDRESS_POPUP = By.cssSelector(".modal.modal--sm.modal--visible");
	private static final By DELETE_ADDRESS_POPUP_TITLE_LABEL = By.cssSelector(".modal.modal--sm.modal--visible > .modal__header > .modal__title");
	private static final By DELETE_ADDRESS_POPUP_DELETE_THIS_ADDRESS_LABEL = By.cssSelector(".address-book__modal-wrapper > h2");
	private static final By DELETE_ADDRESS_POPUP_DELETE_DESCRIPTION_LABEL = By.cssSelector(".address-book__modal-wrapper > p");
	private static final By DELETE_ADDRESS_POPUP_DELETE_BUTTON = By.cssSelector(".address-book__modal-wrapper > button.button");
	private static final By DELETE_ADDRESS_POPUP_CANCEL_BUTTON = By.cssSelector(".address-book__modal-wrapper > button[data-translate='text.button.cancel']");
	private static final By ADDRESS_DISPLAYED = By.className("account-card");
	//    private static final By ADD_A_NEW_ADDRESS_BUTTON = By.cssSelector(".button.button--secondary.button--secondary-black.address-book__btn.ng-scope");
	private static final By MOBILE_NUMBER = By.id("mobilePhone");
	private static final By ADD_ADDRESS_BUTTION_IN_POPUP = By.cssSelector(".button.button--secondary.button--secondary-black.modal__button.checkout__delivery-btn.account__delivery-btn--use-address.ng-scope");
	private static final By ENTER_ADDRESS_MANUALLY_LINK = By.cssSelector(".button-link.ng-scope[data-translate='address.lookup.manual.link']");
	private static final By ADDRESS_LINE_ONE_FIELD = By.id("line1");
	private static final By ADDRESS_LINE_ONE_LABEL = By.xpath("//*[@for='line1']");
	private static final By ADDRESS_LINE_TWO_FIELD = By.id("line2");
	private static final By ADDRESS_LINE_TWO_LABEL = By.xpath("//*[@for='line2']");
	private static final By ADDRESS_TOWN_OR_CITY_FIELD = By.id("town");
	private static final By ADDRESS_POSTCODE_FIELD = By.id("postalCode");
	private static final By FIRST_NAME = By.cssSelector("#editAddress #firstName");
	private static final By LAST_NAME = By.id("lastName");
	private static final By ADDRESS_LOOKUP_RESULTS = By.cssSelector(".address-lookup__results .address-lookup__results-list-item-link");
	private static final By ADDRESS_LOOKUP = By.id("addressLookup");
	private static final By ADD_ADDRESS = By.xpath("//*[@name='useAddress']");
	private static final By SAVED_ADDREDSS = By.cssSelector(".account__cell");
	private static final By SAVED_ADDREDSS_LINE_POSTCODE = By.cssSelector(".account-card__content>p:nth-child(6)");
	private static final By SAVED_ADDREDSS_LINE = By.cssSelector(".account-card__content>p");
	private static final By DEFAULT_ADDRESS_LINK = By.cssSelector(".address-book__link>span");
	private static final By MOBILE_SEARCH_ICON = By.cssSelector("a.header__search.header-refresh__icon-link > i.masthead__icon");

	private static final java.lang.String ADD_A_NEW_ADDRESS = "Add a new address";

	public int numberOfAddresses;

	public void clickDeleteAddress() throws InterruptedException
	{
		try
		{
			getNumberOfAddressDisplayed();
//            waitForElementVisible(ADDRESS_DELETE_BUTTON, 30);
//            scrollForFocusAndClick(ADDRESS_DELETE_BUTTON, 5);
			pause(2000);
			clickByJavaScriptExecutor(webDriver.findElements(By.cssSelector("button")).stream().filter(a -> a.getText().equalsIgnoreCase("Delete")).findFirst().get());
		}
		catch (Exception e)
		{
			//do nothing
		}
	}

	public boolean getDeleteAddressPopup()
	{
		pause(2000);
		waitForElementVisible(DELETE_ADDRESS_POPUP, 30);
		if (!webDriver.findElements(DELETE_ADDRESS_POPUP).isEmpty())
		{
			return true;
		}
		return false;
	}

	public List<String> getDeleteAddressPopupLabels()
	{
		waitForAndGetElement(DELETE_ADDRESS_POPUP, DEFAULT_TIMEOUT);
		ArrayList<String> deleteAddressPopupLabels = new ArrayList<>();
		deleteAddressPopupLabels.add(webDriver.findElement(DELETE_ADDRESS_POPUP_TITLE_LABEL).getText());
		deleteAddressPopupLabels.add(webDriver.findElement(DELETE_ADDRESS_POPUP_DELETE_THIS_ADDRESS_LABEL).getText());
		deleteAddressPopupLabels.add(webDriver.findElement(DELETE_ADDRESS_POPUP_DELETE_DESCRIPTION_LABEL).getText());
		deleteAddressPopupLabels.add(webDriver.findElement(DELETE_ADDRESS_POPUP_DELETE_BUTTON).getText());
		deleteAddressPopupLabels.add(webDriver.findElement(DELETE_ADDRESS_POPUP_CANCEL_BUTTON).getText());
		return deleteAddressPopupLabels;

	}

	public void clickDeleteAddressInPopup()
	{

		waitForAndGetElement(DELETE_ADDRESS_POPUP, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(DELETE_ADDRESS_POPUP_DELETE_BUTTON).isEmpty())
		{
			webDriver.findElement(DELETE_ADDRESS_POPUP_DELETE_BUTTON).click();
		}
	}

	private MyAddressPage getNumberOfAddressDisplayed()
	{
		this.numberOfAddresses = webDriver.findElements(ADDRESS_DELETE_BUTTON).size();
		return this;
	}

	public int getNumberOfAddresses()
	{
		waitForIsClickable(webDriver.findElements(ADDRESS_DISPLAYED), DEFAULT_TIMEOUT);
		if (!webDriver.findElements(ADDRESS_DISPLAYED).isEmpty())
		{
			while (webDriver.findElements(ADDRESS_DISPLAYED).size() != numberOfAddresses)
			{
				return webDriver.findElements(ADDRESS_DISPLAYED).size();
			}
		}
		return 0;
	}

	public void addNewAddressToMyAccount()
	{
		waitForAndGetElement(webDriver, ADDRESS_DISPLAYED, DEFAULT_TIMEOUT);
		getNumberOfAddressDisplayed();
//        webDriver.findElement(ADD_A_NEW_ADDRESS_BUTTON).click();
		webDriver.findElements(By.cssSelector("button")).stream()
				.filter(a -> StringUtils.containsIgnoreCase(a.getText(), "ADD A NEW ADDRESS"))
				.findFirst().get().click();
		enterMobileNumberIfDisplayed();
		addAddressManually();
		pause(2000);
	}

	public void enterMobileNumberIfDisplayed()
	{
		waitForAndGetElement(MOBILE_NUMBER, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(MOBILE_NUMBER).isEmpty())
		{
			webDriver.findElement(MOBILE_NUMBER).sendKeys("075" + RandomGenerator.randomInteger(8));
		}
	}

	public void addAddressManually()
	{
		waitForAndGetElement(ENTER_ADDRESS_MANUALLY_LINK, DEFAULT_TIMEOUT);
		webDriver.findElement(ENTER_ADDRESS_MANUALLY_LINK).click();
		waitForAndGetElement(ADDRESS_LINE_ONE_FIELD, DEFAULT_TIMEOUT);
		webDriver.findElement(ADDRESS_LINE_ONE_FIELD).sendKeys("Hewlett Packard Enterprise");
		//webDriver.findElement(ADDRESS_LINE_ONE_LABEL).click();
		pause(2000);
		//waitForElementPresence(ADDRESS_LINE_TWO_FIELD,DEFAULT_TIMEOUT);
		//        if(isElementPresent(ADDRESS_LINE_TWO_FIELD))
		//        {
		//	        webDriver.findElement(ADDRESS_LINE_TWO_FIELD).sendKeys("1 Aldermanbury Square");
		//        }
		//webDriver.findElement(ADDRESS_LINE_TWO_LABEL).click();
		//pause(2000);
		webDriver.findElement(ADDRESS_TOWN_OR_CITY_FIELD).sendKeys("LONDON");
		webDriver.findElement(ADDRESS_POSTCODE_FIELD).sendKeys("EC2V 7HR");
		webDriver.findElement(ADD_ADDRESS_BUTTION_IN_POPUP).click();

	}


	public void addAddressManually(String postcode)
	{
		pause(3000);
		getButtonWithText(ADD_A_NEW_ADDRESS).click();
		pause(3000);
		waitForAndGetElement(ENTER_ADDRESS_MANUALLY_LINK, DEFAULT_TIMEOUT);
		pause(1000);
		webDriver.findElement(ENTER_ADDRESS_MANUALLY_LINK).click();
		waitForAndGetElement(ADDRESS_LINE_ONE_FIELD, DEFAULT_TIMEOUT);
		webDriver.findElement(ADDRESS_LINE_ONE_FIELD).sendKeys("1 Altmore Avenue");
		webDriver.findElement(ADDRESS_LINE_TWO_FIELD).sendKeys("1 Altmore Avenue");
		if (!webDriver.findElements(MOBILE_NUMBER).isEmpty())
		{
			webDriver.findElement(MOBILE_NUMBER).sendKeys("09032390653");
		}
		webDriver.findElement(ADDRESS_TOWN_OR_CITY_FIELD).sendKeys("LONDON");
		webDriver.findElement(ADDRESS_POSTCODE_FIELD).sendKeys(postcode);
		webDriver.findElement(ADD_ADDRESS_BUTTION_IN_POPUP).click();

	}

	public void addAddressUsingFinder(String firstName, String lastName, String postCode)
	{
		pause(2000);
		getButtonWithText(ADD_A_NEW_ADDRESS).click();
		waitForElementPresence(FIRST_NAME, 5).clear();
		waitForElementPresence(FIRST_NAME, 5).sendKeys(firstName);
		webDriver.findElement(LAST_NAME).clear();
		webDriver.findElement(LAST_NAME).sendKeys(lastName);
		if (!webDriver.findElements(MOBILE_NUMBER).isEmpty())
		{
			webDriver.findElement(MOBILE_NUMBER).sendKeys("09032390653");
		}
		webDriver.findElement(ADDRESS_LOOKUP).sendKeys(postCode);
		pause(3500);
		elementToBeClickable(ADDRESS_LOOKUP_RESULTS).click();
		waitForElementWithText(ADD_ADDRESS, "ADD ADDRESS", 10);
		scrollForFocus(ADD_ADDRESS, 5);
		waitForElementVisible(ADD_ADDRESS, 5);
		getWebDriver().findElement(ADD_ADDRESS).click();
		pause(1000);

	}

	public WebElement getSavedAddress(String postCode) throws InterruptedException
	{
		Thread.sleep(3000);
		if (!PLATFORM.contains("macOS 10.14"))
		{
			waitForH2Heading("My Addresses", 5);
		}
		return webDriver.findElements(SAVED_ADDREDSS).stream().filter(a -> a.findElement(SAVED_ADDREDSS_LINE_POSTCODE).getText().equalsIgnoreCase(postCode)).findFirst().get();
	}

	public String getSavedAddressDetails(String postCode) throws InterruptedException
	{
		if (!PLATFORM.contains("macOS 10.14"))
		{
			waitForH2Heading("My Addresses", 5);
		}
		pause(2000);
		String result = "";
		WebElement cell = getSavedAddress(postCode);
		for (WebElement e : cell.findElements(SAVED_ADDREDSS_LINE))
		{
			if (!e.getText().isEmpty())
			{
				result = result + e.getText() + ";";
			}
		}
		return result;

	}

	public void setAsDefaultAddress(String postCode)
	{
		pause(1000);
		waitForAndGetElement(DEFAULT_ADDRESS_LINK, DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(webDriver.findElement(DEFAULT_ADDRESS_LINK));
		pause(1000);
	}
}
