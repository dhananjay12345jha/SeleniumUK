package com.salmon.test.step_definitions.gui.then;

import com.salmon.test.page_objects.gui.StoreLocatorPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;

public class StoreFinderPageThenStepDefs
{

	@Autowired
	private StoreLocatorPage storeLocatorPage;

//	public StoreFinderPageThenStepDefs(StoreLocatorPage storeLocatorPage)
//	{
//
//		this.storeLocatorPage = storeLocatorPage;
//	}

	@Then("^Store finder page should show map pins$")
	public void storeFinderPageShouldShowMapPins()
	{
		Assert.assertTrue(storeLocatorPage.getGoogleMapPins());
	}

	@Then("^The number of pins displayed in store finder page should equal to stores displayed in search result$")
	public void theNumberOfPinsDisplayedInStoreFinderPageShouldEqualToStoresDisplayedInSearchResult() throws Throwable
	{
		Assert.assertEquals(storeLocatorPage.getNumberOfMapPinsDisplayedInMap(),
							storeLocatorPage.getNumberOfStoreResultsDisplayed());
	}

	@Then("^The result displayed should match with the map result$")
	public void theResultDisplayedShouldMatchWithTheMapResult()
	{
		Assert.assertEquals(storeLocatorPage.getTitleOfFirstFromResults(),
							storeLocatorPage.getLocationFromTheMapsResult());
	}

	@Then("^The result displayed should match with the map result on store finder page$")
	public void theResultDisplayedShouldMatchWithTheMapResultOnStoreFinderPage()
	{
		final String mapsResult;
		final String titleFromResults;
		if (IS_MOBILE)
		{
			storeLocatorPage.clickMobileStoreResultsTabButton();
			//click on a map pin to get its details
			storeLocatorPage.clickTheFirstResultPinInMapMobile();
			mapsResult = storeLocatorPage.getLocationFromTheMapsResult();
			storeLocatorPage.clickMobileStoreResultsTabList();
		}
		else
		{
			mapsResult = storeLocatorPage.getLocationFromTheMapsResult();
		}

		titleFromResults = storeLocatorPage.getTitleOfFirstFromResultsOnStoreFinderPage();

		Assert.assertEquals(titleFromResults, mapsResult);
	}

	@And("^I click on map tab button$")
	public void iClickOnMapTabButton()
	{
		storeLocatorPage.clickMobileStoreResultsTabButton();
	}
}
