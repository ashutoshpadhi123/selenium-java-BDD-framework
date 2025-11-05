package api;

import Config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    private static final String baseUri = ConfigReader.get("base.url");
    private static final boolean useAuth = Boolean.parseBoolean(ConfigReader.get("use_auth"));

    private static final RequestSpecification reqSpec;

    static {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        if (useAuth) {
            builder.addHeader("Authorization", "Bearer " + TokenGetter.getBearerToken());
        }

        reqSpec = builder.build();
    }

    private static final ResponseSpecification resSpec = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .build();

    public static Response get(String endpoint) {
        return given().spec(reqSpec)
                .when().get(endpoint)
                .then().spec(resSpec)
                .extract().response();
    }

    public static Response post(String endpoint, Object body) {
        return given().spec(reqSpec)
                .body(body)
                .when().post(endpoint)
                .then().spec(resSpec)
                .extract().response();
    }

    public static Response put(String endpoint, Object body) {
        return given().spec(reqSpec)
                .body(body)
                .when().put(endpoint)
                .then().spec(resSpec)
                .extract().response();
    }

    public static Response delete(String endpoint) {
        return given().spec(reqSpec)
                .when().delete(endpoint)
                .then().spec(resSpec)
                .extract().response();
    }
}
