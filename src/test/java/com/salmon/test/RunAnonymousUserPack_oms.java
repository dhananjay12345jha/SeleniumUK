package com.salmon.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features", tags = {"@Anonymous and not @inprogress and not @todo and not @Ignore and not @condev_data and not @todo_oms and not @orderprocess_condev"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report",
        "json:target/cucumber-report/cucumber3/cucumber.json",
        "rerun:target/cucumber-report/cucumber3/rerun.txt"},
        glue = "com.salmon.test")
public class RunAnonymousUserPack_oms extends AbstractTestNGCucumberTests {
}
