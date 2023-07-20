package com.salmon.test.framework.helpers;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;
import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

public class UrlBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(UrlBuilder.class);
    private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";
    private static URL basePath;
    private static URL apiUrl;

    static {
        try {
            Props.loadRunConfigProps(RUN_CONFIG_PROPERTIES);
            basePath = new URL(System.getProperty("site.url", Props.getProp("site.url")) + Props.getProp("locale"));

            apiUrl = new URL(System.getProperty("api.url", Props.getProp("api.url")));
        } catch (MalformedURLException e) {
            LOG.error(e.getMessage());
        }

    }

    public static String getSiteUrl() {
        return System.getProperty("site.url", Props.getProp("site.url"));
    }

    public static String getChopinUrl() {
        return Props.getProp("chopinUrl");
    }

    public static void startAtHomePage() throws MalformedURLException
    {
	    startAtPage(basePath);
    }

	public static void startAtPage(final URL path) throws MalformedURLException
    {
		WebDriverHelper.startWebDriver();
		WebDriverHelper.getWebDriver().manage().deleteAllCookies();
		WebDriverHelper.getWebDriver().navigate().refresh();
		LOG.debug("Navigation to the website -- " + path);
		WebDriverHelper.getWebDriver().navigate().to(path);
		if(BROWSER.contains("sauceMobile")|| BROWSER.contains("saucebrowseremulator")){
            new WebDriverWait(WebDriverHelper.getWebDriver(), 60).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        }
        else
        {
            new WebDriverWait(WebDriverHelper.getWebDriver(), 15).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        }

	}

	public static void startAtAppPage(String appPath) throws MalformedURLException
    {
		startAtPage(createApiUrl(appPath));
	}

	public static void startAtHomePage(String localte) {
        URL basePathLoc = null;
        try {
            basePathLoc = new URL(Props.getProp("site.url") + localte);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        WebDriverHelper.getWebDriver().manage().deleteAllCookies();
        WebDriverHelper.getWebDriver().navigate().to((basePathLoc));
    }

    public static void navigateToAddressBookPage() {
        WebDriverHelper.getWebDriver().navigate().to((basePath + "/my-account/address-book"));

    }

    public static void navigateToHomePage() {
        WebDriverHelper.getWebDriver().navigate().to((basePath));

    }


    public static void navigateToOrdersPage() {
        WebDriverHelper.getWebDriver().navigate().to((basePath + "/my-account/orders"));

    }

    ///my-account/saved-store-cards

    public static void navigateToStoreCardsPage() {
        WebDriverHelper.getWebDriver().navigate().to((basePath + "/my-account/saved-store-cards"));
    }

    public static void navigateToPaymentsCardsPage() {
        WebDriverHelper.getWebDriver().navigate().to((basePath + "/my-account/saved-cards"));
    }

    public static void navigateToPersonalDetailsPage(){
        WebDriverHelper.getWebDriver().navigate().to((basePath + "/my-account/profile"));
    }

    public static void startAtMobileHomePage() {
        AndroidHelper.getAndroidWebDriver().navigate().to(basePath);
    }

    public static URL getApiUrlForEndPoint(String endpoint) {
        return createApiUrl(endpoint);
    }

    public static URI getBasePathURI() {
        return URI.create(System.getProperty("api.url", Props.getProp("api.url")));
    }

	public static void navigateHomePageWithLocale(String locale){
		WebDriverHelper.getWebDriver().navigate().to(getSiteUrl() + locale);
	}

	public static void navigateToURL(String url){
		WebDriverHelper.getWebDriver().navigate().to(url);
	}

    public static String getUrl(String applicationUrl) {
        return Props.getProp(applicationUrl);
    }

    public static URL createApiUrl(String endpoint) {
        try {
            return new URL(apiUrl.getProtocol(), apiUrl.getHost(), apiUrl.getPort(), endpoint);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public static URL createUrl(String path) {
        try {
            return new URL(basePath.getProtocol(), basePath.getHost(), basePath.getPort(), path);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getWebsiteUrl() {
        return System.getProperty("site.url", Props.getProp("site.url"));
    }

    public  static void  launchHac(){
    	getWebDriver().get("https://apps-aws19.dev-newlook.com/hac/");
    }
	public  static void  launchNewLook(){
		getWebDriver().get("https://aws19.dev-newlook.com/uk/");
	}
}
