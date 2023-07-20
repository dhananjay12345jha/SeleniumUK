# 22/02/18
@newlook @Anonymous
Feature: My Bag Anonymous user

	Background:
		Given i navigate to "Newlook" home page


	Scenario:1 Requested quantity low stock
		And feature "feature.storefront.stock.urgency.messaging.cart" is switched off
		And customer search for low stock product with size "36C - Only 2 items left!" and add product to my page
		When navigated to my bag page
		Then I am only shown the quantity 2 in the picker


	Scenario:2 As a Customer, I want to increase the quantities of line items in my cart, so that I can control the volume of products I am going to buy
		And customer select product and navigate to my bag page
		When I change the quantity to "2" for a line item 1
		Then the additional products will be committed to my cart with the same quantity: "2"


	Scenario:3 Select color from swatch panel
		And customer search for multi coloured product and navigate to my bag page
		When customer change the product colour
		Then product code will change with the colour change

##messages are not added to the examples need to figure out what messages need to be passed
#    @todo
#  Scenario Outline:5 As a Hard Logged In Customer, I want to be warned when my address cannot be delivered to by the current store, so that I can complete my purchase in the correct hybris store
#    And I click on the locale selector in the header
#    And I select a different country "<country>" using the drop down
#    When I click Sign In on the header bar
#    And I enter valid login details into the registration form
#    And I submit the registration form for other countries
#    When I search for a product
#    And click on a product image or title
#    Then select size of the product
#    And I click Add to Bag
#    Then click on the bag icon
#    When i click on checkout button
#    And i click on delivery button
#    Then change the "<deliveryCountry>" in the delivery country address form the dropdown of checkout page
#    Then you should see appropirate message for changed "<messagePart1>" "<deliveryCountry>" "<messagePart2>"
#
#    Examples:
#      | country       | deliveryCountry | messagePart1 | messagePart2 |
#      | United States | Uganda          |              |              |



	Scenario:6 As a Customer I want to view collection location results based on my search in ascending order
		And Select a product and navigate to payment page
		And provide guest user details
		And search for nearest store with "london"
		Then I am shown the distance of the store from my search location in both miles and kilometres


	Scenario Outline:7 As a Customer on clicking Map and Opening times' link more information on the collection location is displayed
		And Select a product and navigate to payment page
		And provide guest user details
		And search for nearest store with "<area>"
		When I select the Collection Location name or 'Map and Opening times' link
		Then more information on the collection location is displayed
		Examples:
			| area     |
			| KT1      |
			| london   |
			| SN25 2GE |
