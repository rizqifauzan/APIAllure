import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("Advanced API Testing")
@Feature("Allure Integration")
public class AllureAdvancedTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(description = "Verify GET /posts/1 returns expected data")
    @Story("GET Request Validation")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-001")
    @Issue("BUG-101")
    @Link(name = "API Docs", url = "https://jsonplaceholder.typicode.com")
    public void testGetRequest() {
        stepLog("Mengirim permintaan GET ke /posts/1");

        Response response = given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .extract().response();

        attachResponse(response.asString());
        Assert.assertEquals(response.jsonPath().getString("userId"), "1");
    }

    @Test(description = "Verify POST /posts creates new data")
    @Story("POST Request Validation")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-002")
    public void testPostRequest() {
        String requestBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";
        stepLog("Mengirim permintaan POST ke /posts");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract().response();

        attachResponse(response.asString());
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
    }

    @Step("Logging: {0}")
    private void stepLog(String message) {
        // Fungsi ini menambahkan log ke laporan Allure
    }

    @Attachment(value = "API Response", type = "text/plain")
    private String attachResponse(String response) {
        return response;
    }
}
