package com.salmon.test.step_definitions.gui.then;



public class SignInFeatureThenStepDefs
{
	/*//@Inject
	private SignInPage signInPage;

	//@Inject
	private MyAccountPage myAccountPage;

	public void SignInFeatureThenStepDefs(

	@Then("^Validate singIn page$")
	public void validateSingInPage(DataTable text)
	{
		List<String> displayedTextInSingInBox = signInPage.normaliseList(signInPage.getSignInboxLabels());
		List<String> expectedLabels = new ArrayList<>();
		expectedLabels.add(text.raw().get(0).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.raw().get(1).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.raw().get(2).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.raw().get(3).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.raw().get(4).toString().replace("]", "").replace("[", ""));
		expectedLabels.add(text.raw().get(5).toString().replace("]", "").replace("[", ""));
		Assert.assertEquals(signInPage.normaliseList(expectedLabels), displayedTextInSingInBox);
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
*/
}
