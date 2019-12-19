package pl.wiktor.shop.tests;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserSteps {
    private String login;
    private String password;
    private Account account;

    @Given("login is {string} and password is {string}")
    public void loginIsLoginAndPasswordIsPassword() {

    }

    @Given("login {string} and password {string}")
    public void loginAndPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @When("I send request to register my account")
    public void iRegisterMyAccount() {
        this.account = new Account(login, password);

    }

    @Then("I got status {string} and return message {string}")
    public void iGotStatusAndReturnMessage(String status, String returnMessage) {
    }
}
