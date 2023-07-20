package com.salmon.test.framework.helpers;

import com.salmon.test.framework.helpers.utils.SessionInfo;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class WebDriverHelper extends EventFiringWebDriver
{
	private static final Logger LOG = LoggerFactory.getLogger(WebDriverHelper.class);
	private static RemoteWebDriver REAL_DRIVER;
	private static final Thread CLOSE_THREAD = new Thread(() -> REAL_DRIVER.quit());
	public static final String BROWSER;
	public static final String PLATFORM;
	private static final String FILE_SEPARATOR;
	private static String SELENIUM_REMOTE_URL;
	private static final Dimension BROWSER_WINDOW_SIZE;
	private static final Integer BROWSER_WINDOW_WIDTH;
	private static final Integer BROWSER_WINDOW_HEIGHT;

	private static final String DEVICE_NAME_KEY = "device";
	public static final String DEVICE_NAME;
	private static final String USE_REAL_DEVICE_KEY = "realDevice";
	public static final boolean USE_REAL_DEVICE;

	private static final String VENDOR_KEY = "vendor";
	private static final String VENDOR;

	private static final String IS_LOCAL_KEY = "local";
	private static final Boolean IS_LOCAL;

	private static final String BROWSERSTACK_URL = "@hub-cloud.browserstack.com/wd/hub";
	private static final String BROWSERSTACK_USERNAME_KEY = "bsUser";
	private static final String BROWSERSTACK_ACCESS_KEY_KEY = "bsKey";
	private static final String BROWSERSTACK_USERNAME;
	private static final String BROWSERSTACK_ACCESS_KEY;

	private static final String BROWSER_VERSION_KEY = "browser.version";
	private static final String BROWSER_VERSION;
	private static final String OS_KEY = "os";
	private static final String OS;
	private static final String OS_VERSION_KEY = "os.version";
	private static final String OS_VERSION;

	private static final String APPIUM_BROWSER_NAME;
	private static final String APPIUM_PLATFORM_VERSION;
	private static final String APPIUM_PLATFORM_NAME;
	private static final String APPIUM_DEVICE_NAME;
	public static final boolean IS_MOBILE;

	static
	{
		Props.loadRunConfigProps("/environment.properties");

		FILE_SEPARATOR = System.getProperty("file.separator");
		PLATFORM = Props.getProp("platform");
		BROWSER = System.getProperty("browser", Props.getDeviceProp("browser"));
		APPIUM_BROWSER_NAME = System.getProperty("appium.browser.name", Props.getProp("appium.browser.name"));
		APPIUM_PLATFORM_VERSION = System.getProperty("appium.platform.version",
													 Props.getProp("appium.platform.version"));
		APPIUM_PLATFORM_NAME = System.getProperty("appium.platform.name", Props.getProp("appium.platform.name"));
		APPIUM_DEVICE_NAME = System.getProperty("appium.device.name", Props.getProp("appium.device.name"));
		IS_MOBILE = Boolean.parseBoolean(System.getProperty("mobile", Props.getDeviceProp("mobile")));

		IS_LOCAL = Boolean.parseBoolean(Props.getProp(IS_LOCAL_KEY));

		DEVICE_NAME = Props.getDeviceProp(DEVICE_NAME_KEY);
		USE_REAL_DEVICE = Boolean.parseBoolean(Props.getDeviceProp(USE_REAL_DEVICE_KEY));
		VENDOR = Props.getDeviceProp(VENDOR_KEY);

		BROWSERSTACK_USERNAME = System.getProperty(BROWSERSTACK_USERNAME_KEY, Props.getProp(BROWSERSTACK_USERNAME_KEY));
		BROWSERSTACK_ACCESS_KEY = System.getProperty(BROWSERSTACK_ACCESS_KEY_KEY, Props.getProp(BROWSERSTACK_ACCESS_KEY_KEY));

		BROWSER_VERSION = Props.getDeviceProp(BROWSER_VERSION_KEY);
		OS = Props.getDeviceProp(OS_KEY);
		OS_VERSION = Props.getDeviceProp(OS_VERSION_KEY);

		SELENIUM_REMOTE_URL = "https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_ACCESS_KEY + BROWSERSTACK_URL;

		BROWSER_WINDOW_WIDTH = Integer.parseInt(System.getProperty("browser.width",
																   Props.getDeviceProp("browser.width")));
		BROWSER_WINDOW_HEIGHT = Integer.parseInt(System.getProperty("browser.height",
																	Props.getDeviceProp("browser.height")));
		BROWSER_WINDOW_SIZE = new Dimension(BROWSER_WINDOW_WIDTH, BROWSER_WINDOW_HEIGHT);

		if (!"appium".equalsIgnoreCase(BROWSER) && IS_LOCAL)
		{
			System.setProperty("webdriver.gecko.driver", getDriverPath());
			System.setProperty("webdriver.chrome.driver", getDriverPath());
			System.setProperty("webdriver.ie.driver", getDriverPath());
			System.setProperty("phantomjs.binary.path", getDriverPath());
		}
		Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
	}

	public static void startWebDriver() throws MalformedURLException
	{
		if (IS_LOCAL)
		{
			startLocalWebDriver();
		}
		else
		{
			startBrowserstackWebDriver();
		}
	}

	public static void startLocalWebDriver()
	{
		try
		{
			switch (BROWSER.toLowerCase())
			{
				case ("chrome"):
					startChromeDriver();
					break;
				case ("firefox"):
					startFireFoxDriver();
					break;
				case ("iexplore"):
					startIEDriver();
					break;
				case ("phantomjs"):
					startPhantomJsDriver();
					break;
				case ("sauce"):
					startSauceDriver();
					break;
				case ("saucesimulator"):
					startSauceSimulatorDriver();
					break;
				case ("saucemobile"):
					startSauceMobileDriver();
					break;
				case ("localbrowseremulator"):
					startLocalBrowserEmulatorDriver();
					break;
				case ("saucebrowseremulator"):
					startBrowserEmulatorSauceDriver();
					break;
				case ("appium"):
					startAppiumDriver();
					break;
				default:
					throw new IllegalArgumentException("Browser " + BROWSER + " or Platform "
													   + PLATFORM + " type not supported");

			}
		}
		catch (final IllegalStateException e)
		{
			LOG.error(" Browser parameter " + BROWSER + " Platform parameter " + PLATFORM
					  + " type not supported");
		}
	}

	private WebDriverHelper()
	{
		super(REAL_DRIVER);
	}

	public static WebDriver getWebDriver()
	{
		return REAL_DRIVER;
	}

	private static String getDriverPath()
	{
		switch (BROWSER.toLowerCase())
		{
			case "firefox":
			{
				return getDriverPath("geckodriver", "geckodriver.exe");
			}
			case "chrome":
			case "android":
			{
				return (PLATFORM.contains("win"))
						? getDriverPath("chromedriver", "chromedriver.exe")
						: getDriverPath("chromedriver", "chromedriver");
			}
			case "iexplore":
			{
				return getDriverPath("iedriver", "IEDriverServer.exe");
			}
			case "phantomjs":
			{
				return getDriverPath("phantomjs", "phantomjs.exe");
			}
			default:
			{
				throw new IllegalArgumentException("Unknown browser: " + BROWSER);
			}
		}
	}

	public static String getDriverPath(final String directory, final String filename)
	{
		return "tools" + FILE_SEPARATOR + directory + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR + filename;
	}

	private static void startIEDriver()
	{
		final DesiredCapabilities capabilities = getInternetExploreDesiredCapabilities();
		REAL_DRIVER = new InternetExplorerDriver(capabilities);
		REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
	}

	private static void startFireFoxDriver()
	{
		final DesiredCapabilities capabilities = getFireFoxDesiredCapabilities();
		REAL_DRIVER = new FirefoxDriver(capabilities);
		REAL_DRIVER.manage().window().maximize();
	}

	private static void startPhantomJsDriver()
	{
		final DesiredCapabilities capabilities = getPhantomJsCapabilities();
		REAL_DRIVER = new PhantomJSDriver(capabilities);
		REAL_DRIVER.manage().window().maximize();
	}

	private static void startAppiumDriver()
	{
		final DesiredCapabilities capabilities = getAppiumDesiredCapabilities();
		try
		{
			REAL_DRIVER = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		}
		catch (final MalformedURLException e)
		{
			LOG.error("Appium Server Error " + e.getMessage());
		}
	}

	private static void startSauceDriver()
	{
		DesiredCapabilities capabilities = getSauceCapabilities();
		try
		{
			REAL_DRIVER = new RemoteWebDriver(new URL("https://" +
													  BROWSERSTACK_USERNAME +
													  ":" +
													  BROWSERSTACK_ACCESS_KEY +
													  "@ondemand.eu-central-1.saucelabs.com/wd/hub"), capabilities);
			attachSauceLabsVideoLinkToScenario();
		}
		catch (MalformedURLException e)
		{
			LOG.error(" Error Sauce Url " + e.getMessage());
		}
	}

	private static void startBrowserEmulatorSauceDriver()
	{
		Map<String, Object> mobileEmulation = new HashMap<>();
		Map<String, Object> deviceMetrics = new HashMap<>();
		String userAgent = "";
		switch (DEVICE_NAME)
		{
			case "iPhone 11":
				deviceMetrics.put("width", 414);
				deviceMetrics.put("height", 896);
				deviceMetrics.put("pixelRatio", 2);
				userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.4 Mobile/15E148 Safari/604.1";
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", userAgent);
				break;
			case "iPhone 11 Pro":
				deviceMetrics.put("width", 375);
				deviceMetrics.put("height", 812);
				deviceMetrics.put("pixelRatio", 3);
				userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.4 Mobile/15E148 Safari/604.1";
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", userAgent);
				break;
			case "iPhone 11 Pro Max":
				deviceMetrics.put("width", 414);
				deviceMetrics.put("height", 896);
				deviceMetrics.put("pixelRatio", 3);
				userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.4 Mobile/15E148 Safari/604.1";
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", userAgent);
				break;
			case "iPhone 8":
				mobileEmulation.put("deviceName", "iPhone 8");
				break;
			case "Galaxy S9":
				deviceMetrics.put("width", 360);
				deviceMetrics.put("height", 740);
				deviceMetrics.put("pixelRatio", 4);
				userAgent = "Mozilla/5.0 (Linux; Android 8.0.0; SM-G960F Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.137 Mobile Safari/537.36";
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", userAgent);
				break;
			case "Galaxy S10":
				deviceMetrics.put("width", 360);
				deviceMetrics.put("height", 760);
				deviceMetrics.put("pixelRatio", 4);
				userAgent = "Mozilla/5.0 (Linux; Android 9; SAMSUNG SM-G977N Build/PPR1.180610.011) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/9.2 Chrome/67.0.3396.87 Mobile Safari/537.36";
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", userAgent);
				break;
			case "iPad":
				mobileEmulation.put("deviceName", "iPad");
				break;
//			    5th generation
			case "iPad 2017":
				deviceMetrics.put("width", 768);
				deviceMetrics.put("height", 1024);
				deviceMetrics.put("pixelRatio", 2);
				userAgent = "Mozilla/5.0 (iPad; CPU OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148";
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", userAgent);
				break;
			default:
				LOG.error("Unknown device to emulate");
				throw new IllegalArgumentException("UnKnown Device to emulate");
		}

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

		DesiredCapabilities capabilities = getSauceCapabilities();
//	    capabilities.setCapability("browserName", SAUCE_LABS_BROWSER);
		capabilities.setCapability("platform", PLATFORM);
		capabilities.setCapability("version", BROWSER_VERSION);
		capabilities.setCapability("sauce-advisor", true);
		capabilities.setCapability("idleTimeout", "200");
		capabilities.setCapability("build", "NewLook" + " Test - " + SessionInfo.startTime);
		capabilities.setCapability("name", SessionInfo.scenarioName);
		capabilities.setCapability("sharetunnel", "dominic.corbin");
		capabilities.setCapability("--disable-web-security", true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		try
		{
			REAL_DRIVER = new RemoteWebDriver(new URL("https://" +
													  BROWSERSTACK_USERNAME +
													  ":" +
													  BROWSERSTACK_ACCESS_KEY +
													  "@ondemand.eu-central-1.saucelabs.com/wd/hub"), capabilities);
			attachSauceLabsVideoLinkToScenario();
		}
		catch (MalformedURLException e)
		{
			LOG.error(" Error Sauce Url " + e.getMessage());
		}
	}

	private static void startSauceSimulatorDriver()
	{
		DesiredCapabilities capabilities = getSauceSimulatorCapabilities();
		try
		{
			REAL_DRIVER = new RemoteWebDriver(new URL("https://" +
													  BROWSERSTACK_USERNAME +
													  ":" +
													  BROWSERSTACK_ACCESS_KEY +
													  "@ondemand.eu-central-1.saucelabs.com/wd/hub"), capabilities);
			attachSauceLabsVideoLinkToScenario();
		}
		catch (MalformedURLException e)
		{
			LOG.error(" Error Sauce Url " + e.getMessage());
		}
	}

	private static void startSauceMobileDriver()
	{
		DesiredCapabilities capabilities = getSauceMobileCapabilities();
		try
		{
			REAL_DRIVER = new RemoteWebDriver(new URL("https://eu1.appium.testobject.com/wd/hub"), capabilities);
		}
		catch (MalformedURLException e)
		{
			LOG.error(" Error Sauce Url " + e.getMessage());
		}
	}

	private static void startChromeDriver()
	{
		if (StringUtils.isBlank(DEVICE_NAME))
		{
			REAL_DRIVER = new ChromeDriver(getChromeDesiredCapabilities());
		}
		else
		{
			startLocalBrowserEmulatorDriver();
		}
		REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
	}

	private static void startBrowserstackWebDriver() throws MalformedURLException
	{
		if (StringUtils.isBlank(DEVICE_NAME))
		{
			REAL_DRIVER = getBrowserstackRemoteWebDriver(getBrowserstackChromeDesiredCapabilities());
			REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
		}
		else
		{
			REAL_DRIVER = getBrowserstackRemoteWebDriver(getBrowserstackMobileChromeDesiredCapabilities());
		}
	}

	private static DesiredCapabilities getChromeDesiredCapabilities()
	{
		final LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.OFF);
		final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);

		final ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-web-security");
		chromeOptions.addArguments("--test-type");
		chromeOptions.addArguments("--start-maximized");
		capabilities.setCapability("chrome.verbose", false);

		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		return capabilities;
	}

	private static DesiredCapabilities getBrowserstackChromeDesiredCapabilities()
	{
		// TODO: Might be able to generify this so it's not so Chrome-specific
		final LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.OFF);
		final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);

		final ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-web-security");
		chromeOptions.addArguments("--test-type");

		chromeOptions.setCapability("browser", BROWSER);
		chromeOptions.setCapability("browser_version", BROWSER_VERSION);
		chromeOptions.setCapability("os", OS);
		chromeOptions.setCapability("os_version", OS_VERSION);
		chromeOptions.setCapability("resolution", BROWSER_WINDOW_WIDTH + "x" + BROWSER_WINDOW_HEIGHT);

		chromeOptions.setCapability("browserstack.local", "true");
		chromeOptions.setCapability("browserstack.localIdentifier", System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER"));
		chromeOptions.setCapability("browserstack.console", "errors");

		chromeOptions.setCapability("recordScreenshots", false);
		chromeOptions.setCapability("extendedDebugging", false);

		chromeOptions.setCapability("build", "New Look Test - " + SessionInfo.startTime);
		chromeOptions.setCapability("name", SessionInfo.scenarioName);
		chromeOptions.setCapability("project", "New Look");
		chromeOptions.setCapability("autoGrantPermissions", "true");

		capabilities.setCapability("chrome.verbose", false);

		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		return capabilities;
	}

	private static DesiredCapabilities getBrowserstackMobileChromeDesiredCapabilities()
	{
		final LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.OFF);
		final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);

		final ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-web-security");
		chromeOptions.addArguments("--test-type");

		chromeOptions.setCapability("browserName", BROWSER);
		chromeOptions.setCapability("browser_version", BROWSER_VERSION);
		chromeOptions.setCapability("device", DEVICE_NAME);
		chromeOptions.setCapability("realMobile", USE_REAL_DEVICE);
		chromeOptions.setCapability("os", OS);
		chromeOptions.setCapability("os_version", OS_VERSION);

		chromeOptions.setCapability("browserstack.local", "true");
		chromeOptions.setCapability("browserstack.localIdentifier", System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER"));
		chromeOptions.setCapability("browserstack.console", "errors");

		chromeOptions.setCapability("recordScreenshots", false);
		chromeOptions.setCapability("extendedDebugging", false);

		chromeOptions.setCapability("build", "New Look Test - " + SessionInfo.startTime);
		chromeOptions.setCapability("name", SessionInfo.scenarioName);
		chromeOptions.setCapability("project", "New Look");

		chromeOptions.setCapability("autoGrantPermissions", "true");
		chromeOptions.setCapability("autoAcceptAlerts", "true");
		chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, "true");
		chromeOptions.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20);

		capabilities.setCapability("chrome.verbose", false);

		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		return capabilities;
	}

	private static DesiredCapabilities getFireFoxDesiredCapabilities()
	{
		final FirefoxOptions options = new FirefoxOptions();

		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("moz:firefoxOptions", options);

		final FirefoxProfile profile = new FirefoxProfile();
		capabilities.setCapability(FirefoxDriver.PROFILE, profile);

		capabilities.setCapability("marionette", true);
		capabilities.setCapability("platform", "WINDOWS");
		capabilities.setBrowserName("firefox");
		capabilities.setCapability("acceptInsecureCerts", true);

		return capabilities;
	}

	private static DesiredCapabilities getInternetExploreDesiredCapabilities()
	{
		final LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.OFF);
		final DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setVersion("9");
		return capabilities;
	}

	private static DesiredCapabilities getPhantomJsCapabilities()
	{
		final LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.OFF);
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		final String[] cli_args = new String[]{"--ssl-protocol=any", "--ignore-ssl-errors=true", "--web-security=false"};
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cli_args);
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("takesScreenshot", true);
		return capabilities;
	}

	private static DesiredCapabilities getAppiumDesiredCapabilities()
	{
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, APPIUM_BROWSER_NAME);
		capabilities.setCapability("plaformVersion", APPIUM_PLATFORM_VERSION);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, APPIUM_PLATFORM_NAME);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, APPIUM_DEVICE_NAME);
		capabilities.setCapability("automationName", "XCUITest");
		return capabilities;
	}

	private static DesiredCapabilities getSauceSimulatorCapabilities()
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appiumVersion", BROWSER_VERSION);
		capabilities.setCapability("deviceName", DEVICE_NAME);
		capabilities.setCapability("deviceOrientation", "portrait");
		capabilities.setCapability("platformVersion", OS_VERSION);
		capabilities.setCapability("platformName", PLATFORM);
		capabilities.setCapability("browserName", "Safari");
		capabilities.setCapability("build", "NewLook" + " Test - " + SessionInfo.startTime);
		capabilities.setCapability("name", SessionInfo.scenarioName);
