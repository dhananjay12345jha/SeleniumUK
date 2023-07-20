package com.salmon.test.step_definitions.gui.then;

import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.models.gui.OrderConfirmationModel;
import com.salmon.test.models.gui.TrackMyOrderModel;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.services.PaymentReportService;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.*;
import static com.salmon.test.page_objects.gui.Features.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PurchasesThenStepDefs
{
	@Autowired
	private OrderConfirmationPage orderConfirmationPage;
	private OrderConfirmationModel orderConfirmationModel = new OrderConfirmationModel();
	@Autowired
	private TrackMyOrderPage trackMyOrderPage;
	@Autowired
	private CheckoutAndPaymentsPage checkoutAndPaymentsPage;
	private TrackMyOrderModel trackMyOrderModel =new TrackMyOrderModel();
	@Autowired
	private MyOrdersPage myOrdersPage;
	@Autowired
	private CheckOutPage checkOutPage;
	@Autowired
	private MyAccountPage myAccountPage;

//	public PurchasesThenStepDefs(final OrderConfirmationPage orderConfirmationPage,
//	                             final TrackMyOrderPage trackMyOrderPage,
//	                             final CheckoutAndPaymentsPage checkoutAndPaymentsPage,
//	                             final MyOrdersPage myOrdersPage,
//	                             final CheckOutPage checkOutPage,
//	                             final MyAccountPage myAccountPage)
//	{
//		this.orderConfirmationPage = orderConfirmationPage;
//		this.trackMyOrderPage = trackMyOrderPage;
//		this.checkoutAndPaymentsPage = checkoutAndPaymentsPage;
//		this.myOrdersPage = myOrdersPage;
//		this.checkOutPage = checkOutPage;
//		this.myAccountPage = myAccountPage;
//	}

	@Then("^Order has been successful$")
	public void orderHasBeenSuccessful()
	{
		assertTrue(orderConfirmationPage.isThankYouMessageDisplayed());
	}

	@And("^user clicks on Track My Order from order confirmation page$")
	public void userClicksOnTrackMyOrderFromOrderConfirmationPage() throws Exception
	{
		orderConfirmationPage.clickTrackMyOrder(orderConfirmationModel);
	}

	@And("^set fields for delivery and navigate to my account on mobile$")
	public void userClicksOnTrackMyOrderDeliveryFromOrderConfirmationPageOnMobile()
	{
		orderConfirmationPage.setFieldValuesAndNavigateToMyAccountForDeliveryMobile(orderConfirmationModel);
	}

	@And("^set fields for collection and navigate to my account on mobile$")
	public void setFieldsAndNavigateToMyAccountOnMobile()
	{
		orderConfirmationPage.setOrderNumber();
		trackMyOrderModel.setOrderNumber(orderConfirmationPage.getOrderNumberField());
		orderConfirmationPage.setFieldValuesAndNavigateToMyAccountMobile(orderConfirmationModel);
	}

	@And("^I get order number from the order confirmation page for collection$")
	public void iGetOrderNumberFromTheOrderConfirmationPageForCollection()
	{
		orderConfirmationPage.setOrderNumber();
		trackMyOrderModel.setOrderNumber(orderConfirmationPage.getOrderNumberField());
		orderConfirmationPage.clickTrackMyOrderCollection(orderConfirmationModel);
	}

	@Then("^check if correct order details are displayed$")
	public void checkIfCorrectOrderDetailsAreDisplayed() throws Exception
	{
		if (WebDriverHelper.PLATFORM.contains("macOS 10.14"))
		{
			assertEquals(orderConfirmationModel.getItemQuantity().trim(), trackMyOrderPage.getItemQuantity().trim());

		}
		else
		{
			assertEquals(orderConfirmationModel.getItemQuantity(), trackMyOrderPage.getItemQuantity());
		}
		assertOnDeliveryOrderConfirmation();
	}

	@Then("^check if correct order details are displayed on mobile$")
	public void checkIfCorrectOrderDetailsAreDisplayedOnMobile() throws Exception
	{
		assertOnDeliveryOrderConfirmation();
	}

	private void assertOnDeliveryOrderConfirmation()
	{
		assertTrue(trackMyOrderPage.getDeliveryDetails().contains(orderConfirmationModel.getDeliveryDetails()));
		assertEquals(orderConfirmationModel.getDeliveryAddress(), trackMyOrderPage.getDeliveryAddress());
		assertEquals(orderConfirmationModel.getItemTotalPrice(), trackMyOrderPage.getTotalPrice());
		assertEquals(orderConfirmationModel.getCardInfo(), trackMyOrderPage.getCardInfo());
		assertEquals(orderConfirmationModel.getProductImage(), trackMyOrderPage.getProductThumbnail());
		assertTrue(orderConfirmationModel.getProductName().contains(trackMyOrderPage.getProductName()));
	}

	private void assertOnCollectionOrderConfirmation()
	{
		assertEquals(orderConfirmationModel.getItemTotalPrice(), trackMyOrderPage.getTotalPrice());
		assertEquals(orderConfirmationModel.getCardInfo(), trackMyOrderPage.getCardInfo());
		assertEquals(orderConfirmationModel.getProductImage(), trackMyOrderPage.getProductThumbnail());
		assertTrue(orderConfirmationModel.getProductName().contains(trackMyOrderPage.getProductName()));
	}

	@And("^user makes a purchase with a different mode$")
	public void userMakesAPurchaseWithADifferentMode()
	{
		checkOutPage.pause(2000);
		checkoutAndPaymentsPage.clickDaytimeDeliveryMode();
		checkoutAndPaymentsPage.clickFirstAvailableDate();
		checkOutPage.enterMandatoryMobileField();
	}

	@And("user places order")
	public void userPlacesOrder()
	{
		checkoutAndPaymentsPage.clickPlaceOrderButton();
	}

	@Then("^check if correct collection order details are displayed on mobile$")
	public void checkIfCorrectCollectionOrderDetailsAreDisplayedOnMobile()
	{
		assertOnCollectionOrderConfirmation();
	}

	@And("^payment with card \"([^\"]*)\" exists$")
	public void paymentWithCardExists(String paymentNumber)
	{
		assertTrue(orderConfirmationPage.isPaymentCardPresent(paymentNumber));
	}

	@Then("^check the order status  \"([^\"]*)\" is in track my order page$")
	public void checkTheOrderStatusIsInTrackMyOrderPage(String orderStatus) throws Throwable
	{
		assertEquals(orderStatus, "Confirmed");
	}

	@And("^refund history \"([^\"]*)\" section exits$")
	public void refundHistorySectionExits(Boolean bool) throws Throwable
	{
		//adding this if statement as the simulator on aws is slower compared to qa-rd
		if (trackMyOrderPage.getItemStatus().contains("Dispatched"))
		{
			if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
					WebDriverHelper.DEVICE_NAME.contains("iPad") ||
					WebDriverHelper.DEVICE_NAME.contains("Samsung") ||
					BROWSER.contains("emulator"))
			{
				myOrdersPage.pause(10000);
				myOrdersPage.refresh();
			}
		else
			{
				myOrdersPage.pause(30000);
				myOrdersPage.refresh();
			}
		}
		if (trackMyOrderPage.getItemStatus().contains("Returned") && !trackMyOrderPage.isRefundSectionDisplayed())
		{
			int count = 0;
			while (count <= 4)
			{
				myOrdersPage.pause(30000);
				myOrdersPage.refresh();
				count++;
			}
			trackMyOrderPage.refresh();
		}
		assertEquals(bool, trackMyOrderPage.isRefundSectionDisplayed());
	}

	@And("^I get order number from the order confirmation page$")
	public void iGetOrderNumberFromTheOrderConfirmationPage()
	{
		orderConfirmationPage.setOrderNumber();
		trackMyOrderModel.setOrderNumber(orderConfirmationPage.getOrderNumberField());
		if (IS_MOBILE)
		{
			orderConfirmationPage.setFieldValuesAndNavigateToMyAccountForDeliveryMobile(orderConfirmationModel);
		}
		else
		{
			orderConfirmationPage.clickTrackMyOrder(orderConfirmationModel);
		}
	}

	@And("^I get order number from the order confirmation page and select order with status \"([^\"]*)\" and wait for (\\d+) seconds$")
	public void iGetOrderNumberFromTheOrderConfirmationPageAndSelectOrderWithStatus(String status,
	                                                                                int milliSeconds) throws Throwable
	{
		orderConfirmationPage.pause(milliSeconds);
		orderConfirmationPage.setOrderNumber();
		orderConfirmationPage.clickTrackMyOrder(orderConfirmationModel);
		myOrdersPage.selectViewOrder(orderConfirmationPage.getOrderNumberField(), status, trackMyOrderModel);
	}

	@And("^I get order number from the order confirmation page and select order with status \"([^\"]*)\" and wait for (\\d+) seconds on mobile$")
	public void iGetOrderNumberFromTheOrderConfirmationPageAndSelectOrderWithStatusOnMobile(String status,
	                                                                                        int milliSeconds) throws Throwable
	{
		orderConfirmationPage.pause(milliSeconds);
		orderConfirmationPage.setOrderNumber();
		trackMyOrderModel.setOrderNumber(orderConfirmationPage.getOrderNumberField());
		orderConfirmationPage.setFieldValuesAndNavigateToMyAccountForDeliveryMobile(orderConfirmationModel);
		myOrdersPage.clickViewOrder();
	}

	@Then("^also verify other order details in items section with status \"([^\"]*)\" and \"([^\"]*)\"$")
	public void also_verify_other_order_details_in_items_section_with_status(String orderStatus,
	                                                                         String deliveryType) throws Exception
	{
		Assert.assertTrue(trackMyOrderPage.isCorrectItemStatusDisplayed(orderStatus, deliveryType));
		Assert.assertTrue(trackMyOrderPage.isCorrectOrderNumberDisplayed(trackMyOrderModel));
	}

	@And("^completed checkout with collection details \"([^\"]*)\" and \"([^\"]*)\"$")
	public void completedCheckoutWithCollectionDetailsAnd(String firstName, String lastName) throws Throwable
	{
		checkoutAndPaymentsPage.selectDeliveryOrCollectionOption("COLLECTION");
		checkoutAndPaymentsPage.selectCollectionPointFromTheSearchDropdown();
		checkOutPage.collectionDetails();
		checkOutPage.continueToPaymentSection();
		checkOutPage.updateCollectionDetails(firstName, lastName);
		checkOutPage.continueToPaymentSection();
		checkOutPage.enterMandatoryMobileField();
		checkOutPage.continueToPaymentSection();
		checkoutAndPaymentsPage.clickCreditDebitCardButton();
		checkoutAndPaymentsPage.addBillingAddress("London");
		checkoutAndPaymentsPage.fillCardDetails();
		checkoutAndPaymentsPage.placeOrder();
	}

	@Then("^verify order details in items section with item status \"([^\"]*)\", delivery details \"([^\"]*)\" and other order details$")
	public void verify_order_details_in_items_section_with_item_status_delivery_details_and_other_order_details(String orderStatus,
	                                                                                                            String deliveryType) throws Exception
	{
		Assert.assertTrue(trackMyOrderPage.isCorrectCollectionNameDisplayed(orderStatus, deliveryType));
		Assert.assertTrue(trackMyOrderPage.isCorrectOrderNumberDisplayed(trackMyOrderModel));
		assertEquals("Collection Details", trackMyOrderPage.getCollectionDetails().getText());
		Assert.assertTrue(trackMyOrderPage.getOpeningHours().isDisplayed());
		Assert.assertTrue(trackMyOrderPage.getOpeningHoursList().isDisplayed());
		Assert.assertTrue(trackMyOrderPage.getLocationPin().isDisplayed());
		Assert.assertTrue(trackMyOrderPage.getMapComponent().isDisplayed());
	}


	@And("^also verify other order details in items section with status \"([^\"]*)\"$")
	public void alsoVerifyOtherOrderDetailsInItemsSectionWithStatus(String status) throws Throwable
	{
		String itemStatus;
		if (IS_MOBILE)
		{
			itemStatus = "Dispatched" + "3 " + status;
		}
		else
		{
			itemStatus = "Dispatched" + '\n' + "3 " + status;
		}
		Assert.assertEquals(trackMyOrderPage.getItemStatus(), itemStatus);
	}

	@And("^select order with status \"([^\"]*)\"$")
	public void selectOrderWithStatus(String status)
	{
		int count = 0;
		while (count <= 50 && !myOrdersPage.getOrderStatus().contains(status))
		{
			myOrdersPage.pause(30000);
			myOrdersPage.refresh();
			count++;
		}
		myOrdersPage.selectViewOrder(orderConfirmationPage.getOrderNumberField(), status, trackMyOrderModel);

	}

	@And("^select order with status \"([^\"]*)\" order status with \"([^\"]*)\" link$")
	public void selectOrderWithStatusOMS(String status, String orderViewLink)
	{
		int count = 0;
		while (count <= 50 && !myOrdersPage.getOrderStatusOms().contains(status))
		{
			myOrdersPage.pause(30000);
			myOrdersPage.refresh();
			count++;
		}
		myOrdersPage.selectOrderLinkOMS(orderConfirmationPage.getOrderNumberField(), status, trackMyOrderModel, orderViewLink);
	}
	@And("^select order with status \"([^\"]*)\" order status with \"([^\"]*)\" link mobile$")
	public void selectOrderWithStatusOMSMobile(String status, String orderViewLink)
	{
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad") ||
				WebDriverHelper.DEVICE_NAME.contains("Samsung") ||
				BROWSER.contains("emulator"))
		{
			myAccountPage.clickMyOrders();
		}
		int count = 0;
		while (count <= 60 && !myOrdersPage.getOrderStatusOms().contains(status))
		{
			myOrdersPage.pause(10000);
			myOrdersPage.refresh();
			count++;
		}
		myOrdersPage.selectOrderLinkOMSMobile(orderViewLink);
	}

	@And("^wait till order with status \"([^\"]*)\"$")
	public void waitTillOrderWithStatus(String status)
	{
		int count = 0;
		while (count <= 50 && !myOrdersPage.getOrderStatus().contains(status))
		{
			myOrdersPage.pause(30000);
			myOrdersPage.refresh();
			count++;
		}
	}


	@And("^select order with status \"([^\"]*)\" on mobile$")
	public void selectOrderWithStatusOnMobile(String status) throws Throwable
	{
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad") ||
				WebDriverHelper.DEVICE_NAME.contains("Samsung") ||
				BROWSER.contains("emulator"))
		{
			myAccountPage.clickMyOrders();
		}
		int count = 0;
		while (count <= 11 && !myOrdersPage.getOrderStatus().contains(status))
		{
			myOrdersPage.pause(10000);
			myOrdersPage.refresh();
			count++;
		}
		myOrdersPage.clickViewOrder();
	}
	@And("^select order with status \"([^\"]*)\" on oms mobile$")
	public void selectOrderWithStatusOnMobileOms(String status) throws Throwable
	{
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad") ||
				WebDriverHelper.DEVICE_NAME.contains("Samsung") ||
				BROWSER.contains("emulator"))
		{
			myAccountPage.clickMyOrders();
		}
		int count = 0;
		while (count <= 11 && !myOrdersPage.getOrderStatusOms().contains(status))
		{
			myOrdersPage.pause(10000);
			myOrdersPage.refresh();
			count++;
		}
		myOrdersPage.clickViewOrderOms();
	}
	@And("^user choose delivery option \"([^\"]*)\" mode$")
	public void user_choose_delivery_option_mode(String mode)
	{
		checkoutAndPaymentsPage.pause(2000);
		checkoutAndPaymentsPage.chooseDeliveryMode(mode);
		checkoutAndPaymentsPage.clickFirstAvailableDate();
	}

	@Then("^user verifies if selected delivery mode has been highlighted with appropriate \"([^\"]*)\"$")
	public void user_verifies_if_selected_delivery_mode_has_been_highlighted_with_appropriate(String deliverytext)
	{
		assertTrue(checkoutAndPaymentsPage.getTextOfDeliveryMessage().contains(deliverytext));
	}

	@Then("^user verifies if selected delivery mode has been highlighted with Standard delivery description$")
	public void user_verifies_if_selected_delivery_mode_has_been_highlighted_with_Standard_delivery_description()
	{
		assertTrue(checkoutAndPaymentsPage.getTextOfStandardDeliveryMessage().contains("Standard"));
	}

	@And("^I record my \"([^\"]*)\" order number with popup=\"([^\"]*)\"$")
	public void iRecordMyOrderNumberWithPopup(final String card, final String popup)
	{
		final String orderNumber;
		final String success;
		if (orderConfirmationPage.isOrderConfirmationVisible())
		{
			orderNumber = orderConfirmationPage.getOrderNumber();
			success = "YES";
		}
		else if (checkoutAndPaymentsPage.isCheckoutVisible())
		{
			orderNumber = checkoutAndPaymentsPage.getOrderNumber();
			success = "NO";
		}
		else
		{
			orderNumber = "ERROR";
			success = "NO";
		}

		PaymentReportService.addCardToReport(card, popup, success, orderNumber);
	}
}
