package com.salmon.test.step_definitions.gui;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;


import com.salmon.test.framework.helpers.UrlBuilder;

import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.asserts.SoftAssert;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.models.gui.CarouselModel;
import com.salmon.test.models.gui.CheckoutModel;
import com.salmon.test.page_objects.gui.CheckOutPage;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.HomePageOld;
import com.salmon.test.page_objects.gui.MyBagPage;
import com.salmon.test.page_objects.gui.NewLookHelper;
import com.salmon.test.page_objects.gui.PdpPage;
import com.salmon.test.page_objects.gui.PlpPage;
import com.salmon.test.page_objects.gui.PlpPageOld;
import com.salmon.test.page_objects.gui.StoreLocatorPage;
import com.salmon.test.page_objects.gui.WishListPage;
import com.salmon.test.page_objects.pojos.PdpCarouselItem;
import com.salmon.test.properties.AlertsProperties;
import com.salmon.test.properties.ProductProperties;
import com.salmon.test.utils.SeleniumUtils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;


import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;
import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Slf4j
public class PdpStepDefinitions
{

	public static String colorPdp;
	public static String sizePdp;
	public static String productNamePdp;
	@Autowired
	private CheckOutPage checkOutPage;
	@Autowired
	private HomePageOld homePageOld;
	@Autowired
	private HomePage homePage;
	@Autowired
	private PdpPage pdpPage;
	private CarouselModel carouselModel = new CarouselModel();
	private CheckoutModel checkoutModel = new CheckoutModel();
	@Autowired
	private PlpPage plpPage;
	@Autowired
	private PlpPageOld plpPageOld;
	@Autowired
	private StoreLocatorPage storeLocatorPage;
	@Autowired
	private MyBagPage myBagPage;
	@Autowired
	private WishListPage wishListPage;

//	public PdpStepDefinitions(CheckOutPage checkOutPage,
//	                          HomePageOld homePageOld,
//	                          PdpPage pdpPage,
//	                          PlpPage plpPage,
//	                          PlpPageOld plpPageOld,
//	                          HomePage homePage,
//	                          StoreLocatorPage storeLocatorPage,
//	                          MyBagPage myBagPage,
//	                          WishListPage wishListPage)
//	{
//		this.checkOutPage = checkOutPage;
//		this.homePageOld = homePageOld;
//		this.pdpPage = pdpPage;
//		this.plpPage = plpPage;
//		this.plpPage = plpPage;
//		this.homePage = homePage;
//		this.storeLocatorPage = storeLocatorPage;
//		this.myBagPage = myBagPage;
//		this.wishListPage = wishListPage;
//	}

	@Then("^any of the Size variants set up against the product have \"([^\"]*)\",\"([^\"]*)\" should be available$")
	public void any_of_the_Size_variants_set_up_against_the_product_have_should_be_available(String outOfStock,
	                                                                                         String lowStock)
	{
		checkOutPage.selectSize().click();
		assertTrue(pdpPage.outOfStockDisabled().getAttribute("label").contains(outOfStock));
		checkOutPage.selectSizeOfTheProductPrecisely(lowStock);
		assertTrue(checkOutPage.selectSize().getText().contains(lowStock));
	}

	@Then("^pdp size options should contain a size with note '(.*)'$")
	public void assertSizeOption(String expString)
	{
		boolean result = false;
		checkOutPage.clickByJavaScriptExecutor(checkOutPage.selectSize());
		for (WebElement e : new Select(checkOutPage.selectSize()).getOptions())
		{
			if (e.getText().contains(expString))
			{
				result = true;
			}
		}

		assertThat(result).describedAs("can not find exp note in size options").isTrue();
	}

	@Then("^switch to quick cart view$")
	public void switch_to_quick_cart_view()
	{
		checkOutPage.switchToCheckoutIframe();
	}

	@And("^click on quick view link$")
	public void click_on_quick_view_link()
	{
		pdpPage.scrollToBottom();
		pdpPage.clickQuickViewLink();

	}

	@Then("^i should see \"([^\"]*)\" in the full cart page$")
	public void i_should_see_in_the_full_cart_page(String message)
	{

		assertTrue(checkOutPage.fullCartLowInStock().getText().contains(message));
	}

	@And("^the product with no stock should be disabled$")
	public void the_product_with_no_stock_should_be_disabled()
	{
		checkOutPage.selectSizeFromFullCartPage().click();
		assertTrue(checkOutPage.returnAttributesOfUnavailableSize()
				.contains("nl-select__item ui-select-choices-row ng-scope nl-select__item--disabled"));
	}

	@When("^i navigate to PDP page with no stock$")
	public void i_navigate_to_PDP_page_with_no_stock()
	{

		checkOutPage.getWebDriver()
				.navigate()
				.to("TBD" +
						"/UK-Store-Category/UK-Website-Root/UK-Teens-Department/Shoes/915-CHESHIRE---BRODERIE-PUMP/p/1927571_88");
	}

	@Then("^you should see a out of stock message , the size drop down is not present$")
	public void you_should_see_a_out_of_stock_message_the_size_drop_down_is_not_present()
	{
		assertTrue(checkOutPage.outOfStockText().getText().contains("Sorry this product is out of stock."));
		assertFalse(checkOutPage.sizeDropDownPresent());

	}

	@Then("^I will see the following information related to the Colour-level product$")
	public void I_will_see_the_following_information_related_to_the_Colour_level_product()
	{
		assertTrue(pdpPage.breadCrumbTrail().isDisplayed());
		assertTrue(pdpPage.productPrice().isDisplayed());
		assertTrue(pdpPage.productDescritption_Cms().isEnabled());

	}


	@Then("^I will see the following information related to the Colour-level product on quick view$")
	public void I_will_see_the_following_information_related_to_the_Colour_level_product_on_quick_view()
	{
		List<String> colorsAvailable;

		assertTrue(pdpPage.productPrice().isDisplayed());
		assertTrue(pdpPage.colorSwatchesOnQuickView().isDisplayed());
		assertTrue(pdpPage.productName().isDisplayed());

		//check multiple colors displayed
		colorsAvailable = pdpPage.getColorsFromTheSwatches();
		assertEquals(colorsAvailable.size(),3);

		///need to impelemt meida gallery is present
	}

	@And("^select different color from the swatch$")
	public void select_different_color_from_the_swatch()
	{
		pdpPage.colorSwatchesOnQuickView().click();
	}

	@Then("^I should be navigated to the full PDP page$")
	public void I_should_be_navigated_to_the_full_PDP_page()
	{
		pdpPage.pause(2000);
		assertTrue(pdpPage.breadCrumbTrail().isDisplayed());
		assertTrue(pdpPage.productPrice().isDisplayed());
		assertTrue(pdpPage.productName().isDisplayed());
	}

	@Then("^a colour swatch panel should display swatches that represent all alternative colour variants$")
	public void a_colour_swatch_panel_should_display_swatches_that_represent_all_alternative_colour_variants()
	{
		assertTrue(pdpPage.getTotalColorSwatches() > 1);
	}

	@And("^I can click on a swatch to be taken to that colour product's PDP$")
	public void I_can_click_on_a_swatch_to_be_taken_to_that_colour_product_s_PDP()
	{

		String[] productCodes = {"2089953_60", "2089953_1", "2089953_10"};

		for (int i = 0; i < productCodes.length; i++)
		{
			List<WebElement> colorSwathces = homePageOld.getWebDriver()
					.findElements(By.cssSelector(".swatches__swatch"));
			colorSwathces.get(i).click();
			// assertTrue(pdpPage.productCode().getText().contains("Product Code: "+productCodes[i]));
		}

	}

	@Then("^swatch our of stock should be still available on the quick view$")
	public void swatch_our_of_stock_should_be_still_available_on_the_quick_view() throws Throwable
	{
		checkOutPage.switchToCheckoutIframe();
		assertTrue(pdpPage.swatchOutOfStock().isDisplayed());

	}

	@When("^you click on the out of stock swatch$")
	public void you_click_on_the_out_of_stock_swatch()
	{

		pdpPage.outOfStockSwatch().click();

	}

	@Then("^the title of the colour is displayed$")
	public void the_title_of_the_colour_is_displayed() throws InterruptedException
	{
		pdpPage.hoverOverSwatch();
	}

	@Then("^I can see media related to the product$")
	public void I_can_see_media_related_to_the_product()
	{

		pdpPage.hoverOverThePdp();

		assertTrue(pdpPage.carouselPinholes().isDisplayed());
		assertTrue(pdpPage.productImageOnPdp().isDisplayed());

	}

