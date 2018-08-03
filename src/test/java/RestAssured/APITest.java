package RestAssured;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;


public class APITest {
    private static String season = "2016";
    private static String driver = "max_verstappen";

    private final String website = "http://ergast.com";

    @Test
    public void vailidateResponseCodeLastResults() {
        RestAssured.given().when().get("http://ergast.com/api/f1/current/last/results").then().statusCode(200);
    }

    @Test
    public void validateContent() {
        given().when().get("http://ergast.com/api/f1/2016/drivers/max_verstappen/results.json").
                then().assertThat().body("MRData.RaceTable.driverId", containsString(("max")));
    }

    @Test
    public void testWithParameters() {
        given().pathParam("season", 2017).when().get("http://ergast.com/api/f1/{season}/drivers/max_verstappen/results").then().statusCode(200);
    }

    @Test
    public void testWithParametersVariables() {
        given().pathParam("season", season).pathParam("driver", driver).
                when().get("http://ergast.com/api/f1/{season}/drivers/{driver}/results").then().statusCode(200);
    }

}
