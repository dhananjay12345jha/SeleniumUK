package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.WebDriverHelper;

import io.cucumber.datatable.DataTable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.*;
import static org.junit.Assert.assertTrue;

public class MyBagPage extends PageObject
{
	public static final By BAG_ITEMS = By.className("bag-item");
	private static final By CHANGE_COLOUR_LINK = By.className("bag-item__control--more-colours");
	private static final By PRODUCT_CODE = By.cssSelector(".bag-item__prod-code.ng-binding");
	private static final By COLOR_SWATCH_PANEL = By.cssSelector(".popover__content.bag-item__content.popover__content--above");
	private static final By SELECTED_COLOUR = By.className("bag-item__colour");
	private static final By PRODUCT_NAME = By.cssSelector(".link--nounderline.ng-binding");
	private static final By BAG_ITEM_COLOUR = By.className("bag-item__colour");
	private static final By BAG_ITEM_SIZE = By.cssSelector(".bag-item__size-selector option[selected='selected'");
	public static final By BAG_ITEM_SIZE_SINGLE = By.className("bag-item__size");
	private static final By CHANGE_DELIVERY_COUNTRY = By.cssSelector("[href*='#language-change']");
	private static final By CHANGE_COUNTRY_SEARCH_FIELD = By.cssSelector(".nl-select__search-field > .ui-select-search[aria-owns='ui-select-choices-3']");
	private static final By MAST_HEAD_CHANGE_COUNTRY_SEARCH_FIELD = By.cssSelector(".ui-select-search.nl-select__search.ng-pristine.ng-valid");
	private static final By CHANGE_COUNTRY_SEARCH_RESULTS = By.className("ui-select-choices-row-inner");
	private static final By APPLY_CHANGE_BUTTON = By.className("bag-footer__country-change-button");
	private static final By CHANGE_COUNTRY_FIELD = By.cssSelector("div[data-ng-model='selected.country']");
	private static final By CHANGE_COUNTRY_FIELD_MOBILE = By.xpath("//*[@data-ng-model='selected.country']");
	private Logger log = LogManager.getLogger(MyBagPage.class.getName());
	private static final By ITEMS_DISPLAYED = By.className("bag-item");
	private static final By SELECT_SIZE_OF_PRODUCT = By.className("saveditems--select");
	private static final By MOVE_TO_SAVED_ITEMS_LINK = By.cssSelector("a[href='#body']");
	private static final By SAVED_ITEMS_BUTTON = By.cssSelector(".iconf.iconf--saved-items-icon");
	private static final By VIEW_FULL_PAGE_BUTTON = By.cssSelector("a[href*='/uk/saved-items']");
	private static final By SAVED_ITEMS_SIDE_PANEL = By.cssSelector("iframe.side-panel__iframe");//By.xpath("//iframe[contains(@class,'side-panel__iframe')]");
	private static final By SECURE_CHECKOUT_PRIMARY_BUTTON = By.cssSelector(".order-summary__checkout.button.button--primary.checkoutButton.button");
	private static final By PRODUCT_SIZE_SELECTOR = By.className("bag-item__size-selector");
	public static final By BAG_ERROR_ALERT = By.cssSelector(".alert__group--error span");
	private static final By MY_BAG_HEADER = By.cssSelector(".header__bagcount.masthead__bagcount.ng-binding");
	private String myBagCounter = null;
	private static final By REMOVE_LINK = By.xpath("//a[contains(@id,'removeEntry')]");
	private static final By PRODUCT_SIZE_DROPDOWN = By.cssSelector(".select.select--white.select--bag-item.ng-pristine");
	private static final By ALERT_MESSAGE = By.className("alert__item");
	private String productCode;
	private static final By EDIT_MOBILE = By.xpath("//*[contains(@id,'editEntry')]");
	private static final By MY_BAG_ITEM_MESSAGE = By.cssSelector(".bag-item__message.bag-item__message--text");
	private final By CHANGE_COUNTRY_LINK_SELECTOR = By.cssSelector(".ng-scope a[href='#language-change']");
	private final By BREXIT_MESSAGE_SELECTOR = By.cssSelector(".brexit-banner-bag.ng-scope p");
	private final By STOCK_MSG = By.xpath("//span[contains(@ng-if,'entry.product.stock.stockLevel')]");
	private final By TROLLEY_ROCKET = By.xpath("//*[@class='trolley-rocket__progress-indicator']");
	private final By FREE_DELIVERY_THRESHOLD_MESSAGE = By.xpath("//*[@class='trolley-rocket__bottom-message']//span");
	private final By FREE_DELIVERY_QUALIFIED_MESSAGE = By.xpath("//*[@class='trolley-rocket__top-message']/span");
	private final By CART_LIMIT_MESSAGE = By.xpath("//*[@data-ng-repeat='item in messages']/span");
	private static final By SAVED_ITEMS_COUNT_ICON = By.cssSelector("a[href='/uk/saved-items']");
	private static final By CART_ENTRIES = By.cssSelector("[data-ng-repeat='entry in bag.entries']");
	private static final By PRODUCT_NAMES_LIST = By.cssSelector("[class='bag-item__prod-name'] a");
	private static final By STANDARD_DELIVERY_MESSAGE = By.cssSelector("[data-translate='sfs.delivery.notification.short']");
	private By EXPAND_SHOW_MORE_CART = By.cssSelector(".sfs-message-header__cta--tab> p");
	private By CONTRACT_SHOW_LESS_CART = By.cssSelector("[data-translate='sfs.messages.contract']");
	private By GROUP_DELIVERY_MESSAGE = By.cssSelector("[data-translate='sfs.messages.title']");
	private By GROUP_DELIVERYMSG_TEXT = By.cssSelector("p[data-translate='sfs.messages.internal']");
	private final By PROMO_CODE_ERROR_MSG = By.xpath("//span[contains(@class,'coupon-error--desktop-sticky')]");
	private final By PROMO_CODE_APPLIED_COUPON = By.xpath("//*[@class='applied-coupons applied-coupons--desktop-sticky']");
	private final By TOTAL_AMOUNT_IN_STICKY = By.xpath("//strong[contains(@class,'order-summary__label--total')]/following-sibling::span");
	private final By DELIVERING_TO_COUNTRY = By.xpath("//span[contains(@class,'delivering-to-country-section')]");
	private final By DELIVERY_INFORMATION = By.xpath("//span[contains(@class,'order-summary__label')]");
	private final By APPLIED_DISCOUNT_AMOUNT = By.xpath("//span[contains(@class,'order-summary__label') and contains(text(),'Savings')]/following-sibling::span");
	private static final By PROMISE_CALL_OOS_MESSAGE = By.cssSelector("div[data-message-type='error'] [class^='alert__item']");
	// Delivery method
	private static final By COLLECTION_METHOD_DISABLED = By.cssSelector(".collection__tab__collect--disabled");
	private static final By COLLECTION_METHOD_DISABLED_Text_Message = By.cssSelector(".collection__tab__collect--disabled>div>div>span[class='tab-button__secondary ng-scope']");
	public static String productName;
	@Autowired
	private PdpPage pdpPage;

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public void clickPrimarySecureCheckoutButton()
	{
		pause(3000);
		if (!WebDriverHelper.BROWSER.equals("firefox"))
		{
			getSecureCheckoutPrimaryButton();
		}
		waitForExpectedElement1(SECURE_CHECKOUT_PRIMARY_BUTTON, 10);
		if (webDriver.findElements(SECURE_CHECKOUT_PRIMARY_BUTTON).stream().filter(a -> a.getText().contains("SECURE CHECKOUT".trim())).count() != 0)
		{
			clickByJavaScriptExecutor(webDriver.findElements(SECURE_CHECKOUT_PRIMARY_BUTTON).stream().filter(a -> a.getText().contains("SECURE CHECKOUT".trim())).findFirst().get());
		}
		else
		{
			waitForExpectedElement1(SECURE_CHECKOUT_PRIMARY_BUTTON, 10);
			webDriver.findElements(SECURE_CHECKOUT_PRIMARY_BUTTON).get(0).click();
		}
		waitForPageLoad();
	}

