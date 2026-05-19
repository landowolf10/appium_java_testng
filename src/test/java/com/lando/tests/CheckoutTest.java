package com.lando.tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.qameta.allure.*;
import org.lando.utils.JsonReader;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Epic("Checkout")
@Feature("E2E Purchase")
public class CheckoutTest extends BaseTest {
    @DataProvider(name = "checkoutDataProvider")
    public Object[][] getCheckoutData() {
        String jsonPath = "src/test/resources/data/checkoutData.json";
        JsonObject jsonObject = JsonReader.getJsonData(jsonPath);

        return new Object[][] {
                {
                        jsonObject.get("username").getAsString(),
                        jsonObject.get("password").getAsString(),
                        jsonObject.get("firstName").getAsString(),
                        jsonObject.get("lastName").getAsString(),
                        jsonObject.get("zipCode").getAsString()
                }
        };
    }

    @Test(dataProvider = "checkoutDataProvider")
    @Story("Complete checkout flow")
    @Severity(SeverityLevel.CRITICAL)
    public void checkoutFlow(String username, String password, String firstName, String lastName, String zipCode) {
        loginPage.login(username, password);
        assertions.assertLoginSuccess();
        assertions.assertProductsLoaded();

        productPage.addRandomProductToCart();
        productPage.gotToCart();
        assertions.assertCartNotEmpty();

        cartPage.checkout();

        checkoutPage.fillInformation(firstName, lastName, zipCode);

        assertions.assertOverviewTotals();

        overviewPage.finishCheckout();

        assertions.assertOrderComplete();
        assertions.assertBackHomeButton();
        completePage.clickBackToHomeButton();
    }
}
