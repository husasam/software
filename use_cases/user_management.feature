Feature: User Management

  Scenario: Admin creates a new store owner account
    Given I am logged in as an admin
    When I create a new user with the role "store_owner"
    And I enter "store_owner1" as the username
    And I enter "password123" as the password
    And I enter "store_owner1@example.com" as the email
    And I enter "Nablus" as the city
    Then the new user should be created successfully

  Scenario: Admin views a list of all users
    Given I am logged in as an admin
    When I view the user list
    Then I should see all users with their roles and cities

  Scenario: Admin deletes a user account
    Given I am logged in as an admin
    And a user with username "store_owner1" exists
    When I delete the user "store_owner1"
    Then the user should be deleted successfully
