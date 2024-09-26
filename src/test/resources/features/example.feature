Feature: exampleScenario

  Scenario: example scenario test
    Given user on "login" page
    When user taps on "login" button
    Then user should see the title "Welcome Home"