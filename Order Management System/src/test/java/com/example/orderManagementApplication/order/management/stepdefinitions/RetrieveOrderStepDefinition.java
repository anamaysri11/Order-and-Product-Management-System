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

public class RetrieveOrderStepDefinition {
    private RestTemplate restTemplate = new RestTemplate();
    private final String RETRIEVE_ORDER_SERVICE_BASE_URL = "http://localhost:8082/orders";
    private ResponseEntity<String> response;

    @Given("the order service is running")
    public void orderServiceIsRunning() {
        RestTemplate restTemplate1 = new RestTemplate();
        try {
            ResponseEntity<String> responseEntity = restTemplate1.getForEntity(RETRIEVE_ORDER_SERVICE_BASE_URL + "/retrieve-orders", String.class);
            assertEquals(200, responseEntity.getStatusCode().value());
        } catch (HttpClientErrorException e) {
            fail("the order service is not running: " + e.getMessage());
        }
    }

    @When("I request the order with ID {string}")
    public void requestTheOrderWithId(String id) {
        response = restTemplate.getForEntity(RETRIEVE_ORDER_SERVICE_BASE_URL + "/retrieve-order/" + id, String.class);
    }

    @Then("the response code should be {int}")
    public void responseCodeReceived(int statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @Then("the response should contain the order with ID {string}")
    public void responseCodeCheck(String id) {
        assertTrue(response.getBody().contains(id));
    }
}
