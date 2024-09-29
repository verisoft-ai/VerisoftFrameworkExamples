package co.verisoft.fw.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Test;
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


 @Given("User found on page {string}")
 public void foundOnPage(String action) {extendedActions.foundOnPage(action);}

 @When("User perform action {string}")
 public void performAction(String action) {
  extendedActions.performAction(action);
 }

 @Then("User expect to receive result of {string}")
 public void receivingResult(String action) {extendedActions.receivingResult(action);}

}
