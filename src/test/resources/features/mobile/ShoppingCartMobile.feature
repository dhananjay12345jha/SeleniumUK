@AnonymousMobile
Feature: Shopping cart mobile

  Background:
    Given i navigate to "Newlook" home page

  Scenario Outline:8 Change country location in checkout page display 'collection Country and change country warning' message
	 And feature "feature.storefront.brexitBanner.enabled" is switched off
	 When user provides required details to create new account on mobile
    And I click on the locale selector in the header
    And I select a different country country_Ireland using the drop down
    And Select a product and navigate to payment page on mobile
    And select collection button
    And user changes the collection country from preselected to country "<country>"
    Then you should see appropriate message for changed "<messagePart1>" "<country>" "<messagePart2>"

    Examples:
      | country | messagePart1                          | messagePart2           |
      | Italy   | If you change your delivery country to| Please review your bag |

  Scenario:9 Change in collection country to the non-collection country within ROW re-directs user to delivery page in checkout
    When user provides required details to create new account on mobile
    And I click on the locale selector in the header
    And I select a different country country_Ireland using the drop down
    And Select a product and navigate to payment page on mobile
    And select collection button
    And user should be able to change the collection country to "Italy"
    Then My Bag page should be displayed
    When click secure checkout button
    Then The user should be redirected to home delivery method

  Scenario: 10 Changing country from ROW(not ROI) to ROI redirect customer to bag page and customer is shown home delivery and collection options in checkout
    When user provides required details to create new account on mobile
    And I click on the locale selector in the header
    And I select a different country country_Malta using the drop down
    And Select a product and navigate to payment page on mobile
    When change the "Republic of Ireland" in the delivery country address form the dropdown of checkout page
    And I click on apply change country button
    Then My Bag page should be displayed
    When click secure checkout button
    Then Delivery option should be displayed
    And  Collection option should be displayed

	@OMSF-997 @OMSF-1009 @oms_flow
	Scenario: 14 AC1,AC2,AC3 Shopping Cart has single size product with Low Stock in MAO
		Given I search for a product with system property "MAO_LowStock_Mobile"
		And click on a product image or title
		And select size of the product "UK 4"
		And I click Add to Bag
		And click on the bag icon
		And I set 3342399 as instock in hybris with minus 14 minutes
		And change the size to "UK 5" of the product on mobile
		When I change the quantity to "Qty: 7" of a line item 1
		Then click on the bag icon
		And low stock message is displayed on cart page
		And quantity for a line item 1 is 3

	@newlook @oms_flow @OMSF-294
	Scenario:15 PDP - Standard Delivery message on Cart Page - AC1,AC2,AC3
		Given I search for a product with system property "MAO_MultiSize_sfs"
		And click on a product image or title
		And select size of the product "UK 10"
		And I click Add to Bag
		Given I search for a product with system property "MAO_SingleSize_sfs"
		And click on a product image or title
		And I click Add to Bag
		Given I search for a product with system property "MAO_LowStock_MultiSize_JEANS"
		And click on a product image or title
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
