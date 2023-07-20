package com.salmon.test.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SeleniumUtils
{
	public static void clearFieldAndType(final WebElement field, final String text)
	{
		field.clear();
		field.sendKeys(text);
	}

	public static WebElement findElementOrElseNull(final SearchContext context, final By locator)
	{
		if (context.findElements(locator).isEmpty())
		{
			return null;
		}
		else
		{
			return context.findElement(locator);
		}
	}

	public static void selectRandomFromDropdown(final WebElement dropdown)
	{
		selectRandomFromDropdown(new Select(dropdown));
	}

	private static void selectRandomFromDropdown(final ISelect dropdown)
	{
		final List<String> options = dropdown.getOptions()
											 .stream()
											 .filter(WebElement::isEnabled)
											 .filter(element -> !element.getAttribute("value").isEmpty())
											 .filter(element -> !"disabled".equals(element.getAttribute("disabled")))
											 .map(WebElement::getText)
											 .collect(Collectors.toList());

		dropdown.selectByVisibleText(options.get(new Random().nextInt(options.size())));
	}
}
