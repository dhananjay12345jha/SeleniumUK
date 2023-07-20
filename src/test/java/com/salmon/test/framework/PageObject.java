package com.salmon.test.framework;


import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.salmon.test.framework.helpers.WebDriverHelper;

import io.cucumber.datatable.DataTable;

import lombok.Getter;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;
import static com.salmon.test.framework.helpers.WebDriverHelper.PLATFORM;
import static java.lang.String.format;

public abstract class PageObject
{

	private static final long DRIVER_WAIT_TIME = 20;
	//    private static final Logger LOG = LoggerFactory.getLogger(PageObject.class);
	private static final Logger LOG = LogManager.getLogger(PageObject.class.getName());
	protected final int DEFAULT_TIMEOUT = 20;
	private final int WAIT_INTERVAL = 1;
	@Getter
	protected WebDriverWait wait;
	@Getter
	protected WebDriver webDriver;


	protected PageObject()
	{
		this.webDriver = WebDriverHelper.getWebDriver();
		this.wait = new WebDriverWait(webDriver, DRIVER_WAIT_TIME);
	}

	public static ExpectedCondition<Boolean> listLoadedWithCount(final List<WebElement> elements, int no)
	{
		return new ExpectedCondition<Boolean>()
		{
			@Override
			public Boolean apply(WebDriver driver)
			{
				return (elements.size() >= no);
			}

			@Override
			public String toString()
			{
				return String.format("list loaded %s", elements);
			}
		};
	}

	public static void selectFromDropDown(final WebElement selector, final int value)
	{
		try
		{
			final ISelect dropdown = new Select(selector);
			dropdown.selectByIndex(value);
		}
		catch (final StaleElementReferenceException e)
		{

		}
	}


	public static void selectFromDropDown(final WebElement selector, final String value)
	{
		try
		{
			final ISelect dropdown = new Select(selector);
			dropdown.selectByValue(value);
		}
		catch (final StaleElementReferenceException e)
		{

		}
	}

	public void selectFromDropDownWithVisibleText(final WebElement selector, String text)
	{
		try
		{
			selector.click();
			final ISelect dropdown = new Select(selector);
			pause(2000);
			dropdown.selectByVisibleText(text);
		}
		catch (final StaleElementReferenceException e)
		{

		}
	}

	public void goToSite(String url)
	{
		LOG.info("Go to site: " + url);
		webDriver.get(url);
	}

	public void scrollForFocusAndClick(WebElement e, int timeout)
	{
		waitForElementVisible(e, timeout);
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", e);
		e.click();
	}

	public void selectFromDropdownByPatialText(By selectDropdown, By selectOption, String text)
	{
		getWebDriver().findElement(selectDropdown).click();
		waitForIsClickable(selectOption);
		List<WebElement> options = getWebDriver().findElements(selectOption);
		for (WebElement el : options
		)
		{
			if (el.getText().trim().equals(text))
			{
				el.click();
				break;
			}
			else if (el.getText().trim().contains(text))
			{
				el.click();
				break;
			}

		}
	}

	public WebElement getElementWithText(By by, String text) throws InterruptedException
	{
		return getWebDriver().findElements(by).stream()
				.filter(s -> StringUtils.containsIgnoreCase(s.getText(), text))
				.findFirst().get();
	}

	/**
	 * Returns the current Url from page
	 **/
	public String getCurrentUrl()
	{
		return webDriver.getCurrentUrl();
	}

	/**
	 * Returns the current page title from page
	 */
	public String getCurrentPageTitle()
	{
		return getWebDriver().getTitle();
	}

