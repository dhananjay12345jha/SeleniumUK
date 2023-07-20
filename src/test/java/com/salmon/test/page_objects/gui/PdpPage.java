package com.salmon.test.page_objects.gui;


import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.models.gui.CarouselModel;
import com.salmon.test.models.gui.CheckoutModel;
import com.salmon.test.page_objects.pojos.FavouriteItem;
import com.salmon.test.page_objects.pojos.PdpCarouselItem;
import com.salmon.test.step_definitions.gui.ShoppingItem;

import lombok.Data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.salmon.test.framework.helpers.WebDriverHelper.*;
import static org.junit.Assert.assertFalse;

/**
 * Created by phani.kaliginadi on 26/10/17.
 */

@Data
public class PdpPage extends PageObject
{
	private static final By BODY = By.cssSelector("body");
	private static final By FREE_DELIVERY_VAN_ICON = By.cssSelector(".html_component>img");
	private static final By FREE_STANDARD_DELIVERY_HEADER = By.cssSelector(".product-promo > div > div > strong");
	private static final By FREE_DELIVERY_RETURN_LABEL = By.cssSelector(".product-promo > div > span > a");
	private static final By CHOOSE_SIZE_OPTIONS = By.cssSelector(
			"select[data-ng-model='productInfo.selectedSize'] >option");
	private static final By SAVED_ITEMS_HEART_ICON = By.cssSelector("i.product-item__saveditem-icon.product-item__pdp-heart-icon--large");
	private static final By SAVED_ITEMS_HEART_ICON_CAROUSEL = By.cssSelector("i.product-item__saveditem-icon");

	private static final By CAROUSEL_ITEM = By.className("carousel-component__item");
	private static final By CAROUSEL_ITEM_ACTIVE = By.className("slick-active");
	private static final By CAROUSEL_ITEM_NAME = By.className("carousel-component-details__name");
	private static final By CAROUSEL_WRAPPER = By.className("carousel-component__wrapper");
	private static final By SAVED_ITEMS_URGENCY_WARNING = By.className("saveditems__urgency-message");
	private static final By DROPDOWN_OPTION = By.cssSelector("option");
	private Logger log = LogManager.getLogger(PdpPage.class.getName());
	private static final By SHOW_MORE_BUTTON = By.cssSelector(".iconf.iconf--Show-More");
	private final By productsSize = By.className("bag-item__col--size");
	private final By ADD_TO_SAVED = By.xpath("//button[contains(@data-ng-click,'toggleSavedItem')]");
	private final By breadCrumbTrail = By.xpath("//*[@class='breadcrumb__list list--unordered']");
	private final By productName = By.className("product-description__name");
	private final By productPrice = By.className("product-description__price");
	private final By productDescritption_Cms = By.cssSelector(".product-details--description.cms");
	private final By productDetails = By.className("product-details");
	private final By colorSwatchesOnQuickView = By.className("swatch__link");
	private final By productCode = By.className("product-details--product-code");
	private final By swatchOutOfStock = By.cssSelector("span[class*='swatch--out-of-stock']");
	private final By productImageOnPdp = By.xpath("(//img[@class='product-gallery__image'])[3]");
	private final By carouselPinholes = By.className("owl-dot");
	private final By closeModalWindow = By.className("close-modal-view");
	private final By productPreviousPrice = By.cssSelector("strike[class*='previous-price']");
	private final By selectSize = By.cssSelector(".product-sizes select");
	private final By addToCartButtonDisabled = By.cssSelector("button[class*='button--disabled']");
	private final By addedToBagText = By.xpath("//*[@data-translate='product.addedToBag.added']");
	private final By viewBagandCheckout = By.xpath("//a[contains(text(),'View Bag & Checkout')]");
	private final By ONE_SIZE_MESSAGE = By.className("product-sizes__one-size");
	private final By productItemCode = By.className("bag-item__prod-code");
	private final By outOfStockDisabled = By.cssSelector("option[disabled='']");
	private final By outOfStockSwatch = By.cssSelector("span[class*='swatch--out-of-stock']");
	private final By deliveryAndReturnsInformationLink = By.cssSelector("span[class='product-delivery-link']>a");
	private final By deliverySidePanel = By.xpath("//div[contains(@class,'side-panel--visible side-panel--left')]");
	private final By otherDeliveryOptions = By.cssSelector("p[class='order-summary__row']>a");
	private final By sizeChartLink = By.cssSelector("div[class*='product-sizes__size-chart ng-scope']");
	private final By productImageGallery = By.className("product-gallery__image");
	private final By productDetailsPage = By.className("product-details-page");
	private final By ACTIVE_COLOR = By.xpath("//span[contains(@class,'swatches__swatch--active')]");
	private final By ACTIVE_COLOR_LINK = By.xpath("//span[contains(@class,'swatches__swatch--active')]//a");
	private final By SIZE_SELECT = By.xpath("//div[@class='product-sizes__select-wrapper']//select");
	private final By SIZE_OPTIONS = By.xpath("//div[@class='product-sizes__select-wrapper']//select//option");
	private final By ONE_SIZE = By.xpath("//div[text()='One Size']");
	//private final By PRODUCT_NAME = By.xpath("//h2[contains(@class,'product-description__name')]");
	private final By PRODUCT_NAME = By.className("product-description__name");
	private final By ADD_TO_CART_BUTTON_ENABLED = By.xpath(
			"//button[@class='button button--primary button--full-width ng-scope']");
	private static final By STICKY_ADD_TO_BAG = By.id("stickySelectButton");
	private final By MSG_SAVED = By.xpath("//span[@data-ng-bind-html='savedItemsMessage.text']");
	private final By SIZE_VALIDATION_MESSAGE = By.cssSelector(
			".promo-message__text.product-details--warning-text.ng-scope");
	public By productImageOnPdpCurrent = By.cssSelector(".slick-active > .product-gallery__image.ng-scope");
	By productCodeNew = By.xpath("//section[@class='product-details']//div[@class='product-details--product-code']");
	By productCodeNewMobile = By.xpath(
			"//section[@class='product-details-wrapper product-details-wrapper--mobile']//div[@class='product-details--product-code']");
	By productNameNew = By.cssSelector(".product-heading>h1");
	By productPriceNew = By.cssSelector(".product-description__prices>span");
	By reviewSection = By.className("bv-content-list-container");
	private By SELECT_SIZE_DROP_DOWN = By.cssSelector(".product-ctas .select.select--white.select--pdp.ng-pristine");
	private By SELECT_SIZE_DROP_DOWN_OPTIONS_SELECTOR = By.cssSelector(
			".product-ctas .select.select--white.select--pdp.ng-pristine option");
	private static final By ADD_TO_BAG_BUTTON = By.className("button--primary__add");
	private By ADD_TO_BAG_PRIMARY_BUTTON_SELECTOR = By.cssSelector(
			".product-details-page.content .button.button--primary.button--full-width.ng-scope");
	private By PRODUCT_ZOOM_SELECTOR = By.cssSelector(".gallery-modal-view.ng-scope.active .modal-view-image");
	private By PRODUCT_ZOOM_CLOSE_BUTTON_SELECTOR = By.cssSelector(
			".gallery-modal-view.ng-scope.active .close-modal-view");
	//private By ADD_TO_SAVED_ITEMS_BUTTON = By.cssSelector(".button.button--secondary.button--full-width.ng-scope");
	private By VIEW_MY_BAG_AND_CHECKOUT_BUTTON = By.cssSelector(".button.button--primary.ng-scope[href*='/cart']");
	private By bagItems = By.className("bag-item__size-selector");
	private By itemsSize = By.cssSelector(".bag-item__size-selector>select>option");
	private By carouselProduct = By.className("product-item__name");
	private By allCarouselProduct = By.cssSelector(
			".slick-track>div[aria-hidden=false][aria-describedby*=slick-slide2]>a>img");
	//private By carouselProductName = By.cssSelector("div[aria-hidden=false][aria-describedby*=slick-slide2]>div>div>a>span>.product-item__name");
	private By carouselProductName = By.className("product-item__details");
	private By newProductCarouselProductName = By.className("product-item__name");
	private By slickerSection = By.className("slick-dots");
	private By carouselTitle = By.className("carousel-component__title");
	private By showMore = By.cssSelector(".iconf.iconf--Show-More");
	private By showLess = By.cssSelector(".pdp-showless>button");
	private By addToSavedItem = By.className("product-item__saveditem-icon");
	private By removeSavedItem = By.className("iconf--saved-items-icon-selected");
	private By headerSavedItemIcon = By.cssSelector(".header__message>span>a>i[class*=saved-items-icon]");
	private By sidePanelProductTitle = By.className("product-description__name");
	private By savedItemsCount = By.cssSelector("span[data-ng-bind=savedItemsCount]");
	private By carouselChevron = By.cssSelector(
			"div[class*=slick-nav-button][aria-disabled=false]>.iconf.iconf--chevron.slick-next");
	private By slickSlider = By.cssSelector("li[id*=slick-slide2][aria-hidden=true]");
	private By highlightBadge = By.cssSelector(".product-description__highlight>img");
	private By promotionLink = By.className("product-description__highlight--active-promo-link");
	private By markedDownPrice = By.cssSelector(".price.price--marked-down.product-description__price");
	private By previousPrice = By.cssSelector("strike.product-description__price--previous-price.ng-scope");
	private By swatchColour = By.className("swatch__link");
	private By sidePanelProductName = By.cssSelector(".product-description__name>a");
	private By slickDots = By.cssSelector("ul[role=tablist]>li[id*=slick-slide2]");
	private By THUMBNAIL_IMAGE = By.className("product-description-image__item");
	private By PRODUCT_DETAILS_HEADER = By.cssSelector("h2.product-details__heading");
	private By WRITE_A_REVIEW = By.cssSelector(".bv-write-review.bv-focusable.bv-submission-button");
	private By REVIEW_POPUP = By.id("bv-mbox-lightbox-list");
	private By REVIEW_SUMMARY_BODY = By.className("bv-content-summary-body");
	private By PRE_ORDER_NOTIFICATION_EMAIL_BOX = By.id("preorder-notification-email");
	private By FIND_IN_STORE = By.cssSelector(".button.button--secondary.button--full-width.ng-scope");
	private By SIDE_PANEL_IFRAME = By.className("side-panel__iframe");
	private By CHECK_AVAILABILITY_BUTTON = By.cssSelector(
			".button.button--secondary.button--secondary-black.button--full-width.ng-scope");
	private By PRODUCT_IN_IFRME = By.cssSelector(".store-stock-lookup__product-desc.ng-binding");
	private By PROD_SIZE = By.cssSelector("div[class='store-stock-lookup__select-wrapper']");
	private By IFRAME_COLOUR_SWATCH = By.cssSelector(".store-stock-lookup__swatches-wrapper.ng-scope");
	private By POSTCODE_TOWN_SEARCHBOX = By.id("locationTextbox");
	private By PRE_SELECTED_SIZE = By.cssSelector("option[selected='selected']");
	private By STORE_INFO_LINK = By.cssSelector("a[class='store-stock-lookup__results-store-info-link ng-scope']");
	private By STORE_LOOKUP_RESULTS = By.cssSelector(
			"a[class='store-stock-lookup__results-store-link ng-scope ng-binding']");
	private By PLUS_BUTTON = By.cssSelector(
			".accordion-item__indicator.store-stock-lookup__accordion-item-indicator.icon.icon--show");
	private By MAP_COMPONENT = By.cssSelector(
			"div[class='accordion-item store-stock-lookup__accordion-item ng-scope active']");
	private By STOCK_INFORMATION = By.cssSelector(".store-stock-lookup__result-item-status.ng-binding");
	private By DISCLAIMER_TEXT = By.cssSelector(".store-stock-lookup__stock-level-msg");
	private static final By ADD_TO_SAVED_ITEMS_BUTTON = By.cssSelector(
			"span.ng-isolate-scope > span > button:nth-child(1)");
	private static final By REMOVE_FROM_SAVED_ITEMS_BUTTON = By.cssSelector(
			".button.button--secondary.button--full-width.product-details__save-cta");
	private static final By SELECTED_SELECT_SIZE_DROP_DOWN = By.cssSelector(
			".select.select--white.select--pdp.ng-valid.ng-dirty.ng-touched.ng-empty");
	private final By QUICK_VIEW_LINK = By.linkText("QUICK VIEW");
	JavascriptExecutor executor = (JavascriptExecutor) webDriver;
	WebElement element;
	private CheckOutPage checkOutPage = new CheckOutPage();
	private static final By SIDE_PANEL_SIZE = By.cssSelector(".select.select--white.select--pdp.ng-valid");
	private static final By FIXED_PRICE_TEXT = By.cssSelector("a.promotion__highlight.ng-scope > span.ng-binding");
	private static final By SHOP_TO_LOOK_BUTTON = By.cssSelector(".simple-cta.shop-the-look");
	private static final By CAROUSEL_IMAGE = By.cssSelector(".carousel-component__img");
	private final By ITEM_ADDED_TO_BAG = By.cssSelector(".product-details__added-to-bag.ng-scope>p");
	private final By ADD_TO_SAVED_ITEMS_MESSAGE = By.cssSelector(".product-item__saveditem-tooltip--add");
	private final By REMOVE_FROM_SAVED_ITEMS_MESSAGE = By.cssSelector(".product-item__saveditem-tooltip--remove");
	private final By PDP_REVIEW_CONTAINER = By.cssSelector(".bv-prs-container");
	private final By SIZE_GUIDE_CTA = By.xpath("//div[@class='product-sizes__size-chart ng-scope']/a");
	private final By SIZE_GUIDE_SIDEPANEL = By.xpath("//button[@class='side-panel__close']/i");
	private By DROPDOWN_SIZE_PICKER = By.cssSelector(
			"div.product-sizes.ng-scope > div.product-sizes__select-wrapper > select");
	private By DROPDOWN_SIZE_PICKER_SAVEDITEMS_PANEL = By.xpath("//select[contains(@class,'saveditems--select')]");
	private By DROPDOWN_CURRENT_OPTIONS = By.cssSelector(
			"div.product-sizes.ng-scope > div.product-sizes__select-wrapper > select > option");
	private By ONESIZE_STOCK_MSG = By.className("product-sizes__urgency-message");
	private static final By ONESIZE_STOCK_MSG_VALUE = By.className("ng-scope");

