package pl.wiktor.shop.tests.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.wiktor.shop.tests.model.Account;
import pl.wiktor.shop.tests.model.Item;

import java.net.URI;
import java.net.URISyntaxException;

public class ItemSteps {

    private Item item;
    private HttpHeaders httpHeaders = new HttpHeaders();
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;
    private String statusCodeResponse;


    @Given("I have item")
    public void iHaveItem() {
        Item item = new Item();
        item.setName("Książka o Javie");
        this.item = item;
    }

    @When("I send request to add item")
    public void iSendRequestToAddItem() throws JsonProcessingException, URISyntaxException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestToJson = objectMapper.writeValueAsString(item);
        URI url = new URI("http://localhost:7901/item/add");

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            this.response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(requestToJson, httpHeaders), String.class);
        } catch (HttpClientErrorException e) {
            this.statusCodeResponse = e.getStatusCode().toString();
        }
    }
}
