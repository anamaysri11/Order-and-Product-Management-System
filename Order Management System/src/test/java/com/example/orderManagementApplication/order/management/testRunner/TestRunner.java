package com.example.orderManagementApplication.order.management.testRunner;

import io.cucumber.junit.platform.engine.CucumberTestEngine;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/features",
        glue={"src/test/java/com/example/orderManagementApplication/order/management/stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber.html"},
        monochrome=true
)
public class TestRunner {
}
