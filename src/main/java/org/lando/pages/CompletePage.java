package org.lando.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.lando.locators.CompleteCheckoutLocators;
import org.lando.utils.BasePage;

public class CompletePage extends BasePage {
    public CompletePage(AppiumDriver driver) {
        super(driver);
    }

    public void clickBackToHomeButton() {
        clickElement(AppiumBy.accessibilityId(CompleteCheckoutLocators.backToHomeButton), 10);
    }
}