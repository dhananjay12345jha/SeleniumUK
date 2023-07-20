# 06/12/17
@firefox @Anonymous2 @newlook
Feature: alert pop up in input fields

  Background:
    Given i navigate to "Newlook" home page

  Scenario:2 search
    When search site with string <script>alert('test');</script>
    Then alert should not appear

  Scenario:3 sign in
    And Navigate to singIn page
    When enter alert script while sign in
    Then alert should not appear

  Scenario:4 registration
    When user provides required details to create new account
    Then alert should not appear

  @OMSF_Bug @OMSF-1023
  #OMSF-1023 bug raised to land on contact preference page after creation of new account on OMS environment
  Scenario:5 save address
    When user provides required details to create new account
    Then user is taken to contact preferences page
    And User navigate to address book page
    And find and add new address with pop up script in input field
    Then alert should not appear

  Scenario:6 save payment card
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account
    And user checkout a product
    And save payment card details with no <script>alert('test');</script>
    Then alert should not appear

  Scenario:7 Add item to basket and checkout as logged in user for collection
    When user provides required details to create new account
    And I search for a product
    And click on a product image or title
    And select size of the product
    And I click Add to Bag
    And click on the bag icon
    And i click on checkout button
    And search for nearest collection location as popup alert script
    Then alert should not appear

  Scenario:8 find store
    When I click on the locale selector in the header
    And I select a different country country_France using the drop down
    And I can click the link of store locator
    And I enter a search term "<script>alert('test');</script>"
    And I click on Find Stores CTA
    Then alert should not appear
