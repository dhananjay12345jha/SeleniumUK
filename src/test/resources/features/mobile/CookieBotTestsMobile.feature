@UserDependentMobile
Feature: Cookie Bot - Mobile

	#NLCC-6848 (Automation for NLCC-5855)
	Background:
		Given i navigate to "Newlook" home page and not interact with cookies
		And feature "feature.storefront.cookieBot.enabled" is switched on

	Scenario:1 Customer see cookie bot on home page
		Then I can see cookie bot banner

	Scenario:2 Customer see cookie bot on another page
		And Navigate to mega menu T1 from Womens department
		Then I can see cookie bot banner

	Scenario:3 Customer accepts cookie and cookie banner disappears
		And I accept cookies
		Then cookie bot banner is not displayed

	Scenario:4 Customer opens cookie settings and cookie settings appears
		And I click cookies settings
		Then cookie bot settings banner is displayed

	Scenario:5 Customer opens cookie settings and cookie settings appears
		And I click cookies settings
		And I close cookie settings banner
		Then cookie bot settings banner is not displayed

	Scenario:6 Returning Customer doesn't see cookie banner if they opt out of unnecessary cookies
		And login with username "myAccountPersonalDetailsUserEmail" and password "password"
		And I accept cookies
		And customer is logged out
		And login with username "myAccountPersonalDetailsUserEmail" and password "password"
		Then cookie bot banner is not displayed

	Scenario:7 Returning Customer doesn't see cookie banner if they opt in all unnecessary cookies
		And login with username "myAccountPersonalDetailsUserEmail" and password "password"
		And I opt in for all cookies and accept
		And customer is logged out
		And login with username "myAccountPersonalDetailsUserEmail" and password "password"
		Then cookie bot banner is not displayed

	Scenario:8 Returning Customer doesn't see cookie banner if they partially opt out of unnecessary cookies
		And login with username "myAccountPersonalDetailsUserEmail" and password "password"
		And I opt in for partial cookies and accept
		And customer is logged out
		And login with username "myAccountPersonalDetailsUserEmail" and password "password"
		Then cookie bot banner is not displayed
