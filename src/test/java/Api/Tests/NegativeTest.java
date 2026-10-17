package Api.Tests;


import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;


public class NegativeTest extends BaseTest {

    @Test
    void shouldRejectRequestWithoutToken() {
        given()
                .header("Accept", "application/json")
                .when()
                .get("/api/admin/projects?fields=id,name,shortName")
                .then()
                .statusCode(anyOf(is(401), is(403)));
    }

}
