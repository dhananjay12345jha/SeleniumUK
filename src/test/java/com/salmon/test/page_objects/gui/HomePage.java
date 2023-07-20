package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.step_definitions.gui.DataUnderTest;

import lombok.Data;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.*;
import static com.salmon.test.page_objects.gui.CheckOutPage.MAST_HEADER_SEARCH_FIELD;

/**
 * Created by phani.kaliginadi on 26/10/17.
 */
@Data
public class HomePage extends PageObject
{
	private static final By SIGN_OUT_BUTTON = By.cssSelector(".link.link--nounderline[href*='logout']");
	private static final By LOGOUT_MESSAGE = By.cssSelector(".alert__item.alert__item--rendered");
	private static final By HEADER_NAVIGATION_LINKS = By.cssSelector(".header__navitem--link > a");
	private static final By BAG_ICON = By.cssSelector(".iconf.iconf--bag");
	private static final By KLARNA_LOGO_FOOTER = By.xpath("//span[@class='payment-card klarna']");
	private static final By GOT_IT_BUTTON = By.cssSelector(".cookie-banner__accept-cta.button.button--secondary.button--secondary-white.ng-scope");
	public final By megaMenuHeading = By.cssSelector(".main-navigation__secondary-menu-container>p>a");
	private final By searchFieldLocator = By.id("js-site-search-input");
	private final By searchButton = By.cssSelector(".search-area__search-trigger.link.link--nounderline");
	public By productItemsDetail = By.cssSelector(".product-item__details-wrapper>.product-item__details>a");
	private final By localeSelector = By.className("header__navitem");
	@FindBy(css = ".main-navigation__tertiary-menu-item>a")
	List<WebElement> tertiaryMenu;
	private DataUnderTest dataUnderTest;
	private By HEADER_SIGNIN_LINK = By.cssSelector("[data-ng-hide='isLoggedIn'] .header__navitem > a[href*='/uk/login']");
	private By HEADER_CREATE_NEW_ACCOUNT_LINK = By.cssSelector("a[href*='/register']");
	private By SEARCH_RESULTS_LIST = By.cssSelector(".site-search__product-suggestions > li");
	private By MY_ACCOUNT_BUTTON = By.cssSelector(".header__navitem > .link.link--nounderline[href*='my-account'] > span.ng-scope");
	private By megaMenu = By.cssSelector(".main-navigation__primary-menu-link-content");
	private By secondaryMenu = By.className("main-navigation__secondary-menu-link");
	private By products = By.cssSelector(".plp-item .plp-carousel");
	private By PRODUCTS_NAMES = By.className("product-item__name");
	public static By breadCrumbs = By.cssSelector(".breadcrumb__link>span");
	private By footerLinks = By.cssSelector(".page-footer__primary-nav>section>ul>li>a");
	private By appFooterLink = By.className("app-footer--container");
	//    private Logger log = LoggerFactory.getLogger(HomePage.class);
	private Logger log = LogManager.getLogger(HomePage.class.getName());
	private By trackMyOrder = By.cssSelector("li.header__navitem>a[href*=trackMyOrder]");
	private final By countryOptions = By.className("nl-select__choice");
	private final By countrySearch = By.className("ui-select-search");
	private final By countrySearchSelect = By.className("ui-select-highlight");
	private By changeDeliveryCountryPopUp = By.className("popover__content");
	private static final By MY_BAG_ICON = By.cssSelector(".iconf--bag");
	private static final By ALL_PRODUCTS_RESULT_COUNT = By.cssSelector(".search-filter__count.ng-binding");
	private static final By ALL_PRODUCTS_RESULT_COUNT_MOBILE = By.cssSelector(".facets-mobile-toggle__selected.ng-scope");
	private static final By SIGN_ME_UP_BUTTON = By.cssSelector(".button.button--secondary.button--secondary-black.page-footer__sign-up-button");
	private static final By ERROR_MSG = By.cssSelector(".form__error-msg.form__error-msg--email-address.ng-scope");
	private static final By ACCOUNT_HEADER = By.cssSelector(".masthead__icon.masthead__icon-user");
	private static final By CREATE_AN_ACCOUNT = By.className("register__tab-button");
	private CheckOutPage checkOutPage = new CheckOutPage();
	public static final By MAST_HEADER_SEARCH_BUTTON = By.className("auto-complete__button");
	public static final By MAST_HEADER_WISH_LIST_LINK = By.cssSelector(".masthead__icon.masthead__icon--grey.masthead__icon-saved");
	public static final By MAST_HEADER_SIGN_OUT_BUTTON = By.cssSelector(".masthead-account__button.button.button--secondary-black");
	public static final By MAST_HEADER_MY_ORDERS = By.cssSelector(".masthead-account__link.link.link--nounderline[href*='/uk/my-account/orders']");
	public static final By TRACK_MY_ORDER = By.cssSelector("div[data-ng-show*=isLoggedIn]>li>a[href*=orders]");
	public static final By LOCAL_COUNTRY = By.cssSelector(".locale-select__flag.locale-select__flag.ng-scope");
	private static final By DELIVERY_COUNTRY_DROPDOWN = By.xpath("//form[contains(@class,'popover__content')]//div[contains(@class,'ui-select-container')]");
	private static final By DELIVERY_COUNTRY_DROPDOWN_MOBILE = By.xpath("//*[@class='select ng-pristine ng-untouched ng-valid ng-not-empty']");
	private static final By DELIVERY_COUNTRY_DROPDOWN_OPTIONS_MOBILE = By.xpath("//*[@class='select ng-pristine ng-untouched ng-valid ng-not-empty']/option[@selected='selected']");


