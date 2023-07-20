package com.salmon.test.step_definitions.gui.when;

import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.StoreLocatorPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import org.springframework.beans.factory.annotation.Autowired;

public class StoreFinderWhenStepDefs {

	@Autowired
    private HomePage homePage;
	@Autowired
    private StoreLocatorPage storeLocatorPage;

//    public StoreFinderWhenStepDefs(HomePage homePage,
//                                   StoreLocatorPage storeLocatorPage) {
//        this.homePage = homePage;
//        this.storeLocatorPage = storeLocatorPage;
//    }




    @And("^customer navigate to store finder page$")
    public void customerNavigateToStoreFinderPage() {
        homePage.clickStoreFinderLink();
    }

    @When("^search for (.*) in store finder page$")
    public void searchForStoreInStoreFinderPage(String searchTerm) {
        storeLocatorPage.searchForStores(searchTerm);
    }

    @And("^click pin in the map$")
    public void clickPinInTheMap() throws Throwable {
        storeLocatorPage.clickTheFirstResultPinInMap();
    }
}
