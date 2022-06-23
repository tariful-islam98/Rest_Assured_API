package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utils.RestAssuredExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class GetSteps {
    private static ResponseOptions<Response> response;
    public static String token = "Bearer 0553ef87a77b79a95756c0ae88cb50df43dfaaf5d9df4fc04791520a4a7a7df3";

    @Given("user wants to get all information for {string}")
    public void userWantsToGetAllInformationFor(String url) throws URISyntaxException {
        RestAssuredExtension extension = new RestAssuredExtension(url, "get", "");
        response = extension.executeRequests();
    }

    @Then("response {string} should be received")
    public void responseShouldBeReceived(String response) {
        assertThat(this.response.getStatusCode(), is(200));
    }

    @And("user information should be received")
    public void userInformationShouldBeReceived() {
        assertThat(this.response.getBody().jsonPath().get("title"), containsInAnyOrder("java", "c#", "xml", "json"));
    }

    @And("response {string} should be {string}")
    public void responseShouldBe(String title, String value) {
//        System.out.println((String) response.getBody().jsonPath().get());
        assertThat(this.response.getBody().jsonPath().get(title), equalToIgnoringCase(value));
    }

    @Given("user wants to get information for {string} having")
    public void userWantsToGetInformationForHaving(String url, DataTable table) {
        var data = table.column(0);
        Map<String, String> queryParams = new HashMap<>();

        queryParams.put(data.get(0), data.get(1));

        RestAssuredExtension extension = new RestAssuredExtension(url, "Get", "");

        response = extension.executeWithQueryParam(queryParams);
    }

    @And("response for profile {string} should be {string}")
    public void responseForProfileShouldBe(String key, String value) {
        var res = response.getBody().jsonPath().get(key);
        assertThat(res, equalToObject(value));
    }
}
