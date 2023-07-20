package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static com.jcabi.matchers.RegexMatchers.matchesPattern;



public class GiftCardStepDefinitions {

	@Autowired
    private CheckOutPage checkOutPage;
	@Autowired
    private ChopinPage chopinPage;
	@Autowired
    private HomePageOld homePage;
	@Autowired
    private GiftCardSection giftCardSection;
	@Autowired
    private CheckoutAndPaymentsPage checkoutAndPaymentsPage;
	@Autowired
    private NewLookHelper newLookHelper;

    private String giftCardNo;
    private String giftCardPin;

//    public GiftCardStepDefinitions(CheckOutPage checkOutPage,
//                                   ChopinPage chopinPage,
//                                   HomePageOld homePageOld,
//                                   GiftCardSection giftCardSection,
//                                   CheckoutAndPaymentsPage checkoutAndPaymentsPage,
//                                   NewLookHelper newLookHelper){
//        this.chopinPage=chopinPage;
//        this.homePage=homePageOld;
//        this.giftCardSection=giftCardSection;
//        this.checkOutPage=checkOutPage;
//        this.checkoutAndPaymentsPage = checkoutAndPaymentsPage;
//        this.newLookHelper = newLookHelper;
//    }

    @Given("^I have a (registered|unregistered|blocked|zero balance) Gift Card$")
    public void i_have_a_registered_Gift_Card(String cardStatus) {

        final String giftCardStatus;
        PrePayGiftCardApi prePayGiftCardApi = new PrePayGiftCardApi();

        switch (cardStatus) {
            case "registered":
//                this.giftCardNo = "6335863601681343";
//                this.giftCardPin = "1665";
                this.giftCardNo = Props.getProp("registeredGiftCard");//"6335863608087205";
                this.giftCardPin = Props.getProp("registeredGiftCardPin") ;//"1890";
//                giftCardStatus = prePayGiftCardApi.accountNo(giftCardNo).accountPin(giftCardPin).getBalance();
//                this.giftCardbalance = giftCardStatus;
//                assertTrue("Message: " + giftCardStatus, giftCardStatus.chars().allMatch(Character::isDigit));
                break;
            case "unregistered":
                this.giftCardNo = "6335863608772772";
                this.giftCardPin = "5045";
//                giftCardStatus = prePayGiftCardApi.accountNo(giftCardNo).accountPin(giftCardPin).getBalance();
//                assertEquals("CARD HAS NOT BEEN ACTIVATED", giftCardStatus);
                break;
            case "blocked":
                this.giftCardNo =Props.getProp("blockedGiftCard");// "6335863609042944";
                this.giftCardPin = Props.getProp("blockedGiftCardPin") ;//"0472";
//                giftCardStatus = prePayGiftCardApi.accountNo(giftCardNo).accountPin(giftCardPin).getBalance();
//                assertEquals("CARD BLOCKED", giftCardStatus);
                break;
            case "zero balance":
                this.giftCardNo = "6335863604846299";
                this.giftCardPin = "5425";
//                giftCardStatus = prePayGiftCardApi.accountNo(giftCardNo).accountPin(giftCardPin).getBalance();
//                this.giftCardbalance = giftCardStatus;
//                assertTrue(giftCardStatus.equals("0"));
                break;
        }
    }

    @Given("^I'm on the (FR|row|UK) checkout page$")
    public void i_m_on_the_checkout_page(String country) throws Exception {
//        landingPage.anonymouslyGoToNewLookWebsite("en", "GBP", country);
//        checkOutPage.searchForInStockRandomProduct("womens");
//        plpPage.selectItem();

        checkOutPage.searchField().sendKeys(Props.getProp("productCode_stock"));
        checkOutPage.searchButton().click();
        homePage.selectRandomProduct();
        checkOutPage.selectFirstAvailableSizeOfTheProduct();
        checkOutPage.addToCartButton().click();
        checkOutPage.basketIcon().click();
	     checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
        checkOutPage.secureCheckout().click();

        checkOutPage.guestEmailAddress().sendKeys(RandomGenerator.randomEmailAddress(5));
        checkOutPage.checkOutAsGuestButton().click();

        checkOutPage.collectionButton().click();
        if(country.equalsIgnoreCase("fr")){
            checkOutPage.postcodeOrCountryField().sendKeys("paris");
        }
        if(country.equalsIgnoreCase("uk")){
            checkOutPage.postcodeOrCountryField().sendKeys("london");
        }

        checkOutPage.collectionSubmitButton().click();
        checkoutAndPaymentsPage.selectTheFirstCollectionOptionFromList();
//        checkOutPage.scrollForFocusAndClick(checkOutPage.selectStoreButton, 5);
        try {
            checkOutPage.collectionDetails();
        } catch (Exception e) {
           System.out.print("no collection section");
        }
        checkOutPage.pause(1000);
        checkOutPage.enterMandatoryMobileField();
    }

    @Given("^I'm on the bag page with product code (.*)$")
    public void i_m_on_the_bsg_page(String prodCoode) throws Exception {
        checkOutPage.searchField().sendKeys(prodCoode);
        checkOutPage.searchButton().click();
        homePage.selectRandomProduct();
        checkOutPage.selectFirstAvailableSizeOfTheProduct();
        checkOutPage.addToCartButton().click();
        checkOutPage.basketIcon().click();

    }

