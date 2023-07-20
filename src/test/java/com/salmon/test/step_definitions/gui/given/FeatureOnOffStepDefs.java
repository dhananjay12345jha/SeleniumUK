package com.salmon.test.step_definitions.gui.given;

import com.salmon.test.page_objects.gui.NewLookHelper;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.testng.SkipException;

/**
 * Created by gates on 26/03/18.
 */
@Slf4j
public class FeatureOnOffStepDefs
{
	@Given("^feature \"([^\"]*)\" is switched (on|off)$")
	public void featureIsSwitchedOnOff(final String featureName, final String onOrOff)
	{
		final boolean featureStatus = NewLookHelper.getFeatureStatus(featureName);
		if (featureStatus == "on".equals(onOrOff))
		{
			log.info("### feature is " + onOrOff + ": " + featureName);
		}
		else
		{
			throw new SkipException("FEATURE IS NOT " + onOrOff + ": " + featureName);
		}
	}
}
