package Api;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectCSVTest extends BaseApiTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/projects.csv",  numLinesToSkip = 1)
    void projectDataShouldBeValid(String id, String name, String shortname) {
        given()
                .header("Authorization", "Bearer " + getToken())
                .header("Accept", "application/json")
                .queryParam("fields", "id,name,shortName")
                .when()
                .get("/api/admin/projects/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("shortName", equalTo(shortname))
                .body("name", not(emptyOrNullString()));

        System.out.println("PASS: " + name);
    }
}
