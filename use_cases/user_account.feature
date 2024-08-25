Feature: User Account

  Scenario: Beneficiary user signs up for a new account
    Given I am on the signup page
    When I enter valid account details
    Then my account should be created successfully

  Scenario: Beneficiary user signs in to the platform
    Given I am on the login page
    When I enter my username and password
    Then I should be logged in successfully

  Scenario: Beneficiary user fails to sign in with incorrect details
    Given I am on the login page
    When I enter incorrect username or password
    Then I should see an error message
