@Anonymous2
Feature: Klarna Order Promotion

	Background:
		Given i navigate to "Newlook" home page
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"

	@CreateOrderPromotion @condev_data
	Scenario: 15 Use Klarna to checkout order with order level promotions
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "shoes"
		And pays by paymentType_Virtualcard - -
		Then order has been successful

	@RemoveOrderPromotion @condev_data
	Scenario:16 Use Klarna and Gift Card to checkout order with order level promotion
		And user provides required details to create new account
		And Select a product "shoes" and navigate to payment page
		And I have checked out with the a delivery address "return" and "return"
		And pays by paymentType_VirtualcardAndGiftcard1 - -
		Then order has been successful
		And order history shows correct order number
		And Selects last order
		Then payment with card "Klarna - Pay in 30 days" exists
		Then payment with card "7198" exists

	@CreateOrderPromotion @oms_data
	Scenario: 17 Use Klarna to checkout order with order level promotions OMS
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "jeans"
		And pays by paymentType_Virtualcard - -
		Then order has been successful

	@OMSF_Bug @OMSF-995
  #OMSF-995 raised for gift card payment issue on OMS environment
	@RemoveOrderPromotion @oms_data
	Scenario:18 Use Klarna and Gift Card to checkout order with order level promotion OMS
		And user provides required details to create new account
		And Select a product "jeans" and navigate to payment page
		And I have checked out with the a delivery address "return" and "return"
		And pays by paymentType_VirtualcardAndGiftcard1 - -
		Then order has been successful
		And order history shows correct order number
		And Selects last order
		Then payment with card "Klarna - Pay in 30 days" exists
		Then payment with card "7198" exists
