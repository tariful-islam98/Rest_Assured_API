package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utils.RestAssuredExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class PostSteps {
    private ResponseOptions<Response> response;

    @Given("user performs post operation for {string}")
    public void userPerformsPostOperationFor(String url, DataTable table) {
        var data = table.row(1);

        HashMap<String, String> body = new HashMap<>();
        body.put("id",data.get(0));
        body.put("name",data.get(1));

        RestAssuredExtension extension = new RestAssuredExtension(url, "post", "");
        response = extension.executePostWithBody(body);
    }

    @Then("response body should have {string}")
    public void responseBodyShouldHave(String value) {
        assertThat(response.getBody().jsonPath().get("name"), equalTo(value));
    }
}
