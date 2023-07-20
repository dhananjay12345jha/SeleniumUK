@Anonymous
Feature: Shopping cart

  Background:
    Given i navigate to "Newlook" home page

  Scenario:1 cart merge as unknown user and then logged in later
    When search and add a product to bag 376010217
    And click secure checkout button
    And login as already existing user
    Then the cart I built up as a guest before I signed up is maintained, and merged with the cart stored against my account

	  @condev_data
  Scenario Outline:2 The quantity picker will be re-set based on the available stock figure for the size variant I have chosen
    When search low stock product
    And click on a product image or title
    And select size of the product "34DD"
    And I click Add to Bag
    And click on the bag icon
    And I change the "<item_count>" of a line item 1
    And select size of the product "36C" from my bag page
    Then the maximum quantity option as listed in the Quantity picker will be re-set and will be less than "<item_count>"
    Examples:
      | item_count |
      | 10         |

  Scenario:3 The line item I am interested does not have an alternative size variant in stock ,my cart remains unchanged
    When search one size product
    And click on a product image or title
    And select size of the product
    And I click Add to Bag
    And click on the bag icon
    Then I am unable to select an alternative size from the picker

  Scenario Outline:4 Add item to basket and checkout as logged in user for rest of the world
	 And feature "feature.pci.microform.enabled.row-site" is switched off
	 And I click on the locale selector in the header
    And I select a different country <country> using the drop down
    And user provides required details to create new account
    And I checkout a product
    And fill the shipping details for rest of the world "<country>","<title>","<firstName>","<lastName>","<postCode>","<addressLine1>","<addressLine2>","<city>","<state>","<phoneNumber>"
    And select debit or credit card tab
    And fill the card details "<cardType>","<cardNumber>","<date>","<year>","<cvv>"
    And click checkout
    Then order has been successful
	  Examples:
      | country   | title | firstName | lastName | postCode | addressLine1 | addressLine2 | city    | state   | phoneNumber  | cardType         | cardNumber      | date | year | cvv |
#      | Australia | Mr.   | joe       | bloggs   | 2600     | avenue       | city hall    | newyork | Alabama | +12345672189 | Diner's Club     | 6011111111111117 | 07   | 2021 | 123 |
      | country_Australia | Mr.   | joe       | bloggs   | 2600     | avenue       | city hall    | newyork | Alabama | +12345672189 | American Express | 378282246310005 | 07   | 2027 | 1234 |


	Scenario Outline:5 Add item to basket and checkout as logged in user for rest of the world - PCI microforms
		And feature "feature.pci.microform.enabled.row-site" is switched on
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		And user provides required details to create new account
		And I checkout a product
		And fill the shipping details for rest of the world "<country>","<title>","<firstName>","<lastName>","<postCode>","<addressLine1>","<addressLine2>","<city>","<state>","<phoneNumber>"
		And select debit or credit card tab
		And fill the card details "<cardType>","<cardNumber>","<date>","<year>","<cvv>"
		And click checkout
		Then order has been successful
		Examples:
			| country   | title | firstName | lastName | postCode | addressLine1 | addressLine2 | city    | state   | phoneNumber  | cardType         | cardNumber      | date | year | cvv |
#      | Australia | Mr.   | joe       | bloggs   | 2600     | avenue       | city hall    | newyork | Alabama | +12345672189 | Diner's Club     | 6011111111111117 | 07   | 2021 | 123 |
			| country_Australia | Mr.   | joe       | bloggs   | 2600     | avenue       | city hall    | newyork | Alabama | +12345672189 | American Express | 378282246310005 | 07   | 2027 | 1234 |


	Scenario Outline:6 Add item to basket and checkout as logged in user for collection for GERMANY
	 And feature "feature.pci.microform.enabled.de-site" is switched off
    And I click on the locale selector in the header
    And I select a different country <country> using the drop down
    And user provides required details to create new account
    And I checkout a product
    And select collection button
    And search for nearest collection location to: "<place>"
    And click continue to payment button
    And fill the billing address details for checkout "<postCode>" , "<city>"
    And fill the card details "<cardType>","<cardNumber>","<date>","<year>","<cvv>"
    And click checkout
    Then order has been successful

    Examples:
      | country | postCode | city   | cardType         | cardNumber      | date | year | cvv | place  |
