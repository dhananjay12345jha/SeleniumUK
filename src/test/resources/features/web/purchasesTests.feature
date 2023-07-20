Feature: Purchases

	Background:
		Given i navigate to "Newlook" home page

	@newlook @NewUsers @condev_data
	Scenario Outline:1 New user makes a payment for collection and checks order history
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And new customer checkout a product for collection
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		And order history shows correct order number
		Examples:
			| payment type          | card type  | card number           |
			| paymentType_Card      | Visa       | visa_CardNumber_1     |
			| paymentType_Card      | Maestro    | Maestro_CardNumber    |
			| paymentType_Card      | Mastercard | Mastercard_CardNumber |
			| paymentType_Storecard |            |                       |
			| paymentType_Paypal    |            |                       |


	@newlook
		@NewUsers
	Scenario Outline:2 New user makes a payment for collection and checks order history - pci microforms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And new customer checkout a product for collection
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		And order history shows correct order number
		Examples:
			| payment type          | card type  | card number           |
			| paymentType_Card      | Visa       | visa_CardNumber_1     |
			| paymentType_Card      | Maestro    | Maestro_CardNumber    |
			| paymentType_Card      | Mastercard | Mastercard_CardNumber |
			| paymentType_Storecard |            |                       |
			| paymentType_Paypal    |            |                       |

	@NewUsers
	Scenario Outline:1a New user makes a payment for collection and checks order history - gift card
		When user provides required details to create new account
		And new customer checkout a product for collection
		And I update customer details "<firstName>" and "<lastName>"
		And pays by <payment type> "" ""
		Then order has been successful
		And order history shows correct order number
		Examples:
			| payment type         | firstName | lastName |
			| paymentType_Giftcard | cancel    | cancel   |


	@NLRD-1473 @newlook @smokepack
	@NewUsers @condev_data
	Scenario Outline:2a) New customer makes a purchase for home delivery
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And Customer makes a home delivery purchase with "<payment type>" and product "<product>"
		And order has been successful
		And user clicks on Track My Order from order confirmation page
		And select view order in my orders page
		Then check if correct order details are displayed
		Examples:
			| payment type | product |
			| card         | jeans   |

	@NLRD-1473 @newlook @smokepack
		@NewUsers
	Scenario Outline:2a) New customer makes a purchase for home delivery - PCI microforms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And Customer makes a home delivery purchase with "<payment type>" and product "<product>"
		And order has been successful
		And user clicks on Track My Order from order confirmation page
		And select view order in my orders page
		Then check if correct order details are displayed
		Examples:
			| payment type | product |
			| card         | jeans   |

	@NLRD-1473 @newlook
		@NewUsers @condev_data
	Scenario Outline:2b) New customer makes a purchase for home delivery
		When user provides required details to create new account
		And Customer makes a home delivery purchase with "<payment type>"
		And order has been successful
		And user clicks on Track My Order from order confirmation page
		And select view order in my orders page
		Then check if correct order details are displayed
		Examples:
			| payment type |
			| store card   |

	@newlook
		@NewUsers
	Scenario Outline:3 Guest makes a payment for collection and checks order history
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And new customer checkouts a product for collection as a guest with <payment type>
		Then order has been successful
		Examples:
			| payment type |
			| card         |

	@newlook
		@NewUsers
	Scenario Outline:4 Guest makes a payment for collection and checks order history -- PCI microforms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And new customer checkouts a product for collection as a guest with <payment type>
		Then order has been successful
		Examples:
			| payment type |
			| card         |

	@newlook
	@NewUsers
	Scenario Outline:5 Guest makes a payment for home delivery and checks order history
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And new customer checkouts a product for home delivery as a guest with "<payment type>"
		Then order has been successful
		Examples:
			| payment type |
			| card         |
			| store card   |
			| pay pal      |

	@newlook
		@NewUsers
	Scenario Outline:6 Guest makes a payment for home delivery and checks order history - PCI micro forms
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And new customer checkouts a product for home delivery as a guest with "<payment type>"
		Then order has been successful
		Examples:
			| payment type |
			| card         |
			| store card   |
			| pay pal      |

	@NewUsers
	Scenario:4a Guest makes a payment for home delivery and checks order history
		And new customer checkouts a product for home delivery as a guest with giftcard
		Then order has been successful

