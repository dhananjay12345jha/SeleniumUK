package com.salmon.test.page_objects.gui;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.WebElementNotFoundException;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.models.gui.CheckoutModel;
import com.salmon.test.properties.AlertsProperties;
import com.salmon.test.properties.CardProperties;


import static com.jayway.restassured.RestAssured.given;
import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;
import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static com.salmon.test.page_objects.gui.Features.CHECKOUT_NEW_DESIGN_FEATURE;
import static com.salmon.test.page_objects.gui.Features.CHECKOUT_USE_CARD_CTA_FEATURE;
import static com.salmon.test.page_objects.gui.Features.PEAK_CAPPING_FEATURE;
import static org.junit.Assert.assertEquals;

public class CheckoutAndPaymentsPage extends PageObject
{
	private final Logger log = LogManager.getLogger(CheckoutAndPaymentsPage.class.getName());

	private static final By PAY_WITH_OPTIONS = By.className("checkout__payment-options");
	private static final String TEST_ENVIRONMENT = "testEnv";
	private static String TEST_ENV;
	private static final By PAY_WITH_PAYPAL_OPTION = By.className("nl__select-paypal");
	private static final By PAY_WITH_NEW_LOOK_STORE_CARD_OPTION = By.className("nl__select-store-card");
	private static final By PAY_WITH_CREDIT_CARD_OPTION = By.className("nl__select-credit-debit-card");
	private static final By CHANGE_BILLING_ADDRESS_BUTTON = By.cssSelector(
			".checkout__payment-btn.checkout__payment-btn--change-billing.nl__change-billing-address.button-link.button-link--billing-address.ng-scope");
	private static final By CHECKOUT_PAYMENT_BILLING_ADDRESS_POPUP = By.className("modal--large");
	private static final By CHECKOUT_PAYMENT_POPUP_USE_THIS_ADDRESS_BUTTON = By.className(
			"checkout__delivery-btn--use-address");
	private static final By CHECKOUT_PAYMENT_POPUP_ADDRESS_WITHOUT_TITLE = By.xpath(
			"//label[@class='label radio__label']/strong[not(contains(text(),'M'))]");

	private static final By CHECKOUT_PAYMENT_POPUP_ADD_NEW_ADDRESS_BUTTON = By.className(
			"checkout__delivery-btn--add-address");
	private static final By SELECTED_COLLECTION_STORE = By.cssSelector(".collection__tab__collect.collection__tab--selected");
	private static final By SELECT_THE_STORE_BUTTON = By.className(
			"button--collection-selection");
	private static final By CHECKOUT_PAYMENT_POPUP_BILLING_ADDRESS_LABEL = By.cssSelector(
			".modal__content >.modal__text.ng-scope");
	private static final By CHECKOUT_PAYMENT_BILLING_ADDRESS_POPUP_HEADER_LABEL = By.className("modal__title");
	private static final By CHECKOUT_PAYMENT_POPUP_COUNTRY_LABEL = By.xpath(
			"//*[@id='editAddress']//*[@for='country']");
	private static final By CHECKOUT_PAYMENT_POPUP_TITLE_LABEL = By.xpath(
			"//*[@id='editAddress']//*[@for='titleCode']");
	private static final By CHECKOUT_PAYMENT_POPUP_FIRST_NAME_LABEL = By.xpath(
			"//*[@id='editAddress']//*[@for='firstName']");
	private static final By CHECKOUT_PAYMENT_POPUP_LAST_NAME_LABEL = By.xpath(
			"//*[@id='editAddress']//*[@for='lastName']");
	private static final By CHECKOUT_PAYMENT_POPUP_ADDRESS_FINDER_LABEL = By.xpath(
			"//*[@id='editAddress']//*[@for='addressLookup']");
	private static final By CHECKOUT_PAYMENT_POPUP_ENTER_ADDRESS_MANUALLY_LABEL = By.xpath(
			"//*[@id='editAddress']//*[@class='button-link ng-scope']");
	private static final By CHECKOUT_PAYMENT_POPUP_USE_THIS_ADDRESS_BUTTON_LABEL = By.xpath(
			"//*[@id='editAddress']//*[@name='useAddress']");
	private static final By CHECKOUT_PAYMENT_POPUP_CANCEL_LABEL = By.className(
			"checkout__delivery-btn--cancel-address");
	private static final By CHECKOUT_PAYMENT_POPUP_CROSS_LABEL = By.className(
			"modal__close");
	private static final By TITLE_DROPDOWN_FIELD = By.id("titleCode");
	private static final By CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_FIRST_NAME_FIELD = By.xpath("(//*[@id='firstName'])[1]");
	private static final By CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_LAST_NAME_FIELD = By.xpath("(//*[@id='lastName'])[1]");
	private static final By DELIVERY_ADDRESS_FINDER_FIELD = By.id("addressLookup");
	private static final By DELIVERY_ADDRESS_LIST = By.xpath("//*[@data-ng-show='!modal']");
	private static final By DELIVERY_ADDRESS_USE_THIS_ADDRESS_BUTTON = By.cssSelector(
			".button.button--secondary.button--secondary-black.modal__button.checkout__delivery-btn.checkout__delivery-btn--use-address.ng-scope");
	private static final By MOBILE_NUMBER_FIELD = By.id("mobilePhone");
	private static final By PAYMENT_METHOD = By.className("checkout__payment-choice");
	private static final By COLLECTION_BUTTON = By.className("tab-text__checkout--collection");
	private static final By COLLECTION_SEARCH_BUTTON = By.className("button--store-search");
	private static final By BILLING_ADDRESS = By.xpath("(//*[@placeholder='Start typing your address or postcode'])[2]");
	private static final By CARD_TYPE = By.cssSelector("select#card-type");
	private static final By CARD_NUMBER_INPUT_FIELD = By.id("card_number");
	private static final By CARD_EXPIRE_MONTH_FIELD = By.id("card_expiry_month");
	private static final By CARD_EXPIRE_YEAR_FIELD = By.id("card_expiry_year");
	private static final By CONFIRM_SECURITY_CODE = By.id("CV2");
	private static final By BILLING_ADDRESS_LIST = By.xpath("//*[@data-ng-show='!modal']");
	private static final By BILLING_ADDRESS_USE_THIS_CARD_BUTTON = By.cssSelector(
			".checkout__delivery-modal-actions[data-ng-class*='checkout__hidden-field'] > .button--secondary");
	private static final By DELIVERY_ADDRESS_POSTCODE = By.id("postalCode");
	private static final By GIFT_CARD_BALANCE_BEFORE_PURCHASE = By.className("gift-card__apply-balance-value");
	public static final By COLLECTION_SEARCH_RESULT_MAP = By.className("gm-style");
	private static final By GOOGLE_MAP_TOP_BUTTONS = By.cssSelector(".gmnoprint > div > button");
	private static final By GOOGLE_MAP_CLICK_TO_SEE_IN_GOOGLE_MAPS_BUTTON = By.cssSelector(".gm-style > div > a");
	private static final By GOOGLE_MAP_DATA_LABEL = By.xpath("//*[@title='Map Data']//following-sibling::span");
	private static final By GOOGLE_MAP_TERMS_OF_USE = By.cssSelector(".gm-style-cc > div > a");
	private static final By COLLECTION_MAP_PINS = By.xpath(
			"//*[@aria-roledescription='map']//*[@src='https://maps.gstatic.com/mapfiles/transparent.png']");
	private static final By COLLECTION_STORE_PINS_DISPALYED = By.className("store-results__pin-text");
	private static final By COLLECTION_NEWLOOK_MAP_PINS = By.cssSelector(
			"div.gm-style > div > div > div > div > div > img:not([role])");
	private static final By CHOOSE_ANOTHER_COLLECTION_POINT = By.className("collection-selection__link");
	private static final By NEW_SEARCH_SAVED_COLLECTION_POINT = By.xpath("//button[text()='New Search']");
	private static final By UK_STANDARD_DELIVERY_OPTION = By.cssSelector(".radio__label[for*='mode0']");
	private static final By SELECT_DELIVERY_DATE = By.xpath(
			"//*[@class='delivery-option ng-scope']");
	private static final By ADDRESS_LINE_1 = By.id("line1");
	private static final By ADDRESS_LINE_2 = By.id("line2");
	private static final By POSTAL_CODE = By.id("postalCode");
	private static final By TOWN_FIELD = By.id("town");
	private static final By ADD_NEW_ADDRESS_BUTTON = By.cssSelector(
			".button.button--secondary.button--secondary-black.modal__button.checkout__delivery-btn.checkout__delivery-btn--use-address.ng-scope.nl__add-new-address-save");
	private static final By DELIVERY_ADDRESS_POPUP = By.cssSelector(".modal.modal--visible");
	private static final By CHANGE_DELIVERY_ADDRESS_BUTTON = By.cssSelector(
			"#delivery2 > div > a.checkout__delivery-link");
	private static final By ADD_NEW_ADDRESS_BUTTON_IN_POPUP = By.cssSelector(
			".modal--visible > .modal__content > div > ng-transclude > form > .modal__actions > button.button--secondary");
	private static final By POPUP_TITLE_DROPDOWN_FIELD = By.cssSelector(
			"#editAddress > div > div > div > div > div > #titleCode");
	private static final By POPUP_DELIVERY_ADDRESS_FIRST_NAME_FIELD = By.cssSelector(
			"#editAddress > div > div > div > div > div > #firstName");
	private static final By POPUP_DELIVERY_ADDRESS_LAST_NAME_FIELD = By.cssSelector(
			"#editAddress > div > div > div > div > div > #lastName");
	private static final By POPUP_ADDRESS_LINE_1 = By.cssSelector("#editAddress > div > div > div > div > div > #line1");
	private static final By POPUP_ADDRESS_LINE_2 = By.cssSelector("#editAddress > div > div > div > div > div > #line2");
	private static final By POPUP_POSTAL_CODE = By.cssSelector(
			"#editAddress > div > div > div > div > div > #postalCode");
	private static final By POPUP_TOWN_FIELD = By.cssSelector("#editAddress > div > div > div > div > div > #town");
	private static final By POPUP_ADD_NEW_ADDRESS_BUTTON = By.cssSelector(
			"#editAddress > div > div > div > button.button--secondary");
	private static final By SAVE_MY_CARD_DETAILS_FOR_NEXT_TIME_RADIO_BUTTON = By.cssSelector(".checkbox--checkout");
	private static final By USE_THIS_CARD_BUTTON = By.cssSelector(
			".button.button--secondary-black.checkout__payment-btn.checkout__payment-btn--save-card.nl__add-new-card-save");
	private static final By PLACE_ORDER_BUTTON = By.xpath(
			"//*[contains(@class,'button button--primary checkout__total-submit nl__place-order ng-scope')]");
	private static final By CHECKOUT_PAYMENT_POPUP_COUNTRY_FIELD = By.cssSelector(
			".nl-select__choice.ui-select-match.ui-select-toggle.nl-select-toggle.ng-scope");
	private static final By CHECKOUT_PAYMENT_POPUP_COUNTRY_SEARCH_FIELD = By.cssSelector(
			".nl-select__search-field > input");
	private static final By CHECKOUT_PAYMENT_POPUP_COUNTRY_SEARCH_RESULT = By.className("ui-select-highlight");
	private static final By POPUP_APPLY_CHANGE_BUTTON = By.cssSelector(
			".button.button--secondary.button--secondary-black.modal__redirect.checkout__delivery-btn.checkout__delivery-btn--country-select.ng-scope");
	private static final By DELIVERY_COUNTRY_FIELD = By.cssSelector(
			".nl-select__choice.ui-select-match.ui-select-toggle.nl-select-toggle");
	private static final By DELIVERY_COUNTRY_SEARCH_FIELD = By.cssSelector(".nl-select__search-field > input");
	private static final By DELIVERY_COUNTRY_SEARCH_RESULT = By.cssSelector(".ui-select-highlight");
	private static final By SAVED_CARD_CLASS = By.className("checkout__payment-card-summary");
	private static final By SAVED_CARD_LAST_4_DIGITS = By.cssSelector(".checkout__payment-card-summary .ng-binding");
	private static final By PAYMENT_NEW_DESIGN = By.cssSelector(".checkout__paywith.checkout__paywith--updated-design");
	private static final By KLARNA_BUTTON = By.className("checkout__payment-choice--Klarna");
	private static final By KLARNA_RADIO_BUTTON = By.cssSelector(".checkout__payment-choice--Klarna > .checkout__payment-choice__radio-select");
	public static final By KLARNA_LOGO = By.id("klarna-logo");
	public static final By ORDERY_SUMMARY_WRAP = By.xpath(
			"//h3[@class='order__summary-title order__summary-title--mobile icon icon--chevron-down-up']");
	public static final By EDIT_BAG_LINK = By.xpath("//a[@data-translate='checkout.summary.editBag']");
	private static final By STORE_SEARCH_RESULTS = By.cssSelector(".label.radio__label.pudo__standard-text.ng-binding");
	private static final By PEAK_CAPPING_STORE_SEARCH_RESULTS = By.cssSelector(".label.radio__label.pudo__standard-text");
	private static final By COLLECTION_STORE_NAMES = By.cssSelector(".link.link--nounderline.ng-binding");
	private final By DELIVERY_TO_HOME_OR_OFFICE_BUTTON = By.cssSelector(
			".collection__tab-button > .tab-text__checkout--delivery");
	private final By COLLECTION_STORE_SEARCH_FIELD = By.id("storelocator-query");
	private final By DEFAULT_DELIVERY_ADDRESS = By.cssSelector("#delivery2 .checkout__delivery-info-wrapper>p");
	private final By DELIVERY_BUTTON = By.cssSelector(".collection__tab__deliver>.collection__tab-button");
	private final By selectedCollectionStoreLabel = By.cssSelector(".label.radio__label.pudo__standard-text>span");
	private final By PEAK_CAPPING_SelectedCollectionStoreLabel = By.cssSelector("span.iphone-label-click.delivery-mode__description--item.ng-binding:nth-child(2)");
	private final By orderSummary = By.className("order__summary-delivery-mode");
	public static final By DEFAULT_PAYMENT_CARD = By.className("checkout__payment-card-summary");
	public static final By selectStoreButton = By.className("button--collection-selection");
	private String giftCardBalanceBeforePurchase;
	private static final By CHANGE_DELIVERY_ADDRESS = By.cssSelector(".checkout__delivery-link.nl__change-delivery-address.ng-scope");
	private static final By ADD_NEW_ADDRESS_DELIVERY_BUTTON = By.cssSelector(".button.button--secondary.modal__button.checkout__delivery-btn.checkout__delivery-btn--add-address.checkout__delivery-btn--modal.ng-scope.nl__add-new-address");
	private static final By DELIVERY_USE_THIS_ADDRESS = By.cssSelector(".button.button--secondary.button--secondary-black.modal__button.checkout__delivery-btn.checkout__delivery-btn--use-address.ng-scope.nl__add-new-address-save");
	private static final By ADD_ANOTHER_CARD = By.cssSelector(".button-link.checkout__payment-link.checkout__payment-update-card.nl__add-another-card.ng-scope");
	private static final By FIRST_ADDRESS_RADIO_BUTTON = By.cssSelector("div.radio.radio--checkout-modal > label.label.radio__label > strong.ng-binding");
	private static final By CREDIT_DEBIT_CARD_BUTTON = By.cssSelector(".checkout__payment-choice.checkout__payment-choice--card.checkout__payment-choice--Card.nl__select-credit-debit-card");

