package com.salmon.test.step_definitions.gui;



public class Converter {

    public static Double convertCurrencyValue(String value) {
        return Double.valueOf(value.replaceAll("[^\\d.]+", ""));
    }

}
