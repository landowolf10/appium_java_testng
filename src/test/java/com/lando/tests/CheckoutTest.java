package com.lando.tests;

import org.testng.annotations.*;

public class CheckoutTest extends BaseTest{
    @Test
    public void checkoutFLow() {
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