	@And("^click on product image$")
	public void click_on_product_image()
	{
		pdpPage.pause(1000);
		pdpPage.waitForAndGetElement(pdpPage.productImageOnPdpCurrent, 10).click();

	}

	@When("^clicked on X of the page over lay$")
	public void clicked_on_X_of_the_page_over_lay()
	{
		pdpPage.closeModalWindow().click();
	}

	@Then("^I should navigate back to PDP page$")
	public void I_should_navigate_back_to_PDP_page()
	{
		assertTrue(pdpPage.breadCrumbTrail().isDisplayed());
		assertTrue(pdpPage.productPrice().isDisplayed());

	}

	@Then("^the previous price and the current price should be shown$")
	public void the_previous_price_and_the_current_price_should_be_shown()
	{
		assertTrue(pdpPage.productPrice().isDisplayed());
		assertTrue(pdpPage.productPreviousPrice().isDisplayed());
	}

	@When("^I select an alternative variant from the colour swatch panel$")
	public void I_select_an_alternative_variant_from_the_colour_swatch_panel()
	{
		pdpPage.switchToDifferentColor();
	}

	@And("^this variant has a different price$")
	public void this_variant_has_a_different_price()
	{
		pdpPage.pause(4000);
		assertTrue(pdpPage.productPrice().getText().contains("£15.99"));
	}

	@And("^the price DOES NOT update when I select between size variants$")
	public void the_price_DOES_NOT_update_when_I_select_between_size_variants()
	{
		assertTrue(pdpPage.checkPricesHaveNotChanged());
	}

	@Then("^the add to bag button should be disabled if not size is selected$")
	public void the_add_to_bag_button_should_be_disabled_if_not_size_is_selected()
	{

		assertTrue(pdpPage.addToCartButtonDisabled().isDisplayed());
	}

	@Then("^the add to bag button should be enabled if size is selected$")
	public void the_add_to_bag_button_should_be_enabled_if_size_is_selected() throws Throwable
	{
		assertTrue(checkOutPage.addToCartButton().isDisplayed());
	}

	@And("^I am not automatically taken to the full cart$")
	public void I_am_not_automatically_taken_to_the_full_cart() throws Throwable
	{
		assertTrue(pdpPage.addedToBagText().getText().contains("Added to your bag"));
	}

	@When("^I click on view bag and checkout button$")
	public void I_click_on_view_bag_and_checkout_button()
	{
		JavascriptExecutor js = (JavascriptExecutor) pdpPage.getWebDriver();
		js.executeScript("window.scrollBy(0,500)", "");
		pdpPage.viewBagandCheckout().click();
	}

	@When("^I click on view bag and checkout button on pdp page$")
	public void I_click_on_view_bag_and_checkout_button_on_pdp_page()
	{
		pdpPage.viewBagandCheckout().click();
	}

	@And("^I am not presented with a size picker$")
	public void I_am_not_presented_with_a_size_picker()
	{
		assertFalse(checkOutPage.sizeDropDownPresent());
		//   assertTrue(checkOutPage.colorSwatchPresent()==true);
	}

	@Then("^I am shown text that tells me the product comes in One Size only$")
	public void I_am_shown_text_that_tells_me_the_product_comes_in_One_Size_only()
	{
		final WebElement oneSizeMessage = pdpPage.getOneSizeMessage();
		assertTrue(oneSizeMessage.isDisplayed());
		assertTrue(oneSizeMessage.getText().contains("One size"));
	}

	@And("^click on select size$")
	public void click_on_select_size()
	{
		pdpPage.selectSize().click();
	}

	@Then("^the product out of stock is greyed out$")
	public void the_product_out_of_stock_is_greyed_out() throws Throwable
	{
		checkOutPage.selectSize().click();
		assertTrue(pdpPage.outOfStockDisabled().getAttribute("label").contains("10"));
	}


	@And("^as I select different color from the swatch the product code should be changed$")
	public void as_I_select_different_color_from_the_swatch_the_product_code_should_be_changed() throws InterruptedException
	{

		//String[] productCodes = {"216129601", "216129604", "216129634"};


		//check default selected swatch has got product code
		// assertTrue(pdpPage.productCode().getText().contains("Product Code: 216129601"));

		// for (int i = 1; i < productCodes.length; i++) {

		List<WebElement> colorSwathces = pdpPage.getSwatchColorsElements();
		pdpPage.clickByJavaScriptExecutor(colorSwathces.get(0));
		pdpPage.clickShowMoreProductDetailsButton();
		String prodCode1 = pdpPage.productCode().getText();
		colorSwathces = pdpPage.getSwatchColorsElements();
		pdpPage.clickByJavaScriptExecutor(colorSwathces.get(1));
		pdpPage.clickShowMoreProductDetailsButton();
		String prodCode2 = pdpPage.productCode().getText();
		assertThat(prodCode1).isNotEqualTo(prodCode2);
		// assertTrue(pdpPage.productCode().getText().contains("Product Code: " + productCodes[i]));


	}

	@Then("^different color and size should be displayed$")
	public void different_color_and_size_should_be_displayed()
	{
		assertTrue(pdpPage.productItemCode().getText().contains("2161331"));
	}

	@When("^I see a link that lets me know that there are alternative delivery options offered by New Look$")
	public void I_see_a_link_that_lets_me_know_that_there_are_alternative_delivery_options_offered_by_New_Look()
	{
		assertTrue(pdpPage.deliveryAndReturnsInformationLink().isDisplayed());
	}

	@Then("^I can select the link$")
	public void I_can_select_the_link()
	{
		pdpPage.clickByJavaScriptExecutor(pdpPage.deliveryAndReturnsInformationLink());
	}

	@And("^the content will open in a side panel from the left and I am not navigated away from the current page$")
	public void the_content_will_open_in_a_side_panel_from_the_left_and_I_am_not_navigated_away_from_the_current_page()
	{
		assertTrue(pdpPage.deliverySidePanel().isDisplayed());
	}

	@And("^the delivery options listed are all delivery modes available and active for the current base store$")
	public void the_delivery_options_listed_are_all_delivery_modes_available_and_active_for_the_current_base_store() throws Throwable
	{
		//String[] deliveryMethod = {"Standard Tracked Delivery", "Premium Delivery"};
		//ToDO: Phani: check with existing team about this updated exp result
		String[] deliveryMethod = {"UK Standard - Metapack", "Channel Islands"};

		checkOutPage.getWebDriver().switchTo().frame(pdpPage.sidePanelIframe());

		List<String> deliveryMethodText;
		deliveryMethodText = pdpPage.getDeliveryMethodText();
		for (int i = 0; i < deliveryMethod.length; i++)
		{
			assertTrue(deliveryMethodText.get(i).contains(deliveryMethod[i]));
		}

	}

	@When("^I see a link that lets me know that there are alternative delivery options offered by New Look from full cart page$")
	public void I_see_a_link_that_lets_me_know_that_there_are_alternative_delivery_options_offered_by_New_Look_from_full_cart_page()
	{
		assertTrue(pdpPage.otherDeliveryOptions().isDisplayed());
	}

	@Then("^I can select the link from full cart page$")
	public void I_can_select_the_link_from_full_cart_page()
	{
		pdpPage.clickByJavaScriptExecutor(pdpPage.otherDeliveryOptions());
	}

	@Then("^I can see size chart link diplayed$")
	public void I_can_see_size_chart_link_diplayed()
	{

		assertTrue(pdpPage.sizeChartLink().isDisplayed());
	}

