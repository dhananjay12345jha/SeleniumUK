Feature: Purchases mobile

	Background:
		Given i navigate to "Newlook" home page

	@UserDependentMobileSamsung @condev_data
	Scenario Outline:1 New user makes a payment for collection and checks order history on mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for collection on mobile <payment type> <card type> <card number>
		Then order has been successful
		And set fields for collection and navigate to my account on mobile
		And select view order in my orders page
		Then check if correct collection order details are displayed on mobile
		Examples:
			| payment type          | card type  | card number      |
			| paymentType_Card      | Visa       | 4111111111111111 |
			| paymentType_Card      | Maestro    | 6759411100000008 |
			| paymentType_Card      | Mastercard | 5200000000000114 |
			| paymentType_Storecard |            |                  |

	@UserDependentMobileSamsung
	Scenario Outline:2 New user makes a payment for collection and checks order history on mobile -PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for collection on mobile <payment type> <card type> <card number>
		Then order has been successful
		And set fields for collection and navigate to my account on mobile
		And select view order in my orders page
		Then check if correct collection order details are displayed on mobile
		Examples:
			| payment type          | card type  | card number      |
			| paymentType_Card      | Visa       | 4111111111111111 |
			| paymentType_Card      | Maestro    | 6759411100000008 |
			| paymentType_Card      | Mastercard | 5200000000000114 |
			| paymentType_Storecard |            |                  |

	@MobileSmokePack
		@UserDependentMobileSamsung @condev_data
	Scenario Outline:2a New user makes a payment for home delivery and checks order history on mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for home delivery on mobile <payment type> <card type> <card number>
		Then order has been successful
		And set fields for delivery and navigate to my account on mobile
		And select view order in my orders page
		Then check if correct order details are displayed on mobile
		Examples:
			| payment type | card type | card number      |
			| card         | Visa      | 4111111111111111 |

	@MobileSmokePack
		@UserDependentMobileSamsung
	Scenario Outline:2b New user makes a payment for home delivery and checks order history on mobile -PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for home delivery on mobile <payment type> <card type> <card number>
		Then order has been successful
		And set fields for delivery and navigate to my account on mobile
		And select view order in my orders page
		Then check if correct order details are displayed on mobile
		Examples:
			| payment type | card type | card number      |
			| card         | Visa      | 4111111111111111 |

	@UserDependentMobileSamsung @condev_data
	Scenario Outline:2c New user makes a payment for home delivery and checks order history on mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for home delivery on mobile <payment type> <card type> <card number>
		Then order has been successful
		And set fields for delivery and navigate to my account on mobile
		And select view order in my orders page
		Then check if correct order details are displayed on mobile
		Examples:
			| payment type | card type  | card number      |
			| card         | Maestro    | 6759411100000008 |
			| card         | Mastercard | 5200000000000114 |
			| store card   |            |                  |

	@UserDependentMobileSamsung
	Scenario Outline:2d New user makes a payment for home delivery and checks order history on mobile - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for home delivery on mobile <payment type> <card type> <card number>
		Then order has been successful
		And set fields for delivery and navigate to my account on mobile
		And select view order in my orders page
		Then check if correct order details are displayed on mobile
		Examples:
			| payment type | card type  | card number      |
			| card         | Maestro    | 6759411100000008 |
			| card         | Mastercard | 5200000000000114 |
			| store card   |            |                  |

	@UserDependentMobileSamsung
	Scenario Outline:3 Guest makes a payment for collection and checks order history
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And searches for random product on mobile
		And purchase a random product for collection on mobile <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| payment type     | card type  | card number      |
			| paymentType_Card | Visa       | 4111111111111111 |
			| paymentType_Card | Maestro    | 6759411100000008 |
			| paymentType_Card | Mastercard | 5200000000000114 |

	@UserDependentMobileSamsung
	Scenario Outline:4 Guest makes a payment for collection and checks order history -PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And searches for random product on mobile
		And purchase a random product for collection on mobile <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| payment type     | card type  | card number      |
			| paymentType_Card | Visa       | 4111111111111111 |
			| paymentType_Card | Maestro    | 6759411100000008 |
			| paymentType_Card | Mastercard | 5200000000000114 |

	@UserDependentMobileSamsung
	Scenario Outline:5 Guest makes a payment for home delivery and checks order history
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And searches for random product on mobile
		And purchase a random product for home delivery on mobile <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| payment type | card type | card number      |
			| card         | Visa      | 4111111111111111 |
      #| card         |  Maestro  |  6759411100000008 |
      #| card         |Mastercard |   5200000000000114|
      #| store card   |           |                   |
      #| pay pal      |           |                   |

	@UserDependentMobileSamsung
	Scenario Outline:6 Guest makes a payment for home delivery and checks order history -PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And searches for random product on mobile
		And purchase a random product for home delivery on mobile <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| payment type | card type | card number      |
			| card         | Visa      | 4111111111111111 |

	@UserDependentMobileSamsung
	Scenario:7 Guest makes a payment for home delivery and checks order history - giftcard
		And searches for random product on mobile
		And purchase a random product for home delivery using giftcard
		Then order has been successful

