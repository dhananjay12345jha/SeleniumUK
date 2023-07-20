package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static org.junit.Assert.*;


import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

public class PlpPageOld extends PageObject
{
	public static final By MOBILE_DEPARTMENTS_DROPDOWN_VALUES = By.cssSelector(".search-filter__select option");
	private Logger log = LogManager.getLogger(PlpPageOld.class.getName());
	public final By loadAllButton = By.className("plp-pagination__load-all");
	private final By homeLink = By.xpath("//*[@id='skiptonavigation']/nav/ol/li[2]/div/div/div[1]/p/a");
	private final By mensHomeLink = By.xpath("//a[contains(@href,'/uk-mens-department/c/uk-mens')]");
	private final By teensHomeLink = By.xpath("//a[contains(@href,'/uk-teens-department/c/uk-teens')]");
	private final By womensHomeLink = By.xpath("//a[contains(@href,'/uk-womens-department/c/uk-womens')]");
	private final By facetheadings = By.cssSelector(".facet__label");
	private final By sortByRelevance = By.cssSelector("a[id*='price-asc']");
	private final By sortByNameDescending = By.id("price-desc");
	private final By facetOptions = By.cssSelector(".facet-options.facet-options--show");
	private final By noMathcesFoundText = By.cssSelector(".search__headline");
	private final By searchTextSuggestions = By.cssSelector("li[class*='word-suggestions']");
	private final By productText = By.cssSelector("a[class*='product-text']");
	private final By colorCodes = By.cssSelector(".facet-options__list-item-value.ng-binding.facet-options__list-item-value--colour");
	private final By category = By.xpath("//a[contains(@id, 'relevance')]");
	private final By selectSize = By.cssSelector("a[id*='relevance:displaySize:']");
	private final By clearFiltersLink = By.cssSelector("span[data-translate='search.page.clearFilters']");
	private final By modelOnOff = By.cssSelector(".toggle-model");
	private final By defaultImageInPdp = By.cssSelector(".plp-carousel__img");
	private final By modelOffSelected = By.cssSelector(".off.selected");
	private final By contentSearch = By.cssSelector(".word-suggestions__word>a");
	private final By productSearch = By.cssSelector("a[class*='product-suggestions__product-text']");
	private final By departmentFacets = By.cssSelector(".search-filter__item.ng-scope");
	private final By priceCategory = By.xpath("(//button[@class='facet__label ng-binding'])[4]");
	private final By coloursSelected = By.cssSelector("li[class='applied-facets__item ng-scope'] >a");
	public final By productCount = By.cssSelector(".product-count");
	private final By loadNextButton = By.cssSelector(".button.button--secondary.ng-scope.button");
	//colour codes ID's
	private final By greyColour = By.xpath("//span[contains(text(),'Grey')]");
	private final By blueColour = By.xpath("//span[contains(text(),'Blue')]");
	private List<String> departmentFacetBinValue = new ArrayList<>();
	public final By FILTER_PRODUCT_COUNT = By.cssSelector(".facets-mobile-toggle__selected");
	private static final By PRODUCT_COUNT_HEADER = By.className("facets-mobile-toggle__selected");
	private final By LOAD_ALL_PRODUCTS = By.xpath("//span[@class='facets__loadall-link ng-scope']/span[@class='ng-scope']");
	public WebElement deptHomeLink()
	{
		return waitForElementPresence(homeLink);
	}

	public WebElement mensHomeLink()
	{
		return waitForExpectedElement(mensHomeLink);
	}

	public WebElement teensHomeLink()
	{
		return waitForExpectedElement(teensHomeLink);
	}

	public WebElement womensHomeLink()
	{
		return waitForExpectedElement(womensHomeLink);
	}

	public WebElement facetheadings()
	{
		return waitForExpectedElement(facetheadings);
	}

	public WebElement sortByRelevance()
	{
		return waitForExpectedElement(sortByRelevance);
	}

