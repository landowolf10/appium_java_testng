package org.lando.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class Scroll extends BasePage{
    public Scroll(AppiumDriver driver) {
        super(driver);
    }

    public void scrollUntilVisible(By elementLocator, String direction, int maxAttempts) {
        for (int i = 0; i < maxAttempts; i++) {

            if (elementExists(elementLocator, 2)) {
                return;
            }

            scroll(direction);
        }

        throw new RuntimeException("Element not found after scrolling: " + elementLocator);
    }

    public void scroll(String direction) {
        Dimension dim = driver.manage().window().getSize();

        int width = dim.getWidth();
        int height = dim.getHeight();

        int x = width / 2;

        int startY;
        int endY;

        if (direction.equalsIgnoreCase("up")) {
            startY = (int) (height * 0.7);
            endY = (int) (height * 0.3);
        } else {
            startY = (int) (height * 0.3);
            endY = (int) (height * 0.7);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), x, startY));

        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), x, endY));

        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(swipe));
    }
}