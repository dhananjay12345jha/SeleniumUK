# Created by gokhan.ates

@newlook @UserDependent
Feature: Right to be forgotten

  Background:
    Given i navigate to "Newlook" home page

  Scenario:1 access My Data Privacy Rights section is present
    When login with username "myAccountPersonalDetailsUserEmail" and password "password"
    And Navigate to "Personal_Details" pages
    Then My Data Privacy Rights section is present

  Scenario:2 Fill in RTBF form Person Details as an existing user
    When login with username "myAccountPersonalDetailsUserEmail" and password "password"
    And Navigate to "Personal_Details" pages
    And From the My Data Privacy Rights Section I click on Find Out More button
    And I navigate to RTBF page and fill in form
    Then Check your Inbox section is present
