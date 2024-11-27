package com.example.orderManagementApplication.order.management.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCustomerStepDefinitions {
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;
    private final String POST_CUSTOMER_SERVICE_BASE_URL = "http://localhost:8081/customer";
    private HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity;

    @When("I provide the customerName as {string}, customerAddress as {string} and phoneNo as {string}")
    public void addNewCustomer(String customerName, String customerAddress, String phoneNo) {
        String jsonBody = "{\"customerName\":\"" + customerName + "\",\"customerAddress\":\"" + customerAddress + "\",\"phoneNo\":\"" + phoneNo + "\"}";
        headers.setContentType(MediaType.APPLICATION_JSON);
        entity = new HttpEntity<String>(jsonBody, headers);
    }

    @When("Send a POST HTTP request")
    public void sendPostHttpRequest() {
        String url = POST_CUSTOMER_SERVICE_BASE_URL + "/add";
        try {
            response = restTemplate.postForEntity(url, entity, String.class);
        } catch (HttpClientErrorException e) {
            throw e;
        }
    }

    @Then("the new customer should be added into the customer service database")
    public void newCustomerShouldBeAdded() throws JSONException {
        String responseBody = response.getBody();
        JSONObject object = new JSONObject(responseBody);
        String customerId = object.getString("customerId");
        assertTrue(response.getBody().contains(customerId));
        assertEquals(200, response.getStatusCode().value());
    }
}