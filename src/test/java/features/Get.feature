Feature: Testing different Get requests

  @smoke
  Scenario: Get all
    Given user wants to get all information for "posts"
    Then response "200" should be received
    And user information should be received

  @smoke
  Scenario: Get specific
    Given user wants to get all information for "posts/1"
    Then response "200" should be received
    And response "title" should be "json"

  @smoke
  Scenario: Get with query parameter
    Given user wants to get information for "profile" having
      | id |
      | 1  |

    Then response "200" should be received
