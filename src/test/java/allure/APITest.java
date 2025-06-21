package allure;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class APITest {

    @Test
    @Epic("catfact")
    @Feature("random fact")
    @Story("Get Random fact of cat")
    @Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    @Severity(CRITICAL)
    @Owner("Rizqi Fauzan")
    public void testCatFact(){
       Response response = RestAssured.given()
                .get("https://catfact.ninja/fact");

       attachResponse(response.getBody().asString());
       response.then().statusCode(200);

       String fact = response.jsonPath().get("fact");
       Assert.assertNotNull(fact);

       Integer length = response.jsonPath().get("length");
       Assert.assertNotNull(length);
    }


    @Attachment(value = "API Response", type = "text/plain")
    private String attachResponse(String response) {
        return response;
    }

}
