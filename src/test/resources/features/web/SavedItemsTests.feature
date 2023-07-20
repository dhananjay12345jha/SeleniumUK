@newlook @Anonymous
Feature: Saved Items
  As a Customer,
  upon saving items and subsequently registering or logging in,
  I want the items to be added to my wish list,
  so the list I created is not lost.

  Background:
    Given i navigate to "Newlook" home page

  Scenario:1 anonymous customer has not added items to wish list
    When viewing the saved items side pane
    Then no item will be present


  @smokepack @todo
  Scenario:2 anonymous customer adds and then removes item from wish list
    And saves a item
    And viewing the saved item side pane
    And saved item will be present


  #ToDo need to write new logic
    When customer selects the saved item in side pane
    Then saved item is displayed in full
#
    When customer selects remove from wish list
    Then viewing the saved item side pane
    And no item will be present

  Scenario:3 anonymous customer deletes all items from their wish list
    And has added items to wish list from product search
    And viewing the saved item side pane
    When deleting the all items
    And no item will be present

  Scenario:4 logged in customer saves items to wish list
    And viewing the saved item side pane
    When deleting the all items
    And no item will be present
    And has added 3 items to wish list from product search
    When viewing the saved items side pane
    Then 3 saved items will be present

  Scenario:5 anonymous customer adds the same item in two sizes to wish list
    And search for item with multiple sizes
    And click on a product image or title
    And select first available size of the product
    And click save item button
    And select second available size of the product
    And click save item button
    When viewing the saved items side pane
    Then 2 saved items will be present

  Scenario:6 anonymous customer adds the same item in two colours to wish list
    And search multi colour product
    And click on a product image or title
    And select first colour of the product
    And select first available size of the product
    And click save item button
    And select second colour of the product
    And select second available size of the product
    And click save item button
    When viewing the saved items side pane
    Then 2 saved items will be present

  Scenario:7 anonymous customer saves items from product listings and then registers
    And has quickly added items to wish list from product listings
    When viewing the saved items side pane
    Then 3 saved items will be present
    When customer selects Register
    Then customer is taken to the sign in page
    But with create an account view
    When customer completed registration
    Then saved items page is displayed
    And saved items will be present

  Scenario:8 anonymous customer with an account saves items from product listings
     And customer completes registration form
    And customer is logged out
    And has added a item to wish list from product listings quick view
    When viewing saved items on full page
    Then saved item will be present
    When viewing the saved items side pane
    When customer selects Sign In
    Then customer is taken to the sign in page
    But with i've got an account view
    When customer successfully signs in
    Then saved items page is displayed
    And saved item will be present

  Scenario:9 anonymous customer with account saves items from product search and logs in
   And customer completes registration form
    And customer is logged out
    And has added items to wish list from product search
    And viewing the saved items side pane
    And 3 saved items will be present
    When customer selects Sign In
    Then customer is taken to the sign in page
    But with i've got an account view
    When customer successfully signs in
    Then saved items page is displayed
    And 3 saved items will be present

  Scenario:10 anonymous customer creates wish list that registers to keep their saved items
    And has added items to wish list from product search
    And viewing the saved items side pane
    And 3 saved items will be present

    When customer selects Register
    Then customer is taken to the sign in page
    But with create an account view
    When customer completed registration

    Then saved items page is displayed
    And 3 saved items will be present

  Scenario:11 Customer saved item from my bag page to saved items and an alert appears when moving to save items from bag
    And user provides required details to create new account
    When a product is added to saved items from my bag page
    Then an alert appears with the following message "Item has been moved to your Saved Items"
    And viewing saved items on full page
    Then product should be displayed in saved items

  Scenario:12 Add to saved items heart message - PDP
    When customer search for product productCode_stock and navigates to pdp page
    And customer hovers on saved icon button
    Then add to saved items message appears

  Scenario:13 Remove from saved items heart message - PDP
    When customer search for product productCode_stock and navigates to pdp page
    And click save item button
    And customer hovers on saved icon button
    Then remove from saved items message appears
