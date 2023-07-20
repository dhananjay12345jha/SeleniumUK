package com.salmon.test.step_definitions.gui.given;


import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.HomePage;
import io.cucumber.java.en.Given;

import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;


public class NewLookHomePageGivenStepDefs
{
	//@Inject
	@Autowired
	private HomePage homePage;

    @Given("^Customer navigates to the Newlook website$")
    public void customerNavigatesToTheNewlookWebsite() throws MalformedURLException
	{
        UrlBuilder.startAtHomePage();
    	//homePage.navigateToHomePage();
    }

//    @Given("^UK base store$")
//    public void goToUKBaseSite() {
//        homePage.goToSite(uiApp.getBaseUri() + BASE_STORE_PATH_UK);
//        homePage.clearLocalStorageAndCookies();
//        homePage.setBaseCountry("uk");
//    }
}
