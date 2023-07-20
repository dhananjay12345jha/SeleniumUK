package com.salmon.test.properties;

import com.salmon.test.framework.helpers.Props;

public class PaymentProperties
{
	private static final String PAYMENT_PREFIX = "payment.";

	public static String getPaymentProperty(final String id)
	{
		return Props.getMessage(PAYMENT_PREFIX + id);
	}

	public static String klarnaDateOfBirth()
	{
		return getPaymentProperty("klarna.dateOfBirth");
	}
}
