package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;

public class DeliveryPassPage extends PageObject
{

	private Logger log = LogManager.getLogger(DeliveryPassPage.class.getName());
	private static final By DELIVERY_PASS_SIGN_UP_LABEL = By.cssSelector(".show-on-desktop>center>p:nth-of-type(1)>span");
	private static final By DELIVERY_PASS_PAGE_DESCRIPTION = By.cssSelector(".show-on-desktop>center>p:nth-of-type(2)>span");
	private static final By DELIVERY_PASS_PRICE_LABEL = By.cssSelector(".show-on-desktop>center>h2>span>strong");
	private static final By DELIVERY_PASS_SIGN_UP_LABEL_MOBILE = By.cssSelector(".show-on-mobile>center>p:nth-of-type(1)>span");
	private static final By DELIVERY_PASS_PAGE_DESCRIPTION_MOBILE = By.cssSelector(".show-on-mobile>center>p:nth-of-type(2)>span");
	private static final By DELIVERY_PASS_PRICE_LABEL_MOBILE = By.cssSelector(".show-on-mobile>center>h2>span>strong");
	private static final By ADD_TO_BAG_BUTTON = By.cssSelector(".button.button--primary.delivery-pass__add-product-cta");
	private static final By UK_ONLU_LABEL = By.cssSelector("div:nth-of-type(1)>.cms-paragraph>p");
	private static final By DELIVERY_PASS_TERMS_AND_CONDITIONS_LABEL = By.cssSelector("#navigationNode_000001JK > div > button");
	private final String DELIVERY_PASS_PAGE = "/subscription/delivery-pass/51300069999";
	private final By ADD_TO_BAG = By.className("delivery-pass__add-product-cta");
	private final By REGISTER = By.xpath("//a[contains(@class, 'delivery-pass__register')]");
	private final By SIGN_IN = By.className("delivery-pass__content-spacing");
	private final By DELIVERY_PASS_PRODUCT_DESCRIPTION = By.className("delivery-pass__wrapper");
	private final By DELIVERY_PASS_PRODUCT_ADDED = By.className("checkout__login-box--deliverypassadded");

	private final By ITEMS_IN_BAG = By.className("bag-item");
	private final By ITEM_COLOUR = By.className("bag-item__col--colour");
	private final By ITEM_SIZE = By.className("bag-item__col--size");
	private final By ITEM_QUANTITY = By.className("bag-item__col--quantity");
	private final By ITEM_PRICE = By.className("bag-item__price");

	private final By ORDER_SUMMARY = By.className("order-summary__row");
	private final By ORDER_SUMMARY_VALUE = By.className("order-summary__value");

	public List<WebElement> bagItems()
	{
		return webDriver.findElements(ITEMS_IN_BAG);
	}

	public List<WebElement> itemColourList()
	{
		return webDriver.findElements(ITEM_COLOUR);
	}

	public List<WebElement> itemSizeList()
	{
		return webDriver.findElements(ITEM_SIZE);
	}

	public List<WebElement> itemQuantityList()
	{
		return webDriver.findElements(ITEM_QUANTITY);
	}

	public List<WebElement> itemPriceList()
	{
		return webDriver.findElements(ITEM_PRICE);
	}

	public boolean deliveryPassInBag(List<WebElement> attributeList)
	{
		boolean isPresent = false;
		for (int i = 0; i < bagItems().size(); i++)
		{
			if (bagItems().get(i).getText().toLowerCase().contains("pass") && attributeList.get(i).getText().isEmpty())
			{
				isPresent = true;
				break;
			}
		}
		return isPresent;
	}

	public WebElement deliveryCost()
	{
		waitForElementVisible(ORDER_SUMMARY, DEFAULT_TIMEOUT);
		return webDriver.findElements(ORDER_SUMMARY).get(2).findElement(ORDER_SUMMARY_VALUE);
	}

	public void goToDeliveryPassPage()
	{
		pause(3000);
		webDriver.get(UrlBuilder.getWebsiteUrl() + "/uk" + DELIVERY_PASS_PAGE);
		waitForPageLoad();
	}

	public void gotoShoppingBag()
	{
		webDriver.get(UrlBuilder.getWebsiteUrl() + "/cart");
		waitForPageLoad();
	}

	public WebElement delivery_pass_wrapper()
	{
		waitForElementVisible(DELIVERY_PASS_PRODUCT_DESCRIPTION, DEFAULT_TIMEOUT);
		return webDriver.findElement(DELIVERY_PASS_PRODUCT_DESCRIPTION);
	}

	public WebElement add_to_bag_button()
	{
		waitForAndGetElement(ADD_TO_BAG, DEFAULT_TIMEOUT);
		return webDriver.findElement(ADD_TO_BAG);
	}

	public WebElement added()
	{
		waitForElementVisible(DELIVERY_PASS_PRODUCT_ADDED, DEFAULT_TIMEOUT);
		return webDriver.findElement(DELIVERY_PASS_PRODUCT_ADDED);
	}

	public WebElement register()
	{
		return waitForExpectedElement(REGISTER);
	}

	public WebElement signIn()
	{
		return webDriver.findElement(SIGN_IN);
	}

	public List<String> getDeliveryPassLabels()
	{
		waitForAndGetElement(webDriver, DELIVERY_PASS_PRODUCT_DESCRIPTION, DEFAULT_TIMEOUT);
		List<String> deliveryPassLabels = new ArrayList<>();
		if (IS_MOBILE)
		{
			deliveryPassLabels.add(webDriver.findElement(DELIVERY_PASS_SIGN_UP_LABEL_MOBILE).getText());
			deliveryPassLabels.add(webDriver.findElement(DELIVERY_PASS_PAGE_DESCRIPTION_MOBILE).getText().replaceAll("[\\r\\n]+", " "));
			deliveryPassLabels.add(webDriver.findElement(DELIVERY_PASS_PRICE_LABEL_MOBILE).getText());
		}
		else
		{
			deliveryPassLabels.add(webDriver.findElement(DELIVERY_PASS_SIGN_UP_LABEL).getText());
			deliveryPassLabels.add(webDriver.findElement(DELIVERY_PASS_PAGE_DESCRIPTION).getText().replaceAll("[\\r\\n]+", " "));
			deliveryPassLabels.add(webDriver.findElement(DELIVERY_PASS_PRICE_LABEL).getText());
		}
		deliveryPassLabels.add(webDriver.findElement(ADD_TO_BAG_BUTTON).getText());
		deliveryPassLabels.add(webDriver.findElement(UK_ONLU_LABEL).getText().trim());
		deliveryPassLabels.add(webDriver.findElement(DELIVERY_PASS_TERMS_AND_CONDITIONS_LABEL).getText());
		return deliveryPassLabels;
	}
}
