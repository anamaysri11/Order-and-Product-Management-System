package com.example.orderManagementApplication.order.management.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
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
import java.util.List;
import java.util.Map;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddOrderStepDefinition {
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;
    private final String CREATE_ORDER_SERVICE_BASE_URL = "http://localhost:8082/orders";
    private final String PRODUCT_SERVICE_BASE_URL = "http://localhost:8081/products/retrieve";
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<String> entity;

    @Given("the product service is running")
    public void theProductServiceIsRunning() {
        RestTemplate restTemplate1 = new RestTemplate();
        try {
            ResponseEntity<String> responseEntity = restTemplate1.getForEntity(PRODUCT_SERVICE_BASE_URL, String.class);
            assertEquals(200, responseEntity.getStatusCode().value());
        } catch (HttpClientErrorException e) {
            fail("the product service is not running: " + e.getMessage());
        }
    }

    @When("I provide the order with modeOfPayment as {string}, customerId as {string}, and selected product from the following table")
    public void orderDetailsProvided(String modeOfPayment, String customerId, DataTable products) {
        String jsonBody = createOrderJsonBody(modeOfPayment, customerId, products);
        headers.setContentType(MediaType.APPLICATION_JSON);
        entity = new HttpEntity<String>(jsonBody, headers);
    }

    @When("Send a POST HTTP request to order service")
    public void sendPostHTTPRequestToOrderService() {
        String url = CREATE_ORDER_SERVICE_BASE_URL + "/create";
        try {
            response = restTemplate.postForEntity(url, entity, String.class);
        } catch (HttpClientErrorException e) {
            throw e;
        }
    }

    @Then("the new order should be added into the order service database")
    public void newOrderShouldBeAdded() throws JSONException {
        String responseBody = response.getBody();
        JSONObject object = new JSONObject(responseBody);
        assertNotNull(responseBody.contains("orderId"));
        assertEquals(200, response.getStatusCode().value());
    }

    private String createOrderJsonBody(String modeOfPayment, String customerId, DataTable products) {
        Map<String, Object> orderDetails = new HashMap<>();
        orderDetails.put("modeOfPayment", modeOfPayment);
        orderDetails.put("customerId", customerId);
        Map<String, Integer> selectedProducts = new HashMap<>();
        List<List<String>> rows = products.asLists(String.class);
        int flag = 0;
        for (List<String> row : rows) {
            if (flag == 0) {
                flag++;
                continue;
            }
            String productId = row.get(0);
            Integer quantity = Integer.parseInt(row.get(1));
            selectedProducts.put(productId, quantity);
        }
        orderDetails.put("selectedProducts", selectedProducts);
        return new JSONObject(orderDetails).toString();
    }
}
