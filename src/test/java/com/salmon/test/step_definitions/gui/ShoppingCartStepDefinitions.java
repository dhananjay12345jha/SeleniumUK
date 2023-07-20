package com.salmon.test.step_definitions.gui;

import java.util.List;
import java.util.regex.Pattern;


import com.salmon.test.page_objects.gui.CheckOutPage;
import com.salmon.test.page_objects.gui.CheckoutAndPaymentsPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.LoginPage;

import com.salmon.test.page_objects.gui.MyBagPage;
import com.salmon.test.page_objects.gui.MyOrdersPage;
import com.salmon.test.page_objects.gui.PdpPage;
import com.salmon.test.page_objects.gui.StoreLocatorPage;
import com.salmon.test.page_objects.gui.TrackMyOrderPage;
import com.salmon.test.page_objects.gui.WishListPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.salmon.test.framework.WebElementNotFoundException;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.gui.CheckoutModel;
import com.salmon.test.page_objects.gui.HomePageOld;
import com.salmon.test.page_objects.gui.PudoSection;
import com.salmon.test.page_objects.gui.RandomGenerator;
import com.salmon.test.properties.AlertsProperties;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

@Slf4j
public class ShoppingCartStepDefinitions
{
	private static final double DELTA = 0.01;
	public static String productId;
	public static String department;
	@Autowired
	private HomePageOld homePage;
	@Autowired
	private LoginPage loginPage;
	@Autowired
	private CheckOutPage checkOutPage;
	@Autowired
	private WishListPage wishListPage;
	@Autowired
	private StoreLocatorPage storeLocatorPage;
	@Autowired
	private ProductCodeProvider productCodeProvider;
	@Autowired
	private PudoSection pudoSection;
	@Autowired
	private CheckoutAndPaymentsPage checkoutAndPaymentsPage;
	@Autowired
	private MyBagPage myBagPage;
	@Autowired
	private TrackMyOrderPage trackMyOrderPage;
	@Autowired
	private PdpPage pdpPage;
	@Autowired
	private CheckoutModel checkoutModel;

	private String changedProductCode;
	private double productPrice;
	private String generatedName;

	private String generatedEmail;
	private String orderNumber;

	private HomePage homePageNew;
	private MyOrdersPage myOrdersPage;

//	public ShoppingCartStepDefinitions(HomePageOld homePageOld,
//									   LoginPage loginPage,
//									   CheckOutPage checkOutPage,
//									   WishListPage wishListPage,
//									   StoreLocatorPage storeLocatorPage,
//									   PudoSection pudoSection,
//									   CheckoutAndPaymentsPage checkoutAndPaymentsPage,
//									   MyBagPage myBagPage,
//									   TrackMyOrderPage trackMyOrderPage,
//									   HomePage homePageNew,
//									   MyOrdersPage myOrdersPage,
//									   PdpPage pdpPage,
//									   CheckoutModel checkoutModel
//	)
//	{
//		this.homePage = homePageOld;
//		this.loginPage = loginPage;
//		this.wishListPage = wishListPage;
//		this.checkOutPage = checkOutPage;
//		this.storeLocatorPage = storeLocatorPage;
//		this.pudoSection = pudoSection;
//		this.checkoutAndPaymentsPage = checkoutAndPaymentsPage;
//		this.myBagPage = myBagPage;
//		this.trackMyOrderPage = trackMyOrderPage;
//		this.homePageNew = homePageNew;
//		this.myOrdersPage = myOrdersPage;
//		this.checkoutModel = checkoutModel;
//		this.pdpPage = pdpPage;
//	}


	@Then("^I am shown a breakdown of costs to be applied to my purchase$")
	public void I_am_shown_a_breakdown_of_costs_to_be_applied_to_my_purchase()
	{
		double subtotal = checkOutPage.getSubtotal();
		double totalCost = checkOutPage.getDeliveryTotal();
		assertEquals(subtotal, totalCost, DELTA);

	}

	@Then("^I can see a cart ID displayed$")
	public void I_can_see_a_cart_ID_displayed()
	{
		Pattern p = Pattern.compile("Bag ID [EN]: " + "HY\\d{8}");
	}


	@And("^I enter valid login details \"([^\"]*)\" and \"([^\"]*)\" in the log in form$")
	public void I_enter_valid_login_details_and_in_the_log_in_form(String username, String password)
	{
		loginPage.usernameFieldLocator().sendKeys(username);
		loginPage.passwordFieldLocator().sendKeys(password);
	}

	@And("^I submit the form$")
	public void I_submit_the_form()
	{
		loginPage.loginButtonLocator().click();

	}

	@And("^click continue to payment button$")
	public void click_continue_to_payment_button() throws InterruptedException
	{
		checkOutPage.gotToBillingAddressSection();

		if (checkOutPage.continueToPaymentButtonPresent())
		{
			checkOutPage.continueToPaymentButton().click();
		}
		checkoutAndPaymentsPage.clickCreditDebitCardButton();
	}

//    @Then("^click on the bag icon$")
//    public void click_on_the_bag_icon() {
//        checkOutPage.basketIcon().click();
//    }

	@And("^I view my cart Quick View side panel$")
	public void I_view_my_cart_Quick_View_side_panel() throws Throwable
	{
		checkOutPage.basketIcon().click();
	}

	@And("^continue as guest$")
	public void continue_as_guest()
	{
		checkOutPage.guestEmailAddress().sendKeys(RandomGenerator.randomEmailAddress(5));
		checkOutPage.checkOutAsGuestButton().click();
	}

	@And("^click checkout$")
	public void click_checkout()
	{
		checkOutPage.clickPlaceOrderButton();
	}

	@Then("^no cart ID is displayed$")
	public void no_cart_ID_is_displayed()
	{
		checkOutPage.switchToCheckoutIframe();
		assertFalse(checkOutPage.getWebDriver().findElement(By.cssSelector(".ng-scope")).getText().contains("Bag ID:"));
	}

	@Then("^I CANNOT see a cart ID displayed$")
	public void I_CANNOT_see_a_cart_ID_displayed()
	{
		assertTrue(checkOutPage.shoppingCartEmptyMessage()
				.getText()
				.contains("Your bag is currently empty. Get shopping..."));
	}

