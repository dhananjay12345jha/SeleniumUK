package com.salmon.test.step_definitions.gui.when;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;
public class PersonalDetailsWhenStepDefs {
	@Autowired
	private MyAccountPage myAccountPage;
	@Autowired
	private PersonalDetailsPage personalDetailsPage;
	@Autowired
	private HomePage homePage;
	@Autowired
	private SignInPage signInPage;
	private String userName = "autotester@example.com";
	private String password = "password123";
	@Autowired
	private PdpPage productDetailsPage;
	@Autowired
	private MyBagPage myBagPage;
	@Autowired
	private CheckOutPage checkOutPage;


//	public PersonalDetailsWhenStepDefs(final MyAccountPage myAccountPage,
//									   final PersonalDetailsPage personalDetailsPage,
//									   final HomePage homePage,
//									   final SignInPage signInPage, final PdpPage productListPage, final MyBagPage myBagPage, final CheckOutPage checkOutPage
//	) {
//		this.myAccountPage = myAccountPage;
//		this.personalDetailsPage = personalDetailsPage;
//		this.homePage = homePage;
//		this.signInPage = signInPage;
//		this.productDetailsPage = productListPage;
//		this.myBagPage = myBagPage;
//		this.checkOutPage = checkOutPage;
//
//	}

	@When("^Personal Details link is clicked$")
	public void personalDetailsLinkIsClicked() {
		myAccountPage.selectMyAccountLinks("PERSONAL DETAILS");

	}

	@And("^Customer signs in and navigates to personal details page to update$")
	public void customerSignsInAndNavigatesToPersonalDetailsPageToUpdate() {
		homePage.navigateToSingInPageByClickingSingInLinkInHeaders();
		signInPage.signInUsingUserNameAndPassword(Props.getProp("myAccountPersonalDetailsUserEmail"), Props.getProp("password"));
		myAccountPage.selectMyAccountLinks("PERSONAL DETAILS");

	}

	@When("^updated (.*) in and (.*) and save changes$")
	public void updatedAFieldAndClickedSaveChanges(String field, String button) {
		personalDetailsPage.clickPersonalDetailsPageButton(button);
		personalDetailsPage.updatePersonalDetailsField(field, "SAVE CHANGES");


	}

	@When("^Navigate to \"([^\"]*)\" pages$")
	public void navigate_to_pages(String pages) throws Exception {
		switch (pages) {
			case "Personal_Details":
				if(BROWSER.equals("firefox")){
					homePage.clickUserAccountIcon();
					myAccountPage.clickMyPersonalDetails();
				}
					UrlBuilder.navigateToPersonalDetailsPage();
				break;
			case "Checkout":
				homePage.searchForProductUsingRandomProductCode();
				homePage.selectRandomProduct();
				productDetailsPage.selectProductAndMoveToMyBagPage();
				checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
				myBagPage.clickPrimarySecureCheckoutButton();
				checkOutPage.selectDelivery().click();
				break;
			case "Checkout mobile":
				homePage.mobileInsertTextAndSearch(Props.getProp("productCode_stock"));
				homePage.selectRandomProduct();
				productDetailsPage.selectProductAndMoveToMyBagPage();
				checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
				myBagPage.clickPrimarySecureCheckoutButton();
				checkOutPage.selectDelivery().click();
				break;
		}

	}

}
