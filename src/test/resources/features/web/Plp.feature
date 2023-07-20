@Anonymous
Feature: Plp feature

	Background: I go to New Looks home page
		Given i navigate to "Newlook" home page


	@newlook @nlrd-2555
	Scenario:1a Clicking on the Price and move the price slider from both sides. Validate if the filter criteria updates as per the price slider values
		When Navigate to Mens department from mega menu
		And select the sorting criteria as "PRICE"
		Then price slider can be moved from left and right

	@newlook @nlrd-2555
	Scenario:1b Product results changes after moving the price slider from both sides.
		When Navigate to Mens department from mega menu
		And select the sorting criteria as "PRICE"
		Then total displayed products changes after moving the price slider from both sides

	@newlook  @smokepack
	Scenario:1c sort and filter at a time
		When Navigate to Mens department from mega menu
		When user select sort option 'Price Low - High'
		Then the products should be displayed in price ascending order
		And select option from filter
			| 1 |
		When user select sort option 'Price High - Low'
		Then the products should be displayed in price descending order
		When user select sort option 'Bestsellers'
		Then the value menu is closed automatically
		And select option from filter
			| 2 |
		Then plp page should show correct filtered attributes

#    this test has been covered in SLP
#  @newlook
#  Scenario:2 plp product dept filters
#    When product search with string shirt should show min 10 matched results
#    Then plp page should show below departments
#      | TEENS        |
#      | ALL PRODUCTS |

	@newlook
	Scenario Outline: 3 sorting
		When Navigate to Mens department from mega menu
		When user select sort option '<sort>'
		Examples:
			| sort             |
			| Price High - Low |
			| Price Low - High |
			| Bestsellers      |

#    covered in SLP
#  @newlook
#  Scenario:4 select multiple sizes at time
#    When Navigate to Womens department from mega menu
#    And Select filter all products from search filter list
#    And select option from filter
#      | 1 |
#      | 2 |
#    Then selected facet filters should match with filters displayed

	@newlook
	Scenario:5 plp items details
		When Navigate to Mens department from mega menu
		Then all the plp items should have correct product details


	@newlook
	Scenario:6 only one facet displayed at a time
		When Navigate to Mens department from mega menu
		When I select a single-select facet sortby
		Then I am unable to expose another facet at the same time
		And if I select another facet to expose
		Then any already-exposed facets will close

	@newlook
	Scenario:7 Applied facet values can be cleared by using clear link and the set returns to the unrefined state displaying all the products
		When Navigate to Mens department from mega menu
		When I select a single-select facet Size
		And select option from filter
			| 1 |
    #And select a size from the displayed facet values
		When I review the facet bar/element and the facets I have currently applied to the product set
		Then I am able to remove all applied facets from the product set using clear link displayed alongside these
		And the product set returns to an un-refined state and all products in the listing are displayed

	@newlook
	Scenario:8 The products should narrow down when I unselect any on the value form multi-select facet value
		When Navigate to Mens department from mega menu
		When I select a single-select facet colour
		And have selected multiple values in that facet
		When I re-click the value
		Then there are other values selected
		And the product set narrows to products that match the remaining selected values

	@newlook
	Scenario:10 For ROW when I visit Teens section the default selection should be model off as per set data in the back end hybris
		When I click on the locale selector in the header
		And I select a different country country_USA using the drop down
		When Navigate to Teens department from mega menu
		Then the category has been set to display Model off images by default

    #required configuration

	@newlook
	Scenario:11 Load next button should load next available product images
		When I click on the locale selector in the header
		And I select a different country country_USA using the drop down
		When Navigate to Womens department from mega menu
		And click on load next button
		Then next products will be displayed

	@newlook
	Scenario:12 As a customer when I select Sort By facet then I should see list of available options
		When Navigate to Mens department from mega menu
		When I select a single-select facet sortby
		Then I am presented with a list of options on which to sort the product set in view

