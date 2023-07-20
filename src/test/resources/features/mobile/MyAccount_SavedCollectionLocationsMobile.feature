# Created by Gokhan.Ates at 04/01/19
@newlook @NewUsersMobileSamsung
Feature: My collection locations - mobile

  Background:
    Given i navigate to "Newlook" home page
    #And user provides required details to create new account on mobile


  Scenario:1 Validate my collection location page without any saved locations - mobile
    When user provides required details to create new account on mobile
    And customer navigated to my location page
    Then saved location should be empty

  Scenario Outline:2 Add a collection location and verify my collection location page - mobile
	  And feature "feature.pci.microform.enabled.uk-site" is switched off
	  When user provides required details to create new account on mobile
	  And searches for random product on mobile
	  And purchase a random product for collection on mobile <payment type> <card type> <card number>
	  Then order has been successful
	  When customer navigated to my location page
     Then location should be displayed in my collection location page
	  Examples:
		  | payment type | card type | card number       |
		  | paymentType_Card         |     Visa  |  4111111111111111 |

	Scenario Outline:3 Add a collection location and verify my collection location page - mobile - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for collection on mobile <payment type> <card type> <card number>
		Then order has been successful
		When customer navigated to my location page
		Then location should be displayed in my collection location page
		Examples:
			| payment type | card type | card number       |
			| paymentType_Card         |     Visa  |  4111111111111111 |
