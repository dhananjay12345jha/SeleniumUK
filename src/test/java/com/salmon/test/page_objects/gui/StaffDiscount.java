package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

public class StaffDiscount extends PageObject {


    private Logger log = LogManager.getLogger(StaffDiscount.class.getName());
    private final By Start_Registration = By.xpath(".//*[@class='button button--primary staff-card__cta-start']");

    // Membership Details page

    private final By Membership_Logo = By.xpath(".//*[@class='staff-card__register-heading']");
    private final By Emp_ID = By.xpath(".//*[@id='employeeID']");
    private final By IKANO_CARD_NUMBER = By.xpath(".//*[@id='ikanoStaffCardNumber']");
    private final By Accept_Checkbox = By.xpath(".//input[@class='checkbox__input']");
    private final By Submit = By.xpath(".//*[@class='button button--primary staff-card__cta-register']");
    private final String staff_discount_url = "uk/staff-discount";

    // Registration Confirmation page
    private By Confirmation_Label = By.xpath(".//*[@class='staff-card-register__title']");
    private By Goto_My_Account = By.xpath(".//a[text()='Go to my Account']");
    private By Continue_Shopping = By.xpath(".//a[text()='Continue Shopping']");


    public WebElement Start_Registration() {
        return waitForExpectedElement(Start_Registration);
    }

    public WebElement Membership_Logo() {
        return getWebDriver().findElement(Membership_Logo);
    }

    public WebElement Emp_ID() {
        return waitForExpectedElement(Emp_ID);
    }

    public WebElement IKANO_CARD_NUMBER() {
        return getWebDriver().findElement(IKANO_CARD_NUMBER);
    }

    public WebElement Accept_Checkbox() {
        return waitForExpectedElement(Accept_Checkbox);
    }

    public WebElement Submit() {
        return waitForExpectedElement(Submit);
    }


    public WebElement Confirmation_Label() {
        return getWebDriver().findElement(Confirmation_Label);
    }

    public WebElement Goto_My_Account() {
        waitForElementVisible(Goto_My_Account, 10);
        return getWebDriver().findElement(Goto_My_Account);
    }

    public WebElement Continue_Shopping() {
        return getWebDriver().findElement(Continue_Shopping);
    }

    public int start_registration_count() {
        return getWebDriver().findElements(Start_Registration).size();
    }

    public void navigateToStaffDiscount() {

        getWebDriver().get(UrlBuilder.getWebsiteUrl() + staff_discount_url);
    }

}




