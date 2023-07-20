@newlook @AnonymousMobile
Feature: My Bag Anonymous user

	Background:
		Given i navigate to "Newlook" home page

	@condev_data
	Scenario:1 Requested quantity low stock - mobile
		And customer search for low stock product with size "36D - Only 2 items left!" and add product to my page on mobile
		When navigated to my bag page
		And I click edit on mobile for line item 1
		Then I am only shown the quantity 2 in the picker on mobile

	Scenario:2 As a Customer, I want to increase the quantities of line items in my cart, so that I can control the volume of products I am going to buy - mobile
		And customer select product and navigate to my bag page on mobile
		And I click edit on mobile for line item 1
		When I change the quantity to "Qty: 2" of a line item 1
		Then the additional products will be committed to my cart with the same quantity: "2" on mobile

	Scenario:3 Select color from swatch panel - mobile
		And customer search for multi coloured product and navigate to my bag page on mobile
		And I click edit on mobile for line item 1
		When customer change the product colour
		Then product code will change with the colour change

	Scenario:6 As a Customer I want to view collection location results based on my search in ascending order - mobile
		And Select a product and navigate to payment page on mobile
		And provide guest user details
		And search for nearest store with "london"
		Then I am shown the distance of the store from my search location in both miles and kilometres


	Scenario Outline:7 As a Customer on clicking Map and Opening times' link more information on the collection location is displayed - mobile
		And Select a product and navigate to payment page on mobile
		And provide guest user details
		And search for nearest store with "<area>"
		When I select the Collection Location name or 'Map and Opening times' link
		Then more information on the collection location is displayed
		Examples:
			| area     |
			| KT1      |
			| london   |
			| SN25 2GE |

	@MobileSmokePack
	Scenario:8 On searching for store locator from checkout page each collection point result is listed as a summary information - mobile
		And Select a product and navigate to payment page on mobile
		And provide guest user details
		And search for nearest store with "london"
		Then for each collection point result in the list I am shown a summary of information, where available

	Scenario Outline:9 Address not recognised - mobile
		And Select a product and navigate to payment page on mobile
		And provide guest user details
		And search for nearest store with invalid "<postcode>"
		Then an error message is displayed
		Examples:
			| postcode    |
			| belgium     |
			| 43453434533 |
			| £$%£$%£$%   |

  @condev_data
	Scenario:10 product error message - mobile
		And I click on the locale selector in the header
		And I select a different country country_France using the drop down
		And mobile customer search for product "productCode_France" with size "one size"
		And navigated to my bag page
		When change delivery country to Royaume-Uni in my bag page
		Then item Pink Feather Fascinator should show error Not for delivery in UK!!!

	#NLTA-823, NLCC-4328, NLCC-4327
	@NLCC-4328
	@NLCC-4327
	@condev_data
	Scenario: 11 - Stock equal to or less than 5 units (Single Size Item) - mobile
		And feature "feature.storefront.stock.urgency.messaging.pdp" is switched on
		When customer search for product productCode_OneSize_LowStock and navigates to pdp page
		And The 5 items left message is visible on the PDP
		And I click Add to Bag
		And navigated to my bag page
		Then message "Only 5 items left!" should be displayed

   #NLTA-823, NLCC-4327, NLCC-4328
	@NLCC-4327
		@NLCC-4328
		@condev_data
	Scenario Outline: 12 - Stock equal to or less than 5 units (Multi Size Item) - mobile
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
	Scenario: 13 - Trolley Rocket - Standard/Express Delivery - mobile
		And feature "feature.storefront.bag.header.promos.progress.rocket" is switched on
		When customer navigates to pdp page of "368453263"
		And select size of the product
		And I click Add to Bag
		And navigated to my bag page
		Then trolley rocket exists
		And delivery threshold message "FREE Standard Delivery" is displayed
		And delivery qualified message "FREE Standard Delivery" is not displayed
		And I click edit on mobile for line item 1
		When I change the quantity to "Qty: 3" of a line item 1
		Then trolley rocket exists
		And delivery qualified message "FREE Standard Delivery" is displayed
		And delivery threshold message "FREE Express Delivery" is displayed
		And I click edit on mobile for line item 1
		When I change the quantity to "Qty: 6" of a line item 1
		Then trolley rocket exists
		And delivery threshold message "FREE Express Delivery" is not displayed
		And delivery qualified message "FREE Express Delivery" is displayed

   #NLCC-6726
	@newlook @Anonymous
	Scenario: 14 - Trolley Rocket - Standard/Express Delivery Boundary Threshold Value - mobile
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
	Scenario: 15 - Trolley Rocket - Standard/Express Delivery - Flag Off - Mobile
		And feature "feature.storefront.bag.header.promos.progress.rocket" is switched off
		When customer navigates to pdp page of "368453263"
		And select size of the product
		And I click Add to Bag
		And navigated to my bag page
		Then trolley rocket doesn't exist
		And delivery threshold message "FREE Standard Delivery" is not displayed
		And delivery qualified message "FREE Standard Delivery" is not displayed

	#NLTA-823, NLCC-4328, NLCC-4327
	@NLCC-4328
	@NLCC-4327
	@oms_data
	Scenario: 16 - Stock equal to or less than 5 units (Single Size Item) OMS - mobile
		And feature "feature.storefront.stock.urgency.messaging.pdp" is switched on
		When customer search for product productCode_OneSize_LowStock_3Left_OMS and navigates to pdp page
		And The 3 items left message is visible on the PDP
		And I click Add to Bag
		And navigated to my bag page
		Then message "Only 3 items left!" should be displayed


  @oms_data
	Scenario:18 product error message OMS - mobile
		And I click on the locale selector in the header
		And I select a different country country_France using the drop down
		And mobile customer search for product "productCode_France_OMS" with size "UK 8"
		And navigated to my bag page
		When change delivery country to Royaume-Uni in my bag page
		Then item Pink Feather Fascinator should show error Not for delivery in UK!!!

	@oms_data
	Scenario: 19 Requested quantity low stock OMS - mobile
		And customer search for low stock product with size "12L30 - Only 3 items left!" and add product to my page on oms mobile
		When navigated to my bag page
		And I click edit on mobile for line item 1
		Then I am only shown the quantity 3 in the picker on mobile

	@oms_flow @OMSF-337
	Scenario: 20 hard promise call fails with out of stock - oms - mobile
		Given Select a product and navigate to payment page on mobile
		And provide guest user details
		And I have checked out with the a delivery address "bob" and "rob" and postcode "EE1 1EE"
		When pays by paymentType_Paypal - -
		Then out of stock message is displayed on cart page
