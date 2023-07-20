package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;


public class MyAccountsPage extends PageObject {

    private Logger log = LogManager.getLogger(MyAccountPage.class.getName());
    private final By loggedInUserLinkLocator = By.partialLinkText("account");
    private final By myAccountLinkLocator = By.partialLinkText("My Account");
    private final By addressBook = By.cssSelector("a[href*='address-book']");
    private final By addNewAddressButton = By.cssSelector("button[class*='address-book']");
    private final By addMoreLinesAddressLink = By.cssSelector("a[data-ng-click='addAddressLine()']");
    private final By addressLinks = By.className("address-book__address-data-link");

    private final By deleteAddressBox = By.cssSelector(".address-book__box");
    private final By deleteAddressButton = By.xpath("//button[contains(text(),'Delete Address')]");
    private final By defaultAddress = By.cssSelector(".account-card");
    private final By cancelLinkDeleteAddress = By.cssSelector(".address-book__modal-link.ng-scope");
    private final By personalDetailsHeader = By.cssSelector("a[href*='profile']");
    private final By updatePersonalDetails = By.cssSelector("button[data-ng-click=\"editProfile('details')\"]");
    private final By saveChangesButton = By.cssSelector("button[data-translate='profile.edit.details.submit']");
    private final By accountDetailsUpdatedText = By.cssSelector(".alert__item.ng-binding");
    private final By personalDetailsFrame = By.cssSelector(".account-card.account-card--details");
    private final By cancelUpdatingPersonalDetailsLink = By.cssSelector("button[data-ng-click='cancelEdit()']");
    private final By updateEmail = By.cssSelector("button[data-ng-click=\"editProfile('email')\"]");
    private final By updatePassword = By.cssSelector("button[data-ng-click=\"editProfile('password')\"]");
    private final By updateEmailButton = By.cssSelector("button[data-translate*='email.submit']");
    private final By newEmailAddressField = By.id("email");
    private final By confirmNewMailField = By.id("chkEmail");
    private final By changeMailPasswordField = By.id("password");
    private final By newEmailAddressFieldError = By.cssSelector("p[data-translate*='email.required']");
    //By wrongPasswordError = By.cssSelector("p[data-ng-if='emailPwdErrorMessages']");
    private final By wrongPasswordError = By.cssSelector("div.form-group__field.form-group__field--account > p");
    private final By emailErrorMessage = By.cssSelector("div.form-group__field.form-group__field--account > p");
    private final By currentPasswordField = By.id("currentPassword");
    private final By newPasswordFiled = By.id("newPassword");
    private final By confirmPasswordField = By.id("checkNewPassword");
    private final By updatePasswordButton = By.cssSelector("button[data-translate*='password.submit']");
    private final By passwordRequiredError = By.cssSelector("p[data-translate*='currentPassword.required']");
    private final By wrongConfirmPasswordError = By.cssSelector("p[data-translate*='checkNewPassword.compareTo']");
    private final By passwordFieldToggle = By.cssSelector("span[password-toggle='']");
    private final By forgottenPasswordLink = By.xpath("//a[contains(text(),'Forgotten password')]");
    private final By phoneNumberFieldInCheckout = By.xpath("//input[@name='customerPhoneNumber']");
    private final By forgottenPasswordEmailAddressField = By.id("email");
    private final By resetPasswordButton = By.cssSelector("button[class*='button button--primary button--full-width']");
    private final By resetPasswordConfirmationMessage = By.cssSelector("div[class='checkout__notify']> .checkout__form-title");
    private final By passwordResetError = By.className("alert__group--error");
    private final By trackMyOrderLink = By.xpath("//a[contains(text(),'Track')]");
    private final By orderNumberField = By.id("orderNumber");
    private final By emailAddressFieldOrder = By.id("email");
    private final By signInTrackOrderButton = By.cssSelector("button[class*='checkout__form-button']");
    private final By orderHistoryTable = By.cssSelector("td[class*='recent-orders']");
    private final By orderNumber = By.cssSelector("td[class='recent-orders__order-number']");
    private final By trackOrderButton = By.xpath("(//button[@type='submit'])[3]");
    private final By accountHeaderOrderNumber = By.cssSelector(".account__header");
    private final By softLoggedInSignoutLink = By.cssSelector("a[class='checkout__form-link']");
    private final By incorrectOrderNumberError = By.className("alert__group--error");
    private final By orderConfirmationNumber = By.cssSelector(".confirmation-order__title");
    private final By phoneNumberChangeLink = By.xpath("//form[@name='deliveryUpdates']//a[text()='Change']");
    private final By accountSections = By.className("my-account-dashboard__title");
    private final By savedCollection = By.xpath("//div[contains(@class,'account-card__content')]");
    private final By removeSavedCollectionLink = By.xpath("//button[contains(text(),'Remove')]");
    private final By savedCollectionModel = By.cssSelector("div[class*='modal modal--sm']");
    private final By cancelSavedLocationDeleting = By.xpath("(//button[contains(text(),'Cancel')])");
    private final By deleteSavedLocationButton = By.xpath("//button[contains(text(),'Delete')]");
    private final By Staff_Card_Number = By.xpath(".//span[text()='Staff Card Number']");
    private final By Personal_Details = By.xpath("//*[@href = '/uk/my-account/profile']");
    private final By Staff_Card_Number_Details = By.xpath("//span[contains(text(),'982')]");


