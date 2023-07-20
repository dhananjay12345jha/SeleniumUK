package com.salmon.test.step_definitions.gui;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by phani.kaliginadi on 28/09/17.
 */
//@Data
public class ProductPojo {
    boolean success;


    //    @Data
    public class ProductData {
        ArrayList<SizeOption> sizeOptions;
        Map<String, ColorOption> colourOptions;
        String stock;
        Price price;
        Price previousPrice;
        String parentCategory;
        String name;
        String code;
    }

    //  @Data
    public class SizeOption {
        String code;
        String value;
        String stockStatus;
        String sizeCode;
        String sku;
        int stockLevel;

    }

    //  @Data
    public class ColorOption {
        String displayName;
        boolean available;
        int value;

    }

    //  @Data
    public class Price {
        String currencyIso;
        String formattedValue;
        double value;

    }


}
