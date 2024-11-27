package com.example.productManagementApplication.product.management.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RetrieveProductStepDefinition {
    private RestTemplate restTemplate = new RestTemplate();
    private final String RETRIEVE_PRODUCT_SERVICE_BASE_URL = "http://localhost:8081/products";
    private ResponseEntity<String> response;

    @Given("the product service is running")
    public void productServiceIsRunning() {
        RestTemplate restTemplate1 = new RestTemplate();
        try {
            ResponseEntity<String> responseEntity = restTemplate1.getForEntity(RETRIEVE_PRODUCT_SERVICE_BASE_URL + "/retrieve", String.class);
            assertEquals(200, responseEntity.getStatusCode().value());
        } catch (HttpClientErrorException e) {
            fail("the product service is not running: " + e.getMessage());
        }
    }

    @When("I request the product with ID {string}")
    public void requestTheProductWithId(String id) {
        response = restTemplate.getForEntity(RETRIEVE_PRODUCT_SERVICE_BASE_URL + "/retrieve/" + id, String.class);
    }
    @Then("the response code should be {int}")
    public void responseCodeCheck(int statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @Then("the response should contain the product with ID {string}")
    public void responseShouldContainTheProductWithId(String id) {
        assertTrue(response.getBody().contains(id));
    }
}
