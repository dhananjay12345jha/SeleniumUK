package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;


import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

public class PudoSection extends PageObject {

    private Logger log = LogManager.getLogger(PudoSection.class.getName());
    private final By CLOSEST_COLLECTION_POINT = By.id("closest");
    private final By LOWEST_PRICE_COLLECTION_POINT = By.id("price");
    private final By EARLIEST_COLLECTION_POINT = By.id("date");
    private final By NL_STORES_COLLECTION_POINT = By.className("pudo-sort__checkbox-label");
    private final By COLLECTION_SELECTION_BUTTON = By.className("button--collection-selection");
    private final By STANDARD_STORE_COLLECTION_OPTIONS = By.className("pudo__standard-text");
    private final By PUDO_DELIVERY_OPTIONS = By.xpath("//pudo-delivery-options");
    private final By DELIVERY_CHARGES = By.xpath("//input[@type='radio' and @class='radio__input']");
    private final By MAP_TIMES = By.xpath("//a[contains(text(), 'Map')]");
    private final By NEWLOOK_OPTION = By.xpath("//label[contains(@class, 'radio__label')]");
    private final By NEWLOOK_ONLY_CHECKBOX = By.xpath("//label[@for='newlookOnly']");
    @FindBy(css = ".radio--account")
    WebElement priceRadioButton;
    private
    CheckOutPage checkOutPage;
    private int option;

    public List<WebElement> PUDO_OPTION() {
        return getWebDriver().findElements(PUDO_DELIVERY_OPTIONS);
    }

    public List<WebElement> PUDO_NEWLOOK_OPTION() {
        return getWebDriver().findElements(PUDO_DELIVERY_OPTIONS);
    }

    public WebElement closestCollectionPoint() {
        return waitForExpectedElement(CLOSEST_COLLECTION_POINT);
    }

    public WebElement newlookOnly() {
        return waitForExpectedElement(NEWLOOK_ONLY_CHECKBOX);
    }

    public WebElement collectionSelection() {
        return waitForExpectedElement(COLLECTION_SELECTION_BUTTON);
    }

    public void selectCollectionOption() throws InterruptedException {
        //  ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", getWebDriver().findElement(PUDO_DELIVERY_OPTIONS));
//        int PUDOOPTIONS = webDriver.findElements(COLLECTION_SELECTION_BUTTON).size();
//        for(int i=0; i<PUDOOPTIONS; i++){
//            option = i;
//            if(getWebDriver().findElements(COLLECTION_SELECTION_BUTTON).get(i).getAttribute("class").contains("disabled")) {
//                ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", getWebDriver().findElements(COLLECTION_SELECTION_BUTTON).get(i++));
//            }
//            else{
//                break;
//            }
//        }
//        getWebDriver().findElements(COLLECTION_SELECTION_BUTTON).get(option).click();

        //Thread.sleep(3000);
//        waitForElementVisible(priceRadioButton,5);
//        //WebElement e =webDriver.findElement(By.cssSelector(".radio--account"));
//        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);",priceRadioButton);
//        getWebDriver().findElements(By.cssSelector(".radio--account")).get(0).click();
//        priceRadioButton.click();
        scrollForFocusAndClick(By.cssSelector(".radio--account"), 5);
        Actions actions = new Actions(webDriver);
//        actions.moveToElement(e,e.getLocation().getX(),e.getLocation().getY()).click();
//        actions.moveToElement(e).click().perform();
        getWebDriver().findElement(By.cssSelector(".store-results__list li button")).click();

    }

    public void newlookCollectionOption() {
        scrollForFocusAndClick(NEWLOOK_ONLY_CHECKBOX, 5);
        //newlookOnly().click();
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", PUDO_OPTION().get(0).findElement(NEWLOOK_OPTION));
        PUDO_OPTION().get(0).findElement(NEWLOOK_OPTION).click();
        PUDO_OPTION().get(0).findElement(COLLECTION_SELECTION_BUTTON).click();
    }

}
