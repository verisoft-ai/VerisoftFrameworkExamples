package co.verisoft.examples;

import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import io.visual_regression_tracker.sdk_java.VisualRegressionTracker;
import io.visual_regression_tracker.sdk_java.VisualRegressionTrackerConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

/**
 * Test example for VRT
 * @author Nir Gallner
 * @since Sep 2021
 * @url <a href="https://github.com/Visual-Regression-Tracker/sdk-java/blob/master/README.md">...</a>
 */
public class VrtExampleTests extends BaseTest{

    @DriverCapabilities
    private ChromeOptions capabilities = new ChromeOptions();


    @Test
    @DisplayName("Wikipedia main page visual test")
    public void test1(VerisoftDriver driver) throws IOException, InterruptedException {

        // VRT example
        VisualRegressionTrackerConfig config = new VisualRegressionTrackerConfig(
                "http://localhost:4200",
                "YT2ZC8..................73FR",
                "81......-......-.....-.....-.......42e",
                "master",
                "commit_sha",
                false,
                15
        );

        // Create VRT instance
        VisualRegressionTracker visualRegressionTracker = new VisualRegressionTracker(config);

        // Start VRT
        visualRegressionTracker.start();

        // Selenium example
        driver.get("https://www.wikipedia.org/");
        String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

        // Track screenshot
        visualRegressionTracker.track(
                "Name for test",
                screenshotBase64
        );
    }
}
