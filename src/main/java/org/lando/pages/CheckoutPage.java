package org.lando.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.lando.locators.CheckoutLocators;
import org.lando.utils.BasePage;

public class CheckoutPage extends BasePage {
    public CheckoutPage(AppiumDriver driver) {
        super(driver);
    }

    public void fillInformation(String firstName, String lastName, String zipCode) {
        writeText(AppiumBy.accessibilityId(CheckoutLocators.firstName), firstName, 10);
        writeText(AppiumBy.accessibilityId(CheckoutLocators.lastName), lastName, 10);
        writeText(AppiumBy.accessibilityId(CheckoutLocators.zipCode), zipCode, 10);
        clickElement(AppiumBy.accessibilityId(CheckoutLocators.continueButton), 10);
    }
}