package com.salmon.test.properties;

import com.salmon.test.framework.helpers.Props;

public class ProductProperties
{
	private static final String PRODUCT_PREFIX = "product.";

	public static String getProductProperty(final String id)
	{
		return Props.getMessage(PRODUCT_PREFIX + id);
	}

	public static String productCode(final String id)
	{
		return getProductProperty(id + ".code");
	}

	public static String productName(final String id)
	{
		return getProductProperty(id + ".name");
	}
}