	@When("^I view my cart, as Quick View or full view$")
	public void I_view_my_cart_as_Quick_View_or_full_view()
	{
		checkOutPage.basketIcon().click();
		checkOutPage.switchToCheckoutIframe();
	}

	@Then("^I am shown an order subtotal$")
	public void I_am_shown_an_order_subtotal()
	{
		assertTrue(checkOutPage.priceSummary().isDisplayed());
	}

	@And("^the subtotal does not include any delivery costs$")
	public void the_subtotal_does_not_include_any_delivery_costs()
	{

		assertEquals(this.productPrice, checkOutPage.getTheProductPriceFromQuickcart(), DELTA);
	}

	@And("^I change the (?:quantity to |)\"([^\"]*)\" (?:for|of) a line item (\\d+)$")
	public void I_change_the_quantity_of_a_line_item(String quantity, int lineItem) throws InterruptedException
	{
		checkOutPage.selectQuantityOfLineItem(quantity, lineItem);
		Thread.sleep(1000);
	}

	@And("^quantity (?:for|of) a line item (\\d+) is (\\d+)$")
	public void Get_quantity_of_a_line_item(int lineItem, int quantity) throws InterruptedException
	{
		Assert.assertEquals(quantity, Integer.parseInt(checkOutPage.getQuantityOfLineItem(lineItem).replaceAll("[^0-9]", "")));
	}

	@Then("^cart limit error message (is|is not) displayed$")
	public void cartLimitErrorMessageDisplayedOrNot(String displayedOrNot)
	{
		pdpPage.pause(4000);
		if (displayedOrNot.equals("is"))
		{
			Assert.assertTrue(myBagPage.getCartLimitMessage().contains(AlertsProperties.cartLimitMessage()));
		}
		else if (displayedOrNot.equals("is not"))
		{
			assertNull(myBagPage.getCartLimitMessage());
		}
		else
		{
			Assert.fail("Cart Limit Message Verification Failed.");
		}
	}

	@And("^change the \"([^\"]*)\" of the product$")
	public void change_the_of_the_product(String size)
	{
		checkOutPage.selectSize(size);
	}

	@Then("^the additional products will be committed to my cart with the same (?:quantity: |)\"([^\"]*)\"$")
	public void the_additional_products_will_be_committed_to_my_cart_with_the_same(String quantity) throws InterruptedException
	{
		checkOutPage.pause(4000);
		assertThat(Integer.parseInt(checkOutPage.totalItemsInTheBasket()
				.getText())).isEqualTo(Integer.valueOf(quantity));
	}

	@Then("^the additional products will be committed to my cart with the same quantity: \"([^\"]*)\" on mobile$")
	public void theAdditionalProductsWillBeCommittedToMyCartWithTheSameQuantityOnMobile(String quantity)
	{
		checkOutPage.pause(1500);
		assertThat(Integer.parseInt(checkOutPage.totalItemsInTheBasketMobile().getText())).isEqualTo(Integer.valueOf(
				quantity));
	}

	@When("^click on a product with limited stock$")
	public void click_on_a_product_with_limited_stock()
	{
		checkOutPage.limitedQuantityProduct().click();
	}

	@Then("^increase the product quantity before adding to the bag$")
	public void increase_the_prodcut_quantity_before_adding_to_the_bag()
	{
		checkOutPage.addToCartInput().clear();
		checkOutPage.addToCartInput().sendKeys("4");
	}

	@Then("^I am only shown the quantity (\\d) in the picker$")
	public void I_am_only_shown_the_quantity_available_in_the_picker2(int qty)
	{
		assertThat(checkOutPage.returnTotalNumberOfSizes()).isEqualTo(qty);
	}

	@Then("^I am only shown the quantity (\\d) in the picker on mobile$")
	public void I_am_only_shown_the_quantity_available_in_the_picker_on_mobile(int qty)
	{
		assertThat(checkOutPage.returnTotalNumberOfSizesOnMobile()).isEqualTo(qty);
	}


	@Then("^Total amount of products in basket is \"([^\"]*)\"$")
	public void check_total_amount_in_basket(String totalBasketAmount)
	{
		checkOutPage.pause(2000);
		if (IS_MOBILE)
		{
			assertThat(checkOutPage.totalItemsInTheBasketMobile().getText()).isEqualTo(totalBasketAmount);
		}
		else
		{
			checkOutPage.basketIcon().click();
			assertThat(checkOutPage.totalItemsInTheBasket().getText()).isEqualTo(totalBasketAmount);
		}
	}

	@Then("^the cart I built up as a guest before I signed up is maintained, and merged with the cart stored against my account$")
	public void the_cart_I_built_up_as_a_guest_before_I_signed_up_is_maintained_and_merged_with_the_cart_stored_against_my_account()
	{
		assertTrue(checkOutPage.productRemoveLink().isDisplayed());
		assertTrue(myBagPage.addProductAndReturnName());
	}

	@Then("^the delivery \"([^\"]*)\" will be preset to the country I have been browsing the site in$")
	public void the_delivery_will_be_preset_to_the_country_I_have_been_browsing_the_site_in(String country)
	{
		assertTrue(checkOutPage.deliveringCountry().getText().contains(country));
	}

	@Then("^the delivery \"([^\"]*)\" will be preset in the checkout to the country I have been browsing the site in$")
	public void the_delivery_will_be_preset_in_the_checkout_to_the_country_I_have_been_browsing_the_site_in(String deliveryCountry)
	{
		assertTrue(checkOutPage.selectedCountry().getText().contains(deliveryCountry));
	}

	@Then("^the maximum quantity option as listed in the Quantity picker will be re-set and will be less than \"([^\"]*)\"$")
	public void theMaximumQuantityOptionAsListedInTheQuantityPickerWillBeReSetAndWillBeLessThan(int size)
	{
		checkOutPage.pause(2000);
		if (checkOutPage.returnTotalNumberOfSizes() < size)
		{
			assertTrue(checkOutPage.returnTotalNumberOfSizes() < size);
		}
		else
		{
			checkOutPage.refresh();
			checkOutPage.pause(2000);
			assertTrue(checkOutPage.returnTotalNumberOfSizes() < size);
		}
	}

