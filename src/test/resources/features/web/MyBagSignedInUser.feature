# 22/02/18

@UserDependent @newlook
Feature: My Bag Signed In User

	Background:
		Given i navigate to "Newlook" home page
		And login with username "myBagUserEmail" and password "password"
		And remove all the added items

	@condev_data
	Scenario:1 bag multi size
		And customer search for product productCode_stock with size 12L32
		When navigated to my bag page
		Then item Dark Blue Skinny Jeans should present with size 12L32 colour Blue Pattern

	@condev_data
	Scenario:2 bag one size
		And customer search for product productCode_OneSize with size one size
		When navigated to my bag page
		Then cart header should be My Bag
		And item Pure Colour Dark Red Nail Polish should present with size One size colour Dark Red
	@condev_data
	Scenario:3 bag one size + multi-size
		And customer search for product productCode_stock with size 12L32
		And customer search for product productCode_OneSize with size one size
		When navigated to my bag page
		Then my bag should consist
			| Dark Blue Skinny Jeans           |
			| 12L32                            |
			| Blue Pattern                     |
			| Pure Colour Dark Red Nail Polish |
			| Dark Red                         |
			| One size                         |

	@condev_data
	Scenario:4 bag country
		And customer search for product productCode_stock with size 12L32
		When navigated to my bag page
		Then item Dark Blue Skinny Jeans should present with size 12L32 colour Blue Pattern
		And user should be able to change the delivery country from bag
	@condev_data
	#NLCC-3964 AC1 - Brexit message appears on Delivery Country Selector (Bag)
	Scenario: 5 Brexit message appears on Delivery Country Selector (Bag)
		And customer search for product productCode_stock with size 12L32
		When navigated to my bag page
		And click on Change country CTA in the bag
		Then user see the Brexit message displayed above the 'APPLY CHANGE' CTA


   #NLCC-5919 is testing NLCC-5776
	@NLCC-5776 	@condev_data
	Scenario:6 Empty Bag Signin Prompt
		And feature "feature.storefront.cart.empty.login" is switched on
		And customer search for product productCode_stock with size 12L32
		And customer search for product productCode_OneSize with size one size
		And click on signout
		When navigated to my bag page
		Then user should see sign in or create account prompt


	@oms_data
	Scenario:7 bag multi size OMS
		And customer search for product productCode_stock with size 12L28
		When navigated to my bag page
		Then item Dark Blue Skinny Jeans should present with size 12L28 colour Blue Pattern

	@oms_data
	Scenario:8 bag one size OMS
		And customer search for product productCode_OneSize with size one size
		When navigated to my bag page
		Then cart header should be My Bag
		And item Pure Colour Dark Red Nail Polish should present with size One size colour Dark Red
	@oms_data
	Scenario:9 bag one size + multi-size
		And customer search for product productCode_stock with size 12L28
		And customer search for product productCode_OneSize with size one size
		When navigated to my bag page
		Then my bag should consist
			| Dark Blue Skinny Jeans           |
			| 12L28                            |
			| Blue Pattern                     |
			| Pure Colour Dark Red Nail Polish |
			| Dark Red                         |
			| One size                         |

	@oms_data
	Scenario:10 bag country OMS
		And customer search for product productCode_stock with size 12L28
		When navigated to my bag page
		Then item Dark Blue Skinny Jeans should present with size 12L28 colour Blue Pattern
		And user should be able to change the delivery country from bag
		
	@oms_data
	#NLCC-3964 AC1 - Brexit message appears on Delivery Country Selector (Bag) OMS
	Scenario: 11 Brexit message appears on Delivery Country Selector (Bag)
		And customer search for product productCode_stock with size 12L28
		When navigated to my bag page
		And click on Change country CTA in the bag
		Then user see the Brexit message displayed above the 'APPLY CHANGE' CTA


   #NLCC-5919 is testing NLCC-5776
	@NLCC-5776 	@oms_data
	Scenario:12 Empty Bag Signin Prompt OMS
		And feature "feature.storefront.cart.empty.login" is switched on
		And customer search for product productCode_stock with size 12L28
		And customer search for product productCode_OneSize with size one size
		And click on signout
		When navigated to my bag page
		Then user should see sign in or create account prompt

