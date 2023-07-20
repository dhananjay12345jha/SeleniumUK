package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;
import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static com.salmon.test.page_objects.gui.Features.PEAK_CAPPING_FEATURE;
import static com.salmon.test.page_objects.gui.HomePage.COUNTRY_DROPDOWN_SELECTOR;
import static com.salmon.test.page_objects.gui.HomePage.LOCAL_COUNTRY;
import static com.salmon.test.page_objects.gui.HomePage.MAST_HEADER_STORE_LOCATOR;

public class StoreLocatorPage extends PageObject
{

	private static final By STORE_FINDER_PAGE_GOOGLE_MAP_TOP_BUTTONS = By.cssSelector(".gmnoprint > div");
	private static final By STORE_FINDER_PAGE_GOOGLE_MAP_CLICK_TO_SEE_IN_GOOGLE_MAPS_BUTTON = By.cssSelector(
			".gm-style > div > a");
	private static final By STORE_FINDER_PAGE_GOOGLE_MAP_DATA_LABEL = By.cssSelector(".gmnoprint > div > div > span");
	private static final By STORE_FINDER_PAGE_GOOGLE_MAP_TERMS_OF_USE = By.cssSelector(".gm-style-cc > div > a");
	private static final By STORE_FINDER_PAGE_GOOGLE_MAP_PINS = By.cssSelector("map[id*='gmimap']>area");
	private static final By STORE_FINDER_PAGE_GOOGLE_MAP_PINS_MOBILE = By.xpath("//div[contains(@role, 'button')]");
	private static final By STORE_FINDER_SEARCH_RESULT_DISPLAYED = By.cssSelector(".store-results__item > h3");
	private static final By ADDRESS_POP_UP_HEADER = By.cssSelector(".gm-style-iw > div > div > div > div > h3");
	private static final By STORE_LOCATION_ADDRESS = By.xpath(
			"//p[@class='store-results__pin-text ng-binding'][contains(text(),'A')]/../../p[1]");
	private static final By STORE_LOCATION_ADDRESS2 = By.xpath(
			"//p[@class='store-results__pin-text ng-binding'][contains(text(),'A')]/../following-sibling::p[@class='store-results__address ng-binding']");
	private static final By PEAK_CAPPING_STORE_LOCATION_ADDRESS2 = By.xpath(
			"//p[@class='store-results__pin-text ng-binding'][contains(text(),'A')]/../following-sibling::div/p[1]");
	private static final By COLLECTION_LOCATION_STORE_FINDER_SEARCH_RESULT_DISPLAYED = By.cssSelector(
			".store-results__item >div>h3");
	private static final By PEAK_CAPPING_COLLECTION_LOCATION_STORE_FINDER_SEARCH_RESULT_DISPLAYED = By.cssSelector(
			".store-results__item >div>div>h3");
	private static final By STORE_LOCATOR_LOCATION_ADDRESS = By.xpath(
			"//span[@class='store-results__gmap-marker ng-binding'][contains(text(),'A')]/../p[@class='store-results__address ng-binding']");
	private static final By ADDRESS_POP_UP_LOCATION = By.id("infowindow_content");
	private static final By ADDRESS_POP_UP_TEXT_LOCATION = By.className("store-results__address");
	private Logger log = LogManager.getLogger(StoreLocatorPage.class.getName());

