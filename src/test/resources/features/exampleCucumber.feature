Feature: exampleCucumber

  #example of UI scenario test
  Scenario: example of UI scenario test
    Given user on "login" page
    When user taps on "login" button
    Then user should see the title "Welcome Home"

  #example of API scenario test
  Scenario: example scenario API test
    Given User has initialized the SSL and certificates at "crt1.pfx" with password "123456"
    When User sets the following headers
      | X-Message-ID | {{$guid}} |
      | X-Transaction-ID | {{$guid}} |
      | X-Trace-ID | {{$guid}} |
      | X-Channel-ID | 1250 |
    Then User should receive a response with status code 200

  #example of scenario test that use with specific dictionary implementation
  Scenario: example scenario specific test
    Given User found on page "{string}"
    When User perform action "{string}"
    Then User expect to receive result of "{string}"

  #example of scenario test that use with mix of dictionaries
  Scenario: example scenario  that use with mix of dictionaries test
    #spesific dictionary
    Given User found on page "login"
    #UI dictionary
    When user taps on "login" button
    #spesific dictionary
    When User perform action "type the user name"
    #spesific dictionary
    When User perform action "type the password"
    #UI dictionary
    When user taps on "OK" button
    #UI dictionary
    Then user should see the title "Welcome Home"

  #example of scenario test that use with parametrized cucumber params
  Scenario: example scenario  that use with parametrized cucumber params
    When user report the message "<username>"
    When user report the message "<password>"
    When user report the message "<price>"
