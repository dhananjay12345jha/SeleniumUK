package com.salmon.test;

import com.salmon.test.framework.helpers.Props;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import io.cucumber.testng.CucumberOptions;

import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by rrbaddipadaga on 11/06/2018.
 */
@CucumberOptions(features = "@target/cucumber-report/initialMerge/rerun.txt", monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/reRunReport",
        "json:target/cucumber-report/reRunReport/cucumber.json"}, glue = "com.salmon.test")

public class RerunFailedScenariosSuite {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        if (isThresholdSatisfied())
            testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable {
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    /**
     * Returns two dimensional array of PickleEventWrapper scenarios with their associated CucumberFeatureWrapper feature.
     *
     * @return a two dimensional array of scenarios features.
     */
    @DataProvider
    public Object[][] scenarios() {
        if (testNGCucumberRunner == null) {
            return new Object[0][0];
        }
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        if (testNGCucumberRunner == null) {
            return;
        }
        testNGCucumberRunner.finish();
    }

    public boolean isThresholdSatisfied() {
        //check if rerun threshold is set from system property or config property
        int rerunThreshold = 0;
        Props.loadRunConfigProps("/environment.properties");
        try {
            rerunThreshold = Integer.parseInt(System.getProperty("rerunThreshold", Props.getProp("rerunThreshold")));
        } catch (Exception e) {
            rerunThreshold = 0;
        }

        System.out.println("Threshold is: " + rerunThreshold);
        long noOfFailures = 0;
        File reportDirectory = new File("target/cucumber-report/initialMerge");
        Collection<File> rerunFilesList = FileUtils.listFiles(reportDirectory, new String[]{"txt"}, false);
        for (File rerunSourceReport : rerunFilesList) {
            try {
                noOfFailures = noOfFailures + FileUtils.readFileToString(rerunSourceReport).split("\n").length;
            } catch (IOException e) {
                e.printStackTrace();
            };
        }
        System.out.println("No Of Failed Scenarios are: " + noOfFailures);
        if (noOfFailures >= rerunThreshold || noOfFailures == 0) {
            return false;
        }
        return true;
    }
}
