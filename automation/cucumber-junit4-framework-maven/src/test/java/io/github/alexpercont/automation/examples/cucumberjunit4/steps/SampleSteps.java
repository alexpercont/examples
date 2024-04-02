package io.github.alexpercont.automation.examples.cucumberjunit4.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SampleSteps {

    @Given("I am a regular user")
    public void retrieveStandardCredentials() {
        // Empty on purpose.

    }

    @When("I login to the app")
    public void doLogin(){
        // Empty on purpose.
    }

    @Then("the app displays a message saying {string}")
    public void isExpectedMessageDisplayedInScreen(String message) {
        Assert.assertEquals("Hello", message);
    }
}
