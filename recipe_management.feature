Feature: Recipe Management

  Scenario: Beneficiary user posts a new recipe
    Given I am logged in as a beneficiary user
    When I post a new recipe titled "Vanilla Ice Cream"
    And I enter the ingredients "Milk, Sugar, Vanilla"
    And I enter the instructions "Mix and freeze"
    Then the recipe should be posted successfully

  Scenario: Beneficiary user views a list of recipes
    Given I am logged in as a beneficiary user
    When I view the list of recipes
    Then I should see all recipes with their titles and creators

  Scenario: Beneficiary user deletes a recipe
    Given I am logged in as a beneficiary user
    And a recipe titled "Vanilla Ice Cream" exists
    When I delete the recipe "Vanilla Ice Cream"
    Then the recipe should be deleted successfully
