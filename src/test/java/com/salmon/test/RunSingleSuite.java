package com.salmon.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features/web/", tags = {"@single and not @oms_data and not @oms_flow and not @orderprocess_oms"}, monochrome = true,
		plugin = {
        "pretty", "html:target/cucumber-report/single",
        "json:target/cucumber-report/single/cucumber.json",
        "rerun:target/cucumber-report/single/rerun.txt"},
        glue = "com.salmon.test")
public class RunSingleSuite extends AbstractTestNGCucumberTests {
}
