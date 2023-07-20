@Anonymous2
#NLCC-2897 Implement multi-site landing Page at domain root
Feature:Multi-site landing page

	Scenario: 1 New customer is directed to NewLook.com (without Akamai)
		Given I navigate to "Newlook" homepage for first time with no valid cookies
		Then I see new landing page presented with country list

	Scenario: 2 New customer (without Akamai) can select 'delivery' country
		Given I navigate to "Newlook" homepage for first time with no valid cookies
		Then I see new landing page presented with country list
		When I select 'delivery' country UK from country list
		Then I'm redirected to relevant New Look site
		And I see NL_LOCALE2 cookie and value en_GB_NewLook

	Scenario: 4b Direct URL requests are honoured - New Customer
		Given I navigate to "Newlook" homepage for first time with no valid cookies
		And I access website via a direct URL with locale fr
		Then I'm redirected to requested New Look site with locale fr, country France, currency € and language Français

	Scenario: 5a Direct URL requests are honoured - Existing Customer
		Given I navigate to "Newlook" homepage for first time with no valid cookies
		Then I see new landing page presented with country list
		When I select 'delivery' country UK from country list
		Then I'm redirected to relevant New Look site
		And I access website via a direct URL with locale de
		Then I'm redirected to requested New Look site with locale de, country Deutschland, currency € and language Deutsch

	Scenario: 5b Direct URL requests are honoured - Existing Customer
		Given i navigate to "Newlook" home page
		When user should be able to change the delivery country from landing page
		Then I'm redirected to requested New Look site with locale row, country Italy, currency € and language English

	Scenario: 6 Page Meta Data to be included
		Given I navigate to "Newlook" homepage for first time with no valid cookies
		Then I see new landing page presented with country list
		When I select 'delivery' country UK from country list
		When Navigate to Womens department from mega menu
		Then I see relevant content

	Scenario: 7 Ability to change delivery country within site
		Given i navigate to "Newlook" home page
		Then I'm redirected to relevant New Look site
		And login with username "myBagUserEmail" and password "password"
		And search and add a product to bag
		When navigated to my bag page
		And user should be able to change the delivery country from bag
		And I see NL_LOCALE2 cookie and value en_ROW_NewLook

#		NLCC-3846 - AC1 - Brexit message banner appears on landing page
	Scenario: 8  Brexit message banner appears on landing page
		Given I navigate to "Newlook" homepage for first time with no valid cookies
		Then I see new landing page presented with country list
		Then I see a Brexit message banner displayed at the top of the page

#		NLCC-3846 - AC2 - Brexit message banner links to Help Centre article
	Scenario: 9 Brexit message banner links to Help Centre article
		Given I navigate to "Newlook" homepage for first time with no valid cookies
		Then I see a Brexit message banner displayed at the top of the page
		When I click on the Learn more CTA in the Brexit message banner
		Then I'm redirected to the Brexit Help Centre article

#		 NLCC-3964 AC1 - Brexit message appears on Delivery Country Selector (Bag)
	Scenario: 7 France Brexit message appears on Delivery Country Selector (Bag)
		Given I navigate to "Newlook" homepage for first time with no valid cookies
		And I access website via a direct URL with locale fr
		When I search for a product
		And click on a product image or title
		And select size of the product
		And I click Add to Bag
		When navigated to my bag page
		And click on Change country CTA in the bag
		Then user see the Brexit message displayed above the 'APPLY CHANGE' CTA

#	NLCC-3964 AC1 - Brexit message appears on Delivery Country Selector (Bag)
	Scenario: 8 Germany Brexit message appears on Delivery Country Selector (Bag)
		Given I navigate to "Newlook" homepage for first time with no valid cookies
		And I access website via a direct URL with locale de
		When I search for a product
		And click on a product image or title
		And select size of the product
		And I click Add to Bag
		When navigated to my bag page
		And click on Change country CTA in the bag
		Then user see the Brexit message displayed above the 'APPLY CHANGE' CTA

#	NLCC-3964 AC1 - Brexit message appears on Delivery Country Selector (Bag)
	Scenario: 8 ROW Brexit message appears on Delivery Country Selector (Bag)
		Given I navigate to "Newlook" homepage for first time with no valid cookies
		And I access website via a direct URL with locale row
		When I search for a product
		And click on a product image or title
		And select size of the product
		And I click Add to Bag
		When navigated to my bag page
		And click on Change country CTA in the bag
		Then user see the Brexit message displayed above the 'APPLY CHANGE' CTA