#    @NLDS-591 Then add to saved items message does not appear

  Scenario:14 Add to saved items heart message - PLP
    When I search for the product with "jeans"
    And customer hovers on saved icon button
    Then add to saved items message appears

  Scenario:15 Remove to saved items heart message - PLP
    And saves a item
    And customer hovers on saved icon button
    Then remove from saved items message appears
    #    @NLDS-591 Then add to saved items message does not appear

	@oms_flow @OMSF-293
	Scenario: 16 Standard Delivery message on saved items page - 2 item - OMS
		When I search for the product MAO_MultiSize_sfs and navigate to pdp page
		And select size of the product "UK 10"
		And click save item button
		When I search for the product MAO_MultiSize_sfs_dc and navigate to pdp page
		And select size of the product "UK 16"
		And click save item button
		When viewing the saved items side pane
		Then 2 saved items will be present
		But I navigate to saved items page
		Then standard delivery message is displayed on pdp page for products
			| MAO_MultiSize_sfs |
		And stock location is store in the datalayer product object
			| productCode       | sizeCode |
			| MAO_MultiSize_sfs | 10       |
		And stock location is dc in the datalayer product object
			| productCode          | sizeCode |
			| MAO_MultiSize_sfs_dc | 16       |

	@oms_flow @OMSF-293
	Scenario: 17 Standard Delivery message on saved items page - 1 items - OMS
		When I search for the product MAO_MultiSize_sfs and navigate to pdp page
		And select size of the product "UK 10"
		And click save item button
		And select size of the product "UK 14"
		And click save item button
		When viewing the saved items side pane
		Then 2 saved items will be present
		But I navigate to saved items page
		Then standard delivery message is displayed on pdp page for products
			| MAO_MultiSize_sfs |
		And stock location is store in the datalayer product object
			| productCode       | sizeCode |
			| MAO_MultiSize_sfs | 10       |

	@oms_flow @OMSF-293
	Scenario: 18 Standard Delivery message on saved items page - 1 item - Single size AC1 - OMS
		When I search for the product MAO_SingleSize_sfs and navigate to pdp page
		And click save item button
		When I search for the product MAO_SingleSize_sfs_dc and navigate to pdp page
		And click save item button
		When viewing the saved items side pane
		Then 2 saved items will be present
		But I navigate to saved items page
		Then standard delivery message is displayed on pdp page for products
			| MAO_SingleSize_sfs |
		And stock location is store in the datalayer product object
			| productCode        | sizeCode |
			| MAO_SingleSize_sfs | 99       |
		And stock location is dc in the datalayer product object
			| productCode           | sizeCode |
			| MAO_SingleSize_sfs_dc | 99       |

	@oms_flow @OMSF-293
	Scenario: 19 Standard Delivery message on saved items page - 1 item - Multisize Multicolor AC3 - OMS
		When I search for the product productCode_MultiSize_multicolor_OutOfStock and navigate to pdp page
		And select size of the product "UK 8"
		And click save item button
		When viewing the saved items side pane
		Then 1 saved items will be present
		But I navigate to saved items page
		Then standard delivery message is displayed on pdp page for products
			| productCode_MultiSize_multicolor_OutOfStock |
		And stock location is store in the datalayer product object
			| productCode                                 | sizeCode |
			| productCode_MultiSize_multicolor_OutOfStock | 8       |

	@oms_flow @OMSF-293
	Scenario: 20 Standard Delivery message on saved items page - 1 item - AC2 - OMS
		When I search for the product MAO_MultiSize_sfs and navigate to pdp page
		And click save item button
		When viewing the saved items side pane
		Then 1 saved items will be present
		And select size of the product "UK 16"
		But I navigate to saved items page
		When customer change size of the product
		Then standard delivery message is displayed on pdp page for products
			| MAO_MultiSize_sfs |
		And stock location is store in the datalayer product object
			| productCode       | sizeCode |
			| MAO_MultiSize_sfs | 10       |


