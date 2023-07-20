package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.step_definitions.gui.ShoppingItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static com.salmon.test.page_objects.gui.HomePage.MAST_HEADER_WISH_LIST_COUNT;
import static com.salmon.test.page_objects.gui.HomePage.MAST_HEADER_WISH_LIST_LINK;
import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;


import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

public class WishListPage extends PageObject
{
	private static final By MAST_HEADER_WISH_LIST_COUNT_MOBILE = By.xpath("//li[@class='masthead__mobile-item']//div[@data-ng-show='savedItemsCount > 0']");
	private static final By SIZE_SELECTOR = By.className("saveditems--select");
	private static final By CHANGE_SIZE = By.className("saveditems__change-size");
	private static final By CHANGE_SIZE_BUTTON = By.className("button-link");
	//    Logger log = LoggerFactory.getLogger(WishListPage.class);
	private Logger log = LogManager.getLogger(WishListPage.class.getName());
	private static final By SAVED_ITEMS_PAGE = By.className("page-saved-items");
	private static final By PRODUCT_WISH_LIST_BUTTON = By.cssSelector("span.ng-isolate-scope > span > button:nth-child(1)");
	private static final By WISH_LIST_LINK = By.xpath("//i[@class='iconf iconf--saved-items-icon']");
	private static final By WISH_LIST_SIDE_PANE = By.xpath("//iframe[contains(@class,'side-panel__iframe')]");
	private static final By WISH_LIST_REGISTER = By.xpath(".//*[@id='body']/main/link-modifier/div[2]/div/div[1]/ul/li[2]/a");

	public List<WebElement> getRegister_tabs()
	{
		return webDriver.findElements(REGISTER__TABS);
	}

	private static final By REGISTER__TABS = By.cssSelector(".button[href*='register']");//By.xpath("//a[contains(@href, 'register')]");
	private static final By WISH_LIST_PRODUCT_NAME = By.xpath("//a[contains(@class,'product-item__name')]");
	private static final By PRODUCT_NAME = By.xpath("//h1[contains(@class,'product-description__name')]");
	private static final By PRODUCT_PRICE = By.className("product-description__price");
	private static final By SAVED_PRODUCT_NAME = By.className("product-item__name");
	private static final By SAVED_PRODUCT_COUNT = By.cssSelector(".saveditems-column.ng-scope");
	private static final By SAVED_PRODUCT_PRICE = By.className("saveditems__price");
	private static final By SAVED_PRODUCTS = (By.className("saveditems__size-colour"));
	private static final By BAG_COUNTER = By.className("saveditems__actionbar__header");
	private static final By VIEW_FULL_PAGE_BUTTON = By.xpath(".//*[@id='body']/main/link-modifier/div[2]/div/div[3]/div[1]/ul/li[2]/a");
	private static final By WISH_LIST_BAG_COUNT = By.className("header__bagcount");
	private static final By ITEM_SAVED_MESSAGE = By.className("product-details__save-item-success");
	private static final By LISTING_ITEM = By.className("saveditems__selectioncontent");
	private static final By PLP_ITEM = By.className("plp-item");
	private static final By LISTING_ITEM_NAME = By.className("product-item__name");
	private static final By LISTING_ITEM_PRICE = By.className("price");
	private static final By LISTING_ITEM_SAVE = By.className("product-item__saveditem");
	private static final By LISTING_PRODUCT_QUICK_VIEW = By.xpath("//a[@class='link link--nounderline product-item__quick-view-link ng-scope']");
	private static final By EMPTY_SAVED_ITEMS_BAG = By.className("bag-panel-empty__menu");
	private static final By REMOVE_SAVED_ITEM = By.className("saveditems__remove");
	private static final By MOVE_ALL_TO_BAG = By.xpath("//button[text()='MOVE ALL TO BAG']");
	private static final By CART_PAGE = By.xpath("//a[text()='Bag']");
	private static final By CART_ITEM_DESCRIPTION = By.xpath("//a[contains(@class,'link--nounderline ng-binding') and contains(@href,'/uk/uk')]");
	private static final By SEARCH_BUTTON = By.xpath("(//button[@type='submit'])[1]");
	private static final By REMOVE_ITEM_ICON = By.cssSelector("[data-ng-click='removeItem(product)']");
	private final By SEARCH_FIELD = By.id("js-site-search-input");
	private final By SIZE_SELCTOR_DROPDOWN = By.xpath("//select[contains(@class,'saveditems--select')]");
	@FindBy(css = "button[href]")
	public WebElement productImageOnPdpCurrent;
	private By productTitle = By.cssSelector(".product-heading>h1");
	private By productPrice = By.cssSelector(".product-heading>.product-description__prices>span");
	private By SIMILIAR_ITEMS_BUTTON = By.cssSelector(".button.button--secondary.button--secondary-white.button--full-width.saveditems__btn-action--similar");
	private By SELECT_SIZE_DROP_DOWN_OPTIONS_SELECTOR = By.xpath("//select[contains(@class,'saveditems--select')]/option");
	private By SELECTED_SIZE_OPTION = By.xpath("//div[@class='saveditems__urgency-message ng-scope']");
	public String productName;