	public WebElement getSecureCheckoutPrimaryButton()
	{
		return waitForExpectedElement(SECURE_CHECKOUT_PRIMARY_BUTTON, 50);
	}

	public boolean getAddedItemsExist()
	{
		waitForAndGetElement(webDriver, ITEMS_DISPLAYED, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(ITEMS_DISPLAYED).isEmpty())
		{
			return true;
		}
		return false;
	}

	public boolean getTrolleyRocketExist()
	{
		waitForAndGetElement(webDriver, TROLLEY_ROCKET, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(TROLLEY_ROCKET).isEmpty())
		{
			return true;
		}
		return false;
	}

	public void selectSizeOfTheProduct()
	{
		waitForAndGetElement(webDriver, SELECT_SIZE_OF_PRODUCT, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(SELECT_SIZE_OF_PRODUCT).isEmpty())
		{
			scrollElementIntoView(SELECT_SIZE_OF_PRODUCT);
			selectFromDropDown(webDriver.findElement(SELECT_SIZE_OF_PRODUCT), 1);
		}
	}

	public void clickMoveToSavedItemsLink()
	{
		waitForAndGetElement(webDriver, MOVE_TO_SAVED_ITEMS_LINK, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(MOVE_TO_SAVED_ITEMS_LINK).isEmpty())
		{
			clickByJavaScriptExecutor(webDriver.findElements(MOVE_TO_SAVED_ITEMS_LINK).get(0));
		}
	}

