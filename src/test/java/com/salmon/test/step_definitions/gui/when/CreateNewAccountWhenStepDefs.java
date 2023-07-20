package com.salmon.test.step_definitions.gui.when;


import com.salmon.test.framework.helpers.utils.Credentials;
import com.salmon.test.page_objects.gui.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;



public class CreateNewAccountWhenStepDefs
{

	@Autowired
	private HomePage homePage;
	@Autowired
	private CreateNewAccountPage createNewAccountPage;
	@Autowired
	private MyAccountPage myAccountPage;
	@Autowired
	private SignInPage signInPage;
	@Autowired
	private Credentials credentials;

//	public CreateNewAccountWhenStepDefs(HomePage homePage,
//										CreateNewAccountPage createNewAccountPage,
//										MyAccountPage myAccountsPage,
//										SignInPage signInPage)
//	{
//		this.homePage = homePage;
//		this.createNewAccountPage = createNewAccountPage;
//		this.myAccountPage = myAccountsPage;
//		this.signInPage = signInPage;
//	}

	@When("^Navigate to create account page$")
	public void navigateToCreateAccountPage()
	{
		homePage.navigateToCreateNewAccountPageByClickingHeaderLink();
	}

	@When("^user provides required details to create new account$")
	public void userProvidesRequiredDetailsToCreateNewAccount()
	{
		homePage.navigateToCreateNewAccountPageByClickingHeaderLink();
		credentials = createNewAccountPage.createNewAccountWithCorrectDetails();
	}

	@When("^user provides required details to create new account on mobile$")
	public void userProvidesRequiredDetailsToCreateNewAccountOnMobile()
	{
		navigateToCreateAccountPageOnMobile();
		credentials = createNewAccountPage.createNewAccountWithCorrectDetails();
		homePage.clickMyAccountBreadCrumb();
	}

	@When("^user relogin his account$")
	public void userProvidesRequiredDetailsToCreateNewAccount2()
	{
		homePage.clickMyAccountButtonIfDisplayed();
		homePage.signOutOfAccount();
		myAccountPage.deleteAllCookies();
		homePage.navigateToSingInPageByClickingSingInLinkInHeaders();
		homePage.clickSignIn();
		signInPage.signInUsingUserNameAndPassword(credentials.getEmail(), credentials.getPassword());
	}

	@And("^user relogins in to account on mobile$")
	public void userReloginsInToAccountOnMobile()
	{
		homePage.logOutFromAccountMobile();
		homePage.mobileNavigateToLoginPage();
		signInPage.signInUsingUserNameAndPassword(credentials.getEmail(), credentials.getPassword());
	}

	@Then("^over 18 checkbox is displayed$")
	public void over18CheckboxIsDisplayed()
	{
		Assert.assertTrue(createNewAccountPage.ageConfirmationCheckBoxExists());
	}

	@When("^consent principal is switched on, user provides required details to create new account$")
	public void consentPrincipalIsSwitchedOnUserProvidesRequiredDetailsToCreateNewAccount()
	{
		homePage.navigateToCreateNewAccountPageByClickingHeaderLink();
		credentials = createNewAccountPage.createNewAccountWithCorrectDetailsAndStayOnConsentPage();
	}

	@When("^consent principal is switched on, user provides required details to create new account on mobile$")
	public void consentPrincipalIsSwitchedOnUserProvidesRequiredDetailsToCreateNewAccountOnMobile()
	{
		navigateToCreateAccountPageOnMobile();
		credentials = createNewAccountPage.createNewAccountWithCorrectDetailsAndStayOnConsentPage();
	}

	@When("^Navigate to create account page on mobile$")
	public void navigateToCreateAccountPageOnMobile()
	{
		homePage.mobileNavigateToLoginPage();
		homePage.clickCreateAnAccountButton();
	}
}
