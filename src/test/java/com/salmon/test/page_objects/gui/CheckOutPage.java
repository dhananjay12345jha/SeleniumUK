package com.salmon.test.page_objects.gui;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.models.gui.CheckoutModel;
import com.salmon.test.properties.CardProperties;
import com.salmon.test.properties.PaymentProperties;
import com.salmon.test.step_definitions.gui.GiftCardSection;

import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;
import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static com.salmon.test.page_objects.gui.CheckoutAndPaymentsPage.*;
import static com.salmon.test.page_objects.gui.Features.*;
import static com.salmon.test.page_objects.gui.HomePage.*;


public class CheckOutPage extends PageObject
{
	private Logger log = LogManager.getLogger(CheckOutPage.class.getName());
	private static String originalWindowHandle;
	public static final By CARDINAL_ID = By.id("Cardinal-Modal");
	public static final String CARDINAL_IFRAME = "Cardinal-CCA-IFrame";
	private static final By CODE_FIELD = By.id("password");
	private static final By SUBMIT_BUTTON_OTP = By.xpath("//input[@value='Submit']");
	public static final By PAYMENT_FORM = By.className("payment-authentication__payment-form");

	public static final By BAG_ITEMS = By.className("bag-item");
	private static final By STORE_SEARCH_RESULTS = By.cssSelector(".label.radio__label.pudo__standard-text.ng-binding");
	private static final By PEAK_CAPPING_STORE_SEARCH_RESULTS = By.className("pudo__standard-text");
	private static final By SELECT_THE_STORE_BUTTON = By.className("button--collection-selection");
	public static final By BAG_ITEM_NAME = By.cssSelector(".bag-item__prod-name>a");
	public static final By BAG_ITEM_COLOUR = By.className("bag-item__colour");
	public static final By BAG_ITEM_SIZE_SELECTED = By.cssSelector(".bag-item__size-selector option[selected='selected']");
	public static final By BAG_ITEM_SIZE_SINGLE = By.className("bag-item__size");
	public static final By BAG_HEADER = By.className("header-cart__title");
	public static final By CHECKOUT_IFRAME = By.cssSelector("iframe[class='side-panel__iframe']");

	//########################################
	public static final By TOTAL_ITEMS_IN_BASKET = By.xpath("//div[@class='header__bagcount ng-binding']");
	public static final By CHECKOUT_BUTTON = By.className("checkoutButton");
	public static final By noDeliveryUpdatesButton = By.cssSelector("label[for='delivery-updates-no']");
	public static final By saveShippingAddress = By.xpath("(//button[@name='useAddress'])[1]");

	//Collection locators
	private static final By CHANGE_DELIVERY_COUNTRY = By.cssSelector("[href*='#language-change']");
	private static final By CHANGE_COUNTRY_FIELD_MOBILE = By.cssSelector("select[data-ng-model='selected.country']");
	public static final By deliveryButton = By.xpath("//button[@id='delivery']");
	public static final By selectStoreButton = By.className("button--collection-selection");
	public static final By useThisAddressBilling = By.cssSelector("#billing button[name^='useAddress']");
	public static final By DEBIT_CARD = By.cssSelector(
			".checkout__payment-choice.checkout__payment-choice--card.checkout__payment-choice--Card.nl__select-credit-debit-card");
	public static final By SECURE_CHECKOUT = By.className("header-cart__button");
	private static final By PDP_CONTROLS_CONTAINER = By.className("product-side-wrapper");
	private static final By addToCartButton = By.className("button--primary__add");
	private static final By reviewAndCheckoutButton = By.xpath("//a[contains(text(),'REVIEW & CHECKOUT')]");
	private static final By qunatitiesInTheDropDown = By.cssSelector(
			"div[class='ui-select-choices-row-inner nl-select__row-inner']");
	private static final By promoPotentialReceiveMessage = By.cssSelector("span[data-translate*='messages.potential']");
	private static final By changeDeliveryCountryLink = By.cssSelector("a[href='#language-change']");
	private static final By countriesSuggested = By.xpath("//*[contains(@id,'ui-select-choices-row-11')]");
	private static final By alertText = By.cssSelector(".alert__list.alert__list--checkout");
	private static final By phoneNumberFieldInCheckout = By.xpath("//input[@name='customerPhoneNumber']");
	private static final By priceOfTheProduct = By.className("product-description__price");
	private static final By billingAddressSection = By.id("billing-address-section");
	private static final By ITEM_PRICE = By.className("bag-item__price");
	private static final By PROMO_CODE = By.id("bag-footer__promo-code");
	private static final By PROMO_CODE_BUTTON = By.className("bag-footer__button");
	private static final By PROMO_DISCOUNT = By.className("order-summary__row--promo");
	private static final By PROMO_DISCOUNT_VALUE = By.className("order-summary__value");
	private static final By PROMO_CODE_ERROR = By.className("coupon-error");
	private static final By DISCOUNT_MESSAGE = By.className("order-summary__subscriptionitems--messages");



	//***************************Delivery Address Locators**********************//
	private static final By selectedCountry = By.xpath("//*[@data-ng-model='selected.country']");
	private static final By countryor = By.xpath("//*[@data-ng-model='selected.country']");
	private static final By tileSelector = By.id("titleCode");
	private static final By firstname = By.id("firstName");
	private static final By lastName = By.id("lastName");
	private static final By addressLine1 = By.id("line1");
	private static final By addressLine2 = By.id("line2");
	private static final By city = By.id("town");
	private static final By postCode = By.id("postalCode");
	private static final By deliveryAddressForm = By.name("editAddressFormDelivery");
	private static final By collectionDeliveryAddressForm = By.name("nominatedUserForm");

	private static final By deliveryAddressFormMandatoryMobileTextBox = By.xpath("//*[@name='editAddressFormDelivery']//*[@id='mobilePhone']");
	private static final By deliveryAddressFormMandatoryMobileMessage = By.xpath("//*[@name='editAddressFormDelivery']//span[@class='form__subline-text ng-binding ng-scope']");

	private static final By collectionDeliveryAddressFormMandatoryMobileTextBox = By.xpath("//*[@name='nominatedUserForm']//*[@id='customerPhoneNumber']");
	private static final By collectionDeliveryAddressFormMandatoryMobileMessage = By.xpath("//*[@name='nominatedUserForm']//p[@class='ng-scope']");


	//******************Delivery Address Locators*********************//
	private static final By billingAddressTileSelector = By.xpath("(//select[@name='titleCode'])[2]");
	private static final By billingAddressTileSelectorEuro = By.xpath("(//select[@name='title'])[2]");
	private static final By billingAddressFirstname = By.xpath("(//input[@id='firstName'])[2]");
	private static final By billingAddressLastName = By.xpath("(//input[@id='lastName'])[2]");
	private static final By STORE_CARD_BILLING_ADDRESS_FIRST_NAME = By.id("firstName");
	private static final By STORE_CARD_BILLING_ADDRESS_LAST_NAME = By.id("lastName");
	private static final By billingAddressAddressLine1 = By.cssSelector("#billing #line1");
	private static final By billingAddressAddressLine2 = By.cssSelector("#billing #line2");
	private static final By billingAddressAddressLine3 = By.id("line3");
	private static final By billingAddressCity = By.cssSelector("#billing #town");
	private static final By billingAddressPostCode = By.cssSelector("#billing #postalCode");
	private static final By billingAddressPostCodeLabel = By.xpath("//*[@for='postalCode']");

	private static final By billingAddressSaveShippingAddress = By.xpath(
			"//*[@id=\"billing\"]/div/div/div[11]/button[1]");
	private static final By selectSate = By.xpath("(//select[@name='region'])[1]");
	private static final By ADDRESS_LOOKUP_RESULTS = By.xpath(
			"//div[contains(@class, 'address-lookup__results')]/ul/li[contains(@class, 'address-lookup__results')][1]");

	//********************Pay Pal Locators*******************//
	private static final By paypalButton = By.className("nl__select-paypal");
	private static final By paypalCheckoutButton = By.xpath("//form[@id='beginPayPalContainer']//button");
	private static final By paypalEmailid = By.id("email");
	private static final By paypalPassword = By.id("password");
	private static final By paypalLoginButton = By.id("btnLogin");
	private static final By paypalButtonPayNow = By.id("payment-submit-btn");
	private static final By deliveryServiceStandardDeliveryButton = By.cssSelector(
			"label[class='label radio__label ng-binding']");
	private static final By deliveryServicePremiumDeliveryButton = By.cssSelector(
			"label[class='label radio__label ng-binding']");
	private static final By selectCardType = By.name("card_type");
	private static final By cardNumber = By.name("card_number");
	private static final By submitCardDetails = By.name("submit");
	public static final By cardVerificationNumber = By.id("CV2");
	private static final By PLACE_ORDER_BUTTON = By.xpath("//button[(contains(@class,'checkout__total-submit'))]");
	private static final By PLACE_ORDER_BUTTON_KLARNA = By.xpath("//button[contains(@class,'button--klarna-pay')]");
	private static final By checkouMessage = By.className("confirmation-header__title");
	private static final By guestEmailAddress = By.id("email");
	private static final By checkOutAsGuestButton = By.cssSelector(
			".checkout__form-button.button.button--primary.guestCheckoutBtn");
	private static final By priceSummary = By.className("order-summary__value");
	private static final By selectSize = By.xpath("//select[@data-ng-model='productInfo.selectedSize']");
	private static final By selectQuantity = By.xpath("//*[@data-ng-model='entry.quantity']");
	private static final By selectSizeFromFullCartPage = By.xpath("//*[@data-ng-model='entry.selectedSize']");
	private static final By productWithNoSize = By.cssSelector("div[class*='bag-item__size']");
	private static final By oneSize = By.className("product-sizes__one-size");
	private static final By limitedQuantityProduct = By.cssSelector("a[href*='/Caps/']");
	private static final By addToCartInput = By.id("pdpAddtoCartInput");
	private static final By productRemoveLink = By.cssSelector("a[id*='removeEntry']");
	private static final By shoppitnCartEmptyMessage = By.cssSelector(".yCmsContentSlot.bag-empty__text>div>p");
	private static final By searchButton = By.xpath("(//button[@type='submit'])[1]");
	private static final By pdpQunatity = By.id("pdpAddtoCartInput");
	private static final By delveringCountry = By.className("bag-footer__delivering-to");
	private static final By changeColorLink = By.xpath("//a[contains(text(),'Change colour')]");
	private static final By colorSwatchPanel = By.cssSelector(
			".popover__content.bag-item__content.popover__content--above");
	private static final By colorName = By.className("bag-item__square-colour");
	private static final By productCode = By.cssSelector(".bag-item__prod-code.ng-binding");
	private static final By outOfStockText = By.cssSelector(".product-details__out-of-stock.ng-scope");
	private static final By fullCartLowInStock = By.cssSelector("div[class*='bag-item__message--low-stock']");

	//error prone fields
	private static final By collectionButton = By.className("tab-text__checkout--collection");
	private static final By postcodeOrCountryField = By.id("storelocator-query");
	private static final By collectionSubmitButton = By.cssSelector(".button.button--primary.button--store-search");
	private static final By collectionDetailsTitle = By.id("nominatedUserForm.nominatedSelect");
	private static final By collectionDetailsFirstName = By.id("nominatedFirstName");
	private static final By collectionDetailsLastName = By.id("nominatedLastName");
	private static final By collectionDetailsConfirmButton = By.cssSelector(
			".bag-setcollector__button.button.button--secondary.button--secondary-black");
	public static final By noStoresFoundError = By.xpath(
			"//div[contains(@class,'checkout-collectio')]/div[contains(@class, 'store-results__no-stores')]");
	private static final By changeCountry = By.xpath("//*[@data-ng-model='selected.country']");
	private static final By changeCountryMobile = By.xpath("//*[@data-ng-model='selected.country']");
	private static final By applyChangeButton = By.cssSelector("div[class*='checkout__delivery-country-select'] >button");
	private static final By countryInputTextField = By.xpath("(//input[@type='text'])[2]");
	private static final By countryInputBagPage = By.cssSelector(".bag-footer__delivery .nl-select__dropdown input");
	private static final By countryInputSelection = By.cssSelector(".bag-footer__delivery .ui-select-choices>li span");
	private static final By selectCountryForCollection = By.cssSelector(
			".ui-select-choices.ui-select-choices-content.nl-select__group.ng-scope");
	private static final By deliveryCountry = By.cssSelector("span[class*='bag-footer__delivering']");
	private static final By collectionLocation = By.xpath("//*[@class='collection-selection']");
	private static final By changeLocationLink = By.xpath("//a[contains(text(),'Change location')]");
	private static final By changeDeliveryCountry = By.cssSelector("[class='link ng-scope']");
	private static final By checkoutCountryPicker = By.cssSelector("span[class='country-selector__wrapper'] >div");
	private static final By checkoutSection = By.className("checkout__payment-wrap");
	private static final By continueToPaymentButton = By.xpath("//button[contains(@class,'continue-to-payment')]");
	private static final By continueToDeliveryButton = By.xpath("//*[contains(text(),'Continue to delivery options')]");
	private static final By secureCheckout = By.className("order-summary__checkout");
	private static final By SELECT_DELIVERY = By.xpath(
			"//div[@class='collection__tab-button' and contains(@data-ng-click,'delivery')]");
	private static final By DELIVERY_ADDRESS_HEADER_SELECTOR = By.id("delivery-address-section");
	private static final By useThisAddress = By.name("useAddress");
	private static final By checkOutTotalBalance = By.className("checkout__total-title--price");
	private static final By VIEW_BAG_BUTTON = By.xpath("//a[text()='View Bag & Checkout']");
	private static final By STORE_CARD = By.cssSelector(
			".checkout__payment-choice.checkout__payment-choice--StoreCard");
	private static final By STORE_CARD_NUMBER_FIELD = By.name("storeCardNumber");
	private static final By STORE_CARD_EXPIRY_MONTH_FIELD = By.name("storeCardExpiryMonth");
	private static final By STORE_CARD_EXPIRY_YEAR_FIELD = By.name("storeCardExpiryYear");
	private static final By STORE_CARD_USE_BUTTON = By.xpath("//*[contains(@class,'checkout-store-card__save-card-btn')]");
	private static final By PAYMENTS_OPTIONS_ACTIVE = By.xpath(
			"//button[contains(@data-ng-class,'checkout__payment-choice--active')]");
	private static final By ADDRESS_LOOKUP = By.id("addressLookup");
	private static final By MANUALLY_ADD_ADDRESS = By.cssSelector(".button-link.ng-scope");
	private static final By PREORDER_EMAIL = By.id("preorder-notification-email");
	private static final By STOCK_NOT_AVAILABLE = By.xpath(
			"//*[contains(text(), 'not available for your selected delivery country')]");
	private static final By COLLECTION_RADIO = By.cssSelector(".radio.radio--account");
	private static final By SAVE_CARD_FUTURE_USE = By.cssSelector(".checkbox--save-card>label");
	private static final String SAVE_CARD_NOT_EMPTY = "ng-not-empty";

