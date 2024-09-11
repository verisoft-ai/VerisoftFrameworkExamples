package co.verisoft.examples;
import co.verisoft.fw.CustomReportPortalExtension;
import co.verisoft.fw.asserts.SoftAssertsScreenShot;
import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

@ExtendWith({CustomReportPortalExtension.class})
public class SoftAssertWithScreenshotTest extends BaseTest {

    @DriverCapabilities
    private ChromeOptions capabilities = new ChromeOptions();
    @Test
    public void AssertWithScreenshot(VerisoftDriver driver){
        SoftAssertsScreenShot softAssertsScreenShot = new SoftAssertsScreenShot(driver);
        driver.get("https://google.com/");
        softAssertsScreenShot.assertTrue(false,"fail");
        softAssertsScreenShot.assertTrue(true,"pass");
        driver.get("https://bitbucket.org/nir_gallner/verisoftframeworkexamples/src/master/");
        softAssertsScreenShot.assertTrue(false,"fail");
        Report.info("Add file",new File("src/test/resources/screenush.png"));
        softAssertsScreenShot.assertAll();
    }

}
