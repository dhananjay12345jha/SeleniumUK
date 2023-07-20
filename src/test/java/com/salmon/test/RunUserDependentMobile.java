package com.salmon.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", tags = {"@UserDependentMobile and not @inprogress and not @todo and not @Ignore and not @UserDependentMobileSamsung and not @oms_data and not @oms_flow and not @orderprocess_oms"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report",
        "json:target/cucumber-report/cucumber8/cucumber.json",
        "rerun:target/cucumber-report/cucumber8/rerun.txt"},
        glue = "com.salmon.test")
public class RunUserDependentMobile extends AbstractTestNGCucumberTests {

}
