package com.salmon.test.step_definitions.gui.when;


import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.framework.helpers.utils.Credentials;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.step_definitions.gui.DataUnderTest;
import com.salmon.test.step_definitions.gui.ProductCodeProvider;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.*;
import static junit.framework.TestCase.assertTrue;


@Slf4j
public class SavedItemsWhenStepDefs
{
	@Autowired
	private Credentials credentials;
	@Autowired
	private WishListPage wishListPage;
	private WishListPage.ItemsDescription selectedItem;
	private WishListPage.ItemsDescription addedItems;
	@Autowired
	private ProductCodeProvider productCodeProvider;
	@Autowired
	private CheckOutPage checkOutPage;
	@Autowired
	private SignInPage signInPage;
	@Autowired
	private HomePage homePage;
	@Autowired
	private CreateNewAccountPage createNewAccountPage;
	@Autowired
	private PlpPage plpPage;
	@Autowired
	private PdpPage pdpPage;
	@Autowired
	private MyBagPage myBagPage;
	@Autowired
	private DataUnderTest dataUnderTest;
	private String userName = "autotester@example.com";
	private String password = "password123";
	private static final String DEPARTMENT_MENS = "Mens";

//	public SavedItemsWhenStepDefs(
//			final Credentials credentials,
//			final DataUnderTest dataUnderTest,
//			final WishListPage wishListPage,
//			final WishListPage.ItemsDescription selectedItem,
//			final ProductCodeProvider productCodeProvider,
//			final CheckOutPage checkOutPage,
//			final WishListPage.ItemsDescription addedItems,
//			final SignInPage signInPage,
//			final HomePage homePage,
//			final CreateNewAccountPage createNewAccountPage,
//			final PlpPage plpPage, PdpPage pdpPage, MyBagPage myBagPage)
//	{
//		this.credentials = credentials;
//		this.dataUnderTest = dataUnderTest;
//		this.wishListPage = wishListPage;
//		this.selectedItem = selectedItem;
//		this.productCodeProvider = productCodeProvider;
//		this.checkOutPage = checkOutPage;
//		this.addedItems = addedItems;
//		this.signInPage = signInPage;
//		this.homePage = homePage;
//		this.createNewAccountPage = createNewAccountPage;
//		this.plpPage = plpPage;
//		this.pdpPage = pdpPage;
//		this.myBagPage = myBagPage;
//	}

	@When("^viewing the saved (?:items|item) side pane$")
	public void viewing_the_saved_items_side_pane() throws InterruptedException
	{
		assertTrue(wishListPage.selectWishListView());

	}

	@And("^saves a item$")
	public void saves_a_item() throws Exception
	{
		String productCode = Props.getProp("productCode_savedItems");

		if(IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(productCode);
		}
		else
		{
			homePage.searchForProduct(productCode);
		}
		plpPage.saveItemFromList();
	}

	@When("^customer selects (the|most recent|second|third) saved (?:item|items) in side pane$")
	public void customer_selects_saved_item_in_side_pane(String itemInList)
	{
		switch (itemInList)
		{
			default:
				this.selectedItem = wishListPage.selectItemInWishListSidePane(0);
				break;
			case "most recent":
				this.selectedItem = wishListPage.selectItemInWishListSidePane(0);
				break;
			case "second":
				this.selectedItem = wishListPage.selectItemInWishListSidePane(1);
				break;
			case "third":
				this.selectedItem = wishListPage.selectItemInWishListSidePane(2);
				break;
		}
	}

	@When("^customer selects remove from wish list$")
	public void customer_selects_remove_from_wishlist()
	{
		assertTrue("Item is not in wish list ", wishListPage.removeFromWishList(selectedItem.getName().get(0)));
	}

