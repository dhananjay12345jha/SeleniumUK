package com.salmon.test.step_definitions.gui.then;

import com.salmon.test.page_objects.gui.CheckOutPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.MyBagPage;
import com.salmon.test.page_objects.gui.MySavedItemsPage;

import io.cucumber.java.en.Then;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


public class MyAccountSavedITemsThenStepDefs {
	@Autowired
    private MySavedItemsPage mySavedItemsPage;
	@Autowired
    private MyBagPage myBagPage;
	@Autowired
    private CheckOutPage checkOutPage;
	@Autowired
    private HomePage homePage;

//    public MyAccountSavedITemsThenStepDefs(final MySavedItemsPage mySavedItemsPage,
//                                           final MyBagPage myBagPage,
//                                           final CheckOutPage checkOutPage,
//                                           final HomePage homePage) {
//        this.mySavedItemsPage = mySavedItemsPage;
//        this.myBagPage = myBagPage;
//        this.checkOutPage = new CheckOutPage();
//        this.homePage = new HomePage();
//    }


    @Then("^my product should be displayed in my account saved item page$")
    public void myProductShouldBeDisplayedInMyAccountSavedItemPage() {
        Assert.assertTrue(mySavedItemsPage.getMySavedItemList());
    }

    @Then("^my saved items page is displayed with buttons$")
    public void mySavedItemsPageIsDisplayedWithButtons(List<String> stringList) {
        for (String contents : stringList){
	        assertThat(mySavedItemsPage.getHeaderH3Label().get(0)).isEqualTo(contents);
        }
    }

    @Then("^my saved items page has (\\d+) products and \"([^\"]*)\" button$")
    public void mySavedItemsPageHasProductsAndButton(int numberOfProducts, List<String> stringList) {
        for (String contents : stringList) {
            assertThat(mySavedItemsPage.getHeaderH3Label().get(0)).isEqualTo(contents);
        }
	    Assert.assertEquals(numberOfProducts, mySavedItemsPage.getNumberOfItemsSaved());
    }

    @Then("^my saved items page has (\\d+) products and buttons$")
    public void mySavedItemsPageHasProductsAndButtons(int numberOfProducts, List<String> stringList) {
        mySavedItemsPageHasProductsAndButton(numberOfProducts, stringList);
    }

    @Then("^my bag should have moved items$")
    public void myBagShouldHaveMovedItems() {
        assertThat(mySavedItemsPage.getMovedToBagMessage());
        mySavedItemsPage.navigateToMybag();
        assertThat(myBagPage.getAddedItemsExist());
    }

    @Then("^Item should be removed$")
    public void itemShouldBeRemoved() {
        Assert.assertEquals(0, mySavedItemsPage.getNumberOfItemsSaved());
    }

    @Then("^Item should have same size in checkout page$")
    public void itemShouldHaveSameSizeInCheckoutPage() {
        checkOutPage.pause(1000);
        String selectedSize = mySavedItemsPage.moveSavedItemsToMyBag();
        checkOutPage.pause(2500);
        checkOutPage.basketIcon().click();
        String sizeOfProductInMyBagPage = myBagPage.getTheSizeOfTheProduct();
        Assert.assertTrue(selectedSize.contains(sizeOfProductInMyBagPage));

    }

    @Then("^Item should have same size in checkout page on mobile$")
    public void itemShouldHaveSameSizeInCheckoutPageOnMobile() {
        checkOutPage.pause(1000);
        String selectedSize = mySavedItemsPage.moveSavedItemsToMyBag();
        checkOutPage.pause(2500);
        homePage.clickBagIcon();
        String sizeOfProductInMyBagPage = myBagPage.getTheSizeOfTheProductOnMobile();
        Assert.assertTrue(selectedSize.contains(sizeOfProductInMyBagPage));
    }

    @Then("^My bag counter should be (.*)$")
    public void myBagCounterShouldBeCounterStatus(String counterSize) {
        int previousCounterSize;
        int currentCounterSize;
        switch (counterSize.toUpperCase()) {
            case "DECREMENT":
                previousCounterSize = Integer.parseInt(myBagPage.getMyBagCounter());
	             myBagPage.pause(3000);
                myBagPage.setSizeOfTheBag();
                currentCounterSize = Integer.parseInt(myBagPage.getMyBagCounter());
                Assert.assertNotEquals(previousCounterSize, currentCounterSize);
                break;
            case "INCREMENT":
                previousCounterSize = Integer.parseInt(myBagPage.getMyBagCounter());
                mySavedItemsPage.selectSizeForAllProductsInSavedItems();
                mySavedItemsPage.moveSavedItemsToMyBag();
                mySavedItemsPage.pause(1000);
                mySavedItemsPage.navigateToMybag();
	             myBagPage.pause(3000);
                myBagPage.setSizeOfTheBag();
                myBagPage.pause(1000);
                currentCounterSize = Integer.parseInt(myBagPage.getMyBagCounter());
                Assert.assertNotEquals(previousCounterSize, currentCounterSize);
        }
    }
}
