package com.salmon.test.step_definitions.gui;


import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.step_definitions.gui.when.SingInFeatureWhenStepDefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static com.salmon.test.framework.helpers.WebDriverHelper.PLATFORM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by neoworks on 03/02/17.
 */


public class PlpSteps
{


	int countBeforeFilter;
	String url;
	List<String> tmp_facetsSeleted = new ArrayList<>();
	private DataUnderTest dataUnderTest;
	@Autowired
	private HomePage homePageNew;
	@Autowired
	private PdpPage pdpNewPage;
	@Autowired
	private PlpPage plpNewPage;
	private ProductCodeProvider productCodeProvider;
	private int tmpData_facetFilterCount;
	@Autowired
	private PlpPageOld plpPageOld;
	private int totalImages;
	private int totalImagesAfterApplyingFilter;
	private List<String> departmentFacetBinValue = new ArrayList<>();
	private Logger log = LogManager.getLogger(SingInFeatureWhenStepDefs.class.getName());
	@Autowired
	private HomePage homePage;
	@Autowired
	private WishListPage wishListPage;

//	public PlpSteps(HomePage homePageNew, DataUnderTest dataUnderTest, PdpPage pdpNewPage, PlpPage plpNewPage, PlpPageOld plpPageOld, HomePage homePage, WishListPage wishListPage)
//	{
//		this.homePageNew = homePageNew;
//		this.plpNewPage = plpNewPage;
//		this.pdpNewPage = pdpNewPage;
//		this.dataUnderTest = dataUnderTest;
//		this.baseCountry = dataUnderTest.getBaseCountry();
//		this.plpPageOld = plpPageOld;
//		url = UrlBuilder.getWebsiteUrl();
//		this.homePage = homePage;
//		this.wishListPage = wishListPage;
//	}


//    @Then("^pdp should show correct details of the product$")
//    public void pdpShouldShowCorrectDetailsOfTheProduct() throws Throwable {
//    final String baseCountry = dataUnderTest.getBaseCountry();
//        String productCode = pdpNewPage.getProductCode();
//        ProductPojo product = productCodeProvider.getProductDetails(productCode, dataUnderTest.getBaseCountry());
//        assertThat(pdpNewPage.getName()).isEqualToIgnoringWhitespace(product.getData().getName());
//        assertThat(pdpNewPage.getPrice().contains(String.valueOf(product.getData().getPrice().getValue())));
//        int actButtons = (pdpNewPage.getNumberOfButtons(pdpNewPage.getReviewSection(), "WRITE A REVIEW"));
//        int actLink = (pdpNewPage.getNumberOfLinks(pdpNewPage.getReviewSection(), "Be the first to write a Review"));
//        assertThat(actButtons == 1 || actLinks == 1).isTrue();
//        assertThat(homePageNew.getBreadCrumbs().get(3).getText()).isEqualToIgnoringWhitespace(product.getData().getName());
//    }


	@Then("^all the plp items should have correct product details$")
	public void allThePlpItemsShouldHaveCorrectProductDetails() throws Throwable
	{
		List<WebElement> items = plpNewPage.getPlpItems();
		items.forEach(a -> {
			log.info("checking plp item: {}", a.findElement(plpNewPage.getProductName()).getText());
			assertThat(plpNewPage.getProductName(a)).isNotEmpty();
			assertThat(plpNewPage.getProductPrice(a)).isNotEmpty();
			assertThat(plpNewPage.isSavedItemIconPresent(a)).isTrue();
			if (!IS_MOBILE && !Features.QUICK_VIEW_DESKTOP_HIDDEN)
			{
				assertThat(plpNewPage.getQuickView(a)).isNotEmpty();
			}
		});
	}

	@Then("^plp page should show below departments$")
	public void plpPageShouldShowBelowDepartments(DataTable dataTable) throws Throwable
	{
		List<String> actDepts;
		List<String> expDepts = dataTable.asList(String.class);

		if (IS_MOBILE)
		{
			actDepts = plpPageOld.getDepartFacetNamesMobile();
		}
		else
		{
			actDepts = plpNewPage.getSearchDepartments();
		}
		expDepts.forEach(a -> {
			assertThat(actDepts).contains(a);
		});
	}