	private static final By SHOW_FAVOURITES_BUTTON = By.className("masthead__saved-link");
	private static final By MOBILE_SHOW_FAVOURITES_BUTTON = By.xpath("//ul[@class=\"masthead__mobile-wrapper\"]/li[@class=\"masthead__mobile-item\"][4]");
	private static final By FAVOURITE_ITEM = By.className("saveditems__entry");
	private static final By FAVOURITE_ITEM_CONTAINER = By.className("saveditems__maincontent");
	private static final By FAVOURITES_CROSS = By.className("side-panel__close");
	private static final By FAVOURITES_PANEL = By.className("side-panel-wrapper");

	private static final By STICKY_ADDED_TO_BAG = By.className("product-details__bag-alert--show");
	private static final By STICKY_DETAILS_WRAPPER = By.className("product-details__sticky-cta-wrapper");
	private static final By STICKY_PRODUCT_DETAILS = By.className("product-details__sticky-info--visible");
	private static final By STICKY_PRODUCT_NAME = By.className("ng-binding");
	private static final By STICKY_PRODUCT_PRICE = By.className("price");
	private static final By RELATED_ITEMS_CTA = By.xpath("//*[@class='product-oos__button button button-primary']");
	private static final By DISABLED_SCROLL_ITEM = By.xpath("//*[@class='product-gallery--disabled ng-scope']");
	private static final By CAROUSEL_ABOVE_PRODUCT = By.xpath("//*[@class='product-gallery product-gallery--has-dots']/../..//*[@class='carousel-component carousel-component--new ng-scope']//div[@class='carousel-component__wrapper']");

	private static final By CAROUSEL_ABOVE_PRODUCT_NAME = By.xpath("//*[@class='product-item__name link--nounderline carousel-component-details__name ng-binding ng-scope']");
	private static final By CAROUSEL_ABOVE_PRODUCT_PRICE = By.xpath("//*[@class='carousel-component-details__price']");
	private static final By CAROUSEL_ABOVE_PRODUCT_ADDTOBAG = By.xpath("//*[@class='button button--primary button--full-width carousel-component-details__bag-button ng-scope']");
	private static final By CAROUSEL_ABOVE_PRODUCT_ADDTOWISHLIST = By.xpath("//*[@class='product-item__saveditem-icon product-item__saveditem-icon--hover iconf ng-scope iconf--saved-items-icon']");
	private static final By CAROUSEL_ABOVE_PRODUCT_IMAGE = By.xpath("//*[@class='carousel-component__img lazyloaded']");