	public WebElement searchTextSuggestions()
	{
		return waitForExpectedElement(searchTextSuggestions);
	}

	public WebElement noMathcesFoundText()
	{
		return waitForExpectedElement(noMathcesFoundText);
	}

	public WebElement sortByNameDescending()
	{
		return waitForExpectedElement(sortByNameDescending);
	}

	public WebElement facetOptions()
	{
		return waitForAndGetElement(facetOptions, 3);
	}

	public WebElement productText()
	{
		return waitForExpectedElement(productText);
	}

	public WebElement colorCodes()
	{
		return waitForExpectedElement(colorCodes);
	}

	public WebElement category()
	{
		return waitForExpectedElement(category);
	}

	public WebElement greyColour()
	{
		return waitForExpectedElement(greyColour);
	}

	public WebElement blueColour()
	{
		return waitForExpectedElement(blueColour);
	}

	public WebElement selectSize()
	{
		return waitForExpectedElement(selectSize);
	}

	public WebElement clearFiltersLink()
	{
		return waitForExpectedElement(clearFiltersLink);
	}

	public WebElement modelOnOff()
	{
		return waitForExpectedElement(modelOnOff);
	}

	public WebElement modelOffSelected()
	{
		return waitForExpectedElement(modelOffSelected);
	}

	public WebElement defaultImageInPdp()
	{
		return waitForExpectedElement(defaultImageInPdp);
	}

	public WebElement contentSearch()
	{
		return waitForExpectedElement(contentSearch);
	}

	public WebElement productSearch()
	{
		return waitForExpectedElement(productSearch);
	}

	public WebElement departmentFacets()
	{
		return waitForExpectedElement(departmentFacets);
	}

	public WebElement coloursSelected()
	{
		return waitForExpectedElement(coloursSelected);
	}

	public WebElement priceCategory()
	{
		return waitForExpectedElement(priceCategory);
	}

	public WebElement productCount()
	{
		return waitForExpectedElement(productCount);
	}

	public WebElement filterProductCount()
	{
		waitForExpectedElement(FILTER_PRODUCT_COUNT, 10);
		return webDriver.findElements(FILTER_PRODUCT_COUNT).get(1);
	}

	public WebElement loadNextButton()
	{
		return waitForAndGetElement(loadNextButton, DEFAULT_TIMEOUT);
	}

	public WebElement loadAllButton()
	{
		return waitForExpectedElement(loadAllButton);
	}

	public void scrollToNextButton()
	{
		if (IS_MOBILE)
		{
			scrollElementIntoView(loadNextButton);
		}
		else
		{
			scrollElementIntoView(loadNextButton());
		}
	}

	public void scrollToAllButton()
	{
		scrollElementIntoView(getWebDriver().findElement(loadAllButton));
	}

	public void clickOnFacadeColour()
	{
		facetheadings();
		getWebDriver().findElement(By.xpath("(//button[@class='facet__label ng-binding'])[2]")).click();
	}

	public void clickOnFacadeSortBy()
	{
		facetheadings();
//        getWebDriver().findElement(By.xpath("(//button[@class='facet__label'])[1]")).click();
		getWebDriver().findElement(By.cssSelector(".facet__label.facet__label--sort")).click();
	}

	public void clickOnFacadeCategory()
	{
		facetheadings();
//        clickByJavaScriptExecutor(getWebDriver().findElements(By.cssSelector(".facet-options__list-item-value")).get(1));
		getWebDriver().findElements(By.cssSelector(".facet__label")).get(2).click();
	}

	public void clickOnFacadePrice()
	{
		facetheadings();
		getWebDriver().findElement(By.xpath("(//button[@class='facet__label'])[4]")).click();
	}

	public void clickOnFacadeSize()
	{
		facetheadings().click();
		getWebDriver().findElements(By.cssSelector(".facet__label")).get(5).click();
	}


