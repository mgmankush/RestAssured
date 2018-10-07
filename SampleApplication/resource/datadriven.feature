@Important
Feature: Data driven in Rest Assured test POST1

  Scenario Outline: Test data driven111
    Given user update name with "<name>" name
    When user update job with "<job>" name
    Then user post the request
    Then user validate the id is not null

    Examples: 
      | name    | job         |
      | Tom     | Manager     |
      | Jerry   | QA Engineer |
      | Charlin | Director    |
