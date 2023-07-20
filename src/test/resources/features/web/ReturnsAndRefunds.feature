@newlook @Anonymous2
Feature: Returns and Refunds

	Background:
		Given i navigate to "Newlook" home page
    
	@orderprocess_condev
	Scenario Outline:1 Check Order Returns and Refunds - Return
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>"
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName | Refund History | order_status | Milli_seconds | delivery_details_type |
			| return    | return   | true           | Returned     | 160000        | UK Daytime            |

	Scenario Outline:2 Check Order Returns and Refunds - Return - PCI micro forms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And I have checked out "4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>"
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName | Refund History | order_status | delivery_details_type |
			| return    | return   | true           | Returned     | UK Daytime           |

    @orderprocess_condev
	Scenario Outline:3 Check Order Returns and Refunds - Partial Cancel
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>"
		And refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_line_status>"
		Examples:
			| firstName     | lastName | Refund History | order_status | Milli_seconds | order_line_status |
			| partialcancel | 3        | false          | Dispatched   | 130000        | Cancelled         |

	Scenario Outline:4 Check Order Returns and Refunds - Partial Cancel - PCI micro forms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And I have checked out "4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>"
		And refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_line_status>"
		Examples:
			| firstName     | lastName | Refund History | order_status | Milli_seconds | order_line_status |
			| partialcancel | 3        | false          | Dispatched   | 130000        | Cancelled         |

     @orderprocess_condev
	Scenario Outline:5 Check Order Returns and Refunds - Partial Return
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "Dispatched"
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_line_status>"
		And user should see track my order button
		Examples:
			| firstName     | lastName | Refund History | order_status | Milli_seconds | order_line_status |
			| partialreturn | 3        | true           | Dispatched   | 160000        | Returned          |

	Scenario Outline:6 Check Order Returns and Refunds - Partial Return - PCI micro forms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And I have checked out "4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "Dispatched"
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_line_status>"
		And user should see track my order button
		Examples:
			| firstName     | lastName | Refund History | order_status | Milli_seconds | order_line_status |
			| partialreturn | 3        | true           | Dispatched   | 160000        | Returned          |
    
	@orderprocess_condev
	Scenario Outline:7 Check Order Returns and Refunds - Cancelled
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>"
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName | Refund History | order_status | Milli_seconds | delivery_details_type |
			| cancel    | cancel   | false          | Cancelled    | 130000        | UK Daytime           |

	Scenario Outline:8 Check Order Returns and Refunds - Cancelled - PCI microforms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And I have checked out "4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>"
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName | Refund History | order_status | Milli_seconds | delivery_details_type |
			| cancel    | cancel   | false          | Cancelled    | 130000        | UK Daytime           |

    @orderprocess_oms
	Scenario Outline:1 Check Order Returns and Refunds - Return OMS
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>" order status with "<Order_view_Link>" link
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName | Refund History | order_status | Milli_seconds | delivery_details_type |Order_view_Link          |
			| return    | return   | true           | Returned     | 160000        | UK Daytime            |Track and view order     |
			
	@orderprocess_oms		
	Scenario Outline:3 Check Order Returns and Refunds - Partial Cancel OMS
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>" order status with "<Order_view_Link>" link
		And refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_line_status>"
		Examples:
			| firstName     | lastName | Refund History | order_status | Milli_seconds | order_line_status |Order_view_Link          |
			| partialcancel | 3        | false          | Dispatched   | 130000        | Cancelled         |View order               |	
			
    @orderprocess_oms
	Scenario Outline:5 Check Order Returns and Refunds - Partial Return OMS
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>" order status with "<Order_view_Link>" link
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_line_status>"
		And user should see track my order button
		Examples:
			| firstName     | lastName | Refund History | order_status | Milli_seconds | order_line_status |Order_view_Link          |
			| partialreturn | 3        | true           | Dispatched   | 160000        | Returned          |Track and view order     |		

	@orderprocess_oms
	Scenario Outline:7 Check Order Returns and Refunds - Cancelled OMS
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>" order status with "<Order_view_Link>" link
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName | Refund History | order_status | Milli_seconds | delivery_details_type |Order_view_Link    |
			| cancel    | cancel   | false          | Cancelled    | 130000        | UK Daytime            |	View order     |			