	private static final By BRAND_CATEGORY_CTA = By.xpath("//*[@class='product-details__heading']/..//a");
	private static final By ATC_CALL_OOS_MESSAGE = By.cssSelector("div[data-message-type='error'] [class^='alert__item']");
	private static final By ATC_CALL_LS_MESSAGE = By.cssSelector("div[data-message-type='warning'] li");
	private static final By STANDARD_DELIVERY_MESSAGE = By.cssSelector("[data-translate='sfs.delivery.notification.short']");
	private static final By SAVED_ITEMS_HEART_ICON_OMS = By.xpath("//h1[@class='product-description__name']/.//i");
	private final By selectSizes = By.cssSelector(".product-sizes select option");
	private final By User_NAME_HAC = By.cssSelector("[name=j_username]");
	private final By PASSWORD_HAC = By.cssSelector("[name=j_password]");
	private final By CLICK_LOGIN = By.cssSelector("[type=submit]");
	private final By MONITORING = By.cssSelector("[id=monitoring]");
	private final By CLEAR_HAC = By.cssSelector("[data-menuitem=cache]>a");
	private final By CACHE_CLEAR = By.cssSelector("[id=resetCache]");


	public WebElement getRelatedItemCTA()
	{
		return webDriver.findElement(RELATED_ITEMS_CTA);
	}

	public WebElement getDisabledScrollItem()
	{
		return webDriver.findElement(DISABLED_SCROLL_ITEM);
	}

	public WebElement getCarouselAboveProduct()
	{
		return webDriver.findElement(CAROUSEL_ABOVE_PRODUCT);
	}

	public Boolean getCarouselAboveProductComponents()
	{
		Boolean displayed = false;
		displayed = isElementVisible(waitForExpectedElement(CAROUSEL_ABOVE_PRODUCT), CAROUSEL_ABOVE_PRODUCT_NAME, DEFAULT_TIMEOUT)
				&& isElementVisible(waitForExpectedElement(CAROUSEL_ABOVE_PRODUCT), CAROUSEL_ABOVE_PRODUCT_IMAGE, DEFAULT_TIMEOUT)
				&& isElementVisible(waitForExpectedElement(CAROUSEL_ABOVE_PRODUCT), CAROUSEL_ABOVE_PRODUCT_PRICE, DEFAULT_TIMEOUT)
				&& isElementVisible(waitForExpectedElement(CAROUSEL_ABOVE_PRODUCT), CAROUSEL_ABOVE_PRODUCT_ADDTOWISHLIST, DEFAULT_TIMEOUT)
				&& isElementVisible(waitForExpectedElement(CAROUSEL_ABOVE_PRODUCT), CAROUSEL_ABOVE_PRODUCT_ADDTOBAG, DEFAULT_TIMEOUT);
		return displayed;
	}

	public WebElement getDisclaimerText()
	{
		return webDriver.findElement(DISCLAIMER_TEXT);
	}

	public WebElement getStockInformation()
	{
		return waitForAndGetElement(STOCK_INFORMATION, 10);
	}

	public WebElement getMapComponent()
	{
		return waitForExpectedElement(MAP_COMPONENT);
	}

	public WebElement getPlusButton()
	{
		return waitForExpectedElement(PLUS_BUTTON);
	}

	public void clickStoreInfoLink()
	{
		webDriver.findElements(STORE_INFO_LINK).get(0).click();
	}

	public void clickDropdownSizepicker()
	{
		elementToBeClickable(DROPDOWN_SIZE_PICKER);
		waitForElementVisible(DROPDOWN_SIZE_PICKER, DEFAULT_TIMEOUT);
		webDriver.findElement(DROPDOWN_SIZE_PICKER).click();
	}

	public WebElement preSelectedSize()
	{
		return webDriver.findElement(PRE_SELECTED_SIZE);
	}


	public WebElement getPostcodeTownSearchbox()
	{
		pause(1000);
		return webDriver.findElement(POSTCODE_TOWN_SEARCHBOX);
	}

	public String getProductCode()
	{
		if (IS_MOBILE)
		{
			return waitForExpectedElement(productCodeNewMobile).getText().split(":")[1].trim();
		}
		else
		{
			return waitForExpectedElement(productCodeNew).getText().split(":")[1].trim();
		}
	}

	public WebElement getIframeColourSwatch()
	{
		return webDriver.findElement(IFRAME_COLOUR_SWATCH);
	}

	public WebElement getProductSize()
	{
		return webDriver.findElement(PROD_SIZE);
	}

	public WebElement getProductInIframe()
	{
		return webDriver.findElement(PRODUCT_IN_IFRME);
	}

	public WebElement getFindInStore()
	{
		return webDriver.findElement(FIND_IN_STORE);
	}

	public void clickFindInStore()
	{
		waitForElementPresence(FIND_IN_STORE);
		waitForAndGetElement(FIND_IN_STORE, DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(webDriver.findElement(FIND_IN_STORE));
	}

	public int getFindInStoreButtons()
	{
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad") ||
				WebDriverHelper.DEVICE_NAME.contains("Samsung") ||
				BROWSER.contains("emulator"))
		{
			waitForAndGetElement(FIND_IN_STORE, DEFAULT_TIMEOUT);
		}
		return webDriver.findElements(FIND_IN_STORE).size();
	}

	public int getNumberOfStoreResults()
	{
		pause(4000);
		int numberOfStoreResults = webDriver.findElements(STORE_LOOKUP_RESULTS).size();
		return numberOfStoreResults;
	}

	public boolean getfindInStoreCount()
	{
		waitForElementPresence(FIND_IN_STORE);
		waitForAndGetElement(FIND_IN_STORE, DEFAULT_TIMEOUT);
		int totalFindInStore = webDriver.findElements(FIND_IN_STORE).size();
		if (totalFindInStore <= 0 || totalFindInStore > 1)
		{
			return false;
		}
		return true;
	}

	public WebElement getCheckAvailabilityButton()
	{
		return waitForExpectedElement(CHECK_AVAILABILITY_BUTTON);
	}


	public WebElement getSidePanelIframe()
	{
		return webDriver.findElement(SIDE_PANEL_IFRAME);
	}

	public WebElement breadCrumbTrail()
	{
		waitForElementVisible(breadCrumbTrail, DEFAULT_TIMEOUT);
		return waitForExpectedElement(breadCrumbTrail);
	}

	public WebElement productName()
	{
		return waitForExpectedElement(productName);
	}

	public WebElement productPrice()
	{
		return waitForExpectedElement(productPrice);
	}

	public WebElement productDescritption_Cms()
	{
		return waitForAndGetElement(productDescritption_Cms, 5);
	}

	public WebElement productDetails()
	{
		return waitForExpectedElement(productDetails);
	}


	public WebElement colorSwatchesOnQuickView()
	{
		return waitForExpectedElement(colorSwatchesOnQuickView);
	}

	public WebElement swatchOutOfStock()
	{
		return waitForExpectedElement(swatchOutOfStock);
	}

	public WebElement productCode()
	{
		return waitForAndGetElement(productCode, DEFAULT_TIMEOUT);
	}

	public WebElement productImageOnPdp()
	{
		return waitForExpectedElement(productImageOnPdp);
	}


	public WebElement closeModalWindow()
	{
		return waitForExpectedElement(closeModalWindow);
	}

	public WebElement carouselPinholes()
	{
		return waitForExpectedElement(carouselPinholes);
	}

	public WebElement productPreviousPrice()
	{
		return waitForExpectedElement(productPreviousPrice);
	}

	public WebElement selectSize()
	{
		return waitForExpectedElement(selectSize);
	}


	public WebElement viewBagandCheckout()
	{
		return waitForExpectedElement(viewBagandCheckout);
	}

	public WebElement getOneSizeMessage()
	{
		return waitForExpectedElement(ONE_SIZE_MESSAGE);
	}

	public WebElement productItemCode()
	{
		return waitForExpectedElement(productItemCode);
	}

	public WebElement outOfStockDisabled()
	{
		return waitForExpectedElement(outOfStockDisabled);
	}

	public WebElement addedToBagText()
	{
		return waitForExpectedElement(addedToBagText);
	}

	public WebElement addToCartButtonDisabled()
	{
		return waitForExpectedElement(addToCartButtonDisabled);
	}


	public WebElement outOfStockSwatch()
	{
		return waitForExpectedElement(outOfStockSwatch);
	}

	public WebElement deliveryAndReturnsInformationLink()
	{
		return waitForAndGetElement(deliveryAndReturnsInformationLink, DEFAULT_TIMEOUT);
	}

	public WebElement deliverySidePanel()
	{
		return waitForExpectedElement(deliverySidePanel);
	}

	public WebElement otherDeliveryOptions()
	{
		return waitForExpectedElement(otherDeliveryOptions);
	}

	public WebElement sizeChartLink()
	{
		return waitForExpectedElement(sizeChartLink);
	}

	public WebElement productImageGallery()
	{
		return waitForExpectedElement(productImageGallery);
	}

	public WebElement sidePanelIframe()
	{
		return waitForExpectedElement(SIDE_PANEL_IFRAME);
	}

	public WebElement brandCategoryCta()
	{
		return waitForExpectedElement(BRAND_CATEGORY_CTA);
	}