	public final By mapAndOpeningTimes = By.xpath("//a[contains(text(),'Map and opening times')]");
	private final By storeLocatorLink = By.xpath("//a[contains(text(),'Store')]");
	private final By storeFinderTextBox = By.id("storelocator-query");
	private final By FIND_STORES_BUTTON = By.cssSelector(".button.button--primary.button--store-search");
	private final By storesNearMeButton = By.cssSelector("button[data-ng-if='hasGeoLocation']");
	//private final By changeCountryLink = By.xpath("//button[contains(text(),'Change country')]");
	private final By changeCountryLink = By.cssSelector(".header__navitem .header__locale-select>a");
	private static final By SEARCH_COUNTRY_CONTAINER = By.className("store-search__country-trigger");
	private static final By SEARCH_COUNTRY = By.className("ng-binding");
	//private final By countryDropdown = By.cssSelector("div[class='store-search__country-select']");
	private final By COUNTRY_SELECTED_VALUE = By.className("nl-select__choice--chosen");
	private final By COUNTRY_DROPDOWN = By.className("ng-valid");
	private final By totalSearchResults = By.cssSelector("p[class='store-results__total']");
	private final By totalSearchedList = By.cssSelector(".store-results__list>.store-results__item");
	private final By searchAgainLink = By.xpath("//a[contains(text(),'New search')]");
	private final By storeResultsList = By.cssSelector(".store-results__list");
	private final By totalNumberOfStoreResults = By.cssSelector(".store-results__item.ng-scope");
	private final By moreInfoLink = By.cssSelector(".store-results__details-link>a");
	private final By shopName = By.cssSelector(".store-results__title");
	private final By shopAddress = By.cssSelector(".store-results__address");
	private final By distanceFromSearch = By.cssSelector(".store-details__nearby-distance");
	private final By backToResultsLink = By.cssSelector(".store-details__backlink-text");
	private final By storeLocatorErrorMessage = By.cssSelector("p[data-ng-if='sf.noLocationEnteredError']");
	private final By nearMeButton = By.cssSelector("button[data-ng-if='hasGeoLocation']");
	private final By distanceBetweenStores = By.cssSelector("p[class*='store-results__distance']");
	public final By storeResultsTitle = By.cssSelector("h3[class*='store-results__title']");
	private final By storeResultsAddress = By.cssSelector("p[class*='store-results__address']");
	private final By selectSavedStoreButton = By.cssSelector(".button.button--primary.button--collection-selection");
	private final By locationDetails = By.cssSelector("div[class*='modal--map modal--visible']");
	private final By STORE_RESULTS = By.cssSelector(".store-results__distance.ng-binding");
	private final By MOBILE_STORE_RESULTS_TAB_MAP_BUTTON = By.cssSelector(".store-results__tab-button:nth-child(2)");
	private final By MOBILE_STORE_RESULTS_TAB_LIST_BUTTON = By.cssSelector(".store-results__tab-button:nth-child(1)");

	public WebElement storeLocatorLink()
	{
		return waitForAndGetElement(MAST_HEADER_STORE_LOCATOR, DEFAULT_TIMEOUT);
	}

	public WebElement storeFinderTextBox()
	{
		waitForElementPresence(storeFinderTextBox);
		return waitForExpectedElement(storeFinderTextBox);
	}

	public WebElement findStoresButton()
	{
		return waitForExpectedElement(FIND_STORES_BUTTON);
	}

	public WebElement storesNearMeButton()
	{
		return waitForExpectedElement(storesNearMeButton);
	}

	public WebElement changeCountryLink()
	{
		scrollForFocus(LOCAL_COUNTRY, 5);
		return waitForAndGetElement(LOCAL_COUNTRY, DEFAULT_TIMEOUT);
	}

	public WebElement countrySelected()
	{
		return waitForExpectedElement(SEARCH_COUNTRY_CONTAINER).findElement(SEARCH_COUNTRY);
	}

	public WebElement countryDropdown()
	{
		return waitForExpectedElement(COUNTRY_SELECTED_VALUE);
	}

	public List<WebElement> totalSearchedList()
	{
		return getWebDriver().findElements(totalSearchedList);
	}

	public WebElement totalSearchResults()
	{
		return waitForExpectedElement(totalSearchResults, 120);
	}

	public WebElement searchAgainLink()
	{
		return waitForExpectedElement(searchAgainLink);
	}

	public WebElement storeResultsList()
	{
		return waitForExpectedElement(storeResultsList);
	}

	public WebElement totalNumberOfStoreResults()
	{
		return waitForExpectedElement(totalNumberOfStoreResults);
	}

	public void clickMoreInfoLink()
	{
		waitForExpectedElement(moreInfoLink);
		clickByJavaScriptExecutor(getWebDriver().findElement(moreInfoLink));
	}

	public WebElement shopName()
	{
		return waitForExpectedElement(shopName);
	}

