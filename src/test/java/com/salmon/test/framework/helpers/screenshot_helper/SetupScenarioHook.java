package com.salmon.test.framework.helpers.screenshot_helper;

import com.salmon.test.framework.helpers.utils.SessionInfo;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SetupScenarioHook
{
	@Before
	public void setupScenario(final Scenario scenario)
	{
		SessionInfo.scenarioName = scenario.getName();
	}
}
