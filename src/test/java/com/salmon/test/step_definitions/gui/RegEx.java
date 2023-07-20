package com.salmon.test.step_definitions.gui;

public enum RegEx {

    BRITISH("^\\£?(\\d{1,3},?(\\d{3},?)*\\d{3}(.\\d{0,3})?|\\d{1,3}(.\\d{2})?)$"),
    EURO("^\\€?(\\d{1,3},?(\\d{3},?)*\\d{3}(.\\d{0,3})?|\\d{1,3}(.\\d{2})?)$");

    private final String currency;

    RegEx(String currency) {
        this.currency = currency;
    }

    public String currency() {
        return currency;
    }

}

