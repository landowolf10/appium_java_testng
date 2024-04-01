package org.lando.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SetUp {
    AppiumDriver driver;
    private static boolean driverInstanceExists = false;
    private static AppiumDriver driverInstance = null;

    public AppiumDriver getDriver(String deviceName, String platformName, String platformVersion, boolean isRemote) {
        if (driverInstanceExists)
            driver = driverInstance;
        else
            if (isRemote)
                driver = createRemoteDriver(deviceName, platformName, platformVersion);
            else
                driver = createLocalDriver(deviceName, platformName, platformVersion);

        driverInstanceExists = true;
        driverInstance = driver;

        return driver;
    }

    private AppiumDriver createRemoteDriver(String deviceName, String platformName, String platformVersion) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        String userName = System.getenv("browserstack_username");
        String accessKey = System.getenv("browserstack_access_key");

        System.out.println("Username: " + userName);
        System.out.println("Access key: " + accessKey);

        browserstackOptions.put("appiumVersion", "2.0.1");
        browserstackOptions.put("gpsLocation", "41.8755616,-87.6244212");

        if (platformName.equals("Android")) {
            capabilities.setCapability("bstack:options", browserstackOptions);
            capabilities.setCapability("buildName", "Testing Android app");
            capabilities.setCapability("projectName", "Successful login");
            capabilities.setCapability("deviceName", deviceName);
            capabilities.setCapability("platformName", platformName);
            capabilities.setCapability("platformVersion", platformVersion);
            capabilities.setCapability("app", ConstantData.androidApp);
            capabilities.setCapability("autoGrantPermissions", "true");
        }
        else if (platformName.equals("iOS")) {
            capabilities.setCapability("bstack:options", browserstackOptions);
            capabilities.setCapability("buildName", "Testing iOS app");
            capabilities.setCapability("projectName", "Successful login");
            capabilities.setCapability("deviceName", deviceName);
            capabilities.setCapability("platformName", platformName);
            capabilities.setCapability("platformVersion", platformVersion);
            capabilities.setCapability("app", ConstantData.iosApp);
            capabilities.setCapability("autoGrantPermissions", "true");
        }

        try {
            driver = new AppiumDriver(new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", userName,
                    accessKey)), capabilities   );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }

    private AppiumDriver createLocalDriver(String deviceName, String platformName, String platformVersion) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platformName.equals("Android")) {
            capabilities.setCapability("automationName", ConstantData.automationName);
            capabilities.setCapability("platformName", ConstantData.platformName);
            capabilities.setCapability("deviceName", ConstantData.deviceName);
            capabilities.setCapability("appPackage", ConstantData.appPackage);
            capabilities.setCapability("appActivity", ConstantData.appActivity);
            capabilities.setCapability("autoGrantPermissions", true);
        }
        else if (platformName.equals("iOS")) {
            capabilities.setCapability("buildName", "Testing iOS app");
            capabilities.setCapability("projectName", "Successful login");
            capabilities.setCapability("deviceName", deviceName);
            capabilities.setCapability("platformName", platformName);
            capabilities.setCapability("platformVersion", platformVersion);
            capabilities.setCapability("app", ConstantData.iosApp);
            capabilities.setCapability("autoGrantPermissions", "true");
        }

        try {
            driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }

    public static void quitDriver() {
        AppiumDriver currentDriver;

        if (driverInstanceExists) {
            currentDriver = driverInstance;
            currentDriver.quit();
        }

        driverInstanceExists = false;
        driverInstance = null;
    }
}