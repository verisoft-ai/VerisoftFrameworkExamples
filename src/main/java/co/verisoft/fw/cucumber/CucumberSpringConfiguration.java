package co.verisoft.fw.cucumber;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

/**
 * CucumberSpringConfiguration class sets up Spring context configuration for Cucumber tests.
 **/
@CucumberContextConfiguration
@ContextConfiguration(classes = CucumberSpringConfig.class)
public class CucumberSpringConfiguration {}