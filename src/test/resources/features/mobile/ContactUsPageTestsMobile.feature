@AnonymousMobile
Feature: Contact Us From

  Background:
    Given i navigate to "Newlook" home page

  Scenario: Customer is redirect to contact us page
    When I select contact us form link from the footer on mobile
    Then I am taken to the contact us redirect page