	@When("^has added (items|a item|a one size item) to wish list from product search$")
	public void has_added_items_to_wish_list(String items) throws Throwable
	{
		String p1 = Props.getProp("productCode_stock");
		String p2 = Props.getProp("productCode_OneSize");
		String p3 = Props.getProp("productCode_MultiColour");
		switch (items)
		{
			case "items":
				if(IS_MOBILE)
				{
					homePage.mobileInsertTextAndSearch(p1);
					plpPage.saveItemFromList();
					homePage.mobileInsertTextAndSearch(p2);
					plpPage.saveItemFromList();
					homePage.mobileInsertTextAndSearch(p3);
					plpPage.saveItemFromList();
				}
				else
				{
					homePage.searchForProduct(p1);
					plpPage.saveItemFromList();
					homePage.searchForProduct(p2);
					plpPage.saveItemFromList();
					homePage.searchForProduct(p3);
					plpPage.saveItemFromList();
				}

				break;
			case "a item":
				this.selectedItem = wishListPage.addItemsToWishList("216143041");
				break;
			case "a one size item":
				this.selectedItem = wishListPage.addItemsToWishList("225203872");
				break;
		}
	}

	@When("^deleting the (first|only|all) (?:item|items)$")
	public void deleting_the_item(String deleteItems) throws Throwable
	{
		wishListPage.removeSavedItemsFromLit(deleteItems);
		dataUnderTest.getSavedItems().clear();
	}

	@When("^has added (\\d+) items to wish list from product search$")
	public void has_added_items_to_wish_listV2(Integer n) throws Throwable
	{
		wishListPage.refresh();
		int i = 0;
		do
		{
//            String p1 = dataUnderTest.getARandomProductCodeWithMultipleSizes();
			String p1 = Props.getProp("productCode" + Integer.toString(i + 1));
			if(IS_MOBILE)
			{
				homePage.mobileInsertTextAndSearch(p1);
				plpPage.saveItemFromList();
			}
			else
			{
				addItemToWishListFromSearch(p1);
			}
			i++;

		} while (i < n);
	}

