package com.salmon.test.step_definitions.gui;


import com.salmon.test.framework.helpers.Props;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Data
public class DataUnderTest {

    ProductPojo product;
    ProductCodeProvider productCodeProvider;
    List<ShoppingItem> savedItems = new ArrayList<>();
    List<String> allProductCodes = new ArrayList<>();
    List<String> productCodesWomen = new ArrayList<>();
    List<String> productCodesMen = new ArrayList<>();
    List<String> productCodesTeen = new ArrayList<>();
    String guestEmail;
    String baseCountry;
    String password;


    String sizeSelected;
    ProductPojo.SizeOption sizeOptionSelected;


    // @Inject
    //public DataUnderTest(ProductCodeProvider productCodeProvider, ConfigurationLookup config) {
    public DataUnderTest(ProductCodeProvider productCodeProvider) {
        //this.baseCountry = config.getRunConfig().getDefaultCountry();
        this.baseCountry = Props.getProp("locale");
        //allProductCodes.add("329435117");
        String quiet = System.getProperty("quiet");
        if (!StringUtils.isEmpty(quiet)) {
            try {
//                log.info("Quiet period started:{} minutes", quiet);
                Thread.sleep(Integer.valueOf(quiet) * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.productCodeProvider = productCodeProvider;
        extractProductCodes(baseCountry);
    }

	public DataUnderTest()
	{

	}

	public static String getARandomProductCode(List<String> listOfCodes) {
        int i = ThreadLocalRandom.current().nextInt(listOfCodes.size());
        return listOfCodes.get(i);

    }

    public String getARandomProductCodeWithAvailableStock() {
        return getARandomProductCode(allProductCodes);

    }

    public String getARandomProductCodeWithMultipleSizes() {
//        return getARandomProductCode(productCodeProvider.getAllProductsWithMultiSizes(allProductCodes));
        return null;
    }

    public String getARandomWomenProductCodeWithAvailableStock() {
        return getARandomProductCode(productCodesWomen);

    }

    public String getARandomMenProductCodeWithAvailableStock() {
        return getARandomProductCode(productCodesMen);

    }

    public void extractProductCodes(String country) {
//        log.info("********* Extract products codes of Womens, mens, Teens from this env **********");
        List<String> availableCodes;
//        availableCodes = productCodeProvider.getProductCodes(country, ProductCodeProvider.Department.WOMENS, 30);
//        productCodesWomen = productCodeProvider.getAllProductsWithAvailableStock(availableCodes);
//        availableCodes = productCodeProvider.getProductCodes(country, ProductCodeProvider.Department.MENS, 30);
//        productCodesMen = productCodeProvider.getAllProductsWithAvailableStock(availableCodes);
//        availableCodes = productCodeProvider.getProductCodes(country, ProductCodeProvider.Department.TEENS, 30);
//        productCodesTeen = productCodeProvider.getAllProductsWithAvailableStock(availableCodes);

        allProductCodes.addAll(productCodesWomen);
        allProductCodes.addAll(productCodesMen);
        allProductCodes.addAll(productCodesTeen);
//        log.info("********* Extract products codes completed *************************************");

    }


}
