  Feature: Google Search Scenarios

  Scenario: Search Bahamas
    Given I Search text "Bahamas"
    When I write text and click search button
    Then I return result page and captured screen as "Bahamas.png"

  Scenario: Search Amsterdam
    Given I Search text "Amsterdam"
    When I write text and click search button
    Then I return result page and captured screen as "Amsterdam.png"