@UserDependent
Feature: My account orders - User Dependent

  Background:
    Given i navigate to "Newlook" home page

  @NLRD-1473 @newlook
  Scenario:1 Verifying the breadcrumbs to My Account on the My Orders page
    When user clicks on Track My Order
    And login as already existing user
    And user clicks on breadcrumb 'My Account'
    Then user will navigate to 'My Account' page

  @NLRD-1473 @newlook @orderprocess_condev
  Scenario Outline:2 Verifying the breadcrumbs to My Account on the Track My Order page
    When user clicks on Track My Order
    And login as already existing user
    And select view order for "<order_number>" with status "<status>" in my orders page
    And user clicks on breadcrumb 'My Account'
    Then user will navigate to 'My Account' page
    Examples:
      | order_number | status |
      | testOrder-1  | Placed |

  @oms_data @orderprocess_oms
  Scenario Outline:3 Verifying the breadcrumbs to My Account on the Track My Order page OMS
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I have checked out "1" of a line item
		And I have checked out with the a delivery address "<firstName>" and "<lastName>"
		And select debit or credit card tab
		And i change the billing address and add another card
		And I get order number from the order confirmation page
		When user clicks on Track My Order
		And select order with status "Placed" order status with "<Order_view_Link>" link
		And also verify other order details in items section with status "<order_status>" and "<delivery_details_type>"
		And user clicks on breadcrumb 'My Account'
        Then user will navigate to 'My Account' page
		Examples:
		| firstName               | lastName         | delivery_details_type | order_status            | Milli_seconds |Order_view_Link|
        | manual                  |awaiting dispatch | UK Royal Mail         |  Awaiting dispatch      |   2500        |  View order  |
