@NewUsersMobileSamsung
Feature: PCI Micro Forms on checkout page on mobile

	Background:
		Given feature "feature.pci.microform.enabled.uk-site" is switched on
		And i navigate to "Newlook" home page

	@NLTA-622, @NLTA-551
	Scenario:1 Registered Customer / Payment - Card / Card Expiry on mobile
		When login with username "checkoutAndPaymentsUserEmail" and password "password" on mobile
		And Select a product and navigate to payment page on mobile
		And select delivery option
		And select debit or credit card tab
		When I type my card expiry date with "abcd","abcd"
		Then the date field should not display anything
		When I type my card expiry date with "!@#$","%^&*()"
		Then the date field should not display anything
		When I type my card expiry date with "00","00"
		Then the date field should display error message "Invalid date format"
		When I type my card expiry date with "15","10"
		Then the date field should display error message "Invalid date format"
		When I type my card expiry date with "11","20"
		Then the date field should display error message "Card has expired."
		When I type my card expiry date with "12","22"
		Then the date field should not display anything

	@NLTA-622, @NLTA-551
	Scenario:2 Guest Customer / Payment - Card / Card Expiry on mobile
		And Select a product and navigate to payment page on mobile
		And continue as guest
		And search for nearest collection location to: "London"
		And select debit or credit card tab
		And Add billing address - guest user
		When I type my card expiry date with "abcd","abcd"
		Then the date field should not display anything
		When I type my card expiry date with "!@#$","%^&*()"
		Then the date field should not display anything
		When I type my card expiry date with "00","00"
		Then the date field should display error message "Invalid date format"
		When I type my card expiry date with "15","10"
		Then the date field should display error message "Invalid date format"
		When I type my card expiry date with "11","20"
		Then the date field should display error message "Card has expired."
		When I type my card expiry date with "12","22"
		Then the date field should not display anything

	@NLTA-623
	Scenario:3 Registered Customer / Payment using Unsupported/Bad Card
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And find and add new address using post code E6 2HE
		When Select a product and navigate to payment page on mobile
		And select delivery option
		And select debit or credit card tab
		When I type my card number as "3545 5066 3794 6924"
		Then the card number field should display error message "Card not accepted."
		When I type my card number as "4444 4444 4444 4444444"
		Then the card number field should display error message "Card not accepted."

	@NLTA-623
	Scenario:4 Guest Customer /  Payment using Unsupported/Bad Card
		When Select a product and navigate to payment page on mobile
		And continue as guest
		And search for nearest collection location to: "London"
		And select debit or credit card tab
		And Add billing address - guest user
		When I type my card number as "3545 5066 3794 6924"
		Then the card number field should display error message "Card not accepted."
		When I type my card number as "4444 4444 4444 4444444"
		Then the card number field should display error message "Card not accepted."

	@NLTA-724, @NLTA-459
	Scenario:5 Registered Customer / Payment - Card Not Saved / Add New Card on mobile
		When login with username "pciFormNewUser" and password "password"
		And Select a product and navigate to payment page on mobile
		And select delivery option
		And select debit or credit card tab
		And make payment with credit card with PCI form on
		Then there is no further form to capture CVV
		And the card details is shown with the expiry date and last 4 digits of the card number


	@NLTA-724, @NLTA-459
	Scenario:6 Guest Customer / Payment - Card Not Saved / Add New Card on mobile
		And Select a product and navigate to payment page on mobile
		And continue as guest
		And search for nearest collection location to: "London"
		And select debit or credit card tab
		And fill in mobile number
		And Add billing address - guest user
		And make payment with credit card with PCI form on
		Then there is no further form to capture CVV
		And the card details is shown with the expiry date and last 4 digits of the card number

	@NLTA-723, @NLTA-621 @NLTA-630 @NLTA-426
	Scenario:7 Registered Customer / Save card check box - selected
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And find and add new address using post code E6 2HE
		And Select a product and navigate to payment page on mobile
		And select delivery option
		And select debit or credit card tab
		And entering new card details and select 'Save card' check box
		And user should see the added card ending with number 1111
		And user places order
		Then order has been successful
		Then My Account payment cards section should have card with ending "1111" on mobile
		And Select a product and navigate to payment page on mobile
		And select delivery option
		And user should see the added card ending with number 1111
		Then there is no further form to capture CVV
		And user places order
		Then order has been successful
		Then My Account payment cards section should have card with ending "1111" on mobile
		And Delete card
		Then Card is deleted from my account page
		And searches for random product on mobile
		And checkout a product and navigate to payment section on mobile
		Then user should not see the added card ending with number 1111

	@NLTA-723, @NLTA-621 @NLTA-630 @NLTA-426
	Scenario:8 Registered Customer / Save card check box - NOT selected
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And find and add new address using post code E6 2HE
		When Select a product and navigate to payment page on mobile
		And select delivery option
		And select debit or credit card tab
		And entering new card details and deselect 'Save card' check box
		And user should see the added card ending with number 1111
		And user places order
		Then order has been successful
		Then My Account payment cards section should not have card with ending "1111" on mobile
		And searches for random product on mobile
		And checkout a product and navigate to payment section on mobile
		Then user should not see the added card ending with number 1111

	@NLTA-723, @NLTA-621 @NLTA-630 @NLTA-426
	Scenario:9 Guest Customer / Save card check box - selected
		When Select a product and navigate to payment page on mobile
		And continue as guest
		And search for nearest collection location to: "London"
		And select debit or credit card tab
		And Add billing address - guest user
		And entering new card details and select 'Save card' check box
		And user should see the added card ending with number 1111
		And user confirms over the age of 18
		And user places order
		Then order has been successful
		And Customer is displayed with option to add a password and register an account
		And feature "feature.storefront.recaptcha.enabled" is switched off
		Then Guest user register for an account by adding a password and login
		Then My Account payment cards section should have card with ending "1111" on mobile
		When Select a product and navigate to payment page on mobile
		And select delivery option
		And user should see the added card ending with number 1111
		Then there is no further form to capture CVV
		And user places order
		Then order has been successful
		Then My Account payment cards section should have card with ending "1111" on mobile
		And Delete card
		Then Card is deleted from my account page
		When Select a product and navigate to payment page on mobile
		And select delivery option
		Then user should not see the added card ending with number 1111

	@NLTA-723, @NLTA-621 @NLTA-630 @NLTA-426
	Scenario:10 Guest Customer / Save card check box - NOT selected
		When Select a product and navigate to payment page on mobile
		And continue as guest
		And search for nearest collection location to: "London"
		And select debit or credit card tab
		And Add billing address - guest user
		And entering new card details and deselect 'Save card' check box
		And user should see the added card ending with number 1111
		And user confirms over the age of 18
		And user places order
		Then order has been successful
		And Customer is displayed with option to add a password and register an account
		And feature "feature.storefront.recaptcha.enabled" is switched off
		Then Guest user register for an account by adding a password and login
		Then My Account payment cards section should not have card with ending "1111" on mobile


	@NLCC-4890,@NLTA-620
	Scenario:11 change card registered customer
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And find and add new address using post code E6 2HE
		And Select a product and navigate to payment page on mobile
		And select delivery option
		And select debit or credit card tab
		And entering new card details and select 'Save card' check box
		And user should see the added card ending with number 1111
		And user places order
		Then order has been successful
		And Select a product and navigate to payment page on mobile
		And customer adds another card
		And selects change card
		Then an overlay of all cards listed with radio buttons is displayed
		When user confirms the card from the overlay
		And user should see the added card ending with number 1111


	@NLCC-4890,@NLTA-620
	Scenario:12 change card guest customer
		When Select a product and navigate to payment page on mobile
		And continue as guest
		And search for nearest collection location to: "London"
		And select debit or credit card tab
		And Add billing address - guest user
		And entering new card details and select 'Save card' check box
		And user should see the added card ending with number 1111
		And customer adds another card
		And selects change card
		Then an overlay of all cards listed with radio buttons is displayed
		When user confirms the card from the overlay
		And user should see the added card ending with number 1111

	@NLCC-4888
	@NLTA-425
	Scenario: 13 Amex card auto-detection (guest)
		Given Select a product and navigate to payment page on mobile
		When I proceed to the guest checkout payment step

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
		Given user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
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
		Given Select a product and navigate to payment page on mobile
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
		Given user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
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

   #NLCC-5721
	Scenario:17 Registered user When Changes Delivery Mode is asked to enter CVV again
		And feature "feature.storefront.checkout.cvv.check" is switched on
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location with "London" and select collection as "Click & Collect Standard"
		And enter mandatory mobile number
		And pays by paymentType_Card Visa visa_CardNumber_1
		Then order has been successful
		And order history shows correct order number
		When customer select product and navigate to my bag page on mobile
		And I change the quantity to "Qty: 5" of a line item 1
		And click secure checkout button
		And select delivery option
		And enter delivery mandatory mobile number
		And select available delivery mode
		Then Confirm CVV Field is displayed
		And Place order button is "greyed out"
		And Enter CVV Number
		And I click on the place order button
		Then order has been successful
		And order history shows correct order number
		When customer select product and navigate to my bag page on mobile
		And I change the quantity to "Qty: 5" of a line item 1
		And click secure checkout button
		And select delivery option
		And i click change delivery address
		And select available delivery mode
		And saved card is displayed
		Then Confirm CVV Field is displayed
		And Place order button is "greyed out"
		And Enter CVV Number
		And I click on the place order button
		Then order has been successful
		And order history shows correct order number
		When customer select product and navigate to my bag page on mobile
		And I change the quantity to "Qty: 5" of a line item 1
		And click secure checkout button
		And select collection button
		Then Confirm CVV Field is displayed
		And Place order button is "greyed out"
		And Enter CVV Number
		And I click on the place order button
		Then order has been successful
		And order history shows correct order number
		When customer select product and navigate to my bag page on mobile
		And I change the quantity to "Qty: 5" of a line item 1
		And click secure checkout button
		And select collection button and enter London in search field
		And select first Available collection Store
		Then Confirm CVV Field is displayed
		And Place order button is "greyed out"
		And Enter CVV Number
		And I click on the place order button
		Then order has been successful
		And order history shows correct order number

   #NLCC-5721
	Scenario:18 Registered user When Changes Delivery Mode cant place order without entering CVV
		And feature "feature.storefront.checkout.cvv.check" is switched on
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location with "London" and select collection as "Click & Collect Standard"
		And enter mandatory mobile number
		And pays by paymentType_Card Visa visa_CardNumber_1
		Then order has been successful
		And order history shows correct order number
		When customer select product and navigate to my bag page on mobile
		And I change the quantity to "Qty: 5" of a line item 1
		And click secure checkout button
		And select delivery option
		And enter delivery mandatory mobile number
		Then Confirm CVV Field is displayed
		Then Place order button is "greyed out"
		And user places order
		Then payment method error is displayed

   #NLCC-6380
	@newlook
	Scenario:19 Guest user can see ghost text and greyed out place order button mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		And Select a product and navigate to payment page on mobile
		And continue as guest
		And user opted DELIVERY with post code CH7 4TW
		Then Place order button is "greyed out"
		And pays by paymentType_Card Visa visa_CardNumber_1

	#NLCC-6380
	@newlook
	Scenario:20 Registered user can see ghost text and greyed out place order button
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		When user provides required details to create new account on mobile
		When customer select product and navigate to my bag page on mobile
		And I change the quantity to "Qty: 5" of a line item 1
		And click secure checkout button
		And chooses standard delivery
		Then Place order button is "greyed out"
		And pays by paymentType_Card Visa visa_CardNumber_1

   #NLCC-6858
	@newlook @condev_data
	Scenario:21 Guest user can see correct save card message mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		And Select a product and navigate to payment page on mobile
		And continue as guest
		And user opted DELIVERY with post code CH7 4TW
		And click continue to payment button
		Then Proper save card message is displayed
		And fill the card details "Visa","4000000000001091","07","2022","123"
		And customer clicks add another card
		Then Proper save card message is displayed


	#NLCC-6858
	@newlook @condev_data
	Scenario:22 Registered user can see correct save card message mobile
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And chooses standard delivery
		And click continue to payment button
		Then Proper save card message is displayed
		And fill the card details "Visa","4000000000001091","07","2022","123"
		And customer clicks add another card
		Then Proper save card message is displayed
		
   #NLCC-6858
	@newlook @oms_data
	Scenario:23 Guest user can see correct save card message mobile OMS
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		And Select a product and navigate to payment page on mobile
		And continue as guest
		And user opted DELIVERY with post code CH7 4TW
		And click continue to payment button
		Then Proper save card message is displayed
		And fill the card details "Visa","4000000000001091","07","2022","123"
		Then Proper save card message is displayed		
				
	#NLCC-6858
	@newlook @oms_data
	Scenario:24 Registered user can see correct save card message mobile OMS
		And feature "feature.storefront.checkout.new.tender" is switched on
		And customer is logged out
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And chooses standard delivery
		And click continue to payment button
		Then Proper save card message is displayed
		And fill the card details "Visa","4000000000001091","07","2022","123"
		Then Proper save card message is displayed