	private CheckOutPage checkOutPage;

	private HomePageOld homePage;

	private PlpPage plpPage;
	private HomePage homePageNew = new HomePage();


	public WebElement productWishListButton()
	{
		return waitForExpectedElement(PRODUCT_WISH_LIST_BUTTON);
	}

	public WebElement wishListLink()
	{
		WebElement heartIconElement;
		heartIconElement = waitForAndGetElement(MAST_HEADER_WISH_LIST_LINK, 10);
		return heartIconElement;
	}

	private WebElement wishListSidePane()
	{

		return waitForAndGetElement(WISH_LIST_SIDE_PANE, DEFAULT_TIMEOUT);
	}

	private WebElement registerButton()
	{
		return webDriver.findElement(WISH_LIST_REGISTER);
	}

	private WebElement productNamePDP()
	{
		return waitForExpectedElement(PRODUCT_NAME);
	}

	private WebElement productPrice()
	{
		return waitForExpectedElement(PRODUCT_PRICE);
	}

	private WebElement bagCounter()
	{
		return waitForExpectedElement(BAG_COUNTER);
	}

	public WebElement wishListBagCount()
	{
		pause(1500);
		if (IS_MOBILE)
		{
			return waitForAndGetElement(MAST_HEADER_WISH_LIST_COUNT_MOBILE, DEFAULT_TIMEOUT);

		}
		return waitForAndGetElement(MAST_HEADER_WISH_LIST_COUNT, DEFAULT_TIMEOUT);
	}

	private List<WebElement> itemsName()
	{
		return webDriver.findElements(SAVED_PRODUCT_COUNT);
	}

	private List<WebElement> itemsPrice()
	{
		return webDriver.findElements(SAVED_PRODUCT_PRICE);
	}

	private List<WebElement> itemsDescriptions()
	{
		return webDriver.findElements(SAVED_PRODUCTS);
	}

	private List<WebElement> removeSavedItems()
	{
		return webDriver.findElements(REMOVE_SAVED_ITEM);
	}

	private Boolean itemIsAddedToWishList()
	{
		return elementPresence(ITEM_SAVED_MESSAGE);
	}

	private Boolean sidePanePresence()
	{
		return elementPresence(WISH_LIST_SIDE_PANE);
	}

	public Boolean savedItemsPage()
	{
		getWebDriver().switchTo().defaultContent();
		waitForExpectedElement1(SAVED_ITEMS_PAGE, 10);
		return elementPresence(SAVED_ITEMS_PAGE);
	}

	private Boolean itemIsAlreadyInWishList()
	{
		waitForElementVisible(By.cssSelector(".main-container>.product-details-page>.product-side-wrapper>section>.product-ctas>.product-details__cta-wrapper>span > span > .button"), 10);
		By by = By.cssSelector(".main-container>.product-details-page>.product-side-wrapper>section>.product-ctas>.product-details__cta-wrapper>span > span > .button");

		return getWebDriver().findElement(by).findElement(By.className("ng-scope")).getAttribute("data-ng-if").equals("isInSavedItems");
	}

	public void switchToSavedItemsSidePane()
	{
		webDriver.switchTo().frame(webDriver.findElement(WISH_LIST_SIDE_PANE));
	}

	public void switchToMainPage()
	{
		webDriver.switchTo().defaultContent();
	}


