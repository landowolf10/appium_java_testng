package org.lando.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    private WebElement getElementBy(By elementLocator, int maxWaitSec)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitSec));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    public List<WebElement> getAllElementsBy(By elementLocator)
    {
        return driver.findElements(elementLocator);
    }

    public void writeText(By elementLocator, String text, int maxWaitSec) {
        getElementBy(elementLocator, maxWaitSec).clear();
        getElementBy(elementLocator, maxWaitSec).sendKeys(text);
    }

    public void clickElement(By elementLocator, int maxWaitSec) {
        getElementBy(elementLocator, maxWaitSec).click();
    }

    public void clickElementFromList(WebElement element)
    {
        element.click();
    }

    public String getElementText(By elementLocator, int maxWaitSec) {
        return getElementBy(elementLocator, maxWaitSec).getText();
    }

    public String getAttribute(By elementLocator, String attribute, int maxWaitSec) {
        return getElementBy(elementLocator, maxWaitSec).getAttribute(attribute);
    }

    public Object platformName() {
        return driver.getCapabilities().getCapability("platformName");
    }

    public WebElement waitUntilElementLocated(By locatorType, int maxWaitSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitSec));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locatorType));
    }

    public boolean elementIsDisplayed(By locatorType, int maxWaitSec) {
        return getElementBy(locatorType, maxWaitSec).isDisplayed();
    }

    public boolean elementExists(By elementLocator, int maxWaitSec) {
        boolean elementDisplayed;

        try {
            elementDisplayed = waitUntilElementLocated(elementLocator, maxWaitSec).isDisplayed();
        }
        catch (TimeoutException e) {
            elementDisplayed = false;
        }

        return elementDisplayed;
    }

    public boolean elementIsSelected(By locatorType, int maxWaitSec) {
        return getElementBy(locatorType, maxWaitSec).isSelected();
    }

    public boolean elementIsEnabled(By locatorType, int maxWaitSec) {
        return getElementBy(locatorType, maxWaitSec).isEnabled();
    }

    /*public void quitDriver() {
        if (driver != null) {
            driver.quit();
            SetUp.killDriver();
            driver = null;
        }
    }*/

}