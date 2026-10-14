package Api;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;

class ProjectCsvTest extends BaseApiTest {

    @ParameterizedTest
    @CsvFileSource(
            resources = "/projects.csv",
            numLinesToSkip = 1
    )
    void projectShouldMatchCsvData(
            String id,
            String name,
            String shortName) {

        given()
                .spec(ApiSpecifications.baseRequestSpec())
                .header("Authorization", "Bearer " + getToken())
                .queryParam("fields", "id,name,shortName")
                .when()
                .get(ProjectEndpoints.projectById(id))
                .then()
                .spec(ApiSpecifications.successResponseSpec())
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("shortName", equalTo(shortName))
                .body("name", not(emptyOrNullString()));

        System.out.println("PASS: " + name);
    }
}
