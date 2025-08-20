Feature: Google Search with Playwright

  Scenario: Search OpenAI on Google
    Given I am on Google home page
    When I search for "OpenAI"
    Then results should be shown with the query "OpenAI"
