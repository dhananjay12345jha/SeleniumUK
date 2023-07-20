package com.salmon.test.framework;

import com.salmon.test.framework.helpers.AndroidHelper;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class AndroidObject {
    private static final long DRIVER_WAIT_TIME = 10;
//    private static final Logger LOG = LoggerFactory.getLogger(AndroidObject.class);
        private static final Logger LOG = LogManager.getLogger(AndroidObject.class.getName());


	@Getter
    protected AndroidDriver androidDriver;


    protected AndroidObject() {
        this.androidDriver = AndroidHelper.getAndroidWebDriver();
    }

    public void swipe(int startx, int starty, int endx, int endy, int duration) {
        androidDriver.swipe(startx, starty, endx, endy, duration);
    }

}
