package com.salmon.test.step_definitions.gui.and;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.CreateNewAccountPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.LoginPage;
import com.salmon.test.page_objects.gui.SignInPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;


public class NewLookLoginPageAndStepDefs
{
	@Autowired
	private HomePage homePage;
	@Autowired
	private SignInPage signInPage;
	@Autowired
	private LoginPage loginPage;
	@Autowired
	private CreateNewAccountPage createNewAccountPage;

//	public NewLookLoginPageAndStepDefs(HomePage homePage,
//									   SignInPage signInPage,
//									   LoginPage loginPage,
//									   CreateNewAccountPage createNewAccountPage)
//	{
//		this.homePage=homePage;
//		this.signInPage=signInPage;
//		this.loginPage=loginPage;
//		this.createNewAccountPage = createNewAccountPage;
//	}

	@And("^Customer signed in$")
	public void customerSignedInHasAnAccount()
	{
		homePage.navigateToSingInPageByClickingSingInLinkInHeaders();
		signInPage.signInUsingUserNameAndPassword(createNewAccountPage.getUserName(), createNewAccountPage.getNewPassword());

	}

    @When("^user click login link$")
    public void userClickLoginLink() throws Throwable {
		loginPage.clickLoginLink();
    }

	@When("^login with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void loginWithUserNameAndPassword(String userName, String password) {
		if(IS_MOBILE){
			loginWithUserNameAndPasswordOnMobile(userName,password);
		}
		else{
			homePage.navigateToSingInPageByClickingSingInLinkInHeaders();
			signInPage.signInUsingUserNameAndPassword(Props.getProp(userName),Props.getProp(password));
		}
	}

	@When("^login with username \"([^\"]*)\" and password \"([^\"]*)\" on mobile$")
	public void loginWithUserNameAndPasswordOnMobile(String userName, String password) {
		homePage.mobileNavigateToLoginPage();
		signInPage.signInUsingUserNameAndPassword(Props.getProp(userName),Props.getProp(password));
	}

	@When("^login with username \"([^\"]*)\" and password \"([^\"]*)\" non system property$")
	public void loginWithUserNameAndPasswordNonSystemProperty(String userName, String password) {
		homePage.navigateToSingInPageByClickingSingInLinkInHeaders();
		signInPage.signInUsingUserNameAndPassword(userName,password);
	}

	@When("^login with username \"([^\"]*)\" and password \"([^\"]*)\" non system property on mobile$")
	public void loginWithUserNameAndPasswordNonSystemPropertyOnMobile(String userName, String password) {
		homePage.mobileNavigateToLoginPage();
		signInPage.signInUsingUserNameAndPassword(userName,password);
	}
}
