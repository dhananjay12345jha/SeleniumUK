package com.salmon.test.framework.helpers.screenshot_helper;

import java.io.IOException;
import java.util.Map;

import com.salmon.test.allure.Report;

import io.cucumber.java.Scenario;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salmon.test.framework.helpers.WebDriverHelper;


import io.cucumber.java.After;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

public class ScreenshotHook
{

	private static final Logger LOG = LoggerFactory.getLogger(ScreenshotHook.class);

	//Not Execute for any api or database tests
	@After
	public void embedScreenshot(Scenario scenario)
	{
		final JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();

		try
		{
			Map<String, Object> screenShots = ScreenshotHelper.getScreenShotsForCurrentTest();
			for (Map.Entry<String, Object> screenShot : screenShots.entrySet())
			{
				scenario.log(screenShot.getKey());
				scenario.attach((byte[]) screenShot.getValue(), "image/png", "screenshot");
			}
			jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test Passed\"}}");

			ScreenshotHelper.tidyUpAfterTestRun();
			try
			{
				Report.copyCategoriesFile();
			} catch (IOException e){
				System.out.println(e+" - couldn't copy categories.json file from test/resources");
			}

			if (scenario.isFailed())
			{
				scenario.log(WebDriverHelper.getWebDriver().getCurrentUrl());
				byte[] screenShot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenShot, "image/png", "screenshot");
				Report.addScreenshotAsImage("failure screen", screenShot);
				jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Test Failed!\"}}");
			}

		}
		catch (WebDriverException | ClassCastException wde)
		{
			LOG.error(wde.getMessage());
		}
		finally
		{
			if (WebDriverHelper.getWebDriver() != null &&
					((RemoteWebDriver) WebDriverHelper.getWebDriver()).getSessionId() != null)
			{
				getWebDriver().switchTo().defaultContent();
				getWebDriver().quit();
			}
		}
	}
}
