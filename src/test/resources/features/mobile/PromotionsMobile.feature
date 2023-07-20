@newlook @NewUsersMobile @mobilePromotions
Feature: Promotions Feature Mobile

	Background: I go to New Looks home page
		Given i navigate to "Newlook" home page

	@condev_data
	Scenario Outline:1 Promotions based on percentage discounts - mobile
		Given search previous price product
		And click on a product image or title
		And select size of the product "UK 8"
		And I click Add to Bag
		And click on the bag icon
		And apply the "<PromotionCode>" in the promo box
		When click apply
		Then you should see percentage discount "<message>"
		Examples:
			| PromotionCode | message                                       |
			| 10PCOFF       | 10 percentage discount - You have saved £0.90 |
			| 15PCOFF       | 15 percentage discount - You have saved £1.35 |
			| 20PCOFF       | 20 percentage discount - You have saved £1.80 |
			| 50PCOFF       | 50 percentage discount - You have saved £4.50 |

	Scenario Outline:2 Promotions based on price discounts - mobile
		When I search for the product with "363626272"
		And click on a product image or title
		Then select size of the product
		And I click Add to Bag
		And click on the bag icon
		And I click edit on mobile for line item 1
		When I change the quantity to "Qty: <quantity>" of a line item 1
		And apply the "<PromotionCode>" in the promo box
		When click apply
		Then you should see price discount "<message>"
		Examples:
			| quantity | PromotionCode | message                             |
			| 3        | 5OFF          | You saved £5.00 for spending over   |
			| 3        | 10OFF         | You saved £10.00 for spending over  |
			| 3        | 15OFF         | You saved £15.00 for spending over  |
			| 3        | 20OFF         | You saved £20.00 for spending over  |
			| 3        | 50OFF         | You saved £50.00 for spending over  |
			| 3        | 100OFF        | You saved £100.00 for spending over |

    @condev_data
	Scenario Outline:3 Products promotions removed by decreasing the line quantity for multiple items in the cart - mobile
		When I search for a product
		And click on a product image or title
		And select size of the product "12L32"
		And I click Add to Bag
		When search multi colour product
		And click on a product image or title
		And select size of the product
		And I click Add to Bag
		And click on the bag icon
		And I click edit on mobile for line item 1
		When I change the quantity to "Qty: <quantity>" of a line item 1
		And proceed to remove one products from full cart page
		And the potential promotion message is displayed

		Examples:
			| quantity |
			| 6        |


	Scenario Outline:4 Decrease the quantity of the item cart - mobile
		When I search for a product
		And click on a product image or title
		And select size of the product
		And I click Add to Bag
		Then click on the bag icon
		And I click edit on mobile for line item 1
		When I change the quantity to "Qty: <quantity>" of a line item 1
		And I click edit on mobile for line item 1
		When I change the quantity to "Qty: <decreasedQty>" of a line item 1
		Then the potential promotion message is displayed
		Examples:
			| quantity | decreasedQty |
			| 8        | 3            |

	Scenario: 5 Fixed price discount message shows for promo - mobile
		And I search for the product with "365907910"
		And click on a product image or title
		And I click Add to Bag
		And click on the bag icon
		Then I should see the fixed price discount message

	Scenario Outline:6 Promotions based on price discounts restrict to CRM Segment - mobile
		Given feature "feature.customer.segments.relation.enabled" is switched on
		And I search for the product with "360197241"
		And click on a product image or title
		And select size of the product
		And I click Add to Bag
		And click on the bag icon
		And apply the "<PromotionCode>" in the promo box
		When click apply
		Then locked promo code error is displayed
		Examples:
			| PromotionCode |
			| 1000OFF       |

	Scenario Outline:7 Promotions based on price discounts allow CRM Segment users - mobile
		Given feature "feature.customer.segments.relation.enabled" is switched on
		And login with username "crmSegmentSpecificPromotionsUserEmail" and password "password"
		And click on the bag icon
		And apply the "<PromotionCode>" in the promo box
		When click apply
		Then you should see price discount "<message>"
		Examples:
			| PromotionCode | message                       |
			| 10OFF         | You saved £10.00 for spending |

	@oms_data
	Scenario Outline:8 Promotions based on percentage discounts OMS - Mobile
		When search previous price product for oms
		And click on a product image or title
		And select size of the product "UK 7"
		And I click Add to Bag
		And click on the bag icon
		When I change the quantity to "Qty: <quantity>" of a line item 1
		And apply the "<PromotionCode>" in the promo box
		When click apply
		Then you should see percentage discount "<message>"
		Examples:
			| quantity | PromotionCode | message                                        |
			| 3        | 10PCOFF       | 10 percentage discount - You have saved £4.80 |
			| 3        | 15PCOFF       | 15 percentage discount - You have saved £7.20  |
			| 3        | 20PCOFF       | 20 percentage discount - You have saved £9.60  |
			| 3        | 50PCOFF       | 50 percentage discount - You have saved £24.00 |
			
    @oms_data
	Scenario Outline:9 Products promotions removed by decreasing the line quantity for multiple items in the cart OMS- mobile
		When I search for a product
		And click on a product image or title
		And select size of the product "18L30"
		And I click Add to Bag
		When search multi colour product
		And click on a product image or title
		And select size of the product
		And I click Add to Bag
		And click on the bag icon
		And I click edit on mobile for line item 1
		When I change the quantity to "Qty: <quantity>" of a line item 1
		And proceed to remove one products from full cart page
		And the potential promotion message is displayed		
		
		Examples:
			| quantity |
			| 6        |				
