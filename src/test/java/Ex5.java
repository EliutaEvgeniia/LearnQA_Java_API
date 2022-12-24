import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Ex5 {

    @Test
    public void testRestAssured(){

        JsonPath response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        List<String> message = response.getList("messages.message",String.class);
        System.out.println(message.get(1));

    }
}
