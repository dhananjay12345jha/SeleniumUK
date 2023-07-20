package com.salmon.test.framework.helpers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidHelper extends EventFiringWebDriver {

    private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";
    private static String VENDOR;
    private static AndroidDriver<WebElement> ANDROID_DRIVER = null;
    private static final Thread CLOSE_THREAD = new Thread() {

        @Override
        public void run() {
            ANDROID_DRIVER.quit();
        }
    };

    static {
        Props.loadRunConfigProps(RUN_CONFIG_PROPERTIES);
        VENDOR = Props.getProp("vendor");
        ANDROID_DRIVER = startAppiumDriver();
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    private AndroidHelper() {
        super(ANDROID_DRIVER);
    }


    private static AndroidDriver<WebElement> startAppiumDriver() {
        try {
            if (VENDOR.equalsIgnoreCase("MoTel")) {
                DesiredCapabilities capabilities = getMoTelDesiredCapabilities();
                ANDROID_DRIVER = new AndroidDriver<>(new URL("https://motel.criticalsoftware.com/wd/hub"), capabilities);
            } else {
                DesiredCapabilities capabilities = getAppiumDesiredCapabilities();
                ANDROID_DRIVER = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ANDROID_DRIVER;
    }


    private static DesiredCapabilities getMoTelDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.ANDROID);
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("deviceName", "Nexus 5X");
        capabilities.setCapability("token", "0483d18d8a0d4511bd7b292725203878cfd64c3f05cd43d3889dd5b74e9f5c3b");
        return capabilities;
    }

    private static DesiredCapabilities getAppiumDesiredCapabilities() {
        File app = new File("D:\\projects\\master_cucumber_testng\\tools\\ContactManager.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.example.android.contactmanager");
        //capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, ".ContactManager");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        //capabilities.setCapability("deviceName", "42f7ab1fb7b59fab");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        return capabilities;
    }

    public static AndroidDriver<WebElement> getAndroidWebDriver() {
        return ANDROID_DRIVER;
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException(
                    "You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }
}
