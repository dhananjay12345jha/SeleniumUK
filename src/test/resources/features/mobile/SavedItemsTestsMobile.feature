@newlook @NewUsersMobile

Feature: Saved Items - Mobile
  As a Customer,
  upon saving items and subsequently registering or logging in,
  I want the items to be added to my wish list,
  so the list I created is not lost.

  Background:
    Given i navigate to "Newlook" home page

  Scenario:1 anonymous customer has not added items to wish list
    When viewing the saved items side pane
    Then no item will be present

  Scenario:2 anonymous customer deletes all items from their wish list
    And has added items to wish list from product search
    And viewing the saved item side pane
    When deleting the all items
    And no item will be present

  Scenario:3 logged in customer saves items to wish list
    And has added 3 items to wish list from product search
    When viewing the saved items side pane
    Then 3 saved items will be present

  Scenario:4 anonymous customer adds the same item in two sizes to wish list
    And search for item with multiple sizes
    And click on a product image or title
    And select first available size of the product
    And click save item button
    And select second available size of the product
    And click save item button
    When viewing the saved items side pane
    Then 2 saved items will be present

  Scenario:5 anonymous customer adds the same item in two colours to wish list
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

  Scenario:6 anonymous customer saves items from product listings and then registers
    And has added items to wish list from product search
    When viewing the saved items side pane
    Then 3 saved items will be present
    When customer selects Register
    Then customer is taken to the sign in page
    When user provides required details to create new account on mobile
    When viewing the saved items side pane
    And 3 saved items will be present

  Scenario:7 anonymous customer with an account saves items from product listings
    And customer completes registration form
    And user logs out on mobile
    And I search for the product with "mens"
    And has added a item to wish list from product listings quick view
    When viewing saved items on full page
    Then saved item will be present
    When viewing the saved items side pane
    When customer selects Sign In
    When customer successfully signs in
    And 2 saved items will be present

  Scenario:8 anonymous customer with account saves items from product search and logs in
    And customer completes registration form
    When user logs out on mobile
    And has added items to wish list from product search
    And viewing the saved items side pane
    And 3 saved items will be present
    When customer selects Sign In
    When customer successfully signs in
    And 3 saved items will be present

  Scenario:9 anonymous customer creates wish list that registers to keep their saved items
    And has added items to wish list from product search
    And viewing the saved items side pane
    And 3 saved items will be present
    When customer selects Register
    When customer completes registration form
    When viewing the saved items side pane
    And 3 saved items will be present

	@oms_flow @OMSF-293
	Scenario: 10 Standard Delivery message on saved items page - 2 items - OMS
	    When user provides required details to create new account on mobile
		When I search for a product with system property "MAO_MultiSize_sfs"
		And click on a product image or title
		And select size of the product "UK 10"
		And click save item button
		And select size of the product "UK 14"
		And click save item button
		When viewing the saved items side pane
		Then 2 saved items will be present
		But I navigate to saved items page
		Then standard delivery message is displayed on saveditems page for products
			| MAO_MultiSize_sfs |
		And stock location is store in the datalayer product object
			| productCode       | sizeCode |
			| MAO_MultiSize_sfs | 10       |