	public WebElement productDetailsPage()
	{
		return waitForExpectedElement(productDetailsPage);
	}

	public WebElement SIZE_VALIDATION_MESSAGE()
	{
		return waitForExpectedElement(SIZE_VALIDATION_MESSAGE);
	}

	public List<WebElement> getCarouselProductList()
	{
		return visibilityOfAllElementsLocatedBy(carouselProductName);
	}

	public void hoverOverSwatch() throws InterruptedException
	{

		List<WebElement> totalSizes = webDriver.findElements(By.cssSelector(
				"span[class='swatches__swatch-container ng-scope']"));
		List<WebElement> colorNames = webDriver.findElements(By.cssSelector("span[data-ng-bind='swatch.displayName']"));
		String[] colors = {"Red", "Black", "White"};
		int index = 0;

		for (WebElement size : totalSizes)
		{
			Actions action = new Actions(webDriver);
			action.moveToElement(size).build().perform();
			Thread.sleep(1000);
			Assert.assertTrue(colorNames.get(index).getText().contains(colors[index]));
			index++;

		}
	}

	public void hoverOverThePdp()
	{
		Actions action = new Actions(webDriver);
		action.moveToElement(productImageGallery()).build().perform();
	}

	public void switchToDifferentColor()
	{
		pause(4000);
		colorSwatchesOnQuickView();
		try
		{
			scrollElementIntoView(colorSwatchesOnQuickView);
			List<WebElement> totalSizes = webDriver.findElements(colorSwatchesOnQuickView);
			clickByJavaScriptExecutor(totalSizes.get(1));
		}
		catch (StaleElementReferenceException e)
		{
			clickByJavaScriptExecutor(webDriver.findElements(colorSwatchesOnQuickView).get(1));
		}
	}

	public int getTotalColorSwatches()
	{
		colorSwatchesOnQuickView();
		List<WebElement> totalSizes = webDriver.findElements(By.cssSelector(".swatch__link"));

		return totalSizes.size();
	}

	public List<String> getColorsFromTheSwatches()
	{
		colorSwatchesOnQuickView();
		List<String> colorsAvailable = new ArrayList<>();
		List<WebElement> colors = webDriver.findElements(By.cssSelector(
				"span[class='swatches__swatch-container ng-scope']"));

		for (WebElement color : colors)
		{
			String[] numbers = color.getCssValue("background-color").replace("rgba(", "").replace(")", "").split(",");
			int number1 = Integer.parseInt(numbers[0]);
			numbers[1] = numbers[1].trim();
			int number2 = Integer.parseInt(numbers[1]);
			numbers[2] = numbers[2].trim();
			int number3 = Integer.parseInt(numbers[2]);
			String hex = String.format("#%02x%02x%02x", number1, number2, number3);
			colorsAvailable.add(hex);
		}

		return colorsAvailable;
	}

	public boolean checkPricesHaveNotChanged()
	{
		boolean samePrice = true;

		clickByJavaScriptExecutor(selectSize());
		new Select(webDriver.findElement(By.cssSelector("select[data-ng-model='productInfo.selectedSize']"))).selectByIndex(
				1);
		if (!(productPrice().getText().contains("£2.99")))
		{
			samePrice = false;
		}

		clickByJavaScriptExecutor(selectSize());
		new Select(webDriver.findElement(By.cssSelector("select[data-ng-model='productInfo.selectedSize']"))).selectByIndex(
				2);
		if (!(productPrice().getText().contains("£2.99")))
		{
			samePrice = false;
		}
		return samePrice;
	}


	public List<String> getDeliveryMethodText()
	{
		List<WebElement> totalSizes = webDriver.findElements(By.xpath("//table/tbody/tr/td[1]"));
		List<String> deliveryMethodText = new ArrayList<>();
		for (WebElement totalSize : totalSizes)
		{
			deliveryMethodText.add(totalSize.getText());
		}
		return deliveryMethodText;
	}

	public String getFirstAvailableSizeIfAny()
	{
		String sizeOption = null;
		try
		{
			webDriver.findElement(SIZE_SELECT);
			List<WebElement> items = webDriver.findElements(SIZE_OPTIONS);
			for (WebElement option : items)
			{
				if (!option.getText().contains("currently not available") && !option.getText().contains("CHOOSE SIZE"))
				{
					option.click();
					sizeOption = option.getText();
					if (sizeOption.contains("-"))
					{
						sizeOption = sizeOption.substring(0, sizeOption.lastIndexOf("-"));
					}
					return sizeOption.trim();
				}

			}

		}
		catch (NoSuchElementException e)
		{
			try
			{
				webDriver.findElement(ONE_SIZE);
				sizeOption = "One size";

			}
			catch (Exception e2)
			{
			}

		}
		return sizeOption;
	}

	public void clickAddToBagBtn()
	{
		waitForExpectedElement(ADD_TO_CART_BUTTON_ENABLED);
		webDriver.findElement(ADD_TO_CART_BUTTON_ENABLED).click();
	}

	public String getProductName()
	{
		waitForElementPresence(PRODUCT_NAME);
		return waitForExpectedElement(PRODUCT_NAME, 30).getText().trim();
	}

	public void addToSavedTemsList() throws Exception
	{
		By by = By.cssSelector(
				".main-container>.product-details-page>.product-side-wrapper>section>.product-ctas>.product-details__cta-wrapper>span>.button");
		try
		{
			scrollForLocationFocusAndClick(by, 5);
		}
		catch (Exception e)
		{
			hoverAndClick(getWebDriver().findElement(by));
		}

		try
		{
			waitForElementPresence(MSG_SAVED);
		}
		catch (NoSuchElementException ex)
		{
			throw new Exception("Message that item is added to saved is not displayed!");
		}
	}


	public void selectColour(int index)
	{
		waitForAndGetElement(colorSwatchesOnQuickView, DEFAULT_TIMEOUT);
		List<WebElement> colorSwathces;
		try
		{
			colorSwathces = getWebDriver().findElements(By.cssSelector(".swatch__link"));
		}
		catch (Exception e)
		{
			colorSwathces = getWebDriver().findElements(By.cssSelector(".swatch__link"));
		}
		clickByJavaScriptExecutor(colorSwathces.get(index - 1));
	}

	public void selectSizeFromDropDown()
	{
		waitForExpectedElement1(SELECT_SIZE_DROP_DOWN, 10);
		waitForExpectedElement1(SELECT_SIZE_DROP_DOWN_OPTIONS_SELECTOR, 10);
		if (IS_MOBILE)
		{
			pause(1000);
		}
		if (!webDriver.findElements(SELECT_SIZE_DROP_DOWN).isEmpty())
		{
			selectFromDropDown(waitForExpectedElement(SELECT_SIZE_DROP_DOWN), 1);
		}
	}

	public void selectSizeFromDropDownMultiPdp(int productArray)
	{
		waitForAndGetElement(SELECT_SIZE_DROP_DOWN, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(SELECT_SIZE_DROP_DOWN).isEmpty())
		{
			selectFromDropDown(webDriver.findElements(SELECT_SIZE_DROP_DOWN).get(productArray), 1);
		}
	}

	public void selectSizeFromDropDownWUrgMsgOn(Integer itemIndex)
	{
		new Select(waitForExpectedElement(SIZE_SELECT)).selectByIndex(itemIndex);
	}

	public String getSizeTextFromDropDownWUrgentMsgOn(Integer itemIndex)
	{
		String urgentMessage = null;
		waitForAndGetElement(DROPDOWN_CURRENT_OPTIONS, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(DROPDOWN_CURRENT_OPTIONS).isEmpty())
		{
			urgentMessage = webDriver.findElements(DROPDOWN_CURRENT_OPTIONS)
					.get(itemIndex)
					.getText()
					.split("-")[1].trim();
		}

		return urgentMessage;
	}

	public String getStockUrgMsgForOneSizeProdWUrgentMsgOn()
	{
		return waitForAndGetElement(ONESIZE_STOCK_MSG, DEFAULT_TIMEOUT).findElement(ONESIZE_STOCK_MSG_VALUE)
				.getText();
	}

	public Integer getSizeDisplayOrderFromDropDownWUrgMsgOn(String size)
	{
		Integer sizeOrderInDropdown = 0;
		switch (size)
		{
			case "XS":
			case "UK 8":
			case "S/M":
				sizeOrderInDropdown = 1;
				break;
			case "S":
			case "UK 10":
			case "M/L":
				sizeOrderInDropdown = 2;
				break;
			case "M":
			case "UK 12":
				sizeOrderInDropdown = 3;
				break;
			case "XL":
			case "UK 14":
				sizeOrderInDropdown = 4;
				break;
			case "XXL":
			case "UK 16":
				sizeOrderInDropdown = 5;
				break;
			default:
				log.info("Wrong size value " + size + ", the input size is not in size list. ");
		}
		return sizeOrderInDropdown;
	}

