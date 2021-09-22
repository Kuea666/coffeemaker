Feature: Purchase a beverage in the coffeemaker
  User can Purchase a beverage

  Background:
    Given coffeemaker is ready to use

  Scenario: Purchase beverage with enough money
    When I want to purchase a Coffee
    And I give 50
    Then I got change 0

  Scenario: Purchase beverage with not enough money
    When I want to purchase a Coffee
    And I give 40
    Then I got change 40

  Scenario: Purchase beverage Latte
    When I want to purchase a Latte
    And I give 900
    Then I got change 800
