Scenario Outline: Retrieve an existing customer by ID
Given the customer service is running
When I request the customer with ID <customerId>
Then I should get response code as 200
And the response should contain the customer with ID <customerId>
Examples:
| customerId |
| "1"        |
| "2"        |
| "3"        |

Scenario: Retrieve all existing customers in customer service
Given the customer service is running
When I request to retrieve all customers
Then I should receive a list of customers

Scenario Outline: Add a new customer
Given the customer service is running
When I provide the customerName as <customerName>, customerAddress as <customerAddress> and
phoneNo as <phoneNo>
And Send a POST HTTP request
Then the new customer should be added into the customer service database
Examples:
| customerName | customerAddress | phoneNo      |
| "Cust200"    | "Kolkata"       | "9988770077" |
| "Cust201"    | "Gurgaon"       | "8880660088" |
| "Cust202"    | "Chandigarh"    | "7766550000" |

Scenario: Delete customer
Given the customer service is running
And the customer with ID "185" exists
When I request to delete the customer with ID "185"
Then the customer with ID "185" should be deleted
