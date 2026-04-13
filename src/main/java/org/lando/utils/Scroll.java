package org.lando.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
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
        scroll(direction, null);
    }

    public void scroll(String direction, By containerLocator) {

        int startX, startY, endY;

        if (containerLocator != null) {
            WebElement container = driver.findElement(containerLocator);

            Point location = container.getLocation();
            Dimension size = container.getSize();

            startX = location.getX() + size.getWidth() / 2;

            if (direction.equalsIgnoreCase("up")) {
                startY = location.getY() + (int)(size.getHeight() * 0.8);
                endY   = location.getY() + (int)(size.getHeight() * 0.2);
            } else {
                startY = location.getY() + (int)(size.getHeight() * 0.2);
                endY   = location.getY() + (int)(size.getHeight() * 0.8);
            }

        } else {
            Dimension dim = driver.manage().window().getSize();

            int width = dim.getWidth();
            int height = dim.getHeight();

            startX = width / 2;

            if (direction.equalsIgnoreCase("up")) {
                startY = (int) (height * 0.7);
                endY   = (int) (height * 0.3);
            } else {
                startY = (int) (height * 0.3);
                endY   = (int) (height * 0.7);
            }
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), startX, startY));

        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(new Pause(finger, Duration.ofMillis(200)));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(800),
                PointerInput.Origin.viewport(), startX, endY));

        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(swipe));
    }
}