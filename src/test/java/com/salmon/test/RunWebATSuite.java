package com.salmon.test;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/web",
        tags = {"@newlook and not @todo and not @oms_data and not @oms_flowand not @orderprocess_oms"},
        monochrome = true,
        plugin = {
        "pretty", "html:target/cucumber-report/runwebat",
        "json:target/cucumber-report/runwebat/cucumber.json",
        "rerun:target/cucumber-report/runwebat/rerun.txt"},
        glue = "com.salmon.test")
public class RunWebATSuite extends AbstractTestNGCucumberTests
{
}
