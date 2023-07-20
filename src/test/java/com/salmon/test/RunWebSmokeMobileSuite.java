package com.salmon.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", tags = {"@MobileSmokePack and not @todo and not @duplicate and not @Ignore and not @oms_data and not @oms_flow and not @orderprocess_oms"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/mobile-smoke",
        "json:target/cucumber-report/mobile-smoke/cucumber.json",
        "rerun:target/cucumber-report/mobile-smoke/rerun.txt"},
        glue = "com.salmon.test")
public class RunWebSmokeMobileSuite extends AbstractTestNGCucumberTests {
}
