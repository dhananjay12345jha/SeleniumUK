package com.salmon.test.step_definitions.gui.then;

import com.salmon.test.page_objects.gui.PersonalDetailsPage;
import com.salmon.test.page_objects.gui.SignInPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class PersonalDetailsThenStepDefs
{
//	public PersonalDetailsThenStepDefs(final PersonalDetailsPage personalDetailsPage,final SignInPage signInPage )
//	{
//		this.personalDetailsPage = personalDetailsPage;
//		this.signInPage = signInPage;
//	}

	@Autowired
	private PersonalDetailsPage personalDetailsPage;
	@Autowired
	private SignInPage signInPage;

	@Then("^User should see labels$")
	public void userShouldSeePersonalDetailsLabel(DataTable dataTable){
		for (List<String> elementItem : dataTable.cells()) {
			assertThat(personalDetailsPage.selectMyAccountPersonalDetailsLabel(elementItem.toString()).equals(elementItem));
		}
	}

	@Then("^User should see buttons$")
	public void userShouldSeeButtonsPersonalDetailsButtons(DataTable button){
		assertThat(personalDetailsPage.selectMyAccountPersonalDetailsButtons(button.toString()).equals(button.toString()));
	}

	@Then("^Personal details (.*) should update$")
	public void personalDetailsFieldShouldUpdate(String field)
	{
		assertThat(personalDetailsPage.getGeneratedName().contains(personalDetailsPage.selectMyAccountPersonalDetailsValues(field).get(0)));
	}

	@Then("^Newsletter opt-in checkbox should not be present$")
	public void newsletter_opt_in_checkbox_should_not_be_present() throws Exception {

		assertFalse(signInPage.newsLetteroOtincCheckboxExists());
	}

	@Then("^Newsletter opt-in checkbox should be present$")
	public void newsletter_opt_in_checkbox_should_be_present() throws Exception {

		assertTrue(signInPage.newsLetteroOtincCheckboxExists());
	}

}
