Feature: Checkout and payments mobile

	Background:
		Given i navigate to "Newlook" home page

	@UserDependentMobile
	Scenario:1 Returning customer wants to add new card at checkout without having to re-enter the billing address - mobile
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		Then remove all the added items
		And Select a product and navigate to payment page on mobile
		And Select to change billing address
		Then Customer should see address options in popup
			| Checkout - Payment                             |
			| Select or add the billing address for new card |
			| Use this address                               |
			| Add new address                                |

	@UserDependentMobile
	Scenario:2 Returning customer want to add new address address
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And navigate to bag page and secure checkout
		And Select to change billing address
		Then Popup should be displayed

	@UserDependentMobile
	Scenario:3 Verify the add new billing address form
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And navigate to bag page and secure checkout
		And add new address is selected
		Then Billing address form should display
			| Country                |
			| Title                  |
			| First Name             |
			| Last Name              |
			| Address Finder *       |
			| Enter address manually |
			| Use this address       |
			| Cancel                 |

	@UserDependentMobile
	Scenario:4 Verify that customer able to view delivery option in checkout page
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And navigate to bag page and secure checkout
		Then Delivery option should be displayed

	@UserDependentMobile
	Scenario:5 Verify that customer able to view collect option in checkout page
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And navigate to bag page and secure checkout
		Then Collection option should be displayed

	@UserDependentMobile
	Scenario Outline:6 When user searches for the collection stores, New Look stores should always displayed first
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And navigate to bag page and secure checkout
		And select collection button and enter <postcode> in search field
		Then New Look should be the first store in the result
		Examples:
			| postcode |
			| bb1 3bb  |

	@UserDependentMobile
	Scenario:7 User should see map when search for collection locations
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And navigate to bag page and secure checkout
		And select collection button and enter London in search field
		And I select the Collection Location name or 'Map and Opening times' link
		Then Map should be displayed

	@UserDependentMobile
	Scenario:8 User should see map contents when search for collection locations
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And navigate to bag page and secure checkout
		And select collection button and enter London in search field
		And I select the Collection Location name or 'Map and Opening times' link
		Then Map should be displayed with
			| Map          |
			| Satellite    |
			| Terms of Use |

   @UserDependentMobile
	Scenario:9 User should see pins in map when search for collection locations
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And navigate to bag page and secure checkout
		And select collection button and enter London in search field
		And I select the Collection Location name or 'Map and Opening times' link
		Then Map should display pins

	@UserDependentMobile
	Scenario Outline:11 Results should match when we click the pin in map
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And navigate to bag page and secure checkout
		And select collection button and enter <location> in search field
		And I select the Collection Location name or 'Map and Opening times' link
		And click checkout page pin in the map
		Then The result displayed should match with the map result
		Examples:
			| location |
			| London   |
			| SN25 2GE |

#  @NewUsersMobile
	@UserDependentMobile
	Scenario Outline:12 Add item to basket and checkout as logged in user for collection
		And user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location with "<postCode>" and select collection as "<selectedCollectionType>"
		Then user verifies if selected collection checkbox is selected in the checkout page
		And also verifies if delivery options can be toggled
		And user clicks on order summary on mobile
		And also checks if the changed delivery option will be updated as "<toggledCollectionType>" in the order summary
		Examples:
			| postCode | selectedCollectionType   | toggledCollectionType       |
			| SN25 2GE | Click & Collect Standard | UK Click & Collect Next Day |

	@AnonymousMobile
	Scenario Outline:13 Add item to basket and checkout as guest user for collection
		And Select a product and navigate to payment page on mobile
		And continue as guest
		And search for nearest collection location with "<postCode>" and select collection as "<selectedCollectionType>"
		Then user verifies if selected collection checkbox is selected in the checkout page
		And also verifies if delivery options can be toggled
		And user clicks on order summary on mobile
		And also checks if the changed delivery option will be updated as "<toggledCollectionType>" in the order summary
		Examples:
			| postCode | selectedCollectionType   | toggledCollectionType       |
			| SN25 2GE | Click & Collect Standard | UK Click & Collect Next Day |

