package org.automation.utilities;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonRestUtils {

    private RequestSpecification request;
    private Gson gson;

    public CommonRestUtils(String baseUri) {
        gson = new Gson();
        request = RestAssured.given();
        request.baseUri(baseUri);
    }

    public Response request(String basePath, Method crudMethod) {
        return request.basePath(basePath).log().all().request(crudMethod);
    }

    public Object mapJsonToPojo(String jsonContent, Class classToMap) {
        return gson.fromJson(jsonContent, classToMap);
    }
}
