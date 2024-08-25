Feature: Add User

  Scenario: Successfully add a user
    Given the user enters "JohnDoe" as username
    And the user enters "john@example.com" as email
    And the user selects "Admin" as role
    When the user clicks on "Add User" button
    Then a success message "User added successfully." should be displayed

  Scenario: Adding a user with an empty username
    Given the user enters "" as username
    And the user enters "john@example.com" as email
    And the user selects "Admin" as role
    When the user clicks on "Add User" button
    Then an error message "Error adding user." should be displayed

  Scenario: Adding a user with an invalid email
    Given the user enters "JohnDoe" as username
    And the user enters "johnexample.com" as email
    And the user selects "Admin" as role
    When the user clicks on "Add User" button
    Then an error message "Error adding user." should be displayed
