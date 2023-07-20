package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.RandomGenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang.StringUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;


import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;


public class PersonalDetailsPage extends PageObject {
    private Logger log = LogManager.getLogger(PersonalDetailsPage.class.getName());
    private static final By PERSONAL_DETAILS_CONTAINER_LABELS = By.className("account__row-label");
    private static final By PERSONAL_DETAILS_CONTAINER_BUTTON = By.cssSelector(".button.button--secondary.button--secondary-black.account__edit-button");
    private static final By FIRST_NAME_TEXT_FIELD = By.id("firstName");
    private static final By LAST_NAME_TEXT_FIELD = By.id("lastName");
    private static final By GENDER_SELECTION_FEMALE = By.cssSelector(".label.radio__label[for='gender0']");
    private static final By SELECT_DATE_OF_BIRTH = By.className("select--dob");
    private static final By MOBILE_NUMBER_INPUT_FIELD = By.id("mobileNumber");
    private static final By UPDATE_PERSONAL_DETAILS_POPUP_SAVE_BUTTON = By.cssSelector(".button.button--secondary.button--secondary-black.button--full-width.ng-scope");
    private static final By PERSONAL_DETAILS_CONTAINER_VALUES = By.cssSelector(".account__row-text.ng-binding");
    private static final By MY_DATA_PRIVACY_RIGHTS_SECTION = By.className("rtbf");
    private static final By FIND_OUT_MORE_BUTTON = By.cssSelector(".rtbf__button.button.button--secondary.button--full-width-tablet");
    private String generatedName;

    public List<String> selectMyAccountPersonalDetailsLabel(String category) {
        waitForAndGetElement(PERSONAL_DETAILS_CONTAINER_LABELS,DEFAULT_TIMEOUT);
        log.info("### Looking for Personal details page item {}", category);
        waitForListLoaded(webDriver.findElements(PERSONAL_DETAILS_CONTAINER_LABELS), 3, 5);
        return webDriver.findElements(PERSONAL_DETAILS_CONTAINER_LABELS).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> selectMyAccountPersonalDetailsButtons(final String button) {
    	  pause(2000);
        waitForAndGetElement(PERSONAL_DETAILS_CONTAINER_LABELS,DEFAULT_TIMEOUT);
        log.info("### Looking for personal details page button {}", button);
        waitForListLoaded(webDriver.findElements(PERSONAL_DETAILS_CONTAINER_BUTTON), 3, 5);
        return webDriver.findElements(PERSONAL_DETAILS_CONTAINER_BUTTON).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickPersonalDetailsPageButton(String category) {

        log.info("### Looking for my account page item {}", category);
        waitForListLoaded(webDriver.findElements(PERSONAL_DETAILS_CONTAINER_BUTTON), 3, 5);
        clickByJavaScriptExecutor(getWebDriver().findElements(PERSONAL_DETAILS_CONTAINER_BUTTON).stream()
                .filter(a -> StringUtils.containsIgnoreCase(a.getText(),category.trim()))
                .findFirst().get());
    }

    public String updatePersonalDetailsField(String field, String button) {
//		String generatedName = null;
        switch (field.toUpperCase()) {
            case "FIRSTNAME":
                generatedName = RandomGenerator.randomAlphabetic(5);
                webDriver.findElement(FIRST_NAME_TEXT_FIELD).clear();
                webDriver.findElement(FIRST_NAME_TEXT_FIELD).sendKeys(generatedName);
                break;
            case "LASTNAME":
                generatedName = RandomGenerator.randomAlphabetic(5);
                webDriver.findElement(LAST_NAME_TEXT_FIELD).clear();
                webDriver.findElement(LAST_NAME_TEXT_FIELD).sendKeys(generatedName);
                break;
            case "GENDER":
                generatedName = webDriver.findElement(GENDER_SELECTION_FEMALE).getText();
                webDriver.findElement(GENDER_SELECTION_FEMALE).click();
                break;
            case "DATE OF BIRTH":
                selectFromDropDown(webDriver.findElements(SELECT_DATE_OF_BIRTH).get(0), 10);
                selectFromDropDown(webDriver.findElements(SELECT_DATE_OF_BIRTH).get(1), 1);
                selectFromDropDown(webDriver.findElements(SELECT_DATE_OF_BIRTH).get(2), 10);
                break;
            case "MOBILENUMBER":
                generatedName = RandomGenerator.randomInteger(11);
	             webDriver.findElement(MOBILE_NUMBER_INPUT_FIELD).clear();
                webDriver.findElement(MOBILE_NUMBER_INPUT_FIELD).sendKeys(generatedName);
                break;
            default:
                log.error("Please check the field name provided {}" + field);
        }

        clickWhenClickable(webDriver
                .findElements(UPDATE_PERSONAL_DETAILS_POPUP_SAVE_BUTTON)
                .stream()
                .filter(a -> StringUtils.containsIgnoreCase(a.getText(), button.trim()))
                .findFirst().get(), 5);
        return generatedName;
    }

    public List<String> selectMyAccountPersonalDetailsValues(String category) {
        log.info("### Looking for Personal details page item {}", category);
        waitForListLoaded(webDriver.findElements(PERSONAL_DETAILS_CONTAINER_VALUES), 3, 5);
        return webDriver.findElements(PERSONAL_DETAILS_CONTAINER_VALUES).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getGeneratedName() {
        return this.generatedName;
    }

    public void myDataPrivacyRightsSectionExists() {
        waitForAndGetElement(MY_DATA_PRIVACY_RIGHTS_SECTION, DEFAULT_TIMEOUT);
        getWebDriver().findElement(MY_DATA_PRIVACY_RIGHTS_SECTION).isDisplayed();
    }

    public void clickFindOutMore() {
        waitForAndGetElement(FIND_OUT_MORE_BUTTON, DEFAULT_TIMEOUT).click();
    }
}
