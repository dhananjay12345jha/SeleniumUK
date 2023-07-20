package com.salmon.test.step_definitions.gui;

import com.salmon.test.page_objects.gui.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gates on 04/05/18.
 */
public class RTBFStepDefinitions {

	@Autowired
    private RTBFPage rtbfPage;
	@Autowired
    private HelpPage helpPage;
	@Autowired
    private SecurityPage securityPage;
	@Autowired
    private PersonalDetailsPage personalDetailsPage;

//    public RTBFStepDefinitions(final RTBFPage rtbfPage,
//                               final HelpPage helpPage,
//                               final SecurityPage securityPage,
//                               final PersonalDetailsPage personalDetailsPage) {
//        this.personalDetailsPage = personalDetailsPage;
//        this.securityPage = securityPage;
//        this.rtbfPage = rtbfPage;
//        this.helpPage = helpPage;
//    }

    @And("^From the My Data Privacy Rights Section I click on Find Out More button$")
    public void fromTheMyDataPrivacyRightsSectionIClickOnFindOutMoreButton() throws Throwable {
        personalDetailsPage.clickFindOutMore();
    }

    @And("^I navigate to RTBF page and fill in form$")
    public void iNavigateToRTBFPageAndFillInForm() throws Throwable {
        rtbfPage.completeDetails();
    }

    @And("^I click on SecurityPrivacyCookies link$")
    public void iClickOnSecurityPrivacyCookiesLink() throws Throwable {
        helpPage.clickSecurityPrivacyCookiesLink();
    }


    @Then("^Check your Inbox section is present$")
    public void checkYourInboxSectionIsPresent() throws Throwable {
        Assert.assertEquals(rtbfPage.getCheckYourInboxTitle(), "Check your Inbox!");
    }

    @Then("^My Data Privacy Rights section is present$")
    public void myDataPrivacyRightsSectionIsPresent() throws Throwable {
        personalDetailsPage.myDataPrivacyRightsSectionExists();
    }
}
