#noinspection CucumberUndefinedStep
Feature: PDP feature

	Background:
		Given i navigate to "Newlook" home page

	@newlook @Anonymous @single
	Scenario:1 low stock item
		And feature "feature.storefront.stock.urgency.messaging.pdp" is switched off
		When search low stock product
		And click on a product image or title
		Then pdp size options should contain a size with note 'FEW REMAINING'

	@newlook @Anonymous
	Scenario:2 As a Customer, I want to see a variety of information associated with a product on PDP page
		When I search for a product
		And click on a product image or title
		Then I will see the following information related to the Colour-level product

	@newlook @Anonymous
	Scenario:3 As a Customer, when I click on different color from the full PDP page then I should see products displayed based on the color I have selected
		When search multi colour product
		And click on a product image or title
		Then a colour swatch panel should display swatches that represent all alternative colour variants

	@newlook @smokepack @Anonymous
	Scenario:4 I should be able to close the zoom overlay window by clicking on X and return back to PDP page
		When I search for the product with "shoes"
		And click on a product image or title
		And click on product image
		When clicked on X of the page over lay
		Then I should navigate back to PDP page

	@newlook @Anonymous @condev_data
	Scenario:5 I should be see previous and current price on the PDP page
		When search previous price product
		And click on a product image or title
		Then the previous price and the current price should be shown

	@newlook @Anonymous
	Scenario:6 Different price for the product with colour variants on the PDP page
		When search for the multicolour product
		And click on a product image or title
		When I select an alternative variant from the colour swatch panel
		Then this variant has a different price

	@newlook @Anonymous
	Scenario:6.1 Same price for the products with the same colour but different sizes on the PDP page
		When search for the multicolour product
		And click on a product image or title
		And the price DOES NOT update when I select between size variants

	@newlook @Anonymous
	Scenario:7 Add to bag button disabled on PDP page if not size is selected
		When I search for a product
		And click on a product image or title
		Then the add to bag button should be enabled if size is selected

	@newlook @Anonymous
	Scenario:8 Add to bag button enabled on PDP page if size is selected
		When I search for a product
		And click on a product image or title
		Then select size of the product
		Then the add to bag button should be enabled if size is selected

	@newlook @Anonymous
	Scenario:9 Add to bag button should be enabled on the PDP page even if no size is selected
		When I search for a product
		And click on a product image or title
		Then Add to bag button should always be enabled

	@newlook @Anonymous
	Scenario:10 Validation message should be displayed when user clicks "Add to bag" button without selecting a size
		When I search for a product
		And click on a product image or title
		And Click Add to Bag button without selecting a size
		Then Validation message to select a size should be displayed

	@newlook @Anonymous
	Scenario Outline:11 & 12 View the multiPDP page for the product
		When search and select the product "<Product>" with the perfect partner
		And click on the searched product name
		Then the Multi PDP page should be displayed with the perfect partner products
			| White Contrast Trim T-Shirt   |
			| Dark Pink Roll Sleeve T-Shirt |
			| Navy Suedette Desert Boots    |
		And the displayed products count on the Multi PDP page should match with the "<No. of products set in HMC for multi PDP>"
		Examples:
			| Product   | No. of products set in HMC for multi PDP |
			| 353844010 | 3                                        |

	@newlook @Anonymous
	Scenario Outline:13 User should see if the thumbnail component exists on the Multi PDP page
		When search and select the product "<Product>" with the perfect partner
		And click on the searched product name
		Then the thumbnail component should exist for the selected product and its associated perfect partner products
		Examples:
			| Product   |
			| 353844010 |

	@newlook @Anonymous
	Scenario:14 Add to bag button should be enabled on the PDP page even if no size is selected
		When I search for a product
		And click on a product image or title
		Then Add to bag button should always be enabled

	@newlook @Anonymous
	Scenario:15 As a Customer, I want to have the option to continue shopping or proceed to the Cart once I've added an item to my cart
		When I search for a product
		And click on a product image or title
		And select size of the product
		And I click Add to Bag
		And I am not automatically taken to the full cart
		When I click on view bag and checkout button on pdp page
		Then I am taken to the full cart page

	@newlook @Anonymous
	Scenario:16 Product with no size variant should have no size picker and color swatch on PDP page
		When search one size product
		And click on a product image or title
		And I am not presented with a size picker
		Then I am shown text that tells me the product comes in One Size only

	@newlook @Anonymous
	Scenario:17 product with multiple colors display's relevant color_products upon selecting the color from the swatch
		When search multi colour product
		And click on a product image or title
		And as I select different color from the swatch the product code should be changed

	@newlook @Anonymous @condev_data
	Scenario:18 cart page has product color and size selected in the pdp
		When I search for a product
		And click on a product image or title
		And select size of the product "12L32"
		And I click Add to Bag
		And click on the bag icon
		And selected size 12L32 is displayed

	@newlook @Anonymous
	Scenario:19 As a customer I can access information about the delivery option offered by new look for UK
		When I search for a product with system property "productCode"
		And click on a product image or title
		When I see a link that lets me know that there are alternative delivery options offered by New Look
		Then I can select the link
		And the content will open in a side panel from the left and I am not navigated away from the current page

	@newlook @Anonymous
	Scenario Outline:20 As a customer I can access information about the delivery option offered by new look for NON UK
		And I click on the locale selector in the header
		And  I select a different country <country> using the drop down
		And user search for a product with product code
		And click on a product image or title
		And I see a link that lets me know that there are alternative delivery options offered by New Look
		Then I can select the link
		And the content will open in a side panel from the left and I am not navigated away from the current page
		Examples:
			| country         |
			| country_France  |
			| country_Germany |
			| country_USA     |
			| country_Albania |

    #When I click on the locale selector in the header
    #And I select a different country "<country>" using the drop down
    #When I select change settings
    #When I search for the product with the "2161296"
    #And click on a product image or title
    #When I see a link that lets me know that there are alternative delivery options offered by New Look
    #Then I can select the link
    #And the content will open in a side panel from the left and I am not navigated away from the current page
    #And the delivery options are listed: <mode1>, <mode2>

    #more generic check points

  # The below scenario has combined with the scenario 15.
  #@todo
  #Scenario:16 As a customer I can access information about the delivery option offered by new look, so that I can make a choice which service I would like to use for my order on PDP page
   # When I click on the locale selector in the header
    #And I select a different country "United States" using the drop down
    #When I select change settings
    #When I search for the product with the "2161296"
    #And click on a product image or title
    #When I see a link that lets me know that there are alternative delivery options offered by New Look
    #Then I can select the link
    #And the content will open in a side panel from the left and I am not navigated away from the current page
    #And the delivery options are listed: Global Standard Delivery, Standard Delivery, Global Sunday

	@newlook @Anonymous
		@Ignore
		#Ignoring for now as NLCC-7599 is re-designing my cart to view it at all times
	Scenario:21 As a customer I can access information about the delivery option offered by new look, so that I can make a choice which service I would like to use for my order on fill cart page
		When user search for a product with product code
		And click on a product image or title

   #When I search for a product
   #And click on a product image or title
		Then select size of the product
		And I click Add to Bag
		Then click on the bag icon
		When I see a link that lets me know that there are alternative delivery options offered by New Look from full cart page
		Then I can select the link from full cart page
		And the content will open in a side panel from the left and I am not navigated away from the current page
    #And the delivery options listed are all delivery modes available and active for the current base store

	@newlook @Anonymous
		@Ignore
		#Ignoring for now as NLCC-7599 is re-designing my cart to view it at all times
	Scenario Outline:
	22 As a customer I can access information about the delivery option offered by new look, so that I can make a choice which service I would like to use for my order on fill cart page
		And I click on the locale selector in the header
		And I select a different country <Country> using the drop down
		And user search for a product with product code
		And click on a product image or title
		Then select size of the product
		And I click Add to Bag
		Then click on the bag icon
		When I see a link that lets me know that there are alternative delivery options offered by New Look from full cart page
		Then I can select the link from full cart page
		And the content will open in a side panel from the left and I am not navigated away from the current page
    #And the delivery options are listed: Germany Standard Tracked, Germany Express

		Examples:
			| Country         |
			| country_Germany |
			| country_USA     |
			| country_France  |
			| country_Albania |

  #@todo
  #Scenario:19 As a customer I can access information about the delivery option offered by new look, so that I can make a choice which service I would like to use for my order on full cart page
    #When I click on the locale selector in the header
    #And I select a different country "United States" using the drop down
    #When I select change settings
    #When I search for a product
    #And click on a product image or title
    #Then select size of the product
    #And I click Add to Bag
    #Then click on the bag icon
    #When I see a link that lets me know that there are alternative delivery options offered by New Look from full cart page
    #Then I can select the link from full cart page
    #And the content will open in a side panel from the left and I am not navigated away from the current page
    #And the delivery options are listed: USA Standard Tracked, USA Express

	@NLRD-2077 @newlook @Anonymous
	Scenario:23 Validate when the perfect partner is setup, it appears in the carousel in PDP and on clicking a product opens in the side panel.
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched off
		When user search for a product with product code
		And click on a product image or title
		And user click on a product that appears in carousel
		Then the product opens in side panel

