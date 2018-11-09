Feature : Add a transaction
    As a user
    I want to add income and expense

Background :
    Given a user with balance 1000 exists

Scenario: Deposit
    When I deposit 100
    Then my account have balance 1100 exists

Scenario: Expense
    When I expense 100
    Then my account have balance 900 exists

Scenario: Expense more than Balance
    When I expense 2000
    Then my account have balance -1000 exists

