package co.verisoft.proxy;

import co.verisoft.examples.BaseTest;
import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.selenium.drivers.VerisoftMobileDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.selenium.drivers.factory.DriverCommandExecutor;
import co.verisoft.fw.utils.Asserts;
import co.verisoft.fw.utils.Waits;
import io.appium.java_client.MobileCommand;
import io.appium.java_client.remote.AppiumCommandExecutor;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.netty.ProxyNettyClient;

import java.net.MalformedURLException;
import java.net.URL;

@ExtentReport
public class WorkingWithProxy extends BaseTest {

    @DriverCapabilities
    private DesiredCapabilities capabilities = new DesiredCapabilities();
    {
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability("appPackage", "com.android.chrome");
        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        capabilities.setCapability("automationName", "UIAutomator2");
    }

    @DriverCommandExecutor
    private HttpCommandExecutor commandExecutor = new AppiumCommandExecutor(
            MobileCommand.commandRepository, new URL("http://127.0.0.1:4723/wd/hub/"), new ProxyNettyClient.Factory());

    @BeforeAll
    public static void start(){
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "8888");
    }

    public WorkingWithProxy() throws MalformedURLException {
    }


    @Test
    @DisplayName("Working with proxy example")
    public void testWithProxy(VerisoftMobileDriver driver){
        driver.findElement(By.id("com.android.chrome:id/terms_accept")).click();
        WebElement e = Waits.visibilityOfElementLocated(driver, 30, By.id("com.android.chrome:id/negative_button"));

        // Note!! Verisoft Assert
        String phrase = "No thanks";
        Asserts.assertTrue(e.getText().contains(phrase), "Page should contain the pharase: " + phrase);

        Report.info("Got to this point - We are operating Google chrome on mobile deviceS");
    }
}
