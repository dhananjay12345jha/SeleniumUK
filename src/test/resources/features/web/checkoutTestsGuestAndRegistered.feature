@UserDependent
Feature: checkout page tests

   Background:
		Given i navigate to "Newlook" home page

	@newlook @Anonymous @oms_flow @OMSF-296
	Scenario: 0 Guest - Analytics - stock location - sizeData - 1 product - OMS
		When I search for the product productCode_analytics_oms2 and navigate to pdp page
		And select size of the product "UK 14"
		And I click Add to Bag
		And navigate to bag page and secure checkout
		And continue as guest
		And I have checked out with the a delivery address "bob" and "rob" and postcode "DE6 1EE"
		And stock location is store in the datalayer product object
			| productCode                | sizeCode |
			| productCode_analytics_oms2 | 14       |
		And stock location is store in the datalayer sizeData object
			| productCode                | sizeCode |
			| productCode_analytics_oms2 | 14       |

	@newlook @Anonymous @oms_flow @OMSF-296
	Scenario: 1 Registered - Analytics - stock location - sizeData - 2 products - OMS
		And user provides required details to create new account
		When I search for the product productCode_analytics_oms2 and navigate to pdp page
		And select size of the product "UK 14"
		And I click Add to Bag
		When I search for the product productCode_analytics_oms6 and navigate to pdp page
		And select size of the product "UK 14"
		And I click Add to Bag
		And navigate to bag page and secure checkout
		And I have checked out with the a delivery address "bob" and "rob" and postcode "DE6 1EE"
		And stock location is dc in the datalayer product object
			| productCode                | sizeCode |
			| productCode_analytics_oms6 | 14       |
		And stock location is dc in the datalayer sizeData object
			| productCode                | sizeCode |
			| productCode_analytics_oms6 | 14       |
		And stock location is store in the datalayer product object
			| productCode                | sizeCode |
			| productCode_analytics_oms2 | 14       |
		And stock location is store in the datalayer sizeData object
			| productCode                | sizeCode |
			| productCode_analytics_oms2 | 14       |

	@newlook @Anonymous @oms_flow @OMSF-296
	Scenario: 2 Guest - Analytics - stock location - sizeData - 4 products - OMS
		When I search for the product productCode_analytics_oms1 and navigate to pdp page
		And I click Add to Bag
		When I search for the product productCode_analytics_oms2 and navigate to pdp page
		And select size of the product "UK 14"
		And I click Add to Bag
		When I search for the product productCode_analytics_oms5 and navigate to pdp page
		And select size of the product "UK 10"
		And I click Add to Bag
		When I search for the product productCode_analytics_oms4 and navigate to pdp page
		And I click Add to Bag
		And navigate to bag page and secure checkout
		And continue as guest
		And I have checked out with the a delivery address "bob" and "rob" and postcode "DE6 1EE"
		And stock location is dc in the datalayer product object
			| productCode                | sizeCode |
			| productCode_analytics_oms1 | 99       |
			| productCode_analytics_oms4 | 99       |
		And stock location is store in the datalayer product object
			| productCode                | sizeCode |
			| productCode_analytics_oms2 | 14       |
			| productCode_analytics_oms5 | 10       |
		And stock location is dc in the datalayer sizeData object
			| productCode                | sizeCode |
			| productCode_analytics_oms1 | 99       |
			| productCode_analytics_oms4 | 99       |
		And stock location is store in the datalayer sizeData object
			| productCode                | sizeCode |
			| productCode_analytics_oms2 | 14       |
			| productCode_analytics_oms5 | 10       |





