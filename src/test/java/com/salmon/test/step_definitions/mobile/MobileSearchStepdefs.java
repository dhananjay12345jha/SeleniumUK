package com.salmon.test.step_definitions.mobile;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import org.springframework.beans.factory.annotation.Autowired;

public class MobileSearchStepdefs {

	@Autowired
    private CheckoutLoginPage checkoutLoginPage;
	@Autowired
    private CheckoutAndPaymentsPage checkoutAndPaymentsPage;
	@Autowired
    private HomePage homePage;
	@Autowired
    private PdpPage pdpPage;

//    public MobileSearchStepdefs(MyBagPage myBagPage,CheckoutLoginPage checkoutLoginPage,CheckoutAndPaymentsPage checkoutAndPaymentsPage, HomePage homePage, PdpPage pdpPage) {
//        this.checkoutLoginPage = checkoutLoginPage;
//        this.checkoutAndPaymentsPage = checkoutAndPaymentsPage;
//        this.homePage = homePage;
//        this.pdpPage = pdpPage;
//    }

    @And("^searches for product on mobile (.*)$")
    public void searchesForProduct(String product) {
        homePage.mobileInsertTextAndSearch(Props.getProp(product));
    }

    @And("^searches for random product on mobile$")
    public void searchesForRandomProduct() throws Throwable {
        homePage.mobileInsertTextAndSearch(Props.getProp("productCode_brand"));
    }
}
