Feature: Order Management

  Scenario: Beneficiary user places an order
    Given I am logged in as a beneficiary user
    And a product named "Chocolate Cake" exists
    When I place an order for "Chocolate Cake"
    Then the order should be placed successfully
    And the order status should be "pending"

  Scenario: Store owner processes an order
    Given I am logged in as a store owner
    And an order for "Chocolate Cake" exists
    When I mark the order as "processed"
    Then the order status should be updated to "processed"

  Scenario: Store owner ships an order
    Given I am logged in as a store owner
    And an order for "Chocolate Cake" with status "processed" exists
    When I mark the order as "shipped"
    Then the order status should be updated to "shipped"
