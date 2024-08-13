Feature: User Login

  Scenario Outline: Successful login for different user roles
    Given the user is on the login page
    When the user enters "<username>" and "<password>"
    And the user clicks the login button
    Then the user should be redirected to the "<dashboard>"

    Examples:
      | username    | password    | dashboard   |
      | admin       | adminPass   | admin       |
      | storeOwner  | storePass   | store_owner |
      | supplier    | supplierPass| supplier    |
      | user        | userPass    | user        |

  Scenario Outline: Unsuccessful login for different user roles
    Given the user is on the login page
    When the user enters "<username>" and "<password>"
    And the user clicks the login button
    Then an error message should be displayed

    Examples:
      | username    | password    |
      | admin       | wrongPass   |
      | storeOwner  | wrongPass   |
      | supplier    | wrongPass   |
      | user        | wrongPass   |