#  Scenario:6 As a customer I want to make a purchase with multiple gift cards
#    When Customer make a purchase with multiple gift card
#    Then Order has been successful

#  @UserDependent
	@NewUsers
	Scenario: 7 Change delivery mode and complete purchase
		When login with username "myAccountCardsUserEmail" and password "password"
		And Select a product and navigate to payment page
		And select delivery option
		And user makes a purchase with a different mode
		And user places order
		Then order has been successful

	@NLCC-4
		@NewUsers
	Scenario Outline: 8 Default payment provider is updated after place order
		And feature "feature.checkout.updateDefaultPaymentProviderAfterPlaceOrder" is switched on
		And feature "feature.payment.storecards.management.enabled" is switched on
		When user provides required details to create new account
		And Customer makes a home delivery purchase with "card"
		And order has been successful
		When user checkout a product
		Then checkout page should show default payment card ending 1111
		And pays by <payment type> <card type> <card number>
		And order has been successful
		When user checkout a product
		Then checkout page should show default payment card ending 9997
		Examples:
			| payment type          | card type | card number |
			| paymentType_Storecard |           |             |

	@NewUsers
	Scenario Outline: 8 Credit card and gift card
		When user provides required details to create new account
		And I checkout a product
		And I have checked out with the a delivery address "return" and "return"
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		And payment with card "9302" exists
		And payment with card "1111" exists
		Examples:
			| payment type                      | card type | card number       |
			| paymentType_CreditcardAndGiftcard | Visa      | visa_CardNumber_1 |

	@NewUsers
	Scenario Outline: 9 Gift card and store card
		When user provides required details to create new account
		And I checkout a product
		And I have checked out with the a delivery address "return" and "return"
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		And payment with card "5660" exists
		And payment with card "9997" exists
		Examples:
			| payment type                     | card type | card number |
			| paymentType_GiftcardAndStorecard |           |             |

  ## 3DS secure tests ##

	@newlook
		@NewUsers
	Scenario Outline: 10 Card enrolled in 1.0 feature is on
		And feature "feature.payment.3ds2.enabled.uk-site" is switched on
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And new customer checkout a product for collection
		And pays by <payment type> <card type> <card number>
		And goes through pop up window
		Then order has been successful
		And order history shows correct order number
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_2 |

	@newlook
		@NewUsers
	Scenario Outline: 11 Card enrolled in 1.0 feature is on -- PCI microforms
		And feature "feature.payment.3ds2.enabled.uk-site" is switched on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And new customer checkout a product for collection
		And pays by <payment type> <card type> <card number>
		And goes through pop up window
		Then order has been successful
		And order history shows correct order number
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_2 |

	@NewUsers @condev_data
	Scenario Outline: 12 Card enrolled in 1.0 feature is off
		And feature "feature.payment.3ds2.enabled.uk-site" is switched off
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And new customer checkout a product for collection
		And pays by <payment type> <card type> <card number>
		And goes through iframe
		Then order has been successful
		And order history shows correct order number
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_2 |

	@NewUsers
	Scenario Outline: 13 Card enrolled in 1.0 feature is off - PCI microforms
		And feature "feature.payment.3ds2.enabled.uk-site" is switched off
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And new customer checkout a product for collection
		And pays by <payment type> <card type> <card number>
		And goes through iframe
		Then order has been successful
		And order history shows correct order number
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_2 |

	@NewUsers
	Scenario Outline:14 Card enrolled in 2.1 feature is on for France
		And feature "feature.payment.3ds2.enabled.fr-site" is switched on
		And feature "feature.pci.microform.enabled.fr-site" is switched off
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		And user provides required details to create new account
		And I checkout a product
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

	@NewUsers
	Scenario Outline:15 Card enrolled in 2.1 feature is on for France - PCI micro forms
		And feature "feature.payment.3ds2.enabled.fr-site" is switched on
		And feature "feature.pci.microform.enabled.fr-site" is switched on
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		And user provides required details to create new account
		And I checkout a product
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

	@NewUsers
	Scenario Outline:16 Card enrolled in 2.1 feature is off for France
		And feature "feature.payment.3ds2.enabled.fr-site" is switched off
		And feature "feature.pci.microform.enabled.fr-site" is switched off
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		And user provides required details to create new account
		And I checkout a product
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

	@NewUsers
	Scenario Outline:17 Card enrolled in 2.1 feature is off for France - PCI micro forms
		And feature "feature.payment.3ds2.enabled.fr-site" is switched off
		And feature "feature.pci.microform.enabled.fr-site" is switched on
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		And user provides required details to create new account
		And I checkout a product
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


	@NewUsers
	Scenario Outline:17 Gift card and paypal
		When user provides required details to create new account
		And I checkout a product
		And I have checked out with the a delivery address "return" and "return"
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		And payment with card "PayPal" exists
		And payment with card "4704" exists
		Examples:
			| payment type                     | card type | card number |
			| paymentType_GiftcardAndPaypal |           |             |

	@NewUsers
	Scenario:18 Bug NLCC-2682 CVV section disabled after card authorization
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And I checkout a product
		And user opted DELIVERY with post code CH7 4TW
		And select delivery option
		And user makes a purchase with a different mode
		And fill in mobile number
		And pays by paymentType_Card Visa visa_CardNumber_1
		Then order has been successful

	@NewUsers
	Scenario:19 Bug NLCC-2682 CVV section disabled after card authorization
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And I checkout a product
		And user opted DELIVERY with post code CH7 4TW
		And select delivery option
		And user makes a purchase with a different mode
		And fill in mobile number
		And pays by paymentType_Card Visa visa_CardNumber_1
		Then order has been successful

	@NewUsers
	Scenario:20 Bug NLCC-2682 Edit the bag and come back to checkout page
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And Select a product and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Edit bag with quantity "2"
		And pays by paymentType_Virtualcard - -
		Then order has been successful

	@NewUsers
	Scenario:21 Bug NLCC-2682 Refresh checkout page after completing delivery sections
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And Select a product and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And Select payment with virtual card
		And Reload checkout page
		And pays by paymentType_Virtualcard - -
		Then order has been successful

	@DefaultDelivery
	@NewUsers
	Scenario:22 Bug NLCC-2807 Customer can't select C&C tab when no default Home Delivery Option is available
		When user provides required details to create new account
		And I checkout a product
		And select collection button
		And search for nearest collection location to: "London"
		And user opted DELIVERY with post code CH7 4TW
		And select collection button
		Then click link with text Choose another collection point

	@newlook
	@NewUsers
	Scenario:23 Guest makes a payment for home delivery billing address without title
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account
		And I checkout a product
		And user opted DELIVERY with post code CH7 4TW
		And user makes a purchase with a different mode
		And pays by paymentType_Paypal - -
		Then order has been successful
		When customer navigated to my addresses page
		Then number of addresses should be 2
		And I checkout a product
		And select delivery option
		And user makes a purchase with a different mode
		And i change the billing address
		And pays by paymentType_Virtualcard - -
		Then order has been successful

	@NewUsers
	#NLCC-7571 is testing NLCC-7499 - Guest user
	Scenario:24 Guest Customer checkout using store, switch to other payment option and switch back to store card
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

	@NewUsers
	#NLCC-7571 is testing NLCC-7499 - reg user
	Scenario:25 Registered Customer checkout using store, switch to other payment option and switch back to store card
		When user provides required details to create new account
		And I checkout a product
		And search for nearest collection location to: "London"
		And I continue to payment
		And save store card details with no 9826220039999997
		And select payment method as "paypal"
		And save store card details with no 9826220039999997
		And click age verification check box
		And user places order
		Then order has been successful

	@Anonymous
   #NLCC-7710 Guest user
	Scenario Outline:26 Guest Customer checkout using invalid store card number, switch to card payment option
		And Select a product and navigate to payment page
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

	@UserDependent @condev_data
   #NLCC-7710 Registered user
	Scenario Outline:27 Registered Customer checkout using invalid store card number, switch to card payment option
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

	@Anonymous
   #NLCC-7710 Guest user
	Scenario Outline:28 Guest Customer checkout using invalid store card expiry date, switch to card payment option
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

	@UserDependent @condev_data
   #NLCC-7710 Registered user
	Scenario Outline:29 Registered Customer checkout using invalid store card expire date, switch to card payment option
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

	@Anonymous
   #NLCC-7710 Guest user
	Scenario Outline:30 Guest Customer checkout using invalid debit card , switch to store card payment option
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

	@UserDependent @condev_data
   #NLCC-7710 Registered user
	Scenario Outline:31 Registered Customer checkout using invalid debit card , switch to store card payment option
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