	/**
	 * An expectation for checking the title of a page.
	 *
	 * @param title the expected title, which must be an exact match
	 * @return true when the title matches, false otherwise
	 */
	public boolean checkPageTitle(String title)
	{
		return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.titleIs(title));
	}

	/**
	 * An expectation for checking that the title contains a case-sensitive
	 * substring
	 *
	 * @param title the fragment of title expected
	 * @return true when the title matches, false otherwise
	 */
	public boolean checkPageTitleContains(String title)
	{
		return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.titleContains(title));
	}

	/**
	 * An expectation for the URL of the current page to be a specific url.
	 *
	 * @param url the url that the page should be on
	 * @return <code>true</code> when the URL is what it should be
	 */
	public boolean checkPageUrlToBe(String url)
	{
		return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.urlToBe(url));
	}

	/**
	 * An expectation for the URL of the current page to contain specific text.
	 *
	 * @param fraction the fraction of the url that the page should be on
	 * @return <code>true</code> when the URL contains the text
	 */
	public boolean checkPageUrlContains(String fraction)
	{
		return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.urlContains(fraction));
	}

	/**
	 * Expectation for the URL to match a specific regular expression
	 *
	 * @param regex the regular expression that the URL should match
	 * @return <code>true</code> if the URL matches the specified regular expression
	 */

	public boolean checkPageUrlMatches(String regex)
	{
		return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.urlMatches(regex));
	}

	/**
	 * Clear the text in the element
	 * Find the dynamic element wait until its visible
	 *
	 * @param by Element location found by css, xpath, id etc...
	 **/
	protected WebElement waitForExpectedElementToClear(final By by)
	{
		waitForExpectedElement(by).clear();
		return waitForExpectedElement(by);
	}

	/**
	 * Find the dynamic element wait until its visible
	 *
	 * @param by Element location found by css, xpath, id etc...
	 **/
	protected WebElement waitForExpectedElement(final By by)
	{
		return wait.until(visibilityOfElementLocated(by));
	}

	/**
	 * Find the dynamic element wait until its visible for a specified time
	 *
	 * @param by                Element location found by css, xpath, id etc...
	 * @param waitTimeInSeconds max time to wait until element is visible
	 **/

	public WebElement waitForExpectedElement(final By by, long waitTimeInSeconds)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(getWebDriver(), waitTimeInSeconds);
			return wait.until(visibilityOfElementLocated(by));
		}
		catch (NoSuchElementException e)
		{
			LOG.info(e.getMessage());
			return null;
		}
		catch (TimeoutException e)
		{
			LOG.info(e.getMessage());
			return null;
		}
	}

	private ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) throws NoSuchElementException
	{
		return driver -> {
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				LOG.error(e.getMessage());
			}
			WebElement element = getWebDriver().findElement(by);
			return element.isDisplayed() ? element : null;
		};
	}

	/**
	 * An expectation for checking if the given text is present in the specified element.
	 *
	 * @param element the WebElement
	 * @param text    to be present in the element
	 * @return true once the element contains the given text
	 */
	public boolean textToBePresentInElement(WebElement element, String text)
	{
		return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElement(
				element,
				text));
	}

	/**
	 * An expectation for checking if the given text is present in the element that matches
	 * the given locator.
	 *
	 * @param by   used to find the element
	 * @param text to be present in the element found by the locator
	 * @return true once the first element located by locator contains the given text
	 */
	public boolean textToBePresentInElementLocated(final By by, final String text)
	{
		return new WebDriverWait(getWebDriver(),
				DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElementLocated(by, text));
	}

	/**
	 * An expectation for checking if the given text is present in the specified
	 * elements value attribute.
	 *
	 * @param element the WebElement
	 * @param text    to be present in the element's value attribute
	 * @return true once the element's value attribute contains the given text
	 */
	public boolean textToBePresentInElementValue(final WebElement element, final String text)
	{
		return new WebDriverWait(getWebDriver(),
				DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElementValue(element,
				text));
	}

	/**
	 * An expectation for checking if the given text is present in the specified
	 * elements value attribute.
	 *
	 * @param by   used to find the element
	 * @param text to be present in the value attribute of the element found by the locator
	 * @return true once the value attribute of the first element located by locator contains
	 * the given text
	 */
	public boolean textToBePresentInElementValue(final By by, final String text)
	{
		return new WebDriverWait(getWebDriver(),
				DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElementValue(by, text));
	}

	/**
	 * An expectation for checking whether the given frame is available to switch
	 * to. <p> If the frame is available it switches the given driver to the
	 * specified frame.
	 *
	 * @param frameLocator used to find the frame (id or name)
	 */
	public WebDriver frameToBeAvailableAndSwitchToIt(final String frameLocator)
	{
		return new WebDriverWait(getWebDriver(),
				DRIVER_WAIT_TIME).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	/**
	 * An expectation for checking whether the given frame is available to switch
	 * to. <p> If the frame is available it switches the given driver to the
	 * specified frame.
	 *
	 * @param by used to find the frame
	 */
	public WebDriver frameToBeAvailableAndSwitchToIt(final By by)
	{
		return new WebDriverWait(getWebDriver(),
				DRIVER_WAIT_TIME).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
	}

	/**
	 * An expectation for checking that an element is either invisible or not
	 * present on the DOM.
	 *
	 * @param by used to find the element
	 */
	public boolean invisibilityOfElementLocated(By by)
	{
		return (new WebDriverWait(getWebDriver(),
				DRIVER_WAIT_TIME)).until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	/**
	 * An expectation for checking that an element with text is either invisible
	 * or not present on the DOM.
	 *
	 * @param by   used to find the element
	 * @param text of the element
	 */
	public boolean invisibilityOfElementWithText(final By by, final String text)
	{
		return (new WebDriverWait(getWebDriver(),
				DRIVER_WAIT_TIME)).until(ExpectedConditions.invisibilityOfElementWithText(by, text));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 *
	 * @param by used to find the element
	 * @return the WebElement once it is located and clickable (visible and enabled)
	 */
	public WebElement elementToBeClickable(By by)
	{
		return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(by));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 *
	 * @param element the WebElement
	 * @return the (same) WebElement once it is clickable (visible and enabled)
	 */

	public WebElement elementToBeClickable(final WebElement element)
	{
		return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(
				element));
	}

	public void waitForListLoaded(List<WebElement> elements, int no, long wait)
	{
		new WebDriverWait(webDriver, wait)
				.until(listLoadedWithCount(elements, no));

	}

	/**
	 * Wait until an element is no longer attached to the DOM.
	 *
	 * @param element The element to wait for.
	 * @return false is the element is still attached to the DOM, true
	 * otherwise.
	 */
	public boolean stalenessOf(final WebElement element)
	{
		return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.stalenessOf(element));
	}

	/**
	 * An expectation for checking if the given element is selected.
	 */
	public boolean elementToBeSelected(final By by)
	{
		return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeSelected(by));
	}

	/**
	 * An expectation for checking if the given element is selected.
	 */
	public boolean elementToBeSelected(final WebElement element)
	{
		return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeSelected(
				element));
	}

	/**
	 * An expectation for checking if the given element is selected.
	 */
	public boolean elementSelectionStateToBe(final WebElement element, final boolean selected)
	{
		return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementSelectionStateToBe(
				element,
				selected));
	}

	/**
	 * An expectation for checking if the given element is selected.
	 */
	public boolean elementSelectionStateToBe(final By by,
	                                         final boolean selected)
	{
		return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementSelectionStateToBe(
				by,
				selected));
	}

	public void waitForAlert()
	{
		(new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 *
	 * @param by used to find the element
	 * @return the list of WebElements once they are located
	 */
	public List<WebElement> visibilityOfAllElementsLocatedBy(final By by)
	{
		return (new WebDriverWait(getWebDriver(),
				DRIVER_WAIT_TIME)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 *
	 * @param elements list of WebElements
	 * @return the list of WebElements once they are located
	 */
	public List<WebElement> visibilityOfAllElements(final List<WebElement> elements)
	{
		return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.visibilityOfAllElements(
				elements));
	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 *
	 * @param by used to find the element
	 * @return the list of WebElements once they are located
	 */
	public List<WebElement> presenceOfAllElementsLocatedBy(final By by)
	{
		return (new WebDriverWait(getWebDriver(),
				DRIVER_WAIT_TIME)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}

	/**
	 * An expectation for checking that an element, known to be present on the DOM
	 * of a page, is visible. Visibility means that the element is not only
	 * displayed but also has a height and width that is greater than 0.
	 *
	 * @param element the WebElement
	 * @return the (same) WebElement once it is visible
	 */

	public WebElement visibilityOf(final WebElement element)
	{
		return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a
	 * page. This does not necessarily mean that the element is visible.
	 *
	 * @param by used to find the element
	 * @return the WebElement once it is located
	 */
	public boolean isElementPresent(final By by)
	{
		try
		{
			new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.presenceOfElementLocated(by));

		}
		catch (TimeoutException exception)
		{
			LOG.info(exception.getMessage());
			return false;
		}
		return true;
	}

	public boolean isElementPresent(WebElement element, long wait)
	{
		try
		{
			new WebDriverWait(webDriver, wait).until(ExpectedConditions.textToBePresentInElement(element, ""));
			return true;
		}
		catch (Exception exception)
		{
			return false;
		}
	}

	public boolean isElementPresent(By by, long wait)
	{
		try
		{
			new WebDriverWait(webDriver, wait).until(ExpectedConditions.presenceOfElementLocated(by));
			return true;
		}
		catch (Exception exception)
		{
			return false;
		}
	}

	public WebDriver getBrowserByPageTitle(String pageTitle)
	{
		for (String windowHandle : webDriver.getWindowHandles())
		{
			webDriver = webDriver.switchTo().window(windowHandle);
			if (pageTitle.equalsIgnoreCase(webDriver.getTitle()))
			{
				return webDriver;
			}
		}
		return null;
	}

	public void navigateToPreviousPageUsingBrowserBackButton()
	{
		webDriver.navigate().back();
	}

	public void clickWithinElementWithXYCoordinates(WebElement webElement, int x, int y)
	{
		Actions builder = new Actions(webDriver);
		builder.moveToElement(webElement, x, y);
		builder.click();
		builder.perform();
	}

	public String getElementByTagNameWithJSExecutor(String tagName)
	{
		return ((JavascriptExecutor) webDriver).executeScript(
				"return window.getComputedStyle(document.getElementsByTagName('" + tagName + "')").toString();
	}

	public String getElementByQueryJSExecutor(String cssSelector)
	{
		return ((JavascriptExecutor) webDriver).executeScript("return window.getComputedStyle(document.querySelector('" +
				cssSelector +
				"')").toString();
	}

	public WebElement waitForAndGetElement(final By element, final int waitTime)
	{
		for (int tick = 0; tick < waitTime / WAIT_INTERVAL; tick++)
		{
			try
			{
				if (!webDriver.findElements(element).isEmpty() && webDriver.findElement(element).isEnabled())
				{
					return webDriver.findElement(element);
				}
			}
			catch (Exception e)
			{
				LOG.info("Exception for: " + element);
				LOG.info(e.getMessage());
			}
		}
		return null;
	}

	public WebElement waitForExpectedElement1(final By element, final int waitTime)
	{
		WebDriverWait wait = new WebDriverWait(webDriver, waitTime);
		try
		{
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		catch (TimeoutException | ElementNotFoundException e)
		{
			LOG.info("Element not found or timed out for : " + element);
			LOG.info(e.getMessage());
			return null;
		}
	}

	public void waitForPopUp(int waitTime)
	{
		for (int tick = 0; tick < waitTime / WAIT_INTERVAL; tick++)
		{
			try
			{
				webDriver.switchTo().activeElement().isDisplayed();
				if (!webDriver.getWindowHandle().isEmpty())
				{
					return;
				}
			}
			catch (final StaleElementReferenceException ignored)
			{
				// Do nothing, keep trying
			}
		}

	}

	public void clickWhenClickable(WebElement element, int wait)
	{
		scrollToObject(element);
		waitForIsClickable(element, wait);
		element.click();
	}

//    public Boolean waitForElementToBeClickable(List<WebElement> element, final Integer... timeout) {
//        try {
//            final Wait<WebDriver> wait = new FluentWait<>(webDriver).withTimeout(timeout[0], TimeUnit.MILLISECONDS)
//                    .pollingEvery(5500, TimeUnit.MILLISECONDS);
//            wait.until((ExpectedCondition<Boolean>) wdriver ->
//            {
//                try {
//                    if (element.isEmpty()) {
//                        return false;
//                    }
//                    return element.get(0).isDisplayed();
//                } catch (final StaleElementReferenceException e) {
//                    LOG.debug(e.getMessage() + "\n");
//                    LOG.debug("Trying again ...");
//                    return false;
//
//                }
//            });
//        } catch (final org.openqa.selenium.TimeoutException exception) {
//            return false;
//        }
//        return true;
//    }


	public void scrollForFocusAndClick(By by, int timeout)
	{
		waitForElementVisible(by, timeout);
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webDriver.findElement(by));
		webDriver.findElement(by).click();
	}

	public void mouseHoverByJavaScript(WebElement webElement)
	{
		String mouseOverScript = "if (document.querySelector('.masthead-account__popup')) { document.querySelector('.masthead-account__popup').style.display = 'block' }";
		((JavascriptExecutor) webDriver).executeScript(mouseOverScript, webElement);
	}

	public void waitForElementVisible(By by, long wait)
	{
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, wait);
		LOG.info("Waiting for element visible [{}]. Timeout:{} sec", by, wait);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
		LOG.info("found element visibility [{}]", by);
	}

	public WebElement returnAvailableElement(By by)
	{
		if(webDriver.findElements(by).size() == 1)
		{
			return webDriver.findElement(by);
		}
		else if (webDriver.findElements(by).size() > 1)
		{
			return webDriver.findElements(by).get(1);
		}
		else
		{
			return null;
		}
	}

	public void clearAndSendkeys(By by, String text)
	{
		webDriver.findElement(by).clear();
		webDriver.findElement(by).sendKeys(text);
	}

	public void waitForTextToBePresentInElement(By element, String text, long wait)
	{
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, wait);
		LOG.info("Wait for text '{}' to be displayed. Timeout:{} seconds", text, wait);
		webDriverWait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	public void scrollIntoView(final By locator)
	{
		try
		{
			final WebElement element = webDriver.findElement(locator);
			final JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		}
		catch (Exception e)
		{
			LOG.info("Not able to scroll to the element");
		}
	}

	public void clickByJavaScriptExecutor(final WebElement element)
	{
		pause(1000);
		((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", element);
	}

	public void staleElementHandle(WebElement element)
	{
		int count = 0;
		boolean clicked = false;
		while (count < 4 && !clicked)
		{
			try
			{
				element.click();
				clicked = true;
			}
			catch (StaleElementReferenceException e)
			{
				e.toString();
				LOG.info("Trying to recover from a stale element :" + e);
				count = count + 1;
			}
		}
	}

	public void scrollToObject(final WebElement element)
	{
		final int elementPosition = element.getLocation().getY();
		final String js = format("window.scroll(0, %s)", elementPosition);
		((JavascriptExecutor) webDriver).executeScript(js);
	}

	public void waitForPageTitleContains(String text, long wait)
	{
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, wait);
		LOG.info("Waiting for page: " + wait + " seconds");
		webDriverWait.until(ExpectedConditions.titleContains(text));
	}

	public List<String> normaliseList(final Collection<String> list)
	{
		final List<String> formattedList = list.stream()
				.collect(Collectors.toList());
		Collections.sort(formattedList);
		return formattedList;
	}

	public void deleteAllCookies()
	{
		webDriver.manage().deleteAllCookies();
	}


	public boolean elementPresent(By element)
	{
		try
		{
			getWebDriver().findElement(element);
			return true;
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}

	public void scrollToBottom()
	{
		((JavascriptExecutor) getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollToTop()
	{
		((JavascriptExecutor) getWebDriver()).executeScript("window.scrollTo(0, 0)");
	}

	public void scrollHalfwayDown()
	{
		((JavascriptExecutor) getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
	}

	public void scrollByCoordinates(int coordinate)
	{
		((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0," + coordinate + ")", "");
	}

	public void clickLink(String linkText)
	{
		webDriver.findElements(By.cssSelector("a"))
				.stream()
				.filter(a -> a.getText().contains(linkText))
				.findFirst()
				.get()
				.click();
	}

	public WebElement getLink(String linkText)
	{
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return webDriver.findElements(By.cssSelector("a"))
				.stream()
				.filter(a -> a.getText().contains(linkText))
				.findFirst()
				.get();
	}

	public Boolean waitForIsClickable(List<WebElement> element, final Integer... timeout)
	{
		try
		{
			final Wait<WebDriver> wait = new FluentWait<>(webDriver).withTimeout(timeout[0], TimeUnit.MILLISECONDS)
					.pollingEvery(5500, TimeUnit.MILLISECONDS);
			wait.until((ExpectedCondition<Boolean>) wdriver ->
			{
				try
				{
					if (element.isEmpty())
					{
						return false;
					}
					return element.get(0).isDisplayed();
				}
				catch (final StaleElementReferenceException e)
				{
					LOG.debug(e.getMessage() + "\n");
					LOG.debug("Trying again ...");
					return false;

				}
			});
		}
		catch (final org.openqa.selenium.TimeoutException exception)
		{
			return false;
		}
		return true;
	}


	public Boolean waitForIsClickable(WebElement element, final Integer... timeout)
	{
		try
		{
			final Wait<WebDriver> wait = new FluentWait<>(webDriver).withTimeout(timeout[0], TimeUnit.MILLISECONDS)
					.pollingEvery(5500, TimeUnit.MILLISECONDS);
			wait.until((ExpectedCondition<Boolean>) wdriver ->
			{
				try
				{
					if (element.isDisplayed())
					{
						return false;
					}
					return element.isDisplayed();
				}
				catch (final StaleElementReferenceException e)
				{
					LOG.debug(e.getMessage() + "\n");
					LOG.debug("Trying again ...");
					return false;

				}
			});
		}
		catch (final org.openqa.selenium.TimeoutException exception)
		{
			return false;
		}
		return true;
	}

	public Boolean waitForIsClickable(By by, final Integer... timeout)
	{
		try
		{
			final Wait<WebDriver> wait = new FluentWait<>(webDriver).withTimeout(timeout[0], TimeUnit.MILLISECONDS)
					.pollingEvery(5500, TimeUnit.MILLISECONDS);
			wait.until((ExpectedCondition<Boolean>) wdriver ->
			{
				try
				{
					if (getWebDriver().findElement(by).isDisplayed())
					{
						return false;
					}
					return getWebDriver().findElement(by).isDisplayed();
				}
				catch (final StaleElementReferenceException e)
				{
					LOG.debug(e.getMessage() + "\n");
					LOG.debug("Trying again ...");
					return false;

				}
			});
		}
		catch (final org.openqa.selenium.TimeoutException exception)
		{
			return false;
		}
		return true;
	}

	public void hoverOnElement(WebElement element)
	{
		Actions action = new Actions(webDriver);
		action.moveToElement(element).perform();
		pause(2000);
	}

	public void hoverAndClick(WebElement element)
	{
		Actions action = new Actions(webDriver);
		action.moveToElement(element).perform();
		pause(2000);
		action.click(element).perform();
	}

	public void hoverOnElementAndClick(WebElement element, WebElement elementToBeClicked)
	{
		Actions action = new Actions(webDriver);
		action.moveToElement(element).perform();
		pause(2000);
		action.click(element).perform();
		elementToBeClicked.click();
	}

	public List<String> getHeaderLabel()
	{
		waitForElementVisible(By.cssSelector("h2"), DEFAULT_TIMEOUT);
		waitForAndGetElement(By.cssSelector("h2"), DEFAULT_TIMEOUT);
		return webDriver.findElements(By.cssSelector("h2"))
				.stream()
				.map(WebElement::getText)
				.collect(Collectors.toList());
	}

	public List<String> getHeaderH3Label()
	{
		return webDriver.findElements(By.cssSelector("h3[class*='saveditems__actionbar']"))
				.stream()
				.map(WebElement::getText)
				.collect(Collectors.toList());
	}


	public void sendTextToField(By by, String text)
	{

		List<WebElement> elements = webDriver.findElements(by);
		elements.stream()
				.filter(element -> Pattern.compile("check_(\\d+)_box").matcher(element.getAttribute("id")).matches())
				.forEach(element -> element.sendKeys(text));
	}

	public WebElement waitForElementPresence(final By by)
	{
		this.wait = new WebDriverWait(this.getWebDriver(), DRIVER_WAIT_TIME);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(by)));
		return getWebDriver().findElement(by);
	}

	public WebElement waitForElementPresence(final By by, int timeout)
	{
		this.wait = new WebDriverWait(this.getWebDriver(), timeout);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(by)));
		return getWebDriver().findElement(by);
	}

	public void scrollElementIntoView(final By by)
	{
		if (PLATFORM.contains("OS") || BROWSER.contains("firefox") || BROWSER.contains("iPhone") || BROWSER.contains("Android"))
		{
			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);",
					webDriver.findElement(by));
		}
		else
		{
			Actions actions = new Actions(getWebDriver());
			actions.moveToElement(getWebDriver().findElement(by)).perform();
		}
	}

	public void scrollElementIntoView(final WebElement webElement)
	{
		Actions actions = new Actions(getWebDriver());
		actions.moveToElement(webElement).perform();
	}

	public void scrollElementIntoViewAndClick(final WebElement webElement)
	{
		Actions actions = new Actions(getWebDriver());
		actions.moveToElement(webElement);
		actions.perform();
		webElement.click();
	}

	public List<String> addDataTableItemsToList(DataTable table)
	{
		List<String> tableElements = new ArrayList<>();
		for (int i = 0; i < table.cells().size(); i++)
		{
			tableElements.add(table.cells().get(i).toString().replace("]", "").replace("[", ""));
		}
		return tableElements;
	}


	public void scrollForLocationFocusAndClick(By by, int timeout)
	{
		waitForElementVisible(by, timeout);
		WebElement element = webDriver.findElement(by);
		int elementPosition = element.getLocation().getY();
		String js = String.format("window.scroll(0, %s)", elementPosition);
		((JavascriptExecutor) webDriver).executeScript(js);
		element.click();
	}

	public boolean elementPresence(By element)
	{
		try
		{
			getWebDriver().findElement(element);
			return true;
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}


	public void scrollForFocus(By by, int timeout)
	{
		waitForElementVisible(by, timeout);
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webDriver.findElement(by));
		pause(2000);

	}

	public void waitForPresenceAndScrollForFocusAndClick(By by, int timeout)
	{
		waitForElementPresence(by, timeout);
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webDriver.findElement(by));
		webDriver.findElement(by).click();
	}

	public void waitForPresenceOfElement(WebElement element, long wait)
	{
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, wait);
		webDriverWait
				.ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.textToBePresentInElement(element, ""));
	}

	public void waitForPresenceOfElement(By by, long wait)
	{
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, wait);
		webDriverWait
				.ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public WebElement getButtonWithText(String text)
	{
		pause(2000);
		return getWebDriver().findElements(By.cssSelector("button")).stream()
				.filter(s -> StringUtils.containsIgnoreCase(s.getText(), text))
				.findFirst().get();
	}

	public WebElement getLabelWithText(String text)
	{
		return getWebDriver().findElements(By.cssSelector("label")).stream()
				.filter(s -> StringUtils.containsIgnoreCase(s.getText(), text))
				.findFirst().get();
	}


	public void waitForElementVisible(WebElement element, long wait)
	{
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, wait);
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
	}

	public boolean isElementVisible(WebElement element, long wait)
	{
		try
		{
			waitForElementVisible(element, wait);
			return true;
		}
		catch (Exception exception)
		{
			return false;
		}
	}

	public boolean isElementVisible(By by, long wait)
	{
		try
		{
			waitForElementVisible(by, wait);
			return true;
		}
		catch (Exception exception)
		{
			return false;
		}
	}

	public boolean isElementVisible(final By by)
	{
		return isElementVisible(by, DEFAULT_TIMEOUT);
	}

	public boolean isElementVisible(final SearchContext parent, final By by, final long wait)
	{
		if (!parent.findElements(by).isEmpty())
		{
			try
			{
				return isElementVisible(parent.findElement(by), wait);
			}
			catch (final Exception ex)
			{
				LOG.error("Error checking for visibility of " + by, ex);
			}
		}

		return false;
	}

	public void scrollToElementByElement(WebElement element)
	{
		Coordinates coordinates = ((Locatable) element).getCoordinates();
		coordinates.inViewPort();

	}

	public String selectFromDropdownRandom(By selectDropDown)
	{
		Select select = new Select(webDriver.findElement(selectDropDown));
		int index = ThreadLocalRandom.current().nextInt(select.getOptions().size());
		String selectedOption = select.getOptions().get(index).getText();
		select.selectByIndex(index);
		LOG.info(selectedOption);
		return selectedOption;
	}

	public void refresh()
	{
		webDriver.navigate().refresh();
	}

	public WebElement findElementOrThrowException(final By by) throws WebElementNotFoundException
	{
		waitForAndGetElement(webDriver, by, DEFAULT_TIMEOUT);

		return webDriver.findElements(by)
				.stream()
				.findFirst()
				.orElseThrow(() -> new WebElementNotFoundException(by));
	}

	public void waitForAndGetElement(final SearchContext element,
	                                 final By by,
	                                 final int waitTime)
	{
		for (int tick = 0; tick < waitTime / WAIT_INTERVAL; tick++)
		{
			if (!element.findElements(by).isEmpty())
			{
				final WebElement foundElement = element.findElement(by);

				try
				{
					if (foundElement.isDisplayed() && foundElement.isEnabled())
					{
						return;
					}
				}
				catch (final StaleElementReferenceException ignored)
				{
					// Do nothing, keep trying
				}
			}
		}
	}

	public void waitForH3Heading(String text, int wait)
	{
		new WebDriverWait(getWebDriver(), wait)
				.ignoring(StaleElementReferenceException.class)
				.until(findElementWithText(By.cssSelector("h3"), text));
	}

	public void waitForH2Heading(String text, int wait)
	{
		new WebDriverWait(getWebDriver(), wait)
				.ignoring(StaleElementReferenceException.class)
				.until(findElementWithText(By.cssSelector("h2"), text));
	}

	public void waitForElementWithText(By by, String text, int wait)
	{
		new WebDriverWait(getWebDriver(), wait)
				.ignoring(StaleElementReferenceException.class)
				.until(findElementWithText(by, text));
	}

	public static ExpectedCondition<Boolean> findElementWithText(By by, String text)
	{

		return new ExpectedCondition<Boolean>()
		{
			@Override
			public Boolean apply(WebDriver driver)
			{
				try
				{
					driver.findElements(by)
							.stream()
							.filter(a -> a.getText().equalsIgnoreCase(text.trim()))
							.findFirst()
							.get();
					return true;
				}
				catch (Exception e)
				{
					return null;
				}
			}

			@Override
			public String toString()
			{
				return String.format("finding text %s", text);
			}
		};
	}

	public boolean isAlertPresent(int wait)
	{

		try
		{
			new WebDriverWait(webDriver, wait).until(ExpectedConditions.alertIsPresent());
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public void assertAndVerifyElement(By element)
	{

		boolean isPresent = false;

		for (int i = 0; i < 5; i++)
		{
			try
			{
				if (getWebDriver().findElement(element) != null)
				{
					isPresent = true;
					break;
				}
			}
			catch (Exception e)
			{
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		Assert.assertTrue("\"" + element + "\" is not present.", isPresent);
	}

	public void assertAndVerifyElement(WebElement element)
	{

		boolean isPresent = false;

		for (int i = 0; i < 5; i++)
		{
			try
			{
				if ((element) != null)
				{
					isPresent = true;
					break;
				}
			}
			catch (Exception e)
			{
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		Assert.assertTrue("\"" + element + "\" is not present.", isPresent);
	}

	public void waitUntilElementNotDisplayed(final WebElement webElement, int TIMEOUT)
	{
		WebDriverWait wait = new WebDriverWait(webDriver, TIMEOUT);
		ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>()
		{
			public Boolean apply(WebDriver arg0)
			{
				try
				{
					webElement.isDisplayed();
					return false;
				}
				catch (NoSuchElementException e)
				{
					return true;
				}
				catch (StaleElementReferenceException f)
				{
					return true;
				}
			}
		};
//        wait.until(elementIsDisplayed);
	}

	public void waitUntilElementNotDisplayed(final By by)
	{
		final WebDriverWait wait = new WebDriverWait(webDriver, DEFAULT_TIMEOUT);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public void pause(int timeInMilliSeconds)
	{
		try
		{
			Thread.sleep(timeInMilliSeconds);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public void clickElementByQueryJSExecutor(final WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		jse.executeScript("arguments[0].scrollIntoView()", element);
		element.click();
	}

	public void pressEnterFromKeyboard(By element)
	{
		webDriver.findElement(element).click();
		webDriver.findElement(element).sendKeys(Keys.ENTER);
	}

	public WebElement find(By locator)
	{
		return webDriver.findElement(locator);
	}

	public void click(By locator)
	{
		waitForExpectedElement1(locator, DEFAULT_TIMEOUT).click();
	}

	public void click(By locator, int time)
	{
		waitForExpectedElement1(locator, time).click();
	}

	public String getInnerTextJavaScript(WebElement element)
	{
		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		return (String) (jse.executeScript("return jQuery(arguments[0]).text();", element));
	}

	public void waitForPageLoad()
	{
		Wait<WebDriver> wait = new WebDriverWait(webDriver, 120);
		wait.until(new Function<WebDriver, Boolean>()
		{
			public Boolean apply(WebDriver driver)
			{
				((JavascriptExecutor) driver).executeScript("return document.readyState");
				return String
						.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	public String getElementText(By locator)
	{
		waitForElementVisible(locator, DEFAULT_TIMEOUT);
		return getWebDriver().findElement(locator).getText();
	}

	public String getCookieValue(String cookieName)
	{
		return getWebDriver().manage().getCookieNamed(cookieName).getValue();
	}

	public String getAttributeValue(By locator, String attribute)
	{
		return getWebDriver().findElement(locator).getAttribute(attribute);
	}

	public String getAttributeValue(WebElement element, String attribute)
	{
		return element.getAttribute(attribute);
	}

	public void selectDropdownFirstValue(By by)
	{
		waitForElementVisible(by, 20);
		getWebDriver().findElements(by).stream().findFirst().get().click();
	}

	public String getNetworkLog()
	{
		String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
		String netData = ((JavascriptExecutor) webDriver).executeScript(scriptToExecute).toString();
		return netData;
	}

	public void highlightElement(final WebElement element)
	{
		// Useful for debugging - can ensure we don't have the wrong element selected.
		final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

	public void verifyFontAndSize(By locator, String fontSize, String rgb)
	{
		verifyFontAndSize(getWebDriver().findElement(locator),fontSize, rgb);
	}
	public void verifyFontAndSize(WebElement element, String fontSize, String rgb)
	{
		Assert.assertEquals(fontSize, element.getCssValue("font-size"));
//		Assert.assertEquals(rgb, element.getCssValue("color"));
	}

}
