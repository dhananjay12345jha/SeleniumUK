# Created by Gokhan.Ates

@UserDependentMobile @newlook
Feature: My Bag Signed In User mobile

	Background:
		Given i navigate to "Newlook" home page
		And login with username "myBagUserEmail" and password "password" on mobile
		And remove all the added items

	@condev_data
	Scenario:1 bag multi size - mobile
		And customer select product and navigate to my bag page on mobile
		When navigated to my bag page
		And I click edit on mobile for line item 1
		Then item Dark Blue Skinny Jeans should present with size 12L32 colour Blue Pattern

	@condev_data
	Scenario:2 bag one size - mobile
		And mobile customer search for product "productCode_OneSize" with size "one size"
		When navigated to my bag page
		Then cart header should be My Bag
		And I click edit on mobile for line item 1
		And item Pure Colour Dark Red Nail Polish should present with size Size: One size colour Dark Red

	@condev_data
	Scenario:3 bag one size + multi-size - mobile
		And mobile customer search for product "productCode_stock" with size "12L32"
		And mobile customer search for product "productCode_OneSize" with size "one size"
		When navigated to my bag page
		And I click edit on mobile for line item 1
		Then my bag should consist mobile
			| Dark Blue Skinny Jeans           |
			| 12L32                            |
			| Blue Pattern                     |
			| Pure Colour Dark Red Nail Polish |

	@condev_data
	Scenario:4 bag country - mobile
		And mobile customer search for product "productCode_stock" with size "12L32"
		When navigated to my bag page
		And I click edit on mobile for line item 1
		Then item Dark Blue Skinny Jeans should present with size 12L32 colour Blue Pattern
		And user should be able to change the delivery country from bag

   #NLCC-3964 AC1 - Brexit message appears on Delivery Country Selector (Bag)
	Scenario: 5 Brexit message appears on Delivery Country Selector (Bag)
		And mobile customer search for product "productCode_OneSize" with size "one size"
		When navigated to my bag page
		And click on Change country CTA in the bag
		Then user see the Brexit message displayed above the 'APPLY CHANGE' CTA

   #NLCC-5919 is testing NLCC-5776
	@NLCC-5776 @condev_data
	Scenario:6 Empty Bag Signin Prompt - mobile
		And feature "feature.storefront.cart.empty.login" is switched on
		And mobile customer search for product "productCode_stock" with size "12L32"
		And mobile customer search for product "productCode_OneSize" with size "one size"
		And customer is logged out
		When navigated to my bag page
		Then user should see sign in or create account prompt

	@oms_data
	Scenario:7 bag multi size OMS-Mobile
		And customer search for product productCode_stock with size 12L28
		When navigated to my bag page
		And I click edit on mobile for line item 1
		Then item Dark Blue Skinny Jeans should present with size 12L28 colour Blue Pattern

	@oms_data
	Scenario:8 bag one size OMS-Mobile
		And customer search for product productCode_OneSize with size one size
		When navigated to my bag page
		Then cart header should be My Bag
		And I click edit on mobile for line item 1
		And item Pure Colour Dark Red Nail Polish should present with size Size: One size colour Dark Red

	@oms_data
	Scenario:9 bag one size + multi-size OMS-Mobile
		And customer search for product productCode_stock with size 12L28
		And customer search for product productCode_OneSize with size one size
		When navigated to my bag page
		And I click edit on mobile for line item 1
		Then my bag should consist mobile
			| Dark Blue Skinny Jeans           |
			| 12L28                            |
			| Blue Pattern                     |
			| Pure Colour Dark Red Nail Polish |

	@NLCC-5776 	@oms_data
	Scenario:10 Empty Bag Signin Prompt OMS - Mobile
		And feature "feature.storefront.cart.empty.login" is switched on
		And customer search for product productCode_stock with size 12L28
		And customer search for product productCode_OneSize with size one size
		And customer is logged out
		When navigated to my bag page
		Then user should see sign in or create account prompt

	@oms_data
	Scenario:10 bag country OMS -Mobile
		And customer search for product productCode_stock with size 12L28
		When navigated to my bag page
		And I click edit on mobile for line item 1
		Then item Dark Blue Skinny Jeans should present with size 12L28 colour Blue Pattern
		And user should be able to change the delivery country from bag
