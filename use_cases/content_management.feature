Feature: Content Management

  Scenario: Admin manages recipes
    Given I am logged in as an admin
    When I view all recipes
    Then I should be able to edit or delete any recipe

  Scenario: Admin manages user feedback
    Given I am logged in as an admin
    When I view all feedback
    Then I should be able to remove any inappropriate feedback
