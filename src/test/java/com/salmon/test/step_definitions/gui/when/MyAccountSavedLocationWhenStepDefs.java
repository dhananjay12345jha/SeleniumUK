package com.salmon.test.step_definitions.gui.when;


import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.CheckOutPage;
import com.salmon.test.page_objects.gui.CheckoutAndPaymentsPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.MyAccountPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;

public class MyAccountSavedLocationWhenStepDefs
{
	@Autowired
	private MyAccountPage myAccountPage;
	@Autowired
	private CheckoutAndPaymentsPage checkoutAndPaymentsPage;
	@Autowired
	private HomePage homePage;
	@Autowired
	private CheckOutPage checkoutPage;
//
//	public MyAccountSavedLocationWhenStepDefs(final MyAccountPage myAccountPage,
//	                                          final CheckoutAndPaymentsPage checkoutAndPaymentsPage, final HomePage homePage, final CheckOutPage checkoutPage)
//	{
//		this.myAccountPage = myAccountPage;
//		this.checkoutAndPaymentsPage = checkoutAndPaymentsPage;
//		this.homePage = homePage;
//		this.checkoutPage = checkoutPage;
//	}

	@When("^customer navigated to my location page$")
	public void customerNavigatedToMyLocationPage()
	{
		if(IS_MOBILE){
			homePage.navigateToMyAccountFromHeaderOnMobile();
		}
		else{
			homePage.navigateToMyAccountPage();
		}
		myAccountPage.selectMyAccountLinks("SAVED COLLECTION POINTS");
	}

	@When("^customer navigated to my addresses page$")
	public void customerNavigatedToMyAddressesPage()
	{
		if(IS_MOBILE){
			homePage.navigateToMyAccountFromHeaderOnMobile();
		}
		else{
			homePage.navigateToMyAccountPage();
		}
		myAccountPage.selectMyAccountLinks(Props.getMessage("navigation.account.myAddresses"));
	}

	@And("^Select collection location$")
	public void selectCollectionLocation()
	{
		checkoutAndPaymentsPage.selectDeliveryOrCollectionOption("COLLECTION");


	}

	@And("^Customer makes a purchase to collect (.*)$")
	public void customerMakesAPurchaseToCollect(String paymentMethod) throws Throwable
	{
		checkoutAndPaymentsPage.selectDeliveryOrCollectionOption("COLLECTION");
		checkoutAndPaymentsPage.selectCollectionPointFromTheSearchDropdown();
		checkoutPage.collectionDetails();
		checkoutPage.enterMandatoryMobileField();
		checkoutPage.continueToPaymentSection();
		checkoutAndPaymentsPage. selectPayWithOption(paymentMethod);
		checkoutAndPaymentsPage.addBillingAddress("London");
		checkoutAndPaymentsPage.fillCardDetails();
		checkoutAndPaymentsPage.placeOrder();
	}

}
