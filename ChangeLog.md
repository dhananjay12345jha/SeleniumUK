**19th June**

- Updated gecko driver to version 0.14.0 in order to be compatible with Firefox version 51.0.1
- Updated ChromeDriver to version 2.30
- Updated WebDriverHelper.java file
    //System.setProperty("webdriver.firefox.marionette", getDriverPath());
                to
     System.setProperty("webdriver.gecko.driver", getDriverPath());
- Firefox capabilities marionette set to true.
- Below dependency added to support selenium 3.1 +
    <dependency>
                <groupId>com.google.guava</groupId>
                <artifactId>guava</artifactId>
                <version>21.0</version>
            </dependency>


Known issue - Firefox working locally but not in Selenium Grid.


**21st June**

- Updated geckodriver to version 0.16.1
- Selenium webdriver is updated to version 3.4.0
- All selenium nodes are now running Firefox 53.0
- Updated Firefox Capabilities to support latest geckodriver 0.16.1
private static DesiredCapabilities getFireFoxDesiredCapabilities() {

        FirefoxOptions options=new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("moz:firefoxOptions", options);

        FirefoxProfile profile=new FirefoxProfile();
       capabilities.setCapability(FirefoxDriver.PROFILE, profile);

        capabilities.setCapability("marionette", true);
        capabilities.setCapability("platform", "WINDOWS");
        capabilities.setBrowserName("firefox");
        return capabilities;

    }
- Firefox and Chrome browsers max sessions are bumped from 3 to 5 in all nodes

**23rd June**

- Firefox capabilities - options.setBinary() commented to invoke Firefox on grid without any issues.
- Firefox capabilities - included  capabilities.setCapability("acceptInsecureCerts", true); to handle security certificates

**26th June**

- Updated versions of libraries - POM.xml
- Chrome capabilities - Fix to take screenshots
        chromeOptions.addArguments("--enable-automation");
- Introduced a method waitForExpectedElementToClear() in PageObject class        

    protected WebElement waitForExpectedElementToClear(final By by) {
            waitForExpectedElement(by).clear();
            return waitForExpectedElement(by);
        }

- Updated ChromeDriver method in WebDriverHelper.java.
- Updated phantomjs.exe and its capabilities
- Amended phantomjs related dependencies in POM.xml

**4th Sep**

- Updated versions of libraries - POM.xml
- Included MoTel capabilites in AndriodHelper.java

