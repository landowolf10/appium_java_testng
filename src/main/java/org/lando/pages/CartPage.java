package org.lando.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.lando.locators.CartLocators;
import org.lando.utils.BasePage;
import org.lando.utils.Scroll;

public class CartPage extends BasePage {
    private final Scroll scroll;

    public CartPage(AppiumDriver driver) {
        super(driver);
        scroll = new Scroll(driver);
    }

    public void checkout() {
        elementIsDisplayed(AppiumBy.accessibilityId(CartLocators.cart), 10);
        scroll.scrollUntilVisible(
                AppiumBy.accessibilityId(CartLocators.checkoutButton), "up", 5
        );
        clickElement(AppiumBy.accessibilityId(CartLocators.checkoutButton), 10);
    }
}