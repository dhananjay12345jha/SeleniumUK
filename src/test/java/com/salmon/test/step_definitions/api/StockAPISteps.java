package com.salmon.test.step_definitions.api;

import com.salmon.test.services.NewLookTest;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;


    public class StockAPISteps
    {
    	@Given("^I set (.*) as (.*) in hybris with (minus|plus) (.*) minutes$")
      public void i_set_as_in_hybris(String sku, String stockLevel, String time, String minutes) {
    	  String stock = stockLevel.equals("lowstock") ? "2" : "24";
        NewLookTest.patchMAOStockAlert(stock, stock, stockLevel.toUpperCase(),sku,time, Long.parseLong(minutes));
      }


	    @And("I set (.*) as (lowstock|instock|outofstock) with (minus|plus) (.*) minutes with Store (.*) and Dc (.*) quantity$")
	    public void i_set_as_in_hybris(String sku,String stockLevel,String time,String minutes,String Store,String DC )  {
		    NewLookTest.patchMAOStockAlert(Store, DC, stockLevel.toUpperCase(),sku,time, Long.parseLong(minutes));
	    }
    }
