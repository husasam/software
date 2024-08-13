Feature: Monitoring and Reporting

  Scenario: Admin views financial reports
    Given I am logged in as an admin
    When I view the financial reports
    Then I should see the total profits for all stores

  Scenario: Admin identifies best-selling products
    Given I am logged in as an admin
    When I view the best-selling products
    Then I should see the products listed by sales numbers

  Scenario: Admin views user statistics by city
    Given I am logged in as an admin
    When I view user statistics by city
    Then I should see the number of registered users for each city
