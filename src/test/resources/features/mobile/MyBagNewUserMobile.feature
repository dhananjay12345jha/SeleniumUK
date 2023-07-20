@newlook @NewUsersMobile
Feature: My Bag New user - mobile

	Background:
		Given i navigate to "Newlook" home page
		And user provides required details to create new account on mobile

	Scenario Outline:1 My bag count for new user with one product - mobile
		And customer select product and navigate to my bag page on mobile
		Then my bag header should display <count>
		Examples:
			| count  |
			| My Bag |

	Scenario Outline:2 As a New Customer, I want to be warned when my address cannot be delivered to by the current store, so that I can complete my purchase in the correct hybris store
		And feature "feature.storefront.brexitBanner.enabled" is switched off
		And Select a product and navigate to payment page on mobile
		And select DELIVERY as option
		When change the "<deliveryCountry>" in the delivery country address form the dropdown of checkout page
		Then you should see appropriate message for changed "<messagePart1>" "<deliveryCountry>" "<messagePart2>"

		Examples:
			| deliveryCountry | messagePart1                           | messagePart2           |
			| Finland         | If you change your delivery country to | Please review your bag |














