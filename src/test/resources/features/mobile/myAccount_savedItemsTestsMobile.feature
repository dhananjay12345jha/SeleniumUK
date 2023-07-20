@AnonymousMobile2
Feature: My Account Saved Items - mobile

	Background:
		Given i navigate to "Newlook" home page
		And user provides required details to create new account on mobile

	Scenario:1 Add a product to the my saved items - mobile
		When customer select a product to saved items and navigates to MY SAVED ITEMS page on mobile
		Then my product should be displayed in my account saved item page

	Scenario:2 Validate my account saved items page with one item - mobile
		When customer select a product to saved items and navigates to MY SAVED ITEMS page on mobile
		Then my saved items page is displayed with buttons
			| My Saved Items (1) |
			| MOVE ALL TO BAG    |
			| Move To Bag        |

	Scenario:3 Validate my account saved items page with more than one item - mobile
		When customer select 3 product to saved items and navigates to my saved items page on mobile
		Then my saved items page has 3 products and buttons
			| My Saved Items (3) |
			| MOVE ALL TO BAG    |
			| Move To Bag        |

	Scenario:4 Move saved item to bag - mobile
		When customer move item from my saved items to my bag on mobile
		Then my bag should have moved items

	@anonymousMobile2Samsung
	Scenario:5 Purchase saved items - mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When customer move item from my saved items to my bag on mobile
		And Navigate to checkout page
		And Customer makes a purchase to collect CREDITCARD
		Then Order has been successful

	@anonymousMobile2Samsung
	Scenario:6 Purchase saved items - mobile - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When customer move item from my saved items to my bag on mobile
		And Navigate to checkout page
		And Customer makes a purchase to collect CREDITCARD
		Then Order has been successful

	Scenario:7 Remove item from saved items page - mobile
		When customer select a product to saved items and navigates to MY SAVED ITEMS page on mobile
		When customer remove item from saved items
		Then Item should be removed

	Scenario:8 Change the size of saved item - mobile
		When customer select a product to saved items and navigates to MY SAVED ITEMS page on mobile
		When customer change size of the product
		Then Item should have same size in checkout page on mobile

	Scenario Outline:9 Check the increment and decrement of the My Bag counter when user adds and moves item to saved items - mobile
		When Customer <addRemove> a item to saved items from my bag page on mobile
		Then My bag counter should be <counterStatus>
		Examples:
			| counterStatus | addRemove |
			| increment     | add       |
			| decrement     | remove    |

	#NLTA-823, NLCC-4329
	@condev_data
	Scenario: 10 - Stock equal to or less than 5 units (Single Size Item)
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		When customer search for product productCode_OneSize_LowStock and navigates to pdp page
		And click save item button
		And viewing the saved items side pane
		Then message "Only 5 items left!" will display next to the one size string

#NLTA-823, NLCC-4329, NLCC-4327
	@condev_data
	Scenario: 11 - Stock equal to or less than 5 units (Multi Size Item)
		Given feature "feature.storefront.stock.urgency.messaging.saved" is switched on
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

	#NLTA-823, NLCC-4329
	@oms_data
	Scenario: 12 - Stock equal to or less than 5 units (Single Size Item) OMS
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		When customer search for product productCode_OneSize_LowStock_3Left_OMS and navigates to pdp page
		And click save item button
		And viewing the saved items side pane
		Then message "Only 3 items left!" will display next to the one size string


