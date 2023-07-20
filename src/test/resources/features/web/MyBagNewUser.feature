@newlook @NewUsers
Feature: My Bag New user

	Background:
		Given i navigate to "Newlook" home page
		And user provides required details to create new account

	@condev_data
	Scenario Outline:1 My bag count for new user with one product
		And customer search for product productCode_stock with size <size>
		When navigated to my bag page
		Then my bag header should display <count>
		Examples:
			| size  | count |
			| 12L32 | 1     |

	Scenario Outline:2 As a New Customer, I want to be warned when my address cannot be delivered to by the current store, so that I can complete my purchase in the correct hybris store
		And feature "feature.storefront.brexitBanner.enabled" is switched off
		And customer search for product productCode_stock with size 12L32
		And navigated to my bag page
		And navigate to checkout and payments page
		And select DELIVERY as option
		When change the "<deliveryCountry>" in the delivery country address form the dropdown of checkout page
#    And navigate to checkout and payments page
		Then you should see appropriate message for changed "<messagePart1>" "<deliveryCountry>" "<messagePart2>"

		Examples:
			| country | deliveryCountry | messagePart1                           | messagePart2           |
			| France  | Finland         | If you change your delivery country to | Please review your bag |

   #NLCC-7675 is testing NLCC-7599
	@condev_data
	Scenario:3 Secure Checkout CTA and Have a promo code dropdown displayed to the right-hand side of the bag page for New User -- New Design
		And feature "feature.storefront.desktop.sticky.cart.cta" is switched on
		And customer search for product productCode_stock with size 12L32
		And navigated to my bag page
		Then secure checkout CTA displayed
		And total amount displayed in sticky
		And delivery information displayed
		And have a promo code dropdown displayed
		And delivering to country displayed
		And "new" user can navigate to checkout quicker

   #NLCC-7675 is testing NLCC-7599
	@condev_data
	Scenario:4 Invalid/Valid promo code applied on bag page for New User -- New Design
		And feature "feature.storefront.desktop.sticky.cart.cta" is switched on
		And customer search for product productCode_stock with size 12L32
		And navigated to my bag page
		When click on have a promo code dropdown
		And apply the "CIN-NPDK-4KNK-RH2H" in the promo box
		And click apply
		Then promo code "invalid" message displayed
		And apply the "10PCOFF" in the promo box
		And click apply
		Then promo code "valid" message displayed
		And applied discount displayed


	@oms_data
	Scenario Outline:5 My bag count for new user with one product OMS
		And customer search for product productCode_stock with size <size>
		When navigated to my bag page
		Then my bag header should display <count>
		Examples:
			| size  | count |
			| 8L34 | 1     |


	@oms_data
	Scenario:6 Secure Checkout CTA and Have a promo code dropdown displayed to the right-hand side of the bag page for New User OMS-- New Design
		And feature "feature.storefront.desktop.sticky.cart.cta" is switched on
		And customer search for product productCode_stock with size 8L34
		And navigated to my bag page
		Then secure checkout CTA displayed
		And total amount displayed in sticky
		And delivery information displayed
		And have a promo code dropdown displayed
		And delivering to country displayed
		And "new" user can navigate to checkout quicker


	@oms_data
	Scenario:7 Invalid/Valid promo code applied on bag page for New User OMS-- New Design
		And feature "feature.storefront.desktop.sticky.cart.cta" is switched on
		And customer search for product productCode_stock with size 8L34
		And navigated to my bag page
		When click on have a promo code dropdown
		And apply the "CIN-NPDK-4KNK-RH2H" in the promo box
		And click apply
		Then promo code "invalid" message displayed
		And apply the "10PCOFF" in the promo box
		And click apply
		Then promo code "valid" message displayed
		And applied discount displayed

	@newlook @oms_flow @OMSF-337
	Scenario Outline: 8 hard promise call fails with out of stock creditcard - oms
		Given I checkout a product
		And I have checked out with the a delivery address "bob" and "rob" and postcode "EE1 1EE"
		And pays by <payment type> <card type> <card number>
		Then I am taken to the full cart page
		And out of stock message is displayed on cart page
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |

	@newlook @oms_flow @OMSF-337
	Scenario: 9 hard promise call fails with out of stock paypal - oms
		Given I checkout a product
		And I have checked out with the a delivery address "bob" and "rob" and postcode "EE1 1EE"
		And pays by paymentType_Paypal - -
		Then I am taken to the full cart page
		And out of stock message is displayed on cart page
		
	@newlook @oms_flow @OMSF-337
	Scenario Outline: 10 Error scenario - hard promise successful but payment validation fails - AC4 oms
		Given I checkout a product
		And I have checked out with the a delivery address "bob" and "rob" and postcode "KY8 5NZ"
		And pays by <payment type> <card type> <card number>
		Then I am on the checkout page
		And The failed payment error message is visible
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_3 |		







