package SampleApi;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class GetRequestTest {

    @Test
    public void validateSampleRequest() {

        Response response = given()
                .when()
                .get("https://echo.free.beeceptor.com/sample-request?author=beeceptor")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Print response
        response.prettyPrint();

        // Validate path
        String path = response.jsonPath().getString("path");
        System.out.println("Path: " + path);
        assert path != null;

        // Validate IP
        String ip = response.jsonPath().getString("ip");
        System.out.println("IP: " + ip);
        assert ip != null;

        // Validate Headers
        Object headers = response.jsonPath().get("headers");
        System.out.println("Headers: " + headers);
        assert headers != null;
    }
}