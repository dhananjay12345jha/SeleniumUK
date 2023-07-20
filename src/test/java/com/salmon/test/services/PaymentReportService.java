package com.salmon.test.services;

import io.cucumber.java.Before;
import edu.emory.mathcs.backport.java.util.LinkedList;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.collections.Lists;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class PaymentReportService
{
	private static final Logger LOG = LoggerFactory.getLogger(PaymentReportService.class);

	private static final String CARD = "Card";
	private static final String POPUP = "Popup";
	private static final String SUCCESS = "Success";
	private static final String ORDER = "Order";

	private static boolean SETUP_COMPLETE;

	private static final List<List<String>> report = new LinkedList();

	public static void addCardToReport(final String card, final String popup, final String success, final String order)
	{
		report.add(Lists.newArrayList(card, popup, success, order));
	}

	@Before
	public static void addReportHook()
	{
		if (!SETUP_COMPLETE)
		{
			Runtime.getRuntime().addShutdownHook(new Thread(PaymentReportService::generateReportCsv));
			SETUP_COMPLETE = true;
		}
	}

	public static void generateReportCsv()
	{
		final File file = new File("psd2-payment-report.csv");

		if (file.exists())
		{
			try
			{
				FileUtils.write(file, "", Charset.defaultCharset());
			}
			catch (final IOException e)
			{
				LOG.error("Error clearing PSD2 report CSV", e);
			}
		}

		try
		{
			FileUtils.write(file,
							formatCardDetails(Lists.newArrayList(CARD, POPUP, SUCCESS, ORDER)),
							"UTF-8",
							true);
		}
		catch (final IOException e)
		{
			LOG.error("Error writing PSD2 report CSV header", e);
		}

		for (final List<String> cardDetails : report)
		{
			try
			{
				FileUtils.write(file,
								formatCardDetails(cardDetails),
								"UTF-8",
								true);
			}
			catch (final IOException e)
			{
				LOG.error("Error writing PSD2 report CSV values: " + formatCardDetails(cardDetails), e);
			}
		}
	}

	private static String formatCardDetails(final List<String> cardDetails)
	{
		return String.join(",", cardDetails) + "\n";
	}
}
