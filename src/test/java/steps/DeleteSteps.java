package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utils.RestAssuredExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeleteSteps {

    private ResponseOptions<Response> response;

    @Given("user performs delete operation for {string}")
    public void userPerformsDeleteOperationFor(String url) {
        RestAssuredExtension extension = new RestAssuredExtension(url, "delete", "");
        response = extension.executeRequests();
    }

    @Then("response code for delete operation should be {int}")
    public void responseCodeForDeleteOperationShouldBe(int resp) {
        assertThat(response.getStatusCode(), is(resp));
    }
}
