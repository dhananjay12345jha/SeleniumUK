package com.salmon.test.step_definitions.gui.then;

import com.salmon.test.page_objects.gui.MySavedCollectionPage;
import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class MyAccountSavedLocationThenStepDefs
{
	@Autowired
	private MySavedCollectionPage mySavedCollectionLocation;

//	public MyAccountSavedLocationThenStepDefs(final MySavedCollectionPage mySavedCollectionLocation)
//	{
//		this.mySavedCollectionLocation = mySavedCollectionLocation;
//	}


	@Then("^saved location should be empty$")
	public void savedLocationShouldBeEmpty()
	{
		assertThat(mySavedCollectionLocation.getHeaderLabel().contains("My Collection Locations"));
	}

	@Then("^location should be displayed in my collection location page$")
	public void locationShouldBeDisplayedInMyCollectionLocationPage()
	{
		assertThat(mySavedCollectionLocation.getCollectionLocation());
	}
}
