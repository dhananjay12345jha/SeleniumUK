package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.models.gui.TrackMyOrderModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyOrdersPage extends PageObject {

    private Logger log = LogManager.getLogger(MyOrdersPage.class.getName());
    private By orderNumber = By.cssSelector(".my-account-dashboard__recent-order-table>tbody>tr>td[class*=order-number]");
	private By orderNumberOms = By.cssSelector("[class*=content__order-number]");
    private By orderStatus = By.cssSelector(".my-account-dashboard__recent-order-table>tbody>tr>td[class*=status]");
	private By orderStatusOms =  By.cssSelector("[class='order-content order-content__status']>span");
    private By viewOrder = By.cssSelector(".my-account-dashboard__recent-order-table>tbody>tr>td[class*=view]>a");
	private By orderLinkOms = By.cssSelector("a[class*='order-content order-content']");
    private By viewOrderOms = By.cssSelector("a[class='order-content order-content__view']:nth-of-type(1)");
    private By KLARNA_LOGO_TENDER = By.cssSelector(".payment-card.klarna.large");
    private static final By KLARNA_TEXT = By.xpath(".//span[contains(text(), 'Klarna - Pay in 30 days')]");
    private static final By GUEST_REGISTERED_FORM = By.id("guestRegisterForm");
    private static final By PASSWORD_FIELD_REG_FORM = By.xpath("//input[@type='password']");
    private static final By CREATE_ACCOUNT_BTN_REG_FORM = By.xpath("//button[text()='Create Account']");

    private List<WebElement> getOrderNumberList() {
        return visibilityOfAllElementsLocatedBy(orderNumber);
    }

    private List<WebElement> getOrderStatusList() {
        return visibilityOfAllElementsLocatedBy(orderStatus);
    }

    private List<WebElement> getViewOrderList() {
        return visibilityOfAllElementsLocatedBy(viewOrder);
    }

    private List<WebElement> getOrderNumberListOMS() {
        return visibilityOfAllElementsLocatedBy(orderNumberOms);
    }

    private List<WebElement> getOrderStatusListOMS() {
        return visibilityOfAllElementsLocatedBy(orderStatusOms);
    }

    private List<WebElement> getViewOrderListOMS() {
        return visibilityOfAllElementsLocatedBy(viewOrderOms);
    }

	private List<WebElement> getOrderListOMS() {
		return visibilityOfAllElementsLocatedBy(orderLinkOms);
	}

    public void selectViewOrder(String orderNumber, String orderStatus, TrackMyOrderModel trackMyOrderModel) {
        String status;
        String orderNo;

        for (int i = 0; i < getOrderStatusList().size(); i++) {
            status = getOrderStatusList().get(i).getText().trim();
            orderNo = getOrderNumberList().get(i).getText();
            if (status.contains(orderStatus) && orderNumber.equalsIgnoreCase(orderNo)) {
                clickByJavaScriptExecutor(getViewOrderList().get(i));
                trackMyOrderModel.setOrderNumber(orderNumber);
                break;
            }
        }
    }

	public void selectOrderLinkOMS(String orderNumber, String orderStatus, TrackMyOrderModel trackMyOrderModel,
			String orderViewLink) {
		getOrderStatusListOMS().stream().filter(status -> status.getText().trim().contains(orderStatus));
		getOrderNumberListOMS().stream().filter(orderNo -> orderNo.getText().contains(orderNumber));
		clickByJavaScriptExecutor(
				getOrderListOMS().stream().filter(a -> a.getText().contains(orderViewLink.trim())).findFirst().get());
		trackMyOrderModel.setOrderNumber(orderNumber);
	}
	public void selectOrderLinkOMSMobile(String orderViewLink) {
		clickByJavaScriptExecutor(
				getOrderListOMS().stream().filter(a -> a.getText().contains(orderViewLink.trim())).findFirst().get());

	}

    public void clickViewOrder() {
        waitForExpectedElement(viewOrder);
        clickByJavaScriptExecutor(waitForExpectedElement(viewOrder));
    }
	public void clickViewOrderOms() {
		waitForExpectedElement(viewOrderOms);
		log.info( "before clicking" + " " +webDriver.findElement(viewOrderOms).getText());
		clickByJavaScriptExecutor(waitForExpectedElement(viewOrderOms));
		log.info( "after clicking");
	}
	private List<WebElement> getStatusOrderOms() {
		return visibilityOfAllElementsLocatedBy(orderStatusOms);
	}

    public String getOrderStatus(){
    	   waitForPageLoad();
        return waitForExpectedElement(orderStatus,DEFAULT_TIMEOUT).getText();
    }
	public String getOrderStatusOms(){
		waitForPageLoad();
		log.info("getting order status" + getStatusOrderOms().get(0).getText());
		return getStatusOrderOms().get(0).getText();
	}

    public boolean isKlarnaTenderDisplayed()
    {
        return isElementVisible(KLARNA_LOGO_TENDER, DEFAULT_TIMEOUT) && isElementPresent(KLARNA_TEXT, DEFAULT_TIMEOUT);
    }

    public boolean isOptionForRegisteringPresent()
    {
        return isElementVisible(GUEST_REGISTERED_FORM, DEFAULT_TIMEOUT)&&isElementVisible(PASSWORD_FIELD_REG_FORM, DEFAULT_TIMEOUT)
                && isElementVisible(CREATE_ACCOUNT_BTN_REG_FORM, DEFAULT_TIMEOUT);
    }

	public void guestRegisteringAccount()
	{
		webDriver.findElement(PASSWORD_FIELD_REG_FORM).sendKeys("Password123");
		clickByJavaScriptExecutor(waitForExpectedElement(CREATE_ACCOUNT_BTN_REG_FORM));
	}

}
