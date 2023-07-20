package com.salmon.test.step_definitions.gui;


import com.salmon.test.page_objects.gui.CheckOutPage;
import com.salmon.test.page_objects.gui.Features;
import com.salmon.test.page_objects.gui.PromotionsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 * Created by sreekanth.bongunuri on 06/01/16.
 */
public class PromotionsStepDefinitions {

	@Autowired
    private CheckOutPage checkOutPage;
	@Autowired
    private PromotionsPage promotionsPage;

//    public PromotionsStepDefinitions(CheckOutPage checkOutPage, PromotionsPage promotionsPage){
//        this.checkOutPage=checkOutPage;
//        this.promotionsPage=promotionsPage;
//    }


    @And("^the totals update based on the promotions qualified for by the remaining products with promotion \"([^\"]*)\"$")
    public void the_totals_update_based_on_the_promotions_qualified_for_by_the_remaining_products_with_promotion(String message) {
        assertTrue(promotionsPage.promoReceivedMessage().getText().contains("You've received this Promotion:"));
        assertTrue(promotionsPage.priceDiscountMessage().contains(message));
    }


    @And("^increase the \"([^\"]*)\" of the product$")
    public void increase_the_of_the_product(String pdpQunatity) {
        checkOutPage.pdpQuantity().clear();
        checkOutPage.pdpQuantity().sendKeys(pdpQunatity);
    }

    @And("^the potential promotion message is displayed$")
    public void the_potential_promotion_message_is_displayed() {
        assertTrue(checkOutPage.promoPotentialReceiveMessage().getText().contains("Add more to your bag to qualify for a promotion"));
    }

    @And("^proceed to remove one products from full cart page$")
    public void proceed_to_remove_one_products_from_full_cart_page() throws Throwable {
        checkOutPage.productRemoveLink().click();
    }


    @Then("^apply the \"([^\"]*)\" in the promo box$")
    public void apply_the_in_the_promo_box(String promotionCode)
    {
	    if (Features.PROMO_CODE_NEW_STICKY_CART)
	    {
		    promotionsPage.havePromoCodeLabel().click();
	    }
	    promotionsPage.promoCodeTextBox().sendKeys(promotionCode);
    }

    @And("^click apply$")
    public void click_apply() {
        promotionsPage.applyPromoCodeButton().click();
        promotionsPage.pause(1200);
        promotionsPage.waitForPageLoad();
    }

    @Then("^you should see percentage discount \"([^\"]*)\"$")
    public void you_should_see_percentage_discount(String discountMessage) {
       // assertTrue(promotionsPage.percentageDiscountMessage().getText().contains(discountMessage));
        promotionsPage.waitForTextToBePresentInElement(promotionsPage.percentageDiscountMessage,discountMessage, 10);
    }

    @Then("^you should see price discount \"([^\"]*)\"$")
    public void you_should_see_price_discount(String message) {
        assertTrue(promotionsPage.priceDiscountMessage().contains(message));
        promotionsPage.clickRemovePromoLink();
    }


}