	private void addItemToWishList()
	{
		By by = By.cssSelector(".main-container>.product-details-page>.product-side-wrapper>section>.product-ctas>.product-details__cta-wrapper>span>.button");
		if (isElementPresent(webDriver.findElement(by), 3))
		{
			try
			{
				scrollForLocationFocusAndClick(by, 5);
			}
			catch (Exception e)
			{
				hoverAndClick(getWebDriver().findElement(by));
			}
		}
	}

	public void removeSavedItemsFromLit(String items) throws Exception
	{
		if (sidePanePresence())
		{
			switchToSavedItemsSidePane();
		}
		Integer itemCount = removeSavedItems().size();

		switch (items)
		{
			default:

				for (int i = 0; i < itemCount; i++)
				{
					webDriver.findElement(REMOVE_SAVED_ITEM).click();
					Thread.sleep(1000);
				}
				break;
			case "first":
				webDriver.findElement(REMOVE_SAVED_ITEM).click();
			case "second":
				removeSavedItems().get(1).click();
		}
		switchToMainPage();
	}

	public ItemsDescription addItemsFromListingsQuickly(int numberOfItems) throws Exception
	{
		waitForPageLoad();
		waitForAndGetElement(LISTING_ITEM_NAME, DEFAULT_TIMEOUT);
		ItemsDescription itemsDescription = new ItemsDescription();

		for (int i = 0; i < numberOfItems; i++)
		{
			Thread.sleep(100);
			itemsDescription.getName().add(webDriver.findElements(PLP_ITEM).get(i).findElement(LISTING_ITEM_NAME).getText());
			itemsDescription.getPrice().add(webDriver.findElements(PLP_ITEM).get(i).findElement(LISTING_ITEM_PRICE).getText());
			ShoppingItem shoppingItem = new ShoppingItem();
			shoppingItem.setName(webDriver.findElements(PLP_ITEM).get(i).findElement(LISTING_ITEM_NAME).getText());
			shoppingItem.setPrice(webDriver.findElements(PLP_ITEM).get(i).findElement(LISTING_ITEM_PRICE).getText());
			log.info("target saved item:{}, {} ", shoppingItem.getName(), shoppingItem.getPrice());
			// dataUnderTest.getSavedItems().add(shoppingItem);
		}

		//This second loop is due to stale element state being caused after wishlist icon clicks
		for (int i = 0; i < numberOfItems; i++)
		{
			Thread.sleep(1000);
			if (IS_MOBILE)
			{
				By savedItem = By.xpath(".//i[contains(@class,'product-item__saveditem-icon')]");
				webDriver.findElements(PLP_ITEM).get(i).findElement(savedItem).click();
			}
			else
			{
				webDriver.findElements(PLP_ITEM).get(i).findElement(LISTING_ITEM_SAVE).click();
			}
		}

		assertEquals("Saved items counter is not the same as saved items", wishListBagCount().getText(), String.valueOf(numberOfItems));

		itemsDescription.setItemCount(wishListBagCount().getText());

		return itemsDescription;
	}

	private void searchItemByCatNumber(String catNumber)
	{
		webDriver.findElement(SEARCH_FIELD).sendKeys(catNumber);
		webDriver.findElement(SEARCH_BUTTON).click();

		if (checkOutPage.searchPageStillAvailable())
		{
			homePage.selectProduct().click();
		}
	}

	public ItemsDescription addItemsToWishList(String... catNumbers)
	{
		ItemsDescription itemsDescription = new ItemsDescription();

		for (String catNumber : catNumbers)
		{
			searchItemByCatNumber(catNumber);

			productName = productNamePDP().getText();


			if (checkOutPage.sizeDropDownPresent())
			{
				itemsDescription.getSize().add(checkOutPage.selectSizeOfTheProduct());
				while (!checkOutPage.addToCartButtonPresent() && (checkOutPage.preOrderEmailPresent() || checkOutPage.stockNotAvailable()))
				{
					//checkOutPage.searchForInStockRandomProduct("womens");
					homePage.selectProduct().click();
					if (checkOutPage.sizeDropDownPresent())
					{
						itemsDescription.getSize().add(checkOutPage.selectSizeOfTheProduct());
					}
					else
					{
						itemsDescription.getSize().add(checkOutPage.oneSize().getText().toLowerCase());
						break;
					}
				}
			}
			else
			{
				itemsDescription.getSize().add(checkOutPage.oneSize().getText().toLowerCase());
			}
			itemsDescription.getName().add(productNamePDP().getText());
			itemsDescription.getPrice().add(productPrice().getText());

			addItemToWishList();

			assertTrue(String.format("Item %s has not been added to wishlist", catNumber), itemIsAddedToWishList());
		}

		String numberOfItems = String.valueOf(catNumbers.length);
		itemsDescription.setItemCount(numberOfItems);

		assertEquals("Saved items counter is not the same as saved items", wishListBagCount().getText(), String.valueOf(catNumbers.length));

		return itemsDescription;
	}

