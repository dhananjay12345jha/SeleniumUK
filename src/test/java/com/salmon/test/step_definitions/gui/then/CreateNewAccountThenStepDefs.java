package com.salmon.test.step_definitions.gui.then;

import com.salmon.test.page_objects.gui.CreateNewAccountPage;
import com.salmon.test.page_objects.gui.MyAccountPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CreateNewAccountThenStepDefs {

	@Autowired
    private CreateNewAccountPage createNewAccountPage;
	@Autowired
    private MyAccountPage myAccountPage;

//    public CreateNewAccountThenStepDefs(CreateNewAccountPage createNewAccountPage, MyAccountPage myAccountPage) {
//        this.createNewAccountPage = createNewAccountPage;
//        this.myAccountPage = myAccountPage;
//    }

    @Then("^Validate create your account form$")
    public void validateCreateYourAccountForm(DataTable text) {
        List<String> displayedTextInCreateNewAccountForm = createNewAccountPage.getCreateNewAccountFormLabels();
        List<String> expectedLabels = new ArrayList<>();
        for (int i = 0; i < text.cells().size(); i++) {
            expectedLabels.add(text.cells().get(i).toString().replace("]", "").replace("[", ""));
        }
        Assert.assertEquals(expectedLabels.toString().replaceAll("\\s+", " "), displayedTextInCreateNewAccountForm.toString().replaceAll("\\s+", " "));
    }

    @Then("^new account should be create and user should navigate to my account page$")
    public void newAccountShouldBeCreateAndUserShouldNavigateToMyAccountPage() {
        Assert.assertEquals(myAccountPage.getMyAccountHeaderLabel(), "My Account");
    }

    @Then("^user is taken to contact preferences page$")
    public void userIsTakenToContactPreferencesPage() throws Throwable {
        Assert.assertEquals(myAccountPage.getManageContactPreferencesHeader(), "Manage Contact Preferences");
    }
}
