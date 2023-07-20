@anonymousMobile2Samsung
Feature: Klarna Product Promotion - mobile

	Background:
		Given i navigate to "Newlook" home page
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"

	@CreateProductPromotion @condev_data
	Scenario: 13 Use Klarna to checkout order with product level promotions - mobile
		And Create product level promotions for "shoes"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "shoes" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And pays by paymentType_Virtualcard - -
		Then order has been successful

    @condev_data @RemoveProductPromotion
	Scenario:16 Use Klarna and Gift Card to checkout order with product level promotion - mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And user provides required details to create new account on mobile
		And Select a product "shoes" and navigate to payment page
		And I have checked out with the a delivery address "return" and "return"
		And pays by paymentType_VirtualcardAndGiftcard2 - -
		Then order has been successful
		Then payment with card "Klarna - Pay in 30 days" exists
		Then payment with card "6255" exists

	@RemoveProductPromotion
	Scenario:17 Use Klarna and Gift Card to checkout order with product level promotion-PCI on - mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And user provides required details to create new account on mobile
		And Select a product "shoes" and navigate to payment page
		And I have checked out with the a delivery address "return" and "return"
		And pays by paymentType_VirtualcardAndGiftcard2 - -
		Then order has been successful
		Then payment with card "Klarna - Pay in 30 days" exists
		Then payment with card "6255" exists
		
	@CreateProductPromotion @oms_data
	Scenario: 18 Use Klarna to checkout order with product level promotions OMS - mobile
		And Create product level promotions for "jeans"
		And user provides required details to create new account on mobile
		And Navigate to my address page
		And Added a new address
		And Select a product "jeans" and navigate to payment page
		And search for nearest collection location to: "London"
		And I continue to payment
		And pays by paymentType_Virtualcard - -
		Then order has been successful

  @oms_data
	@RemoveProductPromotion
	Scenario:19 Use Klarna and Gift Card to checkout order with product level promotion OMS- mobile
		And feature "feature.pci.microform.enabled.uk-site" is switched off
		And user provides required details to create new account on mobile
		And Select a product "jeans" and navigate to payment page
		And I have checked out with the a delivery address "return" and "return"
		And pays by paymentType_VirtualcardAndGiftcard2 - -
		Then order has been successful
		Then payment with card "Klarna - Pay in 30 days" exists
		Then payment with card "6255" exists		

