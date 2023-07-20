@NewUsers
Feature: My account orders

	Background:
		Given i navigate to "Newlook" home page

    @condev_data
	Scenario Outline:1 Create order for awaiting dispatch for home delivery
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>"
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName          | delivery_details_type | order_status      | Milli_seconds |
			| manual    | awaiting dispatch | UK Standard            | Awaiting dispatch | 25000         |

	Scenario Outline:2 Create order for awaiting dispatch for home delivery - PCI micro form
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>"
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName          | delivery_details_type | order_status      |
			| manual    | awaiting dispatch | UK Standard           | Awaiting dispatch |

	@nlcc-180 @orderprocess_condev
	Scenario Outline:3 Create order for awaiting dispatch for collection
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And I get order number from the order confirmation page for collection
		And select order with status "<order_status>"
		And verify order details in items section with item status "<order_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName | lastName          | collection_details_type     | order_status      | Milli_seconds |
			| manual    | awaiting dispatch | UK Click & Collect Standard | Awaiting dispatch | 25000         |

	@nlcc-180
	Scenario Outline:4 Create order for awaiting dispatch for collection - PCI micro forms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And I get order number from the order confirmation page for collection
		And select order with status "<order_status>"
		And verify order details in items section with item status "<order_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName | lastName          | collection_details_type     | order_status      |
			| manual    | awaiting dispatch | UK Click & Collect Standard | Awaiting dispatch |

  @orderprocess_condev
	Scenario Outline:5 Create order for awaiting collection for collection
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And I get order number from the order confirmation page for collection
		And select order with status "<order_status>"
		And verify order details in items section with item status "<order_line_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName           | lastName        | collection_details_type     | order_status         | Milli_seconds | order_line_status |
			| awaiting collection | awaitCollection | UK Click & Collect Standard | Ready for collection | 325000        | Dispatched        |

  @orderprocess_condev
	Scenario Outline:6 Create order for awaiting collection for collection
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And I get order number from the order confirmation page for collection
		And select order with status "<order_status>"
		And verify order details in items section with item status "<order_line_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName           | lastName        | collection_details_type     | order_status         | Milli_seconds | order_line_status |
			| awaiting collection | awaitCollection | UK Click & Collect Standard | Ready for collection | 325000        | Dispatched        |

	@orderprocess_condev
	Scenario Outline:7 Create order for awaiting collection for collection - PCI micro form
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And I get order number from the order confirmation page for collection
		And select order with status "<order_status>"
		And verify order details in items section with item status "<order_line_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName           | lastName        | collection_details_type     | order_status         | Milli_seconds | order_line_status |
			| awaiting collection | awaitCollection | UK Click & Collect Standard | Ready for collection | 325000        | Dispatched        |

  @orderprocess_condev
	Scenario Outline:8 Create order for dispatched for collection
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And I get order number from the order confirmation page for collection
		And select order with status "<order_status>"
		And verify order details in items section with item status "<order_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName  | lastName   | collection_details_type     | order_status | Milli_seconds |
			| Dispatched | Dispatched | UK Click & Collect Standard | Dispatched   | 230000        |

	Scenario Outline:9 Create order for dispatched for collection - PCI microforms on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And I get order number from the order confirmation page for collection
		And select order with status "<order_status>"
		And verify order details in items section with item status "<order_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName  | lastName   | collection_details_type     | order_status | Milli_seconds |
			| Dispatched | Dispatched | UK Click & Collect Standard | Dispatched   | 230000        |

  @orderprocess_condev
	Scenario Outline:10 Create order for collected
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And I get order number from the order confirmation page for collection
		And select order with status "<order_status>"
		And verify order details in items section with item status "<order_line_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName | lastName  | collection_details_type     | order_status | order_line_status |
			| collected | collected | UK Click & Collect Standard | Collected    | Dispatched        |

	Scenario Outline:11 Create order for collected - pci microforms on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And I get order number from the order confirmation page for collection
		And select order with status "<order_status>"
		And verify order details in items section with item status "<order_line_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName | lastName  | collection_details_type     | order_status | order_line_status |
			| collected | collected | UK Click & Collect Standard | Collected    | Dispatched        |

	#NLCC-6601 AC2, #NLCC-7622 is testing NLCC-7618
	Scenario:12 Edit Delivery form for logged in user for home delivery
		And feature "feature.deliveryUpdate.forms.enabled" is switched on
		When user provides required details to create new account
		And Select a product and navigate to payment page
		And select delivery option
		Then delivery edit form should be displayed with mandatory mobile number for HOME DELIVERY
		And Collection details error messages are "not displayed"

   #NLCC-6601 AC6 #NLCC-7622 is testing NLCC-7618
	Scenario:13 Edit Delivery form for logged in user for collection delivery
		And feature "feature.deliveryUpdate.forms.enabled" is switched on
		When user provides required details to create new account
		And Select a product and navigate to payment page
		And search for nearest collection location with "london" and select collection location as "Click & Collect Standard"
		Then delivery edit form should be displayed with mandatory mobile number for COLLECTION
		And Collection details error messages are "not displayed"
		And continue to payment button
		And Collection details error messages are "displayed"

  @oms_data @orderprocess_condev
	Scenario Outline:14 Create order for awaiting dispatch for home delivery
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>"
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName | lastName          | delivery_details_type | order_status      | Milli_seconds |
			| manual    | awaiting dispatch | UK Royal Mail            | Awaiting dispatch | 25000      |


	@orderprocess_oms
	Scenario Outline:15 Create order for awaiting collection for collection OMS
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And I get order number from the order confirmation page for collection
		And select order with status "<order_status>" order status with "<Order_view_Link>" link
		And verify order details in items section with item status "<order_line_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName           | lastName        | collection_details_type     | order_status         | Milli_seconds | order_line_status | Order_view_Link          |
			| awaiting collection | awaitCollection | UK Click & Collect Standard | Ready for collection | 325000        | Dispatched        | Track and view order     |

    @orderprocess_oms
	Scenario Outline:16 Create order for collected OMS
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And I get order number from the order confirmation page for collection
		And select order with status "<order_status>" order status with "<Order_view_Link>" link
		And verify order details in items section with item status "<order_line_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName | lastName  | collection_details_type     | order_status | order_line_status |Order_view_Link          |
			| collected | collected | UK Click & Collect Standard | Collected    | Dispatched        |Track and view order     |
			
    @oms_data @orderprocess_oms
	Scenario Outline:17 Create order for "<order_status>" for home delivery OMS
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "<order_status>" order status with "<Order_view_Link>" link
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName               | lastName         | delivery_details_type | order_status      | Milli_seconds |Order_view_Link|
			| awaiting dispatch       | await            | UK Royal Mail         | Awaiting dispatch | 25000         | View order    |
			
	@nlcc-180 @orderprocess_oms
	Scenario Outline:18 Create order for "<order_status>" for collection OMS
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And I get order number from the order confirmation page for collection
		And select order with status "<order_status>" order status with "<Order_view_Link>" link
		And verify order details in items section with item status "<order_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName  | lastName          | collection_details_type     | order_status      | Milli_seconds | Order_view_Link          |
			| OMS        | awaiting dispatch | UK Click & Collect Standard | Awaiting dispatch | 25000         |  View order              |                  
		    | Dispatched | Dispatched        | UK Click & Collect Standard | Dispatched        | 230000        |  Track and view order    | 
			
	@oms_data @orderprocess_oms
	Scenario Outline:19 Create order for "<order_status>" for home delivery OMS
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		And select order with status "In Process" order status with "<Order_view_Link>" link
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		Examples:
			| firstName               | lastName         | delivery_details_type | order_status            | Milli_seconds |Order_view_Link|
            | manual                  |awaiting dispatch | UK Royal Mail         |  Awaiting dispatch      |   2500        |  View order  |
			
	@nlcc-180 @orderprocess_oms
	Scenario Outline:20 Create order for "<order_status>" for collection OMS
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And completed checkout with collection details "<firstName>" and "<lastName>"
		And I get order number from the order confirmation page for collection
		And select order with status "In Process" order status with "<Order_view_Link>" link
		And verify order details in items section with item status "<order_status>", delivery details "<collection_details_type>" and other order details
		Examples:
			| firstName  | lastName          | collection_details_type     | order_status             | Milli_seconds | Order_view_Link          |
			| manual     | awaiting dispatch | UK Click & Collect Standard | Awaiting dispatch        | 25000         |  View order              |  	

  @oms_data @orderprocess_oms
	Scenario Outline:21 Create order for 'Placed' for home delivery OMS
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
	  When I search for the product MAO_MultiSize_sfs and navigate to pdp page
	  And select size of the product "UK 10"
	  And I click Add to Bag
	  When I search for the product MAO_MultiSize_sfs_dc and navigate to pdp page
	  And select size of the product "UK 16"
	  And I click Add to Bag
	  When I search for the product MAO_SingleSize_sfs_dc and navigate to pdp page
	  And I click Add to Bag
	  When I search for the product MAO_SingleSize_sfs and navigate to pdp page
	  And I click Add to Bag
	  And I click on view bag and checkout button
	  And i click on checkout button
	  And I have checked out with the a delivery address "<firstName>" and "<lastName>"
	  And select debit or credit card tab
	  And i change the billing address and add another card
	  And I get order number from the order confirmation page
	  And select order with status "<order_status>" order status with "<Order_view_Link>" link
	  And stock location is store in the datalayer product object
		  | productCode        | sizeCode |
		  | MAO_MultiSize_sfs  | 10       |
		  | MAO_SingleSize_sfs | 99       |
	  And stock location is dc in the datalayer product object
		  | productCode           | sizeCode |
		  | MAO_MultiSize_sfs_dc  | 16       |
		  | MAO_SingleSize_sfs_dc | 99       |
	  Examples:
		  | firstName | lastName | delivery_details_type | order_status |Order_view_Link          |
		  | manual    | Placed   | UK Royal Mail         | Placed       | View order              |  


