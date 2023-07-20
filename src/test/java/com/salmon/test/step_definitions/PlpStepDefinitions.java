package com.salmon.test.step_definitions;


import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.salmon.test.page_objects.gui.CheckOutPage;
import com.salmon.test.page_objects.gui.PlpPage;
import com.salmon.test.page_objects.gui.PlpPageOld;
import com.salmon.test.properties.FacetProperties;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.When;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


public class PlpStepDefinitions
{
	//private  CheckOutPage checkOutPage;
	@Autowired
	private PlpPageOld plpPageOld;
	private int totalImages;
	private int totalImagesAfterApplyingFilter;
	private Response response;
	@Autowired
	private PlpPage plpNewPage;
	@Autowired
	private CheckOutPage checkOutPage;

//	public PlpStepDefinitions(PlpPageOld plpPageOld, PlpPage plpNewPage, CheckOutPage checkOutPage)
//	{
//		this.plpPageOld = plpPageOld;
//		this.plpNewPage = plpNewPage;
//		this.checkOutPage = checkOutPage;
//	}


	@Then("^I should see the \"([^\"]*)\" related to it$")
	public void I_should_see_the_related_to_it(String suggestions)
	{
		List<String> suggestionsFound = plpNewPage.getSearchSuggestions();
//        suggestionsFound = plpPageOld.getSearchSuggestions();

		for (String aSuggestionsFound : suggestionsFound)
		{
			assertTrue(aSuggestionsFound.toLowerCase().contains(suggestions));
		}
	}

	@Then("^I should see the product \"([^\"]*)\" related to it$")
	public void I_should_see_the_product_related_to_it(String suggestions)
	{
		List<String> suggestionsFound;
		suggestionsFound = plpPageOld.getProductSuggestednames();
		assertTrue(suggestionsFound.get(0).contains(suggestions));
	}


	@And("^I should see product \"([^\"]*)\" related to the search$")
	public void I_should_see_product_related_to_the_search(String suggestions)
	{

		List<String> suggestionsFound;
		suggestionsFound = plpPageOld.getProductSearchSuggestions();
		assertTrue(suggestionsFound.get(0).contains(suggestions));

	}


	@Then("^the page reloads with a page that tells me zero results have been matched against my \"([^\"]*)\"$")
	public void the_page_reloads_with_a_page_that_tells_me_zero_results_have_been_matched_against_my(String searchWord)
	{

		assertTrue(plpPageOld.noMathcesFoundText().getText().contains("'asdfsafa'"));

	}
//
//    @Then("^I am presented with the product listing by default$")
//    public void I_am_presented_with_the_product_listing_by_default() throws Throwable {
//        Thread.sleep(2000);
//        //checkOutPage.searchButton().click();
//    }

	@And("^I am able to 'toggle' to the Content results by using the department facets displayed at the top of the page$")
	public void I_am_able_to_toggle_to_the_Content_results_by_using_the_department_facets_displayed_at_the_top_of_the_page()
	{
		assertTrue(plpPageOld.departmentFacets().isDisplayed());
	}


	@And("^the department facet for the results I am viewing i.e. Product or Content is displayed in a 'selected' state$")
	public void the_department_facet_for_the_results_I_am_viewing_i_e_Product_or_Content_is_displayed_in_a_selected_state() throws InterruptedException
	{
		plpPageOld.departmentFacetToggle();
	}


	@And("^I am unable to select more than one department facet at once$")
	public void I_am_unable_to_select_more_than_one_department_facet_at_once() throws InterruptedException
	{
		plpPageOld.departmentFacetToggle();
	}

	@And("^navigate to Mens home$")
	public void navigate_to_Mens_home()
	{
		plpPageOld.mensHomeLink().click();

	}


	@Then("^I am able to select a value for sort relevance by descending order$")
	public void I_am_able_to_select_a_value_for_sort_relevance_by_descending_order()
	{
		plpPageOld.sortByNameDescending().click();
		assertTrue(plpPageOld.currentProductCount() > 0);
	}


	@And("^the product set will automatically refresh in the background in order to display products containing that value from that facet, and no other values from that facet$")
	public void the_product_set_will_automatically_refresh_in_the_background_in_order_to_display_products_containing_that_value_from_that_facet_and_no_other_values_from_that_facet() throws InterruptedException
	{

		List<String> names;
		names = plpPageOld.getproductNames();

		for (int i = 0; i < names.size(); i++)
		{
			if (i == names.size() - 1)
			{
				break;
			}
			int compare = names.get(i + 1).compareTo(names.get(i));
			assertTrue(compare <= 0);
		}

	}


