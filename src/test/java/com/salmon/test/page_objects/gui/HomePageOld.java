package com.salmon.test.page_objects.gui;


import com.jayway.restassured.response.Response;
import com.salmon.test.framework.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.salmon.test.page_objects.gui.HomePage.LOCAL_COUNTRY;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

public class HomePageOld extends PageObject {

    private Logger log = LogManager.getLogger(HomePageOld.class.getName());

    private final By titleBrandLocator = By.cssSelector("a[title='Brands']");
    private final By searchFieldLocator = By.id("js-site-search-input");
    private final By localeSelector = By.className("header__navitem");
    private final By changeSettingsLocaleButton = By.xpath("(//button[@data-translate='countrySelector.cta.heading'])[3]");
    private final By countrySelector = By.xpath("(//div[@ng-model='country.selected'])[0]");
    private final By currencySelector = By.xpath("(//select[@ng-model='currency.selected'])[3]");
    private final By languageSelector = By.xpath("(//select[@ng-model='language.selected'])");
    private final By selectPdpProductImage = By.xpath("//img[@class='plp-carousel__img ng-scope']");
    private final By pdpProductsImage = By.className("plp-carousel__item");
    private final By countrySearchLocator = By.xpath("(//input[@type='text'])[5]");
    private final By countryListSuggestions = By.cssSelector(".ui-select-choices-row-inner.nl-select__row-inner");
    private final By megaMenu = By.cssSelector("a[href='#Womens']");
    private final By noMatchesFound = By.cssSelector(".ui-select-choices-row-inner.nl-select__row-inner");
    private final By footerPrimaryNavigationLinks = By.cssSelector(".page-footer__primary-nav-item>a");
    private final By footerSecondaryNavigationLinks = By.cssSelector(".page-footer__secondary-nav-link");
    public final By quickViewLink = By.linkText("QUICK VIEW");
    private final By enterCountryText = By.xpath("(//input[@type='text'])[5]");
    private final By enterCountryTextInCheckoutPage = By.cssSelector("div[class='nl-select__search-field'] >input");
    private final By atgWarningMessage = By.xpath("(//ul[@class='alert__list'])[3]");
    private final By localeUpdateButton = By.className("locale-select__button");
    private final By countryOptions = By.className("nl-select__choice");
    private final By countrySearch = By.className("ui-select-search");
    private final By countrySearchSelect = By.className("ui-select-highlight");
    private final By PLP_ITEMS = By.className("plp-carousel__item");
    private final By countryor = By.cssSelector("#delivery3 .nl-select__choice--chosen");

	private final By PRODUCT_NOT_FOUND = By.xpath("//h1[contains(text(), 'we couldn’t find anything')]");
    public By products = By.cssSelector(".plp-item .plp-carousel");

    public List<WebElement> pdpProductsImage() {
        waitForElementPresence(pdpProductsImage);
        return getWebDriver().findElements(pdpProductsImage);
    }

    public WebElement titleBrand() {
        return waitForExpectedElement(titleBrandLocator);
    }

    public WebElement searchFieldLocator() {
        return waitForExpectedElement(searchFieldLocator);
    }

    public WebElement localeSelector() {
	    try
	    {
		    waitForPageLoad();
		    scrollForFocus(LOCAL_COUNTRY, 20);
		    scrollToBottom();
		    return waitForAndGetElement(LOCAL_COUNTRY, DEFAULT_TIMEOUT);
	    }
	    catch (Exception e)
	    {
		    scrollForFocus(LOCAL_COUNTRY, 20);
		    scrollToBottom();
		    return waitForAndGetElement(LOCAL_COUNTRY, DEFAULT_TIMEOUT);
	    }
    }

    public WebElement localeUpdateButton() {
        return waitForExpectedElement(localeUpdateButton);
    }

    public WebElement changeSettingsLocaleButton() {
        return waitForExpectedElement(changeSettingsLocaleButton);
    }

    public WebElement countrySelector() {
        return waitForExpectedElement(countrySelector);
    }

    public WebElement currencySelector() {
        return waitForExpectedElement(currencySelector);
    }


    public WebElement selectPdpProductImage() {
        return waitForExpectedElement(selectPdpProductImage);

    }

    public void selectRandomProduct() {
        waitForAndGetElement(selectPdpProductImage,10);
        scrollForFocusAndClick(products, 2);
        //products.click();

    }

    public WebElement selectProduct() {
        List<WebElement> thing = getWebDriver().findElements(PLP_ITEMS);
        if (thing.size() == 1) {
            return thing.get(0);
        } else {
            return thing.get(1);
        }
    }

    public boolean selectProductPresent() {
        return isElementPresent(PLP_ITEMS);
    }

    public boolean productNotFound() {
        return isElementPresent(PRODUCT_NOT_FOUND);
    }


    public void scrollToProductImage() {
        scrollElementIntoView(selectPdpProductImage);
    }

    public WebElement quickViewLink() {
        return waitForExpectedElement(quickViewLink);
    }

    public WebElement enterCountryText() {
        return waitForExpectedElement(enterCountryText);
    }

