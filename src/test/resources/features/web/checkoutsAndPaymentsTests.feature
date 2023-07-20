@UserDependent
Feature: checkout page

  #The scenarios will cover the using an existing cards and adding new cards to the account
	Background:
		Given i navigate to "Newlook" home page
		When login with username "checkoutAndPaymentsUserEmail" and password "password"

	Scenario:1 Returning customer wants to add new card at checkout without having to re-enter the billing address
	    Then remove all the added items
		When Select a product and navigate to payment page
		And Select to change billing address
		Then Customer should see address options in popup
			| Checkout - Payment                             |
			| Select or add the billing address for new card |
			| Use this address                               |
			| Add new address                                |

	Scenario:2 Returning customer want to add new address address
		And navigate to bag page and secure checkout
		And Select to change billing address
		Then Popup should be displayed

	Scenario:3 Verify the add new billing address form
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

	Scenario:4 Verify that customer able to view delivery option in checkout page
		And navigate to bag page and secure checkout
		Then Delivery option should be displayed

	Scenario:5 Verify that customer able to view collect option in checkout page
		And navigate to bag page and secure checkout
		Then Collection option should be displayed

	Scenario Outline:6 When user searches for the collection stores, New Look stores should always displayed first
		And navigate to bag page and secure checkout
		And select collection button and enter <postcode> in search field
		Then New Look should be the first store in the result
		Examples:
			| postcode |
			| bb1 3bb  |
			| bn21 3ej |

	Scenario:7 User should see map when search for collection locations
		And navigate to bag page and secure checkout
		And select collection button and enter London in search field
		Then Map should be displayed

	Scenario:8 User should see map contents when search for collection locations
		And navigate to bag page and secure checkout
		And select collection button and enter London in search field
		Then Map should be displayed with
			| Map          |
			| Satellite    |
			| Terms of Use |

	Scenario:9 User should see pins in map when search for collection locations
		And navigate to bag page and secure checkout
		And select collection button and enter London in search field
		Then Map should display pins

	Scenario Outline:10 User should see pins in map when search for collection locations
		And navigate to bag page and secure checkout
		And select collection button and enter <location> in search field
		Then The number of pins displayed should equal to stores displayed
		Examples:
			| location |
			| London   |
			| Thurso   |

	Scenario Outline:11 Results should match when we click the pin in map
		And customer navigate to store finder page
		And navigate to bag page and secure checkout
		And select collection button and enter <location> in search field
		And click checkout page pin in the map
		Then The result displayed should match with the map result
		Examples:
			| location |
			| London   |