	public WebElement shopAddress()
	{
		return waitForExpectedElement(shopAddress);
	}

	public WebElement distanceFromSearch()
	{
		return waitForExpectedElement(distanceFromSearch);
	}

	public WebElement backToResultsLink()
	{
		return waitForExpectedElement(backToResultsLink);
	}

	public WebElement storeLocatorErrorMessage()
	{
		return waitForExpectedElement(storeLocatorErrorMessage);
	}

	public WebElement nearMeButton()
	{
		return waitForExpectedElement(nearMeButton);
	}

	public WebElement distanceBetweenStores()
	{
		return waitForExpectedElement(distanceBetweenStores);
	}

	public WebElement mapAndOpeningTimes()
	{
		return waitForAndGetElement(mapAndOpeningTimes, 10);
	}

	public WebElement storeResultsTitle()
	{
		return waitForExpectedElement(storeResultsTitle);
	}

	public WebElement storeResultsAddress()
	{
		return waitForExpectedElement(storeResultsAddress);
	}

	public WebElement locationDetails()
	{
		return waitForExpectedElement(locationDetails);
	}


	public int totalStoresDisplayed()
	{
		totalNumberOfStoreResults();
		List<WebElement> totalStoreResults = getWebDriver().findElements(By.cssSelector(".store-results__item.ng-scope"));

		return totalStoreResults.size();
	}

	public List<String> distance()
	{
		List<String> collectTheDistanceText = new ArrayList<>();
		List<String> distances = new ArrayList<>();
		List<WebElement> totalStoreResults = getWebDriver().findElements(STORE_RESULTS);
		for (WebElement result : totalStoreResults)
		{
			collectTheDistanceText.add(result.getText());
		}

		for (String distance : collectTheDistanceText)
		{
			String[] splitDistance = distance.split("[ miles, Miles]");
			distances.add(splitDistance[0]);
		}
		return distances;

	}

	public List<String> distanceBetweenTheStores()
	{
		distanceBetweenStores();
		List<String> collectTheDistanceText = new ArrayList<>();
		List<String> distances = new ArrayList<>();
		List<WebElement> totalStoreResults = getWebDriver().findElements(STORE_RESULTS);
		for (WebElement result : totalStoreResults)
		{
			collectTheDistanceText.add(result.getText());
		}

		for (String distance : collectTheDistanceText)
		{
			String[] splitDistance = distance.split("/");
			distances.add(splitDistance[0]);
		}
		return distances;
	}


	public boolean storeLocatorLinkPresent()
	{
		boolean present;
		try
		{
			getWebDriver().findElement(storeLocatorLink);
			present = true;
		}
		catch (org.openqa.selenium.NoSuchElementException e)
		{
			present = false;
		}
		return present;

	}

	public void searchForStores(String searchTerm)
	{
		pause(3000);
		waitForElementPresence(storeFinderTextBox);
		getWebDriver().findElement(storeFinderTextBox).sendKeys(searchTerm);
		getWebDriver().findElement(FIND_STORES_BUTTON).click();
	}

	public boolean getGoogleMapPins()
	{
		pause(2000);
		if (IS_MOBILE)
		{
			find(MOBILE_STORE_RESULTS_TAB_MAP_BUTTON).click();
		}
		return getWebDriver().findElements(STORE_FINDER_PAGE_GOOGLE_MAP_PINS_MOBILE).size() > 0;
	}

	public int getNumberOfMapPinsDisplayedInMap()
	{
		pause(2000);
		if (IS_MOBILE)
		{
			find(MOBILE_STORE_RESULTS_TAB_MAP_BUTTON).click();
		}
		if (!webDriver.findElements(STORE_FINDER_PAGE_GOOGLE_MAP_PINS_MOBILE).isEmpty())
		{
			int size = webDriver.findElements(STORE_FINDER_PAGE_GOOGLE_MAP_PINS_MOBILE).size();
			log.info("Total number of pins in map are  -- " + size);
			return size;
		}
		return 0;
	}

