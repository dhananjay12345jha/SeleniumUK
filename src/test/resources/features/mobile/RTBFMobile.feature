# Created by gokhan.ates

@newlook @userDependentMobileSamsung
Feature: Right to be forgotten - mobile

  Background:
    Given i navigate to "Newlook" home page

  Scenario:1 access My Data Privacy Rights section is present - mobile
    When login with username "myAccountPersonalDetailsUserEmail" and password "password" on mobile
    And Navigate to "Personal_Details" pages
    Then My Data Privacy Rights section is present

  Scenario:2 Fill in RTBF form Person Details as an existing user - mobile
    When login with username "myAccountPersonalDetailsUserEmail" and password "password" on mobile
    And Navigate to "Personal_Details" pages
    And From the My Data Privacy Rights Section I click on Find Out More button
    And I navigate to RTBF page and fill in form
    Then Check your Inbox section is present
