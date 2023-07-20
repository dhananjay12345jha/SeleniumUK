@newlook @UserDependent
Feature: My account cards

  Background:
    Given i navigate to "Newlook" home page

  Scenario:1 save payment card and check my account card section
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account
    And user checkout a product
    And save payment card details with no 4111111111111111
	 And user places order
    Then My Account payment cards section should have card with ending 1111
    And Delete card
    Then Card is deleted from my account page
    And user checkout a product
    And I continue to payment
    Then No saved card is displayed

   Scenario:2 save payment card and check my account card section - PCI micro forms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And user checkout a product
		And save payment card details with no 4111111111111111
		And clear bag item from checkout page
		Then My Account payment cards section should have card with ending 1111
		And Delete card
		Then Card is deleted from my account page
		And user checkout a product
		And I continue to payment
		Then No saved card is displayed


  Scenario:2 save store card and check my account store card section
    And feature "feature.payment.storecards.management.enabled" is switched on
    When user provides required details to create new account
    And user checkout a product
    And save store card details with no 9826220039999997
	 And user places order
    Then My Account store cards section should not have card with ending 9997

  Scenario:3 save payment card and confirm the card is saved in checkout page
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account
    And user checkout a product
    And save payment card details with no 4111111111111111
    And user places order
    Then My Account payment cards section should have card with ending 1111
    When user checkout a product
    Then checkout page should show default payment card ending 1111

	Scenario:4 save payment card and confirm the card is saved in checkout page - PCI micro forms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And user checkout a product
		And save payment card details with no 4111111111111111
		And user places order
		Then My Account payment cards section should have card with ending 1111
		When user checkout a product
		Then checkout page should show default payment card ending 1111

  Scenario:5 save store card and check my account card is saved in checkout page
    And feature "feature.payment.storecards.management.enabled" is switched on
    When user provides required details to create new account
    And user checkout a product
    And save store card details with no 9826220039999997
	 And user places order
    Then My Account store cards section should have card with ending 9997
    And user checkout a product
    And checkout page should show default store card saying New Look Store Card ending 9997

  Scenario:6 save both payment and store card and check my account cards
    And feature "feature.payment.storecards.management.enabled" is switched on
    When login with username "myAccountCardsUserEmail" and password "password"
    And user checkout a product
	 And user places order
    Then My Account payment cards section should have card with ending 1111
    And My Account store cards section should have card with ending 9997

  Scenario:7 default payment+store cards and check checkout page
    And feature "feature.payment.storecards.management.enabled" is switched on
    When login with username "myAccountCardsUserEmail" and password "password"
    And user checkout a product
    Then checkout page should show default payment card ending 1111
    And checkout page should show default store card saying New Look Store Card ending 9997

  Scenario:8 Delete a saved payment card and check my account card section and checkout section
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
	 When user provides required details to create new account
    And user checkout a product
    And save payment card details with no 4111111111111111
	 And user places order
    Then My Account payment cards section should have card with ending 1111
    And Delete card
    Then Card is deleted from my account page
    And user checkout a product
    And I continue to payment
    Then No saved card is displayed

	Scenario:9 Delete a saved payment card and check my account card section and checkout section - PCI micro forms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And user checkout a product
		And save payment card details with no 4111111111111111
		And user places order
		Then My Account payment cards section should have card with ending 1111
		And Delete card
		Then Card is deleted from my account page
		And user checkout a product
		And I continue to payment
		Then No saved card is displayed
