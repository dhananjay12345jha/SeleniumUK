@newlook @UserDependent
Feature: Personal Details

  Background:
    Given i navigate to "Newlook" home page

  Scenario:1 Validate personal details page
    When login with username "myAccountPersonalDetailsUserEmail" and password "password"
    And Personal Details link is clicked
    Then User should see labels
      | personalDetailsAccountLabel |
      | Name                        |
      | Gender                      |
      | Date of Birth               |
      | Mobile Number               |
      | Email                       |
      | Password                    |

  Scenario:2 Validate personal details page buttons
    When login with username "myAccountPersonalDetailsUserEmail" and password "password"
    And Personal Details link is clicked
    Then User should see buttons
      | Update Personal Details |
      | Update email            |
      | Update password         |

  Scenario Outline:3 Update the personal details in personal details page
    When login with username "myAccountPersonalDetailsUserEmail" and password "password"
    And Navigate to "Personal_Details" pages
    And updated <field> in and UPDATE PERSONAL DETAILS and save changes
    Then Personal details <field> should update
    Examples:
      | field        |
      | firstName    |
      | lastName     |
      | gender       |
      | mobileNumber |

  @NLRD-2295 @newlook
  Scenario Outline:4 Newsletter opt-in checkbox should not be present on the users My Account and Checkout pages
    When login with username "myAccountPersonalDetailsUserEmail" and password "password"
    And Navigate to "<pages>" pages
    Then Newsletter opt-in checkbox should not be present
    Examples:
      | pages            |
      | Personal_Details |
      | Checkout         |
