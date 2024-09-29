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
     * Executes a test scenario with a specified feature file and dictionary.
     *            The default -
     *                      package - co/verisoft/fw/cucumber
     */
    @Test
    public void exampleTest1() { executeTest(new Scenario("example of UI scenario test"),new FeatureFile("exampleCucumber"),new Dictionary("UI"));}

    /**
     * Executes a test scenario with a specified feature file.
     *      The default -
     *                dictionary - UI,
     *                package - co/verisoft/fw/cucumber
     */
    @Test
    public void exampleTest2() {
        executeTest(new Scenario("example of UI scenario test"),new FeatureFile("exampleCucumber"));
    }

    /**
     * Executes a test scenario.
     * The default -
     *          featureFile - exampleCucumber,
     *          dictionary - UI,
     *          package - co/verisoft/fw/cucumber
     */
    @Test
    public void exampleTest3() {
        executeTest(new Scenario("example scenario  that use with mix of dictionaries test"));
    }

    /**
     * Executes a test scenario.
     * The default -
     *               package - co/verisoft/fw/cucumber
     */
    @Test
    public void exampleTest4() { executeTest(new Scenario("example scenario API test"),new FeatureFile("exampleCucumber"), new Dictionary("API"));}
}
