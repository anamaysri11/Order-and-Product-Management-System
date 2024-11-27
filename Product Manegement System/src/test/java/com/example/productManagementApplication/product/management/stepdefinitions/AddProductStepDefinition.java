package com.example.productManagementApplication.product.management.stepdefinitions;

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

import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddProductStepDefinition {
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;
    private HttpHeaders headers = new HttpHeaders();
    private final String POST_PRODUCT_SERVICE_BASE_URL = "http://localhost:8081/products";
    HttpEntity<String>entity;
    @When("I provide the productName as {string}, productDescription as {string},productCost as {string} and productRemainingQty as {string}")
    public void addNewProduct(String productName, String productDescription,
                              String productCost, String productRemainingQty) throws JSONException {
        String jsonBody = "{\"productName\":\"" + productName + "\",\"productDescription\":\""
                + productDescription + "\",\"productCost\":\"" + productCost
                + "\",\"productRemainingQty\":\"" + productRemainingQty + "\"}";
        headers.setContentType(MediaType.APPLICATION_JSON);
        entity = new HttpEntity<>(jsonBody, headers);
    }
        @When("Send a POST HTTP request")
        public void sendPostHTTPRequest() {
            String url = POST_PRODUCT_SERVICE_BASE_URL + "/create";
            try {
                response = restTemplate.postForEntity(url, entity, String.class);
            } catch (HttpClientErrorException e) {
                throw e;
            }
        }

        @Then("the new product should be added into the product service database")
        public void newProductShouldBeAdded() throws JSONException {
            String responseBody = response.getBody();
            JSONObject object = new JSONObject(responseBody);
            String productId = object.getString("productId");
            assertTrue(Objects.requireNonNull(response.getBody()).contains(productId));
            assertEquals(200, response.getStatusCode().value());
        }

}
