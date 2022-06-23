package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utils.RestAssuredExtension;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PutSteps {
    private ResponseOptions<Response> response;

    @Given("user performs put operation for {string}")
    public void userPerformsPostOperationFor(String url, DataTable table) {
        var data = table.row(1);

        HashMap<String, String> body = new HashMap<>();
        body.put("name", data.get(1));

        RestAssuredExtension extension = new RestAssuredExtension(url, "put", "");
        response = extension.executePostWithBody(body);
    }

    @Then("put response body should have {string}")
    public void responseBodyShouldHave(String value) {
        assertThat(response.getBody().jsonPath().get("name"), equalTo(value));
    }
}
