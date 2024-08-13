Feature: Exploration and Purchase

  Scenario: Beneficiary user browses dessert recipes
    Given I am logged in as a beneficiary user
    When I browse the dessert recipes
    Then I should see a list of available recipes

  Scenario: Beneficiary user filters recipes based on dietary needs
    Given I am logged in as a beneficiary user
    When I filter recipes for "gluten-free"
    Then I should see only gluten-free recipes
