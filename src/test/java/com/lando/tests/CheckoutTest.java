package com.lando.tests;

import io.qameta.allure.*;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;

@Epic("Checkout")
@Feature("E2E Purchase")
public class CheckoutTest extends BaseTest {
    @Test
    @Story("Complete checkout flow")
    @Severity(SeverityLevel.CRITICAL)
    public void checkoutFlow() {
        List<String> productsToBuy = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Fleece Jacket");

        loginPage.login("standard_user", "secret_sauce");
        assertions.assertLoginSuccess();
        assertions.assertProductsLoaded();

        productPage.addRandomProductToCart();
        productPage.gotToCart();
        assertions.assertCartNotEmpty();

        cartPage.checkout();

        checkoutPage.fillInformation("Orlando", "Avila", "10330");

        assertions.assertOverviewTotals();

        overviewPage.finishCheckout();

        assertions.assertOrderComplete();
        assertions.assertBackHomeButton();
        completePage.clickBackToHomeButton();
    }
}
