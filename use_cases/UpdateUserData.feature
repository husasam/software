Feature: Update User Data

  Scenario: Successfully update user data
    Given the user is on the Edit User page for "user"
    And the user enters "newemail@example.com" as email
    And the user selects "Store Owner" as role
    When the user clicks on "Save Changes" button
    Then a success message "User data updated successfully." should be displayed
    And the user data should be updated with new email and role

  Scenario: Update user data with empty email
    Given the user is on the Edit User page for "JohnDoe"
    And the user enters "" as email
    And the user selects "Store Owner" as role
    When the user clicks on "Save Changes" button
    Then an error message "Error updating user data." should be displayed

  Scenario: Update user data with invalid role
    Given the user is on the Edit User page for "JohnDoe"
    And the user enters "newemail@example.com" as email
    And the user selects "InvalidRole" as role
    When the user clicks on "Save Changes" button
    Then an error message "Error updating user data." should be displayed