#  Scenario:6 As a customer I want to make a purchase with multiple gift cards
#    When Customer make a purchase with multiple gift card
#    Then Order has been successful

#  @UserDependentMobile
	@UserDependentMobileSamsung
	Scenario: 8 Change delivery mode and complete purchase
		And login with username "myAccountCardsUserEmail" and password "password" on mobile
		And Select a product and navigate to payment page on mobile
		And select delivery option
		And user makes a purchase with a different mode
		And Enter CVV Number
		And user places order
		Then order has been successful

	@UserDependentMobileSamsung
	Scenario Outline: 9 Card enrolled in 1.0 feature is on
		And feature "feature.payment.3ds2.enabled.uk-site" is switched on
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for home delivery on mobile <payment type> <card type> <card number>
		And goes through pop up window
		Then order has been successful
		And order history shows correct order number
		Examples:
			| payment type | card type | card number      |
			| card         | Visa      | 4000000000000002 |

	@UserDependentMobileSamsung
	Scenario Outline: 10 Card enrolled in 1.0 feature is on -PCI on
		And feature "feature.payment.3ds2.enabled.uk-site" is switched on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for home delivery on mobile <payment type> <card type> <card number>
		And goes through pop up window
		Then order has been successful
		And order history shows correct order number
		Examples:
			| payment type | card type | card number      |
			| card         | Visa      | 4000000000000002 |

	@Ignore
		@UserDependentMobileSamsung
	#The test is invalid for safari browser automation as iphones doesnt allow switching frames not of same site origin
	#works well for android devices
	Scenario Outline: 11 Card enrolled in 1.0 feature is off
		And feature "feature.payment.3ds2.enabled.uk-site" is switched off
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for home delivery on mobile <payment type> <card type> <card number>
		And goes through iframe
		Then order has been successful
		Examples:
			| payment type | card type | card number      |
			| card         | Visa      | 4000000000000002 |

	@Ignore
		@UserDependentMobileSamsung
	#The test is invalid for safari browser automation as iphones doesnt allow switching frames not of same site origin
	#works well for android devices
	Scenario Outline: 12 Card enrolled in 1.0 feature is off - PCI on
		And feature "feature.payment.3ds2.enabled.uk-site" is switched off
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for home delivery on mobile <payment type> <card type> <card number>
		And goes through iframe
		Then order has been successful
		Examples:
			| payment type | card type | card number      |
			| card         | Visa      | 4000000000000002 |

	@UserDependentMobileSamsung
	Scenario Outline: 13  Card enrolled in 2.1 feature is on for France
		And feature "feature.payment.3ds2.enabled.fr-site" is switched on
		And feature "feature.pci.microform.enabled.fr-site" is switched off
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And select collection button
		And search for nearest collection location to: "<place>"
		And click continue to payment button
		And fill the billing address details for checkout "<postCode>" , "<city>"
		And fill the card details "<cardType>","<cardNumber>","<date>","<year>","<cvv>"
		And click checkout
		And goes through authentication window OTP
		Then order has been successful

		Examples:
			| country        | postCode | city  | cardType | cardNumber       | date | year | cvv | place |
			| country_France | 75000    | Paris | Visa     | 4000000000001091 | 07   | 2021 | 123 | Paris |

	@UserDependentMobileSamsung
	Scenario Outline: 14  Card enrolled in 2.1 feature is on for France -PCI on
		And feature "feature.payment.3ds2.enabled.fr-site" is switched on
		And feature "feature.pci.microform.enabled.fr-site" is switched on
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And select collection button
		And search for nearest collection location to: "<place>"
		And click continue to payment button
		And fill the billing address details for checkout "<postCode>" , "<city>"
		And fill the card details "<cardType>","<cardNumber>","<date>","<year>","<cvv>"
		And click checkout
		And goes through authentication window OTP
		Then order has been successful

		Examples:
			| country        | postCode | city  | cardType | cardNumber       | date | year | cvv | place |
			| country_France | 75000    | Paris | Visa     | 4000000000001091 | 07   | 2023 | 123 | Paris |

	@Ignore
		@UserDependentMobileSamsung
	#The test is invalid for safari browser automation as iphones doesnt allow switching frames not of same site origin
	#works well for android devices
	Scenario Outline:15 Card enrolled in 2.1 feature is off for France
		And feature "feature.payment.3ds2.enabled.fr-site" is switched off
		And feature "feature.pci.microform.enabled.fr-site" is switched off
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		And user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And select collection button
		And search for nearest collection location to: "<place>"
		And click continue to payment button
		And fill the billing address details for checkout "<postCode>" , "<city>"
		And fill the card details "<cardType>","<cardNumber>","<date>","<year>","<cvv>"
		And click checkout
		And goes through iframe
		Then order has been successful

		Examples:
			| country        | postCode | city  | cardType | cardNumber       | date | year | cvv | place |
			| country_France | 75000    | Paris | Visa     | 4000000000001091 | 07   | 2021 | 123 | Paris |

	@Ignore
		@UserDependentMobileSamsung
	#The test is invalid for safari browser automation as iphones doesnt allow switching frames not of same site origin
	#works well for android devices
	Scenario Outline:16 Card enrolled in 2.1 feature is off for France - PCI on
		And feature "feature.payment.3ds2.enabled.fr-site" is switched off
		And feature "feature.pci.microform.enabled.fr-site" is switched on
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		And user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And select collection button
		And search for nearest collection location to: "<place>"
		And click continue to payment button
		And fill the billing address details for checkout "<postCode>" , "<city>"
		And fill the card details "<cardType>","<cardNumber>","<date>","<year>","<cvv>"
		And click checkout
		And goes through iframe
		Then order has been successful

		Examples:
			| country        | postCode | city  | cardType | cardNumber       | date | year | cvv | place |
			| country_France | 75000    | Paris | Visa     | 4000000000001091 | 07   | 2021 | 123 | Paris |

	@UserDependentMobileSamsung
	Scenario:17 Bug NLCC-2628 CVV section disabled after card authorization - mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location to: "London"
		And user opted DELIVERY with post code CH7 4TW
		And select collection button
		And fill in mobile number
		And pays by paymentType_Card Visa visa_CardNumber_1
		Then order has been successful

	@UserDependentMobileSamsung
	Scenario:18 Bug NLCC-2628 CVV section disabled after card authorization - mobile - PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location to: "London"
		And user opted DELIVERY with post code CH7 4TW
		And select collection button
		And fill in mobile number
		And pays by paymentType_Card Visa visa_CardNumber_1
		Then order has been successful

	@UserDependentMobileSamsung
	Scenario:19 Bug NLCC-2628 Edit the bag and come back to checkout page - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product and navigate to payment page on mobile
		And provide guest user details
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Edit bag with quantity "2"
		And pays by paymentType_Virtualcard - -
		Then order has been successful

	@UserDependentMobileSamsung
	Scenario:20 Bug NLCC-2628 Refresh checkout page after completing delivery sections - mobile
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Reload checkout page
		And pays by paymentType_Virtualcard - -
		Then order has been successful

	@DefaultDelivery
	@UserDependentMobileSamsung
	Scenario: 21 Bug NLCC-2807 Customer can't select C&C tab when no default Home Delivery Option is available - mobile
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And select collection button
		And search for nearest collection location to: "London"
		And user opted DELIVERY with post code CH7 4TW
		And select collection button
		Then click link with text Map and opening times

	@UserDependentMobileSamsung
   #NLCC-7571 is testing NLCC-7499 - Gues user - mobile
	Scenario:22 Guest Customer checkout using store, switch to other payment option and switch back to store card - mobile
		And Select a product and navigate to payment page
		And provide guest user details
		And search for nearest collection location to: "London"
		And I continue to payment
		And save store card details with no 9826220039999997
		And select payment method as "paypal"
		And save store card details with no 9826220039999997
		And click age verification check box
		And user places order
		Then order has been successful

	@UserDependentMobileSamsung
	#NLCC-7571 is testing NLCC-7499 - reg user - mobile
	Scenario:23 Registered Customer checkout using store, switch to other payment option and switch back to store card - mobile
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location to: "London"
		And I continue to payment
		And save store card details with no 9826220039999997
		And select payment method as "paypal"
		And save store card details with no 9826220039999997
		And click age verification check box
		And user places order
		Then order has been successful

	@AnonymousMobile
   #NLCC-7710 Guest user
	Scenario Outline:24 Guest Customer checkout using invalid store, switch to card payment option - mobile
		And Select a product and navigate to payment page on mobile
		And provide guest user details
		And search for nearest collection location to: "London"
		And I continue to payment
		And add store card details with no <storeCard Number> and expiry date 0823
		And click age verification check box
		And user places order
		Then invalid card message "displayed" for "number"
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| storeCard Number | payment type          | card type | card number       |
			| 9826220039999995 | paymentType_Card      | Visa      | visa_CardNumber_1 |
			| 9826220039999995 | paymentType_Storecard |           |                   |

	@@UserDependentMobileSamsung @condev_data
   #NLCC-7710 Registered user
	Scenario Outline:25 Registered Customer checkout using invalid store, switch to card payment option - mobile
		And login with username "myBagUserEmail" and password "password"
		And remove all the added items
		And Select a product and navigate to payment page
		And select delivery option
		And enter delivery mandatory mobile number
		And user choose delivery option "Standard" mode
		And I continue to payment
		And add store card details with no <storeCard Number> and expiry date 0823
		And click age verification check box
		And user places order
		Then invalid card message "displayed" for "number"
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| storeCard Number | payment type          | card type | card number       |
			| 9826220039999995 | paymentType_Card      | Visa      | visa_CardNumber_1 |
			| 9826220039999995 | paymentType_Storecard |           |                   |

	@AnonymousMobile
   #NLCC-7710 Guest user
	Scenario Outline:26 Guest Customer checkout using invalid store card expiry date, switch to card payment option- mobile
		And Select a product and navigate to payment page
		And provide guest user details
		And search for nearest collection location to: "London"
		And I continue to payment
		And add store card details with no <storeCard Number> and expiry date 0824
		And click age verification check box
		And user places order
		Then invalid card message "displayed" for "expiryDate"
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| storeCard Number | payment type          | card type | card number       |
			| 9826220022220013 | paymentType_Card      | Visa      | visa_CardNumber_1 |
			| 9826220022220013 | paymentType_Storecard |           |                   |

	@UserDependentMobileSamsung @condev_data
   #NLCC-7710 Registered user
	Scenario Outline:27 Registered Customer checkout using invalid store card expire date, switch to card payment option - mobile
		And login with username "myBagUserEmail" and password "password"
		And remove all the added items
		And Select a product and navigate to payment page
		And select delivery option
		And enter delivery mandatory mobile number
		And user choose delivery option "Standard" mode
		And I continue to payment
		And add store card details with no <storeCard Number> and expiry date 0824
		And click age verification check box
		And user places order
		Then invalid card message "displayed" for "expiryDate"
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| storeCard Number | payment type          | card type | card number       |
			| 9826220022220013 | paymentType_Card      | Visa      | visa_CardNumber_1 |
			| 9826220022220013 | paymentType_Storecard |           |                   |

	@AnonymousMobile
   #NLCC-7710 Guest user
	Scenario Outline:28 Guest Customer checkout using invalid debit card , switch to store card payment option - mobile
		And Select a product and navigate to payment page
		And provide guest user details
		And search for nearest collection location to: "London"
		And I continue to payment
		And select debit or credit card tab
		And Add billing address
		When I type my card number as "3545 5066 3794 6924"
		Then the card number field should display error message "Card not accepted."
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| payment type          | card type | card number       |
			| paymentType_Storecard |           |                   |
			| paymentType_Card      | Visa      | visa_CardNumber_1 |

	@UserDependentMobileSamsung @condev_data
   #NLCC-7710 Registered user
	Scenario Outline:29 Registered Customer checkout using invalid debit card , switch to store card payment option - mobile
		And login with username "myBagUserEmail" and password "password"
		And remove all the added items
		And Select a product and navigate to payment page
		And select delivery option
		And enter delivery mandatory mobile number
		And user choose delivery option "Standard" mode
		And I continue to payment
		And select debit or credit card tab
		And Add billing address
		When I enter card number as "3545 5066 3794 6924" in card popup
		Then the card number field should display error message "Card not accepted."
		And I close popups
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| payment type          | card type | card number       |
			| paymentType_Storecard |           |                   |
			| paymentType_Card      | Visa      | visa_CardNumber_1 |

  @UserDependentMobileSamsung @oms_data
   #NLCC-7710 Registered user
	Scenario Outline:30 Registered Customer checkout using invalid store, switch to card payment option OMS- mobile
		And login with username "myBagUserEmail" and password "password"
		And remove all the added items
		And Select a product and navigate to payment page
		And select delivery option
		And enter delivery mandatory mobile number
		And user choose delivery option "UK Royal Mail" mode
		And I continue to payment
		And add store card details with no <storeCard Number> and expiry date 0823
		And click age verification check box
		And user places order
		Then invalid card message "displayed" for "number"
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| storeCard Number | payment type          | card type | card number       |
			| 9826220039999995 | paymentType_Card      | Visa      | visa_CardNumber_1 |
			| 9826220039999995 | paymentType_Storecard |           |                   |

	@UserDependentMobileSamsung @oms_data
   #NLCC-7710 Registered user
	Scenario Outline:31 Registered Customer checkout using invalid store card expire date, switch to card payment option OMS - mobile
		And login with username "myBagUserEmail" and password "password"
		And remove all the added items
		And Select a product and navigate to payment page
		And select delivery option
		And enter delivery mandatory mobile number
		And user choose delivery option "UK Royal Mail" mode
		And I continue to payment
		And add store card details with no <storeCard Number> and expiry date 0824
		And click age verification check box
		And user places order
		Then invalid card message "displayed" for "expiryDate"
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| storeCard Number | payment type          | card type | card number       |
			| 9826220022220013 | paymentType_Card      | Visa      | visa_CardNumber_1 |
			| 9826220022220013 | paymentType_Storecard |           |                   |

	@UserDependentMobileSamsung @oms_data
  #NLCC-7710 Registered user
	Scenario Outline:32 Registered Customer checkout using invalid debit card , switch to store card payment option OMS - mobile
		And login with username "myBagUserEmail" and password "password"
		And remove all the added items
		And Select a product and navigate to payment page
		And select delivery option
		And enter delivery mandatory mobile number
		And user choose delivery option "UK Royal Mail" mode
		And I continue to payment
		And select debit or credit card tab
		And Add billing address
		When I enter card number as "3545 5066 3794 6924" in card popup
		Then the card number field should display error message "Card not accepted."
		And I close popups
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		Examples:
			| payment type          | card type | card number       |
			| paymentType_Storecard |           |                   |
			| paymentType_Card      | Visa      | visa_CardNumber_1 |

	@MobileSmokePack
		@UserDependentMobileSamsung @oms_flow @Omsf-300
	Scenario Outline:33 New user makes a payment for home delivery and checks order history on Oms mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for home delivery on mobile <payment type> <card type> <card number>
		Then order has been successful
		And set fields for delivery and navigate to my account on mobile
		And select view order in my orders page oms
		Then check if correct order details are displayed on mobile
		Examples:
			| payment type | card type | card number      |
			| card         | Visa      | 4111111111111111 |

	@UserDependentMobileSamsung @oms_flow @Omsf-300
	Scenario Outline:34 New user makes a payment for collection and checks order history on oms mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for collection on mobile <payment type> <card type> <card number>
		Then order has been successful
		And set fields for collection and navigate to my account on mobile
		And select view order in my orders page oms
		Then check if correct collection order details are displayed on mobile
		Examples:
			| payment type          | card type  | card number      |
			| paymentType_Card      | Visa       | 4111111111111111 |
			| paymentType_Card      | Maestro    | 6759411100000008 |
			| paymentType_Card      | Mastercard | 5200000000000114 |
			| paymentType_Storecard |            |                  |

	@UserDependentMobileSamsung @oms_flow @Omsf-300
	Scenario Outline:35 New user makes a payment for home delivery and checks order history on Oms mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account on mobile
		And searches for random product on mobile
		And purchase a random product for home delivery on mobile <payment type> <card type> <card number>
		Then order has been successful
		And set fields for delivery and navigate to my account on mobile
		And select view order in my orders page oms
		Then check if correct order details are displayed on mobile
		Examples:
			| payment type | card type  | card number      |
			| card         | Maestro    | 6759411100000008 |
			| card         | Mastercard | 5200000000000114 |
			| store card   |            |                  |

