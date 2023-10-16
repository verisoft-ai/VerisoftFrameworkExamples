package co.verisoft.examples;

import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.selenium.drivers.factory.DriverUrl;

import co.verisoft.fw.selenium.junit.extensions.PerfectoLogExtension;
import co.verisoft.fw.store.StoreManager;
import co.verisoft.fw.store.StoreType;
import com.perfecto.reportium.client.ReportiumClient;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({PerfectoLogExtension.class})
public class PerfectoTest extends BaseTest {

    @DriverCapabilities

    UiAutomator2Options capabilities = new UiAutomator2Options();

    {
        Map<String, Object> perfectoOptions = new HashMap<>();
        capabilities.setCapability("platformName", "android");
        perfectoOptions.put("securityToken", "ey...x");
        perfectoOptions.put("scriptName", "my script name2");
        perfectoOptions.put("app", "PUBLIC:app-xl-leumi-TestingRelease.apk");
        perfectoOptions.put("manufacturer", "Samsung");
        perfectoOptions.put("automationName", "Appium");
        perfectoOptions.put("model", "galaxy s21");
        perfectoOptions.put("waitForAvailableLicense", true);
        perfectoOptions.put("useVirtualDevice", true);
        perfectoOptions.put("automationVersion", "latest");
        perfectoOptions.put("autoDismissAlerts", true);
        perfectoOptions.put("autoGrantPermissions", true);
        perfectoOptions.put("platformVersion", "12");
        capabilities.setCapability("perfecto:options", perfectoOptions);
    }


    @DriverUrl
    private URL url = new URL("https://link.to.perfecto");


    public PerfectoTest() throws MalformedURLException {
    }


    @Test
    @Tag("sanity")
    @Tag("regression")
    public void tryReportToPerfecto(VerisoftDriver driver) {
        Report.info("info message");
        Report.debug("debug message");
        Report.error("error message");
    }
}