#      | oxford circus |


	@smokepack
	Scenario:8 On searching for store locator from checkout page each collection point result is listed as a summary information
		And Select a product and navigate to payment page with product "jeans"
		And provide guest user details
		And search for nearest store with "london"
		Then for each collection point result in the list I am shown a summary of information, where available


	Scenario Outline:9 Address not recognised
		And Select a product and navigate to payment page
		And provide guest user details
		And search for nearest store with invalid "<postcode>"
		Then an error message is displayed

		Examples:
			| postcode    |
			| belgium     |
			| 43453434533 |
			| £$%£$%£$%   |

  @condev_data
	Scenario:10 product error message
    #Given i navigate to "Newlook" home page with locale fr
		And I click on the locale selector in the header
		And I select a different country country_France using the drop down
		And customer search for product productCode_France with size 12L32
		And navigated to my bag page
		When change delivery country to Royaume-Uni in my bag page
		Then item Pink Feather Fascinator should show error Not for delivery in UK!!!

	#NLTA-823, NLCC-4328
	@newlook @Anonymous @condev_data
	Scenario: 11 - Stock equal to or less than 5 units (Single Size Item)
		And feature "feature.storefront.stock.urgency.messaging.pdp" is switched on
		When customer search for product productCode_OneSize_LowStock and navigates to pdp page
		And I click Add to Bag
		And navigated to my bag page
		Then message "Only 5 items left!" should be displayed

    #NLTA-823, NLCC-4327, NLCC-4328
	@NLCC-4327
		@NLCC-4328
		@newlook @Anonymous @condev_data
	Scenario Outline: 12 - Stock equal to or less than 5 units (Multi Size Item)
		And feature "feature.storefront.stock.urgency.messaging.cart" is switched on
		And customer search for low stock product productCode_MultiSize_LowStock with size <size>
		And The size picker contains the few items left message against each size
			| UK 8  | 2 |
			| UK 10 | 3 |
			| UK 12 | 1 |
			| UK 14 | 5 |
			| UK 16 | 4 |
		And navigated to my bag page
		Then message "<message>" should be displayed

		Examples:
			| size  | message            |
			| UK 8  | Only 2 items left! |
			| UK 10 | Only 3 items left! |
			| UK 12 | Only 1 item left!  |
			| UK 14 | Only 5 items left! |
			| UK 16 | Only 4 items left! |

   #NLCC-6726
	@newlook @Anonymous
	Scenario: 13 - Trolley Rocket - Standard/Express Delivery
		And feature "feature.storefront.bag.header.promos.progress.rocket" is switched on
		When customer navigates to pdp page of "368453263"
		And select size of the product
		And I click Add to Bag
		And navigated to my bag page
		Then trolley rocket exists
		And delivery threshold message "FREE Standard Delivery" is displayed
		And delivery qualified message "FREE Standard Delivery" is not displayed
		When I change the quantity to "Qty: 3" of a line item 1
		Then trolley rocket exists
		And delivery qualified message "FREE Standard Delivery" is displayed
		And delivery threshold message "FREE Express Delivery" is displayed
		When I change the quantity to "Qty: 6" of a line item 1
		Then trolley rocket exists
		And delivery threshold message "FREE Express Delivery" is not displayed
		And delivery qualified message "FREE Express Delivery" is displayed

   #NLCC-6726
	@newlook @Anonymous
	Scenario: 14 - Trolley Rocket - Standard/Express Delivery Boundary Threshold Value
		And feature "feature.storefront.bag.header.promos.progress.rocket" is switched on
		And remove all the added items
		When customer navigates to pdp page of "373012840"
		And select size of the product
		And I click Add to Bag
		And navigated to my bag page
		Then trolley rocket exists
		And delivery qualified message "FREE Standard Delivery" is displayed
		And delivery threshold message "FREE Express Delivery" is displayed
		And remove all the added items
		When customer navigates to pdp page of "366058343"
		And select size of the product
		And I click Add to Bag
		And navigated to my bag page
		Then trolley rocket exists
		And delivery qualified message "FREE Standard Delivery" is displayed
		And delivery threshold message "FREE Express Delivery" is displayed
		And remove all the added items
		When customer navigates to pdp page of "358495345"
		And select size of the product
		And I click Add to Bag
		And navigated to my bag page
		Then trolley rocket exists
		And delivery threshold message "FREE Express Delivery" is not displayed
		And delivery qualified message "FREE Express Delivery" is displayed
		And remove all the added items
		When customer navigates to pdp page of "362547145"
		And select size of the product
		And I click Add to Bag
		And navigated to my bag page
		Then trolley rocket exists
		And delivery threshold message "FREE Express Delivery" is not displayed
		And delivery qualified message "FREE Express Delivery" is displayed

	#NLCC-6726
	@newlook @Anonymous
	Scenario: 15 - Trolley Rocket - Standard/Express Delivery - Flag Off
		And feature "feature.storefront.bag.header.promos.progress.rocket" is switched off
		When customer navigates to pdp page of "368453263"
		And select size of the product
		And I click Add to Bag
		And navigated to my bag page
		Then trolley rocket doesn't exist
		And delivery threshold message "FREE Standard Delivery" is not displayed
		And delivery qualified message "FREE Standard Delivery" is not displayed

		@oms_data
		Scenario:16 product error message OMS
    #Given i navigate to "Newlook" home page with locale fr
		And I click on the locale selector in the header
		And I select a different country country_France using the drop down
		And customer search for product productCode_France_OMS with size UK 8
		And navigated to my bag page
		When change delivery country to Royaume-Uni in my bag page
		Then item Pink Feather Fascinator should show error Not for delivery in UK!!!

		#NLTA-823, NLCC-4327
	  @newlook @Anonymous @oms_data
	  Scenario: 17 - Stock equal to or less than 5 units (Single Size Item) OMS
		And feature "feature.storefront.stock.urgency.messaging.pdp" is switched on
		When customer search for product productCode_OneSize_LowStock_3Left_OMS and navigates to pdp page
		And I click Add to Bag
		And navigated to my bag page
		Then message "Only 3 items left!" should be displayed

    #NLTA-823, NLCC-4327, NLCC-4328
	  @NLCC-4327
		@NLCC-4328
		@newlook @Anonymous @oms_data
	  Scenario Outline: 18 - Stock equal to or less than 5 units (Multi Size Item) OMS
		And feature "feature.storefront.stock.urgency.messaging.cart" is switched on
		  And I set 3596569 as lowstock with minus 1 minutes with Store 2 and Dc 0 quantity
		  And I set 3596570 as lowstock with minus 1 minutes with Store 2 and Dc 1 quantity
		  And I set 3596571 as lowstock with minus 1 minutes with Store 1 and Dc 0 quantity
		  And I set 3596572 as lowstock with minus 1 minutes with Store 3 and Dc 2 quantity
		  And I set 3596573 as lowstock with minus 1 minutes with Store 2 and Dc 2 quantity
		  And i open hac page
		  And i provide valid username and password and click
		  And i have cleared the Cache from Hac
		  And i open newLook page
		And customer search for low stock product productCode_MultiSize_LowStock with size <size>
		And The size picker contains the few items left message against each size
			| UK 8  | 2 |
			| UK 10 | 3 |
			| UK 12 | 1 |
			| UK 14 | 5 |
			| UK 16 | 4 |
		And navigated to my bag page
		Then message "<message>" should be displayed
		  Examples:
			  | size  | message            |
			  | UK 8  | Only 2 items left! |
			  | UK 10 | Only 3 items left! |
			  | UK 12 | Only 1 item left!  |
			  | UK 14 | Only 5 items left! |
			  | UK 16 | Only 4 items left! |

   #NLCC-7675 is testing NLCC-7599
	@condev_data
	Scenario:19 Secure Checkout CTA and Have a promo code dropdown displayed to the right-hand side of the bag page for Guest User -- New Design
		And feature "feature.storefront.desktop.sticky.cart.cta" is switched on
		And customer search for product productCode_stock with size 12L32
		When navigated to my bag page
		Then secure checkout CTA displayed
		And total amount displayed in sticky
		And delivery information displayed
		And have a promo code dropdown displayed
		And delivering to country displayed
		And "guest" user can navigate to checkout quicker

   #NLCC-7675 is testing NLCC-7599
	@condev_data
	Scenario:20 Invalid/Valid promo code applied on bag page for Guest User-- New Design
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
	Scenario:21 Secure Checkout CTA and Have a promo code dropdown displayed to the right-hand side of the bag page for Guest User OMS-- New Design
		And feature "feature.storefront.desktop.sticky.cart.cta" is switched on
		And customer search for product productCode_stock with size 8L34
		When navigated to my bag page
		Then secure checkout CTA displayed
		And total amount displayed in sticky
		And delivery information displayed
		And have a promo code dropdown displayed
		And delivering to country displayed
		And "guest" user can navigate to checkout quicker

	@oms_data
	Scenario:22 Invalid/Valid promo code applied on bag page for Guest User OMS-- New Design
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
	Scenario Outline: 23 hard promise call fails with out of stock - oms
		And Select a product and navigate to payment page
		And continue as guest
		And I have checked out with the a delivery address "bob" and "rob" and postcode "EE1 1EE"
		And pays by <payment type> <card type> <card number>
		Then I am taken to the full cart page
		And out of stock message is displayed on cart page
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |
