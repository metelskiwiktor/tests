package pl.wiktor.shop.tests.user;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.wiktor.shop.tests.model.Account;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class UserSteps {
    private String login;
    private String password;
    private Account account;
    private ResponseEntity<String> response;
    private String statusCodeResponse;
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders httpHeaders = new HttpHeaders();
    private String token;

    @Given("login {string} and password {string}")
    public void loginAndPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @When("I send request to register my account")
    public void iRegisterMyAccount() throws JsonProcessingException, URISyntaxException {
        account = new Account(login, password);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestToJson = objectMapper.writeValueAsString(account);
        URI url = new URI("http://localhost:7901/register/");

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            this.response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(requestToJson, httpHeaders), String.class);
            this.statusCodeResponse = response.getStatusCode().toString();
        } catch (HttpClientErrorException e) {
            this.statusCodeResponse = e.getStatusCode().toString();
        }
    }

    @Then("I got status {string}")
    public void iGotStatusAndReturnMessage(String statusCode) {
        assertEquals(statusCode, statusCodeResponse);
    }

    @When("I send request to login my account")
    public void iSendRequestToLoginMyAccount() throws JsonProcessingException, URISyntaxException {
        account = new Account(login, password);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestToJson = objectMapper.writeValueAsString(account);
        URI url = new URI("http://localhost:7901/login/");

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            this.response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(requestToJson, httpHeaders), String.class);
            this.statusCodeResponse = response.getStatusCode().toString();
            this.token = response.getBody();
        } catch (HttpClientErrorException e) {
            this.statusCodeResponse = e.getStatusCode().toString();
        }
    }

    @And("I got token")
    public void iGotToken() {
        System.out.println(token);
        assertNotNull(token);
    }

    @When("I send request to delete my account")
    public void iSendRequestToDeleteMyAccount() throws URISyntaxException {
        URI url = new URI("http://localhost:7901/account/delete/");

        System.out.println(token);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Token", token);
        try {
            this.response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);
            this.statusCodeResponse = response.getStatusCode().toString();
        } catch (HttpClientErrorException e) {
            this.statusCodeResponse = e.getStatusCode().toString();
        }
    }

    @Given("logged account with credentials {string} and {string}")
    public void loggedAccount(String login, String password) throws JsonProcessingException, URISyntaxException {
        this.login = login;
        this.password = password;
        iSendRequestToLoginMyAccount();
    }

    @And("I don't have token")
    public void iDonTHaveToken() {
        assertNull(token);
    }
}
