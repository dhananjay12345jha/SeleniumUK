package constants;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

public class apiDetails
{

	public static final String MAO_STOCK_ALERT_API = "{\n" +
			"  \"stockStatus\": \"$stockLevel\",\n" +
			"  \"locations\": [\n" +
			"    {\n" +
			"      \"location\": \"newlookGlobalWarehouse\",\n" +
			"      \"quantity\": \"$dcStock\"\n" +
			"    },\n" +
			"    {\n" +
			"      \"location\": \"store\",\n" +
			"      \"quantity\": \"$storeStock\"\n" +
			"    }\n" +
			"  ],\n" +
			"  \"sku\": \"$sku\",\n" +
			"  \"view\": \"ECOMMERCE_COMBINED_NETWORK_VIEW\",\n" +
			"  \"timestamp\": \"$timeStamp\"\n" +
			"}";

	public static String getCurrentSystemDateTime(String type, long minutes) {
		ZonedDateTime dtime = ZonedDateTime.now(ZoneId.of("Europe/London")).truncatedTo(ChronoUnit.MILLIS);
		String time = type.equalsIgnoreCase("plus") ? dtime.plusMinutes(minutes).toString() : dtime.minusMinutes(minutes).toString();
		return 	time.split(Pattern.quote("["))[0];
	}

}
