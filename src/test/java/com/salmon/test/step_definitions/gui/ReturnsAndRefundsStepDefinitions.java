package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.models.gui.OrderConfirmationModel;
import com.salmon.test.models.gui.TrackMyOrderModel;
import com.salmon.test.page_objects.gui.*;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;

import static org.junit.Assert.assertEquals;

/**
 * @author gokhan.ates
 */
@Slf4j
public class ReturnsAndRefundsStepDefinitions extends PageObject
{

	private TrackMyOrderModel trackMyOrderModel = new TrackMyOrderModel();
	private OrderConfirmationModel orderConfirmationModel =new OrderConfirmationModel() ;
	private AddNewBillingAddressPopup addNewBillingAddressPopup = new AddNewBillingAddressPopup();
	@Autowired
	private PdpPage productDetailsPage;
	@Autowired
	private MyBagPage myBagPage;
	@Autowired
    private LoginPage loginPage;
	@Autowired
    private CheckoutAndPaymentsPage checkoutAndPaymentsPage;
	@Autowired
    private HomePage homePage;
	@Autowired
    private CheckOutPage checkOutPage;
	@Autowired
    private MyOrdersPage myOrdersPage;
	@Autowired
    private TrackMyOrderPage trackMyOrderPage;
	@Autowired
    private MyAccountPage myAccountPage;
	@Autowired
    private OrderConfirmationPage orderConfirmationPage;



//    public ReturnsAndRefundsStepDefinitions(LoginPage loginPage, CheckoutAndPaymentsPage checkoutAndPaymentPage, HomePage homePage, CheckOutPage checkOutPage, MyOrdersPage myOrdersPage, TrackMyOrderPage trackMyOrderPage, MyAccountPage myAccountPage, OrderConfirmationPage orderConfirmationPage, OrderConfirmationModel orderConfirmationModel, AddNewBillingAddressPopup addNewBillingAddressPopup, PdpPage productDetailsPage, MyBagPage myBagPage) {
//        this.loginPage = loginPage;
//        this.checkoutAndPaymentsPage = checkoutAndPaymentPage;
//        this.homePage = homePage;
//        this.checkOutPage = checkOutPage;
//        this.myOrdersPage = myOrdersPage;
//        this.trackMyOrderPage = trackMyOrderPage;
//        this.myAccountPage = myAccountPage;
//        this.orderConfirmationPage = orderConfirmationPage;
//        this.orderConfirmationModel = orderConfirmationModel;
//        this.addNewBillingAddressPopup = addNewBillingAddressPopup;
//        this.productDetailsPage = productDetailsPage;
//        this.myBagPage= myBagPage;
//    }

	@And("^I have checked out \"([^\"]*)\" of a line item$")
	public void iHaveCheckedOutOfALineItem(String quantity) throws Throwable
	{
		log.info("Getting the product code -- " + Props.getProp("productCode_brand"));
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(Props.getProp("productCode_brand"));
			homePage.selectRandomProduct();
			productDetailsPage.selectProductAndMoveToMyBagPage();
			myBagPage.clickEditForLineItemMobile(1);
			checkOutPage.selectQuantityOfLineItem(quantity, 1);
			checkOutPage.clickCheckoutButton();
		}
		else
		{
			checkOutPage.searchField().sendKeys(Props.getProp("productCode_brand"));
			checkOutPage.searchButton().click();
			homePage.selectRandomProduct();
			pause(1000);
			checkOutPage.selectFirstAvailableSizeOfTheProduct();
			checkOutPage.selectFirstAvailableSizeOfTheProduct();
			checkOutPage.addToCartButton().click();
			pause(3000);
			checkOutPage.basketIcon().click();
			checkOutPage.selectQuantityOfLineItem(quantity, 1);
			Thread.sleep(1000);
			//modified by Harsha as the below is not clicking in sauce labs
            /*
            checkOutPage.checkOutButton().click();*/
			checkOutPage.clickCheckoutButton();
		}
	}

	@And("^I have checked out with the a delivery address \"([^\"]*)\" and \"([^\"]*)\"$")
	public void iHaveCheckedOutWithTheADeliveryAddressAnd(String firstName, String lastName) throws Throwable
	{
		iHaveCheckedOutWithTheADeliveryAddressAnd(firstName, lastName, "KT1 3HP");
	}

	@And("^I have checked out with the a delivery address \"([^\"]*)\" and \"([^\"]*)\" and postcode \"([^\"]*)\"$")
	public void iHaveCheckedOutWithTheADeliveryAddressAnd(String firstName, String lastName, String postCode) throws Throwable
	{
		checkOutPage.selectDelivery().click();
		checkOutPage.selectDelivery().click();
		checkOutPage.deliveryAddressWithPostcode(firstName, lastName, postCode);
		checkOutPage.enterMandatoryMobileField();
	}


	@And("^i click change delivery address$")
	public void iClickChangeDeliveryAddress() throws Throwable
	{
		checkoutAndPaymentsPage.changeDeliveryAddress();
	}

	@And("^i change the billing address$")
	public void iChangeTheBillingAddress() throws Throwable
	{
		clickByJavaScriptExecutor(checkOutPage.klarna());
		checkoutAndPaymentsPage.selectChangeBillingAddress();
		checkoutAndPaymentsPage.selectOptionInCheckoutPaymentPopup("USETHISADDRESSWITHOUTTITLE");
	}


	@And("^i another card and choose a billing address$")
	public void iAnotherCardAndChooseABillingAddress() throws Throwable
	{
		checkOutPage.fillCardDetailsWithoutCVV();
		checkoutAndPaymentsPage.addAnotherCard();
		checkOutPage.fillCVVField();

	}

	@And("^fill the delivery details with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void fillTheDeliveryDetailsWithAnd(String firstName, String lastName) throws Throwable
	{
		checkOutPage.selectDelivery().click();
		checkOutPage.deliveryAddressWithNameAndSurname(firstName, lastName);
	}

	@And("^i change the billing address and add another card$")
	public void iChangeTheBillingAddressAndAddAnotherCard() throws Throwable
	{
		checkoutAndPaymentsPage.selectChangeBillingAddress();
		checkoutAndPaymentsPage.selectOptionInCheckoutPaymentPopup("ADDNEWADDRESS");
		addNewBillingAddressPopup.updateBillingAddress("KT1 3HP");  //This post code is not working,we are investigating why it is not working
		checkOutPage.fillCardDetailsWithoutCVV();
		checkoutAndPaymentsPage.addAnotherCard();
//		try
//		{
//			checkOutPage.fillCVVField();
//		}
//		catch (Exception e)
//		{
//			log.info("Not able to enter CVV number");
//		}
		checkOutPage.placeOrderButton().isEnabled();
		checkOutPage.clickPlaceOrderButton();
	}

}
