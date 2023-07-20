package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.Credentials;
import com.salmon.test.page_objects.gui.*;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DeliveryPassStepDefinitions extends PageObject
{
	Credentials credentials;
	//    private LandingPage landingPage;
	@Autowired
	private DeliveryPassPage deliveryPassPage;
	private String userName = "autotester@example.com";
	private String password = "password123";
	@Autowired
	private SignInPage signInPage;
	@Autowired
	private CreateNewAccountPage createNewAccountPage;
	@Autowired
	private HomePage homePage;
	@Autowired
	private MyAccountPage myAccountPage;
	@Autowired
	private CheckOutPage checkOutPage;
	@Autowired
	private CheckoutAndPaymentsPage checkoutAndPaymentsPage;

//	public DeliveryPassStepDefinitions(final DeliveryPassPage deliveryPassPage,
//	                                   final SignInPage signInPage,
//	                                   final CreateNewAccountPage createNewAccountPage,
//	                                   final HomePage homePage,
//	                                   final MyAccountPage myAccountPage,
//	                                   final CheckOutPage checkOutPage,
//	                                   final CheckoutAndPaymentsPage checkoutAndPaymentsPage)
//	{
//		this.deliveryPassPage = deliveryPassPage;
//		this.signInPage = signInPage;
//		this.createNewAccountPage = createNewAccountPage;
//		this.homePage = homePage;
//		this.myAccountPage = myAccountPage;
//		this.checkOutPage = checkOutPage;
//		this.checkoutAndPaymentsPage = checkoutAndPaymentsPage;
//	}

	@Given("^anonymous user navigates to delivery pass page$")
	public void anonymous_user_is_on_Delivery_Pass_page()
	{
		deliveryPassPage.goToDeliveryPassPage();
	}

	@Given("^user goes to Delivery Pass page$")
	public void user_goes_to_Delivery_Pass_page()
	{
		deliveryPassPage.goToDeliveryPassPage();
	}

	@Given("^add to bag button is (disabled|enabled)$")
	public void add_to_bag_button_is_disabled(String enabled)
	{
		if (enabled.contains("disabled"))
		{
			assertFalse(deliveryPassPage.add_to_bag_button().isEnabled());
		}
		else
		{
			assertTrue(deliveryPassPage.add_to_bag_button().isEnabled());
		}
	}

	@When("^user checks their bag$")
	public void user_checks_their_bag()
	{
		deliveryPassPage.gotoShoppingBag();
	}

	@Then("^Delivery Pass is present without attributes$")
	public void delivery_Pass_is_present_without_attributes()
	{
		pause(1500);
		assertTrue(deliveryPassPage.deliveryPassInBag(deliveryPassPage.itemColourList()));
		assertTrue(deliveryPassPage.deliveryPassInBag(deliveryPassPage.itemSizeList()));
		assertTrue(deliveryPassPage.deliveryPassInBag(deliveryPassPage.itemQuantityList()));
		assertFalse(deliveryPassPage.deliveryPassInBag(deliveryPassPage.itemPriceList()));
	}

	@Then("^delivery is free$")
	public void delivery_is_free()
	{
		assertEquals("free", deliveryPassPage.deliveryCost().getText().toLowerCase());
	}

	@Given("^customer already has an account$")
	public void customer_already_has_an_account()
	{
//        landingPage.anonymouslyGoToNewLookWebsite("en", "GBP", "UK");
//        loginPage.createAccount().click();
//        this.credentials = loginPage.registerNewUser();
		// UrlBuilder.startAtHomePage();
		if (IS_MOBILE)
		{
			homePage.mobileNavigateToLoginPage();
			homePage.clickCreateAnAccountButton();
		}
		else
		{
			homePage.navigateToCreateNewAccountPageByClickingHeaderLink();
		}
		this.credentials = createNewAccountPage.createNewAccountWithCorrectDetails();
	}

	@When("^customer registers$")
	public void customer_registers()
	{
		deliveryPassPage.register().click();
		//assertTrue(loginPage.logInSection().isDisplayed());
		//assertTrue(loginPage.registerForm().isDisplayed());
		createNewAccountPage.createNewAccountWithCorrectDetails();

	}

	@When("^customer signs in$")
	public void customer_signs_in()
	{
		deliveryPassPage.signIn().click();
		assertTrue(signInPage.logInSection().isDisplayed());
		assertTrue(signInPage.loginForm().isDisplayed());
		signInPage.signInUsingUserNameAndPassword(credentials.getEmail(), credentials.getPassword());
	}

	@Then("^user is on Delivery Pass page$")
	public void user_is_on_Delivery_Pass_page()
	{
		assertTrue(deliveryPassPage.delivery_pass_wrapper().isDisplayed());
	}

	@When("^customer adds Delivery Pass to bag$")
	public void customer_adds_Delivery_Pass_to_bag()
	{
		deliveryPassPage.add_to_bag_button().click();
	}

	@Then("^Delivery Pass has been added to bag$")
	public void delivery_Pass_has_been_added_to_bag()
	{
		assertTrue(deliveryPassPage.added().isDisplayed());
	}

	@When("^I signin on the checkout page$")
	public void iSigninOnTheCheckoutPage()
	{
		signInPage.signInUsingUserNameAndPassword(userName, password);
	}

	@And("^customer is logged out$")
	public void customerIsLoggedOut()
	{
		if (IS_MOBILE)
		{
			homePage.logOutFromAccountMobile();
			homePage.waitForPageLoad();
		}
		else
		{
			homePage.clickMyAccountButtonIfDisplayed();
			homePage.signOutOfAccount();
			homePage.getLogoutMessageDisplayed();
		}
	}

	@Then("^validate the delivery pass page$")
	public void validateTheDeliveryPassPage(DataTable text) throws Throwable
	{
		List<String> displayedTextInDeliveryPassLabels = deliveryPassPage.getDeliveryPassLabels();
		List<String> expectedLabels = new ArrayList<>();
		expectedLabels.add(text.cells().get(0).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.cells().get(1).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.cells().get(2).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.cells().get(3).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.cells().get(4).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.cells().get(5).toString().replace("]", "").replace("[", ""));
		Assert.assertEquals(deliveryPassPage.normaliseList(expectedLabels), deliveryPassPage.normaliseList(displayedTextInDeliveryPassLabels));
	}

	@Then("^delivery pass is applied$")
	public void deliveryPassIsApplied()
	{
		if (IS_MOBILE)
		{
			assertTrue(checkOutPage.getDeliveryPassAppliedText().equals("Delivery Pass Applied"));
		}
		else
		{
			int count = 0;
			while (count <= 4 && !checkOutPage.getDeliveryPassAppliedText().contains("Delivery Pass Applied - on account"))
			{
				checkOutPage.refresh();
				checkOutPage.pause(30000);
				checkoutAndPaymentsPage.selectDeliveryOrCollectionOption("delivery");
				count++;
			}
			assertTrue(checkOutPage.getDeliveryPassAppliedText().equals("Delivery Pass Applied - on account"));
		}
	}
}
