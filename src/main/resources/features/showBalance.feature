Feature: show my balance
    As a customer
    I want to deposit to my account
    And check my account balance

Background:
    Given a customer with initial balance 50 exists

Scenario: Deposit money to my account
    When I deposit 500 to my account
    Then my account balance is 550
    And my account income is 550

Scenario: Withdraw money from my account
    When I withdraw 20 from my account
    Then my account balance is 30
    And my account expense is 20

Scenario: Withdraw money from my account over my balance
    When I withdraw 100 from my account
    Then my account balance is 50
    And my account expense is 0