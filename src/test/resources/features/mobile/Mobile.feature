@MobileSmokePack @NewUsersMobile
Feature: Mobile smoke pack

  Background:
    Given i navigate to "Newlook" home page

  Scenario: PDP mobile :I should be able to close the zoom overlay window by clicking on X and return back to PDP page
    And searches for random product on mobile
    And click on a product image or title
    And click on product image
    When clicked on X of the page over lay
    Then I should navigate back to PDP page

  Scenario: Create new account on mobile
    When user provides required details to create new account on mobile
    Then new account should be create and user should navigate to my account page

  Scenario:Delivery pass mobile : anonymous customer registers to add delivery pass
    And anonymous user navigates to delivery pass page
    But add to bag button is disabled
    When customer registers
    Then user is on Delivery Pass page
    And add to bag button is enabled
    When customer adds Delivery Pass to bag
    Then Delivery Pass has been added to bag
    And add to bag button is disabled
    When user checks their bag
    Then Delivery Pass is present without attributes

  Scenario Outline:Store locator mobile : Country is prepopulated in the store locator page
    And I click on the locale selector in the header
    And I select a different country <country> using the drop down
    When I can click the link of store locator on mobile
    And my "<expText>" is preset for me underneath
    And click on change country link
    Then the selected "<expText>" should be prepopulate in the drop down list on mobile
    Examples:
      | country | expText     |
      | country_Germany | Deutschland |

  Scenario: Plp -  when scrolling down the filter should be hidden
    And searches for product on mobile searchProduct_Jeans
    And I scroll down
    Then I should not see the filter

  Scenario: Plp -  when scrolling down and then scrolling up the filter should not be hidden
    And searches for product on mobile searchProduct_Jeans
    And I scroll down
    And I scroll up
    Then I should see the filter