#  TBC
	Scenario:24 Check that Wear with carousel is not displayed on side panel view.
		When user search for a product with product code
		And click on a product image or title
		And user click on a product that appears in carousel
		Then the wear with carousel should not be displayed on side panel

	@NLRD-2077 @newlook @Anonymous
	Scenario:25 Check that Show More/Show Less is present on the Product Details and Care section on PDP.
		When user search for a product with product code
		And click on a product image or title
		Then click on show more to expand
		And click on show less to collapse

	@NLRD-2077 @newlook @Anonymous
	Scenario:26 Check that wear with carousel products can be configured with add to saved item
		When user search for a product with product code
		And click on a product image or title
		And add the product to add to saved items
		Then check if the product is added to saved items in side panel

	@NLRD-2077 @newlook @Anonymous
	Scenario:27 Check that wear with carousel products can be configured with colour picker
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched off
		When user search for a product with product code
		And click on a product image or title
		Then select a specific product 'Yellow Scoop Neck Vest' from the wear with carousel section
		And check if colour is updated when different colour is applied using colour picker

	@NLRD-2077 @newlook @Anonymous
	Scenario:28 Check that wear with carousel products can be configured with highlight badge
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched off
		When user search for a product with product code
		And click on a product image or title
		Then select a specific product 'Blue Button Front Flared Jeans' from the wear with carousel section
		And check the product with highlight badge

	@NLRD-2077 @newlook @Anonymous
	Scenario:29 Check that wear with carousel products can be configured with promotion badge
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched off
		When user search for a product with product code
		And click on a product image or title
		Then select a specific product 'Blue Pug Print Trainer Socks' from the wear with carousel section
		And check the product with promotion link

	@NLRD-2077 @newlook @Anonymous
	Scenario:30 Check that wear with carousel products can be configured with updated price
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched off
		When user search for a product with product code
		And click on a product image or title
		Then select a specific product 'Blue Button Front Flared Jeans' from the wear with carousel section
		And check the product with product name

	@NLRD-2077 @newlook @Anonymous
	Scenario:31 Check that wear with carousel products can be configured with product name
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched off
		When user search for a product with product code
		And click on a product image or title
		Then select a specific product 'Blue Pug Print Trainer Socks' from the wear with carousel section
		And check the product with product name

	@NLRD-2272 @newlook @Anonymous
	Scenario Outline:32 Check that delivery options displayed in pdp page
		When search and select the product "<Product>" with the perfect partner
		And click on the searched product name
		Then delivery options are displayed
			| Delivery icon          |
			| FREE Standard Delivery |
			| Free Delivery*         |
			| Free Returns*          |
		Examples:
			| Product   |
			| 363066817 |
			| 363126349 |

	@NLRD-2458 @Anonymous
	Scenario: 33 Verify saved items heart icon displayed in pdp page
		When customer search for product productCode_stock and navigates to pdp page
		Then saved items heart icon should be displayed


	@newlook @Anonymous
	Scenario: 34 Storelookup on the PDP page
		When I search for a product
		And click on a product image or title
		Then the store lookup button should be displayed on the page

	@newlook @Anonymous
	Scenario: 35 Storelookup on the PDP page
		When I search for a product
		And click on a product image or title
		And click on the store lookup button
		Then side panel should open

	@newlook @Anonymous
	Scenario: 36 Storelookup on the PDP page
		When I search for a product
		And click on a product image or title
		Then the store lookup button should be displayed on the page

	@newlook @Anonymous @NLRD-2456
	Scenario: 37 Validate the fields in the side panel after user clicks on the 'FIND IN STORE' button
		When search multi colour product
		And click on a product image or title
		And click on the store lookup button
		Then the 'Product Name','Product Size Picker','Colour Swatch', 'Location Search' and 'Check Availability Button' should be present on the page

	@newlook @Anonymous @NLRD-2456 @condev_data
	Scenario: 38 Check if the default product size has selected on the side panel
		When search multi colour product
		And click on a product image or title
		And select size of the product "S"
		And click on the store lookup button
		Then the selected size "S" should be pre-populated on the page

	@newlook @Anonymous @nlrd-2562
	Scenario: 39 Validation message for the size picker if user clicks check availability button in the side panel without selecting the product size
		When I search for a product
		And click on a product image or title
		And click on the store lookup button
		And switch to side panel
		And click on 'Check Availibility' button
		Then Validation message to select a size should be displayed in the side panel

	@newlook @Anonymous @nlrd-2562
	Scenario Outline: 40 Validation message should be displayed if the user tries to search with the invalid search criteria
		When search multi colour product
		And click on a product image or title
		And select size of the product "S"
		And click on the store lookup button
		And switch to side panel
		Then correct validation message should be displayed if the user tries to search with invalid search "<InvalidCriteria>" criteria
		Examples:
			| InvalidCriteria |
			|                 |
			| null            |

	@newlook @Anonymous @nlrd-2562
	Scenario Outline: 41 Validate the results for the correct search criteria
		When search multi colour product
		And click on a product image or title
		And select size of the product "S"
		And click on the store lookup button
		And switch to side panel
		And enter the correct search "<ValidCriteria>" criteria and search
		Then the below details should be displayed in the result
		Examples:
			| ValidCriteria |
			| London        |
			| bb1 3bb       |

	@newlook @Anonymous @nlrd-2562
	Scenario Outline: 42 Check if the store details page opens on click of "More info and opening timings" link
		When search multi colour product
		And click on a product image or title
		And select size of the product "S"
		And click on the store lookup button
		And switch to side panel
		And enter the correct search "<ValidCriteria>" criteria and search
		Then the store details page should open after clicking on the 'More info and opening timings' link
		Examples:
			| ValidCriteria |
			| London        |

	@newlook @Anonymous @nlrd-2562
	Scenario Outline: 43 Check if the map exists on the side-panel
		When search multi colour product
		And click on a product image or title
		And select size of the product "S"
		And click on the store lookup button
		And switch to side panel
		And enter the correct search "<ValidCriteria>" criteria and search
		Then the map should be available in the side panel after clicking on the 'Plus' button
		Examples:
			| ValidCriteria |
			| London        |

	@newlook @Anonymous @nlrd-2562
	Scenario Outline: 44 Check if the stock information for the searched result displays on the side-panel
		When search multi colour product
		And click on a product image or title
		And select size of the product "S"
		And click on the store lookup button
		And switch to side panel
		And enter the correct search "<ValidCriteria>" criteria and search
		Then the the stock information for the searched result displays
		Examples:
			| ValidCriteria |
			| Manchester    |

	@newlook @Anonymous @nlrd-2562
	Scenario Outline: 45 Check if the disclaimer text displays on the side-panel
		When search multi colour product
		And click on a product image or title
		And select size of the product "S"
		And click on the store lookup button
		And switch to side panel
		And enter the correct search "<ValidCriteria>" criteria and search
		Then the disclaimer text displays
		Examples:
			| ValidCriteria |
			| Manchester    |

	@newlook @Anonymous @nlrd-2562
	Scenario Outline: 46 Check if the max "6" results displays on the page after searching the stores
		When search multi colour product
		And click on a product image or title
		And select size of the product "S"
		And click on the store lookup button
		And switch to side panel
		And enter the correct search "<ValidCriteria>" criteria and search
		Then maximum "<storeResults>" should be displayed on the page
		Examples:
			| ValidCriteria | storeResults |
			| Manchester    | 6            |