@UserDependent @oms_data
   #NLCC-7710 Registered user
	Scenario Outline:32 Registered Customer checkout using invalid store card number, switch to card payment option OMS
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

	@UserDependent @oms_data
   #NLCC-7710 Registered user
	Scenario Outline:33 Registered Customer checkout using invalid store card expire date, switch to card payment option OMS
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

	@UserDependent @oms_data
   #NLCC-7710 Registered user
	Scenario Outline:34 Registered Customer checkout using invalid debit card , switch to store card payment option OMS
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


	@newlook @NewUsers @oms_data @oms_flow
	Scenario Outline:35 New user makes a payment for collection and checks order history Oms
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And new customer checkout a product for collection
		And pays by <payment type> <card type> <card number>
		Then order has been successful
		And order history shows correct order number for Oms
		Examples:
			| payment type          | card type  | card number           |
			| paymentType_Card      | Visa       | visa_CardNumber_1     |
			| paymentType_Card      | Maestro    | Maestro_CardNumber    |
			| paymentType_Card      | Mastercard | Mastercard_CardNumber |
			| paymentType_Storecard |            |                       |
			| paymentType_Paypal    |            |                       |

	@NLRD-1473 @newlook @smokepack
		@NewUsers @oms_flow @oms_data
	Scenario Outline:36 New customer makes a purchase for home delivery
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And Customer makes a home delivery purchase with "<payment type>" and product "<product>"
		And order has been successful
		And user clicks on Track My Order from order confirmation page
		And select order with status "<order_status>" order status with "<Order_view_Link>" link
		Then check if correct order details are displayed
		Examples:
			| payment type | product | order_status |Order_view_Link          |
			| card         | jeans   |     Placed   |     View order          |

	@NLRD-1473 @newlook @NewUsers @oms_data @oms_flow
	Scenario Outline:37 New customer makes a purchase for home delivery
		When user provides required details to create new account
		And Customer makes a home delivery purchase with "<payment type>"
		And order has been successful
		And user clicks on Track My Order from order confirmation page
		And select order with status "<order_status>" order status with "<Order_view_Link>" link
		Then check if correct order details are displayed
		Examples:
			| payment type | order_status |Order_view_Link          |
			| store card   |     Placed   |     View order          |

	@NewUsers @oms_data @oms_flow
	Scenario Outline:38 Card enrolled in 1.0 feature is off
		And feature "feature.payment.3ds2.enabled.uk-site" is switched off
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		When user provides required details to create new account
		And new customer checkout a product for collection
		And pays by <payment type> <card type> <card number>
		And goes through iframe
		Then order has been successful
		And order history shows correct order number for Oms
		Examples:
			| payment type     | card type | card number       |
			| paymentType_Card | Visa      | visa_CardNumber_2 |
