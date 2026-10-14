package Api;

import io.restassured.common.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
public class ProjectApiTest extends  BaseApiTest {

    @Test
    void shouldGetProjects() {
        System.out.println("START: shouldGetProjects");

        List<ProjectDto> projects =
                given()
                        .spec(ApiSpecifications.baseRequestSpec())
                        .header("Authorization", "Bearer " + getToken())
                        .queryParam("fields", "id,name,shortName")
                        .when()
                        .get(ProjectEndpoints.PROJECTS)
                        .then()
                        .spec(ApiSpecifications.successResponseSpec())
                        .extract()
                        .as(new TypeRef<List<ProjectDto>>() {});

        assertNotNull(projects, "Projects list should not be null");

        System.out.println("Projects received: " + projects.size());
        System.out.println("PASS: shouldGetProjects");

        for (ProjectDto project : projects) {
            System.out.println(
                    "ID: " + project.getId()
                            + " | Name: " + project.getName()
                            + " | Short name: " + project.getShortName()
            );
        }
    }
}
