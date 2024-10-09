package org.automation.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.automation.objects.ManageObjects;

public class TestFanCodeTodo {

    private ManageObjects manageObjects;

    @Before
    public void setup() {
        manageObjects = new ManageObjects();
    }

    @Given("User has the todo tasks")
    public void userHasTheTodoTasks() {
        manageObjects.getUserTodo().verifyUsersHaveTodoTasks();
    }

    @And("User belongs to the city FanCode")
    public void userBelongsToTheCityFanCode() {
        manageObjects.getUserTodo().getUsersBelongingToCityFanCode();
    }

    @Then("User Completed task percentage should be greater than {int}%")
    public void verifyUserCompletedTaskPercentage(int expectedCompletionPercentage) {
        manageObjects.getUserTodo().verifyCompletionPercentage(expectedCompletionPercentage);
    }
}
