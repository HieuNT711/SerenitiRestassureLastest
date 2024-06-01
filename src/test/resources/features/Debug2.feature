Feature: Order2

  @1
  Scenario: Create Order4
    Given I generate access token
    Given I create Order
    And I get Order info

  @1
  Scenario: Create Order3
    Given I generate access token
    Given I create Order
    And I get Order info

