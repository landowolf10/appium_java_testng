package org.lando.assertions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.lando.locators.CompleteCheckoutLocators;
import org.lando.locators.ProductLocators;
import org.lando.utils.BasePage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.*;

public class CustomAssertions extends BasePage {
    public CustomAssertions(AppiumDriver driver) {
        super(driver);
    }

    public void assertLoginSuccess() {
        assertTrue(
                elementIsDisplayed(AppiumBy.accessibilityId(ProductLocators.productsContainer), 10),
                "Login was not successful - Products screen not visible"
        );
    }

    public void assertProductsLoaded() {
        int count = getAllElementsBy(AppiumBy.accessibilityId(ProductLocators.addToCartButton), 5).size();
        assertTrue(count > 0, "No products loaded in product page");
    }

    public void assertCartNotEmpty() {
        List<WebElement> items =
                getAllElementsBy(AppiumBy.accessibilityId("test-Item"), 5);

        assertFalse(items.isEmpty(), "Cart is empty");
    }

    public void assertOverviewTotals() {

        String subtotalText = getElementText(
                AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Item total:\")"),
                5
        );

        String taxText = getElementText(
                AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Tax:\")"),
                5
        );

        List<WebElement> labels = driver.findElements(
                AppiumBy.className("android.widget.TextView")
        );

        String totalText = labels.stream()
                .map(WebElement::getText)
                .filter(t -> t.startsWith("Total:") && !t.startsWith("Item"))
                .findFirst()
                .orElseThrow();

        double subtotal = extractPrice(subtotalText);
        double tax = extractPrice(taxText);
        double total = extractPrice(totalText);

        assertEquals(total, subtotal + tax, 0.01, "Total calculation is incorrect");
    }

    public void assertOrderComplete() {
        assertTrue(
                elementIsDisplayed(AppiumBy.accessibilityId(CompleteCheckoutLocators.completeCheckoutView), 10),
                "Order completion screen not displayed"
        );
    }

    public void assertBackHomeButton() {
        assertTrue(
                elementIsDisplayed(AppiumBy.accessibilityId("test-BACK HOME"), 10),
                "Back Home button not visible"
        );
    }
}