	public void clickAddToBagButton()
	{
		if (IS_MOBILE)
		{
			pause(4000);
			waitForElementVisible(ADD_TO_BAG_PRIMARY_BUTTON_SELECTOR, DEFAULT_TIMEOUT);
			scrollElementIntoView(ADD_TO_BAG_PRIMARY_BUTTON_SELECTOR);
			clickByJavaScriptExecutor(getWebDriver().findElement(ADD_TO_BAG_PRIMARY_BUTTON_SELECTOR));
		}
		else if (webDriver.findElements(ADD_TO_BAG_BUTTON)
				.stream()
				.anyMatch(a -> a.getText().contains("ADD TO BAG")))
		{
			waitForElementVisible(ADD_TO_BAG_BUTTON, DEFAULT_TIMEOUT);
			webDriver.findElements(ADD_TO_BAG_BUTTON)
					.stream()
					.filter(a -> a.getText().contains("ADD TO BAG"))
					.findFirst()
					.orElseThrow(() -> new NotFoundException("No add to bag button found"))
					.click();
		}
		else
		{
			waitForExpectedElement(ADD_TO_BAG_BUTTON).click();
		}
	}

	public void clickAddToBagButton(int addToBagButtonArray)
	{
		waitForElementVisible(ADD_TO_BAG_BUTTON, DEFAULT_TIMEOUT);
		webDriver.findElements(ADD_TO_BAG_BUTTON).get(addToBagButtonArray).click();
	}

	public void clickMyBagAndCheckoutButton()
	{
		waitForElementVisible(checkOutPage.basketIcon(), DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(checkOutPage.basketIcon());
		waitForPageLoad();
	}

	public void selectProductAndMoveToMyBagPage()
	{
		if (WebDriverHelper.BROWSER.contains("firefox"))
		{
			pause(2000);
		}
		waitForPageLoad();
		selectSizeFromDropDown();
		waitForPageLoad();
		clickAddToBagButton();
		waitForPageLoad();
		pause(9000);
		clickMyBagAndCheckoutButton();
		waitForPageLoad();
	}

	public List<WebElement> getProductSizeList()
	{
		return visibilityOfAllElementsLocatedBy(itemsSize);
	}

	public String getSelectedProductSize()
	{
		String productSize = "";
		for (int i = 0; i < getProductSizeList().size(); i++)
		{
			String selectedSize = getProductSizeList().get(i).getAttribute("selected");
			if (null != selectedSize)
			{
				if (selectedSize.equalsIgnoreCase("true"))
				{
					productSize = getProductSizeList().get(i).getText();
					break;
				}

			}
		}
		return productSize;
	}

	public void clickShowMoreProductDetailsButton()
	{
		waitForAndGetElement(SHOW_MORE_BUTTON, 10);
		if (!getWebDriver().findElements(SHOW_MORE_BUTTON).isEmpty())
		{
			scrollElementIntoView(SHOW_MORE_BUTTON);
			try
			{
				clickByJavaScriptExecutor(getWebDriver().findElements(By.cssSelector("button"))
						.stream()
						.filter(a -> a.getText().contains("Show More".trim()))
						.findFirst()
						.get());
			}
			catch (Exception e)
			{
				waitForAndGetElement(SHOW_MORE_BUTTON, 10);
				clickByJavaScriptExecutor(getWebDriver().findElements(By.cssSelector("button"))
						.stream()
						.filter(a -> a.getText().contains("Show More".trim()))
						.findFirst()
						.get());
			}
		}
	}


	public void clickOnFirstProductInCarousel(CarouselModel carouselModel)
	{
		pause(2000);
		carouselModel.setProductName(waitForExpectedElement(carouselProduct).getText());
		clickByJavaScriptExecutor(waitForExpectedElement(carouselProduct));
	}

	public void clickOnFirstProductInNewCarousel(CarouselModel carouselModel)
	{
		carouselModel.setProductName(waitForExpectedElement(newProductCarouselProductName).getText());
		clickByJavaScriptExecutor(waitForExpectedElement(newProductCarouselProductName));
	}

	public boolean verifyIfProductOpensInSidePanel(CarouselModel carouselModel)
	{
		boolean isOpenedInSidePanel = false;
		try
		{
			getWebDriver().switchTo().frame(getSidePanelIframe());
			String name = waitForExpectedElement(sidePanelProductTitle).getText();
			if (carouselModel.getProductName().equalsIgnoreCase(name))
			{
				isOpenedInSidePanel = true;
			}
		}
		catch (Exception e)
		{
			log.info("side panel did not open " + e);
		}
		return isOpenedInSidePanel;
	}

	/**
	 * Switch to side panel first and then verify if carousel container is not displayed
	 **/
	public boolean verifyIfWearWithCarouselNotDisplayed()
	{
		getWebDriver().switchTo().frame(getSidePanelIframe());
		boolean isPresent = false;
		pause(2000);
		if (!elementPresent(carouselTitle) && !elementPresent(CAROUSEL_WRAPPER))
		{
			isPresent = true;
		}
		return isPresent;
	}

	public void clickOnShowMore()
	{
		if (IS_MOBILE)
		{
			waitForAndGetElement(showMore, DEFAULT_TIMEOUT);
			if (getWebDriver().findElements(showMore).size() > 1)
			{
				clickByJavaScriptExecutor(getWebDriver().findElements(showMore).get(1));
			}
			else
			{
				clickByJavaScriptExecutor(getWebDriver().findElements(showMore).get(0));
			}
		}
		else
		{
			pause(2000);
			scrollIntoView(showMore);
			waitForExpectedElement(showMore);
			clickByJavaScriptExecutor(getWebDriver().findElement(showMore));
		}
	}

	public void clickShowMoreOnSidePanel()
	{
		pause(2000);
		getWebDriver().switchTo().frame(getSidePanelIframe());
		pause(2000);
		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("scroll(0,700);");
		waitForExpectedElement(showMore).click();
	}

	public void clickOnShowLess()
	{
		waitForAndGetElement(showLess, 10);
		clickByJavaScriptExecutor(waitForAndGetElement(showLess, DEFAULT_TIMEOUT));
	}

	public void addCarouselProductToSavedList()
	{
		waitForExpectedElement(addToSavedItem);
		clickByJavaScriptExecutor(getWebDriver().findElement(addToSavedItem));
	}

	public void removeProductFromSavedList()
	{
		waitForExpectedElement(removeSavedItem);
		clickByJavaScriptExecutor(getWebDriver().findElement(removeSavedItem));
	}

	public void verifyIfSavedItemIsAdded()
	{
		getWebDriver().switchTo().frame(getSidePanelIframe());
	}

	public void clickCarouselSlicker()
	{
		if (IS_MOBILE)
		{
			waitForPageLoad();
			scrollByCoordinates(1200);
			List<WebElement> carouselNames = webDriver.findElements(By.xpath(
					"//span[@class='product-item__name link--nounderline carousel-component__details-name ng-binding']"));
			Actions act = new Actions(webDriver);
			act.dragAndDrop(carouselNames.get(0), carouselNames.get(1)).build().perform();
		}
		else
		{
			if (waitForExpectedElement(slickDots).isDisplayed())
			{
				hoverAndClick(waitForExpectedElement(slickDots));
				clickByJavaScriptExecutor(waitForExpectedElement(carouselChevron));
			}
		}
	}

	public void selectSpecificProductFromCarousel(String productName, CheckoutModel checkoutModel)
	{
		int count = 0;
		if (IS_MOBILE)
		{
			pause(2000);

			for (WebElement element : webDriver.findElements(carouselProductName))
			{
				if (getInnerTextJavaScript(element).trim().equalsIgnoreCase(productName))
				{
					clickByJavaScriptExecutor(element);
					break;
				}
			}
			checkoutModel.setProductName(productName);
		}
		else
		{
			for (int i = 0; i < getCarouselProductList().size(); i++)
			{
				pause(2000);
				String product = getCarouselProductList().get(i).getText();
				if (productName.equalsIgnoreCase(product))
				{
					clickByJavaScriptExecutor(getCarouselProductList().get(i));
					checkoutModel.setProductName(productName);
					break;
				}
				if (count == 2)
				{
					//clickByJavaScriptExecutor(waitForExpectedElement(By.cssSelector("li[id*=slick-slide2"+j+"][aria-hidden=true]")));
					//waitForExpectedElement(By.cssSelector("li[aria-controls*=navigation0"+j+"]")).click();
					clickCarouselSlicker();
					count = 0;
					i = 0;
				}
				count++;
			}
		}
		waitForPageLoad();
	}

	public void clickOnAnotherColourFromSwatch()
	{
		pause(4000);
		if (!IS_MOBILE)
		{
			getWebDriver().switchTo().frame(getSidePanelIframe());
		}
		scrollForFocus(swatchColour, DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(waitForAndGetElement(swatchColour, DEFAULT_TIMEOUT));
	}

	public void verifyIfProductGetsUpdated()
	{
		if (IS_MOBILE)
		{
			waitForPageLoad();
			scrollElementIntoView(webDriver.findElements(showMore).get(1));
			webDriver.findElements(showMore).get(1).click();
			waitForPageLoad();
		}
		else
		{
			waitForAndGetElement(showMore, 10);
			waitForExpectedElement(showMore).click();
		}
	}

	public String getSavedItemsCount()
	{
		waitForElementVisible(savedItemsCount, 20);
		return waitForExpectedElement(savedItemsCount).getText();
	}

	public boolean checkIfProductHaveHighlightBadge()
	{
		boolean isBadgePresent = false;
		pause(2000);
		if (!IS_MOBILE)
		{
			getWebDriver().switchTo().frame(getSidePanelIframe());
		}
		pause(1000);
		if (waitForExpectedElement(highlightBadge).isDisplayed())
		{
			isBadgePresent = true;
		}
		return isBadgePresent;
	}

	public boolean isPromotionLinkPresent()
	{
		boolean isPromotionLinkPresent = false;
		pause(2000);
		if (!IS_MOBILE)
		{
			getWebDriver().switchTo().frame(getSidePanelIframe());
		}
		if (waitForExpectedElement(promotionLink).isDisplayed())
		{
			isPromotionLinkPresent = true;
		}
		return isPromotionLinkPresent;
	}

	public boolean isPriceUpdated()
	{
		boolean isPriceUpdated = false;
		pause(2000);
		getWebDriver().switchTo().frame(getSidePanelIframe());
		if (waitForExpectedElement(markedDownPrice).isDisplayed() &&
				waitForExpectedElement(previousPrice).isDisplayed())
		{
			isPriceUpdated = true;
		}
		return isPriceUpdated;
	}

	public String getProductNameFromSidePanel()
	{
		pause(2000);
		if (IS_MOBILE)
		{
			return waitForExpectedElement(productName).getText();
		}
		else
		{
			getWebDriver().switchTo().frame(getSidePanelIframe());
			return waitForExpectedElement(sidePanelProductName).getText();
		}
	}

	public List<String> findPerfectPartnerProductNames()
	{
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad") ||
				BROWSER.contains("emulator"))
		{
			pause(2000);
		}
		List<WebElement> prodname = webDriver.findElements(PRODUCT_NAME);
		List<String> prodnameString = new ArrayList<>();
		for (WebElement wb : prodname)
		{
			prodnameString.add(wb.getText());
		}
		return prodnameString;
	}

	public boolean checkMultiPdpThumbnailImage()
	{
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad") ||
				BROWSER.contains("emulator"))
		{
			pause(3000);
		}
		boolean thumbnailExists = false;
		int i = webDriver.findElements(THUMBNAIL_IMAGE).size();
		pause(2000);
		int j = webDriver.findElements(productName).size();

		if (i != j)
		{
			log.info("The number of thumbnail images count is not matching with the displayed products");
		}
		else if (i == j)
		{
			thumbnailExists = true;
		}
		return thumbnailExists;
	}