	//***************************Delivery Modes**********************//
	private static final By DELIVERY_MODE_RADIO = By.className("delivery-mode__radio");
	private static final By RADIO_BUTTONS_DELIVERY = By.xpath(
			"//div[@id='modes']//div[contains(@class,'radio--checkout')]//input[not (@disabled)]//..//label");
	private static final By DELIVERY_MODES = By.xpath("//div[contains(@class,'delivery-modes')]");
	private static final By ACTIVE_DEL_OPTIONS = By.xpath(
			"//button[contains(@class,'delivery-mode__radio') and not(@disabled)]");
	private static final By ACTIVE_COLOR = By.xpath("//span[contains(@class,'swatches__swatch--active')]");
	private static final By ACTIVE_COLOR_LINK = By.xpath("//span[contains(@class,'swatches__swatch--active')]//a");
	private static final By products = By.cssSelector(".plp-item .plp-carousel");
	private static final By mobileNoFieldBilling = By.cssSelector("#billing #mobilePhone");
	public static final By mobileNoFieldDeliveruy = By.cssSelector("#delivery3 #mobilePhone");
	public static final By deliveryAddress = By.cssSelector(".checkout-type--selected>h3");
	private static final By trackMyOrderEmail = By.id("j_username");
	private static final By trackMyOrderPassword = By.id("j_password");
	private static final By trackOrder = By.cssSelector("button[data-ng-click*=loginForm]");
	private static final By TOTAL_INCLUDING_DELIVERY = By.cssSelector(".order__summary-total-title.ng-scope");
	private static final By chooseAnotherCollectionPoint = By.cssSelector(".collection-selection__link.ng-scope");
	@FindBy(css = ".collection__tab__collect--selected")
	public static final By mobileNoField = By.id("mobilePhone");
	public static final By oneSize2 = By.className("product-sizes__one-size");
	public static final By selectSize2 = By.cssSelector(".product-sizes select");
	private static final By billingManually2 = By.cssSelector("#billing .address-lookup__wrapper a");
	private static final By deliveryManually2 = By.cssSelector("#delivery3 .address-lookup__wrapper a");
	private static final By ordernoBy = By.className("recent-orders__order-number");
	private By orderNumberOms = By.cssSelector("[class*=content__order-number]");
	private static final By applyChangeCountry = By.cssSelector("#language-change .bag-footer__delivery button");
	private static final By ORDER_NUMBER = By.className("confirmation-order__title");
	private String parentWindow;
	private String childWindow;
	private HomePageOld homePage;
	private static final By MAST_HEADER_BASKET_ICON = By.cssSelector(
			".masthead__icon.masthead__icon--grey.masthead__icon-bag");
	private static final By MOBILE_BASKET_ICON = By.cssSelector(
			".masthead__mobile-wrapper .masthead__mobile-item a[href*='/cart']");
	private static final By MOBILE_BASKET_ICON_BAG_COUNT = By.cssSelector(
			".masthead__mobile-wrapper .masthead__mobile-item a[href*='/cart'] .header__bagcount");
	public static final By MAST_HEADER_SEARCH_FIELD = By.cssSelector(".auto-complete__text");
	private static final By CHANGE_DETAILS = By.cssSelector(".link.change__link.change-collector.ng-scope");
	private static final By PAYPAL_CONTINUE_BUTTON = By.id("payment-submit-btn");
	private static final By DELIVERY_PASS_APPLIED_FIELD = By.className("order-summary__subscriptionitems--messages");
	private static final By DELIVER_PASS_APPLIED_FIELD_MOBILE = By.className("confirmation-delivery-pass__detail--text");
	private final GiftCardSection giftCardSection = new GiftCardSection();
	private static final By SELECT_QUANTITY_MOBILE = By.cssSelector("select[data-ng-model='entry.decoratedQuantity']");
	private static final By TELEPHONE_NUMBER_MANDATORY = By.cssSelector(
			".input.checkout__delivery-phone.ng-pristine.ng-valid.ng-empty.ng-valid-pattern.ng-valid-maxlength");

	private static final By DELIVERY_TELEPHONE_NUMBER_MANDATORY = By.id("deliveryMobileAdd");


	private static final By AUTH_PASSWORD = By.id("password");
	private static final By AUTH_SUBMIT_BTN = By.name("UsernamePasswordEntry");
	private static final By payRegisteruserSigninLink = By.xpath("//a[@data-test-id='text-log-in-link']");
	public static final By DELIVERY_PASS_CONFIRMATION_TEXT_SELECTOR = By.className(
			"confirmation-delivery-pass__detail--text");
	public static final By DELIVERY_PASS_TICK_SELECTOR = By.cssSelector(
			".iconf.iconf--tick.collection__tick--subscription");
	public static final By EXPIRY_MONTH = By.id("expirationMonth");
	public static final By EXPIRY_YEAR = By.id("expirationYear");
	public static final By EXPIRY_ERROR = By.cssSelector("#my-form > div:nth-child(2) > div >span");
	public static final By CARD_NUMBER_ERROR = By.cssSelector("form[name='useThisCardForm'] div[id='number-container']");
	private static final By CARD_NUMBER_INPUT_PCI_FORM = By.cssSelector("form>input[name='number']");
	private static final By CVV_INPUT_PCI_FORM = By.cssSelector("body > input[name=securityCode]");
	private static final By NUM_IFRAME_PCI_FORM = By.cssSelector("#number-container > iframe");
	private static final By SEC_IFRAME_PCI_FORM = By.cssSelector("#securityCode-container > iframe");
	private static final By USE_THIS_CARD_PCI_FORM = By.xpath("//button[contains(@class,'microform_btn--use-this-card')]");
	private static final By SAVE_CARD_CHECKBOX = By.id("saveCard");
	private static final By USE_THIS_ADDRESS_BUTTON = By.className("checkout__delivery-btn--use-address");
	private static final By ADD_CARD_BUTTON = By.className("nl__add-another-card");
	private static final By CHANGE_STORE_CARD_BUTTON = By.xpath("//*[contains(@class,'checkout__radio-card-details--store-card')]//*[contains(@class,'nl__add-another-card')]");
	private static final By CHANGE_STORE_CARD_BUTTON_OLD_DESIGN = By.xpath("//*[contains(@class,'nl__add-another-card')]");
	private static final By CHANGE_STORE_CARD_BUTTON_MOBILE = By.xpath("//*[@class='checkout-store-card__change-card-links ng-scope checkout-store-card__change-card-links--radio']");
	private static final By CHANGE_STORE_CARD_BUTTON_OLD_DESIGN_MOBILE = By.xpath("//*[@class='checkout-store-card__change-card-links ng-scope']//*[@class='button-link checkout__payment-link checkout__payment-update-card nl__add-another-card ng-scope']");
	private static final By ADD_CARD_BUTTON_MOBILE = By.xpath("//div[@class='checkout__edit-card-buttons-wrap--inside-radio ng-scope']//*[@class='button-link checkout__payment-link checkout__payment-update-card nl__add-another-card ng-scope']");
	private static final By ADD_CARD_BUTTON_MOBILE_OLD_DESIGN = By.xpath("//*[@class='button-link checkout__payment-link checkout__payment-update-card nl__add-another-card ng-scope']");
	private static final By EXISTING_CARD = By.xpath("//*[contains(@class,'nl__select-credit-debit-card')]//*[@class='checkout__payment-card-summary ng-scope']");
	private static final By EXISTING_CARD_OLD_DESIGN = By.xpath("//*[@class='checkout__payment-card-summary ng-scope']");
	private static final By EXISTING_STORE_CARD = By.xpath("//*[contains(@class,'nl__select-store-card')]//*[@class='checkout__payment-card-summary ng-scope']");
	private static final By MODAL_CONTENT_CONTAINER = By.className("modal__content");

	// Klarna
	private static final By KLARNA_BUTTON = By.xpath("//button[contains(@class,'checkout__payment-choice--Klarna')]");
	public static final By KLARNA_PAY_LATER = By.id("klarnaOption_pay_later");
	private static final By KLARNA_VERIFY_POPUP_WINDOW = By.id("ua-opf-dialog");
	private static final By KLARNA_VERIFY_BUTTON = By.xpath("//*[@testid='confirm-and-pay']");
	private static final By KLARNA_VERIFY_CODE_FIELD = By.id("otp_field");
	private static final By KLARNA_VERIFY_CONFIRM_BUTTON = By.id("onContinue");
	private static final By KLARNA_VERIFY_CONFIRM_BUTTON_EMAIL = By.id("onEmailContinue");
	private static final By KLARNA_VERIFY_CONFIRM_COMPLETE_ACCOUNT = By.xpath("//*[@data-testid='kaf-button']");

	private static final By KLARNA_DATE_OF_BIRTH_FIELD = By.id("addressCollector-date_of_birth");
	private static final By KLARNA_IGNORE_MISMATCH_BUTTON = By.id("btn-continue");
	private static final By KLARNA_CONTINUE_ANYWAY_BUTTON = By.xpath("//*[@data-testid='kaf-button']");
	private static final By KLARNA_CONTINUE_PAY_OPTION = By.xpath("//*[@data-testid='select-payment-category']");
	private static final By KLARNA_CONTINUE_PICK_PLAN_OPTION = By.xpath("//*[@data-testid='pick-plan']");
	private static final By KLARNA_CONTINUE_NOT_NOW_OPTION = By.id("btn_not_now");

	private static final By KLARNA_EMAIL = By.id("email");
	private static final By KLARNA_PAYMENT_OPTIONS_TABS = By.xpath("//*[@class='klarna__payment-option klarna-tab-view__tab-wrap ng-scope']");
	private static final By CONFIRM_SECURITY_CODE_LBL_OLD_DESIGN = By.xpath("//*[@data-translate='checkout.payment.confirm.cv2']");
	private static final By CONFIRM_SECURITY_CODE_LBL_NEW_DESIGN= By.xpath("//*[@data-translate='checkout.security.code']");

	// Delivery method
	private static final By COLLECTION_METHOD_DISABLED = By.cssSelector(".collection__tab__collect--disabled");
	private static final By COLLECTION_METHOD_DISABLED_Text_Message = By.cssSelector(".collection__tab__collect--disabled>div>div>span[class='tab-button__secondary ng-scope']");
	private static final By HOME_DELIVERY_METHOD_ENABLED = By.xpath("//li[@class='collection__tab collection__tab__deliver']");
	private static final By HOME_DELIVERY_METHOD_SELECTED = By.cssSelector(".collection__tab__deliver.collection__tab--selected");
	private static final By Messaging_Above_Delivery_Option = By.cssSelector("p[data-translate='checkout.delivery.sfs.message']");
	private static final By Collection_Not_Available_Message = By.cssSelector("[data-translate='checkout.collection.collection.tab.disabled.text2']");

	public boolean isPCIFeatureFlagDisabled()
	{
		return !NewLookHelper.getFeatureStatus("feature.pci.microform.enabled.uk-site");
	}

	public WebElement returnBagHeader()
	{
		return waitForExpectedElement(BAG_HEADER);
	}

