package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.stream.Collectors;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static com.salmon.test.page_objects.gui.PlpPageOld.MOBILE_DEPARTMENTS_DROPDOWN_VALUES;


import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

/**
 * Created by phani.kaliginadi on 26/10/17.
 */

@Data
public class PlpPage extends PageObject
{
	private static final By SELECTED_FILTERS = By.cssSelector(".link.link--nounderline.ng-binding");
	private static final By FACET_COLOURS = By.cssSelector(".facet-options__list-item-value--colour");
	private static final By SELECTED_FILTERS_IN_FACET = By.cssSelector(
			"li[class='facet-options__list-item ng-scope selected']");
	private static final By FACET_SELECTED = By.className("selected");
	private static final By FACET_FILTERS_WRAPPER = By.className("facet-filters-wrapper");
	private static final By FACET_SORT_WRAPPER = By.className("facet-sort-wrapper");
	private static final By FACET = By.className("facet");
	private static final By CLOSE_FILTERS_BUTTON = By.className("facets-mobile-close");
	private static final By ITEM_COUNT = By.className("facets--total-results");
	private static final By MODEL_ON_ENABLED = By.cssSelector(".on.selected");
	private static final By MODEL_OFF_ENABLED = By.cssSelector(".off.selected");
	private static final By MODEL_ON_OFF_BUTTON = By.cssSelector(".toggle-model > span.off");
	private static final By BREADCRUMB__LIST = By.className("breadcrumb__list");
	private static final By BREADCRUMB__LINK = By.className("breadcrumb__link");
	private Logger log = LogManager.getLogger(PlpPage.class.getName());
	private static final By PRODUCT_LIST = By.cssSelector(".product-item__details > a");
	private static final By LIST_OF_PRODUCTS_DISPLAYED = By.cssSelector(".product-item__details > a");
	private final By homeLink = By.xpath("//*[@id='skiptonavigation']/nav/ol/li[2]/div/div/div[1]/p/a");
	private final By clearFiltersLink = By.cssSelector("span[data-translate='search.page.clearFilters']");
	private static final By productLinkName = By.xpath(
			".//*[@id='body']/main/div[5]/div/div[2]/div[1]/div/span/div[1]/div/ul/li[2]/a/img");
	By productName = By.cssSelector(".product-item__details>a");
	By productPrice = By.cssSelector(".product-item__price>span");
	By savedItemIcon = By.cssSelector(".product-item__saveditem>i");
	By ratingsicon = By.cssSelector(".bv-rating-stars.bv-rating-stars-off");
	By RATINGICON_PDP = By.cssSelector(".bv-rating.bv-rating-stars.bv-rating-stars-off");
	By plpItem = By.className("plp-item");
	By searchDept = By.cssSelector(".search-filter__list>li>a>.search-filter__title");
	By facetOption = By.cssSelector(".facet-options li>a>span");
	By facetButton = By.cssSelector(".facet__label-wrapper>button");
	By filterOption = By.cssSelector(".facet--open .facet-options__list-item");
	By filerOptionLabel = By.cssSelector(".facet-options__list-item-value");
	By filerOptionCount = By.className("facet__count");
	By openFilter = By.cssSelector(".facet--open button");
	By searchBox = By.cssSelector(".search-area input");
	By searchSuggestions = By.cssSelector(".word-suggestions__word");
	By searchProductSuggestions = By.cssSelector(".product-suggestions__product-text");
	By filteredAttributes = By.cssSelector(".applied-facets__item");
	private By facetViewAllResults = By.cssSelector("div.facets-info > .facets__loadall-link > span");
	private int filterSize;
	private By quickViewButton = By.cssSelector(".product-item__price > a");
	private By removeBagItem = By.cssSelector("a[id*=removeEntry]");
	private By SLIDER_LINE = By.cssSelector(".ui-slider-range.ui-corner-all.ui-widget-header");
	private By SLIDER_COMPONENT_CONTAINER = By.cssSelector(".facet-options.ng-scope.facet-options--show");
	private By SLIDER_LEFT_PRICE_VALUE = By.cssSelector(".slider-value__range-start.ng-binding");
	private By SLIDER_RIGHT_PRICE_VALUE = By.cssSelector(".slider-value__range-end.ng-binding");
	private By SLIDER_LEFT_ICON = By.cssSelector(
			"span[class='ui-slider-handle ui-corner-all ui-state-default'][style='left: 0%;']");
	private By SLIDER_RIGHT_ICON = By.cssSelector(
			"span[class='ui-slider-handle ui-corner-all ui-state-default'][style='left: 100%;']");
	private By SEARCHED_HEADER = By.cssSelector(".search__headline");
	private By TOTAL_PRODUCT_RESULTS = By.cssSelector(".search-filter__count.ng-binding");
	private By SORTING_LABEL = By.cssSelector(".facet__label");
	private By ALL_PRODUCTS_RESULT_COUNT_MOBILE = By.cssSelector("facets-mobile-toggle__selected ng-scope");
	private By SORT_BY_MOBILE = By.cssSelector(".facets-mobile-toggle__selected.ng-binding");
	private By FILTER_MOBILE = By.className("facets-mobile-toggle");
	private By APPLY_BUTTON_MOBILE = By.cssSelector(
			".applied-facets__button.applied-facets__button--apply.nl__apply-filter.ng-scope");
	private By SALE_ICON = By.cssSelector(".product-item__promo-image");
	private By FILTER_MOBILE_STICKY = By.cssSelector(".facets-wrapper.facets--not-sticky");
	private By PLP_TITLE = By.cssSelector(".plp-title");
	private By BACK_TO_TOP_BUTTON = By.cssSelector(".back-to-top__button");
	private By ALL_PRODUCTS_FILTER = By.cssSelector("ul.search-filter__list li:nth-child(1)");
	private By FILTER_OPTIONS_MOBILE = By.cssSelector(".facet-options__list-item.ng-scope");
	private By FILTER_OPTIONS_COUNT = By.cssSelector(".facet__count.ng-binding.ng-scope");
	PlpPageOld plpPageOld = new PlpPageOld();

