package com.lando.tests;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import io.qameta.allure.testng.AllureTestNg;
import org.lando.assertions.CustomAssertions;
import org.lando.pages.*;
import org.lando.utils.SetUp;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Listeners({AllureTestNg.class})
public class BaseTest {
    AppiumDriver driver;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OverviewPage overviewPage;
    CompletePage completePage;
    CustomAssertions assertions;


    @BeforeMethod
    @Parameters({"deviceName", "platformName", "platformVersion"})
    public void setUp(String deviceName, String platformName, String platformVersion) {
        SetUp setUp = new SetUp();
        driver = setUp.getDriver(deviceName, platformName, platformVersion);

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        overviewPage = new OverviewPage(driver);
        completePage = new CompletePage(driver);

        assertions = new CustomAssertions(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {
            attachScreenshot();
        }

        SetUp.quitDriver();
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
