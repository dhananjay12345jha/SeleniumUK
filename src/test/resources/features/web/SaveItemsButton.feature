# 06/03/18

#@feature.storefront.storeStockLookup.heartIcon.enabled
Feature: Save items heart icon

#  depending if feature is on or off it will run the method
  Scenario: Verify that save item heart icon is displayed in pdp page
    Given Customer navigates to the Newlook website
    When customer search for product productCode_stock and navigates to pdp page
    Then saved items heart icon should be displayed

#Skip when the feature is switched off/on
  Scenario: Verify that save item heart icon is displayed in pdp page
    Given feature "feature.storefront.storeStockLookup.heartIcon.enabled" is switched on
    And Customer navigates to the Newlook website
    When customer search for product productCode_stock and navigates to pdp page
    Then saved items heart icon should be displayed
