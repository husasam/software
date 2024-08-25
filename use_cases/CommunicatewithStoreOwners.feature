Feature: Communicate with Store Owners

  Scenario: Successfully send a message
    Given the user is on the Communicate with Store Owners page
    And the user enters "storeOwner1" as receiver username
    And the user enters "Hello, this is a test message." as the message
    When the user clicks on "Send Message" button
    Then a success message "Message sent successfully!" should be displayed

  Scenario: Attempt to send a message with empty receiver username
    Given the user is on the Communicate with Store Owners page
    And the user enters "" as receiver username
    And the user enters "Hello, this is a test message." as the message
    When the user clicks on "Send Message" button
    Then an error message "Receiver name and message cannot be empty." should be displayed

  Scenario: Attempt to send a message with empty message
    Given the user is on the Communicate with Store Owners page
    And the user enters "storeOwner1" as receiver username
    And the user enters "" as the message
    When the user clicks on "Send Message" button
    Then an error message "Receiver name and message cannot be empty." should be displayed

  Scenario: Attempt to send a message to a non-existent receiver
    Given the user is on the Communicate with Store Owners page
    And the user enters "nonExistentUser" as receiver username
    And the user enters "Hello, this is a test message." as the message
    When the user clicks on "Send Message" button
    Then an error message "Receiver not found." should be displayed