#      | Germany | 10117    | berlin | Diner's Club     | 36148900647913   | 07   | 2021 | 123 | berlin |
      | country_Germany | 10117    | berlin | American Express | 378282246310005 | 07   | 2027 | 1234 | berlin |

	Scenario Outline:7 Add item to basket and checkout as logged in user for collection for GERMANY - PCI microforms
		And feature "feature.pci.microform.enabled.de-site" is switched on
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		And user provides required details to create new account
		And I checkout a product
		And select collection button
		And search for nearest collection location to: "<place>"
		And click continue to payment button
		And fill the billing address details for checkout "<postCode>" , "<city>"
		And fill the card details "<cardType>","<cardNumber>","<date>","<year>","<cvv>"
		And click checkout
		Then order has been successful

		Examples:
			| country | postCode | city   | cardType         | cardNumber      | date | year | cvv | place  |
#      | Germany | 10117    | berlin | Diner's Club     | 36148900647913   | 07   | 2021 | 123 | berlin |
			| country_Germany | 10117    | berlin | American Express | 378282246310005 | 07   | 2027 | 1234 | berlin |

	Scenario Outline:8 Add item to basket and checkout as guest user for collection for GERMANY
	 And feature "feature.pci.microform.enabled.de-site" is switched off
    And I click on the locale selector in the header
    And I select a different country <country> using the drop down
    And I checkout a product
    And continue as guest
    And search for nearest collection location to: "<place>"
    And fill the collection details
    And click continue to payment button for guest
    And fill the billing address details for checkout "<postCode>" , "<city>"
    And fill the card details "<cardType>","<cardNumber>","<date>","<year>","<cvv>"
    And click checkout
    Then order has been successful

    Examples:
      | country | postCode | city   | cardType         | cardNumber      | date | year | cvv | place  |
#      | Germany | 10117    | berlin | Diner's Club     | 36700102000000   | 07   | 2021 | 123 | berlin |
      | country_Germany | 10117    | berlin | American Express | 378282246310005 | 07   | 2027 | 1234 | berlin |

	Scenario Outline:9 Add item to basket and checkout as guest user for collection for GERMANY -- PCI microforms
		And feature "feature.pci.microform.enabled.de-site" is switched on
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		And I checkout a product
		And continue as guest
		And search for nearest collection location to: "<place>"
		And fill the collection details
		And click continue to payment button for guest
		And fill the billing address details for checkout "<postCode>" , "<city>"
		And fill the card details "<cardType>","<cardNumber>","<date>","<year>","<cvv>"
		And click checkout
		Then order has been successful

		Examples:
			| country | postCode | city   | cardType         | cardNumber      | date | year | cvv | place  |
#      | Germany | 10117    | berlin | Diner's Club     | 36700102000000   | 07   | 2021 | 123 | berlin |
			| country_Germany | 10117    | berlin | American Express | 378282246310005 | 07   | 2027 | 1234 | berlin |

  Scenario:10 Change location link from checkout page should navigate back to find collection point
    When user provides required details to create new account
    And search and add a product to bag
    And I click on view bag and checkout button on pdp page
    And click on checkout button from my bag page
    And select collection button
    And click on change delivery country
    And should be able to change collection "Germany"
    Then My Bag page should be displayed

