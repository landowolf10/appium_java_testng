package com.lando.tests;

import io.appium.java_client.AppiumDriver;
import org.lando.pages.LoginPage;
import org.lando.utils.SetUp;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    AppiumDriver driver;
    LoginPage loginPage;

    @BeforeTest
    @Parameters({"deviceName", "platformName", "platformVersion"})
    public void setUp(String deviceName, String platformName, String platformVersion) {
        SetUp setUp = new SetUp();
        driver = setUp.getDriver(deviceName, platformName, platformVersion, true);
        //this.testData = JsonLoader.getTestData(testDataPath, FlightTestData.class);

        loginPage = new LoginPage(driver);

        Assert.assertTrue(loginPage.homeButtonDisplayed());
    }

    @Test
    public void successfulLoginTest() {
        loginPage.pressLoginOption();
        loginPage.writeCredentials("lando.wolg@gmail.com", "lando1234");
        loginPage.pressLoginButton();

        Assert.assertEquals(loginPage.getLoginModalText(), "You are logged in!");
    }

    @AfterSuite
    public void tearDown() {
        SetUp.quitDriver();
    }
}