#model wears
	@newlook @Anonymous
	Scenario: 47 Model wears product - Click on carousel product and confirm product name is correct
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched off
		When customer search for product productCode_ModelWears and navigates to pdp page
		And select a specific product 'Plus Size Blue Stripe Wide Leg Trousers' from the wear with carousel section
		Then the wear with carousel should not be displayed on side panel
		And I should be navigated to the full PDP page and the product name is "Plus Size Blue Stripe Wide Leg Trousers"

	@newlook @Anonymous
	Scenario: 48 Model wears product - Click on carousel product and save carousel items
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched off
		When customer search for product productCode_ModelWears and navigates to pdp page
		And save 3 multi pdp items
		And viewing the saved items side pane
		Then 3 saved items will be present

	@newlook @Anonymous
	Scenario: 49 Model wears product - Click on carousel product, save and remove saved item
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched off
		When customer search for product productCode_ModelWears and navigates to pdp page
		And save 3 multi pdp items
		And remove 1 saved multi pdp items
		And viewing the saved items side pane
		Then 2 saved items will be present

	@newlook @Anonymous
	Scenario: 50 Model wears product - Click on carousel product text and confirm in the side panel
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched off
		When customer search for product productCode_ModelWears and navigates to pdp page
		And user clicks on shop to look button
		And user click on a product that appears in carousel
		Then the wear with carousel should not be displayed on side panel

	@newlook @Anonymous
	Scenario: 51 Model wears product - Navigate to out of stock url and the carousel items are still available
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched off
		When user navigates to "OutOfStockProductUrl"
		Then you should see a out of stock message , the size drop down is not present
		When select a specific product 'Plus Size Blue Stripe Wide Leg Trousers' from the wear with carousel section
		Then the wear with carousel should not be displayed on side panel
		And I should be navigated to the full PDP page and the product name is "Plus Size Blue Stripe Wide Leg Trousers"

	@newlook @Anonymous
	Scenario: 52 Model wears product - Main product is not available in the uk but carousel items are
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched off
		When customer search for product productCode_ModelWearsNotAvailableInUk and navigates to pdp page
		And select a specific product 'Plus Size Blue Stripe Wide Leg Trousers' from the wear with carousel section
		Then the wear with carousel should not be displayed on side panel


	@newlook @Anonymous
	Scenario Outline:53 View the multiPDP page for the product
		When search and select the product "<Product>" with the perfect partner
		And click on the searched product name
		Then delivery options are displayed
			| Delivery icon |
		Examples:
			| Product   |
			| 363066817 |

	@newlook @Anonymous
	Scenario: 54 Click on carousel image should open in seperate PDP page and not side panel
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched off
		When customer search for product productCode_ModelWears and navigates to pdp page
		And I click on the carousel image
		Then the wear with carousel should not be displayed on side panel
		And I should be navigated to the full PDP page and the product name is "Plus Size Blue Stripe Wide Leg Trousers"
