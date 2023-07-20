package com.salmon.test;


import com.salmon.test.framework.helpers.Props;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import io.leangen.geantyref.TypeFactory;
import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AbstractTestNGCucumberTestsCustom {
	private TestNGCucumberRunner testNGCucumberRunner;
	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		Class claz=this.getClass();
		CucumberOptions options=this.getClass().getAnnotation(CucumberOptions.class);
		String originalTags[]=options.tags();
		Props.loadRunConfigProps("/environment.properties");
		String newTags[]=ArrayUtils.addAll(originalTags,"@"+System.getProperty("locale", Props.getProp("defaultLocale")));
		Field annotationDataField = claz.getClass().getDeclaredField("annotationData");
		annotationDataField.setAccessible(true);
		Field annotationsField = annotationDataField.get(claz).getClass().getDeclaredField("annotations");
		annotationsField.setAccessible(true);
		Map<String, Object> annotationParameters = new HashMap<>();
		annotationParameters.put("features",options.features());
		annotationParameters.put("tags",newTags);
		annotationParameters.put("monochrome",options.monochrome());
		annotationParameters.put("plugin",options.plugin());
		annotationParameters.put("glue",options.glue());
		CucumberOptions newOptions= TypeFactory.annotation(CucumberOptions.class, annotationParameters);
		Map<Class<? extends Annotation>, Annotation> annotations =  (Map<Class<? extends Annotation>, Annotation>) annotationsField.get(annotationDataField.get(claz));
		annotations.put(CucumberOptions.class, newOptions);

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


}
