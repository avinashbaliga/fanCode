package org.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/org/automation/features",
        glue = "org/automation/stepdefs",
        plugin = {"pretty", "html:src/main/resources/report.html"}
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
}