	@And("^the facet menu remains open$")
	public void the_facet_menu_remains_open()
	{
		assertTrue(plpPageOld.facetOptions().isDisplayed());
	}


	@Then("^I am able to select relevance \"([^\"]*)\" for prices$")
	public void I_am_able_to_select_relevance_for_prices(String value)
	{
		plpPageOld.selectPriceValue(value);
		assertTrue(plpPageOld.currentProductCount() > 0);


	}

	@Then("^I am able to select relevance \"([^\"]*)\"$")
	public void I_am_able_to_select_relevance(String value)
	{
		plpPageOld.selectPriceValue(value);
	}

	@And("^the product set will automatically refresh in the background in order to display products prices in \"([^\"]*)\"$")
	public void the_product_set_will_automatically_refresh_in_the_background_in_order_to_display_products_prices_in(
			String order) throws Throwable
	{
		List<String> prices;
		prices = plpPageOld.getPrices();

		for (int i = 0; i < prices.size(); i++)
		{
			if (i == prices.size() - 1)
			{
				break;
			}
			if (order.equals("ascending"))
			{
				assertTrue(Float.parseFloat(prices.get(i)) <= Float.parseFloat(prices.get(i + 1)));
			}
			if (order.equals("descending"))
			{
				assertTrue(Float.parseFloat(prices.get(i)) >= Float.parseFloat(prices.get(i + 1)));
			}

		}
	}


	@And("^I should see categories \"([^\"]*)\" related to the search$")
	public void I_should_see_categories_related_to_the_search(String products)
	{


		List<String> productSuggestions;
		productSuggestions = plpPageOld.getProductSuggestions();

		for (String suggestionsFound : productSuggestions)
		{
			assertTrue(suggestionsFound.contains(products));
		}
	}

	@And("^I should see \"([^\"]*)\" related to the search$")
	public void I_should_see_related_to_the_search(String products)
	{
		List<String> productSuggestions;
		productSuggestions = plpPageOld.getProductSuggestions();

		for (String suggestionsFound : productSuggestions)
		{
			assertTrue(suggestionsFound.contains(products));
		}

	}


	@When("^I select \"([^\"]*)\" for \"([^\"]*)\" , \"([^\"]*)\" for \"([^\"]*)\"$")
	public void I_select_for_for(String heading1,
								 String colour,
								 String heading2,
								 String accesories) throws InterruptedException
	{
		plpPageOld.clickOnFacadeColour();
		String colourFacetCount = plpPageOld.getColourFacetCount();
		plpPageOld.selectColour(colour);
		Thread.sleep(3000);
		assertEquals(plpPageOld.returnTotalProductsAvailable(), Integer.parseInt(colourFacetCount));
		plpPageOld.clickOnFacadeColour();
		plpPageOld.clickOnFacadePrice();
		Thread.sleep(3000);

	}

	@And("^the product listing is refined to present products with the selected value attribute only$")
	public void the_product_listing_is_refined_to_present_products_with_the_selected_value_attribute_only() throws InterruptedException
	{
		List<String> prices;
		prices = plpPageOld.getPrices();
		for (String price : prices)
		{
			assertTrue(Integer.parseInt(price) <= 19.99);
		}
	}

	@Then("^the default image set for a particular product should be displayed$")
	public void the_default_image_set_for_a_particular_product_whould_be_displayed() throws Throwable
	{
		assertTrue(plpPageOld.getDataModelOnImage().contains("226604110M1"));
		assertTrue(plpPageOld.modalOnElementIsClickable());

	}

	@Then("^the default image in the search should be a model on image$")
	public void the_default_image_in_the_search_should_be_a_model_on_image()
	{
		assertTrue(plpPageOld.getDataModelOnImage().contains("226604110M1"));

		assertTrue(plpPageOld.defaultImageInPdp().getAttribute("src").contains("226604110M1"));
	}

	@And("^no product or content results can be matched$")
	public void no_product_or_content_results_can_be_matched()
	{

		assertFalse(plpPageOld.suggestionsAvailable());
	}

	@And("^navigate to Teens home$")
	public void navigate_to_Teens_home()
	{
		plpPageOld.teensHomeLink().click();
	}

	@And("^navigate to Womens home$")
	public void navigate_to_Womens_home() throws InterruptedException
	{
		plpPageOld.womensHomeLink().click();
		this.totalImages = plpPageOld.returnTotalProductsAvailable();
	}

//    @And("^each facet option has a bin count$")
//    public void each_facet_option_has_a_bin_count() {
//        this.departmentFacetBinValue = plpPageOld.getDepartmentFacetBinValues();
//    }

//    @And("^I can select a facet to refine the product listing to exclude the other departments$")
//    public void I_can_select_a_facet_to_refine_the_product_listing_to_exclude_the_other_departments() throws InterruptedException {
//        plpPageOld.toggleBetweenDepartFacets();
//    }

