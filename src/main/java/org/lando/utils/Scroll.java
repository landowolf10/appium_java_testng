package org.lando.utils;

import io.appium.java_client.AppiumDriver;
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

    public void scroll(String direction)
    {
        if (platformName().toString().equalsIgnoreCase("ios"))
        {
            final HashMap<String, String> scrollObject = new HashMap<>();
            scrollObject.put("direction", direction.toLowerCase());
            driver.executeScript("mobile:swipe", scrollObject);

            return;
        }

        Dimension dim = driver.manage().window().getSize();
        int x = dim.getWidth() / 2;
        int start = dim.getHeight() / 2 + 490;
        int end = dim.getHeight() / 2;

        Point source;
        Point target;

        if(direction.equalsIgnoreCase("up"))
        {
            source = new Point(x, start);
            target = new Point(x, end);
        }
        else
        {
            source = new Point(x, end);
            target = new Point(x, start);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), source.x, source.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),target.x, target.y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(swipe));
    }
}