Feature: Put operations

  @smoke
  Scenario: Put data to profile
    Given user performs put operation for "profile/3"
      | id | name |
      | 3  | eee1 |

    Then put response body should have "eee1"