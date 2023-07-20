@anonymousMobile2Samsung
Feature: Klarna Order Promotion - mobile

	Background:
		Given i navigate to "Newlook" home page
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"

	@CreateOrderPromotion @condev_data
	Scenario: 14 Use Klarna to checkout order with order level promotions - mobile
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "shoes" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And pays by paymentType_Virtualcard - -
		Then order has been successful

	@RemoveOrderPromotion @condev_data
	Scenario:15 Use Klarna and Gift Card to checkout order with order level promotion - mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And user provides required details to create new account on mobile
		And Select a product "shoes" and navigate to payment page
		And I have checked out with the a delivery address "return" and "return"
		And pays by paymentType_VirtualcardAndGiftcard - -
		Then order has been successful
		Then payment with card "Klarna - Pay in 30 days" exists
		Then payment with card "2429" exists

	@RemoveOrderPromotion
	Scenario:16 Use Klarna and Gift Card to checkout order with order level promotion - mobile -PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And user provides required details to create new account on mobile
		And Select a product "shoes" and navigate to payment page
		And I have checked out with the a delivery address "return" and "return"
		And pays by paymentType_VirtualcardAndGiftcard - -
		Then order has been successful
		Then payment with card "Klarna - Pay in 30 days" exists
		Then payment with card "2429" exists


	@CreateOrderPromotion @oms_data
	Scenario:17 Use Klarna to checkout order with order level promotions OMS - mobile
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "jeans" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And pays by paymentType_Virtualcard - -
		Then order has been successful
		
  @oms_data
	@RemoveOrderPromotion
	Scenario:18 Use Klarna and Gift Card to checkout order with order level promotion OMS - mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And user provides required details to create new account on mobile
		And Select a product "jeans" and navigate to payment page
		And I have checked out with the a delivery address "return" and "return"
		And pays by paymentType_VirtualcardAndGiftcard - -
		Then order has been successful
		Then payment with card "Klarna - Pay in 30 days" exists
		Then payment with card "2429" exists		
		

