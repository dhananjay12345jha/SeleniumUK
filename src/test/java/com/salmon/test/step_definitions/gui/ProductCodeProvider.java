package com.salmon.test.step_definitions.gui;


//@Slf4j
//@ScenarioScoped

public class ProductCodeProvider {


//    public String code = null;
//
//    public ProductCodeProvider(String site, Department department) {
//        code = randomProductCode(inStockProductCodes(site, department));
//    }
//
//    public ProductCodeProvider() {
//
//    }
//
//
//    private static String uniqueRandomProductCode(List<String> listOFProducts) {
//        String productNumber;
//        if (!listOFProducts.isEmpty()) {
//            int index = ThreadLocalRandom.current().nextInt(listOFProducts.size());
//            productNumber = String.valueOf(listOFProducts.get(index));
//            listOFProducts.remove(index);
//        } else {
//            return "ran out of numbers";
//        }
//        return productNumber;
//
//    }
//
//    public static String randomProductCode(List<String> listOFProducts) {
//        int index = ThreadLocalRandom.current().nextInt(listOFProducts.size());
//        return String.valueOf(listOFProducts.get(index));
//    }
//
//    private String endpoint(String site, Department department) {
//
//        String location="uk";
//
//        if (site.substring(site.length() - 3).contains("uk")) {
//            location = "uk";
//        } else if (site.substring(site.length() - 3).contains("row")) {
//            location = "row";
//        } else {
//            location = "fr";
//        }
//        return site + "/" + location + "-" + department.getText() + location + "-" + department.getText() + ".json";
//    }
//
//    private int numberOfPages(String site, Department department) {
//
//        return given()
//                .get(endpoint(site, department))
//                .then().extract().response().getBody().path("data.pagination.numberOfPages");
//
//    }
//
//
//
//    private ArrayList<String> inStockProductCodes(String site, Department department) {
//        ArrayList<String> codes = new ArrayList<>();
//        int page = 1;
//        for (int i = 0; i < page; i++) {
//            Response response = given().queryParam("page", i).get(endpoint(site, department)).then().extract()
//                    .response();
//
//            ArrayList<String> codesList = response.getBody().path("data.results.code");
//            ArrayList<String> stocksList = response.getBody().path("data.results.stock.stockLevelStatus.code");
//
//            for (int ii = 0; ii < codesList.size(); ii++) {
//
//                if (stocksList.get(ii).contains("outOfStock") || stocksList.get(ii).contains("isExternalStockCheck")) {
//                    codesList.remove(ii);
//                }
//            }
//
//            codes.addAll(codesList);
//            if (codes.size() >= 100 || page >= 30) {
//                break;
//            } else
//                page++;
//        }
//        return codes;
//    }
//
//    public enum Department {
//
//        MENS("mens"),
//        WOMENS("womens"),
//        TEENS("teens");
//
//        private final String department;
//
//        Department(String department) {
//            this.department = department;
//        }
//
//        public String getText() {
//            return this.department;
//        }
//    }
//
//    //################################
//    private String endpointProductDetails(String site, String productId) {
//
//        String location;
//
//        if (site.substring(site.length() - 3).contains("uk")) {
//            location = "uk";
//        } else if (site.substring(site.length() - 3).contains("row")) {
//            location = "row";
//        } else {
//            location = "fr";
//        }
//        return String.format(site + "/" + "json/product/productDetails.json?productCode=%s", productId);
//    }
//
//
//    public ProductPojo getProductDetails(String prodId) {
//        //   log.info(endpointProductDetails(testWrapper.siteURL, prodId));
////        Response response = given().get(endpointProductDetails(testWrapper.siteURL, prodId)).then().extract()
////                .response();
//      //  String responseAsString = response.getBody().asString();
//     //   return new Gson().fromJson(responseAsString, ProductPojo.class);
//        return null;
//
//
//    }
//
//
//




}