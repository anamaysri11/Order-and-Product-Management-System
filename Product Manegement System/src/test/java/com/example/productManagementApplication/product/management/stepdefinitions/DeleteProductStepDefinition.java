package com.example.productManagementApplication.product.management.stepdefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteProductStepDefinition {
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;
    private final String DELETE_PRODUCT_SERVICE_BASE_URL = "http://localhost:8081/products";

    @Given("the product with ID {string} exists")
    public void productWithIdExists(String id) {
        String getUrl = DELETE_PRODUCT_SERVICE_BASE_URL + "/retrieve/" + id;
        try {
            ResponseEntity<String> getResponse = restTemplate.getForEntity(getUrl, String.class);
            assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        } catch (HttpClientErrorException e) {
            fail("Product with ID " + id + " does not exist");
        }
    }

    @When("I request to delete the product with ID {string}")
    public void requestToDeleteTheProduct(String id) {
        String deleteUrl = DELETE_PRODUCT_SERVICE_BASE_URL + "/delete/" + id;
        try {
            restTemplate.delete(deleteUrl);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getStatusCode());
        }
    }
    @Then("the product with ID {string} should be deleted")
    public void productWithIdShouldBeDeleted(String id) {
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            fail("Failed to delete the order with id " + id + ": " + response.getStatusCode());
        }
        String getUrl = DELETE_PRODUCT_SERVICE_BASE_URL + "/retrieve/" + id;
        try {
            ResponseEntity<String> response1 = restTemplate.getForEntity(getUrl, String.class);
            fail("Product with ID " + id + " should have been deleted");
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }
}