	@Then("^I am unable to select an alternative size from the picker$")
	public void I_am_unable_to_select_an_alternative_size_from_the_picker()
	{
		assertTrue(checkOutPage.productWithNoSize()
				.getAttribute("data-ng-if")
				.contains("!entry.showSizeSelect && entry.quantity > 0"));
	}

	@And("^I am presented with a colour swatch panel$")
	public void I_am_presented_with_a_colour_swatch_panel()
	{
		assertTrue(checkOutPage.colorSwatchPanel().isDisplayed());
	}

	@And("^I can select a new colour$")
	public void I_can_select_a_new_colour() throws Throwable
	{
		this.changedProductCode = checkOutPage.productCode().getText();
		checkOutPage.selectColorCodeFromSwatch();
	}

	@And("^the swatch panel will close automatically$")
	public void the_swatch_panel_will_close_automatically() throws Throwable
	{
		//TODO remove static sleep
		Thread.sleep(1000);
		assertFalse(checkOutPage.checkSwatchPanelIsAvailable());
	}

	@Then("^I am NOT presented with a 'Change Colour' link$")
	public void I_am_NOT_presented_with_a_Change_Colour_link()
	{
		assertFalse(checkOutPage.changeColorLinkDisplayed());
	}

	@When("^I search for the product with the \"([^\"]*)\" on mobile$")
	public void I_search_for_the_product_with_the_on_mobile(String productId)
	{
		loginPage.getWebDriver().navigate().to("TBD" + "/search/?text=" + productId);
	}

	@And("^fill the shipping details for rest of the world \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void fill_the_shipping_details_for_rest_of_the_world_(String country,
	                                                             String title,
	                                                             String firstName,
	                                                             String lastName,
	                                                             String postCode,
	                                                             String addressLine1,
	                                                             String addressLine2,
	                                                             String city,
	                                                             String state,
	                                                             String phoneNumber) throws InterruptedException
	{

//        checkOutPage.selectDelivery().click();
		checkOutPage.selectTitleFromDropdownList(title);
		checkOutPage.firstname().sendKeys(firstName);
		checkOutPage.lastName().sendKeys(lastName);
		checkOutPage.postCode().sendKeys(postCode);
		checkOutPage.addressLine1().sendKeys(addressLine1);
		checkOutPage.addressLine2().sendKeys(addressLine2);
		try
		{
			checkOutPage.mobileNumber().sendKeys(phoneNumber);
		}
		catch (Exception e)
		{
			log.info("No Mobile Number Field");
		}

		checkOutPage.city().sendKeys(city);
		((JavascriptExecutor) loginPage.getWebDriver()).executeScript("window.scrollBy(0, 250)", "");
		checkOutPage.saveShippingAddress().click();
		//TODO remove static sleep
		Thread.sleep(4000);
		checkOutPage.selectAvailableDelivery();
		try
		{
			checkOutPage.waitForPresenceOfElement(checkOutPage.noDeliveryUpdatesButton, 2);
			checkOutPage.noDeliveryUpdatesButton().click();
		}
		catch (Exception e)
		{
			log.info("No delivery update button");
		}
		try
		{
			checkOutPage.continueToPaymentButton().click();
		}
		catch (Exception e)
		{
			log.info("Continue to payment button not available.");
		}
		((JavascriptExecutor) loginPage.getWebDriver()).executeScript("window.scrollBy(0, 250)", "");
		checkOutPage.enterMandatoryMobileField();
		checkOutPage.selectDebitOrCreditCardTab().click();
	}

	@And("^fill the shipping details for europe \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void fill_the_shipping_details_for_europe_(String title,
	                                                  String firstName,
	                                                  String lastName,
	                                                  String postCode,
	                                                  String addressLine1,
	                                                  String addressLine2,
	                                                  String city) throws Throwable
	{
		checkOutPage.clickManualAddr();
		checkOutPage.selectTitleFromDropdownList(title);
		checkOutPage.firstname().sendKeys(firstName + RandomGenerator.randomAlphabetic(5));
		checkOutPage.lastName().sendKeys(lastName + RandomGenerator.randomAlphabetic(5));
		checkOutPage.postCode().sendKeys(postCode);
		checkOutPage.addressLine1().sendKeys(addressLine1 + RandomGenerator.randomAlphabetic(5));
		checkOutPage.addressLine2().sendKeys(addressLine2 + RandomGenerator.randomAlphabetic(5));
		checkOutPage.city().sendKeys(city);
		try
		{
			checkOutPage.getWebDriver().findElement(checkOutPage.mobileNoFieldDeliveruy).sendKeys("07698866778");
		}
		catch (Exception e)
		{
			log.info("No mobile field");
		}
		((JavascriptExecutor) loginPage.getWebDriver()).executeScript("window.scrollBy(0, 250)", "");
		checkOutPage.saveShippingAddress().click();
		//TODO remove static sleep
		Thread.sleep(1000);
		checkOutPage.deliveryServicePremiumDeliveryButton().click();
		try
		{
			checkOutPage.noDeliveryUpdatesButton().click();
		}
		catch (Exception e)
		{
			log.info("No delivery update option");
		}

	}

	@Then("^click pay using paypal for french site$")
	public void click_pay_using_paypal_for_french_site() throws InterruptedException
	{
		((JavascriptExecutor) loginPage.getWebDriver()).executeScript("window.scrollBy(0, 250)", "");
		checkOutPage.paypalButton().click();
	}

	@And("^switch to paypal window$")
	public void switch_to_paypal_window() throws InterruptedException
	{
		checkOutPage.switchToPaypalWindow();
	}

	@And("^submit by clicking paynow from paypal window$")
	public void submit_by_clciking_paynow_from_paypal_window() throws Throwable
	{
		checkOutPage.clickPaynowPaypalButton();
	}

	@Then("^change the \"([^\"]*)\" in the delivery country address form the dropdown$")
	public void change_the_in_the_delivery_country_address_form_the_dropdown(String deliveryCountry) throws InterruptedException
	{
		checkOutPage.selectDifferentDeliveryCountry(deliveryCountry);
	}


