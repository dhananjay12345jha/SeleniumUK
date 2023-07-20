package com.salmon.test.step_definitions.gui.then;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.CheckOutPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.MyAccountPage;
import com.salmon.test.page_objects.gui.MyAddressPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
public class MyAccountThenStepDefs
{
	@Autowired
	private HomePage homePage;
	@Autowired
	private MyAddressPage myAddressPage;
	@Autowired
	private MyAccountPage myAccountPage;
	@Autowired
	private CheckOutPage myCheckOutPage;

//	public MyAccountThenStepDefs(HomePage homePage, final MyAddressPage myAddressPage, final MyAccountPage myAccountPage, final  CheckOutPage myCheckOutPage)
//	{
//		this.homePage = homePage;
//		this.myAddressPage = myAddressPage;
//		this.myAccountPage = myAccountPage;
//		this.myCheckOutPage = myCheckOutPage;
//	}

	@Then("^I should be logged out from the account$")
	public void iShouldBeLoggedOutFromTheAccount()
	{
		Assert.assertTrue(homePage.getLogoutMessageDisplayed());
	}

	@Then("^A pop up with delete message and option with delete or cancel displayed$")
	public void aPopUpWithDeleteMessageAndOptionWithDeleteOrCancelDisplayed(DataTable dataTable)
	{
		Assert.assertTrue("Not able to find the delete address popup", myAddressPage.getDeleteAddressPopup());
		List<String> displayedString = myAddressPage.getDeleteAddressPopupLabels();
		List<String> expectedLabels = new ArrayList<>();
		for (int i = 0; i < dataTable.cells().size(); i++)
		{
			expectedLabels.add(dataTable.cells().get(i).toString().replace("]", "").replace("[", ""));
		}
		Assert.assertEquals(myAddressPage.normaliseList(expectedLabels), myAddressPage.normaliseList(displayedString));
	}

	@Then("^Address should be removed from my addresses$")
	public void addressShouldBeRemovedFromMyAddresses()
	{
		int numberOfAddresses = myAddressPage.numberOfAddresses;
		int numberOfAddressesDisplayedAfterDelete = myAddressPage.getNumberOfAddresses();
		Assert.assertTrue("The number of address displayed is equals to the number addresses before deleted", numberOfAddressesDisplayedAfterDelete != numberOfAddresses);
	}

	@Then("^The address should be displayed in the my addresses$")
	public void theAddressShouldBeDisplayedInTheMyAddresses()
	{
		int numberOfAddresses = myAddressPage.numberOfAddresses;
		int numberOfAddressesDisplayedAfterDelete = myAddressPage.getNumberOfAddresses();
		Assert.assertTrue("The number of address displayed is not greater than before adding" + numberOfAddressesDisplayedAfterDelete + "--" + numberOfAddresses, numberOfAddressesDisplayedAfterDelete != numberOfAddresses);
	}

	@Then("^User should navigate to (.*) links$")
	public void userShouldNavigateToIconsLinks(String headerLabel)
	{
		myAccountPage.pause(2000);
		if (headerLabel.equalsIgnoreCase("MY SAVED ITEMS"))
		{
			assertThat(myAccountPage.getHeaderH3Label().get(0).contains("My Saved Items"));
		}
		else
		{
			assertThat(headerLabel.compareToIgnoreCase(myAccountPage.getHeaderLabel().get(0)));
		}
	}

	@Then("^My Account payment cards section (.*) have card with ending (\\d+)$")
	public void myAccountPaymentCardsSectionShouldHaveCardWithEnding(String should, String cardEndDigits) throws Throwable
	{
		homePage.clickUserAccountIcon();
		myAccountPage.clickMyPaymentCards();
		if (should.equals("should"))
		{
			assertThat(myAccountPage.isSavedCardExist(cardEndDigits)).isTrue();
		}
		else
		{
			assertThat(myAccountPage.isSavedCardExist(cardEndDigits)).isFalse();
		}

	}

	@Then("^My Account store cards section (.*) have card with ending (\\d+)$")
	public void myAccountStoreCardsSectionShouldHaveCardWithEnding(String should,String arg0) throws Throwable
	{
		homePage.clickUserAccountIcon();
		myAccountPage.clickMyStoreCards();
		if (should.equals("should"))
		{
			assertThat(myAccountPage.isSavedCardExist(arg0)).isTrue();
		}
		else
		{
			assertThat(myAccountPage.isSavedCardExist(arg0)).isFalse();
		}
	}


	@Then("^My Account store cards section (.*) have card with ending \"([^\"]*)\" on mobile$")
	public void myAccountStoreCardsSectionShouldHaveCardWithEndingOnMobile(String should,String arg0) throws Throwable
	{
		homePage.navigateToMyAccountFromHeaderOnMobile();
		myAccountPage.clickMyStoreCards();
		if (should.equals("should"))
		{
			assertThat(myAccountPage.isSavedCardExist(arg0)).isTrue();
		}
		else
		{
			assertThat(myAccountPage.isSavedCardExist(arg0)).isFalse();
		}
	}

	@Then("^My Account payment cards section (.*) have card with ending \"([^\"]*)\" on mobile$")
	public void myAccountPaymentCardsSectionShouldHaveCardWithEndingOnMobile(String should, String cardEndDigits)
	{
		homePage.navigateToMyAccountFromHeaderOnMobile();
		myAccountPage.clickMyPaymentCards();
		if (should.equals("should"))
		{
			assertThat(myAccountPage.isSavedCardExist(cardEndDigits)).isTrue();
		}
		else
		{
			assertThat(myAccountPage.isSavedCardExist(cardEndDigits)).isFalse();
		}
	}

	@Then("^User should navigate to my account page and see links to$")
	public void userShouldNavigateToMyAccountPageAndSeeLinksTo(List<String> contents)
	{
		for (String content : contents)
		{
			assertThat(myAccountPage.selectMyAccountLinksLabel(content).equals(content));
		}
	}

	@And("^Delete card$")
	public void deleteCard() throws InterruptedException
	{
		myAccountPage.deleteCard();
	}

	@Then("^Card is deleted from my account page$")
	public void assertSavedCardIsDeleted() throws InterruptedException
	{
		assertThat(myAccountPage.assertSavedCardIsDeleted()).isTrue();
	}

	@Then("^number of addresses should be (\\d+)$")
	public void numberOfAddressesShouldBe(int count)
	{
		assertThat(myAddressPage.getNumberOfAddresses()).isEqualTo(count);
	}

	@Then("^delivery edit form should be displayed with mandatory mobile number for (.*)$")
	public void deliveryEditFormShouldBeDisplayedWithMandatoryMobileNumber(String deliveryType)
	{
		switch (deliveryType){
			case "HOME DELIVERY":
				assertThat(myCheckOutPage.deliveryEditFormWithMandatoryMobileNumberDisplayed()).isTrue();
				break;
			case "COLLECTION":
				assertThat(myCheckOutPage.collectionDeliveryEditFormWithMandatoryMobileNumberDisplayed()).isTrue();
				break;
		}

	}
}
