@AnonymousMobile2
Feature: Staff discount tests

  Background:
    Given i navigate to "Newlook" home page
    When user registers and navigates to staff discount registration

	@RemoveStaffSubscription
  Scenario Outline:1 Employee will be registering himself with the staff card and will get the staff discount while doing a purchase.
    And register by entering "<Employee_ID>" and "<Staff_Card_Number>"
    Then registered card should be visible in the My Account Section
    And Select a product and navigate to payment page on mobile
    Then the staff discount should be applied on the checkout page
    Examples:
      | Employee_ID | Staff_Card_Number |
      | 1234567890  | 9826249999999812  |

	@RemoveStaffSubscription
  Scenario Outline:2 Staff registration button should not be enabled for the already registered user
    And register by entering "<Employee_ID>" and "<Staff_Card_Number>"
    Then registered card should be visible in the My Account Section
    And navigates to Staff Registration page
    Then the staff registration button should be disabled
    Examples:
      | Employee_ID | Staff_Card_Number |
      | 1234567890  | 9826249999999812  |

    #  @newlook @UserDependent
#  Scenario Outline:1 Employee will be registering himself with the staff card and will get the staff discount while doing a purchase.
#    Given i navigate to "Newlook" home page
#    When login with username "staffDiscountUserEmail" and password "password"
#    And navigates to Staff Registration page
#    And clicks the staff discount registration button
#    And register by entering "<Employee_ID>" and "<Staff_Card_Number>"
#    Then registered card should be visible in the My Account Section
#    When I checkout a product
#    Then the staff discount should be applied on the checkout page
#
#    Examples:
#      | Employee_ID | Staff_Card_Number |
#      | 1234567890  | 9826249999999812  |

#  @newlook @UserDependent
#  Scenario Outline:2 Staff registration button should not be enabled for the already registered user
#    Given i navigate to "Newlook" home page
#    When login with username "staffDiscountUserEmail" and password "password"
#    And navigates to Staff Registration page
#    And clicks the staff discount registration button
#    And register by entering "<Employee_ID>" and "<Staff_Card_Number>"
#    Then registered card should be visible in the My Account Section
#    And navigates to Staff Registration page
#    Then the staff registration button should be disabled
#
#    Examples:
#      | Employee_ID | Staff_Card_Number |
#      | 1234567890  | 9826249999999812  |
