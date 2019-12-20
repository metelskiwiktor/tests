package pl.wiktor.shop.tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MyStepdefs {
    private int n1;
    private int n2;
    private int result;

    @Given("number {int} and {int}")
    public void numberNumberAndNumber(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    @When("I add {int} plus {int}")
    public void iAddNumberPlusNumber(int n1, int n2) {
        result = n1 + n2;
    }

    @Then("It should return {int} value")
    public void itShouldReturnResultValue(int result) {
        assertEquals(result, this.result);
    }
}
