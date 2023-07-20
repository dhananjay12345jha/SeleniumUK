@Anonymous
Feature: StoreLocator feature

	Background: I go to New Looks home page
		Given i navigate to "Newlook" home page

	@smokepack
	Scenario Outline:1a) Country has been preset to selected country on the store locator page
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		When I can click the link of store locator
		And my "<expText>" is preset for me underneath
		And click on change country link
		Then the selected "<expText>" should be prepopulate in the drop down list
		Examples:
			| country        | expText |
			| country_France | France  |

	Scenario Outline:1b) Country has been preset to selected country on the store locator page
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		When I can click the link of store locator
		And my "<expText>" is preset for me underneath
		And click on change country link
		Then the selected "<expText>" should be prepopulate in the drop down list
		Examples:
			| country         | expText     |
			| country_Germany | Deutschland |

	Scenario Outline:2 As a Customer, I want to search for stores by postcode or town, so that I can find the one nearest to that criteria
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		When I can click the link of store locator
		And I enter a search term "<location>"
		And I click on Find Stores CTA
		Then the page reloads and a store results page is displayed
		And start new search by clicking link <link>
		Examples:
			| country         | location | link               |
			| country_Germany | Berlin   | Neue Suche         |
			| country_France  | Paris    | Nouvelle recherche |

	Scenario Outline:3 As a Customer, I want to search for stores by postcode or town, that is configured or located within the radius distance
		When I click on the locale selector in the header
		And I select a different country <country> using the drop down
		When I can click the link of store locator
		And I enter a search term "<location>"
		And I click on Find Stores CTA

		Examples:
			| country         | location |
			| country_Germany | Berlin   |
			| country_France  | Paris    |

	@smoketests
	Scenario:4 The search results should have the key word along with the total results
		When I can click the link of store locator
		And I enter a search term "London"
		And I click on Find Stores CTA
		Then I am presented with a list of results and a line of text that tells me the quantity of results returned that matched my search term

	Scenario:5 The total number of search results should not be more than ten
		When I can click the link of store locator
		And I enter a search term "London"
		And I click on Find Stores CTA

	Scenario:6 search again link should navigate back to the store finder page
		When I can click the link of store locator
		And I enter a search term "London"
		And I click on Find Stores CTA
		When I perform another search by clicking on the 'search again' link
		Then the page reloads to display the Store Finder search page.

	Scenario:7 The more info should display name, address ,phone number,distance form serach location information
		When I can click the link of store locator
		And I enter a search term "London"
		And I click on Find Stores CTA
		Then click on moreinfo link

	Scenario:8 On clicking back to results link from store info page , it should go back to the results page
		When I can click the link of store locator
		And I enter a search term "London"
		And I click on Find Stores CTA
		Then click on moreinfo link
		And from store info page click on back to results link
		Then the page reloads and a store results page is displayed

	Scenario:9 Error message should be displayed if nothing enterd in the store finder and clicked on find store
		When I can click the link of store locator
		And I click on Find Stores CTA
		Then you should see error message displayed

#    TODO - fix on saucelabs
#  Scenario:10 On clicking near by button it should display location based on default selection or geo location
#    When I can click the link of store locator
#    And click on near by button
#    Then the page reloads and a store results page is displayed

	Scenario Outline:11 Default language should be English for UK, French for France, German for Germany and English for Rest of the world
		And I click on the locale selector in the header
		And I select a different country <country> using the drop down
		Then The selected country "<country>" should have the "<Expected_Language>"
		Examples:
			| country         | Expected_Language |
			| country_Germany | Deutsch           |
			| country_France  | Français          |
			| country_UK      | English           |
			| country_Albania | English           |

	Scenario:12 User should see map contents when search for Store locations in store finder page
		And customer navigate to store finder page
		When search for London in store finder page
		Then Map should be displayed with
			| Map          |
			| Satellite    |
			| Terms of Use |

	Scenario:13 User should see pins in map when search for store locations
		And customer navigate to store finder page
		When search for London in store finder page
		Then Store finder page should show map pins

	Scenario Outline:14 User should see pins in map when search for store in store location page
		And customer navigate to store finder page
		When search for <location> in store finder page
		Then The number of pins displayed in store finder page should equal to stores displayed in search result
		Examples:
			| location   |
			| London     |
			| Birmingham |
			| Manchester |

	Scenario Outline:15 Results should match when we click the pin in map
		And customer navigate to store finder page
		When search for <location> in store finder page
		And click pin in the map
		Then The result displayed should match with the map result on store finder page
		Examples:
			| location |
			| London   |
			| SN25 2GE |
