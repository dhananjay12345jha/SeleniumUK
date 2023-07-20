@AnonymousMobileSamsung
Feature: My account cards - mobile

  Background:
    Given i navigate to "Newlook" home page


#  @NewUsersMobile
  Scenario:1 save payment card and check my account card section - mobile
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And searches for random product on mobile
    And checkout a product and navigate to payment section on mobile
    And save payment card details with no 4111111111111111
    And user places order
    Then My Account payment cards section should have card with ending "1111" on mobile

	Scenario:2 save payment card and check my account card section - mobile - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And checkout a product and navigate to payment section on mobile
		And save payment card details with no 4111111111111111
		And user places order
		Then My Account payment cards section should have card with ending "1111" on mobile


#  @NewUsersMobile
  Scenario:3 save store card and check my account store card section - mobile
    And feature "feature.payment.storecards.management.enabled" is switched on
    When user provides required details to create new account on mobile
    And searches for random product on mobile
    And checkout a product and navigate to payment section on mobile
    And save store card details with no 9826220039999997
	 And user places order
    Then My Account store cards section should have card with ending "9997" on mobile

#  @NewUsersMobile
  Scenario:4 save payment card and confirm the card is saved in checkout page - mobile
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And searches for random product on mobile
    And checkout a product and navigate to payment section on mobile
    And save payment card details with no 4111111111111111
	 And user places order
    Then My Account payment cards section should have card with ending "1111" on mobile
    When searches for random product on mobile
    And checkout a product and navigate to payment section on mobile
    Then checkout page should show default payment card ending 1111

	Scenario:5 save payment card and confirm the card is saved in checkout page - mobile - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And checkout a product and navigate to payment section on mobile
		And save payment card details with no 4111111111111111
		And user places order
		Then My Account payment cards section should have card with ending "1111" on mobile
		When searches for random product on mobile
		And checkout a product and navigate to payment section on mobile
		Then checkout page should show default payment card ending 1111

#  @NewUsersMobile
  Scenario:6 save store card and check my account card is saved in checkout page - mobile
    And feature "feature.payment.storecards.management.enabled" is switched on
    When user provides required details to create new account on mobile
    And searches for random product on mobile
    And checkout a product and navigate to payment section on mobile
    And save store card details with no 9826220039999997
	 And user places order
    Then My Account store cards section should have card with ending "9997" on mobile
    When searches for random product on mobile
    And checkout a product and navigate to payment section on mobile
    And checkout page should show default store card saying New Look Store Card ending 9997

#  @UserDependentMobile
  Scenario:7 save both payment and store card and check my account cards - mobile
    And feature "feature.payment.storecards.management.enabled" is switched on
    When login with username "myAccountCardsUserEmail" and password "password" on mobile
    And searches for random product on mobile
    And checkout a product and navigate to payment section on mobile
	 And user places order
    Then My Account payment cards section should have card with ending "1111" on mobile
    And My Account store cards section should have card with ending "9997" on mobile

#  @UserDependentMobile
  Scenario:8 default payment+store cards and check checkout page - mobile
    And feature "feature.payment.storecards.management.enabled" is switched on
    When login with username "myAccountCardsUserEmail" and password "password" on mobile
    And searches for random product on mobile
    And checkout a product and navigate to payment section on mobile
    Then checkout page should show default payment card ending 1111
    And checkout page should show default store card saying New Look Store Card ending 9997

  Scenario:9 save payment card and check my account card section - mobile
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And searches for random product on mobile
    And checkout a product and navigate to payment section on mobile
    And save payment card details with no 4111111111111111
    And user places order
    Then My Account payment cards section should have card with ending "1111" on mobile
    And Delete card
    Then Card is deleted from my account page
    And searches for random product on mobile
    And checkout a product and navigate to payment section on mobile
    Then No saved card is displayed

	Scenario:10 save payment card and check my account card section - mobile - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And checkout a product and navigate to payment section on mobile
		And save payment card details with no 4111111111111111
		And user places order
		Then My Account payment cards section should have card with ending "1111" on mobile
		And Delete card
		Then Card is deleted from my account page
		And searches for random product on mobile
		And checkout a product and navigate to payment section on mobile
		Then No saved card is displayed
