@Anonymous2
Feature: Gift card
  As a Customer,
  I want to be able to redeem
  A gift card.

  Background:
    Given i navigate to "Newlook" home page

  @OMSF_Bug @OMSF-995
  Scenario:2 I check my Gift Card balance but this is currently at zero
    And I have a zero balance Gift Card
    And I'm on the UK checkout page
    And I continue to payment
    And there is a option for Gift Card
    And I expand Gift Card section
    And check balance of my Gift Card
    Then my Gift Card balance is displayed
    But I can not apply Gift Card

  Scenario:3 I try to check my Gift Card balance but my card is blocked
    And I have a blocked Gift Card
    And I'm on the UK checkout page
    And I continue to payment
    And there is a option for Gift Card
    And I expand Gift Card section
    And check balance of my Gift Card
    Then I wil get an error message

  Scenario:4 I try to check my Gift Card balance but with incorrect pin
    And I have a registered Gift Card
    And I'm on the UK checkout page
    And I continue to payment
    And there is a option for Gift Card
    And I expand Gift Card section
    And check balance of my Gift Card with incorrect pin
    Then I wil get an error message

  Scenario:5 I have a unregistered Gift Card but try to check my available balance
    And I have a unregistered Gift Card
    And I'm on the UK checkout page
    And I continue to payment
    And there is a option for Gift Card
    And I expand Gift Card section
    And check balance of my Gift Card
    Then I wil get an error message

  Scenario:6 As I'm using the French site I will not be given to use gift card fr
    And I click on the locale selector in the header
    And I select a different country country_France using the drop down
    And I'm on the FR checkout page
    When I continue to payment
    Then there isn't a option for Gift Card



