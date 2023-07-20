@AnonymousMobile
Feature: Mobile Search feature

  Background:
    Given i navigate to "Newlook" home page

  Scenario:1 Mobile search place holder
    Then mobile search box should have default text place holder as search

  Scenario Outline: 2 Mobile home page search - did you mean
    And searches for product on mobile <searchTerm>
    Then it gives an did you mean option with text "<didYouMean>"
  Examples:
    | searchTerm | didYouMean |
    | searchProduct_Jeens      | jean    |

  Scenario: 3 home page search - autosuggest UK - mobile
    And I click on search icon on mobile
    And type "deni" in the search box on mobile
    Then item text suggestion is displayed "Denim jacket"

  Scenario:4 home page search - autosuggest FR - mobile
    When I click on the locale selector in the header
    And I select a different country country_France using the drop down
    When I can click the link of store locator on mobile
    And I click on search icon on mobile
    And type "deni" in the search box on mobile
    Then item text suggestion is not displayed "Denim jacket"
