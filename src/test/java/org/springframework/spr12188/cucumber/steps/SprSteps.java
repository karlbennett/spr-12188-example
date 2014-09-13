package org.springframework.spr12188.cucumber.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.spr12188.cucumber.CucumberConfiguration;
import org.springframework.test.context.ContextConfiguration;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;
import java.util.Map;

import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@ContextConfiguration(classes = CucumberConfiguration.class)
public class SprSteps {

    @Autowired
    private WebTarget client;

    private final MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();

    private Response response;

    @After
    public void tearDown() {
        headers.clear();
    }

    @When("^I attempt a successful request$")
    public void I_attempt_a_successful_request() {
        response = client.request().headers(headers).get();
    }

    @Then("^I should receive a status code of (\\d+)$")
    public void I_should_receive_a_status_code_of(int status) {
        assertEquals("the response HTTP status code should be correct.", status, response.getStatus());
    }

    @Then("^the response body should contain a \"([^\"]*)\" message of \"(true|false)\"$")
    public void the_response_body_should_contain_a_message_of(String name, boolean value) {
        @SuppressWarnings("unchecked")
        final Map<String, Boolean> actual = (Map<String, Boolean>) response.readEntity(Map.class);

        assertThat("the response body should be correct.", actual, hasEntry(name, value));
    }

    @Given("^I have an \"([^\"]*)\" header of \"([^\"]*)\"$")
    public void I_have_an_header_of(String name, String value) {
        headers.add(name, value);
    }

    @When("^I attempt a failed request$")
    public void I_attempt_a_failed_request() {
        response = client.path("/fail").request().headers(headers).get();
    }
}
