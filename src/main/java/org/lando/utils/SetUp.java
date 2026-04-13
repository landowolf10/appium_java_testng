package org.lando.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SetUp {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public AppiumDriver getDriver(String deviceName, String platformName, String platformVersion) {

        if (driver.get() == null) {

            boolean isRemote = Boolean.parseBoolean(
                    System.getenv().getOrDefault("RUN_ON_BROWSERSTACK", "false")
            );

            if (isRemote) {
                driver.set(createRemoteDriver(deviceName, platformName, platformVersion));
            } else {
                driver.set(createLocalDriver(platformName));
            }
        }

        return driver.get();
    }

    private AppiumDriver createRemoteDriver(String deviceName, String platformName, String platformVersion) {

        String userName = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        String androidApp = System.getenv("BROWSERSTACK_ANDROID_APP");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("appium:deviceName", deviceName);
        capabilities.setCapability("appium:platformVersion", platformVersion);
        capabilities.setCapability("appium:app", androidApp);
        capabilities.setCapability("appium:autoGrantPermissions", true);

        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("appiumVersion", "2.0.1");
        browserstackOptions.put("buildName", "Testing Android app");
        browserstackOptions.put("projectName", "Successful login");

        capabilities.setCapability("bstack:options", browserstackOptions);

        try {
            return new AppiumDriver(
                    new URL(String.format(
                            "https://%s:%s@hub.browserstack.com/wd/hub",
                            userName,
                            accessKey
                    )),
                    capabilities
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private AppiumDriver createLocalDriver(String platformName) {

        try {
            if (platformName.equals("Android")) {

                UiAutomator2Options options = new UiAutomator2Options();
                options.setDeviceName(ConstantData.deviceName);
                options.setAppPackage(ConstantData.appPackage);
                options.setAppActivity(ConstantData.appActivity);
                options.setAutoGrantPermissions(true);
                options.setCapability("appium:settings[waitForIdleTimeout]", 0);
                options.setCapability("appium:uiautomator2ServerLaunchTimeout", 60000);

                return new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("Platform not supported: " + platformName);
    }

    public static void quitDriver() {

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}