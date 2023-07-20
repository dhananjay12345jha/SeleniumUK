package com.salmon.test.step_definitions.gui.when;

import com.salmon.test.page_objects.gui.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

public class MyAccountSavedItemsWhenStepDefs {
	@Autowired
    private HomePage homePage;
	@Autowired
    private MyAccountPage myAccountPage;
	@Autowired
    private PlpPage plpPage;
	@Autowired
    private PdpPage pdpPage;
	@Autowired
    private MySavedItemsPage mySavedItemsPage;
	@Autowired
    private MyBagPage myBagPage;
	@Autowired
    private CheckoutAndPaymentsPage checkoutAndPaymentsPage;
	@Autowired
    private WishListPage wishListPage;

//    public MyAccountSavedItemsWhenStepDefs(final HomePage homePage,
//                                           final MyAccountPage myAccountPage,
//                                           final PlpPage plpPage,
//                                           final PdpPage pdpPage,
//                                           final MySavedItemsPage mySavedItemsPage,
//                                           final MyBagPage myBagPage, final CheckoutAndPaymentsPage checkoutAndPaymentsPage,
//                                           final WishListPage wishListPage) {
//        this.homePage = homePage;
//        this.myAccountPage = myAccountPage;
//        this.plpPage = plpPage;
//        this.pdpPage = pdpPage;
//        this.mySavedItemsPage = mySavedItemsPage;
//        this.myBagPage = myBagPage;
//        this.checkoutAndPaymentsPage = checkoutAndPaymentsPage;
//        this.wishListPage = new WishListPage();
//    }


    @When("^customer select a product to saved items and navigates to (.*) page$")
    public void customerSelectAProductToSavedItemsAndNavigateToMySavedItemsPage(String link) throws InterruptedException {
        homePage.searchForProductUsingRandomProductCode();
        plpPage.saveItemFromList();
        wishListPage.selectWishListView();
        wishListPage.selectSavedItemsFullPage();
        //homePage.navigateToMyAccountPage();
        //myAccountPage.selectMyAccountLinks(link);
    }

    @When("^customer select a product to saved items and navigates to MY SAVED ITEMS page on mobile$")
    public void customerSelectAProductToSavedItemsAndNavigatesToMYSAVEDITEMSPageOnMobile() throws Throwable {
        homePage.searchForProductUsingRandomProductCodeOnMobile();
        plpPage.saveItemFromList();
        homePage.clickMySavedItems();
        wishListPage.selectSavedItemsFullPage();
    }

    @When("^customer select (\\d+) product to saved items and navigates to (MY SAVED ITEMS) page$")
    public void customerSelectProductToSavedItemsAndNavigatesToMYSAVEDITEMSPage(int addNumberOfItems, String link) throws InterruptedException {
        homePage.searchForProduct("dress");
        plpPage.saveItemFromList(addNumberOfItems);
        wishListPage.selectWishListView();
        wishListPage.selectSavedItemsFullPage();
    }

    @When("^customer select (\\d+) product to saved items and navigates to my saved items page on mobile$")
    public void customerSelectProductToSavedItemsAndNavigatesToPageOnMobile(int addNumberOfItems) throws Throwable {
        homePage.mobileInsertTextAndSearch("dress");
        plpPage.saveItemFromList(addNumberOfItems);
        homePage.clickMySavedItems();
        wishListPage.selectSavedItemsFullPage();
    }

    @When("^customer move item from (MY SAVED ITEMS) to my bag$")
    public void customerMoveItemFromMYSAVEDITEMSToMyBag(String link) throws InterruptedException {
        homePage.searchForProductUsingRandomProductCode();
        plpPage.saveItemFromList();
        wishListPage.selectWishListView();
        wishListPage.selectSavedItemsFullPage();
        myBagPage.selectSizeOfTheProduct();
        mySavedItemsPage.moveSavedItemsToMyBag();
    }

    @When("^customer move item from my saved items to my bag on mobile$")
    public void customerMoveItemFromMySavedItemsToMyBagOnMobile() throws Throwable{
        homePage.searchForProductUsingRandomProductCodeOnMobile();
        plpPage.saveItemFromList();
        homePage.clickMySavedItems();
        wishListPage.selectSavedItemsFullPage();
        myBagPage.selectSizeOfTheProduct();
        mySavedItemsPage.moveSavedItemsToMyBag();
    }

