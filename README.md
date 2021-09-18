# Web Test Automation

Automation cases:
1. Searching a city on Google search, validate the result page and
   take screenshot.

2. Searching a flight on [http://www.expedia.com](http://www.expedia.com/) between Brussels to New York
   with selecting child (with age) and adult passenger information, selecting
   flight date, validate the result page.

I have used Selenium WebDriver to automate user journeys and implemented used test scenarios with Cucumber Framework.
# Requirements

1. Java
2. Maven

## Run Test Cases

    mvn clean install
**Test Case 1.**

    mvn exec:java -Dexec.classpathScope=test -Dexec.mainClass=io.cucumber.core.cli.Main -Dexec.args=src/test/resources/features/Search.feature
**Test Case 2.**

    mvn exec:java -Dexec.classpathScope=test -Dexec.mainClass=io.cucumber.core.cli.Main -Dexec.args=src/test/resources/features/FlightAccommodation.feature