#  @ignore
#  Scenario Outline:8 Add item to basket and checkout as logged in user using paypal for french site
#    And I click on the locale selector in the header
#    And I select a different country "<country>" using the drop down
#    And user provides required details to create new account
#    And I checkout a product
#    And i click on delivery button
#    And fill the shipping details for europe "<title>","<firstName>","<lastName>","<postCode>","<addressLine1>","<addressLine2>","<city>"
#    And click pay using paypal for french site
#    And switch to paypal window
#    And submit by clicking paynow from paypal window
#    Then order has been successful
#
#    Examples:
#      | country | title | firstName | lastName | postCode | addressLine1     | addressLine2 | city  |
#      | France  | Mr.   | euro      | user     | 75045    | Forum des Halles | Porte Berger | paris |

  Scenario Outline:11 Change country location in checkout page display 'collection Country and change country warning' message
	 And feature "feature.storefront.brexitBanner.enabled" is switched off
	 When I click on the locale selector in the header
    And I select a different country country_Ireland using the drop down
    And I checkout a product
    And login as already existing user
    And click secure checkout button
    And select collection button
    And user changes the collection country from preselected to country "<country>"
    Then you should see appropriate message for changed "<messagePart1>" "<country>" "<messagePart2>"

    Examples:
      | country | messagePart1                               | messagePart2           |
      | Italy  | If you change your delivery country to| Please review your bag |

  Scenario:12 Change in collection country to the non-collection country within ROW re-directs user to delivery page in checkout
    When I click on the locale selector in the header
    And I select a different country country_Ireland using the drop down
    And I checkout a product
    And login as already existing user
    And click secure checkout button
    And select collection button
    And user should be able to change the collection country to "Italy"
    Then My Bag page should be displayed
    When click secure checkout button
    Then The user should be redirected to home delivery method

  Scenario: 13 Changing country from ROW(not ROI) to ROI redirect customer to bag page and customer is shown home delivery and collection options in checkout
    When I click on the locale selector in the header
    And I select a different country country_Malta using the drop down
    And I checkout a product
    And login as already existing user
    And click secure checkout button
    When change the "Republic of Ireland" in the delivery country address form the dropdown of checkout page
    And I click on apply change country button
    Then My Bag page should be displayed
    When click secure checkout button
    Then Delivery option should be displayed
    And  Collection option should be displayed

	@OMSF-997 @oms_flow @OMSF-1106 
	Scenario: 14 AC1,AC2 Shopping Cart Has One or More SKU items with Low Stock in MAO
		Given customer search for product MAO_LowStock_MultiSize with text UK 16 in size dropdown
		And I click Add to Bag
		And click on the bag icon
		And I set 3617719 as instock in hybris with minus 14 minutes
		And change the "UK 18" of the product
		When I change the quantity to "Qty: 6" of a line item 1
		And click on the bag icon
		Then low stock message is displayed on cart page
		And quantity for a line item 1 is 3

	@newlook @Anonymous @oms_flow @OMSF-294
	Scenario:15 Standard Delivery message on Cart Page - AC1,AC2
		When I search for the product MAO_MultiSize_sfs and navigate to pdp page
		And select size of the product "UK 10"
		And I click Add to Bag
		When I search for the product MAO_SingleSize_sfs and navigate to pdp page
		And I click Add to Bag
		When I search for the product MAO_LowStock_MultiSize_JEANS and navigate to pdp page
		And select size of the product "28L32"
		And I click Add to Bag
		And click on the bag icon
		Then standard delivery message is displayed on pdp page for products
			| MAO_MultiSize_sfs |
			| MAO_SingleSize_sfs |
			| MAO_LowStock_MultiSize_JEANS |
		And stock location is store in the datalayer product object
			| productCode       | sizeCode |
			| MAO_MultiSize_sfs | 10       |
			| MAO_SingleSize_sfs           | 99       |
			| MAO_LowStock_MultiSize_JEANS | 28L32    |
		And stock location is store in the datalayer sizeData object
			| productCode       | sizeCode |
			| MAO_MultiSize_sfs | 10       |
			| MAO_SingleSize_sfs           | 99       |
			| MAO_LowStock_MultiSize_JEANS | 28L32    |
		And group delivery message displayed

	@newlook @Anonymous @oms_flow @OMSF-294
	Scenario:16 Standard Delivery message on Cart Page - AC3
		When I search for the product MAO_MultiSize_sfs and navigate to pdp page
		And select size of the product "UK 10"
		And I click Add to Bag
		When I search for the product MAO_InStock_White and navigate to pdp page
		And select size of the product "XS"
		And I click Add to Bag
		And click on the bag icon
		Then standard delivery message is displayed on pdp page for products
			| MAO_MultiSize_sfs |
		And stock location is store in the datalayer product object
			| productCode       | sizeCode |
			| MAO_MultiSize_sfs | 10       |
		And group delivery message displayed

	@oms_data
	Scenario Outline:17 The quantity picker will be re-set based on the available stock figure for the size variant I have chosen OMS
		When search low stock product
		And click on a product image or title
		And select size of the product "36C"
		And I click Add to Bag
		And click on the bag icon
		And I change the "<item_count>" of a line item 1
		And select size of the product "34DD" from my bag page
		Then the maximum quantity option as listed in the Quantity picker will be re-set and will be less than "<item_count>"
		Examples:
			| item_count |
			| 10         |




