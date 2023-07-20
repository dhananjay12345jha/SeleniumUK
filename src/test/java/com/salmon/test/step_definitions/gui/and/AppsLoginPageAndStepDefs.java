package com.salmon.test.step_definitions.gui.and;

import com.salmon.test.page_objects.gui.BackofficePage;

import com.salmon.test.page_objects.gui.SmartEditPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;


public class AppsLoginPageAndStepDefs
{

	@Autowired
	private BackofficePage backofficePage;
	@Autowired
	private SmartEditPage smartEditPage;

//	public AppsLoginPageAndStepDefs(BackofficePage backofficePage,
//	                                SmartEditPage smartEditPage)
//	{
//		this.backofficePage = backofficePage;
//		this.smartEditPage = smartEditPage;
//	}

	@When("^login with username \"([^\"]*)\" and password \"([^\"]*)\" on backoffice$")
	public void loginWithUserNameAndPasswordOnBackoffice(String userName, String password)
	{
		backofficePage.signInUsingUserNameAndPasswordOnBackoffice(userName, password);
	}

	@When("^login with username \"([^\"]*)\" and password \"([^\"]*)\" on smartedit$")
	public void loginWithUserNameAndPasswordOnSmartEdit(String userName, String password)
	{
		smartEditPage.signInUsingUserNameAndPasswordOnSmartEdit(userName, password);
	}

	@Then("^I have successfully logged in backoffice$")
	public void iHaveSuccessfullyLoggedIn() {
		Assert.assertTrue(backofficePage.searchInTypeTreeIsDisplayed());
	}

	@Then("^I have successfully logged in smartedit")
	public void iHaveSuccessfullyLoggedInSmartEdit() {
		Assert.assertTrue(smartEditPage.searchDropdownBoxDisplayed());
	}
}
