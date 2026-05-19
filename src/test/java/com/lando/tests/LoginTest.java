package com.lando.tests;

import com.google.gson.JsonObject;
import io.qameta.allure.*;
import org.lando.utils.JsonReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Login")
@Feature("Login feature")
public class LoginTest extends BaseTest {
    @DataProvider(name = "loginDataProvider")
    public Object[][] getCheckoutData() {
        String jsonPath = "src/test/resources/data/checkoutData.json";
        JsonObject jsonObject = JsonReader.getJsonData(jsonPath);

        return new Object[][] {
                {
                        jsonObject.get("username").getAsString(),
                        jsonObject.get("password").getAsString()
                }
        };
    }

    @DataProvider(name = "invalidLoginDataProvider")
    public Object[][] getInvalidLoginData() {
        String jsonPath = "src/test/resources/data/checkoutData.json";
        JsonObject jsonObject = JsonReader.getJsonData(jsonPath);

        return new Object[][] {
                {
                        jsonObject.get("invalid_username").getAsString(),
                        jsonObject.get("invalid_password").getAsString(),
                        jsonObject.get("error_message").getAsString()
                }
        };
    }

    @Test(dataProvider = "loginDataProvider", priority = 1)
    @Story("Complete login flow")
    @Severity(SeverityLevel.CRITICAL)
    public void successfulLoginFlow(String username, String password) {
        loginPage.login(username, password);
        assertions.assertLoginSuccess();
        assertions.assertProductsLoaded();
    }

    @Test(dataProvider = "invalidLoginDataProvider", priority = 2)
    @Story("Login flow with invalid credentials")
    @Description("Verify that the system denies access when incorrect credentials are provided and displays the native error message.")
    @Severity(SeverityLevel.NORMAL)
    public void invalidLoginFlow(String invalidUser, String invalidPassword, String errorMessage) {
        loginPage.login(invalidUser, invalidPassword);

        assertions.assertLoginErrorMessage(errorMessage);
    }
}
