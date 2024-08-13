Feature: Communication and Notification

  Scenario: Store owner sends a message to a supplier
    Given I am logged in as a store owner
    When I send a message to supplier "supplier1"
    Then the message should be sent successfully

  Scenario: Store owner receives a notification for a special request
    Given I am logged in as a store owner
    When a special request is made
    Then I should receive an email notification