	public void navigateToSavedItemPage()
	{
//		scrollToObject(webDriver.findElement(SAVED_ITEMS_BUTTON));
//		clickWhenClickable(webDriver.findElement(SAVED_ITEMS_BUTTON), 20);
		waitForAndGetElement(webDriver, SAVED_ITEMS_SIDE_PANEL, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(SAVED_ITEMS_SIDE_PANEL).isEmpty())
		{
			webDriver.switchTo().frame(webDriver.findElement(SAVED_ITEMS_SIDE_PANEL));
			clickWhenClickable(webDriver.findElement(VIEW_FULL_PAGE_BUTTON), 10);
			waitForPageLoad();
		}
		pause(4000);
	}

	public void setTheSizeOfTheProduct(String size)
	{
		waitForElementVisible(PRODUCT_SIZE_DROPDOWN, 10);
		webDriver.findElement(PRODUCT_SIZE_DROPDOWN).click();
		(getWebDriver().findElement(By.cssSelector("select >option[label*='" + size + "']"))).click();
	}

	public String getTheSizeOfTheProduct()
	{
		pause(2000);
		waitForElementVisible(PRODUCT_SIZE_SELECTOR, 10);
		List<WebElement> elements = getWebDriver().findElements(PRODUCT_SIZE_SELECTOR);
		if (CollectionUtils.isNotEmpty(elements))
		{
			return elements.get(0).findElement(By.cssSelector("option")).getText();
		}
		return null;
	}

	public String getDeliveryThresholdMessage()
	{
		pause(2000);
		try
		{
			List<WebElement> elements = getWebDriver().findElements(FREE_DELIVERY_THRESHOLD_MESSAGE);
			if (CollectionUtils.isNotEmpty(elements))
			{
				return elements.get(0).getText();
			}
		}
		catch (Exception e)
		{
			log.info("No delivery threshold message displayed");
		}
		return null;
	}

	public String getDeliveryQualifiedMessage()
	{
		pause(2000);
		try
		{
			List<WebElement> elements = getWebDriver().findElements(FREE_DELIVERY_QUALIFIED_MESSAGE);
			if (CollectionUtils.isNotEmpty(elements))
			{
				return elements.get(0).getText();
			}
		}
		catch (Exception e)
		{
			log.info("No delivery qualified message displayed");
		}
		return null;
	}

