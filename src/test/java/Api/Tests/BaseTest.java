package Api.Tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost:8080";

        String token = System.getenv("YOUTRACK_TOKEN");

        System.out.println(
                "YOUTRACK_TOKEN available: " +
                        (token != null && !token.isBlank())
        );
    }

    protected String getToken() {
        String token = System.getenv("YOUTRACK_TOKEN");

        if (token == null || token.isBlank()) {
            throw new IllegalStateException(
                    "YOUTRACK_TOKEN is missing or empty"
            );
        }

        return token;
    }
}