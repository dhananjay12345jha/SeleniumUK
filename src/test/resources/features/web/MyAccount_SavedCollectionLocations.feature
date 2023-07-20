# 18/12/17
@newlook @NewUsers
Feature: My collection locations

  Background:
    Given i navigate to "Newlook" home page
    And user provides required details to create new account


  Scenario:1 Validate my collection location page without any saved locations
    When customer navigated to my location page
    Then saved location should be empty

  Scenario:2 Add a collection location and verify my collection location page
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    And Select a product and navigate to payment page
    And Customer makes a purchase to collect CREDITCARD
    When customer navigated to my location page
    Then location should be displayed in my collection location page

	Scenario:3 Add a collection location and verify my collection location page - PCI micro forms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And Select a product and navigate to payment page
		And Customer makes a purchase to collect CREDITCARD
		When customer navigated to my location page
		Then location should be displayed in my collection location page