	public ItemsDescription savedItems()
	{
		ItemsDescription itemsDescription = new ItemsDescription();

		if (sidePanePresence())
		{
			switchToSavedItemsSidePane();
		}

		for (WebElement itemName : itemsName())
		{
			itemsDescription.getName().add(itemName.getText());
		}

		for (WebElement itemPrice : itemsPrice())
		{
			itemsDescription.getPrice().add(itemPrice.getText());
		}

		for (WebElement itemsText : itemsDescriptions())
		{
			String size = null;
			try
			{
				size = itemsText.getText().split("Size:")[1].trim();
			}
			catch (Exception e)
			{

			}
			itemsDescription.getSize().add(size);

		}

		itemsDescription.setItemCount(itemCount());

		switchToMainPage();

		return itemsDescription;
	}


	public boolean noSavedItems()
	{
		switchToSavedItemsSidePane();
		try
		{
			pause(1000);
			waitForAndGetElement(EMPTY_SAVED_ITEMS_BAG, 10).isDisplayed();
//            switchToMainPage();
			return true;
		}
		catch (NoSuchElementException e)
		{
//            switchToMainPage();
			return false;
		}

	}

	public boolean selectSavedItemsFullPage() throws InterruptedException
	{
		pause(3000);
		switchToWishListPaneIfExists();
		pause(1000);
		getElementWithText(By.cssSelector(".saveditems-container li a"), "View full page").click();
		return savedItemsPage();
	}

	public boolean selectWishListView()
	{
		pause(1000);
		log.info("Checking that saved items side panel is opened");
		if (IS_MOBILE)
		{
			homePageNew.clickMySavedItems();
		}
		else
		{
			waitForAndGetElement(MAST_HEADER_WISH_LIST_LINK, DEFAULT_TIMEOUT).click();
		}
		waitForPageLoad();
		return wishListSidePane().isDisplayed();
	}

	public void selectRegister()
	{
		if (sidePanePresence())
		{
			switchToSavedItemsSidePane();
		}
		registerButton().click();
	}

	public boolean registerOptionsPresence()
	{
		switchToSavedItemsSidePane();
		try
		{
			webDriver.findElement(REGISTER__TABS);
			switchToMainPage();
			return true;
		}
		catch (NoSuchElementException e)
		{
			switchToMainPage();
			return false;
		}
	}

	public ItemsDescription selectItemInWishListSidePane(int item)
	{
		ItemsDescription itemsDescription = new ItemsDescription();

//        switchToSavedItemsSidePane();
		WebElement selectedItem = webDriver.findElements(LISTING_ITEM).get(item);
		itemsDescription.getName().add(selectedItem.findElement(SAVED_PRODUCT_NAME).getText());
		itemsDescription.getPrice().add(selectedItem.findElement(SAVED_PRODUCT_PRICE).getText());
		//String size = webDriver.findElement(SAVED_PRODUCTS).getText().split("\\n")[3];
		//itemsDescription.getSize().add(size.split("\\|")[1]);
		//new Actions(webDriver.).click(selectedItem).perform();
		webDriver.findElement(By.className("plp-carousel")).click();
		return itemsDescription;
	}