# @todo
#  Scenario Outline:6 As a Hard Logged In Customer, I want to be warned when my address cannot be delivered to by the current store, so that I can complete my purchase in the correct hybris store
#    And  customer search for product productCode_stock with size 12L32
#    And Select a product and navigate to payment page
#    And select DELIVERY as option
#    When change the "<deliveryCountry>" in the delivery country address form the dropdown of checkout page
#    And navigate to checkout and payments page
#    Then you should see appropirate message for changed "<messagePart1>" "<deliveryCountry>" "<messagePart2>"
#    Examples:
#      | country | deliveryCountry | messagePart1                               | messagePart2           |
#      | France  | Finland         | Some of your items may not be available in | Please review your bag |

#   @todo
#  Scenario:7 Multiple addresses
#    And  customer search for product productCode_stock with size 12L32
#    And Select a product and navigate to payment page
#    When select collection button and enter London in search field
#    Then for each collection point result in the list I am shown a summary of information, where available

# #Add the assertion and validate the page
#  @todo
#  Scenario:8 Change country for a collection from checkout page
#    And  customer search for product productCode_stock with size 12L32
##    And navigated to my bag page
##    When i click on checkout button
#    And Select a product and navigate to payment page
#    When select collection button
#    Then user should be able to change the delivery country
#
##Fix the collection add the test to checkout and payments scenarios
#    @todo
#  Scenario:9 As a Customer I want my last used delivery method to be pre-selected, so that I can move through checkout quickly
##    And  customer search for product productCode_stock with size 12L32
##    And navigated to my bag page
###    When i click on checkout button
##    And i click on delivery button
#    And Select a product and navigate to payment page
#    And select collection button and enter <london> in search field
#
##    And search for nearest collection location to: "london"
#    And fill the collection details
##    And click continue to payment button
#    And pays by card
#    When I go to checkout with the same account
#    Then "COLLECTION" is automatically selected
#
#      @todo
#  Scenario:10 As a +Customer I want my last used delivery method to be pre-selected, so that I can move through checkout quickly
#    And  customer search for product productCode_stock with size 12L32
#    And navigated to my bag page
#    And selects secure checkout
##    And chooses standard delivery
##    And select available delivery mode
##    When pays by card
#    And Customer make a purchase with card
#    Then order has been successful
#    When I go to checkout with the same account
#    Then "DELIVERY" is automatically selected
#
#    #need to check if there new functionallity is added
#    @todo
#  Scenario Outline:11 Add item to basket and checkout as logged in user for europe
#    And user changed delivery <country>
#    And Select a product and navigate to payment page
#    And Select delivery option and provide europe shipping details "<title>","<firstName>","<lastName>","<postCode>","<addressLine1>","<addressLine2>","<city>"
#    When Made payment with "<cardType>","<cardNumber>","<date>","<year>","<cvv>"
#    Then Order has been successful
#    Examples:
#      | country | title | firstName | lastName | postCode | addressLine1     | addressLine2 | city  | cardType         | cardNumber       | date | year | cvv |
#      | France  | Mr.   | euro      | user     | 75001    | Forum des Halles | Porte Berger | paris | American Express | 378282246310005  | 07   | 2019 | 123 |
#      | France  | Mr.   | euro      | user     | 75001    | Forum des Halles | Porte Berger | paris | Diner's Club     | 6011111111111117 | 07   | 2021 | 123 |
#
#      @todo
#  Scenario:12 page Alert
#    Given i navigate to "Newlook" home page with locale fr
#    And I'm on the bag page with product code 340365270
#    And I'm on the bag page with product code 340926949
#    When change delivery country to Royaume-Uni in my bag page
#    Then bag page should show an alert Some items can't be delivered to this country, when you checkout any unavailable items will be removed from your bag
#
#    @todo
#  Scenario:13 product error msg on multiple items in bag
#    Given i navigate to "Newlook" home page with locale fr
#    And I'm on the bag page with product code 340365270
#    And I'm on the bag page with product code 340926949
#    And I'm on the bag page with product code 370980913
#    When change delivery country to Royaume-Uni in my bag page
#    Then bag page should show an alert Some items can't be delivered to this country, when you checkout any unavailable items will be removed from your bag
#    And item Pink Feather Fascinator should show error Not for delivery in UK!!!
#    And item Cream Crochet Bucket Hat should show error Not for delivery in UK!!!
