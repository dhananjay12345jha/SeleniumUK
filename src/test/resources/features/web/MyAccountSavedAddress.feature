@newlook @NewUsers
Feature: My account Saved addresses

	Background:
		Given i navigate to "Newlook" home page
		When user provides required details to create new account
		And User navigate to address book page
		And find and add new address using post code E6 2HE

	Scenario:1 I First address should be default
		And account holder in checkout page
		Then checkout default delivery address should be with post code E6 2HE

	Scenario:2 second saved address should not impact current default
		And find and add new address using post code E12 6JH
		And account holder in checkout page
		Then checkout default delivery address should be with post code E6 2HE

	Scenario: 3 change default with existing bag
		And find and add new address using post code E12 6JH
		And account holder in checkout page
		And checkout default delivery address should be with post code E6 2HE
		And clear bag item from checkout page
		And User navigate to address book page
		And set address with post code E12 6JH as default
		And user relogin his account
		And account holder in checkout page
		Then checkout default delivery address should be with post code E12 6JH

	Scenario: 4 change default start a new bag
		And find and add new address using post code E12 6JH
		And account holder in checkout page
		And checkout default delivery address should be with post code E6 2HE
		And clear bag item from checkout page

		And User navigate to address book page
		And set address with post code E12 6JH as default
		And user relogin his account
		And account holder in checkout page
		Then checkout default delivery address should be with post code E12 6JH

	#NLCC-6601 AC3
	Scenario:5 Default address should be pre-populated in delivery page
		And feature "feature.deliveryUpdate.forms.enabled" is switched on
		And account holder in checkout page
		Then checkout default delivery address should be with post code E6 2HE
		And default Delivery Address is displayed along with mandatory mobile number







