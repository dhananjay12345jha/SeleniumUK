package com.salmon.test.services;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.salmon.test.framework.helpers.Props;

import constants.Impexes;
import com.salmon.test.framework.helpers.ApiHelper;

import constants.apiDetails;

import static com.jayway.restassured.RestAssured.given;
import static constants.apiDetails.getCurrentSystemDateTime;

/**
 * Created by gates on 16/01/18.
 */
public class NewLookTest extends ApiHelper {

    public static final String impexEndpoint = "/newlooktest/impex/raw";
    public static final String stockAPIEndpoint = "/webhooks/stock/alerts/";
    public static final String oauthToken = "/newlookapi/oauth/token";

    public static Response postNewLookStaffRecordImpex(String customerEmail) {
        String body = "REMOVE NewLookStaffRecord;customer(uid)[unique=true];\n" +
                ";"+customerEmail+";";
        return givenConfig().body(body).
                when().
                post(impexEndpoint);
    }
    public static Response postNewLookBazaarVoiceImpex(String productNumber, String status) {
     String body= Impexes.UPDATE_BAZAARVOICE.replace("$productNumber$", productNumber).replace( "$status$", status);
        return givenConfig().body(body).
                when().
                post(impexEndpoint);
    }

    public static Response postKlarnaMerchConfigUpdateImpex(String status)
    {
        return givenConfig().body(Impexes.UPDATE_KLARNA_MERCH_CONFIG.replace("$status$", status)).
                when().
                post(impexEndpoint);
    }

    public static Response postKlarnaMerchConfigUpdateImpexMobile(String status)
    {
        return givenConfig().body(Impexes.UPDATE_KLARNA_MERCH_CONFIG_MOBILE.replace("$status$", status)).
                when().
                post(impexEndpoint);
    }

    public static void postCreateProductPercentagePromotion(String productGroup)
    {
          givenConfig().log().all().body(Impexes.CREATE_PRODUCT_PERCENTAGE_PROMOTION.replace("$productGroup$", productGroup))
                .when()
                .post(impexEndpoint).prettyPeek().then().statusCode(200);
    }

    public static void postRemovePromotion(String promotionId)
    {
          givenConfig().log().all().body(Impexes.DISABLE_PROMOTION.replace("sample", promotionId))
                .when()
                .post(impexEndpoint).prettyPeek().then().statusCode(200);
    }

    public static void postCreateOrderLevelPromotion()
    {
        givenConfig().log().all().body(Impexes.CREATE_ORDER_PROMOTION)
                .when()
                .post(impexEndpoint).prettyPeek().then().statusCode(200);
    }

	public static void postSetDefaultHomeDelivery(String status)
      {
	       givenConfig().log().all().body(Impexes.REMOVE_DEFAULT_HOME_DELIVERY.replace("$status$", status))
	               .when()
	               .post(impexEndpoint).prettyPeek().then().statusCode(200);
	    }

	public static void postEnableATCCallOnAddToBag()
	{
		givenConfig().log().all().body(Impexes.ADD_TO_BAG_ATC_FEATURE)
				.when()
				.post(impexEndpoint).prettyPeek().then().statusCode(200);
	}

	public static void patchMAOStockAlert(String storeStock, String dcStock, String stockLevel, String sku, String time, long minutes)
	{
		String access_token = givenConfig().log().all()
				.when()
				.header("X-SSL-Client-Verified","{{header-X-SSL-Client-Verified}}")
				.queryParam("client_id","bmV3bG9va193ZWJob29rX2NsaWVudA==")
				.queryParam("client_secret","bmV3bG9va193ZWJob29rX3NlY3JldA==")
				.queryParam("grant_type","client_credentials")
				.post(oauthToken).prettyPeek().getBody().path("access_token");

		givenConfig().log().all().body(apiDetails.MAO_STOCK_ALERT_API.replace("$stockLevel", stockLevel)
				.replace("$dcStock",dcStock)
				.replace("$storeStock",storeStock)
				.replace("$timeStamp", getCurrentSystemDateTime(time, minutes))
				.replace("$sku", sku))
				.when().queryParam("access_token", access_token)
				.patch(stockAPIEndpoint).prettyPeek().then().statusCode(200);
	}

}
