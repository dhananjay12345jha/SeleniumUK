package com.salmon.test.services;

import com.jayway.restassured.response.Response;
import com.salmon.test.framework.helpers.ApiHelper;

/**
 * Created by gates on 22/05/18.
 */
public class GetFeatureStatuses extends ApiHelper {
    private static final String featuresListEndpoint = "/newlooktools/features/list";

    private static Response getFeatureList() {
        return givenConfig().
                when().
                post(featuresListEndpoint);
    }

    public static void getFeatureStatus(String key) {
        final Response response = getFeatureList();
        final Feature[] features = response.getBody().as(Feature[].class);

        for (Feature feature : features) {
            if (feature.getCode().equalsIgnoreCase(key)) {
                System.setProperty(feature.getCode(), feature.isOn());
                return;
            }
        }
        throw new IllegalArgumentException("No feature found for code " + key + ".");
    }

    private static class Feature {
        private String code;
        private String on;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String isOn() {
            return on;
        }

        public void setOn(String on) {
            this.on = on;
        }
    }
}
