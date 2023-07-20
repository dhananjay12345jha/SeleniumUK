  @newlook  @Anonymous
Feature: Contact Us From

  Background:
    Given i navigate to "Newlook" home page

  @smokepack
  Scenario:1 Customer lands on redirect page
    When I select contact us form link from the footer
    Then I am taken to the contact us redirect page
