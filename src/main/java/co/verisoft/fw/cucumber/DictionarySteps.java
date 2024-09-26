package co.verisoft.fw.cucumber;

import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * DictionarySteps class implements the ExtendedActions dictionary and provides step definitions for Cucumber tests.
 */
public class DictionarySteps implements ExtendedActions {

private final ExtendedActions extendedActions;

 @Autowired
public DictionarySteps(ExtendedActions extendedActions) {
 this.extendedActions = extendedActions;
}

 @When("I perform an extended action {string}")
public void performAction(String action) {
 extendedActions.performAction(action);
}}