	public void totalProductCount(int noofprods)
	{
		//smart wait untill the product name/pdp is loaded
		waitForExpectedElement(PRODUCT_NAME);
		List<WebElement> prodname = webDriver.findElements(PRODUCT_NAME);
		int availableprods = prodname.size();
		Assert.assertEquals(noofprods, availableprods);
	}

	public boolean getFreeDeliveryIcon()
	{
		scrollElementIntoView(FREE_DELIVERY_VAN_ICON);
		if (!getWebDriver().findElements(FREE_DELIVERY_VAN_ICON).isEmpty())
		{
			return true;
		}
		return false;
	}

	public String getFreeStandardDeliveryHeader()
	{
		if (!getWebDriver().findElements(FREE_STANDARD_DELIVERY_HEADER).isEmpty())
		{
			return getWebDriver().findElement(FREE_STANDARD_DELIVERY_HEADER).getText();
		}
		return null;
	}

	public String getFreeDeliveryReturnLabel(String optionLabel)
	{
		if (!getWebDriver().findElements(FREE_DELIVERY_RETURN_LABEL).isEmpty())
		{
			return getWebDriver().findElements(FREE_DELIVERY_RETURN_LABEL)
					.stream()
					.filter(a -> a.getText().contains(optionLabel))
					.findFirst()
					.get()
					.getText();
		}
		return null;
	}

	public void selectSizeOfTheProduct(String size)
	{
		pause(2000);
		if (!size.equalsIgnoreCase("one size"))
		{
			try
			{
				waitForPresenceOfElement(selectSize, DEFAULT_TIMEOUT);
				scrollElementIntoView(selectSize);
			}
			catch (Exception e)
			{
				log.info("Size selector not present");
			}
			if (!getWebDriver().findElements(selectSize).isEmpty())
			{
				Select select = new Select(getWebDriver().findElement(selectSize));
				select.selectByVisibleText(size);
			}
		}
	}

	public void selectSizeOfTheProductLowStock(String size)
	{
		Integer sizeOrder = getSizeDisplayOrderFromDropDownWUrgMsgOn(size);
		pause(2000);
		if (!getWebDriver().findElements(selectSize).isEmpty())
		{
			Select select = new Select(getWebDriver().findElement(selectSize));
			select.selectByIndex(sizeOrder);
		}
		pause(2000);
		waitForPageLoad();
	}

	public String getProductDetailsHeader()
	{
		waitForPageLoad();
		scrollElementIntoView(PRODUCT_DETAILS_HEADER);
		waitForAndGetElement(PRODUCT_DETAILS_HEADER, DEFAULT_TIMEOUT);
		if (!getWebDriver().findElements(PRODUCT_DETAILS_HEADER).isEmpty())
		{
			return getWebDriver().findElement(PRODUCT_DETAILS_HEADER).getText();
		}
		return null;
	}

	public void clickWriteAReviewLink()
	{
		waitForAndGetElement(WRITE_A_REVIEW, DEFAULT_TIMEOUT);
		getWebDriver().findElement(WRITE_A_REVIEW).click();
	}

	public void reviewPopUpExists()
	{
		waitForAndGetElement(REVIEW_POPUP, DEFAULT_TIMEOUT);
		pause(1000);
		getWebDriver().findElement(REVIEW_POPUP).isDisplayed();
	}

	public void reviewExists()
	{
		waitForAndGetElement(REVIEW_SUMMARY_BODY, DEFAULT_TIMEOUT);
		getWebDriver().findElement(REVIEW_SUMMARY_BODY).isDisplayed();
	}

	public void textDoesNotExistInPage(String text)
	{
		assertFalse(getWebDriver().getPageSource().contains(text));
	}

	public String getEmailFromTextBox()
	{
		return getWebDriver().findElement(PRE_ORDER_NOTIFICATION_EMAIL_BOX).getAttribute("value");
	}

	public int returnTotalNumberOfWriteAReviewLinks()
	{
		pause(1000);
		List<WebElement> sizesAvailable = getWebDriver().findElements(By.cssSelector(
				".bv-write-review.bv-focusable.bv-submission-button"));
		return sizesAvailable.size();
	}

	public boolean getSavedItemsHeartIcon()
	{
		waitForExpectedElement(SAVED_ITEMS_HEART_ICON);
		waitForAndGetElement(getWebDriver(), SAVED_ITEMS_HEART_ICON, DEFAULT_TIMEOUT);
		if (!getWebDriver().findElements(SAVED_ITEMS_HEART_ICON).isEmpty())
		{
			return true;
		}
		return false;
	}

	public void clickSavedItemsHeartIcon()
	{
		waitForExpectedElement(SAVED_ITEMS_HEART_ICON).click();
	}

	public void clickSavedItemsHeartIconForOutOfStockProductOMS()
	{
		waitForExpectedElement(SAVED_ITEMS_HEART_ICON_OMS).click();
	}


	public void hoverToSavedItemsHeartIcon()
	{
		hoverOnElement(waitForAndGetElement(SAVED_ITEMS_HEART_ICON, DEFAULT_TIMEOUT));
	}

	public void clickAddToSavedItemsButton()
	{
		waitForExpectedElement(ADD_TO_SAVED_ITEMS_BUTTON).click();
	}

	public boolean getSavedItemsButton()
	{
		waitForAndGetElement(getWebDriver(), ADD_TO_SAVED_ITEMS_BUTTON, DEFAULT_TIMEOUT);
		if (!getWebDriver().findElements(ADD_TO_SAVED_ITEMS_BUTTON).isEmpty())
		{
			return true;
		}
		return false;
	}

