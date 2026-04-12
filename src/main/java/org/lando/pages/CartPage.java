package org.lando.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.lando.locators.CartLocators;
import org.lando.utils.BasePage;

public class CartPage extends BasePage {
    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    public void checkout() {
        elementIsDisplayed(AppiumBy.accessibilityId(CartLocators.cart), 10);
        clickElement(AppiumBy.accessibilityId(CartLocators.checkoutButton), 10);
    }
}