	public String getCartLimitMessage()
	{
		pause(2000);
		try
		{
			List<WebElement> elements = getWebDriver().findElements(CART_LIMIT_MESSAGE);
			if (CollectionUtils.isNotEmpty(elements))
			{
				return elements.get(0).getText();
			}
		}
		catch (Exception e)
		{
			log.info("No cart limit message displayed");
		}
		return null;
	}

	public String getTheSizeOfTheProductOnMobile()
	{
		pause(2000);
		clickEditForLineItemMobile(1);
		waitForElementVisible(PRODUCT_SIZE_SELECTOR, 10);
		if (!webDriver.findElements(PRODUCT_SIZE_SELECTOR).isEmpty())
		{
			return getWebDriver().findElements(PRODUCT_SIZE_SELECTOR).get(0).findElement(By.cssSelector("option")).getText();
		}
		return null;
	}

	public void clickEditForLineItemMobile(int lineItem)
	{
		pause(3000);
		waitForPresenceOfElement(EDIT_MOBILE, DEFAULT_TIMEOUT);
		waitForElementVisible(PRODUCT_CODE, 10);
		this.productCode = webDriver.findElement(PRODUCT_CODE).getText();
		scrollElementIntoView(EDIT_MOBILE);
		waitForAndGetElement(EDIT_MOBILE, DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(presenceOfAllElementsLocatedBy(EDIT_MOBILE).get(lineItem - 1));
	}

	public void setSizeOfTheBag()
	{
		pause(4000);
		if (!getWebDriver().findElements(MY_BAG_HEADER).isEmpty())
		{
			this.myBagCounter = getWebDriver().findElement(MY_BAG_HEADER).getText().replaceAll("[^\\d.]", "");
		}
	}

	public String getMyBagCounter()
	{
		return myBagCounter;
	}

	public WebElement removeLink()
	{
		return waitForAndGetElement(REMOVE_LINK, 10);
	}

	public void clickRemoveLink(WebElement element)
	{
		scrollElementIntoView(element);
		clickByJavaScriptExecutor(element);
	}

	public void removeAllItems() throws InterruptedException
	{
		pause(5000);
		waitForAndGetElement(REMOVE_LINK, DEFAULT_TIMEOUT);
		if (webDriver.findElements(REMOVE_LINK).size() == 0)
		{
			log.info("empty products");
		}
		else
		{
			waitForElementVisible(REMOVE_LINK, 30);
			int size = webDriver.findElements(REMOVE_LINK).size();
			for (int i = 0; i < size; i++)
			{
				pause(2000);
				try
				{
					WebElement wb = webDriver.findElements(REMOVE_LINK).get(0);
					clickRemoveLink(wb);
					pause(3000);
				}
				catch (Exception e)
				{
					WebElement wb = webDriver.findElements(REMOVE_LINK).get(0);
					clickRemoveLink(wb);
					pause(2000);
				}
			}

			log.info("removed all items");

		}
	}

	public boolean addProductAndReturnName()
	{
		boolean prodName = false;
		String productName = getProductName();
		if (webDriver.findElements(By.xpath("//a[contains(@class,'link--nounderline ng-binding') and contains(text(),'" + productName + "')]")).size() > 0)
		{
			prodName = true;
		}
		return prodName;
	}

	public List<WebElement> getBagItems()
	{
		return new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(BAG_ITEMS));
	}

	public List<String> getMyBagHeaderAndItemsAddedInMyBagPage()
	{
		List<String> displayedItems = new ArrayList<>();
		//displayedItems.add(getMyBagHeader());
		for (WebElement e : getBagItems())
		{
			displayedItems.add(e.findElement(PRODUCT_NAME).getText().toString().replaceAll("\\d", "").replaceAll("\\p{P}", "").trim());
			displayedItems.add(e.findElement(BAG_ITEM_COLOUR).getText());
		}
		if (isElementPresent(BAG_ITEM_SIZE))
		{
			displayedItems.add(getWebDriver().findElement(BAG_ITEM_SIZE).getText().trim());
		}
		if (isElementPresent(BAG_ITEM_SIZE_SINGLE))
		{
			displayedItems.add(getWebDriver().findElement(BAG_ITEM_SIZE_SINGLE).getText().trim());
		}
		return displayedItems;
	}

