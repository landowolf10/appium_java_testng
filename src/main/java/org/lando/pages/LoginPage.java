package org.lando.pages;

import io.appium.java_client.AppiumDriver;
import org.lando.locators.LoginLocators;
import org.lando.utils.BasePage;
import org.openqa.selenium.By;

import static org.lando.locators.LoginLocators.modalWindowLogin;

public class LoginPage extends BasePage {

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public Boolean homeButtonDisplayed() {
        return elementIsDisplayed(By.xpath(LoginLocators.homeOption), 10);
    }

    public void pressLoginOption() {
        clickElement(By.xpath(LoginLocators.loginOption), 10);
    }

    public void writeCredentials(String email, String password) {
        writeText(By.xpath(LoginLocators.userTextbox), email, 10);
        writeText(By.xpath(LoginLocators.passwordTextbox), password, 10);
    }

    public void pressLoginButton()
    {
        clickElement(By.xpath(LoginLocators.submitButton), 10);
    }

    public String getLoginModalText() {
        return getElementText(By.xpath(modalWindowLogin), 10);
    }
}