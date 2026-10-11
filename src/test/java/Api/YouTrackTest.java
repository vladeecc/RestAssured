package Api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


public class YouTrackTest extends BaseApiTest {

    @Test
    void youTrackOpen() {
        given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .body(notNullValue());
    }
}