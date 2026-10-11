package Api;


import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;


public class NegativeApiTest extends BaseApiTest {

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
