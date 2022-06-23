Feature: Delete requests

  Scenario: Delete an item from profile
    Given user performs delete operation for "profile/3"
    Then response code for delete operation should be 200