	@And("^the delivery options are listed: (.*)$")
	public void the_delivery_options_are_listed(List<String> options) throws Throwable
	{
		//TODO: Phani: probably a bug. I have updated exp results of delivery options as in current qa-rd env
		checkOutPage.getWebDriver().switchTo().frame(pdpPage.sidePanelIframe());

		List<String> deliveryMethodText = pdpPage.getDeliveryMethodText();

		for (String option : options)
		{
			assertTrue(deliveryMethodText.contains(option));
		}


	}

//    @And("I add the product in the basket")
//    //selected color: default
//    //selected size: first with available stock
//    public void add_product_in_basket() throws Exception {
//        while (!homePageOld.selectProductPresent() || homePageOld.productNotFound()) {
//            ShoppingCartStepDefinitions.productId = checkOutPage
//                    .searchForInStockRandomProduct(ShoppingCartStepDefinitions.department);
//        }
//        homePageOld.selectProduct().click();
//        //colorPdp = pdpPage.getDefaultColor(); --commented till it gets clarified---
//        sizePdp = pdpPage.getFirstAvailableSizeIfAny();
//        productNamePdp = pdpPage.getProductName();
//        while (!checkOutPage.addToCartButtonPresent()
//                && (checkOutPage.preOrderEmailPresent() || checkOutPage.stockNotAvailable())) {
//            ShoppingCartStepDefinitions.productId = checkOutPage
//                    .searchForInStockRandomProduct(ShoppingCartStepDefinitions.department);
//            homePageOld.selectProduct().click();
//            //colorPdp = pdpPage.getDefaultColor();
//            sizePdp = pdpPage.getFirstAvailableSizeIfAny();
//            productNamePdp = pdpPage.getProductName();
//        }
//        pdpPage.clickAddToBagBtn();
//    }
//
//
//    @And("I navigate to PDP old$")
//    public void navigate_to_PDP() throws Exception {
//        while (!homePageOld.selectProductPresent() || homePageOld.productNotFound()) {
//            ShoppingCartStepDefinitions.productId = checkOutPage
//                    .searchForInStockRandomProduct(ShoppingCartStepDefinitions.department);
//        }
//        homePageOld.selectProduct().click();
////        colorPdp = pdpPage.getDefaultColor();
//        sizePdp = pdpPage.getFirstAvailableSizeIfAny();
//        productNamePdp = pdpPage.getProductName();
//        while (!checkOutPage.addToCartButtonPresent()
//                && (checkOutPage.preOrderEmailPresent() || checkOutPage.stockNotAvailable())) {
//            ShoppingCartStepDefinitions.productId = checkOutPage
//                    .searchForInStockRandomProduct(ShoppingCartStepDefinitions.department);
//            homePageOld.selectProduct().click();
////            colorPdp = pdpPage.getDefaultColor();
//            sizePdp = pdpPage.getFirstAvailableSizeIfAny();
//            productNamePdp = pdpPage.getProductName();
//        }
//    }

	@And("I navigate to PDP$")
	public void navigate_to_PDPv2() throws Exception
	{
		homePageOld.selectRandomProduct();
		checkOutPage.selectSizeOfTheProduct();
		sizePdp = pdpPage.getFirstAvailableSizeIfAny();
		productNamePdp = pdpPage.getProductName();


//        colorPdp = pdpPage.getDefaultColor();
		//sizePdp = pdpPage.getFirstAvailableSizeIfAny();


	}


	@And("Item is added to (basket|saved)")
	public void item_is_added_to_basketOrSaved(String trigger) throws Exception
	{
		switch (trigger)
		{
			case "basket":
				pdpPage.clickAddToBagBtn();
				break;
			case "saved":
				pdpPage.addToSavedTemsList();
				break;
		}
	}

	@And("^select first colour of the product$")
	public void selectFirstColourOfTheProduct() throws Throwable
	{
		// Write code here that turns the phrase above into concrete actions
		pdpPage.selectColour(1);
	}

	@And("^select second colour of the product$")
	public void selectFirstColourOfTheProduct2() throws Throwable
	{
		// Write code here that turns the phrase above into concrete actions
		pdpPage.selectColour(2);
	}

	@And("^click on a product image or title$")
	public void click_on_a_product_image_or_title()
	{
		homePage.selectRandomProduct();
	}

	@Then("^select size of the product$")
	public void select_size_of_the_product()
	{
		checkOutPage.selectFirstAvailableSizeOfTheProduct();
	}

	@And("^add this item to bag with size (.*)$")
	public void select_size_of_the_product2(String size) throws InterruptedException
	{
		homePage.selectRandomProduct();
		pdpPage.selectSizeOfTheProduct(size);
//        checkOutPage.addProductToBag(size);
		pdpPage.clickAddToBagButton();
		pdpPage.clickMyBagAndCheckoutButton();
	}

	@And("^I click Add to Bag$")
	public void I_click_Add_to_Bag() throws InterruptedException
	{
		if (IS_MOBILE)
		{
			if (WebDriverHelper.DEVICE_NAME.contains("iPhone") || BROWSER.contains("emulator"))
			{
				checkOutPage.pause(1300);
			}
			checkOutPage.clickByJavaScriptExecutor(checkOutPage.addToCartButton());
		}
		else
		{
			checkOutPage.addToCartButton().click();
		}
		checkOutPage.waitForPageLoad();
	}


	@And("^click Add to Bag$")
	public void I_click_Add_to_Bag2() throws InterruptedException
	{
		checkOutPage.addToCartButton().click();

	}

	@Then("^click on the bag icon$")
	public void click_on_the_bag_icon()
	{
		if (IS_MOBILE)
		{
			pdpPage.clickMyBagAndCheckoutButton();
		}
		else
		{
			checkOutPage.clickByJavaScriptExecutor(checkOutPage.basketIcon());
		}
//        checkOutPage.basketIcon().click();
	}


	@Then("^I am taken to the full cart page$")
	public void I_am_taken_to_the_full_cart_page() throws Throwable
	{
		assertTrue(checkOutPage.checkOutButton().isDisplayed());
		assertTrue(checkOutPage.getWebDriver().getCurrentUrl().contains("/cart"));
	}

	@When("^I search for the product with the \"([^\"]*)\"$")
	public void I_search_for_the_product_with_the(String productCode)
	{
		checkOutPage.searchField().sendKeys(Props.getProp("productCode_stock"));
		checkOutPage.searchButton().click();

	}