	@When("^I make a call to the newlook service with facet value \"([^\"]*)\"$")
	public void I_make_a_call_to_the_newlook_service_with_facet_value(String facetValue)
	{
		given().contentType(ContentType.JSON);
		response = given().when()
						  .get(("http://ta01.nlk374.neoworks.co.uk/uk/UK-Mens-Department/c/uk-mens.json?page=0&q=:" +
								facetValue +
								""));
	}

	@Then("^response code should be (\\d+)$")
	public void response_code_should_be(int arg1)
	{
		assertTrue(response.statusCode() == 200);
	}

	@And("^the sort value from the json response should be \"([^\"]*)\"$")
	public void the_sort_value_from_the_json_response_should_be(String facetValue) throws Throwable
	{
		assertEquals(response.then().extract().path("data.pagination.sort").toString(), facetValue);
	}

	@When("^I click on a \"([^\"]*)\" using the megamenu navigation$")
	public void I_click_on_a_using_the_megamenu_navigation(String category)
	{
		//homePage.hoverOverMegaBrand(category);
		//homePageNew.selectMegaMenu(category);
	}

	@And("^select the \"([^\"]*)\"$")
	public void selectThe(String arg0) throws Throwable
	{
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@And("^select the sorting criteria as \"([^\"]*)\"$")
	public void selectTheSortingCriteriaAs(String sortCriteria) throws Exception
	{
		plpNewPage.clickSortingCriteria(sortCriteria);
	}

	@Given("^I search for the product with the specified criteria \"([^\"]*)\"$")
	public void iSearchForTheProductWithTheSpecifiedCriteria(String criteria)
	{
		checkOutPage.searchField().sendKeys(criteria);
		checkOutPage.searchButton().click();
	}

	@Then("^price slider can be moved from left and right$")
	public void priceSliderMove() throws Exception
	{
		assertThat(plpNewPage.getsliderLine().isDisplayed());
		assertTrue(plpNewPage.performDragDrop(plpNewPage.getSliderLeftIcon(), 36, 3));
		assertTrue(plpNewPage.performDragDrop(plpNewPage.getSliderRightIcon(), -30, 3));
	}

	@Then("^total displayed products changes after moving the price slider from both sides$")
	public void totalDisplayedProductsChangesAfterMovingThePriceSliderFromBothSides() throws Exception
	{
		assertTrue(plpNewPage.checkProductResultChange());
	}

	@And("^Filter count is (\\d+)$")
	public void filterCountIs(final int count)
	{
		assertEquals(count, plpNewPage.getMobileFilterCount());
	}

	@When("^I remove a filter$")
	public void iRemoveAFilter()
	{
		plpNewPage.removeFilter();
	}

	@And("^The filter and sort buttons are visible side by side$")
	public void theFilterAndSortButtonsAreVisibleSideBySide()
	{
		final WebElement filterButton = plpNewPage.getFilterButton();
		final WebElement sortButton = plpNewPage.getSortButton();

		assertTrue(filterButton.isDisplayed());
		assertTrue(sortButton.isDisplayed());
		assertEquals(sortButton.getLocation().getY(), filterButton.getLocation().getY());
	}

	@When("^I apply a filter$")
	public void iApplyAFilter()
	{
		plpNewPage.clickFilterButtonOnMobile();
		plpNewPage.selectFacetButton(FacetProperties.colourFacet());
		plpNewPage.selectColoursFromOpenFacet(FacetProperties.colourFacetValue());
	}

	@And("^I click on the close filters button$")
	public void iClickOnTheCloseFiltersButton()
	{
		plpNewPage.getCloseFiltersButton().click();
	}

	@Then("^The item count is visible below the filter and sort buttons$")
	public void theItemCountIsVisibleBelowTheFilterAndSortButtons()
	{
		final WebElement itemCount = plpNewPage.getItemCount();
		final WebElement sortButton = plpNewPage.getSortButton();

		assertTrue(itemCount.isDisplayed());
		assertTrue(itemCount.getLocation().getY() > sortButton.getLocation().getY());
	}

    @And("^I click on the first ancestor breadcrumb$")
    public void iClickOnTheFirstAncestorBreadcrumb()
    {
        plpNewPage.getFirstAncestorBreadcrumb().click();
    }
}
