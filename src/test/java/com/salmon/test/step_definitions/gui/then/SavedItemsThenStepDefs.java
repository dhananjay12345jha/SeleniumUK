package com.salmon.test.step_definitions.gui.then;

import org.junit.Assert;

import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.page_objects.gui.MySavedItemsPage;
import com.salmon.test.page_objects.gui.PdpPage;
import com.salmon.test.page_objects.gui.SignInPage;
import com.salmon.test.page_objects.gui.WishListPage;
import com.salmon.test.step_definitions.gui.DataUnderTest;

import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;
import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SavedItemsThenStepDefs
{

	private DataUnderTest dataUnderTest;
	@Autowired
	private PdpPage pdpPage;
	@Autowired
	private WishListPage wishListPage;
	private WishListPage.ItemsDescription addedItems;
	private WishListPage.ItemsDescription selectedItem;
	@Autowired
	private SignInPage  signInPage;
	@Autowired
	private MySavedItemsPage mySavedItemsPage;

//	public SavedItemsThenStepDefs(final WishListPage wishListPage,
//	                              final WishListPage.ItemsDescription addedItems,
//	                              final WishListPage.ItemsDescription selectedItem,
//	                              final DataUnderTest dataUnderTest,
//	                              final PdpPage pdpPage, final SignInPage signInPage,
//	                              MySavedItemsPage mySavedItemsPage)
//	{
//		this.wishListPage = wishListPage;
//		this.addedItems = addedItems;
//		this.selectedItem = selectedItem;
//		this.dataUnderTest = dataUnderTest;
//		this.pdpPage = pdpPage;
//		this.signInPage = signInPage;
//		this.mySavedItemsPage = mySavedItemsPage;
//	}





	@Then("^(\\d+) saved items will be present$")
	public void items_will_be_presentv2(String n) throws Throwable {
		wishListPage.pause(1300);
		WishListPage.ItemsDescription savedItems = wishListPage.savedItems();
//        assertEquals(dataUnderTest.getSavedItems().stream().map(a -> a.getName().trim()).sorted().collect(Collectors.toList()), savedItems.getName().stream().map(s -> s.trim()).sorted().collect(Collectors.toList()));
		assertEquals(n, savedItems.getItemCount());
	}


	@Then("no (?:items|item) will be present$")
	public void no_items_will_be_present() {
//        assertEquals("0", wishListPage.savedItemsBagCount());
		assertTrue(wishListPage.noSavedItems());
		//wishListPage.refresh();
	}
	@Then("^(no|a) option to sign in will be present$")
	public void no_option_to_sign_in_will_be_present(String presence) {
		if (presence.equals("no")) {
			assertFalse(wishListPage.registerOptionsPresence());
		} else {
			assertTrue(wishListPage.registerOptionsPresence());
		}
	}

	@Then("^saved (?:items|item) will be present$")
	public void items_will_be_present() throws Throwable {
//        WishListPage.ItemsDescription savedItems = wishListPage.savedItems();
//        assertEquals(Lists.reverse(this.addedItems.getName()), savedItems.getName());
//        assertEquals(Lists.reverse(this.addedItems.getPrice()), savedItems.getPrice());
//        boolean arraycheck = true;
//        for (int i = 0; i < this.addedItems.getSize().size(); i++) {
//            arraycheck = this.addedItems.getSize().get(i).equalsIgnoreCase("one size");
//        }
//        if (this.addedItems.getSize().size() > 0 && !arraycheck) {
//            List<String> wishListSizes = Lists.reverse(this.addedItems.getSize());
//            wishListSizes.forEach(sizeOfItem -> {
//                assertEquals(("[" + sizeOfItem.trim() + "]"), savedItems.getSize().toString());
//            });
//        }
//        assertEquals(this.addedItems.getItemCount(), savedItems.getItemCount());

		if (IS_MOBILE)
		{
			assertTrue(wishListPage.checkSavedItemsPresentOnMobile()>0);
		}
		else
			assertTrue(!wishListPage.getItemName().isEmpty());
	}

	@Then("^saved item is displayed in full$")
	public void saved_item_is_displayed() {
		assertTrue(pdpPage.productDetailsPage().isDisplayed());
//        assertEquals(this.selectedItem.getName(), wishListPage.productDetail().getName());
//        assertEquals(this.selectedItem.getPrice(), wishListPage.productDetail().getPrice());
	}

      @Then("^customer is taken to the sign in page$")
    public void customer_is_navigated_to_the_registration_page() {
	    if(WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
		   WebDriverHelper.DEVICE_NAME.contains("iPad") ||
			    WebDriverHelper.DEVICE_NAME.contains("Samsung") || BROWSER.contains("emulator")){
            wishListPage.switchToMainPage();
        }
        assertTrue(signInPage.logInSection().isDisplayed());
    }

	@Then("^with (create an|i've got an) account view$")
	public void with_create_an_account_view(String view) {
		if (view.equals("create an")) {
			assertTrue(signInPage.registerForm().isDisplayed());
		} else {
			assertTrue(signInPage.loginForm().isDisplayed());
		}
	}

	@Then("^saved items page is displayed$")
	public void saved_items_page_is_displayed() {
		assertTrue(wishListPage.savedItemsPage());
	}

	@Then("^product should be displayed in saved items$")
	public void productShouldBeDisplayedInSavedItems() {
		assertTrue(mySavedItemsPage.getMySavedItemList());
	}

	@Then("^add to saved items message appears$")
	public void addToSavedItemsMessageAppears() {
		assertTrue(pdpPage.isAddToSavedMessageDisplayed());
	}

	@Then("^remove from saved items message appears$")
	public void removeFromSavedItemsMessageAppears() {
		assertTrue(pdpPage.isRemoveFromSavedMessageDisplayed());

	}

	@Then("^The saved items page urgency warning says \"([^\"]*)\"$")
	public void theSavedItemsPageUrgencyWarningSays(final String expectedMessage) throws InterruptedException
	{
		Thread.sleep(2000);
		Assert.assertEquals(expectedMessage, mySavedItemsPage.getSavedItemsUrgencyWarning().getText());
	}


	@Then("^The saved items page has no urgency warning$")
	public void theSavedItemsPageHasNoUrgencyWarning()
	{
		Assert.assertNull(pdpPage.getSavedItemsUrgencyWarning());
	}
}