	public void clickSortingCriteria(String sorting_criteria)
	{
		if (IS_MOBILE)
		{
			clickFilterButtonOnMobile();
		}
		List<WebElement> sortHeaders = webDriver.findElements(SORTING_LABEL);
		for (WebElement wb : sortHeaders)
		{
			if (wb.getText().equals(sorting_criteria))
			{
				wb.click();
				pause(1000);
			}
		}
	}

	public WebElement getSortingLabel()
	{
		return webDriver.findElement(SORTING_LABEL);
	}

	public WebElement getProductResults()
	{

		return webDriver.findElements(TOTAL_PRODUCT_RESULTS).get(0);
	}

	public WebElement getsliderLine()
	{
		return webDriver.findElement(SLIDER_LINE);
	}

	public WebElement getSliderLeftIcon()
	{
		return webDriver.findElement(SLIDER_LEFT_ICON);
	}

	public WebElement getSliderRightIcon()
	{
		return webDriver.findElement(SLIDER_RIGHT_ICON);
	}

	public WebElement getSearchedHeaderElelment()
	{
		return webDriver.findElement(SEARCHED_HEADER);
	}

	public String getSearchedHeaderText()
	{
		return waitForAndGetElement(SEARCHED_HEADER, DEFAULT_TIMEOUT).getText();
	}

	public String getPlpTitle()
	{
		return waitForAndGetElement(PLP_TITLE, DEFAULT_TIMEOUT).getText();
	}

	public List<WebElement> getPlpItems()
	{
		waitForExpectedElement(plpItem);
		return webDriver.findElements(plpItem);
	}

	public List<WebElement> getRemoveBagItemList()
	{
		return webDriver.findElements(removeBagItem);
	}

	public WebElement productLinkName()
	{
		return waitForExpectedElement(productLinkName);
	}

