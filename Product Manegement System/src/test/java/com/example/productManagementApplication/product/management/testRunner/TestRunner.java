package com.example.productManagementApplication.product.management.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/feature",
        glue={"src/test/java/com/example/productManagementApplication/product/management/stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber.html"},
        monochrome=true
)
public class TestRunner {
}