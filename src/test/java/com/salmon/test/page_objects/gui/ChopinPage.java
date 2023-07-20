package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;


public class ChopinPage extends PageObject {

private Logger log = LogManager.getLogger(ChopinPage.class.getName());

    //Login Page
    private final By username = By.xpath("//input[@name='username']");
    private final By password = By.xpath("//input[@name='password']");
    private final By logintochopin = By.xpath("//input[@name='submit' and @value='Login']");
    private final By logintextverify = By.xpath("//*[contains(text(),'Enter the cardholder search criteria below, and submit the form to get list of matching cardholders ...')]");


    //Search and select the card page
    private final By serialnumber = By.xpath("//input[@name='extSeqNo']");
    private final By cardsearch = By.xpath("//input[@name='cardNumSubmitButton' and @value='Search']");
    private final By selectcard = By.xpath("//input[@name='_card_0' and @value='Select']");

    //select card on account confirmation page
    // Click adjust balance link after selecting the card
    private final By adjustbalancelink = By.xpath("//*[contains(@href,'/chopin/adjustBalance.do')]");
    private final By remaining_balance = By.xpath(".//*[@id='adjustBalanceCommand']/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[2]");

    // Page to enter the amount and reason
    private final By amount_to_enter = By.xpath("//input[@name='amountNum']");
    private final By reason_to_enter = By.xpath("//input[@name='reason']");
    private final By submit_amount = By.xpath("//input[@type='submit' and @value='Submit']");
    private final By message_after_topup = By.xpath("//*[contains(text(),'After Adjustment')] ");

    // After submiting the amount page
    private int amount_to_adjust = 0;


    public WebElement remaining_balance() {
        return getWebDriver().findElement(remaining_balance);
    }

    public WebElement username() {
        return getWebDriver().findElement(username);
    }


    public WebElement password() {
        return getWebDriver().findElement(password);
    }

    public WebElement logintochopin() {
        return getWebDriver().findElement(logintochopin);
    }

    public WebElement serialnumber() {
        return getWebDriver().findElement(serialnumber);
    }

    public WebElement cardsearch() {
        return getWebDriver().findElement(cardsearch);
    }

    public WebElement selectcard() {
        return getWebDriver().findElement(selectcard);
    }


    public WebElement amount_to_enter() {
        return getWebDriver().findElement(amount_to_enter);
    }

    public WebElement reason_to_enter() {
        return getWebDriver().findElement(reason_to_enter);
    }

    public WebElement submit_amount() {
        return getWebDriver().findElement(submit_amount);
    }

    public WebElement adjustbalancelink() {
        return getWebDriver().findElement(adjustbalancelink);
    }

    public WebElement logintext() {
        return getWebDriver().findElement(logintextverify);
    }


    public boolean message_after_topup() {

        return isElementPresent(message_after_topup);
    }


    public boolean verifytext() {

        return isElementPresent(logintextverify);
    }

    public void login(String usrnm, String passwd) {
        username().sendKeys(usrnm);
        password().sendKeys(passwd);
        logintochopin().click();
    }

    public void anonymouslygotochopin() {
        webDriver.navigate().refresh();
        webDriver.manage().window().maximize();
        webDriver.get(UrlBuilder.getChopinUrl());

    }

    public void search_giftcard(String srno) {

        serialnumber().sendKeys(srno);
        cardsearch().click();
    }


    public int topup_amount(int Total) {

        final float maxamt = Total;

        String balancetext = remaining_balance().getText();

        String regex = "([0-9]+[.][0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(balancetext);

        float amount_on_card = 0;
        while (matcher.find()) {
            String bb = matcher.group();
            amount_on_card = Float.parseFloat(bb);
        }
        amount_to_adjust = (int) (maxamt - amount_on_card);

        return amount_to_adjust;
    }

    public void topup(int adjust_amount) {

        String topup_amt = Integer.toString(adjust_amount);

        amount_to_enter().sendKeys(topup_amt);

        reason_to_enter().sendKeys("topup this balance");

        submit_amount().click();
    }


    public boolean success_toppedup_message() {

        return isElementPresent(message_after_topup);
    }


}
