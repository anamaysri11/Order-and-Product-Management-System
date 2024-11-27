package com.example.productManagementApplication.product.management.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class RetrieveAllProductStepDefinition {
    private RestTemplate restTemplate = new RestTemplate();
    private final String RETRIEVE_PRODUCT_SERVICE_BASE_URL = "http://localhost:8081/products";
    private ResponseEntity<String> response;

    @When("I request to retrieve all products")
    public void requestToRetrieveAllProducts() {
        response = restTemplate.getForEntity(RETRIEVE_PRODUCT_SERVICE_BASE_URL + "/retrieve", String.class);
    }

    @Then("I should receive a list of products")
    public void listOfProducts() {
        assertNotNull("The response should not be null", response.getBody());
        assertTrue("The response should start with a '[' indicating a JSON array",
                response.getBody().startsWith("["));
        assertTrue("The response should end with ']' indicating a JSON array",
                response.getBody().endsWith("]"));
    }
}
