package com.salmon.test.step_definitions.gui.when;



import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.MyAccountPage;
import com.salmon.test.page_objects.gui.SignInPage;

import com.salmon.test.step_definitions.gui.ProductCodeProvider;

import io.cucumber.datatable.DataTable;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;


/*@ScenarioScoped
@Slf4j*/
public class SingInFeatureWhenStepDefs
{

	@Autowired
	private SignInPage signInPage;
	@Autowired
	private HomePage homePage;
	@Autowired
	private MyAccountPage myAccountPage;

	private String userName = "autotester@example.com";
	private String password = "password123";
//	private Logger log = LoggerFactory.getLogger(SingInFeatureWhenStepDefs.class);
	private Logger log = LogManager.getLogger(SingInFeatureWhenStepDefs.class.getName());

//	public SingInFeatureWhenStepDefs(SignInPage signInPage, HomePage homePage, MyAccountPage myAccountPage)
//	{
//		this.signInPage=signInPage;
//		this.homePage=homePage;
//		this.myAccountPage=myAccountPage;
//
//	}

	@When("^Navigate to singIn page$")
	public void navigateToSingInPage()
	{
		log.info("Navigating to the sign in page");

		if (IS_MOBILE)
		{
			homePage.mobileNavigateToLoginPage();
		}
		else
		//homePage.navigateToCreateNewAccountPageByClickingHeaderLink();
		homePage.navigateToSingInPageByClickingSingInLinkInHeaders();
	}

	@When("^Correct user credentials are entered$")
	public void correctUserCredentialsAreEntered()
	{
		homePage.clickSignIn();
		log.info("Entering the username and password");
		signInPage.signInUsingUserNameAndPassword(userName, password);
	}

	@When("^enter alert script while sign in$")
	public void correctUserCredentialsAreEnteredAlert()
	{
		homePage.clickSignIn();
		signInPage.signInUsingUserNameAndPassword("<script>alert('test');</script>", password);

	}

	@When("^Wrong (.*) entered$")
	public void wrongCredentialsEntered(String credentials)
	{
		switch (credentials)
		{
			case "wrongUser":
				signInPage.signInUsingUserNameAndPassword(userName + "m", password);
				break;
			case "wrongPassword":
				signInPage.signInUsingUserNameAndPassword(userName + "m", password + "45");
				break;
			case "noUser":
				signInPage.signInUsingUserNameAndPassword("", password + "45");
				break;
			case "noPassword":
				signInPage.signInUsingUserNameAndPassword(userName, "");
				break;
			default:
				log.info("Please check the credentials you have provided -- " + credentials);
		}

	}


	@Then("^Validate singIn page$")
	public void validateSingInPage(DataTable text)
	{
		List<String> displayedTextInSingInBox = signInPage.getSignInboxLabels();
		List<String> expectedLabels = new ArrayList<>();
		expectedLabels.add(text.cells().get(0).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.cells().get(1).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.cells().get(2).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.cells().get(3).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.cells().get(4).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.cells().get(5).toString().replace("]", "").replace("[", ""));
		Assert.assertEquals(signInPage.normaliseList(expectedLabels), signInPage.normaliseList(displayedTextInSingInBox));
	}

	@Then("^User should navigate to my account$")
	public void userShouldNavigateToMyAccount()
	{
		Assert.assertEquals(myAccountPage.getMyAccountHeaderLabel(), "My Account");
	}

	@Then("^User should see error message for wrong (.*)$")
	public void userShouldSeeErrorMessageForWrongCredentials(String credentials)
	{
		String errorMessageDisplayed = null;
		String expectedErrorMessage = "Your email or password was incorrect";
		switch (credentials)
		{
			case "wrongUser":
				errorMessageDisplayed = signInPage.getEmailOrPasswordWasIncorrectMessage();
				break;
			case "wrongPassword":
				errorMessageDisplayed = signInPage.getEmailOrPasswordWasIncorrectMessage();
				break;
			case "noUser":
				errorMessageDisplayed = signInPage.getPleaseEnterAnEmailMessage();
				expectedErrorMessage = "Please enter an email";
				break;
			case "noPassword":
				errorMessageDisplayed = signInPage.getPleaseEnterPasswordMessage();
				expectedErrorMessage = "Please enter your password";
				break;
		}
		Assert.assertEquals(expectedErrorMessage, errorMessageDisplayed);
	}

}
