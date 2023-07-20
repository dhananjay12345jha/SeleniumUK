@newlook @Smartedit
Feature: SmartEdit page

	Background:
		Given i navigate to "/smartedit" app page

	Scenario:1 Login to smartedit
		When login with username "admin" and password "nimda" on smartedit
		Then I have successfully logged in smartedit
