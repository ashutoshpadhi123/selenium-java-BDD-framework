package api;

import Config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenGetter {

    private static String token;

    public static String getBearerToken() {
        if (token == null) {
            // Base URL picked from environment properties
            RestAssured.baseURI = ConfigReader.get("base.url");

            Response response = given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("grant_type", ConfigReader.get("grant_type"))
                    .formParam("client_id", ConfigReader.get("client_id"))
                    .formParam("client_secret", ConfigReader.get("client_secret"))
                    .formParam("username", ConfigReader.get("username"))
                    .formParam("password", ConfigReader.get("password"))
                    .when()
                    .post(ConfigReader.get("token_endpoint"));

            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to get token: " + response.asPrettyString());
            }

            token = response.jsonPath().getString("access_token");
        }
        return token;
    }
}