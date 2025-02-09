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

    /**
     *
     * @param chromeDriver  initialized by bean-defined capabilities in CapabiitiesInjection.class, the url is the default- the field with the annotation @DriverUrl
     * @param edgeDriver  initialized by bean-defined capabilities in CapabiitiesInjection.class, the url is the default- the field with the annotation @DriverUrl
     */
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
    /**
     *
     * @param chromeDriver  initialized capabilities specified in a JSON configuration (my_capabilities.json). the url is the default- the field with the annotation @DriverUrl
     * @param edgeDriver   initialized capabilities specified in a JSON configuration (my_capabilities.json), the url is the default- the field with the annotation @DriverUrl
     */
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

    /**
     *
     * @param chromeDriver  initialized capabilities specified in a JSON configuration (my_capabilities.json). the url is the default- the field with the annotation @DriverUrl
     * @param edgeDriver  initialized by bean-defined capabilities in CapabiitiesInjection.class, the url is the default- the field with the annotation @DriverUrl
     */
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

    /**
     *
     * @param chromeDriver  with driver name for  extracting it from VerisoftDriverManager
     * @param edgeDriver  with driver name for extracting it from VerisoftDriverManager to edgedriver1
     */
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

    /**
     *
     * @param chromeDriver initialized by bean-defined capabilities and URL in CapabiitiesInjection.class.
     */
    @Test
    public void oneDriverWithCapabilitiesAndURLBean(
            @DriverCapabilities("chrome")  @DriverUrl("grid") VerisoftDriver chromeDriver) {
        chromeDriver.get("http://www.google.com");
        String title = chromeDriver.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in chrome");
    }


    /**
     *
     * @param chromeDriver initialized by bean-defined capabilities in CapabiitiesInjection.class. The url is hard-coded in the annotation
     */
    @Test
    public void oneDriverWithCapabilitiesAndURLHardCoded(
            @DriverCapabilities("chrome")  @DriverUrl("http://1.2.3.1:4444/wd/hub/") VerisoftDriver chromeDriver) {
        chromeDriver.get("http://www.google.com");
        String title = chromeDriver.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in chrome");
    }

    /**
     *
     * @param chromeDriver initialized by bean-defined capabilities in CapabiitiesInjection.class. the url is the default- the field with the annotation @DriverUrl
     */
    @Test
    public void oneDriverWithCapabilitiesAndURLField(
            @DriverCapabilities("chrome") VerisoftDriver chromeDriver)
    {
        chromeDriver.get("http://www.google.com");
        String title = chromeDriver.getTitle();
        Asserts.assertEquals("Google", title, "We are in Google homepage in chrome");
    }

    /**
     *
     * @param mobileDriver  initialized capabilities specified in a JSON configuration (my_capabilities.json). The url is hard-coded in the annotation
     */
    @Test
    public void oneMobileDriverWithCapabilitiesAndURL(
            @DriverCapabilities("mobileJson") @DriverUrl("http://127.0.0.1:4723/wd/hub/") VerisoftMobileDriver mobileDriver)
    {
        mobileDriver.findElement(By.id("SOME_ID")).click();
    }
    /**
     *
     * @param mobileDriver  initialized capabilities specified in a JSON configuration (my_capabilities.json).
     *                     The command executor is initialized by the proxy netty client factory like this:
     *                     AppiumCommandExecutor(MobileCommand.commandRepository, new URL("<http://127.0.0.1:4723/wd/hub/>"), new ProxyNettyClient.Factory())
     */
    @Test
    public void oneDriverWithCapabilitiesAndProxyCommandExecutor(
            @DriverCapabilities("mobileJson") @DriverCommandExecutor("org.openqa.selenium.remote.http.netty.ProxyNettyClient$Factory") VerisoftMobileDriver mobileDriver)
    {
        mobileDriver.findElement(By.id("SOME_ID")).click();
    }
    /**
     * @param mobileDriver initialized by bean-defined capabilities and URL in CapabiitiesInjection.class.
     */
    @Test
    public void oneDriverWithCapabilitiesAndProxyCommandExecutorBean(
            @DriverCapabilities("mobileJson") @DriverCommandExecutor("commandExecutor") VerisoftMobileDriver mobileDriver)
    {
        mobileDriver.findElement(By.id("SOME_ID")).click();
    }

}