	private static final By AGE_CONFIRMATION_WRAPPER = By.className("age-consent__wrapper");
	private static final By PAYMENT_DETAILS_CONTAINER = By.className("checkout__payment-details");

	private static final By ENTER_ADDRESS_MANUALLY_LINK = By.linkText("Enter address manually");
	public static final By CREDIT_CARD_OVER_AGE_18 = By.cssSelector(
			".label.checkbox__label[for='creditCardageConfirmation']");
	public static final By STORE_CARD_OVER_AGE_18 = By.xpath("//*[@for='storeCardAgeConfirmation']");
	public static final By GIFT_CARD_OVER_AGE_18 = By.xpath("//*[@for='giftCardAgeConfirmation']");
	public static final By KLARNA_CARD_OVER_AGE_18 = By.xpath("//*[@for='klarnaAgeVerification']");

	public static final By OVER_AGE_18_CHECKBOX_COMMON = By.xpath("//*[@for='storeCardAgeConfirmation']");

	private static final String PLACE_ORDER = "Place Order";
	private static final String BUTTON = "button";
	private static final By DELIVERY_MODE_2 = By.xpath("//span[@class='delivery-mode__description--item ng-binding']");
	private static final By DELIVERY_DATE = By.cssSelector("button.delivery-mode__radio-checked");
	private static final By MOBILE_ORDER_SUMMARY_TITLE = By.cssSelector(
			".order__summary-title.order__summary-title--mobile.icon.icon--chevron-down-up");
	private static final By ORDER_SUMMARY = By.className("order__summary");
	private static final By ORDER_SUMMARY_TOTAL = By.xpath("//span[@data-translate='mobile.checkout.total']");
	private static final By DELIVERY_ALERT_MESSAGE = By.cssSelector(".alert__item.alert__item--rendered.alert__item--checkout-delivery-nominated");
	private static final By STANDARD_DELIVERY_MESSAGE = By.cssSelector("span.delivery-mode__description--item.ng-binding:nth-child(2)");
	private static final By DELIVERY_METHOD_TITLE = By.xpath("//span[contains(text(),'Delivery Method')]");
	private static final By DELIVERY_ADDRESS_SECTION = By.xpath("//h3[@id='delivery-address-section']");
	private static final By SAVED_COLLECTION_POPUP = By.cssSelector(".modal__title.ng-scope");
	private static final By TITLE_DROPDOWN_FIELD_GUEST = By.xpath("//edit-address[@id='billing']//select[@id='titleCode']");
	private static final By CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_FIRST_NAME_FIELD_GUEST = By.xpath("//edit-address[@id='billing']//input[@id='firstName']");
	private static final By CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_LAST_NAME_FIELD_GUEST = By.xpath("//edit-address[@id='billing']//input[@id='lastName']");
	private static final By CHANGE_COUNTRY_FIELD_MOBILE = By.cssSelector("select[data-ng-model='selected.country']");
	private static final By NUM_IFRAME_PCI_FORM = By.cssSelector("#number-container > iframe");
	private static final By SEC_IFRAME_PCI_FORM = By.cssSelector("#securityCode-container > iframe");
	private static final By CARD_NUMBER_INPUT_PCI_FORM = By.name("number");
	private static final By CVV_INPUT_PCI_FORM = By.xpath("//*[@name='securityCode' and @type='tel']");
	private static final By VISA_ENDING_MSG_PCI_FORM = By.cssSelector("div.checkout__payment-card-summary > div >span.checkout__payment-card-data.ng-scope");
	private static final By VISA_ENDING_NUM_PCI_FORM = By.cssSelector("div.checkout__payment-card-summary > div > span.ng-binding");
	private static final By VISA_EXP_DATE_PCI_FORM = By.cssSelector("div.checkout__payment-card-summary >  div:nth-child(2) > span:nth-child(1)");
	private static final By SAVE_CARD_CHECKBOX = By.id("saveCard");
	private static final By USE_THIS_CARD_PCI_FORM = By.cssSelector("#my-form > button:nth-of-type(1)");
	private static final By SAVE_CARD_CHECKBOX_PCI_FORM = By.xpath("//input[@id='saveCard']");
	private static final By CHANGE_CARD = By.xpath("//button[text()='Change card']");
	private static final By CARD_SELECTION_OVERLAY = By.xpath("//h3[text()='Select or add a payment card']");
	private static final By AVAILABLE_CARDS = By.xpath("//div[@data-ng-repeat='card in cards']");
	private static final By USE_THIS_PAYMENT_CARD = By.xpath("//button[@name='useCard']");

	private static final By MICROFORM_CARD_ICON_VALID = By.className("microform_card-icon-valid");
	private static final By MICROFORM_USE_CARD_BUTTON = By.className("microform_btn--use-this-card");
	private static final By MICROFORM_EXPIRY_DATE_MONTH = By.id("expirationMonth");
	private static final By MICROFORM_EXPIRY_DATE_YEAR = By.id("expirationYear");

	private final CheckOutPage checkOutPage = new CheckOutPage();

	private static final By ORDER_NUMBER_ID = By.className("order__summary-bag-id");

	private static final By ALERT = By.className("alert__item");
	private static final By AGE_CONFIRMATION_CHECKBOX = By.id("creditCardageConfirmation");
	private static final By CHECKOUT_CONTAINER = By.className("checkout");

	private static final By CARD_3DS_GATEWAY_IFRAME = By.id("runPayerAuthentication");
	private static final By CARD_3DS_PASSWORD_FIELD = By.id("password");
	private static final By CARD_3DS_SUBMIT_BUTTON = By.name("UsernamePasswordEntry");
	private static final By CARD_3DS_POPUP_IFRAME = By.id("Cardinal-CCA-IFrame");
	private static final By CARD_3DS_POPUP_PASSWORD_FIELD = By.className("input-field");
	private static final By CARD_3DS_POPUP_SUBMIT_BUTTON = By.className("primary");

	private static final By Confirm_CVV_Label = By.className("cv2__box-title");
	private static final By Confirm_CVV_Field = By.xpath("//*[@class='cv2__box']//input");
	private static final By GREYED_PLACE_ORDER_BUTTON = By.xpath("//*[contains(@class,'button button--primary checkout__total-submit button--disabled')]");
	private static final By PAYMENT_METHOD_ERROR = By.xpath("//*[@class='alert__item alert__item--rendered']");
	private static final By SAVE_CARD_MESSAGE = By.xpath("//*[@for='saveCard']");
	private static final By FIRST_DELIVERY_OPTION = By.xpath("(//*[@name='deliveryMode'])[1]/../..");
	private static final By CARD_BILLING_ADDRESS_FORM = By.xpath("//button[@id='card']//following-sibling::div//form[@name='editAddressFormBilling']");
	private static final By KLARNA_BILLING_ADDRESS_FORM = By.xpath("//button[contains(@class,'checkout__payment-choice--Klarna')]//following-sibling::div//form[@name='editAddressFormBilling']");
	private static final By CARD_SAVED_BILLING_ADDRESS = By.xpath("//button[@id='card']//following-sibling::div//div[contains(@class,'checkout__payment-user-details')]");
	private static final By KLARNA_SAVED_BILLING_ADDRESS = By.xpath("//button[contains(@class,'checkout__payment-choice--Klarna')]//following-sibling::div//div[contains(@class,'checkout__delivery-info--address-details')]");
	private static final By KLARNA_PAY_IN_30_DAYS = By.xpath("//div[@id='granular-category-header-pay_later']");
	private static final By KLARNA_PAY_OVER_TIME = By.xpath("//div[@id='granular-category-header-pay_over_time']");
	private static final By KLARNA_PAY_IN_30_DAYS_TAB = By.xpath("//button[@id='klarnaOption_pay_later']");
	private static final By KLARNA_PAY_OVER_TIME_TAB = By.xpath("//button[@id='klarnaOption_pay_over_time']");
	private static final By IFRAME_KLARNA_PAY_OVER_TIME = By.id("klarna-pay-over-time-main");
	private static final By IFRAME_KLARNA_PAY_LATER = By.id("klarna-pay-later-main");
	private static final By KLARNA_ERROR_MSG = By.xpath("//div[contains(@class,'paymentError')]");
	private static final By NEW_LOOK_STORE_CARD_TXTBOX = By.id("storeCardNumber");
	private static final By NEWLOOK_STORECARD_BUTTON = By.className("checkout__payment-choice--StoreCard");
	private static final By INVALID_CARD_MESSAGE = By.xpath("//*[@class='alert__item alert__item--rendered']");
	private static final By PAYPAL_CHECKBOX = By.xpath("//button[contains(@class,'select-paypal')]");
	private static final By PAYPAL_RETURN_INFORMATION = By.xpath("//div[contains(@class,'checkout__payment-details--paypal-radio-content')]");
	private static final By PAYPAL_BUTTON = By.xpath("//button[contains(@class,'paypal-button')]");
	private static final By USE_THIS_ADDRESS_BUTTON = By.xpath("(//button[@name='useAddress'])[1]");
	private static final By TOTAL_AMOUNT = By.xpath("(//*[contains(@class,'checkout__total-title--price ng-binding')])[1]");
	private static final By KLARNA_PAY_IN_30_DAYS_RADIO_BTN = By.xpath("//label[@for='klarnaOption_pay_later']");
	private static final By KLARNA_PAY_OVER_TIME_RADIO_BTN = By.xpath("//label[@for='klarnaOption_pay_over_time']");
	private static final By COLLECTION_DETAILS_TITLE_ERROR_MSG = By.xpath("//*[@class='form__error-msg form__error-msg--collection-mobile ng-scope']");
	private static final By COLLECTION_DETAILS_MOBILE_ERROR_MSG = By.xpath("//*[@class='form__error-msg ng-scope']");
	private static final By STORE_CARD_BILLING_DETAILS = By.xpath("//span[contains(@class,'checkout__payment-billing-data')]");

	public boolean isPCIFeatureFlagDisabled()
	{
		return !NewLookHelper.getFeatureStatus("feature.pci.microform.enabled.uk-site");
	}

	public boolean checkDeliveryButtonDisplayed()
	{
		waitForElementVisible(DELIVERY_TO_HOME_OR_OFFICE_BUTTON, DEFAULT_TIMEOUT);
		return !webDriver.findElements(DELIVERY_TO_HOME_OR_OFFICE_BUTTON).isEmpty();
	}

