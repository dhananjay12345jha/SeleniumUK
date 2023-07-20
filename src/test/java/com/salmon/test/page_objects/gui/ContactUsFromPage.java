package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

import java.util.List;

public class ContactUsFromPage extends PageObject
{
//	private Logger LOG = LoggerFactory.getLogger(ContactUsFromPage.class);
    private Logger log = LogManager.getLogger(ContactUsFromPage.class.getName());
	private final By CONTACT_US_FORM_LINK = By.xpath("//li[@class='yCmsComponent page-footer__primary-nav-item']/a[text()='Contact Us']");
    private final By emailSubject = By.className("form-group__field");
    private final By contactUsForm = By.name("contactUsForm");
    private final By CONTACT_US_FIRST_NAME = By.id("firstName");
    private final By CONTACT_US_LAST_NAME = By.id("surname");
    private final By CONTACT_US_EMAIL = By.id("email");
    private final By CONTACT_US_MSG = By.id("message");
    private final By SEND_EMAIL_BTN = By.xpath("//button[text()='SEND EMAIL']");
    private final By ASK_ANOTHER_QUESTION = By.xpath("//a[text()='ASK ANOTHER QUESTION']");
    private final By ERROR_MESSAGE = By.className("form__error-msg");

    public WebElement contactUsFormLink() {
        scrollToBottom();
        return waitForAndGetElement(CONTACT_US_FORM_LINK,DEFAULT_TIMEOUT);
    }

    public WebElement emailSubject() {
        return waitForExpectedElement(emailSubject);
    }

    public WebElement contactUsForm() {
        return waitForExpectedElement(contactUsForm);
    }

    public Select dropdown() {
        return new Select(webDriver.findElement(By.id("subject")));
    }

    public void randomSelectOption() {

        //return subject = selectFromDropdownRandom(SELECT_SUBJECT_DROPDOWN);

        selectFromDropDown(getWebDriver().findElement(By.id("subject")), 5);
        pause(2000);
    }

    public void fillInFirstName() {
        getWebDriver().findElement(CONTACT_US_FIRST_NAME).sendKeys("ContactUsTestFirstName");
    }

    public void fillInAlertInContactUsForm() {
        getWebDriver().findElement(CONTACT_US_FIRST_NAME).clear();
        getWebDriver().findElement(CONTACT_US_FIRST_NAME).sendKeys("<script>alert('test');</script>");
    }

    public void fillInLastName() {
        getWebDriver().findElement(CONTACT_US_LAST_NAME).sendKeys("ContactUsTestLastName");
    }

    public void fillInEmail() {
        getWebDriver().findElement(CONTACT_US_EMAIL).sendKeys("ContactUsTestEmail@test.test");
    }

    public void fillInEmail(String email) {
        getWebDriver().findElement(CONTACT_US_EMAIL).sendKeys(email);
    }

    public void fillInMsg() {
        getWebDriver().findElement(CONTACT_US_MSG).sendKeys("ContactUsTestMessage");
    }

    public void clickSendEmail() {
        webDriver.findElement(SEND_EMAIL_BTN).click();
    }

    public boolean isAskAnotherQuestionBtnDisplayed() {
        try {
            waitForIsClickable(ASK_ANOTHER_QUESTION, DEFAULT_TIMEOUT);
        } catch (NoSuchElementException e) {
            log.info("Ask another question button is not present!");
        }
        return isElementPresent(ASK_ANOTHER_QUESTION);
    }

    public void selectMyEnquiry()  {

        try {
            Thread.sleep(2000);
            selectFromDropdownRandom(By.id("queryType"));
        } catch (NoSuchElementException e) {
            log.info("My enquiry dropdown is not present in the Contact Us Form");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectOrder() {
        try {
            selectFromDropdownRandom(By.id("orderQueryType"));
        } catch (NoSuchElementException e) {
            log.info("Order dropdown is not present in the Contact Us Form");
        }
    }

    public void selectPaymentMethod() {
        try {
            selectFromDropdownRandom(By.id("paymentMethod"));
        } catch (NoSuchElementException e) {
            log.info("Payment method dropdown is not present in the Contact Us Form");
        }
    }


    public void selectChannel() {
        try {
            selectFromDropdownRandom(By.id("channel"));
        } catch (NoSuchElementException e) {
            log.info("Channel dropdown is not present in the Contact Us Form");
        }
    }

    public void selectBrowser() {
        try {
            selectFromDropdownRandom(By.id("browser"));
        } catch (NoSuchElementException e) {
            log.info("Browser dropdown is not present in the Contact Us Form");
        }
    }

    public boolean errorMessageIsDisplayed() {
        return waitForAndGetElement(ERROR_MESSAGE,DEFAULT_TIMEOUT).isDisplayed();
    }

    public String getErrorMessage() {
        return waitForAndGetElement(ERROR_MESSAGE,DEFAULT_TIMEOUT).getText();
    }
}
