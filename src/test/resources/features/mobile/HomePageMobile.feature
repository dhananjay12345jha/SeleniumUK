@AnonymousMobile2
Feature: home page mobile

	Background:
		Given i navigate to "Newlook" home page

	Scenario:1 mega menu all links
		Then check all mega menu headings

	Scenario Outline:4 footer links - mobile
		And click  "<footer link>" on mobile
		Then should navigate to mobile page "<page>"
		Examples:
			| footer link      | page                                |
			| FAQ              | Frequently Asked Questions FAQ Page |
			| Track Your Order | Track My Order Page                 |
			| Staff Discount   | Staff Discount                      |

	Scenario Outline: 5 home page search - mobile
		When searches for product on mobile <searchTerm>
		Then it should show min 10 matched results
		Examples:
			| searchTerm          |
			| searchProduct_Jeans |
			| searchProduct_Shirt |
			| searchProduct_Tops  |

	Scenario: 6 Gender button does not exist in the newsletter signup - mobile
		Then gender button does not exist "I’M MALE"
		And news letter signup button equals "SIGN ME UP"

	Scenario: 7 Attempt to sign up to newsletter without email address - mobile
		And I click on Sign Me Up button on mobile
		Then this is a required field error box appears

	Scenario: 9 Ikano card image is displayed in the footer - mobile
		Then IKANO card image is displayed in the footer

	Scenario: 10 Select delivery from the footer -mobile
		When click  "Delivery" on mobile
		Then I am taken to the delivery redirect page

	Scenario: 11 Validate that #! href link target has been removed from 'Got It' CTA button on cookie banner - mobile
		Then Validate Got It button

	Scenario: 12 Validate that cookie banner is not displayed to a known user on landing page if Got It CTA button was selected in previous visit - mobile
		And Click on Got It button
		And Refresh homepage
		Then Got It button is not present

	Scenario: 13 Validate that cookie banner redisplayed to a user has no #! href link target on 'Got It' CTA button if browser cache from previous sessions are cleared - mobile
		And Click on Got It button
		And Clear cookies
		And Refresh homepage
		Then Validate Got It button

	Scenario: 14 Validate that comp locationContext parentContext and dataTrackerCode are not visible as query parameters - mobile
		And feature "feature.storefront.page.tracking.cookie.enabled" is switched on
		And Navigate to Womens department from mega menu
		Then url does not contain "comp="
		Then url does not contain "LocationContext"
		Then url does not contain "ParentContext"
		Then url does not contain "DataTrackerCode"

	Scenario: 15 Validate that comp locationContext parentContext and dataTrackerCode are visible as query parameters if Feature Flag is off - mobile
		And feature "feature.storefront.page.tracking.cookie.enabled" is switched off
		And Navigate to Womens department from mega menu
		Then url contains "comp="
		Then url contains "LocationContext"
		Then url contains "ParentContext"
		Then url contains "DataTrackerCode"

#	The scenario is no more valid reference #NLCC-2898
#  Scenario: 16 Validate that comp locationContext parentContext and dataTrackerCode are visible as query parameters if Feature Flag is On and Ensighten On - mobile
#    And feature "feature.storefront.page.tracking.cookie.enabled" is switched on
#    And feature "feature.storefront.ensighten.bootstrap.disable" is switched on
#    And Navigate to Womens department from mega menu
#    Then url contains "comp="
#    Then url contains "LocationContext"
#    Then url contains "ParentContext"
#    Then url contains "DataTrackerCode"

	@Ignore
#NLCC-3006 update FE Meganav Rendering
	Scenario: 16 non Server Side Rendered Tier 1 MegaNav without cache
		And feature "feature.storefront.masthead.v3.enabled" is switched on
		And user hovers on Mens department from mega menu
		Then I see network call to load json data

	@Ignore
#NLCC-3006 update FE Meganav Rendering
	Scenario: 17 non Server Side Rendered Tier 1 MegaNav with cache
		And feature "feature.storefront.masthead.v3.enabled" is switched on
		And user hovers on Mens department from mega menu
		Then I see network call to load json data
		When user closes mobile main navigation
		And user hovers on Mens department from mega menu
		Then I see json data loaded from cache

#NLCC-3006 update FE Meganav Rendering
	Scenario: 18 non Server Side Rendered Tier 1 MegaNav
		And feature "feature.storefront.masthead.v3.enabled" is switched on
		And user hovers on Womens department from mega menu
		Then I will not see network call to load json data

#NLCC-4147 Brexit messaging delivery country selector
	Scenario: 19 Brexit message appears on Delivery Country Selector on footer
		And feature "feature.storefront.brexitBanner.enabled" is switched on
		And click on change delivery country link on footer
		Then I should see brexit message above change setting cta

#NLCC-4147 Brexit messaging delivery country selector
	Scenario: 20 ROW Brexit message appears on Delivery Country Selector on footer
		And feature "feature.storefront.brexitBanner.enabled" is switched on
		When click on change delivery country link on footer
		And I select a different country country_USA using the drop down
		And click on change delivery country link on footer
		Then I should see brexit message above change setting cta

#NLCC-4147 Brexit messaging delivery country selector
	Scenario Outline: 21 France Brexit message appears on Delivery Country Selector on footer
		And feature "feature.storefront.brexitBanner.enabled" is switched on
		When click on change delivery country link on footer
		And I select a different country <country> using the drop down
		And click on change delivery country link on footer
		Then I should see brexit message above change setting cta
		And I should see brexit message for country <message1>
		When I change country <country1> to <country2>
		And click on change delivery country link on footer
		Then I should see brexit message for country <message2>

		Examples:
			| country         | message1        | country1       | country2  | message2   |
			| country_France  | message_France  | countryFrance  | countryUK | message_UK |
			| country_Germany | message_Germany | countryGermany | countryUK | message_UK |

	#NLCC-3726 Tracker code is populated in MegaNav v3
	Scenario Outline: 22 SSR Tracker code is populated correctly on T1 and T3 menu items - mobile
		And feature "feature.storefront.masthead.v3.enabled" is switched on
		And Navigate to mega menu <tag> from Womens department and get Data tracker code
		Then Data Tracker code is same as that of productFindingMethod value
		And Navigate to mega menu <tag> from Mens department and get Data tracker code
		Then Data Tracker code is same as that of productFindingMethod value
		Examples:
			| tag |
			| T1  |
			| T3  |

	 #NLCC-3726 Tracker code is populated in MegaNav v3
	Scenario Outline: 23 Non-SSR Tracker code is populated correctly on T1 and T3 menu items- mobile
		And feature "feature.storefront.masthead.v3.enabled" is switched on
		And Navigate to mega menu <tag> from Mens department and get Data tracker code
		And Navigate to mega menu <tag> from Womens department
		Then Data Tracker code is different as that of productFindingMethod value
		And Navigate to mega menu <tag> from Womens department and get Data tracker code
		And Navigate to mega menu <tag> from Mens department
		Then Data Tracker code is different as that of productFindingMethod value
		Examples:
			| tag |
			| T1  |
			| T3  |

		 #NLCC-7350 is testing NLCC-6831
	@NewUsersMobileSamsung @NewUsersMobile
	Scenario: 24 Get App Link in footer for mobile
		And user provides required details to create new account on mobile
		Then App footer link is displayed
