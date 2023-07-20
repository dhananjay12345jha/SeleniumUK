package com.salmon.test.properties;

import com.salmon.test.framework.helpers.Props;

public class AlertsProperties
{
	private static final String ALERT_PREFIX = "alert.";

	public static String getAlert(final String id)
	{
		return Props.getMessage(ALERT_PREFIX + id);
	}

	public static String paymentFailed()
	{
		return getAlert("payment.failed");
	}

	public static String paymentCannotAuthenticate()
	{
		return getAlert("payment.cannotAuthenticate");
	}

	public static String addedToBag()
	{
		return getAlert("pdp.addedToBag");
	}

	public static String fewItemsRemaining(final int count)
	{
		if (1 == count)
		{
			return getAlert("fewItemsRemaining.singular");
		}
		else
		{
			return String.format(getAlert("fewItemsRemaining.multiple"), count);
		}
	}

	public static String emailMeMessage()
	{
		return getAlert("emailMe");
	}

	public static String cartLimitMessage()
	{
		return getAlert("cartLimit");
	}

	public static String invalidCardMessage()
	{
		return getAlert("invalidCard");
	}

	public static String invalidExpiryDateMessage()
	{
		return getAlert("invalidExpiryDate");
	}

}