	@And("^select debit or credit card tab$")
	public void select_debit_or_credit_card_tab() throws InterruptedException, WebElementNotFoundException
	{
		try
		{
			checkOutPage.pause(1000);
			checkOutPage.waitForAndGetElement(checkOutPage.noDeliveryUpdatesButton, 4);
			checkOutPage.noDeliveryUpdatesButton().click();
			checkOutPage.waitForPageLoad();
		}
		catch (Exception e)
		{
			log.info("No delivery update button found");
		}

		if (checkOutPage.isElementPresent(checkOutPage.phoneNumberFieldInCheckout(), 10))
		{
			checkOutPage.yesDeliveryUpdates();
		}

		if (checkOutPage.continueToPaymentButtonPresent())
		{
			checkOutPage.continueToPaymentButton().click();
		}
		checkOutPage.pause(2000);
		((JavascriptExecutor) loginPage.getWebDriver()).executeScript("window.scrollBy(0, 250)", "");
		checkOutPage.pause(2000);
		checkoutAndPaymentsPage.clickCreditDebitCardButton();
	}

	@Then("^you should see appropriate message for changed \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void you_should_see_appropriate_message_for_changed(String messagePart1, String country, String messagePart2)
	{
		if (messagePart1.equals("") || messagePart2.equals(""))
		{
			assertFalse(checkOutPage.alertMessageIsDisplayed());
		}
		else
		{
			assertTrue(checkOutPage.alertText().isDisplayed());
			assertTrue(checkOutPage.alertText()
					.getText()
					.contains("If you change your delivery country to " +
							country +
							" we will redirect you to the " +
							country +
							" website, which may remove items from your order. Please review your bag after changing delivery country."));
		}
	}

	@And("^fill the card details \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void fill_the_card_details_(String cardType, String cardNumber, String date, String year, String cvv)
	{
		checkOutPage.fillCardDetails(cardType, cardNumber, date, year, cvv);
	}

	@When("^I type my card expiry date with \"([^\"]*)\",\"([^\"]*)\"$")
	public void fill_the_card_expiry_date(String month, String year)
	{
		checkOutPage.fillCardExpiryDateWithPCIOn(month, year);
	}

	@Then("^the date field should not display anything$")
	public void check_date_field()
	{
		assertFalse(checkOutPage.checkExpiryDateInputResultIsNotEmpty());
	}

	@Then("^the date field should display error message \"([^\"]*)\"$")
	public void check_date_field_with_error(String errorMessage)
	{
		assertEquals(errorMessage, checkOutPage.getExpiryDateErrorMessage());
	}


	@When("^I type my card number as \"([^\"]*)\"$")
	public void iTypeMyCardNumberAs(String cardNumber)
	{
		checkOutPage.fillCardNumberWithPCIOn(cardNumber);
	}

	@Then("^the card number field should display error message \"([^\"]*)\"$")
	public void check_card_number_field_with_error(String errorMessage)
	{
		assertEquals(checkOutPage.getCardNumberErrorMessage(), errorMessage);
	}


	@And("^search for nearest collection location to: \"([^\"]*)\"$")
	public void search_for_nearest(String collectionLocation)
	{
		checkOutPage.collectionButton().click();
		checkOutPage.waitForPageLoad();
		checkoutAndPaymentsPage.searchForCollectionLocationsRestOfWorld(collectionLocation);
		try
		{
			checkoutAndPaymentsPage.selectTheFirstCollectionOptionFromList();
		}
		catch (Exception e)
		{
			checkOutPage.refresh();
			checkOutPage.pause(1000);
			checkOutPage.collectionButton().click();
			checkOutPage.pause(2000);
			checkoutAndPaymentsPage.searchForCollectionLocationsRestOfWorld(collectionLocation);
			checkOutPage.pause(1000);
			checkoutAndPaymentsPage.selectTheFirstCollectionOptionFromList();
		}
		try
		{
			checkOutPage.collectionDetails();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@And("^search for nearest collection location as popup alert script$")
	public void search_for_nearestAsPopUP()
	{
		checkOutPage.collectionButton().click();
		checkOutPage.postcodeOrCountryField().sendKeys("<script>alert('test');</script>");
		checkOutPage.collectionSubmitButton().click();
	}

	@And("^fill the collection details$")
	public void fill_the_collection_details()
	{
		checkOutPage.collectionDetailsRestOfWorld();
	}

	@And("^search for nearest store with \"([^\"]*)\"$")
	public void search_for_nearest_store_with(String postcode) throws Throwable
	{
		log.info("Searching nearest store");
		checkOutPage.collectionButton().click();
		checkOutPage.postcodeOrCountryField().sendKeys(postcode);
		checkOutPage.collectionSubmitButton().click();
		if (!IS_MOBILE)
		{
			checkoutAndPaymentsPage.waitForElementVisible(checkoutAndPaymentsPage.COLLECTION_SEARCH_RESULT_MAP, 10);
		}
	}

	@And("^search for nearest store with invalid \"([^\"]*)\"$")
	public void search_for_nearest_store_with_invalid(String postcode)
	{
		checkOutPage.collectionButton().click();
		checkOutPage.postcodeOrCountryField().sendKeys(postcode);
		checkOutPage.collectionSubmitButton().click();
	}

	@Then("^an error message is displayed$")
	public void an_error_message_is_displayed()
	{
		storeLocatorPage.waitForAndGetElement(checkOutPage.noStoresFoundError, 10);
		assertTrue(checkOutPage.noStoresFoundError().isDisplayed());
	}

	@And("^select collection button$")
	public void select_collection_button() throws InterruptedException
	{
		Thread.sleep(1000);
		checkOutPage.collectionButton().click();
	}

	@And("^user should be able to change the delivery country$")
	public void user_should_be_able_to_change_the_delivery_country()
	{
		checkOutPage.changeDeliveryCountryLink().click();
		checkOutPage.changeCountry().click();
		checkOutPage.countryInputTextField().sendKeys("italy");
		checkOutPage.selectCountryForCollection().click();
		checkOutPage.pause(1000);
		checkOutPage.clickByJavaScriptExecutor(checkOutPage.applyChangeButton());
		assertTrue(checkOutPage.deliveryCountry().getText().contains("Italy"));
	}

	@And("^user should be able to change the delivery country from bag$")
	public void user_should_be_able_to_change_the_delivery_countrybag() throws InterruptedException
	{
		checkOutPage.changeCountryFromBag();
		assertTrue(checkOutPage.deliveryCountry().getText().contains("Italy"));
	}

	@And("^user should be able to change the delivery country to \"([^\"]*)\" from bag$")
	public void user_should_be_able_to_change_the_delivery_country_to_from_bag(String country) throws InterruptedException
	{
		checkOutPage.changeCountryFromBag(country);
	}

	@And("^bag page should show an alert (.*)$")
	public void changeCountryAlert(String msg) throws InterruptedException
	{
		checkOutPage.waitForTextToBePresentInElement(MyBagPage.BAG_ERROR_ALERT, msg, 5);
		//assertTrue(checkOutPage.deliveryCountry().getText().contains(country));
	}

	@And("^changed delivery country should be (.*)$")
	public void assertChangeCountryMybagPage(String country) throws InterruptedException
	{
		assertTrue(checkOutPage.deliveryCountry().getText().contains(country));
	}

	@And("^fill the billing address details$")
	public void fill_the_billing_address_details()
	{
		checkOutPage.billingAddressAddressLine1().sendKeys(RandomGenerator.randomAlphabetic(5));
		checkOutPage.billingAddressPostCode().sendKeys("75045");
		checkOutPage.billingAddressCity().sendKeys("paris");
		//checkOutPage.billingAddressSaveShippingAddress().click();
		checkOutPage.getButtonWithText("USE THIS ADDRESS").click();
	}

	@And("^fill the billing address details for checkout \"([^\"]*)\" , \"([^\"]*)\"$")
	public void fill_the_billing_address_details_for_checkout_(String postCode, String city) throws Exception
	{
		checkOutPage.enterMandatoryMobileField();
		checkOutPage.fillBillingAddressFormManual(postCode, city);
	}

	@And("^fill the billing address details for guest$")
	public void fill_the_billing_address_details_for_guest() throws Throwable
	{
		checkOutPage.selectBillingAddressTitle();
		checkOutPage.billingAddressFirstname().sendKeys(RandomGenerator.randomAlphabetic(5));
		checkOutPage.billingAddressLastName().sendKeys(RandomGenerator.randomAlphabetic(5));
		checkOutPage.billingAddressPostCode().sendKeys("75045");
		checkOutPage.billingAddressAddressLine1().sendKeys(RandomGenerator.randomAlphabetic(5));
		checkOutPage.billingAddressAddressLine2().sendKeys(RandomGenerator.randomAlphabetic(5));
		checkOutPage.billingAddressCity().sendKeys(RandomGenerator.randomAlphabetic(5));
		//checkOutPage.billingAddressSaveShippingAddress().click();
		checkOutPage.scrollForFocusAndClick(checkOutPage.useThisAddressBilling, 3);
	}


	@And("^fill the billing address details for checkout \"([^\"]*)\" , \"([^\"]*)\" as guest customer$")
	public void fill_the_billing_address_details_for_checkout_as_guest_customer(String postcode,
	                                                                            String city) throws Throwable
	{
		checkOutPage.fillBillingAddressForm(postcode, city);
	}

	@When("^I go to checkout with the same account$")
	public void I_go_to_checkout_with_the_same_account() throws InterruptedException
	{

		checkOutPage.searchField().sendKeys(Props.getProp("productCode_brand"));
		checkOutPage.searchButton().click();
		homePage.selectRandomProduct();
		if (checkOutPage.searchPageStillAvailable())
		{
			homePage.selectPdpProductImage().click();
		}
		checkOutPage.selectSizeOfTheProduct();

		checkOutPage.addToCartButton().click();
		JavascriptExecutor jse = (JavascriptExecutor) checkOutPage.getWebDriver();
		jse.executeScript("scroll(0, -250);");

		checkOutPage.basketIcon().click();
		checkOutPage.selectQuantityOfLineItem("Qty: 5", 1);
		// checkOutPage.switchToCheckoutIframe();
		// checkOutPage.reviewAndCheckoutButton().click();

		// if (checkOutPage.reviewCheckoutStillAvailable()) {
		//   checkOutPage.reviewAndCheckoutButton().click();
		// }
		Thread.sleep(2000);

		//checkOutPage.checkOutButton().click();
		checkOutPage.clickCheckoutButton();

	}

	@When("^user changes the collection country from preselected to country \"([^\"]*)\"$")
	public void user_changes_the_collection_country_from_preselected_to_country(String country)
	{
		if (IS_MOBILE)
		{
			checkOutPage.changeTheDeliveryCountryWithoutConfirmingMobile(country);
		}
		else
		{
			checkOutPage.pause(6000);
			checkOutPage.changeDeliveryCountryLink().click();
			checkOutPage.changeCountry().click();
			checkOutPage.countryInputTextField().sendKeys(country);
			checkOutPage.selectCountryForCollection().click();
			checkOutPage.pause(1000);
		}
	}

	@When("^user should be able to change the collection country to \"([^\"]*)\"$")
	public void user_should_be_able_to_change_the_collection_country_to(String country)
	{
		if (IS_MOBILE)
		{
			checkOutPage.changeTheDeliveryCountryMobile(country);
		}
		else
		{
			checkOutPage.changeDeliveryCountryLink().click();
			checkOutPage.changeCountry().click();
			checkOutPage.countryInputTextField().sendKeys(country);
			checkOutPage.selectCountryForCollection().click();
			checkOutPage.pause(1000);
			checkOutPage.clickByJavaScriptExecutor(checkOutPage.applyChangeButton());
			assertTrue(checkOutPage.deliveryCountry().getText().contains(country));
		}
	}

	@Then("^\"([^\"]*)\" is automatically selected$")
	public void is_automatically_selected(String deliveryType) throws Throwable
	{
		if (deliveryType.equalsIgnoreCase("DELIVERY"))
		{
			assertTrue(checkOutPage.isElementVisible(checkOutPage.deliveryAddress, 1));
		}
		if (deliveryType.equalsIgnoreCase("COLLECTION"))
		{
			assertFalse(checkOutPage.isElementVisible(checkOutPage.deliveryAddress, 1));
		}
	}

	@When("^i click on checkout button for collection$")
	public void i_click_on_checkout_button_for_collection() throws Throwable
	{
		checkOutPage.switchToCheckoutIframe();
		checkOutPage.reviewAndCheckoutButton().click();

		if (checkOutPage.reviewCheckoutStillAvailable())
		{
			checkOutPage.reviewAndCheckoutButton().click();
		}
		checkOutPage.selectQuantityOfLineItem("8", 1);
		//TODO remove static sleep
		Thread.sleep(2000);
		checkOutPage.checkOutButton().click();
	}


	@Then("^saved location should be present for collection$")
	public void saved_location_should_be_present_for_collection()
	{
		assertTrue(checkOutPage.collectionLocation().isDisplayed());

	}


	@And("^click on change location link$")
	public void click_on_change_location_link()
	{
		checkOutPage.changeLocationLink().click();
	}

	@Then("^I should be navigated back to checkout page$")
	public void I_should_be_navigated_back_to_checkout_page()
	{
		assertTrue(checkOutPage.postcodeOrCountryField().isDisplayed());
		assertTrue(checkOutPage.collectionSubmitButton().isDisplayed());
	}

	@And("^click on change delivery country$")
	public void click_on_change_delivery_country()
	{
		checkOutPage.changeDeliveryCountry().click();
	}


	@And("^should be able to change collection \"([^\"]*)\"$")
	public void should_be_able_to_change_collection(String country) throws Throwable
	{
		checkOutPage.changeDeliveryCountryForCollection(country);
	}

	@And("^click continue to payment button for guest$")
	public void click_continue_to_payment_button_for_guest() throws InterruptedException
	{
		try
		{
			checkOutPage.yesDeliveryUpdates();
		}
		catch (Exception e)
		{
			log.info("no delivery updates option");
		}
		checkoutAndPaymentsPage.clickCreditDebitCardButton();
	}

	@And("^fill the shipping details for guest$")
	public void fill_the_shipping_details_for_guest() throws Throwable
	{
		checkOutPage.fillTheCheckoutAddressForm();
		//TODO remove static sleep
		Thread.sleep(2000);
	}

	@And("^i click on delivery button$")
	public void i_click_on_delivery_button()
	{
		// checkOutPage.deliveryButton().click();
		checkOutPage.selectDelivery().click();
	}

	@When("^i click on checkout button$")
	public void i_click_on_checkout_button() throws InterruptedException
	{
		checkOutPage.clickCheckoutButton();
		//checkOutPage.checkOutButton().click();
	}


	@Then("^review the quick view cart$")
	public void review_the_quick_view_cart()
	{
		checkOutPage.basketIcon().click();
	}

	@Then("^order has been successful$")
	public void order_has_been_successful()
	{
		checkOutPage.waitForPageLoad();
		checkOutPage.waitForElementVisible(checkOutPage.checkouMessage(),20);
		assertTrue("Order Successful message not found", checkOutPage.checkouMessage().isDisplayed());
	}

	@And("^customer see Delivery Pass confirmation$")
	public void customerSeeDeliveryPassConfirmation()
	{
		assertTrue("Delivery Pass confirmation text is not visible", checkOutPage.isDeliveryPassApplied());
	}

	@Then("^order history shows correct order number$")
	public void order_has_been_successfulTracking()
	{
		String expOrderno = checkOutPage.getConfirmationOrderNo();
		if (IS_MOBILE)
		{
			homePageNew.navigateToMyAccountFromHeaderOnMobile();
			myOrdersPage.clickViewOrder();
		}
		else
		{
			UrlBuilder.navigateToOrdersPage();
		}
		checkOutPage.waitForOrderNO(expOrderno.trim());
	}
	@Then("^order history shows correct order number for Oms$")
	public void orderSuccessfullyTrackingOms()
	{
		String expOrderNo = checkOutPage.getConfirmationOrderNo();
		if (IS_MOBILE)
		{
			homePageNew.navigateToMyAccountFromHeaderOnMobile();
			myOrdersPage.clickViewOrder();
		}
		else
		{
			UrlBuilder.navigateToOrdersPage();
		}
		checkOutPage.waitForOrderNOOms(expOrderNo.trim());
	}

	@Given("^delete all cookies$")
	public void deleteAllCookies() throws Throwable
	{
		loginPage.deleteAllCookies();
	}

	@Then("^logout of the account$")
	public void logout_of_the_account() throws Throwable
	{
		// loginPage.scrollForFocusAndClick(loginPage.loggedInUserLinkLocator,3);
		loginPage.loggedInUserLinkLocator().click();
		loginPage.signoutLinkLocator().click();
	}

	@When("^I click Sign In on the header bar$")
	public void I_click_Sign_In_on_the_header_bar()
	{
		loginPage.logoutIfSignIn();
		loginPage.signinLinkLocator().click();

	}

	@When("^user click (.*) on the header bar$")
	public void I_click_Sign_In_on_the_header_bar2(String link)
	{
		loginPage.clickUserAccountLink(link.trim());

	}

	@And("^I am shown the distance of the store from my search location in both miles and kilometres$")
	public void I_am_shown_the_distance_of_the_store_from_my_search_location_in_both_miles_and_kilometres()
	{
		List<String> distances = storeLocatorPage.distanceBetweenTheStores();
		for (int i = 1; i < distances.size(); i++)
		{
			assertTrue(Float.parseFloat(distances.get(i - 1)) <= Float.parseFloat(distances.get(i)));
		}
	}

	@And("^fill the shipping details$")
	public void fill_the_shipping_details() throws InterruptedException
	{
		checkOutPage.fillTheCheckoutAddressForm();
		checkOutPage.selectDebitOrCreditCardTab().click();
	}

	@When("^I select the Collection Location name or 'Map and Opening times' link$")
	public void I_select_the_Collection_Location_name_or_Map_and_Opening_times_link()
	{
		storeLocatorPage.waitForAndGetElement(storeLocatorPage.mapAndOpeningTimes, 10);
		storeLocatorPage.scrollForFocusAndClick(storeLocatorPage.mapAndOpeningTimes, 3);
		//storeLocatorPage.mapAndOpeningTimes().click();
	}

	@Then("^more information on the collection location is displayed$")
	public void more_information_on_the_collection_location_is_displayed()
	{
		assertTrue(storeLocatorPage.locationDetails().isDisplayed());
	}

	@Then("^for each collection point result in the list I am shown a summary of information, where available$")
	public void for_each_collection_point_result_in_the_list_I_am_shown_a_summary_of_information_where_available()
	{
		storeLocatorPage.waitForAndGetElement(storeLocatorPage.storeResultsTitle, 10);
		assertTrue(storeLocatorPage.storeResultsTitle().isDisplayed());
		assertTrue(storeLocatorPage.storeResultsAddress().isDisplayed());
		assertTrue(storeLocatorPage.mapAndOpeningTimes().isDisplayed());
	}

	@Given("^selects (view bag & checkout|secure checkout)$")
	public void selects_button(String button)
	{
		switch (button)
		{
			case "view bag & checkout":
				checkOutPage.itemAddedToBagGoToCart().click();
				break;
			case "secure checkout":
				checkOutPage.secureCheckoutButton().click();
				break;
		}
	}

	@Then("^chooses (standard delivery|collection|AddressLookup)$")
	public void chooses_delivery(String deliveryChoice) throws Exception
	{
		switch (deliveryChoice)
		{
			case "standard delivery":
				checkOutPage.selectDelivery().click();
				checkOutPage.deliveryAddress("UK");
				break;
			case "collection":
				checkOutPage.collectionButton().click();
				break;
			case "AddressLookup":
				checkOutPage.selectDelivery().click();
				checkOutPage.lookupAddress("UK");
				break;
		}
	}

	@And("^select available delivery mode$")
	public void select_available_delivery() throws Throwable
	{
		checkOutPage.pause(5000);
		checkOutPage.selectAvailableDelivery();
	}

	@Given("^pays by (.*) (.*) (.*)$")
	public void pays_by(String payment, String cardType, String cardNumber)
	{
		checkOutPage.enterCardDetailsAndMakePlaceOrder(Props.getProp(payment), cardType, Props.getProp(cardNumber));
	}

	@When("^I enter card number as \"([^\"]*)\" in card popup$")
	public void enterInvalidCard(String cardNumber)
	{
		checkOutPage.enterInvalidCardAndClosePopups(cardNumber);
	}

	@And("^I close popups$")
	public void iClosePopups()
	{
		checkOutPage.ClosePopups();
	}

	@Given("^save (payment card|staff card|store card) details with no (.*)$")
	public void saveCard(String payment, String no) throws Exception
	{
		checkOutPage.continueToPaymentSection();
		switch (payment)
		{
			case "payment card":
				//checkOutPage.selectDebitorCreditCardTab().click();
				checkOutPage.clickDebitOrCreditCardTab();
				checkOutPage.fillBillingAddressForm();
				checkOutPage.fillCardDetails(no);
				break;
			case "store card":
			case "staff card":
				checkOutPage.storeCard().click();
				checkOutPage.fillBillingAddressFormStoreCard();
				checkOutPage.enterStoreOrStaffCardDetails(no, "08", "23");
				break;
		}
	}

	@Given("^add store card details with no (.*) and expiry date (.*)$")
	public void saveStoreCard(String no, String expiryDate) throws Exception
	{
		checkOutPage.continueToPaymentSection();
		checkOutPage.storeCard().click();
		checkOutPage.fillBillingAddressFormStoreCard();
		if(expiryDate.length()>3){
			checkOutPage.enterStoreOrStaffCardDetails(no, expiryDate.substring(0, 2), expiryDate.substring(2));
		}
		else{
			checkOutPage.enterStoreOrStaffCardDetails(no, expiryDate.substring(0, 1), expiryDate.substring(1));
		}
	}

	@And("^fill store card billing address$")
	public void fillStoreCardBillingAddress()
	{
		checkOutPage.fillBillingAddressFormStoreCard();
	}

	@And("^Customer placed order$")
	public void customerPlacedOrder() throws Throwable
	{
		Thread.sleep(2000);
		checkOutPage.clickCheckoutButton();
		checkOutPage.collectionButton().click();
		checkOutPage.postcodeOrCountryField().sendKeys("london");
		checkOutPage.collectionSubmitButton().click();
		checkOutPage.pause(3000);
		checkOutPage.clickCollectionStoreSub();
		checkOutPage.scrollForFocusAndClick(checkOutPage.selectStoreButton, 15);
		try
		{
			checkOutPage.collectionDetails();
		}
		catch (Exception e)
		{
			log.info("No collection details from found");
		}
		checkOutPage.enterMandatoryMobileField();
	}

	@Then("^user verifies if selected collection checkbox is selected in the checkout page$")
	public void userVerifiesIfSelectedCollectionCheckboxIsSelectedInTheCheckoutPage() throws Exception
	{
		assertTrue(checkoutAndPaymentsPage.verifySelectedCollectionPoint(checkoutModel));
	}

	@And("^also verifies if delivery options can be toggled$")
	public void alsoVerifiesIfDeliveryOptionsCanBeToggled() throws Exception
	{
		checkoutAndPaymentsPage.clickOnOtherCollectionType(checkoutModel);
	}

	@And("^also checks if the changed delivery option will be updated as \"([^\"]*)\" in the order summary$")
	public void alsoChecksIfTheChangedDeliveryOptionWillBeUpdatedInTheOrderSummary(String toggledCollectionType) throws Exception
	{
		assertTrue(checkoutAndPaymentsPage.getOrderSummary().contains(toggledCollectionType));
	}

	@And("^search for nearest collection location with \"([^\"]*)\" and select collection as \"([^\"]*)\"$")
	public void searchForNearestCollectionLocationWithAndSelectOnClickCollectStandard(String postcode,
	                                                                                  String collectionType) throws Exception
	{
		checkOutPage.collectionButton().click();
		checkOutPage.postcodeOrCountryField().sendKeys(postcode);
		checkOutPage.collectionSubmitButton().click();
		checkoutAndPaymentsPage.selectCollectionType(collectionType, checkoutModel);
		checkOutPage.collectionDetails();
	}

	@And("^search for nearest collection location with \"([^\"]*)\" and select collection location as \"([^\"]*)\"$")
	public void searchForNearestCollectionLocationWithAndSelectCollectionLocation(String postcode, String collectionType) throws Exception
	{
		checkOutPage.collectionButton().click();
		checkOutPage.pause(3000);
		try
		{
			if (checkOutPage.postcodeOrCountryField().isDisplayed())
			{
				checkOutPage.postcodeOrCountryField().sendKeys(postcode);
				checkOutPage.collectionSubmitButton().click();
				checkoutAndPaymentsPage.selectCollectionType(collectionType, checkoutModel);
			}
		}
		catch (Exception e)
		{
			log.info("Use with saved address");
		}
	}

	@Then("^My Bag page should be displayed$")
	public void my_Bag_page_should_be_displayed() throws Exception
	{
		assertTrue(checkOutPage.returnBagHeader().isDisplayed());
	}

	@And("^enter mandatory mobile number$")
	public void enterMandatoryMobileNumber()
	{
		checkOutPage.enterMandatoryMobileField();
	}

	@And("^enter collection details$")
	public void enterCollectionDetails()
	{
		checkOutPage.collectionDetails();
	}

	@And("^I click on apply change country button$")
	public void iClickOnApplyChangeCountryButton()
	{
		checkoutAndPaymentsPage.clickApplyChangeCountryButton();
	}

	@And("^goes through pop up window$")
	public void goesThroughPopUpWindow()
	{
		checkOutPage.switchToPopUp();

	}

	@And("^goes through iframe$")
	public void goesThroughIframe()
	{
		checkOutPage.switchToIframe();

	}

	@And("^goes through authentication window OTP$")
	public void goesThroughAuthenticationWindowOTP()
	{
		checkOutPage.continueToAuthenticationWindowOTP();

	}

	@And("^Add billing address$")
	public void addBillingAddress()
	{
		checkOutPage.fillBillingAddressForm();
	}

	@And("^use this card option is \"([^\"]*)\"$")
	public void theUseThisCardOptionWillBeDisabled(String enabled)
	{
		switch (enabled.toLowerCase())
		{
			case "disabled":
				assertFalse(checkOutPage.useThisCard().isEnabled());
				break;
			case "enabled":
				assertTrue(checkOutPage.useThisCard().isEnabled());
				break;
			case "displayed":
				assertTrue(checkOutPage.useThisCard().isDisplayed());
				break;
			case "not displayed":
				assertNull(checkOutPage.useThisCard());
				break;
			default:
				Assert.fail("Not able to validate USE THIS CARD CTA status");
		}
	}

	@When("^I proceed to the guest checkout payment step$")
	public void iProceedToTheGuestCheckoutPaymentStep() throws WebElementNotFoundException, InterruptedException
	{
		continue_as_guest();
		iProceedToTheCheckoutPaymentStep();
	}

	@When("^I proceed to the checkout payment step$")
	public void iProceedToTheCheckoutPaymentStep() throws WebElementNotFoundException, InterruptedException
	{
		search_for_nearest("London");
		select_debit_or_credit_card_tab();
		addBillingAddress();
	}

	@And("^enter delivery mandatory mobile number$")
	public void enterDeliveryMandatoryMobileNumber()
	{
		checkOutPage.enterDeliveryMandatoryMobileField();
	}

	@Then("^Total saved item count is \"([^\"]*)\"$")
	public void check_total_saved_amount_in_basket(String totalSavedAmount)
	{
		checkOutPage.pause(2000);
		assertThat(wishListPage.wishListBagCount().getText()).isEqualTo(totalSavedAmount);
	}

	@And("^select payment method as \"([^\"]*)\"$")
	public void selectPaymentMethodAs(String paymentMethod)
	{
		try
		{
			checkoutAndPaymentsPage.selectPayWithOption(paymentMethod);
		}
		catch (Exception e)
		{
			log.info(paymentMethod.toLowerCase() + " not displayed");
		}
	}

	@Then("^billing address form should be displayed inside selected payment as \"([^\"]*)\"$")
	public void billingAddressFormDisplayedInsideSelected(String paymentMethod)
	{
		checkoutAndPaymentsPage.billingAddressFormDisplayedInsideSelectedPaymentOption(paymentMethod);
	}

	@And("^saved billing address should be displayed inside selected payment as \"([^\"]*)\"$")
	public void savedBillingAddressDisplayedInsideSelectedPaymentMethod(String paymentMethod)
	{
		checkoutAndPaymentsPage.isSavedBillingAddressDisplayed(paymentMethod);
	}

	@Then("^Ghost Text should be \"([^\"]*)\"$")
	public void ghostTextDisplayedOrNot(String status)
	{
		checkoutAndPaymentsPage.verifyGhostTextDisplayed(status);
	}

	@Then("^invalid card message \"([^\"]*)\" for \"([^\"]*)\"$")
	public void invalidCardNumberDisplayedOrNot(String displayed, String field)
	{
		checkoutAndPaymentsPage.verifyInvalidCardMessageDisplayed(displayed.trim(), field.trim());
	}

	@And("^continue to payment button$")
	public void continueToPaymentButton()
	{
		try
		{
			checkOutPage.collectionDetailsConfirmButton().click();
		}
		catch (Exception e)
		{
			Assert.fail("No Continue to payment button found");
		}
	}

	@And("^change the size to \"([^\"]*)\" of the product on mobile$")
	public void change_the_size_of_the_product_on_mobile(String size)
	{
		myBagPage.clickEditForLineItemMobile(1);
		checkOutPage.selectSize(size);
	}

	@And("^standard delivery message is displayed on pdp page for products$")
	public void standard_delivery_message_is_displayed_on_pdppage_for_products(DataTable table)
	{
		myBagPage.verifyDeliveryMessagesOnCartPage(table);
	}

	@And("^standard delivery message is displayed on saveditems page for products$")
	public void standard_delivery_message_is_displayed_on_saveditemspage_for_products(DataTable table)
	{
		myBagPage.verifyDeliveryMessagesOnSavedItemsPage(table);
	}

	@And("^group delivery message displayed$")
	public void group_delivery_message_displayed()
	{
		myBagPage.verifyGroupDeliveryMessage();
	}

	@And("^sla broken and group delivery message displayed along with collection disabled text \"([^\"]*)\"$")
	public void standardMessageAndCollectionDisabledText(String featureName)
	{
		myBagPage.standardMessageAndCollectionDisabledText(featureName);
	}
	
	
	@And("^group delivery message not displayed$")
	public void group_delivery_message_not_displayed()
	{
		myBagPage.verifyGroupDeliveryMessageNotDisplayed();
	}

	@And("^i provide valid username and password and click$")
	public void credentialToLoginHac(){

    pdpPage.enterCredentialClick();
	}

	@And("^i have cleared the Cache from Hac$")
	public void clearingCache(){

		pdpPage.clearingCache();
	}


}