	public List<String> getproductNames() throws InterruptedException
	{
		Thread.sleep(3000);
		List<WebElement> productNames = getWebDriver().findElements(By.cssSelector(".link--nounderline.ng-binding"));
		List<String> names = new ArrayList<>();
		for (WebElement productName : productNames)
		{
			names.add(productName.getText());
		}
		return names;
	}

	public List<String> getSearchSuggestions()
	{
		searchTextSuggestions();

		List<WebElement> searchTextSuggestionsFound = getWebDriver().findElements(By.cssSelector("a[href*='/uk/search/']"));
		List<String> suggestions = new ArrayList<>();
		for (WebElement aSearchTextSuggestionsFound : searchTextSuggestionsFound)
		{
			suggestions.add(aSearchTextSuggestionsFound.getText());
		}
		return suggestions;
	}

	public List<String> getProductSuggestednames()
	{
		productSearch();

		List<WebElement> searchTextSuggestionsFound = getWebDriver().findElements(By.cssSelector("a[class*='product-suggestions__product-text']"));
		List<String> suggestions = new ArrayList<>();
		for (WebElement aSearchTextSuggestionsFound : searchTextSuggestionsFound)
		{
			suggestions.add(aSearchTextSuggestionsFound.getText());
		}
		return suggestions;
	}

	public List<String> getProductSearchSuggestions()
	{
		contentSearch();
		List<WebElement> searchTextSuggestionsFound = getWebDriver().findElements(By.cssSelector(".word-suggestions__word>a"));
		List<String> suggestions = new ArrayList<>();
		for (WebElement aSearchTextSuggestionsFound : searchTextSuggestionsFound)
		{
			suggestions.add(aSearchTextSuggestionsFound.getText());
		}
		return suggestions;
	}


	public List<String> getProductSuggestions()
	{
		productText();

		List<WebElement> productSuggestions = getWebDriver().findElements(By.cssSelector("a[class*='product-text']"));
		List<String> suggestions = new ArrayList<>();
		for (WebElement productSuggestion : productSuggestions)
		{
			suggestions.add(productSuggestion.getText());
		}
		return suggestions;
	}

	public void selectPriceValue(String value)
	{
		sortByRelevance();
		getWebDriver().findElement(By.cssSelector("a[id*='" + value + "']")).click();
	}

	public void selectColour(String colour)
	{
		List<WebElement> availableColorCodes = getWebDriver().findElements(By.cssSelector(".facet-options__list-item-value.ng-binding.facet-options__list-item-value--colour"));

		for (WebElement availableColorCode : availableColorCodes)
		{
			if (availableColorCode.getText().contains(colour))
			{
				availableColorCode.click();
				break;
			}
		}
	}

	public String getColourFacetCount()
	{
		colorCodes();
		String colorAvaialble = getWebDriver().findElement(By.cssSelector(".facet__count")).getText();
		String[] split = colorAvaialble.split("\\(");
		String[] split1 = split[1].split("\\)");
		return split1[0];
	}

	public List<String> getPrices() throws InterruptedException
	{

		Thread.sleep(3000);
		List<WebElement> prices = getWebDriver().findElements(By.cssSelector(".product-item__price.ng-binding"));
		List<String> price = new ArrayList<>();
		List<String> productPrices = new ArrayList<>();
		for (int i = 0; i < prices.size(); i++)
		{
			price.add(prices.get(i).getText());
			String[] split = price.get(i).trim().split("\\s+");
			String[] splitAgain = split[0].trim().split("£");
			productPrices.add(splitAgain[1]);
		}
		return productPrices;
	}

	public int returnTotalProductsAvailable()
	{
		pause(5000);
		if (IS_MOBILE)
		{
			return Integer.parseInt(getWebDriver().findElements(PRODUCT_COUNT_HEADER).get(1).getText().replaceAll("[^\\d.]", ""));
		}
		else
		{
			return Integer.parseInt(getWebDriver().findElement(By.xpath("//p[@data-ng-if='totalResultsCount']/span")).getText().replaceAll("[^\\d.]", ""));
		}
	}

