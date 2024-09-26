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
     */
    @Test
    public void exampleTest1() {
            executeTest(new Scenario("example scenario test"),new FeatureFile("example"),new Dictionary("UI"));
    }

    /**
     * Executes a test scenario with a specified feature file.
     */
    @Test
    public void exampleTest2() {
        executeTest(new Scenario("example scenario test"),new FeatureFile("example"));
    }

    /**
     * Executes a test scenario.
     */
    @Test
    public void exampleTest3() {
        executeTest(new Scenario("example scenario test"));
    }
}
