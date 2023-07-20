@NewUsers
Feature: PCI Micro Forms on checkout page

	Background:
		And i navigate to "Newlook" home page

	@NLTA-622, @NLTA-551
	Scenario:1 Registered Customer / Payment - Card / Card Expiry
		And user provides required details to create new account
		And new customer checkout a product for collection
		And select debit or credit card tab
		And fill in mobile number
		And Add billing address
		When I type my card expiry date with "abcd","abcd"
		Then the date field should not display anything
		When I type my card expiry date with "!@#$","%^&*()"
		Then the date field should not display anything
		When I type my card expiry date with "0000","0000"
		Then the date field should display error message "Invalid date format"
		When I type my card expiry date with "15","10"
		Then the date field should display error message "Invalid date format"
		When I type my card expiry date with "11","20"
		Then the date field should display error message "Card has expired."
		When I type my card expiry date with "12","22"
		Then the date field should not display anything

	@NLTA-622, @NLTA-551
	Scenario:2 Guest Customer / Payment - Card / Card Expiry
		When Select a product and navigate to payment page
		And continue as guest
		And search for nearest collection location to: "London"
		And select debit or credit card tab
		And Add billing address - guest user
		When I type my card expiry date with "abcd","abcd"
		Then the date field should not display anything
		When I type my card expiry date with "!@#$","%^&*()"
		Then the date field should not display anything
		When I type my card expiry date with "0000","0000"
		Then the date field should display error message "Invalid date format"
		When I type my card expiry date with "15","10"
		Then the date field should display error message "Invalid date format"
		When I type my card expiry date with "11","20"
		Then the date field should display error message "Card has expired."
		When I type my card expiry date with "12","22"
		Then the date field should not display anything

	@NLTA-623
	Scenario:3 Registered Customer / Payment using Unsupported/Bad Card
		And user provides required details to create new account
		And new customer checkout a product for collection
		And select debit or credit card tab
		And fill in mobile number
		And Add billing address
		When I type my card number as "3545 5066 3794 6924"
		Then the card number field should display error message "Card not accepted."
		When I type my card number as "4444 4444 4444 4444444"
		Then the card number field should display error message "Card not accepted."

	@NLTA-622, @NLTA-551
	Scenario:4 Guest Customer /  Payment using Unsupported/Bad Card
		When Select a product and navigate to payment page
		And continue as guest
		And search for nearest collection location to: "London"
		And select debit or credit card tab
		And Add billing address - guest user
		And select debit or credit card tab
		When I type my card number as "3545 5066 3794 6924"
		Then the card number field should display error message "Card not accepted."
		When I type my card number as "4444 4444 4444 4444444"
		Then the card number field should display error message "Card not accepted."

	@NLTA-724, @NLTA-459
	Scenario:5 Registered Customer / Payment - Card Not Saved / Add New Card
		And user provides required details to create new account
		And new customer checkout a product for collection
		And select debit or credit card tab
		And Add billing address
		And make payment with credit card
		Then there is no further form to capture CVV
		And the card details is shown with the expiry date and last 4 digits of the card number

	@NNLTA-724, @NLTA-459
	Scenario:6 Guest Customer / Payment - Card Not Saved / Add New Card
		When Select a product and navigate to payment page
		And continue as guest
		And search for nearest collection location to: "London"
		And select debit or credit card tab
		And fill in mobile number
		And Add billing address - guest user
		And make payment with credit card with PCI form on
		Then there is no further form to capture CVV
		And the card details is shown with the expiry date and last 4 digits of the card number

	@NLTA-723, @NLTA-621
	Scenario:7 Registered Customer / Save card check box - selected
		And user provides required details to create new account
		And new customer checkout a product for collection
		And select debit or credit card tab
		And Add billing address
		And make payment with credit card
		And user places order
		Then order has been successful
		Then My Account payment cards section should have card with ending 1111
		And Select a product and navigate to payment page
		And select delivery option
		And select available delivery mode
		And enter delivery mandatory mobile number
		And user should see the added card ending with number 1111
		Then Confirm CVV Field is displayed
		And Enter CVV Number
		And user places order
		Then order has been successful


	@NLTA-723, @NLTA-621
	Scenario:8 Registered Customer / Save card check box - NOT selected
		And user provides required details to create new account
		And new customer checkout a product for collection
		And select debit or credit card tab
		And Add billing address
		And entering new card details and deselect 'Save card' check box
		And user confirms over the age of 18
		And user places order
		Then order has been successful
		Then My Account payment cards section should not have card with ending 1111
		Then Select a product and navigate to payment page
		Then user should not see the added card ending with number 1111

	@NLTA-723, @NLTA-621
	Scenario:9 Guest Customer / Save card check box - selected
		When Select a product and navigate to payment page
		And continue as guest
		And search for nearest collection location to: "London"
		And select debit or credit card tab
		And Add billing address - guest user
		And entering new card details and select 'Save card' check box
		And user confirms over the age of 18
		And user places order
		Then order has been successful
		And Customer is displayed with option to add a password and register an account
		And feature "feature.storefront.recaptcha.enabled" is switched off
		Then Guest user register for an account by adding a password and login
		Then My Account payment cards section should have card with ending 1111
		When Select a product and navigate to payment page
		And select delivery option
		And select available delivery mode
		And enter delivery mandatory mobile number
		Then user should see the added card ending with number 1111
		And Enter CVV Number
		And user places order
		Then order has been successful

	@NLTA-723, @NLTA-621
	Scenario:10 Guest Customer / Save card check box - NOT selected
		When Select a product and navigate to payment page
		And continue as guest
		And search for nearest collection location to: "London"
		And select debit or credit card tab
		And Add billing address - guest user
		And entering new card details and deselect 'Save card' check box
		And user confirms over the age of 18
		And user places order
		Then order has been successful
		And Customer is displayed with option to add a password and register an account
		And feature "feature.storefront.recaptcha.enabled" is switched off
		Then Guest user register for an account by adding a password and login
		Then My Account payment cards section should not have card with ending 1111

	@NLCC-4890,@NLTA-620
	Scenario:11 change card registered customer
		And user provides required details to create new account
		And new customer checkout a product for collection
		And select debit or credit card tab
		And Add billing address
		And make payment with credit card
		And user should see the added card ending with number 1111
		And user places order
		Then order has been successful
		And Select a product and navigate to payment page
		And customer adds another card
		And selects change card
		Then an overlay of all cards listed with radio buttons is displayed
		When user confirms the card from the overlay
		And user should see the added card ending with number 1111


	@NLCC-4890,@NLTA-620
	Scenario:12 change card guest customer
		When Select a product and navigate to payment page
		And continue as guest
		And search for nearest collection location to: "London"
		And select debit or credit card tab
		And Add billing address - guest user
		And entering new card details and select 'Save card' check box
		And customer adds another card
		And selects change card
		Then an overlay of all cards listed with radio buttons is displayed
		When user confirms the card from the overlay
		And user should see the added card ending with number 1111

	@NLCC-4888
	@NLTA-425
	Scenario: 13 Amex card auto-detection (guest)
		Given Select a product and navigate to payment page
		When I proceed to the guest checkout payment step
		And select debit or credit card tab

		# Amex - 4 digit CVV
		Given I type 4 digits in the CVV field
		And There are 3 digits in the CVV field
		When I type an "amex" card number
		And I type 5 digits in the CVV field
		Then There are 4 digits in the CVV field

		# Valid card
		When I type an "amex" card number
		And I type an "amex" expiry date
		And I type an "amex" CVV
		Then The use this card button is enabled

	@NLCC-4888
	@NLTA-425
	Scenario: 14 Amex card auto-detection (registered)
		Given user provides required details to create new account
		And Select a product and navigate to payment page
		When I proceed to the checkout payment step

	  	# Amex - 4 digit CVV
		Given I type 4 digits in the CVV field
		And There are 3 digits in the CVV field
		When I type an "amex" card number
		And I type 5 digits in the CVV field
		Then There are 4 digits in the CVV field

		# Valid card
		When I type an "amex" card number
		And I type an "amex" expiry date
		And I type an "amex" CVV
		Then The use this card button is enabled

	@NLCC-4888
	@NLTA-425
	Scenario: 15 Card auto-detection (guest)
		Given Select a product and navigate to payment page
		When I proceed to the guest checkout payment step

	 	# Card icon
		Then Typing a card number makes the correct icon appear next to the card number field:
			| visa       |
			| mastercard |
			| amex       |
			| maestro    |

		# Invalid card number
		When I type an "invalid" card number
		Then The use this card button is disabled

		# Non-Amex - 3 digit CVV
		Given I type a "visa" card number
		And I type 4 digits in the CVV field
		Then There are 3 digits in the CVV field

		# Valid card
		When I type a "visa" card number
		And I type a "visa" expiry date
		And I type a "visa" CVV
		Then The use this card button is enabled

	@NLCC-4888
	@NLTA-425
	Scenario: 16 Card auto-detection (registered)
		Given user provides required details to create new account
		And Select a product and navigate to payment page
		When I proceed to the checkout payment step

	 	# Card icon
		Then Typing a card number makes the correct icon appear next to the card number field:
			| visa       |
			| mastercard |
			| amex       |
			| maestro    |

		# Invalid card number
		When I type an "invalid" card number
		Then The use this card button is disabled

		# Non-Amex - 3 digit CVV
		Given I type a "visa" card number
		And I type 4 digits in the CVV field
		Then There are 3 digits in the CVV field

		# Valid card
		When I type a "visa" card number
		And I type a "visa" expiry date
		And I type a "visa" CVV
		Then The use this card button is enabled
