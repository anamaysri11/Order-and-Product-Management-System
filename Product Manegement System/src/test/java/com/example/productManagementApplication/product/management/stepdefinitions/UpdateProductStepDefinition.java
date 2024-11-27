package com.example.productManagementApplication.product.management.stepdefinitions;

import aj.org.objectweb.asm.commons.JSRInlinerAdapter;
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

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateProductStepDefinition {
    private RestTemplate restTemplate = new RestTemplate();
    private final String PUT_PRODUCT_SERVICE_BASE_URL = "http://localhost:8081/products";
    private ResponseEntity<String> response;
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<String> entity;
    private String productId;
    Map<String,String>map = new HashMap<>();

    @When("I provide the productName as {string}, productDescription as {string},productCost as {string} and productRemainingQty as {string} of the product with id {string}")
    public void addNewProduct(String productName, String productDescription, String productCost, String productRemainingQty, String id) throws JSONException {
        String jsonBody = setJsonBody(productName, productDescription, productCost, productRemainingQty);
        headers.setContentType(MediaType.APPLICATION_JSON);
        entity = new HttpEntity<>(jsonBody, headers);
        this.productId = id;
    }

    @When("Send a PUT HTTP request")
    public void sendPutHTTPRequest() {
        String url = PUT_PRODUCT_SERVICE_BASE_URL + "/update/" + productId;
        try {
            restTemplate.put(url, entity, String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getStatusCode());
        }
    }

    @Then("the product with ID {string} should get updated")
    public void productShouldGetUpdated(String id) throws JSONException {
        String responseBody = restTemplate.getForEntity(PUT_PRODUCT_SERVICE_BASE_URL + "/retrieve/" + id, String.class).getBody();
        JSONObject object = new JSONObject(responseBody);
        String productName = object.getString("productName");
        String productDescription = object.getString("productDescription");
        String productCost = object.getString("productCost");
        String productRemainingQty = object.getString("productRemainingQty");


        for (Map.Entry<String, String> mp : map.entrySet()) {
            if (mp.getKey().equals("productName"))
                assertEquals(mp.getValue(), productName);
            if (mp.getKey().equals("productDescription"))
                assertEquals(mp.getValue(), productDescription);
            if (mp.getKey().equals("productCost"))
                assertEquals(mp.getValue(), productCost);
            if (mp.getKey().equals("productRemainingQty"))
                assertEquals(mp.getValue(), productRemainingQty);
        }
    }

    private String setJsonBody(String productName, String productDescription, String productCost, String productRemainingQty) throws JSONException {
        JSONObject jsonBody = new JSONObject();
        Map<String, String> map = new HashMap<>();
        map.put("productName", productName);
        if (!productDescription.isEmpty()) {
            jsonBody.put("productDescription", productDescription);
            map.put("productDescription", productDescription);
        }
        if (!productCost.isEmpty()) {
            jsonBody.put("productCost", productCost);
            map.put("productCost", productCost);
        }
        if (!productRemainingQty.isEmpty()) {
            jsonBody.put("productRemainingQty", productRemainingQty);
            map.put("productRemainingQty", productRemainingQty);
        }
        return jsonBody.toString();
    }
}
