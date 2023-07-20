package com.salmon.test.step_definitions.gui.when;

import com.salmon.test.framework.WebElementNotFoundException;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.step_definitions.gui.GiftCardSection;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class PurchasesWhenStepDefs {
	@Autowired
    private HomePage homePage;
	@Autowired
    private PdpPage productDetailsPage;
	@Autowired
    private PlpPage productListPage;
	@Autowired
    private MyBagPage myBagPage;
	@Autowired
    private CheckoutLoginPage checkoutLoginPage;
	@Autowired
    private CheckoutAndPaymentsPage checkoutAndPaymentsPage;
	@Autowired
    private LoginPage loginPage;
	@Autowired
    private CheckOutPage checkOutPage;
	@Autowired
    private GiftCardSection giftCardSection;
	@Autowired
	private NewLookHelper newLookHelper;


//    public PurchasesWhenStepDefs(final HomePage homePage,
//                                 final PdpPage productDetailsPage,
//                                 final PlpPage productListPage,
//                                 final MyBagPage myBagPage,
//                                 final CheckoutLoginPage checkoutLoginPage,
//                                 final CheckoutAndPaymentsPage checkoutAndPaymentsPage,
//                                 final LoginPage loginPage,
//                                 final CheckOutPage checkOutPage,
//                                 final GiftCardSection giftCardSection) {
//        this.homePage = homePage;
//        this.productDetailsPage = productDetailsPage;
//        this.productListPage = productListPage;
//        this.myBagPage = myBagPage;
//        this.checkoutLoginPage = checkoutLoginPage;
//        this.checkoutAndPaymentsPage = checkoutAndPaymentsPage;
//        this.loginPage = loginPage;
//        this.checkOutPage = checkOutPage;
//        this.giftCardSection = giftCardSection;
//    }

    @When("^Customer makes a home delivery purchase with \"([^\"]*)\"$")
    public void customerMakeAPurchaseWithPaymentMethod(String paymentType) throws WebElementNotFoundException {
        newLookHelper.purchaseARandomProduct(paymentType);
    }

    @When("^Customer makes a home delivery purchase with \"([^\"]*)\" and product \"([^\"]*)\"$")
    public void customerMakeAPurchaseWithPaymentMethod(String paymentType, String product) {
        newLookHelper.purchaseAProduct(paymentType, product);
    }

    @When("^new customer checkout a product for collection")
    public void customerMakeAPurchaseWithPaymentMethod2() throws WebElementNotFoundException, InterruptedException {
        checkOutPage.checkoutProduct(Props.getProp("productCode_brand"));
    }

    @And("^new customer checkouts a product for collection as a guest with (.*)$")
    public void newCustomerCheckoutAProductForCollectionAsAGuestWith(String paymentType) {
        newLookHelper.purchaseARandomProductAsGuestForCollection(paymentType);
    }

    @And("^new customer checkouts a product for home delivery as a guest with \"([^\"]*)\"$")
    public void newCustomerCheckoutsAProductForHomeDeliveryAsAGuestWithPaymentType(String paymentType)  {
        newLookHelper.purchaseARandomProductAsGuestForDelivery(paymentType);
    }

    @And("^new customer checkouts a product for home delivery as a guest with giftcard$")
    public void newCustomerCheckoutsAProductForHomeDeliveryAsAGuestWithGiftcard() throws Throwable {
        newLookHelper.searchAndSelectProductThenCheckout();
        checkoutLoginPage.enterEmailAddressForGuestCheckoutField();
        checkoutLoginPage.clickGuestCheckoutButton();
        checkoutAndPaymentsPage.selectDeliveryOrCollectionOption("DELIVERY");
        checkOutPage.deliveryAddressWithNameAndSurname("cancel","cancel");
        checkOutPage.enterMandatoryMobileField();
        checkOutPage.enterCardDetailsAndMakePlaceOrder("gift card 1", "", "");
    }

    @When("^new customer signup newlook")
    public void customerSignUp() throws WebElementNotFoundException, InterruptedException {
        log.debug("Logging into my account");
        loginPage.newSignUp();
    }

    @When("^new customer signup with alert popup script in input")
    public void customerSignUpAlert() throws WebElementNotFoundException, InterruptedException {
        loginPage.newSignUpWithAlertPopUpScript();

    }

    @When("^user checkout a product")
    public void customerCheckout() throws WebElementNotFoundException, InterruptedException {
        UrlBuilder.navigateToHomePage();
        checkOutPage.checkoutProduct(Props.getProp("productCode_brand"));
    }

    @When("^Customer uses gift card for payment$")
    public void customerUsesGiftCardForPayment() throws WebElementNotFoundException {
        homePage.searchForProductUsingRandomProductCode();
        homePage.selectRandomProduct();
        productDetailsPage.selectProductAndMoveToMyBagPage();
	    checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
        myBagPage.clickPrimarySecureCheckoutButton();
        checkoutLoginPage.enterEmailAddressForGuestCheckoutField();
        checkoutLoginPage.clickGuestCheckoutButton();
        checkoutAndPaymentsPage.selectDeliveryOrCollectionOption("collection");
        checkoutAndPaymentsPage.selectCollectionPointFromTheSearchDropdown();
        checkOutPage.collectionDetails();
        checkOutPage.noDeliveryUpdatesButton().click();
        giftCardSection.openGiftCardSection().click();
        giftCardSection.enterGiftCard(Props.getProp("registeredGiftCard"), Props.getProp("registeredGiftCardPin"));
        giftCardSection.clickWhenClickable(giftCardSection.applyGiftCard(), 5);
        checkoutAndPaymentsPage.useGiftCardForPayment();
    }

    @And("^purchase a random product for home delivery on mobile (.*) (.*) (.*)$")
    public void purchaseARandomProductForHomeDeliveryOnMobile(String paymentType, String cardType, String cardNumber) {
        homePage.selectRandomProduct();
        productDetailsPage.selectProductAndMoveToMyBagPage();
	    checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
        myBagPage.clickPrimarySecureCheckoutButton();
        checkoutLoginPage.enterEmailAddressForGuestCheckoutField();
        checkoutLoginPage.clickGuestCheckoutButton();
        checkoutLoginPage.waitForPageLoad();
        checkoutAndPaymentsPage.makePaymentForDelivery(paymentType, cardType, cardNumber);
	    checkoutAndPaymentsPage.waitForPageLoad();
    }

    @And("^purchase a random product for collection on mobile (.*) (.*) (.*)$")
    public void purchaseARandomProductForCollectionOnMobile(String paymentType, String cardType, String cardNumber) {
        homePage.selectRandomProduct();
        productDetailsPage.selectProductAndMoveToMyBagPage();
	    checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
        myBagPage.clickPrimarySecureCheckoutButton();
        checkoutLoginPage.enterEmailAddressForGuestCheckoutField();
        checkoutLoginPage.clickGuestCheckoutButton();
        checkoutAndPaymentsPage.makePaymentForCollection(Props.getProp(paymentType), cardType, cardNumber);
    }

    @And("^checkout a product for collection with code \"([^\"]*)\"$")
    public void newCustomerCheckoutAProductForCollectionWithCode(String productCode) throws WebElementNotFoundException, InterruptedException
    {
        checkOutPage.checkoutProduct(productCode);

    }

    @And("^I update customer details \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iUpdateCustomerDetailsAnd(String firstName, String lastName) throws Throwable {
        checkOutPage.updateCollectionDetails(firstName, lastName);
    }

    @And("^purchase a random product for home delivery using giftcard$")
    public void purchaseARandomProductForHomeDeliveryUsingGiftcard() throws Throwable {
        homePage.selectRandomProduct();
        productDetailsPage.selectProductAndMoveToMyBagPage();
	     //checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
        myBagPage.clickPrimarySecureCheckoutButton();
        checkoutLoginPage.enterEmailAddressForGuestCheckoutField();
        checkoutLoginPage.clickGuestCheckoutButton();
        checkoutAndPaymentsPage.selectDeliveryOrCollectionOption("DELIVERY");
        checkOutPage.deliveryAddressWithNameAndSurname("cancel","cancel");
        checkOutPage.enterMandatoryMobileField();
        checkOutPage.enterCardDetailsAndMakePlaceOrder("gift card 2", "", "");

    }

	@And("^user search and checkout a product$")
	public void userSearchAndCheckoutAProduct() throws WebElementNotFoundException, InterruptedException
	{
		UrlBuilder.navigateToHomePage();
		checkOutPage.searchAndCheckoutProduct(Props.getProp("productCode_stock"));
	}

	@And("^Enter CVV Number$")
	public void enterCVVNumber()
	{
		checkOutPage.EnterCVVNumber();
	}
}