	@Then("^sort 'Price Low - High' should show products in sorted order$")
	public void selectPriceSortAsc() throws Throwable
	{
		plpNewPage.selectFacetButton("SORT BY");
		plpNewPage.selectSortOption("Price Low - High");
		plpNewPage.waitForFocus();
		List<Double> priceList = plpNewPage.getPlpItems().stream().map(a -> Double.valueOf(plpNewPage.getProductPrice(a).substring(1))).collect(Collectors.toList());
		assertThat(priceList).isSorted();
	}

	@Then("^sort 'Price High - Low' should show products in sorted order$")
	public void selectPriceSort() throws Throwable
	{
		plpNewPage.selectFacetButton("SORT BY");
		plpNewPage.selectSortOption("Price High - Low");
		//plpNewPage.waitForFocus();
		List<Double> priceList = plpNewPage.getPlpItems().stream().map(a -> Double.valueOf(plpNewPage.getProductPrice(a).substring(1))).collect(Collectors.toList());
		assertThat(priceList).isSortedAccordingTo(Comparator.reverseOrder());
	}

	@When("^clear filters should show full list of items$")
	public void clearFiltersShouldShowFullListOfItems() throws Throwable
	{
		plpNewPage.clickWhenClickable(pdpNewPage.getLink("Clear filters"), 5);
		plpNewPage.waitForFocus();
		log.info("waiting for plp items: {}", countBeforeFilter);
		plpNewPage.waitFroPlpItemsLoaded(countBeforeFilter, 10);
	}

	@When("^typing '(.*)' in the search box$")
	public void iStartTypingForTheProductInTheSearchBox(String text) throws Throwable
	{
		homePage.enterTextInSearchBox(text);
	}

	@Then("^should see suggestions related to (.*) and category (.*)$")
	public void shouldSeeSuggestionRelatedToKeywordAndACategoryCategory(String wordSuggest, String categorySuggest) throws Throwable
	{
		if (categorySuggest.isEmpty())
		{
			try
			{
				plpNewPage.getSearchSuggestions();
				Assert.fail();
			}
			catch (Exception e)
			{
				log.info("test passed");
			}
		}
		else
		{
			plpNewPage.getSearchSuggestions().forEach(a -> assertThat(a).containsIgnoringCase(wordSuggest));
			assertThat(plpNewPage.getSearchSuggestions().stream().filter(a -> a.equalsIgnoreCase(categorySuggest)).collect(Collectors.toList()).size()).isGreaterThanOrEqualTo(1);
		}
	}

	@When("^should see product suggestions realted to (.*)$")
	public void iStartTypingForInTheSearchBox(String suggest) throws Throwable
	{
		plpNewPage.getSearchProductSuggestions().forEach(a -> assertThat(a).containsIgnoringCase(suggest));

	}


	@When("^search string '(.*)' in the search box should show zero results$")
	public void searchStringSearchTermInTheSearchBoxShouldShowZeroResults(String text) throws Throwable
	{
		plpNewPage.search(text);
		try
		{
			plpNewPage.getSearchSuggestions();
			Assert.fail();
		}
		catch (Exception e)
		{
			log.info("test passed");
		}
	}

	@When("^user select sort option '(.*)'$")
	public void userSelectSortOptionPriceLowHigh(String sortOption) throws Throwable
	{
		if (IS_MOBILE)
		{
			plpNewPage.clickSortByButtonOnMobile();
		}
		else
		{
			plpNewPage.selectFacetButton("SORT BY");
		}
		plpNewPage.selectSortOption(sortOption);
	}

	@When("^user select sort option \"([^\"]*)\" on mobile$")
	public void userSelectSortOptionOnMobile(String sortOption)
	{
		plpNewPage.clickSortByButtonOnMobile();
		plpNewPage.selectSortOption(sortOption);
	}

