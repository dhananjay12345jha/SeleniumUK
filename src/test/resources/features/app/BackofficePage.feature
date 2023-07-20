@newlook @Backoffice
Feature: Backoffice page

	Background:
		Given i navigate to "/backoffice" app page

	Scenario:1 Login to backoffice
		When login with username "admin" and password "nimda" on backoffice
		Then I have successfully logged in backoffice
