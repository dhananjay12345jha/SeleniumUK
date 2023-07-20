package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.services.NewLookTest;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.salmon.test.framework.helpers.utils.Credentials;

import org.springframework.beans.factory.annotation.Autowired;

public class StaffDiscountStepDefinition {

    public String Card_Number;
	@Autowired
    StaffDiscount staffDiscountPage;
	@Autowired
    MyAccountsPage myAccountsPage;
	@Autowired
    private CheckOutPage checkOutPage;
	@Autowired
    private HomePage homePage;
	@Autowired
    PromotionsPage promotionsPage;
	@Autowired
    LoginPage loginPage;
	@Autowired
    CheckoutAndPaymentsPage checkoutAndPaymentsPage;
    private String generatedName;
    private String generatedFirstName;
    private String generatedLastName;
    private String generatedEmail;
    private String orderNumber;
	@Autowired
	private NewLookHelper newLookHelper;
	@Autowired
    private Credentials credentials;
	@Autowired
    private CreateNewAccountPage createNewAccountPage;

//    public StaffDiscountStepDefinition(StaffDiscount staffDiscountPage, MyAccountsPage myAccountsPage, PromotionsPage promotionsPage, LoginPage loginPage, CheckOutPage checkoutPage, HomePage homepage, Credentials credentials, CreateNewAccountPage createNewAccountPage, CheckoutAndPaymentsPage checkoutAndPaymentsPage){
//        this.staffDiscountPage=staffDiscountPage;
//        this.myAccountsPage=myAccountsPage;
//        this.promotionsPage=promotionsPage;
//        this.loginPage=loginPage;
//        this.checkOutPage=checkoutPage;
//        this.homePage = homepage;
//        this.credentials=credentials;
//        this.createNewAccountPage = createNewAccountPage;
//        this.checkoutAndPaymentsPage = checkoutAndPaymentsPage;
//
//    }

    @Then("^the staff discount should be applied on the checkout page$")
    public void the_StaffDiscount_Should_Be_Applied_On_The_CheckoutPage() {

        if(IS_MOBILE)
        {
            checkoutAndPaymentsPage.clickOrderSummaryTitleMobile();
        }

        String msg = promotionsPage.staffdiscountmessage().getText();
        Assert.assertThat(msg, containsString("Staff Discount"));

    }

    @And("^register by entering \"([^\"]*)\" and \"([^\"]*)\"$")
    public void registerByEnteringAnd(String emp_id, String card_number) {

        staffDiscountPage.Emp_ID().sendKeys(emp_id);
        staffDiscountPage.IKANO_CARD_NUMBER().sendKeys(card_number);
        staffDiscountPage.IKANO_CARD_NUMBER().sendKeys(Keys.TAB, Keys.SPACE);
        staffDiscountPage.Submit().click();

        this.Card_Number = card_number;
    }


    @Then("^registered card should be visible in the My Account Section$")
    public void registeredCardShouldBeVisibleInTheMyAccountSection() {

        staffDiscountPage.Goto_My_Account().click();
        assertTrue(myAccountsPage.myAccountLinkLocator().isDisplayed());
        myAccountsPage.Personal_Details().click();
        assertTrue(myAccountsPage.Staff_Card_Number().isDisplayed());
        assertEquals(Card_Number, myAccountsPage.Staff_Card_Number_Details().getText());

    }


    @And("^navigates to Staff Registration page$")
    public void navigatesToStaffRegistrationPage() {

        staffDiscountPage.navigateToStaffDiscount();


    }

    @And("^clicks the staff discount registration button$")
    public void clicksTheStaffDiscountRegistrationButton() {

        staffDiscountPage.Start_Registration().click();

    }

    @Then("^the staff registration button should be disabled$")
    public void theStaffRegistrationButtonShouldBeDisabled() {

        Assert.assertTrue(staffDiscountPage.start_registration_count() == 0);

    }


    @And("^register a new user$")
    public void register_a_new_user() {
        homePage.navigateToCreateNewAccountPageByClickingHeaderLink();
        credentials = createNewAccountPage.createNewAccountWithCorrectDetails();
    }

    @When("^I checkout a product$")
    public void iCheckoutAProduct() throws Throwable {
        newLookHelper.checkoutAProduct();

    }

    @When("^user registers and navigates to staff discount registration$")
    public void userRegistersAndNavigatesToStaffDiscountRegistration() throws Throwable {
        if (IS_MOBILE) {
            homePage.mobileNavigateToLoginPage();
            homePage.clickCreateAnAccountButton();
            this.credentials = createNewAccountPage.createNewAccountWithCorrectDetails();
            homePage.clickMyAccountBreadCrumb();
        }
        else {
            homePage.navigateToCreateNewAccountPageByClickingHeaderLink();
            this.credentials = createNewAccountPage.createNewAccountWithCorrectDetails();

        }
        staffDiscountPage.navigateToStaffDiscount();
        staffDiscountPage.Start_Registration().click();
    }

    @After("@RemoveStaffSubscription")
    public void removeStaffSubscription() throws Throwable {
        try {
            NewLookTest.postNewLookStaffRecordImpex(credentials.getEmail());
            //NewLookApiTestSteps.removeStaffRecordScription(Props.getProp("staffDiscountUserEmail"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
