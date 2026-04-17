package com.lando.tests;

import io.qameta.allure.*;
import org.testng.annotations.*;

@Epic("Checkout")
@Feature("E2E Purchase")
public class CheckoutTest extends BaseTest{
    @Test
    @Story("Complete checkout flow")
    @Severity(SeverityLevel.CRITICAL)
    public void checkoutFlow() {
        loginPage.login("standard_user", "secret_sauce");
        assertions.assertLoginSuccess();

        productPage.addRandomProductToCart();
        assertions.assertProductsLoaded();

        productPage.gotToCart();
        assertions.assertCartNotEmpty();

        cartPage.checkout();

        checkoutPage.fillInformation("Orlando", "Avila", "10330");

        assertions.assertOverviewTotals();

        overviewPage.finishCheckout();

        completePage.assertOrderComplete();
        assertions.assertOrderComplete();
        assertions.assertBackHomeButton();
    }
}
