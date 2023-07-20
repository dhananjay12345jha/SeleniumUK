package com.salmon.test.step_definitions.gui;


import java.util.ArrayList;
import java.util.List;

import com.salmon.test.page_objects.gui.PromotionsPage;

import org.junit.Assert;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.CheckOutPage;
import com.salmon.test.page_objects.gui.CheckoutAndPaymentsPage;
import com.salmon.test.page_objects.gui.CheckoutLoginPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.MyBagPage;
import com.salmon.test.page_objects.gui.NewLookHelper;
import com.salmon.test.page_objects.gui.PdpPage;
import com.salmon.test.page_objects.gui.SignInPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BagStepDefinitions
{

	@Autowired
	private MyBagPage myBagPage;
	@Autowired
	private CheckOutPage checkOutPage;
	@Autowired
	private HomePage homePage;
	@Autowired
	private PdpPage productDetailsPage;
	@Autowired
	private CheckoutLoginPage checkoutLoginPage;
	@Autowired
	private CheckoutAndPaymentsPage checkoutAndPaymentsPage;
	@Autowired
	private SignInPage signInPage;
	@Autowired
	private NewLookHelper newLookHelper;
	@Autowired
	private PromotionsPage promotionsPage;

	@Then("^remove all the added items$")
	public void remove_all_the_added_items() throws Exception
	{
		homePage.clickBagIcon();
		myBagPage.removeAllItems();

	}

	@And("^user changed delivery (.*)$")
	public void userChangedDeliveryCountry(String deliveryCountry)
	{
		homePage.changeDeliveryCountry(deliveryCountry);
	}

	@And("^customer select product and navigate to my bag page$")
	public void customerSelectProductAndNavigateToMyBagPage()
	{
		homePage.searchForProductUsingRandomProductCode();
		homePage.selectRandomProduct();
		productDetailsPage.selectProductAndMoveToMyBagPage();
	}

	@When("^navigated to my bag page$")
	public void navigatedToMyBagPage()
	{
		productDetailsPage.pause(4000);
		productDetailsPage.clickMyBagAndCheckoutButton();
	}

	@Then("^trolley rocket (exists|doesn't exist)$")
	public void trolleyRocketExists(String value)
	{
		if (value.equalsIgnoreCase("exists"))
		{
			assertTrue("Trolley Rocket Doesn't Exist", myBagPage.getTrolleyRocketExist());
		}
		else
		{
			assertFalse("Trolley Rocket Doesn't Exist", myBagPage.getTrolleyRocketExist());
		}
	}

	@Then("^delivery threshold message \"([^\"]*)\" (is|is not) displayed$")
	public void deliveryThresholdMessageDisplayed(String message, String displayedOrNot)
	{
		if (displayedOrNot.equals("is"))
		{
			assertTrue(myBagPage.getDeliveryThresholdMessage().contains(message));
		}
		else if (displayedOrNot.equals("is not"))
		{
			assertNull(myBagPage.getDeliveryThresholdMessage());
		}
		else
		{
			Assert.fail("Delivery Threshold Message Verification Failed.");
		}
	}

	@Then("^delivery qualified message \"([^\"]*)\" (is|is not) displayed$")
	public void deliveryQualifiedMessageDisplayed(String message, String displayedOrNot)
	{
		if (displayedOrNot.equals("is"))
		{
			assertTrue(myBagPage.getDeliveryQualifiedMessage().contains(message));
		}
		else if (displayedOrNot.equals("is not"))
		{
			assertNull(myBagPage.getDeliveryQualifiedMessage());
		}
		else
		{
			Assert.fail("Delivery Qualified Message Verification Failed.");
		}
	}

	@And("^customer search for multi coloured product and navigate to my bag page$")
	public void customerSearchForMultiColouredProductAndNavigateToMyBagPage()
	{
		homePage.searchForProduct(Props.getProp("productCode_MultiColour"));
		homePage.selectRandomProduct();
		productDetailsPage.selectProductAndMoveToMyBagPage();
	}

	@And("^customer search for multi coloured product and navigate to my bag page on mobile$")
	public void customerSearchForMultiColouredProductAndNavigateToMyBagPageOnMobile()
	{
		homePage.mobileInsertTextAndSearch(Props.getProp("productCode_MultiColour"));
		homePage.selectRandomProduct();
		productDetailsPage.selectProductAndMoveToMyBagPage();
	}

	@When("^I click the 'Change Colour' link$")
	public void I_click_the_Change_Colour_link()
	{

		checkOutPage.changeColorLink().click();
	}

	@When("^customer change the product colour$")
	public void customerChangeTheProductColour()
	{
		myBagPage.changeProductColour();
	}

	@Then("^product code will change with the colour change$")
	public void productCodeWillChangeWithTheColourChange()
	{
		String productCodeBefore = myBagPage.getProductCode();
		myBagPage.pause(4000);
		myBagPage.setProductCode();
		myBagPage.pause(2000);
		Assert.assertNotEquals(productCodeBefore, myBagPage.getProductCode());

	}

	@And("^customer search for product (.*) with size (.*)")
	public void customerSearchForMultiSizeLProductAndAddToBag(String productCode, String size)
	{
		newLookHelper.searchForProductAndSelectSize(productCode, size);
	}

	@And("^customer search for low stock product (.*) with size (.*)")
	public void customerSearchForMultiSizeLProductAndAddToBagLowStock(String productCode, String size)
	{
		newLookHelper.searchForProductAndSelectSizeLowStock(productCode, size);
	}

	@And("^mobile customer search for product \"([^\"]*)\" with size \"([^\"]*)\"$")
	public void mobileCustomerSearchForProductWithSize(String product, String size) throws Throwable
	{
		homePage.mobileInsertTextAndSearch(Props.getProp(product.toString()));
		homePage.selectRandomProduct();
		if (!size.equalsIgnoreCase("one size"))
		{
			productDetailsPage.selectSizeOfTheProduct(size);
		}
		productDetailsPage.clickAddToBagButton();
	}

	@Then("^my bag should consist$")
	public void myBagShouldConsist(DataTable text)
	{
		List<String> displayedItems = myBagPage.getMyBagHeaderAndItemsAddedInMyBagPage();
		List<String> expectedLabels = new ArrayList<>();
		for (int i = 0; i < text.cells().size(); i++)
		{
			expectedLabels.add(text.cells().get(i).toString().replace("]", "").replace("[", ""));
		}
		Assert.assertEquals(myBagPage.normaliseList(expectedLabels).toString(), myBagPage.normaliseList(displayedItems).toString().replaceAll("\\s+", " "));
	}

	@Then("^my bag should consist mobile$")
	public void myBagShouldConsistMobile(DataTable dataTable)
	{
		List<String> displayedItems = myBagPage.getMyBagHeaderAndItemsAddedInMyBagPage();
		for (List<String> expectedItems : dataTable.cells())
		{
			assertThat(displayedItems).contains(expectedItems.toString().replace("]", "").replace("[", ""));
		}
	}

	@Then("^my bag header should display (.*)$")
	public void myBagHeaderShouldDisplayCount(String count)
	{
		Assert.assertEquals(count, myBagPage.getMyBagHeader());
	}

	@And("^provide guest user details$")
	public void provideGuestUserDetails()
	{
		checkoutLoginPage.enterEmailAddressForGuestCheckoutField();
		checkoutLoginPage.clickGuestCheckoutButton();
	}

	@Then("^change the \"([^\"]*)\" in the delivery country address form the dropdown of checkout page$")
	public void change_the_in_the_delivery_country_address_form_the_dropdown_of_checkout_page(String deliveryCountry) throws Throwable
	{
		checkoutAndPaymentsPage.changeDeliveryCountry(deliveryCountry);
	}

	@And("^navigate to checkout and payments page$")
	public void navigateToCheckoutAndPaymentsPage()
	{
		myBagPage.clickPrimarySecureCheckoutButton();
	}

	@And("^change delivery country to (.*) in my bag page$")
	public void changeCountryMybagPage(String country) throws InterruptedException
	{
		myBagPage.changeTheDeliveryCountry(country);
	}

	@When("^search and add a product to bag$")
	public void searchAndAddAProductToBag() throws Exception
	{
		newLookHelper.searchAndAddAProductToBasket("340926949");
	}

	@And("^click on checkout button from my bag page$")
	public void clickOnCheckoutButtonFromMyBagPage() throws Exception
	{
		myBagPage.getSecureCheckoutPrimaryButton().click();
	}

	@When("^search and add a product to bag (.*)$")
	public void searchAndAddAProductToBag(String product)
	{
		newLookHelper.searchAndAddAProductToBasket(product);
		checkOutPage.basketIcon().click();
		checkOutPage.pause(5000);
	}

	@And("^I click edit on mobile for line item (\\d+)$")
	public void iClickEditOnMobile(int lineItem)
	{
		myBagPage.clickEditForLineItemMobile(lineItem);
	}

	@And("^customer select product and navigate to my bag page on mobile$")
	public void customerSelectProductAndNavigateToMyBagPageOnMobile()
	{
		homePage.mobileInsertTextAndSearch(Props.getProp("productCode_stock"));
		homePage.selectRandomProduct();
		productDetailsPage.selectProductAndMoveToMyBagPage();
	}

	@And("^click on Change country CTA in the bag$")
	public void clickOnChangeCountryCTAInTheBag()
	{
		myBagPage.clickChangeCountryLink();
	}

	@Then("^user see the Brexit message displayed above the 'APPLY CHANGE' CTA$")
	public void userSeeTheBrexitMessageDisplayedAboveTheAPPLYCHANGECTA()
	{
		assertFalse("Brexit message is not displayed on bag page", myBagPage.getBrexitMessage().isEmpty());
	}

	@Then("^message \"([^\"]*)\" should be displayed$")
	public void messageShouldBeDisplayed(String message) throws Throwable
	{
		String realMessage = myBagPage.getStockUrgMsg();
		Assert.assertEquals(realMessage, message);
	}

	@Then("^user should see sign in or create account prompt$")
	public void userShouldSeeSignInOrCreateAccountPrompt()
	{
		myBagPage.pause(2000);
		Assert.assertTrue(signInPage.logInSection().isDisplayed());
		Assert.assertTrue(signInPage.loginForm().isDisplayed());
	}

	@And("^customer search for product (.*) with text (.*) in size dropdown")
	public void customerSearchForMultiSizeLProduct(String productCode, String size)
	{
		newLookHelper.searchForProductAndSelectBySizeText(productCode, size);
	}

	@Then("^secure checkout CTA displayed$")
	public void secureCheckoutCtaDisplayed() {
		Assert.assertTrue("Secure Checkout CTA button not displayed at right",myBagPage.getSecureCheckoutPrimaryButton().isDisplayed());
	}

	@And("^\"([^\"]*)\" user can navigate to checkout quicker$")
	public void userCanNavigateToCheckoutQuicker(String userType) {
		myBagPage.clickPrimarySecureCheckoutButton();
		myBagPage.pause(3000);
		switch (userType.toUpperCase())
		{
			case "REGISTERED":
			case "NEW":
				Assert.assertTrue("Checkout page not displayed",checkOutPage.checkPageTitle("Multi Checkout Summary Page | New Look UK"));
				break;
			case "GUEST":
				Assert.assertTrue("Checkout-Login page not displayed",checkOutPage.checkPageTitle("Checkout-Login Page | New Look UK"));
				break;
		}
	}

	@And("^have a promo code dropdown displayed$")
	public void haveAPromoCodeDropdownDisplayed() {
		Assert.assertTrue("Have a promo code dropdown not displayed",promotionsPage.havePromoCodeLabel().isDisplayed());
	}

	@And("^click on have a promo code dropdown$")
	public void clickOnHaveAPromoCodeDropdown() {
		promotionsPage.havePromoCodeLabel().click();
		myBagPage.pause(3000);
	}

	@Then("^promo code \"([^\"]*)\" message displayed$")
	public void promoCodeMessageDisplayed(String messageType) {
		myBagPage.verifyPromoCodeMessageDisplayed(messageType);
	}

	@And("^total amount displayed in sticky$")
	public void totalAmountDisplayed() {
		Assert.assertTrue("Total amount not displayed",myBagPage.totalAmountInSticky().isDisplayed());
	}

	@And("^delivering to country displayed$")
	public void deliveringToCountryDisplayed() {
		Assert.assertTrue("Delivering to Country not displayed",myBagPage.deliveringToCountry().isDisplayed());
	}

	@And("^delivery information displayed$")
	public void deliveringInformationDisplayed() {
		Assert.assertTrue("Delivering information not displayed",myBagPage.deliveryInformation().isDisplayed());
	}

	@And("^applied discount displayed$")
	public void appliedDiscountDisplayed() {
		Assert.assertTrue("Applied discount not displayed",myBagPage.discountedAmount().isDisplayed());
	}

	@And("^out of stock message is displayed on cart page$")
	public void outOfStockMessageIsDisplayedOnCartPage() {
		assertTrue(myBagPage.outOfStockCartMessage());
	}

}
