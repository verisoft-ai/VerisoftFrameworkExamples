package co.verisoft.examples;
import co.verisoft.examples.dependencyinjection.CapabiitiesInjection;
import co.verisoft.fw.asserts.Asserts;
import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.VerisoftDriverManager;
import co.verisoft.fw.selenium.drivers.VerisoftMobileDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.selenium.drivers.factory.DriverCommandExecutor;
import co.verisoft.fw.selenium.drivers.factory.DriverName;
import co.verisoft.fw.selenium.drivers.factory.DriverUrl;
import co.verisoft.fw.selenium.junit.extensions.DriverInjectionExtension;
import co.verisoft.fw.selenium.junit.extensions.ScreenShotExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtentReport
@ExtendWith({SpringExtension.class,DriverInjectionExtension.class, ScreenShotExtension.class})
@ContextConfiguration(classes = {CapabiitiesInjection.class})
public class MultipleDriversTests {

    @DriverCapabilities
    EdgeOptions options = new EdgeOptions();
    {
        options.addArguments("--headless");
    }
    @DriverUrl
    String url="http://95.216.217.133:4444/wd/hub/";

    @Test
    public void twoDriversWithOnlyBeanCapabilities(
            @DriverCapabilities("chrome")  VerisoftDriver chromeDriver,
            @DriverCapabilities("edge") VerisoftDriver edgeDriver) {
        chromeDriver.get("http://www.google.com");
        String title = chromeDriver.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in chrome");
        edgeDriver.get("http://www.google.com");
        title = edgeDriver.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in edge");

    }
    @Test
    public void clearCache()
    {
        WebDriverManager.chromedriver().clearDriverCache();    }

    @Test
    public void twoDriversWithOnlyJsonCapabilities(
            @DriverCapabilities("chromeJson")  VerisoftDriver chromeDriver,
            @DriverCapabilities("edgeJson") VerisoftDriver edgeDriver) {
        chromeDriver.get("http://www.google.com");
        String title = chromeDriver.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in chrome");
        edgeDriver.get("http://www.google.com");
        title = edgeDriver.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in edge");

    }

    @Test
    public void twoDriversWithBeanAndJsonCapabilities(
            @DriverCapabilities("chromeJson")  VerisoftDriver chromeDriver,
            @DriverCapabilities("edge") VerisoftDriver edgeDriver) {
        chromeDriver.get("http://www.google.com");
        String title = chromeDriver.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in chrome");
        edgeDriver.get("http://www.google.com");
        title = edgeDriver.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in edge");

    }

    @Test
    public void twoDriversWithOnlyOneCapabilitiesWithDriversNames(
           @DriverName("chrome bean") @DriverCapabilities("chrome")  VerisoftDriver chromeDriver,
           @DriverName("edge field")  VerisoftDriver edgeDriver) {
        chromeDriver.get("http://www.google.com");
        String title = chromeDriver.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in chrome");
        WebDriver edgeDriver1=VerisoftDriverManager.getDriver("edge field");
        edgeDriver1.get("http://www.google.com");
        title = edgeDriver1.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in edge");

    }

    @Test
    public void oneDriverWithCapabilitiesAndURLBean(
            @DriverCapabilities("chrome")  @DriverUrl("grid1") VerisoftDriver chromeDriver) {
        chromeDriver.get("http://www.google.com");
        String title = chromeDriver.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in chrome");
    }

    @Test
    public void oneDriverWithCapabilitiesAndURLHardCoded(
            @DriverCapabilities("chrome")  @DriverUrl("http://95.216.217.133:4444/wd/hub/") VerisoftDriver chromeDriver) {
        chromeDriver.get("http://www.google.com");
        String title = chromeDriver.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in chrome");
    }

    @Test
    public void oneDriverWithCapabilitiesAndURLField(
            @DriverCapabilities("chrome") VerisoftDriver chromeDriver)
    {
        chromeDriver.get("http://www.google.com");
        String title = chromeDriver.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in chrome");
    }

    @Test
    public void oneMobileDriverWithCapabilitiesAndURL(
            @DriverCapabilities("mobileJson") @DriverUrl("http://127.0.0.1:4723/wd/hub/") VerisoftMobileDriver leumiDriver)
    {
        leumiDriver.findElement(By.id("SOME_ID")).click();
    }

    @Test
    public void oneDriverWithCapabilitiesAndProxyCommandExecutor(
            @DriverCapabilities("mobileJson") @DriverCommandExecutor("org.openqa.selenium.remote.http.netty.ProxyNettyClient$Factory") VerisoftMobileDriver chromeDriver)
    {
        chromeDriver.findElement(By.id("SOME_ID")).click();
    }

    @Test
    public void oneDriverWithCapabilitiesAndProxyCommandExecutorBean(
            @DriverCapabilities("mobileJson") @DriverCommandExecutor("commandExecutor") VerisoftMobileDriver chromeDriver)
    {
        chromeDriver.findElement(By.id("SOME_ID")).click();
    }

}




