$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/mobile/ReturnsAndRefundsTestsMobile.feature");
formatter.feature({
  "name": "Returns and Refunds -Mobile",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@anonymousMobile2Samsung"
    }
  ]
});
formatter.scenarioOutline({
  "name": "1 Check Order Returns and Refunds - Return",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@orderprocess_oms"
    },
    {
      "name": "@testing"
    }
  ]
});
formatter.step({
  "name": "feature \"feature.pci.microform.enabled.uk-site\" is switched off",
  "keyword": "And "
});
formatter.step({
  "name": "user provides required details to create new account on mobile",
  "keyword": "When "
});
formatter.step({
  "name": "I have checked out \"Qty: 4\" of a line item",
  "keyword": "And "
});
formatter.step({
  "name": "I have checked out with the a delivery address \"\u003cfirstName\u003e\" and \"\u003clastName\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "select debit or credit card tab",
  "keyword": "And "
});
formatter.step({
  "name": "i change the billing address and add another card",
  "keyword": "And "
});
formatter.step({
  "name": "I get order number from the order confirmation page",
  "keyword": "And "
});
formatter.step({
  "name": "select order with status \"\u003corder_status\u003e\" order status with \"\u003cOrder_view_Link\u003e\" link mobile",
  "keyword": "And "
});
formatter.step({
  "name": "refund history \"\u003cRefund History\u003e\" section exits",
  "keyword": "Then "
});
formatter.step({
  "name": "also verify other order details in items section with status \"\u003corder_status\u003e\" and \"\u003cdelivery_details_type\u003e\"",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "firstName",
        "lastName",
        "Refund History",
        "order_status",
        "delivery_details_type",
        "Order_view_Link"
      ]
    },
    {
      "cells": [
        "return",
        "return",
        "true",
        "Returned",
        "UK Standard",
        "Track and view order"
      ]
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "i navigate to \"Newlook\" home page",
  "keyword": "Given "
});
formatter.match({
  "location": "com.salmon.test.step_definitions.gui.NavigationSteps.i_navigate_to_the_Salmon_page(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "1 Check Order Returns and Refunds - Return",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@anonymousMobile2Samsung"
    },
    {
      "name": "@orderprocess_oms"
    },
    {
      "name": "@testing"
    }
  ]
});
formatter.step({
  "name": "feature \"feature.pci.microform.enabled.uk-site\" is switched off",
  "keyword": "And "
});
formatter.match({
  "location": "com.salmon.test.step_definitions.gui.given.FeatureOnOffStepDefs.featureIsSwitchedOnOff(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user provides required details to create new account on mobile",
  "keyword": "When "
});
formatter.match({
  "location": "com.salmon.test.step_definitions.gui.when.CreateNewAccountWhenStepDefs.userProvidesRequiredDetailsToCreateNewAccountOnMobile()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I have checked out \"Qty: 4\" of a line item",
  "keyword": "And "
});
formatter.match({
  "location": "com.salmon.test.step_definitions.gui.ReturnsAndRefundsStepDefinitions.iHaveCheckedOutOfALineItem(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I have checked out with the a delivery address \"return\" and \"return\"",
  "keyword": "And "
});
formatter.match({
  "location": "com.salmon.test.step_definitions.gui.ReturnsAndRefundsStepDefinitions.iHaveCheckedOutWithTheADeliveryAddressAnd(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "select debit or credit card tab",
  "keyword": "And "
});
formatter.match({
  "location": "com.salmon.test.step_definitions.gui.ShoppingCartStepDefinitions.select_debit_or_credit_card_tab()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "i change the billing address and add another card",
  "keyword": "And "
});
formatter.match({
  "location": "com.salmon.test.step_definitions.gui.ReturnsAndRefundsStepDefinitions.iChangeTheBillingAddressAndAddAnotherCard()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get order number from the order confirmation page",
  "keyword": "And "
});
formatter.match({
  "location": "com.salmon.test.step_definitions.gui.then.PurchasesThenStepDefs.iGetOrderNumberFromTheOrderConfirmationPage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "select order with status \"Returned\" order status with \"Track and view order\" link mobile",
  "keyword": "And "
});
formatter.match({
  "location": "com.salmon.test.step_definitions.gui.then.PurchasesThenStepDefs.selectOrderWithStatusOMSMobile(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "refund history \"true\" section exits",
  "keyword": "Then "
});
formatter.match({
  "location": "com.salmon.test.step_definitions.gui.then.PurchasesThenStepDefs.refundHistorySectionExits(java.lang.Boolean)"
});
formatter.result({
  "error_message": "java.lang.AssertionError: expected:\u003ctrue\u003e but was:\u003cfalse\u003e\r\n\tat org.junit.Assert.fail(Assert.java:89)\r\n\tat org.junit.Assert.failNotEquals(Assert.java:835)\r\n\tat org.junit.Assert.assertEquals(Assert.java:120)\r\n\tat org.junit.Assert.assertEquals(Assert.java:146)\r\n\tat com.salmon.test.step_definitions.gui.then.PurchasesThenStepDefs.refundHistorySectionExits(PurchasesThenStepDefs.java:189)\r\n\tat ✽.refund history \"true\" section exits(file:///D:/OMSPROJECT/NLK374/test/NEWLOOK_SAF/src/test/resources/features/mobile/ReturnsAndRefundsTestsMobile.feature:142)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "also verify other order details in items section with status \"Returned\" and \"UK Standard\"",
  "keyword": "And "
});
formatter.match({
  "location": "com.salmon.test.step_definitions.gui.then.PurchasesThenStepDefs.also_verify_other_order_details_in_items_section_with_status(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.write("https://www-aws19.dev-newlook.com/uk/my-account/order/HY01597003");
formatter.embedding("image/png", "embedded0.png", "screenshot");
formatter.after({
  "status": "passed"
});
});