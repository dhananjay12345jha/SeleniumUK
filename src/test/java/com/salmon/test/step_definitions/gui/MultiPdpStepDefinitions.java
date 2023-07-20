package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.PdpPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gates on 08/03/18.
 */
public class MultiPdpStepDefinitions {

    private final String PRODUCT_DETAILS_HEADER = "Product Details & Care Guide";
	@Autowired
    private PdpPage pdpPage;

//    public MultiPdpStepDefinitions(PdpPage pdpPage){
//        this.pdpPage = pdpPage;
//    }


    @Then("^I should see Product details and Care Guide$")
    public void iShouldSeeProductDetailsAndCareGuide() {
        Assert.assertEquals(PRODUCT_DETAILS_HEADER, pdpPage.getProductDetailsHeader());
    }

    @Then("^product info details exists$")
    public void productInfoDetailsExists() {
        Assert.assertTrue(pdpPage.elementPresence( By.id("productInfoStructure")));

    }

    @And("^click on write a review link$")
    public void clickOnWriteAReviewLink() {
        pdpPage.clickWriteAReviewLink();
    }

    @Then("^the review popup exists$")
    public void theReviewPopupExists() {
        pdpPage.reviewPopUpExists();
    }

    @And("^a review exists$")
    public void aReviewExists() {
        pdpPage.reviewExists();
    }

    @Then("^\"([^\"]*)\" does not exist in the page$")
    public void doesNotExistInThePage(String text) {
        pdpPage.textDoesNotExistInPage(text);
    }

    @Then("^the preOrder notification textbox is populated with email address$")
    public void thePreOrderNotificationTextboxIsPopulatedWithEmailAddress() {
        Assert.assertEquals(Props.getProp("multiPdpUserEmail"), pdpPage.getEmailFromTextBox());
    }


    @Then("^the preOrder notification textbox is not populated with email address$")
    public void thePreOrderNotificationTextboxIsNotPopulatedWithEmailAddress() {
        Assert.assertEquals("", pdpPage.getEmailFromTextBox());

    }

    @Then("^review is only for main product$")
    public void reviewIsOnlyForMainProduct() {
        Assert.assertEquals(2, pdpPage.returnTotalNumberOfWriteAReviewLinks());

    }

    @And("^save (\\d+) multi pdp items$")
    public void saveMultiPdpItems(int numberOfItems) {
        pdpPage.saveMultiItemsFromMultiPdpPage(numberOfItems);
    }

	@And("^save (\\d+) carousel pdp items$")
	public void saveCarouselPdpItems(int numberOfItems) {
		pdpPage.saveMultiItemsFromMultiPdpPage(numberOfItems);
	}

    @And("^remove (\\d+) saved multi pdp items$")
    public void removeSavedMultiPdpItems(int numberOfItems) {
        pdpPage.removeSavedMultiItemsFromMultiPdpPage(numberOfItems);
    }

	@And("^remove (\\d+) saved carousel pdp items$")
	public void removeCarouselMultiPdpItems(int numberOfItems) {
		pdpPage.removeSavedMultiItemsFromMultiPdpPage(numberOfItems);
	}

    @And("^there are \"([^\"]*)\" find in store buttons$")
    public void thereAreFindInStoreButtons(int numberOfStoreButtons) {
        Assert.assertEquals(numberOfStoreButtons, pdpPage.getFindInStoreButtons());

    }

    @And("^add the (\\d+) product to the bag$")
    public void addTheProductToTheBag(int productNo) {
        pdpPage.selectSizeFromDropDownMultiPdp(productNo);
        pdpPage.clickAddToBagButton(productNo);
    }

    @Then("^a confirmation message is displayed$")
    public void aConfirmationMessageIsDisplayed(){
        Assert.assertTrue(pdpPage.getItemAddedToBagText().contains("Added to your bag"));
    }
}
