package com.salmon.test;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/web",
        tags = {"@newlook and not @todo and not @condev_data and not @todo_oms and not @orderprocess_condev"},
        monochrome = true,
        plugin = {
        "pretty", "html:target/cucumber-report/runwebat",
        "json:target/cucumber-report/runwebat/cucumber.json",
        "rerun:target/cucumber-report/runwebat/rerun.txt"},
        glue = "com.salmon.test")
public class RunWebATSuite_oms extends AbstractTestNGCucumberTests
{
}
