@Anonymous
@NLCC-5256
# See more information here: https://wiki.nlk374.neoworks.co.uk/display/NLCE/PSD2+Test+Cases
Feature: PSD2 payment card responses

	Background:
		Given feature "feature.payment.3ds2.enabled.uk-site" is switched on
		And feature "feature.pci.microform.enabled.uk-site" is switched on
		And i navigate to "Newlook" home page

	Scenario Outline: PSD2 successful, no popup card responses
		Given Select a product and navigate to payment page
		And I proceed to the guest checkout payment step

		When I fill in "<cardType>" card details and place an order

		Then Order has been successful
		And I record my "<cardType>" order number with popup="NO"
		Examples:
			| cardType                                                 |
			| visa.3ds.2.noPopup.frictionless.success                  |
			| visa.3ds.2.noPopup.frictionless.attemptsProcessing       |
			| visa.3ds.2.noPopup.frictionless.unavailable              |
			| visa.3ds.2.noPopup.lookup.unavailable                    |
			| visa.3ds.2.noPopup.enrollmentCheck.error                 |
			| visa.3ds.2.noPopup.enrollmentCheck.timeOut               |
			| visa.3ds.2.noPopup.authentication.bypassed               |

			| visa.3ds.1.noPopup.attemptsProcessing                    |
			| visa.3ds.1.noPopup.authentication.incomplete             |
			| visa.3ds.1.noPopup.authentication.unavailable            |
			| visa.3ds.1.noPopup.notEnrolled                           |
			| visa.3ds.1.noPopup.enrollmentCheck.timeOut               |
			| visa.3ds.1.noPopup.enrollmentCheck.error                 |

			| mastercard.3ds.2.noPopup.frictionless.success            |
			| mastercard.3ds.2.noPopup.frictionless.attemptsProcessing |
			| mastercard.3ds.2.noPopup.frictionless.unavailable        |
			| mastercard.3ds.2.noPopup.lookup.unavailable              |
			| mastercard.3ds.2.noPopup.enrollmentCheck.error           |
			| mastercard.3ds.2.noPopup.enrollmentCheck.timeOut         |
			| mastercard.3ds.2.noPopup.authentication.bypassed         |

			| amex.3ds.2.noPopup.frictionless.success                  |
			| amex.3ds.2.noPopup.frictionless.attemptsProcessing       |
			| amex.3ds.2.noPopup.frictionless.unavailable              |
			| amex.3ds.2.noPopup.lookup.unavailable                    |
			| amex.3ds.2.noPopup.enrollmentCheck.error                 |
			| amex.3ds.2.noPopup.enrollmentCheck.timeOut               |
			| amex.3ds.2.noPopup.authentication.bypassed               |

	Scenario Outline: PSD2 successful, popup card responses
		Given Select a product and navigate to payment page
		And I proceed to the guest checkout payment step

		When I fill in "<cardType>" card details and place an order
		And I verify my 3DS card

		Then Order has been successful
		And I record my "<cardType>" order number with popup="YES"
		Examples:
			| cardType                                  |
			| visa.3ds.2.popup.stepUp.success           |
			| visa.3ds.2.popup.stepUp.unavailable       |
			| visa.3ds.1.popup.success                  |
			| mastercard.3ds.2.popup.stepUp.success     |
			| mastercard.3ds.2.popup.stepUp.unavailable |
			| amex.3ds.2.popup.stepUp.success           |
			| amex.3ds.2.popup.stepUp.unavailable       |

	Scenario Outline: PSD2 fail, no popup card responses
		Given Select a product and navigate to payment page
		And I proceed to the guest checkout payment step

		When I fill in "<cardType>" card details and place an order

		Then I am on the checkout page
		And The cannot authenticate payment error message is visible
		And I record my "<cardType>" order number with popup="NO"
		Examples:
			| cardType                                       |
			| visa.3ds.2.noPopup.frictionless.fail           |
			| visa.3ds.2.noPopup.frictionless.rejected       |
			| visa.3ds.1.noPopup.authentication.error        |
			| mastercard.3ds.2.noPopup.frictionless.fail     |
			| mastercard.3ds.2.noPopup.frictionless.rejected |
			| amex.3ds.2.noPopup.frictionless.fail           |
			| amex.3ds.2.noPopup.frictionless.rejected       |

	Scenario Outline: PSD2 fail, popup card responses
		Given Select a product and navigate to payment page
		And I proceed to the guest checkout payment step

		When I fill in "<cardType>" card details and place an order
		And I verify my 3DS card

		Then I am on the checkout page
		And The failed payment error message is visible
		And I record my "<cardType>" order number with popup="YES"
		Examples:
			| cardType                                            |
			| visa.3ds.2.popup.stepUp.fail                        |
			| visa.3ds.1.popup.authentication.fail                |
			| visa.3ds.1.popup.authentication.fail.customerExited |
			| visa.3ds.1.popup.invalidPares.success               |
			| mastercard.3ds.2.popup.stepUp.fail                  |
			| amex.3ds.2.popup.stepUp.fail                        |
