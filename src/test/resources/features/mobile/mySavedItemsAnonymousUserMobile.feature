@newlook @AnonymousMobile
Feature: My Saved Items Anonymous user

	Background:
		Given i navigate to "Newlook" home page

	#NLCC-5273 is testing NLCC-4329, NLCC-4327
	@NLCC-4329
	@NLCC-4327 @condev_data
	Scenario Outline:1 - Stock equal to or less than 5 units (Single Size Item)
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
	Scenario:2 - Stock is greater than 5 units (Single Size Item)
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		When customer search for product productCode_OneSize and navigates to pdp page
		And click save item button
		And viewing the saved items side pane
		Then The saved items pane has no urgency warning
		When viewing the saved items page
		Then The saved items page has no urgency warning

   #NLCC-5273 is testing NLCC-4329
	@NLCC-4329
	Scenario:3 - Stock is equal to 0 (Single Size Item)
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		When user navigates to "OutOfStockProductUrl"
		And click save item button
		And viewing the saved items side pane
		Then The saved items pane has no urgency warning
		When viewing the saved items page
		Then The saved items page has no urgency warning

	#NLCC-5273 is testing NLCC-4329, NLCC-4327
	@NLCC-4329
	@NLCC-4327
	@condev_data
	Scenario:4 - Stock equal to or less than 5 units (Multi Size Item)
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
	Scenario:5 - Stock greater than 5 units (Multi Size Item)
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
	Scenario:6 - Stock is equal to zero (Multi Size Item)
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		When customer search for product productCode_MultiSize_OutOfStock and navigates to pdp page
		And click save item button
		And viewing the saved items
		When I select low stock size "S/M" from saved items
		Then The saved items page has no urgency warning

	#NLCC-5273 is testing NLCC-4329, NLCC-4327
	@NLCC-4329
	@NLCC-4327 @oms_data
	Scenario Outline:7 - Stock equal to or less than 5 units (Single Size Item) OMS
		And feature "feature.storefront.stock.urgency.messaging.saved" is switched on
		When customer search for product <productType> and navigates to pdp page
		And The <itemCount> items left message is visible on the PDP
		And click save item button
		And viewing the saved items side pane
		Then The saved items pane urgency warning says "<warningMessage>"
		And viewing the saved items page
		Then The saved items page urgency warning says "<warningMessage>"
				Examples:
			| productType                              | itemCount | warningMessage     |
			| productCode_OneSize_LowStock_3Left_OMS   | 3         | Only 3 items left! |
			| productCode_OneSize_LessThan5Stock_OMS   | 4         | Only 4 items left! |
			| productCode_OneSize_Only1Stock_OMS       |  1         | Only 1 item left!  |


