@AnonymousMobile2
Feature: alert pop up in input fields

  Background:
    Given i navigate to "Newlook" home page

  Scenario:1 search - mobile
    When searches for product on mobile searchProductNoAlert
    Then alert should not appear

  Scenario:2 sign in - mobile
    And login with username "<script>alert('test');</script>" and password "<script>alert('test');</script>" non system property on mobile
    Then alert should not appear

  Scenario:3 registration - mobile
    When user provides required details to create new account on mobile
    Then alert should not appear

  @OMSF_Bug @OMSF-1023
  #OMSF-1023 bug raised to land on contact preference page after creation of new account on OMS environment
  Scenario:4 save address - mobile
    When user provides required details to create new account on mobile
    And new account should be create and user should navigate to my account page
    And User navigate to address book page on mobile
    And find and add new address with pop up script in input field
    Then alert should not appear

  Scenario:5 save payment card - mobile
	 And feature "feature.pci.microform.enabled.uk-site" is switched off
    When user provides required details to create new account on mobile
    And Select a product and navigate to payment page on mobile
    And search for nearest collection location to: "London"
    And I continue to payment
    And enter mandatory mobile number
    And save payment card details with no <script>alert('test');</script>
    Then alert should not appear

	@anonymousMobile2Samsung
	Scenario:6 save payment card - mobile -PCI on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		When user provides required details to create new account on mobile
		And Select a product and navigate to payment page on mobile
		And search for nearest collection location to: "London"
		And I continue to payment
		And enter mandatory mobile number
		And save payment card details with no <script>alert('test');</script>
		Then alert should not appear

  Scenario:7 Add item to basket and checkout as logged in user for collection
    When user provides required details to create new account on mobile
    And Select a product and navigate to payment page on mobile
    And search for nearest collection location as popup alert script
    Then alert should not appear

  Scenario:8 find store
    And I click on the locale selector in the header
    And I select a different country country_France using the drop down
    And I can click the link of store locator on mobile
    And I enter a search term "<script>alert('test');</script>"
    And I click on Find Stores CTA
    Then alert should not appear
