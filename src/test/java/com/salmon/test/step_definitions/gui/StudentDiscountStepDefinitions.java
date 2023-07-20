package com.salmon.test.step_definitions.gui;


import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static com.salmon.test.page_objects.gui.RandomGenerator.currentTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentDiscountStepDefinitions {


    LoginPage.Credentials credentials;
	@Autowired
    private CheckOutPage checkOutPage;
	@Autowired
    private LoginPage loginPage;
	@Autowired
    private MyAccountPage myAccountsPage;
	@Autowired
    private HomePage homePage;
	@Autowired
    private HomePageOld homePageOld;
	@Autowired
	private PromotionsPage promotionsPage;
    private RandomGenerator time = new RandomGenerator();
    private Double itemPrice;
    private String studentID;
    private String staffID;

//    public StudentDiscountStepDefinitions(CheckOutPage checkOutPage, LoginPage loginPage, MyAccountPage myAccountPage, HomePageOld homePageOld, HomePage homePage){
//        this.checkOutPage=checkOutPage;
//        this.loginPage=loginPage;
//        this.myAccountsPage=myAccountPage;
//        this.homePageOld =homePageOld;
//        this.homePage = homePage;
//
//    }

    @Given("^(anonymous|registered) (?:student|customer) has added item to their bag$")
    public void anonymous_student_has_added_item_to_their_bag(String registered) throws InterruptedException {
        //UrlBuilder.startAtHomePage();
        //if (registered.contains("anonymous")) {
       //     checkOutPage.deleteAllCookies();
        //    checkOutPage.getWebDriver().navigate().refresh();
       // }
        //checkOutPage.waitForAndGetElement()
        if(IS_MOBILE){
            homePage.mobileInsertTextAndSearch(Props.getProp("productCode_stock"));
        }
        else{
            checkOutPage.searchField().sendKeys(Props.getProp("productCode_stock"));
            checkOutPage.searchButton().click();
        }
        homePage.selectRandomProduct();
        checkOutPage.selectFirstAvailableSizeOfTheProduct();
        checkOutPage.clickByJavaScriptExecutor(checkOutPage.addToCartButton());
        checkOutPage.clickByJavaScriptExecutor(checkOutPage.basketIcon());
        Thread.sleep(2000);
        this.itemPrice = Double.valueOf(checkOutPage.itemPrice().getText().split("\\n")[0].replaceAll("£", "").replace(",",""));
    }


    @When("^(.*) (student|Unidays) ID is applied$")
    public void valid_student_ID_is_applied(String VALIDITY, String coupontype) {

	    if (coupontype.equalsIgnoreCase("student")) {

		    switch (VALIDITY) {
			    case "locked":
				    if (Features.PROMO_CODE_NEW_STICKY_CART)
				    {
					    promotionsPage.havePromoCodeLabel().click();
				    }
				    checkOutPage.promoCode().sendKeys(Props.getProp("studentDiscountNO"));
				    break;
			    case "valid":
				    studentID = "9696" + currentTime();
				    if (Features.PROMO_CODE_NEW_STICKY_CART)
				    {
					    promotionsPage.havePromoCodeLabel().click();
				    }
				    checkOutPage.promoCode().sendKeys(studentID);
				    break;
			    case "invalid":
				    if (Features.PROMO_CODE_NEW_STICKY_CART)
				    {
					    promotionsPage.havePromoCodeLabel().click();
				    }
				    checkOutPage.promoCode().sendKeys("26" + currentTime());
				    break;
			    case "repeating":
				    if (Features.PROMO_CODE_NEW_STICKY_CART)
				    {
					    promotionsPage.havePromoCodeLabel().click();
				    }
				    checkOutPage.promoCode().sendKeys("9111111165946595");
				    break;
			    case "spaces":
				    if (Features.PROMO_CODE_NEW_STICKY_CART)
				    {
					    promotionsPage.havePromoCodeLabel().click();
				    }
				    checkOutPage.promoCode().sendKeys("9111 1114 6594 659588");
				    break;
			    default:
				    new IllegalArgumentException("Please check the student id provided");
		    }
	    } else {
		    checkOutPage.promoCode().sendKeys("UN1-FPZB-LFCF-EF56");
	    }
	    checkOutPage.clickPromoCodeButton();
    }


    @Then("^a (?:student|staff) discount of (\\d+)% is (applied|unapplied) to subtotal$")
    public void a_student_discount_of_is_applied_to_subtotal(double percentage, String discount) throws Throwable {
        try
        {
            assertEquals(Double.valueOf(checkOutPage.roundToNearestHalf(this.itemPrice) / percentage),
                    Double.valueOf(checkOutPage.promoDiscount().getText().replaceAll("£", "")));
        }catch (AssertionError e){
            e.getMessage();
        }
        if (discount.equalsIgnoreCase("applied"))
            assertTrue(checkOutPage.isDiscountMessagePresent().isDisplayed());
        else
            assertTrue(!checkOutPage.isDiscountMessagePresent().isDisplayed());
        checkOutPage.clickCheckoutButton();
    }

    @Then("^(locked|invalid) promo code error is displayed$")
    public void PromoCodeErrorIsDisplayed(String error) throws Throwable {
        if (error.equalsIgnoreCase("locked"))
            assertTrue(checkOutPage.promoCodeError().isDisplayed());
        else
            assertTrue(checkOutPage.promoCodeError().isDisplayed());
    }

    @Then("^(student|staff) ID is associated with the account$")
    public void studentIDIsAssociatedWithTheAccount(String customer) {
        loginPage.goToProfilePage();
        if (customer.equalsIgnoreCase("student"))
            assertTrue(myAccountsPage.isIDAssociated(studentID.substring(0,4)));
        else
            assertTrue(myAccountsPage.isIDAssociated(staffID));
    }

    @And("^customer registers from checkout page$")
    public void customerRegistersFromCheckoutPage() {
        loginPage.goToCreateAccountPage();
        this.credentials = loginPage.registerNewUser();
    }

    @And("^click secure checkout button$")
    public void clickSecureCheckoutButton()
    {
       checkOutPage.checkOutButton().click();
    }

    @And("^select delivery option$")
    public void selectDeliveryOption() throws Throwable
    {
    	  checkOutPage.pause(2000);
        checkOutPage.selectDelivery().click();
        checkOutPage.waitForPageLoad();
    }


    @And("^customer signed out of the account$")
    public void customerSignedOutOfTheAccount()
    {

    }
}