    @When("^customer remove item from saved items$")
    public void customerRemoveItemFromSavedItems() {
        mySavedItemsPage.removeItemFromSavedItems();
    }

    @And("^customer selects (.*) option$")
    public void customerSelectsDeliveryOption(String deliveryOption) throws Throwable {
        checkoutAndPaymentsPage.selectDeliveryOrCollectionOption(deliveryOption.toUpperCase());
    }

    @And("^Navigate to checkout page$")
    public void navigateToCheckoutPage() throws Throwable {
        mySavedItemsPage.navigateToMybag();
        myBagPage.clickPrimarySecureCheckoutButton();
    }

    @When("^customer change size of the product$")
    public void customerChangeSizeOfTheProduct() {
        mySavedItemsPage.changeTheSizeOfTheProduct();
    }

    @When("^Customer (add|remove) a item to saved items from my bag page$")
    public void customerAddsRemovesAItemToSavedItemsFromMyBagPage(String addRemoveItem) throws InterruptedException
    {
	    switch (addRemoveItem.toUpperCase())
	    {
		    case "REMOVE":
			    homePage.searchForProductUsingRandomProductCode();
			    homePage.selectRandomProduct();
			    pdpPage.selectProductAndMoveToMyBagPage();
			    myBagPage.setSizeOfTheBag();
			    myBagPage.clickMoveToSavedItemsLink();
			    break;
		    case "ADD":
			    homePage.searchForProductUsingRandomProductCode();
			    homePage.selectRandomProduct();
			    pdpPage.selectProductAndMoveToMyBagPage();
			    myBagPage.setSizeOfTheBag();
			    homePage.searchForProduct("dress");
			    plpPage.saveItemFromList(2);
			    wishListPage.selectWishListView();
			    wishListPage.selectSavedItemsFullPage();
			    //homePage.navigateToMyAccountPage();
			    //myAccountPage.selectMyAccountLinks("MY SAVED ITEMS");
	    }
    }

    @When("^Customer (add|remove) a item to saved items from my bag page on mobile$")
    public void customerAddsRemovesAItemToSavedItemsFromMyBagPageOnMobile(String addRemoveItem) throws InterruptedException {
        switch (addRemoveItem.toUpperCase()) {
            case "REMOVE":
                homePage.searchForProductUsingRandomProductCodeOnMobile();
                homePage.selectRandomProduct();
                pdpPage.selectProductAndMoveToMyBagPage();
                myBagPage.setSizeOfTheBag();
                myBagPage.clickMoveToSavedItemsLink();
                break;
            case "ADD":
                homePage.searchForProductUsingRandomProductCodeOnMobile();
                homePage.selectRandomProduct();
                pdpPage.selectProductAndMoveToMyBagPage();
                myBagPage.setSizeOfTheBag();
                homePage.mobileInsertTextAndSearch("dress");
                plpPage.saveItemFromList(2);
                homePage.clickMySavedItems();
                wishListPage.selectSavedItemsFullPage();
        }
    }

    @And("^I select low stock size \"([^\"]*)\" from saved items$")
    public void iSelectLowStockSizeFromSavedItems(String size) throws Throwable {
        wishListPage.selectSizeFromDropDownWUrgMsgOn(pdpPage.getSizeDisplayOrderFromDropDownWUrgMsgOn(size));
    }

	@Then("^message \"([^\"]*)\" will display next to the size \"([^\"]*)\" value on saved items$")
	public void messageWillDisplayNextToTheSizeValueOnSavedItems(final String message, final String size)
    {
        final String expectedMessage = size + " - " + message;

        final Integer sizeOrder = pdpPage.getSizeDisplayOrderFromDropDownWUrgMsgOn(size);
		final String realMessage = wishListPage.getSizeTextFromDropDownWUrgentMsgOn(sizeOrder);
		Assert.assertEquals(realMessage, expectedMessage);
	}
}