	public String getMyBagHeader()
	{
//        waitForElementVisible(MY_BAG_HEADER, 10);#
		pause(2000);
		if (!getWebDriver().findElements(MY_BAG_HEADER).isEmpty())
		{
			return getWebDriver().findElement(MY_BAG_HEADER).getText();
		}
		return null;
	}

	public void changeProductColour()
	{
		clickByJavaScriptExecutor(webDriver.findElement(CHANGE_COLOUR_LINK));
		waitForElementVisible(COLOR_SWATCH_PANEL, 10);
		selectColorCodeFromSwatch();
	}


	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode()
	{
		waitForElementVisible(PRODUCT_CODE, 10);
		this.productCode = webDriver.findElement(PRODUCT_CODE).getText();
	}


	public void selectColorCodeFromSwatch()
	{
		List<WebElement> colorCodes = getWebDriver().findElements(By.cssSelector(".bag-item__square-colour"));
		int sizeOfColorCodes = colorCodes.size();

		if (sizeOfColorCodes == 1)
		{
			colorCodes.get(1).click();
		}
		else
		{
			if (sizeOfColorCodes > 2)
			{
				colorCodes.get(2).click();
			}
		}
	}


	public void changeTheDeliveryCountry(String deliveryCountry)
	{
		if (IS_MOBILE)
		{
			scrollByCoordinates(120);
			clickByJavaScriptExecutor(waitForExpectedElement(CHANGE_DELIVERY_COUNTRY, 30));
			scrollByCoordinates(120);
			selectFromDropDownWithVisibleText(getWebDriver().findElement(CHANGE_COUNTRY_FIELD_MOBILE), deliveryCountry);
			clickByJavaScriptExecutor(waitForExpectedElement(APPLY_CHANGE_BUTTON, 20));
		}
		else
		{
			waitForElementVisible(CHANGE_DELIVERY_COUNTRY, 10);
			clickByJavaScriptExecutor(getWebDriver().findElement(CHANGE_DELIVERY_COUNTRY));
			pause(2000);
			getWebDriver().findElement(CHANGE_COUNTRY_FIELD).click();
			waitForAndGetElement(MAST_HEAD_CHANGE_COUNTRY_SEARCH_FIELD, 10).sendKeys(deliveryCountry);
			getWebDriver().findElements(CHANGE_COUNTRY_SEARCH_RESULTS).get(0).click();
			getWebDriver().findElement(APPLY_CHANGE_BUTTON).click();
		}
	}

	public void changeTheDeliveryCountryWithoutConfirmingMobile(String deliveryCountry)
	{
		scrollByCoordinates(120);
		clickByJavaScriptExecutor(waitForExpectedElement(CHANGE_DELIVERY_COUNTRY, 30));
		scrollByCoordinates(120);
		selectFromDropDownWithVisibleText(getWebDriver().findElement(CHANGE_COUNTRY_FIELD_MOBILE), deliveryCountry);
	}

	public String getAlertMessage()
	{
		waitForAndGetElement(ALERT_MESSAGE, DEFAULT_TIMEOUT);
		return waitForAndGetElement(ALERT_MESSAGE, DEFAULT_TIMEOUT).getText();
	}

	public String getBagItemMessage()
	{
		waitForAndGetElement(MY_BAG_ITEM_MESSAGE, DEFAULT_TIMEOUT);
		String s = webDriver.findElements(MY_BAG_ITEM_MESSAGE).get(0).getText();
		if (s.isEmpty())
		{
			s = webDriver.findElements(MY_BAG_ITEM_MESSAGE).get(1).getText();
		}
		return s;
	}

