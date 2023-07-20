package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.models.gui.TrackMyOrderModel;
import com.salmon.test.page_objects.gui.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static org.junit.Assert.assertEquals;

/**
 * Created by hbkpreddy on 24/01/2018
 */
public class TrackMyOrderStepDefinitions {

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
    private TrackMyOrderModel trackMyOrderModel = new TrackMyOrderModel();

//    public TrackMyOrderStepDefinitions(HomePage homePage, CheckOutPage checkOutPage, MyOrdersPage myOrdersPage, TrackMyOrderPage trackMyOrderPage, MyAccountPage myAccountPage) {
//        this.homePage = homePage;
//        this.checkOutPage = checkOutPage;
//        this.myOrdersPage = myOrdersPage;
//        this.trackMyOrderPage = trackMyOrderPage;
//        this.myAccountPage = myAccountPage;
//    }

    @When("^user clicks on Track My Order$")
    public void iClickOnTrackMyOrder() throws Exception {
        homePage.clickTrackMyOrder();
    }

    @And("^login as already existing user$")
    public void loginAsAlreadyExistingUser() {
        checkOutPage.loginAsExistingUser();
    }

    @When("^select view order for \"([^\"]*)\" with status \"([^\"]*)\" in my orders page$")
    public void select_view_order_for_with_status_in_my_orders_page(String orderNumber, String orderStatus) throws Exception {
        Thread.sleep(3000);
        myOrdersPage.selectViewOrder(orderNumber, orderStatus, trackMyOrderModel);
    }

    @Then("^check the order status as \"([^\"]*)\" in track my order page$")
    public void check_the_order_status_as_in_track_my_order_page(String orderStatus) throws Exception {
        assertEquals(orderStatus, trackMyOrderPage.isOrderInCorrectStatus());
    }

    @And("^select view order for a confirmed order with status '(.*)' in my orders page$")
    public void selectViewOrderForAConfirmedOrderWithStatusConfirmedInMyOrdersPage(String orderStatus) throws Exception {
        String orderNumber = Props.getProp("orderConfirmed");
        myOrdersPage.selectViewOrder(orderNumber, orderStatus, trackMyOrderModel);
    }

    @And("^select view order for a awaiting dispatch order with status '(.*)' in my orders page$")
    public void selectViewOrderForAAwaitingDispatchOrderWithStatusAwaitingDispatchInMyOrdersPage(String orderStatus) throws Exception {
        String orderNumber = Props.getProp("orderAwaitingDispatch");
        myOrdersPage.selectViewOrder(orderNumber, orderStatus, trackMyOrderModel);
    }

    @And("^user clicks on breadcrumb '(.*)'$")
    public void userClicksOnBreadcrumbMyAccount(String breadcrumbName) throws Exception {
        trackMyOrderPage.clickOnBreadcrumb(breadcrumbName);
    }

    @Then("^user will navigate to '(.*)' page$")
    public void userWillNavigateToMyAccountPage(String pageTitle) throws Exception {
        assertEquals(pageTitle, myAccountPage.getPageTitle());
    }

    @And("^select view order in my orders page$")
    public void selectViewOrderInMyOrdersPage() throws Exception {
        myOrdersPage.clickViewOrder();
    }
	@And("^select view order in my orders page oms$")
	public void selectViewOrderInMyOrdersPageOms() throws Exception {
		myOrdersPage.clickViewOrderOms();
	}

    @When("^user clear cookies$")
    public void user_clear_cookies() throws Exception {
        trackMyOrderPage.deleteCookies();
    }

    @Then("^user should see track my order button$")
    public void userShouldSeeTrackMyOrderButton() {
        Assert.assertTrue(trackMyOrderPage.getTrackMyOrderButton());
    }

    @And("^User navigates to order history page$")
    public void userNavigatesToOrderHistoryPage()
    {
       homePage.clickTrackMyOrderFromHeader();
    }

    @And("^Selects last order$")
    public void selectsLastOrder()
    {
        myOrdersPage.clickViewOrder();
    }

    @Then("^Klarna payment tender is displayed$")
    public void klarnaPaymentTenderIsDisplayed()
    {
        Assert.assertTrue(myOrdersPage.isKlarnaTenderDisplayed());
    }

    @And("^Customer is displayed with option to add a password and register an account$")
    public void customerIsDisplayedWithOptionToAddAPasswordAndRegisterAnAccount()
    {
       Assert.assertTrue(myOrdersPage.isOptionForRegisteringPresent());
    }

	@Then("^Guest user register for an account by adding a password and login$")
	public void guestUserCreatesAnAccountByAddingAPasswordAndLogsin()
	{
		myOrdersPage.guestRegisteringAccount();
	}
}
