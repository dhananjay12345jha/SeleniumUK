package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.page_objects.gui.CheckOutPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.HomePageOld;
import com.salmon.test.page_objects.gui.MyAccountPage;
import com.salmon.test.properties.NavigationProperties;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Data;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.*;
import static com.salmon.test.page_objects.gui.CheckOutPage.MAST_HEADER_SEARCH_FIELD;
import static com.salmon.test.page_objects.gui.HomePage.MOBILE_SEARCH_TEXTBOX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@Data
public class HomePageSteps
{

	@Autowired
	private HomePage homePage;
	private String homePageUri = UrlBuilder.getWebsiteUrl();;
	private String baseCountry = "uk";
	private String tmp_Dept;
	private String tmp_DeptUrl;
	@Autowired
	private HomePageOld homePageOld;
	@Autowired
	private MyAccountPage myAccountPage;
	@Autowired
	private CheckOutPage checkOutPage;
	private String expectedDataTracker;

//	public HomePageSteps(HomePage homePage, HomePageOld homePageOld, MyAccountPage myAccountPage, CheckOutPage checkOutPage)
//	{
//		this.homePage = homePage;
//		this.homePageUri = UrlBuilder.getWebsiteUrl();
//		this.homePageOld = homePageOld;
//		this.myAccountPage = myAccountPage;
//		this.checkOutPage = checkOutPage;
//	}

	@Given("^able to navigate through mega menu (.*)$")
	public void navigateToSection(String direction) throws Throwable
	{
		homePage.goToSite(homePageUri);
		List<String> cat = Arrays.asList(direction.split("->"));
		homePage.selectMegaMenu(cat.get(0));
		homePage.waitForTextToBePresentInElement(homePage.megaMenuHeading, cat.get(0).trim(), 5);
		homePage.selectSecondaryMenu(cat.get(1));
	}

	@Then("^check all mega menu headings$")
	public void assertHeadings() throws Throwable
	{
		for (WebElement e : homePage.getMegaMenu())
		{
			e.click();
			if (homePage.isElementPresent(homePage.megaMenuHeading))
			{
				homePage.waitForTextToBePresentInElement(homePage.megaMenuHeading, e.getText(), 5);
			}
		}
	}

	@When("^Navigate to (.*) department from mega menu$")
	public void navigateToDepartmentFromMegaMenu(final String dept)
	{
		if (IS_MOBILE)
		{
			homePage.navigateToDepartmentMobile(dept);
		}
		else
		{
			homePage.navigateToDepartmentFromMegaMenu(dept);
		}
	}

	@When("^Navigate to mega menu (.*) from (.*) department and get Data tracker code$")
	public void navigateToDepartmentFromMegaMenuTagAndGetDataTrackerCode(final String tag, String dept)
	{
		if (IS_MOBILE)
		{
			expectedDataTracker = homePage.navigateToDepartmentFromMegaMenuTagAndGetDataTrackerCodeMobile(dept, tag);
		}
		else
		{
			expectedDataTracker = homePage.navigateToDepartmentFromMegaMenuTagAndGetDataTrackerCode(dept, tag);
		}
	}

	@When("^Navigate to mega menu (.*) from (.*) department$")
	public void navigateToDepartmentFromMegaMenuTag(final String tag, String dept)
	{
		if (IS_MOBILE)
		{
			homePage.navigateToDepartmentFromMegaMenuTagMobile(dept, tag);
		}
		else
		{
			homePage.navigateToDepartmentFromMegaMenuTag(dept, tag);
		}
	}

	@When("^navigate to (.*) department home page from mega menu$")
	public void navigateToDepartmentHomePageFromMegaMenu(String dept) throws Throwable
	{
		homePage.navigateToDepartmentHomePageFromMegaMenu(dept);
	}

