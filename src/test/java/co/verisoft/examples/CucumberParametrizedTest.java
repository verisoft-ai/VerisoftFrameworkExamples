package co.verisoft.examples;
import co.verisoft.fw.cucumber.CucumberBaseTest;
import co.verisoft.fw.cucumber.Dictionary;
import co.verisoft.fw.cucumber.FeatureFile;
import co.verisoft.fw.cucumber.Scenario;
import co.verisoft.fw.extentreport.ExtentReport;
import org.junit.jupiter.params.ParameterizedTest;
import io.cucumber.core.runtime.CucumberParams;


@ExtentReport
public class CucumberParametrizedTest extends CucumberBaseTest {

    @ParameterizedTest
    @CucumberParams(filePath = "src/test/resources/SenarioExample.csv")
    public void example1(String username,String password,String age) {
        executeTest(new Scenario("example of UI scenario test"),
                new FeatureFile("exampleCucumber"),
                new Dictionary("UI"));
    }
}