	public void saveMultiItemsFromMultiPdpPage(int numberOfItems)
	{
		waitForElementVisible(SAVED_ITEMS_HEART_ICON_CAROUSEL, DEFAULT_TIMEOUT);
		waitForAndGetElement(SAVED_ITEMS_HEART_ICON_CAROUSEL, DEFAULT_TIMEOUT);
		for (int i = 0; i < numberOfItems; i++)
		{
			element = getWebDriver().findElements(SAVED_ITEMS_HEART_ICON_CAROUSEL).get(i);
			pause(3000);
			try
			{
				scrollElementIntoView(element);
				executor.executeScript("arguments[0].click();", element);
			}
			catch (StaleElementReferenceException e)
			{
				element = getWebDriver().findElements(SAVED_ITEMS_HEART_ICON_CAROUSEL).get(i);
				scrollElementIntoView(element);
				executor.executeScript("arguments[0].click();", element);
			}
			pause(500);
		}
	}

	public void saveItem()
	{
		clickSavedItemsHeartIcon();
		ShoppingItem shoppingItem = new ShoppingItem();
//        waitForElementVisible(productTitle, 10);
//        shoppingItem.setName(webDriver.findElement(productTitle).getText());
		shoppingItem.setPrice(webDriver.findElement(productPrice).getText());
		log.info("***** Saved item:{} *****", shoppingItem.getName());

	}

	public void saveItemOMS()
	{
		clickSavedItemsHeartIconForOutOfStockProductOMS();
		ShoppingItem shoppingItem = new ShoppingItem();
		shoppingItem.setPrice(webDriver.findElement(productPrice).getText());
		log.info("***** Saved item:{} *****", shoppingItem.getName());

	}

	public void removeSavedMultiItemsFromMultiPdpPage(int numberOfItems)
	{
		for (int i = 0; i < numberOfItems; i++)
		{
			element = getWebDriver().findElements(SAVED_ITEMS_HEART_ICON_CAROUSEL).get(i);
			executor.executeScript("arguments[0].click();", element);
			pause(500);
		}
	}

	public void switchtoframe(WebElement iframeElement)
	{
		webDriver.switchTo().frame(iframeElement);
	}

	public String getStoreName() throws InterruptedException
	{
		Thread.sleep(2000);
		String storename = webDriver.findElements(STORE_LOOKUP_RESULTS).get(0).getText();
		return storename;
	}

	public void clickQuickViewLink()
	{
		WebElement element;
		JavascriptExecutor executor = (JavascriptExecutor) webDriver;
		element = getWebDriver().findElements(QUICK_VIEW_LINK).get(0);
		executor.executeScript("arguments[0].click();", element);
	}

	public void selectSizeFromSidePanel()
	{
		waitForAndGetElement(SIDE_PANEL_SIZE, DEFAULT_TIMEOUT);
		Select select = new Select(getWebDriver().findElement(SIDE_PANEL_SIZE));
		select.selectByIndex(1);
	}

	public void selectSizeFromSavedSidePanel()
	{
		waitForAndGetElement(DROPDOWN_SIZE_PICKER_SAVEDITEMS_PANEL, DEFAULT_TIMEOUT);
		Select select = new Select(getWebDriver().findElement(DROPDOWN_SIZE_PICKER_SAVEDITEMS_PANEL));
		select.selectByIndex(1);
	}

	public String getFixedPriceText()
	{
		return waitForExpectedElement(FIXED_PRICE_TEXT).getText();
	}

	public void clickShopToLookButton()
	{
		clickByJavaScriptExecutor(waitForAndGetElement(SHOP_TO_LOOK_BUTTON, DEFAULT_TIMEOUT));
		waitForPageLoad();
	}

	public void clickOnCarouselImage()
	{
		waitForExpectedElement(CAROUSEL_IMAGE, DEFAULT_TIMEOUT).click();
	}

	public String getItemAddedToBagText()
	{
		waitForAndGetElement(ITEM_ADDED_TO_BAG, DEFAULT_TIMEOUT);
		return waitForAndGetElement(ITEM_ADDED_TO_BAG, DEFAULT_TIMEOUT).getText();
	}

	public boolean isAddToSavedMessageDisplayed()
	{
		return waitForAndGetElement(ADD_TO_SAVED_ITEMS_MESSAGE, DEFAULT_TIMEOUT).isDisplayed();
	}

	public boolean isRemoveFromSavedMessageDisplayed()
	{
		return waitForAndGetElement(REMOVE_FROM_SAVED_ITEMS_MESSAGE, DEFAULT_TIMEOUT).isDisplayed();
	}

	public void pdpReviewAndRatingSectionExist()
	{
		waitForAndGetElement(PDP_REVIEW_CONTAINER, DEFAULT_TIMEOUT);
		pause(1000);
		getWebDriver().findElement(PDP_REVIEW_CONTAINER).isDisplayed();
	}

	public void pdpReviewAndRatingSectionDoesNotExist()
	{
		assertFalse(reviewElementsExist());
	}

	public boolean reviewElementsExist()
	{
		boolean present;
		try
		{
			getWebDriver().findElement(PDP_REVIEW_CONTAINER);
			present = true;
		}
		catch (NoSuchElementException e)
		{
			present = false;
		}
		return present;
	}

	//the below method returns list of swatch colour elements
	public List<WebElement> getSwatchColorsElements()
	{
		waitForElementPresence(swatchColour);
		waitForExpectedElement(swatchColour);
		return getWebDriver().findElements(swatchColour);
	}

	public boolean isSizeGuideCTADisplayed()
	{
		return isElementVisible(SIZE_GUIDE_CTA, 10);
	}

	public void clickOnSizeguideCta()
	{
		scrollForFocus(SIZE_GUIDE_CTA, 5);
		clickByJavaScriptExecutor(waitForElementPresence(SIZE_GUIDE_CTA));
	}

	public boolean isSizeGuideSidePanelDisplayed()
	{
		return isElementVisible(SIZE_GUIDE_SIDEPANEL, 5);
	}

	private WebElement getCarousel()
	{
		return waitForExpectedElement(CAROUSEL_WRAPPER);
	}

	private List<PdpCarouselItem> getCarouselItems()
	{
		waitForElementVisible(CAROUSEL_ITEM, DEFAULT_TIMEOUT);
		scrollElementIntoView(CAROUSEL_ITEM);
		return getCarousel().findElements(CAROUSEL_ITEM)
				.stream()
				.map(PdpCarouselItem::generatePdpCarouselItem)
				.collect(Collectors.toList());
	}

	public PdpCarouselItem getPdpCarouselItem(final String productName)
	{
		pause(3000);
		final PdpCarouselItem item = getCarouselItems().stream()
				.filter(product -> product.getName()
						.getAttribute("textContent")
						.trim()
						.equalsIgnoreCase(productName))
				.findAny()
				.orElseThrow(() -> new NotFoundException(
						"No carousel item found: " + productName));

		if (IS_MOBILE)
		{
			scrollCarouselItemIntoView(productName);
		}

		return item;
	}

	private void scrollCarouselItemIntoView(final String item)
	{
		final List<WebElement> items = getCarousel().findElements(CAROUSEL_ITEM);
		final WebElement name = items.stream()
				.filter(i -> i.findElement(CAROUSEL_ITEM_NAME)
						.getAttribute("textContent")
						.trim()
						.equalsIgnoreCase(item))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("No carousel item found: " + item));
		if (!isElementPresent(FAVOURITES_PANEL))
		{
			scrollElementIntoView(name);
		}
		final Actions actions = new Actions(getWebDriver());

		final int rightEdge = webDriver.findElement(BODY).getRect().width;