	public String getProductName(WebElement item)
	{
		return item.findElement(productName).getText();

	}

	public WebElement deptHomeLink()
	{
		return waitForElementPresence(homeLink);
	}

	public String getProductPrice(WebElement item)
	{
		return item.findElement(productPrice).getText();

	}

	public boolean isSavedItemIconPresent(WebElement item)
	{
		return isElementPresent(item.findElement(savedItemIcon), 1);

	}

	public void clickRatingPlp(WebElement item)
	{
		scrollForFocus(productName, 20);
		pause(3000);
		clickByJavaScriptExecutor(item.findElement(ratingsicon));
	}

	public List<String> getSearchDepartments()
	{
		if (IS_MOBILE)
		{
			return getWebDriver().findElements(MOBILE_DEPARTMENTS_DROPDOWN_VALUES)
								 .stream()
								 .map(WebElement::getText)
								 .collect(Collectors.toList());
		}
		else
		{
			return getWebDriver().findElements(searchDept)
								 .stream()
								 .map(WebElement::getText)
								 .collect(Collectors.toList());
		}
	}

	public void selectFacetButton(String label)
	{
		pause(1000);
		clickByJavaScriptExecutor(getWebDriver().findElements(facetButton)
												.stream()
												.filter(a -> a.getText().contains(label))
												.findFirst()
												.get());
	}


	public void selectSortOption(String sort)
	{
		waitForListLoaded(getWebDriver().findElements(facetOption), 3, 3);
		getWebDriver().findElements(facetOption)
					  .stream()
					  .filter(a -> a.getText().contains(sort))
					  .findFirst()
					  .get()
					  .click();

	}

	public Map<String, String> selectAndGetNoOfItemsInFilter(int index)
	{
		Map<String, String> filter = new HashMap<>();
		String countStr = getWebDriver().findElements(filterOption).get(index).findElement(filerOptionCount).getText();
		String name = getWebDriver().findElements(filterOption).get(index).findElement(filerOptionLabel).getText();
		getWebDriver().findElements(filterOption).get(index).findElement(filerOptionLabel).click();
		log.info("count {}", countStr);
		filter.put("filter", name);
		filter.put("count", countStr.substring(1, countStr.length() - 1));
		return filter;

	}

	public void waitFroPlpItemsLoaded(int no, int timeout)
	{
		waitForListLoaded(getWebDriver().findElements(plpItem), no, timeout);
	}

	public void closeFilter()
	{
		clickByJavaScriptExecutor(getWebDriver().findElement(openFilter));
	}


	public void clickViewAllIfVisible()
	{
		try
		{
//            clickLink("View all");
			clickByJavaScriptExecutor(getWebDriver().findElements(facetViewAllResults)
													.stream()
													.filter(a -> a.getText().contains("View all"))
													.findFirst()
													.get());
		}
		catch (Exception e)
		{
			log.info("No view all button");
		}

	}

	public void waitForFocus()
	{
		pause(1500);
	}

	public void search(String text)
	{
		getWebDriver().findElement(searchBox).sendKeys(text);
		// searchFindBy.sendKeys(text);
	}

