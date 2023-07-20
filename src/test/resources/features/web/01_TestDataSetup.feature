@web @htest @todo
Feature: Gift card top-up
  As a Chopin user,
  I want to add the balance to the Giftcard
  So that the card will always has positive balance

  Scenario Outline: Chopin Login
    Given Anonymous user goes to chopin site
    And  logins with the valid "acntest001" and "Newlook4$"
    And  The page to topup the giftcard balance should be displayed
    When he searches the gift_card "<GiftCard_Serial>"
    And cliks in select button
    And cliks on the Adjust Balance link
    Then gift card is topped up with "<Amount>"

    Examples:
      | GiftCard_Serial | Amount |
      | 2010001032      | 250    |
      #| 2010001033      | 1      |
