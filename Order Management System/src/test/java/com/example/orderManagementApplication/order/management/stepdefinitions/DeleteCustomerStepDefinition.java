package com.example.orderManagementApplication.order.management.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCustomerStepDefinition {
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;
    private String DELETE_CUSTOMER_SERVICE_BASE_URL = "http://localhost:8082/customer";

    @Given("the customer with ID {string} exists")
    public void customerWithIdExists(String id) {
        String getUrl = DELETE_CUSTOMER_SERVICE_BASE_URL + "/retrieve/" + id;
        try {
            ResponseEntity<String> getResponse = restTemplate.getForEntity(getUrl, String.class);
            assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        } catch (HttpClientErrorException e) {
            fail("Customer with ID " + id + " does not exists");
        }
    }

    @When("I request to delete the customer with ID {string}")
    public void requestToDeleteTheCustomer(String id) {
        String deleteUrl = DELETE_CUSTOMER_SERVICE_BASE_URL + "/delete/" + id;
        try {
            restTemplate.delete(deleteUrl);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getStatusCode());
        }
    }

    @Then("the customer with ID {string} should be deleted")
    public void customerWithIdShouldBeDeleted(String id) {
        String getUrl = DELETE_CUSTOMER_SERVICE_BASE_URL + "/retrieve/" + id;
        try {
            ResponseEntity<String> response1 = restTemplate.getForEntity(getUrl, String.class);
            fail("Customer with ID " + id + " should have been deleted");
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }
}
