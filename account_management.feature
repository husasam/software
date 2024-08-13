Feature: Account Management

  Scenario: Store owner updates business information
    Given I am logged in as a store owner
    When I update my business information
    Then the information should be updated successfully

  Scenario: Beneficiary user manages personal account
    Given I am logged in as a beneficiary user
    When I update my personal account details
    Then the details should be updated successfully