#      |363126349   |

	#NLCC-4022
	@newlook @Anonymous
	Scenario: 55 Model wears new product carousel - Click on carousel product and confirm product name is correct
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched on
		When customer search for product productCode_ModelWears and navigates to pdp page
		And select a specific product 'Plus Size Blue Stripe Wide Leg Trousers' from the product carousel section
		Then the carousel product should not be displayed on side panel
		And I should be navigated to the full PDP page and the product name is "Plus Size Blue Stripe Wide Leg Trousers"

		#NLCC-4022
	@newlook @Anonymous
	Scenario: 56 Model wears new product carousel - Click on carousel product and save carousel items
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched on
		When customer search for product productCode_ModelWears and navigates to pdp page
		And save 3 carousel pdp items
		And viewing the saved items side pane
		Then 3 saved items will be present

		#NLCC-4022
	@newlook @Anonymous
	Scenario: 57 Model wears new product carousel- Click on carousel product, save and remove saved item
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched on
		When customer search for product productCode_ModelWears and navigates to pdp page
		And save 3 multi pdp items
		And remove 1 saved carousel pdp items
		And viewing the saved items side pane
		Then 2 saved items will be present

		#NLCC-4022
	@newlook @Anonymous
	Scenario: 58 Model wears new product carousel- Click on carousel product text and confirm in the side panel
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched on
		When customer search for product productCode_ModelWears and navigates to pdp page
		And user clicks on shop to look button
		And user click on a product that appears in carousel
		Then the carousel product should not be displayed on side panel

		#NLCC-4022
	@newlook @Anonymous @condev_data
	Scenario: 59 Model wears new product carousel - Navigate to out of stock url and the carousel items are still available
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched on
		When user navigates to "OutOfStockProductUrl"
		Then you should see a out of stock message , the size drop down is not present
		When select a specific product 'Plus Size Blue Stripe Wide Leg Trousers' from the product carousel section
		Then the carousel product should not be displayed on side panel
		And I should be navigated to the full PDP page and the product name is "Plus Size Blue Stripe Wide Leg Trousers"

		#NLCC-4022
	@newlook @Anonymous
	Scenario: 60 Model wears new product carousel - Main product is not available in the uk but carousel items are
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched on
		When customer search for product productCode_ModelWearsNotAvailableInUk and navigates to pdp page
		And select a specific product 'Plus Size Blue Stripe Wide Leg Trousers' from the product carousel section
		Then the carousel product should not be displayed on side panel

		#NLCC-4022
	@newlook @Anonymous
	Scenario: 61 Model wears new product carousel-Click on carousel image
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched on
		When customer search for product productCode_ModelWears and navigates to pdp page
		And user clicks on shop to look button
		And I click on the carousel image
		Then the carousel product should not be displayed on side panel
		And I should be navigated to the full PDP page and the product name is "Plus Size Blue Stripe Wide Leg Trousers"

		#NLCC-4022
	@newlook @Anonymous
	@Ignore # This functionality has been replaced by NLCC-4022 and is covered by its tests
	Scenario:62 Model wears new product carousel previous and markeddown price on the PDP page
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched on
		When customer search for product productCode_ModelWears and navigates to pdp page
		And user clicks on shop to look button
		Then the previous price and the marked down price should be displayed for carousel product "Blue Stripe Metallic Wrap Front Dress"

		#NLCC-4022
	@newlook @Anonymous
	Scenario:63 Model wears new product carousel current price on the PDP page
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched on
		When customer search for product productCode_ModelWears and navigates to pdp page
		And user clicks on shop to look button
		Then the current price should be displayed for carousel product "Plus Size Blue Stripe Wide Leg Trousers"

	@NLDS-1580 @Anonymous
	Scenario: 64 Validate product's review and ratings section non-availability on PDP page
		When I disable feature bazaarVoice for productNumber "bazaarVoiceProductNumber"
		And customer search for product bazaarVoiceProductNumber and navigates to pdp page
		Then PDP review and rating section should not exist

