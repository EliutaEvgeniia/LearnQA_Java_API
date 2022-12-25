import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


public class Ex7 {

    @Test
    public void testRestAssured(){
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();

       int statusCode = response.getStatusCode();
       while (statusCode == 301) {
           String location = response.getHeader("Location");
           response = RestAssured
                   .given()
                   .redirects()
                   .follow(false)
                   .when()
                   .get(location)
                   .andReturn();
           statusCode = response.getStatusCode();
           System.out.println(location);
       }
        System.out.println(statusCode);

    }
}