	public List<String> getSearchSuggestions()
	{
		new WebDriverWait(getWebDriver(),
						  2).until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchSuggestions));
		return getWebDriver().findElements(searchSuggestions)
							 .stream()
							 .map(WebElement::getText)
							 .collect(Collectors.toList());
	}

	public List<String> getSearchProductSuggestions()
	{
		new WebDriverWait(getWebDriver(), 2).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				searchProductSuggestions));
		return getWebDriver().findElements(searchProductSuggestions)
							 .stream()
							 .map(WebElement::getText)
							 .collect(Collectors.toList());
	}

	public List<String> getFilteredAttributes()
	{
		pause(1000);
		return getWebDriver().findElements(filteredAttributes)
							 .stream()
							 .map(WebElement::getText)
							 .collect(Collectors.toList());
	}

	public void saveItemFromList()
	{
		pause(3000);
		waitForElementVisible(savedItemIcon, DEFAULT_TIMEOUT);
		webDriver.findElement(savedItemIcon).click();
	}

	public void saveItemFromList(int numberOfItemsToSelect)
	{
		for (int i = 0; i < numberOfItemsToSelect; i++)
		{
			waitForAndGetElement(webDriver, savedItemIcon, DEFAULT_TIMEOUT);
			clickByJavaScriptExecutor(webDriver.findElements(savedItemIcon).get(i));
			pause(1000);

		}
	}

	public void clickFirstAvailableProduct()
	{
		pause(1500);
//        clickWhenClickable(webDriver.findElements(LIST_OF_PRODUCTS_DISPLAYED).get(0), 10);
		clickByJavaScriptExecutor(webDriver.findElements(LIST_OF_PRODUCTS_DISPLAYED).get(0));
	}

	public void setTheSizeOfFilter(int filterSize)
	{
		this.filterSize = filterSize;
	}

	public List<String> getSelectedFilters()
	{
		List<String> selectedSizes = new ArrayList<>();
		waitForAndGetElement(SELECTED_FILTERS, DEFAULT_TIMEOUT);
		List<WebElement> selectedSize = getWebDriver().findElements(SELECTED_FILTERS);
		for (int i = 0; i < selectedSize.size(); i++)
		{
			selectedSizes.add(selectedSize.get(i).getText());
		}
		return selectedSizes;
	}

	public void clickClearFiltersLink()
	{
		if (!getWebDriver().findElements(clearFiltersLink).isEmpty())
		{
			getWebDriver().findElement(clearFiltersLink).click();
		}
		log.error("Clear filters link is not displayed");
	}

	public void selectColoursFromOpenFacet(String colour)
	{
		pause(2000);
		if (!getWebDriver().findElements(FACET_COLOURS).isEmpty())
		{
			clickWhenClickable(getWebDriver().findElements(FACET_COLOURS)
											 .stream()
											 .filter(a -> a.getText().contains(colour))
											 .findFirst()
											 .get(), 10);
		}
		else
		{
			log.error("Colours are not displayed");
		}
	}

	public int getTotalNumberOfProductsAvailable()
	{
		pause(5000);
		List<WebElement> total = getWebDriver().findElements(PRODUCT_LIST);
		return total.size();
	}

	public int totalSelectedVaules()
	{
		List<WebElement> selectedValues = getWebDriver().findElements(SELECTED_FILTERS_IN_FACET);

		return selectedValues.size();
	}

	public boolean getModelOnCategory()
	{
		waitForElementVisible(MODEL_ON_ENABLED, 10);
		if (!getWebDriver().findElements(MODEL_ON_ENABLED).isEmpty())
		{
			return true;
		}
		return false;
	}

	public boolean getModelOffCategory()
	{
		waitForElementVisible(MODEL_OFF_ENABLED, 10);
		if (!getWebDriver().findElements(MODEL_OFF_ENABLED).isEmpty())
		{
			return true;
		}
		return false;
	}

	public void clickModelOffButton(String onOffButton)
	{
		pause(2000);
		waitForElementVisible(MODEL_ON_OFF_BUTTON, 10);
		if (!getWebDriver().findElements(MODEL_ON_OFF_BUTTON).isEmpty())
		{
			getWebDriver().findElement(By.cssSelector("." + onOffButton.toLowerCase())).click();

		}
	}

	public String getQuickView(WebElement item)
	{
		return item.findElement(quickViewButton).getText();
	}

	public void removeBagItemList()
	{
		int count = getRemoveBagItemList().size();
		for (int i = 0; i < getRemoveBagItemList().size(); i++)
		{
			if (count == 0)
			{
				break;
			}
			staleElementHandle(getRemoveBagItemList().get(i));
			count--;

		}
	}


	public boolean performDragDrop(WebElement sideIcon, int xOffset, int yOffset)
	{

		String originalLeftValue = sideIcon.getText();
		boolean match = true;
		Actions action = new Actions(webDriver);
		action.clickAndHold(sideIcon).moveByOffset(xOffset, yOffset).release().perform();
		String newValue = sideIcon.getText();
		waitForElementVisible(SELECTED_FILTERS, 40);
		String filterValue = webDriver.findElement(SELECTED_FILTERS).getText();
		//this will check if the new value is part of the filter criteria
		if (!filterValue.contains(newValue))
		{
			match = false;
			return match;
		}

		return match;
	}


	public boolean performDragDropLeft()
	{

		String originalLeftValue = webDriver.findElement(SLIDER_LEFT_PRICE_VALUE).getText();
		boolean match = true;
		Actions action = new Actions(webDriver);
		action.clickAndHold(getSliderLeftIcon()).moveByOffset(36, 3).release().perform();
		String newValue = webDriver.findElement(SLIDER_LEFT_PRICE_VALUE).getText();
		waitForElementVisible(SELECTED_FILTERS, 40);
		String filterValue = webDriver.findElement(SELECTED_FILTERS).getText();
		//this will check if the new value is part of the filter criteria
		if (!filterValue.contains(newValue))
		{
			match = false;
			return match;
		}

		return match;
	}

	public boolean performDragDropRight()
	{

		String originalLeftValue = webDriver.findElement(SLIDER_RIGHT_PRICE_VALUE).getText();
		boolean match = true;
		Actions action = new Actions(webDriver);
		action.clickAndHold(getSliderRightIcon()).moveByOffset(-30, 3).release().perform();
		String newValue = webDriver.findElement(SLIDER_RIGHT_PRICE_VALUE).getText();
		waitForElementVisible(SELECTED_FILTERS, 40);
		String filterValue = webDriver.findElement(SELECTED_FILTERS).getText();
		//this will check if the new value is part of the filter criteria
		if (!filterValue.contains(newValue))
		{
			match = false;
			return match;
		}

		return match;
	}


	public boolean checkProductResultChange()
	{
		int beforeProductResults;
		if (IS_MOBILE)
		{
			beforeProductResults = plpPageOld.currentProductCountFromMobileFilter();
			clickSortingCriteria("PRICE");
		}
		else
		{
			beforeProductResults = plpPageOld.totalProductCount();
		}
		boolean itemResults = false;

		Actions action = new Actions(webDriver);
		action.clickAndHold(getSliderLeftIcon()).moveByOffset(36, 3).release().perform();
		waitForElementVisible(SELECTED_FILTERS, 40);
		action.clickAndHold(getSliderRightIcon()).moveByOffset(-30, 3).release().perform();
		waitForElementVisible(SELECTED_FILTERS, 40);
		if (!IS_MOBILE)
		{
			clickSortingCriteria("PRICE");
		}
		int afterProductResults;
		if (IS_MOBILE)
		{
			clickApplyButtonOnMobile();
			afterProductResults = plpPageOld.currentProductCountFromMobileFilter();
		}
		else
		{
			afterProductResults = plpPageOld.totalProductsFromFilter();
		}


		if (beforeProductResults >= afterProductResults)
		{
			itemResults = true;
			return itemResults;

		}
		return itemResults;
	}

	public void clickPrice()
	{

		List<WebElement> sortHeaders = webDriver.findElements(By.cssSelector(".facet__label"));
		for (WebElement wb : sortHeaders)
		{

			if (wb.getText().equals("PRICE"))
			{
				wb.click();

			}
		}
	}

	public int getNoOfProductsMatchSearchOnMobile()
	{
		waitForAndGetElement(ALL_PRODUCTS_RESULT_COUNT_MOBILE, DEFAULT_TIMEOUT);
		String count = webDriver.findElement(ALL_PRODUCTS_RESULT_COUNT_MOBILE).getText();

		int result = Integer.parseInt(count.replaceAll("[^\\d.]", ""));
		return result;
	}

	public void clickSortByButtonOnMobile()
	{
		pause(1500);
		refresh();
		pause(4000);
		waitForAndGetElement(SORT_BY_MOBILE, DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(waitForAndGetElement(SORT_BY_MOBILE, DEFAULT_TIMEOUT));
	}

	public void clickFilterButtonOnMobile()
	{
		waitForAndGetElement(FILTER_MOBILE, DEFAULT_TIMEOUT);
		clickByJavaScriptExecutor(webDriver.findElements(FILTER_MOBILE).get(1));
	}

	public void clickApplyButtonOnMobile()
	{
		waitForAndGetElement(APPLY_BUTTON_MOBILE, DEFAULT_TIMEOUT).click();
	}

	public boolean isSaleIconPresent()
	{
		return waitForAndGetElement(SALE_ICON, DEFAULT_TIMEOUT).isDisplayed();
	}

	public int getNoOfSaleIcons()
	{
		return webDriver.findElements(SALE_ICON).size();
	}

	public boolean isFilterDisplayed()
	{
		boolean b = false;
		try
		{
			webDriver.findElement(FILTER_MOBILE_STICKY).isEnabled();
		}
		catch (Exception e)
		{
			b = true;
		}
		return b;
	}

	public void clickBackToTopButton()
	{
		waitForAndGetElement(BACK_TO_TOP_BUTTON, DEFAULT_TIMEOUT).click();
	}

	public boolean isHeaderDisplayed()
	{
		pause(1000);
		boolean b = true;
		try
		{
			webDriver.findElement(HomePage.breadCrumbs).click();
		}
		catch (Exception e)
		{
			b = false;
		}
		return b;
	}

	public void clickAllProductsFilter()
	{
		webDriver.findElement(ALL_PRODUCTS_FILTER).click();
	}

	public boolean addFilterAndClearFilterOnMobile()
	{
		int beforeProductResults;
		boolean itemResults = false;
		beforeProductResults = plpPageOld.currentProductCountFromMobileFilter();
		clickFilterButtonOnMobile();
		selectFacetButton("COLOUR");
		selectColoursFromOpenFacet("Blue");
		selectColoursFromOpenFacet("White");
		pause(2000);
		clickClearFiltersLink();

		int afterProductResults = plpPageOld.currentProductCountFromMobileFilter();


		if (beforeProductResults == afterProductResults)
		{
			itemResults = true;
			return itemResults;

		}
		return itemResults;
	}

	public String getTitleTag()
	{
		return getCurrentPageTitle();
	}

	public String getContent(String name)
	{
		By by = By.cssSelector("meta[name='" + name + "']");
		return getAttributeValue(by, "content");
	}

	public int getMobileFilterCount()
	{
		return Integer.parseInt(waitForExpectedElement(FILTER_MOBILE).findElement(By.className("ng-binding"))
																	 .getAttribute("textContent")
																	 .replaceAll("[^0-9]", ""));
	}

	public void removeFilter()
	{
		final WebElement facet = getFacetGroups().stream()
												 .filter(element -> !element.findElements(FACET_SELECTED)
																		.isEmpty())
												 .findFirst()
												 .orElse(null);
		if (Objects.nonNull(facet))
		{
			facet.click();
			facet.findElement(FACET_SELECTED).click();
		}
	}

	private List<WebElement> getFacetGroups()
	{
		return waitForExpectedElement(FACET_FILTERS_WRAPPER).findElements(FACET);
	}

	public WebElement getFirstAncestorBreadcrumb()
	{
		return waitForExpectedElement(BREADCRUMB__LIST).findElements(BREADCRUMB__LINK).get(1);
	}

	public WebElement getFilterButton()
	{
		return waitForExpectedElement(FACET_FILTERS_WRAPPER);
	}

	public WebElement getSortButton()
	{
		return waitForExpectedElement(FACET_SORT_WRAPPER);
	}

	public WebElement getCloseFiltersButton()
	{
		return waitForExpectedElement(CLOSE_FILTERS_BUTTON);
	}

	public WebElement getItemCount()
	{
		return waitForExpectedElement(ITEM_COUNT);
	}
}
