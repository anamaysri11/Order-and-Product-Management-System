package com.example.orderManagementApplication.order.management.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class RetrieveAllCustomerStepDefinition {
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;
    private final String RETRIEVE_CUSTOMER_SERVICE_BASE_URL = "http://localhost:8082/customer";

    @When("I request to retrieve all customers")
    public void requestToRetrieveAllCustomers() {
        response = restTemplate.getForEntity(RETRIEVE_CUSTOMER_SERVICE_BASE_URL + "/retrieve", String.class);
    }

    @Then("I should receive a list of customers")
    public void listOfCustomers() {
        assertNotNull("The response should not be null", response.getBody());
        assertTrue("The response should start with a '[' indicating a JSON array", response.getBody().startsWith("["));
        assertTrue("The response should end with a ']' indicating a JSON array", response.getBody().endsWith("]"));
    }
}