    public WebElement Staff_Card_Number_Details() {
        return getWebDriver().findElement(Staff_Card_Number_Details);
    }

    public WebElement Personal_Details() {
        return getWebDriver().findElement(Personal_Details);
    }

    public WebElement Staff_Card_Number() {
        return getWebDriver().findElement(Staff_Card_Number);
    }

    public WebElement phoneNumberChnageLink() {
        return waitForExpectedElement(phoneNumberChangeLink);
    }

    public WebElement loggedInUserLinkLocator() {
        return waitForExpectedElement(loggedInUserLinkLocator);
    }

    public WebElement myAccountLinkLocator() {
        return waitForExpectedElement(myAccountLinkLocator);
    }

    public WebElement addressBook() {
        return waitForExpectedElement(addressBook);
    }

    public WebElement addNewAddressButton() {
        return waitForExpectedElement(addNewAddressButton);
    }

    public WebElement editAddressLink() {
        waitForExpectedElement(addressLinks);
        return getWebDriver().findElements(addressLinks).get(0);
    }

    public WebElement addMoreLinesAddressLink() {
        return waitForExpectedElement(addMoreLinesAddressLink);
    }

    public WebElement deleteAddressLink() {
        waitForExpectedElement(addressLinks);
        return getWebDriver().findElements(addressLinks).get(1);
    }

    public WebElement deleteAddressBox() {
        return waitForExpectedElement(deleteAddressBox);
    }

    public WebElement deleteAddressButton() {
        return waitForExpectedElement(deleteAddressButton);
    }

    public WebElement cancelLinkDeleteAddress() {
        return waitForExpectedElement(cancelLinkDeleteAddress);
    }

    public WebElement defaultAddress() {
        return waitForExpectedElement(defaultAddress);
    }

    public WebElement personalDetailsHeader() {
        return waitForExpectedElement(personalDetailsHeader);
    }

    public WebElement updatePersonalDetails() {
        return waitForExpectedElement(updatePersonalDetails);
    }

    public WebElement saveChangesButton() {
        return waitForExpectedElement(saveChangesButton);
    }

    public WebElement accountDetailsUpdatedText() {
        return waitForExpectedElement(accountDetailsUpdatedText);
    }

    public WebElement personalDetailsFrame() {
        return waitForExpectedElement(personalDetailsFrame);
    }

    public WebElement cancelUpdatingPersonalDetailsLink() {
        return waitForExpectedElement(cancelUpdatingPersonalDetailsLink);
    }

    public WebElement updateEmail() {
        return waitForExpectedElement(updateEmail);
    }

    public WebElement updatePassword() {
        return waitForExpectedElement(updatePassword);
    }

    public WebElement updateEmailButton() {
        return waitForExpectedElement(updateEmailButton);
    }

    public WebElement newEmailAddressField() {
        return waitForExpectedElement(newEmailAddressField);
    }

    public WebElement confirmNewMailField() {
        return waitForExpectedElement(confirmNewMailField);
    }

    public WebElement changeMailPasswordField() {
        return waitForExpectedElement(changeMailPasswordField);
    }

    public WebElement newEmailAddressFieldError() {
        return waitForExpectedElement(newEmailAddressFieldError);
    }

    public WebElement wrongPasswordError() {
        return waitForExpectedElement(wrongPasswordError);
    }

    public WebElement emailErrorMessage() {
        return waitForExpectedElement(emailErrorMessage);
    }

