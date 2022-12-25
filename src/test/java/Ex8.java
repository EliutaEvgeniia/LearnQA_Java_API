import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;


public class Ex8 {

    @Test
    public void testRestAssured() throws Exception {
        JsonPath response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();
        String token = response.getString("token");
        int time = response.getInt("seconds");

        JsonPath notReadyResponse = RestAssured
                .given()
                .queryParam("token",token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();
        String status = notReadyResponse.getString("status");
        System.out.println(status);

       Thread.sleep(time * 1000L);

        JsonPath readyResponse = RestAssured
                .given()
                .queryParam("token",token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();
        String readyStatus = readyResponse.getString("status");
        String result = readyResponse.getString("result");
        System.out.println(readyStatus);
        System.out.println(result);



    }
}