		for (int i = 1; i < items.size(); i++)
		{
			if (name.getLocation().x < rightEdge)
			{
				return;
			}

			final List<WebElement> activeItems = getActiveCarouselItems();
			final WebElement rightmostVisibleItem = activeItems.get(activeItems.size() - 1);

			actions.clickAndHold(rightmostVisibleItem)
					.moveByOffset(-rightmostVisibleItem.getRect().width, 0)
					.release()
					.perform();
			pause(500);
		}
	}

	private List<WebElement> getActiveCarouselItems()
	{
		return getCarousel().findElements(CAROUSEL_ITEM_ACTIVE);
	}

	public void selectSpecificProductFromNewCarousel(final String productName, final CheckoutModel checkoutModel)
	{
		pause(3000);
		waitForElementPresence(newProductCarouselProductName);
		for (final WebElement element : webDriver.findElements(newProductCarouselProductName))
		{
			if (getInnerTextJavaScript(element).trim().equalsIgnoreCase(productName))
			{
				clickByJavaScriptExecutor(element);
				break;
			}
		}
		checkoutModel.setProductName(productName);
		waitForPageLoad();
	}

	public String getMarkedDownPriceForProductCarouselItem(String productName)
	{
		By markDownPriceProductCarouselItem = By.xpath(
				"//div[@class='carousel-component-details__product-details' and contains(.,'" +
						productName +
						"')]//span[contains(@class,'price--marked-down')]");
		return getInnerTextJavaScript(waitForAndGetElement(markDownPriceProductCarouselItem, 5));
	}

	public String getPreviousPriceForProductCarouselItem(String productName)
	{
		By previousPriceProductCarouselItem = By.xpath(
				"//div[@class='carousel-component-details__product-details' and contains(.,'" +
						productName +
						"')]//strike[contains(@class,'price--previous-price')]");
		return getInnerTextJavaScript(waitForAndGetElement(previousPriceProductCarouselItem, 5));
	}

	public String getCurrentPriceForProductCarouselItem(String productName)
	{
		By previousPriceProductCarouselItem = By.xpath(
				"//div[@class='carousel-component-details__product-details' and contains(.,'" +
						productName +
						"')]//span[contains(@class,'price ng-binding')]");
		return waitForAndGetElement(previousPriceProductCarouselItem, 5).getText();
	}

	public void selectAvailableSizeFromProductCarousel(final String productName)
	{
		selectFromDropDown(getPdpCarouselItem(productName).getVariantSelector(), 1);
	}

	public void addToBagCarouselProduct(final String productName)
	{
		final PdpCarouselItem item = getPdpCarouselItem(productName);
		scrollElementIntoView(item.getAddToBagButton());
		scrollByCoordinates(30);
		clickByJavaScriptExecutor(item.getAddToBagButton());
	}

	public boolean isSizeSelectorPresentProductCarousel(final String productName)
	{
		final PdpCarouselItem item = getPdpCarouselItem(productName);
		return Objects.nonNull(item.getVariantSelector()) && item.getVariantSelector().isDisplayed();
	}

	public boolean isOneSizeMessageDisplayedProductCarousel(final String productName)
	{
		final PdpCarouselItem item = getPdpCarouselItem(productName);
		return Objects.nonNull(item.getOneSizeMessage()) && item.getOneSizeMessage().isDisplayed();
	}

	public boolean getCarouselComponentSuccessMessageAlert(String productName)
	{
		By carouselComponentMessage = By.xpath(
				"//div[@class='carousel-component-details__product-details' and contains(.,'" +
						productName +
						"')]/..//p[contains(@data-ng-class,'showBagMessage')]/span");
		try
		{
			waitForElementVisible(carouselComponentMessage, DEFAULT_TIMEOUT);
		}
		catch (Exception e)
		{
			log.info("Element not visible");
		}

		return find(carouselComponentMessage).isDisplayed();
	}

	public String getCarouselComponentErrorMessage(String productName)
	{
		By carouselComponentMessage = By.xpath(
				"//div[@class='carousel-component-details__product-details' and contains(.,'" +
						productName +
						"')]/..//p[contains(@data-ng-class,'showSelectionError')]");
		return waitForExpectedElement(carouselComponentMessage, 3).getText();
	}

	public WebElement getSavedItemsUrgencyWarning()
	{
		try
		{
			frameToBeAvailableAndSwitchToIt(SIDE_PANEL_IFRAME);
			return waitForExpectedElement(SAVED_ITEMS_URGENCY_WARNING);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public WebElement getStickyAddToBagButton()
	{
		return waitForExpectedElement(STICKY_ADD_TO_BAG);
	}

	public boolean isStickyAddToBagButtonVisible()
	{
		return isElementVisible(STICKY_ADD_TO_BAG);
	}

	public List<WebElement> getStickySizePickerOptions()
	{
		return getStickyAddToBagButton().findElements(DROPDOWN_OPTION);
	}

	public boolean isStickyAddedToBagMessageVisible()
	{
		return isElementVisible(STICKY_ADDED_TO_BAG, 1);
	}

	public WebElement getStickyAddedToBagMessage()
	{
		return waitForExpectedElement(STICKY_ADDED_TO_BAG);
	}

	public FavouriteItem getFavourite(final String item)
	{
		return getFavouriteItems().stream()
				.filter(favourite -> favourite.getName()
						.getAttribute("textContent")
						.trim()
						.equalsIgnoreCase(item))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Favourite item not found: " + item));
	}

	public List<FavouriteItem> getFavouriteItems()
	{
		return getFavouritesContainer().findElements(FAVOURITE_ITEM)
				.stream()
				.map(FavouriteItem::generateFavouriteItem)
				.collect(Collectors.toList());
	}

	public WebElement getFavouritesContainer()
	{
		webDriver.switchTo().defaultContent();
		frameToBeAvailableAndSwitchToIt(SIDE_PANEL_IFRAME);
		return waitForExpectedElement(FAVOURITE_ITEM_CONTAINER);
	}

	public WebElement getFavouritesCross()
	{
		webDriver.switchTo().defaultContent();
		return waitForExpectedElement(FAVOURITES_CROSS);
	}

	public void waitUntilFavouritesSectionIsInvisible()
	{
		waitUntilElementNotDisplayed(FAVOURITES_PANEL);
	}

	public WebElement getShowFavouritesButton()
	{
		scrollToTop();
		if (IS_MOBILE)
		{
			return waitForExpectedElement(MOBILE_SHOW_FAVOURITES_BUTTON);
		}
		else
		{
			return waitForExpectedElement(SHOW_FAVOURITES_BUTTON);
		}
	}

	public List<String> getSizeValues()
	{
		return getSizePickerOptions().stream()
				.filter(element -> !element.getAttribute("value").isEmpty())
				.map(WebElement::getText)
				.collect(Collectors.toList());
	}

	private List<WebElement> getSizePickerOptions()
	{
		return waitForExpectedElement(SIZE_SELECT).findElements(DROPDOWN_OPTION);
	}

	public WebElement getStickyProductName()
	{
		return waitForExpectedElement(STICKY_PRODUCT_DETAILS).findElement(STICKY_PRODUCT_NAME);
	}

	public boolean isStickyProductNameVisible()
	{
		return isElementVisible(STICKY_PRODUCT_DETAILS) &&
				isElementVisible(waitForExpectedElement(STICKY_PRODUCT_DETAILS), STICKY_PRODUCT_NAME, DEFAULT_TIMEOUT);
	}

	public WebElement getStickyProductPrice()
	{
		return waitForExpectedElement(STICKY_PRODUCT_DETAILS).findElement(STICKY_PRODUCT_PRICE);
	}

	public boolean isStickyProductPriceVisible()
	{
		return isElementVisible(STICKY_PRODUCT_DETAILS) &&
				isElementVisible(waitForExpectedElement(STICKY_PRODUCT_DETAILS), STICKY_PRODUCT_PRICE, DEFAULT_TIMEOUT);
	}

	public WebElement getAddToBagButton()
	{
		return waitForElementPresence(ADD_TO_BAG_BUTTON);
	}

	public WebElement getStickyDetailsWrapper()
	{
		return waitForElementPresence(STICKY_DETAILS_WRAPPER);
	}

	public boolean outOfStockPdpMessage()
	{
		return getElementText(ATC_CALL_OOS_MESSAGE).contains(Props.getProp("pdp_oos_atc_call_message"));
	}

	public boolean lowStockCartMessage()
	{
		return getElementText(ATC_CALL_LS_MESSAGE).contains(Props.getProp("cart_low_stock_message"));
	}

	public void selectSizeWithText(String text)
	{
		webDriver.findElements(selectSizes)
				.stream()
				.filter(a -> a.getText().contains(text))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("No size with text " + text + " found"))
				.click();
		pause(2000);
		waitForPageLoad();
	}

	public boolean standardDeliveryPdpMessageDisplayed()
	{
		verifyFontAndSize(STANDARD_DELIVERY_MESSAGE, "14px", "rgba(55, 35, 247, 1)");
		return getElementText(STANDARD_DELIVERY_MESSAGE).equals(Props.getProp("standard_delivery_message"));
	}
 public void enterCredentialClick(){
   waitForPageLoad();
	 webDriver.findElement(User_NAME_HAC).sendKeys("admin");
	 webDriver.findElement(PASSWORD_HAC).sendKeys("nimda");
	 webDriver.findElement(CLICK_LOGIN).click();

 }
	public void clearingCache(){
		Actions actions = new Actions(getWebDriver());
		actions.moveToElement(webDriver.findElement(MONITORING)).perform();
		webDriver.findElement(CLEAR_HAC).click();
		webDriver.findElement(CACHE_CLEAR).click();
		log.info("cache clear");
		webDriver.findElement(CACHE_CLEAR).click();
		log.info("cache clear");


	}


}