	public boolean removeFromWishList(String selectSize)
	{
		if (elementPresent(By.className("product-sizes__select-wrapper")))
		{

//            selectFromDropdownByPatialText(By.xpath("//select[contains(@class,'select--pdp')]"), By.xpath("//select[contains(@class,'select--pdp')]/option"), selectSize);
		}
		if (itemIsAlreadyInWishList())
		{
			waitForElementVisible(By.cssSelector(".main-container>.product-details-page>.product-side-wrapper>section>.product-ctas>.product-details__cta-wrapper>span > span > .button"), 10);
			By by = By.cssSelector(".main-container>.product-details-page>.product-side-wrapper>section>.product-ctas>.product-details__cta-wrapper>span > span > .button");

			try
			{
				getWebDriver().findElement(by).click();
//                clickWhenClickable(webDriver.findElement(by), 5);
			}
			catch (Exception e)
			{
				hoverAndClick(getWebDriver().findElement(by));
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	private String itemCount()
	{
		if (sidePanePresence())
		{
			switchToSavedItemsSidePane();
		}
		String count = bagCounter().getText().replaceAll("\\D+", "");
		switchToMainPage();
		return count;
	}

	public boolean viewFullPage() throws InterruptedException
	{
		switchToWishListPaneIfExists();
		getElementWithText(By.cssSelector(".saveditems-container li a"), "VIEW FULL PAGE").click();
		return savedItemsPage();
	}

	public String getItemName()
	{
		if (sidePanePresence())
		{
			switchToSavedItemsSidePane();
		}
		productName = webDriver.findElement(WISH_LIST_PRODUCT_NAME).getText().trim();
		return productName;
	}

	public void switchToWishListPaneIfExists()
	{
		if (sidePanePresence())
		{
			switchToSavedItemsSidePane();
		}

	}

	public String getProductTitle()
	{
		waitForElementVisible(webDriver.findElement(productTitle), 10);
		if (!webDriver.findElements(productTitle).isEmpty())
		{
			return webDriver.findElement(productTitle).getText();
		}
		return null;
	}

	public String getProductPrice()
	{
		waitForElementVisible(webDriver.findElement(productPrice), 10);
		if (!webDriver.findElements(productPrice).isEmpty())
		{
			return webDriver.findElement(productPrice).getText();
		}
		return null;
	}

	public void clickDropdownSizepicker()
	{
		scrollElementIntoView(waitForExpectedElement(SIZE_SELCTOR_DROPDOWN));
		waitForExpectedElement(SIZE_SELCTOR_DROPDOWN).click();
	}

	public void selectSizeFromDropDownWUrgMsgOn(final Integer itemIndex)
	{
		if (!isElementVisible(SIZE_SELECTOR, DEFAULT_TIMEOUT))
		{
			getChangeSizeButton().click();
		}
		new Select(waitForExpectedElement(SIZE_SELECTOR)).selectByIndex(itemIndex);
	}

	private WebElement getChangeSizeButton()
	{
		return waitForExpectedElement(CHANGE_SIZE).findElement(CHANGE_SIZE_BUTTON);
	}

	public String getSizeTextFromDropDownWUrgentMsgOn(final Integer itemIndex)
	{
		return new Select(waitForExpectedElement(SIZE_SELECTOR))
				.getOptions()
				.get(itemIndex)
				.getText();
	}

	public static class ItemsDescription
	{
		private final List<String> name = new ArrayList<>();
		private final List<String> price = new ArrayList<>();
		private final List<String> size = new ArrayList<>();
		private String itemCount;

		public List<String> getName()
		{
			return name;
		}

		public List<String> getPrice()
		{
			return price;
		}

		public List<String> getSize()
		{
			return size;
		}

		public String getItemCount()
		{
			return itemCount;
		}

		private void setItemCount(String itemCount)
		{
			this.itemCount = itemCount;
		}
	}

	public boolean getSimiliarItemsButtonIsDisplayed()
	{
		pause(2000);
		switchToSavedItemsSidePane();
		return waitForAndGetElement(SIMILIAR_ITEMS_BUTTON, DEFAULT_TIMEOUT).isDisplayed();
	}

	public int checkSavedItemsPresentOnMobile()
	{
		scrollElementIntoView(By.xpath("//div[@data-ng-repeat='entry in savedItemEntries']"));
		List<WebElement> savedItems = webDriver.findElements(By.xpath("//div[@data-ng-repeat='entry in savedItemEntries']"));
		return savedItems.size();
	}
}
