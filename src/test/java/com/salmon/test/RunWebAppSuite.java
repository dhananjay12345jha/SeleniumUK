package com.salmon.test;


import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "target/test-classes", tags = {"@mobile and not @oms_data and not @oms_flow and not @orderprocess_oms"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/webapp",
        "json:target/cucumber-report/webapp/cucumber.json",
        "rerun:target/cucumber-report/webapp/runwebat/rerun.txt"},
        glue = "com.salmon.test.step_definitions.webapp")
public class RunWebAppSuite {

}
