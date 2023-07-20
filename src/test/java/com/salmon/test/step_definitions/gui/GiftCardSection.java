package com.salmon.test.step_definitions.gui;


import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;

import static com.salmon.test.step_definitions.gui.Converter.convertCurrencyValue;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

public class GiftCardSection extends PageObject {

    private static final By GIFT_CARD_HEADING = By.className("gift-card__heading");
    private static final By  GIFT_CARD_ICON = By.cssSelector(".gift-card__heading-wrap");
    private static final By GIFT_CARD_NUMBER = By.cssSelector("#giftCardNumber");
    private static final By GIFT_CARD_PIN = By.cssSelector("#giftCardPin");
    private static final By GIFT_CARD_BALANCE_BUTTON = By.cssSelector(".button.button--secondary.button--secondary-black.button--full-width");
    private static final By GIFT_CARD_NUMBER_ENDING = By.className("gift-card__apply-card-ending");
    private static final By GIFT_CARD_BALANCE = By.className("gift-card__apply-balance-value");
    public static final By GIFT_CARD_ERROR = By.cssSelector(".giftcard__error-msg");
    private static final By APPLY_GIFT_CARD = By.className("gift-card-balance-btn");
    private static final By GIFT_CARD_ORIGINAL_AMOUNT = By.className("gift-card__original-balance-value");
    private static final By GIFT_CARD_AMOUNT_USED = By.className("gift-card__amount-used-value");
    private static final By GIFT_CARD_AMOUNT_REMAINING = By.className("gift-card__balance-remaining-value");
    private static final By GIFT_CARD_AMOUNT_DUE = By.className("gift-card__amount-due-value");
    private static final By BILLING_SECTION = By.id("billing-address-section");

    public WebElement giftCardHeading() {
        return waitForExpectedElement(GIFT_CARD_HEADING);
    }

    public WebElement openGiftCardSection() {
        waitForAndGetElement(GIFT_CARD_ICON,DEFAULT_TIMEOUT);
        scrollIntoView(GIFT_CARD_ICON);
        return waitForExpectedElement(GIFT_CARD_ICON);
    }

    public WebElement applyGiftCard() {
        return waitForAndGetElement(APPLY_GIFT_CARD,DEFAULT_TIMEOUT);
    }

    public void scrollToGiftCardSection() {
        scrollElementIntoView(BILLING_SECTION);
    }

    public boolean giftCardSectionPresent() {
        return isElementPresent(GIFT_CARD_HEADING);
    }

    public WebElement giftCardNumberEnding() {
        return waitForExpectedElement(GIFT_CARD_NUMBER_ENDING);
    }

    public WebElement giftCardBalance() {
        return waitForExpectedElement(GIFT_CARD_BALANCE);
    }

    public void enterGiftCard(String number, String pin) {
        scrollElementIntoView(GIFT_CARD_BALANCE_BUTTON);
        getWebDriver().findElement(GIFT_CARD_NUMBER).sendKeys(number);
        getWebDriver().findElement(GIFT_CARD_PIN).sendKeys(pin);
        clickByJavaScriptExecutor(getWebDriver().findElement(GIFT_CARD_BALANCE_BUTTON));
        pause(2000);
//        if (getWebDriver().findElement(GIFT_CARD_ERROR).getText().contains(Props.getProp("giftCardErrorMessage"))) {
//            getWebDriver().findElement(GIFT_CARD_NUMBER).sendKeys(Props.getProp("registeredGiftCard2"));
//            getWebDriver().findElement(GIFT_CARD_PIN).sendKeys(Props.getProp("registeredGiftCardPin2"));
//            getWebDriver().findElement(GIFT_CARD_BALANCE_BUTTON).click();
//        }
        pause(500);
    }

    public WebElement giftCardError() {
        return waitForExpectedElement(GIFT_CARD_ERROR);
    }

    public boolean amountDueLessGiftCardAmountUsed(String giftCardBalance, String checkOutTotalBalance) {
        waitForElementPresence(GIFT_CARD_AMOUNT_DUE);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        Double giftCardStartingBalance = Double.valueOf(decimalFormat.format(Integer.valueOf(giftCardBalance) / 100.00));
        Double checkOutBalance = convertCurrencyValue(checkOutTotalBalance);
        Double amountDue = convertCurrencyValue(getWebDriver().findElement(GIFT_CARD_AMOUNT_DUE).getText());

        if (giftCardStartingBalance >= checkOutBalance) {
            return amountDue == 0.0;

        } else {
            return amountDue == checkOutBalance - giftCardStartingBalance;
        }
    }


    public boolean giftCardCorrectBalanceRemaining() {
        waitForElementPresence(GIFT_CARD_ORIGINAL_AMOUNT);
        Double originalBalance = convertCurrencyValue(getWebDriver().findElement(GIFT_CARD_ORIGINAL_AMOUNT).getText());
        Double amountUsed = convertCurrencyValue(getWebDriver().findElement(GIFT_CARD_AMOUNT_USED).getText());

        Double amountRemaining = convertCurrencyValue(getWebDriver().findElement(GIFT_CARD_AMOUNT_REMAINING).findElement(By.tagName("strong")).getText());

        return originalBalance - amountUsed == amountRemaining;
    }


}