#NLCC-4330
	@newlook @Anonymous
	Scenario: 65 Size guide - one size
		When search one size product
		And click on a product image or title
		Then size guide cta should not be displayed
#NLCC-4330
	@newlook @Anonymous
	Scenario: 66 Size guide - multi color
		When search multi colour product
		And click on a product image or title
		Then size guide cta should be displayed
		When I click on the size guide cta
		Then size guide side panel should open
#NLCC-4330
	@newlook @Anonymous
	Scenario: 67 Size guide - Shoes
		When I search for the product with "shoes"
		And click on a product image or title
		Then size guide cta should be displayed
		When I click on the size guide cta
		Then size guide side panel should open

#NLCC-4023
	@newlook @Anonymous
	Scenario:68 Model wears product carousel - multi size product
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched on
		When customer search for product productCode_ModelWears and navigates to pdp page
		And I select size and add to bag carousel product "Plus Size Blue Stripe Wide Leg Trousers"
		Then product added to bag message should be displayed for carousel product "Plus Size Blue Stripe Wide Leg Trousers"
		And click on the bag icon
		Then product "Plus Size Blue Stripe Wide Leg Trousers" should be added to the bag

#NLCC-4023
	@newlook @Anonymous
	Scenario:69 Model wears product carousel - one size product
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched on
		When customer search for product productCode_ModelWears and navigates to pdp page
		Then size selector should not be present for carousel product "Pure Colour Dark Red Nail Polish"
		And One Size message should be displayed for carousel product "Pure Colour Dark Red Nail Polish"
		When I add one size product "Pure Colour Dark Red Nail Polish" from product carousel
		Then product added to bag message should be displayed for carousel product "Pure Colour Dark Red Nail Polish"
		And click on the bag icon
		Then product "Pure Colour Dark Red Nail Polish" should be added to the bag

#NLCC-4023
	@newlook @Anonymous
	Scenario:70 Model wears product carousel - error message if size not selected
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched on
		When customer search for product productCode_ModelWears and navigates to pdp page
		And add to bag carousel product "Plus Size Blue Stripe Wide Leg Trousers"
		Then please choose size message should be displayed for carousel product "Plus Size Blue Stripe Wide Leg Trousers"

#NLTA-823, NLCC-4327
	@newlook @Anonymous @condev_data
	Scenario: 71 - Stock equal to or less than 5 units (Single Size Item)
		And feature "feature.storefront.stock.urgency.messaging.pdp" is switched on
		When customer search for product productCode_OneSize_LowStock and navigates to pdp page
		Then message "Only 5 items left!" will display next to the one size string

