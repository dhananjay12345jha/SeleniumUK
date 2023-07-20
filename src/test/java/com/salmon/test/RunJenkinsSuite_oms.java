package com.salmon.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features", tags = "@mytest and not @condev_data and not @todo_oms and not @orderprocess_condev", monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report",
        "json:target/cucumber-report/cucumber.json",
        "rerun:target/cucumber-report/runjenkins/rerun.txt"},
        glue = "com.salmon.test")
public class RunJenkinsSuite_oms extends AbstractTestNGCucumberTests {
}
