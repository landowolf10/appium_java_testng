package org.lando.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.lando.locators.OverviewLocators;
import org.lando.utils.BasePage;
import org.lando.utils.Scroll;

public class OverviewPage extends BasePage {
    private final Scroll scroll;

    public OverviewPage(AppiumDriver driver) {
        super(driver);
        scroll = new Scroll(driver);
    }

    public void finishCheckout() {
        scroll.scrollUntilVisible(
                AppiumBy.accessibilityId(OverviewLocators.finishButton), "up", 5
        );
        clickElement(AppiumBy.accessibilityId(OverviewLocators.finishButton), 10);
    }
}