#    this has been moved to SLP
#  @newlook
#  Scenario:13 As a Customer, when the select department facet form plp page then the bin value of the facet should reflect the total results
#    When product search with string jumper should show min 10 matched results
#    And Select filter all products from search filter list
#    Then plp page should show below departments
#      | TEENS        |
#      | ALL PRODUCTS |
#    Then I am presented with the product listing by default
#    And each facet option has a bin count
#    And I can select a facet to refine the product listing to exclude the other departments

     #depends on no of products- data set up configuration required to enable view all option
	@newlook
	Scenario:14 Load all button should load all available product images for that category
		When I click on the locale selector in the header
		And I select a different country country_USA using the drop down
		When Navigate to Mens department from mega menu
		And click on load all button
		Then all products will be displayed


	@newlook
	Scenario Outline:15 As a customer I can navigate to a category where model on is the default and customer can toggle between on and off
		When I click on the locale selector in the header
		And I select a different country country_USA using the drop down
		When Navigate to <department> department from mega menu
		Then the model <button> button is enabled for the category by default
		Examples:
			| department | button |
			| Mens       | on     |
			| Teens      | off    |

	@newlook
	Scenario: 16 Sale icon appears in PLP
		When user navigates to "ProductOnSaleUrl"
		Then sale icon appears in plp grid
		And there are more than 8 sale icons

	@newlook @condev_data
	Scenario: 17 Similiar items button is displayed
		When user navigates to "OutOfStockProductUrl"
		And click save item button
		And viewing the saved items side pane
		Then similiar items button is displayed

	@newlook @NLDS-505
	Scenario:18  PLP pagination beyond 3 pages
		When Navigate to Womens department from mega menu
		And I click on the first ancestor breadcrumb
		And click on load next button
		Then next products will be displayed
		And url contains "page=1"
		And click on load next button
		Then next products will be displayed
		And url contains "page=2"
		And click on load next button
		Then next products will be displayed
		And url contains "page=3"
		And click on load next button
		Then next products will be displayed
		And url contains "page=4"
		And click on load next button
		Then next products will be displayed
		And url contains "page=5"

	Scenario: 19 - Click on back to top button on PLP
		When Navigate to Womens department from mega menu
		And click on load next button
		And I click on back to top button
		Then I am taken to the top of PLP page

#    this test is in SLP
#  Scenario: 20 - Articles and info has been removed from PLP
#    When Navigate to Womens department from mega menu
#    Then articles and info has been removed from search filter

	Scenario:21 View product from PLP
		When Navigate to Mens department from mega menu
		Then all the plp items should have correct product details

	Scenario:22 View product from PLP
		When Navigate to Mens department from mega menu
		Then all the plp items should have correct product details
		And select a random product from PLP
		And I should be navigated to the full PDP page

 #NLCC-2893 direct link to homepage from department homepage
	Scenario: 23 Newlook logo header from department homepage
		When navigate to Mens department home page from mega menu
		And user clicks on the New Look logo
		Then I'm redirected to relevant New Look site

#NLCC-2893 direct link to homepage from category page
	Scenario: 24 Newlook logo header from category page
		When Navigate to Mens department from mega menu
		And user clicks on the New Look logo
		Then I'm redirected to relevant New Look site

#NLCC-2893 direct link to homepage from product description page
	Scenario:25 Newlook logo header from product description page
		When Navigate to Mens department from mega menu
		And select a random product from PLP
		And user clicks on the New Look logo
		Then I'm redirected to relevant New Look site

	#NLCC-2893 A returning visitor with a department set of 'neutral' is redirected to regional homepage
	Scenario: 26 Returning customer from neutral department
		When navigate to Sale department home page from mega menu
		And user access newlook through home page url
		Then I'm redirected to relevant New Look site

#NLCC-2893 A returning visitor with a department set of 'non neutral' redirected to department homepage
	Scenario: 27 Returning customer from non neutral department
		When navigate to Mens department home page from mega menu
		And user access newlook through home page url
		Then I'm redirected to Mens department homepage

#NLCC-2893 A returning visitor with a department set of 'non neutral' redirected to department homepage
	Scenario: 28 Returning customer from category page
		When Navigate to Mens department from mega menu
		And user access newlook through home page url
		Then I'm redirected to Mens department homepage

#NLCC-2893 A returning visitor with a department set of 'non neutral' redirected to department homepage
	Scenario: 29 Returning customer from pdp
		When Navigate to Mens department from mega menu
		And select a random product from PLP
		And user access newlook through home page url
		Then I'm redirected to Mens department homepage

	@newlook @oms_data
	Scenario: 30 Similiar items button is displayed OMS
		When user navigates to "OutOfStockProductUrl_singleSize_oms"
		And click save item button OMS
		And viewing the saved items side pane
		Then similiar items button is displayed