	public int getNumberOfStoreResultsDisplayed()
	{
		pause(2000);
		if(IS_MOBILE)
		{
			find(MOBILE_STORE_RESULTS_TAB_LIST_BUTTON).click();
		}
		if (!webDriver.findElements(STORE_FINDER_SEARCH_RESULT_DISPLAYED).isEmpty())
		{
			int size = webDriver.findElements(STORE_FINDER_SEARCH_RESULT_DISPLAYED).size();
			log.info("Total number of results are  -- " + size);
			return size;
		}
		return 0;
	}

	public void clickMobileStoreResultsTabButton()
	{
		waitForAndGetElement(MOBILE_STORE_RESULTS_TAB_MAP_BUTTON, DEFAULT_TIMEOUT).click();
	}

	public void clickMobileStoreResultsTabList()
	{
		waitForAndGetElement(MOBILE_STORE_RESULTS_TAB_LIST_BUTTON, DEFAULT_TIMEOUT).click();
	}

	public void clickTheFirstResultPinInMap()
	{
		pause(2000);
		final List<WebElement> pins;
		if (IS_MOBILE)
		{
			clickMobileStoreResultsTabButton();

		}
		pins = webDriver.findElements(STORE_FINDER_PAGE_GOOGLE_MAP_PINS_MOBILE);
		log.info("Total number of pins in map are  -- " + pins.size());
		pins.get(0).click();
	}

	public String getTitleOfFirstFromResultsOnStoreFinderPage()
	{
		if (!webDriver.findElements(STORE_FINDER_SEARCH_RESULT_DISPLAYED).isEmpty())
		{
			if (!webDriver.findElements(STORE_LOCATOR_LOCATION_ADDRESS).isEmpty())
			{
				return webDriver.findElements(STORE_LOCATOR_LOCATION_ADDRESS).get(0).getText();
			}
		}
		return null;
	}

	public String getTitleOfFirstFromResults()
	{
		if (PEAK_CAPPING_FEATURE)
		{
			if (!webDriver.findElements(PEAK_CAPPING_COLLECTION_LOCATION_STORE_FINDER_SEARCH_RESULT_DISPLAYED)
						  .isEmpty())
			{
				if (!webDriver.findElements(PEAK_CAPPING_STORE_LOCATION_ADDRESS2).isEmpty())
				{
					return webDriver.findElements(PEAK_CAPPING_STORE_LOCATION_ADDRESS2).get(0).getText();
				}
			}
		}
		else if (!webDriver.findElements(COLLECTION_LOCATION_STORE_FINDER_SEARCH_RESULT_DISPLAYED).isEmpty())
		{
			if (!webDriver.findElements(STORE_LOCATION_ADDRESS).isEmpty())
			{
				return webDriver.findElements(STORE_LOCATION_ADDRESS).get(0).getText();
			}
			if (!webDriver.findElements(STORE_LOCATION_ADDRESS2).isEmpty())
			{
				return webDriver.findElements(STORE_LOCATION_ADDRESS2).get(0).getText();
			}
		}
		return null;
	}

	public String getLocationFromTheMapsResult()
	{
		pause(3000);
		final List<WebElement> addresses = webDriver.findElements(ADDRESS_POP_UP_LOCATION);
		if (!addresses.isEmpty())
		{
			return addresses.get(0).findElement(ADDRESS_POP_UP_TEXT_LOCATION).getText();
		}
		return null;
	}

	//The below method clicks the random pin from the map in mobile
	public void clickTheFirstResultPinInMapMobile()
	{
		if (!webDriver.findElements(STORE_FINDER_PAGE_GOOGLE_MAP_PINS_MOBILE).isEmpty())
		{
			clickByJavaScriptExecutor(webDriver.findElements(STORE_FINDER_PAGE_GOOGLE_MAP_PINS_MOBILE).get(0));
		}
	}

	public WebElement selectedCountryDropdownValue(String country)
	{
		pause(3000);
		return waitForAndGetElement(By.xpath("//form[contains(@class,'popover__content--above')]//span[@class='nl-select__choice--chosen' and text()='"+country+"']"),DEFAULT_TIMEOUT);
	}
}