#	@oms_flow @OMSF-1240 @orderprocess_oms @test
#	Scenario Outline:13 NL Data Layer in the Order Details Page - Registered Customer
#		And feature "feature.pci.microform.enabled.uk-site" is switched off
#		When user provides required details to create new account
#		When I search for the product MAO_MultiSize_sfs and navigate to pdp page
#		And select size of the product "UK 10"
#		And I click Add to Bag
#		When I search for the product MAO_MultiSize_sfs_dc and navigate to pdp page
#		And select size of the product "UK 16"
#		And I click Add to Bag
#		When I search for the product MAO_SingleSize_sfs_dc and navigate to pdp page
#		And I click Add to Bag
#		When I search for the product MAO_SingleSize_sfs and navigate to pdp page
#		And I click Add to Bag
#		And I click on view bag and checkout button
#		And i click on checkout button
#		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
#		And select debit or credit card tab
#		And i change the billing address and add another card
#		And I get order number from the order confirmation page
#		And select order with status "<order_status>" OMS
#		And stock location is store in the datalayer product object
#			| productCode        | sizeCode |
#			| MAO_MultiSize_sfs  | 10       |
#			| MAO_SingleSize_sfs | 99       |
#		And stock location is dc in the datalayer product object
#			| productCode           | sizeCode |
#			| MAO_MultiSize_sfs_dc  | 10       |
#			| MAO_SingleSize_sfs_dc | 99       |
#		Examples:
#			| firstName | lastName | delivery_details_type | order_status |
#			| manual    | Placed   | UK Royal Mail         | Placed       |



