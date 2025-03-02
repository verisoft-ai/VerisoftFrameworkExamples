package co.verisoft.examples;
import co.verisoft.fw.cucumber.CucumberBaseTest;
import co.verisoft.fw.cucumber.Dictionary;
import co.verisoft.fw.cucumber.FeatureFile;
import co.verisoft.fw.cucumber.Scenario;
import co.verisoft.fw.extensions.jupiter.CucumberParamsIterationExtension;
import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.selenium.junit.extensions.DriverInjectionExtension;
import co.verisoft.fw.selenium.junit.extensions.ScreenShotExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import io.cucumber.core.runtime.CucumberParams;
import org.openqa.selenium.chrome.ChromeOptions;


@ExtentReport
@ExtendWith({CucumberParamsIterationExtension.class, DriverInjectionExtension.class, ScreenShotExtension.class})
public class CucumberParametrizedTest extends CucumberBaseTest {


    @DriverCapabilities
    ChromeOptions chromeOptions = new ChromeOptions();


    @ParameterizedTest
    @CucumberParams(filePath = "src/test/resources/ScenarioExample.csv")
    public void cucumberParameterizedTest(String username, String password, String price) {
        executeTest(new Scenario("example scenario  that use with parametrized cucumber params"),
                new FeatureFile("exampleCucumber"),
                new Dictionary("UI"));
    }

    @ParameterizedTest
    @CucumberParams(filePath = "src/test/resources/ScenarioExample.csv")
    public void cucumberParameterizedTestWithDriverInjection(String username, String password, String price, VerisoftDriver driver) {
        executeTest(new Scenario("example scenario  that use with parametrized cucumber params"),
                new FeatureFile("exampleCucumber"),
                new Dictionary("UI"));
    }
}
