@NewUsersMobile
Feature: Create New Account mobile
  As a new customer
  I want to create a new account in NewLook website

  Background:
    Given i navigate to "Newlook" home page

  Scenario: Validate create an account form
    When Navigate to create account page on mobile
    Then Validate create your account form
      | Create Your Account                                                                                                                      |
      | Faster checkout Save your bag See the status of all your orders                                                                          |
      | Email Address                                                                                                                            |
      | Password                                                                                                                                 |
      | Show                                                                                                                                     |
      | Use 7 or more characters with at least 1 number                                                                                          |
      | Title                                                                                                                                    |
      | First Name                                                                                                                               |
      | Last Name                                                                                                                                |
      | CREATE MY ACCOUNT                                                                                                                        |

  @MobileSmokePack
  Scenario: Create new account on mobile
    Given feature "feature.create.consent.principal.enabled" is switched on
    When user provides required details to create new account on mobile
    Then new account should be create and user should navigate to my account page


  Scenario: Validate age checkbox exists
    When Navigate to create account page on mobile
    Then over 18 checkbox is displayed

  @Ignore
  # The contact preferences no longer appear here
  Scenario: Validate create an account contact preferences
    And feature "feature.display.contact.preferences.enabled" is switched on
    When Navigate to create account page on mobile
    Then Validate create your account form
      | Tick here to be the first to hear about the latest trends, new arrivals and exclusive offers by e-mail. You can unsubscribe at any time. |

	@OMSF_Bug @OMSF-1023
  Scenario: Validate create an account consent principal
    And feature "feature.create.consent.principal.enabled" is switched off
    When consent principal is switched on, user provides required details to create new account on mobile
    Then user is taken to contact preferences page
