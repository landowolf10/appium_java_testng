package com.lando.tests;

import org.testng.annotations.*;

public class CheckoutTest extends BaseTest{
    @Test
    public void checkoutFLow() {
        loginPage.login("standard_user", "secret_sauce");
        productPage.addRandomProductToCart();
        productPage.gotToCart();
        cartPage.checkout();
        checkoutPage.fillInformation("Orlando", "Avila", "10330");
        overviewPage.finishCheckout();
        completePage.assertOrderComplete();
    }
}
