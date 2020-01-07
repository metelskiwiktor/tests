Feature: Item microservice can add items, authors, tags, categories

  Scenario: Item is added
    Given I have item
    When I send request to add item