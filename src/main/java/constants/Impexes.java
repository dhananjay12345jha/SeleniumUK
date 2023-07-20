package constants;

public class Impexes {
    public static final String UPDATE_BAZAARVOICE =
            "$productCatalog=newLookGlobalProductCatalog;\n" +
                    "$stagedProduct=catalogVersion(catalog(id[default=$productCatalog]),version[default='Staged'])[unique=true,default='$productCatalog:Staged'];\n" +
                    "$onlineProduct=catalogVersion(catalog(id[default=$productCatalog]),version[default='Online'])[unique=true,default='$productCatalog:Online'];\n" +
                    "INSERT_UPDATE NewLookColourVariantProduct;code[unique=true];$stagedProduct;enableBazaarVoiceProductReviews\n" +
                    ";" + "$productNumber$" + ";" + "newlookGlobalProductCatalog:Staged" +
                    ";" + "$status$" + ";" +
                    "INSERT_UPDATE NewLookColourVariantProduct;code[unique=true];$onlineProduct;enableBazaarVoiceProductReviews\n" +
                    ";" + "$productNumber$" + ";" + "newlookGlobalProductCatalog:Online" +
                    ";" + "$status$";

    public static final String UPDATE_KLARNA_MERCH_CONFIG = "UPDATE PaymentProviderMerchantConfiguration;code[unique=true];enabled;\n" +
            ";UK GBP E-Commerce Klarna Payments;$status$;";

    public static final String UPDATE_KLARNA_MERCH_CONFIG_MOBILE = "UPDATE PaymentProviderMerchantConfiguration;code[unique=true];enabled;\n" +
            ";UK GBP Mobile Klarna Payments;$status$;";

    public static final String CREATE_PRODUCT_PERCENTAGE_PROMOTION =
            "$productCatalog=newlookGlobalProductCatalog\n" +
                    "$catalogVersion=catalogVersion(CatalogVersion.catalog(Catalog.id[default=$productCatalog]),CatalogVersion.version[default=Online])[default=$productCatalog:Online]\n" +
                    "$promo-gr=promotiongroup-uk\n" +
                    "$lang=en\n" +
                    "\n" +
                    "# Percentage promotions\n" +
                    "INSERT_UPDATE ProductPercentageDiscountPromotion;code[unique=true];$catalogVersion[unique=true];title;name[lang=$lang];description;percentageDiscount;priority;categories(code,$catalogVersion);PromotionGroups(identifier);enabled[default=true]\n" +
                    ";5PercentOff_UI_AUTO_PRODUCT;;5% Fixed Percentage Discount;5% Off;5% Fixed Percentage Discount;5;5000;uk-mens-shoes-and-boots,uk-women-shoes,uk-teens-shoes;$promo-gr\n";

    public static final String DISABLE_PROMOTION =
            "$catalog-id=newlookGlobalProductCatalog\n" +
                    "$catalog-version=Online\n" +
                    "$catalogVersion=catalogVersion(catalog(id),version)[unique=true,default=$catalog-id:$catalog-version]\n" +
                    "$promotionId = sample\n" +
                    "\n" +
                    "UPDATE AbstractPromotion[batchmode=true];code[unique=true];$catalogVersion[unique=true];enabled[default=false]\n" +
                    ";$promotionId\n";

    public static final String DISABLE_ALL_PROMOTIONS =
            "$catalog-id=newlookGlobalProductCatalog\n" +
                    "$catalog-version=Online\n" +
                    "$catalogVersion=catalogVersion(catalog(id),version)[unique=true,default=$catalog-id:$catalog-version]\n" +
                    "\n" +
                    "UPDATE AbstractPromotion[batchmode=true];$catalogVersion[unique=true];enabled\n" +
                    ";;false\n";

     public static final String CREATE_ORDER_PROMOTION =
            "$catalog-id=newlookGlobalProductCatalog\n" +
                    "$catalog-version=Online\n" +
                    "$catalogVersion=catalogVersion(catalog(id),version)[unique=true,default=$catalog-id:$catalog-version]\n" +
                    "INSERT PromotionPriceRow;&id;currency(isocode);price\n"+
                    ";price5;GBP;5.00;\n"+
                    ";price10;GBP;10.00;\n"+
                    "\n" +
                    "INSERT_UPDATE OrderThresholdDiscountPromotion;code[unique=true];$catalogVersion[unique=true];title;name[lang=en];description;priority;enabled[default=true];discountPrices(&id);thresholdTotals(&id);messageFired[lang=en];promotionGroups(identifier)\n"+
                    ";5FixedPriceOff_UI_AUTO_ORDER;;5 Fixed Price Discount;5 Off;5 Fixed Price Discount;5000;;price5;price10;5FixedPriceOff_UI_AUTO_ORDER PROMOTION Fired;promotiongroup-default\n";

	  public static final String REMOVE_DEFAULT_HOME_DELIVERY = "UPDATE ZoneDeliveryMode;code[unique=true];active\n" +
		";uk-Standard - Metapack;$status$" ;

	public static final String ADD_TO_BAG_ATC_FEATURE = "INSERT_UPDATE NeoworksConfigurationItem; key[unique = true]; value;feature.newlookintegrationipaas.atc.addToCart.enabled;true;" ;

}
