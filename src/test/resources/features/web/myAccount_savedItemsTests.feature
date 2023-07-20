# 13/12/17
@newlook @Anonymous2
Feature: My Account Saved Items

	Background:
		Given i navigate to "Newlook" home page
		And user provides required details to create new account

	Scenario:1 Add a product to the my saved items
		When customer select a product to saved items and navigates to MY SAVED ITEMS page
		Then my product should be displayed in my account saved item page

	Scenario:2 Validate my account saved items page with one item
		When customer select a product to saved items and navigates to MY SAVED ITEMS page
		Then my saved items page is displayed with buttons
			| My Saved Items (1) |

	Scenario:3 Validate my account saved items page with more than one item
		When customer select 3 product to saved items and navigates to MY SAVED ITEMS page
		Then my saved items page has 3 products and buttons
			| My Saved Items (3) |

	Scenario:4 Move saved item to bag
		When customer move item from MY SAVED ITEMS to my bag
		Then my bag should have moved items

	Scenario:5 Purchase saved items
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When customer move item from MY SAVED ITEMS to my bag
		And Navigate to checkout page
		And Customer makes a purchase to collect CREDITCARD
		Then Order has been successful

	Scenario:6 Purchase saved items -PCI microforms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When customer move item from MY SAVED ITEMS to my bag
		And Navigate to checkout page
		And Customer makes a purchase to collect CREDITCARD
		Then Order has been successful

	Scenario:7 Purchase saved items - PCI micro forms
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When customer move item from MY SAVED ITEMS to my bag
		And Navigate to checkout page
		And Customer makes a purchase to collect CREDITCARD
		Then Order has been successful

	Scenario:8 Remove item from saved items page
		And customer select a product to saved items and navigates to MY SAVED ITEMS page
		When customer remove item from saved items
		Then Item should be removed

	Scenario:9 Change the size of saved item
		And customer select a product to saved items and navigates to MY SAVED ITEMS page
		When customer change size of the product
		Then Item should have same size in checkout page

	Scenario Outline:10 Check the increment and decrement of the My Bag counter when user adds and moves item to saved items
		When Customer <addRemove> a item to saved items from my bag page
		Then My bag counter should be <counterStatus>
		Examples:
			| counterStatus | addRemove |
			| increment     | add       |
			| decrement     | remove    |

	#NLTA-823 is testing NLCC-4327
	#NLCC-5273 is testing NLCC-4329
	@NLCC-4327 @NLCC-4329 @condev_data
	Scenario Outline:11 - Stock equal to or less than 5 units (Single Size Item)
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		When customer search for product <productType> and navigates to pdp page
		And The <itemCount> items left message is visible on the PDP
		And click save item button
		And viewing the saved items side pane
		Then The saved items pane urgency warning says "<warningMessage>"
		And viewing the saved items page
		Then The saved items page urgency warning says "<warningMessage>"

		Examples:
			| productType                        | itemCount | warningMessage     |
			| productCode_OneSize_LowStock       | 5         | Only 5 items left! |
			| productCode_OneSize_LessThan5Stock | 4         | Only 4 items left! |
			| productCode_OneSize_Only1Stock     | 1         | Only 1 item left!  |

	#NLCC-5273 is testing NLCC-4329
	@NLCC-4329
	Scenario:12 - Stock is greater than 5 units (Single Size Item)
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		When customer search for product productCode_OneSize and navigates to pdp page
		And click save item button
		And viewing the saved items side pane
		Then The saved items pane has no urgency warning
		When viewing the saved items page
		Then The saved items page has no urgency warning

   #NLCC-5273 is testing NLCC-4329
	@NLCC-4329
	Scenario:13 - Stock is equal to 0 (Single Size Item)
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		When user navigates to "OutOfStockProductUrl"
		And click save item button
		And viewing the saved items side pane
		Then The saved items pane has no urgency warning
		When viewing the saved items page
		Then The saved items page has no urgency warning

  #NLTA-823 is testing NLCC-4327
  #NLCC-5273 is testing NLCC-4329
  #@NLCC-4327 @NLCC-4329
  @condev_data
	Scenario:14 - Stock equal to or less than 5 units (Multi Size Item)
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		When customer search for product productCode_MultiSize_LowStock and navigates to pdp page
		And The size picker contains the few items left message against each size
			| UK 8  | 2 |
			| UK 10 | 3 |
			| UK 12 | 1 |
			| UK 14 | 5 |
			| UK 16 | 4 |
		And click save item button
		And viewing the saved items
		When I open the drop down size picker from saved items

		Then message "Only 2 items left!" will display next to the size "UK 8" value on saved items
		And message "Only 3 items left!" will display next to the size "UK 10" value on saved items
		And message "Only 1 item left!" will display next to the size "UK 12" value on saved items
		And message "Only 5 items left!" will display next to the size "UK 14" value on saved items
		And message "Only 4 items left!" will display next to the size "UK 16" value on saved items

		When I select low stock size "UK 8" from saved items
		Then The saved items page urgency warning says "Only 2 items left!"
		When I select low stock size "UK 10" from saved items
		Then The saved items page urgency warning says "Only 3 items left!"
		When I select low stock size "UK 12" from saved items
		Then The saved items page urgency warning says "Only 1 item left!"
		When I select low stock size "UK 14" from saved items
		Then The saved items page urgency warning says "Only 5 items left!"
		When I select low stock size "UK 16" from saved items
		Then The saved items page urgency warning says "Only 4 items left!"

   #NLCC-5273 is testing NLCC-4329, NLCC-4327
	@NLCC-4329
	@NLCC-4327
	@condev_data
	Scenario:15 - Stock greater than 5 units (Multi Size Item)
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		When customer search for product productCode_MultiSize and navigates to pdp page
		And The size picker does not contains the few items left message against different size
			| UK 3 |
			| UK 4 |
			| UK 5 |
			| UK 6 |
			| UK 7 |
			| UK 8 |
			| UK 9 |
		And click save item button
		And viewing the saved items
		When I select low stock size "UK 3" from saved items
		Then The saved items page has no urgency warning
		When I select low stock size "UK 4" from saved items
		Then The saved items page has no urgency warning
		When I select low stock size "UK 5" from saved items
		Then The saved items page has no urgency warning
		When I select low stock size "UK 6" from saved items
		Then The saved items page has no urgency warning
		When I select low stock size "UK 7" from saved items
		Then The saved items page has no urgency warning

   #NLCC-5273 is testing NLCC-4329
	@NLCC-4329
	Scenario:16 - Stock is equal to zero (Multi Size Item)
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		When customer search for product productCode_MultiSize_OutOfStock and navigates to pdp page
		And click save item button
		And viewing the saved items
		When I select low stock size "S/M" from saved items
		Then The saved items page has no urgency warning


	#NLTA-823 is testing NLCC-4327
	#NLCC-5273 is testing NLCC-4329
	@NLCC-4327 @NLCC-4329 @oms_data
	Scenario Outline:17 - Stock equal to or less than 5 units (Single Size Item) OMS
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		And I set 3542359 as lowstock with minus 1 minutes with Store 3 and Dc 2 quantity
		And I set 3577139 as lowstock with minus 1 minutes with Store 2 and Dc 2 quantity
		And I set 3540969 as lowstock with minus 1 minutes with Store 1 and Dc 0 quantity
		And i open hac page
		And i provide valid username and password and click
		And i have cleared the Cache from Hac
		And i open newLook page
		When customer search for product <productType> and navigates to pdp page
		And The <itemCount> items left message is visible on the PDP
		And click save item button
		And viewing the saved items side pane
		Then The saved items pane urgency warning says "<warningMessage>"
		And viewing the saved items page
		Then The saved items page urgency warning says "<warningMessage>"

		Examples:
			| productType                              | itemCount | warningMessage     |
			| productCode_OneSize_LowStock_3Left_OMS   | 5         | Only 5 items left! |
			| productCode_OneSize_LessThan5Stock_OMS   | 4         | Only 4 items left! |
			| productCode_OneSize_Only1Stock_OMS       | 1         | Only 1 item left!  |

  #NLTA-823 is testing NLCC-4327
  #NLCC-5273 is testing NLCC-4329
  #@NLCC-4327 @NLCC-4329
  @oms_data
	Scenario:18 - Stock equal to or less than 5 units (Multi Size Item) OMS
	  And I set 3596569 as lowstock with minus 1 minutes with Store 2 and Dc 0 quantity
	  And I set 3596570 as lowstock with minus 1 minutes with Store 2 and Dc 1 quantity
	  And I set 3596571 as lowstock with minus 1 minutes with Store 1 and Dc 0 quantity
	  And I set 3596572 as lowstock with minus 5 minutes with Store 3 and Dc 2 quantity
	  And I set 3596573 as lowstock with minus 5 minutes with Store 2 and Dc 2 quantity
	  And feature "feature.storefront.stock.urgency.messaging.pdp" is switched on
	  And i open hac page
	  And i provide valid username and password and click
	  And i have cleared the Cache from Hac
	  And i open newLook page
	  When customer search for product productCode_MultiSize_LowStock and navigates to pdp page
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		When customer search for product productCode_MultiSize_LowStock and navigates to pdp page
		And The size picker contains the few items left message against each size
			| UK 8  | 2 |
			| UK 10 | 3 |
			| UK 12 | 1 |
			| UK 14 | 5 |
			| UK 16 | 4 |
		And click save item button
		And viewing the saved items
		When I open the drop down size picker from saved items

		Then message "Only 2 items left!" will display next to the size "UK 8" value on saved items
		And message "Only 3 items left!" will display next to the size "UK 10" value on saved items
		And message "Only 1 item left!" will display next to the size "UK 12" value on saved items
		And message "Only 5 items left!" will display next to the size "UK 14" value on saved items
		And message "Only 4 items left!" will display next to the size "UK 16" value on saved items

		When I select low stock size "UK 8" from saved items
		Then The saved items page urgency warning says "Only 2 items left!"
		When I select low stock size "UK 10" from saved items
		Then The saved items page urgency warning says "Only 3 items left!"
		When I select low stock size "UK 12" from saved items
		Then The saved items page urgency warning says "Only 1 item left!"
		When I select low stock size "UK 14" from saved items
		Then The saved items page urgency warning says "Only 5 items left!"
		When I select low stock size "UK 16" from saved items
		Then The saved items page urgency warning says "Only 4 items left!"