	private static final By DELIVERY_COUNTRY_SEARCH_BOX = By.className("ui-select-search");
	private static final By DELIVERY_COUNTRY_SEARCH_CHOICE = By.className("ui-select-highlight");
	public static final By MAST_HEADER_STORE_LOCATOR = By.cssSelector(".masthead__icon.masthead__icon-pin");
	private static final By MAST_HEADER_WOMENS_MENU = By.cssSelector(".main-navigation__primary-menu-item:nth-child(1) > a");
	private static final By MAST_HEADER_MENS_MENU = By.cssSelector(".main-navigation__primary-menu-item:nth-child(2) > a");
	private static final By MAST_HEADER_TEENS_MENU = By.cssSelector(".main-navigation__primary-menu-item:nth-child(3) > a");
	public static final By MAST_HEADER_WISH_LIST_COUNT = By.cssSelector(".masthead__saved-link .masthead__bagcount");
	public static final By MAST_HEADER_BAG_COUNT = By.cssSelector(".header__bagcount.masthead__bagcount.ng-binding");
	public static final By MAST_HEADER_BAG_COUNT_MOBILE = By.cssSelector(".masthead__mobile-item .js-mini-cart-link .masthead__bagcount");
	private static final By MAST_HEADER_MENU_CONTENT = By.cssSelector("main-navigation__primary-menu--top-content");
	private static final By HOTSPOT_TRIGGER = By.cssSelector(".hotspots__trigger.hotspots__trigger");
	private static final By HOTSPOT_FIND_OUT_MORE = By.linkText("bra");
	private static final By MAST_HEADER_TRACK_YOUR_ORDER = By.cssSelector(".yCmsComponent.page-footer__primary-nav-item>a[href*='orders']");
	private static final By DID_YOU_MEAN_TEXT = By.cssSelector(".search__suggestion>a");
	private static final By HOTSPOT_IMAGE = By.className("hotspots__image");
	private static final By MOBILE_SEARCH_ICON = By.cssSelector(".masthead__mobile-wrapper li:nth-child(2) .masthead__icon");
	public static final By MOBILE_SEARCH_TEXTBOX = By.cssSelector(".masthead__mobile.masthead--is-touch [name='text']");
	private static final By AUTO_COMPLETE_LINK = By.className("auto-complete__link");
	private static final By MOBILE_SEARCH_COMPLETE_ICON = By.xpath("(//button[@type='submit'])[2]");
	private static final By MOBILE_BURGER_LINK = By.cssSelector("a.masthead__icon-burger-link");
	private static final By MOBILE_BURGER_ACCOUNT_LOGIN_LINK = By.cssSelector(".link.link--nounderline.login_ref");
	private static final By MOBILE_BURGER_ACCOUNT_MENU = By.cssSelector(".main-navigation__action-item--text.ng-scope");
	private static final By MOBILE_STORE_LOCATOR_LINK = By.cssSelector("li.masthead__mobile-item > a.masthead__icon-link > i.masthead__icon > svg");
	private static final By MOBILE_SAVED_ITEMS_LINK = By.cssSelector("a.masthead__icon-link.ng-scope");
	private static final By MOBILE_FOOTER_HELP_ICON = By.cssSelector(".page-footer__primary-nav-title.icon.icon--show");
	private static final By IKANO_CARD_IMAGE = By.cssSelector(".payment-card.ikano");
	private static final By AUTO_SUGGESTION_ITEM = By.cssSelector(".auto-complete__suggestion");
	private static final String CHANGE_SETTINGS = "CHANGE SETTINGS";
	private static final By LOG_OUT_MOBILE = By.cssSelector(".link.link--nounderline.ng-scope");
	private static final By AUTO_COMPLETE_TREND = By.cssSelector(".auto-complete__trend-link");
	private static final By AUTO_COMPLETE_TREND_ITEM = By.cssSelector(".link.link--nounderline");
	private static final By AUTO_COMPLETE_NAME = By.cssSelector(".auto-complete__name");
	private static final By AUTO_SUGGEST_WOMENS_SECTION = By.cssSelector(".masthead__search-wrapper div.auto-complete__departments-wrapper li:nth-child(1)");
	private static final By AUTO_SUGGEST_MENS_SECTION = By.cssSelector(".masthead__search-wrapper div.auto-complete__departments-wrapper li:nth-child(2)");
	private static final By AUTO_SUGGEST_TEEN_SECTION = By.cssSelector(".masthead__search-wrapper div.auto-complete__departments-wrapper li:nth-child(3)");
	private static final By AUTO_SUGGEST_HOME_LIVING_SECTION = By.cssSelector(".masthead__search-wrapper div.auto-complete__departments-wrapper li:nth-child(4)");
	private static final By FILTERED_SECTION = By.xpath(".//*[@class=\"search-filter__item ng-scope search-filter__item--selected\"]/a/div");
	private static final By SEE_ALL_RESULTS = By.cssSelector(".auto-complete__submit+a");
	private static final By MOBILE_AUTO_SUGGEST_WOMENS_SECTION = By.cssSelector(".masthead__mobile .auto-complete__departments-wrapper li:nth-child(1)");
	private static final By MOBILE_AUTO_SUGGEST_MENS_SECTION = By.cssSelector(".masthead__mobile .auto-complete__departments-wrapper li:nth-child(2)");
	private static final By MOBILE_AUTO_SUGGEST_TEEN_SECTION = By.cssSelector(".masthead__mobile .auto-complete__departments-wrapper li:nth-child(3)");
	private static final By MOBILE_AUTO_SUGGEST_HOME_LIVING_SECTION = By.cssSelector(".masthead__mobile .auto-complete__departments-wrapper li:nth-child(4)");
	private static final By MOBILE_FILTERED_SECTION = By.cssSelector(".search-filter select> option[selected=\"selected\"]");
	private static final By HIGHLIGHTED_DEPARTMENT = By.cssSelector(".masthead__search-wrapper .auto-complete__department-button.ng-binding.auto-complete__department-button--active");
	private static final By MOBILE_HIGHLIGHTED_DEPARTMENT = By.cssSelector(".masthead__mobile .auto-complete__department-button.ng-binding.auto-complete__department-button--active");
	private static final By CHANGE_SETTINGS_BUTTON = By.cssSelector(".locale-select__submit-button.button.button--secondary.button--secondary-black.ng-scope");
	public static final By COUNTRY_DROPDOWN_SELECTOR = By.cssSelector(".locale-select__content .select");
	public static final By CATEGORY_T2 = By.xpath("//div[contains(@class,'main-navigation__details-container--open')]//li[@class='main-navigation__secondary-menu-item'][1]/a");
	public static final By CATEGORY_T2_MOBILE = By.xpath("//div[contains(@class,'main-navigation__details-container--open')]//li[@class='main-navigation__secondary-menu-item'][1]/a");
	public static final By CATEGORY_T1_MOBILE = By.xpath("//div[contains(@class,'main-navigation__details-container--open')]//a");
	private By CATEGORY_T3_MOBILE = By.xpath("//div[@class='main-navigation__tertiary-menu-seperator']//ol[contains(li/span,'Department')]//li[2]//a");
	private By CATEGORY_T3_WOMENS_MOBILE = By.xpath("//div[@class='main-navigation__tertiary-menu-seperator']//ol[contains(li/span,'New In')]//li[3]//a");
	private By CLOSE_MAIN_NAVIGATION_MOBILE = By.xpath("//a[@class='main-navigation__close-btn']");
	private static final By NEWLOOK_LOGO = By.xpath("//*[@id='NewLookLogo']/..");
	private By selectedCountryLink = By.cssSelector(".locale-select__flag.locale-select__flag.ng-scope");
	private By brexitDeliveryMessage = By.xpath("//div[@class='alert alert--locale-select ng-scope']//li[@class='alert__item ng-scope']");

