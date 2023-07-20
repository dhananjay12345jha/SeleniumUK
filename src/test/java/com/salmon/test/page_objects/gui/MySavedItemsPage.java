package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

public class MySavedItemsPage extends PageObject {

    private Logger log = LogManager.getLogger(MySavedItemsPage.class.getName());

    private static final By LIST_OF_SAVED_ITEMS = By.className("saveditems__selectioncontent");
    private static final By MOVE_TO_MY_BAG_BUTTON = By.cssSelector("button");
    private static final By MOVED_TO_BAG_MESSAGE = By.cssSelector(".alert__list > li > span");
    private static final By MOVED_TO_BAG_MESSAGE_BAG_LINK = By.cssSelector(".alert__list > li > span > a[href*='uk/cart']");
    private static final By REMOVE_BUTTON = By.cssSelector(".iconf.iconf--close.saveditems__remove");
    private static final By SELECT_SIZE_DROP_DOWN = By.className("saveditems--select");
    private static final By SELECTED_SIZE = By.cssSelector(".saveditems__size-colour > span");
    private static final By SAVED_ITEMS_URGENCY_WARNING = By.className("saveditems__urgency-message");

    private CheckOutPage checkOutPage = new CheckOutPage();

    public boolean getMySavedItemList() {
        waitForAndGetElement(webDriver, LIST_OF_SAVED_ITEMS, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(LIST_OF_SAVED_ITEMS).isEmpty()) {
            return true;
        }
        return false;
    }

    public int getNumberOfItemsSaved() {
        pause(1000);
        waitForAndGetElement(LIST_OF_SAVED_ITEMS, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(LIST_OF_SAVED_ITEMS).isEmpty()) {
            return webDriver.findElements(LIST_OF_SAVED_ITEMS).size();
        }
        return 0;
    }

    public String moveSavedItemsToMyBag() {
        String selectedSize = getSelectedSizeText();
        pause(1000);
        waitForElementVisible(LIST_OF_SAVED_ITEMS, 10);
        if (!webDriver.findElements(LIST_OF_SAVED_ITEMS).isEmpty()) {
        	pause(9000);
            clickByJavaScriptExecutor(getWebDriver().findElements(MOVE_TO_MY_BAG_BUTTON).stream().filter(a -> a.getText().contains("Move to bag".trim())).findFirst().get());
        }
        return selectedSize;
    }

    public boolean getMovedToBagMessage() {
        waitForAndGetElement(webDriver, MOVED_TO_BAG_MESSAGE, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(MOVED_TO_BAG_MESSAGE).isEmpty()) {
            return true;
        }
        return false;
    }

    public void navigateToMybag() {
        waitForAndGetElement(webDriver, MOVED_TO_BAG_MESSAGE_BAG_LINK, DEFAULT_TIMEOUT);
        pause(2000);
        if (!webDriver.findElements(MOVED_TO_BAG_MESSAGE_BAG_LINK).isEmpty()) {
            clickByJavaScriptExecutor(getWebDriver().findElements(MOVED_TO_BAG_MESSAGE_BAG_LINK).stream().filter(a -> a.getText().contains("Bag".trim())).findFirst().get());
        }
    }

    public void removeItemFromSavedItems() {
        webDriver.findElements(REMOVE_BUTTON).get(0).click();
    }

    public void changeTheSizeOfTheProduct() {
        waitForAndGetElement(SELECT_SIZE_DROP_DOWN, DEFAULT_TIMEOUT);
        scrollElementIntoView(SELECT_SIZE_DROP_DOWN);
        selectFromDropDown(webDriver.findElements(SELECT_SIZE_DROP_DOWN).get(0), 1);
    }

    public String getSelectedSizeText() {
        waitForAndGetElement(webDriver,SELECTED_SIZE, DEFAULT_TIMEOUT);
        if (!webDriver.findElements(SELECTED_SIZE).isEmpty()) {
            return webDriver.findElement(SELECTED_SIZE).getText();
        }
        return null;
    }

    public void selectSizeForAllProductsInSavedItems() {
        waitForAndGetElement(webDriver, SELECT_SIZE_DROP_DOWN, DEFAULT_TIMEOUT);

        while (!getWebDriver().findElements(By.cssSelector("select.saveditems--select")).isEmpty())
        {
            selectFromDropDown(getWebDriver().findElement(By.cssSelector("select.saveditems--select")), 1);
        }
    }

    public WebElement getSavedItemsUrgencyWarning()
    {
    	return waitForExpectedElement(SAVED_ITEMS_URGENCY_WARNING);
    }
}
