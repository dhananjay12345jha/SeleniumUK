@Anonymous
Feature: Search auto suggest

  Background:
    Given i navigate to "Newlook" home page
    And feature "feature.autosuggest.update.enabled.uk-site" is switched on

  @newlook
    Scenario: 1 - Click on search box and click on a category match item
    And login with username "becca@me.com" and password "test123" non system property
    And I click on search field
    And click on auto trend item "Boots"
#    And url contains "searchType=category&searchTerm=Boots&searchSuggestion=dresses&searchPos=1&searchTab=Womens"
   Then the searched plp headline equals You searched for "boots"

  Scenario: 2 - Click on search box and click on not a category match item
    And login with username "becca@me.com" and password "test123" non system property
    And I click on search field
    And click on auto trend item "Bodysuit"
    Then the searched plp headline equals You searched for "bodysuit"

   Scenario: 3 - Type dresses and click on dresses(category match) will take you to dresses category plp
     And login with username "becca@me.com" and password "test123" non system property
     And I click on search field
     And typing 'dresses' in the search box
     And click on auto suggestion item "Dresses"
     And url contains "searchType=auto&searchTerm=dresses&searchSuggestion=dresses&searchPos=1&searchTab=Womens"
     Then the searched plp headline equals You searched for "dresses"

  Scenario: 4 - Type a dresses and click on a pdp item takes you to pdp page
    And login with username "becca@me.com" and password "test123" non system property
    And I click on search field
    And typing 'dresses' in the search box
    And I click on a dress image
    And url contains "searchType=product&searchTerm=dresses&searchPos=1&searchTab=Womens"
    Then I should be navigated to the full PDP page

  Scenario: 5 - Type dress and click on auto suggestion will take you to plp for search item
    And login with username "becca@me.com" and password "test123" non system property
    And I click on search field
    And typing 'dress' in the search box
    And click on auto suggestion item "Dress"
    And url contains "searchType=auto&searchTerm=dress&searchSuggestion=dress&searchPos=2&searchTab=Womens"
    Then the searched plp headline equals You searched for "dress"

  Scenario: 6 - Enter search text and click enter
    And login with username "becca@me.com" and password "test123" non system property
    And I click on search field
    And typing 'dress' in the search box
    And press enter from the keyboard on search
    Then the searched plp headline equals You searched for "dress"

#  @NLDS-582
  Scenario: 7 - Search term entered with minimum characters not met
    And login with username "becca@me.com" and password "test123" non system property
    And I click on search field
    And typing 'd' in the search box
    And typing 'r' in the search box
    And item text suggestion is not displayed "dress"
    And typing 'e' in the search box
    And click on auto suggestion item "DreSs"
    Then the searched plp headline equals You searched for "dress"

  Scenario: 8 - Search for item and the search type should be freetext
    And login with username "becca@me.com" and password "test123" non system property
    And I click on search field
    And I search for the product with "dress"
    Then url contains "searchType=freetext&searchTab=Womens"

  Scenario: 9 - Search an item in a department and results should be filtered according to selected department
    And I click on search field
    And I click on "MENS" department
    And I search for the product with "Jeans"
    Then The selected filter is equal "MENS"

  Scenario: 10 - Search All Results in a department and results should be filtered according to selected department
    And I click on search field
    And I click on "TEENS" department
    And typing 'Dress' in the search box
    And I scroll down
    And I click on See All Results
    Then The selected filter is equal "TEENS"

  Scenario: 11 - Switch between different search headings
    And I click on search field
    Then when the below department is clicked, then the below department is displayed
      | MENS        |
      | WOMENS      |
      | TEENS       |
      | Home&Living |

  Scenario: 12 - Previously viewed dept is displayed as active dept without refreshing the page
    And I click on search field
    And I click on "TEENS" department
    And I search for the product with "Jeans"
    And click on a product image or title
    And user clicks on breadcrumb '<Jeans>'
    And I click on search field
    Then highlighted department should be equals to clicked department "TEENS"