	public void clickChangeCountryLink()
	{
		pause(3000);
		if (IS_MOBILE)
		{
			pause(3000);
			scrollElementIntoView(By.className("bag-footer__options"));
			clickByJavaScriptExecutor(getWebDriver().findElement(CHANGE_COUNTRY_LINK_SELECTOR));
		}
		else
		{
			scrollElementIntoView(CHANGE_COUNTRY_LINK_SELECTOR);
			waitForExpectedElement(CHANGE_COUNTRY_LINK_SELECTOR).click();
		}
	}

	public String getBrexitMessage()
	{
		return waitForExpectedElement1(BREXIT_MESSAGE_SELECTOR, 20).getText();
	}


	public String getStockUrgMsg()
	{
		String urgentMessage = null;
		waitForAndGetElement(STOCK_MSG, DEFAULT_TIMEOUT);
		if (!webDriver.findElements(STOCK_MSG).isEmpty())
		{
			urgentMessage = webDriver.findElements(STOCK_MSG).get(0).getText().trim();
		}
		return urgentMessage;
	}

	public void verifyDeliveryMessagesOnCartPage(DataTable table)
	{
		pause(5000);
		List<String> productCodes = new ArrayList<>();
		table.cells().forEach(a -> {
			productCodes.add(Props.getProp(a.get(0)));
		});
		
		waitForElementPresence(STANDARD_DELIVERY_MESSAGE);
		if (IS_MOBILE) { 
		    scrollIntoView(STANDARD_DELIVERY_MESSAGE); 
		}

		List<WebElement> messages = getWebDriver().findElements(STANDARD_DELIVERY_MESSAGE);
		Assert.assertEquals(table.cells().size(), messages.size());
		for (WebElement ele : messages)
		{
			pdpPage.verifyFontAndSize(ele, "14px", "rgba(55, 35, 247, 1)");
		}

		List<WebElement> cartEntries = getWebDriver().findElements(CART_ENTRIES);
		cartEntries.forEach(e -> {
			if (productCodes.contains(e.findElement(PRODUCT_NAMES_LIST).getAttribute("href")))
			{
				System.out.println(e.findElement(PRODUCT_NAMES_LIST).getAttribute("href"));
				Assert.assertEquals(Props.getProp("standard_delivery_message"), e.findElement(STANDARD_DELIVERY_MESSAGE).getText());
			}
		});
	}

	public void verifyDeliveryMessagesOnSavedItemsPage(DataTable table)
	{
		waitForPageLoad();
		List<WebElement> messages = getWebDriver().findElements(STANDARD_DELIVERY_MESSAGE);
		Assert.assertEquals(table.cells().size(), messages.size());
		for (WebElement ele : messages)
		{
			pdpPage.verifyFontAndSize(ele, "14px", "rgba(55, 35, 247, 1)");
			Assert.assertEquals(Props.getProp("standard_delivery_message"), ele.getText());

		}
	}

	public void verifyGroupDeliveryMessage()
	{
		Assert.assertEquals(Props.getProp("group_standard_delivery_message"), getWebDriver().findElement(GROUP_DELIVERY_MESSAGE).getText());
		pdpPage.verifyFontAndSize(GROUP_DELIVERY_MESSAGE, "14px", "rgba(60, 60, 60, 1)");
		clickByJavaScriptExecutor(getWebDriver().findElement(EXPAND_SHOW_MORE_CART));
		Assert.assertTrue(getWebDriver().findElement(GROUP_DELIVERYMSG_TEXT).isDisplayed());
		Assert.assertEquals(Props.getProp("group_standard_delivery_message_inner_text"),getWebDriver().findElement(GROUP_DELIVERYMSG_TEXT).getText());
		clickByJavaScriptExecutor(getWebDriver().findElement(CONTRACT_SHOW_LESS_CART));
	}

	public void verifyGroupDeliveryMessageNotDisplayed()
	{
		List<WebElement> messagesList = getWebDriver().findElements(GROUP_DELIVERY_MESSAGE);
		Assert.assertTrue(messagesList.isEmpty());
	}

