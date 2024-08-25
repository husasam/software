Feature: Feedback

  Scenario: Beneficiary user submits feedback on a product
    Given I am logged in as a beneficiary user
    And I purchased a product named "Chocolate Cake"
    When I submit a feedback with comment "Great taste" and rating "5"
    Then the feedback should be submitted successfully

  Scenario: Admin views feedback on a product
    Given I am logged in as an admin
    And feedback exists for the product "Chocolate Cake"
    When I view feedback for "Chocolate Cake"
    Then I should see all feedback with comments and ratings
