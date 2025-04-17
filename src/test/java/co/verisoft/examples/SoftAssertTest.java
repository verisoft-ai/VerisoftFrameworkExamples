package co.verisoft.examples;

import co.verisoft.examples.dependencyinjection.CapabiitiesInjection;
import co.verisoft.fw.CustomReportPortalExtension;
import co.verisoft.fw.asserts.SoftAsserts;
import co.verisoft.fw.asserts.SoftAssertsScreenShot;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.test.context.ContextConfiguration;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({CustomReportPortalExtension.class})
@ContextConfiguration(classes = {CapabiitiesInjection.class})
public class SoftAssertTest extends BaseTest {

    /**
     * If no specific capabilities are defined, the driver will use this capabilities
     */
    @DriverCapabilities
    EdgeOptions options = new EdgeOptions();

    {
        options.addArguments("--headless");
    }


    @DriverCapabilities
    private ChromeOptions capabilities = new ChromeOptions();


    @Test
    public void shouldSoftAssert(VerisoftDriver driver) {

        SoftAsserts softAsserts = new SoftAsserts();

        driver.get("https://google.com/");
        softAsserts.assertTrue(false, "should fail");

        driver.get("https://translate.google.com/");
        softAsserts.assertTrue(true, "should pass");

        softAsserts.assertAll();
    }


    @Test
    public void shouldFailAndShowTwoScreenshotsInReport(VerisoftDriver driver) {
        SoftAssertsScreenShot softAssertsScreenShot = new SoftAssertsScreenShot(driver);

        driver.get("https://google.com/");
        softAssertsScreenShot.assertTrue(false, "should fail");

        driver.get("https://translate.google.com/");
        softAssertsScreenShot.assertTrue(true, "should pass");

        softAssertsScreenShot.assertAll();
    }

    /**
     * Test method utilizing multiple VerisoftDriver instances with specific capabilities.
     * This test function demonstrates the usage of multiple drivers, specifically Chrome and Edge,
     * by leveraging the capabilities defined by Spring Beans. Error handling is integrated
     * using the SoftAssertsScreenShot mechanism, which manages assertions and driver states
     * by utilizing the current driver set. It showcases how to handle errors and take screenshots
     * for both Chrome and Edge drivers during testing.
     *
     * @param chromeDriver VerisoftDriver instance configured with Chrome capabilities.
     * @param edgeDriver   VerisoftDriver instance configured with Edge capabilities.
     */
    @Test
    public void twoDriversWithOnlyBeanCapabilities(
            @DriverCapabilities("chrome") VerisoftDriver chromeDriver,
            @DriverCapabilities("edge") VerisoftDriver edgeDriver) {

        SoftAssertsScreenShot softAssertsScreenShot = new SoftAssertsScreenShot(chromeDriver);

        chromeDriver.get("http://www.google.com");
        softAssertsScreenShot.assertTrue(false, "the chrome driver");

        edgeDriver.get("http://www.google.com");
        softAssertsScreenShot.setDriver(edgeDriver);

        softAssertsScreenShot.assertTrue(false, "the edge driver");
        softAssertsScreenShot.assertAll();
    }


    @Test
    public void twoSoftAssertsObjects(
            @DriverCapabilities("chrome") VerisoftDriver chromeDriver,
            @DriverCapabilities("edge") VerisoftDriver edgeDriver) {

        SoftAssertsScreenShot softAssertsScreenShotChrome = new SoftAssertsScreenShot(chromeDriver);
        SoftAssertsScreenShot softAssertsScreenShotEdge = new SoftAssertsScreenShot(edgeDriver);

        chromeDriver.get("http://www.google.com");
        softAssertsScreenShotChrome.assertTrue(false, "the chrome driver");

        edgeDriver.get("http://www.google.com");
        softAssertsScreenShotEdge.assertTrue(false, "the edge driver");

        Assertions.assertAll(softAssertsScreenShotChrome::assertAll
                , softAssertsScreenShotEdge::assertAll);
    }
}