	public WebElement useThisCard()
	{
		try
		{
			return waitForExpectedElement(USE_THIS_CARD_PCI_FORM);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public boolean klarnaPaymentOption()
	{
		return isElementPresent(KLARNA_PAYMENT_OPTIONS_TABS, DEFAULT_TIMEOUT);
	}

	public WebElement addressLookup()
	{
		return getWebDriver().findElement(ADDRESS_LOOKUP);
	}

	public WebElement useThisAddress()
	{
		return waitForExpectedElement(useThisAddress);
	}

	private WebElement manaulAddAddress()
	{
		return waitForExpectedElement(MANUALLY_ADD_ADDRESS);
	}

	public WebElement itemAddedToBagGoToCart()
	{
		waitForElementPresence(VIEW_BAG_BUTTON);
		return getWebDriver().findElement(VIEW_BAG_BUTTON);
	}

	public WebElement storeCard()
	{
		waitForAndGetElement(STORE_CARD, DEFAULT_TIMEOUT);
		return getWebDriver().findElement(STORE_CARD);
	}

	public WebElement klarna()
	{
		scrollElementIntoView(KLARNA_BUTTON);
		waitForAndGetElement(KLARNA_BUTTON, DEFAULT_TIMEOUT);
		return getWebDriver().findElement(KLARNA_BUTTON);
	}

	public WebElement addressLookupResult()
	{
		return waitForExpectedElement(ADDRESS_LOOKUP_RESULTS);
	}

	public void enterStoreOrStaffCardDetails(final String cardNo, String expiryMonth, String expiryYear)
	{
		pause(4000);
		waitForAndGetElement(STORE_CARD_NUMBER_FIELD, DEFAULT_TIMEOUT);
		waitForPageLoad();
		pause(4000);
		clickByJavaScriptExecutor(waitForAndGetElement(STORE_CARD_NUMBER_FIELD, 10));
		waitForPageLoad();
		getWebDriver().findElement(STORE_CARD_NUMBER_FIELD).sendKeys(cardNo);
		waitForPageLoad();

		pause(3000);
		if (!CHECKOUT_NEW_DESIGN_FEATURE)
		{
			new Select(getWebDriver().findElement(STORE_CARD_EXPIRY_MONTH_FIELD)).selectByVisibleText(expiryMonth);
			new Select(getWebDriver().findElement(STORE_CARD_EXPIRY_YEAR_FIELD)).selectByVisibleText("20" + expiryYear);
		}
		else
		{
			waitForAndGetElement(STORE_CARD_EXPIRY_MONTH_FIELD,DEFAULT_TIMEOUT);
			getWebDriver().findElement(STORE_CARD_EXPIRY_MONTH_FIELD).sendKeys(expiryMonth);
			waitForAndGetElement(STORE_CARD_EXPIRY_YEAR_FIELD,DEFAULT_TIMEOUT);
			getWebDriver().findElement(STORE_CARD_EXPIRY_YEAR_FIELD).sendKeys(expiryYear);
		}

		if (STORE_CARDS_ENABLED_FEATURE)
		{
			clickByJavaScriptExecutor(getWebDriver().findElement(SAVE_CARD_FUTURE_USE));
		}

		if (new CheckoutAndPaymentsPage().getCheckoutPaymentAddressPopup() != null)
		{
			clickByJavaScriptExecutor(getWebDriver().findElement(STORE_CARD_USE_BUTTON));
		}
		else
		{
			if (CHECKOUT_USE_CARD_CTA_FEATURE)
			{
				scrollForFocus(STORE_CARD_USE_BUTTON, DEFAULT_TIMEOUT);
				clickByJavaScriptExecutor(getWebDriver().findElement(STORE_CARD_USE_BUTTON));
			}
		}
	}

	public WebElement secureCheckoutButton()
	{
		waitForExpectedElement(SECURE_CHECKOUT, 10);
		return getWebDriver().findElement(SECURE_CHECKOUT);
	}

	public WebElement itemPrice()
	{
		pause(1300);
		waitForAndGetElement(ITEM_PRICE, DEFAULT_TIMEOUT);
		return getWebDriver().findElement(ITEM_PRICE);
	}

	public WebElement promoCode()
	{
		return getWebDriver().findElement(PROMO_CODE);
	}

	public void clickPromoCodeButton()
	{
		waitForExpectedElement1(PROMO_CODE_BUTTON, 40);
		clickByJavaScriptExecutor(getWebDriver().findElement(PROMO_CODE_BUTTON));
	}

	public WebElement promoDiscount()
	{
		waitForExpectedElement1(PROMO_DISCOUNT, DEFAULT_TIMEOUT);
		pause(1000);
		return getWebDriver().findElement(PROMO_DISCOUNT).findElement(PROMO_DISCOUNT_VALUE);
	}

	public WebElement promoCodeError()
	{
		return waitForExpectedElement(PROMO_CODE_ERROR);
	}

	public WebElement oneSize()
	{
		return waitForExpectedElement(oneSize);
	}

	public WebElement addToCartButton()
	{
		if (IS_MOBILE)
		{
			pause(2000);
			waitForElementPresence(PDP_CONTROLS_CONTAINER);
			scrollIntoView(PDP_CONTROLS_CONTAINER);
		}
		return waitForExpectedElement(addToCartButton);
	}

	public boolean addToCartButtonPresent()
	{
		return isElementPresent(addToCartButton);
	}

	public boolean preOrderEmailPresent()
	{
		return isElementPresent(PREORDER_EMAIL);
	}

	public boolean stockNotAvailable()
	{
		return isElementPresent(STOCK_NOT_AVAILABLE);
	}

	public WebElement isDiscountMessagePresent()
	{
		return waitForExpectedElement(DISCOUNT_MESSAGE);
	}

	public WebElement checkOutButton()
	{
		return waitForExpectedElement(CHECKOUT_BUTTON);
	}

	public WebElement reviewAndCheckoutButton()
	{
		return waitForExpectedElement(reviewAndCheckoutButton);
	}

	public WebElement productWithNoSize()
	{
		return waitForExpectedElement(productWithNoSize);
	}

	public WebElement quantitiesInTheDropDown()
	{
		return waitForExpectedElement(qunatitiesInTheDropDown);
	}

	public WebElement changeDeliveryCountryLink()
	{
		return waitForExpectedElement(changeDeliveryCountryLink);
	}

	public WebElement outOfStockText()
	{
		waitForPageLoad();
		return waitForExpectedElement(outOfStockText);
	}

	public WebElement fullCartLowInStock()
	{
		return waitForExpectedElement(fullCartLowInStock);
	}

	public WebElement selectSizeFromFullCartPage()
	{
		return waitForExpectedElement(selectSizeFromFullCartPage);
	}

	public WebElement priceOfTheProduct()
	{
		return waitForExpectedElement(priceOfTheProduct);
	}

	public WebElement continueToPaymentButton()
	{
		return waitForAndGetElement(continueToPaymentButton, DEFAULT_TIMEOUT);
	}

	public WebElement continueToDeliveryOptionButton()
	{
		return waitForAndGetElement(continueToDeliveryButton, DEFAULT_TIMEOUT);
	}

	public WebElement billingAddressSection()
	{
		return waitForExpectedElement(billingAddressSection);
	}

	public WebElement promoPotentialReceiveMessage()
	{
		return waitForExpectedElement(promoPotentialReceiveMessage);
	}

	public WebElement alertText()
	{
		return waitForExpectedElement(alertText);
	}

	public WebElement selectDebitOrCreditCardTab()
	{
		waitForElementPresence(DEBIT_CARD);
		scrollForFocus(DEBIT_CARD, 2);
		return waitForExpectedElement(DEBIT_CARD);
	}

	public String getConfirmationOrderNo()
	{
		return getWebDriver().findElement(ORDER_NUMBER).getText().substring(6, 16);
	}

	public void waitForOrderNO(final String orderNo)
	{
		if (IS_MOBILE)
		{
			waitForExpectedElement(By.xpath("//section[@class='my-account__order-details ']/h2[contains(text(),'" +
					orderNo +
					"')]"));
		}
		else
		{
			waitForElementWithText(ordernoBy, orderNo, 10);
		}
	}
	public void waitForOrderNOOms(final String orderNo)
	{
		if (IS_MOBILE)
		{
			waitForExpectedElement(By.xpath("//section[@class='my-account__order-details ']/h2[contains(text(),'" +
					orderNo +
					"')]"));
		}
		else
		{
			waitForElementWithText(orderNumberOms, orderNo, 10);
		}
	}

	public void clickDebitOrCreditCardTab()
	{
		waitForAndGetElement(DEBIT_CARD, DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(waitForAndGetElement(DEBIT_CARD, DEFAULT_TIMEOUT));
	}

	public WebElement noDeliveryUpdatesButton()
	{
		return waitForExpectedElement(noDeliveryUpdatesButton, 3);
	}

	public WebElement phoneNumberFieldInCheckout()
	{
		return waitForExpectedElement(phoneNumberFieldInCheckout, 5);
	}

	public WebElement deliveryButton()
	{
		return waitForExpectedElement(deliveryButton);
	}

	public WebElement postcodeOrCountryField()
	{
		waitForExpectedElement1(postcodeOrCountryField, 20);
		return getWebDriver().findElement(postcodeOrCountryField);
	}

	//************ Deliver Address Identifiers*******************************//

	public WebElement collectionSubmitButton()
	{
		return waitForExpectedElement(collectionSubmitButton);
	}

	public WebElement collectionButton()
	{
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad Pro") ||
				BROWSER.contains("emulator"))
		{
			pause(1500);
		}
		return waitForAndGetElement(collectionButton, 10);
	}

	public WebElement collectionDetailsTitle()
	{
		return waitForExpectedElement(collectionDetailsTitle);
	}

	public WebElement collectionDetailsFirstName()
	{
		return waitForExpectedElement(collectionDetailsFirstName, 4);
	}

	public WebElement collectionDetailsLastName()
	{
		return waitForExpectedElement(collectionDetailsLastName);
	}

	public WebElement collectionDetailsConfirmButton()
	{
		return waitForExpectedElement(collectionDetailsConfirmButton);
	}

	public WebElement selectedCountry()
	{
		return waitForExpectedElement(selectedCountry);
	}

	public WebElement countryor()
	{
		return waitForExpectedElement(countryor);
	}


	//************ Billing Address Identifiers*******************************//

	public WebElement tileSelector()
	{
		return waitForExpectedElement(tileSelector);
	}

	public WebElement firstname()
	{
		return waitForExpectedElement(firstname);
	}

	public WebElement lastName()
	{
		return waitForExpectedElement(lastName);
	}

	public WebElement addressLine1()
	{
		return waitForExpectedElement(addressLine1);
	}

	public WebElement addressLine2()
	{
		return waitForExpectedElement(addressLine2);
	}

	public WebElement city()
	{
		return waitForExpectedElement(city);
	}

	public WebElement mobileNumber()
	{
		return waitForExpectedElement(mobileNoField);
	}

	public WebElement postCode()
	{
		return waitForExpectedElement(postCode);
	}

	public WebElement billingAddressTileSelectorEuro()
	{
		return waitForExpectedElement(billingAddressTileSelectorEuro);
	}

	public WebElement billingAddressFirstname()
	{
		return waitForExpectedElement(billingAddressFirstname);
	}


	//**********************Pay pal Locators****************//

	public WebElement billingAddressLastName()
	{
		return waitForExpectedElement(billingAddressLastName);
	}

	public WebElement billingAddressAddressLine1()
	{
		return waitForExpectedElement(billingAddressAddressLine1);
	}

	public WebElement billingAddressAddressLine2()
	{
		return waitForExpectedElement(billingAddressAddressLine2);
	}

	public WebElement billingAddressAddressLine3()
	{
		return waitForExpectedElement(billingAddressAddressLine3);
	}

	public WebElement billingAddressCity()
	{
		return waitForExpectedElement(billingAddressCity);
	}

	public WebElement billingAddressPostCode()
	{
		return waitForExpectedElement(billingAddressPostCode);
	}

	public WebElement billingAddressPostCodeLabel()
	{
		return waitForExpectedElement(billingAddressPostCodeLabel);
	}

	public WebElement billingAddressSaveShippingAddress()
	{
		return waitForExpectedElement(billingAddressSaveShippingAddress);
	}

	public WebElement paypalButton()
	{
		waitForElementPresence(paypalButton);
		return waitForExpectedElement(paypalButton);
	}

	public WebElement paypalCheckoutButton()
	{
		return waitForExpectedElement(paypalCheckoutButton);
	}

	public WebElement paypalEmailid()
	{
		return waitForExpectedElement(paypalEmailid, 20);
	}

	public WebElement paypalPassword()
	{
		return waitForExpectedElement(paypalPassword);
	}

	public WebElement paypalLoginButton()
	{
		return waitForExpectedElement(paypalLoginButton);
	}

	public WebElement paypalButtonPayNow()
	{
		return waitForExpectedElement(paypalButtonPayNow);
	}

	public WebElement saveShippingAddress()
	{
		return waitForExpectedElement(saveShippingAddress);
	}

	public WebElement deliveryServiceStandardDeliveryButton()
	{
		return waitForExpectedElement(deliveryServiceStandardDeliveryButton);
	}

	public WebElement deliveryServicePremiumDeliveryButton()
	{
		return waitForExpectedElement(deliveryServicePremiumDeliveryButton);
	}

	public WebElement selectCardType()
	{
		return waitForExpectedElement(selectCardType);
	}

	public WebElement submitCardDetails()
	{
		return waitForExpectedElement1(submitCardDetails, 10);
	}

	public void clickSubmitCardDetails()
	{
		clickByJavaScriptExecutor(webDriver.findElements(submitCardDetails).get(0));
	}

	public void clickPlaceOrderButton()
	{
		pause(3000);
		clickByJavaScriptExecutor(webDriver.findElements(PLACE_ORDER_BUTTON).get(0));
		waitForPageLoad();
	}

	public WebElement cardNumber()
	{
		return waitForExpectedElement(cardNumber);
	}

	public WebElement cardNumberPCI()
	{
		return waitForExpectedElement(CARD_NUMBER_INPUT_PCI_FORM);
	}

	public WebElement cardVerificationNumber()
	{
		return waitForExpectedElement(cardVerificationNumber);
	}

	public WebElement cardVerificationNumberPCI()
	{
		return waitForExpectedElement(CVV_INPUT_PCI_FORM);
	}

	public WebElement placeOrderButton()
	{
		creditCard_overAge18Check();
		waitForPageLoad();
		scrollIntoView(PLACE_ORDER_BUTTON);
		log.info("place button found ");
		return waitForExpectedElement1(PLACE_ORDER_BUTTON, DEFAULT_TIMEOUT);
	}

	public WebElement placeOrderButton_Klarna()
	{
		return waitForAndGetElement(PLACE_ORDER_BUTTON_KLARNA, DEFAULT_TIMEOUT);
	}

	public void creditCard_overAge18Check()
	{
		pause(2000);
		if (isElementPresent((CREDIT_CARD_OVER_AGE_18), 5))
		{
			if (getWebDriver().findElement(CREDIT_CARD_OVER_AGE_18).isDisplayed())
			{
				clickByJavaScriptExecutor(webDriver.findElements(CREDIT_CARD_OVER_AGE_18).get(0));
			}
		}
	}

	public void clickOver18CheckBox(final int number)
	{
		pause(2000);
		if (isElementPresent(STORE_CARD_OVER_AGE_18, 5))
		{
			if (getWebDriver().findElements(STORE_CARD_OVER_AGE_18).get(number).isDisplayed())
			{
				clickByJavaScriptExecutor(webDriver.findElements(STORE_CARD_OVER_AGE_18).get(number));
			}
			else
			{
				if (getWebDriver().findElements(STORE_CARD_OVER_AGE_18).size() > 1)
				{
					clickByJavaScriptExecutor(webDriver.findElements(STORE_CARD_OVER_AGE_18).get(1));
				}
			}
			if (getWebDriver().findElements(STORE_CARD_OVER_AGE_18).size() > 2)
			{
				clickByJavaScriptExecutor(webDriver.findElements(STORE_CARD_OVER_AGE_18).get(2));
			}
		}
	}

	public void clickOver18CheckBoxGiftCard(final int number)
	{
		pause(2000);
		if (isElementPresent(GIFT_CARD_OVER_AGE_18, 5))
		{
			if (getWebDriver().findElements(GIFT_CARD_OVER_AGE_18).get(number).isDisplayed())
			{
				clickByJavaScriptExecutor(webDriver.findElements(GIFT_CARD_OVER_AGE_18).get(number));
			}
			else
			{
				if (getWebDriver().findElements(GIFT_CARD_OVER_AGE_18).size() > 1)
				{
					clickByJavaScriptExecutor(webDriver.findElements(GIFT_CARD_OVER_AGE_18).get(1));
				}
			}
			if (getWebDriver().findElements(GIFT_CARD_OVER_AGE_18).size() > 2)
			{
				clickByJavaScriptExecutor(webDriver.findElements(GIFT_CARD_OVER_AGE_18).get(2));
			}
		}
	}

	public void clickOver18CheckBoxKlarnaCard(final int number)
	{
		pause(2000);
		if (isElementPresent(KLARNA_CARD_OVER_AGE_18, 5))
		{
			if (getWebDriver().findElements(KLARNA_CARD_OVER_AGE_18).get(number).isDisplayed())
			{
				clickByJavaScriptExecutor(webDriver.findElements(KLARNA_CARD_OVER_AGE_18).get(number));
			}
			else
			{
				if (getWebDriver().findElements(KLARNA_CARD_OVER_AGE_18).size() > 1)
				{
					clickByJavaScriptExecutor(webDriver.findElements(KLARNA_CARD_OVER_AGE_18).get(1));
				}
			}
			if (getWebDriver().findElements(KLARNA_CARD_OVER_AGE_18).size() > 2)
			{
				clickByJavaScriptExecutor(webDriver.findElements(KLARNA_CARD_OVER_AGE_18).get(2));
			}
		}
	}

	public WebElement checkouMessage()
	{
		waitForPageLoad();
		waitForExpectedElement1(checkouMessage, 20);
		return getWebDriver().findElement(checkouMessage);
	}

	public boolean isDeliveryPassApplied()
	{
		scrollElementIntoView(DELIVERY_PASS_CONFIRMATION_TEXT_SELECTOR);
		isElementVisible(DELIVERY_PASS_TICK_SELECTOR, 30);
		return isElementVisible(DELIVERY_PASS_CONFIRMATION_TEXT_SELECTOR, 30);
	}

	public WebElement guestEmailAddress()
	{
		return waitForExpectedElement(guestEmailAddress);
	}

	public WebElement checkOutAsGuestButton()
	{
		return waitForExpectedElement(checkOutAsGuestButton);
	}

	public WebElement priceSummary()
	{
		return waitForExpectedElement(priceSummary);
	}

	public WebElement selectQuantity()
	{
		return waitForAndGetElement(selectQuantity, 10);
	}

	public WebElement basketIcon()
	{
		pause(600);
		if (IS_MOBILE)
		{
			return waitForAndGetElement(MOBILE_BASKET_ICON, 10);
		}
		return waitForAndGetElement(MAST_HEADER_BASKET_ICON, 10);
	}

	public WebElement shoppingCartEmptyMessage()
	{
		return waitForExpectedElement(shoppitnCartEmptyMessage);
	}

	public WebElement addToCartInput()
	{
		return waitForExpectedElement(addToCartInput);
	}

	public WebElement limitedQuantityProduct()
	{
		return waitForExpectedElement(limitedQuantityProduct);
	}

	public WebElement selectSize()
	{
		return waitForExpectedElement(selectSize);
	}

	public WebElement checkoutIframe()
	{
		return waitForExpectedElement(CHECKOUT_IFRAME);
	}

	public WebElement productRemoveLink()
	{
		pause(4000);
		try
		{
			scrollIntoView(productRemoveLink);
			return waitForElementPresence(productRemoveLink);
		}
		catch (StaleElementReferenceException e)
		{
			return waitForElementPresence(productRemoveLink);
		}

	}

	public WebElement totalItemsInTheBasket()
	{
		return waitForAndGetElement(MAST_HEADER_BAG_COUNT, DEFAULT_TIMEOUT);
	}

	public WebElement totalItemsInTheBasketMobile()
	{
		return waitForAndGetElement(MAST_HEADER_BAG_COUNT_MOBILE, DEFAULT_TIMEOUT);
	}

	public WebElement searchField()
	{
		return waitForAndGetElement(MAST_HEADER_SEARCH_FIELD, 10);
	}

	public WebElement searchButton()
	{
		return waitForAndGetElement(MAST_HEADER_SEARCH_BUTTON, DEFAULT_TIMEOUT);
	}

	public WebElement pdpQuantity()
	{
		return waitForExpectedElement(pdpQunatity);
	}

	public WebElement changeColorLink()
	{
		return waitForExpectedElement(changeColorLink);
	}

	public WebElement colorSwatchPanel()
	{
		return waitForExpectedElement(colorSwatchPanel);
	}

	public WebElement productCode()
	{
		return waitForExpectedElement(productCode);
	}

	public WebElement colorName()
	{
		return waitForExpectedElement(colorName);
	}

	public WebElement deliveringCountry()
	{
		return waitForExpectedElement(delveringCountry);
	}

	public WebElement noStoresFoundError()
	{
		return waitForExpectedElement(noStoresFoundError);
	}

	public WebElement changeCountry()
	{
		return waitForExpectedElement(changeCountry);
	}

	public WebElement applyChangeButton()
	{
		return waitForExpectedElement(applyChangeButton);
	}

	public WebElement countryInputTextField()
	{
		return waitForExpectedElement(countryInputTextField);
	}

	public WebElement selectCountryForCollection()
	{
		return waitForAndGetElement(selectCountryForCollection, DEFAULT_TIMEOUT);
	}

	public WebElement deliveryCountry()
	{
		return waitForExpectedElement(deliveryCountry);
	}

	public WebElement collectionLocation()
	{
		return waitForExpectedElement(collectionLocation);
	}

	public WebElement changeLocationLink()
	{
		return waitForExpectedElement(changeLocationLink);
	}

	public WebElement changeDeliveryCountry()
	{
		return waitForExpectedElement(changeDeliveryCountry);
	}

	public WebElement checkoutCountryPicker()
	{
		return waitForExpectedElement(checkoutCountryPicker);
	}

	public WebElement selectDelivery()
	{
		waitForPageLoad();
		return waitForExpectedElement(SELECT_DELIVERY);
	}

	public void clickOnDeliveryMethod()
	{
		waitForExpectedElement1(SELECT_DELIVERY, 10).click();
		waitForExpectedElement1(DELIVERY_ADDRESS_HEADER_SELECTOR, 30);
	}

	public void clickCheckoutButton()
	{
		clickByJavaScriptExecutor(waitForAndGetElement(CHECKOUT_BUTTON, 10));
	}

	public WebElement checkOutTotalBalance()
	{
		return waitForElementPresence(checkOutTotalBalance);
	}

	public void selectTitleFromDropdownList(final String title)
	{
		tileSelector();
		new Select(getWebDriver().findElement(By.id("titleCode"))).selectByVisibleText(title);
	}

	public void selectTitle()
	{
		selectTitleFromDropdownList("Mr.");
	}

	public void selectTitleForCollection()
	{
		collectionDetailsTitle();
		new Select(getWebDriver().findElement(By.id("nominatedUserForm.nominatedSelect"))).selectByVisibleText("Mr.");
	}

	public void selectTitleForCollectionRestOfWorld()
	{
		collectionDetailsTitle();
		new Select(getWebDriver().findElement(By.id("nominatedUserForm.nominatedSelect"))).selectByIndex(1);
	}

	public void selectBillingAddressTitle()
	{
		new Select(getWebDriver().findElement(billingAddressTileSelector)).selectByVisibleText("Mr.");
	}

	public void selectCard()
	{
		selectCardType();
		new Select(getWebDriver().findElement(By.name("card_type"))).selectByVisibleText("Visa");
	}

	public void selectCard(final String cardType)
	{
		selectCardType();
		new Select(getWebDriver().findElement(By.name("card_type"))).selectByVisibleText(cardType);
	}

	public void cardExpiryDate()
	{
		if (WebDriverHelper.BROWSER.contains("microsoftEdge") | WebDriverHelper.PLATFORM.contains("macOS 10.14"))
		{
			scrollElementIntoView(getWebDriver().findElement(By.name("card_expiry_month")));
		}
		new Select(getWebDriver().findElement(By.name("card_expiry_month"))).selectByVisibleText("06");
	}

	public void cardExpiryDate(final String date)
	{
		new Select(getWebDriver().findElement(By.name("card_expiry_month"))).selectByVisibleText(date);
	}

	public void inputExpiryDate(final String date, final By element)
	{
		waitForExpectedElement(element).clear();
		waitForExpectedElement(element).sendKeys(date);
	}

	public void cardExpiryYear()
	{
		new Select(getWebDriver().findElement(By.name("card_expiry_year"))).selectByVisibleText("2022");
	}

	public void cardExpiryYear(final String year)
	{
		new Select(getWebDriver().findElement(By.name("card_expiry_year"))).selectByVisibleText(year);
	}

	public void selectColorCodeFromSwatch()
	{
		colorName();
		final List<WebElement> colorCodes = getWebDriver().findElements(By.cssSelector(".bag-item__square-colour"));
		final int sizeOfColorCodes = colorCodes.size();

		if (sizeOfColorCodes == 1)
		{
			colorCodes.get(0).click();
		}
		else
		{
			if (sizeOfColorCodes > 2)
			{
				colorCodes.get(2).click();
			}
		}
	}

	public boolean checkSwatchPanelIsAvailable()
	{
		try
		{
			getWebDriver().findElement(By.cssSelector(".popover__content.bag-item__content.popover__content--above"));
			return true;
		}
		catch (final NoSuchElementException ignored)
		{
		}

		return false;
	}

	public void verifyOver18Age()
	{
		try
		{
			if (isElementPresent((CREDIT_CARD_OVER_AGE_18), 5))
			{
				if (getWebDriver().findElement(CREDIT_CARD_OVER_AGE_18).isDisplayed())
				{
					clickByJavaScriptExecutor(webDriver.findElements(CREDIT_CARD_OVER_AGE_18).get(0));
				}
			}
		}
		catch (final NoSuchElementException ignored)
		{
			log.info("Over 18 confirmation issue");
		}
	}

	public void clickVerifyOver18AgeCommon()
	{
		try
		{
			if (isElementPresent((OVER_AGE_18_CHECKBOX_COMMON), 5))
			{
				if (getWebDriver().findElement(OVER_AGE_18_CHECKBOX_COMMON).isDisplayed())
				{
					clickByJavaScriptExecutor(webDriver.findElements(OVER_AGE_18_CHECKBOX_COMMON).get(0));
				}
			}
		}
		catch (final NoSuchElementException ignored)
		{
			log.info("Over 18 confirmation issue");
		}
	}

	public void fillTheCheckoutAddressForm() throws InterruptedException
	{
		selectTitle();
		firstname().sendKeys("neo");
		lastName().sendKeys("works");
		addressLine1().sendKeys("Basinghall Street");
		addressLine2().sendKeys("City Tower");
		city().sendKeys("London");
		postCode().sendKeys("KT1 3HP");
		((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, 250)", "");
		saveShippingAddress().click();
		Thread.sleep(2000);
		deliveryServiceStandardDeliveryButton().click();
		try
		{
			noDeliveryUpdatesButton().click();
		}
		catch (final Exception e)
		{
			log.info("no delivery option");
		}
		((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, 250)", "");
	}

	public boolean deliveryEditFormWithMandatoryMobileNumberDisplayed()
	{
		return find(deliveryAddressForm).isDisplayed() && find(deliveryAddressFormMandatoryMobileTextBox).isDisplayed()
				&& find(deliveryAddressFormMandatoryMobileMessage).getText().contains("Don't worry, we'll only send you delivery updates.");
	}

	public boolean collectionDeliveryEditFormWithMandatoryMobileNumberDisplayed()
	{
		return find(collectionDeliveryAddressForm).isDisplayed() && find(collectionDeliveryAddressFormMandatoryMobileTextBox).isDisplayed()
				&& find(collectionDeliveryAddressFormMandatoryMobileMessage).getText().contains("Don't worry, we'll only send you delivery updates.");
	}

	public void deliveryAddress(final String country)
	{
		try
		{
			selectTitle();
			firstname().sendKeys("mike");
			lastName().sendKeys("test");

			if (elementPresence(ADDRESS_LOOKUP))
			{
				scrollElementIntoView(By.className("checkout__section"));
				manaulAddAddress().click();
			}
			addressLine1().sendKeys("40 Basinghall Street");
			addressLine2().sendKeys("City Tower");
			city().sendKeys("London");
			if ("UK".equalsIgnoreCase(country))
			{
				postCode().sendKeys("KT1 3HP");
			}
			else
			{
				postCode().sendKeys("75045");
			}

			try
			{
				getWebDriver().findElement(mobileNoField).sendKeys("07598877990");
			}
			catch (final Exception e)
			{
				log.info("No mobile field");
			}

			//scrollElementIntoView(By.className("checkout__delivery-service"));
			scrollForFocusAndClick(useThisAddress, 5);
		}
		catch (Exception e)
		{
			Assert.fail("Not able to fill delivery address");
		}
	}

	public void lookupAddress(final String country) throws Exception
	{
		selectTitle();
		firstname().sendKeys("mike");
		lastName().sendKeys("test");
		try
		{
			getWebDriver().findElement(mobileNoField).sendKeys("07592277990");
		}
		catch (final Exception e)
		{
			log.info("there is no mobile field");
		}
		scrollElementIntoView(By.className("checkout__section"));
		addressLookup().sendKeys("MK107HP");
		Thread.sleep(2000);
		addressLookupResult().click();
		scrollElementIntoView(By.className("checkout__delivery-service"));
		useThisAddress().click();
	}

	public void yesDeliveryUpdates()
	{
		phoneNumberFieldInCheckout().clear();
		phoneNumberFieldInCheckout().sendKeys("7894938774");
		pause(1000);
		continueToPaymentButton().click();
	}//fill the delivery details with

	public void deliveryAddressWithNameAndSurname(final String firstName, final String lastName)
	{
		deliveryAddressWithPostcode(firstName, lastName, "KT1 3HP");
	}

	public void deliveryAddressWithPostcode(final String firstName, final String lastName,String postCode)
	{
		selectTitle();
		firstname().clear();
		firstname().sendKeys(firstName);
		lastName().clear();
		lastName().sendKeys(lastName);

		if (elementPresence(ADDRESS_LOOKUP))
		{
			scrollElementIntoView(By.className("checkout__section"));
			clickByJavaScriptExecutor(manaulAddAddress());
		}
		addressLine1().sendKeys("40 Basinghall Street");
		addressLine2().sendKeys("City Tower");
		city().sendKeys("London");
		postCode().sendKeys(postCode);

		try
		{
			getWebDriver().findElement(mobileNoField).sendKeys("07598877990");
		}
		catch (final Exception e)
		{
			log.info("No mobile field");
		}

		scrollForFocusAndClick(useThisAddress, 5);
		try
		{
			noDeliveryUpdatesButton().click();
		}
		catch (final Exception e)
		{
			log.info("no delivery option");
		}
		if (!CHECKOUT_DEFAULT_DELIVERY_SELECTED_FEATURE)
		{
			new CheckoutAndPaymentsPage().chooseDeliveryMode("DAYTIME");
		}
		new CheckoutAndPaymentsPage().clickFirstAvailableDate();
	}


	public void fillBillingAddressForm(final String postcode, final String city)
	{
		scrollIntoView(billingAddressFirstname());

		selectBillingAddressTitle();
		billingAddressFirstname().sendKeys(RandomGenerator.randomAlphabetic(5));
		billingAddressLastName().sendKeys(RandomGenerator.randomAlphabetic(5));
		billingAddressAddressLine1().sendKeys(RandomGenerator.randomAlphabetic(5));
		billingAddressAddressLine2().sendKeys(RandomGenerator.randomAlphabetic(5));
		billingAddressAddressLine3().sendKeys(RandomGenerator.randomAlphabetic(5));
		billingAddressCity().sendKeys(city);
		billingAddressPostCode().sendKeys(postcode);
		billingAddressSaveShippingAddress().click();
	}

	public void fillBillingAddressForm()
	{
		if (elementPresence(billingAddressFirstname))
		{
			scrollIntoView(billingAddressFirstname());

			selectBillingAddressTitle();
			billingAddressFirstname().clear();
			billingAddressFirstname().sendKeys(RandomGenerator.randomAlphabetic(5));
			billingAddressLastName().clear();
			billingAddressLastName().sendKeys(RandomGenerator.randomAlphabetic(5));

			try
			{
				getWebDriver().findElement(mobileNoFieldBilling).sendKeys("07698866778");
			}
			catch (final Exception e)
			{
				log.info("No mobile field");
			}

			if (elementPresence(ADDRESS_LOOKUP))
			{
				clickByJavaScriptExecutor(getWebDriver().findElement(billingManually2));
			}
			billingAddressAddressLine1().sendKeys(RandomGenerator.randomAlphabetic(5));
			billingAddressAddressLine2().sendKeys(RandomGenerator.randomAlphabetic(5));
			billingAddressCity().sendKeys("London");
			billingAddressPostCode().sendKeys("KT1 3HP");
			//elementToBeClickable(ADDRESS_LOOKUP_RESULTS).click();

			pause(1000);
			billingAddressPostCodeLabel().click();
			//Todo:webdriver wait

			pause(4000);
			final WebElement e = getWebDriver().findElement(By.cssSelector(".nl__add-new-billing-address-save"));
			((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", e);
			e.click();
			waitForPageLoad();
		}
	}

	public void fillBillingAddressFormStoreCard()
	{
		if (elementPresence(billingAddressFirstname))
		{
			scrollIntoView(STORE_CARD_BILLING_ADDRESS_FIRST_NAME);

			selectBillingAddressTitle();
			if (!webDriver.findElements(STORE_CARD_BILLING_ADDRESS_FIRST_NAME).isEmpty())
			{
				returnAvailableElement(STORE_CARD_BILLING_ADDRESS_FIRST_NAME).sendKeys(RandomGenerator.randomAlphabetic(5));
				returnAvailableElement(STORE_CARD_BILLING_ADDRESS_LAST_NAME).sendKeys(RandomGenerator.randomAlphabetic(5));
			}
			else
			{
				log.info("No Store care Billing form present");
			}
			try
			{
				getWebDriver().findElement(mobileNoFieldBilling).sendKeys("07698866778");
			}
			catch (final Exception e)
			{
				log.info("No mobile field");
			}

			if (elementPresence(ADDRESS_LOOKUP))
			{
				clickByJavaScriptExecutor(getWebDriver().findElements(billingManually2).get(0));
			}
			billingAddressAddressLine1().sendKeys(RandomGenerator.randomAlphabetic(5));
			billingAddressAddressLine2().sendKeys(RandomGenerator.randomAlphabetic(5));
			billingAddressCity().sendKeys("London");
			billingAddressPostCode().sendKeys("KT1 3HP");

			final WebElement e = getWebDriver().findElements(By.cssSelector(".nl__add-new-billing-address-save"))
					.get(0);
			((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", e);
			e.click();
		}
		else
		{
			CheckoutAndPaymentsPage checkoutAndPaymentsPageObj = new CheckoutAndPaymentsPage();
			AddNewBillingAddressPopup addNewBillingAddressPopupObj = new AddNewBillingAddressPopup();
			pause(3000);
			if (!getChangeStoreCardButton().isEmpty())
			{
				clickByJavaScriptExecutor(getChangeStoreCardButton().get(0));
			}
			else
			{
				checkoutAndPaymentsPageObj.selectChangeBillingAddress();
			}
			checkoutAndPaymentsPageObj.selectOptionInCheckoutPaymentPopup("ADDNEWADDRESS");
			addNewBillingAddressPopupObj.updateBillingAddress("KT1 3HP");
		}
	}

	public void fillBillingAddressFormManual(final String postCode, final String city)
	{
		continueToPaymentSection();
		if (elementPresence(billingAddressFirstname))
		{
			scrollIntoView(billingAddressFirstname());
			try
			{
				getWebDriver().findElement(mobileNoFieldBilling).sendKeys("07698866778");
			}
			catch (final Exception e)
			{
				log.info("No mobile field");
			}

			if (elementPresence(ADDRESS_LOOKUP))
			{
				getWebDriver().findElement(billingManually2).click();
			}
			billingAddressAddressLine1().sendKeys(RandomGenerator.randomAlphabetic(5));
			billingAddressAddressLine2().sendKeys(RandomGenerator.randomAlphabetic(5));
			billingAddressCity().sendKeys(city);
			billingAddressPostCode().sendKeys(postCode);
			//Todo:webdriver wait

			final WebElement e = getWebDriver().findElement(By.cssSelector(".nl__add-new-billing-address-save"));
			((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", e);
			e.click();
		}
	}

	public double getSubtotal()
	{
		final String[] splitSubtotal = priceSummary().getText().split("€");
		return Math.round(Double.parseDouble(splitSubtotal[1]));
	}

	public double getDeliveryTotal()
	{
		final List<WebElement> cartPrices = getWebDriver().findElements(By.cssSelector(".order-summary__value"));
		final String[] splitDeliveryTotal = cartPrices.get(0).getText().split("€");

		final double deliveryTotal = Double.parseDouble(splitDeliveryTotal[1]);

		return Math.round(deliveryTotal);
	}

	public void enterInvalidCardAndClosePopups(String cardNumber)
	{
		CheckoutAndPaymentsPage objCheckOut = new CheckoutAndPaymentsPage();
		if (isExistingCardVisible())
		{
			clickByJavaScriptExecutor(getAddNewCardButton());
			getUseThisAddressButton().click();
		}
		fillCardNumberWithPCIOn(cardNumber);
	}

	public void ClosePopups()
	{
		CheckoutAndPaymentsPage objCheckOut = new CheckoutAndPaymentsPage();
		if (objCheckOut.getCancelButtonOnPopup() != null)
		{
			objCheckOut.getCancelButtonOnPopup().click();
		}
		pause(2000);
		if (objCheckOut.getCrossButtonOnPopup() != null)
		{
			objCheckOut.getCrossButtonOnPopup().click();
		}
	}

	public void fillCardDetails(final String type, final String number)
	{
//		if (isPCIFeatureFlagDisabled())
//		{
//			waitForExpectedElement(cardNumber, 20);
//			scrollElementIntoView(cardNumber);
//			selectCard(type.trim());
//			cardNumber().sendKeys(number);
//			cardExpiryDate();
//			cardExpiryYear();
//			scrollElementIntoView(submitCardDetails);
//			submitCardDetails().click();
//			waitForPageLoad();
//			waitForExpectedElement(cardVerificationNumber, 60);
//			scrollElementIntoView(cardVerificationNumber);
//			cardVerificationNumber().sendKeys("123");
//		}
//		else
//		{
		try
		{
			Thread.sleep(5000);
			waitForPageLoad();
			if (isExistingCardVisible())
			{
				clickByJavaScriptExecutor(getAddNewCardButton());
				getUseThisAddressButton().click();
			}

			frameToBeAvailableAndSwitchToIt(NUM_IFRAME_PCI_FORM);
			Assert.assertTrue(isElementPresent(By.xpath("//*[@name='number']"), DEFAULT_TIMEOUT));

			//Below Line of code should be uncommented once bug is fixed - Bug# NLCC-6950
			//Assert.assertTrue(find(By.xpath("//*[@for='saveCard']")).equals("Save card details for next time"));
			cardNumberPCI().clear();
			cardNumberPCI().sendKeys(number);
			webDriver.switchTo().defaultContent();
			waitForPageLoad();
			fillCardExpiryDateWithPCIOn("06", "27");
			waitForPageLoad();
			frameToBeAvailableAndSwitchToIt(SEC_IFRAME_PCI_FORM);
			cardVerificationNumberPCI().clear();
			cardVerificationNumberPCI().sendKeys("123");
			webDriver.switchTo().defaultContent();
			try
			{
				if (new CheckoutAndPaymentsPage().getCheckoutPaymentCardPopup() != null)
				{
					clickByJavaScriptExecutor(useThisCard());
				}
			}
			catch (Exception e)
			{
				log.info("No Popup available");
			}
			if (CHECKOUT_USE_CARD_CTA_FEATURE)
			{
				clickByJavaScriptExecutor(useThisCard());
			}
			pause(5000);

			//To be tested as sometimes, we see CVV number is asked 2 times
			if(CHECKOUT_NEW_DESIGN_FEATURE)
			{
				if (getWebDriver().findElement(CONFIRM_SECURITY_CODE_LBL_NEW_DESIGN).isDisplayed())
				{
					getWebDriver().findElement(cardVerificationNumber).click();
					getWebDriver().findElement(cardVerificationNumber).sendKeys("123");
				}
			}
			else
			{
				if (getWebDriver().findElement(CONFIRM_SECURITY_CODE_LBL_OLD_DESIGN).isDisplayed())
				{
					getWebDriver().findElement(cardVerificationNumber).click();
					scrollIntoView(cardVerificationNumber);
					getWebDriver().findElement(cardVerificationNumber).sendKeys("123");
				}
			}
		}
		catch (Exception e)
		{
			log.info("Not able to fill card details");
		}
		pause(1000);
		getExistingCard();
		//}
		waitForPageLoad();
	}

	public WebElement getUseThisAddressButton()
	{
		return waitForExpectedElement(MODAL_CONTENT_CONTAINER).findElement(USE_THIS_ADDRESS_BUTTON);
	}

	public WebElement getAddNewCardButton()
	{
		if (IS_MOBILE)
		{
			if (!CHECKOUT_NEW_DESIGN_FEATURE)
			{
				return waitForExpectedElement(ADD_CARD_BUTTON_MOBILE_OLD_DESIGN);
			}
			else
			{
				return waitForExpectedElement(ADD_CARD_BUTTON_MOBILE);
			}
		}
		return waitForExpectedElement(ADD_CARD_BUTTON);
	}

	public List<WebElement> getChangeStoreCardButton()
	{
		if (IS_MOBILE)
		{
			if (!CHECKOUT_NEW_DESIGN_FEATURE)
			{
				return getWebDriver().findElements(CHANGE_STORE_CARD_BUTTON_OLD_DESIGN_MOBILE);
			}
			return getWebDriver().findElements(CHANGE_STORE_CARD_BUTTON_MOBILE);
		}
		else
		{
			if (!CHECKOUT_NEW_DESIGN_FEATURE)
			{
				return getWebDriver().findElements(CHANGE_STORE_CARD_BUTTON_OLD_DESIGN);
			}
			return getWebDriver().findElements(CHANGE_STORE_CARD_BUTTON);
		}
	}

	public boolean isExistingCardVisible()
	{
		if (!CHECKOUT_NEW_DESIGN_FEATURE)
		{
			return isElementVisible(EXISTING_CARD_OLD_DESIGN, 10);
		}
		return isElementVisible(EXISTING_CARD, 10);
	}

	public boolean isExistingStoreCardVisible()
	{
		return isElementVisible(EXISTING_STORE_CARD, 5);
	}

	public WebElement getExistingCard()
	{
		return waitForExpectedElement(EXISTING_CARD, 5);
	}

	public void uncheckSaveCardCheckbox()
	{
		final WebElement checkbox = waitForElementPresence(SAVE_CARD_CHECKBOX);
		if (checkbox.getAttribute("class").contains(SAVE_CARD_NOT_EMPTY))
		{
			checkbox.click();
		}
	}

	public void fillCardDetailsWithoutCVV()
	{
//		if (isPCIFeatureFlagDisabled())
//		{
//			waitForExpectedElement(cardNumber, 60);
//			scrollElementIntoView(cardNumber);
//			selectCard();
//			cardNumber().sendKeys("4111111111111111");
//			cardExpiryDate();
//			cardExpiryYear();
//			scrollElementIntoView(submitCardDetails);
//			clickSubmitCardDetails();
//			waitForExpectedElement(cardVerificationNumber, 40);
//			scrollElementIntoView(cardVerificationNumber);
//		}
//		else
		try
		{
			frameToBeAvailableAndSwitchToIt(NUM_IFRAME_PCI_FORM);
			cardNumberPCI().clear();
			cardNumberPCI().sendKeys("4111111111111111");
			waitForPageLoad();
			webDriver.switchTo().defaultContent();
			fillCardExpiryDateWithPCIOn("06", "27");
			waitForPageLoad();
			frameToBeAvailableAndSwitchToIt(SEC_IFRAME_PCI_FORM);
			cardVerificationNumberPCI().clear();
			cardVerificationNumberPCI().sendKeys("123");
			waitForPageLoad();
			webDriver.switchTo().defaultContent();
			if (CHECKOUT_USE_CARD_CTA_FEATURE)
			{
				useThisCard().click();
			}
			waitForPageLoad();
			if(CHECKOUT_NEW_DESIGN_FEATURE)
			{
				if (getWebDriver().findElement(CONFIRM_SECURITY_CODE_LBL_NEW_DESIGN).isDisplayed())
				{
					getWebDriver().findElement(cardVerificationNumber).click();
					getWebDriver().findElement(cardVerificationNumber).sendKeys("123");
				}
			}
			else
			{
				if (getWebDriver().findElement(CONFIRM_SECURITY_CODE_LBL_OLD_DESIGN).isDisplayed())
				{
					getWebDriver().findElement(cardVerificationNumber).click();
					getWebDriver().findElement(cardVerificationNumber).sendKeys("123");
				}
			}
		}
		catch (Exception e)
		{
			log.info("Not able to fill card details");
		}
	}

	public void fillCVVField()
	{
		if (isPCIFeatureFlagDisabled())
		{
			pause(2000);
			cardVerificationNumber().sendKeys("123");
		}
	}

	public void EnterCVVNumber()
	{
		if (waitForAndGetElement(cardVerificationNumber, DEFAULT_TIMEOUT) != null)
		{
			pause(2000);
			cardVerificationNumber().sendKeys("123");
		}
	}

	public void fillCardDetails(String no)
	{
//		if (isPCIFeatureFlagDisabled())
//		{
//			waitForExpectedElement(cardNumber, 20);
//			scrollElementIntoView(cardNumber);
//			selectCard();
//			cardNumber().sendKeys(no);
//			cardExpiryDate();
//			cardExpiryYear();
//			scrollElementIntoView(submitCardDetails);
//			submitCardDetails().click();
//		}
//		else
//		{
		fillCardDetails("VISA", no);
		//}
	}

	public void fillCardExpiryDateWithPCIOn(final String monthString, final String yearString)
	{
		inputExpiryDate(monthString, EXPIRY_MONTH);
		inputExpiryDate(yearString, EXPIRY_YEAR);
	}

	public String getExpiryDateErrorMessage()
	{
		return getWebDriver().findElement(EXPIRY_ERROR).getAttribute("data-error-message");
	}

	public void fillCardNumberWithPCIOn(final String cardNumber)
	{
		waitForPageLoad();
		frameToBeAvailableAndSwitchToIt(NUM_IFRAME_PCI_FORM);
		pause(3000);
		cardNumberPCI().sendKeys(cardNumber);
		webDriver.switchTo().defaultContent();
	}

	public String getCardNumberErrorMessage()
	{
		webDriver.switchTo().defaultContent();
		return getWebDriver().findElement(CARD_NUMBER_ERROR).getAttribute("data-error-message");
	}

	public boolean checkExpiryDateInputResultIsNotEmpty()
	{
		final boolean monthPopulated = !checkExpiryFieldEmpty(EXPIRY_MONTH);
		final boolean yearPopulated = !checkExpiryFieldEmpty(EXPIRY_YEAR);

		return monthPopulated || yearPopulated;
	}

	public boolean checkExpiryFieldEmpty(final By element)
	{
		final String inputResult = getWebDriver().findElement(element).getText();
		return inputResult.isEmpty();
	}

	public void fillCardDetails(final String cardType,
	                            final String cardNumber,
	                            final String date,
	                            final String year,
	                            final String cvv)
	{
//		if (isPCIFeatureFlagDisabled())
//		{
//			selectCard(cardType);
//			cardNumber().sendKeys(cardNumber);
//			cardExpiryDate(date);
//			cardExpiryYear(year);
//			submitCardDetails().click();
//		}
//		else
		try
		{
			waitForPageLoad();
			frameToBeAvailableAndSwitchToIt(NUM_IFRAME_PCI_FORM);
			cardNumberPCI().sendKeys(cardNumber);
			webDriver.switchTo().defaultContent();
			waitForPageLoad();
			fillCardExpiryDateWithPCIOn("06", "27");
			waitForPageLoad();

			try
			{
				frameToBeAvailableAndSwitchToIt(SEC_IFRAME_PCI_FORM);
				cardVerificationNumberPCI().sendKeys(cvv);
				webDriver.switchTo().defaultContent();
				if (CHECKOUT_USE_CARD_CTA_FEATURE)
				{
					useThisCard().click();
				}
				waitForPageLoad();
			}
			catch (Exception e)
			{
				log.info("CVV no field not displayed");
			}

		}
		catch (Exception e)
		{
			log.info("Not able to fill card details");
		}
		try
		{
			waitForPresenceOfElement(deliveryButton, 2);
			deliveryButton().click();
		}
		catch (final Exception e)
		{
			log.info("No delivery button");
		}
		if (isElementPresent((CREDIT_CARD_OVER_AGE_18), 5))
		{
			if (getWebDriver().findElement(CREDIT_CARD_OVER_AGE_18).isDisplayed())
			{
				clickByJavaScriptExecutor(webDriver.findElements(CREDIT_CARD_OVER_AGE_18).get(0));
			}
		}
		if (isPCIFeatureFlagDisabled())
		{
			try
			{
				cardVerificationNumber().sendKeys(cvv);
			}
			catch (Exception e)
			{
				log.info("CVV no field not displayed");
			}
		}
	}

	public void selectQuantityOfLineItem(final String quantity, int lineItem)
	{
		pause(5000);
		if (IS_MOBILE)
		{
			MyBagPage myBagPage = new MyBagPage();
			myBagPage.clickEditForLineItemMobile(lineItem);
			waitForAndGetElement(SELECT_QUANTITY_MOBILE, DEFAULT_TIMEOUT);
			new Select(getWebDriver().findElements(SELECT_QUANTITY_MOBILE).get(lineItem - 1)).selectByVisibleText(quantity);
		}
		else
		{
			new Select(getWebDriver().findElements(By.cssSelector("select[data-ng-model='entry.quantity']")).get(lineItem - 1)).selectByVisibleText(
					quantity.replaceAll("[^\\d.]", ""));
		}
	}

	public String getQuantityOfLineItem(int lineItem)
	{
		pause(2000);
		if (IS_MOBILE)
		{
			return getWebDriver().findElements(By.xpath("//*[@class='bag-item__details list list--unordered']/li[3]")).get(lineItem - 1).getText();
		}
		else
		{
			return new Select(getWebDriver().findElements(By.cssSelector("select[data-ng-model='entry.quantity']")).get(lineItem - 1)).getFirstSelectedOption().getText();
		}
	}

	public void selectSize(final String size)
	{
		selectSizeFromFullCartPage();
		final List<WebElement> options = new Select(getWebDriver().findElement(By.cssSelector(
				"select[data-ng-model='entry.selectedSize']"))).getOptions();
		options.stream()
				.filter(a -> a.getText().contains(size))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Size not found: " + size))
				.click();
	}

	public String selectSizeOfTheProduct()
	{
		String selectedSize = "";
		if (elementPresence(oneSize))
		{
			selectedSize = oneSize().getText();
		}
		if (elementPresent(selectSize))
		{
			final Select select = new Select(getWebDriver().findElement(selectSize));
			select.selectByIndex(1);
		}
		return selectedSize;
	}

	public void selectSizeOfTheProductPrecisely(final String size)
	{
		selectSize();
		getWebDriver().findElement(By.cssSelector("select >option[label*='" + size + "']")).click();
	}

	public void selectSizeOfTheProduct(final int index)
	{
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") || BROWSER.contains("emulator"))
		{
			pause(1200);
		}
		clickByJavaScriptExecutor(selectSize());
		new Select(selectSize()).selectByIndex(index);
		clickByJavaScriptExecutor(selectSize());
	}

	public String selectSizeOfTheProduct(final String size)
	{
		selectSize().click();
		final List<WebElement> optionsInnerText = webDriver.findElements(By.cssSelector(
				"select[data-ng-model='productInfo.selectedSize'] >option"));

		for (final WebElement text : optionsInnerText)
		{
			if (text.getText().contains(size))
			{
				text.click();
			}
		}
		return selectSize().getText();
	}

	public boolean sizeDropDownPresent()
	{
		return isElementPresent(By.cssSelector("select[data-ng-model='productInfo.selectedSize']"));
	}

	public boolean continueToPaymentButtonPresent()
	{
		return isElementPresent(continueToPaymentButton);
	}

	public boolean searchPageStillAvailable()
	{
		return isElementPresent(By.cssSelector(".plp-results"));
	}

	public boolean reviewCheckoutStillAvailable()
	{
		return isElementPresent(By.xpath("//a[contains(text(),'REVIEW & CHECKOUT')]"));
	}

	public void switchToCheckoutIframe()
	{
		checkoutIframe();
		getWebDriver().switchTo()
				.frame(getWebDriver().findElement(By.cssSelector("iframe[class='side-panel__iframe']")));
	}

	public boolean changeColorLinkDisplayed()
	{
		return isElementPresent(By.xpath("//a[contains(text(),'Change colour')]"));
	}

	public void switchToPaypalWindow()
	{
		this.parentWindow = getWebDriver().getWindowHandle();
		scrollToBottom();
		paypalCheckoutButton().click();
		waitForElementInvisibility(By.id("preloaderSpinner"));

		if ((getWebDriver().getWindowHandles().size() > 1))
		{
			this.childWindow = (String) getWebDriver().getWindowHandles().toArray()[1];

			try
			{
				waitForAndGetElement(PAYPAL_CONTINUE_BUTTON, 5).click();
			}
			catch (final Exception e)
			{
				log.info("Paypal continue button does not exist" + e);
			}

			getWebDriver().switchTo().window(childWindow);
			//below line added by harsha as paypal window by default is now showing guest and it requires to click on login button
			clickOnPaypalLoginLink();
			enterPayPalCredentials("UK01.DPPTest@gmail.com", "DPPTest123");

		}
		else
		{
			final List<WebElement> framesList = getWebDriver().findElements(By.xpath("//iframe"));
			if (WebDriverHelper.DEVICE_NAME.contains("iPhone") || BROWSER.contains("emulator") || WebDriverHelper.DEVICE_NAME.contains("Samsung"))
			{
				enterPayPalCredentials("UK01.DPPTest@gmail.com", "DPPTest123");
			}
			else
			{
				getWebDriver().switchTo().frame(framesList.get(1));
				getWebDriver().switchTo().frame(getWebDriver().findElement(By.xpath("//iframe[@frameborder='0']")));
			}
		}

	}

	private void clickOnPaypalLoginLink()
	{
		if (isElementVisible(payRegisteruserSigninLink, 2))
		{
			clickByJavaScriptExecutor(waitForExpectedElement(payRegisteruserSigninLink));
		}
	}

	public void clickPaynowPaypalButton()
	{
		if ((getWebDriver().getWindowHandles().size() > 1))
		{
			getWebDriver().switchTo().window(this.childWindow);
		}
		waitForElementInvisibility(By.id("spinner"));
		pause(3000);
		scrollToBottom();
		waitForElementPresence(PAYPAL_CONTINUE_BUTTON);
		pause(10000);
		//waitForAndGetElement(PAYPAL_CONTINUE_BUTTON, 20).click();
		pause(200);
		clickByJavaScriptExecutor(paypalButtonPayNow());
		pause(2000);
		getWebDriver().switchTo().window(this.parentWindow);
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") || BROWSER.contains("emulator"))
		{
			pause(15000);
		}
	}

	public boolean alertMessageIsDisplayed()
	{
		return isElementPresent(By.cssSelector(".alert__list.alert__list--checkout"));
	}

	public int returnTotalNumberOfSizes()
	{
		selectQuantity().click();

		return getWebDriver().findElements(By.cssSelector("select[data-ng-model='entry.quantity'] >option"))
				.size();
	}

	public int returnTotalNumberOfSizesOnMobile()
	{
		return getWebDriver().findElements(SELECT_QUANTITY_MOBILE).size() + 1;
	}

	public String returnAttributesOfUnavailableSize()
	{
		quantitiesInTheDropDown();
		return getWebDriver().findElement(By.cssSelector("li[id*='ui-select-choices-row']"))
				.getAttribute("class");
	}

	public double getTheProductPriceFromQuickcart()
	{
		final String[] splitDeliveryTotal = priceSummary().getText().split("€");

		return Math.round(Double.parseDouble(splitDeliveryTotal[1]));
	}

	public void changeDeliveryCountryForCollection(final String country) throws InterruptedException
	{
		checkoutCountryPicker().click();
		countryInputTextField().sendKeys(country);
		clickByJavaScriptExecutor(waitForExpectedElement(TOTAL_INCLUDING_DELIVERY));
		Thread.sleep(2000);
		webDriver.findElement(applyChangeButton).click();
	}

	public double roundToNearestHalf(final double number)
	{
		final double diff = number - Math.floor(number);

		if (0.25 <= diff && 0.75 > diff)
		{
			return Math.floor(number) + 0.5;
		}
		else
		{
			return Math.round(number);
		}
	}

	public void enterPayPalCredentials(final String username, final String password)
	{
		paypalEmailid().clear();
		paypalEmailid().sendKeys(username);
		paypalPassword().sendKeys(password);
		paypalLoginButton().click();
		waitForElementInvisibility(By.id("spinner-message"));
	}

	public void collectionDetails()
	{
		try
		{
			pause(3000);
			scrollIntoView(collectionDetailsFirstName());

			selectTitleForCollection();
			collectionDetailsFirstName().sendKeys("neo");
			collectionDetailsLastName().sendKeys("worker");

			//Add below 2 lines of code only if Mandatory mobile FF is True otherwise comment them
			phoneNumberFieldInCheckout().clear();
			phoneNumberFieldInCheckout().sendKeys("7894938774");

			clickByJavaScriptExecutor(collectionDetailsConfirmButton());
		}
		catch (Exception e)
		{
			log.info("Collection Details section not visible");
		}
	}

	public boolean collectionDetailsFormDisplayed()
	{
		pause(3000);
		try
		{
			scrollIntoView(collectionDetailsFirstName());
			return collectionDetailsFirstName().isDisplayed() && collectionDetailsLastName().isDisplayed() &&
					phoneNumberFieldInCheckout().isDisplayed() && phoneNumberFieldInCheckout().getText().equalsIgnoreCase("");
		}
		catch (Exception e)
		{
			log.info("Could not find collection details form");
			return false;
		}
	}

	public void collectionDetailsRestOfWorld()
	{
		selectTitleForCollectionRestOfWorld();
		collectionDetailsFirstName().sendKeys("neo");
		collectionDetailsLastName().sendKeys("worker");
		scrollToBottom();
		clickByJavaScriptExecutor(collectionDetailsConfirmButton());
	}

	public void updateCollectionDetails(final String firstName, final String lastName)
	{
		pause(1200);
		clickByJavaScriptExecutor(waitForAndGetElement(CHANGE_DETAILS, DEFAULT_TIMEOUT));
		scrollIntoView(collectionDetailsFirstName());
		collectionDetailsFirstName().clear();
		collectionDetailsFirstName().sendKeys(firstName);
		collectionDetailsLastName().clear();
		collectionDetailsLastName().sendKeys(lastName);
		collectionDetailsConfirmButton().click();
	}

	public void gotToBillingAddressSection()
	{
		pause(4000);
		scrollIntoView(billingAddressSection());
	}

	public void waitForElementInvisibility(final By element)
	{
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	private void scrollIntoView(final WebElement webElement)
	{
		try
		{
			((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
		}
		catch (Exception e)
		{
			log.info("Could not scroll into section");
		}
	}

	public WebElement secureCheckout()
	{
		return waitForExpectedElement(secureCheckout);
	}

	public void continueToPaymentSection()
	{
		// Thread.sleep(2000);
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad") ||
				BROWSER.contains("emulator"))
		{
			pause(500);
		}
		if (!WebDriverHelper.DEVICE_NAME.contains("iPhone") || WebDriverHelper.DEVICE_NAME.contains("Samsung") || !BROWSER.contains("emulator"))
		{
			try
			{
				scrollElementIntoView(checkoutSection);
			}
			catch (final Exception e)
			{
				log.info("Could not scroll into checkout section");
			}
		}
		try
		{
			log.info("looking for continue to payment button");
			clickByJavaScriptExecutor(continueToPaymentButton());
		}
		catch (final Exception e)
		{
			log.info("No continue to payment button");
		}
	}

	public boolean isProductNamePresent(final String productName)
	{
		return isElementPresent(By.xpath("//a[contains(text(),\"" + productName + "\")]"));
	}

	public void selectAvailableDelivery()
	{
		try
		{
			waitForElementPresence(RADIO_BUTTONS_DELIVERY, 5);
			getWebDriver().findElement(RADIO_BUTTONS_DELIVERY).click();
			pause(3000);
			waitForElementPresence(DELIVERY_MODES, 5);
			getWebDriver().findElement(ACTIVE_DEL_OPTIONS).click();
			pause(3000);
		}
		catch (final Throwable ignored)
		{
		}
	}

	public void selectDifferentDeliveryCountry(final String deliveryCountry) throws InterruptedException
	{
		countryor().click();
		homePage.enterCountryText().click();
		homePage.enterCountryText().sendKeys(deliveryCountry);
		final List<WebElement> countries = getWebDriver().findElements(By.cssSelector(
				".ui-select-choices-row-inner.nl-select__row-inner"));
		for (final WebElement country : countries)
		{
			if (country.getText().contains(deliveryCountry))
			{
				Thread.sleep(2000);
				final WebElement selectCountry = getWebDriver().findElement(By.cssSelector(".ui-select-highlight"));
				selectCountry.click();
				break;
			}
		}
	}

	public void selectFirstAvailableSizeOfTheProduct()
	{
		try
		{
			if (WebDriverHelper.DEVICE_NAME.contains("iPhone") || BROWSER.contains("emulator"))
			{
				pause(1500);
			}

			final Select select = new Select(waitForElementPresence(selectSize2));
			pause(1000);
			select.selectByIndex(1);
		}
		catch (final Exception e)
		{
			log.info("could not select product size" + e);
		}
	}

	public void selectSpecificSizeOfTheProduct(final String size)
	{
		if (!isElementPresent(oneSize2))
		{
			final Select select = new Select(getWebDriver().findElement(selectSize2));
			select.selectByVisibleText(size);
		}

	}

	public void clickManualAddr()
	{
		if (elementPresence(ADDRESS_LOOKUP))
		{
			scrollForFocusAndClick(deliveryManually2, 5);
		}
	}

	public void clickCollectionStoreSub()
	{
		try
		{
			waitForElementPresence(COLLECTION_RADIO);
			scrollForFocusAndClick(COLLECTION_RADIO, 5);
		}
		catch (final Exception e)
		{
			log.info("dont have radio for collection sub category");
		}
	}

	public void goToCheckoutPage()
	{
		searchField().sendKeys(Props.getProp("productCode_stock"));

		scrollForFocusAndClick(searchButton, 10);
		waitForAndGetElement(products, 10);
		pause(3000);
		scrollForFocusAndClick(products, 2);
		selectFirstAvailableSizeOfTheProduct();
		addToCartButton().click();
		pause(3000);
		basketIcon().click();
		selectQuantityOfLineItem("Qty: 5", 1);
		secureCheckout().click();
	}

	public void checkoutProduct(final String productCode) throws InterruptedException
	{
		searchField().sendKeys(productCode);
		searchButton().click();
		waitForAndGetElement(products, 10);
		scrollForFocusAndClick(products, 2);
		selectFirstAvailableSizeOfTheProduct();
		selectFirstAvailableSizeOfTheProduct();
		String count = totalItemsInTheBasket().getText();
		int bagCount = count.equals("") ? 0 : Integer.parseInt(count);
		addToCartButton().click();
		pause(4000);
		while (Integer.parseInt(totalItemsInTheBasket().getText()) != bagCount + 1)
		{
			pause(2000);
		}
		basketIcon().click();
		//selectQuantityOfLineItem("Qty: 5", 1);
		waitForPageLoad();
		Thread.sleep(2000);
		clickCheckoutButton();
		collectionButton().click();
		try
		{
			postcodeOrCountryField().sendKeys("london");
			collectionSubmitButton().click();

			selectTheFirstCollectionOptionFromList();
			collectionDetails();
		}
		catch (final Exception ignored)
		{
			//donothing
		}
		enterMandatoryMobileField();
	}

	public void enterMandatoryMobileField()
	{
		pause(2500);
		waitForAndGetElement(TELEPHONE_NUMBER_MANDATORY, DEFAULT_TIMEOUT);
		if (DELIVERY_UPDATE_ENABLED_FEATURE)
		{
			try
			{
				if (webDriver.findElement(TELEPHONE_NUMBER_MANDATORY).isDisplayed())
				{
					final String phoneNumber = "075" + RandomGenerator.random(8, PermittedCharacters.NUMERIC);
					waitForAndGetElement(TELEPHONE_NUMBER_MANDATORY, DEFAULT_TIMEOUT)
							.sendKeys(phoneNumber);
					log.info("Phone number entered: " + phoneNumber);
					pause(3000);
					clickByJavaScriptExecutor(continueToPaymentButton());
					pause(1200);
				}
			}
			catch (final Exception e)
			{
				log.info("Telephone mandatory field is not displayed");
			}
		}
	}

	public void enterDeliveryMandatoryMobileField()
	{
		pause(2500);
		waitForAndGetElement(DELIVERY_TELEPHONE_NUMBER_MANDATORY, DEFAULT_TIMEOUT);
		if (DELIVERY_UPDATE_ENABLED_FEATURE)
		{
			try
			{
				if (webDriver.findElement(DELIVERY_TELEPHONE_NUMBER_MANDATORY).isDisplayed())
				{
					final String phoneNumber = "075" + RandomGenerator.random(8, PermittedCharacters.NUMERIC);
					waitForAndGetElement(DELIVERY_TELEPHONE_NUMBER_MANDATORY, DEFAULT_TIMEOUT)
							.sendKeys(phoneNumber);
					log.info("Phone number entered: " + phoneNumber);
					pause(3000);
					clickByJavaScriptExecutor(continueToDeliveryOptionButton());
					pause(1200);
				}
			}
			catch (final Exception e)
			{
				log.info("Telephone mandatory field is not displayed");
			}
		}
	}

	public void selectTheFirstCollectionOptionFromList()
	{
		if (PEAK_CAPPING_FEATURE)
		{
			waitForElementVisible(PEAK_CAPPING_STORE_SEARCH_RESULTS, DEFAULT_TIMEOUT);
			if (!webDriver.findElements(PEAK_CAPPING_STORE_SEARCH_RESULTS).isEmpty())
			{
				scrollToObject(webDriver.findElements(PEAK_CAPPING_STORE_SEARCH_RESULTS).get(0));
			}
		}
		else
		{
			waitForElementVisible(STORE_SEARCH_RESULTS, DEFAULT_TIMEOUT);
			if (!webDriver.findElements(STORE_SEARCH_RESULTS).isEmpty())
			{
				scrollToObject(webDriver.findElements(STORE_SEARCH_RESULTS).get(0));
			}
		}
		clickWhenClickable(webDriver.findElements(By.cssSelector("label"))
						.stream()
						.filter(a -> a.getText().contains("Click & Collect Standard"))
						.findFirst()
						.orElseThrow(() -> new NotFoundException("Click and collect standard not found")),
				10);
		log.info("Click the use this store button");
		clickByJavaScriptExecutor(webDriver.findElements(SELECT_THE_STORE_BUTTON).get(0));
	}

	public List<WebElement> getBagItems()
	{
		waitForAndGetElement(BAG_ITEMS, DEFAULT_TIMEOUT);
		return new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(BAG_ITEMS));
	}

	public String getBagItemSize(final WebElement item)
	{
		String size;
		try
		{
			size = item.findElement(By.xpath("./../../..")).findElement(BAG_ITEM_SIZE_SELECTED).getText();
		}
		catch (final Exception e)
		{
			size = item.findElement(By.xpath("./../../..")).findElement(BAG_ITEM_SIZE_SELECTED).getText();
		}
		return size;
	}

	public String getBagItemSingleSize(final WebElement item)
	{
		String size;
		try
		{
			size = item.findElement(By.xpath("./../../..")).findElement(BAG_ITEM_SIZE_SINGLE).getText();
		}
		catch (final Exception e)
		{
			size = item.findElement(By.xpath("./../../..")).findElement(BAG_ITEM_SIZE_SINGLE).getText();
		}
		return size;
	}

	public void changeCountryFromBag() throws InterruptedException
	{
		waitForExpectedElement(changeDeliveryCountryLink);
		scrollElementIntoView(changeDeliveryCountryLink);
		clickByJavaScriptExecutor(changeDeliveryCountryLink());
		if (IS_MOBILE)
		{
			scrollIntoView(By.className("bag-footer__options"));
			selectFromDropDownWithVisibleText(getWebDriver().findElement(changeCountryMobile), "Italy");
			waitForPageLoad();
			clickByJavaScriptExecutor(getElementWithText(By.cssSelector("button"), "APPLY CHANGE"));
		}
		else
		{
			changeCountry().click();
			scrollIntoView(countryInputBagPage);
			webDriver.findElement(countryInputBagPage).sendKeys("italy");
			new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
					countryInputSelection)).get(0).click();
			getElementWithText(By.cssSelector("button"), "APPLY CHANGE").click();
		}
	}

	public void changeCountryFromBag(final String country) throws InterruptedException
	{
		changeDeliveryCountryLink().click();
		changeCountry().click();
		webDriver.findElement(countryInputBagPage).sendKeys(country);
		new WebDriverWait(webDriver, 20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(countryInputSelection))
				.get(0)
				.click();
		webDriver.findElement(applyChangeCountry).click();
	}

	public void searchForAProductWithProductCode(final CheckoutModel checkoutModel)
	{
		waitForElementVisible(MAST_HEADER_SEARCH_FIELD, DEFAULT_TIMEOUT);
		searchField().sendKeys(Props.getProp("productCode"));
		checkoutModel.setProductCode(Props.getProp("productCode"));
		searchButton().click();
		waitForPageLoad();
	}

	public void loginAsExistingUser()
	{
		waitForExpectedElement(trackMyOrderEmail).sendKeys(Props.getProp("emailAddress"));
		waitForExpectedElement(trackMyOrderPassword).sendKeys(Props.getProp("password"));
		waitForExpectedElement(trackOrder).click();
	}

	public String getDeliveryPassAppliedText()
	{
		if (IS_MOBILE)
		{
			return waitForAndGetElement(DELIVER_PASS_APPLIED_FIELD_MOBILE, DEFAULT_TIMEOUT).getText();
		}
		else
		{
			return waitForAndGetElement(DELIVERY_PASS_APPLIED_FIELD, DEFAULT_TIMEOUT).getText();
		}
	}

	public void enterCardDetailsAndMakePlaceOrder(final String payment, final String cardType, final String cardNumber)
	{
		continueToPaymentSection();
		pause(2000);
		switch (payment)
		{
			case "card":
				clickDebitOrCreditCardTab();
				fillBillingAddressForm();
				fillCardDetails(cardType, cardNumber);
				clickByJavaScriptExecutor(placeOrderButton());
				break;
			case "store card":
				storeCard().click();
				fillBillingAddressFormStoreCard();
				waitForPageLoad();
				enterStoreOrStaffCardDetails(Props.getProp("storeCardNumber").toString(), "08", "23");
				clickOver18CheckBox(0);
				placeOrderButton().click();
				break;
			case "staff card":
				storeCard().click();
				fillBillingAddressFormStoreCard();
				enterStoreOrStaffCardDetails(Props.getProp("staffCard").toString(), "08", "23");
				placeOrderButton().click();
				break;
			case "gift card 1":
				giftCardSection.scrollToGiftCardSection();
				clickByJavaScriptExecutor(giftCardSection.openGiftCardSection());
				giftCardSection.enterGiftCard(CardProperties.giftCardNumber(), CardProperties.giftCardPin()); //6335863608087205 1890 6335863604597413 1739
				clickByJavaScriptExecutor(giftCardSection.applyGiftCard());
				giftCardSection.scrollToBottom();
				clickOver18CheckBoxGiftCard(0);
				placeOrderButton().click();
				break;
			case "gift card 2":
				giftCardSection.scrollToGiftCardSection();
				clickByJavaScriptExecutor(giftCardSection.openGiftCardSection());
				giftCardSection.enterGiftCard("6335863608087205", "1890"); //6335863608087205 1890 6335863604597413 1739
				clickByJavaScriptExecutor(giftCardSection.applyGiftCard());
				giftCardSection.scrollToBottom();
				clickOver18CheckBoxGiftCard(0);
				placeOrderButton().click();
				break;
			case "credit card and gift card":
				giftCardSection.scrollToGiftCardSection();
				clickByJavaScriptExecutor(giftCardSection.openGiftCardSection());
				giftCardSection.enterGiftCard("6335863606579302", "7652");
				clickByJavaScriptExecutor(giftCardSection.giftCardBalance());
				pause(1000);
				clickByJavaScriptExecutor(giftCardSection.applyGiftCard());
				clickDebitOrCreditCardTab();
				fillBillingAddressForm();
				fillCardDetails(cardType, cardNumber);
				placeOrderButton().click();
				break;
			case "gift card and store card":
				giftCardSection.scrollToGiftCardSection();
				clickByJavaScriptExecutor(giftCardSection.openGiftCardSection());
				giftCardSection.enterGiftCard("6335863940235660", "7826");
				clickByJavaScriptExecutor(giftCardSection.giftCardBalance());
				clickByJavaScriptExecutor(giftCardSection.applyGiftCard());
				scrollIntoView(storeCard());
				clickByJavaScriptExecutor(storeCard());
				fillBillingAddressFormStoreCard();
				enterStoreOrStaffCardDetails(Props.getProp("storeCardNumber").toString(), "08", "23");
				placeOrderButton().click();
				break;
			case "gift card and paypal":
				giftCardSection.scrollToGiftCardSection();
				clickByJavaScriptExecutor(giftCardSection.openGiftCardSection());
				giftCardSection.enterGiftCard("6335863603484704", "8035");
				clickByJavaScriptExecutor(giftCardSection.giftCardBalance());
				clickByJavaScriptExecutor(giftCardSection.applyGiftCard());
				paypalButton().click();
				switchToPaypalWindow();
				clickPaynowPaypalButton();
				break;
			case "pay pal":
				pause(3000);
				paypalButton().click();
				switchToPaypalWindow();
				clickPaynowPaypalButton();
				break;
			case "virtual card":
				waitForPageLoad();
				waitForAndGetElement(KLARNA_BUTTON, DEFAULT_TIMEOUT).click();
				refresh();
				waitForPageLoad();
				waitForAndGetElement(KLARNA_BUTTON, DEFAULT_TIMEOUT).click();
				pause(3000);
				waitForPageLoad();
				try
				{
					if (isElementVisible(By.id("klarna-pay-later-main"), 3))
					{
						getWebDriver().switchTo().frame("klarna-pay-later-main");
					}
				}
				catch (final NoSuchFrameException e)
				{
					log.info("Klarna widget is not displayed!");
					throw new NoSuchElementException();
				}
				getWebDriver().switchTo().defaultContent();
				clickOver18CheckBoxKlarnaCard(0);
				try
				{
					clickByJavaScriptExecutor(getKlarnaPayLaterRadioButton());
				}
				catch (Exception e)
				{
					log.info("Klarna Pay Later tab not available");
				}
				pause(4000);
				try
				{
					placeOrderButton_Klarna().click();
				}
				catch (Exception e)
				{
					Assert.fail("Not able to click on place order button");
				}
				pause(2000);
				// Handle the verify account popup if it appears
				if (isKlarnaVerifyPopupVisible())
				{
					handleKlarnaVerification();
				}
				break;
			case "virtual card and gift card":
				giftCardSection.scrollToGiftCardSection();
				clickByJavaScriptExecutor(giftCardSection.openGiftCardSection());
				giftCardSection.enterGiftCard("6335863609402429", "8435");
				clickByJavaScriptExecutor(giftCardSection.giftCardBalance());
				waitForPageLoad();
				clickByJavaScriptExecutor(giftCardSection.applyGiftCard());
				waitForPageLoad();
				pause(3000);
				waitForAndGetElement(KLARNA_BUTTON, DEFAULT_TIMEOUT).click();
				clickOver18CheckBoxKlarnaCard(0);
				waitForPageLoad();
				pause(3000);
				try
				{
					clickByJavaScriptExecutor(getKlarnaPayLaterRadioButton());
				}
				catch (Exception e)
				{
					log.info("Klarna Pay Later tab not available");
				}
				pause(3000);
				placeOrderButton_Klarna().click();
				if (isKlarnaVerifyPopupVisible())
				{
					handleKlarnaVerification();
				}
				pause(2000);
				waitForPageLoad();
				break;
			case "virtual card and gift card 1":
				giftCardSection.scrollToGiftCardSection();
				clickByJavaScriptExecutor(giftCardSection.openGiftCardSection());
				giftCardSection.enterGiftCard("6335863609637198","0555");
				clickByJavaScriptExecutor(giftCardSection.giftCardBalance());
				waitForPageLoad();
				clickByJavaScriptExecutor(giftCardSection.applyGiftCard());
				waitForPageLoad();
				pause(3000);
				waitForAndGetElement(KLARNA_BUTTON, DEFAULT_TIMEOUT).click();
				waitForPageLoad();
				pause(3000);
				clickOver18CheckBoxKlarnaCard(0);
				try
				{
					clickByJavaScriptExecutor(getKlarnaPayLaterRadioButton());
				}
				catch (Exception e)
				{
					log.info("Klarna Pay Later tab not available");
				}
				pause(3000);
				placeOrderButton_Klarna().click();
				if (isKlarnaVerifyPopupVisible())
				{
					handleKlarnaVerification();
				}
				pause(2000);
				waitForPageLoad();
				break;
			case "virtual card and gift card 2":
				giftCardSection.scrollToGiftCardSection();
				clickByJavaScriptExecutor(giftCardSection.openGiftCardSection());
				giftCardSection.enterGiftCard("6335863603536255", "5863"); //6335863603536255 5863
				clickByJavaScriptExecutor(giftCardSection.giftCardBalance());
				waitForPageLoad();
				clickByJavaScriptExecutor(giftCardSection.applyGiftCard());
				waitForPageLoad();
				pause(3000);
				waitForAndGetElement(KLARNA_BUTTON, DEFAULT_TIMEOUT).click();
				waitForPageLoad();
				pause(3000);
				clickOver18CheckBoxKlarnaCard(0);
				try
				{
					clickByJavaScriptExecutor(getKlarnaPayLaterRadioButton());
				}
				catch (Exception e)
				{
					log.info("Klarna Pay Later tab not available");
				}
				pause(3000);
				placeOrderButton_Klarna().click();
				if (isKlarnaVerifyPopupVisible())
				{
					handleKlarnaVerification();
				}
				pause(2000);
				waitForPageLoad();
				break;
		}
	}

	private void handleKlarnaVerification()
	{
		//webDriver.switchTo().frame(getKlarnaVerifyPopupIframe());
		pause(5000);
		clickByJavaScriptExecutor(getKlarnaVerifyTextCodeButton());

		pause(2000);
		// Sometimes we have to double confirm
		if (isElementVisible(KLARNA_VERIFY_CONFIRM_BUTTON))
		{
			clickByJavaScriptExecutor(getKlarnaVerifyTextCodeButton());
		}

		pause(2000);
		try
		{
			getKlarnaVerifyCodeField().sendKeys(RandomGenerator.randomInteger(6));
		}
		catch (Exception e)
		{
			log.info(e.getMessage());
		}

		pause(3000);
		if (isKlarnaEmailFieldPresent())
		{
			clickByJavaScriptExecutor(getKlarnaVerifyEmailButton());
			try
			{
				getKlarnaVerifyCodeField().sendKeys(RandomGenerator.randomInteger(6));
			}
			catch (Exception e)
			{
				log.info(e.getMessage());
			}
		}

		if (isKlarnaDateOfBirthFieldPresent())
		{
			getKlarnaDateOfBirthField().sendKeys(PaymentProperties.klarnaDateOfBirth());
			clickByJavaScriptExecutor(getKlarnaVerifyCompleteAccountButton());
			waitForElementInvisibility(KLARNA_DATE_OF_BIRTH_FIELD);
		}

		pause(2000);
		if (isElementVisible(KLARNA_IGNORE_MISMATCH_BUTTON))
		{
			clickByJavaScriptExecutor(getKlarnaIgnoreMismatchButton());
		}

		if (isElementVisible(KLARNA_CONTINUE_ANYWAY_BUTTON))
		{
			clickByJavaScriptExecutor(getKlarnaContinueAnywayButton());
		}

		if (isElementVisible(KLARNA_CONTINUE_NOT_NOW_OPTION))
		{
			clickByJavaScriptExecutor(getKlarnaNotNowButton());
		}

		if(isElementVisible(KLARNA_CONTINUE_PAY_OPTION))
		{
			clickByJavaScriptExecutor(getKlarnaContinuePayOptionButton());
		}

		if (isElementVisible(KLARNA_CONTINUE_PICK_PLAN_OPTION))
		{
			clickByJavaScriptExecutor(getKlarnaPickPlanOptionButton());
		}

		pause(2000);
		clickByJavaScriptExecutor(getKlarnaVerifyConfirmButton());
		webDriver.switchTo().window(originalWindowHandle);
		pause(2000);
	}

	private WebElement getKlarnaIgnoreMismatchButton()
	{
		return waitForExpectedElement(KLARNA_IGNORE_MISMATCH_BUTTON);
	}

	private WebElement getKlarnaNotNowButton()
	{
		return waitForExpectedElement(KLARNA_CONTINUE_NOT_NOW_OPTION);
	}

	private WebElement getKlarnaContinueAnywayButton()
	{
		return waitForExpectedElement(KLARNA_CONTINUE_ANYWAY_BUTTON);
	}

	private WebElement getKlarnaContinuePayOptionButton()
	{
		return waitForExpectedElement(KLARNA_CONTINUE_PAY_OPTION);
	}

	private WebElement getKlarnaPickPlanOptionButton()
	{
		return waitForExpectedElement(KLARNA_CONTINUE_PICK_PLAN_OPTION);
	}

	private WebElement getKlarnaPayLaterRadioButton()
	{
		return waitForElementPresence(KLARNA_PAY_LATER);
	}

	public WebElement getKlarnaDateOfBirthField()
	{
		return waitForElementPresence(KLARNA_DATE_OF_BIRTH_FIELD);
	}

	public boolean isKlarnaDateOfBirthFieldPresent()
	{
		return isElementPresent(KLARNA_DATE_OF_BIRTH_FIELD);
	}

	public boolean isKlarnaEmailFieldPresent()
	{
		return isElementPresent(KLARNA_EMAIL);
	}

	public WebElement getKlarnaVerifyConfirmButton()
	{
		return waitForExpectedElement(KLARNA_VERIFY_BUTTON);
	}

	public WebElement getKlarnaVerifyCodeField()
	{
		return waitForExpectedElement(KLARNA_VERIFY_CODE_FIELD);
	}

	public WebElement getKlarnaVerifyTextCodeButton()
	{
		return waitForExpectedElement(KLARNA_VERIFY_CONFIRM_BUTTON);
	}

	public WebElement getKlarnaVerifyCompleteAccountButton()
	{
		return waitForExpectedElement(KLARNA_VERIFY_CONFIRM_COMPLETE_ACCOUNT);
	}

	public WebElement getKlarnaVerifyEmailButton()
	{
		return waitForExpectedElement(KLARNA_VERIFY_CONFIRM_BUTTON);
	}

	public WebElement getKlarnaVerifyPopupIframe()
	{
		return waitForExpectedElement(KLARNA_VERIFY_POPUP_WINDOW);
	}

	public boolean isKlarnaVerifyPopupVisible()
	{
		originalWindowHandle = getWebDriver().getWindowHandle();
		Set<String> windows = getWebDriver().getWindowHandles();
		for (String s:windows)
		{
			if (s != originalWindowHandle)
			{
				getWebDriver().switchTo().window(s);
			}
		}
		return isElementVisible(KLARNA_VERIFY_POPUP_WINDOW);
	}

	public void switchToPopUp()
	{
		waitForPresenceOfElement(CARDINAL_ID, 10);
		getWebDriver().switchTo().frame(CARDINAL_IFRAME);
		waitForAndGetElement(AUTH_PASSWORD, 10).sendKeys("1234");
		clickByJavaScriptExecutor(waitForExpectedElement(AUTH_SUBMIT_BTN, 10));
	}

	public void switchToIframe()
	{
		try
		{
			waitForPresenceOfElement(PAYMENT_FORM, 1000);
			getWebDriver().switchTo().frame("runPayerAuthentication");
			waitForAndGetElement(AUTH_PASSWORD, 1000).sendKeys("1234");
			waitForAndGetElement(AUTH_SUBMIT_BTN, 10).click();
			webDriver.switchTo().defaultContent();
		}
		catch (final WebDriverException e)
		{
			log.info(e.getMessage());
		}
	}


	public void continueToAuthenticationWindowOTP()
	{
		waitForPresenceOfElement(CARDINAL_ID, 1000);
		getWebDriver().switchTo().frame(CARDINAL_IFRAME);
		if (isElementVisible(CODE_FIELD, 2))
		{
			waitForAndGetElement(CODE_FIELD, 10).sendKeys("1234");
		}
		else
		{
			waitForAndGetElement(By.cssSelector(".input-field"), 10).sendKeys("1234");
		}
		if (isElementVisible(SUBMIT_BUTTON_OTP, 2))
		{
			clickByJavaScriptExecutor(waitForAndGetElement(SUBMIT_BUTTON_OTP, 10));
		}
		else
		{
			clickByJavaScriptExecutor(waitForExpectedElement(By.cssSelector(".button.primary")));
		}
	}

	public double getTotalPaymentAmount()
	{
		return Double.parseDouble(waitForAndGetElement(By.cssSelector(
				".checkout__total-section .checkout__total-title--price.ng-binding"), DEFAULT_TIMEOUT).getText()
				.substring(1));
	}

	public void changeTheDeliveryCountryWithoutConfirmingMobile(final String deliveryCountry)
	{
		scrollByCoordinates(120);
		clickByJavaScriptExecutor(waitForExpectedElement(CHANGE_DELIVERY_COUNTRY, 30));
		scrollByCoordinates(120);
		selectFromDropDownWithVisibleText(getWebDriver().findElement(CHANGE_COUNTRY_FIELD_MOBILE), deliveryCountry);
	}

	public void changeTheDeliveryCountryMobile(final String deliveryCountry)
	{
		scrollByCoordinates(120);
		clickByJavaScriptExecutor(waitForExpectedElement(CHANGE_DELIVERY_COUNTRY, 30));
		scrollByCoordinates(120);
		selectFromDropDownWithVisibleText(getWebDriver().findElement(CHANGE_COUNTRY_FIELD_MOBILE), deliveryCountry);
		clickByJavaScriptExecutor(waitForExpectedElement(applyChangeButton));
	}

	public void searchAndCheckoutProduct(final String productCode) throws InterruptedException
	{
		searchField().sendKeys(productCode);
		searchButton().click();
		waitForAndGetElement(products, 10);
		scrollForFocusAndClick(products, 2);
		selectFirstAvailableSizeOfTheProduct();
		selectFirstAvailableSizeOfTheProduct();
		addToCartButton().click();
		basketIcon().click();
		selectQuantityOfLineItem("Qty: 5", 1);
		Thread.sleep(2000);
		clickCheckoutButton();
		collectionButton().click();
		clickOnDeliveryMethod();
		collectionButton().click();
	}

	public boolean collectionMethodDisabled()
	{
		pause(2000);
		return isElementPresent(COLLECTION_METHOD_DISABLED);
	}

	public boolean collectionMethodDisabledErrorText()
	{
		pause(2000);
		return isElementPresent(COLLECTION_METHOD_DISABLED_Text_Message);
	}

	public boolean getCollectionMethodDisabledErrorText(String collectionMethodDisabledErrorText)
	{
		pause(2000);
		return getWebDriver().findElement(COLLECTION_METHOD_DISABLED_Text_Message).getText().contentEquals(collectionMethodDisabledErrorText);
	}

	public boolean homeDeliveryMethodEnabled()
	{
		pause(2000);
		return isElementPresent(HOME_DELIVERY_METHOD_ENABLED);
	}

	public boolean homeDeliveryMethodSelectedDefault()
	{
		pause(2000);
		return isElementPresent(HOME_DELIVERY_METHOD_SELECTED);
	}
	public String getMessageForStoreOnlyProduct()
	{
		waitForPresenceOfElement(Messaging_Above_Delivery_Option,1000);
		return getWebDriver().findElement(Messaging_Above_Delivery_Option).getAttribute("innerText");
	}
	public String getMessageCollectionNotAvailable()
	{
			waitForPresenceOfElement(Collection_Not_Available_Message, 1000);
		return getWebDriver().findElement(Collection_Not_Available_Message).getAttribute("innerText");
	}

}
