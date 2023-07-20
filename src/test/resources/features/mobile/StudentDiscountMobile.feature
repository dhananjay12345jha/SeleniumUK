@AnonymousMobile2
Feature: Student Discount/Unidays Coupon
	As a good Student,
	I want to be able to provide my Student ID
	So I get a 10% discount.

	Background:
		Given i navigate to "Newlook" home page


	Scenario Outline:1 Anonymous student adds their student ID to get discount - Mobile
		And anonymous student has added item to their bag
		When <valid> student ID is applied
		Then a student discount of 10% is applied to subtotal
		Examples:
			| valid  |
			| valid  |
			| spaces |

	Scenario Outline:2 anonymous student adds invalid student ID to get discount - Mobile
		And anonymous student has added item to their bag
		When <Invalid> student ID is applied
		Then invalid promo code error is displayed
		Examples:
			| Invalid   |
			| invalid   |
			| repeating |

	Scenario Outline:4 student adds a locked NUS card ID - Mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And user provides required details to create new account on mobile
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

	Scenario Outline:5 student adds a locked NUS card ID - Mobile - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And user provides required details to create new account on mobile
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

	Scenario:6 anonymous student adds a locked NUS card ID and signs in - Mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And login with username "studentDiscountUserEmail" and password "password" on mobile
		And anonymous student has added item to their bag
		When locked student ID is applied
		Then locked promo code error is displayed

	Scenario:7 anonymous student adds a locked NUS card ID and signs in - Mobile - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And login with username "studentDiscountUserEmail" and password "password" on mobile
		And anonymous student has added item to their bag
		When locked student ID is applied
		Then locked promo code error is displayed
