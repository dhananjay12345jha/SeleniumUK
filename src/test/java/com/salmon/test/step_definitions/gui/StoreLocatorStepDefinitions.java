package com.salmon.test.step_definitions.gui;


import com.salmon.test.page_objects.gui.CheckOutPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.HomePageOld;
import com.salmon.test.page_objects.gui.StoreLocatorPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StoreLocatorStepDefinitions
{
	private String searchLocation;
	@Autowired
	private StoreLocatorPage storeLocatorPage;
	@Autowired
	private CheckOutPage checkOutPage;
	@Autowired
	private HomePageOld homePageOld;
	@Autowired
	private HomePage homePage;

//	public StoreLocatorStepDefinitions(StoreLocatorPage storeLocatorPage, CheckOutPage checkOutPage, HomePageOld homePageOld, HomePage homePage)
//	{
//		this.storeLocatorPage = storeLocatorPage;
//		this.checkOutPage = checkOutPage;
//		this.homePageOld = homePageOld;
//		this.homePage = homePage;
//	}

	@When("^I can click the link of store locator$")
	public void I_can_click_the_link_of_store_locator()
	{
		storeLocatorPage.storeLocatorLink().click();
	}

	@When("^I can click the link of store locator on mobile$")
	public void I_can_click_the_link_of_store_locator_on_mobile()
	{
		homePage.clickStoreLocatorLinkOnMobile();
	}

	@Then("^the page reloads to display the Store Finder search page.$")
	public void the_page_reloads_to_display_the_Store_Finder_search_page()
	{
		assertTrue(storeLocatorPage.storeFinderTextBox().isDisplayed());
		assertTrue(storeLocatorPage.findStoresButton().isDisplayed());
		assertTrue(storeLocatorPage.storesNearMeButton().isDisplayed());
		assertTrue(storeLocatorPage.changeCountryLink().isDisplayed());
	}

	@When("^I am viewing a page with the reduced header like  Checkout page$")
	public void I_am_viewing_a_page_with_the_reduced_header_like_Checkout_page()
	{
		checkOutPage.searchField().sendKeys("1920012");
		checkOutPage.searchButton().click();
		homePageOld.selectPdpProductImage().click();
		checkOutPage.selectSizeOfTheProduct();
		checkOutPage.addToCartButton().click();

		JavascriptExecutor jse = (JavascriptExecutor) storeLocatorPage.getWebDriver();
		jse.executeScript("scroll(0, -250);");

		checkOutPage.basketIcon().click();
		checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
		checkOutPage.switchToCheckoutIframe();
		checkOutPage.checkOutButton().click();
		checkOutPage.checkOutButton().click();
	}

	@Then("^I do not see a link displayed for 'Stores'$")
	public void I_do_not_see_a_link_displayed_for_Stores()
	{
		storeLocatorPage.getWebDriver().navigate().refresh();
		assertFalse(storeLocatorPage.storeLocatorLinkPresent());
	}


	@And("^click on change country link$")
	public void click_on_change_country_link()
	{
		storeLocatorPage.changeCountryLink().click();
	}


	@Then("^my \"([^\"]*)\" is preset for me underneath$")
	public void my_is_preset_for_me_underneath(String country)
	{
		storeLocatorPage.pause(2000);
		assertTrue(storeLocatorPage.countrySelected().getText().contains(country));
	}


	@Then("^the selected \"([^\"]*)\" should be prepopulate in the drop down list$")
	public void the_selected_should_be_prepopulate_in_the_drop_down_list(String country)
	{
		storeLocatorPage.pause(3000);
		String selectedCountryValue = storeLocatorPage.selectedCountryDropdownValue(country).getText().trim();
		assertTrue(selectedCountryValue.equals(country));
	}

	@Then("^the selected \"([^\"]*)\" should be prepopulate in the drop down list on mobile$")
	public void the_selected_should_be_prepopulate_in_the_drop_down_list_on_mobile(String country)
	{
		storeLocatorPage.pause(3000);
		String selectedCountryValue = homePage.selectedCountryDropdownValueMobile().getAttribute("label").trim();
		assertTrue(selectedCountryValue.equals(country));
	}

	@And("^I enter a search term \"([^\"]*)\"$")
	public void I_enter_a_search_term(String location)
	{
		this.searchLocation = location;
		storeLocatorPage.storeFinderTextBox().sendKeys(location);
	}

	@And("^I click on Find Stores CTA$")
	public void I_click_on_Find_Stores_CTA()
	{
		storeLocatorPage.findStoresButton().click();
	}

	@Then("^the page reloads and a store results page is displayed$")
	public void the_page_reloads_and_a_store_results_page_is_displayed()
	{
		assertTrue(storeLocatorPage.totalSearchResults().isDisplayed());
		assertTrue(storeLocatorPage.storeResultsList().isDisplayed());
	}

	@Then("^start new search by clicking link (.*)$")
	public void the_page_reloads_and_a_store_results_page_is_displayed2(String text)
	{
		storeLocatorPage.getLink(text.trim()).click();
	}

	@Then("^I am presented with a list of results and a line of text that tells me the quantity of results returned that matched my search term$")
	public void I_am_presented_with_a_list_of_results_and_a_line_of_text_that_tells_me_the_quantity_of_results_returned_that_matched_my_search_term()
	{
		int results = Integer.valueOf(storeLocatorPage.totalSearchResults().getText().replaceAll("[^0-9]", ""));

		assertEquals(results, storeLocatorPage.totalSearchedList().size());
		assertTrue(storeLocatorPage.totalSearchResults().getText().split("“")[1].contains(this.searchLocation));
	}

	@Then("^I am shown a maximum of (\\d+) stores based on their proximity to my search location$")
	public void I_am_shown_a_maximum_of_stores_based_on_their_proximity_to_my_search_location(int arg1)
	{
		assertTrue(storeLocatorPage.totalStoresDisplayed() == 10);
	}

	@And("^they are listed in order of their proximity$")
	public void they_are_listed_in_order_of_their_proximity()
	{
		List<String> distances = storeLocatorPage.distance();
		for (int i = 1; i < distances.size(); i++)
		{
			assertTrue(Float.parseFloat(distances.get(i - 1)) <= Float.parseFloat(distances.get(i)));
		}
	}

	@When("^I perform another search by clicking on the 'search again' link$")
	public void I_perform_another_search_by_clicking_on_the_search_again_link()
	{
		storeLocatorPage.searchAgainLink().click();
	}

	@Then("^click on moreinfo link$")
	public void click_on_moreinfo_link()
	{
		storeLocatorPage.clickMoreInfoLink();
	}

	@And("^store details should be displayed as expected$")
	public void store_details_should_be_displayed_as_expected()
	{
		assertTrue(storeLocatorPage.shopName().isDisplayed());
		assertTrue(storeLocatorPage.shopAddress().isDisplayed());
		assertTrue(storeLocatorPage.distanceFromSearch().isDisplayed());
	}

	@And("^from store info page click on back to results link$")
	public void from_store_info_page_click_on_back_to_results_link()
	{
		storeLocatorPage.backToResultsLink().click();
	}

	@Then("^you should see error message displayed$")
	public void you_should_see_error_message_displayed() throws Throwable
	{
		assertTrue(storeLocatorPage.storeLocatorErrorMessage().getText().contains("Check you've entered a valid postcode or place name"));
	}

	@And("^click on near by button$")
	public void click_on_near_by_button()
	{
		storeLocatorPage.nearMeButton().click();
	}
}
