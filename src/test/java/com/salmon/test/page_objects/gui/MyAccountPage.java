package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.properties.NavigationProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.salmon.test.framework.helpers.WebDriverHelper.IS_MOBILE;
import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;



public class MyAccountPage extends PageObject {

    private Logger log = LogManager.getLogger(MyAccountPage.class.getName());
    private static final By ACCOUNT_DASHBOARD = By.className("my-account-dashboard");
    private static final By MY_ADDRESS_LINK = By.linkText(NavigationProperties.addressBookLink());
    private static final By MY_ADDRESS_DASHBOARD_LINK = By.xpath("//*[contains(@href,'address-book')]");
    private static final By MY_ORDERS_LINK = By.cssSelector(".my-account-dashboard__inner.link--nounderline[href*='orders']");
    private static final By MY_PAYMENTS_LINK = By.xpath("//*[contains(@href,'saved-cards')]");
    private static final By MY_STORE_CARDS_LINK = By.cssSelector(".my-account-dashboard__inner.link--nounderline[href*='saved-store-cards']");
    private static final By MY_PERSONAL_DETAILS_LINK = By.cssSelector(".my-account-dashboard__inner.link--nounderline[href*='profile']");
    private static final By SAVED_COLLECTION_POINTS_LINK = By.cssSelector(".my-account-dashboard__inner.link--nounderline[href*='collection-locations']");
    private static final By MY_CONTACT_PREFERENCES = By.cssSelector(".my-account-dashboard__inner.link--nounderline[href*='contact-preferences']");
    private static final By MY_SAVED_ITEMS_LINK = By.cssSelector(".my-account-dashboard__inner.link--nounderline[href*='saved-items']");
    private static final By DELIVERY_PASS_LINK = By.cssSelector(".my-account-dashboard__inner.link--nounderline[href*='delivery-pass']");
    private static final By RECENT_ORDERS_LABEL = By.cssSelector(".account__header.account__header--left-align");
    private static final By VIEW_ALL_MY_ORDERS_BUTTON_LABEL = By.cssSelector(".button.button--secondary.button--secondary-black.button--full-width-tablet");
    public static final By MY_ACCOUNT_LINKS = By.cssSelector(".my-account-dashboard__inner.link--nounderline");
    private static final By MY_ACCOUNT_LABEL = By.className("account__header");
    private By PAYMENT_CARDS = By.className("account-grid--saved-cards");
    private By SAVED_CARD_NUMBER = By.className("saved-cards__card-number");
    private By pageTitle = By.cssSelector(".my-account-dashboard>h2");
    static final By MY_ACCOUNT_HOME = By.cssSelector(".account__nav-item>a[href*='/uk/my-account']");
    private static final By MANAGE_CONTACT_PREFERENCES = By.className("consent__header");
    private static final By DELETE_CARD_BTN = By.cssSelector(".saved-cards__card-data-link.button-link");
    private static final By DELETE_CARD_BTN_POP_UP = By.cssSelector(".button.button--seconday.button--secondary-black.saved-cards__modal-button.ng-scope");
    private static final By NO_SAVED_CARDS_MSG = By.className("saved-cards__empty-title");


