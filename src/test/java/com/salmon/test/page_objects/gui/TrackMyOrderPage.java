package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.models.gui.TrackMyOrderModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.apache.commons.lang.StringUtils;

import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;
import static com.salmon.test.framework.helpers.WebDriverHelper.DEVICE_NAME;
/**
 * Created by hbkpreddy on 24/01/2018
 */
public class TrackMyOrderPage extends PageObject {

    public static final String TRACK_MY_ORDER = "TRACK MY ORDER";
    private By orderSteps = By.cssSelector("nbsp[data-ng-repeat*=orderSteps]>div[class*=order-progress__step]");
    private By orderStepStatus = By.cssSelector("nbsp[data-ng-repeat*=orderSteps]>div>span[class*=order-progress__status-label]");
    private By itemStatus = By.cssSelector(".bag-item__status");
    private By deliveryTypeDetails = By.cssSelector(".delivery-summary-block>p");
    private By orderNumber = By.cssSelector(".my-account__order-details>h2");
    private By deliveryDetails = By.cssSelector(".delivery-summary-block:nth-child(1)>p");
    private By deliveryAddress = By.cssSelector(".delivery-summary-block>ul>li");
    private By breadcrumb = By.cssSelector("li.list__item>.breadcrumb__link>span");
    private By totalPrice = By.cssSelector(".confirmation-total-list-total>.confirmation-total-list__detail");
    private By itemQuantity = By.cssSelector(".bag-item__details :nth-child(3)");
    private By cardInfo = By.cssSelector(".billing-info__card-info.billing-info__card-info--card>span");
	private By productName = By.cssSelector(".bag-item__prod-name");
	private By productThumbnail = By.cssSelector(".bag-item__col>a>img");
    private By collectionDetails = By.cssSelector(".collection-summary-block:nth-child(1)>strong");
    private By openingHoursList = By.cssSelector(".collection-summary-block__list");
    private By openingHours = By.cssSelector("[data-translate='checkout.collection.openingHours.header']");
    private By collectionName = By.cssSelector(".collection-summary-block:nth-child(1)>p:nth-child(2)");
    private By locationPin = By.cssSelector(".gm-style>div>div>div>div>div>img");
    private By mapComponent = By.cssSelector(".gm-style.gm-style>div:nth-child(2)");
    private By ORDER_STATUS = By.cssSelector(".order-text-status");
    private By refundHistory = By.cssSelector(".refund-confirmation-payment > h2.confirmation-payment__title");

    private List<WebElement> getOrderStepsList() {
        return visibilityOfAllElementsLocatedBy(orderSteps);
    }

    private List<WebElement> getOrderStepStatusList() {
        return visibilityOfAllElementsLocatedBy(orderStepStatus);
    }

    private List<WebElement> getBreadcrumbList() {
        return visibilityOfAllElementsLocatedBy(breadcrumb);
    }

    public String isOrderInCorrectStatus() {
        String progressStatus;
        String status = "";
        for (int i = 0; i < getOrderStepStatusList().size(); i++) {
            progressStatus = getOrderStepsList().get(i).getAttribute("class");
            if (progressStatus.contains("reached") || progressStatus.contains("pulse")) {
                status = getOrderStepStatusList().get(i).getText();
            }
        }
        return status;
    }

    public String getOrderStatus(){
        String orderStatus = waitForExpectedElement(ORDER_STATUS).getText().substring(8).split("\n")[0].trim();
        return orderStatus;
    }

    public boolean isRefundSectionDisplayed(){
        boolean isDisplayed = false;
        if (isDisplayed = webDriver.findElements(refundHistory).size() > 0){
            return true;
        }
        return isDisplayed;
    }


    public boolean isCorrectItemStatusDisplayed(String statusOfItem, String deliveryType) {
        boolean isDisplayed = false;
        String status = waitForExpectedElement(itemStatus).getText();
        String type = waitForExpectedElement(deliveryTypeDetails).getText();
        if (statusOfItem.equalsIgnoreCase(status) && type.contains(deliveryType)) {
            isDisplayed = true;
        }
        return isDisplayed;
    }

    public boolean isCorrectCollectionNameDisplayed(String statusOfItem, String deliveryType) {
        boolean isDisplayed = false;
        String status = waitForExpectedElement(itemStatus).getText();
        String type = waitForExpectedElement(collectionName).getText();
        if (statusOfItem.equalsIgnoreCase(status) && type.contains(deliveryType)) {
            isDisplayed = true;
        }
        return isDisplayed;
    }


    public boolean isCorrectOrderNumberDisplayed(TrackMyOrderModel trackMyOrderModel) {
        boolean isDisplayed = false;
        String orderNo = waitForExpectedElement(orderNumber).getText().substring(6).split("\n")[0].trim();
        if (trackMyOrderModel.getOrderNumber().equalsIgnoreCase(orderNo)) {
            isDisplayed = true;
        }
        return isDisplayed;
    }

    public void clickOnBreadcrumb(String breadcrumbName) {
        String name;
        if(DEVICE_NAME.contains("iPhone") || DEVICE_NAME.contains("iPad") || BROWSER.contains("emulator")){
            pause(1500);
        }
        for (int i = 0; i < getBreadcrumbList().size(); i++) {
            name = getBreadcrumbList().get(i).getText();
            if (name.equalsIgnoreCase(breadcrumbName)) {
                clickByJavaScriptExecutor(getBreadcrumbList().get(i));
                break;
            }
        }
    }

    public String getDeliveryDetails() {
        return waitForExpectedElement(deliveryDetails).getText();
    }

    public String getDeliveryAddress() {
        return waitForExpectedElement(deliveryAddress).getText();
    }

    public String getTotalPrice() {
        return waitForExpectedElement(totalPrice).getText();
    }

    public String getItemQuantity() {
        String[]str= waitForExpectedElement(itemQuantity).getText().split(": ");
        return str[1];
    }

    public String getCardInfo() {
        return waitForExpectedElement(cardInfo).getText();
    }

    public String getProductName() {
        return waitForExpectedElement(productName).getText();
    }

    public String getProductThumbnail() {
        return waitForExpectedElement(productThumbnail).getAttribute("title");
    }

    public WebElement getCollectionDetails() {
        return waitForExpectedElement(collectionDetails);
    }

    public WebElement getOpeningHours() {
        return waitForExpectedElement(openingHours);
    }

    public WebElement getOpeningHoursList() {
        return waitForExpectedElement(openingHoursList);
    }

    public WebElement getCollectionName() {
        return waitForExpectedElement(collectionName);
    }

    public WebElement getLocationPin() {
        return waitForAndGetElement(locationPin,3);
    }

    public WebElement getMapComponent() {
        return waitForExpectedElement(mapComponent);
    }

    public String getItemStatus(){
        return waitForExpectedElement(itemStatus).getText();
    }

    public void deleteCookies() {
        webDriver.manage().deleteAllCookies();
    }


    public boolean getTrackMyOrderButton() {
        final List<WebElement> elements = getWebDriver().findElements(By.cssSelector(".button"));
        final WebElement trackMyOrderElement = elements.stream()
                .filter(webElement -> hasText(webElement, TRACK_MY_ORDER))
                .findFirst().get();

        waitForElementVisible(trackMyOrderElement, 10);

        return trackMyOrderElement != null && trackMyOrderElement.getText() != null;
    }

    private boolean hasText(final WebElement element, final String text)
    {
        return StringUtils.containsIgnoreCase(element.getText(), text);
    }
}




