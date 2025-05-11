Feature: Home page

  Scenario: User is able to view the product details after clicking on it
    Given user launches the application
    When user logs in as "STANDARD_USER"
    Then user should be navigated to homepage
    And user sees the homepage
    Then user clicks on a product and sees the product detail page of the "Sauce Labs Bike Light"

  Scenario: User is able to add products to the cart and it displays in the cart icon
    Given user launches the application
    When user logs in as "STANDARD_USER"
    Then user should be navigated to homepage
    And user sees the homepage
    Then user adds "Sauce Labs Bike Light" and "Sauce Labs Backpack" to the cart
    And user sees the cart icon indicate the number of products in the cart
