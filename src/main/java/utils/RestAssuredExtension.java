package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredExtension {
    private RequestSpecBuilder specBuilder = new RequestSpecBuilder();
    private String url;
    private String method;
    public static RequestSpecification request;

    public RestAssuredExtension(String uri, String method, String token) {
        //Arrange
        this.url = "http://localhost:3000/" + uri;
        this.method = method;

        if (token != null) {
            specBuilder.addHeader("Authorization", "Bearer " + token);
        }
        specBuilder.setContentType(ContentType.JSON);
        var requestSpec = specBuilder.build();
        request = RestAssured.given().spec(requestSpec);
    }

    public ResponseOptions<Response> executeRequests() {

        if (this.method.equalsIgnoreCase(APIConstants.ApiMethods.GET)) {
            return request.get(this.url);
        } else if (method.equalsIgnoreCase(APIConstants.ApiMethods.POST)) {
            return request.post(this.url);
        } else if (method.equalsIgnoreCase(APIConstants.ApiMethods.PUT)) {
            return request.put(this.url);
        } else if (method.equalsIgnoreCase(APIConstants.ApiMethods.DELETE)) {
            return request.delete(this.url);
        } else return null;
    }

    public String getAuthenticationToken(Object body) {
        specBuilder.setBody(body);
        return executeRequests().getBody().jsonPath().get("access_token");
    }

    public ResponseOptions<Response> executeWithPathParam(Map<String, String> path) {
        //Act
        specBuilder.addPathParams(path);
        return executeRequests();
    }

    public ResponseOptions<Response> executeWithQueryParam(Map<String, String> path) {
        //Act
        specBuilder.addQueryParams(path);
        return executeRequests();
    }

    public ResponseOptions<Response> executeWithBody(Map<String, String> body) {
        //Act
        specBuilder.addQueryParams(body);
        return executeRequests();
    }

    public ResponseOptions<Response> executePostWithBody(Map<String, String> body) {
        //Act
        request.body(body);
        return executeRequests();
    }

    public ResponseOptions<Response> executePutWithBody(Map<String, String> body) {
        //Act
        request.body(body);
        return executeRequests();
    }

}
