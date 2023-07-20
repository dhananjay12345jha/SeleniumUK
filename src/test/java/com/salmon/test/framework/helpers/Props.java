package com.salmon.test.framework.helpers;

import com.salmon.test.services.GetFeatureStatuses;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.lang.System.out;

public class Props
{
	private static final Logger LOG = LoggerFactory.getLogger(Props.class);
	private static Properties environmentProps;
	private static Properties properties;
	private static Properties deviceProperties;


	/**
	 * Gets the key from messages.properties for a Site
	 *
	 * @param key
	 **/
	public static String getMessage(final String key)
	{
		if (StringUtils.isEmpty(key))
		{
			return "";
		}
		else
		{
			return ResourceBundle.getBundle("props/messages").getString(key);
		}
	}

	public static String getFeatureStatus(final String feature)
	{
		if (StringUtils.isEmpty(feature))
		{
			return "";
		}
		else
		{
			try
			{
				GetFeatureStatuses.getFeatureStatus(feature);
				return System.getProperty(feature);
			}
			catch (final IllegalArgumentException e)
			{
				LOG.error(e.getMessage());
			}
			return System.getProperty(feature);
		}
	}

	/**
	 * Gets the key from Config.properties related to chosen profile
	 *
	 * @param key
	 **/
	public static String getProp(final String key)
	{
		if (StringUtils.isEmpty(key))
		{
			return "";
		}
		else
		{
			return properties.getProperty(key);
		}
	}

	public static String getDeviceProp(final String key)
	{
		if (StringUtils.isEmpty(key))
		{
			return "";
		}
		else
		{
			try
			{
				return deviceProperties.getProperty(key);
			}
			catch (final Exception ex)
			{
				return getProp(key);
			}
		}
	}


	public static void loadRunConfigProps(final String configPropertyFileLocation)
	{
		environmentProps = new Properties();
		try (final InputStream inputStream = Props.class.getResourceAsStream(configPropertyFileLocation))
		{
			environmentProps.load(inputStream);
			environmentProps.list(out);
		}
		catch (final IOException e)
		{
			LOG.error(e.getMessage());
		}
		properties = new Properties();
		try (final InputStream inputStream = Props.class.getResourceAsStream(environmentProps.getProperty("profile.path")))
		{
			properties.load(inputStream);
			properties.list(out);
		}
		catch (final IOException e)
		{
			LOG.error(e.getMessage());
		}

		deviceProperties = new Properties();
		try (final InputStream inputStream = Props.class.getResourceAsStream(environmentProps.getProperty("device")))
		{
			deviceProperties.load(inputStream);
			deviceProperties.list(out);
		}
		catch (final IOException e)
		{
			LOG.error(e.getMessage());
		}
	}
}