    public boolean isIDAssociated(String ID) {
        //return waitForExpectedElement(By.xpath("//span[text()='" + ID + "']"));
        try {
            getWebDriver().findElements(By.cssSelector(".account-card span")).stream().filter(a -> a.getText().contains(ID)).findFirst();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getMyAccountHeaderLabel() {
        waitForAndGetElement(MY_ACCOUNT_LABEL, DEFAULT_TIMEOUT);
        if(BROWSER.contains("firefox"))
        {
            pause(3000);
        }
        return webDriver.findElement(MY_ACCOUNT_LABEL).getText();
    }

	public void clickMyAddressLink()
    {
        if (isElementVisible(ACCOUNT_DASHBOARD))
        {
	        try
	        {
		        waitForExpectedElement(MY_ADDRESS_DASHBOARD_LINK, DEFAULT_TIMEOUT).click();
	        }
	        catch (Exception e)
	        {
		        waitForExpectedElement(MY_ADDRESS_DASHBOARD_LINK, DEFAULT_TIMEOUT).click();
	        }
        }
        else
        {
            waitForExpectedElement(MY_ADDRESS_LINK, DEFAULT_TIMEOUT).click();
        }
	}

    public void clickMyContactPreferences() {
        waitForAndGetElement(MY_CONTACT_PREFERENCES, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(MY_CONTACT_PREFERENCES).isEmpty()) {
            webDriver.findElement(MY_CONTACT_PREFERENCES).click();
        }
    }

    public void clickMyPersonalDetails(){
        waitForAndGetElement(MY_PERSONAL_DETAILS_LINK, DEFAULT_TIMEOUT).click();
    }

    public void clickMyStoreCards() {
        waitForAndGetElement(MY_STORE_CARDS_LINK, DEFAULT_TIMEOUT).click();
    }

    public void clickMyPaymentCards() {
        clickByJavaScriptExecutor(waitForAndGetElement(MY_PAYMENTS_LINK,5));
        waitForPageLoad();
    }

    public void clickMyOrders() {
        waitForAndGetElement(MY_ORDERS_LINK, DEFAULT_TIMEOUT).click();
    }

    public String getMyAddressLabel() {
        waitForAndGetElement(MY_ADDRESS_LINK, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(MY_ADDRESS_LINK).isEmpty()) {
            return webDriver.findElement(MY_ADDRESS_LINK).getText();
        }
        return null;
    }

    public String getMyPaymentsLabel() {
        waitForAndGetElement(MY_PAYMENTS_LINK, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(MY_PAYMENTS_LINK).isEmpty()) {
            return webDriver.findElement(MY_PAYMENTS_LINK).getText();
        }
        return null;
    }

    public String getMyStoreCardsLabel() {
        waitForAndGetElement(MY_STORE_CARDS_LINK, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(MY_STORE_CARDS_LINK).isEmpty()) {
            return webDriver.findElement(MY_STORE_CARDS_LINK).getText();
        }
        return null;
    }

    public String getMyPersonalDetailsLabel() {
        waitForAndGetElement(MY_PERSONAL_DETAILS_LINK, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(MY_PERSONAL_DETAILS_LINK).isEmpty()) {
            return webDriver.findElement(MY_PERSONAL_DETAILS_LINK).getText();
        }
        return null;
    }

    public String getSavedCollectionPointsLabel() {
        waitForAndGetElement(SAVED_COLLECTION_POINTS_LINK, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(SAVED_COLLECTION_POINTS_LINK).isEmpty()) {
            return webDriver.findElement(SAVED_COLLECTION_POINTS_LINK).getText();
        }
        return null;
    }

    public String getMySavedItemsLabel() {
        waitForAndGetElement(MY_SAVED_ITEMS_LINK, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(MY_SAVED_ITEMS_LINK).isEmpty()) {
            return webDriver.findElement(MY_SAVED_ITEMS_LINK).getText();
        }
        return null;
    }

    public String getManageContactPreferencesHeader() {
        return waitForAndGetElement(MANAGE_CONTACT_PREFERENCES, DEFAULT_TIMEOUT).getText();
    }

    public String getDeliveryPassLabel() {
        waitForAndGetElement(DELIVERY_PASS_LINK, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(DELIVERY_PASS_LINK).isEmpty()) {
            return webDriver.findElement(DELIVERY_PASS_LINK).getText();
        }
        return null;
    }

    public String getRecentOrdersLabel() {
        waitForAndGetElement(RECENT_ORDERS_LABEL, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(RECENT_ORDERS_LABEL).isEmpty()) {
            return webDriver.findElement(RECENT_ORDERS_LABEL).getText();
        }
        return null;
    }

    public String getViewAllByOrdersButtonLabel() {
        waitForAndGetElement(VIEW_ALL_MY_ORDERS_BUTTON_LABEL, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(VIEW_ALL_MY_ORDERS_BUTTON_LABEL).isEmpty()) {
            return webDriver.findElement(VIEW_ALL_MY_ORDERS_BUTTON_LABEL).getText();
        }
        return null;
    }

    public void waitForLoadedList(){
        if(IS_MOBILE){
            try{
                waitForListLoaded(webDriver.findElements(MY_ACCOUNT_LINKS), 3, 5);
            }
            catch(Exception e){
                pause(2500);
            }
        }
        else{
            waitForListLoaded(webDriver.findElements(MY_ACCOUNT_LINKS), 3, 5);
        }
    }

    public void selectMyAccountLinks(String category) {
        log.info("### Looking for my account page item {}", category);
        waitForAndGetElement(MY_ACCOUNT_LINKS,DEFAULT_TIMEOUT);
        waitForLoadedList();
        clickWhenClickable(webDriver.findElements(MY_ACCOUNT_LINKS).stream().filter(a -> a.getText().contains(category.trim())).findFirst().get(), 5);
    }

    public List<String> selectMyAccountLinksLabel(String category) {
        log.info("### Looking for Mega menu item {}", category);
        waitForAndGetElement(MY_ACCOUNT_LINKS,DEFAULT_TIMEOUT);
        waitForLoadedList();
        return webDriver.findElements(MY_ACCOUNT_LINKS).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<WebElement> getPaymentCards() {
        waitForAndGetElement(PAYMENT_CARDS, 10);
        //new WebDriverWait(webDriver,10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(PAYMENT_CARDS));
        return webDriver.findElements(PAYMENT_CARDS);
    }

    public boolean isSavedCardExist(String lastDigits) {
        boolean flag = false;
        for (WebElement e : getPaymentCards()) {
            if (e.findElement(SAVED_CARD_NUMBER).getText().contains(lastDigits)) {
                flag = true;
            }
        }
        return flag;
    }

    public String getPageTitle() {
        return waitForExpectedElement(pageTitle).getText();
    }


    public void deleteCard()
    {
        waitForAndGetElement(DELETE_CARD_BTN, DEFAULT_TIMEOUT).click();
        waitForAndGetElement(DELETE_CARD_BTN_POP_UP, DEFAULT_TIMEOUT + 3000);
        clickByJavaScriptExecutor(waitForAndGetElement(DELETE_CARD_BTN_POP_UP,DEFAULT_TIMEOUT));
    }

    public boolean assertSavedCardIsDeleted() throws InterruptedException {
        pause(3000);
       return waitForAndGetElement(NO_SAVED_CARDS_MSG, DEFAULT_TIMEOUT).isDisplayed();

    }
}