	@Then("^the products should be displayed in price ascending order$")
	public void theProductsShouldBeDisplayedInPriceAscendingOrder() throws Throwable
	{
		plpNewPage.waitForFocus();
		List<Double> priceList = plpNewPage.getPlpItems().stream().map(a -> Double.valueOf(plpNewPage.getProductPrice(a).substring(1))).collect(Collectors.toList());
		assertThat(priceList).isSorted();
	}


	@Then("^the products should be displayed in price descending order$")
	public void theProductsShouldBeDisplayedInPriceDescendingOrder() throws Throwable
	{
		plpNewPage.waitForFocus();
		List<Double> priceList = plpNewPage.getPlpItems().stream().map(a -> Double.valueOf(plpNewPage.getProductPrice(a).substring(1))).collect(Collectors.toList());
		assertThat(priceList).isSortedAccordingTo(Comparator.reverseOrder());
	}

	@And("^select option from filter$")
	public void selectOptionFromCATEGORYFilter(DataTable index) throws Throwable
	{
		if (IS_MOBILE)
		{
			this.totalImages = plpPageOld.currentProductCountFromMobileFilter();
			plpNewPage.clickFilterButtonOnMobile();
		}
		List<Integer> size = new ArrayList<>();
		for (int i = 0; i < index.cells().size(); i++)
		{
			size.add(Integer.parseInt(index.cells().get(i).toString().replace("[", "").replace("]", "")));
			if (PLATFORM.contains("macOS 10.14"))
			{
				plpNewPage.selectFacetButton("Si");
			}
			else
			{
				plpNewPage.selectFacetButton("SIZE");
			}
			Map<String, String> filter = plpNewPage.selectAndGetNoOfItemsInFilter(size.get(i));
			tmp_facetsSeleted.add(filter.get("filter"));
			plpNewPage.setTheSizeOfFilter(tmpData_facetFilterCount);
			plpNewPage.waitForFocus();
			plpNewPage.closeFilter();
		}
	}

	@Then("^page should show right no of filtered results$")
	public void pageShouldShowRightNoOfFilteredResults()
	{
		log.info("waiting for plp items: {}", tmpData_facetFilterCount);
		plpNewPage.waitFroPlpItemsLoaded(tmpData_facetFilterCount, 5);
	}


	@Then("^plp page should show correct filtered attributes$")
	public void plpPageShouldShowCorrectFilteredAttributes()
	{
		if (PLATFORM.contains("macOS 10.14"))
		{
			plpNewPage.getFilteredAttributes().forEach(a -> assertThat(tmp_facetsSeleted).contains(a.trim()));
		}
		else
		{
			tmp_facetsSeleted.forEach(a -> assertThat(plpNewPage.getFilteredAttributes()).contains(a));
		}
	}


	@Then("^I am able to remove all applied facets from the product set using clear link displayed alongside these$")
	public void I_am_able_to_remove_all_applied_facets_from_the_product_set_using_clear_link_displayed_alongside_these()
	{
		plpNewPage.pause(1000);
		if (IS_MOBILE)
		{
			plpNewPage.clickFilterButtonOnMobile();
		}
		plpNewPage.clickClearFiltersLink();
	}


