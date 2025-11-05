@API
Feature: Pet StoreAPI Validation

  Scenario: Verify pet creation and deletion
    Given I add a new pet with name "Bruno" and status "available"
    When I fetch the pet by ID
    Then the pet details should match name "Bruno"
    When I delete the pet
    Then the pet should no longer exist