    public WebElement currentPasswordField() {
        return waitForExpectedElement(currentPasswordField);
    }

    public WebElement newPasswordFiled() {
        return waitForExpectedElement(newPasswordFiled);
    }

    public WebElement confirmPasswordField() {
        return waitForExpectedElement(confirmPasswordField);
    }

    public WebElement updatePasswordButton() {
        return waitForExpectedElement(updatePasswordButton);
    }

    public WebElement passwordRequiredError() {
        return waitForExpectedElement(passwordRequiredError);
    }

    public WebElement wrongConfirmPasswordError() {
        return waitForExpectedElement(wrongConfirmPasswordError);
    }

    public WebElement passwordFieldToggle() {
        return waitForExpectedElement(passwordFieldToggle);
    }

    public WebElement forgottenPasswordLink() {
        return waitForExpectedElement(forgottenPasswordLink);
    }

    public WebElement phoneNumberFieldInCheckout() {
        return waitForExpectedElement(phoneNumberFieldInCheckout);
    }

    public WebElement forgottenPasswordEmailAddressField() {
        return waitForExpectedElement(forgottenPasswordEmailAddressField);
    }

    public WebElement resetPasswordButton() {
        return waitForExpectedElement(resetPasswordButton);
    }

    public WebElement resetPasswordConfirmationMessage() {
        return waitForExpectedElement(resetPasswordConfirmationMessage);
    }

    public WebElement passwordResetError() {
        return waitForExpectedElement(passwordResetError);
    }

    public WebElement trackMyOrderLink() {

        return waitForExpectedElement(trackMyOrderLink);
    }

    public WebElement orderNumberField() {
        return waitForExpectedElement(orderNumberField);
    }

    public WebElement emailAddressFieldOrder() {
        return waitForExpectedElement(emailAddressFieldOrder);
    }

    public WebElement signInTrackOrderButton() {
        return waitForExpectedElement(signInTrackOrderButton);
    }

    public WebElement orderHistoryTable() {
        return waitForExpectedElement(orderHistoryTable);
    }

    public WebElement orderNumber() {
        return waitForExpectedElement(orderNumber);
    }

    public WebElement trackOrderButton() {
        return waitForExpectedElement(trackOrderButton);
    }

    public WebElement accountHeaderOrderNumber() {
        return waitForExpectedElement(accountHeaderOrderNumber);
    }

    public WebElement softLoggedInSignoutLink() {
        return waitForExpectedElement(softLoggedInSignoutLink);
    }

    public WebElement incorrectOrderNumberError() {
        return waitForExpectedElement(incorrectOrderNumberError);
    }

    public WebElement orderConfirmationNumber() {
        return waitForExpectedElement(orderConfirmationNumber);
    }

    public WebElement savedCollection() {
        return waitForExpectedElement(savedCollection);
    }

    public WebElement removeSavedCollectionLink() {
        return waitForExpectedElement(removeSavedCollectionLink);
    }

    public WebElement savedCollectionModel() {
        return waitForExpectedElement(savedCollectionModel);
    }

    public WebElement cancelSavedLocationDeleting() {
        return waitForExpectedElement(cancelSavedLocationDeleting);
    }

    public WebElement deleteSavedLocationButton() {
        return waitForExpectedElement(deleteSavedLocationButton);
    }

    public List<WebElement> accountSections() {
        return getWebDriver().findElements(accountSections);
    }

    public WebElement savedCollectionLocations() {
        return accountSections().get(5);
    }


    public WebElement isIDAssociated(String ID) {
        return waitForExpectedElement(By.xpath("//span[text()='" + ID + "']"));
    }

    public boolean addMoreLinksAvailable() {
        try {
            getWebDriver().findElement(By.cssSelector("a[data-ng-click='addAddressLine()']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean mySavedLocationPresent() {
        try {
            getWebDriver().findElement(By.cssSelector("div[class*='account__cell']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean savedCollectionLocationDisplayed() {
        try {
            getWebDriver().findElement(By.cssSelector("div[class*='account__cell account-grid--address-book']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean phoneNumberChangeLinkPresent() {
        try {
            getWebDriver().findElement(phoneNumberChangeLink);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteSessionCookie() throws InterruptedException {

        Thread.sleep(5000);
        getWebDriver().manage().deleteCookieNamed("JSESSIONID");

        getWebDriver().navigate().refresh();
    }

}
