	@newlook @NewUsersMobile
Feature: My account Saved addresses Mobile

  Background:
    Given i navigate to "Newlook" home page
    When user provides required details to create new account on mobile
    And User navigate to address book page on mobile
    And find and add new address using post code E6 2HE


  Scenario:1 I First address should be default - mobile
    And Select a product and navigate to payment page on mobile
    Then checkout default delivery address should be with post code E6 2HE

  Scenario:2 second saved address should not impact current default - mobile
    And find and add new address using post code E12 6JH
    And Select a product and navigate to payment page on mobile
    Then checkout default delivery address should be with post code E6 2HE

  Scenario: 3 change default with existing bag - mobile
    And find and add new address using post code E12 6JH
    And Select a product and navigate to payment page on mobile
    And checkout default delivery address should be with post code E6 2HE
    And clear bag item from checkout page on mobile
    And User navigate to address book page on mobile
    And set address with post code E12 6JH as default
    And user relogins in to account on mobile
    And Select a product and navigate to payment page on mobile
    Then checkout default delivery address should be with post code E12 6JH

  Scenario: 4 change default start a new bag - mobile
    And find and add new address using post code E12 6JH
    And Select a product and navigate to payment page on mobile
    And checkout default delivery address should be with post code E6 2HE
    And clear bag item from checkout page on mobile
    And User navigate to address book page on mobile
    And set address with post code E12 6JH as default
    And user relogins in to account on mobile
    And Select a product and navigate to payment page on mobile
    Then checkout default delivery address should be with post code E12 6JH








