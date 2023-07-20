@anonymousMobile2Samsung
Feature: Returns and Refunds -Mobile
  Background:
    Given i navigate to "Newlook" home page

	@orderprocess_condev
  Scenario Outline:1 Check Order Returns and Refunds - Return
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And I have checked out "Qty: 4" of a line item
    And I have checked out with the a delivery address "<firstName>" and "<lastName>"
    And select debit or credit card tab
    And i change the billing address and add another card
    And I get order number from the order confirmation page
    And select order with status "<order_status>" on mobile
    Then refund history "<Refund History>" section exits
    And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
    Examples:
      | firstName | lastName | Refund History | order_status | delivery_details_type |
      | return    | return   | true           | Returned     | UK Standard           |

	Scenario Outline:2 Check Order Returns and Refunds - Return - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And I have checked out "Qty: 4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>" on mobile
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName | Refund History | order_status | delivery_details_type |
			| return    | return   | true           | Returned     | UK Standard           |

	@orderprocess_condev
  Scenario Outline:3 Check Order Returns and Refunds - Partial Cancel
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And I have checked out "Qty: 4" of a line item
    And I have checked out with the a delivery address "<firstName>" and "<lastName>"
    And select debit or credit card tab
    And i change the billing address and add another card
    And I get order number from the order confirmation page
    And select order with status "<order_status>" on mobile
    And refund history "<Refund History>" section exits
    And also verify other order details in items section with status "<order_line_status>"
    Examples:
      | firstName     | lastName | Refund History | order_status | order_line_status |
      | partialcancel | 3        | false          | Dispatched   | Cancelled         |

	Scenario Outline:4 Check Order Returns and Refunds - Partial Cancel -PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And I have checked out "Qty: 4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>" on mobile
		And refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_line_status>"
		Examples:
			| firstName     | lastName | Refund History | order_status | order_line_status |
			| partialcancel | 3        | false          | Dispatched   | Cancelled         |

	@orderprocess_condev
   Scenario Outline:5 Check Order Returns and Refunds - Partial Return
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And I have checked out "Qty: 4" of a line item
    And I have checked out with the a delivery address "<firstName>" and "<lastName>"
    And select debit or credit card tab
    And i change the billing address and add another card
    And I get order number from the order confirmation page
    And select order with status "Dispatched" on mobile
    Then refund history "<Refund History>" section exits
    And also verify other order details in items section with status "<order_line_status>"
    And user should see track my order button
    Examples:
      | firstName     | lastName | Refund History | order_status | Milli_seconds | order_line_status |
      | partialreturn | 3        | true           | Dispatched   | 160000        | Returned          |

	Scenario Outline:6 Check Order Returns and Refunds - Partial Return - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And I have checked out "Qty: 4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "Dispatched" on mobile
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_line_status>"
		And user should see track my order button
		Examples:
			| firstName     | lastName | Refund History | order_status | Milli_seconds | order_line_status |
			| partialreturn | 3        | true           | Dispatched   | 160000        | Returned          |

	@orderprocess_condev
	Scenario Outline:7 Check Order Returns and Refunds - Cancelled
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And I have checked out "Qty: 4" of a line item
    And I have checked out with the a delivery address "<firstName>" and "<lastName>"
    And select debit or credit card tab
    And i change the billing address and add another card
    And I get order number from the order confirmation page
    And select order with status "<order_status>" on mobile
    Then refund history "<Refund History>" section exits
    And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
    Examples:
      | firstName | lastName | Refund History | order_status | Milli_seconds | delivery_details_type |
      | cancel    | cancel   | false          | Cancelled    | 130000        | UK Standard           |

	Scenario Outline:8 Check Order Returns and Refunds - Cancelled - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And I have checked out "Qty: 4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>" on mobile
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName | Refund History | order_status | Milli_seconds | delivery_details_type |
			| cancel    | cancel   | false          | Cancelled    | 130000        | UK Standard           |

	@orderprocess_oms
	Scenario Outline:1 Check Order Returns and Refunds - Return
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And I have checked out "Qty: 4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>" order status with "<Order_view_Link>" link mobile
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName | Refund History | order_status | delivery_details_type |Order_view_Link          |
			| return    | return   | true           | Returned     | UK Standard           |Track and view order|


	@orderprocess_oms
	Scenario Outline:3 Check Order Returns and Refunds - Partial Cancel
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And I have checked out "Qty: 4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>" order status with "<Order_view_Link>" link mobile
		And refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_line_status>"
		Examples:
			| firstName     | lastName | Refund History | order_status | order_line_status |Order_view_Link          |
			| partialcancel | 3        | false          | Dispatched   | Cancelled         |Track and view order|



	@orderprocess_oms
	Scenario Outline:5 Check Order Returns and Refunds - Partial Return
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And I have checked out "Qty: 4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>" order status with "<Order_view_Link>" link mobile
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_line_status>"
		And user should see track my order button
		Examples:
			| firstName     | lastName | Refund History | order_status | Milli_seconds | order_line_status |Order_view_Link          |
			| partialreturn | 3        | true           | Dispatched   | 160000        | Returned          |Track and view order|

	@orderprocess_oms
	Scenario Outline:7 Check Order Returns and Refunds - Cancelled
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And I have checked out "Qty: 4" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>" order status with "<Order_view_Link>" link mobile
		Then refund history "<Refund History>" section exits
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName | Refund History | order_status | Milli_seconds | delivery_details_type |Order_view_Link          |
			| cancel    | cancel   | false          | Cancelled    | 130000        | UK Standard           |Track and view order|