	@When("^I search for the product with \"([^\"]*)\"$")
	public void I_search_for_the_product_with(String searchTerm)
	{
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(searchTerm);
		}
		else
		{
			checkOutPage.searchField().sendKeys(searchTerm);
			checkOutPage.searchButton().click();
		}
	}

	@When("^I search for a product$")
	public void I_search_for_the_product_with_the2()
	{
		checkOutPage.pause(5000);
		log.info("Getting the product code -- " + Props.getProp("productCode_stock"));
		String product = Props.getProp("productCode_stock");
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(Props.getProp("productCode_stock"));
		}
		else
		{
			checkOutPage.searchField().sendKeys(product);
			checkOutPage.searchButton().click();
		}
	}

	@When("^search one size product$")
	public void I_search_for_the_product_with_the3()
	{
		String productCode_oneSize = Props.getProp("productCode_OneSize");
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(productCode_oneSize);
		}
		else
		{
			checkOutPage.searchField().sendKeys(productCode_oneSize);
			checkOutPage.searchButton().click();
		}
	}

	@When("^search low stock product$")
	public void I_search_for_the_product_with_the4()
	{
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(Props.getProp("ProductCode_LowStock"));
		}
		else
		{
			checkOutPage.searchField().sendKeys(Props.getProp("ProductCode_LowStock"));
			checkOutPage.searchButton().click();
		}
	}

	@When("^search multi colour product$")
	public void I_search_for_the_product_with_the6()
	{
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(Props.getProp("productCode_MultiColour"));
		}
		else
		{
			checkOutPage.searchField().sendKeys(Props.getProp("productCode_MultiColour"));
			checkOutPage.searchButton().click();
		}
	}

	@When("^search previous price product$")
	public void I_search_for_the_product_with_the7()
	{
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(Props.getProp("productCode_PreviousPrice"));
		}
		else
		{
			checkOutPage.searchField().sendKeys(Props.getProp("productCode_PreviousPrice"));
			checkOutPage.searchButton().click();
		}
	}

	@When("^search previous price product for oms$")
	public void search_previous_price_product_for_oms()
	{
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(Props.getProp("productCode_PreviousPrice_oms"));
		}
		else
		{
			checkOutPage.searchField().sendKeys(Props.getProp("productCode_PreviousPrice_oms"));
			checkOutPage.searchButton().click();
		}

	}

	@And("^select size of the product \"([^\"]*)\"$")
	public void select_size_of_the_product(String size)
	{
		checkOutPage.selectSizeOfTheProductPrecisely(size);

	}

	@And("^select size of the product \"([^\"]*)\" from my bag page$")
	public void select_size_of_the_product_from_my_bag_page(String size) throws Throwable
	{
		myBagPage.setTheSizeOfTheProduct(size);
	}

	@Then("^selected size (.*) is displayed$")
	public void selected_size_is_displayed(String size) throws InterruptedException
	{
		Thread.sleep(2000);
		if (IS_MOBILE)
		{
			myBagPage.clickEditForLineItemMobile(1);
		}
		assertEquals(size, pdpPage.getSelectedProductSize());
	}

	@Then("^item (.*) should present with size (.*) colour (.*)$")
	public void bagItemAssertion(String item, String size, String colour) throws Throwable
	{
		WebElement bagItem = null;
		for (WebElement e : checkOutPage.getBagItems())
		{
			if (e.findElement(CheckOutPage.BAG_ITEM_NAME).getText().contains(item))
			{
				bagItem = e;
				break;
			}
		}
		assertNotNull("Can not find the item with name in bag :: " + item, bagItem);
		assertThat(bagItem.findElement(By.xpath("./../../.."))
				.findElement(CheckOutPage.BAG_ITEM_COLOUR).getText()).isEqualToIgnoringWhitespace(colour);
		if (size.toLowerCase().contains("one size"))
		{
			assertThat(checkOutPage.getBagItemSingleSize(bagItem)).isEqualTo(size);
		}
		else
		{
			assertThat(checkOutPage.getBagItemSize(bagItem)).isEqualToIgnoringWhitespace(size);
		}
	}

	@And("^cart header should be (.*)")
	public void cartHeaderShouldBeMyBa(String header)
	{
		checkOutPage.waitForElementWithText(CheckOutPage.BAG_HEADER, header, 40);
	}

	@Then("^item (.*) should show error (.*)$")
	public void bagItemAssertion(String item, String errorMsg)
	{
		assertThat(myBagPage.getBagItemMessage()).isEqualToIgnoringWhitespace(errorMsg);
	}

	@Then("^Add to bag button should always be enabled$")
	public void add_to_bag_button_should_always_be_enabled()
	{
		assertTrue(checkOutPage.addToCartButton().isEnabled());
	}

	@When("^Click Add to Bag button without selecting a size$")
	public void click_Add_to_Bag_button_without_selecting_a_size()
	{
		checkOutPage.addToCartButton().click();
	}

	@Then("^Validation message to select a size should be displayed$")
	public void validation_message_to_select_a_size_should_be_displayed()
	{
		assertEquals("Please choose a size.", pdpPage.SIZE_VALIDATION_MESSAGE().getText());

	}

	@When("^search and select the product \"([^\"]*)\" with the perfect partner$")
	public void search_and_select_the_product_with_the_perfect_partner(String perfect_partner_product)
	{
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(perfect_partner_product);
			if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
					WebDriverHelper.DEVICE_NAME.contains("iPad") ||
					BROWSER.contains("emulator"))
			{
				homePage.pause(1000);
			}
		}
		else
		{
			checkOutPage.searchField().sendKeys(perfect_partner_product);
			checkOutPage.searchButton().click();
		}
	}

	@Then("^the Multi PDP page should be displayed with the perfect partner products$")
	public void the_Multi_PDP_page_should_be_displayed_with_the_perfect_partner_products(List<String> arg1) throws Exception
	{
		pdpPage.pause(4000);
		assertEquals(arg1, pdpPage.findPerfectPartnerProductNames());
	}


	@When("^user search for a product with product code$")
	public void searchForAProductWithProductCode()
	{
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(Props.getProp("productCode"));
			checkoutModel.setProductCode(Props.getProp("productCode"));
		}
		else
		{
			checkOutPage.searchForAProductWithProductCode(checkoutModel);
		}
	}

	@And("^user click on a product that appears in carousel$")
	public void userClickOnAProductThatAppearsInCarousel()
	{
		pdpPage.clickOnFirstProductInCarousel(carouselModel);
	}

	@And("^user click on a product that appears in new carousel$")
	public void userClickOnAProductThatAppearsInNewCarousel()
	{
		pdpPage.clickOnFirstProductInNewCarousel(carouselModel);
	}

	@Then("^the displayed products count on the Multi PDP page should match with the \"([^\"]*)\"$")
	public void the_displayed_products_count_on_the_Multi_PDP_page_should_match_with_the(int proddisplaycount)
	{

		pdpPage.totalProductCount(proddisplaycount);
	}

	@Then("^the product opens in side panel$")
	public void theProductOpensInSidePanel()
	{
		assertTrue(pdpPage.verifyIfProductOpensInSidePanel(carouselModel));
	}

	@Then("^the wear with carousel should not be displayed on side panel$")
	public void theWearWithCarouselShouldNotBeDisplayedOnSidePanel()
	{
		assertFalse(pdpPage.verifyIfProductOpensInSidePanel(carouselModel));
	}

	@Then("^the carousel product should not be displayed on side panel$")
	public void theCarouselProductShouldNotBeDisplayedOnSidePanel()
	{
		assertFalse("carousel should not open on side panel", pdpPage.verifyIfProductOpensInSidePanel(carouselModel));
	}

	@Then("^click on show more to expand$")
	public void clickOnShowMoreToExpand()
	{
		pdpPage.clickOnShowMore();
	}

	@And("^click on show less to collapse$")
	public void clickOnShowLessToCollapse()
	{
		pdpPage.clickOnShowLess();
	}

	@Then("^check the product with highlight badge$")
	public void checkTheProductWithHighlightBadge()
	{
		assertTrue(pdpPage.checkIfProductHaveHighlightBadge());
	}

	@Then("^check the product with promotion link$")
	public void checkTheProductWithPromotionLink()
	{
		assertTrue(pdpPage.isPromotionLinkPresent());
	}

	@Then("^check the product with updated price$")
	public void checkTheProductWithUpdatedPrice()
	{
		assertTrue(pdpPage.isPriceUpdated());
	}

	@Then("^check the product with product name$")
	public void checkTheProductWithProductName()
	{
		assertEquals(pdpPage.getProductNameFromSidePanel(), checkoutModel.getProductName());
	}

	@And("^add the product to add to saved items$")
	public void addTheProductToAddToSavedItems()
	{
		pdpPage.addCarouselProductToSavedList();
	}

	@And("^remove the product from the saved items$")
	public void removeTheProductFromSavedItems()
	{
		pdpPage.removeProductFromSavedList();
	}

	@Then("^check if the product is added to saved items in side panel$")
	public void checkIfTheProductIsAddedToSavedItemsInSidePanel()
	{
		wishListPage.clickByJavaScriptExecutor(wishListPage.wishListLink());
		pdpPage.verifyIfSavedItemIsAdded();
		assertEquals("1", pdpPage.getSavedItemsCount());

	}

	@Then("^select a specific product '(.*)' from the wear with carousel section$")
	public void selectASpecificProductYellowScoopNeckVestFromTheWearWithCarouselSection(String productName)
	{
		pdpPage.selectSpecificProductFromCarousel(productName, checkoutModel);
	}

	@And("^check if colour is updated when different colour is applied using colour picker$")
	public void checkIfColourIsUpdatedWhenDifferentColourIsAppliedUsingColourPicker()
	{
		pdpPage.clickOnAnotherColourFromSwatch();
		pdpPage.verifyIfProductGetsUpdated();
		assertTrue(!pdpPage.getProductCode().equalsIgnoreCase(checkoutModel.getProductCode()));
	}


	@Then("^click on show more to expand on side panel$")
	public void clickOnShowMoreToExpandOnSidePanel()
	{
		pdpPage.clickShowMoreOnSidePanel();
	}

	@When("^click on the searched product name$")
	public void click_on_the_searched_product_name()
	{
		homePage.selectRandomProduct();
	}


	@And("^I clear the bag$")
	public void iClearTheBag() throws Exception
	{
		checkOutPage.basketIcon().click();
		plpPage.removeBagItemList();
	}

	@Then("^delivery options are displayed$")
	public void deliveryOptionAreDisplayed(List<String> contents)
	{
		if (WebDriverHelper.DEVICE_NAME.contains("iPhone") ||
				WebDriverHelper.DEVICE_NAME.contains("iPad") ||
				WebDriverHelper.DEVICE_NAME.contains("Samsung") ||
				BROWSER.contains("emulator"))
		{
			pdpPage.pause(3000);
		}
		for (String content : contents)
		{
			switch (content)
			{
				case "Delivery icon":
					Assert.assertTrue(pdpPage.getFreeDeliveryIcon());
					break;
				case "FREE STANDARD Delivery":
					Assert.assertEquals(content, pdpPage.getFreeStandardDeliveryHeader());
					break;
				case "Free Delivery*":
					Assert.assertEquals(content, pdpPage.getFreeDeliveryReturnLabel(content));
					break;
				case "Free Returns*":
					Assert.assertEquals(content, pdpPage.getFreeDeliveryReturnLabel(content));
					break;
			}
		}

	}

	@Then("^the thumbnail component should exist for the selected product and its associated perfect partner products$")
	public void the_thumbnail_component_should_exist_for_the_selected_product_and_its_associated_perfect_partner_products()
	{
		assertTrue(pdpPage.checkMultiPdpThumbnailImage());
	}

	@When("^search for the multicolour product$")
	public void search_for_the_multicolour_product()
	{
		String multiColourProduct = Props.getProp("productCode_MultiColour");
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(Props.getProp("productCode_MultiColour"));
		}
		else
		{
			checkOutPage.searchField().sendKeys(multiColourProduct);
			checkOutPage.searchButton().click();
		}
	}

	@And("^customer search for low stock product with size \"([^\"]*)\" and add product to my page$")
	public void customerSearchForLowStockProductWithSizeAndAddProductToMyPage(String sizeOfProduct)
	{
		homePage.searchForProduct(Props.getProp("ProductCode_LowStock"));
		homePage.selectRandomProduct();
		pdpPage.selectSizeOfTheProduct(sizeOfProduct);
		pdpPage.clickAddToBagButton();
	}

	@And("^customer search for low stock product with size \"([^\"]*)\" and add product to my page on mobile$")
	public void customerSearchForLowStockProductWithSizeAndAddProductToMyPageOnMobile(String sizeOfProduct)
	{
		homePage.mobileInsertTextAndSearch(Props.getProp("ProductCode_LowStock"));
		homePage.selectRandomProduct();
		pdpPage.selectSizeOfTheProduct(sizeOfProduct);
		pdpPage.clickAddToBagButton();
	}
	@And("^customer search for low stock product with size \"([^\"]*)\" and add product to my page on oms mobile$")
	public void customerSearchForLowStockProductWithSizeAndAddProductToMyPageOnMobileOms(String sizeOfProduct)
	{
		homePage.mobileInsertTextAndSearch(Props.getProp("ProductCode_LowStock_oms"));
		homePage.selectRandomProduct();
		pdpPage.selectSizeOfTheProduct(sizeOfProduct);
		pdpPage.clickAddToBagButton();
	}
	@Then("^the store lookup button should be displayed on the page$")
	public void theStoreLookupButtonShouldBeDisplayedOnThePage()
	{
		assertTrue(pdpPage.getfindInStoreCount());
	}

	@Then("^side panel should open after clicking on the store lookup button$")
	public void sidePanelShouldOpenAfterClickingOnTheStoreLookupButton()
	{
	}

	@And("^click on the store lookup button$")
	public void clickOnTheStoreLookupButton()
	{
		pdpPage.clickFindInStore();
	}

	@Then("^side panel should open$")
	public void sidePanelShouldOpen()
	{
		assertTrue(pdpPage.sidePanelIframe().isDisplayed());
	}

	@Then("^the elements should be present on the page$")
	public void theElementsShouldBePresentOnThePage()
	{
		pdpPage.sidePanelIframe().click();
		pdpPage.switchtoframe(pdpPage.sidePanelIframe());
		pdpPage.getCheckAvailabilityButton().click();
		assertTrue(pdpPage.getIframeColourSwatch().isDisplayed());
		assertTrue(pdpPage.getProductSize().isDisplayed());
		assertTrue(pdpPage.getFindInStore().isDisplayed());
		assertTrue(pdpPage.getProductInIframe().isDisplayed());
		assertTrue(pdpPage.colorSwatchesOnQuickView().isDisplayed());
		assertTrue(pdpPage.getPostcodeTownSearchbox().isDisplayed());
	}

	@And("^click on 'Check Availibility' button$")
	public void clickOnCheckAvailabilityButton() throws Exception
	{
		Thread.sleep(1000);
		pdpPage.getCheckAvailabilityButton().click();
	}

	@Then("^Validation message to select a size should be displayed in the side panel$")
	public void validation_message_to_select_a_size_should_be_displayed_in_the_side_panel()
	{
		assertEquals("Please choose a size", pdpPage.SIZE_VALIDATION_MESSAGE().getText());

	}

	@And("^switch to side panel$")
	public void switchToSidePanel()
	{
		pdpPage.switchtoframe(pdpPage.sidePanelIframe());
	}

	@Then("^the validation message should be displayed$")
	public void theValidationMessageShouldBeDisplayed()
	{

	}

	@Then("^correct validation message should be displayed if the user tries to search with invalid search \"([^\"]*)\" criteria$")
	public void correctValidationMessageShouldBeDisplayedIfTheUserTriesToSearchWithInvalidSearchCriteria(String invalidCriteria) throws Exception
	{
		pdpPage.getPostcodeTownSearchbox().click();
		pdpPage.getPostcodeTownSearchbox().sendKeys(invalidCriteria);
		pdpPage.getCheckAvailabilityButton().click();
		switch (invalidCriteria)
		{
			case "":
				assertEquals("Check you've entered a valid postcode or place name.",
						pdpPage.SIZE_VALIDATION_MESSAGE().getText());
				break;
			case "dssdsd":
				assertEquals("No stores found, please try another location.",
						pdpPage.SIZE_VALIDATION_MESSAGE().getText());
				break;
		}
	}

	@And("^enter the correct search \"([^\"]*)\" criteria and search$")
	public void enterTheCorrectSearchCriteriaAndSearch(String validSearchCriteria) throws Throwable
	{
		pdpPage.pause(1000);
		pdpPage.getPostcodeTownSearchbox().click();
		pdpPage.getPostcodeTownSearchbox().sendKeys(validSearchCriteria);
		pdpPage.getCheckAvailabilityButton().click();
		Thread.sleep(2000);
	}

	@Then("^the below details should be displayed in the result$")
	public void theBelowDetailsShouldBeDisplayedInTheResult() throws Throwable
	{

	}

	@Then("^the store details page should open after clicking on the 'More info and opening timings' link$")
	public void theStoreDetailsPageShouldOpenAfterClickingOnTheMoreInfoAndOpeningTimingsLink() throws Exception
	{
		String name = pdpPage.getStoreName();
		pdpPage.clickStoreInfoLink();
		assertEquals(name, storeLocatorPage.shopName().getText());
	}

	@When("^I open the drop down size picker$")
	public void openDropdownSizePicker()
	{
		pdpPage.clickDropdownSizepicker();
	}

	@And("^I select low stock size \"([^\"]*)\"$")
	public void checkSizeInDropdown(String size)
	{
		pdpPage.selectSizeFromDropDownWUrgMsgOn(pdpPage.getSizeDisplayOrderFromDropDownWUrgMsgOn(size));
	}

	@Then("^message \"([^\"]*)\" will display next to the size \"([^\"]*)\" value$")
	public void checkDropdownUrgencyMessage(String message, String size)
	{
		Integer sizeOrder = null;
		sizeOrder = pdpPage.getSizeDisplayOrderFromDropDownWUrgMsgOn(size);
		String realMessage = pdpPage.getSizeTextFromDropDownWUrgentMsgOn(sizeOrder);
		Assert.assertEquals(message, realMessage);
	}

	@Then("^message \"([^\"]*)\" will display next to the one size string$")
	public void checkOnesizeUrgencyMessage(String message)
	{
		String realMessage = pdpPage.getStockUrgMsgForOneSizeProdWUrgentMsgOn();
		Assert.assertEquals(realMessage, message);
	}

	@And("^customer search for product (.*) and navigates to pdp page$")
	public void customerSearchForProduct(String productCode)
	{
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(Props.getProp(productCode));
		}
		else
		{
			homePage.searchForProduct(Props.getProp(productCode));
		}
		homePage.selectRandomProduct();
	}

	@Then("^pdp description should contain brand category CTA$")
	public void ThenDescriptionShouldContainBrandcategoryCTA()
	{
		String dd = pdpPage.brandCategoryCta().getAttribute("href");
		Assert.assertNotNull(pdpPage.brandCategoryCta().getAttribute("href"));
	}


	@Then("^saved items heart icon should be displayed$")
	public void savedItemsHeartIconShouldBeDisplayed()
	{
		Assert.assertTrue(pdpPage.getSavedItemsHeartIcon());
	}

	@Given("^Saved items feature is on$")
	public void featureIsOn()
	{
		boolean featureStatus;
		String featureName = "feature.storefront.storeStockLookup.heartIcon.enabled";
		featureStatus = NewLookHelper.getFeatureStatus(featureName);
		if (featureStatus)
		{
			log.info("### feature is enabled: ", featureName);
		}
		else
		{
			throw new AssumptionViolatedException("FEATURE IS DISABLED" + featureName);
		}

	}

	@Then("^the selected size \"([^\"]*)\" should be pre-populated on the page$")
	public void theSelectedSizeShouldBePrePopulatedOnThePage(String size) throws Exception
	{
		pdpPage.sidePanelIframe().click();
		pdpPage.switchtoframe(pdpPage.sidePanelIframe());
		Thread.sleep(1500);
		assertEquals(size, pdpPage.preSelectedSize().getText());
	}

	@Then("^the 'Product Name','Product Size Picker','Colour Swatch', 'Location Search' and 'Check Availability Button' should be present on the page$")
	public void theProductNameProductSizePickerColourSwatchLocationSearchAndCheckAvailabilityButtonShouldBePresentOnThePage()
	{
		pdpPage.sidePanelIframe().click();
		pdpPage.switchtoframe(pdpPage.sidePanelIframe());
		pdpPage.getCheckAvailabilityButton().click();
		assertTrue(pdpPage.getIframeColourSwatch().isDisplayed());
		assertTrue(pdpPage.getProductSize().isDisplayed());
		assertTrue(pdpPage.getFindInStore().isDisplayed());
		assertTrue(pdpPage.getProductInIframe().isDisplayed());
		assertTrue(pdpPage.colorSwatchesOnQuickView().isDisplayed());
		assertTrue(pdpPage.getPostcodeTownSearchbox().isDisplayed());

	}

	@Then("^the map should be available in the side panel after clicking on the 'Plus' button$")
	public void theMapShouldBeAvailableInTheSidePanelAfterClickingOnTheButton()
	{
		pdpPage.getPlusButton().click();
		assertTrue(pdpPage.getMapComponent().isDisplayed());
	}

	@Then("^the the stock information for the searched result displays$")
	public void theTheStockInformationForTheSearchedResultDisplays()
	{
		pdpPage.pause(1000);
		assertTrue(pdpPage.getStockInformation().isDisplayed());
	}

	@Then("^the disclaimer text displays$")
	public void the_disclaimer_text_displays() throws Exception
	{
		pdpPage.pause(3000);
		assertTrue(pdpPage.getDisclaimerText().isDisplayed());
	}

	@Then("^maximum \"([^\"]*)\" should be displayed on the page$")
	public void maximum_should_be_displayed_on_the_page(String maximumStores)
	{
		int i = Integer.parseInt(maximumStores);
		assertEquals(i, pdpPage.getNumberOfStoreResults());
	}

	@And("^select a size from the side panel$")
	public void selectASizeFromTheSidePanel() throws Throwable
	{
		pdpPage.selectSizeFromSidePanel();
	}

	@And("^select a size from the saved side panel$")
	public void selectASizeFromTheSavedSidePanel() throws Throwable
	{
		pdpPage.selectSizeFromSavedSidePanel();
	}

	@Then("^I should see the fixed price discount message$")
	public void iShouldSeeTheFixedPriceDiscountMessage()
	{
		pdpPage.pause(2000);
		assertTrue(pdpPage.getFixedPriceText().contains("Fixed price of"));
	}

	@And("^user clicks on shop to look button$")
	public void userClicksOnShopToLookButton()
	{
		pdpPage.clickShopToLookButton();
	}

	@And("^I should be navigated to the full PDP page and the product name is \"([^\"]*)\"$")
	public void iShouldBeNavigatedToTheFullPDPPageAndTheProductNameIsPlusSizeBlueStripeWideLegTrousers(String productName)
	{
		pdpPage.pause(3000);
		Assert.assertTrue(pdpPage.getProductName().equals(productName));
	}

	@And("^I click on the carousel image$")
	public void iClickOnTheWithCarouselImage()
	{
		pdpPage.clickOnCarouselImage();
	}

	@Then("^PDP review and rating section should exist$")
	public void pdp_review_and_rating_section_should_exist()
	{
		pdpPage.pdpReviewAndRatingSectionExist();
	}

	@Then("^PDP review and rating section should not exist$")
	public void pdp_review_and_rating_section_should_not_exist()
	{
		pdpPage.pdpReviewAndRatingSectionDoesNotExist();
	}

	@And("^I search for the product (.*) and navigate to pdp page$")
	public void iSearchForTheProduct(String productCode)
	{
		homePage.searchForProduct(Props.getProp(productCode));
		homePage.selectRandomProduct();
	}

	@When("^I search for a product with system property \"([^\"]*)\"$")
	public void iSearchForAProductWithSystemProperty(String systemProperty)
	{
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(Props.getProp(systemProperty));
		}
		else
		{
			checkOutPage.searchField().sendKeys(Props.getProp(systemProperty));
			checkOutPage.searchButton().click();
		}
	}

	@And("^click on carousel slicker$")
	public void clickOnCarouselSlicker()
	{
		pdpPage.clickCarouselSlicker();
	}

	@Then("^size guide cta should not be displayed$")
	public void size_guide_cta_should_not_be_displayed()
	{
		assertFalse("Size guide cta is displayed", pdpPage.isSizeGuideCTADisplayed());
	}

	@Then("^size guide cta should be displayed$")
	public void size_guide_cta_should_be_displayed()
	{
		assertTrue("Size guide cta is displayed", pdpPage.isSizeGuideCTADisplayed());
	}

	@When("^I click on the size guide cta$")
	public void iClickOnTheSizeGuideCta()
	{
		pdpPage.clickOnSizeguideCta();
	}

	@Then("^size guide side panel should open$")
	public void size_guide_side_panel_should_open()
	{
		assertTrue("Size guide side panel is displayed", pdpPage.isSizeGuideSidePanelDisplayed());
	}

	@When("^select a specific product '(.*)' from the product carousel section$")
	public void select_a_specific_product_Plus_Size_Blue_Stripe_Wide_Leg_Trousers_from_the_product_carousel_section(
			String productName)
	{
		pdpPage.selectSpecificProductFromNewCarousel(productName, checkoutModel);
	}

	@Then("^the previous price and the marked down price should be displayed for carousel product \"([^\"]*)\"$")
	public void the_previous_price_and_the_current_price_should_be_displayed_for_carousel_product(String productName)
	{
		assertFalse("Markdown price is empty", pdpPage.getMarkedDownPriceForProductCarouselItem(productName).isEmpty());
		assertFalse("previous price is empty", pdpPage.getPreviousPriceForProductCarouselItem(productName).isEmpty());
	}

	@Then("^the current price should be displayed for carousel product \"([^\"]*)\"$")
	public void the_current_price_should_be_displayed_for_carousel_product(String productName)
	{
		assertFalse("Current price is empty", pdpPage.getCurrentPriceForProductCarouselItem(productName).isEmpty());
	}

	@And("^I select size and add to bag carousel product \"([^\"]*)\"$")
	public void i_select_size_and_add_to_bag_carousel_product(String productName)
	{
		pdpPage.selectAvailableSizeFromProductCarousel(productName);
		pdpPage.addToBagCarouselProduct(productName);
	}

	@Then("^product \"([^\"]*)\" should be added to the bag$")
	public void productShouldbeAddedToTheBag(String productName)
	{
		assertTrue(myBagPage.getMyBagHeaderAndItemsAddedInMyBagPage().contains(productName));
	}

	@Then("^size selector should not be present for carousel product \"([^\"]*)\"$")
	public void sizeSelectorShouldNotBeDisplayedForCarouselProduct(String productName)
	{
		assertFalse(pdpPage.isSizeSelectorPresentProductCarousel(productName));
	}

	@And("^One Size message should be displayed for carousel product \"([^\"]*)\"$")
	public void oneSizeMessageShouldBeDisplayedForCarouselProduct(String productName)
	{
		assertTrue(pdpPage.isOneSizeMessageDisplayedProductCarousel(productName));
	}

	@When("^I add one size product \"([^\"]*)\" from product carousel$")
	public void iAddOneSizeProductFromCarousel(String productName)
	{
		pdpPage.addToBagCarouselProduct(productName);
	}

	@Then("^product added to bag message should be displayed for carousel product \"([^\"]*)\"$")
	public void productAddedToBagMessageShouldBeDisplayedForCarouselProduct(String productName)
	{
		assertTrue(pdpPage.getCarouselComponentSuccessMessageAlert(productName));
	}

	@And("^add to bag carousel product \"([^\"]*)\"$")
	public void add_to_bag_carousel_product(String productName)
	{
		pdpPage.addToBagCarouselProduct(productName);
	}

	@Then("^please choose size message should be displayed for carousel product \"([^\"]*)\"$")
	public void pleaseChooseSizeMessageShouldBeDisplayedForCarouselProduct(String productName)
	{
		assertFalse(pdpPage.getCarouselComponentErrorMessage(productName).isEmpty());
	}

	@When("^I open the drop down size picker from saved items$")
	public void iOpenTheDropDownSizePickerFromSavedItems()
	{
		wishListPage.clickDropdownSizepicker();
	}

	@And("^viewing the saved items$")
	public void viewingTheSavedItems() throws Throwable
	{
		wishListPage.selectWishListView();
		wishListPage.selectSavedItemsFullPage();
	}

	@Then("^The saved items pane urgency warning says \"([^\"]*)\"$")
	public void theSavedItemsPaneUrgencyWarningSays(final String expectedMessage)
	{
		Assert.assertEquals(expectedMessage, pdpPage.getSavedItemsUrgencyWarning().getText());
	}

	@Then("^The saved items pane has no urgency warning$")
	public void theSavedItemsPaneHasNoUrgencyWarning()
	{
		Assert.assertNull(pdpPage.getSavedItemsUrgencyWarning());
	}

	@When("^I navigate to the PDP of a \"([^\"]*)\" product$")
	public void iNavigateToThePDPOfAProduct(final String productType)
	{
		final String productCode = ProductProperties.productCode(productType);
		if (IS_MOBILE)
		{
			homePage.mobileInsertTextAndSearch(productCode);
		}
		else
		{
			homePage.searchForProduct(productCode);
		}
		homePage.selectRandomProduct();
	}

	@When("^customer navigates to pdp page of \"([^\"]*)\"$")
	public void customerNavigatesToPdpPageOfProduct(final String productCode)
	{
		WebDriverHelper.getWebDriver().get(UrlBuilder.getSiteUrl() + "uk/mens/clothing/shorts/slim-fit-shorts/stone-chino-shorts/p/" + productCode);
	}


	@And("^I click on the sticky add to bag button$")
	public void iClickOnTheStickyAddToBagButton()
	{
		pdpPage.getStickyAddToBagButton().click();
	}

	@Then("^The sticky size picker is visible$")
	public void theStickySizePickerIsVisible()
	{
		Assert.assertTrue(pdpPage.getStickySizePickerOptions().stream().allMatch(WebElement::isDisplayed));
	}

	@And("^I select a size$")
	public void iSelectASize()
	{
		pdpPage.selectSizeFromDropDown();
	}

	@Then("^The sticky added to bag message is visible$")
	public void theStickyAddedToBagMessageIsVisible()
	{
		pdpPage.pause(1000);
		assertTrue(pdpPage.isStickyAddedToBagMessageVisible());
		assertEquals(AlertsProperties.addedToBag(), pdpPage.getStickyAddedToBagMessage().getText());
	}

	@When("^I scroll halfway down the page$")
	public void iScrollHalfwayDownThePage()
	{
		pdpPage.scrollHalfwayDown();
	}

	@When("^I wait for (\\d+) seconds$")
	public void iWaitForSeconds(final int seconds)
	{
		pdpPage.pause(1000 * seconds);
	}

	@Then("^The sticky added to bag message is not visible$")
	public void theStickyAddedToBagMessageIsNotVisible()
	{
		assertFalse(pdpPage.isStickyAddedToBagMessageVisible());
	}

	@Then("^The add to favourites button is visible under the \"([^\"]*)\" carousel item$")
	public void theAddToFavouritesButtonIsVisibleUnderTheCarouselItem(final String item)
	{
		assertTrue(pdpPage.getPdpCarouselItem(ProductProperties.productName(item)).getHeart().isDisplayed());
	}

	@Then("^I click on the add to favourites button under the \"([^\"]*)\" carousel item$")
	public void iClickOnTheAddToFavouritesButtonUnderTheCarouselItem(final String item)
	{
		pdpPage.scrollToTop();
		pdpPage.getPdpCarouselItem(ProductProperties.productName(item)).getHeart().click();
	}

	@Then("^I click on the show favourites button$")
	public void iClickOnTheShowFavouritesButton()
	{
		pdpPage.getShowFavouritesButton().click();
		pdpPage.pause(2000);
	}

	@Then("^The \"([^\"]*)\" item is visible in the favourites section$")
	public void theItemIsVisibleInTheFavouritesSection(final String item)
	{
		pdpPage.pause(3000);
		assertNotNull(pdpPage.getFavourite(ProductProperties.productName(item)));
	}

	@Then("^The size selector is visible under the \"([^\"]*)\" favourites item$")
	public void theSizeSelectorIsVisibleUnderTheFavouritesItem(final String item)
	{
		final WebElement sizeSelector = pdpPage.getFavourite(ProductProperties.productName(item)).getSizeSelector();
		assertNotNull(sizeSelector);
		assertTrue(sizeSelector.isDisplayed());
	}

	@Then("^I close the favourites section$")
	public void iCloseTheFavouritesSection()
	{
		pdpPage.getFavouritesCross().click();
		pdpPage.waitUntilFavouritesSectionIsInvisible();
	}

	@Then("^I select a size under the \"([^\"]*)\" carousel item$")
	public void iSelectASizeUnderTheCarouselItem(final String item)
	{
		SeleniumUtils.selectRandomFromDropdown(pdpPage.getPdpCarouselItem(ProductProperties.productName(item))
				.getVariantSelector());
	}

	@Then("^shop related item CTA is displayed$")
	public void shopRelatedItemCtaIsDisplayed()
	{
		assertTrue(checkOutPage.isElementPresent(pdpPage.getRelatedItemCTA(), 20));
	}

	@Then("^user should not be able to scroll through different item pictures on pdp$")
	public void userShouldNotBeAbleToScrollThroughDifferentItemPicturesOnPdp()
	{
		assertTrue(checkOutPage.isElementPresent(pdpPage.getDisabledScrollItem(), 20));
	}

	@Then("^carousel is available above product$")
	public void validateCarouselIsAvailableAboveProduct()
	{
		assertTrue(checkOutPage.isElementPresent(pdpPage.getCarouselAboveProduct(), 20));
	}

	@Then("^The size under the \"([^\"]*)\" favourites item matches selected size$")
	public void theSizeUnderTheFavouritesItemMatchesTheSelectedSize(final String item)
	{
		final String product = ProductProperties.productName(item);
		final String actualSize = pdpPage.getFavourite(product).getVariantDetails().getText();

		pdpPage.getWebDriver().switchTo().defaultContent();
		final String expectedSize = pdpPage.getPdpCarouselItem(product).getSelectedVariant();

		assertTrue(actualSize.contains(expectedSize));
	}

	@And("^The carousel product details are visible on the \"([^\"]*)\" product$")
	public void theCarouselProductDetailsAreVisibleOnTheProduct(final String product)
	{
		final SoftAssert softAssert = new SoftAssert();
		final PdpCarouselItem item = pdpPage.getPdpCarouselItem(ProductProperties.productName(product));

		softAssert.assertTrue(item.getName().isDisplayed(), "Name is not visible.");
		softAssert.assertTrue(item.getImage().isDisplayed(), "Image is not visible.");
		softAssert.assertTrue(item.getPrice().isDisplayed(), "Price is not visible.");

		if (Objects.nonNull(item.getOneSizeMessage()))
		{
			softAssert.assertTrue(item.getOneSizeMessage().isDisplayed(), "Size is not visible.");
		}
		else if (Objects.nonNull(item.getVariantSelector()))
		{
			softAssert.assertTrue(item.getVariantSelector().isDisplayed(), "Variant selector is not visible.");
		}

		softAssert.assertAll();
	}

	@And("^The was/now price is visible on the \"([^\"]*)\" carousel product$")
	public void theWasNowPriceIsVisibleOnTheCarouselProduct(final String product)
	{
		final PdpCarouselItem item = pdpPage.getPdpCarouselItem(ProductProperties.productName(product));
		assertNotNull(item.getPreviousPrice());
		assertTrue(item.getPreviousPrice().isDisplayed());
	}

	@When("^I click on the title of the \"([^\"]*)\" product in the carousel$")
	public void iClickOnTheTitleOfTheProductInTheCarousel(final String product)
	{
		pdpPage.getPdpCarouselItem(ProductProperties.productName(product)).getName().click();
	}

	@Then("^I am on the \"([^\"]*)\" PDP$")
	public void iAmOnThePDP(final String product)
	{
		assertEquals(ProductProperties.productName(product), pdpPage.getProductName());
	}

	@And("^The (\\d+) items left message is visible on the PDP$")
	public void theItemsLeftMessageIsVisibleOnThePDP(final int count)
	{
		assertEquals(AlertsProperties.fewItemsRemaining(count),
				pdpPage.getStockUrgMsgForOneSizeProdWUrgentMsgOn());
	}

	@And("^I select an item (.*)")
	public void iSelectSize(final String sizeOfProduct)
	{
		pdpPage.selectSizeOfTheProductLowStock(sizeOfProduct);
	}

	@Then("^The size picker contains the email me message against each size$")
	public void theSizePickerContainsEmailMeMessageAgainstEachSize(final DataTable dataTable)
	{
		final SoftAssert softAssert = new SoftAssert();
		final List<String> sizes = pdpPage.getSizeValues();

		final List<List<String>> data = dataTable.cells();
		for (List<String> datum : data)
		{
			final String size = datum.get(0);

			final String expectedMessage = size + " - " + AlertsProperties.emailMeMessage();

			softAssert.assertTrue(sizes.contains(expectedMessage), "Email me message not found: " + expectedMessage);
		}

		softAssert.assertAll();
	}

	@And("^The size picker contains the few items left message against each size$")
	public void theSizePickerContainsTheFewItemsLeftMessageAgainstEachSize(final DataTable dataTable)
	{
		final SoftAssert softAssert = new SoftAssert();
		final List<String> sizes = pdpPage.getSizeValues();

		final List<List<String>> data = dataTable.cells();
		for (List<String> datum : data)
		{
			final String size = datum.get(0);
			final int stock = Integer.parseInt(datum.get(1));

			final String expectedMessage = size + " - " + AlertsProperties.fewItemsRemaining(stock);

			softAssert.assertTrue(sizes.contains(expectedMessage), "Size/stock message not found: " + expectedMessage);
		}

		softAssert.assertAll();
	}

	@Then("^The sticky add to bag button is visible$")
	public void theStickyAddToBagButtonIsVisible()
	{
		assertTrue(pdpPage.isStickyAddToBagButtonVisible());
	}

	@And("^The product name is visible above the sticky add to bag button$")
	public void theProductNameIsVisibleAboveTheStickyAddToBagButton()
	{
		final WebElement productName = pdpPage.getStickyProductName();
		final WebElement stickyAddToBag = pdpPage.getStickyAddToBagButton();
		assertTrue(productName.isDisplayed());
		assertTrue(productName.getLocation().getY() < stickyAddToBag.getLocation().getY());
	}

	@And("^The product price is visible above the sticky add to bag button$")
	public void theProductPriceIsVisibleAboveTheStickyAddToBagButton()
	{
		final WebElement productPrice = pdpPage.getStickyProductPrice();
		final WebElement stickyAddToBag = pdpPage.getStickyAddToBagButton();
		assertTrue(productPrice.isDisplayed());
		assertTrue(productPrice.getLocation().getY() < stickyAddToBag.getLocation().getY());
	}

	@When("^I scroll down slightly$")
	public void iScrollDownSlightly()
	{
		pdpPage.scrollByCoordinates(50);
	}

	@When("^I scroll to the PDP product name$")
	public void iScrollToThePDPProductName()
	{
		pdpPage.scrollElementIntoView(pdpPage.productName());
		// The name is technically 'in view' but still behind the add to bag button.
		// Scroll a little more to reveal it.
		pdpPage.scrollByCoordinates(pdpPage.getStickyDetailsWrapper().getSize().height);
	}

	@Then("^The product name is not visible above the sticky add to bag button$")
	public void theProductNameIsNotVisibleAboveTheStickyAddToBagButton()
	{
		assertFalse(pdpPage.isStickyProductNameVisible());
	}

	@And("^The product price is not visible above the sticky add to bag button$")
	public void theProductPriceIsNotVisibleAboveTheStickyAddToBagButton()
	{
		assertFalse(pdpPage.isStickyProductPriceVisible());
	}

	@Then("^The sticky add to bag button is not visible$")
	public void theStickyAddToBagButtonIsNotVisible()
	{
		assertFalse(pdpPage.isStickyAddToBagButtonVisible());
	}

	@And("^The size picker does not contains the few items left message against different size$")
	public void theSizePickerDoesNotContainsTheFewItemsLeftMessageAgainstDifferentSize(final DataTable dataTable)
	{
		final SoftAssert softAssert = new SoftAssert();
		final List<String> sizes = pdpPage.getSizeValues();

		final List<List<String>> data = dataTable.cells();
		for (List<String> datum : data)
		{
			final String size = datum.get(0);
			softAssert.assertTrue(sizes.contains(size), "Size not found: " + size);
		}

		softAssert.assertAll();
	}

	@Then("out of stock message is displayed on pdp page")
	public void outOfStockMessageDisplayed()
	{
		assertTrue(pdpPage.outOfStockPdpMessage());
	} //selectSizeWithText

	@Then("low stock message is displayed on cart page")
	public void lowStockMessageIsDisplayed()
	{
		assertTrue(pdpPage.lowStockCartMessage());
	}

	@And("I scroll to the top")
	public void IScrollToTheTop()
	{
		pdpPage.scrollToTop();
	}

	@Then("standard delivery message is displayed on pdp page")
	public void standardDeliveryMessageDisplayed()
	{
		assertTrue(pdpPage.standardDeliveryPdpMessageDisplayed());
	}


	@Then("^stock location is (.*) in the datalayer (sizeData|product) object$")
	public void stockLocationIsSetInTheDatalayerForThe(String location, String datalayer, DataTable table)
	{
		String productCode[] = new String[1];
		String sizeCode[] = new String[1];
		String keyName = datalayer.equals("sizeData") ? "size_code" : "id";
		final AtomicReference<String> stockLocation = new AtomicReference<>("");
		ArrayList productList = (ArrayList) ((JavascriptExecutor) getWebDriver()).executeScript("return nlDatalayer.product");
		List<Map<Object, Object>> productMap = (List<Map<Object, Object>>) productList;
		for (int i = 1; i < table.column(0).size(); i++)
		{
			productCode[0] = Props.getProp(table.cell(i, 0));
			sizeCode[0] = table.cell(i, 1);
			// this loops through product map and filters on the product code and gets the size_data from that product. then loops through size_data map and filters on the size_code and sets the stock location
			if (datalayer.equals("sizeData"))
			{
				productMap.forEach(p -> {
					p.forEach((key, value) -> {
						if (key.equals("id") && value.toString().contains(productCode[0]) && value.toString().endsWith(sizeCode[0]))
						{
							List<Map<Object, Object>> sizeDataMap = (List<Map<Object, Object>>) p.get("size_data");
							sizeDataMap.forEach(s -> {
								s.forEach((key1, value1) -> {
									if (key1.equals(keyName) && value1.toString().equals(sizeCode[0]))
									{
										log.info("stock_location in sizeData array with product code "+productCode[0]+" and sizecode "+sizeCode[0]+"is --"+s.get("stock_location").toString());
										stockLocation.set(s.get("stock_location").toString());
									}
								});
							});
						}
					});
				});
			}
			// this loops through product map and filters on the product code. sets the stock location
			else
			{
				//looping through the TransformedEntryMap and key/value pairs
				productMap.forEach(s -> {
					s.forEach((key, value) -> {
						if (key.equals(keyName) && value.toString().contains(productCode[0]) && value.toString().endsWith(sizeCode[0]))
						{
							log.info("stock_location in product array with product code "+productCode[0]+"is --"+s.get("stock_location").toString());
							stockLocation.set(s.get("stock_location").toString());
						}
					});
				});
			}
			Assert.assertEquals(location, stockLocation.toString());
		}
	}

	@And("carousel contains all necessary components")
	public void carouselContainsAllNecessaryComponents()
	{
		Assert.assertTrue(pdpPage.getCarouselAboveProductComponents());
	}
}
