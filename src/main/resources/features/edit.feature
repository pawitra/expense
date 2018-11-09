jhFeature : Edit a transaction
    As a user
    I want to edit transaction

Background :
    Given a transaction recieve money from mom with 50 income at 2018-11-07

Scenario: Edit amount
    When I edit amount to 500
    Then transaction is recieve money from mom with 500 income at 2018-11-07

Scenario: Edit date
    When I edit date to 2018-11-06
    Then transaction is recieve money from mom with 50 income at 2018-11-06

Scenario: Edit detail
    When I edit detail to buy food
    Then transaction is buy food with 50 income at 2018-11-07

Scenario: Edit type
    When I edit amount to -50
    Then transaction is buy food with 50 expense at 2018-11-07