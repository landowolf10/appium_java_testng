package org.lando.assertions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.lando.locators.CompleteCheckoutLocators;
import org.lando.locators.OverviewLocators;
import org.lando.locators.ProductLocators;
import org.lando.utils.BasePage;
import org.lando.utils.Scroll;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.*;

public class CustomAssertions extends BasePage {
    private final Scroll scroll;

    public CustomAssertions(AppiumDriver driver) {
        super(driver);
        this.scroll = new Scroll(driver);
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

        elementIsDisplayed(
                AppiumBy.accessibilityId(OverviewLocators.overviewContainer),
                10
        );

        scroll.scrollUntilVisible(
                AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Total\")"),
                "up",
                7
        );

        waitUntilElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Total\")"),
                5
        );

        List<WebElement> prices =
                driver.findElements(AppiumBy.className("android.widget.TextView"));

        String subtotalText = prices.stream()
                .map(WebElement::getText)
                .filter(t -> t.contains("Item total"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Subtotal not found"));

        String taxText = prices.stream()
                .map(WebElement::getText)
                .filter(t -> t.contains("Tax"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Tax not found"));

        String totalText = prices.stream()
                .map(WebElement::getText)
                .filter(t -> t.startsWith("Total"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Total not found"));

        double subtotal = extractPrice(subtotalText);
        double tax = extractPrice(taxText);
        double total = extractPrice(totalText);

        assertEquals(
                total,
                subtotal + tax,
                0.01,
                "Total calculation is incorrect"
        );
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