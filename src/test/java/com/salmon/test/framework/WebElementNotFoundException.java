package com.salmon.test.framework;

import org.openqa.selenium.By;

public class WebElementNotFoundException extends Exception
{
	public WebElementNotFoundException(final String name)
	{
		super(String.format("Could not find element with name [%s]", name));
	}

	public WebElementNotFoundException(final By by)
	{
		super(String.format("Could not find element at by [%s]", by.toString()));
	}
}