    public WebElement enterCOuntryTextInCheckoutPage() {
        return waitForExpectedElement(enterCountryTextInCheckoutPage);
    }

    public WebElement countrySearchLocator() {
        return waitForExpectedElement(countrySearchLocator);
    }

    public WebElement countryListSuggestions() {
        return waitForExpectedElement(countryListSuggestions);
    }

    public WebElement megaMenu() {
        return waitForExpectedElement(megaMenu);
    }

    public WebElement noMatchesFound() {
        return waitForExpectedElement(noMatchesFound);
    }

    public WebElement footerPrimaryNavigationLinks() {
        return waitForExpectedElement(footerPrimaryNavigationLinks);
    }

    public WebElement footerSecondaryNavigationLinks() {
        return waitForExpectedElement(footerSecondaryNavigationLinks);
    }

    public WebElement atgWarningMessage() {
        return waitForExpectedElement(atgWarningMessage);
    }

    public void clickOnProductImage() {
        selectPdpProductImage();
        List<WebElement> products = getWebDriver().findElements(By.cssSelector("img[src*='/_ui/accelerator/theme-newlook/images/']"));
        products.get(products.size() - 1).click();
    }

    public void selectLanguage(String language) {
        new Select(getWebDriver().findElement(languageSelector)).selectByVisibleText(language);
    }

    public void selectCountry(String country) {
        getWebDriver().findElement(countryOptions).click();
        getWebDriver().findElement(countrySearch).sendKeys(country);
        getWebDriver().findElement(countrySearchSelect).click();
    }

    public void selectCurrency(String currency) {
        currencySelector();
        (getWebDriver().findElement(By.xpath("(//select[@ng-model='currency.selected'])[3]"))).click();
        List<WebElement> currencies = getWebDriver().findElements(By.cssSelector(".ui-select-choices-row-inner.nl-select__row-inner"));
        for (WebElement currency1 : currencies) {
            if (currency1.getText().contains(currency)) {
                currency1.click();
                break;
            }
        }
    }

    public List<String> countrySuggestionsDisplayed() {
        countryListSuggestions();
        List<String> countries = new ArrayList<>();
        List<WebElement> countriesDisplayed = getWebDriver().findElements(By.cssSelector(".ui-select-choices-row-inner.nl-select__row-inner"));
        for (WebElement aCountriesDisplayed : countriesDisplayed) {
            countries.add(aCountriesDisplayed.getText());
        }
        return countries;
    }

    public String getCurrentUrl() {
        return getWebDriver().getCurrentUrl();
    }

    public List<String> getCountriesDisplayed() {
        countryListSuggestions();
        List<String> countries = new ArrayList<>();
        List<WebElement> countriesDisplayed = getWebDriver().findElements(By.cssSelector(".ui-select-choices-row-inner.nl-select__row-inner"));
        for (WebElement aCountriesDisplayed : countriesDisplayed) {
            countries.add(aCountriesDisplayed.getText());
        }
        return countries;
    }


    public String getCookies() {
        getWebDriver().navigate().refresh();
        return getWebDriver().manage().getCookieNamed("newlookSessionData").toString();
    }


    public void hoverOverMegaBrand(String category) {
        megaMenu();
        WebElement hoverElement = getWebDriver().findElement(By.cssSelector("a[href='#" + category + "']"));
        hoverElement.click();
    }

    public void checkFooterPrimaryNavigationLinksWorking() {
        footerPrimaryNavigationLinks();

        List<WebElement> primaryLinks = getWebDriver().findElements(By.cssSelector(".page-footer__primary-nav-item>a"));
        List<String> links = new ArrayList<>();

        for (WebElement primaryLink : primaryLinks) {
            if (!primaryLink.getAttribute("href").contains("#")) {
                links.add(primaryLink.getAttribute("href"));

            }
        }

        for (String link : links) {
            Response response = given().relaxedHTTPSValidation().when().get(link);
            if (response.statusCode() != 200) {

            }
            org.junit.Assert.assertEquals(" Broken Link primary:" + link, response.statusCode(), 200);
        }
    }

    public boolean localePopup() {
        return isElementPresent(By.className("popover__content"));
    }

//    public void navigateToHomePage()
//    {
//        Application baseUri = configurationLookup.getAppEnvConfig().getApp(AppEnum.nlk_ui);
//        goToSite(baseUri.getBaseUri());
//    }

    public void selectDifferentDeliveryCountryInCheckout(String deliveryCountry) throws InterruptedException {
        waitForElementVisible(countryor, 5);
        getWebDriver().findElement(countryor).click();
        enterCOuntryTextInCheckoutPage().click();
        enterCOuntryTextInCheckoutPage().sendKeys(deliveryCountry);
        List<WebElement> countries = getWebDriver().findElements(By.cssSelector(".ui-select-choices-row-inner.nl-select__row-inner"));
        for (WebElement country : countries) {
            if (country.getText().contains(deliveryCountry)) {
                Thread.sleep(2000);
                WebElement selectCountry = getWebDriver().findElement(By.cssSelector(".ui-select-highlight"));
                selectCountry.click();
                break;
            }
        }
    }


}
