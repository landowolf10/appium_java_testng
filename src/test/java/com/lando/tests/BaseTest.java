package com.lando.tests;

import io.appium.java_client.AppiumDriver;
import org.lando.pages.*;
import org.lando.utils.SetUp;
import org.testng.annotations.*;

public class BaseTest {
    AppiumDriver driver;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OverviewPage overviewPage;
    CompletePage completePage;


    @BeforeTest
    @Parameters({"deviceName", "platformName", "platformVersion"})
    public void setUp(String deviceName, String platformName, String platformVersion) {
        SetUp setUp = new SetUp();
        driver = setUp.getDriver(deviceName, platformName, platformVersion, true);

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        overviewPage = new OverviewPage(driver);
        completePage = new CompletePage(driver);
    }

    @AfterTest
    public void tearDown() {
        SetUp.quitDriver();
    }
}
