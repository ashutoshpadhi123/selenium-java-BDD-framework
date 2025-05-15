@Login
Feature: Login

  Scenario: Successful login with valid credentials
    Given user launches the application
    When user logs in as "STANDARD_USER"
    Then user should be navigated to homepage
    And user sees the homepage

  Scenario: Unsuccessful login with locked out credentials
    Given user launches the application
    When user logs in as "LOCKED_OUT_USER"
    Then user sees the locked out banner