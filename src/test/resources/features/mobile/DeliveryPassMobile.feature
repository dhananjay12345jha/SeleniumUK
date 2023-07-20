@AnonymousMobile2
@newlook
Feature: Delivery Pass
  As a Customer,
  I want to be able to purchase
  A delivery pass.

  Background:
    Given i navigate to "Newlook" home page

  Scenario:1 anonymous customer registers to add delivery pass - mobile
    And anonymous user navigates to delivery pass page
    But add to bag button is disabled

    When customer registers
    Then user is on Delivery Pass page
    And add to bag button is enabled

    When customer adds Delivery Pass to bag
    Then Delivery Pass has been added to bag
    And add to bag button is disabled

    When user checks their bag
    Then Delivery Pass is present without attributes
    And customer is logged out

	  #NLCC-3497
  Scenario:2 logged out registered customer adds delivery pass - mobile
    And customer already has an account
    And user logs out on mobile
    And user goes to Delivery Pass page
    But add to bag button is disabled

    When customer signs in
    Then user is on Delivery Pass page
    And add to bag button is enabled

    When customer adds Delivery Pass to bag
    Then Delivery Pass has been added to bag
    And add to bag button is disabled

    When user checks their bag
    Then Delivery Pass is present without attributes
    And customer is logged out

  Scenario:3 registered customer adds delivery pass - mobile
    And customer already has an account
    And user goes to Delivery Pass page
    But add to bag button is enabled

    Then user is on Delivery Pass page

    When customer adds Delivery Pass to bag
    Then Delivery Pass has been added to bag
    And add to bag button is disabled

    When user checks their bag
    Then Delivery Pass is present without attributes

  Scenario:4 Validate delivery pass page - mobile
    When anonymous user navigates to delivery pass page
    Then validate the delivery pass page
      | Sign up to Delivery Pass                                                                        |
      | Unlimited delivery & collection on all options including precise next day & evening deliveries. |
      | Only £9.99 a year                                                                               |
      | Add To Bag                                                                                  |
      | UK only - all delivery options are free                                                         |
      | By signing up you're agreeing to these terms and conditions                                     |

  Scenario:5 Add delivery pass - mobile
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And anonymous user navigates to delivery pass page
    And customer adds Delivery Pass to bag
    And navigate to bag page and secure checkout
    And Customer makes a purchase to collect CREDITCARD
    And order has been successful
    Then delivery pass is applied

	@anonymousMobile2Samsung
	Scenario:6 Add delivery pass - mobile -PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And anonymous user navigates to delivery pass page
		And customer adds Delivery Pass to bag
		And navigate to bag page and secure checkout
		And Customer makes a purchase to collect CREDITCARD
		And order has been successful
		Then delivery pass is applied