	public int getExposedFacetsValue()
	{
		facetOptions();
		List<WebElement> totalFacetsExposed = getWebDriver().findElements(By.cssSelector(".facet-options.facet-options--show"));
		return totalFacetsExposed.size();
	}

	public void selectMultipleColours()
	{
		greyColour().click();
		blueColour().click();
	}

	public void selectModel(String value)
	{
		modelOnOff();
		getWebDriver().findElement(By.cssSelector("." + value)).click();
	}

	public String getDataModelOnImage()
	{
		return getWebDriver().findElement(By.cssSelector("a[class*='model'] > img[alt*='SB STRIPE SWIM']")).getAttribute("src");
	}

	public boolean modalOnElementIsClickable()
	{
		try
		{
			getWebDriver().findElement(By.cssSelector("a[class*='model'] > img[alt*='SB STRIPE SWIM']")).click();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean facetOpen()
	{
		try
		{
			getWebDriver().findElement(By.cssSelector(".facet-options.facet-options--show"));
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean suggestionsAvailable()
	{
		try
		{
			webDriver.findElement(By.cssSelector("div[class*='site-search__search-suggestions'] >div[ class='site-search__subsuggestions']"));
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public void departmentFacetToggle() throws InterruptedException
	{
		List<WebElement> departmentFacets = getWebDriver().findElements(By.cssSelector("ul[class='search-filter__list'] >li"));
		List<String> facetnames;
		facetnames = getDepartFacetnames();
		assertTrue(departmentFacets.get(0).getAttribute("class").contains("selected"));
		for (int i = 1; i < departmentFacets.size(); i++)
		{
			departmentFacets();
			List<WebElement> departmentFacet = getWebDriver().findElements(By.cssSelector("ul[class='search-filter__list'] >li"));
			departmentFacet.get(i).click();
			Thread.sleep(2000);
			assertTrue(getWebDriver().findElement(By.cssSelector("li[class*='item--selected'] >a >div")).getText().contains(facetnames.get(i)));
		}
	}

	public List<String> getDepartFacetnames()
	{
		departmentFacets();
		List<WebElement> departmentFacetNames = getWebDriver().findElements(By.cssSelector("li[class*='search-filter__item'] >a >div"));
		List<String> facetnames = new ArrayList<>();
		for (WebElement departmentFacetName : departmentFacetNames)
		{
			facetnames.add(departmentFacetName.getText());
		}
		return facetnames;
	}

	public List<String> getDepartFacetNamesMobile()
	{
		pause(1000);
		List<WebElement> departmentFacetNames = getWebDriver().findElements(MOBILE_DEPARTMENTS_DROPDOWN_VALUES);
		List<String> facetNames = new ArrayList<>();
		for (WebElement departmentFacetName : departmentFacetNames)
		{
			facetNames.add(departmentFacetName.getText().split("\\(")[0].trim());
		}
		return facetNames;
	}

	public List<String> getDepartFacetCountMobile()
	{

		List<WebElement> departmentFacetNames = getWebDriver().findElements(MOBILE_DEPARTMENTS_DROPDOWN_VALUES);
		List<String> facetCount = new ArrayList<>();
		for (WebElement departmentFacetName : departmentFacetNames)
		{
			facetCount.add(departmentFacetName.getText().split("\\(")[1].split("\\)")[0].trim());
		}
		return facetCount;
	}

	public void oneDepartmentFacetSelectedAtOnce()
	{
		departmentFacets();
		List<WebElement> departmentFacets = getWebDriver().findElements(By.cssSelector(".search-filter__item.ng-scope"));
		departmentFacets.get(1).click();
		for (WebElement departmentFacet : departmentFacets)
		{
			assertFalse(departmentFacet.getAttribute("class").contains("selected"));
		}
	}

	public List<String> getSortByFacetVaules()
	{
		sortByRelevance();
		List<String> facetnames = new ArrayList<>();
		List<WebElement> departmentFacets = getWebDriver().findElements(By.cssSelector("a[href*='?sort']"));
		for (WebElement departmentFacet : departmentFacets)
		{
			facetnames.add(departmentFacet.getText());
		}
		return facetnames;
	}

	public List<String> getColorsSelected()
	{

		coloursSelected();
		List<String> coloursSelected = new ArrayList<>();

		List<WebElement> colours = getWebDriver().findElements(By.cssSelector("li[class='applied-facets__item ng-scope'] >a"));

		for (WebElement colour : colours)
		{
			coloursSelected.add(colour.getText());
		}
		return coloursSelected;
	}

	public int currentProductCount()
	{
		String currentProducts = productCount().getText();
		String[] split = currentProducts.split("Showing");
		String[] split1 = split[1].split(" ");

		return Integer.parseInt(split1[1].trim());
	}

	public int currentProductCountFromMobileFilter()
	{
		pause(3000);
		String currentProducts = filterProductCount().getText();
		String s = currentProducts.split("Items")[0];
		return Integer.parseInt(s.trim());
	}

	public int totalProductCount()
	{
		String currentProducts = productCount().getText();
		String[] split = currentProducts.split("of");
		String[] split1 = split[1].split(" ");

		return Integer.parseInt(split1[1].trim());
	}

	public int totalProductsFromFilter()
	{
		String currentProducts = productCount().getText();
		String s = "";
		if (currentProducts.contains("result"))
		{
			s = currentProducts.split("result")[0];
		}
		else
		{
			s = currentProducts.split("of")[0].trim().split(" ")[1];
		}
		return Integer.parseInt(s.trim());
	}

	public List<String> getDepartmentFacetBinValues()
	{
		if (IS_MOBILE)
		{
			return getDepartFacetCountMobile();
		}
		else
		{
			List<WebElement> departmentFacets = getWebDriver().findElements(By.cssSelector(".search-filter__count"));
			this.departmentFacetBinValue = new ArrayList<>();
			for (WebElement departmentFacet : departmentFacets)
			{
				departmentFacetBinValue.add(departmentFacet.getText());
			}
			return departmentFacetBinValue;
		}
	}

	public void toggleBetweenDepartFacets() throws InterruptedException
	{
		if (IS_MOBILE)
		{
			assertEquals(currentProductCountFromMobileFilter(), Integer.parseInt(getDepartFacetCountMobile().get(0)));
		}
		else
		{
			assertEquals(totalProductCount(), Integer.parseInt(this.departmentFacetBinValue.get(0)));
		}
		for (int i = 1; i < departmentFacetBinValue.size(); i++)
		{
			List<WebElement> departmentFacets = getWebDriver().findElements(By.cssSelector("ul[class='search-filter__list'] >li"));
			departmentFacets.get(i).click();
			Thread.sleep(2000);
			if (isElementVisible(LOAD_ALL_PRODUCTS, 3))
			{
				clickByJavaScriptExecutor(waitForExpectedElement1(LOAD_ALL_PRODUCTS, DEFAULT_TIMEOUT));
			}
			Thread.sleep(3000);
			List<WebElement> total = getWebDriver().findElements(By.xpath("//div[@class='plp-wrapper']//*[contains(@class,'product-item__name')]"));
			if (i == departmentFacetBinValue.size() - 1)
			{
				break;
			}
			assertEquals(total.size(), Integer.parseInt(this.departmentFacetBinValue.get(i)));
		}
	}

	public void selectItem()
	{
		getWebDriver().findElement(By.className("plp-results")).findElements(By.className("plp-item")).get(0).click();
	}

	public void selectItem(int product)
	{
		getWebDriver().findElement(By.className("plp-results")).findElements(By.className("plp-item")).get(product).click();
	}
}

