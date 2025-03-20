package postman.echo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRawTextTest {

    @Test
    public void testResponseIsOkAndBodyMatches() {
        RestAssured.baseURI = "https://postman-echo.com";
        String expectedResponseBody = "This is expected to be sent back as part of response body.";
        given()
                .header("Content-Type", "application/json")
                .body("{\"test\": \"value\"}")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(expectedResponseBody));
    }

    @Test
    public void testTestingMethod() {
        RestAssured.baseURI = "https://postman-echo.com";
        String requestBody = "{\"test\": \"value\"}";
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/post");
        System.out.println("rahul " + response.getBody().asString());
        response.then()
                .body("json.test", equalTo("value"));
    }
}