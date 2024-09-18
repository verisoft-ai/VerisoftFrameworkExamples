package co.verisoft.examples;
import co.verisoft.fw.CustomReportPortalExtension;
import co.verisoft.fw.asserts.SoftAssertsScreenShot;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;

@ExtendWith({CustomReportPortalExtension.class})
public class SoftAssertWithScreenshotTest extends BaseTest{
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
}