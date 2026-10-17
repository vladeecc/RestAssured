package Api.Tests;

import Api.Specifications.ApiSpecifications;
import Api.Endpoints.ProjectEndpoints;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;

public class ProjectDetailsTest extends BaseTest {

    @Test
    void shouldGetProjectDetails() {
        Response response =
                given()
                        .spec(ApiSpecifications.baseRequestSpec())
                        .queryParam("fields", "id,name,shortName")
                        .when()
                        .get(ProjectEndpoints.PROJECTS)
                        .then()
                        .spec(ApiSpecifications.successResponseSpec())
                .extract()
                .response();

        String projectId = response.jsonPath().getString("[0].id");

        assertNotNull(projectId, "Project ID should not be null");
        assertFalse(projectId.isBlank(), "Project ID should not be empty");

        given()
                .spec(ApiSpecifications.baseAuthorization(getToken()))
                .when()
                .get(ProjectEndpoints.projectById(projectId))
                .then()
                .spec(ApiSpecifications.successResponseSpec())
                .body("id", equalTo(projectId))
                .body("name", not(emptyOrNullString()));

        System.out.println("PASS: shouldGetProjectDetails");
    }
}
