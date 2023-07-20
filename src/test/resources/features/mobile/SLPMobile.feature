@AnonymousMobile
Feature: SLP feature - mobile

	Background: I go to New Looks home page
		Given i navigate to "Newlook" home page

	@newlook @nlrd-2555 @Ignore
	Scenario:1a Clicking on the Price and move the price slider from both sides. Validate if the filter criteria updates as per the price slider values SLP - mobile
		And searches for product on mobile searchProduct_Jeans
		And select the sorting criteria as "PRICE"
		Then price slider can be moved from left and right

	@newlook @nlrd-2555 @Ignore
	Scenario:1b Product results changes after moving the price slider from both sides. SLP - mobile
		And searches for product on mobile searchProduct_Jeans
		Then total displayed products changes after moving the price slider from both sides

	@newlook
	@NLCC-4600
	Scenario: 1 sort and filter at a time SLP - mobile
		When product search with string top should show min 2 matched results
		When user select sort option 'Price Low - High'
		Then the products should be displayed in price ascending order
		And select option from filter
			| 1 |
		And Filter count is 1
		When user select sort option 'Price High - Low'
		Then the products should be displayed in price descending order
		When user select sort option 'Bestsellers'
		Then the value menu is closed automatically
		And select option from filter
			| 2 |
		And Filter count is 2
		Then plp page should show correct filtered attributes

		When I remove a filter
		Then Filter count is 1

	@newlook
	Scenario:2 plp product dept filters - mobile
		When product search with string shirt should show min 10 matched results
		Then plp page should show below departments
			| Teens        |
			| All Products |

	@newlook
	Scenario Outline: 3 sorting SLP - mobile
		When product search with string top should show min 2 matched results
		When user select sort option '<sort>'
		Examples:
			| sort             |
			| Price High - Low |
			| Price Low - High |
			| Bestsellers      |


	@newlook
	Scenario:4 select multiple sizes at time SLP - mobile
		When product search with string top should show min 2 matched results
		And select option from filter
			| 1 |
			| 2 |
		Then selected facet filters should match with filters displayed


	@newlook
	Scenario:5 SLP items details - mobile
		When product search with string belt should show min 2 matched results
		Then all the plp items should have correct product details

#todo
#  @newlook
#  Scenario:6 only one facet displayed at a time SLP
#    And I search for the product with the specified criteria "Jeans"
#    When I select a single-select facet sortby
#    Then I am unable to expose another facet at the same time
#    And if I select another facet to expose
#    Then any already-exposed facets will close

	@newlook
	Scenario:7 Applied facet values can be cleared by using clear link and the set returns to the unrefined state displaying all the products SLP - mobile
		And searches for product on mobile searchProduct_Jeans
		Then I am able to filter and clear filter on mobile

	@newlook
	Scenario:8 The products should narrow down when I unselect any on the value form multi-select facet value SLP - mobile
		And searches for product on mobile searchProduct_Jeans
		When I select a single-select facet colour
		And have selected multiple values in that facet
		When I re-click the value
		Then there are other values selected
		And the product set narrows to products that match the remaining selected values

	@newlook
	Scenario:11 Load next button should load next available product images SLP - mobile
		When I click on the locale selector in the header
		And I select a different country country_USA using the drop down
		When Navigate to Womens department from mega menu
		And click on load next button
		Then next products will be displayed

	@newlook
	Scenario:12 As a customer when I select Sort By facet then I should see list of available options SLP - mobile
		And searches for product on mobile searchProduct_Jeans
		When I select a single-select facet sortby
		Then I am presented with a list of options on which to sort the product set in view

	@newlook
	Scenario:13 As a Customer, when the select department facet form plp page then the bin value of the facet should reflect the total results SLP - mobile
		When product search with string jumper should show min 10 matched results
		Then plp page should show below departments
			| Teens        |
			| All Products |
		Then I am presented with the product listing by default
		And each facet option has a bin count
		And I can select a facet to refine the product listing to exclude the other departments

#     depends on no of products- data set up configuration required to enable view all option
#  This test does not work on mobile as there is no View all button
#  @newlook
#  Scenario:14 Load all button should load all available product images for that category SLP - mobile
#    When I click on the locale selector in the header
#    And I select a different country "United States" using the drop down
#    And searches for product on mobile Shoes
#    And click on load all button
#    Then all products will be displayed

	@newlook @NLDS-505
	Scenario:18  PLP pagination beyond 1 pages SLP - mobile
		And searches for product on mobile searchProduct_Shoes
		And click on load next button
		Then next products will be displayed
		And url contains "page=1"

	Scenario: 19 - Click on back to top button on SLP - mobile
		And I search for the product with "dress"
		And click on load next button
		And I click on back to top button
		Then I am taken to the top of PLP page

	Scenario: 20 - Articles and info has been removed from SLP - mobile
		And I search for the product with "dress"
		Then articles and info has been removed from search filter

	Scenario:21 View product from PLP - mobile
		When Navigate to Mens department from mega menu
		Then all the plp items should have correct product details
		And select a random product from PLP
		And I should be navigated to the full PDP page

	Scenario:22 View product from SLP - mobile
		And searches for product on mobile searchProduct_Jeans
		Then all the plp items should have correct product details
