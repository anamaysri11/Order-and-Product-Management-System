package com.example.orderManagementApplication.order.management.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RetrieveCustomerStepDefinition {
    private RestTemplate restTemplate = new RestTemplate();
    private final String RETRIEVE_CUSTOMER_SERVICE_BASE_URL = "http://localhost:8082/customer";
    private ResponseEntity<String> response;

    @Given("the customer service is running")
    public void customerServiceIsRunning() {
        RestTemplate restTemplate1 = new RestTemplate();
        try {
            ResponseEntity<String> responseEntity = restTemplate1.getForEntity(RETRIEVE_CUSTOMER_SERVICE_BASE_URL + "/retrieve", String.class);
            assertEquals(200, responseEntity.getStatusCode().value());
        } catch (HttpClientErrorException e) {
            fail("the customer service is not running: " + e.getMessage());
        }
    }

    @When("I request the customer with ID {string}")
    public void requestTheCustomer(String id) {
        response = restTemplate.getForEntity(RETRIEVE_CUSTOMER_SERVICE_BASE_URL + "/retrieve/" + id, String.class);
    }

    @Then("I should get response code as {int}")
    public void responseCodeReceived(int statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @Then("the response should contain the customer with ID {string}")
    public void responseCodeCheck(String id) {
       assertTrue(response.getBody().contains(id));
    }
}
