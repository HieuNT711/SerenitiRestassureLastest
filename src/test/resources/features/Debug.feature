Feature: Order

  @1
  Scenario: Create Order
    Given I generate access token
    Given I create Order
    And I get Order info

  @1
  Scenario: Create Order2
    Given I generate access token
    Given I create Order
    And I get Order info

