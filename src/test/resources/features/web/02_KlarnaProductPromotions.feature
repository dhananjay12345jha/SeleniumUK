@Anonymous2
Feature: Klarna Product Promotion

	Background:
		Given i navigate to "Newlook" home page
		And feature "feature.payment.klarna.enabled.uk-site" is switched on
		When Klarna Merchant Config is "true"

	@CreateProductPromotion @condev_data
	Scenario:12 Use Klarna to checkout order with product level promotions
		And Create product level promotions for "shoes"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "shoes"
		And pays by paymentType_Virtualcard - -
		Then order has been successful

	@RemoveProductPromotion @condev_data
	Scenario:13 Use Klarna and Gift Card to checkout order with product level promotion
		And Create product level promotions for "shoes"
		And user provides required details to create new account
		And Select a product "shoes" and navigate to payment page
		And I have checked out with the a delivery address "return" and "return"
		And pays by paymentType_VirtualcardAndGiftcard - -
		Then order has been successful
		Then payment with card "Klarna - Pay in 30 days" exists
		Then payment with card "2429" exists

	@CreateProductPromotion @oms_data
	Scenario:14 Use Klarna to checkout order with product level promotions OMS
		And Create product level promotions for "jeans"
		And user provides required details to create new account
		And Navigate to my address page
		And Added a new address
		And checkout a product for collection with code "jeans"
		And pays by paymentType_Virtualcard - -
		Then order has been successful

	@OMSF_Bug @OMSF-995
  #OMSF-995 raised for gift card payment issue on OMS environment
	@RemoveProductPromotion @oms_data
	Scenario:15 Use Klarna and Gift Card to checkout order with product level promotion OMS
		And Create product level promotions for "jeans"
		And user provides required details to create new account
		And Select a product "jeans" and navigate to payment page
		And I have checked out with the a delivery address "return" and "return"
		And pays by paymentType_VirtualcardAndGiftcard - -
		Then order has been successful
		Then payment with card "Klarna - Pay in 30 days" exists
		Then payment with card "2429" exists
