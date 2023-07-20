package com.salmon.test.step_definitions.gui.when;

import com.gargoylesoftware.htmlunit.html.xpath.XPathUtils;
import com.salmon.test.page_objects.gui.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;

public class MyAccountWhenStepDefs {
    Map<String, String> savedAddresses = new HashMap<>();
	@Autowired
    private HomePage homePage;
	@Autowired
    private CheckOutPage checkOutPage;
	@Autowired
    private MyAccountPage myAccountPage;
	@Autowired
    private MyAddressPage myAddressPage;
	@Autowired
    private CheckoutAndPaymentsPage checkoutAndPaymentsPage;
	@Autowired
    private MyContactPreferencesPage myContactPreferencesPage;

//    public MyAccountWhenStepDefs(HomePage homePage, MyAccountPage myAccountPage, MyAddressPage myAddressPage, CheckOutPage checkOutPage, CheckoutAndPaymentsPage checkoutAndPaymentsPage, MyContactPreferencesPage myContactPreferencesPage) {
//        this.homePage = homePage;
//        this.myAccountPage = myAccountPage;
//        this.myAddressPage = myAddressPage;
//        this.checkOutPage = checkOutPage;
//        this.checkoutAndPaymentsPage = checkoutAndPaymentsPage;
//        this.myContactPreferencesPage = myContactPreferencesPage;
//    }

    @When("^click on signout$")
    public void clickOnSignout() {
        homePage.clickMyAccountButtonIfDisplayed();
        homePage.signOutOfAccount();
    }

    @And("^Navigate to my address page$")
    public void navigateToMyAddressPage() {
        if (IS_MOBILE)
        {
            homePage.navigateToMyAccountFromHeaderOnMobile();
        }
        myAccountPage.clickMyAddressLink();
        myAccountPage.waitForPageLoad();

    }

    @When("^Delete existing address is selected$")
    public void deleteExistingAddressIsSelected() throws InterruptedException {
        myAddressPage.clickDeleteAddress();
    }

    @When("^Deleted existing address$")
    public void deletedExistingAddress() throws InterruptedException {
        myAddressPage.clickDeleteAddress();
        myAddressPage.clickDeleteAddressInPopup();
    }

    @When("^Added a new address$")
    public void addedANewAddress() {
        myAddressPage.addNewAddressToMyAccount();
    }

    @When("^Added a new address with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void addedANewAddressWithFirstNameAndLastName(String firstName, String lastName) {
        myAddressPage.addAddressUsingFinder(firstName, lastName, "EC2V 7HR");
    }


    @When("^find and add new address using post code (.*)$")
    public void addedANewAddress(String postCode) throws InterruptedException
    {
        myAddressPage.addAddressManually(postCode);
        myAddressPage.pause(2000);
        savedAddresses.put(postCode, myAddressPage.getSavedAddressDetails(postCode));
    }

    @When("^find and add new address with pop up script in input field$")
    public void addedANewAddressPopUpScrit() throws InterruptedException {
//        myAccountPage.clickMyAddressLink();
        myAddressPage.addAddressUsingFinder("<script>alert('test');</script>", "lastName", "E6 2HE");

    }

    @When("^User navigate to my account$")
    public void userNavigateToMyAccount() {


    }

    @When("^User navigate to address book page$")
    public void userNavigateToMyAccount4() {
        homePage.clickUserAccountIcon();
        myAccountPage.clickMyAddressLink();

    }

    @When("^User navigate to address book page on mobile$")
    public void userNavigateToMyAccountOnMobile() {
        homePage.navigateToMyAccountFromHeaderOnMobile();
        myAccountPage.clickMyAddressLink();
    }

    @When("^User click the (.*) link$")
    public void userClickTheIconLink(String link) {
        myAccountPage.selectMyAccountLinks(link);
    }

    @When("^set address with post code (.*) as default$")
    public void setAddressWithPostCodeENNAsDefault(String postcode) throws Throwable {
        myAddressPage.setAsDefaultAddress(postcode);
        myAddressPage.pause(1000);
        //defaultAddress= myAddressPage.getSavedAddressDetails(postcode);
    }

    @Given("^account holder in checkout page$")
    public void i_m_on_the_checkout_pageCollection() throws Exception {
        checkOutPage.pause(1000);
        checkOutPage.goToCheckoutPage();
        checkOutPage.getCurrentUrl();
    }

    @And("^checkout default delivery address should be with post code (.*)$")
    public void getDefaultDeliveryAddressFromCheckoutpage(String postCode) throws Throwable {
        checkoutAndPaymentsPage.pause(1000);
        checkoutAndPaymentsPage.selectDeliveryOrCollectionOption("delivery");
        checkoutAndPaymentsPage.pause(1000);
        checkoutAndPaymentsPage.getPostalCode();
        Assert.assertTrue("PostalCode should equal: "+checkoutAndPaymentsPage.getPostalCode(),checkoutAndPaymentsPage.getPostalCode().equals(postCode));
    }

    @And("^I navigate to my contact preferences page$")
    public void iNavigateToMyContactPreferencesPage() throws Throwable {
        myAccountPage.clickMyContactPreferences();
    }

    @And("^I set the radio buttons to yes and save the changes$")
    public void iSetTheRadioButtonsToYesAndSaveTheChanges() throws Throwable {
        myContactPreferencesPage.setRadioButtonsToYes();
        myContactPreferencesPage.clickSavePreferencesButton();
    }

    @Then("^a saved alert item is displayed$")
    public void aSavedAlertItemIsDisplayed() throws Throwable {
        myContactPreferencesPage.alertItemIsDisplayed();
    }

    @And("^all the radio buttons have been set to Yes$")
    public void allTheRadioButtonsHaveBeenSetToYes() throws Throwable {
        if(IS_MOBILE){
            homePage.navigateToMyAccountFromHeaderOnMobile();
        }
        else{
            homePage.navigateToMyAccountPage();
        }
        myAccountPage.clickMyContactPreferences();
        myContactPreferencesPage.assertRadioButtonsSetToYes();

    }

    @And("^I set the radio buttons to no and save the changes$")
    public void iSetTheRadioButtonsToNoAndSaveTheChanges() throws Throwable {
        myContactPreferencesPage.setRadioButtonsToNo();
        myContactPreferencesPage.clickSavePreferencesButton();
    }

    @And("^all the radio buttons have been set to No$")
    public void allTheRadioButtonsHaveBeenSetToNo() throws Throwable {
        if(IS_MOBILE){
            homePage.navigateToMyAccountFromHeaderOnMobile();
        }
        else{
            homePage.navigateToMyAccountPage();
        }
        myAccountPage.clickMyContactPreferences();
        myContactPreferencesPage.assertRadioButtonsSetToNo();
    }


	@And("^default Delivery Address is displayed along with mandatory mobile number$")
	public void defaultDeliveryAddressIsDisplayed()
	{
		Assert.assertTrue(checkoutAndPaymentsPage.getDefaultDeliveryAddress().size() == 10);
	}
}
