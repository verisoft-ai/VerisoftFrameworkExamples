package co.verisoft.examples;

import co.verisoft.fw.cucumber.*;
import co.verisoft.fw.extentreport.ExtentReport;

import org.junit.jupiter.api.Test;

/**
 * ExampleCucumberTest class provides example test cases using the Cucumber framework.
 * And try different types of runs.
 */
@ExtentReport
public class ExampleCucumberTest extends CucumberBaseTest {

    /**
    * The defaults values for the scenario:
    * package - co.verisoft.fw.cucumber
    */
    @Test
    public void exampleTest1() { executeTest(new Scenario("example of UI scenario test"),new FeatureFile("exampleCucumber"),new Dictionary("UI"));}

    /**
     * The defaults values for the scenario:
     * package - co.verisoft.fw.cucumber
     * dictionary - UI (from application.properties)
     */
    @Test
    public void exampleTest2() {
        executeTest(new Scenario("example of UI scenario test"),new FeatureFile("exampleCucumber"));
    }

    /**
     * The defaults values for the scenario:
     * package - co.verisoft.fw.cucumber
     * dictionary - UI (from application.properties)
     * featureFile- examplecucumber (extracted from file name)
     */
    @Test
    public void exampleTest3() {
        executeTest(new Scenario("example scenario that use with mix of dictionaries test"));
    }

    /**
     * The defaults values for the scenario:
     * package - co.verisoft.fw.cucumber
     */
    @Test
    public void exampleTest4() { executeTest(new Scenario("example scenario API test"),new FeatureFile("exampleCucumber"), new Dictionary("API"));}
}