#      | SN25 2GE |

	@NLRD-2296
	Scenario Outline:12 Add item to basket and checkout as logged in user for collection
		And customer is logged out
		And user provides required details to create new account
		And Select a product and navigate to payment page
		And search for nearest collection location with "<postCode>" and select collection as "<selectedCollectionType>"
		Then user verifies if selected collection checkbox is selected in the checkout page
		And also verifies if delivery options can be toggled
		And also checks if the changed delivery option will be updated as "<toggledCollectionType>" in the order summary
		Examples:
			| postCode | selectedCollectionType   | toggledCollectionType       |
			| SN25 2GE | Click & Collect Standard | UK Click & Collect Next Day |

	@NLRD-2296
	Scenario Outline:13 Add item to basket and checkout as guest user for collection
		And customer is logged out
		And Select a product and navigate to payment page
		And continue as guest
		And search for nearest collection location with "<postCode>" and select collection as "<selectedCollectionType>"
		Then user verifies if selected collection checkbox is selected in the checkout page
		And also verifies if delivery options can be toggled
		And also checks if the changed delivery option will be updated as "<toggledCollectionType>" in the order summary
		Examples:
			| postCode | selectedCollectionType   | toggledCollectionType       |
			| SN25 2GE | Click & Collect Standard | UK Click & Collect Next Day |

    @condev_data
	Scenario: 14 Selected home delivery option date highlighted to the customer for Standard delivery
		And Select a product and navigate to payment page
		And select delivery option
		And user choose delivery option "Standard" mode
		Then user verifies if selected delivery mode has been highlighted with Standard delivery description

	Scenario Outline:15 Selected home delivery option date highlighted to the customer
		And customer is logged out
		And user provides required details to create new account
		And Select a product and navigate to payment page
		And chooses standard delivery
		And user choose delivery option "<deliveryOption>" mode
		Then user verifies if selected delivery mode has been highlighted with appropriate "<delivery-mode description>"
		Examples:
			| deliveryOption | delivery-mode description |
			| Evening        | 6pm - 10pm                |
			| Daytime        | 8am - 8pm                 |

	Scenario Outline:16  Selected collection checkbox should be displayed in the checkout page
		And customer is logged out
		And user provides required details to create new account
		And Select a product and navigate to payment page
		And search for nearest collection location with "London" and select collection as "<selectedCollectionType>"
		Then user verifies if selected collection checkbox is selected in the checkout page
		And also checks if the changed delivery option will be updated as "<selectedCollectionType>" in the order summary
		Examples:
			| selectedCollectionType   |
			| Click & Collect Standard |
			| Click & Collect Next Day |

	Scenario Outline:17 For returning customers, last used delivery methods should be pre-selected
		And customer is logged out
		When user provides required details to create new account
		And Select a product and navigate to payment page
		And search for nearest collection location with "London" and select collection as "<selectedCollectionType>"
		And enter mandatory mobile number
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		When I go to checkout with the same account
		Then "COLLECTION" is automatically selected
		Examples:
			| payment type     | card type | card number       | selectedCollectionType   |
			| paymentType_Card | Visa      | visa_CardNumber_1 | Click & Collect Standard |

	Scenario Outline:18  Last selected collection option should be visible to returning customer
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And customer is logged out
		And user provides required details to create new account
		And Select a product and navigate to payment page
		And search for nearest collection location with "London" and select collection as "<selectedCollectionType>"
		And enter mandatory mobile number
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		When I go to checkout with the same account
		Then user verifies if selected collection checkbox is selected in the checkout page
		And also checks if the changed delivery option will be updated as "<selectedCollectionType>" in the order summary
		Examples:
			| payment type     | card type | card number       | selectedCollectionType   |
			| paymentType_Card | Visa      | visa_CardNumber_1 | Click & Collect Next Day |

	Scenario Outline:19 Last selected collection option should be visible to returning customer - PCI micro forms on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And customer is logged out
		And user provides required details to create new account
		And Select a product and navigate to payment page
		And search for nearest collection location with "London" and select collection as "<selectedCollectionType>"
		And enter mandatory mobile number
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		When I go to checkout with the same account
		Then user verifies if selected collection checkbox is selected in the checkout page
		And also checks if the changed delivery option will be updated as "<selectedCollectionType>" in the order summary
		Examples:
			| payment type     | card type | card number       | selectedCollectionType   |
			| paymentType_Card | Visa      | visa_CardNumber_1 | Click & Collect Next Day |

	Scenario Outline:20 Saved locations popup of collections method should be visible for returning customer
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And customer is logged out
		And user provides required details to create new account
		And Select a product and navigate to payment page
		And search for nearest collection location with "London" and select collection as "<selectedCollectionType>"
		And enter mandatory mobile number
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		And I go to checkout with the same account
		And user clicks on choose another collection point button
		Then user verifies if saved collections popup is displayed at checkout page
		Examples:
			| payment type     | card type | card number       | selectedCollectionType   |
			| paymentType_Card | Visa      | visa_CardNumber_1 | Click & Collect Next Day |

	Scenario Outline:21 Saved locations popup of collections method should be visible for returning customer -PCI micro forms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And customer is logged out
		And user provides required details to create new account
		And Select a product and navigate to payment page
		And search for nearest collection location with "London" and select collection as "<selectedCollectionType>"
		And enter mandatory mobile number
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		And I go to checkout with the same account
		And user clicks on choose another collection point button
		Then user verifies if saved collections popup is displayed at checkout page
		Examples:
			| payment type     | card type | card number       | selectedCollectionType   |
			| paymentType_Card | Visa      | visa_CardNumber_1 | Click & Collect Next Day |

	#NLCC-5721
	@newlook @condev_data
	Scenario:24 Registered user When Changes Delivery Mode is asked to enter CVV again
		And feature "feature.storefront.checkout.cvv.check" is switched on
		And customer is logged out
		When user provides required details to create new account
		And new customer checkout a product for collection
		And pays by paymentType_Card Visa visa_CardNumber_1
		Then order has been successful
		And order history shows correct order number
		When customer select product and navigate to my bag page
		And I change the quantity to "5" of a line item 1
		And click secure checkout button
		And select delivery option
		And enter delivery mandatory mobile number
		And select available delivery mode
		Then Confirm CVV Field is displayed
		And Place order button is "greyed out"
		And Enter CVV Number
		And I click on the place order button
		Then order has been successful
		And order history shows correct order number
		When customer select product and navigate to my bag page
		And I change the quantity to "5" of a line item 1
		And click secure checkout button
		And select delivery option
		And i click change delivery address
		And select available delivery mode
		And saved card is displayed
		Then Confirm CVV Field is displayed
		And Place order button is "greyed out"
		And Enter CVV Number
		And I click on the place order button
		Then order has been successful
		And order history shows correct order number
		When customer select product and navigate to my bag page
		And I change the quantity to "5" of a line item 1
		And click secure checkout button
		And select collection button
		Then Confirm CVV Field is displayed
		And Place order button is "greyed out"
		And Enter CVV Number
		And I click on the place order button
		Then order has been successful
		And order history shows correct order number
		When customer select product and navigate to my bag page
		And I change the quantity to "5" of a line item 1
		And click secure checkout button
		And select collection button and enter London in search field
		And select first Available collection Store
		Then Confirm CVV Field is displayed
		And Place order button is "greyed out"
		And Enter CVV Number
		And I click on the place order button
		Then order has been successful
		And order history shows correct order number

   #NLCC-5721
	@newlook @condev_data
	Scenario:25 Registered user When Changes Delivery Mode cant place order without enteriing CVV
		And feature "feature.storefront.checkout.cvv.check" is switched on
		And customer is logged out
		When user provides required details to create new account
		And new customer checkout a product for collection
		And pays by paymentType_Card Visa visa_CardNumber_1
		Then order has been successful
		And order history shows correct order number
		When customer select product and navigate to my bag page
		And I change the quantity to "5" of a line item 1
		And click secure checkout button
		And select delivery option
		And enter delivery mandatory mobile number
		Then Confirm CVV Field is displayed
		Then Place order button is "greyed out"
		And user places order
		Then payment method error is displayed

   #NLCC-6380
	@newlook
	Scenario:26 Guest user can see ghost text and greyed out place order button
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		And Select a product and navigate to payment page
		And continue as guest
		And user opted DELIVERY with post code CH7 4TW
		And pays by paymentType_Card Visa visa_CardNumber_1

	#NLCC-6380
	@newlook
	Scenario:27 Registered user can see ghost text and greyed out place order button
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		When user provides required details to create new account
		And user search and checkout a product
		And chooses standard delivery
		And select available delivery mode
		And click continue to payment button
		Then Place order button is "greyed out"
		And pays by paymentType_Card Visa visa_CardNumber_1
		Then order has been successful

	#NLCC-6858
	@newlook @condev_data
	Scenario:28 Guest user can see correct save card message
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		And Select a product and navigate to payment page
		And continue as guest
		And user opted DELIVERY with post code CH7 4TW
		And select available delivery mode
		And click continue to payment button
		Then Proper save card message is displayed
		And fill the card details "Visa","4000000000001091","07","2022","123"
		And customer clicks add another card
		Then Proper save card message is displayed


	#NLCC-6858
	@newlook @condev_data
	Scenario:29 Registered user can see correct save card message
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		When user provides required details to create new account
		And Select a product and navigate to payment page
		And chooses standard delivery
		And select available delivery mode
		And click continue to payment button
		Then Proper save card message is displayed
		And fill the card details "Visa","4000000000001091","07","2022","123"
		And customer clicks add another card
		Then Proper save card message is displayed

	#NLCC-6998
	Scenario:30 Default delivery mode for home
		And feature "feature.storefront.checkout.defaultDeliveryMode.enabled" is switched on
		And user search and checkout a product
		And select delivery option
		Then default delivery option should be selected

	@newlook
   #NLCC-7572 is testing NLCC-6926 AC2
	Scenario: 31 Billing Address form for logged in user for collection delivery
		And feature "feature.storefront.checkout.new.tender" is switched on
		When customer select product and navigate to my bag page
		And I change the quantity to "5" of a line item 1
		And click secure checkout button
		And select delivery method as "Collection" and location as "London"
		And select payment method as "card"
		Then saved billing address should be displayed inside selected payment as "card"
		And select payment method as "klarna"
		Then saved billing address should be displayed inside selected payment as "klarna"

	@newlook
	#NLCC-7606 is testing NLCC-7428
	Scenario: 32 Hide Klarna Pay in 30 in Checkout A/B test (Registered User)
		And feature "feature.storefront.checkout.hideKlarnaPayIn30" is switched on
		When customer select product and navigate to my bag page
		And I change the quantity to "5" of a line item 1
		And click secure checkout button
		And select delivery method as "Collection" and location as "London"
		And select payment method as "klarna"
		Then Virtual payment option is displayed as "pay over time"
		And Edit bag with quantity "1"
		And select payment method as "klarna"
		Then Virtual payment option is displayed as "error"

	@newlook
   #NLCC-7606 is testing NLCC-7428
	Scenario: 33 Show Klarna Pay in 30 in Checkout A/B test (Registered User)
		And feature "feature.storefront.checkout.hideKlarnaPayIn30" is switched off
		Then remove all the added items
		When customer select product and navigate to my bag page
		And I change the quantity to "5" of a line item 1
		And click secure checkout button
		And select delivery method as "Collection" and location as "London"
		And select payment method as "klarna"
		Then Virtual payment option is displayed as "both"
		And Edit bag with quantity "1"
		And select payment method as "klarna"
		Then Virtual payment option is displayed as "pay later"

	@NewUsers
	#NLCC-7154 is testing NLCC-6787 AC1
	Scenario:34 New customer has no "USE THIS CARD" button when user has no saved cards and delivery option
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		And user provides required details to create new account
		And Select a product and navigate to payment page
		And chooses standard delivery
		And select available delivery mode
		And click continue to payment button
		And select payment method as "NewLookStoreCard"
		Then use this card option is "not displayed"

	@NewUsers
	#NLCC-7154 is testing NLCC-6787 AC2
	Scenario:35 New customer has no "USE THIS CARD" button when user has no saved cards and collection option
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		And user provides required details to create new account
		And Select a product and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And select payment method as "NewLookStoreCard"
		And fill store card billing address
		Then use this card option is "not displayed"

	@Anonymous
   #NLCC-7154 is testing NLCC-6787 AC1
	Scenario:36 Guest customer has no "USE THIS CARD" button when user has no saved cards and delivery option
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		And Select a product and navigate to payment page
		And continue as guest
		And user opted DELIVERY with post code CH7 4TW
		And select available delivery mode
		And click continue to payment button
		And select payment method as "NewLookStoreCard"
		And fill store card billing address
		Then use this card option is "not displayed"

	@Anonymous
	#NLCC-7154 is testing NLCC-6787 AC2
	Scenario:37 Guest customer has no "USE THIS CARD" button when user has no saved cards and collection option
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		And Select a product and navigate to payment page
		And continue as guest
		And search for nearest collection location with "london" and select collection location as "Click & Collect Standard"
		And enter collection details
		And click continue to payment button
		And select payment method as "NewLookStoreCard"
		And fill store card billing address
		Then use this card option is "not displayed"

	#NLCC-6858
	@newlook @oms_data
	Scenario:38 Guest user can see correct save card message OMS
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		And Select a product and navigate to payment page
		And continue as guest
		And user opted DELIVERY with post code CH7 4TW
		And click continue to payment button
		Then Proper save card message is displayed
		And fill the card details "Visa","4000000000001091","07","2022","123"
		Then Proper save card message is displayed

	#NLCC-6858
	@newlook @oms_data
	Scenario:39 Registered user can see correct save card message OMS
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		When user provides required details to create new account
		And Select a product and navigate to payment page
		And chooses standard delivery
		And click continue to payment button
		Then Proper save card message is displayed
		And fill the card details "Visa","4000000000001091","07","2022","123"
		Then Proper save card message is displayed

   #NLCC-7968 is testing NLCC-7899
	Scenario:40 Testing bug# NLCC-7899 Storecard has no billing address and 2 "Change card" CTA's -- Registered User
		And feature "feature.storefront.checkout.new.tender" is switched on
		And Select a product and navigate to payment page
		And select delivery option
		And select available delivery mode
		And add store card details with no "9826220039999995" and expiry date 0823
		And click age verification check box
		And user places order
		Then invalid card message "displayed" for "number"
		And previous billing details and change card button displayed

  #NLCC-7968 is testing NLCC-7899
	@Anonymous
	Scenario:41 Testing bug# NLCC-7899 Storecard has no billing address and 2 "Change card" CTA's -- Guest User
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		And Select a product and navigate to payment page
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
	@Anonymous
	Scenario:42 Testing bug#NLCC-7754 Storecard expiry date error -- Guest User
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		And Select a product and navigate to payment page
		And continue as guest
		And chooses standard delivery
		And select available delivery mode
		And click continue to payment button
		And add store card details with no "9826240012340050" and expiry date 823
		And user confirms over the age of 18 of store card
		And user places order
		And order has been successful

	#NLCC-8009 is testing NLCC-7754
	Scenario:43 Testing bug#NLCC-7754 Storecard expiry date error -- Registered User
		And feature "feature.storefront.checkout.new.tender" is switched on
		And Select a product and navigate to payment page
		And select delivery option
		And enter delivery mandatory mobile number
		And select available delivery mode
		And I continue to payment
		And add store card details with no "9826240012340050" and expiry date 823
		And user confirms over the age of 18 of store card
		And user places order
		And order has been successful

  @newlook @Anonymous @oms_flow @OMSF-1204
	Scenario:44  AC 1 - Successful Soft Promise Call - Store Only - One Size -- Guest User
	  And customer is logged out
		When Select productCode_Store_Only_OneSize_OMS single size product and navigate to payment page
		And provide guest user details
		Then collection method is disabled on checkout page
		And home delivery method is selected on checkout page

	@newlook @Anonymous @oms_flow @OMSF-1204
	Scenario:45  AC 2: Successful Soft Promise Call - DC Only - MultiSize -- Registered user
	    And customer is logged out
		And login with username "deliveryMethodEmail" and password "passwordDeliveryMethod"
		And remove all the added items
		And customer search for product productCode_DC_Only_MultiSize_OMS with size XS - Only 3 items left!
		And navigate to bag page and secure checkout
		Then collection method is enabled on checkout page
		And home delivery method is enabled on checkout page

	@newlook @Anonymous @oms_flow @OMSF-1204 @oms_data
	Scenario:46 - Successful Soft Promise Call - Store Only - One Size -- Registered User - Scenario: 47 Selected home delivery option date highlighted to the customer for Standard delivery OMS
	    And customer is logged out
		And login with username "deliveryMethodEmail" and password "passwordDeliveryMethod"
		And remove all the added items
		And customer search for product productCode_Store_Only_MultiSize_OMS with size UK 12 - Only 3 items left!
		And customer search for product productCode_DC_Only_MultiSize_OMS with size XS - Only 3 items left!
		And navigate to bag page and secure checkout
		Then collection method is disabled on checkout page
		And home delivery method is selected on checkout page
		And fetch all delivery options displayed and verified it is "deliveryOptions"


		#NLCC-5721
	@newlook @oms_flow
	Scenario:47 Registered user When Changes Delivery Mode is asked to enter CVV again
		And feature "feature.storefront.checkout.cvv.check" is switched on
		And customer is logged out
		When user provides required details to create new account
		And new customer checkout a product for collection
		And pays by paymentType_Card Visa visa_CardNumber_1
		Then order has been successful
		And order history shows correct order number for Oms
		When customer select product and navigate to my bag page
		And I change the quantity to "5" of a line item 1
		And click secure checkout button
		And select delivery option
		And enter delivery mandatory mobile number
		And select available delivery mode
		Then Confirm CVV Field is displayed
		And Place order button is "greyed out"
		And Enter CVV Number
		And I click on the place order button
		Then order has been successful
		And order history shows correct order number for Oms
		When customer select product and navigate to my bag page
		And I change the quantity to "5" of a line item 1
		And click secure checkout button
		And select delivery option
		And i click change delivery address
		And select available delivery mode
		And saved card is displayed
		Then Confirm CVV Field is displayed
		And Place order button is "greyed out"
		And Enter CVV Number
		And I click on the place order button
		Then order has been successful
		And order history shows correct order number for Oms
		When customer select product and navigate to my bag page
		And I change the quantity to "5" of a line item 1
		And click secure checkout button
		And select collection button
		Then Confirm CVV Field is displayed
		And Place order button is "greyed out"
		And Enter CVV Number
		And I click on the place order button
		Then order has been successful
		And order history shows correct order number for Oms
		When customer select product and navigate to my bag page
		And I change the quantity to "5" of a line item 1
		And click secure checkout button
		And select collection button and enter London in search field
		And select first Available collection Store
		Then Confirm CVV Field is displayed
		And Place order button is "greyed out"
		And Enter CVV Number
		And I click on the place order button
		Then order has been successful
		And order history shows correct order number for Oms

   #NLCC-5721
	@newlook @oms_flow
	Scenario:48 Registered user When Changes Delivery Mode cant place order without enteriing CVV
		And feature "feature.storefront.checkout.cvv.check" is switched on
		And customer is logged out
		When user provides required details to create new account
		And new customer checkout a product for collection
		And pays by paymentType_Card Visa visa_CardNumber_1
		Then order has been successful
		And order history shows correct order number for Oms
		When customer select product and navigate to my bag page
		And I change the quantity to "5" of a line item 1
		And click secure checkout button
		And select delivery option
		And enter delivery mandatory mobile number
		Then Confirm CVV Field is displayed
		Then Place order button is "greyed out"
		And user places order
		Then payment method error is displayed


	@oms_flow @OMSF-1608
	Scenario Outline:49 Successful Hard Promise Call with Delivery SLA Broken with SKU ends with Zero with singlesize - Checkout Error with Warehouse Stock to Store -- feature flag on
	  And feature "feature.newlookintegrationipaas.customer.order.promise.soft.enabled" is switched on
		Then remove all the added items
    When Select productCode_OneSize_OMS single size product and navigate to payment page
		And select delivery option
		And user makes a purchase with a different mode
		And pays by <payment type> <card type> <card number>
		And change the feature flag "false" for the feature name "featureName"
		And user places order
		And sla broken and group delivery message displayed along with collection disabled text "featureName"
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |


		@oms_flow @OMSF-1608
	  Scenario Outline:50 Successful Hard Promise Call with Delivery SLA Broken with SKU ends with Zero with multisize - Checkout Error with Warehouse Stock to Store --  feature flag on
	  And feature "feature.newlookintegrationipaas.customer.order.promise.soft.enabled" is switched on
		Then remove all the added items
    And customer search for product productCode_stock with size 8L34
    And navigate to bag page and secure checkout
		And select delivery option
		And user makes a purchase with a different mode
		And pays by <payment type> <card type> <card number>
		And change the feature flag "false" for the feature name "featureName"
		And user places order
		And sla broken and group delivery message displayed along with collection disabled text "featureName"
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |

		@oms_flow @OMSF-1608
	  Scenario Outline:51 Successful Hard Promise Call with Delivery SLA Broken with SKU ends with Zero and one with multisize - Checkout Error with Warehouse Stock to Store -- feature flag on
	  And feature "feature.newlookintegrationipaas.customer.order.promise.soft.enabled" is switched on
		Then remove all the added items
    And customer search for product productCode_stock with size 8L34
    And customer search for product productCode_stock with size 10L34
    And navigate to bag page and secure checkout
		And select delivery option
		And user makes a purchase with a different mode
		And pays by <payment type> <card type> <card number>
		And change the feature flag "false" for the feature name "featureName"
		And user places order
		And sla broken and group delivery message displayed along with collection disabled text "featureName"
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |


	@newlook @Anonymous @oms_flow @OMSF-2280
	Scenario:52  AC 1 - Successful Soft Promise Call - Store Only - Check message for Store only product -- Guest User
		And customer is logged out
		When Select productCode_Store_Only_OneSize_OMS single size product and navigate to payment page
		And provide guest user details
		Then collection method is disabled on checkout page
		And the label Not Available is specified on the COLLECTION delivery option
		And the messaging is displayed above the delivery options per the design

	@newlook @Anonymous @oms_flow @OMSF-2280
	Scenario:53  AC 1 - Successful Soft Promise Call - Store Only - Check message for Store only product MutliSize product-- Guest User
		And customer is logged out
		And customer search for product productCode_Store_Only_MultiSize_OMS with size UK 12 - Only 3 items left!
		And navigate to bag page and secure checkout
		And provide guest user details
		Then collection method is disabled on checkout page
		And the label Not Available is specified on the COLLECTION delivery option
		And the messaging is displayed above the delivery options per the design


	@newlook  @oms_flow @OMSF-2280
	Scenario:54  AC 1 - Successful Soft Promise Call - Store+DC Only - Check message product store and Dc both product-- Signed in user
		And customer is logged out
		And login with username "deliveryMethodEmail" and password "passwordDeliveryMethod"
		And remove all the added items
		And customer search for product productCode_Store_Only_MultiSize_OMS with size UK 12 - Only 3 items left!
		And customer search for product productCode_DC_Only_MultiSize_OMS with size XS - Only 3 items left!
		And navigate to bag page and secure checkout
		Then collection method is disabled on checkout page
		And the label Not Available is specified on the COLLECTION delivery option
		And the messaging is displayed above the delivery options per the design


	@oms_flow @OMSF-2280
	Scenario Outline:55 Ac2 : promise return Successful Hard Promise Call with Delivery SLA Broken with SKU ends with Zero with multisize - Checkout Error with Warehouse Stock to Store --  feature flag on
		And feature "feature.newlookintegrationipaas.customer.order.promise.soft.enabled" is switched on
		Then remove all the added items
		And customer search for product productCode_stock with size 8L34
		And navigate to bag page and secure checkout
		And select delivery option
		And user makes a purchase with a different mode
		And pays by <payment type> <card type> <card number>
		And change the feature flag "false" for the feature name "featureName"
		And user places order
		And sla broken and group delivery message displayed along with collection disabled text "featureName"
		And the messaging is displayed above the delivery options per the design
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |

	@oms_flow @OMSF-2280
	Scenario Outline:56 Ac2 : promise return Successful Hard Promise Call with Delivery SLA Broken with SKU ends with Zero and one with multisize - Checkout Error with Warehouse Stock to Store -- feature flag on
		And feature "feature.newlookintegrationipaas.customer.order.promise.soft.enabled" is switched on
		Then remove all the added items
		And customer search for product productCode_stock with size 8L34
		And customer search for product productCode_stock with size 10L34
		And navigate to bag page and secure checkout
		And select delivery option
		And user makes a purchase with a different mode
		And pays by <payment type> <card type> <card number>
		And change the feature flag "false" for the feature name "featureName"
		And user places order
		And sla broken and group delivery message displayed along with collection disabled text "featureName"
		And the messaging is displayed above the delivery options per the design
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |

	@oms_flow @OMSF-2280
	Scenario Outline:57 Ac2 : promise return Successful Hard Promise Call with Delivery SLA Broken with SKU ends with Zero with singlesize - Checkout Error with Warehouse Stock to Store -- feature flag on
		And feature "feature.newlookintegrationipaas.customer.order.promise.soft.enabled" is switched on
		Then remove all the added items
		When Select productCode_OneSize_OMS single size product and navigate to payment page
		And select delivery option
		And user makes a purchase with a different mode
		And pays by <payment type> <card type> <card number>
		And change the feature flag "false" for the feature name "featureName"
		And user places order
		And sla broken and group delivery message displayed along with collection disabled text "featureName"
		And the messaging is displayed above the delivery options per the design
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |
