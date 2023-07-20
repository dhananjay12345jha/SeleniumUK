package com.salmon.test.step_definitions.gui;

import com.salmon.test.page_objects.gui.HelpPage;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.CheckOutPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.HomePageOld;
import com.salmon.test.page_objects.gui.LandingPage;
import com.salmon.test.page_objects.gui.MyAccountPage;
import com.salmon.test.page_objects.gui.PlpPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Data;

import org.openqa.selenium.interactions.Pause;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Data
public class LandingPageStepDefs
{

	@Autowired
	private HomePage homePage;
	private String homePageUri;
	private String baseCountry = "uk";
	private String tmp_Dept;
	private String tmp_DeptUrl;
	@Autowired
	private HomePageOld homePageOld;
	@Autowired
	private MyAccountPage myAccountPage;
	@Autowired
	private CheckOutPage checkOutPage;
	@Autowired
	private LandingPage landingPage;
	@Autowired
	private PlpPage plpPage;
	@Autowired
	private HelpPage helpPage;

//	public LandingPageStepDefs(HomePage homePage, HomePageOld homePageOld, MyAccountPage myAccountPage, CheckOutPage checkOutPage, LandingPage landingPage, PlpPage plpPage, HelpPage helpPage)
//	{
//		this.homePage = homePage;
//		this.homePageUri = UrlBuilder.getWebsiteUrl();
//		this.homePageOld = homePageOld;
//		this.myAccountPage = myAccountPage;
//		this.checkOutPage = checkOutPage;
//		this.landingPage = landingPage;
//		this.plpPage = plpPage;
//		this.helpPage = helpPage;
//
//	}

	@Then("^I see new landing page presented with country list$")
	public void iSeeNewLandingPagePresentedWithCountryList()
	{
		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(landingPage.getLandingPageHeadingText())
				.overridingErrorMessage("Landing Page Heading is incorrect")
				.isEqualTo("Select your country or region to shop");
		softAssertions.assertThat(landingPage.isPopularCountriesListDisplayed())
				.overridingErrorMessage("Popular Countrries List not displayed");
		softAssertions.assertThat(landingPage.isAllCountriesListDisplayed())
				.overridingErrorMessage("All Countrries List not displayed");
		softAssertions.assertAll();
	}

	@When("^I select 'delivery' country UK from country list$")
	public void iSelectDeliveryCountryFromCountryList()
	{
		landingPage.selectUKAsDeliveryCountry();
	}

	@Then("^I'm redirected to relevant New Look site$")
	public void iMRedirectedToRelevantNewLookSite()
	{
		landingPage.waitForPageLoad();
		landingPage.pause(4000);
		String pageUrl=landingPage.getCurrentUrl();
		Assert.assertTrue("The user is not redirected to UK", pageUrl.endsWith("uk")||pageUrl.endsWith("uk/"));
	}

	@And("^I see (.*) cookie and value (.*)$")
	public void iSeeCookieIsApplied(String cookieName, String value)
	{
		Assert.assertEquals("Invalid cookie", value, landingPage.getNLCookieValue(cookieName));
	}

	@And("^I access website via a direct URL with locale (.*)$")
	public void iAccessWebsiteViaADirectURL(String locale)
	{
		UrlBuilder.navigateHomePageWithLocale(locale);
	}

	@Then("^I'm redirected to default New Look site that was configured$")
	public void iMRedirectedToDefaultNewLookSiteThatWasConfigured()
	{
		Assert.assertEquals("Invalid Url when accessed directly with /row", UrlBuilder.getSiteUrl() + "/row", landingPage.getCurrentUrl());
		Assert.assertEquals("Invalid default country", "Albania - € - English", landingPage.getDeliveryCountryValue());
	}

	@Then("^I see relevant content$")
	public void iSeeRelevantContent()
	{
		homePage.pause(4000);
		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat("Women's Clothing | Dresses, tops, skirts, underwear | New Look").isEqualTo(plpPage.getTitleTag());
		softAssertions.assertThat("Explore and view all women's clothing at New Look today. Choose from a wide range of the freshest styles this season. Free delivery options available.").isEqualTo(plpPage.getContent("description"));
		softAssertions.assertThat("index,archive,follow").isEqualTo(plpPage.getContent("robots"));
		softAssertions.assertAll();
	}

	@Then("^I'm redirected to requested New Look site with locale (.*), country (.*), currency (.*) and language (.*)$")
	public void iMRedirectedToRequestedNewLookSite(String locale, String countryName, String currency, String language) throws InterruptedException
	{
			Assert.assertEquals("Invalid Url", UrlBuilder.getSiteUrl() + locale, landingPage.getCurrentUrl());
			Assert.assertEquals("Invalid default country", countryName + " - " + currency + " - " + language, landingPage.getDeliveryCountryValue());
	}

	@When("^user should be able to change the delivery country from landing page$")
	public void userShouldBeAbleToChangeTheDeliveryCountryFromLandingPage() throws InterruptedException
	{
		homePageOld.localeSelector().click();
		homePage.changeDeliveryCountry("Italy");
	}

	@Then("^I see a Brexit message banner displayed at the top of the page$")
	public void iSeeABrexitMessageBannerDisplayedAtTheTopOfThePage()
	{
		assertFalse("Brexit Banner message is not displayed",landingPage.getBrexitBannerMessage().isEmpty());
	}

	@When("^I click on the Learn more CTA in the Brexit message banner$")
	public void iClickOnTheLearnMoreCTAInTheBrexitMessageBanner()
	{
		landingPage.clickOnLearnMoreCTA();
	}

	@Then("^I'm redirected to the Brexit Help Centre article$")
	public void iMRedirectedToTheBrexitHelpCentreArticle()
	{
		helpPage.isLogoVisible();
		assertTrue("Help Centre logo not visible on Help Centre page", helpPage.isLogoVisible());
	}
}
