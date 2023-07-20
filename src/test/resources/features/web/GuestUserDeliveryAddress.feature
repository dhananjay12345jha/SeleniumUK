@newlook @Anonymous
Feature: Guest account addresses form on checkout page

	Background:
		Given i navigate to "Newlook" home page

  #NLCC-5921 AC9, #NLCC-7622 is testing NLCC-7618
	Scenario:1 Edit Delivery form for guest user for home delivery
		And feature "feature.deliveryUpdate.forms.enabled" is switched on
		And Select a product and navigate to payment page
		And continue as guest
		And select delivery option
		Then delivery edit form should be displayed with mandatory mobile number for HOME DELIVERY
		And Collection details error messages are "not displayed"

	#NLCC-5921 AC10, #NLCC-7622 is testing NLCC-7618
	Scenario:2 Edit Delivery form for guest user for collectiondelivery
		And feature "feature.deliveryUpdate.forms.enabled" is switched on
		And Select a product and navigate to payment page
		And continue as guest
		And search for nearest collection location with "london" and select collection location as "Click & Collect Standard"
		Then collection delivery edit form should be displayed with mandatory mobile number
		And Collection details error messages are "not displayed"
		And continue to payment button
		And Collection details error messages are "displayed"

   #NLCC-7572 is testing NLCC-6926 AC1
	Scenario:3 Billing Address form for guest user for collection delivery
		And feature "feature.storefront.checkout.new.tender" is switched on
		And Select a product and navigate to payment page
		And continue as guest
		And search for nearest collection location with "london" and select collection location as "Click & Collect Standard"
		And enter collection details
		And select payment method as "card"
		Then billing address form should be displayed inside selected payment as "card"
		And select payment method as "klarna"
		Then billing address form should be displayed inside selected payment as "klarna"

	@newlook
   #NLCC-6829 is testing NLCC-6382
	Scenario: 4 Show Ghost text visible for New Look Store Card (Guest User)
		And feature "feature.storefront.checkout.new.tender" is switched on
		And Select a product and navigate to payment page
		And continue as guest
		And chooses standard delivery
		And select available delivery mode
		And click continue to payment button
		And select payment method as "NewLookStoreCard"
		Then Ghost Text should be "displayed"
		Then Place order button is "greyed out"
		And save store card details with no 9826220039999997
		And user confirms over the age of 18 of store card
		Then Place order button is "active"

	@newlook
   #NLCC-6829 is testing NLCC-6382
	Scenario: 5 Hide Ghost text for New Look Store Card (Guest User)
		And feature "feature.storefront.checkout.new.tender" is switched off
		And Select a product and navigate to payment page
		And continue as guest
		And chooses standard delivery
		And select available delivery mode
		And click continue to payment button
		And select payment method as "NewLookStoreCard"
		Then Ghost Text should be "not displayed"
		Then Place order button is "greyed out"

   #NLCC-6830 is testing NLCC-6384
	Scenario:6 Paypal return information displayed for guest user as per new design
		And feature "feature.storefront.checkout.new.tender" is switched on
		And Select a product and navigate to payment page
		And continue as guest
		And search for nearest collection location with "london" and select collection location as "Click & Collect Standard"
		And enter collection details
		And select payment method as "paypal"
		Then Paypal return information "displayed"
		Then PayPal CTA button is "Active"
		And click on edit bag link
		And I click on the locale selector in the header
		And change country as "country_France" and checkout with location as "Paris"
		And select payment method as "paypal"
		Then Paypal return information "displayed"
		Then PayPal CTA button is "Active"
		And click on edit bag link
		And I click on the locale selector in the header
		And change country as "countryFrance_countryUK"
		And I click on the locale selector in the header
		And change country as "country_Germany" and add delivery address with postal code as "10115"
		And select available delivery mode
		And click continue to payment button
		And select payment method as "paypal"
		Then Paypal return information "displayed"
		Then PayPal CTA button is "Active"
		And click on edit bag link
		And I click on the locale selector in the header
		And change country as "countryGermany_countryUK"
		And I click on the locale selector in the header
		And change country as "country_Australia" and add delivery address with postal code as "2000"
		And select payment method as "paypal"
		Then Paypal return information "displayed"
		Then PayPal CTA button is "Active"

   #NLCC-6830 is testing NLCC-6384
	Scenario:7 Paypal return information not displayed for guest user as per new design
		And feature "feature.storefront.checkout.new.tender" is switched off
		And Select a product and navigate to payment page
		And continue as guest
		And search for nearest collection location with "london" and select collection location as "Click & Collect Standard"
		And enter collection details
		And select payment method as "paypal"
		Then Paypal return information "not displayed"
		Then PayPal CTA button is "Active"
		And click on edit bag link
		And I click on the locale selector in the header
		And change country as "country_France" and checkout with location as "Paris"
		And select payment method as "paypal"
		Then Paypal return information "not displayed"
		Then PayPal CTA button is "Active"
		And click on edit bag link
		And I click on the locale selector in the header
		And change delivery country to Royaume-Uni in my bag page
		And I click on the locale selector in the header
		And change country as "country_Germany" and add delivery address with postal code as "10115"
		And select available delivery mode
		And click continue to payment button
		And select payment method as "paypal"
		Then Paypal return information "not displayed"
		Then PayPal CTA button is "Active"
		And click on edit bag link
		And I click on the locale selector in the header
		And change delivery country to Vereinigtes Königreich in my bag page
		And I click on the locale selector in the header
		And change country as "country_Australia" and add delivery address with postal code as "2000"
		And select payment method as "paypal"
		Then Paypal return information "not displayed"
		Then PayPal CTA button is "Active"

	#NLCC-7760 is testing NLCC-7579
	Scenario:8 Expiry error message displayed on entering Invalid Store Card expiry date (Guest User)
		And feature "feature.storefront.checkout.new.tender" is switched on
		And Select a product and navigate to payment page
		And continue as guest
		And chooses standard delivery
		And select available delivery mode
		And click continue to payment button
		And select payment method as "NewLookStoreCard"
		Then Ghost Text should be "displayed"
		Then Place order button is "greyed out"
		And Error message "displayed" for expiry month as "05" and year as "21"
		And Error message "not displayed" for expiry month as "05" and year as "27"
