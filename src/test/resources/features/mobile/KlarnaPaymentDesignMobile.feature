@anonymousMobile2Samsung
Feature: Klarna Payment Design - mobile

	Background:
		Given i navigate to "Newlook" home page

	Scenario:1 Display virtual Credit as a payment option to the customer - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		And feature "feature.storefront.checkout.new.tender" is switched on
		When Klarna Merchant Config is "true"
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location to: "London"
		And I continue to payment
		Then Display "new" design with "displayed" Klarna card

#  NLCC-2430
#  Scenario:2 Display new design with NO Virtual Credit payment tender - mobile
#   And feature "feature.payment.klarna.enabled.uk-site" is switched on
#   When Klarna Merchant Config is "false"
#   When user provides required details to create new account on mobile
#   And Select a product and navigate to payment page on mobile
#   And search for nearest collection location to: "London"
#   And I continue to payment
#   Then Display "new" design with "none-displayed" Klarna card

	Scenario:3 Display old design with NO Virtual Credit payment tender,merchant config is on - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched off
		And feature "feature.storefront.checkout.new.tender" is switched off
		When Klarna Merchant Config is "true"
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location to: "London"
		And I continue to payment
		Then Display "old" design with "none-displayed" Klarna card

	Scenario:4 Display old design with NO Virtual Credit payment tender - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched off
		And feature "feature.storefront.checkout.new.tender" is switched off
		When Klarna Merchant Config is "false"
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location to: "London"
		And I continue to payment
		Then Display "old" design with "none-displayed" Klarna card

	Scenario:5 Display virtual credit payment widget - guest user - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And Select a product and navigate to payment page on mobile
		And provide guest user details
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Add billing address - guest user
		Then Virtual credit payment widget is displayed

	Scenario:6 Display virtual credit payment widget - new user - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And fill the billing address details for checkout "SW1W 9SJ" , "London"
		Then Virtual credit payment widget is displayed

	Scenario:7 Display virtual credit payment widget and prev. used billing address- registered user - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		Then Virtual credit payment widget is displayed

	Scenario:8 Display virtual credit payment widget, update defaulted billing address with new address - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Click on change address link to change the address with "W12 7SL"
		Then New address with postcode: "W12 7SL" is displayed
		Then Virtual credit payment widget is displayed
		
    @condev_data
	Scenario:9 Pay with Virtual Credit (home delivery)
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Select a product "shoes" and navigate to payment page
		And I have checked out with the a delivery address "Test" and "Klarna"
		And pays by paymentType_Virtualcard - -
		Then order has been successful

    @condev_data
	Scenario:10 Pay with Virtual Credit (click and collect)
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "shoes" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And pays by paymentType_Virtualcard - -
		Then order has been successful

    @condev_data
	Scenario:11 Customer changes their mind about using Virtual Credit at Checkout, selects a different payment option, then returns to Klarna again - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "shoes" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Select payment with credit card
		And Select payment with virtual card
		Then Virtual credit payment widget is immediately displayed

    @condev_data
	Scenario Outline:12 Customer changes their mind about using Virtual Credit at Checkout
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "shoes" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |

    @condev_data
	Scenario Outline:13 Customer changes their mind about using Virtual Credit at Checkout -PCI on
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "shoes" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |

	Scenario:14 Klarna logo is visible in the footer of Login Page - mobile
		Then Klarna logo is visible in the footer

	Scenario:15 Klarna logo is visible in the footer of Checkout Page - mobile
		And user provides required details to create new account on mobile
		And Select a product "jeans" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		Then Klarna logo is visible in the footer

	Scenario:16 Klarna logo is visible in the footer of Bag Page - mobile
		And user provides required details to create new account on mobile
		And Select a product "jeans" and navigate to payment page
		Then Klarna logo is visible in the footer

    @condev_data
	Scenario:17 Klarna logo is visible in the footer of Order Confirmation Page - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "shoes" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		Then Klarna logo is visible in the footer

    @condev_data
	Scenario:18 Klarna payment tender visible on the customer order history (Registered Customer) - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "shoes" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		And click  "Track Your Order" on mobile
		And Selects last order
		Then Klarna payment tender is displayed

	Scenario:19 Guest Customer checkout using Klarna and immediately after registers an account - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And Select a product and navigate to payment page on mobile
		And provide guest user details
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Add billing address - guest user
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		And Customer is displayed with option to add a password and register an account

	Scenario:20 Klarna payment tender visible on the order confirmation page (Guest Customer) - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And Select a product and navigate to payment page on mobile
		And provide guest user details
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Add billing address - guest user
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		Then Klarna payment tender is displayed

  @condev_data
	Scenario:21 Klarna payment tender visible on the order confirmation page (Registered Customer) - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "shoes" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		Then Klarna payment tender is displayed

	#NLCC-6386
	Scenario:22 Klarna Payment design with new tab design - registered user on mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And Edit bag with quantity "1"
		And chooses standard delivery
		And click continue to payment button
		And I select klarna payment option
		Then new klarna design is visible without tabs
		And Edit bag with quantity "6"
		And I select klarna payment option
		Then new klarna design is visible with tabs

	#NLCC-7606 is testing NLCC-7428
	Scenario: 23 Hide Klarna Pay in 30 in Checkout A/B test - guest user - mobile
		And feature "feature.storefront.checkout.hideKlarnaPayIn30" is switched on
		When Select a product and navigate to payment page on mobile
		And provide guest user details
		And Edit bag with quantity "5"
		And chooses standard delivery
		And select available delivery mode
		And click continue to payment button
		And select payment method as "klarna"
		Then Virtual payment option is displayed as "pay over time"
		And Edit bag with quantity "1"
		And select payment method as "klarna"
		Then Virtual payment option is displayed as "error"

   #NLCC-7606 is testing NLCC-7428
	Scenario: 24 Show Klarna Pay in 30 in Checkout A/B test - guest user - mobile
		And feature "feature.storefront.checkout.hideKlarnaPayIn30" is switched off
		When Select a product and navigate to payment page on mobile
		And provide guest user details
		And Edit bag with quantity "5"
		And chooses standard delivery
		And select available delivery mode
		And click continue to payment button
		And select payment method as "klarna"
		Then Virtual payment option is displayed as "both"
		And Edit bag with quantity "1"
		And select payment method as "klarna"
		Then Virtual payment option is displayed as "pay later"
		
	@oms_data
	Scenario:25 Pay with Virtual Credit (home delivery) OMS
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Select a product "jeans" and navigate to payment page
		And I have checked out with the a delivery address "Test" and "Klarna"
		And pays by paymentType_Virtualcard - -
		Then order has been successful

    @oms_data
	Scenario:26 Pay with Virtual Credit (click and collect) OMS
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "jeans" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And pays by paymentType_Virtualcard - -
		Then order has been successful

    @oms_data
	Scenario:27 Customer changes their mind about using Virtual Credit at Checkout, selects a different payment option, then returns to Klarna again OMS - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "jeans" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Select payment with credit card
		And Select payment with virtual card
		Then Virtual credit payment widget is immediately displayed

    @oms_data
	Scenario Outline:28 Customer changes their mind about using Virtual Credit at Checkout OMS
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "jeans" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |		

    @oms_data
	Scenario:29 Klarna logo is visible in the footer of Order Confirmation Page OMS - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "jeans" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		Then Klarna logo is visible in the footer

    @oms_data
	Scenario:30 Klarna payment tender visible on the customer order history (Registered Customer) OMS - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "jeans" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		And click  "Track Your Order" on mobile
		And Selects last order
		Then Klarna payment tender is displayed

    @oms_data		
	Scenario:31 Klarna payment tender visible on the order confirmation page (Registered Customer) OMS - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "jeans" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		Then Klarna payment tender is displayed		
	
    @oms_data	
	Scenario Outline:32 Customer changes their mind about using Virtual Credit at Checkout OMS -PCI on
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "jeans" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |		
					
