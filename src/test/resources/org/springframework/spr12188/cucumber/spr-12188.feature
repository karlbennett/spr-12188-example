Feature: Exception handling accept behaviour

  Scenario: I can do a successful request.
    When I attempt a successful request
    Then I should receive a status code of 200
    And the response body should contain a "success" message of "true"

  Scenario: I attempt a successful request with an invalid accept type and get a not acceptable response.
    Given I have an "Accept" header of "un/supported"
    When I attempt a successful request
    Then I should receive a status code of 406

  Scenario: I can do a failed request.
    When I attempt a failed request
    Then I should receive a status code of 410
    And the response body should contain a "success" message of "false"

  Scenario: I attempt a failed request with an invalid accept type and get a not acceptable response.
    Given I have an "Accept" header of "un/supported"
    When I attempt a failed request
    Then I should receive a status code of 406