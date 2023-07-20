package com.salmon.test.step_definitions.gui.when;

import com.salmon.test.framework.WebElementNotFoundException;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.models.gui.CheckoutModel;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.properties.CardProperties;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static com.salmon.test.page_objects.gui.Features.CHECKOUT_USE_CARD_CTA_FEATURE;
import static com.salmon.test.utils.SeleniumUtils.clearFieldAndType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutsAndPaymentsWhenStepDefs
{
	@Autowired
	private HomePage homePage;
	@Autowired
	private PdpPage productDetailsPage;
	@Autowired
	private PlpPage productListPage;
	@Autowired
	private MyBagPage myBagPage;
	@Autowired
	private CheckoutAndPaymentsPage checkoutAndPaymentsPage;
	@Autowired
	private CreateNewAccountPage createNewAccountPage;
	@Autowired
	private CheckoutLoginPage checkoutLoginPage;
	@Autowired
	private NewLookHelper newLookHelper;
	@Autowired
	private AddNewBillingAddressPopup addNewBillingAddressPopup;
	@Autowired
	private CheckOutPage checkOutPage;
	@Autowired
	private CheckoutModel checkoutModel;

//	public CheckoutsAndPaymentsWhenStepDefs(final HomePage homePage,
//	                                        final PdpPage productDetailsPage,
//	                                        final PlpPage productListPage,
//	                                        final MyBagPage myBagPage,
//	                                        final CheckoutAndPaymentsPage checkoutAndPaymentsPage,
//	                                        final CreateNewAccountPage createNewAccountPage,
//	                                        CheckoutLoginPage checkoutLoginPage,
//	                                        final NewLookHelper newLookHelper,
//	                                        final AddNewBillingAddressPopup addNewBillingAddressPopup,
//	                                        final CheckOutPage checkOutPage,
//	                                        final CheckoutModel  checkoutModel)
//	{
//		this.homePage = homePage;
//		this.productDetailsPage = productDetailsPage;
//		this.productListPage = productListPage;
//		this.myBagPage = myBagPage;
//		this.checkoutAndPaymentsPage = checkoutAndPaymentsPage;
//		this.createNewAccountPage = createNewAccountPage;
//		this.checkoutLoginPage = checkoutLoginPage;
//		this.newLookHelper = newLookHelper;
//		this.addNewBillingAddressPopup = addNewBillingAddressPopup;
//		this.checkOutPage = checkOutPage;
//		this.checkoutModel = checkoutModel;
//	}


	@When("^Select a product and navigate to payment page$")
	public void selectAProductAndNavigateToPaymentPage()
	{
		newLookHelper.searchAndSelectProductThenCheckout();
	}

	@And("^Select a product and navigate to payment page with product \"([^\"]*)\"$")
	public void selectAProductAndNavigateToPaymentPage(String searchTerm) throws Throwable
	{
		newLookHelper.searchAndSelectProductThenCheckout(searchTerm);
	}

	@When("^Select a product and navigate to payment page on mobile")
	public void selectAProductAndNavigateToPaymentPageOnMobile()
	{
		homePage.mobileInsertTextAndSearch(Props.getProp("productCode_brand"));
		homePage.selectRandomProduct();
		productDetailsPage.selectProductAndMoveToMyBagPage();
		//checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
		myBagPage.clickPrimarySecureCheckoutButton();
	}

	@And("^Select to change billing address$")
	public void selectToChangeBillingAddress() throws WebElementNotFoundException
	{
		checkoutAndPaymentsPage.
				selectChangeBillingAddressOption("COLLECTION",
						"no",
						"CREDITCARD");
	}

	@And("^navigates to payment page$")
	public void navigatesToPaymentPage()
	{
		productDetailsPage.selectProductAndMoveToMyBagPage();

	}

	@And("^add new address is selected$")
	public void addNewAddressIsSelected() throws WebElementNotFoundException
	{
		checkoutAndPaymentsPage.selectChangeBillingAddressOption("COLLECTION", "no", "CREDITCARD");
		checkoutAndPaymentsPage.selectOptionInCheckoutPaymentPopup("ADDNEWADDRESS");
	}


	@Given("^user opted DELIVERY with post code (.*)$")
	public void optDelivery(String postCode) throws Exception
	{
		checkoutAndPaymentsPage.selectDeliveryOrCollectionOption("DELIVERY");
		checkoutAndPaymentsPage.addDeliveryAddress(postCode);
	}

	@Then("^New Look should be the first store in the result$")
	public void new_Look_should_be_the_first_store_in_the_result() throws InterruptedException
	{

		assertTrue(checkoutAndPaymentsPage.checkStoreDisplaySequence());
	}

	@And("^select collection button and enter (.*) in search field$")
	public void selectCollectionButtonAndEnterLondonInSearchField(String location)
	{
		checkoutAndPaymentsPage.selectDeliveryOrCollectionOption("COLLECTION");
		checkoutAndPaymentsPage.searchForCollectionLocations(location);
		checkoutAndPaymentsPage.pause(2000);
	}

	@And("^click checkout page pin in the map$")
	public void clickCheckoutPagePinInTheMap()
	{
		checkoutAndPaymentsPage.clickTheFirstResultPinOfCheckoutPageMap();
	}

	@And("^Select delivery option and provide europe shipping details \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void selectDeliveryOptionAndProvideEuropeShippingDetails(String title,
	                                                                String firstName,
	                                                                String lastName,
	                                                                String postCode,
	                                                                String addressLine1,
	                                                                String addressLine2,
	                                                                String city)
	{
		checkoutAndPaymentsPage.selectDeliveryOrCollectionOption("DELIVERY");
		checkoutAndPaymentsPage.addDeliveryAddressManually(postCode, addressLine1, addressLine2, city);
	}

	@And("^make payment with credit card$")
	public void makePaymentWithCreditCard()
	{
		checkoutAndPaymentsPage.fillCardDetails();
	}

	@And("^make payment with credit card with PCI form on$")
	public void makePaymentWithCreditCardPCI()
	{
		checkoutAndPaymentsPage.fillCardDetails();
	}

	@Then("^there is no further form to capture CVV$")
	public void checkNoCVVFormOnPage()
	{
		Assert.assertFalse(checkoutAndPaymentsPage.isCVVFormVisible());
	}

	@And("^the card details is shown with the expiry date and last 4 digits of the card number$")
	public void checkExpiryDateCardNumberDisplayed()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.findExpiryDateDisplay());
	}

	@And("^entering new card details and (.*) 'Save card' check box$")
	public void enterCreditCardAndSaveCardPCI(String selection)
	{
		checkoutAndPaymentsPage.fillCardDetailsSaveCardPCIForm(selection);
	}

	@And("^user (.*) see the added card ending with number (\\d+)$")
	public void userSeeTheAddedCardEndingWith(String should, String cardEnding)
	{
		if (should.equals("should"))
		{
			assertThat(checkoutAndPaymentsPage.verifyAddedCardDetailOnPCIForm(cardEnding)).isTrue();
		}
		else
		{
			assertThat(checkoutAndPaymentsPage.verifyAddedCardDetailOnPCIForm(cardEnding)).isFalse();
		}
	}

	@And("^Made payment with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void madePaymentWith(String cardType,
	                            String cardNumber,
	                            String date,
	                            String year,
	                            String cvv) throws WebElementNotFoundException
	{
		checkoutAndPaymentsPage.fillCardDetails(cardType, cardNumber, date, year, cvv);
		checkoutAndPaymentsPage.placeOrder();
	}

	@And("^select (.*) as option$")
	public void selectDELIVERYAsOption(String option)
	{
		checkoutAndPaymentsPage.selectDeliveryOrCollectionOption(option);
	}

	@When("^navigate to bag page and secure checkout$")
	public void navigateToBagPageAndSecureCheckout() throws Throwable
	{
		homePage.clickBagIcon();
		myBagPage.clickPrimarySecureCheckoutButton();
	}

	@And("^user clicks on order summary on mobile$")
	public void userClicksOnOrderSummaryOnMobile()
	{
		checkoutAndPaymentsPage.clickOrderSummaryTitleMobile();
	}

	@And("^user clicks on choose another collection point link$")
	public void userClicksOnChooseAnotherCollectionPointLink()
	{
		checkoutAndPaymentsPage.clickChooseAnotherCollectionPoint();
	}

	@And("^user clicks on choose another collection point button$")
	public void userClicksOnChooseAnotherCollectionPointButton()
	{
		checkoutAndPaymentsPage.clickChooseAnotherCollectionPointButton();
	}

	@Then("^user verifies if saved collections popup is displayed at checkout page$")
	public void userVerifiesIfSavedCollectionPopupIsDisplayedAtCheckoutPage() throws Exception
	{
		assertEquals("Saved Collection Points", checkoutAndPaymentsPage.getTextOfSavedCollectionPopup());
	}

	@And("^Select payment with virtual card$")
	public void selectPaymentWithVirtualCard()
	{
		checkoutAndPaymentsPage.clickPayWithKlarnaBtn();
	}

	@And("^Select payment with credit card$")
	public void selectPaymentWithCreditCard()
	{
		checkoutAndPaymentsPage.clickCreditDebitCardButton();
	}


	@And("^Add billing address - guest user$")
	public void addBillingAddressAndCheckAge()
	{
		checkoutAndPaymentsPage.addDeliveryAddressGuestUser();
	}

	@And("^Click on change address link to change the address with \"([^\"]*)\"$")
	public void clickOnChangeAddressLinkToChangeTheAddressWith(String postcode)
	{
		checkoutAndPaymentsPage.selectChangeBillingAddress();
		checkoutAndPaymentsPage.selectOptionInCheckoutPaymentPopup("ADDNEWADDRESS");
		addNewBillingAddressPopup.updateBillingAddress(postcode);
	}

	@And("^Select a product \"([^\"]*)\" and navigate to payment page$")
	public void selectAProductAndNavigateToPaymentPageOnMobile(String productCode)
	{
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(productCode);
		}
		else
		{
			homePage.searchForProduct(productCode);
		}
		homePage.selectRandomProduct();
		productDetailsPage.selectProductAndMoveToMyBagPage();
		checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
		myBagPage.clickPrimarySecureCheckoutButton();

	}

	@And("^Edit bag with quantity \"([^\"]*)\"$")
	public void clickOnEditBagLink(String quantity)
	{
		if (IS_MOBILE)
		{
			checkoutAndPaymentsPage.clickEditBag();
			checkOutPage.selectQuantityOfLineItem(("Qty: " + quantity),1);
		}
		else
		{
			checkoutAndPaymentsPage.clickEditBag();
			checkOutPage.selectQuantityOfLineItem(quantity,1);
		}
		checkOutPage.checkOutButton().click();
	}

	@And("^Reload checkout page$")
	public void reloadPage()
	{
		checkOutPage.refresh();
	}

	@And("^fill in mobile number$")
	public void fillInMobileNumber()
	{
		checkOutPage.enterMandatoryMobileField();
	}

	@And("^user confirms over the age of 18")
	public void userConfirmsOverTheAgeOf()
	{
		checkOutPage.verifyOver18Age();
	}

	@And("^user confirms over the age of 18 of store card")
	public void userConfirmsOverTheAgeOfStoreCard()
	{
		checkOutPage.clickOver18CheckBox(0);
	}

	@And("^customer adds another card$")
	public void customerAddsAnotherCard()
	{
		checkoutAndPaymentsPage.addAnotherCard();
	}

	@And("^selects change card$")
	public void selectsChangeCard()
	{
		checkoutAndPaymentsPage.changeCard();
	}

	@When("^user confirms the card from the overlay$")
	public void userConfirmsTheCardFromTheOverlay()
	{
		checkoutAndPaymentsPage.useThisPaymentCard();
	}

	@When("^I type (a|an) \"([^\"]*)\" card number$")
	public void iTypeACardNumber(final String ignore, final String cardType)
	{
		clearFieldAndType(checkoutAndPaymentsPage.getCardNumberField(), CardProperties.cardNumber(cardType));
	}

	@Then("^The use this card button is disabled$")
	public void theUseThisCardButtonIsDisabled()
	{
		Assert.assertFalse(checkoutAndPaymentsPage.getUseThisCardButton().isEnabled());
	}

	@Given("^I type (\\d+) digits in the CVV field$")
	public void iTypeDigitsInTheCVVField(final int count)
	{
		clearFieldAndType(checkoutAndPaymentsPage.getCvvField(), RandomGenerator.randomInteger(count));
	}

	@And("^There are (\\d+) digits in the CVV field$")
	public void thereAreDigitsInTheCVVField(final int count)
	{
		Assert.assertEquals(checkoutAndPaymentsPage.getCvvField().getAttribute("value").length(),
				count);
	}

	@And("^I type (a|an) \"([^\"]*)\" expiry date$")
	public void iTypeAnExpiryDate(final String ignore, final String cardType)
	{
		clearFieldAndType(checkoutAndPaymentsPage.getExpiryDateMonthField(), CardProperties.cardExpiryMonth(cardType));
		clearFieldAndType(checkoutAndPaymentsPage.getExpiryDateYearField(), CardProperties.cardExpiryYear(cardType));
	}

	@And("^I type (a|an) \"([^\"]*)\" CVV$")
	public void iTypeACVV(final String ignore, final String cardType)
	{
		clearFieldAndType(checkoutAndPaymentsPage.getCvvField(), CardProperties.cardCvv(cardType));
	}

	@Then("^The use this card button is enabled$")
	public void theUseThisCardButtonIsEnabled()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.getUseThisCardButton().isEnabled());
	}

	@When("^I fill in \"([^\"]*)\" card details$")
	public void iFillInCardDetails(final String card)
	{
		if (checkoutAndPaymentsPage.isPCIFeatureFlagDisabled())
		{
			checkoutAndPaymentsPage.fillCardDetails(CardProperties.cardNumber(card + ".legacy"));
			checkoutAndPaymentsPage.enterCVVNumber(CardProperties.cardCvv(card + ".legacy"));
		}
		else
		{
			iTypeACardNumber("", card);
			iTypeAnExpiryDate("", card);
			iTypeACVV("", card);
		}
	}

	@When("^I fill in \"([^\"]*)\" card details and place an order$")
	public void iFillInCardDetailsAndPlaceAnOrder(final String card)
	{
		iFillInCardDetails(card);
		if (!checkoutAndPaymentsPage.isPCIFeatureFlagDisabled())
		{
			iClickOnTheUseThisCardButton();
		}
		iCheckTheAgeConfirmationCheckbox();
		iClickOnThePlaceOrderButton();
	}

	@And("^I click on the use this card button$")
	public void iClickOnTheUseThisCardButton()
	{
		checkoutAndPaymentsPage.getUseThisCardButton().click();
		checkoutAndPaymentsPage.pause(5000);
	}

	@And("^I check the age confirmation checkbox$")
	public void iCheckTheAgeConfirmationCheckbox()
	{
		checkoutAndPaymentsPage.checkAgeConfirmationCheckbox();
	}

	@And("^I click on the place order button$")
	public void iClickOnThePlaceOrderButton()
	{
		checkoutAndPaymentsPage.clickPlaceOrderButton();
	}

	@And("^I verify my 3DS card$")
	public void iVerifyMy3DSCard()
	{
		checkoutAndPaymentsPage.verify3DSCard();
	}

	@And("^select first Available collection Store$")
	public void selectFirstAvailableCollectionStore()
	{
		checkoutAndPaymentsPage.selectTheFirstCollectionOptionFromList();
	}

	@And("^I select klarna payment option$")
	public void iSelectKlarnaPaymentOption() throws InterruptedException
	{
		checkOutPage.clickByJavaScriptExecutor(checkOutPage.klarna());
		Thread.sleep(5000);
	}

	@Then("^default delivery option should be selected$")
	public void defaultDeliveryOptionSelected()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.getFirstDeliverySelectedOrNot());
	}

	@And("^select delivery method as \"([^\"]*)\" and location as \"([^\"]*)\"$")
	public void selectDeliveryMethodAndLocationAs(String deliveryMethod, String location)
	{
		if(deliveryMethod.equalsIgnoreCase("Collection")){
			checkoutAndPaymentsPage.selectDeliveryOrCollectionOption(deliveryMethod);
			checkoutAndPaymentsPage.searchForCollectionLocations(location);
			checkoutAndPaymentsPage.pause(2000);
			checkoutAndPaymentsPage.selectCollectionType("Click & Collect Standard", checkoutModel);
		}
		else{
			checkoutAndPaymentsPage.selectDeliveryOrCollectionOption(deliveryMethod);
		}
	}

	@And("^Select (.*) single size product and navigate to payment page")
	public void selectAProductAndNavigateToPaymentPage1(String searchTerm) throws Throwable
	{
		newLookHelper.searchSingleSizeProductThenCheckout(searchTerm);
	}

	@And("^the messaging is displayed above the delivery options per the design$")
	public void theMessagingDisplayed() {
		Assert.assertEquals(checkOutPage.getMessageForStoreOnlyProduct(),Props.getProp("store_only_message_checkout"));
	}
	@And("^the label Not Available is specified on the COLLECTION delivery option$")
	public void theLevelNotAvailableForCollectionDelivery() {
		Assert.assertEquals(checkOutPage.getMessageCollectionNotAvailable(),Props.getProp("collection_not_available_message"));


	}

    @Then("^Paypal return information \"([^\"]*)\"$")
    public void paypalReturnInformationDisplayed(String status) {
	    checkoutAndPaymentsPage.isPaypalReturnInformationDisplayed(status);
    }

	 @Then("^PayPal CTA button is \"([^\"]*)\"$")
	 public void paypalCtaButtonIsActive(String status) {
		 checkoutAndPaymentsPage.isPaypalCtaButtonActive(status);
	 }

	@Then("^click on edit bag link$")
	public void clickEditBagLink() {
		checkoutAndPaymentsPage.clickEditBag();
	}

}
