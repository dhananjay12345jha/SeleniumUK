@Anonymous
Feature: Search feature

  Background:
    Given i navigate to "Newlook" home page

  @newlook @smokepack @Ignore #Used for the first version of the MegaNav
  Scenario Outline:1 word suggestions
    When  search box should have default text place holder as search
    And typing '<Keyword>' in the search box
    Then should see suggestions related to <Keyword> and category <category>
    Examples:
      | Keyword  | category |
      | bea      | beauty   |
      | asdfsafa |          |

  @newlook @allure
  Scenario:2 place holder search
    Then search box should have default text place holder as search

  Scenario Outline: 3 home page search - did you mean
    And I search for the product with "<searchTerm>"
    Then it gives an did you mean option with text "<didYouMean>"
  Examples:
    | searchTerm | didYouMean |
    | jeens      | jean    |

  Scenario: 4 home page search - autosuggest UK
    And typing 'deni' in the search box
    Then item text suggestion is displayed "Denim jacket"

  Scenario:5 home page search - autosuggest FR
    When I click on the locale selector in the header
    And I select a different country country_France using the drop down
    And typing 'deni' in the search box
    Then item text suggestion is not displayed "Denim jacket"

  @NLDS-105 @OMSF_Bug @OMSF-1023
  #OMSF-1023 bug raised for search item issue for suggestion list on OMS environment
  Scenario Outline: 6 home page search rules
    And feature "feature.autosuggest.update.enabled.uk-site" is switched off
    And typing '<searchTerm>' in the search box
    Then item text suggestion is displayed "<contains>"
    Then item text suggestion is not displayed "<notContains>"
    Examples:
      | searchTerm | contains | notContains |
      | TOP        | top      | tops        |
      | Girls      | girls    | girl        |
      | Scarf      | scarf    | scarves     |
      | Leggings   | leggings | legging     |

  Scenario:7 Click Search icon when search is already open (no text entered)
    When I click on search field
    And I click on search icon
    Then the searched plp headline equals No search text provided

  Scenario:8 Click Search icon when search is already open (text entered)
    And I click on search field
    And typing 'shoes' in the search box
    And I click on search icon
    Then the searched plp headline equals You searched for "shoes"
