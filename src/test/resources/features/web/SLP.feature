@Anonymous
Feature: SLP feature

  Background: I go to New Looks home page
    Given i navigate to "Newlook" home page

  @newlook @nlrd-2555
  Scenario:1a Clicking on the Price and move the price slider from both sides. Validate if the filter criteria updates as per the price slider values SLP
    And I search for the product with the specified criteria "Jeans"
    And select the sorting criteria as "PRICE"
    Then price slider can be moved from left and right

  @newlook @nlrd-2555
  Scenario:1b Product results changes after moving the price slider from both sides. SLP
    And I search for the product with the specified criteria "Jeans"
    And select the sorting criteria as "PRICE"
    Then total displayed products changes after moving the price slider from both sides

  @newlook  @smokepack
  Scenario:1 sort and filter at a time SLP
    When product search with string top should show min 2 matched results
    When user select sort option 'Price Low - High'
    Then the products should be displayed in price ascending order
    And select option from filter
      | 1 |
    When user select sort option 'Price High - Low'
    Then the products should be displayed in price descending order
    When user select sort option 'Bestsellers'
    Then the value menu is closed automatically
    And select option from filter
      | 2 |
    Then plp page should show correct filtered attributes

  @newlook
  Scenario:2 SLP product dept filters
    When product search with string shirt should show min 10 matched results
    Then plp page should show below departments
      | TEENS        |
      | ALL PRODUCTS |

  @newlook
  Scenario Outline: 3 sorting SLP
    When product search with string top should show min 2 matched results
    When user select sort option '<sort>'
    Examples:
      | sort             |
      | Price High - Low |
      | Price Low - High |
      | Bestsellers      |


  @newlook
  Scenario:4 select multiple sizes at time SLP
    When product search with string top should show min 2 matched results
    And Select filter all products from search filter list
    And select option from filter
      | 1 |
      | 2 |
    Then selected facet filters should match with filters displayed


  @newlook
  Scenario:5 SLP items details
    When product search with string belt should show min 2 matched results
    Then all the plp items should have correct product details

#todo
#  @newlook
#  Scenario:6 only one facet displayed at a time SLP
#    And I search for the product with the specified criteria "Jeans"
#    When I select a single-select facet sortby
#    Then I am unable to expose another facet at the same time
#    And if I select another facet to expose
#    Then any already-exposed facets will close

  @newlook
  Scenario:7 Applied facet values can be cleared by using clear link and the set returns to the unrefined state displaying all the products SLP
    And I search for the product with the specified criteria "Jeans"
    When I select a single-select facet Size
    And select option from filter
      | 1 |
    #And select a size from the displayed facet values
    When I review the facet bar/element and the facets I have currently applied to the product set
    Then I am able to remove all applied facets from the product set using clear link displayed alongside these
    And the product set returns to an un-refined state and all products in the listing are displayed

  @newlook
  Scenario:8 The products should narrow down when I unselect any on the value form multi-select facet value SLP
    And I search for the product with the specified criteria "Jeans"
    When I select a single-select facet colour
    And have selected multiple values in that facet
    When I re-click the value
    Then there are other values selected
    And the product set narrows to products that match the remaining selected values

  @newlook
  Scenario:11 Load next button should load next available product images SLP
    When I click on the locale selector in the header
    And I select a different country country_USA using the drop down
    When Navigate to Womens department from mega menu
    And click on load next button
    Then next products will be displayed

  @newlook
  Scenario:12 As a customer when I select Sort By facet then I should see list of available options SLP
    And I search for the product with the specified criteria "Jeans"
    When I select a single-select facet sortby
    Then I am presented with a list of options on which to sort the product set in view

  @newlook
  Scenario:13 As a Customer, when the select department facet form plp page then the bin value of the facet should reflect the total results SLP
    When product search with string jumper should show min 10 matched results
    And Select filter all products from search filter list
    Then plp page should show below departments
      | TEENS        |
      | ALL PRODUCTS |
    Then I am presented with the product listing by default
    And each facet option has a bin count
    And I can select a facet to refine the product listing to exclude the other departments

     #depends on no of products- data set up configuration required to enable view all option
  @newlook
  Scenario:14 Load all button should load all available product images for that category SLP
    When I click on the locale selector in the header
    And I select a different country country_USA using the drop down
    And I search for the product with the specified criteria "Shoes"
    And click on load all button
    Then all products will be displayed

  @newlook @NLDS-505
  Scenario:18  PLP pagination beyond 1 pages SLP
    And I search for the product with the specified criteria "Shoes"
    And click on load next button
    Then next products will be displayed
    And url contains "page=1"

  Scenario: 19 - Click on back to top button on SLP
    And I search for the product with "dress"
    And click on load next button
    And I click on back to top button
    Then I am taken to the top of PLP page

  Scenario: 20 - Articles and info has been removed from SLP
    And I search for the product with "dress"
    Then articles and info has been removed from search filter

  Scenario:21 View product from PLP
    When Navigate to Mens department from mega menu
    Then all the plp items should have correct product details
	 And select a random product from PLP
    And I should be navigated to the full PDP page

   Scenario:22 View product from SLP
    And I search for the product with the specified criteria "Jeans"
    Then all the plp items should have correct product details