	private By CATEGORY_T3_WOMENS = By.xpath("//div[@class='main-navigation__tertiary-menu-seperator']//ol[contains(li/span,' Department')][1]//li[8]//a");
	private By CATEGORY_T3_MENS = By.xpath("//div[@class='main-navigation__tertiary-menu-seperator']//ol[contains(li/span,' Department')][1]//li[2]//a[contains(@href,'clothing')]");

	public void closeMobileBurgerMenu()
	{
		waitForExpectedElement(CLOSE_MAIN_NAVIGATION_MOBILE).click();
	}

	public void navigateToSingInPageByClickingSingInLinkInHeaders()
	{
		clickUserAccountIcon();
		//waitForAndGetElement(webDriver, HEADER_SIGNIN_LINK, DEFAULT_TIMEOUT);
		//webDriver.findElement(HEADER_SIGNIN_LINK).click();
	}

	public void clickUserAccountIcon()
	{
		waitForPageLoad();
		clickByJavaScriptExecutor(waitForExpectedElement(ACCOUNT_HEADER));
		waitForPageLoad();
//        waitForAndGetElement(ACCOUNT_HEADER, 10).click();
	}

	public void navigateToCreateNewAccountPageByClickingHeaderLink()
	{
		//waitForAndGetElement(webDriver.findElement(HEADER_CREATE_NEW_ACCOUNT_LINK), DEFAULT_TIMEOUT);
		log.debug("Navigating to ");
		clickUserAccountIcon();
		waitForPageLoad();
		waitForAndGetElement(CREATE_AN_ACCOUNT, DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(webDriver.findElements(CREATE_AN_ACCOUNT).get(1));
	}

	public void searchForProductUsingRandomProductCode()
	{
		if (IS_MOBILE)
		{
			mobileInsertTextAndSearch(Props.getProp("productCode_brand"));
		}
		else
		{
			searchForProduct(Props.getProp("productCode_brand"));
		}

	}

	public void searchForProductUsingRandomProductCodeOnMobile()
	{
		mobileInsertTextAndSearch(Props.getProp("productCode_brand"));
	}

	public void selectMegaMenu(String category)
	{
		log.info("### Looking for Mega menu item {}", category);
		waitForListLoaded(webDriver.findElements(megaMenu), 3, 5);
		clickByJavaScriptExecutor(getWebDriver().findElements(megaMenu).stream().filter(a -> a.getText().contains(category.trim())).findFirst().get());
	}

	public void selectSecondaryMenu(String category) throws InterruptedException
	{
		log.info("### Looking for category {}", category);
		Thread.sleep(1000);
		clickWhenClickable(webDriver.findElements(secondaryMenu).stream().filter(a -> a.getText().contains(category.trim())).findFirst().get(), 5);
	}

	public void selectRandomProduct()
	{
		if (WebDriverHelper.BROWSER.contains("internet") || WebDriverHelper.BROWSER.contains("firefox") || WebDriverHelper.DEVICE_NAME
				.contains("iPhone") || BROWSER.contains("emulator"))
		{
			pause(3000);
		}
		waitForPresenceOfElement(PRODUCTS_NAMES,DEFAULT_TIMEOUT);
		waitForAndGetElement(webDriver, PRODUCTS_NAMES, DEFAULT_TIMEOUT);
		pause(5000);
		scrollElementIntoView(products);
		clickByJavaScriptExecutor(waitForAndGetElement(PRODUCTS_NAMES, DEFAULT_TIMEOUT));
		waitForPageLoad();
	}

	public void clickFooterLink(String footerLink)
	{
		waitForListLoaded(webDriver.findElements(footerLinks), 7, 5);
		waitForAndGetElement(footerLinks, 2);
		scrollToBottom();
		clickByJavaScriptExecutor(webDriver.findElement(By.linkText(footerLink)));
	}

	public int getNoOfProductsMatchSearch()
	{
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") || WebDriverHelper.BROWSER.contains("internet") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad") || BROWSER.contains("emulator"))
		{
			pause(2000);
		}
		waitForAndGetElement(productItemsDetail, 5);
		if (IS_MOBILE)
		{
			final String str = webDriver.findElements(ALL_PRODUCTS_RESULT_COUNT_MOBILE).get(1).getAttribute("textContent");
			return Integer.parseInt(str.contains(" ") ? str.split(" ")[0] : str);
		}
		else
		{
			return webDriver.findElements(productItemsDetail).size();
		}
	}


	public void searchForProduct(String productName)
	{
		pause(2000);
		waitForAndGetElement(MAST_HEADER_SEARCH_FIELD, 10).sendKeys(productName);
		if (WebDriverHelper.BROWSER.contains("firefox"))
		{
			pause(2000);
		}
		clickByJavaScriptExecutor(webDriver.findElement(MAST_HEADER_SEARCH_BUTTON));
	}

	public void enterTextInSearchBox(String productName)
	{
		waitForAndGetElement(MAST_HEADER_SEARCH_FIELD, 10).sendKeys(productName);
	}

	public void clickSignIn()
	{
		waitForAndGetElement(HEADER_SIGNIN_LINK, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(HEADER_SIGNIN_LINK).isEmpty())
		{
			webDriver.findElement(HEADER_SIGNIN_LINK).click();
		}
	}

	public void clickMegaMenuHeading()
	{
		waitForAndGetElement(megaMenuHeading, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(megaMenuHeading).isEmpty())
		{
			webDriver.findElement(megaMenuHeading).click();
		}
	}

	public List<WebElement> getMegaMenu()
	{
		return webDriver.findElements(MAST_HEADER_MENU_CONTENT);
	}

	public List<WebElement> getBreadCrumbs()
	{
		waitForListLoaded(webDriver.findElements(breadCrumbs), 2, 1);
		return webDriver.findElements(breadCrumbs);
	}

	public void clickMyAccountButtonIfDisplayed()
	{
		if (!webDriver.findElements(MY_ACCOUNT_BUTTON).isEmpty())
		{
			webDriver.findElement(MY_ACCOUNT_BUTTON).click();
		}
	}

	public void signOutOfAccount()
	{
		Actions action = new Actions(getWebDriver());
		WebElement webElement = waitForExpectedElement(ACCOUNT_HEADER);
//            mouseHoverByJavaScript(webElement);
//            waitForAndGetElement(MAST_HEADER_SIGN_OUT_BUTTON,DEFAULT_TIMEOUT).click();
//            WebElement webElement = getWebDriver().findElement(ACCOUNT_HEADER);
		action.moveToElement(webElement).moveToElement(getWebDriver().findElement(MAST_HEADER_SIGN_OUT_BUTTON)).click().build().perform();

	}

	public void clickTrackMyOrderFromHeader()
	{
		WebElement webElement = getWebDriver().findElement(ACCOUNT_HEADER);
		mouseHoverByJavaScript(webElement);
		waitForAndGetElement(MAST_HEADER_MY_ORDERS, DEFAULT_TIMEOUT).click();
	}

	public boolean getLogoutMessageDisplayed()
	{
		waitForAndGetElement(LOGOUT_MESSAGE, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(LOGOUT_MESSAGE).isEmpty())
		{
			return true;
		}
		return false;
	}

	public void hoverOverMegaBrand(String category)
	{
		megaMenu();
		WebElement hoverElement = getWebDriver().findElement(By.cssSelector("a[href='#" + category + "']"));
		hoverElement.click();
	}

	public WebElement megaMenu()
	{
		return waitForExpectedElement(megaMenu);
	}

	public void navigateToMyAccountPage()
	{
		clickUserAccountIcon();
	}

	public void clickTrackMyOrder()
	{
		clickByJavaScriptExecutor(waitForAndGetElement(MAST_HEADER_TRACK_YOUR_ORDER, 10));
	}

	public void clickStoreFinderLink()
	{
		if (IS_MOBILE)
		{
			clickStoreLocatorLinkOnMobile();
		}
		else
		{
			waitForAndGetElement(MAST_HEADER_STORE_LOCATOR, DEFAULT_TIMEOUT).click();
		}
		waitForPageLoad();
	}

	public void clickBagIcon()
	{
		clickByJavaScriptExecutor(checkOutPage.basketIcon());
	}

	public void changeDeliveryCountry(String deliveryCountry)
	{
		pause(5000);
		if (IS_MOBILE)
		{
			pause(3000);
			scrollForFocus(DELIVERY_COUNTRY_DROPDOWN_MOBILE, 5);
			waitForAndGetElement(DELIVERY_COUNTRY_DROPDOWN_MOBILE, 10).click();
			pause(4000);
			new Select(getWebDriver().findElement(DELIVERY_COUNTRY_DROPDOWN_MOBILE)).selectByVisibleText(deliveryCountry);
		}
		else
		{
			waitForAndGetElement(DELIVERY_COUNTRY_DROPDOWN, DEFAULT_TIMEOUT).click();
			pause(4000);
			getWebDriver().findElement(DELIVERY_COUNTRY_SEARCH_BOX).sendKeys(deliveryCountry);
			pause(1000);
			waitForAndGetElement(DELIVERY_COUNTRY_SEARCH_CHOICE, DEFAULT_TIMEOUT).click();
		}
		pause(4000);
		getWebDriver().findElements(By.xpath("//*[contains(@class,'locale-select__submit-button button button--secondary button--secondary-black ng-scope')]")).stream()
				.findFirst().get().click();
		pause(1000);
	}

	public WebElement selectedCountryDropdownValueMobile()
	{
		pause(3000);
		return waitForAndGetElement(DELIVERY_COUNTRY_DROPDOWN_OPTIONS_MOBILE,DEFAULT_TIMEOUT);
	}

	public void selectCountry(String country)
	{
		getWebDriver().findElement(countryOptions).click();
		getWebDriver().findElement(countrySearch).sendKeys(country);
		getWebDriver().findElement(countrySearchSelect).click();
		pause(2000);
		getWebDriver().findElements(By.cssSelector("button")).stream()
				.filter(a -> StringUtils.containsIgnoreCase(a.getText(), CHANGE_SETTINGS))
				.findFirst()
				.get()
				.click();
		pause(1000);
	}

	public String getSignUpButtonText()
	{
		String s = getWebDriver().findElement(SIGN_ME_UP_BUTTON).getText();
		return s;
	}

	public void clickSignMeUpButton()
	{
		scrollForFocus(SIGN_ME_UP_BUTTON, 5);
		getWebDriver().findElement(SIGN_ME_UP_BUTTON).click();
	}

	public void clickSignMeUpButtonOnMobile()
	{
		clickByJavaScriptExecutor(getWebDriver().findElement(SIGN_ME_UP_BUTTON));
	}

	public boolean errorMessageAppears()
	{
		return getWebDriver().findElement(ERROR_MSG).isDisplayed();
	}

	public String getSearchBoxText()
	{
		return getWebDriver().findElement(MAST_HEADER_SEARCH_FIELD).getText();
	}

	public void navigateToDepartmentMobile(String deptName)
	{
		clickBurgerLink();
		pause(1000);
		clickWhenClickable(webDriver.findElements(By.cssSelector(".main-navigation__primary-menu-link-content.main-navigation__arrow")).stream().filter(a -> a.getText().contains(deptName.trim())).findFirst().get(), 5);
		pause(1000);
		waitForPageLoad();
		waitForPresenceOfElement(CATEGORY_T2_MOBILE,DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(waitForAndGetElement(CATEGORY_T2_MOBILE, 10));
		waitForPageLoad();
		if (deptName.equalsIgnoreCase("Womens"))
		{
			waitForPresenceOfElement(CATEGORY_T3_WOMENS_MOBILE,DEFAULT_TIMEOUT);
			clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T3_WOMENS_MOBILE));
		}
		else
		{
			waitForPresenceOfElement(CATEGORY_T3_MOBILE,DEFAULT_TIMEOUT);
			clickByJavaScriptExecutor(webDriver.findElements(CATEGORY_T3_MOBILE).stream().filter(a -> isElementVisible(a, 1)).findFirst().get());
		}
		waitForPageLoad();
	}

	public void navigateToDepartmentFromMegaMenu(String dept)
	{
		switch (dept)
		{
			case "Mens":
				hoverOnElement(waitForAndGetElement(MAST_HEADER_MENS_MENU, DEFAULT_TIMEOUT));
				clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T2));
				break;
			case "Womens":
				hoverOnElement(waitForExpectedElement(MAST_HEADER_WOMENS_MENU, DEFAULT_TIMEOUT));
				clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T2));
				break;
			case "Teens":
				hoverOnElement(waitForExpectedElement(MAST_HEADER_TEENS_MENU, DEFAULT_TIMEOUT));
				clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T2));
				break;
		}
	}

	public String navigateToDepartmentFromMegaMenuTagAndGetDataTrackerCode(String dept,String tag)
	{
		String trackerCode = null;
		switch (dept)
		{
			case "Mens":
				switch (tag)
				{
					case "T3":
						clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_MENS_MENU,DEFAULT_TIMEOUT));
						hoverOnElement(waitForAndGetElement(MAST_HEADER_MENS_MENU, DEFAULT_TIMEOUT));
						hoverOnElement(waitForExpectedElement(CATEGORY_T2,DEFAULT_TIMEOUT));
						hoverOnElement(waitForExpectedElement(CATEGORY_T3_MENS));
						pause(1000);
						trackerCode =  getAttributeValue(CATEGORY_T3_MENS,"data-trackercode");
						clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T3_MENS,DEFAULT_TIMEOUT));
						return trackerCode;
					case  "T1":
						clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_MENS_MENU,DEFAULT_TIMEOUT));
						return getAttributeValue(MAST_HEADER_MENS_MENU,"data-trackercode");
					case  "T2":
						clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_MENS_MENU,DEFAULT_TIMEOUT));
						hoverOnElement(waitForAndGetElement(MAST_HEADER_MENS_MENU, DEFAULT_TIMEOUT));
						hoverOnElement(waitForExpectedElement(CATEGORY_T2,DEFAULT_TIMEOUT));
						pause(1000);
						trackerCode =  getAttributeValue(CATEGORY_T2,"data-trackercode");
						clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T2,DEFAULT_TIMEOUT));
						return trackerCode;
				}
			case "Womens":
				switch (tag)
				{
					case "T3":
						clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_WOMENS_MENU));
						hoverOnElement(waitForAndGetElement(MAST_HEADER_WOMENS_MENU, DEFAULT_TIMEOUT));
						hoverOnElement(waitForExpectedElement(CATEGORY_T2,DEFAULT_TIMEOUT));
						hoverOnElement(waitForExpectedElement(CATEGORY_T3_WOMENS));
						pause(1000);
						trackerCode =  getAttributeValue(CATEGORY_T3_WOMENS,"data-trackercode");
						clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T3_WOMENS,DEFAULT_TIMEOUT));
						return trackerCode;
					case  "T1":
						clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_WOMENS_MENU,DEFAULT_TIMEOUT));
						return getAttributeValue(MAST_HEADER_WOMENS_MENU,"data-trackercode");
					case  "T2":
						clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_WOMENS_MENU));
						hoverOnElement(waitForAndGetElement(MAST_HEADER_WOMENS_MENU, DEFAULT_TIMEOUT));
						hoverOnElement(waitForExpectedElement(CATEGORY_T2,DEFAULT_TIMEOUT));
						pause(1000);
						trackerCode =  getAttributeValue(CATEGORY_T2,"data-trackercode");
					   clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T2,DEFAULT_TIMEOUT));
					   return trackerCode;
				}
			default:
				return null;
		}
	}

	public void navigateToDepartmentFromMegaMenuTag(String dept,String tag)
	{
		switch (dept)
		{
			case "Mens":
				switch (tag)
				{
					case "T3":
						clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_MENS_MENU,DEFAULT_TIMEOUT));
						hoverOnElement(waitForAndGetElement(MAST_HEADER_MENS_MENU, DEFAULT_TIMEOUT));
						hoverOnElement(waitForExpectedElement(CATEGORY_T2,DEFAULT_TIMEOUT));
						hoverOnElement(waitForExpectedElement(CATEGORY_T3_MENS));
						pause(1000);
						clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T3_MENS,DEFAULT_TIMEOUT));
						pause(1000);
						break;
					case  "T1":
						clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_MENS_MENU,DEFAULT_TIMEOUT));
						pause(1000);
						break;
					case  "T2":
						clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_MENS_MENU,DEFAULT_TIMEOUT));
						hoverOnElement(waitForAndGetElement(MAST_HEADER_MENS_MENU, DEFAULT_TIMEOUT));
						hoverOnElement(waitForExpectedElement(CATEGORY_T2,DEFAULT_TIMEOUT));
						pause(1000);
						clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T2,DEFAULT_TIMEOUT));
						pause(1000);
						break;
				}
				break;
			case "Womens":
				switch (tag)
				{
					case "T3":
						clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_WOMENS_MENU));
						hoverOnElement(waitForAndGetElement(MAST_HEADER_WOMENS_MENU, DEFAULT_TIMEOUT));
						hoverOnElement(waitForExpectedElement(CATEGORY_T2,DEFAULT_TIMEOUT));
						hoverOnElement(waitForExpectedElement(CATEGORY_T3_WOMENS));
						pause(1000);
						clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T3_WOMENS,DEFAULT_TIMEOUT));
						break;
					case  "T1":
						clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_WOMENS_MENU,DEFAULT_TIMEOUT));
						break;
					case  "T2":
						clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_WOMENS_MENU));
						hoverOnElement(waitForAndGetElement(MAST_HEADER_WOMENS_MENU, DEFAULT_TIMEOUT));
						hoverOnElement(waitForExpectedElement(CATEGORY_T2,DEFAULT_TIMEOUT));
						pause(1000);
						clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T2,DEFAULT_TIMEOUT));
						break;
				}
				break;
		}
	}

	public String navigateToDepartmentFromMegaMenuTagAndGetDataTrackerCodeMobile(String dept,String tag)
	{
		String trackerCode = null;

		clickBurgerLink();
		pause(1000);
		clickWhenClickable(webDriver.findElements(By.cssSelector(".main-navigation__primary-menu-link-content.main-navigation__arrow")).stream().filter(a -> a.getText().contains(dept.trim())).findFirst().get(), 5);
		pause(1000);
		clickByJavaScriptExecutor(waitForAndGetElement(CATEGORY_T1_MOBILE, 10));
		clickBurgerLink();
		pause(1000);
		clickWhenClickable(webDriver.findElements(By.cssSelector(".main-navigation__primary-menu-link-content.main-navigation__arrow")).stream().filter(a -> a.getText().contains(dept.trim())).findFirst().get(), 5);
		pause(1000);
		waitForPageLoad();

		switch (dept)
		{
			case "Mens":
				switch (tag)
				{
					case "T3":
						waitForPresenceOfElement(CATEGORY_T2_MOBILE, DEFAULT_TIMEOUT);
						clickByJavaScriptExecutor(waitForAndGetElement(CATEGORY_T2_MOBILE, 10));
						waitForPageLoad();
						pause(1000);
						waitForPresenceOfElement(CATEGORY_T3_MENS, DEFAULT_TIMEOUT);
						trackerCode = getAttributeValue(CATEGORY_T3_MENS, "data-trackercode");
						clickByJavaScriptExecutor(webDriver.findElements(CATEGORY_T3_MENS).stream().filter(a -> isElementVisible(a, 1)).findFirst().get());
						return trackerCode;
					case "T1":
						waitForPresenceOfElement(CATEGORY_T1_MOBILE, DEFAULT_TIMEOUT);
						trackerCode = getAttributeValue(CATEGORY_T1_MOBILE, "data-trackercode");
						clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T1_MOBILE));
						waitForPageLoad();
						return trackerCode;
				}
			case "Womens":
				switch (tag)
				{
					case "T3":
						waitForPresenceOfElement(CATEGORY_T2_MOBILE, DEFAULT_TIMEOUT);
						clickByJavaScriptExecutor(waitForAndGetElement(CATEGORY_T2_MOBILE, 10));
						waitForPageLoad();
						pause(1000);
						waitForPresenceOfElement(CATEGORY_T3_WOMENS, DEFAULT_TIMEOUT);
						trackerCode = getAttributeValue(CATEGORY_T3_WOMENS, "data-trackercode");
						clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T3_WOMENS));
						return trackerCode;
					case "T1":
						waitForPresenceOfElement(CATEGORY_T1_MOBILE, DEFAULT_TIMEOUT);
						trackerCode = getAttributeValue(CATEGORY_T1_MOBILE, "data-trackercode");
						clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T1_MOBILE));
						waitForPageLoad();
						return trackerCode;
				}
			default:
				return null;
		}
	}

	public void navigateToDepartmentFromMegaMenuTagMobile(String dept,String tag)
	{
		clickBurgerLink();
		pause(1000);
		clickWhenClickable(webDriver.findElements(By.cssSelector(".main-navigation__primary-menu-link-content.main-navigation__arrow")).stream().filter(a -> a.getText().contains(dept.trim())).findFirst().get(), 5);
		pause(1000);
		clickByJavaScriptExecutor(waitForAndGetElement(CATEGORY_T1_MOBILE, 10));
		clickBurgerLink();
		pause(1000);
		clickWhenClickable(webDriver.findElements(By.cssSelector(".main-navigation__primary-menu-link-content.main-navigation__arrow")).stream().filter(a -> a.getText().contains(dept.trim())).findFirst().get(), 5);
		pause(1000);
		waitForPageLoad();

		switch (dept)
		{
			case "Mens":
				switch (tag)
				{
					case "T3":
						waitForPresenceOfElement(CATEGORY_T2_MOBILE, DEFAULT_TIMEOUT);
						clickByJavaScriptExecutor(waitForAndGetElement(CATEGORY_T2_MOBILE, 10));
						waitForPageLoad();
						pause(1000);
						waitForPresenceOfElement(CATEGORY_T3_MENS, DEFAULT_TIMEOUT);
						clickByJavaScriptExecutor(webDriver.findElements(CATEGORY_T3_MENS).stream().filter(a -> isElementVisible(a, 1)).findFirst().get());
						break;
					case "T1":
						waitForPresenceOfElement(CATEGORY_T1_MOBILE, DEFAULT_TIMEOUT);
						clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T1_MOBILE));
						waitForPageLoad();
						break;
				}
				break;
			case "Womens":
				switch (tag)
				{
					case "T3":
						waitForPresenceOfElement(CATEGORY_T2_MOBILE, DEFAULT_TIMEOUT);
						clickByJavaScriptExecutor(waitForAndGetElement(CATEGORY_T2_MOBILE, 10));
						waitForPageLoad();
						pause(1000);
						waitForPresenceOfElement(CATEGORY_T3_WOMENS, DEFAULT_TIMEOUT);
						clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T3_WOMENS));
						break;
					case "T1":
						waitForPresenceOfElement(CATEGORY_T1_MOBILE, DEFAULT_TIMEOUT);
						clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T1_MOBILE));
						waitForPageLoad();
						break;
				}
		}
	}

	public String getDidYouMeanText()
	{
		String didYouMeanText = waitForAndGetElement(DID_YOU_MEAN_TEXT, DEFAULT_TIMEOUT).getText();
		return didYouMeanText = didYouMeanText.replace("\"", "");
	}

	public void clickHotSpotTrigger()
	{
		scrollForFocus(HOTSPOT_IMAGE, DEFAULT_TIMEOUT);
		Actions action = new Actions(webDriver);
		action.moveToElement(getWebDriver().findElement(HOTSPOT_TRIGGER)).build().perform();
		pause(1000);
		waitForAndGetElement(HOTSPOT_FIND_OUT_MORE, 10).click();
	}

	public void navigateToUrl(String url)
	{
		goToSite(System.getProperty("site.url", Props.getProp("site.url")) + url);
	}

	public void clickMobileSearchIcon()
	{
		try
		{
			waitForPageLoad();
			pause(3000);
			clickByJavaScriptExecutor(getWebDriver().findElement(MOBILE_SEARCH_ICON));
		}
		catch (StaleElementReferenceException e)
		{
			refresh();
			waitForPageLoad();
			pause(3000);
			clickByJavaScriptExecutor(getWebDriver().findElement(MOBILE_SEARCH_ICON));
		}
		catch (Exception e)
		{
			Assert.fail("Not able to click on mobile search icon");
		}
	}

	public void clickSearchField()
	{
		waitForAndGetElement(MAST_HEADER_SEARCH_FIELD, DEFAULT_TIMEOUT).click();
	}

	public void mobileInsertText(String searchText)
	{
		waitForAndGetElement(MOBILE_SEARCH_TEXTBOX, DEFAULT_TIMEOUT).sendKeys(searchText);
	}

	public void mobileInsertTextAndSearch(String productName)
	{
		waitForPageLoad();
		pause(2000);
		clickMobileSearchIcon();
		waitForPageLoad();
		waitForExpectedElement(MOBILE_SEARCH_TEXTBOX, 10).sendKeys(productName);
		waitForPageLoad();
		pause(2000);
		waitForExpectedElement(AUTO_COMPLETE_LINK, 10);
		clickByJavaScriptExecutor(waitForExpectedElement(MOBILE_SEARCH_COMPLETE_ICON));
		waitForPageLoad();
	}

	public void clickBurgerLink()
	{
		if (IS_MOBILE)
		{
			pause(3000);
			waitForAndGetElement(MOBILE_BURGER_LINK, DEFAULT_TIMEOUT);
			try
			{
				clickByJavaScriptExecutor(waitForAndGetElement(MOBILE_BURGER_LINK, DEFAULT_TIMEOUT));
			}
			catch (Exception e)
			{
				clickByJavaScriptExecutor(waitForAndGetElement(MOBILE_BURGER_LINK, DEFAULT_TIMEOUT));
			}
		}
		else
		{
			waitForExpectedElement(MOBILE_BURGER_LINK, DEFAULT_TIMEOUT).click();
		}
	}

	public void mobileNavigateToLoginPage()
	{
		waitForPageLoad();
		clickBurgerLink();
		pause(2000);
		clickByJavaScriptExecutor(getWebDriver().findElement(MOBILE_BURGER_ACCOUNT_LOGIN_LINK));
		waitForPageLoad();
	}

	public void clickCreateAnAccountButton()
	{
		pause(4000);
		waitForAndGetElement(CREATE_AN_ACCOUNT, DEFAULT_TIMEOUT);
		waitForElementVisible(CREATE_AN_ACCOUNT,DEFAULT_TIMEOUT);
		try
		{
			clickByJavaScriptExecutor(webDriver.findElements(CREATE_AN_ACCOUNT).get(1));
		}
		catch (Exception e)
		{
			clickByJavaScriptExecutor(webDriver.findElements(CREATE_AN_ACCOUNT).get(1));
		}
		waitForPageLoad();
	}

	public void clickMyAccountBreadCrumb()
	{
		pause(3000);
		try
		{
			waitForAndGetElement(breadCrumbs, DEFAULT_TIMEOUT);
			clickByJavaScriptExecutor(webDriver.findElements(breadCrumbs).get(1));
		}
		catch (Exception e)
		{
			clickByJavaScriptExecutor(webDriver.findElements(breadCrumbs).get(1));
		}

	}

	public void navigateToMyAccountFromHeaderOnMobile()
	{
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad") || BROWSER.contains("emulator"))
		{
			pause(2000);
		}
		clickBurgerLink();
		clickByJavaScriptExecutor(getWebDriver().findElement(MOBILE_BURGER_ACCOUNT_MENU));
		waitForPageLoad();
	}

	public void clickStoreLocatorLinkOnMobile()
	{
		pause(5000);
		try
		{
			waitForAndGetElement(MOBILE_STORE_LOCATOR_LINK, DEFAULT_TIMEOUT).click();
		}
		catch (Exception e)
		{
			waitForAndGetElement(MOBILE_STORE_LOCATOR_LINK, DEFAULT_TIMEOUT).click();
		}
	}

	public void clickFooterOnMobile(String footer)
	{
		clickByJavaScriptExecutor(webDriver.findElements(MOBILE_FOOTER_HELP_ICON).stream().filter(a -> a.getText().contains(footer)).findFirst().get());
	}

	public boolean isIkanoCardIsDisplayed()
	{
		return waitForAndGetElement(IKANO_CARD_IMAGE, DEFAULT_TIMEOUT).isDisplayed();
	}

	public boolean isItemSuggestionDisplayed(String text)
	{
		pause(2000);
		List<WebElement> autoCompleteTextItems = getWebDriver().findElements(AUTO_SUGGESTION_ITEM);
		return autoCompleteTextItems.stream()
				.map(WebElement::getText)
				.anyMatch(elementText -> elementText.equalsIgnoreCase(text));
	}

	public void clickAutoSuggestion(String item)
	{
		waitForAndGetElement(AUTO_SUGGESTION_ITEM, DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(webDriver.findElements(AUTO_COMPLETE_TREND_ITEM).stream().filter(a -> a.getText().equals(item)).findFirst().get());
	}

	public void clickAutoCompleteTrend(String text)
	{
		waitForAndGetElement(AUTO_COMPLETE_TREND, DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(webDriver.findElements(AUTO_COMPLETE_TREND).stream().filter(a -> a.getText().equals(text)).findFirst().get());
	}

	public void clickAutoCompleteName()
	{
		pause(1500);
		waitForAndGetElement(AUTO_COMPLETE_NAME, DEFAULT_TIMEOUT);
		webDriver.findElements(AUTO_COMPLETE_NAME).get(0).click();
	}

	public void clickMySavedItems()
	{
		clickByJavaScriptExecutor(waitForAndGetElement(MOBILE_SAVED_ITEMS_LINK, DEFAULT_TIMEOUT));
	}

	public void logOutFromAccountMobile()
	{
		clickBurgerLink();
		pause(3000);
		clickByJavaScriptExecutor(waitForAndGetElement(LOG_OUT_MOBILE, DEFAULT_TIMEOUT));
	}

	public void clickOnAutoSuggestTab(String selectDepartment)
	{
		switch (selectDepartment.toUpperCase())
		{
			case ("MENS"):
				if (IS_MOBILE)
				{
					waitForAndGetElement(MOBILE_AUTO_SUGGEST_MENS_SECTION, DEFAULT_TIMEOUT).click();
				}
				else
				{
					waitForAndGetElement(AUTO_SUGGEST_MENS_SECTION, DEFAULT_TIMEOUT).click();
				}
				break;
			case "WOMENS":
				if (IS_MOBILE)
				{
					waitForAndGetElement(MOBILE_AUTO_SUGGEST_WOMENS_SECTION, DEFAULT_TIMEOUT).click();
				}
				else
				{
					waitForAndGetElement(AUTO_SUGGEST_WOMENS_SECTION, DEFAULT_TIMEOUT).click();
				}
				break;
			case "TEENS":
				if (IS_MOBILE)
				{
					waitForAndGetElement(MOBILE_AUTO_SUGGEST_TEEN_SECTION, DEFAULT_TIMEOUT).click();
				}
				else
				{
					waitForAndGetElement(AUTO_SUGGEST_TEEN_SECTION, DEFAULT_TIMEOUT).click();
				}
				break;
			case "HOME&LIVING":
				if (IS_MOBILE)
				{
					waitForAndGetElement(MOBILE_AUTO_SUGGEST_HOME_LIVING_SECTION, DEFAULT_TIMEOUT).click();
				}
				else
				{
					waitForAndGetElement(AUTO_SUGGEST_HOME_LIVING_SECTION, DEFAULT_TIMEOUT).click();
				}
				break;
			default:
				break;
		}
	}

	public String getTextOfFilteredDepartment()
	{
		if (IS_MOBILE)
		{
			return waitForAndGetElement(MOBILE_FILTERED_SECTION, DEFAULT_TIMEOUT).getText();
		}
		else
		{
			return waitForAndGetElement(FILTERED_SECTION, DEFAULT_TIMEOUT).getText();
		}
	}

	public void seeAllResults()
	{
		waitForAndGetElement(SEE_ALL_RESULTS, DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(waitForExpectedElement(SEE_ALL_RESULTS, DEFAULT_TIMEOUT));
	}

	public String getTextOfSelectedAutoSuggestDepartment()
	{
		if (IS_MOBILE)
		{
			return waitForAndGetElement(MOBILE_HIGHLIGHTED_DEPARTMENT, DEFAULT_TIMEOUT).getText();
		}
		else
		{
			return waitForAndGetElement(HIGHLIGHTED_DEPARTMENT, DEFAULT_TIMEOUT).getText();
		}
	}

	public boolean isKlarnaLogoPresentInFooter()
	{
		scrollToBottom();
		return waitForAndGetElement(By.xpath("//*[contains(@class,'klarna-footer-svg')]"), DEFAULT_TIMEOUT).isDisplayed();
	}

	public boolean gotItButtonIsPresent()
	{
		return isElementPresent(GOT_IT_BUTTON);
	}

	public void clickGotItButton()
	{
		waitForAndGetElement(GOT_IT_BUTTON, DEFAULT_TIMEOUT).click();
	}

	public void hoverOnDepartmentFromMegaMenu(String dept)
	{
		if (IS_MOBILE)
		{
			clickBurgerLink();
			pause(1000);
			clickWhenClickable(webDriver.findElements(By.cssSelector(".main-navigation__primary-menu-link-content.main-navigation__arrow")).stream().filter(a -> a.getText().contains(dept.trim())).findFirst().get(), 5);
			waitForPageLoad();
			clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T2_MOBILE));
			waitForPageLoad();
		}
		else
		{
			switch (dept)
			{
				case "Mens":
					hoverOnElement(waitForAndGetElement(MAST_HEADER_MENS_MENU, DEFAULT_TIMEOUT));
					break;
				case "Womens":
					hoverOnElement(waitForExpectedElement(MAST_HEADER_WOMENS_MENU, DEFAULT_TIMEOUT));
					break;
				case "Teens":
					hoverOnElement(waitForExpectedElement(MAST_HEADER_TEENS_MENU, DEFAULT_TIMEOUT));
					break;
			}
			waitForPageLoad();
		}
	}

	public void navigateToDepartmentHomePageFromMegaMenu(String dept)
	{
		if (IS_MOBILE)
		{
			clickBurgerLink();
			pause(1000);
			clickWhenClickable(webDriver.findElements(By.cssSelector(".main-navigation__primary-menu-link-content.main-navigation__arrow")).stream().filter(a -> a.getText().contains(dept.trim())).findFirst().get(), 5);
			waitForPageLoad();
			clickByJavaScriptExecutor(waitForExpectedElement(CATEGORY_T1_MOBILE));
			waitForPageLoad();
		}
		else
		{
			switch (dept)
			{
				case "Mens":
					clickByJavaScriptExecutor(waitForAndGetElement(MAST_HEADER_MENS_MENU, DEFAULT_TIMEOUT));
					break;
				case "Womens":
					clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_WOMENS_MENU, DEFAULT_TIMEOUT));
					break;
				case "Teens":
					clickByJavaScriptExecutor(waitForExpectedElement(MAST_HEADER_TEENS_MENU, DEFAULT_TIMEOUT));
					break;
			}
		}
	}

	public void clickOnNewLookLogo()
	{
		pause(4000);
		waitForPresenceOfElement(NEWLOOK_LOGO, DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(waitForExpectedElement(NEWLOOK_LOGO));
	}

	public void selectCountryLanguageLinkFooter()
	{
		scrollToBottom();
		try
		{
			waitForExpectedElement(selectedCountryLink).click();
		}
		catch (Exception e)
		{
			waitForExpectedElement(selectedCountryLink).click();
		}

	}

	public String getBrexitMessage()
	{
		if (IS_MOBILE)
		{
			return waitForExpectedElement(brexitDeliveryMessage).getText();
		}
		else
		{
			return waitForExpectedElement(brexitDeliveryMessage).getText();
		}
	}

	public boolean appFooterLinkDisplayed()
	{
		try
		{
			return waitForAndGetElement(appFooterLink, DEFAULT_TIMEOUT).isDisplayed();
		}
		catch (Exception e)
		{
			return false;
		}
	}
	

}
