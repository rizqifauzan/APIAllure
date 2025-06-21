import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("API Testing")
@Feature("JSONPlaceholder API Tests")
public class APITest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Test
    @Story("GET Request Test")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify GET /posts/1 API returns expected response")
    public void testGetRequest() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("userId", equalTo(1))
                .body("id", equalTo(1))
                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }

    @Test
    @Story("POST Request Test")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify POST /posts API creates a new post successfully")
    public void testPostRequest() {
        String requestBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract().response();

        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
    }
}