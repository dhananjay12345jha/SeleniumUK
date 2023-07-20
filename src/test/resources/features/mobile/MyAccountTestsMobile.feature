@newlook @UserDependentMobile
Feature: My account tests

  Background:
    Given i navigate to "Newlook" home page

  Scenario: 1 As a customer I want to logout of my account
    And login with username "myAccountPersonalDetailsUserEmail" and password "password" on mobile
    When user logs out on mobile
    Then I should be logged out from the account

  Scenario: 2 As a Customer, I want to see a message when attempting to delete a saved address, so that I can verify I want to take this action
    And user provides required details to create new account on mobile
    And Navigate to my address page
    And Added a new address
    When Delete existing address is selected
    Then A pop up with delete message and option with delete or cancel displayed
      | Delete Address                                       |
      | Delete this address?                                 |
      | The following address will be deleted from your list |
      | Delete Address                                       |
      | Cancel                                               |

  @MobileSmokePack
  Scenario: 3 Delete saved address mobile
    And user provides required details to create new account on mobile
    And User navigate to address book page on mobile
    And find and add new address using post code E6 2HE
    And Navigate to my address page
    When Deleted existing address
    Then Address should be removed from my addresses

  Scenario: 4 Add new address to my account
    And user provides required details to create new account on mobile
    And Navigate to my address page
    When Added a new address
    Then The address should be displayed in the my addresses
    And Deleted existing address

  Scenario: 5 Validate My Account page
    And login with username "myAccountPersonalDetailsUserEmail" and password "password" on mobile
    Then User should navigate to my account page and see links to
      | My Account              |
      | MY ORDERS               |
      | MY ADDRESSES            |
      | MY PAYMENT CARDS        |
      | MY STORE CARDS          |
      | PERSONAL DETAILS        |
      | SAVED COLLECTION POINTS |
      | MY SAVED ITEMS          |
      | DELIVERY PASS           |
      | Recent Orders           |
      | VIEW ALL MY ORDERS      |

  Scenario Outline: 6 Validate My Account page Links
    And login with username "myAccountPersonalDetailsUserEmail" and password "password" on mobile
    When User click the <icons> link
    Then User should navigate to <icons> links
    Examples:
      | icons                   |
      | MY ORDERS               |
      | MY ADDRESSES            |
      | MY PAYMENT CARDS        |
      | PERSONAL DETAILS        |
      | SAVED COLLECTION POINTS |
      | MY SAVED ITEMS          |
      | DELIVERY PASS           |

  @OMSF_Bug @OMSF-1023
  #OMSF-1023 bug raised for search item issue for suggestion list on OMS environment
  Scenario: 7 Navigate to my contact preference and set all radio buttons to yes and then assert
    And feature "feature.account.contactpreferences.enabled" is switched on
    And login with username "emailAddress" and password "password" on mobile
    And I navigate to my contact preferences page
    And I set the radio buttons to yes and save the changes
    Then a saved alert item is displayed
    And all the radio buttons have been set to Yes

  Scenario: 8 Navigate to my contact preference and set all radio buttons to no and then assert
    And feature "feature.account.contactpreferences.enabled" is switched on
    And login with username "emailAddress" and password "password" on mobile
    And I navigate to my contact preferences page
    And I set the radio buttons to no and save the changes
    Then a saved alert item is displayed
    And all the radio buttons have been set to No