	public void verifyPromoCodeMessageDisplayed(String promoCodeType)
	{
		if (promoCodeType.equalsIgnoreCase("invalid"))
		{
			Assert.assertTrue(waitForAndGetElement(PROMO_CODE_ERROR_MSG, DEFAULT_TIMEOUT).getText().trim().equalsIgnoreCase("Promo code could not be redeemed"));
			Assert.assertNull(waitForAndGetElement(PROMO_CODE_APPLIED_COUPON, DEFAULT_TIMEOUT));
		}
		else if (promoCodeType.equalsIgnoreCase("valid"))
		{
			Assert.assertTrue(waitForAndGetElement(PROMO_CODE_APPLIED_COUPON, DEFAULT_TIMEOUT).isDisplayed());
			Assert.assertTrue(waitForAndGetElement(PROMO_CODE_ERROR_MSG, DEFAULT_TIMEOUT).getText().trim().equalsIgnoreCase(""));
		}
		pause(5000);
	}

	public WebElement totalAmountInSticky()
	{
		return waitForAndGetElement(TOTAL_AMOUNT_IN_STICKY, DEFAULT_TIMEOUT);
	}

	public WebElement deliveringToCountry()
	{
		return waitForAndGetElement(DELIVERING_TO_COUNTRY, DEFAULT_TIMEOUT);
	}

	public WebElement deliveryInformation()
	{
		return waitForAndGetElement(DELIVERY_INFORMATION, DEFAULT_TIMEOUT);
	}

	public WebElement discountedAmount()
	{
		return waitForAndGetElement(APPLIED_DISCOUNT_AMOUNT, DEFAULT_TIMEOUT);
	}

	public boolean outOfStockCartMessage()
	{
		System.out.println(getElementText(PROMISE_CALL_OOS_MESSAGE));
		System.out.println(Props.getProp("bag_oos_message"));
		return getElementText(PROMISE_CALL_OOS_MESSAGE).contains(Props.getProp("bag_oos_message"));
	}
	
	public boolean checkoutPageCollectionMethodDisabled()
	{
		pause(2000);
		return isElementPresent(COLLECTION_METHOD_DISABLED);
	}
	
	public boolean checkoutPageGetCollectionMethodDisabledErrorText(String collectionMethodDisabledErrorText)
	{
		pause(2000);
		return getWebDriver().findElement(COLLECTION_METHOD_DISABLED_Text_Message).getText().contentEquals(collectionMethodDisabledErrorText);
	}
	
	public void standardMessageAndCollectionDisabledText(String featureName)
	{		
	   try {
		    CheckoutAndPaymentsPage.setFeatureStatus("true", Props.getProp("featureName"));
	    	Assert.assertEquals(Props.getProp("group_standard_delivery_message"), getWebDriver().findElement(GROUP_DELIVERY_MESSAGE).getText());
	   		pdpPage.verifyFontAndSize(GROUP_DELIVERY_MESSAGE, "14px", "rgba(60, 60, 60, 1)");
	   		clickByJavaScriptExecutor(getWebDriver().findElement(EXPAND_SHOW_MORE_CART));
	   		Assert.assertTrue(getWebDriver().findElement(GROUP_DELIVERYMSG_TEXT).isDisplayed());
	   		Assert.assertEquals(Props.getProp("group_standard_delivery_message_inner_text"),getWebDriver().findElement(GROUP_DELIVERYMSG_TEXT).getText());
	   		clickByJavaScriptExecutor(getWebDriver().findElement(CONTRACT_SHOW_LESS_CART));	   		
	   		assertTrue(checkoutPageCollectionMethodDisabled());
	   		assertTrue(checkoutPageGetCollectionMethodDisabledErrorText(Props.getProp("collectionDisabledText")));
	   		
          }
           finally {
            	   CheckoutAndPaymentsPage.setFeatureStatus( "true", featureName);
          }		
	}
}


