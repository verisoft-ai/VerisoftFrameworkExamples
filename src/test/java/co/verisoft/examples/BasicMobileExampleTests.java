package co.verisoft.examples;

import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.selenium.drivers.VerisoftMobileDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.selenium.drivers.factory.DriverUrl;
import co.verisoft.fw.utils.Asserts;
import co.verisoft.fw.utils.Waits;
import co.verisoft.mobile.pageobjects.MobileChromeTurnOnSyncPage;
import co.verisoft.mobile.pageobjects.MobileChromeWelcomePage;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

@Execution(ExecutionMode.SAME_THREAD)
public class BasicMobileExampleTests extends BaseTest {

    @DriverUrl
    private URL url = new URL("http://127.0.0.1:4723/wd/hub/");

    @DriverCapabilities
    private DesiredCapabilities capabilities = new DesiredCapabilities();

    {
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability("appPackage", "com.android.chrome");
        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        capabilities.setCapability("automationName", "UIAutomator2");
    }


    public BasicMobileExampleTests() throws MalformedURLException {
    }


    @Test
    @DisplayName("Click On Element")
    public void clickOnElement(VerisoftMobileDriver driver) {

        driver.findElement(By.id("com.android.chrome:id/terms_accept")).click();
        WebElement e = Waits.visibilityOfElementLocated(driver, 30, By.id("com.android.chrome:id/negative_button"));

        // Note!! Verisoft Assert
        String phrase = "No thanks";
        Asserts.assertTrue(e.getText().contains(phrase), "Page should contain the pharase: " + phrase);

        Report.info("Got to this point - We are operating Google chrome on mobile deviceS");
    }


    @Test
    @DisplayName("Click On Element with Page Object")
    public void clickOnElementWithPageObject(VerisoftMobileDriver driver) {

        MobileChromeWelcomePage welcomePage = new MobileChromeWelcomePage(driver);
        MobileChromeTurnOnSyncPage turnOnSyncPage = welcomePage.accept();

        // Note!! Verisoft Assert
        Asserts.assertTrue(turnOnSyncPage.isOnPage(), "Should be on page");

        Report.info("Got to this point - We are operating Google chrome on mobile deviceS");
    }
}
