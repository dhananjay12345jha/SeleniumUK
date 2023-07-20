@Anonymous2
Feature: Student Discount/Unidays Coupon
	As a good Student,
	I want to be able to provide my Student ID
	So I get a 10% discount.

	Background:
		Given i navigate to "Newlook" home page

	Scenario Outline:1 Anonymous student adds their student ID to get discount
		And anonymous student has added item to their bag
		When <valid> student ID is applied
		Then a student discount of 10% is applied to subtotal
		Examples:
			| valid  |
			| valid  |
			| spaces |

	Scenario Outline:2 anonymous student adds invalid student ID to get discount
		And anonymous student has added item to their bag
		When <Invalid> student ID is applied
		Then invalid promo code error is displayed
		Examples:
			| Invalid   |
			| invalid   |
			| repeating |

#  @flaky @needed todo
#  Scenario:3 anonymous student adds a locked NUS card ID
#    And customer already has an account
#    And registered student has added item to their bag
#    When valid student ID is applied
#    And Customer placed order
#    And pays by card
#    And order has been successful
#    Then student ID is associated with the account
#    But anonymous student has added item to their bag
#    When locked student ID is applied
#    Then locked promo code error is displayed

	Scenario Outline:4 student adds a locked NUS card ID
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And user provides required details to create new account
		And registered student has added item to their bag
		And valid student ID is applied
		And Customer placed order
		And pays by <payment type> <card type> <card number>
		And order has been successful
		And anonymous student has added item to their bag
		When locked student ID is applied
		Then locked promo code error is displayed
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |

	Scenario Outline:5 student adds a locked NUS card ID -- PCI microforms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And user provides required details to create new account
		And registered student has added item to their bag
		And valid student ID is applied
		And Customer placed order
		And pays by <payment type> <card type> <card number>
		And order has been successful
		And anonymous student has added item to their bag
		When locked student ID is applied
		Then locked promo code error is displayed
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_1 |

	Scenario:6 anonymous student adds a locked NUS card ID and signs in
		And login with username "studentDiscountUserEmail" and password "password"
		And I clear the bag
		And anonymous student has added item to their bag
		When locked student ID is applied
		Then locked promo code error is displayed

#  @needed "todo
#  Scenario:6 anonymous student adds a locked NUS card ID and registers
#    Given i navigate to "Newlook" home page
#    And anonymous student has added item to their bag
#    When valid student ID is applied
#    Then a student discount of 10% is applied to subtotal
#    When customer registers from checkout page
#    Then student ID is associated with the account
#    When anonymous student has added item to their bag
#    And locked student ID is applied
#    Then locked promo code error is displayed
