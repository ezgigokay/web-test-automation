Feature: Expedia Flight Accommodation Scenario

  Scenario: Flight Accommodation from Brussels to New York
    Given I navigate to the Expedia website
    When I am looking for a flight ticket from "Brussels" to "New York" departing for 1 adult and "3" years old 1 child
    Then The result page contains travel option for the chosen destination