package com.salmon.test.properties;

import com.salmon.test.framework.helpers.Props;

public class NavigationProperties
{
	private static final String NAVIGATION_PREFIX = "navigation.";

	public static String getNavigationProperty(final String id)
	{
		return Props.getMessage(NAVIGATION_PREFIX + id);
	}

	public static String testMeganavLink()
	{
		return getNavigationProperty("meganav.testDepartment");
	}

	public static String accountLink(final String id)
	{
		return getNavigationProperty("account." + id);
	}

	public static String addressBookLink()
	{
		return accountLink("addressBook");
	}
}