//        capabilities.setCapability("record-video", true);
//        capabilities.setCapability("record-screenshots", true);
		capabilities.setCapability("sharetunnel", "dominic.corbin");
		capabilities.setCapability("--disable-web-security", true);
//        capabilities.setCapability("acceptInsecureCerts", true);
		return capabilities;
	}

	private static DesiredCapabilities getSauceCapabilities()
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", BROWSER);
		capabilities.setCapability("platform", PLATFORM);
		capabilities.setCapability("version", BROWSER_VERSION);
		capabilities.setCapability("sauce-advisor", true);
		capabilities.setCapability("idleTimeout", "200");
		capabilities.setCapability("build", "NewLook" + " Test - " + SessionInfo.startTime);
		capabilities.setCapability("name", SessionInfo.scenarioName);
//        capabilities.setCapability("record-video", true);
//        capabilities.setCapability("record-screenshots", true);
		capabilities.setCapability("sharetunnel", "dominic.corbin");
		capabilities.setCapability("--disable-web-security", true);
		capabilities.setCapability("screenResolution", BROWSER_WINDOW_WIDTH + "x" + BROWSER_WINDOW_HEIGHT);
//        capabilities.setCapability("acceptInsecureCerts", true);
		return capabilities;
	}

	private static DesiredCapabilities getSauceMobileCapabilities()
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", DEVICE_NAME);
		capabilities.setCapability("platformVersion", OS_VERSION);
		capabilities.setCapability("platformName", PLATFORM);
		capabilities.setCapability("recordScreenshots", false);
		capabilities.setCapability("extendedDebugging", false);
		capabilities.setCapability("maxDuration", "1800");
		capabilities.setCapability("appiumVersion", "1.16.0");
		capabilities.setCapability("build", "NewLook" + " Test - " + SessionInfo.startTime);
		capabilities.setCapability("name", SessionInfo.scenarioName);