	@When("^click megamenu item (.*)$")
	public void navigateTMegaMenu(String dept) throws Throwable
	{
		tmp_Dept = dept;
		homePage.goToSite(homePageUri);
		homePage.selectMegaMenu(dept);
	}

	@Then("^page should show correct breadcrumb for selected department$")
	public void pageShouldShowCorrectDepartmentBreadcrumb() throws Throwable
	{

		assertThat(homePage.getBreadCrumbs().get(1).getText()).isEqualTo(formExpDepartmentBreadCrumb(baseCountry, tmp_Dept));
		tmp_DeptUrl = homePage.getCurrentUrl();
	}

	@And("^select a random product from PLP$")
	public void selectARandomProductFromPLP() throws Throwable
	{
		homePage.selectRandomProduct();
	}

	@And("^clicking department breadcrumb should show right url$")
	public void clickBreadcrumbDept() throws Throwable
	{
		homePage.getBreadCrumbs().get(1).click();
		assertThat(homePage.getCurrentUrl()).containsIgnoringCase(tmp_DeptUrl);
	}

	@And("^clicking home breadcrumb should show right url$")
	public void clickBreadcrumbHome() throws Throwable
	{
		homePage.getBreadCrumbs().get(0).click();
		assertThat(homePage.getCurrentUrl()).contains(homePageUri + baseCountry + "/");
	}

	public String formExpDepartmentBreadCrumb(String country, String cat)
	{
		return country.toUpperCase() + " " + cat + " Department";
	}

	@Then("^click footer link (.*) should take to page with title (.*)$")
	public void clickFooterLinkContactUsShouldTakeToPageWithTitle(String footer, String expTitle) throws Throwable
	{
	}

	@Then("^footer link (.*) should navigate to page (.*)")
	public void clickFooterLinkContactShouldTakeToPageWithTitle(String footer, String expTitle) throws Throwable
	{
		homePage.clickFooterLink(footer);
		homePage.waitForPageTitleContains(expTitle, 20);
	}

	@When("^search site with string (.*)$")
	public void searchProductsWithStringJeans(String str) throws Throwable
	{
		homePage.searchForProduct(str);
	}

	@When("^I click on the locale selector in the header$")
	public void I_click_on_the_locale_selector_in_the_header()
	{
		homePageOld.localeSelector().click();
	}

	@And("^I select a different currency \"([^\"]*)\" using the drop down$")
	public void I_select_a_different_currency_using_the_drop_down(String currency)
	{
		if (!homePageOld.localePopup())
		{
			homePageOld.localeSelector().click();
			homePageOld.selectCurrency(currency);

		}
		else
		{
			homePageOld.selectCurrency(currency);
		}
	}

	@And("^I select a different country (.*) using the drop down$")
	public void I_select_a_different_country_using_the_drop_down(String country) throws InterruptedException
	{
		homePage.changeDeliveryCountry(Props.getProp(country));
	}

	@When("^I select change settings$")
	public void i_select_change_settings() throws InterruptedException
	{
		homePageOld.localeUpdateButton().click();
		Thread.sleep(1000);
	}

	@Then("^should have link (.*)$")
	public void shouldHaveLinkBlog(String link) throws Throwable
	{
		WebElement e = null;
		try
		{
			e = homePage.getLink(link);
		}
		catch (Exception ex)
		{
			//nothing
		}
		assertThat(e).isNotNull();
	}

