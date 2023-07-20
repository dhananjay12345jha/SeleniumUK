package com.salmon.test.properties;

import com.salmon.test.framework.helpers.Props;

public class FacetProperties
{
	private static final String FACET_PREFIX = "facet.";

	public static String getFacetProperty(final String id)
	{
		return Props.getMessage(FACET_PREFIX + id);
	}

	public static String colourFacet()
	{
		return getFacetProperty("colour");
	}

	public static String colourFacetValue()
	{
		return getFacetProperty("colour.value");
	}
}
