package org.lando.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.lando.locators.ProductLocators;
import org.lando.utils.BasePage;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ProductPage extends BasePage {
    public ProductPage(AppiumDriver driver) {
        super(driver);
    }

    public void addRandomProductToCart() {
        List<WebElement> buttons = getAllElementsBy(AppiumBy.accessibilityId(ProductLocators.addToCartButton), 5);

        if (buttons.isEmpty()) {
            throw new RuntimeException("No 'Add to cart' buttons found");
        }

        Random random = new Random();
        WebElement randomButton = buttons.get(random.nextInt(buttons.size()));
        randomButton.click();
    }

    public void gotToCart() {
        clickElement(AppiumBy.accessibilityId(ProductLocators.cart), 10);
    }
}