#NLTA-823, NLCC-4327
	@newlook @Anonymous @condev_data
	Scenario: 72 - Stock equal to or less than 5 units (Multi Size Item)
		And feature "feature.storefront.stock.urgency.messaging.pdp" is switched on
		When customer search for product productCode_MultiSize_LowStock and navigates to pdp page
		When I open the drop down size picker
		And I select low stock size "UK 8"
		Then message "Only 2 items left!" will display next to the size "UK 8" value
		And I select low stock size "UK 10"
		Then message "Only 3 items left!" will display next to the size "UK 10" value
		And I select low stock size "UK 12"
		Then message "Only 1 item left!" will display next to the size "UK 12" value
		And I select low stock size "UK 14"
		Then message "Only 5 items left!" will display next to the size "UK 14" value
		And I select low stock size "UK 16"
		Then message "Only 4 items left!" will display next to the size "UK 16" value

	@Anonymous
	@NLCC-4251
	Scenario: 73 - PDP Carousel - Add to favourites button
	  	# Without a size selected
		Given I navigate to the PDP of a "pdpCarousel" product
		And The add to favourites button is visible under the "carousel.multiSize" carousel item
		When I click on the add to favourites button under the "carousel.multiSize" carousel item
		And I click on the show favourites button
		Then The "carousel.multiSize" item is visible in the favourites section
		And The size selector is visible under the "carousel.multiSize" favourites item

	  	# With a size selected
		Given I close the favourites section
		When I select a size under the "carousel.multiSize" carousel item
		And I click on the add to favourites button under the "carousel.multiSize" carousel item
		And I click on the show favourites button
		Then The "carousel.multiSize" item is visible in the favourites section
		And The size under the "carousel.multiSize" favourites item matches selected size

	@NLCC-4022 @Anonymous
	Scenario: 74 - PDP Carousel - Carousel product details
	  # Regular price
		Given I navigate to the PDP of a "pdpCarousel" product
		And The carousel product details are visible on the "carousel.multiSize" product
		When I click on the title of the "carousel.multiSize" product in the carousel
		Then I am on the "carousel.multiSize" PDP

	  # Was/now price
		Given I navigate to the PDP of a "pdpCarousel.wasNowPrice" product
		And The was/now price is visible on the "carousel.wasNowPrice" carousel product
		When I click on the title of the "carousel.wasNowPrice" product in the carousel
		Then I am on the "carousel.wasNowPrice" PDP

	@NLCC-4898 @Anonymous
	Scenario: 75 - PDP - Special Character Item - Add to cart, saved item
		Given feature "feature.storefront.datalayer.v2.enabled" is switched on
		When user provides required details to create new account
		And I search for the product with "red midi dress"
		And click on a product image or title
		Then Total amount of products in basket is "0"
		And select size of the product
		And I click Add to Bag
		Then Total amount of products in basket is "1"
		And Total saved item count is "0"
		And add the product to add to saved items
		And Total saved item count is "1"
		And viewing the saved item side pane
		When viewing the saved items page
		And product should be displayed in saved items
		And remove the product from the saved items
		And Total saved item count is "0"
		And review the quick view cart
		And change the "UK 14" of the product
		And I change the quantity to "Qty: 2" of a line item 1
		Then Total amount of products in basket is "2"
		When I change the quantity to "Qty: 3" of a line item 1
		Then Total amount of products in basket is "3"

	#NLCC-5607
	#NLCC-7157 is testing NLCC-6980
	@newlook @Anonymous @condev_data
	Scenario: 76 - 100% Out of stock PDP - Guest
		When customer navigates to pdp page of "368612016"
		Then shop related item CTA is displayed
		And carousel is available above product
		And carousel contains all necessary components
		And user should not be able to scroll through different item pictures on pdp

   #NLCC-5607
	#NLCC-7157 is testing NLCC-6980
	@newlook @NewUsers @condev_data
	Scenario: 77 - 100% Out of stock PDP - Registered
		When user provides required details to create new account
		When customer navigates to pdp page of "368612016"
		Then shop related item CTA is displayed
		And carousel is available above product
		And carousel contains all necessary components
		And user should not be able to scroll through different item pictures on pdp

   #NLCC-3821
	@newlook @Anonymous
	Scenario: 78 - Brand category link on PDP
		And feature "feature.brand.cta.on.pdp.description.enabled" is switched on
		When customer search for product productCode_brand and navigates to pdp page
		Then pdp description should contain brand category CTA

  #NLCC-4709
	@newlook @Anonymous @condev_data
	Scenario: 79 - urgency message for pre-orderable out of stock item
		When customer search for product productCode_MultiSize_OutOfStock1 and navigates to pdp page
		Then The size picker contains the email me message against each size
			| XS  |
			| S   |
			| M   |
			| L   |
			| XL  |
			| XXL |

	#NLCC-4709
	@newlook @Anonymous @condev_data
	Scenario: 80 - urgency message for pre-orderable multicolor out of stock item
		When customer search for product productCode_MultiSize_multicolor_OutOfStock and navigates to pdp page
		Then The size picker contains the email me message against each size
			| UK 2 |
			| UK 3 |
			| UK 6 |

	#NLCC-7570 is testing NLCC-5733, NLCC-7312
	@newlook @Anonymous @condev_data
	Scenario: 81 - Limit on total number of items in cart
		And feature "feature.limit.cart.entries.size.enabled" is switched on
		And customer search for product productCode_stock with size 12L32
		And customer search for product productCode_OneSize with size one size
		And customer search for product productCode_savedItems with size one size
		And navigated to my bag page
		And I change the quantity to "10" of a line item 1
		And I change the quantity to "10" of a line item 2
		Then cart limit error message is not displayed
		And I change the quantity to "2" of a line item 3
		Then cart limit error message is displayed
		And quantity for a line item 3 is 1
		And customer search for product productCode_stock with size 12L32
		Then cart limit error message is displayed
		And customer move item from MY SAVED ITEMS to my bag
		Then cart limit error message is displayed

   #NLCC-7155 is testing NLCC-7067
	@newlook @Anonymous @condev_data
	Scenario:82 As a Customer, when I search multicolor product where one color is out of stock then I should see products carousel and category CTA
		And customer navigates to pdp page of "362042020"
		Then shop related item CTA is displayed
		And carousel is available above product
		And user should not be able to scroll through different item pictures on pdp

   #NLCC-7155 is testing NLCC-7067
	@newlook @UserDependent @condev_data
	Scenario:83 As a Customer, when I search multicolor product where one color is out of stock then I should see products carousel and category CTA
		And login with username "myBagUserEmail" and password "password"
		And customer navigates to pdp page of "362042020"
		Then shop related item CTA is displayed
		And carousel is available above product
		And user should not be able to scroll through different item pictures on pdp

	@newlook @Anonymous @oms_flow
	Scenario:84 add to bag - ATC call made - InStock in MAO
		Given I set 3171910 as lowstock in hybris with minus 70 minutes
		When customer search for product MAO_InStock_MultiSize_DRESS with text UK 12 in size dropdown
		And select size of the product "UK 12"
		And I click Add to Bag
		And click on the bag icon
		Then selected size UK 12 is displayed
		And I change the quantity to "Qty: 7" of a line item 1

	@newlook @Anonymous @oms_flow @OMSF-342
	Scenario:85 add to bag - ATC call made - Out Of Stock in MAO
		And I set 3650176 as lowstock with minus 10 minutes with Store 2 and Dc 2 quantity
		And i open hac page
		And i provide valid username and password and click
		And i have cleared the Cache from Hac
		And i open newLook page
		And I search for the product MAO_OutOfStock_SingleSize and navigate to pdp page
		When I click Add to Bag
		Then out of stock message is displayed on pdp page

	@newlook @Anonymous @oms_flow @OMSF-290
	Scenario:86 PDP - Standard Delivery message - AC1 –  single colour single size product
		When I search for the product MAO_SingleSize_sfs and navigate to pdp page
		Then standard delivery message is displayed on pdp page

	@newlook @Anonymous @oms_flow @OMSF-290
	Scenario:87 PDP - Standard Delivery message - AC2,AC3,AC4 –  multi size product
		When I search for the product MAO_MultiSize_sfs and navigate to pdp page
		And select size of the product "UK 10"
		Then standard delivery message is displayed on pdp page
		Then standard delivery message is displayed on pdp page for products
			| MAO_MultiSize_sfs |
		And stock location is store in the datalayer sizeData object
			| productCode       | sizeCode |
			| MAO_MultiSize_sfs | 10       |

	@newlook @Anonymous @oms_data
	Scenario: 88 - Stock equal to or less than 5 units (Single Size Item) OMS
		And feature "feature.storefront.stock.urgency.messaging.pdp" is switched on
		When customer search for product productCode_OneSize_LowStock_oms and navigates to pdp page
		Then message "Only 3 items left!" will display next to the one size string

	@newlook @Anonymous @oms_data
	Scenario:89 I should be see previous and current price on the PDP page OMS
		When search previous price product for oms
		And click on a product image or title
		Then the previous price and the current price should be shown

	@newlook @Anonymous @oms_data
	Scenario: 90 Model wears new product carousel - Navigate to out of stock url and the carousel items are still available OMS
		And feature "feature.storefront.productcarousel.redesign.enabled" is switched on
		When user navigates to "OutOfStockProductUrl_oms"
		Then you should see a out of stock message , the size drop down is not present
		When select a specific product 'Blue Tropical Flowery Print Pencil Skirt' from the product carousel section
		Then the carousel product should not be displayed on side panel
		And I should be navigated to the full PDP page and the product name is "Blue Tropical Flowery Print Pencil Skirt"

	@newlook @Anonymous @oms_data
	Scenario: 91 cart page has product color and size selected in the pdp OMS
		When I search for a product
		And click on a product image or title
		And select size of the product "12L32"
		And I click Add to Bag
		And click on the bag icon
		And selected size 12L32 is displayed

	@newlook @Anonymous @oms_data
	Scenario: 92 - Stock equal to or less than 5 units (Multi Size Item) OMS
		And I set 3596569 as lowstock with minus 1 minutes with Store 2 and Dc 0 quantity
		And I set 3596570 as lowstock with minus 1 minutes with Store 2 and Dc 1 quantity
		And I set 3596571 as lowstock with minus 1 minutes with Store 1 and Dc 0 quantity
		And I set 3596572 as lowstock with minus 1 minutes with Store 3 and Dc 2 quantity
		And I set 3596573 as lowstock with minus 1 minutes with Store 2 and Dc 2 quantity
		And i open hac page
		And i provide valid username and password and click
		And i have cleared the Cache from Hac
		And i open newLook page
		And feature "feature.storefront.stock.urgency.messaging.pdp" is switched on
		When customer search for product productCode_MultiSize_LowStock and navigates to pdp page
		When I open the drop down size picker
		And I select low stock size "UK 8"
		Then message "Only 2 items left!" will display next to the size "UK 8" value
		And I select low stock size "UK 10"
		Then message "Only 3 items left!" will display next to the size "UK 10" value
		And I select low stock size "UK 12"
		Then message "Only 1 item left!" will display next to the size "UK 12" value
		And I select low stock size "UK 14"
		Then message "Only 5 items left!" will display next to the size "UK 14" value
		And I select low stock size "UK 16"
		Then message "Only 4 items left!" will display next to the size "UK 16" value

	@newlook @Anonymous @oms_data
	Scenario: 93 - urgency message for pre-orderable out of stock item OMS
		And I set 3488687 as outofstock with minus 1 minutes with Store 0 and Dc 0 quantity
		And I set 3488688 as outofstock with minus 1 minutes with Store 0 and Dc 0 quantity
		And I set 3488689 as outofstock with minus 1 minutes with Store 0 and Dc 0 quantity
		And I set 3488690 as outofstock with minus 1 minutes with Store 0 and Dc 0 quantity
		And I set 3488691 as outofstock with minus 1 minutes with Store 0 and Dc 0 quantity
		And i open hac page
		And i provide valid username and password and click
		And i have cleared the Cache from Hac
		And i open newLook page
		When customer search for product productCode_MultiSize_OutOfStock1 and navigates to pdp page
		Then The size picker contains the email me message against each size
			| XS  |
			| S   |
			| M   |
			| L   |
			| XL  |
			| XXL |

	@newlook @Anonymous @oms_data
	Scenario: 94 - urgency message for pre-orderable multicolor out of stock item OMS
		And I set 3550351 as outofstock with minus 1 minutes with Store 0 and Dc 0 quantity
		And I set 3550352 as outofstock with minus 1 minutes with Store 0 and Dc 0 quantity
		And I set 3550355 as outofstock with minus 1 minutes with Store 0 and Dc 0 quantity
		And I set 3550356 as instock with minus 1 minutes with Store 10 and Dc 10 quantity
		And I set 3550357 as instock with minus 1 minutes with Store 10 and Dc 10 quantity
		And i open hac page
		And i provide valid username and password and click
		And i have cleared the Cache from Hac
		And i open newLook page
		When customer search for product productCode_MultiSize_multicolor_OutOfStock and navigates to pdp page
		Then The size picker contains the email me message against each size
			| UK 2 |
			| UK 3 |
			| UK 6 |

	@newlook @Anonymous @NLRD-2456 @oms_data
	Scenario: 95 Check if the default product size has selected on the side panel OMS
		When search multi colour product
		And click on a product image or title
		And select size of the product "XS"
		And click on the store lookup button
		Then the selected size "XS" should be pre-populated on the page

	@newlook @UserDependent @oms_data
	Scenario:96 As a Customer, when I search multicolor product where one color is out of stock then I should see products carousel and category CTA OMS
		And login with username "myBagUserEmail" and password "password"
		And customer navigates to pdp page of "341543649"
		Then shop related item CTA is displayed
		And carousel is available above product
		And user should not be able to scroll through different item pictures on pdp


	#NLCC-5607
	#NLCC-7157 is testing NLCC-6980
	@newlook @Anonymous @oms_data
	Scenario:99 - 100% Out of stock PDP OMS - Guest
		When customer navigates to pdp page of "341543649"
		Then shop related item CTA is displayed
		And carousel is available above product
		And carousel contains all necessary components
		And user should not be able to scroll through different item pictures on pdp

  #NLCC-5607
	#NLCC-7157 is testing NLCC-6980
	@newlook @NewUsers @oms_data
	Scenario: 100 - 100% Out of stock PDP OMS - Registered
		When user provides required details to create new account
		When customer navigates to pdp page of "341543649"
		Then shop related item CTA is displayed
		And carousel is available above product
		And carousel contains all necessary components
		And user should not be able to scroll through different item pictures on pdp

	#NLCC-7570 is testing NLCC-5733, NLCC-7312
	@newlook @Anonymous @oms_data
	Scenario: 101 - Limit on total number of items in cart OMS
		And feature "feature.limit.cart.entries.size.enabled" is switched on
		And customer search for product productCode_stock with size 18L30
		And customer search for product productCode_OneSize with size one size
		And customer search for product productCode_savedItems with size one size
		And navigated to my bag page
		And I change the quantity to "10" of a line item 1
		And I change the quantity to "10" of a line item 2
		Then cart limit error message is not displayed
		And I change the quantity to "2" of a line item 3
		Then cart limit error message is displayed
		And quantity for a line item 3 is 1
		And customer search for product productCode_stock with size 18L30
		Then cart limit error message is displayed
		And customer move item from MY SAVED ITEMS to my bag
		Then cart limit error message is displayed
