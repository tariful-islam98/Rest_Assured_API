Feature: Post operations

  @smoke
  Scenario: Post data to profile
    Given user performs post operation for "profile"
      | id | name |
      | 3  | eee  |

    Then response body should have "eee"