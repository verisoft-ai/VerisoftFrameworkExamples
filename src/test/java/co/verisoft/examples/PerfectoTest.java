package co.verisoft.examples;

import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.selenium.drivers.factory.DriverUrl;
import co.verisoft.fw.perfecto.PerfectoLogExtension;
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
        perfectoOptions.put("securityToken", "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI2YTY0NDgyYi00YzA1LTQ1OTQtOWNhNS1hODViMDczNmU1NzkifQ.eyJpYXQiOjE2OTQwMDU2MTUsImp0aSI6ImU1ZTdiN2FjLWE5ZmUtNGYzNi1hZGY4LThkM2M3N2U5MWM0YSIsImlzcyI6Imh0dHBzOi8vYXV0aDcucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL2xldW1pcWEtcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoiaHR0cHM6Ly9hdXRoNy5wZXJmZWN0b21vYmlsZS5jb20vYXV0aC9yZWFsbXMvbGV1bWlxYS1wZXJmZWN0b21vYmlsZS1jb20iLCJzdWIiOiI4YmNjMWMxYS0xOTM1LTRhY2UtYmI3ZC1mOWM4OTAzNmEzMWUiLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJub25jZSI6ImVjNWY5YjVmLTMzMWUtNDVkNi04ZDNjLTQ1ZDg1OGM4OTFjYSIsInNlc3Npb25fc3RhdGUiOiIyYjY1ODgxMy02NmU1LTRjMjQtODRlNC1iMDRiNTkyOThjOTYiLCJzY29wZSI6Im9wZW5pZCBvZmZsaW5lX2FjY2VzcyBwcm9maWxlIGVtYWlsIn0.rhbxZY2czMtCjhswq7vkWvrCnJXK8rOkTYPg0sRB_9g");
        //"eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI2YTY0NDgyYi00YzA1LTQ1OTQtOWNhNS1hODViMDczNmU1NzkifQ.eyJpYXQiOjE2NzMxNzg3NjksImp0aSI6IjY5MmIyMzU3LTk3MzMtNDlkMi05ZDQxLTdhYmZjMWFiODI0YyIsImlzcyI6Imh0dHBzOi8vYXV0aDcucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL2xldW1pcWEtcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoiaHR0cHM6Ly9hdXRoNy5wZXJmZWN0b21vYmlsZS5jb20vYXV0aC9yZWFsbXMvbGV1bWlxYS1wZXJmZWN0b21vYmlsZS1jb20iLCJzdWIiOiJjZjllYjA4MC02M2FkLTRmZWEtOGQwZi0xZTY3NjA4MzI2NWEiLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJub25jZSI6ImYxMzM4MjA2LWMyN2ItNGVkMy04MTYzLWE1NDhlNDRjNTYxZiIsInNlc3Npb25fc3RhdGUiOiI3YWUyM2Y2Ni1kNDdmLTQ5YjEtYjQyNi03MDExY2E1N2ZjZjgiLCJzY29wZSI6Im9wZW5pZCBvZmZsaW5lX2FjY2VzcyBwcm9maWxlIGVtYWlsIn0.FwyDgRDzg3VOw3ZrC_WRnyAPcimVHcFKoE2yjbY8XgE");
        perfectoOptions.put("scriptName", "my script name2");
        perfectoOptions.put("app","PUBLIC:app-xl-leumi-TestingRelease.apk");
        perfectoOptions.put("manufacturer","Samsung");
        perfectoOptions.put("automationName","Appium");
        perfectoOptions.put("model","galaxy s21");
        perfectoOptions.put("waitForAvailableLicense",true);
        perfectoOptions.put("useVirtualDevice",true);
        perfectoOptions.put("automationVersion", "latest");
        perfectoOptions.put("autoDismissAlerts", true);
        perfectoOptions.put("autoGrantPermissions", true);
        perfectoOptions.put("platformVersion", "12");
        capabilities.setCapability("perfecto:options", perfectoOptions);
    }



    @DriverUrl
    private URL url = new URL("https://leumiqa.perfectomobile.com/nexperience/perfectomobile/wd/hub");

    //private URL url = new URL("https://pepper.perfectomobile.com/nexperience/perfectomobile/wd/hub/fast");

    public PerfectoTest() throws MalformedURLException {
    }

    @Test
    @Tag("sanity")
    @Tag("regression")
    public void test1(VerisoftDriver driver) {

//        String tag ="tag1";
//        String testName ="test1";
//        PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
//                .withWebDriver(driver)
//                .build();
//        ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
//        reportiumClient.testStart(testName, new TestContext(tag));
//        reportiumClient.stepStart("This is the 1st step");
//        reportiumClient.reportiumAssert("some assertion", true);
//        reportiumClient.stepEnd();
//        reportiumClient.testStop(TestResultFactory.createSuccess());
    }

    @Test
    @Tag("sanity")
    @Tag("regression")
    public void test2(VerisoftDriver driver) {
        //  ReportiumClient reportiumClient = StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("REPORTIUM");
        //reportiumClient.reportiumAssert("this is my reportium assert2", true);
       // StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("REPORTIUM",reportiumClient);
        Report.info("info message");
        Report.debug("debug message");
        Report.error("error message");
    }
}


