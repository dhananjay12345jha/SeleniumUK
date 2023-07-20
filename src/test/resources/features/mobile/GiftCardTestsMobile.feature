@AnonymousMobile2
Feature: Gift card mobile
  As a Customer,
  I want to be able to redeem
  A gift card.

  Background:
    Given i navigate to "Newlook" home page

#  @AnonymousMobile
  Scenario:1 I try to check my Gift Card balance but my card is blocked - mobile
    And I have a blocked Gift Card
    And searches for random product on mobile
    And checkout a product and navigate to payment section on mobile
    And there is a option for Gift Card
    And I continue to payment
    And I expand Gift Card section
    And check balance of my Gift Card
    Then I wil get an error message

#  @AnonymousMobile
  Scenario:2 I try to check my Gift Card balance but with incorrect pin - mobile
    And I have a registered Gift Card
    And searches for random product on mobile
    And checkout a product and navigate to payment section on mobile
    And there is a option for Gift Card
    And I continue to payment
    And I expand Gift Card section
    And check balance of my Gift Card with incorrect pin
    Then I wil get an error message

#  @AnonymousMobile
  Scenario:3 I have a unregistered Gift Card but try to check my available balance - mobile
    And I have a unregistered Gift Card
    And searches for random product on mobile
    And checkout a product and navigate to payment section on mobile
    And there is a option for Gift Card
    And I continue to payment
    And I expand Gift Card section
    And check balance of my Gift Card
    Then I wil get an error message

#  @AnonymousMobile
  Scenario:4 As I'm using the French site I will not be given to use gift card fr - mobile
    And I click on the locale selector in the header
    And I select a different country country_France using the drop down
    And searches for random product on mobile
    When navigate to the payment page non uk on mobile
    Then there isn't a option for Gift Card



