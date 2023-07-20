@newlook @Anonymous2
Feature: Shop Multi PDP Information

  Background:
    Given i navigate to "Newlook" home page
    When search and select the product "353844010" with the perfect partner

  Scenario:1) Check that only primary product on Multi-PDP has Product Details & Care Guide information
    And click on the searched product name
    Then I should see Product details and Care Guide


  Scenario:2) click Show more
    And click on the searched product name
    And click on show more to expand
    Then product info details exists

  Scenario:3) click Show less
    And click on the searched product name
    And click on show more to expand
    Then click on show less to collapse

#  NLDS-2021
#  Scenario:4) can write a review from main product
#    And click on the searched product name
#    And click on write a review link
#    Then the review popup exists

#  NLDS-2021
#  Scenario:5) read a review
#    And click on the searched product name
#    Then a review exists

#  NLDS-2021
#  Scenario:6) review is only for primary product
#    And click on the searched product name
#    Then review is only for main product

  Scenario Outline:7)Check that email is pre-populated for registered user when pre-orderable and back-orderable box are presented and normal PDP
	 And feature "feature.storefront.stock.urgency.messaging.pdp" is switched off
    And login with username "multiPdpUserEmail" and password "password"
    And search and select the product "363066817" with the perfect partner
    And click on the searched product name
    And select size of the product "<Item Size>"
    Then the preOrder notification textbox is populated with email address
    Examples:
      | Item Size                    |
      | UK 12 - Sold out, more soon! |
      | UK 10                        |

  Scenario Outline:8)Check that email is not populated for unsigned user when pre-orderable and back-orderable box are presented and normal PDP
	 And feature "feature.storefront.stock.urgency.messaging.pdp" is switched off
    And search and select the product "363066817" with the perfect partner
    And click on the searched product name
    And select size of the product "<Item Size>"
    Then the preOrder notification textbox is not populated with email address
    Examples:
      | Item Size                    |
      | UK 12 - Sold out, more soon! |
      | UK 10                        |
#
  Scenario: 9)Check that quick view of Multi-PDP product shows in side panel as single products.
    Given feature "feature.storefront.experience.desktop.quickview" is switched off
    And click on quick view link
    Then "Blue Daisy Jacquard Mini Skirt" does not exist in the page


  Scenario: 10) Save Multi Pdp items and view side pane
    And click on the searched product name
    And save 3 multi pdp items
    And viewing the saved items side pane
    Then 3 saved items will be present


  Scenario: 11) Save Multi Pdp items amd view full page
    And click on the searched product name
    And save 3 multi pdp items
    And viewing saved items on full page
    Then my saved items page has 3 products and "MOVE ALL TO BAG" button

  Scenario: 12) Save and then remove remove Multi Pdp items and view side pane
    And click on the searched product name
    And save 3 multi pdp items
    And remove 2 saved multi pdp items
    And viewing the saved items side pane
    Then 1 saved items will be present

  Scenario: 13) Save and then remove Multi Pdp items amd view full page
    And click on the searched product name
    And save 3 multi pdp items
    And remove 1 saved multi pdp items
    And viewing saved items on full page
    Then my saved items page has 2 products and "MOVE ALL TO BAG" button

  Scenario Outline: 14) Check if the stock information for the searched result displays on the side-panel for multi pdp
	 And feature "feature.storefront.stock.urgency.messaging.pdp" is switched off
    And click on a product image or title
    And there are "4" find in store buttons
    And select size of the product "S"
    And click on the store lookup button
    And switch to side panel
    And enter the correct search "<ValidCriteria>" criteria and search
    Then the the stock information for the searched result displays
    Examples:
      |ValidCriteria|
      |London|

  Scenario: 15) Add second multi pdp item to bag and assert confirmation message
    And click on the searched product name
    And add the 1 product to the bag
    Then a confirmation message is displayed
