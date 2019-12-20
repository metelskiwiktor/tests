package pl.wiktor.shop.tests;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jndi.toolkit.url.Uri;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

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
    public void iRegisterMyAccount() throws JSONException, JsonProcessingException, URISyntaxException {
        this.account = new Account(login, password);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestToJson = objectMapper.writeValueAsString(account);
        URI url = new URI("http://localhost:8080/register/");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(requestToJson, httpHeaders), String.class);
        System.out.println(response);




    }

    private WebClient.ResponseSpec sendRequestToUsers(Account account) {

        return WebClient
                .create()
                .post()
                .uri("localhost:8080/api/register")
                .syncBody(account)
                .retrieve();
    }


    @Then("I got status {string} and return message {string}")
    public void iGotStatusAndReturnMessage(String status, String returnMessage) {
    }
}