//        capabilities.setCapability("browserName", SAUCE_LABS_BROWSER);
//        capabilities.setCapability("privateDevicesOnly", SAUCE_LABS_PRIVATE_DEVICES_ONLY);
		capabilities.setCapability("testobject_api_key", "1E3FDE514D28433899FBDB350AF6F272");
		capabilities.setCapability("sharetunnel", "dominic.corbin");
		return capabilities;
	}

	private static void startLocalBrowserEmulatorDriver()
	{
		final Map<String, Object> mobileEmulation = new HashMap<>();
		final Map<String, Object> deviceMetrics = new HashMap<>();
		final String userAgent;
		switch (DEVICE_NAME)
		{
			case "iPhone 11":
				deviceMetrics.put("width", 414);
				deviceMetrics.put("height", 896);
				deviceMetrics.put("pixelRatio", 2);
				userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.4 Mobile/15E148 Safari/604.1";
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", userAgent);
				break;
			case "iPhone 11 Pro":
				deviceMetrics.put("width", 375);
				deviceMetrics.put("height", 812);
				deviceMetrics.put("pixelRatio", 3);
				userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.4 Mobile/15E148 Safari/604.1";
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", userAgent);
				break;
			case "iPhone 11 Pro Max":
				deviceMetrics.put("width", 414);
				deviceMetrics.put("height", 896);
				deviceMetrics.put("pixelRatio", 3);
				userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.4 Mobile/15E148 Safari/604.1";
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", userAgent);
				break;
			case "iPhone 8":
				mobileEmulation.put("deviceName", "iPhone 8");
				break;
			case "Galaxy S9":
				deviceMetrics.put("width", 360);
				deviceMetrics.put("height", 740);
				deviceMetrics.put("pixelRatio", 4);
//			    userAgent="Mozilla/5.0 (Linux; Android 9; SAMSUNG SM-G977N Build/PPR1.180610.011) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/9.2 Chrome/67.0.3396.87 Mobile Safari/537.36";
				userAgent = "Mozilla/5.0 (Linux; Android 8.0.0; SM-G960F Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.137 Mobile Safari/537.36";
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", userAgent);
				break;
			case "Galaxy S10":
				deviceMetrics.put("width", 360);
				deviceMetrics.put("height", 760);
				deviceMetrics.put("pixelRatio", 4);
				userAgent = "Mozilla/5.0 (Linux; Android 8.0.0; SM-G960F Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.137 Mobile Safari/537.36";
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", userAgent);
				break;
			case "iPad":
				mobileEmulation.put("deviceName", "iPad");
				break;
//				5th generation
			case "iPad 2017":
				deviceMetrics.put("width", 768);
				deviceMetrics.put("height", 1024);
				deviceMetrics.put("pixelRatio", 2);
				userAgent = "Mozilla/5.0 (iPad; CPU OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148";
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", userAgent);
				break;
			default:
				LOG.error("Unknown device to emulate");
				throw new IllegalArgumentException("UnKnown Device to emulate");
		}

		final ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		REAL_DRIVER = new ChromeDriver(chromeOptions);
	}

	private static RemoteWebDriver getBrowserstackRemoteWebDriver(final DesiredCapabilities capabilities) throws MalformedURLException
	{
		LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
		return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (capabilities));
	}

	@Override
	public void close()
	{
		if (Thread.currentThread() != CLOSE_THREAD)
		{
			throw new UnsupportedOperationException(
					"You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
		}
		super.close();
	}

	private static void attachSauceLabsVideoLinkToScenario()
	{
		SessionInfo.sauceLabsVideoLink = "<a href=https://app.eu-central-1.saucelabs.com/tests/" +
										 REAL_DRIVER.getSessionId() +
										 ">Sauce Labs Video Link</a>";
	}
}
