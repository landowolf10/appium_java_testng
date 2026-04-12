package org.lando.pages;

import io.appium.java_client.AppiumDriver;
import org.lando.locators.LoginLocators;
import org.lando.utils.BasePage;
import io.appium.java_client.AppiumBy;

public class LoginPage extends BasePage {

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        writeText(AppiumBy.accessibilityId(LoginLocators.email), email, 10);
        writeText(AppiumBy.accessibilityId(LoginLocators.password), password, 10);
        clickElement(AppiumBy.accessibilityId(LoginLocators.loginButton), 10);
    }
}