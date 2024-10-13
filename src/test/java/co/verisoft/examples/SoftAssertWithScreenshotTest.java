package co.verisoft.examples;
import co.verisoft.fw.CustomReportPortalExtension;
import co.verisoft.fw.asserts.SoftAssertsScreenShot;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.selenium.drivers.factory.DriverUrl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

@ExtendWith({CustomReportPortalExtension.class})
public class SoftAssertWithScreenshotTest extends BaseTest{
    /**
     If no specific capabilities are defined, the driver will use this capabilities
     */
    @DriverCapabilities
    EdgeOptions options = new EdgeOptions();
    {
        options.addArguments("--headless");
    }
    /**
     If no specific URL are defined, the driver will use this URL
     */
    @DriverUrl
    String url="http://1.2.3.4:4444/wd/hub/";


    @DriverCapabilities
    private ChromeOptions capabilities = new ChromeOptions();
    @Test
    public void AssertWithScreenshot(VerisoftDriver driver){
            SoftAssertsScreenShot softAssertsScreenShot = new SoftAssertsScreenShot(driver);

            boolean condition1 = false;
            boolean condition2 = false;

            driver.get("https://google.com/");
            softAssertsScreenShot.assertTrue(condition1 ,"fail");
            driver.get("https://translate.google.com/");
            softAssertsScreenShot.assertTrue(condition2 ,"fail");
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
     * @param edgeDriver VerisoftDriver instance configured with Edge capabilities.
     */
    @Test
    public void twoDriversWithOnlyBeanCapabilities(
            @DriverCapabilities("chrome")  VerisoftDriver chromeDriver,
            @DriverCapabilities("edge") VerisoftDriver edgeDriver) {
        SoftAssertsScreenShot softAssertsScreenShot = new SoftAssertsScreenShot(chromeDriver);
        chromeDriver.get("http://www.google.com");
        softAssertsScreenShot.assertTrue(false,"the chrome driver");
        edgeDriver.get("http://www.google.com");
        softAssertsScreenShot.setDriver(edgeDriver);
        softAssertsScreenShot.assertTrue(false,"the edge driver");
        softAssertsScreenShot.assertAll();
    }

}