	public boolean checkCollectionDetailsErrorMessagesDisplayed()
	{
		if (waitForAndGetElement(COLLECTION_DETAILS_TITLE_ERROR_MSG, DEFAULT_TIMEOUT) != null &&
				waitForAndGetElement(COLLECTION_DETAILS_MOBILE_ERROR_MSG, DEFAULT_TIMEOUT) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean checkCollectionButtonDisplayed()
	{
		waitForElementVisible(COLLECTION_BUTTON, DEFAULT_TIMEOUT);
		return !webDriver.findElements(COLLECTION_BUTTON).isEmpty();
	}

	public void selectDeliveryOrCollectionOption(final String option)
	{
		waitForPageLoad();
		pause(1500);
		CookieBotPage cookieBotPage = new CookieBotPage();
		if (cookieBotPage.cookieBotBannerAccept())
		{
			cookieBotPage.clickCookieBotBannerAccept();
		}
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad") ||
				WebDriverHelper.DEVICE_NAME.contains("Samsung") ||
				BROWSER.contains("emulator"))
		{
			waitForElementVisible(COLLECTION_BUTTON, DEFAULT_TIMEOUT);
		}
		switch (option.toUpperCase())
		{
			case "COLLECTION":
				waitForElementVisible(COLLECTION_BUTTON, DEFAULT_TIMEOUT);
				waitForAndGetElement(COLLECTION_BUTTON, DEFAULT_TIMEOUT);
				clickByJavaScriptExecutor(waitForExpectedElement(COLLECTION_BUTTON));
				break;
			case "DELIVERY":
				waitForElementVisible(DELIVERY_TO_HOME_OR_OFFICE_BUTTON, DEFAULT_TIMEOUT);
				waitForAndGetElement(DELIVERY_TO_HOME_OR_OFFICE_BUTTON, DEFAULT_TIMEOUT);
				if (!webDriver.findElements(DELIVERY_TO_HOME_OR_OFFICE_BUTTON).isEmpty())
				{
					webDriver.findElement(DELIVERY_TO_HOME_OR_OFFICE_BUTTON).click();
				}
				break;
			default:
				log.info("Please check the option provided -- " + option);

		}
		waitForPageLoad();
	}

	public void selectCollectionPointFromTheSearchDropdown()
	{
		waitForAndGetElement(SELECTED_COLLECTION_STORE, DEFAULT_TIMEOUT);
		pause(3000);
		if (!webDriver.findElements(COLLECTION_STORE_SEARCH_FIELD).isEmpty())
		{
			webDriver.findElement(COLLECTION_STORE_SEARCH_FIELD).sendKeys("London");
			scrollByCoordinates(30);
			getButtonWithText("SEARCH").click();
			selectTheFirstCollectionOptionFromList();
		}
	}

	public void selectCollectionPointFromTheSearchDropdown(final String location)
	{
		waitForAndGetElement(webDriver, SELECTED_COLLECTION_STORE, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(COLLECTION_STORE_SEARCH_FIELD).isEmpty())
		{
			searchForCollectionLocationsRestOfWorld(location);
			selectTheFirstCollectionOptionFromList();
		}
	}

	public void selectTheFirstCollectionOptionFromList()
	{
		if (PEAK_CAPPING_FEATURE)
		{
			try
			{
				waitForElementVisible(PEAK_CAPPING_STORE_SEARCH_RESULTS, 10);
				if (!webDriver.findElements(PEAK_CAPPING_STORE_SEARCH_RESULTS).isEmpty())
				{
					scrollToObject(webDriver.findElements(PEAK_CAPPING_STORE_SEARCH_RESULTS).get(0));
					if (webDriver.findElements(By.cssSelector("label"))
							.stream()
							.filter(a -> a.getText().contains("Click & Collect Standard"))
							.findAny()
							.orElseThrow(() -> new NotFoundException("Click & Collect Standard not found"))
							.isDisplayed())
					{
						clickWhenClickable(webDriver.findElements(By.cssSelector("label"))
										.stream()
										.filter(a -> a.getText().contains("Click & Collect Standard"))
										.findFirst()
										.orElseThrow(() -> new NotFoundException(
												"Click & Collect Standard not found")),
								10);
					}

					log.info("Click the use this store button");
					clickByJavaScriptExecutor(webDriver.findElements(SELECT_THE_STORE_BUTTON).get(0));
				}
			}
			catch (final TimeoutException e)
			{
				scrollForFocusAndClick(selectStoreButton, 5);
			}
		}
		else
		{
			try
			{
				waitForElementVisible(STORE_SEARCH_RESULTS, 10);
				if (!webDriver.findElements(STORE_SEARCH_RESULTS).isEmpty())
				{
					scrollToObject(webDriver.findElements(STORE_SEARCH_RESULTS).get(0));
					if (webDriver.findElements(By.cssSelector("label"))
							.stream()
							.filter(a -> a.getText().contains("Click & Collect Standard"))
							.findFirst()
							.orElseThrow(() -> new NotFoundException("Click & Collect Standard not found"))
							.isDisplayed())
					{
						clickWhenClickable(webDriver.findElements(By.cssSelector("label"))
										.stream()
										.filter(a -> a.getText().contains("Click & Collect Standard"))
										.findFirst()
										.orElseThrow(() -> new NotFoundException(
												"Click & Collect Standard not found")),
								10);
					}

					log.info("Click the use this store button");
					clickByJavaScriptExecutor(webDriver.findElements(SELECT_THE_STORE_BUTTON).get(0));
				}
			}
			catch (final TimeoutException e)
			{
				scrollForFocusAndClick(selectStoreButton, 5);
			}
		}
	}

	public void clickCreditDebitCardButton()
	{
		scrollIntoView(CREDIT_DEBIT_CARD_BUTTON);
		clickByJavaScriptExecutor(webDriver.findElements(CREDIT_DEBIT_CARD_BUTTON).get(0));
	}

	public void selectPayWithOption(final String payWithOption) throws WebElementNotFoundException
	{
		try
		{
			pause(5000);
			switch (payWithOption.toUpperCase())
			{
				case "CREDITCARD":
				case "CARD":
					log.info("Clicking the credit/debit card button");
					clickCreditDebitCardButton();
					break;
				case "PAYPAL":
					log.info("Clicking the paypal button");
					clickPayPalBtn();
					break;
				case "NEWLOOKSTORECARD":
					log.info("Clicking the new look store card button");
					clickNewLookStoreCardBtn();
					break;
				case "KLARNA":
					log.info("Clicking the klarna card button");
					clickPayWithKlarnaBtn();
					break;
				default:
					log.info("Please check the pay with option provided -- " + payWithOption);
			}
			waitForPageLoad();
		}
		catch (Exception e)
		{
			Assert.fail("Not able to select payment options");
		}

	}

	public void selectChangeBillingAddress()
	{
		waitForAndGetElement(webDriver, CHANGE_BILLING_ADDRESS_BUTTON, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(CHANGE_BILLING_ADDRESS_BUTTON).isEmpty())
		{
			scrollElementIntoView(CHANGE_BILLING_ADDRESS_BUTTON);
			clickByJavaScriptExecutor(webDriver.findElement(CHANGE_BILLING_ADDRESS_BUTTON));
			waitForPageLoad();
		}
		else
		{
			clickByJavaScriptExecutor(webDriver.findElement(ADD_ANOTHER_CARD));
		}
	}

	public void selectOptionInCheckoutPaymentPopup(final String billingAddressOption)
	{
		pause(3000);
		waitForElementVisible(CHECKOUT_PAYMENT_BILLING_ADDRESS_POPUP, 20);
		switch (billingAddressOption.toUpperCase())
		{
			case "USETHISADDRESSWITHOUTTITLE":
				waitForExpectedElement(CHECKOUT_PAYMENT_POPUP_ADDRESS_WITHOUT_TITLE).click();
				waitForExpectedElement(CHECKOUT_PAYMENT_POPUP_USE_THIS_ADDRESS_BUTTON).click();
				waitForPageLoad();
				break;
			case "ADDNEWADDRESS":
				waitForExpectedElement(CHECKOUT_PAYMENT_POPUP_ADD_NEW_ADDRESS_BUTTON).click();
				waitForPageLoad();
				break;
			default:
				log.info("Please check the billing address option provided -- " + billingAddressOption);
		}
	}

	public boolean getChangeDeliveryAddressPopup()
	{
		waitForAndGetElement(webDriver, CHECKOUT_PAYMENT_BILLING_ADDRESS_POPUP, DEFAULT_TIMEOUT);
		return !webDriver.findElements(CHECKOUT_PAYMENT_BILLING_ADDRESS_POPUP).isEmpty();
	}

	public List<String> getCheckoutPaymentPopupLabels()
	{
		waitForAndGetElement(CHECKOUT_PAYMENT_BILLING_ADDRESS_POPUP, DEFAULT_TIMEOUT);
		final List<String> checkoutPaymentAddNewAddressPopup = new ArrayList<>();
		checkoutPaymentAddNewAddressPopup.add(webDriver.findElement(CHECKOUT_PAYMENT_BILLING_ADDRESS_POPUP_HEADER_LABEL)
				.getText());
		checkoutPaymentAddNewAddressPopup.add(webDriver.findElement(CHECKOUT_PAYMENT_POPUP_BILLING_ADDRESS_LABEL)
				.getText());
		checkoutPaymentAddNewAddressPopup.add(webDriver.findElement(CHECKOUT_PAYMENT_POPUP_USE_THIS_ADDRESS_BUTTON)
				.getText());
		checkoutPaymentAddNewAddressPopup.add(webDriver.findElement(CHECKOUT_PAYMENT_POPUP_ADD_NEW_ADDRESS_BUTTON)
				.getText());
		return checkoutPaymentAddNewAddressPopup;

	}

	public void selectChangeBillingAddressOption(final String deliverOption,
	                                             final String deliveryUpdatesOption,
	                                             final String payWithOption) throws WebElementNotFoundException
	{
		selectDeliveryOrCollectionOption(deliverOption);
		selectCollectionPointFromTheSearchDropdown();

		selectPayWithOption(payWithOption);

		pause(2000);
		fillCardDetails();
		refresh();
		pause(3000);
		clickByJavaScriptExecutor(waitForAndGetElement(ADD_ANOTHER_CARD, DEFAULT_TIMEOUT));
	}

	public List<String> getCheckoutPaymentPopupAddNewAddressLabels()
	{
		waitForAndGetElement(webDriver, CHECKOUT_PAYMENT_BILLING_ADDRESS_POPUP, DEFAULT_TIMEOUT);
		final List<String> checkoutPaymentAddNewAddressPopup = new ArrayList<>();
		checkoutPaymentAddNewAddressPopup.add(webDriver.findElement(CHECKOUT_PAYMENT_POPUP_COUNTRY_LABEL).getText());
		checkoutPaymentAddNewAddressPopup.add(webDriver.findElement(CHECKOUT_PAYMENT_POPUP_TITLE_LABEL).getText());
		checkoutPaymentAddNewAddressPopup.add(webDriver.findElement(CHECKOUT_PAYMENT_POPUP_FIRST_NAME_LABEL).getText());
		checkoutPaymentAddNewAddressPopup.add(webDriver.findElement(CHECKOUT_PAYMENT_POPUP_LAST_NAME_LABEL).getText());
		checkoutPaymentAddNewAddressPopup.add(webDriver.findElement(CHECKOUT_PAYMENT_POPUP_ADDRESS_FINDER_LABEL)
				.getText());
		checkoutPaymentAddNewAddressPopup.add(webDriver.findElement(CHECKOUT_PAYMENT_POPUP_ENTER_ADDRESS_MANUALLY_LABEL)
				.getText());
		checkoutPaymentAddNewAddressPopup.add(webDriver.findElement(CHECKOUT_PAYMENT_POPUP_USE_THIS_ADDRESS_BUTTON_LABEL)
				.getText());
		checkoutPaymentAddNewAddressPopup.add(webDriver.findElement(CHECKOUT_PAYMENT_POPUP_CANCEL_LABEL).getText());
		return checkoutPaymentAddNewAddressPopup;
	}

	public WebElement getCancelButtonOnPopup()
	{
		return waitForAndGetElement(CHECKOUT_PAYMENT_POPUP_CANCEL_LABEL, DEFAULT_TIMEOUT);
	}

	public WebElement getCrossButtonOnPopup()
	{
		return waitForAndGetElement(CHECKOUT_PAYMENT_POPUP_CROSS_LABEL, DEFAULT_TIMEOUT);
	}

	public void addDeliveryAddress(final String postCode) throws InterruptedException
	{
		waitForAndGetElement(webDriver, TITLE_DROPDOWN_FIELD, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(TITLE_DROPDOWN_FIELD).isEmpty())
		{
			pause(1000);
			selectFromDropDown(webDriver.findElement(TITLE_DROPDOWN_FIELD), 1);
			webDriver.findElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_FIRST_NAME_FIELD)
					.sendKeys(RandomGenerator.randomAlphabetic(5));
			webDriver.findElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_LAST_NAME_FIELD)
					.sendKeys(RandomGenerator.randomAlphabetic(5));
			if (!webDriver.findElements(MOBILE_NUMBER_FIELD).isEmpty())
			{
				webDriver.findElement(MOBILE_NUMBER_FIELD)
						.sendKeys("7894938774");
			}
			changeDeliveryAddressUsingPostcode(postCode);
			pause(3000);
			waitForElementVisible(DELIVERY_ADDRESS_USE_THIS_ADDRESS_BUTTON, 10);
			try
			{
				scrollToObject(getWebDriver().findElement(DELIVERY_ADDRESS_USE_THIS_ADDRESS_BUTTON));
			}
			catch (Exception e)
			{
				scrollToObject(getWebDriver().findElement(DELIVERY_ADDRESS_USE_THIS_ADDRESS_BUTTON));
			}

			try
			{
				clickByJavaScriptExecutor(webDriver.findElement(DELIVERY_ADDRESS_USE_THIS_ADDRESS_BUTTON));
			}
			catch (Exception e)
			{
				clickByJavaScriptExecutor(webDriver.findElement(DELIVERY_ADDRESS_USE_THIS_ADDRESS_BUTTON));
			}
		}
	}

	public void changeDeliveryAddressUsingPostcode(final String postCode) throws InterruptedException
	{
		webDriver.findElement(DELIVERY_ADDRESS_FINDER_FIELD).sendKeys(postCode);
		waitForElementVisible(DELIVERY_ADDRESS_LIST, 10);
		if (!webDriver.findElements(DELIVERY_ADDRESS_LIST).isEmpty() &&
				webDriver.findElements(DELIVERY_ADDRESS_LIST).get(0).isEnabled())
		{
			pause(3500);
			clickByJavaScriptExecutor(webDriver.findElements(DELIVERY_ADDRESS_LIST).get(0));
		}
	}

	public void makePaymentForDelivery(final String paymentType, final String cardType, final String cardNumber)
	{
		selectDeliveryOrCollectionOption("DELIVERY");
		pause(2000);
		if (webDriver.findElements(DELIVERY_ADDRESS_POSTCODE).isEmpty())
		{
			waitForAndGetElement(webDriver, ENTER_ADDRESS_MANUALLY_LINK, DEFAULT_TIMEOUT);
			clickByJavaScriptExecutor(webDriver.findElement(ENTER_ADDRESS_MANUALLY_LINK));
			addDeliveryAddressManually("KT1 3HP", "1 Altmore Avenue", "Altmore Avenue", "London");
		}
		selectDeliveryOptionIfNotSelected();
		checkOutPage.enterMandatoryMobileField();
		checkOutPage.continueToPaymentSection();
		waitForIsClickable(webDriver.findElements(PAYMENT_METHOD), 20);
		waitForElementVisible(PAYMENT_METHOD, 10);

		clickByJavaScriptExecutor(webDriver.findElements(PAYMENT_METHOD).get(0));
		checkOutPage.enterCardDetailsAndMakePlaceOrder(paymentType, cardType, cardNumber);
	}

	public void makePaymentForCollection(final String paymentType, final String cardType, final String cardNumber)
	{
		selectDeliveryOrCollectionOption("COLLECTION");
		selectCollectionPointFromTheSearchDropdown();
		try
		{
			checkOutPage.collectionDetails();
		}
		catch (final Exception e)
		{
			log.error(e);
		}
		checkOutPage.continueToPaymentSection();
		checkOutPage.enterMandatoryMobileField();
		try
		{
			selectPayWithOption(paymentType);
		}
		catch (final WebElementNotFoundException e)
		{
			log.error(e);
		}
		checkOutPage.enterCardDetailsAndMakePlaceOrder(paymentType, cardType, cardNumber);
	}

	public void enterAddressForCollection(final String location)
	{
		selectDeliveryOrCollectionOption("COLLECTION");
		selectCollectionPointFromTheSearchDropdown(location);
		try
		{
			checkOutPage.collectionDetails();
		}
		catch (final Exception e)
		{
			log.error(e);
		}
		checkOutPage.continueToPaymentSection();
	}

	public void selectDeliveryOptionIfNotSelected()
	{
		pause(3000);
		if (isElementVisible(UK_STANDARD_DELIVERY_OPTION, 2))
		{
			getWebDriver().findElement(UK_STANDARD_DELIVERY_OPTION).click();
		}
		if (!getWebDriver().findElements(SELECT_DELIVERY_DATE).isEmpty())
		{
			getWebDriver().findElements(SELECT_DELIVERY_DATE).get(0).click();
		}
	}

	public List<WebElement> getDefaultDeliveryAddress()
	{
		webDriver.findElement(DELIVERY_BUTTON).click();
		pause(1000);
		waitForH3Heading("Delivery Address", 5);
		return webDriver.findElements(DEFAULT_DELIVERY_ADDRESS);
	}

	public void removeItem()
	{
		getLink("Edit bag").click();
		getLink("Remove").click();
	}

	//removes an item from the basket on checkout page on mobile
	public void removeItemOnMobile()
	{
		clickByJavaScriptExecutor(waitForExpectedElement(ORDER_SUMMARY));
		clickByJavaScriptExecutor(waitForExpectedElement(ORDER_SUMMARY_TOTAL));
		clickByJavaScriptExecutor(waitForExpectedElement(EDIT_BAG_LINK));
		clickByJavaScriptExecutor(waitForExpectedElement(By.linkText("Remove")));
	}

	public void addBillingAddress(final String postCode)
	{
		waitForAndGetElement(webDriver, BILLING_ADDRESS, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(BILLING_ADDRESS).isEmpty())
		{
			webDriver.findElements(BILLING_ADDRESS).get(0).sendKeys(postCode);
		}
		selectAddressFromDropDown();
		waitForPageLoad();
	}

	public void selectAddressFromDropDown()
	{
		pause(3000);
		waitForElementVisible(BILLING_ADDRESS_LIST, 20);
		pause(2000);
		if (!webDriver.findElements(BILLING_ADDRESS_LIST).isEmpty())
		{
			pause(2000);
			webDriver.findElements(BILLING_ADDRESS_LIST).get(1).click();
			waitForIsClickable(webDriver.findElements(BILLING_ADDRESS_USE_THIS_CARD_BUTTON), DEFAULT_TIMEOUT);
			clickByJavaScriptExecutor(webDriver.findElements(BILLING_ADDRESS_USE_THIS_CARD_BUTTON).get(0));
		}
	}

	public void fillCardDetails()
	{
		fillCardDetails(CardProperties.cardNumber("visa.legacy"));
	}

	public void fillCardDetails(final String cardNumber)
	{
		waitForPageLoad();
//		if (isPCIFeatureFlagDisabled())
//		{
//			waitForAndGetElement(webDriver, CARD_TYPE, DEFAULT_TIMEOUT);
//			if (!webDriver.findElements(CARD_TYPE).isEmpty())
//			{
//				waitForAndGetElement(getWebDriver(), CARD_TYPE, DEFAULT_TIMEOUT);
//				selectFromDropDown(getWebDriver().findElement(CARD_TYPE), "001");
//				getWebDriver().findElement(CARD_NUMBER_INPUT_FIELD).sendKeys(cardNumber);
//				selectFromDropDown(getWebDriver().findElement(CARD_EXPIRE_MONTH_FIELD), 3);
//				selectFromDropDown(getWebDriver().findElement(CARD_EXPIRE_YEAR_FIELD), 3);
//				pause(2000);
//				clickByJavaScriptExecutor(getWebDriver().findElement(USE_THIS_CARD_BUTTON));
//			}
//		}
//		else
//		{
		checkOutPage.fillCardDetails("VISA", cardNumber);
		//}
	}


	public boolean verifyAddedCardDetailOnPCIForm(final String cardLastFourDigits)
	{
		waitForPageLoad();
		pause(2000);
		if (isElementVisible(SAVED_CARD_LAST_4_DIGITS, 5))
		{
			return waitForExpectedElement(SAVED_CARD_LAST_4_DIGITS).getText().contains(cardLastFourDigits);
		}

		return false;
	}

	public void fillCardDetailsSaveCardPCIForm(final String selection)
	{
		pause(2000);
		// only should fill if card details not present
		if (isElementPresent(NUM_IFRAME_PCI_FORM))
		{
			webDriver.switchTo().defaultContent();
			final WebElement frame = getWebDriver().findElement(NUM_IFRAME_PCI_FORM);
			webDriver.switchTo().frame(frame);
			if (isElementVisible(CARD_NUMBER_INPUT_PCI_FORM, 5))
			{
				waitForExpectedElement(CARD_NUMBER_INPUT_PCI_FORM).sendKeys(CardProperties.cardNumber("visa.legacy"));
				waitForPageLoad();
				webDriver.switchTo().defaultContent();
				waitForExpectedElement(CheckOutPage.EXPIRY_MONTH).sendKeys(CardProperties.cardExpiryMonth("visa.legacy"));
				waitForExpectedElement(CheckOutPage.EXPIRY_YEAR).sendKeys(CardProperties.cardExpiryYear("visa.legacy"));
				waitForPageLoad();
				webDriver.switchTo().defaultContent();
				frameToBeAvailableAndSwitchToIt(SEC_IFRAME_PCI_FORM);
				waitForExpectedElement(CVV_INPUT_PCI_FORM).sendKeys(CardProperties.cardCvv("visa.legacy"));
				waitForPageLoad();
				webDriver.switchTo().defaultContent();
				if ("deselect".equals(selection) && webDriver.findElement(SAVE_CARD_CHECKBOX_PCI_FORM).isSelected())
				{
					webDriver.findElement(SAVE_CARD_CHECKBOX_PCI_FORM).click();
					waitForPageLoad();
				}
				pause(2000);
				if (CHECKOUT_USE_CARD_CTA_FEATURE)
				{
					clickByJavaScriptExecutor(checkOutPage.useThisCard());
				}
			}
		}
		waitForPageLoad();
	}

	public Boolean isCVVFormVisible()
	{
		pause(2000);
		webDriver.switchTo().defaultContent();
		if (isElementPresent(SEC_IFRAME_PCI_FORM, 5))
		{
			frameToBeAvailableAndSwitchToIt(SEC_IFRAME_PCI_FORM);
		}

		return isElementVisible((CVV_INPUT_PCI_FORM), 5);
	}

	public Boolean findExpiryDateDisplay()
	{
		pause(2000);
		boolean findExpiryDate = true;
		if (!isElementPresent((VISA_ENDING_MSG_PCI_FORM), 5))
		{
			findExpiryDate = false;
		}
		if (!"1111".equals(waitForExpectedElement(VISA_ENDING_NUM_PCI_FORM).getText().trim()))
		{
			findExpiryDate = false;
		}
		if (!"Expires 06/27".equals(waitForExpectedElement(VISA_EXP_DATE_PCI_FORM).getText()))
		{
			findExpiryDate = false;
		}

		clickByJavaScriptExecutor(webDriver.findElement(PLACE_ORDER_BUTTON));

		return findExpiryDate;
	}

	public void placeOrder()
	{
		pause(2000);
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad") ||
				WebDriverHelper.DEVICE_NAME.contains("Samsung") ||
				BROWSER.contains("emulator"))
		{
			waitForExpectedElement(CheckOutPage.cardVerificationNumber, 20);
		}
		if (isPCIFeatureFlagDisabled())
		{
			if (!getWebDriver().findElements(CONFIRM_SECURITY_CODE).isEmpty() &&
					getWebDriver().findElements(CONFIRM_SECURITY_CODE).get(0).isEnabled())
			{
				if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
						WebDriverHelper.DEVICE_NAME.contains("iPad") ||
						BROWSER.contains("emulator"))
				{
					pause(2000);
				}
				waitForIsClickable(CONFIRM_SECURITY_CODE, DEFAULT_TIMEOUT);
				waitForAndGetElement(CONFIRM_SECURITY_CODE, 10).sendKeys("123");
			}
		}
		if (isElementPresent((CREDIT_CARD_OVER_AGE_18), 5))
		{
			if (getWebDriver().findElement(CREDIT_CARD_OVER_AGE_18).isDisplayed())
			{
				clickByJavaScriptExecutor(webDriver.findElements(CREDIT_CARD_OVER_AGE_18).get(0));
			}
		}
		if (getWebDriver().findElements(By.cssSelector(BUTTON))
				.stream()
				.anyMatch(a -> a.getText().contains(PLACE_ORDER)))
		{
			waitForIsClickable(getWebDriver().findElements(By.cssSelector(BUTTON))
							.stream()
							.filter(a -> a.getText().contains(PLACE_ORDER))
							.findFirst()
							.orElseThrow(() -> new NotFoundException("Place order button not found")),
					DEFAULT_TIMEOUT);
			clickByJavaScriptExecutor(getWebDriver().findElements(By.cssSelector(BUTTON))
					.stream()
					.filter(a -> a.getText().contains(PLACE_ORDER))
					.findFirst()
					.orElseThrow(() -> new NotFoundException(
							"Place order button not found")));
			waitForPageLoad();
		}
		else
		{
			clickPlaceOrderButton();
		}

	}

	public void clickPlaceOrderButton()
	{
		try
		{
			waitForPageLoad();
			pause(5000);
			waitForIsClickable(PLACE_ORDER_BUTTON, DEFAULT_TIMEOUT);
			clickByJavaScriptExecutor(webDriver.findElement(PLACE_ORDER_BUTTON));
			waitForPageLoad();
		}
		catch (Exception e)
		{
			log.info("Place Order Button is disabled");
		}
	}

	public void enterCVVNumber(final String cvvNumber)
	{
		if (!getWebDriver().findElements(CONFIRM_SECURITY_CODE).isEmpty() &&
				getWebDriver().findElement(CONFIRM_SECURITY_CODE).isEnabled())
		{
			waitForIsClickable(CONFIRM_SECURITY_CODE, DEFAULT_TIMEOUT);
			getWebDriver().findElement(CONFIRM_SECURITY_CODE).sendKeys(cvvNumber);
		}
	}


	public void useGiftCardForPayment()
	{
		pause(3000);
		waitForIsClickable(getWebDriver().findElements(By.cssSelector(BUTTON))
						.stream()
						.filter(a -> a.getText().contains(PLACE_ORDER))
						.findFirst()
						.orElseThrow(() -> new NotFoundException("Place order button not found")),
				DEFAULT_TIMEOUT);
		clickWhenClickable(getWebDriver().findElements(By.cssSelector(BUTTON))
						.stream()
						.filter(a -> a.getText().contains(PLACE_ORDER))
						.findFirst()
						.orElseThrow(() -> new NotFoundException("Place order button not found")),
				15);

	}

	public String getGiftCardBalanceBeforePurchase()
	{
		return this.giftCardBalanceBeforePurchase;
	}

	public void setGiftCardBalanceBeforePurchase()
	{
		try
		{
			Thread.sleep(2000);
		}
		catch (final InterruptedException e)
		{
			log.error(e);
		}
		giftCardBalanceBeforePurchase = webDriver.findElement(GIFT_CARD_BALANCE_BEFORE_PURCHASE).getText();
	}

	public boolean checkStoreDisplaySequence() throws InterruptedException
	{
		waitForElementVisible(COLLECTION_STORE_NAMES, 20);

		final WebElement nlStoreName = webDriver.findElements(COLLECTION_STORE_NAMES).get(0);

		if (nlStoreName.getText().contains("New Look"))
		{

			log.info("First store is New Look");

			return true;
		}
		else
		{
			log.info("Failed as NL is not the first store");

			return false;
		}
	}

	public void changeDeliveryAddress()
	{
		webDriver.findElement(CHANGE_DELIVERY_ADDRESS).click();
		webDriver.findElement(ADD_NEW_ADDRESS_DELIVERY_BUTTON).click();
		webDriver.findElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_FIRST_NAME_FIELD).clear();
		webDriver.findElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_FIRST_NAME_FIELD).sendKeys("partialReturn");
		webDriver.findElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_LAST_NAME_FIELD).clear();
		webDriver.findElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_LAST_NAME_FIELD).sendKeys("3");
		if (!webDriver.findElements(MOBILE_NUMBER_FIELD).isEmpty())
		{
			webDriver.findElement(MOBILE_NUMBER_FIELD)
					.sendKeys(com.salmon.test.framework.helpers.utils.RandomGenerator.randomInteger(11));
		}
		webDriver.findElement(DELIVERY_ADDRESS_FINDER_FIELD).sendKeys("london");
		pause(3500);

		if (!webDriver.findElements(DELIVERY_ADDRESS_LIST).isEmpty() &&
				webDriver.findElements(DELIVERY_ADDRESS_LIST).get(0).isEnabled())
		{
			clickByJavaScriptExecutor(webDriver.findElements(DELIVERY_ADDRESS_LIST).get(0));

		}
		pause(2000);
		clickByJavaScriptExecutor(webDriver.findElement(DELIVERY_USE_THIS_ADDRESS));
	}

	public void addAnotherCard()
	{
		fillCardDetails();
	}

	public void searchForCollectionLocationsRestOfWorld(final String location)
	{
		pause(1000);
		waitForExpectedElement(COLLECTION_STORE_SEARCH_FIELD, DEFAULT_TIMEOUT).sendKeys(location);
		webDriver.findElement(COLLECTION_SEARCH_BUTTON).click();
	}

	public void searchForCollectionLocations(final String location)
	{
		clickChooseAnotherCollectionPoint();
		pause(3000);
		waitForExpectedElement(COLLECTION_STORE_SEARCH_FIELD, 10).sendKeys(location);
		waitForExpectedElement(COLLECTION_SEARCH_BUTTON);
		webDriver.findElements(COLLECTION_SEARCH_BUTTON).stream()
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Collection search button not found"))
				.click();
	}

	public void clickChooseAnotherCollectionPointButton()
	{
		pause(2000);
		if (!getWebDriver().findElements(CHOOSE_ANOTHER_COLLECTION_POINT).isEmpty())
		{
			waitForExpectedElement1(By.linkText("Choose another collection point"), 10);
			try
			{
				getWebDriver().findElement(By.linkText("Choose another collection point")).click();
			}
			catch (final Exception e)
			{
				pause(2000);
				clickByJavaScriptExecutor(getWebDriver().findElement(By.linkText("Choose another collection point")));

			}
			waitForPageLoad();
		}
	}

	public void clickChooseAnotherCollectionPoint()
	{
		pause(2000);
		if (!getWebDriver().findElements(CHOOSE_ANOTHER_COLLECTION_POINT).isEmpty())
		{
			waitForExpectedElement1(By.linkText("Choose another collection point"), 10);
			try
			{
				getWebDriver().findElement(By.linkText("Choose another collection point")).click();
			}
			catch (final Exception e)
			{
				pause(2000);
				clickByJavaScriptExecutor(getWebDriver().findElement(By.linkText("Choose another collection point")));

			}
			pause(5000);
			if (!getWebDriver().findElements(NEW_SEARCH_SAVED_COLLECTION_POINT).isEmpty())
			{
				getWebDriver().findElement(NEW_SEARCH_SAVED_COLLECTION_POINT).click();
			}
			waitForPageLoad();
		}
	}

	public boolean getMapOfCollectionLocations()
	{
		pause(1000);
		waitForAndGetElement(COLLECTION_SEARCH_RESULT_MAP, 10);
		if (!IS_MOBILE)
		{
			waitForElementVisible(COLLECTION_SEARCH_RESULT_MAP, 10);
		}
		return !webDriver.findElements(COLLECTION_SEARCH_RESULT_MAP).isEmpty();
	}

	public String getMapContent(final String content)
	{
		waitForAndGetElement(GOOGLE_MAP_DATA_LABEL, 10);
		if (!IS_MOBILE)
		{
			waitForElementVisible(COLLECTION_SEARCH_RESULT_MAP, 10);
		}
		String displayedContent = null;
		switch (content.toUpperCase())
		{
			case "MAP":
			case "SATELLITE":
				displayedContent = webDriver.findElements(GOOGLE_MAP_TOP_BUTTONS)
						.stream()
						.filter(a -> a.getText().equalsIgnoreCase(content))
						.findFirst()
						.orElseThrow(() -> new NotFoundException("No link found: " + content))
						.getText();
				break;
			case "OPEN THIS AREA IN GOOGLE MAPS (OPENS A NEW WINDOW)":
				displayedContent = webDriver.findElements(GOOGLE_MAP_CLICK_TO_SEE_IN_GOOGLE_MAPS_BUTTON)
						.stream()
						.filter(a -> a.getAttribute("title").contains(content))
						.findFirst()
						.orElseThrow(() -> new NotFoundException("No link found: " + content))
						.getAttribute("title");
				break;
			case "MAP DATA ©2020 GOOGLE":
			case "MAP DATA ©2020":
				displayedContent = webDriver.findElements(GOOGLE_MAP_DATA_LABEL)
						.stream()
						.filter(a -> a.getText().contains("Map data"))
						.findFirst()
						.orElseThrow(() -> new NotFoundException("No link found: " + content))
						.getText();
				break;
			case "TERMS OF USE":
				displayedContent = webDriver.findElements(GOOGLE_MAP_TERMS_OF_USE)
						.stream()
						.filter(a -> a.getText().equalsIgnoreCase(content))
						.findFirst()
						.orElseThrow(() -> new NotFoundException("No link found: " + content))
						.getText();
				break;
			case "REPORT A MAP ERROR":
				if (!WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
						WebDriverHelper.DEVICE_NAME.contains("iPad") ||
						WebDriverHelper.DEVICE_NAME.contains("Samsung") ||
						BROWSER.contains("emulator"))
				{
					displayedContent = webDriver.findElements(GOOGLE_MAP_TERMS_OF_USE)
							.stream()
							.filter(a -> a.getText().equalsIgnoreCase(content))
							.findFirst()
							.orElseThrow(() -> new NotFoundException("No link found: " + content))
							.getText();
				}
				else
				{
					displayedContent = "Report a map error";
				}
				break;
		}
		return displayedContent;
	}

	public boolean getGoogleMapPins()
	{
		pause(5000);
		waitForElementVisible(COLLECTION_SEARCH_RESULT_MAP, 10);
		if (!webDriver.findElements(COLLECTION_MAP_PINS).isEmpty())
		{
			final int size = webDriver.findElements(COLLECTION_MAP_PINS).size();
			log.info("Total number of pins in map are  -- " + size);
			return true;
		}
		return false;
	}

	public int getNumberOfMapPinsDisplayed()
	{
		pause(5000);
		waitForElementVisible(COLLECTION_SEARCH_RESULT_MAP, 10);
		if (!webDriver.findElements(COLLECTION_MAP_PINS).isEmpty())
		{
			final int size = webDriver.findElements(COLLECTION_MAP_PINS).size();
			log.info("Total number of pins in map are  -- " + size);
			return size;
		}
		return 0;
	}

	public int getNumberOfResultsDisplayed()
	{
		pause(2000);
		if (!webDriver.findElements(COLLECTION_STORE_PINS_DISPALYED).isEmpty())
		{
			final int size = webDriver.findElements(COLLECTION_STORE_PINS_DISPALYED).size();
			log.info("Total number of pins in map are  -- " + size);
			return size;
		}
		return 0;
	}

	public void clickTheFirstResultPinOfCheckoutPageMap()
	{
		pause(2000);
		waitForAndGetElement(COLLECTION_NEWLOOK_MAP_PINS, 7);
		if (!webDriver.findElements(COLLECTION_NEWLOOK_MAP_PINS).isEmpty())
		{
			clickByJavaScriptExecutor(getWebDriver().findElements(COLLECTION_NEWLOOK_MAP_PINS).get(0));
		}
	}

	private List<WebElement> getStoreResultsList()
	{
		if (PEAK_CAPPING_FEATURE)
		{
			return visibilityOfAllElementsLocatedBy(PEAK_CAPPING_STORE_SEARCH_RESULTS);
		}
		else
		{
			return visibilityOfAllElementsLocatedBy(STORE_SEARCH_RESULTS);
		}
	}

	private List<WebElement> getSelectedStoreLabelList()
	{
		if (PEAK_CAPPING_FEATURE)
		{
			return visibilityOfAllElementsLocatedBy(PEAK_CAPPING_SelectedCollectionStoreLabel);
		}
		else
		{
			return visibilityOfAllElementsLocatedBy(selectedCollectionStoreLabel);
		}
	}

	public void selectCollectionType(final String collectionType, final CheckoutModel checkoutModel)
	{
		if (PEAK_CAPPING_FEATURE)
		{
			waitForPageLoad();
			waitForElementVisible(PEAK_CAPPING_STORE_SEARCH_RESULTS, 10);
			if (!getStoreResultsList().isEmpty())
			{
				scrollToObject(webDriver.findElements(PEAK_CAPPING_STORE_SEARCH_RESULTS).get(0));
				if (getStoreResultsList().stream()
						.filter(a -> a.getText().contains(collectionType))
						.findFirst()
						.orElseThrow(() -> new NotFoundException("No link found: " + collectionType))
						.isDisplayed())
				{
					clickWhenClickable(getStoreResultsList().stream()
									.filter(a -> a.getText().contains(collectionType))
									.findFirst()
									.orElseThrow(() -> new NotFoundException("No link found: " +
											collectionType)),
							10);
					checkoutModel.setCollectionType(collectionType);
				}
			}
		}
		else
		{
			waitForElementVisible(STORE_SEARCH_RESULTS, 10);
			if (!getStoreResultsList().isEmpty())
			{
				scrollToObject(webDriver.findElements(STORE_SEARCH_RESULTS).get(0));
				if (getStoreResultsList().stream()
						.filter(a -> a.getText().contains(collectionType))
						.findFirst()
						.orElseThrow(() -> new NotFoundException("No link found: " + collectionType))
						.isDisplayed())
				{
					clickWhenClickable(getStoreResultsList().stream()
									.filter(a -> a.getText().contains(collectionType))
									.findFirst()
									.orElseThrow(() -> new NotFoundException("No link found: " +
											collectionType)),
							10);
					checkoutModel.setCollectionType(collectionType);
				}
			}
		}
		log.info("Click the use this store button");
		clickByJavaScriptExecutor(waitForExpectedElement(SELECT_THE_STORE_BUTTON));
	}

	public boolean verifySelectedCollectionPoint(final CheckoutModel checkoutModel)
	{
		final int size = getSelectedStoreLabelList().size();
		for (int i = 0; i < size; i++)
		{
			if (PEAK_CAPPING_FEATURE)
			{
				if (webDriver.findElements(By.tagName("input")).get(i).isSelected() &&
						getSelectedStoreLabelList().get(i).getText().contains(checkoutModel.getCollectionType()))
				{
					return true;
				}
			}
			else if (webDriver.findElements(By.tagName("input")).get(i).isSelected() &&
					getSelectedStoreLabelList().get(i).getText().equalsIgnoreCase(checkoutModel.getCollectionType()))
			{
				return true;
			}
		}
		return false;
	}

	public void clickOnOtherCollectionType(final CheckoutModel checkoutModel)
	{
		for (int i = 0; i < getSelectedStoreLabelList().size(); i++)
		{
			if (!webDriver.findElements(By.tagName("input")).get(i).isSelected() &&
					!getSelectedStoreLabelList().get(i).getText().equalsIgnoreCase(checkoutModel.getCollectionType()))
			{
				clickByJavaScriptExecutor(getSelectedStoreLabelList().get(i));
				break;
			}
		}
	}

	public String getOrderSummary()
	{
		pause(2500);
		return waitForAndGetElement(orderSummary, DEFAULT_TIMEOUT).getText();
	}

	public void addDeliveryAddressManually(final String postCode,
	                                       final String addressLine1,
	                                       final String addressLine2,
	                                       final String city)
	{
		if (!webDriver.findElements(DEFAULT_DELIVERY_ADDRESS).isEmpty())
		{
			modifyDeliveryAddress(postCode, addressLine1, addressLine2, city);
		}
		else
		{
			if (!webDriver.findElements(TITLE_DROPDOWN_FIELD).isEmpty())
			{
				selectFromDropDown(webDriver.findElement(TITLE_DROPDOWN_FIELD), 1);
				webDriver.findElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_FIRST_NAME_FIELD)
						.sendKeys(RandomGenerator.randomAlphabetic(5));
				webDriver.findElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_LAST_NAME_FIELD)
						.sendKeys(RandomGenerator.randomAlphabetic(5));
				if (!webDriver.findElements(MOBILE_NUMBER_FIELD).isEmpty())
				{
					webDriver.findElement(MOBILE_NUMBER_FIELD)
							.sendKeys(com.salmon.test.framework.helpers.utils.RandomGenerator.randomInteger(11));
				}
				webDriver.findElement(ADDRESS_LINE_1).sendKeys(addressLine1);
				webDriver.findElement(ADDRESS_LINE_2).sendKeys(addressLine2);
				webDriver.findElement(POSTAL_CODE).sendKeys(postCode);
				webDriver.findElement(TOWN_FIELD).sendKeys(city);
				waitForExpectedElement1(ADD_NEW_ADDRESS_BUTTON, 5).click();
			}

		}
	}

	private void modifyDeliveryAddress(final String postCode,
	                                   final String addressLine1,
	                                   final String addressLine2,
	                                   final String city)
	{
		clickByJavaScriptExecutor(webDriver.findElement(CHANGE_DELIVERY_ADDRESS_BUTTON));
		waitForElementVisible(DELIVERY_ADDRESS_POPUP, 10);
		webDriver.findElement(ADD_NEW_ADDRESS_BUTTON_IN_POPUP).click();
		waitForElementVisible(ADDRESS_LINE_1, 10);
		if (!webDriver.findElements(POPUP_TITLE_DROPDOWN_FIELD).isEmpty())
		{
			selectFromDropDown(webDriver.findElement(POPUP_TITLE_DROPDOWN_FIELD), 1);
			clearAndSendkeys(POPUP_DELIVERY_ADDRESS_FIRST_NAME_FIELD, RandomGenerator.randomAlphabetic(5));
			clearAndSendkeys(POPUP_DELIVERY_ADDRESS_LAST_NAME_FIELD, RandomGenerator.randomAlphabetic(5));
			webDriver.findElement(POPUP_ADDRESS_LINE_1).sendKeys(addressLine1);
			webDriver.findElement(POPUP_ADDRESS_LINE_2).sendKeys(addressLine2);
			webDriver.findElement(POPUP_POSTAL_CODE).sendKeys(postCode);
			webDriver.findElement(POPUP_TOWN_FIELD).sendKeys(city);
			webDriver.findElement(POPUP_ADD_NEW_ADDRESS_BUTTON).click();
		}
	}


	public void fillCardDetails(final String cardType,
	                            final String cardNumber,
	                            final String date,
	                            final String year,
	                            final String cvv)
	{
		pause(2000);
		webDriver.findElement(PAY_WITH_CREDIT_CARD_OPTION).click();
		if (!webDriver.findElements(CARD_TYPE).isEmpty())
		{
			waitForAndGetElement(getWebDriver(), CARD_TYPE, DEFAULT_TIMEOUT);
			selectFromDropDownWithVisibleText(getWebDriver().findElement(CARD_TYPE), cardType);
			getWebDriver().findElement(CARD_NUMBER_INPUT_FIELD).sendKeys(cardNumber);
			selectFromDropDownWithVisibleText(getWebDriver().findElement(CARD_EXPIRE_MONTH_FIELD), date);
			selectFromDropDownWithVisibleText(getWebDriver().findElement(CARD_EXPIRE_YEAR_FIELD), year);
			webDriver.findElement(SAVE_MY_CARD_DETAILS_FOR_NEXT_TIME_RADIO_BUTTON).click();
			pause(2000);
			clickByJavaScriptExecutor(getWebDriver().findElement(USE_THIS_CARD_BUTTON));
		}

	}

	public void changeDeliveryCountry(final String deliveryCountry)
	{
		pause(3000);
		if (!webDriver.findElements(DEFAULT_DELIVERY_ADDRESS).isEmpty())
		{
			clickByJavaScriptExecutor(webDriver.findElement(CHANGE_DELIVERY_ADDRESS_BUTTON));
			waitForElementVisible(DELIVERY_ADDRESS_POPUP, 10);
			webDriver.findElement(ADD_NEW_ADDRESS_BUTTON_IN_POPUP).click();
			waitForElementVisible(CHECKOUT_PAYMENT_POPUP_COUNTRY_FIELD, 10);
			webDriver.findElement(CHECKOUT_PAYMENT_POPUP_COUNTRY_FIELD).click();
			waitForElementVisible(CHECKOUT_PAYMENT_POPUP_COUNTRY_SEARCH_FIELD, 10);
			clearAndSendkeys(CHECKOUT_PAYMENT_POPUP_COUNTRY_SEARCH_FIELD, deliveryCountry);
			waitForElementVisible(CHECKOUT_PAYMENT_POPUP_COUNTRY_SEARCH_RESULT, 10);
			webDriver.findElement(CHECKOUT_PAYMENT_POPUP_COUNTRY_SEARCH_RESULT).click();
			webDriver.findElement(POPUP_APPLY_CHANGE_BUTTON).click();
		}
		else
		{
			if (IS_MOBILE)
			{
				waitForElementPresence(CHANGE_COUNTRY_FIELD_MOBILE);
				selectFromDropDownWithVisibleText(getWebDriver().findElement(CHANGE_COUNTRY_FIELD_MOBILE),
						deliveryCountry);
			}
			else
			{
				waitForExpectedElement(DELIVERY_COUNTRY_FIELD, 10).click();
				waitForElementVisible(DELIVERY_COUNTRY_SEARCH_FIELD, 10);
				webDriver.findElement(DELIVERY_COUNTRY_SEARCH_FIELD).sendKeys(deliveryCountry);
				waitForElementVisible(DELIVERY_COUNTRY_SEARCH_RESULT, 10);
				webDriver.findElement(DELIVERY_COUNTRY_SEARCH_RESULT).click();
			}
		}
	}

	public void clickApplyChangeCountryButton()
	{
		waitForElementPresence(POPUP_APPLY_CHANGE_BUTTON);
		waitForElementVisible(POPUP_APPLY_CHANGE_BUTTON, 10);
		webDriver.findElement(POPUP_APPLY_CHANGE_BUTTON).click();
	}

	public String getPostalCode()
	{
		selectDeliveryOrCollectionOption("delivery");
		waitForAndGetElement(DELIVERY_ADDRESS_POSTCODE, DEFAULT_TIMEOUT);
		return waitForAndGetElement(DELIVERY_ADDRESS_POSTCODE, DEFAULT_TIMEOUT).getText();
	}

	public void clickDaytimeDeliveryMode()
	{
		pause(2000);
		clickByJavaScriptExecutor(webDriver.findElements(DELIVERY_MODE_2)
				.stream()
				.filter(a -> a.getText().contains("Daytime"))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Daytime delivery mode not found")));
	}

	public void clickFirstAvailableDate()
	{
		try
		{
			waitForAndGetElement(DELIVERY_DATE, DEFAULT_TIMEOUT).click();
		}
		catch (Exception e)
		{
			log.info("Date Not available");
		}
	}

	public void clickOrderSummaryTitleMobile()
	{
		waitForAndGetElement(MOBILE_ORDER_SUMMARY_TITLE, DEFAULT_TIMEOUT).click();
	}

	public void chooseDeliveryMode(final String mode)
	{
		waitForExpectedElement(DELIVERY_ADDRESS_SECTION, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(DELIVERY_ADDRESS_SECTION).isEmpty())
		{
			switch (mode.toUpperCase())
			{
				case "DAYTIME":
					clickByJavaScriptExecutor(webDriver.findElements(DELIVERY_MODE_2)
							.stream()
							.filter(a -> a.getText().contains("Daytime"))
							.findFirst()
							.orElseThrow(() -> new NotFoundException(
									"Daytime delivery mode not found")));
					break;
				case "EVENING":
					clickByJavaScriptExecutor(webDriver.findElements(DELIVERY_MODE_2)
							.stream()
							.filter(a -> a.getText().contains("Evening"))
							.findFirst()
							.orElseThrow(() -> new NotFoundException("Evening delivery mode not found")));
					break;
				case "STANDARD":
					clickByJavaScriptExecutor(webDriver.findElements(DELIVERY_MODE_2)
							.stream()
							.filter(a -> a.getText().contains("Standard"))
							.findFirst()
							.orElseThrow(() -> new NotFoundException("Standard delivery mode not found")));
					break;
				case "UK Royal Mail":
					clickByJavaScriptExecutor(webDriver.findElements(DELIVERY_MODE_2)
							.stream()
							.filter(a -> a.getText().contains("Royal Mail"))
							.findFirst()
							.orElseThrow(() -> new NotFoundException("UK Royal Mail delivery mode not found")));
					break;
			}
		}
	}

	public String getTextOfDeliveryMessage()
	{
		pause(4000);
		return getWebDriver().findElement(DELIVERY_ALERT_MESSAGE).getText();
	}

	public String getTextOfStandardDeliveryMessage()
	{
		return getWebDriver().findElement(STANDARD_DELIVERY_MESSAGE).getText();
	}

	public boolean checkDeliveryMethodsTitleDisplayed()
	{
		waitForElementVisible(DELIVERY_METHOD_TITLE, 10);
		return !webDriver.findElements(DELIVERY_METHOD_TITLE).isEmpty();
	}

	public boolean isSavedCardDisplayed()
	{
		return isElementVisible(SAVED_CARD_CLASS);
	}

	public boolean isConfirmCVVFieldDisplayed()
	{
		scrollIntoView(Confirm_CVV_Field);
		return waitForAndGetElement(Confirm_CVV_Field, DEFAULT_TIMEOUT).isDisplayed() &&
				waitForElementPresence(Confirm_CVV_Label, DEFAULT_TIMEOUT).isDisplayed();
	}

	public boolean isPlaceOrderButtonGreyedOut()
	{
		try
		{
			scrollIntoView(GREYED_PLACE_ORDER_BUTTON);
			pause(2000);
			return waitForAndGetElement(GREYED_PLACE_ORDER_BUTTON, DEFAULT_TIMEOUT).isDisplayed();
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean isPlaceOrderButtonEnable()
	{
		try
		{
			scrollIntoView(PLACE_ORDER_BUTTON);
			pause(2000);
			return waitForAndGetElement(PLACE_ORDER_BUTTON, DEFAULT_TIMEOUT).isEnabled();
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean isPaymentMethodErrorDisplayed()
	{
		return waitForAndGetElement(PAYMENT_METHOD_ERROR, DEFAULT_TIMEOUT).isDisplayed();
	}

	public String getTextOfSavedCollectionPopup()
	{
		pause(2000);
		return waitForAndGetElement(SAVED_COLLECTION_POPUP, DEFAULT_TIMEOUT).getText();
	}

	public boolean checkIsNewDesignPresent(final String designStatus)
	{
		switch (designStatus)
		{
			case "new":
				if (waitForExpectedElement(KLARNA_RADIO_BUTTON).isDisplayed())
				{
					return true;
				}
				break;

			case "old":
				if (waitForExpectedElement(CREDIT_DEBIT_CARD_BUTTON).isDisplayed() &&
						!isElementPresent(KLARNA_RADIO_BUTTON))
				{
					return true;
				}
				break;
		}

		return false;
	}

	public boolean checkIsKlarnaButtonPresent(final String klarnaButtonStatus)
	{

		switch (klarnaButtonStatus)
		{
			case "displayed":
				if (waitForExpectedElement(KLARNA_BUTTON).isDisplayed())
				{
					return true;
				}
				break;

			case "none-displayed":
				try
				{
					if (!waitForAndGetElement(KLARNA_BUTTON, 2).isDisplayed())
					{
						return true;
					}
				}
				catch (final NullPointerException e)
				{
					if (!isElementPresent(KLARNA_BUTTON))
					{
						return true;
					}
				}
				break;
		}
		return false;
	}

	public void addDeliveryAddressGuestUser()
	{
		scrollIntoView(TITLE_DROPDOWN_FIELD_GUEST);
		selectFromDropDown(webDriver.findElement(TITLE_DROPDOWN_FIELD_GUEST), "string:ms");
		waitForAndGetElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_FIRST_NAME_FIELD_GUEST, DEFAULT_TIMEOUT).sendKeys(
				RandomGenerator.randomAlphabetic(5));
		waitForAndGetElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_LAST_NAME_FIELD_GUEST, DEFAULT_TIMEOUT).sendKeys(
				RandomGenerator.randomAlphabetic(5));
		addBillingAddress("NW1 6XE");
		waitForAndGetElement(DEFAULT_DELIVERY_ADDRESS, DEFAULT_TIMEOUT);
		if (isElementVisible(DELIVERY_USE_THIS_ADDRESS, 3))
		{
			clickByJavaScriptExecutor(waitForExpectedElement1(DELIVERY_USE_THIS_ADDRESS, DEFAULT_TIMEOUT));
		}
		pause(2000);
	}

	public void clickPayWithKlarnaBtn()
	{
		scrollIntoView(KLARNA_BUTTON);
		clickByJavaScriptExecutor(waitForAndGetElement(KLARNA_BUTTON, DEFAULT_TIMEOUT));
		waitForAndGetElement(By.id("klarna-pay-later-main"), DEFAULT_TIMEOUT);
		pause(2000);
	}

	public boolean virtualCardWidgetIsDisplayed()
	{
		pause(3000);
		return isVirtualCreditCardWidgetImmediatelyDisplayed();
	}


	public boolean isVirtualCreditCardWidgetImmediatelyDisplayed()
	{
		pause(5000);
		scrollElementIntoView(TOTAL_AMOUNT);
		String totalAmount = getWebDriver().findElement(TOTAL_AMOUNT).getText().replace("£", "");
		double totalInt = Double.parseDouble(totalAmount);
		if (totalInt >= 35)
		{
			scrollElementIntoView(getWebDriver().findElement(By.id("klarnaOption_pay_later")));
			getWebDriver().findElement(By.xpath("//*[@id='klarnaOption_pay_later']/..")).click();
			pause(3000);
			final By h2HeadingKlarna = By.className("klarna__payment-options");
			return isElementVisible(h2HeadingKlarna, DEFAULT_TIMEOUT);
		}
		else
		{
			final By h2HeadingKlarna = By.className("klarna__payment-options");
			return isElementVisible(h2HeadingKlarna, DEFAULT_TIMEOUT);
		}
	}

	public boolean postcodeIsDisplayed(final String postcode)
	{
		return isElementPresent(By.xpath("//p[text()='" + postcode + "']"));
	}

	public void clickEditBag()
	{
		pause(3000);
		if (IS_MOBILE)
		{
			scrollElementIntoView(MOBILE_ORDER_SUMMARY_TITLE);
			waitForAndGetElement(MOBILE_ORDER_SUMMARY_TITLE, DEFAULT_TIMEOUT).click();
			pause(3000);
		}
		scrollElementIntoView(EDIT_BAG_LINK);
		waitForAndGetElement(EDIT_BAG_LINK, DEFAULT_TIMEOUT).click();
	}

	public void expandOrderSummaryWrap()
	{
		clickByJavaScriptExecutor(waitForExpectedElement(ORDERY_SUMMARY_WRAP, DEFAULT_TIMEOUT));
		waitForPageLoad();
	}

	public int getNumberOfAvailableDeliveryMethods()
	{
		pause(2000);
		return getWebDriver().findElements(By.cssSelector(".form-group.checkout__delivery-form-group")).size();
	}

	public void changeCard()
	{
		clickByJavaScriptExecutor(waitForExpectedElement(CHANGE_CARD));
		waitForPageLoad();
	}

	public boolean isCardSelectionOverlayDisplayed()
	{
		return isElementVisible(CARD_SELECTION_OVERLAY, DEFAULT_TIMEOUT);
	}

	public int getNumberOfPaymentCards()
	{
		return webDriver.findElements(AVAILABLE_CARDS).size();
	}

	public void useThisPaymentCard()
	{
		clickByJavaScriptExecutor(waitForExpectedElement(USE_THIS_PAYMENT_CARD));
	}

	public WebElement getCardNumberField()
	{
		webDriver.switchTo().defaultContent();
		frameToBeAvailableAndSwitchToIt(NUM_IFRAME_PCI_FORM);
		return waitForExpectedElement(CARD_NUMBER_INPUT_PCI_FORM);
	}

	public WebElement getValidCardIcon()
	{
		webDriver.switchTo().defaultContent();
		return waitForExpectedElement(MICROFORM_CARD_ICON_VALID);
	}

	public WebElement getUseThisCardButton()
	{
		webDriver.switchTo().defaultContent();
		return waitForExpectedElement(MICROFORM_USE_CARD_BUTTON);
	}

	public WebElement getCvvField()
	{
		pause(3000);
		webDriver.switchTo().defaultContent();
		frameToBeAvailableAndSwitchToIt(SEC_IFRAME_PCI_FORM);
		return waitForAndGetElement(CVV_INPUT_PCI_FORM, DEFAULT_TIMEOUT);
	}

	public WebElement getExpiryDateMonthField()
	{
		webDriver.switchTo().defaultContent();
		return waitForExpectedElement(MICROFORM_EXPIRY_DATE_MONTH);
	}

	public WebElement getExpiryDateYearField()
	{
		webDriver.switchTo().defaultContent();
		return waitForExpectedElement(MICROFORM_EXPIRY_DATE_YEAR);
	}

	public WebElement getAgeConfirmationCheckbox()
	{
		return waitForElementPresence(AGE_CONFIRMATION_CHECKBOX);
	}

	public void checkAgeConfirmationCheckbox()
	{
		if (isElementVisible(getPaymentDetailsContainer(), AGE_CONFIRMATION_WRAPPER, 3))
		{
			final WebElement checkbox = getAgeConfirmationCheckbox();
			if (!checkbox.getAttribute("class").contains("ng-not-empty"))
			{
				clickByJavaScriptExecutor(checkbox);
			}
		}
	}

	private WebElement getPaymentDetailsContainer()
	{
		return waitForExpectedElement(PAYMENT_DETAILS_CONTAINER);
	}

	public boolean isCheckoutVisible()
	{
		return isElementVisible(CHECKOUT_CONTAINER, 10);
	}

	public List<WebElement> getAlerts()
	{
		return presenceOfAllElementsLocatedBy(ALERT);
	}

	public void verify3DSCard()
	{
		if (isElementVisible(CARD_3DS_GATEWAY_IFRAME, 10))
		{
			frameToBeAvailableAndSwitchToIt(CARD_3DS_GATEWAY_IFRAME);
			waitForExpectedElement(CARD_3DS_PASSWORD_FIELD).sendKeys(CardProperties.password3DS());
			waitForExpectedElement(CARD_3DS_SUBMIT_BUTTON).click();
		}
		else if (isElementVisible(CARD_3DS_POPUP_IFRAME, 1))
		{
			frameToBeAvailableAndSwitchToIt(CARD_3DS_POPUP_IFRAME);
			if (isElementVisible(CARD_3DS_PASSWORD_FIELD, 1))
			{
				waitForExpectedElement(CARD_3DS_PASSWORD_FIELD).sendKeys(CardProperties.password3DS());
				waitForExpectedElement(CARD_3DS_SUBMIT_BUTTON).click();
			}
			else
			{
				waitForExpectedElement(CARD_3DS_POPUP_PASSWORD_FIELD).sendKeys(CardProperties.password3DS());
				waitForExpectedElement(CARD_3DS_POPUP_SUBMIT_BUTTON).click();
			}
		}
	}

	public String getOrderNumber()
	{
		final String text = waitForExpectedElement(ORDER_NUMBER_ID).getText();
		return text.substring(text.lastIndexOf(" "));
	}

	public WebElement getAlert(final String alertText)
	{
		return getAlerts()
				.stream()
				.filter(element -> element.getText().equalsIgnoreCase(alertText))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Failed payment error message not visible"));
	}

	public String getSaveCardMessage()
	{
		try
		{
			return find(SAVE_CARD_MESSAGE).getText();
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public boolean getFirstDeliverySelectedOrNot()
	{
		try
		{
			waitForAndGetElement(FIRST_DELIVERY_OPTION,DEFAULT_TIMEOUT);
			return find(FIRST_DELIVERY_OPTION).getAttribute("class").equals("form-group checkout__delivery-form-group checkout__delivery-form-group--highlight");
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public void billingAddressFormDisplayedInsideSelectedPaymentOption(String paymentMethod)
	{
		try
		{
			switch (paymentMethod.toLowerCase())
			{
				case "card":
					Assert.assertTrue("Card Billing form not displayed", waitForAndGetElement(CARD_BILLING_ADDRESS_FORM, DEFAULT_TIMEOUT).isDisplayed() &&
							waitForAndGetElement(CARD_BILLING_ADDRESS_FORM, DEFAULT_TIMEOUT).isEnabled());
					Assert.assertNull(waitForAndGetElement(KLARNA_BILLING_ADDRESS_FORM, DEFAULT_TIMEOUT));
					pause(2000);
					break;
				case "klarna":
					Assert.assertTrue("Klarna Billing form not displayed", waitForAndGetElement(KLARNA_BILLING_ADDRESS_FORM, DEFAULT_TIMEOUT).isDisplayed() &&
							waitForAndGetElement(KLARNA_BILLING_ADDRESS_FORM, DEFAULT_TIMEOUT).isEnabled());
					Assert.assertNull(waitForAndGetElement(CARD_BILLING_ADDRESS_FORM, DEFAULT_TIMEOUT));
					pause(2000);
					break;
			}
		}
		catch (Exception e)
		{
			Assert.fail("Billing form not displayed inside payment method: " + paymentMethod.toLowerCase());
		}
	}

	public void isSavedBillingAddressDisplayed(String paymentMethod)
	{
		try
		{
			switch (paymentMethod.toLowerCase())
			{
				case "card":
					pause(2000);
					scrollIntoView(CARD_SAVED_BILLING_ADDRESS);
					Assert.assertTrue("Saved billing address for Card Payment not displayed",
							waitForAndGetElement(CARD_SAVED_BILLING_ADDRESS, DEFAULT_TIMEOUT).isDisplayed() &&
									waitForAndGetElement(CARD_SAVED_BILLING_ADDRESS, DEFAULT_TIMEOUT).isEnabled());
					Assert.assertNull(waitForAndGetElement(KLARNA_SAVED_BILLING_ADDRESS, DEFAULT_TIMEOUT));
					pause(2000);
					break;
				case "klarna":
					pause(2000);
					scrollIntoView(KLARNA_SAVED_BILLING_ADDRESS);
					Assert.assertTrue("Saved billing address for Klarna Payment not displayed",
							waitForAndGetElement(KLARNA_SAVED_BILLING_ADDRESS, DEFAULT_TIMEOUT).isDisplayed() &&
									waitForAndGetElement(KLARNA_SAVED_BILLING_ADDRESS, DEFAULT_TIMEOUT).isEnabled());
					Assert.assertNull(waitForAndGetElement(CARD_SAVED_BILLING_ADDRESS, DEFAULT_TIMEOUT));
					pause(2000);
					break;
			}
		}
		catch (Exception e)
		{
			Assert.fail("Saved Billing address not displayed inside payment method: " + paymentMethod.toLowerCase());
		}
	}

	public void verifyKlarnaPaymentOptionDisplayed(String paymentOption)
	{
		try
		{
			pause(5000);
			if (paymentOption.toLowerCase().equalsIgnoreCase("both"))
			{
				if (CHECKOUT_NEW_DESIGN_FEATURE)
				{
					webDriver.findElement(KLARNA_PAY_IN_30_DAYS_TAB).click();
					pause(2000);
					final WebElement frame_payLater = getWebDriver().findElement(IFRAME_KLARNA_PAY_LATER);
					webDriver.switchTo().frame(frame_payLater);
					pause(5000);
					Assert.assertTrue("Klarna payment option not displayed as " + paymentOption.toLowerCase(),
							waitForAndGetElement(KLARNA_PAY_IN_30_DAYS, DEFAULT_TIMEOUT).isDisplayed() &&
									waitForAndGetElement(KLARNA_PAY_IN_30_DAYS, DEFAULT_TIMEOUT).isEnabled());
					webDriver.switchTo().defaultContent();
					webDriver.findElement(KLARNA_PAY_OVER_TIME_TAB).click();
					final WebElement frame_PayOverTime = getWebDriver().findElement(IFRAME_KLARNA_PAY_OVER_TIME);
					webDriver.switchTo().frame(frame_PayOverTime);
					pause(5000);
					Assert.assertTrue("Klarna payment option not displayed as 'Pay Over the time'",
							waitForAndGetElement(KLARNA_PAY_OVER_TIME, DEFAULT_TIMEOUT).isDisplayed());
					webDriver.switchTo().defaultContent();
				}
				else
				{
					webDriver.findElement(KLARNA_PAY_IN_30_DAYS_RADIO_BTN).click();
					pause(2000);
					final WebElement frame_payLater = getWebDriver().findElement(IFRAME_KLARNA_PAY_LATER);
					webDriver.switchTo().frame(frame_payLater);
					pause(5000);
					Assert.assertTrue("Klarna payment option not displayed as " + paymentOption.toLowerCase(),
							waitForAndGetElement(KLARNA_PAY_IN_30_DAYS, DEFAULT_TIMEOUT).isDisplayed() &&
									waitForAndGetElement(KLARNA_PAY_IN_30_DAYS, DEFAULT_TIMEOUT).isEnabled());
					webDriver.switchTo().defaultContent();
					webDriver.findElement(KLARNA_PAY_OVER_TIME_RADIO_BTN).click();
					final WebElement frame_PayOverTime = getWebDriver().findElement(IFRAME_KLARNA_PAY_OVER_TIME);
					webDriver.switchTo().frame(frame_PayOverTime);
					pause(5000);
					Assert.assertTrue("Klarna payment option not displayed as 'Pay Over the time'",
							waitForAndGetElement(KLARNA_PAY_OVER_TIME, DEFAULT_TIMEOUT).isDisplayed());
					webDriver.switchTo().defaultContent();
				}
				pause(2000);
			}
			else if (paymentOption.toLowerCase().equalsIgnoreCase("pay later"))
			{
				final WebElement frame_PayOverTime = getWebDriver().findElement(IFRAME_KLARNA_PAY_LATER);
				webDriver.switchTo().frame(frame_PayOverTime);
				pause(5000);
				Assert.assertTrue("Klarna payment option displayed as " + paymentOption.toLowerCase(),
						waitForAndGetElement(KLARNA_PAY_IN_30_DAYS, DEFAULT_TIMEOUT).isDisplayed() &&
								waitForAndGetElement(KLARNA_PAY_IN_30_DAYS, DEFAULT_TIMEOUT).isEnabled());
				webDriver.switchTo().defaultContent();
				Assert.assertNull(waitForAndGetElement(KLARNA_PAY_OVER_TIME, DEFAULT_TIMEOUT));
				pause(2000);
			}
			else if (paymentOption.toLowerCase().equalsIgnoreCase("pay over time"))
			{
				final WebElement frame_PayOverTime = getWebDriver().findElement(IFRAME_KLARNA_PAY_OVER_TIME);
				webDriver.switchTo().frame(frame_PayOverTime);
				pause(5000);
				Assert.assertTrue("Klarna payment option displayed as " + paymentOption.toLowerCase(),
						waitForAndGetElement(KLARNA_PAY_OVER_TIME, DEFAULT_TIMEOUT).isDisplayed() &&
								waitForAndGetElement(KLARNA_PAY_OVER_TIME, DEFAULT_TIMEOUT).isEnabled());
				webDriver.switchTo().defaultContent();
				Assert.assertNull(waitForAndGetElement(KLARNA_PAY_IN_30_DAYS, DEFAULT_TIMEOUT));
				pause(2000);
			}
			else if (paymentOption.toLowerCase().equalsIgnoreCase("error"))
			{
				pause(3000);
				Assert.assertTrue("Klarna payment option displayed as " + paymentOption.toLowerCase(),
						waitForAndGetElement(KLARNA_ERROR_MSG, DEFAULT_TIMEOUT).isDisplayed());
				webDriver.switchTo().defaultContent();
				Assert.assertNull(waitForAndGetElement(KLARNA_PAY_OVER_TIME, DEFAULT_TIMEOUT));
				Assert.assertNull(waitForAndGetElement(KLARNA_PAY_IN_30_DAYS, DEFAULT_TIMEOUT));
			}
			else
			{
				Assert.fail("Invalid payment option provided as: " + paymentOption.toLowerCase());
			}
		}
		catch (Exception e)
		{
			Assert.fail("Payment option not displayed as: " + paymentOption.toLowerCase());
		}
	}

	public void clickPayPalBtn()
	{
		scrollIntoView(PAYPAL_CHECKBOX);
		clickByJavaScriptExecutor(webDriver.findElements(PAYPAL_CHECKBOX).get(0));
	}

	public void isPaypalReturnInformationDisplayed(String status)
	{
		try
		{
			if (status.toLowerCase().equalsIgnoreCase("displayed"))
			{
				scrollIntoView(PAYPAL_RETURN_INFORMATION);
				log.info("Paypal return information displayed");
				Assert.assertNotNull("Paypal return information not displayed", waitForAndGetElement(PAYPAL_RETURN_INFORMATION, DEFAULT_TIMEOUT));
				Assert.assertTrue("Paypal return information not displayed", waitForAndGetElement(PAYPAL_RETURN_INFORMATION, DEFAULT_TIMEOUT).isDisplayed());
			}
			else if (status.toLowerCase().equalsIgnoreCase("not displayed"))
			{
				log.info("Paypal return information not displayed");
				Assert.assertNull("Paypal return information displayed", waitForAndGetElement(PAYPAL_RETURN_INFORMATION, DEFAULT_TIMEOUT));
				Assert.assertTrue("Paypal return information displayed", webDriver.findElements(PAYPAL_RETURN_INFORMATION).size() == 0);
			}
		}
		catch (Exception e)
		{
			Assert.fail("Paypal return information not displayed");
		}
	}

	public void isPaypalCtaButtonActive(String status)
	{
		if (status.toLowerCase().equalsIgnoreCase("active"))
		{
			Assert.assertTrue("Paypal CTA button is active", waitForAndGetElement(PAYPAL_BUTTON, DEFAULT_TIMEOUT).isEnabled());
		}
		else if (status.toLowerCase().equalsIgnoreCase("inactive"))
		{
			Assert.assertTrue("Paypal CTA button is inactive", !waitForAndGetElement(PAYPAL_BUTTON, DEFAULT_TIMEOUT).isEnabled());
		}
		else
		{
			Assert.fail("Invalid Paypal button status");
		}
	}

	public void fillDeliveryAddressUsingPostcode(final String postCode)
	{
		pause(4000);
		try
		{
			if (webDriver.findElement(TITLE_DROPDOWN_FIELD).isDisplayed())
			{
				selectFromDropDown(webDriver.findElement(TITLE_DROPDOWN_FIELD), 1);
				webDriver.findElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_FIRST_NAME_FIELD).clear();
				webDriver.findElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_FIRST_NAME_FIELD)
						.sendKeys(RandomGenerator.randomAlphabetic(5));
				webDriver.findElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_LAST_NAME_FIELD).clear();
				webDriver.findElement(CHECKOUT_PAYMENTS_DELIVERY_ADDRESS_LAST_NAME_FIELD)
						.sendKeys(RandomGenerator.randomAlphabetic(5));
				if (!webDriver.findElements(MOBILE_NUMBER_FIELD).isEmpty())
				{
					webDriver.findElement(MOBILE_NUMBER_FIELD).clear();
					webDriver.findElement(MOBILE_NUMBER_FIELD).sendKeys("7894938774");
				}
				if (!webDriver.findElements(DELIVERY_ADDRESS_FINDER_FIELD).isEmpty())
				{
					webDriver.findElement(POSTAL_CODE).clear();
					webDriver.findElement(POSTAL_CODE).sendKeys(postCode);
					waitForElementVisible(DELIVERY_ADDRESS_LIST, 10);
					if (!webDriver.findElements(DELIVERY_ADDRESS_LIST).isEmpty() &&
							webDriver.findElements(DELIVERY_ADDRESS_LIST).get(0).isEnabled())
					{
						pause(3500);
						clickByJavaScriptExecutor(webDriver.findElements(DELIVERY_ADDRESS_LIST).get(0));
					}
					scrollIntoView(DELIVERY_ADDRESS_USE_THIS_ADDRESS_BUTTON);
					clickByJavaScriptExecutor(webDriver.findElements(DELIVERY_ADDRESS_USE_THIS_ADDRESS_BUTTON).get(0));
				}
				else
				{
					addDeliveryAddressManually(postCode);
				}
			}
		}
		catch (Exception e)
		{
			log.info("Delivery Address form not displayed");
		}
	}

	public void addDeliveryAddressManually(String postCode)
	{
		try
		{
			webDriver.findElement(ADDRESS_LINE_1).clear();
			webDriver.findElement(ADDRESS_LINE_1).sendKeys(RandomGenerator.randomAlphabetic(5));
			webDriver.findElement(ADDRESS_LINE_2).clear();
			webDriver.findElement(ADDRESS_LINE_2).sendKeys(RandomGenerator.randomAlphabetic(5));
			webDriver.findElement(TOWN_FIELD).clear();
			webDriver.findElement(TOWN_FIELD).sendKeys(RandomGenerator.randomAlphabetic(5));
			webDriver.findElement(POSTAL_CODE).clear();
			webDriver.findElement(POSTAL_CODE).sendKeys(postCode);
			scrollIntoView(USE_THIS_ADDRESS_BUTTON);
			clickByJavaScriptExecutor(webDriver.findElements(USE_THIS_ADDRESS_BUTTON).get(0));
		}
		catch (Exception e)
		{
			Assert.fail("Unable to fill address details form");
		}
	}

	public void verifyGhostTextDisplayed(String status)
	{
		try
		{
			pause(5000);
			if (status.equalsIgnoreCase("displayed"))
			{
				String ghostText = waitForAndGetElement(NEW_LOOK_STORE_CARD_TXTBOX, DEFAULT_TIMEOUT).getAttribute("placeholder").trim();
				String expiryYearPlaceHolder = waitForAndGetElement(By.id("storeCardExpiryYear"), DEFAULT_TIMEOUT).getAttribute("placeholder").trim();
				Assert.assertEquals("Ghost text not displayed under New Look Store card", "0000 0000 0000 0000", ghostText);
				Assert.assertEquals("Expiry Year placeholder not displayed under New Look Store card", "YY", expiryYearPlaceHolder);
			}
			else if (status.equalsIgnoreCase("not displayed"))
			{
				try
				{
					Assert.assertTrue("Ghost text displayed under New Look Store card", waitForAndGetElement(NEW_LOOK_STORE_CARD_TXTBOX, DEFAULT_TIMEOUT).getAttribute("placeholder").trim().isEmpty());
				}
				catch (Exception e)
				{
					Assert.fail("Ghost text displayed under New Look Store card");
				}
			}
			else
			{
				Assert.fail("Invalid Ghost text details");
			}
		}
		catch (Exception e)
		{
			Assert.fail("Unable to get Ghost text details");
		}
	}

	public void clickNewLookStoreCardBtn()
	{
		pause(3000);
		//scrollIntoView(NEWLOOK_STORECARD_BUTTON);
		clickByJavaScriptExecutor(waitForAndGetElement(NEWLOOK_STORECARD_BUTTON, DEFAULT_TIMEOUT));
		pause(3000);
	}

	public WebElement getCheckoutPaymentAddressPopup()
	{
		try
		{
			return getWebDriver().findElements(CHECKOUT_PAYMENT_BILLING_ADDRESS_POPUP_HEADER_LABEL).get(1);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public WebElement getCheckoutPaymentCardPopup()
	{
		try
		{
			return getWebDriver().findElements(CHECKOUT_PAYMENT_BILLING_ADDRESS_POPUP_HEADER_LABEL).get(2);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public WebElement getNewLookStoreCardMonthField()
	{
		return getWebDriver().findElement(By.id("storeCardExpiryMonth"));
	}

	public WebElement getNewLookStoreCardExpiryYear()
	{
		return getWebDriver().findElement(By.id("storeCardExpiryYear"));
	}

	public void verifyStoreCardInvalidExpiryErrorMessageDisplayed(String msgStatus, String expiryMonth, String expiryYear)
	{
		pause(4000);
		scrollElementIntoView(getNewLookStoreCardMonthField());
		getNewLookStoreCardMonthField().clear();
		getNewLookStoreCardExpiryYear().clear();
		pause(2000);
		getNewLookStoreCardMonthField().sendKeys(expiryMonth);
		getNewLookStoreCardExpiryYear().sendKeys(expiryYear);
		try
		{
			String errorMsg = waitForAndGetElement(By.xpath("//*[contains(@class,'microform_expiry storecard-expiry')]"), DEFAULT_TIMEOUT).getAttribute("data-error-message");
			if (msgStatus.equalsIgnoreCase("displayed"))
			{
				Assert.assertEquals("Error message not displayed for invalid expiry date", "Card has expired.", errorMsg);
			}
			else
			{
				Assert.assertEquals("Error message displayed for valid expiry date", "", errorMsg);
			}
		}
		catch (Exception e)
		{
			Assert.fail("Unable to verify Error message displayed for Invalid expiry date");
		}
	}

	public void verifyInvalidCardMessageDisplayed(String status, String field)
	{
		try
		{
			pause(5000);
			if (status.equalsIgnoreCase("displayed"))
			{
				String message = waitForAndGetElement(INVALID_CARD_MESSAGE, DEFAULT_TIMEOUT).getText();
				switch (field.toLowerCase())
				{
					case "number":
						assertEquals(AlertsProperties.invalidCardMessage(), message);
						Assert.assertEquals("Invalid Card Number Message not displayed under New Look Store card", AlertsProperties.invalidCardMessage(), message);
						break;
					case "expirydate":
						assertEquals(AlertsProperties.invalidExpiryDateMessage(), message);
						Assert.assertEquals("Invalid Card Expiry Date Message not displayed under New Look Store card", AlertsProperties.invalidExpiryDateMessage(), message);
						break;
					case "default":
						Assert.fail("Option provided in invalid. Please provide valid option.");
				}
			}
			else if (status.equalsIgnoreCase("not displayed"))
			{
				Assert.assertNull("Invalid Card Message displayed under New Look Store card", waitForAndGetElement(INVALID_CARD_MESSAGE, DEFAULT_TIMEOUT));
			}
			else
			{
				Assert.fail("Option provided in invalid. Please provide valid option.");
			}
		}
		catch (Exception e)
		{
			Assert.fail("Unable to assert invalid message details");
		}
	}

	public void verifyStoreCardChangeCardButtonDisplayed()
	{
		Assert.assertTrue("Change card button not displayed", isElementVisible(checkOutPage.getChangeStoreCardButton().get(0), DEFAULT_TIMEOUT));
		Assert.assertEquals("Only one Change card button not displayed", 1, checkOutPage.getChangeStoreCardButton().size());
	}

	public void verifyStoreCardBillingDetailsDisplayed()
	{
		Assert.assertTrue("Change card button not displayed", waitForAndGetElement(STORE_CARD_BILLING_DETAILS, DEFAULT_TIMEOUT).isDisplayed());
	}

	public boolean getDeliveryOptionText(String SearchTerm)
	{
		pause(2000);
		return getWebDriver().findElements(DELIVERY_MODE_2).stream()
		.map(WebElement::getText)
		.allMatch(elementText -> elementText.startsWith(SearchTerm));
	}

	public static void setFeatureStatus(String flagStatus, String featureName) {
		CheckoutAndPaymentsPage.runImpexFromFileEnableCodeExecution(CheckoutAndPaymentsPage
				.UPDATE_FEATURE_STATUS(Props.getProp(featureName), Boolean.parseBoolean(flagStatus)));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    public static void runImpexFromFileEnableCodeExecution(String impexBody) {
    	    TEST_ENV = System.getProperty("testEnv", Props.getProp(TEST_ENVIRONMENT));
            RestAssured.requestSpecification = new RequestSpecBuilder().build();
        given().log().all()
                .urlEncodingEnabled(false)
                .queryParams("enableCodeExecution", true)
                .body(impexBody)
                .when()
                .post(Props.getProp("impex").replace("testEnv",TEST_ENV))
                .prettyPeek();
    }

    public static final String UPDATE_FEATURE_STATUS(String featureName, boolean featureStatus){
        return "INSERT_UPDATE NeoworksConfigurationItem; key[unique = true]; value\n" +
        ";"+featureName+";"+String.valueOf(featureStatus);
    }
}
