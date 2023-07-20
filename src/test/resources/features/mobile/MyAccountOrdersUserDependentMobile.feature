@UserDependentMobile
Feature: My account orders - User Dependent

  Background:
    Given i navigate to "Newlook" home page

  @NLRD-1473 @newlook
  Scenario:1 Verifying the breadcrumbs to My Account on the My Orders page - mobile
    When login with username "emailAddress" and password "password" on mobile
    And navigate to my orders on mobile
    And user clicks on breadcrumb 'My Account'
    Then user will navigate to 'My Account' page


  @NLRD-1473 @newlook @condev_data
  Scenario:2 Verifying the breadcrumbs to My Account on the Track My Order page - mobile
    When login with username "emailAddress" and password "password" on mobile
    And navigate to my orders on mobile
    And select view order in my orders page
    And user clicks on breadcrumb 'My Account'
    Then user will navigate to 'My Account' page

	@NLRD-1473 @newlook @oms_data
	Scenario Outline:3 Verifying the breadcrumbs to My Account on the Track My Order page OMS - mobile
		When login with username "emailAddress" and password "password" on mobile
		And select order with status "<order_status>" order status with "<Order_view_Link>" link mobile
		And user clicks on breadcrumb 'My Account'
		Then user will navigate to 'My Account' page
		Examples:
			|  order_status            | Milli_seconds |Order_view_Link|
			|  Dispatched      |   2500        |  Track and view order  |

