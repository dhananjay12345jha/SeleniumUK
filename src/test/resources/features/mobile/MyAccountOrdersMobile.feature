@NewUsersMobileSamsung
Feature: My account orders - mobile

  Background:
    Given i navigate to "Newlook" home page

  @condev_data @orderprocess_condev
  Scenario Outline:1 Create order for awaiting dispatch for home delivery - mobile
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And Select a product and navigate to payment page on mobile
    And I have checked out with the a delivery address "<firstName>" and "<lastName>"
    And select debit or credit card tab
    And i change the billing address and add another card
    And I get order number from the order confirmation page
    And select order with status "<order_status>" on mobile
    And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
    Examples:
      | firstName | lastName          | delivery_details_type | order_status      | Milli_seconds |
      | manual    | awaiting dispatch | UK Standard           | Awaiting dispatch | 25000         |

	Scenario Outline:2 Create order for awaiting dispatch for home delivery - mobile -PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>" on mobile
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName          | delivery_details_type | order_status      | Milli_seconds |
			| manual    | awaiting dispatch | UK Standard           | Awaiting dispatch | 25000         |

	@condev_data @orderprocess_condev
	Scenario Outline:3 Create order for awaiting dispatch for collection - mobile
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And Select a product and navigate to payment page on mobile
    And completed checkout with collection details "<firstName>" and "<lastName>"
    And set fields for collection and navigate to my account on mobile
    And select order with status "<order_status>" on mobile
    And verify order details in items section with item status "<order_status>", delivery details "<collection_details_type>" and other order details
    Examples:
      | firstName | lastName          | collection_details_type     | order_status      | Milli_seconds |
      | manual    | awaiting dispatch | UK Click & Collect Standard | Awaiting dispatch | 25000         |

	Scenario Outline:4 Create order for awaiting dispatch for collection - mobile -PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And set fields for collection and navigate to my account on mobile
		And select order with status "<order_status>" on mobile
		And verify order details in items section with item status "<order_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName | lastName          | collection_details_type     | order_status      | Milli_seconds |
			| manual    | awaiting dispatch | UK Click & Collect Standard | Awaiting dispatch | 25000         |

  @condev_data @orderprocess_condev
	Scenario Outline:5 Create order for awaiting collection for collection - mobile
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And Select a product and navigate to payment page on mobile
    And completed checkout with collection details "<firstName>" and "<lastName>"
    And set fields for collection and navigate to my account on mobile
    And select order with status "<order_status>" on mobile
    And verify order details in items section with item status "<order_line_status>", delivery details "<collection_details_type>" and other order details
    Examples:
      | firstName           | lastName        | collection_details_type     | order_status         | Milli_seconds | order_line_status |
      | awaiting collection | awaitCollection | UK Click & Collect Standard | Ready for collection | 325000        | Dispatched        |

	Scenario Outline:6 Create order for awaiting collection for collection - mobile -PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And set fields for collection and navigate to my account on mobile
		And select order with status "<order_status>" on mobile
		And verify order details in items section with item status "<order_line_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName           | lastName        | collection_details_type     | order_status         | Milli_seconds | order_line_status |
			| awaiting collection | awaitCollection | UK Click & Collect Standard | Ready for collection | 325000        | Dispatched        |

	@condev_data @orderprocess_condev
	Scenario Outline:7 Create order for dispatched for collection - mobile
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And Select a product and navigate to payment page on mobile
    And completed checkout with collection details "<firstName>" and "<lastName>"
    And set fields for collection and navigate to my account on mobile
    And select order with status "<order_status>" on mobile
    And verify order details in items section with item status "<order_status>", delivery details "<collection_details_type>" and other order details
    Examples:
      | firstName  | lastName   | collection_details_type     | order_status | Milli_seconds |
      | Dispatched | Dispatched | UK Click & Collect Standard | Dispatched   | 230000        |

	Scenario Outline:8 Create order for dispatched for collection - mobile -PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And set fields for collection and navigate to my account on mobile
		And select order with status "<order_status>" on mobile
		And verify order details in items section with item status "<order_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName  | lastName   | collection_details_type     | order_status | Milli_seconds |
			| Dispatched | Dispatched | UK Click & Collect Standard | Dispatched   | 230000        |

  @oms_flow @orderprocess_oms
  Scenario Outline:9 Create order for awaiting dispatch for home delivery OMS- mobile
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And Select a product and navigate to payment page on mobile
    And I have checked out with the a delivery address "<firstName>" and "<lastName>"
    And select debit or credit card tab
    And i change the billing address and add another card
    And I get order number from the order confirmation page
	  And select order with status "<order_status>" order status with "<Order_view_Link>" link mobile
    And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
    Examples:
      | firstName | lastName          | delivery_details_type | order_status      | Milli_seconds |Order_view_Link|
      | manual    | awaiting dispatch | UK Royal Mail           | Awaiting dispatch | 25000         |  View order        |


	@oms_flow @orderprocess_oms
	Scenario Outline:10 Create order for awaiting dispatch for collection OMS - mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And set fields for collection and navigate to my account on mobile
		And select order with status "<order_status>" order status with "<Order_view_Link>" link mobile
		And verify order details in items section with item status "<order_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName | lastName          | collection_details_type     | order_status      | Milli_seconds |Order_view_Link|
			| manual    | awaiting dispatch | UK Click & Collect Standard | Awaiting dispatch | 25000         | View order            |


	@oms_flow @orderprocess_oms
	Scenario Outline:11 Create order for awaiting collection for collection OMS - mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And set fields for collection and navigate to my account on mobile
		And select order with status "<order_status>" order status with "<Order_view_Link>" link mobile
		And verify order details in items section with item status "<order_line_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName           | lastName        | collection_details_type     | order_status         | Milli_seconds | order_line_status |Order_view_Link|
			| awaiting collection | awaitCollection | UK Click & Collect Standard | Ready for collection | 325000        | Dispatched        |   Track and view order            |

	@oms_flow @orderprocess_oms
	Scenario Outline:12 Create order for dispatched for collection OMS - mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And set fields for collection and navigate to my account on mobile
		And select order with status "<order_status>" order status with "<Order_view_Link>" link mobile
		And verify order details in items section with item status "<order_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName  | lastName   | collection_details_type     | order_status | Milli_seconds |Order_view_Link|
			| Dispatched | Dispatched | UK Click & Collect Standard | Dispatched   | 230000        |Track and view order            |