	@When("^search for item with multiple sizes$")
	public void searchForMultiSizeItem() throws Throwable
	{
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(Props.getProp("productCode_stock"));
		}
		else
		homePage.searchForProduct(Props.getProp("productCode_stock"));
//        ProductPojo product = productCodeProvider.getProductWithMultiSizes(dataUnderTest.getAllProductCodes());
//        ProductPojo product =  assertThat(product).describedAs("Can not find the product with multiple sizes in this env").isNotNull();
//        dataUnderTest.setProduct(product);
//        checkOutPage.searchField().sendKeys(product.getData().getCode());
//        checkOutPage.searchButton().click();
	}

	@And("^select first available size of the product$")
	public void select_size_of_the_productFirst()
	{
//        ProductPojo.SizeOption sizeOption = dataUnderTest.getProduct().getAvailableSizeOptions().get(0);
//        dataUnderTest.setSizeOptionSelected(sizeOption);
		checkOutPage.selectSizeOfTheProduct(1);

	}

	@And("click save item button")
	public void item_save_button()
	{
		pdpPage.pause(3000);
		pdpPage.saveItem();
	}
	
	@And("click save item button OMS")
	public void item_save_button_OMS()
	{
		pdpPage.pause(3000);
		pdpPage.saveItemOMS();
	}

	@And("^select second available size of the product$")
	public void select_size_of_the_productFirstSecond()
	{
//        ProductPojo.SizeOption sizeOption = dataUnderTest.getProduct().getAvailableSizeOptions().get(1);
//        dataUnderTest.setSizeOptionSelected(sizeOption);
		checkOutPage.selectSizeOfTheProduct(2);

	}

	@When("^has quickly added items to wish list from product listings$")
	public void has_quickly_added_items_to_wish_list_from_product_listings() throws Throwable
	{
		homePage.navigateToDepartmentFromMegaMenu(DEPARTMENT_MENS);
		this.addedItems = wishListPage.addItemsFromListingsQuickly(3);
	}

	@When("^customer selects (Register|Sign In)$")
	public void customer_selects_Register(String signInType) throws InterruptedException
	{
		wishListPage.switchToWishListPaneIfExists();

		if (IS_MOBILE)
		{
			if (signInType.equals("Register")) {
				wishListPage.waitForAndGetElement(By.xpath("//a[text()='Register']"), 10).click();

			}
			else
			wishListPage.waitForAndGetElement(By.xpath("//a[text()='Sign In']"), 10).click();
		}
		else {
			if (signInType.equals("Register")) {
				wishListPage.selectRegister();
			} else {
				wishListPage.waitForAndGetElement(By.cssSelector(".saveditems-container li a"), 10);
				wishListPage.clickByJavaScriptExecutor(wishListPage.getWebDriver().findElements(By.cssSelector(".saveditems-container li a")).stream().filter(a -> a.getText().equalsIgnoreCase("SIGN IN")).findFirst().get());
			}
			wishListPage.switchToMainPage();
		}
	}

	@When("^customer successfully signs in$")
	public void customer_successfully_signs_in()
	{
		if(WebDriverHelper.DEVICE_NAME.contains("iPhone") ||  WebDriverHelper.DEVICE_NAME.contains("Samsung") || BROWSER.contains("emulator")){
			wishListPage.switchToMainPage();
		}
		log.info("email id -- " + credentials.getEmail());
		signInPage.signInUsingUserNameAndPassword(credentials.getEmail(), credentials.getPassword());
	}

	@When("^customer completes registration form$")
	public void customer_completes_registration_form()
	{
		if(WebDriverHelper.DEVICE_NAME.contains("iPhone") || WebDriverHelper.DEVICE_NAME.contains("Samsung") || BROWSER.contains("emulator")){
			wishListPage.switchToMainPage();
		}
		if (IS_MOBILE)
		{
			homePage.mobileNavigateToLoginPage();
			homePage.clickCreateAnAccountButton();
			this.credentials = createNewAccountPage.createNewAccountWithCorrectDetails();
			homePage.clickMyAccountBreadCrumb();
		}
		else {
			if (wishListPage.getRegister_tabs().isEmpty()) {
				homePage.navigateToCreateNewAccountPageByClickingHeaderLink();
			}

			this.credentials = createNewAccountPage.createNewAccountWithCorrectDetails();
		}
	}

	@And("^customer completed registration")
	public void customer_completes_registration()
	{
		this.credentials = createNewAccountPage.createNewAccountWithCorrectDetails();
	}

	@And("^has added a item to wish list from product listings quick view$")
	public void has_added_a_item_to_wish_list_from_product_listings_quick_view() throws Exception
	{
		if(!IS_MOBILE)
		{
			homePage.navigateToDepartmentFromMegaMenu(DEPARTMENT_MENS);
		}
		this.addedItems = wishListPage.addItemsFromListingsQuickly(2);
	}

	@When("^viewing saved items on full page$")
	public void viewing_saved_items_on_full_page() throws InterruptedException
	{
		wishListPage.selectWishListView();
		assertTrue(wishListPage.selectSavedItemsFullPage());
	}

	@When("^viewing the saved items page$")
	public void viewing_the_saved_items_page() throws InterruptedException
	{
		assertTrue(wishListPage.viewFullPage());
	}


	public void addItemToWishListFromSearch(String prodCode)
	{
		checkOutPage.searchField().sendKeys(prodCode);
		checkOutPage.searchButton().click();
		homePage.selectRandomProduct();
		checkOutPage.selectSizeOfTheProduct();
		pdpPage.saveItem();
	}


	private void getToItemsListings()
	{
		homePage.hoverOverMegaBrand("Mens");
		plpPage.deptHomeLink().click();

	}

    @When("^a product is added to saved items from my bag page$")
    public void aProductIsAddedToSavedItemsFromMyBagPage() {
		String p1 = Props.getProp("productCode_stock");
        homePage.searchForProduct(p1);
        plpPage.clickFirstAvailableProduct();
        pdpPage.selectProductAndMoveToMyBagPage();
        myBagPage.clickMoveToSavedItemsLink();
    }

	@Then("^an alert appears with the following message \"([^\"]*)\"$")
	public void anAlertAppearsWithTheFollowingMessage(String alertMessage)  {
		myBagPage.pause(1200);
		Assert.assertTrue(myBagPage.getAlertMessage().contains(alertMessage));
	}

    @And("^customer hovers on saved icon button$")
    public void customerHoversOnSavedIconButton() throws Throwable {
		pdpPage.hoverToSavedItemsHeartIcon();
    }

    @And("I navigate to saved items page")
	public void navigateToSavedItemsPage(){
		myBagPage.navigateToSavedItemPage();
    }
}
