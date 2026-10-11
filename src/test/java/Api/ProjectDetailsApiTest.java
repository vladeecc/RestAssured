package Api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProjectDetailsApiTest extends BaseApiTest {

    @Test
    void shouldGetProjectDetails() {
        String projectId =
                given()
                        .header("Authorization", "Bearer " + getToken())
                        .header("Accept", "application/json")
                        .queryParam("fields", "id,name,shortName")
                        .when()
                        .get("/api/admin/projects")
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getString("[0].id");
        assert projectId != null && !projectId.isBlank()
                : "Project ID should not be empty";

        given()
                .header("Authorization", "Bearer " + getToken())
                .header("Accept", "application/json")
                .queryParam("fields", "id,name,shortName")
                .when()
                .get("/api/admin/projects/" + projectId)
                .then()
                .statusCode(200)
                .body("id", equalTo(projectId))
                .body("name", not(emptyOrNullString()));
    }
}
