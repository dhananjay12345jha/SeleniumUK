package com.salmon.test.config;

import com.salmon.test.framework.helpers.utils.Credentials;
import com.salmon.test.models.gui.CheckoutModel;
import com.salmon.test.page_objects.gui.*;


import com.salmon.test.page_objects.mobile.MobileContactsPage;

import com.salmon.test.step_definitions.gui.DataUnderTest;
import com.salmon.test.step_definitions.gui.GiftCardSection;

import com.salmon.test.step_definitions.gui.ProductCodeProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;


@Configuration
public class Config
{

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public HomePage homePage()
	{
		return new HomePage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public AddNewBillingAddressPopup addNewBillingAddressPopup()
	{
		return new AddNewBillingAddressPopup();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public BackofficePage backofficePage()
	{
		return new BackofficePage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public MyAccountPage myAccountPage()
	{
		return new MyAccountPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public LoginPage loginPage()
	{
		return new LoginPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public LandingPage landingPage()
	{
		return new LandingPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public HomePageOld homePageOld()
	{
		return new HomePageOld();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public HelpPage helpPage()
	{
		return new HelpPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public Features features()
	{
		return new Features();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public DeliveryPassPage deliveryPassPage()
	{
		return new DeliveryPassPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public CreateNewAccountPage createNewAccountPage()
	{
		return new CreateNewAccountPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public CookieBotPage cookieBotPage()
	{
		return new CookieBotPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public ContactUsFromPage contactUsFromPage()
	{
		return new ContactUsFromPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public ChopinPage chopinPage()
	{
		return new ChopinPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public CheckOutPage checkOutPage()
	{
		return new CheckOutPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public CheckoutLoginPage checkoutLoginPage()
	{
		return new CheckoutLoginPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public CheckoutAndPaymentsPage checkoutAndPaymentsPage()
	{
		return new CheckoutAndPaymentsPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public MyBagPage myBagPage()
	{
		return new MyBagPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public MyContactPreferencesPage myContactPreferencesPage()
	{
		return new MyContactPreferencesPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public MyPaymentCardsPage myPaymentCardsPage()
	{
		return new MyPaymentCardsPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public MySavedCollectionPage mySavedCollectionPage()
	{
		return new MySavedCollectionPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public MySavedItemsPage mySavedItemsPage()
	{
		return new MySavedItemsPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public MyAccountsPage myAccountdPage()
	{
		return new MyAccountsPage();
	}



	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public MyStoreCardsPage myStoreCardsPage()
	{
		return new MyStoreCardsPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public NewLookHelper newLookHelper()
	{
		return new NewLookHelper();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public OrderConfirmationPage orderConfirmationPage()
	{
		return new OrderConfirmationPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public PdpPage pdpPage()
	{
		return new PdpPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public PersonalDetailsPage personalDetailsPage()
	{
		return new PersonalDetailsPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public PlpPage plpPage()
	{
		return new PlpPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public PlpPageOld plpPageOld()
	{
		return new PlpPageOld();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public PromotionsPage promotionsPage()
	{
		return new PromotionsPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public PudoSection pudoSection()
	{
		return new PudoSection();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public RTBFPage rtbfPage()
	{
		return new RTBFPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public SavedCollectionPointsPage savedCollectionPointsPage()
	{
		return new SavedCollectionPointsPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public SecurityPage securityPage()
	{
		return new SecurityPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public SignInPage signInPage()
	{
		return new SignInPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public SmartEditPage smartEditPage()
	{
		return new SmartEditPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public StaffDiscount staffDiscount()
	{
		return new StaffDiscount();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public StoreLocatorPage storeLocatorPage()
	{
		return new StoreLocatorPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public TrackMyOrderPage trackMyOrderPage()
	{
		return new TrackMyOrderPage();
	}


	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public WishListPage wishListPage()
	{
		return new WishListPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public MobileContactsPage mobileContactsPage()
	{
		return new MobileContactsPage();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public GiftCardSection giftCardSection() { return new GiftCardSection();}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public MyOrdersPage myOrdersPage() { return new MyOrdersPage();}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public MyAddressPage myAddressPage() { return new MyAddressPage();}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public CheckoutModel checkoutModel()
	{
		return new CheckoutModel();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public ProductCodeProvider productCodeProvider()
	{
		return new ProductCodeProvider();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public Credentials credentials()
	{
		return new Credentials();
	}

	@Bean
	@Lazy
	@Scope("cucumber-glue")
	public DataUnderTest dataUnderTest()
	{
		return new DataUnderTest();
	}

}
