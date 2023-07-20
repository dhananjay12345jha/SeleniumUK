package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.stream.Collectors;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

/**
 * Created by sreekanth.bongunuri on 21/03/16.
 */


public class PromotionsPage extends PageObject
{

    private Logger log = LogManager.getLogger(PromotionsPage.class.getName());
    public final By percentageDiscountMessage = By.cssSelector("a[class='promotion__highlight ng-scope']");
    private final By havePromoCodeLabel = By.xpath("//*[@for='bag-footer__promo-code']");
    private final By promoCodeTextBox = By.id("bag-footer__promo-code");
    private final By applyPromoCodeButton = By.xpath("//button[contains(text(),'Apply')]");
    //private final By percentageDiscountMessage = By.cssSelector(".coupon-description>span");
    private final By priceDiscountMessage = By.cssSelector("span[data-ng-bind-html*='promotion.description']");
    private final By promoReceivedMessage = By.cssSelector("span[data-translate*='messages.received']");
    private final By REMOVE_PROMO_LINK = By.cssSelector(".coupon-remove.link");
    private final By staffdiscountmessage = By.cssSelector(".order-summary__subscriptionitems--messages");

	public WebElement staffdiscountmessage()
	{
		return getWebDriver().findElement(staffdiscountmessage);
	}

	public WebElement promoCodeTextBox()
	{
		return waitForExpectedElement(promoCodeTextBox);
	}

	public WebElement havePromoCodeLabel() {
		return waitForExpectedElement(havePromoCodeLabel);
	}

	public WebElement applyPromoCodeButton()
	{
		scrollByCoordinates(120);
		return waitForExpectedElement(applyPromoCodeButton);
	}

	public String priceDiscountMessage()
	{
		waitForExpectedElement(priceDiscountMessage);
		String msg = webDriver.findElements(priceDiscountMessage).stream().map(WebElement::getText).collect(Collectors.joining());
		return msg;
	}

	public WebElement promoReceivedMessage()
	{
		return waitForExpectedElement(promoReceivedMessage);
	}

	public void clickRemovePromoLink()
	{
		scrollByCoordinates(120);
		waitForAndGetElement(REMOVE_PROMO_LINK, DEFAULT_TIMEOUT).click();
	}
}
