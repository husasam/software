Feature: Product Management

  Scenario: Store owner adds a new product
    Given I am logged in as a store owner
    When I add a new product with name "Chocolate Cake"
    And I enter "Delicious chocolate cake" as the description
    And I enter "15.00" as the price
    Then the product should be added successfully

  Scenario: Store owner updates a product's details
    Given I am logged in as a store owner
    And a product named "Chocolate Cake" exists
    When I update the product "Chocolate Cake" to have a price of "12.00"
    Then the product's details should be updated successfully

  Scenario: Store owner deletes a product
    Given I am logged in as a store owner
    And a product named "Chocolate Cake" exists
    When I delete the product "Chocolate Cake"
    Then the product should be deleted successfully
