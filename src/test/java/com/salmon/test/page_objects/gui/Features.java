package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.helpers.Props;

/**
 * Created by gates on 16/05/18.
 */
public class Features {

    public static final boolean CONTACT_PREFERENCES_FEATURE = NewLookHelper.getFeatureStatus("feature.display.contact.preferences.enabled");
    public static final boolean CONSENT_PRINCIPAL_FEATURE = Boolean.valueOf(Props.getFeatureStatus("feature.create.consent.principal.enabled"));
    public static final boolean DELIVERY_UPDATE_ENABLED_FEATURE = Boolean.valueOf(Props.getFeatureStatus("feature.deliveryUpdate.mandatory.enabled"));
    public static final boolean STORE_CARDS_ENABLED_FEATURE = Boolean.valueOf(Props.getFeatureStatus("feature.payment.storecards.management.enabled"));
	 public static final boolean CHECKOUT_NEW_DESIGN_FEATURE = Boolean.valueOf(Props.getFeatureStatus("feature.storefront.checkout.new.tender"));
	 public static final boolean CHECKOUT_USE_CARD_CTA_FEATURE = Boolean.valueOf(Props.getFeatureStatus("feature.storefront.checkout.use.card.cta.enabled"));
	 public static final boolean CHECKOUT_DEFAULT_DELIVERY_SELECTED_FEATURE = Boolean.valueOf(Props.getFeatureStatus("feature.storefront.checkout.defaultDeliveryMode.enabled"));
    public static final boolean PEAK_CAPPING_FEATURE = Boolean.valueOf(Props.getFeatureStatus("feature.newlookcore.peakcapping.enabled"));
    public static final boolean QUICK_VIEW_DESKTOP_HIDDEN = NewLookHelper.getFeatureStatus("feature.storefront.experience.desktop.quickview");
	public static final boolean COOKIE_BOT_NEW = NewLookHelper.getFeatureStatus("feature.storefront.cookieBot.enabled");
	public static final boolean PROMO_CODE_NEW_STICKY_CART = NewLookHelper.getFeatureStatus("feature.storefront.desktop.sticky.cart.cta");

}
