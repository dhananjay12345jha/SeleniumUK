package com.salmon.test.config;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.ContextConfiguration;


/**
 * This is the spring mandate configuration, which will initialise all the beans on scripts startup
 */
@ContextConfiguration(classes = {Config.class})
@CucumberContextConfiguration
public class ContextConfig {

	/**
	 * Dummy method to tell cucumber to initialise this class
	 */
	@Before
	public void setUp() {
		System.out.println("Trying to set up");
	}

}