    @When("^I continue to payment$")
    public void i_continue_to_payment() throws Exception {
        checkOutPage.pause(2000);
        checkOutPage.enterMandatoryMobileField();
        checkOutPage.continueToPaymentSection();

    }

    @Then("^there (is|isn't) a option for Gift Card$")
    public void there_is_a_option_for_Gift_Card(String ukSite) {
        if (ukSite.equals("is")) {
            assertTrue(giftCardSection.giftCardHeading().isDisplayed());
        } else {
            assertFalse(giftCardSection.giftCardSectionPresent());
        }
    }

    @When("^I expand Gift Card section$")
    public void i_expand_Gift_Card_section() {
        giftCardSection.scrollToGiftCardSection();
        giftCardSection.openGiftCardSection().click();
    }

    @When("^check balance of my Gift Card$")
    public void check_balance_of_my_Gift_Card() {
        giftCardSection.enterGiftCard(this.giftCardNo, this.giftCardPin);
        checkoutAndPaymentsPage.setGiftCardBalanceBeforePurchase();
    }

    @When("^check balance of my Gift Card with incorrect pin$")
    public void check_balance_of_my_Gift_Card_with_incorrect_pin() {
        this.giftCardPin = "0000";
        giftCardSection.enterGiftCard(this.giftCardNo, this.giftCardPin);
    }

    @Then("^I wil get an error message$")
    public void i_wil_get_an_error_message() {
              try {
            giftCardSection.waitForTextToBePresentInElement(giftCardSection.GIFT_CARD_ERROR,"Sorry, the details you have provided are invalid",10 );
        } catch (Exception e) {
            assertTrue(giftCardSection.giftCardError().getText().contains("currently unavailable"));
        }
    }

    @Then("^my Gift Card balance is displayed$")
    public void my_Gift_Card_balance_is_displayed() {
        Assert.assertEquals("£0.00", giftCardSection.giftCardBalance().getText());
        Assert.assertThat("£0.00", matchesPattern(RegEx.BRITISH.currency()));
        Assert.assertEquals(this.giftCardNo.substring(this.giftCardNo.length() - 4), giftCardSection.giftCardNumberEnding()
                .getText().replaceAll("\\D+", ""));
    }

    @When("^I apply Gift Card$")
    public void i_apply_Gift_Card() {
        giftCardSection.clickWhenClickable(giftCardSection.applyGiftCard(),5);
        //giftCardSection.applyGiftCard().click();
    }

    @Then("^I can not apply Gift Card$")
    public void i_can_not_apply_Gift_Card() {
        boolean clickable = true;
        try {

            giftCardSection.applyGiftCard().click();
        }
        catch (Exception e){
            clickable = false;
        }
        assertFalse("Apply giftcard button is dissabled", clickable);
    }

    @Then("^order amount will be deducted from Gift Card balance$")
    public void order_amount_will_be_deducted_from_Gift_Card_balance() {
        assertTrue(giftCardSection.giftCardCorrectBalanceRemaining());
    }

    @Then("^amount due is less amount deducted from Gift Card$")
    public void amount_due_is_less_amount_deducted_from_Gift_Card() {
        assertTrue(!checkoutAndPaymentsPage.getGiftCardBalanceBeforePurchase()
                .equalsIgnoreCase(checkOutPage.checkOutTotalBalance().getText()));
    }

    @Given("^Anonymous user goes to chopin site$")
    public void anonymousUserGoesToChopinSite() throws Throwable {
        chopinPage.anonymouslygotochopin();
    }

    @When("^logins with the valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void loginsWithTheValidAnd(String arg0, String arg1) throws Throwable {
        chopinPage.login(arg0, arg1);
    }

    @Then("^The page to topup the giftcard balance should be displayed$")
    public void thePageToTopupTheGiftcardBalanceShouldBeDisplayed() throws Throwable {
        chopinPage.verifytext();
    }


    @When("^he searches the \"([^\"]*)\"$")
    public void heSearchesThe(String giftcardreference) throws Throwable {


    }

    @When("^he searches the gift_card \"([^\"]*)\"$")
    public void heSearchesTheGift_card(String SlNo) throws Throwable {

        chopinPage.search_giftcard(SlNo);
    }


    @And("^cliks in select button$")
    public void cliksInSelectButton() throws Throwable {

        chopinPage.selectcard().click();
    }

    @And("^cliks on the Adjust Balance link$")
    public void cliksOnTheAdjustBalanceLink() throws Throwable {

        chopinPage.adjustbalancelink().click();
    }

    @Then("^gift card is topped up with \"([^\"]*)\"$")
    public void giftCardIsToppedUp(String amount) {
        int TotalAmt = Integer.parseInt(amount);
        int amount_to_topup = chopinPage.topup_amount(TotalAmt);
        if (amount_to_topup > 0) {
            chopinPage.topup(amount_to_topup);
            chopinPage.submit_amount().click();
            assertTrue(chopinPage.success_toppedup_message());
        } else {
            // log.info("GiftCard has got sufficient balance, so no topup");
        }
    }

    @And("^checkout a product and navigate to payment section on mobile$")
    public void checkOutAProductAndNavigateToPaymentSectionOnMobile() {
        newLookHelper.addProductAndNavigateToPaymentOnMobile("london");
    }

    @And("^navigate to the payment page non uk on mobile$")
    public void navigateToThePaymentPageOnNonUkMobile() {
        newLookHelper.addProductAndNavigateToPaymentOnMobile("paris");
    }
}