	@When("^product search with string (.*) should show min (\\d+) matched results$")
	public void searchProductsWithStringJeans(String str, int results) throws Throwable
	{
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(str);
		}
		else
		{
			homePage.searchForProduct(str);
		}
		assertThat(homePage.getNoOfProductsMatchSearch()).isGreaterThan(results);
	}


	@When("^click link with text (.*)$")
	public void clickLinkWithTextView(String text) throws Throwable
	{
		WebElement e = null;
		try
		{
			e = homePage.getLink(text);
		}
		catch (Exception ex)
		{
			//nothing
		}
		assertThat(e).isNotNull();
		e.click();
	}

	@Then("^alert should not appear$")
	public void assertAlertPresence() throws Throwable
	{
		assertThat(homePage.isAlertPresent(3)).isFalse();
	}

	@Then("^The selected country \"([^\"]*)\" should have the \"([^\"]*)\"$")
	public void the_selected_should_have_the(String country, String language) throws Exception
	{
		switch (country)
		{
			case "France":
				assertTrue(language, homePageOld.localeSelector().getText().contains("Français"));
				break;
			case "Germany":
				assertTrue(language, homePageOld.localeSelector().getText().contains("Deutsch"));
				break;
			case "United Kingdom":
				assertTrue(language, homePageOld.localeSelector().getText().contains("English"));
				break;
			case "Albania":
				assertTrue(language, homePageOld.localeSelector().getText().contains("English"));
				break;
		}
	}

	@Then("^gender button does not exist \"([^\"]*)\"$")
	public void genderButtonDoesNotExist(String buttonText) throws Throwable
	{
		Assert.assertFalse(homePage.getSignUpButtonText().contains(buttonText));
	}

	@And("^news letter signup button equals \"([^\"]*)\"$")
	public void newsLetterSignupButtonEquals(String buttonText) throws Throwable
	{
		Assert.assertTrue(StringUtils.containsIgnoreCase(homePage.getSignUpButtonText(), buttonText));
	}

	@When("^I click on Sign Me Up button$")
	public void iClickOnSignMeUpButton() throws Throwable
	{
		homePage.clickSignMeUpButton();
	}

	@When("^I click on Sign Me Up button on mobile$")
	public void iClickOnSignMeUpButtonOnMobile()
	{
		homePage.clickSignMeUpButtonOnMobile();
	}

	@Then("^this is a required field error box appears$")
	public void thisIsARequiredFieldErrorBoxAppears() throws Throwable
	{
		Assert.assertTrue(homePage.errorMessageAppears());
	}

	@And("^I navigate to the homepage$")
	public void iNavigateToTheHomepage() throws Throwable
	{
		homePage.getBreadCrumbs().get(0).click();
	}

	@Then("^it gives an did you mean option with text \"([^\"]*)\"$")
	public void itGivesAnDidYouMeanOptionWith(String didYouMeanExpectedText) throws Throwable
	{
		Assert.assertTrue(homePage.getDidYouMeanText().contentEquals(didYouMeanExpectedText));
	}

	@Then("^mobile search box should have default text place holder as (.*)$")
	public void mobileSearchBoxShouldHaveDefaultTextPlaceHolderAsSearch(String text)
	{
		homePage.clickMobileSearchIcon();
		assertThat(homePage.getSearchBoxText().equalsIgnoreCase(text));
	}

	@And("^search box should have default text place holder as (.*)")
	public void itShouldHaveDefaultTextPlaceHolderAsSearch(String text) throws Throwable
	{
		assertThat(homePage.getSearchBoxText().equalsIgnoreCase(text));
	}

	@And("^I click on hotspot trigger$")
	public void iClickOnHotspotTrigger() throws Throwable
	{
		homePage.clickHotSpotTrigger();
	}

	@When("^user navigates to \"([^\"]*)\"$")
	public void userNavigatesTo(String url)
	{
		homePage.navigateToUrl(Props.getProp(url));
	}

	@And("^IKANO card image is displayed in the footer$")
	public void ikanoCardImageIsDisplayedInTheFooter()
	{
		Assert.assertTrue(homePage.isIkanoCardIsDisplayed());
	}

	@And("^item text suggestion is displayed \"([^\"]*)\"$")
	public void itemTextSuggestionIsDisplayed(String text)
	{
		Assert.assertTrue(homePage.isItemSuggestionDisplayed(text));
	}

	@And("^item text suggestion is not displayed \"([^\"]*)\"$")
	public void itemTextSuggestionIsNotDisplayed(String text)
	{
		Assert.assertFalse(homePage.isItemSuggestionDisplayed(text));
	}

	@And("^type \"([^\"]*)\" in the search box on mobile$")
	public void typeInTheSearchBoxOnMobile(String searchText)
	{
		homePage.mobileInsertText(searchText);
	}

	@When("^I select \"([^\"]*)\" from the footer$")
	public void iSelectFromTheFooter(String link) throws Throwable
	{
		homePage.clickFooterLink(link);
	}

	@Then("^I am taken to the delivery redirect page$")
	public void iAmTakenToTheDeliveryRedirectPage()
	{
		homePage.waitForPageLoad();
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad") || BROWSER.contains("emulator"))
		{
			homePage.pause(4000);
		}
		try
		{
			homePage.waitForPageTitleContains("Delivery",10);
		}
		catch (Exception e)
		{
			// Do Nothing
		}
		Assert.assertTrue(homePage.getCurrentUrl().contains("Delivery"));
	}

	@Then("^should show min \"([^\"]*)\" matched results$")
	public void shouldShowMinMatchedResults(int results)
	{
		assertThat(homePage.getNoOfProductsMatchSearch()).isGreaterThan(results);
	}

	@And("^click  \"([^\"]*)\" on mobile$")
	public void click(String link)
	{
		switch (link)
		{
			case "FAQ":
				homePage.clickFooterOnMobile("Help");
				homePage.clickFooterLink(link);
				break;
			case "Track Your Order":
			case "Delivery":
				homePage.clickFooterOnMobile("Delivery & Returns");
				homePage.clickFooterLink(link);
				break;
			case "Staff Discount":
				homePage.clickFooterOnMobile("More ways to pay");
				homePage.clickFooterLink(link);
				break;
		}

	}

	@Then("^should navigate to mobile page \"([^\"]*)\"$")
	public void shouldNavigateToMobilePage(String expTitle) throws Throwable
	{
		homePage.waitForPageTitleContains(expTitle, 20);
	}

	@And("^navigate to my orders on mobile$")
	public void navigateToMyOrdersOnMobile()
	{
		homePage.navigateToMyAccountFromHeaderOnMobile();
		myAccountPage.clickMyOrders();
	}

	@When("^user logs out on mobile$")
	public void userLogsOutOnMobile()
	{
		homePage.logOutFromAccountMobile();
		homePage.waitForPageLoad();
	}

	@And("^I click on search field$")
	public void iClickOnSearchField()
	{
		homePage.clickSearchField();
	}

	@And("^I click on search icon on mobile$")
	public void iClickOnSearchIconOnMobile()
	{
		homePage.pause(3000);
		homePage.clickMobileSearchIcon();
	}

	@And("^click on auto trend item \"([^\"]*)\"$")
	public void clickOnAutoTrendItem(String trendText)
	{
		homePage.pause(1500);
		homePage.clickAutoCompleteTrend(trendText);
	}

	@And("^click on auto suggestion item \"([^\"]*)\"$")
	public void clickOnAutoSuggestionItem(String suggestionText)
	{
		homePage.pause(1500);
		homePage.clickAutoSuggestion(suggestionText);
	}

	@And("^I click on a dress image$")
	public void iClickOnADressImage()
	{
		homePage.clickAutoCompleteName();
	}

	@And("^press enter from the keyboard on search$")
	public void pressEnterFromTheKeyboard()
	{
		homePage.pressEnterFromKeyboard(MAST_HEADER_SEARCH_FIELD);
	}

	@And("^press enter from the keyboard on search on mobile$")
	public void pressEnterFromTheKeyboardOnMobile()
	{
		homePage.pressEnterFromKeyboard(MOBILE_SEARCH_TEXTBOX);
	}

	@Given("^I click on \"([^\"]*)\" department$")
	public void i_click_on_department(String suggestDepartment)
	{
		homePage.clickOnAutoSuggestTab(suggestDepartment);
	}

	@Given("^The selected filter is equal \"([^\"]*)\"$")
	public void the_selected_filter_is_equal(String searchedSection)
	{
		if (IS_MOBILE)
		{
			assertTrue(homePage.getTextOfFilteredDepartment().toUpperCase().startsWith(searchedSection));
		}
		else
		{
			assertTrue(homePage.getTextOfFilteredDepartment().equalsIgnoreCase(searchedSection));
		}
	}

	@Given("^I click on See All Results$")
	public void i_click_on_See_All_Results()
	{
		homePage.seeAllResults();
	}

	@Then("^when the below department is clicked, then the below department is displayed$")
	public void when_the_below_department_is_clicked_then_the_below_department_is_displayed(List<String> departments)
	{
		for (String department : departments)
		{
			homePage.clickOnAutoSuggestTab(department);
			if (IS_MOBILE)
			{
				assertTrue(homePage.getTextOfSelectedAutoSuggestDepartment().equalsIgnoreCase(department));
			}
			else
			{
				assertTrue(homePage.getTextOfSelectedAutoSuggestDepartment().equalsIgnoreCase(department));
			}
		}
	}

	@Then("^highlighted department should be equals to clicked department \"([^\"]*)\"$")
	public void highlightedDepartmentShouldBeEqualsToClickedDepartment(String department)
	{
		if (IS_MOBILE)
		{
			assertTrue(homePage.getTextOfSelectedAutoSuggestDepartment().equalsIgnoreCase(department));
		}
		else
		{
			assertTrue(homePage.getTextOfSelectedAutoSuggestDepartment().equalsIgnoreCase(department));
		}
	}

	@And("^I click on search icon$")
	public void iClickOnSearchIcon()
	{
		checkOutPage.searchButton().click();
	}

	@Then("^Klarna logo is visible in the footer$")
	public void klarnaLogoIsVisibleInTheFooter() throws Throwable
	{
		assertTrue(homePage.isKlarnaLogoPresentInFooter());
	}

	@Then("^Validate Got It button$")
	public void validateGotItButton()
	{
		Assert.assertTrue(homePage.gotItButtonIsPresent());
	}

	@Then("^Click on Got It button$")
	public void clickOnGotItButton()
	{
		homePage.clickGotItButton();
	}

	@And("^Refresh homepage$")
	public void refreshHomepage()
	{
		homePage.refresh();
	}

	@Then("^Got It button is not present$")
	public void gotItButtonIsNotPresent() throws Throwable
	{
		Assert.assertFalse(homePage.gotItButtonIsPresent());

	}

	@And("^Clear cookies$")
	public void clearCookies()
	{
		homePage.deleteAllCookies();
	}

	@And("^user hovers on (.*) department from mega menu$")
	public void userHoversOnDepartmentFromMegaMenu(String Department)
	{
		homePage.hoverOnDepartmentFromMegaMenu(Department);
	}

	@Then("^I see network call to load json data$")
	public void iSeeNetworkCallToLoadJsonData()
	{
		boolean isNetworkCallMade = false;
		String networkLog = homePage.getNetworkLog();
		String expectedCall = "^.*\\{.*initiatorType=xmlhttprequest,.*name=.*/uk/json/meganav/tier-one/.*transferSize=.*\\}.*$";
		if (networkLog.matches(expectedCall))
		{
			isNetworkCallMade = true;
		}
		assertTrue(isNetworkCallMade);

	}


	@Then("^I see json data loaded from cache$")
	public void iSeeJsonDataLoadedFromCache()
	{
		boolean isNetworkCallMade = false;
		String networkLog = homePage.getNetworkLog();
		String expectedCall = "^.*\\{.*initiatorType=xmlhttprequest,.*name=.*/uk/json/meganav/tier-one/.*transferSize=0,.*\\}.*$";
		if (networkLog.matches(expectedCall))
		{
			isNetworkCallMade = true;
		}
		assertTrue(isNetworkCallMade);

	}

	@Then("^I will not see network call to load json data$")
	public void iWillNotSeeNetworkCallToLoadJsonData()
	{
		boolean isNetworkCallMade = false;
		String networkLog = homePage.getNetworkLog();
		String expectedCall = "^.*\\{.*initiatorType=xmlhttprequest,.*name=.*/uk/json/meganav/tier-one/.*transferSize=.*\\}.*$";
		if (networkLog.matches(expectedCall))
		{
			isNetworkCallMade = true;
		}
		assertFalse(isNetworkCallMade);
	}

	@When("^user closes mobile main navigation$")
	public void userClosesMobileMainNavigation()
	{
		homePage.closeMobileBurgerMenu();
	}

	@And("^user clicks on the New Look logo$")
	public void userClicksOnTheNewLookLogo()
	{
		homePage.clickOnNewLookLogo();
	}

	@And("^user access newlook through home page url$")
	public void userAccessNewlookThroughHomePageUrl()
	{
		homePage.pause(4000);
		UrlBuilder.navigateToURL(UrlBuilder.getSiteUrl());
	}

	@Then("^I'm redirected to (.*) department homepage$")
	public void imRedirctedToDepartmentHomePage(String dept) throws Throwable
	{
		homePage.waitForPageLoad();
		homePage.pause(3000);
		assertTrue("user is not redirected to department Homepage", homePage.getCurrentUrl().endsWith(dept.toLowerCase()));
	}

	@And("^click on change delivery country link on footer$")
	public void clickOnChangeDeliveryCountryLinkOnFooter()
	{
		homePage.pause(4000);
		if(homePage.isElementPresent(By.xpath("//*[@class='cookie-banner__wrapper-cta']/button")))
		{
			homePage.find(By.xpath("//*[@class='cookie-banner__wrapper-cta']/button")).click();
		}
		homePage.pause(2000);
		homePage.selectCountryLanguageLinkFooter();
	}

	@Then("^I should see brexit message above change setting cta$")
	public void iShouldSeeBrexitMessageAboveChangeSettingCta()
	{
		assertFalse("Brexit message is not displayed", homePage.getBrexitMessage().isEmpty());
	}

	@Given("^I navigate to the PLP$")
	public void iNavigateToThePLP()
	{
		navigateToDepartmentFromMegaMenu(NavigationProperties.testMeganavLink());
	}

	@When("^I change country (.*) to (.*)$")
	public void iChangeCountryCountryToCountry(String currentCountry, String DesiredCountry)
	{
		homePage.changeDeliveryCountry(Props.getProp(currentCountry + "_" + DesiredCountry));
	}

	@And("^I should see brexit message for country (.*)$")
	public void iShouldSeeBrexitMessageMessage(String message)
	{
		String expectedMessage = Props.getProp(message);
		assertTrue("Expected Brexit message is not displayed", homePage.getBrexitMessage().contains(expectedMessage));
	}

	@Then("^Data Tracker code is (.*) as that of productFindingMethod value$")
	public void dataTrackerCodeIsSameAsThatOfProductFindingMethodValue(String comparison)
	{
		homePage.pause(3000);
		homePage.waitForPageLoad();
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		String consoleDataLayerValue = (String) js.executeScript("return nlDatalayer.page.product_finding_method");

		if (comparison.equalsIgnoreCase("same"))
		{
			Assert.assertEquals("Values Don't match", expectedDataTracker, consoleDataLayerValue);
		}
		else
		{
			assertFalse(expectedDataTracker.equalsIgnoreCase(consoleDataLayerValue));
		}

	}

	@Then("App footer link is displayed")
	public void appFooterLinkIsDisplayed()
	{
		Assert.assertTrue(homePage.appFooterLinkDisplayed());
	}
}
