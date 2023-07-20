package com.salmon.test.page_objects.gui;


import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class LoginPage extends PageObject {

    private Logger log = LogManager.getLogger(LoginPage.class.getName());
    public final By loggedInUserLinkLocator = By.partialLinkText("account");
    private final String PROFILE_PAGE = "uk/my-account/profile";
    private final String CREATE_ACCOUNT_PAGE = "uk/register";


    //###############
    private final By signinLinkLocator = By.xpath("//a[contains(text(),'Sign In')]");
    private final By loginLinkLocator = By.cssSelector("a[href*='/login']");
    private final By usernameFieldLocator = By.id("j_username");
    private final By passwordFieldLocator = By.name("j_password");
    private final By loginButtonLocator = By.cssSelector(".checkout__form-button.button.button--primary");
    private final By signoutLinkLocator = By.xpath("//a[contains(text(),'Sign Out')]");
    private final By newToAccount = By.cssSelector("button[class='register__tab-button']");
    private final By logInSection = By.className("register__tabs");
    private final By registerForm = By.id("registerForm");
    private final By loginForm = By.id("loginForm");
    private final By createAccount = By.xpath("//*[contains(text(),' Create Account')]");
    //Registration fields
    private final By title = By.id("titleCode");
    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By emailAddress = By.id("email");
    private final By password = By.id("pwd");
    private final By mobileNumber = By.id("mobileNumber");
    private final By registerButton = By.cssSelector(".button.button--primary.button--full-width");
    private final By genderMale = By.xpath("//*[@for='gender1']");
    private final By genderFemale = By.xpath("//*[@for='gender0']");
    private final By softLoggedInFrame = By.cssSelector("div[class='checkout__softlogin'] >div[class*='checkout__login']");
    public By userAccountLinks = By.cssSelector(".header__account .popover__content>li>a");
    public By headerNavigationLinks = By.className("header__navitem");
    //@FindBy(xpath = "//a[contains(text(),'Sign In')]")
    By signIn = By.xpath("//a[contains(text(),'Sign In')]");
//    private Logger log = LoggerFactory.getLogger(LoginPage.class);
    private String generatedName;
    private String generatedEmail;

    public WebElement signinLinkLocator() {
        return waitForExpectedElement(signinLinkLocator);
    }

    public WebElement createAccount() {
//        deleteAllCookies();
        return waitForExpectedElement(createAccount);
    }

    public WebElement usernameFieldLocator() {
        return waitForExpectedElement(usernameFieldLocator);
    }

    public WebElement passwordFieldLocator() {
        return waitForExpectedElement(passwordFieldLocator);
    }

    public WebElement loginButtonLocator() {
        return waitForExpectedElement(loginButtonLocator);
    }

    public WebElement checkoutButton() {
        return getWebDriver().findElement(By.id("loginForm")).findElement(By.className("checkout__form-button"));
    }

    public WebElement signoutLinkLocator() {
        return waitForExpectedElement(signoutLinkLocator);
    }

    public WebElement logInSection() {
        return waitForExpectedElement(logInSection);
    }

    public WebElement registerForm() {
        return waitForExpectedElement(registerForm);
    }

    public WebElement loginForm() {
        return waitForExpectedElement(loginForm);
    }

    public WebElement title() {
        return waitForExpectedElement(title);
    }

    public WebElement firstName() {
        return waitForExpectedElement(firstName);
    }

    public WebElement lastName() {
        return waitForExpectedElement(lastName);
    }

    public WebElement emailAddress() {
        return waitForExpectedElement(emailAddress);
    }

    public WebElement password() {
        return waitForExpectedElement(password);
    }

    public WebElement registerButton() {
        return waitForExpectedElement(registerButton);
    }

    public WebElement loggedInUserLinkLocator() {
        return waitForExpectedElement(loggedInUserLinkLocator);
    }

    public WebElement newToAccount() {
        return waitForExpectedElement(newToAccount);
    }

    public WebElement mobileNumber() {
        return waitForExpectedElement(mobileNumber);
    }

    public WebElement genderMale() {
        return waitForExpectedElement(genderMale);
    }

    public WebElement genderFemale() {
        return waitForExpectedElement(genderFemale);
    }

    private void selectGenderMale(boolean sex) {
        if (sex) {
            genderMale().click();
        } else {
            genderFemale().click();
        }
    }

    public WebElement softLoggedInFrame() {
        return waitForExpectedElement(softLoggedInFrame);
    }


    public void selectTitle(String title) {
        title();
        new Select(getWebDriver().findElement(By.id("titleCode"))).selectByVisibleText(String.format("%s.", title));

    }

    public void selectTitle() {
        title();
        new Select(getWebDriver().findElement(By.id("titleCode"))).selectByVisibleText("Mr.");
    }

    public void enterDateOfBirth() {
        new Select(getWebDriver().findElement(By.id("dayOfBirth"))).selectByVisibleText("1");
        new Select(getWebDriver().findElement(By.id("monthOfBirth"))).selectByIndex(2);
        new Select(getWebDriver().findElement(By.id("yearOfBirth"))).selectByVisibleText("1986");
    }

    public boolean signIn() {
//        boolean present;
//        try {
//            getWebDriver().findElement(By.xpath("//a[contains(text(),'Sign In')]"));
//            present = true;
//        } catch (Exception e) {
//            present = false;
//        }
//        return present;
        return isElementVisible(getWebDriver().findElement(signIn), 1);
    }


    public Credentials registerNewUser() {
        Credentials credentials = new Credentials();
        emailAddress().sendKeys(credentials.getEmail());
        password().sendKeys(credentials.getPassword());
        selectTitle("Mr");
        firstName().sendKeys(RandomGenerator.randomAlphabetic(7));
        lastName().sendKeys(RandomGenerator.randomAlphabetic(9));
        selectGenderMale(true);
        registerButton().click();

        return credentials;
    }


    public void signIn(String emailAddress, String password) {
        usernameFieldLocator().sendKeys(emailAddress);
        passwordFieldLocator().sendKeys(password);
        loginButtonLocator().click();
    }

//    public void goToProfilePage() {
//        Application baseUri = configurationLookup.getAppEnvConfig().getApp(AppEnum.nlk_ui);
//        log.info("Navigating to the profile page -- " + baseUri.getBaseUri()+ PROFILE_PAGE);
//        getWebDriver().get(baseUri.getBaseUri() + PROFILE_PAGE);
//    }
//
//    public void goToCreateAccountPage() {
//        Application baseUri = configurationLookup.getAppEnvConfig().getApp(AppEnum.nlk_ui);
//        log.info("The url used to create account --" + baseUri.getBaseUri() + CREATE_ACCOUNT_PAGE);
//        getWebDriver().get(baseUri.getBaseUri() + CREATE_ACCOUNT_PAGE);
//    }

    public void clickHeaderLink(String link) {
        log.info("### Looking for link {}", link);
        WebElement element = getWebDriver().findElements(headerNavigationLinks).stream().filter(a -> a.getText().contains(link.trim())).findFirst().get();
        clickWhenClickable(element, 3);

    }

    public void clickUserAccountLink(String link) {
        log.info("### Looking for link {}", link);
        clickHeaderLink("s account");
        getWebDriver().findElements(userAccountLinks).stream().filter(a -> a.getText().contains(link.trim())).findFirst().get().click();

    }

    public void logoutIfSignIn() {
        if (!isElementVisible(getWebDriver().findElement(signIn), 1)) {
            clickUserAccountLink("Sign Out");


        }
    }

    public void clickLoginLink() {
        getWebDriver().findElement(loginLinkLocator).click();

    }

    public void goToProfilePage() {
        log.info("Navigating to the profile page -- " + UrlBuilder.getSiteUrl() + PROFILE_PAGE);
        getWebDriver().get(UrlBuilder.getSiteUrl() + PROFILE_PAGE);
    }

    public void goToCreateAccountPage() {
        log.info("The url used to create account --" + UrlBuilder.getSiteUrl() + CREATE_ACCOUNT_PAGE);
        getWebDriver().get(UrlBuilder.getSiteUrl() + CREATE_ACCOUNT_PAGE);
    }

    public void newSignUp() {
        logoutIfSignIn();
        signinLinkLocator().click();
        newToAccount().click();
        selectTitle();
        this.generatedName = RandomGenerator.randomAlphabetic(5);
        this.generatedEmail = RandomGenerator.randomEmailAddress(5);
        firstName().sendKeys(this.generatedName);
        lastName().sendKeys(RandomGenerator.randomAlphabetic(5));
        emailAddress().sendKeys(this.generatedEmail);
        password().sendKeys("Pa55word");
        registerButton().click();
    }


    public void newSignUpWithAlertPopUpScript() {
        logoutIfSignIn();
        signinLinkLocator().click();
        newToAccount().click();
        selectTitle();
        this.generatedName = RandomGenerator.randomAlphabetic(5);
        this.generatedEmail = RandomGenerator.randomEmailAddress(5);
        firstName().sendKeys("<script>alert('test');</script>");
        lastName().sendKeys(RandomGenerator.randomAlphabetic(5));
        emailAddress().sendKeys(this.generatedEmail);
        password().sendKeys("Pa55word");
        registerButton().click();
    }

    public class Credentials {
        private String email;
        private String password;

        private Credentials() {
            setEmail(RandomGenerator.randomEmailAddress(10));
            setPassword(RandomGenerator.randomAlphabetic(6) + "1");
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


    }

}
