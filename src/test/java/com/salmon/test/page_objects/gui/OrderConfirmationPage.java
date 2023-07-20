package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;

import com.salmon.test.models.gui.OrderConfirmationModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;


public class OrderConfirmationPage extends PageObject
{
	private static final Logger log = LogManager.getLogger(OrderConfirmationPage.class.getName());

	private static final By THANK_YOU_MESSAGE = By.className("confirmation-header__title");
	private static final By trackMyOrder = By.cssSelector("div[data-ng-show*=isLoggedIn]>li>a[href*=orders]");
	private static final By deliveryAddress = By.cssSelector(".confirmation-order__column>.list.list--unordered>li");
	private static final By deliveryDetails = By.className("confirmation-order__arrival");
	private static final By bagItemQuantity = By.className("bag-item__quantity");
	private static final By bagItemPrice = By.className("bag-item__price");
	private static final By totalPrice = By.cssSelector(".confirmation-total-list-total>.confirmation-total-list__detail");
	private static final By billingCardInfo = By.cssSelector(".billing-info__card-info.billing-info__card-info--card>span");
	private static final By BILLING_CARDS = By.cssSelector(".billing-info__card-info>span");
	private static final By productName = By.className("bag-item__prod-name");
	private static final By productImage = By.cssSelector(".bag-item__image-link>img");
	private static final By orderNumber = By.className("confirmation-order__title");
	public static final By ORDER_CONFIRMATION_CONTAINER = By.className("checkout-confirmation-section");
	String orderNumberField = "";
	private final HomePage homePage = new HomePage();

	public boolean isThankYouMessageDisplayed()
	{
		try
		{
			pause(2000);
			waitForElementVisible(THANK_YOU_MESSAGE, 20);
			if (webDriver.findElement(THANK_YOU_MESSAGE).getText().contains("Thanks for your order"))
			{
				return true;
			}
			else if (webDriver.findElement(THANK_YOU_MESSAGE)
					.getText()
					.contains("Nous vous remercions pour votre commande"))
			{
				return true;
			}
			else if (webDriver.findElement(THANK_YOU_MESSAGE).getText().contains("Vielen Dank für deine Bestellung"))
			{
				return true;
			}
		}
		catch (final NoSuchElementException e)
		{
			log.error(e);
		}
		return false;
	}

	public void clickTrackMyOrder(OrderConfirmationModel orderConfirmationModel)
	{
		orderConfirmationModel.setDeliveryAddress(waitForExpectedElement(deliveryAddress).getText());
		orderConfirmationModel.setDeliveryDetails(waitForExpectedElement(deliveryDetails).getText());
		orderConfirmationModel.setItemQuantity(waitForExpectedElement(bagItemQuantity).getText());
		setCommonFields(orderConfirmationModel);
		homePage.clickTrackMyOrderFromHeader();
	}

	public void clickTrackMyOrderCollection(OrderConfirmationModel orderConfirmationModel)
	{
		orderConfirmationModel.setItemQuantity(waitForExpectedElement(bagItemQuantity).getText());
		setCommonFields(orderConfirmationModel);
		homePage.clickTrackMyOrderFromHeader();
	}

	public void setFieldValuesAndNavigateToMyAccountMobile(OrderConfirmationModel orderConfirmationModel)
	{
		setCommonFields(orderConfirmationModel);
		homePage.navigateToMyAccountFromHeaderOnMobile();
	}

	public void setFieldValuesAndNavigateToMyAccountForDeliveryMobile(OrderConfirmationModel orderConfirmationModel)
	{
		orderConfirmationModel.setDeliveryAddress(waitForExpectedElement(deliveryAddress).getText());
		orderConfirmationModel.setDeliveryDetails(waitForExpectedElement(deliveryDetails).getText());
		setCommonFields(orderConfirmationModel);
		homePage.navigateToMyAccountFromHeaderOnMobile();
	}

	public void setCommonFields(OrderConfirmationModel orderConfirmationModel)
	{
		orderConfirmationModel.setItemPrice(waitForExpectedElement(bagItemPrice).getText());
		orderConfirmationModel.setItemTotalPrice(waitForExpectedElement(totalPrice).getText());
		orderConfirmationModel.setCardInfo(waitForExpectedElement(billingCardInfo).getText());
		orderConfirmationModel.setProductImage(waitForExpectedElement(productImage).getAttribute("title"));
		orderConfirmationModel.setProductName(waitForExpectedElement(productName).getText());
	}

	public void setOrderNumber()
	{
		orderNumberField = getOrderNumber();
	}

	public String getOrderNumber()
	{
		return waitForExpectedElement(orderNumber, 30).getText().substring(6).split("\n")[0].trim();
	}

	public String getOrderNumberField()
	{
		return orderNumberField;
	}

	public boolean isPaymentCardPresent(String cardNumber)
	{
		List<WebElement> paymentCards = getWebDriver().findElements(BILLING_CARDS);
		return paymentCards.stream()
				.map(WebElement::getText)
				.anyMatch(elementText -> elementText.contains(cardNumber));
	}

	public boolean isOrderConfirmationVisible()
	{
		return isElementVisible(ORDER_CONFIRMATION_CONTAINER, 5);
	}
}
