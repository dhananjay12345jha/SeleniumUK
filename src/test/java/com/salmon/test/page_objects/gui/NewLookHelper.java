package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.framework.helpers.utils.Credentials;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;

/**
 * Created by gates on 06/03/18.
 */
public class NewLookHelper
{
	@Autowired
	private CheckOutPage checkOutPage;
	@Autowired
	private HomePage homePage;
	@Autowired
	private Credentials credentials;
	@Autowired
	private CreateNewAccountPage createNewAccountPage;
	@Autowired
	private PdpPage productDetailsPage;
	@Autowired
	private MyBagPage myBagPage;
	@Autowired
	private CheckoutLoginPage checkoutLoginPage;
	@Autowired
	private CheckoutAndPaymentsPage checkoutAndPaymentsPage;

	public void createNewAccount()
	{
		homePage.navigateToCreateNewAccountPageByClickingHeaderLink();
		credentials = createNewAccountPage.createNewAccountWithCorrectDetails();
	}


	public void checkoutAProduct() throws Throwable
	{
		searchAndAddADefaultProductToBasket();
		checkOutPage.basketIcon().click();
		//checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
		checkOutPage.clickCheckoutButton();
	}

	public void searchAndAddADefaultProductToBasket()
	{
		String product = Props.getProp("productCode_stock");
		searchAndAddAProductToBasket(product);
	}

	public void searchAndAddAProductToBasket(String product)
	{
		if (IS_MOBILE)
		{
			homePage.clickMobileSearchIcon();
		}
		checkOutPage.searchField().sendKeys(product);
		checkOutPage.searchButton().click();
		checkOutPage.pause(3000);
		String productName = checkOutPage.getWebDriver().findElement(By.xpath("//a[contains(@class,'product-item__name')]")).getText().trim();
		myBagPage.setProductName(productName);
		homePage.selectRandomProduct();
		checkOutPage.selectFirstAvailableSizeOfTheProduct();
		checkOutPage.addToCartButton().click();
	}

	public void purchaseAProduct(String paymentType, String searchTerm)
	{
		searchAndSelectProductThenCheckout(searchTerm);
		checkoutAndPaymentsPage.makePaymentForDelivery(paymentType, "Visa", "4111111111111111");
	}

	public void purchaseARandomProduct(String paymentType)
	{
		searchAndSelectProductThenCheckout();
		checkoutAndPaymentsPage.makePaymentForDelivery(paymentType, "Visa", "4111111111111111");
	}

	public void purchaseARandomProductAsGuestForDelivery(String paymentType)
	{
		searchAndSelectProductThenCheckout();
		checkoutLoginPage.enterEmailAddressForGuestCheckoutField();
		checkoutLoginPage.clickGuestCheckoutButton();
		checkoutAndPaymentsPage.makePaymentForDelivery(paymentType, "Visa", "4111111111111111");
	}

	public void purchaseARandomProductAsGuestForCollection(String paymentType)
	{
		searchAndSelectProductThenCheckout();
		checkoutLoginPage.enterEmailAddressForGuestCheckoutField();
		checkoutLoginPage.clickGuestCheckoutButton();
		checkoutAndPaymentsPage.makePaymentForCollection(paymentType, "Visa", "4111111111111111");
	}

	public void searchAndSelectProductThenCheckout()
	{
		homePage.searchForProductUsingRandomProductCode();
		homePage.selectRandomProduct();
		productDetailsPage.selectProductAndMoveToMyBagPage();
		checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
		myBagPage.clickPrimarySecureCheckoutButton();
	}

	public void searchAndSelectProductThenCheckout(String searchTerm)
	{
		homePage.searchForProduct(searchTerm);
		homePage.selectRandomProduct();
		productDetailsPage.selectProductAndMoveToMyBagPage();
		checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
		myBagPage.clickPrimarySecureCheckoutButton();
	}

	public void addProductAndNavigateToPaymentOnMobile(String location)
	{
		homePage.selectRandomProduct();
		homePage.waitForPageLoad();
		productDetailsPage.selectProductAndMoveToMyBagPage();
		checkOutPage.selectQuantityOfLineItem("Qty: 5",1);
		myBagPage.clickPrimarySecureCheckoutButton();
		checkoutLoginPage.enterEmailAddressForGuestCheckoutField();
		checkoutLoginPage.clickGuestCheckoutButton();
		checkoutAndPaymentsPage.enterAddressForCollection(location);
		checkOutPage.enterMandatoryMobileField();
	}

	public void enterCardDetails(String number)
	{
		checkOutPage.fillCardDetails(number);
	}

//    public void enterCardDetails(){
//        checkOutPage.fillCardDetails();
//    }

	public void searchForProductAndSelectSize(String productCode, String size)
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
		homePage.waitForPageLoad();
		productDetailsPage.selectSizeOfTheProduct(size);
		productDetailsPage.clickAddToBagButton();
	}

	public void searchForProductAndSelectSizeLowStock(String productCode, String size)
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
		homePage.waitForPageLoad();
		productDetailsPage.selectSizeOfTheProductLowStock(size);
		productDetailsPage.clickAddToBagButton();
	}

	public void searchForProductAndSelectBySizeText(String productCode, String size)
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
		homePage.waitForPageLoad();
		productDetailsPage.selectSizeWithText(size);
		productDetailsPage.clickAddToBagButton();
	}

	public static boolean getFeatureStatus(final String featureName)
	{
		return Boolean.parseBoolean(Props.getFeatureStatus(featureName));
	}
	
	public void searchSingleSizeProductThenCheckout(String searchTerm)
	{
		if (IS_MOBILE)
		{ 
			homePage.mobileInsertTextAndSearch((Props.getProp(searchTerm)));
		}
		else {
			homePage.searchForProduct(Props.getProp(searchTerm));
		}		
		homePage.selectRandomProduct();
		productDetailsPage.selectProductAndMoveToMyBagPage();
		myBagPage.clickPrimarySecureCheckoutButton();
	}

}
