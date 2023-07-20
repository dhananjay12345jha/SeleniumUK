package com.salmon.test.step_definitions.api;


import com.salmon.test.framework.helpers.Props;
import com.salmon.test.services.NewLookTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;


import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;


public class ProductAttributeEnabledDisabledStepDefinitions {
    @When("^I enable feature bazaarVoice for productNumber \"([^\"]*)\"$")
    public void i_enable_bazaarVoice_feature_for_productNumber(String productNumber) {
        NewLookTest.postNewLookBazaarVoiceImpex(Props.getProp(productNumber),"true"); }

    @When("^I disable feature bazaarVoice for productNumber \"([^\"]*)\"$")
    public void i_disable_bazaarVoice_feature_for_productNumber(String productNumber) {
        NewLookTest.postNewLookBazaarVoiceImpex(Props.getProp(productNumber),"false"); }

    @When("^Klarna Merchant Config is \"([^\"]*)\"$")
    public void klarna_merch_conf_is_true_or_false(String status)
    {
        if(IS_MOBILE)
        {
            NewLookTest.postKlarnaMerchConfigUpdateImpexMobile(status);
        }
        else
        NewLookTest.postKlarnaMerchConfigUpdateImpex(status);
    }

    @Before("@CreateOrderPromotion$")
    public void createOrderLevelPromotion() {
        NewLookTest.postCreateOrderLevelPromotion();
    }

    @And("^Create product level promotions for \"([^\"]*)\"$")
    public void createProductLevelPromotionsFor(String category) {
        NewLookTest.postCreateProductPercentagePromotion(category);
    }

    @After("@RemoveProductPromotion")
    public void disableProductPromotionCreatedInTests()
    {
        NewLookTest.postRemovePromotion("5PercentOff_UI_AUTO_PRODUCT");
    }

    @After("@RemoveOrderPromotion")
    public void disableOrderPromotionCreatedInTests()
    {
        NewLookTest.postRemovePromotion("5FixedPriceOff_UI_AUTO_ORDER");
    }

	@Before("@DefaultDelivery")
    public void removeDefaultHomeDelivery()
    {
	      NewLookTest.postSetDefaultHomeDelivery("false");
	 }

   @After("@DefaultDelivery")
   public void addDefaultHomeDelivery()
   {
	     NewLookTest.postSetDefaultHomeDelivery("true");
	}
}
