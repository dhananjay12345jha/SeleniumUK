package com.salmon.test.step_definitions.mobile;

import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.mobile.MobileContactsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import org.springframework.beans.factory.annotation.Autowired;

public class MobileSteps {

	@Autowired
    private MobileContactsPage mobileContactsPage;

    public MobileSteps(MobileContactsPage mobileContactsPage) {
        this.mobileContactsPage = mobileContactsPage;
    }

    @Given("^i click on Add New Contacts Button$")
    public void i_click_on_Add_New_Contacts_Button() throws Throwable {
        mobileContactsPage.clickOnAddContact();
    }

    @When("^i add name email and click on Save$")
    public void i_add_name_email_and_click_on_Save() throws Throwable {
        mobileContactsPage.getContactFormFields().get(0).sendKeys(RandomGenerator.randomAlphabetic(5));
        mobileContactsPage.getContactFormFields().get(2).sendKeys(RandomGenerator.randomEmailAddress(8));
        //mobileContactsPage.swipe(100, 500, 100, 100, 2);
        mobileContactsPage.saveContact();
    }
}
