Feature: Product service functionalities

  Scenario Outline: Retrieve an existing product by ID
    Given the product service is running
    When I request the product with ID <productId>
    Then the response code should be 200
    And the response should contain the product with ID <productId>

    Examples:
      | productId |
      | "2"       |
      | "1"       |

  Scenario: Retrieve all products from the database
    Given the product service is running
    When I request to retrieve all products
    Then I should receive a list of products

  Scenario Outline: Delete a product
    Given the product service is running
    And the product with ID <productId> exists
    When I request to delete the product with ID <productId>
    Then the product with ID <productId> should be deleted

    Examples:
      | productId |
      | "91"      |
      | "92"      |
      | "93"      |
  Scenario Outline: Add a new product
    Given the product service is running
    When I provide the productName as <productName>, productDescription as <productDesc>,productCost as <productCost> and productRemainingQty as <productRemainingQty>
    And Send a POST HTTP request
    Then the new product should be added into the product service database

    Examples:
      | productName | productDesc | productCost | productRemainingQty |
      | "Prod33"    | "Desc30"    | "30000"     | "15"                |
      | "Prod53"    | "Desc50"    | "50000"     | "15"                |

  Scenario Outline: Update an existing product
    Given the product service is running
    And the product with ID <productId> exists
    When I provide the productName as <productName>, productDescription as <productDesc>,productCost as <productCost> and productRemainingQty as <productRemainingQty> of the product with id <productId>
    And Send a PUT HTTP request
    Then the product with ID <productId> should get updated

    Examples:
      | productId | productName | productDesc | productCost | productRemainingQty |
      | "3"       | "Prod_12"   | "Desc13"    | "60000.0"   | "15"                |
      | "9"       | "Prod_12"   | "Desc13"    | "70000.0"   | "10"                |
