@Anonymous2
Feature: Klarna Payment Design

	Background:
		Given i navigate to "Newlook" home page

	Scenario:1 Display virtual Credit as a payment option to the customer
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		When user provides required details to create new account
		And Select a product and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		Then Display "new" design with "displayed" Klarna card

#   todo
# Scenario:2 Display new design with NO Virtual Credit payment tender
#   And feature "feature.payment.klarna.enabled.uk-site" is switched on
#   When Klarna Merchant Config is "false"
#   When user provides required details to create new account
#   And Select a product and navigate to payment page
#   And search for nearest collection location to: "London"
#   And I continue to payment
#   Then Display "new" design with "none-displayed" Klarna card

	Scenario:3 Display old design with NO Virtual Credit payment tender,merchant config is on
		And feature "feature.payment.klarna.enabled.uk-site" is switched off
		When Klarna Merchant Config is "true"
		When user provides required details to create new account
		And Select a product and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		Then Display "old" design with "none-displayed" Klarna card

	Scenario:4 Display old design with NO Virtual Credit payment tender
		And feature "feature.payment.klarna.enabled.uk-site" is switched off
		When Klarna Merchant Config is "false"
		When user provides required details to create new account
		And Select a product and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		Then Display "old" design with "none-displayed" Klarna card

	Scenario:5 Display virtual credit payment widget - guest user
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And Select a product and navigate to payment page
		And provide guest user details
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Add billing address - guest user
		Then Virtual credit payment widget is displayed

	Scenario:6 Display virtual credit payment widget - new user
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		When user provides required details to create new account
		And Select a product and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And fill the billing address details for checkout "SW1W 9SJ" , "London"
		Then Virtual credit payment widget is displayed

	Scenario:7 Display virtual credit payment widget and prev. used billing address- registered user
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And Select a product and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		Then Virtual credit payment widget is displayed

	Scenario:8 Display virtual credit payment widget, update defaulted billing address with new address
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And Select a product and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Click on change address link to change the address with "KT1 3HP"
		Then New address with postcode: "KT1 3HP" is displayed
		Then Virtual credit payment widget is displayed

	@condev_data
	Scenario:9 Pay with Virtual Credit (home delivery)
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Select a product and navigate to payment page with product "shoes"
		And I have checked out with the a delivery address "Test" and "Klarna"
		And pays by paymentType_Virtualcard - -
		Then order has been successful

  @condev_data
	Scenario:10 Pay with Virtual Credit (click and collect)
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "shoes"
		And pays by paymentType_Virtualcard - -
		Then order has been successful

   @condev_data
	Scenario:11 Customer changes their mind about using Virtual Credit at Checkout, selects a different payment option, then returns to Klarna again
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "shoes"
		And Select payment with virtual card
		And Select payment with credit card
		And Select payment with virtual card
		Then Virtual credit payment widget is immediately displayed

	@condev_data
	Scenario Outline:12 Customer changes their mind about using Virtual Credit at Checkout
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "shoes"
		And Select payment with virtual card
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		And order history shows correct order number
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |

	Scenario:13 Klarna logo is visible in the footer of Login Page
		Then Klarna logo is visible in the footer

	Scenario:14 Klarna logo is visible in the footer of Checkout Page
		And user provides required details to create new account
		And Select a product and navigate to payment page
		Then Klarna logo is visible in the footer

	Scenario:15 Klarna logo is visible in the footer of Bag Page
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "jeans"
		Then Klarna logo is visible in the footer

	@condev_data
	Scenario:16 Klarna logo is visible in the footer of Order Confirmation Page
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "shoes"
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		Then Klarna logo is visible in the footer

	@condev_data
	Scenario:17 Klarna payment tender visible on the customer order history (Registered Customer)
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "shoes"
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		And User navigates to order history page
		And Selects last order
		Then Klarna payment tender is displayed

	Scenario:18 Guest Customer checkout using Klarna and immediately after registers an account
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And Select a product and navigate to payment page
		And provide guest user details
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Add billing address - guest user
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		And Customer is displayed with option to add a password and register an account


	Scenario:19 Klarna payment tender visible on the order confirmation page (Guest Customer)
		And Select a product and navigate to payment page
		And provide guest user details
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Add billing address - guest user
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		Then Klarna payment tender is displayed

	@condev_data
	Scenario:20 Klarna payment tender visible on the order confirmation page (Registered Customer)
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "shoes"
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		Then Klarna payment tender is displayed

	#NLCC-6386
	@newlook
	Scenario:21 Klarna Payment design with new tab design - registered user
		And feature "feature.storefront.checkout.new.tender" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Select a product and navigate to payment page with product "jeans"
		And Edit bag with quantity "1"
		And I have checked out with the a delivery address "Test" and "Klarna"
		And click continue to payment button
		And I select klarna payment option
		Then new klarna design is visible without tabs
		And Edit bag with quantity "6"
		And I select klarna payment option
		Then new klarna design is visible with tabs

	@oms_data
	Scenario:22 Pay with Virtual Credit (home delivery) OMS
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Select a product and navigate to payment page with product "jeans"
		And I have checked out with the a delivery address "Test" and "Klarna"
		And pays by paymentType_Virtualcard - -
		Then order has been successful

	@oms_data
	Scenario:23 Pay with Virtual Credit (click and collect) OMS
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "jeans"
		And pays by paymentType_Virtualcard - -
		Then order has been successful

	@oms_data
	Scenario:24 Customer changes their mind about using Virtual Credit at Checkout, selects a different payment option, then returns to Klarna again OMS
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "jeans"
		And Select payment with virtual card
		And Select payment with credit card
		And Select payment with virtual card
		Then Virtual credit payment widget is immediately displayed

	@oms_data
	Scenario Outline:25 Customer changes their mind about using Virtual Credit at Checkout OMS
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "jeans"
		And Select payment with virtual card
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		And order history shows correct order number
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |

	@oms_data
	Scenario:26 Klarna logo is visible in the footer of Order Confirmation Page OMS
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "jeans"
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		Then Klarna logo is visible in the footer

	@oms_data
	Scenario:27 Klarna payment tender visible on the customer order history (Registered Customer) OMS
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "jeans"
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		And User navigates to order history page
		And Selects last order
		Then Klarna payment tender is displayed

	@oms_data
	Scenario:28 Klarna payment tender visible on the order confirmation page (Registered Customer) OMS
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "jeans"
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		Then Klarna payment tender is displayed