#  NLOC-358
	@UserDependentMobile @condev_data
	Scenario: 14 Selected home delivery option date highlighted to the customer for Standard delivery
		When login with username "myAccountPersonalDetailsUserEmail" and password "password" on mobile
		And Select a product and navigate to payment page on mobile
		And select delivery option
		And user choose delivery option "Standard" mode
		Then user verifies if selected delivery mode has been highlighted with Standard delivery description

#  NLOC-358
	@UserDependentMobile
	Scenario Outline:15 Selected home delivery option date highlighted to the customer
		When login with username "myAccountPersonalDetailsUserEmail" and password "password" on mobile
		And Select a product and navigate to payment page on mobile
		And select delivery option
		And user choose delivery option "<deliveryOption>" mode
		Then user verifies if selected delivery mode has been highlighted with appropriate "<delivery-mode description>"
		Examples:
			| deliveryOption | delivery-mode description |
			| Evening        | 6pm - 10pm                |
			| Daytime        | 8am - 8pm                 |

	@UserDependentMobile
	Scenario Outline:16  Selected collection checkbox should be displayed in the checkout page
		And user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location with "London" and select collection as "<selectedCollectionType>"
		Then user verifies if selected collection checkbox is selected in the checkout page
		When user clicks on order summary on mobile
		Then also checks if the changed delivery option will be updated as "<selectedCollectionType>" in the order summary
		Examples:
			| selectedCollectionType   |
			| Click & Collect Standard |
			| Click & Collect Next Day |

	@UserDependentMobileSamsung
	Scenario Outline:17 For returning customers, last used delivery methods should be pre-selected
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location with "London" and select collection as "<selectedCollectionType>"
		And enter mandatory mobile number
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		When Select a product and navigate to payment page on mobile
		Then "COLLECTION" is automatically selected
		Examples:
			| payment type     | card type | card number       | selectedCollectionType   |
			| paymentType_Card | Visa      | visa_CardNumber_1 | Click & Collect Standard |

	@UserDependentMobileSamsung
	Scenario Outline:18 For returning customers, last used delivery methods should be pre-selected - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location with "London" and select collection as "<selectedCollectionType>"
		And enter mandatory mobile number
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		When Select a product and navigate to payment page on mobile
		Then "COLLECTION" is automatically selected
		Examples:
			| payment type     | card type | card number       | selectedCollectionType   |
			| paymentType_Card | Visa      | visa_CardNumber_1 | Click & Collect Standard |

	@UserDependentMobileSamsung
	Scenario Outline:19  Last selected collection option should be visible to returning customer
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location with "London" and select collection as "<selectedCollectionType>"
		And enter mandatory mobile number
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		When Select a product and navigate to payment page on mobile
		Then user verifies if selected collection checkbox is selected in the checkout page
		When user clicks on order summary on mobile
		And also checks if the changed delivery option will be updated as "<selectedCollectionType>" in the order summary
		Examples:
			| payment type     | card type | card number       | selectedCollectionType   |
			| paymentType_Card | Visa      | visa_CardNumber_1 | Click & Collect Next Day |

	@UserDependentMobileSamsung
	Scenario Outline:20  Last selected collection option should be visible to returning customer - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location with "London" and select collection as "<selectedCollectionType>"
		And enter mandatory mobile number
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		When Select a product and navigate to payment page on mobile
		Then user verifies if selected collection checkbox is selected in the checkout page
		When user clicks on order summary on mobile
		And also checks if the changed delivery option will be updated as "<selectedCollectionType>" in the order summary
		Examples:
			| payment type     | card type | card number       | selectedCollectionType   |
			| paymentType_Card | Visa      | visa_CardNumber_1 | Click & Collect Next Day |

	@UserDependentMobileSamsung
	Scenario Outline:21 Saved locations popup of collections method should be visible for returning customer
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location with "London" and select collection as "<selectedCollectionType>"
		And enter mandatory mobile number
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		When Select a product and navigate to payment page on mobile
		And user clicks on choose another collection point button
		Then user verifies if saved collections popup is displayed at checkout page
		Examples:
			| payment type     | card type | card number       | selectedCollectionType   |
			| paymentType_Card | Visa      | visa_CardNumber_1 | Click & Collect Next Day |

	@UserDependentMobileSamsung
	Scenario Outline:22 Saved locations popup of collections method should be visible for returning customer - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location with "London" and select collection as "<selectedCollectionType>"
		And enter mandatory mobile number
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		When Select a product and navigate to payment page on mobile
		And user clicks on choose another collection point button
		Then user verifies if saved collections popup is displayed at checkout page
		Examples:
			| payment type     | card type | card number       | selectedCollectionType   |
			| paymentType_Card | Visa      | visa_CardNumber_1 | Click & Collect Next Day |

		#NLCC-6998
	@UserDependentMobile @condev_data
	Scenario:23 Default delivery mode for homes - mobile
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And Select a product and navigate to payment page on mobile
		And select DELIVERY as option
		Then default delivery option should be selected

	@UserDependentMobile
	#NLCC-7572 is testing NLCC-6926 AC2
	Scenario: 24 Billing Address form for logged in user for collection delivery - mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And Select a product and navigate to payment page on mobile
		And select delivery method as "Collection" and location as "London"
		And select payment method as "card"
		Then saved billing address should be displayed inside selected payment as "card"
		And select payment method as "klarna"
		Then saved billing address should be displayed inside selected payment as "klarna"

	@AnonymousMobile
	#NLCC-7572 is testing NLCC-6926 AC1
	Scenario: 25 Billing Address form for guest user for collection delivery - mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		And Select a product and navigate to payment page on mobile
		And continue as guest
		And search for nearest collection location with "london" and select collection location as "Click & Collect Standard"
		And enter collection details
		And select payment method as "card"
		Then billing address form should be displayed inside selected payment as "card"
		And select payment method as "klarna"
		Then billing address form should be displayed inside selected payment as "klarna"

	@UserDependentMobile
	#NLCC-6829 is testing NLCC-6382
	Scenario: 26 Show Ghost text visible for New Look Store Card - mobile (Registered User)
		And feature "feature.storefront.checkout.new.tender" is switched on
		When login with username "myAccountPersonalDetailsUserEmail" and password "password" on mobile
		And Select a product and navigate to payment page on mobile
		And select delivery option
		And select available delivery mode
		And click continue to payment button
		And select payment method as "NewLookStoreCard"
		Then Ghost Text should be "displayed"
		Then Place order button is "greyed out"

	@UserDependentMobile
	#NLCC-6829 is testing NLCC-6382
	Scenario: 27 Hide Ghost text for New Look Store Card - mobile (Registered User)
		And feature "feature.storefront.checkout.new.tender" is switched off
		When login with username "myAccountPersonalDetailsUserEmail" and password "password" on mobile
		And Select a product and navigate to payment page on mobile
		And select delivery option
		And select available delivery mode
		And click continue to payment button
		And select payment method as "NewLookStoreCard"
		Then Ghost Text should be "not displayed"
		Then Place order button is "greyed out"

	@UserDependentMobile
  #NLCC-6830 is testing NLCC-6384
	Scenario:28 Paypal return information displayed for registered user as per new design -Mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And Select a product and navigate to payment page on mobile
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
		And change delivery country to Royaume-Uni in my bag page
		And I click on the locale selector in the header
		And change country as "country_Germany" and add delivery address with postal code as "10115"
		And select available delivery mode
		And click continue to payment button
		And select payment method as "paypal"
		Then Paypal return information "displayed"
		Then PayPal CTA button is "Active"
		And click on edit bag link
		And I click on the locale selector in the header
		And change delivery country to Vereinigtes Königreich in my bag page
		And I click on the locale selector in the header
		And change country as "country_Australia" and add delivery address with postal code as "2000"
		And select payment method as "paypal"
		Then Paypal return information "displayed"
		Then PayPal CTA button is "Active"

	@UserDependentMobile
   #NLCC-6830 is testing NLCC-6384
	Scenario:29 Paypal return information not displayed for registered user as per new design -Mobile
		And feature "feature.storefront.checkout.new.tender" is switched off
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And Select a product and navigate to payment page on mobile
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

	@UserDependentMobile
	#NLCC-7760 is testing NLCC-7579
	Scenario:30 Expiry error message displayed on entering Invalid Store Card expiry date - mobile (Registered User)
		And feature "feature.storefront.checkout.new.tender" is switched on
		When login with username "myAccountPersonalDetailsUserEmail" and password "password" on mobile
		And Select a product and navigate to payment page on mobile
		And select delivery option
		And select available delivery mode
		And click continue to payment button
		And select payment method as "NewLookStoreCard"
		Then Ghost Text should be "displayed"
		Then Place order button is "greyed out"
		And Error message "displayed" for expiry month as "05" and year as "21"
		And Error message "not displayed" for expiry month as "05" and year as "27"

	@NewUsersMobile
   #NLCC-7154 is testing NLCC-6787 AC1
	Scenario:31 New customer has no "USE THIS CARD" button when user has no saved cards and delivery option - mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		And user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And chooses standard delivery
		And select available delivery mode
		And click continue to payment button
		And select payment method as "NewLookStoreCard"
		And fill store card billing address
		Then use this card option is "not displayed"

	@NewUsersMobile
	#NLCC-7154 is testing NLCC-6787 AC2
	Scenario:32 New customer has no "USE THIS CARD" button when user has no saved cards and collection option - mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		And user provides required details to create new account on mobile
		And Select a product and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And select payment method as "NewLookStoreCard"
		And fill store card billing address
		Then use this card option is "not displayed"

	@AnonymousMobile
   #NLCC-7154 is testing NLCC-6787 AC1
	Scenario:33 Guest customer has no "USE THIS CARD" button when user has no saved cards and delivery option - mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		And Select a product and navigate to payment page on mobile
		And continue as guest
		And user opted DELIVERY with post code CH7 4TW
		And select available delivery mode
		And click continue to payment button
		And select payment method as "NewLookStoreCard"
		And fill store card billing address
		Then use this card option is "not displayed"

	@AnonymousMobile
	#NLCC-7154 is testing NLCC-6787 AC2
	Scenario:34 Guest customer has no "USE THIS CARD" button when user has no saved cards and collection option - mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		And Select a product and navigate to payment page on mobile
		And continue as guest
		And search for nearest collection location with "london" and select collection location as "Click & Collect Standard"
		And enter collection details
		And click continue to payment button
		And select payment method as "NewLookStoreCard"
		And fill store card billing address
		Then use this card option is "not displayed"

	#NLCC-7968 is testing NLCC-7899
	@UserDependentMobile
	Scenario:35 Testing bug# NLCC-7899 Storecard has no billing address and 2 "Change card" CTA's -- Registered User - Mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		When login with username "myAccountPersonalDetailsUserEmail" and password "password" on mobile
		And Select a product and navigate to payment page on mobile
		And select delivery option
		And select available delivery mode
		And add store card details with no "9826220039999995" and expiry date 0823
		And click age verification check box
		And user places order
		Then invalid card message "displayed" for "number"
		And previous billing details and change card button displayed

  #NLCC-7968 is testing NLCC-7899
	@AnonymousMobile
	Scenario:36 Testing bug# NLCC-7899 Storecard has no billing address and 2 "Change card" CTA's -- Guest User - Mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		And Select a product and navigate to payment page on mobile
		And continue as guest
		And chooses standard delivery
		And select available delivery mode
		And click continue to payment button
		And add store card details with no "9826220039999995" and expiry date 0823
		And click age verification check box
		And user places order
		Then invalid card message "displayed" for "number"
		And previous billing details and change card button displayed

   #NLCC-8009 is testing NLCC-7754
	@AnonymousMobile
	Scenario:37 Testing bug#NLCC-7754 Storecard expiry date error -- Guest User on Mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		And Select a product and navigate to payment page on mobile
		And continue as guest
		And chooses standard delivery
		And select available delivery mode
		And click continue to payment button
		And add store card details with no "9826240012340050" and expiry date 823
		And user confirms over the age of 18 of store card
		And user places order
		And order has been successful

	#NLCC-8009 is testing NLCC-7754
	@UserDependentMobile
	Scenario:38 Testing bug#NLCC-7754 Storecard expiry date error -- Registered User on Mobile
		And feature "feature.storefront.checkout.new.tender" is switched off
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And Select a product and navigate to payment page on mobile
		And select delivery option
		And enter delivery mandatory mobile number
		And select available delivery mode
		And I continue to payment
		And add store card details with no "9826240012340050" and expiry date 823
		And user confirms over the age of 18 of store card
		And user places order
		And order has been successful

	@UserDependentMobile @oms_flow @OMSF-1204
	Scenario:39  AC 1 - Successful Soft Promise Call - Store Only - One Size -- Registered User
		And login with username "deliveryMethodEmail" and password "passwordDeliveryMethod"
		And remove all the added items
		When Select productCode_Store_Only_OneSize_OMS single size product and navigate to payment page
		And provide guest user details
		Then collection method is disabled on checkout page
		And home delivery method is selected on checkout page

	@UserDependentMobile @oms_flow @OMSF-1204
	Scenario:40  AC 2: Successful Soft Promise Call - DC Only - MultiSize- -- create Account
		And customer search for product productCode_DC_Only_MultiSize_OMS with size XS - Only 3 items left!
		And navigate to bag page and secure checkout
		And provide guest user details
		Then collection method is enabled on checkout page
		And home delivery method is enabled on checkout page

	@UserDependentMobile @oms_data
	Scenario: 41 Selected home delivery option date highlighted to the customer for Standard delivery OMS
		And login with username "deliveryMethodEmail" and password "passwordDeliveryMethod"
		And remove all the added items
		And customer search for product productCode_Store_Only_MultiSize_OMS with size UK 12 - Only 3 items left!
		And navigate to bag page and secure checkout
		And fetch all delivery options displayed and verified it is "deliveryOptions"


	@newlook @UserDependentMobile @oms_flow @OMSF-2280
	Scenario:42  AC 1 - Successful Soft Promise Call - Store Only - Check message for Store only product -- Guest User
		When Select productCode_Store_Only_OneSize_OMS single size product and navigate to payment page
		And continue as guest
		Then collection method is disabled on checkout page
		And the label Not Available is specified on the COLLECTION delivery option
		And the messaging is displayed above the delivery options per the design

	@newlook @UserDependentMobile @oms_flow @OMSF-2280
	Scenario:43  AC 1 - Successful Soft Promise Call - Store Only - Check message for Store only product MutliSize product-- Signed in user
		And login with username "deliveryMethodEmail" and password "passwordDeliveryMethod"
		And remove all the added items
		And customer search for product productCode_Store_Only_MultiSize_OMS with size UK 12 - Only 3 items left!
		And navigate to bag page and secure checkout
		Then collection method is disabled on checkout page
		And the label Not Available is specified on the COLLECTION delivery option
		And the messaging is displayed above the delivery options per the design


	@newlook @UserDependentMobile @oms_flow @OMSF-2280
	Scenario:44  AC 1 - Successful Soft Promise Call - Store+DC Only - Check message product store and Dc both product-- Signed in user
		And login with username "deliveryMethodEmail" and password "passwordDeliveryMethod"
		And remove all the added items
		And customer search for product productCode_Store_Only_MultiSize_OMS with size UK 12 - Only 3 items left!
		And customer search for product productCode_DC_Only_MultiSize_OMS with size XS - Only 3 items left!
		And navigate to bag page and secure checkout
		Then collection method is disabled on checkout page
		And the label Not Available is specified on the COLLECTION delivery option
		And the messaging is displayed above the delivery options per the design

