package com.salmon.test.properties;

import com.salmon.test.framework.helpers.Props;

import java.util.MissingResourceException;

public class CardProperties
{
	private static final String CARD_PREFIX = "card.";

	public static String getCardProperty(final String id)
	{
		return Props.getMessage(CARD_PREFIX + id);
	}

	public static String cardIcon(final String card)
	{
		return getCardProperty(card + ".icon");
	}

	public static String cardNumber(final String card)
	{
		return getCardProperty(card + ".number");
	}

	public static String cardExpiryMonth(final String card)
	{
		// Since cards of a shared type have the same format, we can fall back to a default
		// Saves us from having hundreds of identical properties
		try
		{
			return getCardProperty(card + ".expiry.month");
		}
		catch (final MissingResourceException ignored)
		{
			return getCardProperty(card.split("\\.")[0] + ".expiry.month");
		}
	}

	public static String cardExpiryYear(final String card)
	{
		try
		{
			return getCardProperty(card + ".expiry.year");
		}
		catch (final MissingResourceException ignored)
		{
			return getCardProperty(card.split("\\.")[0] + ".expiry.year");
		}
	}

	public static String cardCvv(final String card)
	{
		try
		{
			return getCardProperty(card + ".cvv");
		}
		catch (final MissingResourceException ignored)
		{
			return getCardProperty(card.split("\\.")[0] + ".cvv");
		}
	}

	public static String password3DS()
	{
		return getCardProperty("3ds.password");
	}

	public static String giftCardNumber()
	{
		return cardNumber("gift");
	}

	public static String giftCardPin()
	{
		return getCardProperty("gift.pin");
	}
}