	@And("^there are other values selected$")
	public void there_are_other_values_selected()
	{
		assertTrue(plpNewPage.totalSelectedVaules() == 1);
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@Then("^the value menu is closed automatically$")
	public void the_value_menu_is_closed_automatically() throws Throwable
	{

		plpNewPage.pause(5000);
		log.info(plpPageOld.facetOpen());
		assertFalse(plpPageOld.facetOpen());
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@When("^I select a single-select facet sortby$")
	public void I_select_a_single_select_facet_sortby()
	{
		this.totalImages = plpPageOld.returnTotalProductsAvailable();
		if (IS_MOBILE)
		{
			plpNewPage.clickSortByButtonOnMobile();
		}
		else
		{
			plpPageOld.clickOnFacadeSortBy();
		}
	}


	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@Then("^I am unable to expose another facet at the same time$")
	public void I_am_unable_to_expose_another_facet_at_the_same_time()
	{
		assertTrue(plpPageOld.getExposedFacetsValue() == 1);
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@And("^if I select another facet to expose$")
	public void if_I_select_another_facet_to_expose()
	{
		plpPageOld.clickOnFacadeCategory();
	}


	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@Then("^any already-exposed facets will close$")
	public void any_already_exposed_facets_will_close() throws Throwable
	{

		assertTrue(plpPageOld.getExposedFacetsValue() == 1);
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@When("^I select a single-select facet Size$")
	public void I_select_a_single_select_facet_Size() throws InterruptedException
	{
		if (IS_MOBILE)
		{
			plpNewPage.clickFilterButtonOnMobile();
			plpNewPage.selectFacetButton("SIZE");

		}
		else
		{
			plpPageOld.clickOnFacadeSize();
			plpNewPage.clickViewAllIfVisible();
		}
		this.totalImages = plpPageOld.returnTotalProductsAvailable();
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@And("^select a size from the displayed facet values$")
	public void select_a_size_from_the_displayed_facet_values() throws Throwable
	{
		plpPageOld.selectSize().click();
		this.totalImagesAfterApplyingFilter = plpPageOld.returnTotalProductsAvailable();
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@When("^I review the facet bar/element and the facets I have currently applied to the product set$")
	public void I_review_the_facet_bar_element_and_the_facets_I_have_currently_applied_to_the_product_set() throws Throwable
	{
		if (IS_MOBILE)
		{
			plpNewPage.clickApplyButtonOnMobile();
			this.totalImagesAfterApplyingFilter = plpPageOld.currentProductCountFromMobileFilter();
		}
		else
		{
			plpNewPage.clickViewAllIfVisible();
			this.totalImagesAfterApplyingFilter = plpPageOld.returnTotalProductsAvailable();
		}

		assertTrue(this.totalImagesAfterApplyingFilter < this.totalImages);
	}


	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@And("^the product set returns to an un-refined state and all products in the listing are displayed$")
	public void the_product_set_returns_to_an_un_refined_state_and_all_products_in_the_listing_are_displayed() throws InterruptedException
	{
		if (IS_MOBILE)
		{
			assertTrue(this.totalImages == plpPageOld.currentProductCountFromMobileFilter());
		}
		else
		{
			plpNewPage.clickViewAllIfVisible();
			assertTrue(this.totalImages == plpPageOld.returnTotalProductsAvailable());
		}
	}


	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@When("^I select a single-select facet colour$")
	public void I_select_a_single_select_facet_colour() throws Throwable
	{
		plpNewPage.pause(5000);
		if (IS_MOBILE)
		{
			plpNewPage.clickFilterButtonOnMobile();
		}
		plpNewPage.selectFacetButton("COLOUR");
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@And("^have selected multiple values in that facet$")
	public void have_selected_multiple_values_in_that_facet()
	{
		plpNewPage.selectColoursFromOpenFacet("Blue");
		plpNewPage.selectColoursFromOpenFacet("White");
		if(IS_MOBILE){
			plpNewPage.clickApplyButtonOnMobile();
		}
		this.totalImages = plpPageOld.returnTotalProductsAvailable();
		log.info(this.totalImages);

	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@When("^I re-click the value$")
	public void I_re_click_the_value() throws Throwable
	{
		plpNewPage.selectColoursFromOpenFacet("Blue");
		if(IS_MOBILE){
			plpNewPage.clickApplyButtonOnMobile();
		}
		this.totalImagesAfterApplyingFilter = plpPageOld.returnTotalProductsAvailable();
		log.info(this.totalImagesAfterApplyingFilter);
	}


	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@And("^the product set narrows to products that match the remaining selected values$")
	public void the_product_set_narrows_to_products_that_match_the_remaining_selected_values() throws Throwable
	{
		assertTrue(this.totalImagesAfterApplyingFilter < this.totalImages);
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@And("^the model (on|off) button is enabled for the category by default$")
	public void the_Model_On_Off_button_is_enabled_for_the_category(String catogeryButton)
	{
		switch (catogeryButton.toUpperCase())
		{
			case "ON":
				assertTrue(plpNewPage.getModelOnCategory());
				plpNewPage.clickModelOffButton(catogeryButton);
				assertTrue(plpNewPage.getModelOffCategory());
				break;
			case "OFF":
				assertTrue(plpNewPage.getModelOffCategory());
				plpNewPage.clickModelOffButton(catogeryButton);
				assertTrue(plpNewPage.getModelOnCategory());
		}
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@And("^the category has been set to display Model off images by default$")
	public void the_category_has_been_set_to_display_Model_off_images_by_default()
	{
		assertTrue(plpNewPage.getModelOffCategory());
	}


	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@And("^click on model \"([^\"]*)\"$")
	public void click_on_model(String model)
	{
		plpPageOld.selectModel(model);
	}


	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@And("^click on load next button$")
	public void click_on_load_next_button()
	{
		plpPageOld.pause(3000);
		plpPageOld.scrollToNextButton();
		try
		{
			plpPageOld.loadNextButton().click();
		}
		catch (Exception e)
		{
			plpPageOld.loadNextButton().click();
		}
		this.totalImagesAfterApplyingFilter = plpPageOld.returnTotalProductsAvailable();
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@Then("^next products will be displayed$")
	public void next_products_will_be_displayed()
	{
		assertTrue(this.totalImages <= this.totalImagesAfterApplyingFilter);
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@And("^click on load all button$")
	public void click_on_load_all_button() throws Throwable
	{
		this.totalImages = plpNewPage.getTotalNumberOfProductsAvailable();
		plpNewPage.clickViewAllIfVisible();
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@And("^the total number of results is less than the maximum allowed for View All$")
	public void the_total_number_of_results_is_less_than_the_maximum_allowed_for_View_All() throws InterruptedException
	{
		this.totalImages = plpPageOld.returnTotalProductsAvailable();
		assertTrue(plpPageOld.totalProductCount() > this.totalImages);
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@Then("^all the products should be displayed$")
	public void all_the_products_should_be_displayed() throws InterruptedException
	{
		assertTrue(plpPageOld.totalProductCount() == plpPageOld.returnTotalProductsAvailable());
	}


	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@Then("^I am presented with a list of options on which to sort the product set in view$")
	public void I_am_presented_with_a_list_of_options_on_which_to_sort_the_product_set_in_view() throws Throwable
	{
		List<String> facetNames;
		facetNames = plpPageOld.getSortByFacetVaules();
		String[] names = {"Best Match", "Price Low - High", "Price High - Low", "Newest", "Bestsellers"};

		for (int i = 0; i < facetNames.size(); i++)
		{
			assertTrue(facetNames.get(i).contains(names[i]));
		}
	}


	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@Then("^I am presented with the product listing by default$")
	public void I_am_presented_with_the_product_listing_by_default()
	{
		Assert.assertTrue(plpNewPage.getTotalNumberOfProductsAvailable() != 0);
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@And("^each facet option has a bin count$")
	public void each_facet_option_has_a_bin_count()
	{
		this.departmentFacetBinValue = plpPageOld.getDepartmentFacetBinValues();
	}

	//todo remove plpPageOld migrate any methods used from plpPageOld to new plp page
	@And("^I can select a facet to refine the product listing to exclude the other departments$")
	public void I_can_select_a_facet_to_refine_the_product_listing_to_exclude_the_other_departments() throws InterruptedException
	{
		plpPageOld.toggleBetweenDepartFacets();
	}

	@Then("^selected facet filters should match with filters displayed$")
	public void numberOfFiltersShouldIncrease()
	{
		Assert.assertEquals(tmp_facetsSeleted, plpNewPage.getSelectedFilters());
	}

	@Then("^all products will be displayed$")
	public void allProductsWillBeDisplayed()
	{
		int totalProductsAfterClickingViewAllButton = plpNewPage.getTotalNumberOfProductsAvailable();
		log.info("The total number of products before clicking viewall button--" + totalImages + " /n The total number of products after clicking viewall button--" + totalProductsAfterClickingViewAllButton);
		Assert.assertNotEquals(totalImages, totalProductsAfterClickingViewAllButton);
	}

	@Then("^the model on off button is enabled for the category by default$")
	public void theModelOnOffButtonIsEnabledForTheCategoryByDefault() throws Throwable
	{
		Assert.assertTrue(plpNewPage.getModelOffCategory());
	}

	@And("^it should show min (\\d+) matched results$")
	public void itShouldShowMinMatchedResults(int results)
	{
		assertThat(plpNewPage.getNoOfProductsMatchSearchOnMobile()).isGreaterThan(results);
	}

	@And("^user clicks on filter on mobile$")
	public void userClicksOnFilterOnMobile()
	{
		plpNewPage.clickFilterButtonOnMobile();
	}

	@And("^user clicks on apply filter on mobile$")
	public void userClickOnApplyFilterOnMobile()
	{
		plpNewPage.clickApplyButtonOnMobile();
	}

	@Then("^sale icon appears in plp grid$")
	public void saleIconAppearsInPlpGrid()
	{
		Assert.assertTrue(plpNewPage.isSaleIconPresent());
	}

	@And("^there are more than (\\d+) sale icons$")
	public void thereAreMoreThanSaleIcons(int saleIcons)
	{
		Assert.assertTrue(plpNewPage.getNoOfSaleIcons() > saleIcons);
	}

	@Then("^similiar items button is displayed$")
	public void similiarItemsButtonIsDisplayed()
	{
		Assert.assertTrue(wishListPage.getSimiliarItemsButtonIsDisplayed());
	}

	@And("^I scroll down$")
	public void iScrollDown()
	{
		plpNewPage.pause(3000);
		plpNewPage.scrollByCoordinates(800);
	}

	@Then("^I should not see the filter$")
	public void iShouldNotSeeTheFilter()
	{
		Assert.assertFalse(plpNewPage.isFilterDisplayed());
	}

	@Then("^I should see the filter$")
	public void iShouldSeeTheFilter()
	{
		Assert.assertTrue(plpNewPage.isFilterDisplayed());
	}

	@And("^I scroll up$")
	public void iScrollUp()
	{
		plpNewPage.scrollByCoordinates(-200);
	}

	@And("^url contains \"([^\"]*)\"$")
	public void urlContains(String urlText)
	{
		assertTrue(plpNewPage.getWebDriver().getCurrentUrl().contains(urlText));
	}

	@And("^url does not contain \"([^\"]*)\"$")
	public void urlDoesNotContain(String urlText)
	{
		assertFalse(plpNewPage.getWebDriver().getCurrentUrl().contains(urlText));
	}

	@And("^the searched plp title headline equals \"([^\"]*)\"")
	public void theSearchedPlpTitleEquals(String text)
	{
		Assert.assertTrue(plpNewPage.getPlpTitle().equals(text));
	}

	@Then("^the searched plp headline equals (.*)")
	public void theTitleOfTheSearchEquals(String text)
	{
		Assert.assertTrue(plpNewPage.getSearchedHeaderText().equals(text));
	}

	@And("^I click on back to top button$")
	public void iClickOnBackToTopButton()
	{
		plpNewPage.clickBackToTopButton();
	}

	@Then("^I am taken to the top of PLP page$")
	public void iAmTakenToTheTopOfPLPPage()
	{
		Assert.assertTrue(plpNewPage.isHeaderDisplayed());
	}

	@And("^Select filter all products from search filter list$")
	public void selectFilterAllProductsFromSearchFilterList()
	{
		plpNewPage.clickAllProductsFilter();
	}

	@Then("^articles and info has been removed from search filter$")
	public void articlesHasBeenRemovedFromSearchFilter()
	{
		if (IS_MOBILE)
		{
			Assert.assertFalse(plpPageOld.getDepartFacetNamesMobile().contains("ARTICLE"));
		}
		else
		{
			Assert.assertFalse(plpPageOld.getDepartFacetnames().contains("ARTICLE"));
		}
	}

	@Then("^I am able to filter and clear filter on mobile$")
	public void iAmAbleToFilterAndClearFilterOnMobile() throws Throwable
	{
		Assert.assertTrue(plpNewPage.addFilterAndClearFilterOnMobile());
	}
}
