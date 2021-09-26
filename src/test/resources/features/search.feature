Feature: Search JP

  @sunny @closeBrowser
  Scenario: valid search
    When user search for "J. P. Morgan"
    And click on first link
    Then company logo should be shown