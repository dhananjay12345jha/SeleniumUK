# 02/11/17
@web @newlook @UserDependent
Feature: SignIn

  Background:
    Given i navigate to "Newlook" home page

  Scenario:1 Validate the SignIn page
    When Navigate to singIn page
    Then Validate singIn page
      | Email Address       |
      | Forgotten password? |
      | Password            |
      | Returning Customers |
      | Sign In             |
      | Show                |

  Scenario:2 SingIn using correct user credentials
    And Navigate to singIn page
    When Correct user credentials are entered
    Then User should navigate to my account

  Scenario Outline:3 SingIn using wrong user credentials
    And Navigate to singIn page
    When Wrong <credentials> entered
    Then User should see error message for wrong <credentials>
    Examples:
      | credentials   |
      | wrongUser     |
      | wrongPassword |
      | noUser        |
      | noPassword    |
