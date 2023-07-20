package com.salmon.test.step_definitions.gui.then;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.CheckOutPage;
import com.salmon.test.page_objects.gui.CheckoutAndPaymentsPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.MyBagPage;
import com.salmon.test.properties.AlertsProperties;
import com.salmon.test.properties.CardProperties;
import com.salmon.test.utils.SeleniumUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckoutsAndPaymentsThenStepDefs
{
	@Autowired
	private CheckoutAndPaymentsPage checkoutAndPaymentsPage;
	@Autowired
	private CheckOutPage checkOutPage;
	@Autowired
	private HomePage homePage;
	@Autowired
	private MyBagPage myBagPage;
	private static final Logger LOG = LogManager.getLogger(PageObject.class.getName());

//	public CheckoutsAndPaymentsThenStepDefs(final CheckoutAndPaymentsPage checkoutAndPaymentsPage,
//	                                        CheckOutPage checkOutPage)
//	{
//		this.checkoutAndPaymentsPage = checkoutAndPaymentsPage;
//		this.checkOutPage = checkOutPage;
//	}

	@Then("^Delivery option should be displayed$")
	public void deliveryOptionShouldBeDisplayed()
	{
		Assert.assertTrue("Delivery button is not displayed", checkoutAndPaymentsPage.checkDeliveryButtonDisplayed());
	}

	@Then("^Collection option should be displayed$")
	public void collectionOptionShouldBeDisplayed()
	{
		Assert.assertTrue("Delivery button is not displayed", checkoutAndPaymentsPage.checkCollectionButtonDisplayed());
	}

	@Then("^Customer should see address options in popup$")
	public void customerShouldSeeAddressOptionsInPopup(DataTable addressOption)
	{
		List<String> displayedTextInSingInBox = checkoutAndPaymentsPage
				.normaliseList(checkoutAndPaymentsPage
						.getCheckoutPaymentPopupLabels());
		Assert.assertEquals(checkoutAndPaymentsPage
						.normaliseList(checkoutAndPaymentsPage
								.addDataTableItemsToList(addressOption)),
				displayedTextInSingInBox);
	}

	@Then("^Popup should be displayed$")
	public void popupShouldBeDisplayed()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.getChangeDeliveryAddressPopup());
	}

	@Then("^All billing addresses are displayed$")
	public void allBillingAddressesAreDisplayed()
	{

	}

	@Then("^Billing address form should display$")
	public void billingAddressFormShouldDisplayLabels(DataTable labels)
	{
		List<String> displayedTextInSingInBox = checkoutAndPaymentsPage.normaliseList(checkoutAndPaymentsPage.getCheckoutPaymentPopupAddNewAddressLabels());

		Assert.assertEquals(checkoutAndPaymentsPage.normaliseList(checkoutAndPaymentsPage.addDataTableItemsToList(labels)),
				displayedTextInSingInBox);
	}


	@And("^clear bag item from checkout page$")
	public void clearBagItemFromCheckoutPage() throws Throwable
	{
		checkoutAndPaymentsPage.removeItem();
	}

	@And("^clear bag item from checkout page on mobile$")
	public void clearBagItemFromCheckoutPageOnMobile() throws Throwable
	{
		Thread.sleep(10000);
		checkoutAndPaymentsPage.removeItemOnMobile();
	}

	@Then("^checkout page (.*) show default payment card ending (\\d+)$")
	public void checkoutPageShouldShowDefaultCardEnding(String should, String arg0) throws Throwable
	{
		if (should.equals("should"))
		{
			assertThat(checkoutAndPaymentsPage.textToBePresentInElementLocated(CheckoutAndPaymentsPage.DEFAULT_PAYMENT_CARD, arg0)).isTrue();
		}
		else
		{
			assertThat(checkoutAndPaymentsPage.textToBePresentInElementLocated(CheckoutAndPaymentsPage.DEFAULT_PAYMENT_CARD, arg0)).isFalse();
		}
	}


	@Then("^checkout page (.*) show default store card saying (.*)$")
	public void checkoutPageShouldShowDefaultStoreCardEnding(String should, String arg0) throws Throwable
	{
		checkOutPage.continueToPaymentSection();
		checkOutPage.storeCard().click();
		if (should.equals("should"))
		{
			assertThat(checkoutAndPaymentsPage.textToBePresentInElementLocated(CheckoutAndPaymentsPage.DEFAULT_PAYMENT_CARD, arg0.trim())).isTrue();
		}
		else
		{
			assertThat(checkoutAndPaymentsPage.textToBePresentInElementLocated(CheckoutAndPaymentsPage.DEFAULT_PAYMENT_CARD, arg0.trim())).isFalse();
		}
	}

	@Then("^Map should be displayed$")
	public void mapShouldBeDisplayed()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.getMapOfCollectionLocations());
	}

	@Then("^Map should be displayed with (.*)$")
	public void mapShouldBeDisplayedWithContents(String contents)
	{
		String mapContentDisplayed = checkoutAndPaymentsPage.getMapContent(contents);
		Assert.assertEquals(contents, mapContentDisplayed);
	}

	@Then("^Map should be displayed with$")
	public void mapShouldBeDisplayedWith(List<String> stringTable)
	{
		checkoutAndPaymentsPage.pause(3000);
		for (String contents : stringTable)
		{
			String mapContentDisplayed = checkoutAndPaymentsPage.getMapContent(contents);
			Assert.assertEquals(contents, mapContentDisplayed);
		}
	}

	@Then("^Map should display pins$")
	public void mapShouldDisplayPins()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.getGoogleMapPins());
	}

	@Then("^The number of pins displayed should equal to stores displayed$")
	public void theNumberOfPinsDisplayedShouldEqualToStoresDisplayed() throws Throwable
	{
		Assert.assertEquals(checkoutAndPaymentsPage.getNumberOfMapPinsDisplayed(),
				checkoutAndPaymentsPage.getNumberOfResultsDisplayed());
	}

	@Then("^The user should be redirected to home delivery method$")
	public void theUserShouldBeRedirectedToHomeDeliveryMethod() throws Throwable
	{
		Assert.assertTrue(checkoutAndPaymentsPage.checkDeliveryMethodsTitleDisplayed());
	}

	@Then("^No saved card is displayed$")
	public void noSavedCardIsDisplayed()
	{
		Assert.assertFalse(checkoutAndPaymentsPage.isSavedCardDisplayed());
	}

	@Then("^saved card is displayed$")
	public void savedCardIsDisplayed()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.isSavedCardDisplayed());
	}

	@Then("^Display \"([^\"]*)\" design with \"([^\"]*)\" Klarna card$")
	public void displayDesignWithKlarnaCardAvailableToTheCustomer(String designStatus,
	                                                              String klarnaButtonStatus) throws Throwable
	{
		Assert.assertTrue(checkoutAndPaymentsPage.checkIsKlarnaButtonPresent(klarnaButtonStatus));
		Assert.assertTrue(checkoutAndPaymentsPage.checkIsNewDesignPresent(designStatus));
	}

	@Then("^Virtual credit payment widget is displayed$")
	public void virtualCreditPaymentWidgetIsDisplayed()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.virtualCardWidgetIsDisplayed());
	}

	@Then("^Virtual credit payment widget is immediately displayed$")
	public void virtualCreditPaymentWidgetIsImmediatelyDisplayed()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.isVirtualCreditCardWidgetImmediatelyDisplayed());
	}

	@Then("^New address with postcode: \"([^\"]*)\" is displayed$")
	public void new_address_is_displayed(String postCode)
	{
		Assert.assertTrue(checkoutAndPaymentsPage.postcodeIsDisplayed(postCode));
	}

	@Then("\"(\\d+)\" delivery options are present")
	public void twoDeliveryOptionsArePresent(int count) throws Throwable
	{
		Assert.assertTrue(checkoutAndPaymentsPage.getNumberOfAvailableDeliveryMethods() == count);
	}

	@Then("^an overlay of all cards listed with radio buttons is displayed$")
	public void anOverlayOfAllCardsListedWithRadioButtonsIsDisplayed()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.isCardSelectionOverlayDisplayed());
		Assert.assertTrue(checkoutAndPaymentsPage.getNumberOfPaymentCards() > 1);
	}

	@Then("^Typing a card number makes the correct icon appear next to the card number field:$")
	public void typingACardNumberMakesTheCorrectIconAppearNextToTheCardNumberField(final List<String> cardTypes)
	{
		final SoftAssert softAssert = new SoftAssert();

		for (final String cardType : cardTypes)
		{
			final String expectedIcon = CardProperties.cardIcon(cardType);
			final String cardNumber = CardProperties.cardNumber(cardType);

			SeleniumUtils.clearFieldAndType(checkoutAndPaymentsPage.getCardNumberField(), cardNumber);

			final String actualCardIcon = checkoutAndPaymentsPage.getValidCardIcon()
					.getAttribute("class");

			softAssert.assertTrue(actualCardIcon.contains(expectedIcon),
					String.format(" * [%s] card icon not found in icon class. Actual value: [%s]",
							cardType,
							actualCardIcon));
		}

		softAssert.assertAll();
	}

	@Then("^I am on the checkout page$")
	public void iAmOnTheCheckoutPage()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.isCheckoutVisible());
	}

	@And("^The failed payment error message is visible$")
	public void theFailedPaymentErrorMessageIsVisible()
	{
		final WebElement alert = checkoutAndPaymentsPage.getAlert(AlertsProperties.paymentFailed());
		Assert.assertTrue(alert.isDisplayed());
	}

	@And("^The cannot authenticate payment error message is visible$")
	public void theCannotAuthenticatePaymentErrorMessageIsVisible()
	{
		final WebElement alert = checkoutAndPaymentsPage.getAlert(AlertsProperties.paymentCannotAuthenticate());
		Assert.assertTrue(alert.isDisplayed());
	}

	@Then("^collection delivery edit form should be displayed with mandatory mobile number$")
	public void collectionDeliveryEditFormShouldBeDisplayedWithMandatoryMobileNumber()
	{
		Assert.assertTrue(checkOutPage.collectionDetailsFormDisplayed());
	}

	@Then("^Confirm CVV Field is displayed$")
	public void confirmCVVFieldIsDisplayed()
	{
		checkOutPage.pause(2000);
		Assert.assertTrue(checkoutAndPaymentsPage.isConfirmCVVFieldDisplayed());
	}

	@And("^Place order button is \"([^\"]*)\"$")
	public void placeOrderButtonIsGreyedOut(String greyedOut)
	{
		if (greyedOut.equalsIgnoreCase("greyed out"))
		{
			Assert.assertTrue(checkoutAndPaymentsPage.isPlaceOrderButtonGreyedOut());
		}
		else if (greyedOut.equalsIgnoreCase("active"))
		{
			Assert.assertTrue(checkoutAndPaymentsPage.isPlaceOrderButtonEnable());
		}
		else
		{
			Assert.fail("Not able to validate place order CTA status");
		}
	}

	@Then("^payment method error is displayed$")
	public void paymentMethodErrorIsDisplayed()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.isPaymentMethodErrorDisplayed());
	}

	@Then("^Proper save card message is displayed$")
	public void properSaveCardMessageIsDisplayed()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.getSaveCardMessage().equals("Save card details for next time"));
	}

	@And("^customer clicks add another card$")
	public void customerClicksAddAnotherCard()
	{
		checkOutPage.clickByJavaScriptExecutor(checkOutPage.getAddNewCardButton());
		checkOutPage.getUseThisAddressButton().click();
	}

	@Then("^new klarna design is visible (.*) tabs$")
	public void newKlarnaDesignIsVisibleWithoutTabs(String option)
	{
		if (option.equals("without"))
		{
			assertThat(checkOutPage.klarnaPaymentOption()).isFalse();
		}
		else
		{
			assertThat(checkOutPage.klarnaPaymentOption()).isTrue();
		}
	}

	@Then("^Virtual payment option is displayed as \"([^\"]*)\"$")
	public void verifyVirtualPaymentOptionDisplayed(String paymentOption)
	{
		checkoutAndPaymentsPage.verifyKlarnaPaymentOptionDisplayed(paymentOption);
	}

	@And("^click age verification check box$")
	public void clickAgeVerificationCheckBox()
	{
		checkOutPage.clickVerifyOver18AgeCommon();
	}

	@And("^change country as \"([^\"]*)\" and checkout with location as \"([^\"]*)\"$")
	public void changeCountryAsAndCheckoutWithCollection(String country, String location)
	{
		homePage.changeDeliveryCountry(Props.getProp(country));
		myBagPage.clickPrimarySecureCheckoutButton();
		checkoutAndPaymentsPage.selectDeliveryOrCollectionOption("COLLECTION");
		checkoutAndPaymentsPage.enterAddressForCollection(location);
	}

	@And("^change country as \"([^\"]*)\"$")
	public void changeCountryAsAndCheckoutWithCollection(String country)
	{
		homePage.changeDeliveryCountry(Props.getProp(country));
	}

	@And("^change country as \"([^\"]*)\" and add delivery address with postal code as \"([^\"]*)\"$")
	public void changeCountryAsAndCheckoutWithDelivery(String country, String postalCode)
	{
		homePage.changeDeliveryCountry(Props.getProp(country));
		myBagPage.clickPrimarySecureCheckoutButton();
		if (!Props.getProp(country).equalsIgnoreCase("australia"))
		{
			checkoutAndPaymentsPage.selectDeliveryOrCollectionOption("DELIVERY");
		}
		checkoutAndPaymentsPage.fillDeliveryAddressUsingPostcode(postalCode);
		checkOutPage.pause(5000);
		checkOutPage.selectAvailableDelivery();
		try
		{
			checkOutPage.continueToPaymentButton().click();
		}
		catch (Exception e)
		{
			LOG.info("Continue to payment button not available.");
		}
	}

	@And("^Error message \"([^\"]*)\" for expiry month as \"([^\"]*)\" and year as \"([^\"]*)\"$")
	public void errorMessageDisplayedInvalidYear(String msgStatus, String month, String year)
	{
		checkoutAndPaymentsPage.verifyStoreCardInvalidExpiryErrorMessageDisplayed(msgStatus, month, year);
	}

	@And("^Collection details error messages are \"([^\"]*)\"$")
	public void collectionDetailsErrorMessagesAre(String displayedOrNot)
	{
		switch (displayedOrNot.toLowerCase())
		{
			case "not displayed":
				Assert.assertFalse(checkoutAndPaymentsPage.checkCollectionDetailsErrorMessagesDisplayed());
				break;
			case "displayed":
				Assert.assertTrue(checkoutAndPaymentsPage.checkCollectionDetailsErrorMessagesDisplayed());
				break;
			case "default":
				Assert.fail("Not able to validate collection error messages");
		}
	}

	@And("^previous billing details and change card button displayed$")
	public void changeCardDetailsButtonDisplayed()
	{
		checkOutPage.storeCard().click();
		checkOutPage.pause(3000);
		checkoutAndPaymentsPage.verifyStoreCardBillingDetailsDisplayed();
		checkoutAndPaymentsPage.verifyStoreCardChangeCardButtonDisplayed();
	}

	@Then("collection method is disabled on checkout page")
	public void collectionMethodDisabled()
	{
		assertTrue(checkOutPage.collectionMethodDisabled());
	}
	
	
	@And("check text message displayed on collection disabled \"([^\"]*)\"$")
	public void verifyTextMessageOnCollectionDisabledMethod(String searchTerm)
	{
		assertTrue(checkOutPage.getCollectionMethodDisabledErrorText(Props.getProp(searchTerm)));

	}
	
	@Then("collection method is enabled on checkout page")
	public void collectionMethodEnabled()
	{
		assertFalse(checkOutPage.collectionMethodDisabled());
	}
	
	@Then("home delivery method is enabled on checkout page")
	public void homeDeliveryMethodEnabled()
	{
		assertTrue(checkOutPage.homeDeliveryMethodEnabled());
	}
	
	@Then("home delivery method is selected on checkout page")
	public void homeDeliveryMethodSelected()
	{
		assertTrue(checkOutPage.homeDeliveryMethodSelectedDefault());
	}
	
	@And("fetch all delivery options displayed and verified it is \"([^\"]*)\"$")
	public void verifyAllDeliveryOptionsDisplayed(String searchTerm)
	{
		assertTrue(checkoutAndPaymentsPage.getDeliveryOptionText(Props.getProp(searchTerm)));

	}
	
	@And("change the feature flag \"([^\"]*)\" for the feature name \"([^\"]*)\"$")
    public void  setFeatureCategoryManagementoFalse(String flagStatus, String featureName){	
		CheckoutAndPaymentsPage.setFeatureStatus(flagStatus, featureName);
	    }
	
}
